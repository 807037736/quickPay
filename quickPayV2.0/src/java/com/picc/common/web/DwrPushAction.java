package com.picc.common.web;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.picc.common.Constants;

@WebService(endpointInterface = "com.picc.common.web.DwrPush")
@Service("dwrPush")
public class DwrPushAction implements DwrPush {
	
	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public void doPush(String showPage, final String functionName, final String msg,
			String[] userCodes) {
		
		try {
			//拼凑页面js方法，待下面调方法时用
			if(userCodes == null){
				//当接收消息的是所有的用户时，从scriptSession对象池中取scriptSession对象是不现实的
				//因为池的key是”userCode+页面请求名“，如果要取出相应页面的scriptSession对象，必须
				//利用for循环加if判断，效率会比较低。所以只能用dwr上下文环境的getScriptSessionsByPage
				//方法获取。而上下文环境对象（serverConetxt）对象的获取在webservice调用与普通调用中不同；
				//故，要先判断当前是否是webservice调用
				//获取ServerContext对象，webservice调用与非webservice调用的获得方式不同
				Message message = PhaseInterceptorChain.getCurrentMessage();
				if(message == null){
//					Browser.withPage(Constants.PAGE_SET.get(showPage), new Runnable(){
					Browser.withPage(showPage, new Runnable(){
						private ScriptBuffer script = new ScriptBuffer();
						public void run() {
							script.appendCall(functionName, msg);
							Collection<ScriptSession> s = Browser.getTargetSessions();
							if(s!=null){
								for(ScriptSession ss:s){
									ss.addScript(script);
								}
							}
						}
					});
				}else{
					//webservice调用
					ServerContext sc = ServerContextFactory.get();
					//userCodes为null时表示消息广播
					Collection<ScriptSession> scriptSessions = sc.getScriptSessionsByPage(Constants.PAGE_SET.get(showPage));
					ScriptBuffer script = new ScriptBuffer();
					script.appendCall(functionName, msg);
					if(scriptSessions!=null){
						for(ScriptSession s:scriptSessions){
							s.addScript(script);
						}
					}
				}
			}else{
				//当接收对象是特定的用户（即非广播）时，只要根据key(“用户编码+要接收的页面请求”)
				//从Map(scriptSession对象池)中取出对应的scriptSesstion即可
				Map scriptSessions = ScriptSessionManagerServlet.scriptSessionMap;
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall(functionName, msg);
				String key = null;
				for(String userCode:userCodes){
					key = userCode + showPage;
					Set<ScriptSession> sessions = (Set<ScriptSession>) scriptSessions.get(key);
					if(sessions!=null){
						for(ScriptSession s:sessions){
							s.addScript(script);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		
	}
}
