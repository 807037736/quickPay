package org.apache.cxf.endpoint;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.wsdl.extensions.soap.SOAPBinding;
import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.Binding;
import org.apache.cxf.common.i18n.UncheckedException;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.AbstractBasicInterceptorProvider;
import org.apache.cxf.interceptor.ClientOutFaultObserver;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.ExchangeImpl;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.phase.PhaseChainCache;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.phase.PhaseManager;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.InterfaceInfo;
import org.apache.cxf.service.model.MessageInfo;
import org.apache.cxf.service.model.OperationInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.MessageObserver;
import org.apache.cxf.workqueue.SynchronousExecutor;
import org.apache.cxf.wsdl11.WSDLServiceFactory;

public class ClientImpl extends AbstractBasicInterceptorProvider implements
		Client, Retryable, MessageObserver {
	public static final String THREAD_LOCAL_REQUEST_CONTEXT = "thread.local.request.context";
	public static final String FINISHED = "exchange.finished";
	private static final Logger LOG = LogUtils.getL7dLogger(ClientImpl.class);
	protected Bus bus;
	protected ConduitSelector conduitSelector;
	protected ClientOutFaultObserver outFaultObserver;
	protected int synchronousTimeout = 60000;

	protected PhaseChainCache outboundChainCache = new PhaseChainCache();
	protected PhaseChainCache inboundChainCache = new PhaseChainCache();

	protected Map<String, Object> currentRequestContext = new ConcurrentHashMap();
	protected Map<Thread, EchoContext> requestContext = Collections
			.synchronizedMap(new WeakHashMap());

	protected Map<Thread, Map<String, Object>> responseContext = Collections
			.synchronizedMap(new WeakHashMap());
	protected Executor executor;

	public ClientImpl(Bus b, Endpoint e) {
		this(b, e, (ConduitSelector) null);
	}

	public ClientImpl(Bus b, Endpoint e, Conduit c) {
		this(b, e, new PreexistingConduitSelector(c));
	}

	public ClientImpl(Bus b, Endpoint e, ConduitSelector sc) {
		this.bus = b;
		this.outFaultObserver = new ClientOutFaultObserver(this.bus);
		getConduitSelector(sc).setEndpoint(e);
		notifyLifecycleManager();
	}

	public ClientImpl(URL wsdlUrl) {
		this(BusFactory.getThreadDefaultBus(), wsdlUrl, (QName) null, null,
				SimpleEndpointImplFactory.getSingleton());
	}

	public ClientImpl(URL wsdlUrl, QName port) {
		this(BusFactory.getThreadDefaultBus(), wsdlUrl, (QName) null, port,
				SimpleEndpointImplFactory.getSingleton());
	}

	public ClientImpl(Bus bus, URL wsdlUrl, QName service, QName port) {
		this(bus, wsdlUrl, service, port, SimpleEndpointImplFactory
				.getSingleton());
	}

	public ClientImpl(Bus bus, URL wsdlUrl, QName service, QName port,
			EndpointImplFactory endpointImplFactory) {
		this.bus = bus;
		this.outFaultObserver = new ClientOutFaultObserver(bus);

		WSDLServiceFactory sf = service == null ? new WSDLServiceFactory(bus,
				wsdlUrl) : new WSDLServiceFactory(bus, wsdlUrl, service);

		Service svc = sf.create();
		EndpointInfo epfo = findEndpoint(svc, port);
		try {
			if (endpointImplFactory != null)
				getConduitSelector().setEndpoint(
						endpointImplFactory.newEndpointImpl(bus, svc, epfo));
			else
				getConduitSelector().setEndpoint(
						new EndpointImpl(bus, svc, epfo));
		} catch (EndpointException epex) {
			throw new IllegalStateException("Unable to create endpoint: "
					+ epex.getMessage(), epex);
		}
		notifyLifecycleManager();
	}

	public ClientImpl(Bus bus, Service svc, QName port,
			EndpointImplFactory endpointImplFactory) {
		this.bus = bus;
		this.outFaultObserver = new ClientOutFaultObserver(bus);
		EndpointInfo epfo = findEndpoint(svc, port);
		try {
			if (endpointImplFactory != null)
				getConduitSelector().setEndpoint(
						endpointImplFactory.newEndpointImpl(bus, svc, epfo));
			else
				getConduitSelector().setEndpoint(
						new EndpointImpl(bus, svc, epfo));
		} catch (EndpointException epex) {
			throw new IllegalStateException("Unable to create endpoint: "
					+ epex.getMessage(), epex);
		}
		notifyLifecycleManager();
	}

	public Bus getBus() {
		return this.bus;
	}

	public void destroy() {
		if (this.bus == null) {
			return;
		}
		ClientLifeCycleManager mgr = (ClientLifeCycleManager) this.bus
				.getExtension(ClientLifeCycleManager.class);
		if (null != mgr) {
			mgr.clientDestroyed(this);
		}

		if (this.conduitSelector != null) {
			if ((this.conduitSelector instanceof Closeable))
				try {
					((Closeable) this.conduitSelector).close();
				} catch (IOException e) {
				}
			else {
				getConduit().close();
			}
		}

		this.bus = null;
		this.conduitSelector = null;
		this.outFaultObserver = null;
		this.outboundChainCache = null;
		this.inboundChainCache = null;

		this.currentRequestContext = null;
		this.requestContext.clear();
		this.requestContext = null;
		this.responseContext.clear();
		this.responseContext = null;
		this.executor = null;
	}

	private void notifyLifecycleManager() {
		ClientLifeCycleManager mgr = (ClientLifeCycleManager) this.bus
				.getExtension(ClientLifeCycleManager.class);
		if (null != mgr)
			mgr.clientCreated(this);
	}

	private EndpointInfo findEndpoint(Service svc, QName port) {
		EndpointInfo epfo;
		if (port != null) {
			epfo = svc.getEndpointInfo(port);
			if (epfo == null)
				throw new IllegalArgumentException("The service "
						+ svc.getName() + " does not have an endpoint " + port
						+ ".");
		} else {
			epfo = null;
			for (ServiceInfo svcfo : svc.getServiceInfos()) {
				for (EndpointInfo e : svcfo.getEndpoints()) {
					BindingInfo bfo = e.getBinding();

					if (bfo.getBindingId().equals(
							"http://schemas.xmlsoap.org/wsdl/soap/")) {
						for (Object o : (Object[]) bfo.getExtensors().get()) {
							if ((o instanceof SOAPBinding)) {
								SOAPBinding soapB = (SOAPBinding) o;
								if (soapB.getTransportURI().equals(
										"http://schemas.xmlsoap.org/soap/http")) {
									epfo = e;
									break;
								}
							}
						}
					}
				}
			}
			if (epfo == null) {
				throw new UnsupportedOperationException(
						"Only document-style SOAP 1.1 http are supported for auto-selection of endpoint; none were found.");
			}

		}

		return epfo;
	}

	public Endpoint getEndpoint() {
		return getConduitSelector().getEndpoint();
	}

	public Map<String, Object> getRequestContext() {
		if (isThreadLocalRequestContext()) {
			if (!this.requestContext.containsKey(Thread.currentThread())) {
				this.requestContext.put(Thread.currentThread(),
						new EchoContext(this.currentRequestContext));
			}
			return (Map) this.requestContext.get(Thread.currentThread());
		}
		return this.currentRequestContext;
	}

	public Map<String, Object> getResponseContext() {
		if (!this.responseContext.containsKey(Thread.currentThread())) {
			this.responseContext.put(Thread.currentThread(), new HashMap());
		}
		return (Map) this.responseContext.get(Thread.currentThread());
	}

	public boolean isThreadLocalRequestContext() {
		if (this.currentRequestContext
				.containsKey("thread.local.request.context")) {
			Object o = this.currentRequestContext
					.get("thread.local.request.context");
			boolean local = false;
			if ((o instanceof Boolean))
				local = ((Boolean) o).booleanValue();
			else {
				local = Boolean.parseBoolean(o.toString());
			}
			return local;
		}
		return false;
	}

	public void setThreadLocalRequestContext(boolean b) {
		this.currentRequestContext.put("thread.local.request.context",
				Boolean.valueOf(b));
	}

	public Object[] invoke(BindingOperationInfo oi, Object[] params)
			throws Exception {
		return invoke(oi, params, null);
	}

	public Object[] invoke(String operationName, Object[] params)
			throws Exception {
		QName q = new QName(getEndpoint().getService().getName()
				.getNamespaceURI(), operationName);

		return invoke(q, params);
	}

	public Object[] invoke(QName operationName, Object[] params)
			throws Exception {
		BindingOperationInfo op = getEndpoint().getEndpointInfo().getBinding()
				.getOperation(operationName);
		if (op == null) {
			throw new UncheckedException(
					new org.apache.cxf.common.i18n.Message("NO_OPERATION", LOG,
							new Object[] { operationName }));
		}

		if (op.isUnwrappedCapable()) {
			op = op.getUnwrappedOperation();
		}

		return invoke(op, params);
	}

	public Object[] invokeWrapped(String operationName, Object[] params)
			throws Exception {
		QName q = new QName(getEndpoint().getService().getName()
				.getNamespaceURI(), operationName);

		return invokeWrapped(q, params);
	}

	public Object[] invokeWrapped(QName operationName, Object[] params)
			throws Exception {
		BindingOperationInfo op = getEndpoint().getEndpointInfo().getBinding()
				.getOperation(operationName);
		if (op == null) {
			throw new UncheckedException(
					new org.apache.cxf.common.i18n.Message("NO_OPERATION", LOG,
							new Object[] { operationName }));
		}

		return invoke(op, params);
	}

	public Object[] invoke(BindingOperationInfo oi, Object[] params,
			Exchange exchange) throws Exception {
		Map context = new HashMap();
		Map resp = new HashMap();
		Map req = new HashMap(getRequestContext());
		context.put("ResponseContext", resp);
		context.put("RequestContext", req);
		try {
			Object[] arrayOfObject = invoke(oi, params, context, exchange);
			return arrayOfObject;
		} finally {
			this.responseContext.put(Thread.currentThread(), resp);
		}
	}

	public Object[] invoke(BindingOperationInfo oi, Object[] params,
			Map<String, Object> context) throws Exception {
		try {
			Object[] arrayOfObject = invoke(oi, params, context,
					(Exchange) null);
			Map resp;
			return arrayOfObject;
		} finally {
			if (context != null) {
				Map resp = CastUtils.cast((Map) context.get("ResponseContext"));
				if (resp != null)
					this.responseContext.put(Thread.currentThread(), resp);
			}
		}
	}

	public void invoke(ClientCallback callback, String operationName,
			Object[] params) throws Exception {
		QName q = new QName(getEndpoint().getService().getName()
				.getNamespaceURI(), operationName);
		invoke(callback, q, params);
	}

	public void invoke(ClientCallback callback, QName operationName,
			Object[] params) throws Exception {
		BindingOperationInfo op = getEndpoint().getEndpointInfo().getBinding()
				.getOperation(operationName);
		if (op == null) {
			throw new UncheckedException(
					new org.apache.cxf.common.i18n.Message("NO_OPERATION", LOG,
							new Object[] { operationName }));
		}

		if (op.isUnwrappedCapable()) {
			op = op.getUnwrappedOperation();
		}

		invoke(callback, op, params);
	}

	public void invokeWrapped(ClientCallback callback, String operationName,
			Object[] params) throws Exception {
		QName q = new QName(getEndpoint().getService().getName()
				.getNamespaceURI(), operationName);
		invokeWrapped(callback, q, params);
	}

	public void invokeWrapped(ClientCallback callback, QName operationName,
			Object[] params) throws Exception {
		BindingOperationInfo op = getEndpoint().getEndpointInfo().getBinding()
				.getOperation(operationName);
		if (op == null) {
			throw new UncheckedException(
					new org.apache.cxf.common.i18n.Message("NO_OPERATION", LOG,
							new Object[] { operationName }));
		}

		invoke(callback, op, params);
	}

	public void invoke(ClientCallback callback, BindingOperationInfo oi,
			Object[] params) throws Exception {
		invoke(callback, oi, params, null, null);
	}

	public void invoke(ClientCallback callback, BindingOperationInfo oi,
			Object[] params, Map<String, Object> context) throws Exception {
		invoke(callback, oi, params, context, null);
	}

	public void invoke(ClientCallback callback, BindingOperationInfo oi,
			Object[] params, Exchange exchange) throws Exception {
		invoke(callback, oi, params, null, exchange);
	}

	public void invoke(ClientCallback callback, BindingOperationInfo oi,
			Object[] params, Map<String, Object> context, Exchange exchange)
			throws Exception {
		doInvoke(callback, oi, params, context, exchange);
	}

	public Object[] invoke(BindingOperationInfo oi, Object[] params,
			Map<String, Object> context, Exchange exchange) throws Exception {
		return doInvoke(null, oi, params, context, exchange);
	}

	private Object[] doInvoke(ClientCallback callback, BindingOperationInfo oi,
			Object[] params, Map<String, Object> context, Exchange exchange)
			throws Exception {
		Bus origBus = BusFactory.getThreadDefaultBus(false);
		ClassLoader origLoader = Thread.currentThread().getContextClassLoader();
		try {
			BusFactory.setThreadDefaultBus(this.bus);
			ClassLoader loader = (ClassLoader) this.bus
					.getExtension(ClassLoader.class);
			if (loader != null) {
				Thread.currentThread().setContextClassLoader(loader);
			}
			if (exchange == null) {
				exchange = new ExchangeImpl();
			}
			exchange.setSynchronous(callback == null);
			Endpoint endpoint = getEndpoint();
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("Invoke, operation info: " + oi + ", params: "
						+ Arrays.toString(params));
			}
			org.apache.cxf.message.Message message = endpoint.getBinding()
					.createMessage();

			Map reqContext = null;
			Map resContext = null;
			if (context == null) {
				context = new HashMap();
			}
			reqContext = CastUtils.cast((Map) context.get("RequestContext"));
			resContext = CastUtils.cast((Map) context.get("ResponseContext"));
			if (reqContext == null) {
				reqContext = new HashMap(getRequestContext());
				context.put("RequestContext", reqContext);
			}
			if (resContext == null) {
				resContext = new HashMap();
				context.put("ResponseContext", resContext);
			}

			message.put("org.apache.cxf.invocation.context", context);
			setContext(reqContext, message);
			if (null != reqContext) {
				exchange.putAll(reqContext);
			}

			setParameters(params, message);

			if (null != oi) {
				exchange.setOneWay(oi.getOutput() == null);
			}

			exchange.setOutMessage(message);
			exchange.put(ClientCallback.class, callback);

			setOutMessageProperties(message, oi);
			setExchangeProperties(exchange, endpoint, oi);

			PhaseInterceptorChain chain = setupInterceptorChain(endpoint);
			message.setInterceptorChain(chain);
			chain.setFaultObserver(this.outFaultObserver);
			prepareConduitSelector(message);

			modifyChain(chain, message, false);
			try {
				chain.doIntercept(message);
			} catch (Fault fault) {
				enrichFault(fault);
				throw fault;
			}
			Object[] fault;
			if (callback != null) {
				fault = null;
				return fault;
			}
			fault = processResult(message, exchange, oi, resContext);
			return fault;
		} finally {
			Thread.currentThread().setContextClassLoader(origLoader);
			BusFactory.setThreadDefaultBus(origBus);
		}
	}

	private void enrichFault(Fault fault) {
		if (((fault.getCause().getCause() instanceof IOException))
				|| ((fault.getCause() instanceof IOException))) {
			String soap11NS = "http://schemas.xmlsoap.org/soap/envelope/";
			String soap12NS = "http://www.w3.org/2003/05/soap-envelope";
			QName faultCode = fault.getFaultCode();

			if ((faultCode.getNamespaceURI().equals(soap11NS))
					&& (faultCode.getLocalPart().equals("Client"))) {
				faultCode = new QName(soap11NS, "Server");
				fault.setFaultCode(faultCode);
			}
			if ((faultCode.getNamespaceURI().equals(soap12NS))
					&& (faultCode.getLocalPart().equals("Sender"))) {
				faultCode = new QName(soap12NS, "Receiver");
				fault.setFaultCode(faultCode);
			}
		}
	}

	protected Object[] processResult(org.apache.cxf.message.Message message,
			Exchange exchange, BindingOperationInfo oi,
			Map<String, Object> resContext) throws Exception {
		Exception ex = null;

		if (!message.get("org.apache.cxf.message.inbound").equals(Boolean.TRUE)) {
			ex = (Exception) message.getContent(Exception.class);
		}
		boolean mepCompleteCalled = false;
		if (ex != null) {
			getConduitSelector().complete(exchange);
			mepCompleteCalled = true;
			if (message.getContent(Exception.class) != null) {
				throw ex;
			}
		}
		ex = (Exception) message.getExchange().get(Exception.class);
		if (ex != null) {
			if (!mepCompleteCalled) {
				getConduitSelector().complete(exchange);
			}
			throw ex;
		}

		Integer responseCode = (Integer) exchange
				.get(org.apache.cxf.message.Message.RESPONSE_CODE);
		if ((null != responseCode) && (202 == responseCode.intValue())) {
			Endpoint ep = exchange.getEndpoint();
			if ((null != ep)
					&& (null != ep.getEndpointInfo())
					&& (null == ep
							.getEndpointInfo()
							.getProperty(
									"org.apache.cxf.ws.addressing.MAPAggregator.decoupledDestination"))) {
				return null;
			}

		}

		if ((oi != null) && (!oi.getOperationInfo().isOneWay())) {
			synchronized (exchange) {
				waitResponse(exchange);
			}

		}

		Boolean keepConduitAlive = (Boolean) exchange.get("KeepConduitAlive");
		if ((keepConduitAlive == null) || (!keepConduitAlive.booleanValue())) {
			getConduitSelector().complete(exchange);
		}

		List resList = null;
		org.apache.cxf.message.Message inMsg = exchange.getInMessage();
		if (inMsg != null) {
			if (null != resContext) {
				resContext.putAll(inMsg);

				resContext.remove("org.apache.cxf.invocation.context");
				this.responseContext.put(Thread.currentThread(), resContext);
			}
			resList = (List) inMsg.getContent(List.class);
		}

		ex = getException(exchange);

		if (ex != null) {
			throw ex;
		}

		if (resList != null) {
			return resList.toArray();
		}

		return null;
	}

	protected Exception getException(Exchange exchange) {
		if (exchange.getInFaultMessage() != null)
			return (Exception) exchange.getInFaultMessage().getContent(
					Exception.class);
		if (exchange.getOutFaultMessage() != null)
			return (Exception) exchange.getOutFaultMessage().getContent(
					Exception.class);
		if (exchange.getInMessage() != null) {
			return (Exception) exchange.getInMessage().getContent(
					Exception.class);
		}
		return null;
	}

	protected void setContext(Map<String, Object> ctx,
			org.apache.cxf.message.Message message) {
		if (ctx != null) {
			message.putAll(ctx);
			if (LOG.isLoggable(Level.FINE))
				LOG.fine("set requestContext to message be" + ctx);
		}
	}

	protected void waitResponse(Exchange exchange) throws IOException {
		int remaining = this.synchronousTimeout;
		while ((!Boolean.TRUE.equals(exchange.get("exchange.finished")))
				&& (remaining > 0)) {
			long start = System.currentTimeMillis();
			try {
				exchange.wait(remaining);
			} catch (InterruptedException ex) {
			}
			long end = System.currentTimeMillis();
			remaining -= (int) (end - start);
		}
		if (!Boolean.TRUE.equals(exchange.get("exchange.finished"))) {
			LogUtils.log(LOG, Level.WARNING, "RESPONSE_TIMEOUT",
					((OperationInfo) exchange.get(OperationInfo.class))
							.getName().toString());

			String msg = new org.apache.cxf.common.i18n.Message(
					"RESPONSE_TIMEOUT", LOG,
					new Object[] { ((OperationInfo) exchange
							.get(OperationInfo.class)).getName().toString() })
					.toString();

			throw new IOException(msg);
		}
	}

	protected void setParameters(Object[] params,
			org.apache.cxf.message.Message message) {
		MessageContentsList contents = new MessageContentsList(params);
		message.setContent(List.class, contents);
	}

	public void onMessage(org.apache.cxf.message.Message message) {
		Endpoint endpoint = message.getExchange().getEndpoint();
		if (endpoint == null) {
			endpoint = getConduitSelector().getEndpoint();
			message.getExchange().put(Endpoint.class, endpoint);
		}
		message = endpoint.getBinding().createMessage(message);
		message.getExchange().setInMessage(message);
		message.put("org.apache.cxf.client", Boolean.TRUE);
		message.put("org.apache.cxf.message.inbound", Boolean.TRUE);
		PhaseManager pm = (PhaseManager) this.bus
				.getExtension(PhaseManager.class);

		List i1 = this.bus.getInInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by bus: " + i1);
		}
		List i2 = getInInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by client: " + i2);
		}
		List i3 = endpoint.getInInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by endpoint: " + i3);
		}
		List i4 = endpoint.getBinding().getInInterceptors();
		if (LOG.isLoggable(Level.FINE))
			LOG.fine("Interceptors contributed by binding: " + i4);
		PhaseInterceptorChain chain;
		if ((endpoint.getService().getDataBinding() instanceof InterceptorProvider)) {
			InterceptorProvider p = (InterceptorProvider) endpoint.getService()
					.getDataBinding();
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("Interceptors contributed by databinging: "
						+ p.getInInterceptors());
			}
			chain = this.inboundChainCache.get(pm.getInPhases(), i1, i2, i3,
					i4, p.getInInterceptors());
		} else {
			chain = this.inboundChainCache
					.get(pm.getInPhases(), i1, i2, i3, i4);
		}
		message.setInterceptorChain(chain);

		chain.setFaultObserver(this.outFaultObserver);
		modifyChain(chain, message, true);
		modifyChain(chain, message.getExchange().getOutMessage(), true);

		Bus origBus = BusFactory.getThreadDefaultBus(false);
		BusFactory.setThreadDefaultBus(this.bus);

		ClientCallback callback = (ClientCallback) message.getExchange().get(
				ClientCallback.class);
		try {
			if (callback != null) {
				if (callback.isCancelled()) {
					getConduitSelector().complete(message.getExchange());
					return;
				}
				callback.start(message);
			}

			String startingAfterInterceptorID = (String) message
					.get("starting_after_interceptor_id");

			String startingInterceptorID = (String) message
					.get("starting_at_interceptor_id");

			if (startingAfterInterceptorID != null) {
				chain.doInterceptStartingAfter(message,
						startingAfterInterceptorID);
			} else if (startingInterceptorID != null) {
				chain.doInterceptStartingAt(message, startingInterceptorID);
			} else if (message.getContent(Exception.class) != null) {
				this.outFaultObserver.onMessage(message);
			} else {
				callback = (ClientCallback) message.getExchange().get(
						ClientCallback.class);

				if ((callback != null) && (!isPartialResponse(message))) {
					try {
						chain.doIntercept(message);
					} catch (Throwable error) {
						message.getExchange().setInMessage(message);
						Map resCtx = CastUtils.cast((Map) message.getExchange()
								.getOutMessage()
								.get("org.apache.cxf.invocation.context"));

						resCtx = CastUtils.cast((Map) resCtx
								.get("ResponseContext"));

						if (resCtx != null) {
							this.responseContext.put(Thread.currentThread(),
									resCtx);
						}
						callback.handleException(resCtx, error);
					}
				} else {
					chain.doIntercept(message);
				}

			}

			callback = (ClientCallback) message.getExchange().get(
					ClientCallback.class);

			if ((callback != null) && (!isPartialResponse(message))) {
				message.getExchange().setInMessage(message);
				Map resCtx = CastUtils.cast((Map) message.getExchange()
						.getOutMessage()
						.get("org.apache.cxf.invocation.context"));

				resCtx = CastUtils.cast((Map) resCtx.get("ResponseContext"));
				if (resCtx != null)
					this.responseContext.put(Thread.currentThread(), resCtx);
				try {
					Object[] obj = processResult(message,
							message.getExchange(), null, resCtx);

					callback.handleResponse(resCtx, obj);
				} catch (Throwable ex) {
					callback.handleException(resCtx, ex);
				}
			}
		} finally {
			BusFactory.setThreadDefaultBus(origBus);
			synchronized (message.getExchange()) {
				if ((!isPartialResponse(message))
						|| (message.getContent(Exception.class) != null)) {
					message.getExchange()
							.put("exchange.finished", Boolean.TRUE);
					message.getExchange().setInMessage(message);
					message.getExchange().notifyAll();
				}
			}
		}
	}

	public Conduit getConduit() {
		org.apache.cxf.message.Message message = new MessageImpl();
		Exchange exchange = new ExchangeImpl();
		message.setExchange(exchange);
		setExchangeProperties(exchange, null, null);
		return getConduitSelector().selectConduit(message);
	}

	protected void prepareConduitSelector(org.apache.cxf.message.Message message) {
		getConduitSelector().prepare(message);
		message.getExchange().put(ConduitSelector.class, getConduitSelector());
	}

	protected void setOutMessageProperties(
			org.apache.cxf.message.Message message, BindingOperationInfo boi) {
		message.put("org.apache.cxf.client", Boolean.TRUE);
		message.put("org.apache.cxf.message.inbound", Boolean.FALSE);
		if (null != boi) {
			message.put(BindingMessageInfo.class, boi.getInput());
			message.put(MessageInfo.class, boi.getOperationInfo().getInput());
		}
	}

	protected void setExchangeProperties(Exchange exchange, Endpoint endpoint,
			BindingOperationInfo boi) {
		if (endpoint != null) {
			exchange.put(Endpoint.class, endpoint);
			exchange.put(Service.class, endpoint.getService());
			if (endpoint.getEndpointInfo().getService() != null) {
				exchange.put(ServiceInfo.class, endpoint.getEndpointInfo()
						.getService());
				exchange.put(InterfaceInfo.class, endpoint.getEndpointInfo()
						.getService().getInterface());
			}
			exchange.put(Binding.class, endpoint.getBinding());
			exchange.put(BindingInfo.class, endpoint.getEndpointInfo()
					.getBinding());
		}
		if (boi != null) {
			exchange.put(BindingOperationInfo.class, boi);
			exchange.put(OperationInfo.class, boi.getOperationInfo());
		}

		if ((exchange.isSynchronous()) || (this.executor == null)) {
			exchange.put(MessageObserver.class, this);
		} else {
			exchange.put(Executor.class, this.executor);
			exchange.put(MessageObserver.class, new MessageObserver() {
				public void onMessage(org.apache.cxf.message.Message message) {
					if (!message.getExchange().containsKey(
							Executor.class.getName() + ".USING_SPECIFIED")) {
						//ClientImpl.this.executor.execute();
					} else
						ClientImpl.this.onMessage(message);
				}
			});
		}
		exchange.put(Retryable.class, this);
		exchange.put(MessageObserver.class, this);
		exchange.put(Bus.class, this.bus);

		if ((endpoint != null) && (boi != null)) {
			EndpointInfo endpointInfo = endpoint.getEndpointInfo();
			exchange.put("javax.xml.ws.wsdl.operation", boi.getName());

			QName serviceQName = endpointInfo.getService().getName();
			exchange.put("javax.xml.ws.wsdl.service", serviceQName);

			QName interfaceQName = endpointInfo.getService().getInterface()
					.getName();
			exchange.put("javax.xml.ws.wsdl.interface", interfaceQName);

			QName portQName = endpointInfo.getName();
			exchange.put("javax.xml.ws.wsdl.port", portQName);
			URI wsdlDescription = (URI) endpointInfo.getProperty("URI",
					URI.class);
			if (wsdlDescription == null) {
				String address = endpointInfo.getAddress();
				try {
					wsdlDescription = new URI(address + "?wsdl");
				} catch (URISyntaxException e) {
				}
				endpointInfo.setProperty("URI", wsdlDescription);
			}
			exchange.put("javax.xml.ws.wsdl.description", wsdlDescription);
		}
	}

	protected PhaseInterceptorChain setupInterceptorChain(Endpoint endpoint) {
		PhaseManager pm = (PhaseManager) this.bus
				.getExtension(PhaseManager.class);

		List i1 = this.bus.getOutInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by bus: " + i1);
		}
		List i2 = getOutInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by client: " + i2);
		}
		List i3 = endpoint.getOutInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by endpoint: " + i3);
		}
		List i4 = endpoint.getBinding().getOutInterceptors();
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interceptors contributed by binding: " + i4);
		}
		List i5 = null;
		if ((endpoint.getService().getDataBinding() instanceof InterceptorProvider)) {
			i5 = ((InterceptorProvider) endpoint.getService().getDataBinding())
					.getOutInterceptors();
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("Interceptors contributed by databinding: " + i5);
			}
		}
		if (i5 != null) {
			return this.outboundChainCache.get(pm.getOutPhases(), i1, i2, i3,
					i4, i5);
		}
		return this.outboundChainCache.get(pm.getOutPhases(), i1, i2, i3, i4);
	}

	protected void modifyChain(InterceptorChain chain,
			org.apache.cxf.message.Message ctx, boolean in) {
		if (ctx == null) {
			return;
		}
		Collection<InterceptorProvider> providers = CastUtils.cast((Collection) ctx
				.get(org.apache.cxf.message.Message.INTERCEPTOR_PROVIDERS));

		if (providers != null) {
			for (InterceptorProvider p : providers) {
				if (in)
					chain.add(p.getInInterceptors());
				else {
					chain.add(p.getOutInterceptors());
				}
			}
		}
		String key = in ? org.apache.cxf.message.Message.IN_INTERCEPTORS
				: org.apache.cxf.message.Message.OUT_INTERCEPTORS;
		Collection is = CastUtils.cast((Collection) ctx.get(key));

		if (is != null)
			chain.add(is);
	}

	protected void setEndpoint(Endpoint e) {
		getConduitSelector().setEndpoint(e);
	}

	public int getSynchronousTimeout() {
		return this.synchronousTimeout;
	}

	public void setSynchronousTimeout(int synchronousTimeout) {
		this.synchronousTimeout = synchronousTimeout;
	}

	public final ConduitSelector getConduitSelector() {
		return getConduitSelector(null);
	}

	protected final ConduitSelector getConduitSelector(ConduitSelector override) {
		if (null == this.conduitSelector) {
			setConduitSelector(override);
		}
		return this.conduitSelector;
	}

	public final synchronized void setConduitSelector(ConduitSelector selector) {
		this.conduitSelector = (selector == null ? new UpfrontConduitSelector()
				: selector);
	}

	private boolean isPartialResponse(org.apache.cxf.message.Message in) {
		return Boolean.TRUE.equals(in.get("org.apache.cxf.partial.response"));
	}

	public void setExecutor(Executor executor) {
		if (!SynchronousExecutor.isA(executor))
			this.executor = executor;
	}

	public static class EchoContext extends HashMap<String, Object> {
		final Map<String, Object> shared;

		public EchoContext(Map<String, Object> sharedMap) {
			super();
			this.shared = sharedMap;
		}

		public Object put(String key, Object value) {
			this.shared.put(key, value);
			return super.put(key, value);
		}

		public void putAll(Map<? extends String, ? extends Object> t) {
			this.shared.putAll(t);
			super.putAll(t);
		}

		public Object remove(Object key) {
			this.shared.remove(key);
			return super.remove(key);
		}

		public void reload() {
			super.clear();
			super.putAll(this.shared);
		}
	}
}