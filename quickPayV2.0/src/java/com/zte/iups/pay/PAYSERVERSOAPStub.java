/**
 * PAYSERVERSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class PAYSERVERSOAPStub extends org.apache.axis.client.Stub implements com.zte.iups.pay.PAYSERVERSOAP {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vPushOrderInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPushOrderInfoReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoReq"), com.zte.iups.pay.VPushOrderInfoReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoRsp"));
        oper.setReturnClass(com.zte.iups.pay.VPushOrderInfoRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPushOrderInfoRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vNotifyPayRslt");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vNotifyPayRsltReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltReq"), com.zte.iups.pay.VNotifyPayRsltReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltRsp"));
        oper.setReturnClass(com.zte.iups.pay.VNotifyPayRsltRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vNotifyPayRsltRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vRevPayment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReq"), com.zte.iups.pay.VRevPaymentReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentRsp"));
        oper.setReturnClass(com.zte.iups.pay.VRevPaymentRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vPaymentCancel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentCancelReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelReq"), com.zte.iups.pay.VPaymentCancelReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelRsp"));
        oper.setReturnClass(com.zte.iups.pay.VPaymentCancelRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentCancelRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vRevPaymentCancel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentCancelReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelReq"), com.zte.iups.pay.VRevPaymentCancelReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelRsp"));
        oper.setReturnClass(com.zte.iups.pay.VRevPaymentCancelRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentCancelRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vPaymentReturn");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentReturnReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnReq"), com.zte.iups.pay.VPaymentReturnReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnRsp"));
        oper.setReturnClass(com.zte.iups.pay.VPaymentReturnRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentReturnRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vRevPaymentReturn");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReturnReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnReq"), com.zte.iups.pay.VRevPaymentReturnReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnRsp"));
        oper.setReturnClass(com.zte.iups.pay.VRevPaymentReturnRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReturnRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vPreAuthOper");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPreAuthOperReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperReq"), com.zte.iups.pay.VPreAuthOperReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperRsp"));
        oper.setReturnClass(com.zte.iups.pay.VPreAuthOperRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPreAuthOperRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("vConfirmRisk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vConfirmRiskReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskReq"), com.zte.iups.pay.VConfirmRiskReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskRsp"));
        oper.setReturnClass(com.zte.iups.pay.VConfirmRiskRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vConfirmRiskRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public PAYSERVERSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PAYSERVERSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PAYSERVERSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VConfirmRiskReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VConfirmRiskRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VNotifyPayRsltReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VNotifyPayRsltRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPaymentCancelReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPaymentCancelRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPaymentReturnReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPaymentReturnRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPreAuthOperReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPreAuthOperRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPushOrderInfoReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VPushOrderInfoRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentCancelReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentCancelRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnReq");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentReturnReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentReturnRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentRsp");
            cachedSerQNames.add(qName);
            cls = com.zte.iups.pay.VRevPaymentRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.zte.iups.pay.VPushOrderInfoRsp vPushOrderInfo(com.zte.iups.pay.VPushOrderInfoReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vPushOrderInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vPushOrderInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VPushOrderInfoRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VPushOrderInfoRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VPushOrderInfoRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VNotifyPayRsltRsp vNotifyPayRslt(com.zte.iups.pay.VNotifyPayRsltReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vNotifyPayRslt");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vNotifyPayRslt"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VNotifyPayRsltRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VNotifyPayRsltRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VNotifyPayRsltRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VRevPaymentRsp vRevPayment(com.zte.iups.pay.VRevPaymentReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vRevPayment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vRevPayment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VRevPaymentRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VRevPaymentRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VRevPaymentRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VPaymentCancelRsp vPaymentCancel(com.zte.iups.pay.VPaymentCancelReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vPaymentCancel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vPaymentCancel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VPaymentCancelRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VPaymentCancelRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VPaymentCancelRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VRevPaymentCancelRsp vRevPaymentCancel(com.zte.iups.pay.VRevPaymentCancelReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vRevPaymentCancel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vRevPaymentCancel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VRevPaymentCancelRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VRevPaymentCancelRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VRevPaymentCancelRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VPaymentReturnRsp vPaymentReturn(com.zte.iups.pay.VPaymentReturnReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vPaymentReturn");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vPaymentReturn"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VPaymentReturnRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VPaymentReturnRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VPaymentReturnRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VRevPaymentReturnRsp vRevPaymentReturn(com.zte.iups.pay.VRevPaymentReturnReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vRevPaymentReturn");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vRevPaymentReturn"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VRevPaymentReturnRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VRevPaymentReturnRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VRevPaymentReturnRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VPreAuthOperRsp vPreAuthOper(com.zte.iups.pay.VPreAuthOperReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vPreAuthOper");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vPreAuthOper"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VPreAuthOperRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VPreAuthOperRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VPreAuthOperRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zte.iups.pay.VConfirmRiskRsp vConfirmRisk(com.zte.iups.pay.VConfirmRiskReq req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://iups.zte.com/pay/vConfirmRisk");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "vConfirmRisk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zte.iups.pay.VConfirmRiskRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zte.iups.pay.VConfirmRiskRsp) org.apache.axis.utils.JavaUtils.convert(_resp, com.zte.iups.pay.VConfirmRiskRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
