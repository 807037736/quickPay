package com.picc.um.appender;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import com.picc.um.log.schema.model.LoGTINFO;
import com.picc.um.log.schema.model.LoGTINFOId;
import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.log.schema.model.LoGTTYPEId;
import com.picc.um.log.service.facade.ILoGTINFOService;
import com.picc.um.log.service.facade.ILoGTTYPEService;
import com.picc.um.log.service.spring.LoGTINFOServiceSpringImpl;
import com.picc.um.log.service.spring.LoGTTYPEServiceSpringImpl;
/**
 * 定义LogBack自定义Appender
 * @author yanglian
 *
 */
public class LogAppender extends AppenderBase<ILoggingEvent> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	PatternLayoutEncoder encoder;

	private static final String[] CONFIGS = { "spring/applicationContext.xml",
			"spring/applicationContext-hibernate.xml",
			"spring/dataAccessContext-hibernate.xml" };
	private AbstractApplicationContext ac;
	
	@Autowired
	private ILoGTINFOService loGTINFOService=null;
	
	@Autowired
	private ILoGTTYPEService loGTTYPEService=null;

	private LoGTTYPE logtype;
	private LoGTTYPEId logtypeid;
	private LoGTINFO loginfo;
	private LoGTINFOId loginfoid;
	static final StackTraceElement EMPTY_CALLER_DATA = null;

	public ILoGTINFOService getLoGTINFOService() {
		return loGTINFOService;
	}

	public void setLoGTINFOService(ILoGTINFOService loGTINFOService) {
		this.loGTINFOService = loGTINFOService;
	}

	public LogAppender() {

	}

	@Override
	public void start() {
		
		  ac = new ClassPathXmlApplicationContext(CONFIGS); 
		  loGTINFOService =	 (LoGTINFOServiceSpringImpl) ac .getBean("loGTINFOService");
		  loGTTYPEService = (LoGTTYPEServiceSpringImpl) ac.getBean("loGTTYPEService");
			
		/*if (loGTINFOService == null)
			loGTINFOService = (ILoGTINFOService) ServiceFactory
					.getService("loGTINFOService");
		if (loGTTYPEService == null)
			loGTTYPEService = (ILoGTTYPEService) ServiceFactory
					.getService("loGTTYPEService");*/
		if (this.encoder == null) {
			addError("No layout set for the appender named[" + name + "].");
			return;
		}
		try {
			encoder.init(System.out);
		} catch (IOException e) {
		}
		super.start();

	}

	@Override
	protected void append(ILoggingEvent event) {

		try {
			logtypeid = new LoGTTYPEId();
			loginfo = new LoGTINFO();
			loginfoid = new LoGTINFOId();
			Object[] argArray = event.getArgumentArray();
			if (argArray != null && argArray.length == 5) {
				logtypeid.setOperateTypeId((String) argArray[0]);
				logtype = (LoGTTYPE) loGTTYPEService
						.findLoGTTYPEByPK(logtypeid);
				// 判断操作类型和操作方法是否在库中存在
				if (logtype != null) {

					loginfoid.setLogId(loginfoid.getLogId());
					loginfo.setId(loginfoid);
					loginfo.setOperateMethod(logtype.getOperateMethod());
					// 获取log传来的消息
					String message = event.getMessage();
					message = message.replaceAll("[\\p{Punct}\\p{Space}]+", "");
					loginfo.setOperateResult(message);
					loginfo.setOperateTime(new Date());
					loginfo.setOperateTypeId(logtypeid.getOperateTypeId());
					loginfo.setOperateTypeName(logtype.getOperateTypeCName());
					loginfo.setUserCode((String) argArray[1]);
					loginfo.setUserName((String) argArray[2]);
					loginfo.setIp((String) argArray[3]);
					loginfo.setComid((String)argArray[4]);
					loGTINFOService.saveLoGTINFO(loginfo);

				}
			}

			// TODO Auto-generated method stub

			// this.encoder.doEncode(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}

	}

	private StackTraceElement extractFirstCaller(
			StackTraceElement[] callerDataArray) {
		StackTraceElement caller = EMPTY_CALLER_DATA;
		if (hasAtLeastOneNonNullElement(callerDataArray))
			caller = callerDataArray[0];
		return caller;
	}

	private boolean hasAtLeastOneNonNullElement(
			StackTraceElement[] callerDataArray) {
		return ((callerDataArray != null) && (callerDataArray.length > 0) && (callerDataArray[0] != null));
	}

	public PatternLayoutEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(PatternLayoutEncoder encoder) {
		this.encoder = encoder;
	}

}
