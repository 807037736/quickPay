package com.picc.common.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;

import com.picc.common.Constants;


/**
 * Servlet组件，用于管理scriptSession，服务器启动时对象被创建
 * 
 * @author shenyichan
 * 
 */
public class ScriptSessionManagerServlet extends HttpServlet {
	
	//一个用户可以有多个scriptSession，将scriptSession维护进Map中，有利于提高查找速度
	public static Map<String,Set<ScriptSession>> scriptSessionMap = new HashMap<String,Set<ScriptSession>>();
	
	public void init() throws ServletException {
		 System.out.println("###########################################:"+System.getProperty("java.endorsed.dirs"));
		 System.out.println("init.....");
		 Container container = ServerContextFactory.get().getContainer();
		 ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		 ScriptSessionListener listener = new ScriptSessionListener(){
			
			 /**
			  * ScriptSession对象创建时的监听方法
			  */
			public void sessionCreated(ScriptSessionEvent ev) {
				System.out.println("a new ScriptSession is created!!");
				
				//每次刷新页面时，获取这个页面的请求路径
				String currentPage = ev.getSession().getPage();
				System.out.println("currenPage:"+currentPage);
				String whichPage="";
				//获取请求名
				if(currentPage.contains(".jsp")){
					whichPage = currentPage.substring(currentPage.lastIndexOf("/")+1, currentPage.indexOf(".jsp"));
				}else{
					whichPage = currentPage.substring(currentPage.lastIndexOf("/")+1, currentPage.indexOf(".do"));
				}
				String userCode = (String) WebContextFactory.get().getSession().getAttribute(Constants.LOGIN_USER);
				
				//用户编号+请求名作为Map的key，表示一个用户对同一个页面可以有多个scriptSession
				String key = userCode + whichPage; 
				
				if(scriptSessionMap.containsKey(key)){
					scriptSessionMap.get(key).add(ev.getSession());
				}else{
					Set<ScriptSession> set = new HashSet<ScriptSession>();
					set.add(ev.getSession());
					scriptSessionMap.put(key, set);
				}
				
				
			}
			
			/**
			  * ScriptSession对象销毁时的监听方法
			  */
			public void sessionDestroyed(ScriptSessionEvent ev) {
				String currentPage = ev.getSession().getPage();
				System.out.println("currenPage:"+currentPage);
				String whichPage="";
				//获取请求名
				if(currentPage.contains(".jsp")){
					whichPage = currentPage.substring(currentPage.lastIndexOf("/")+1, currentPage.indexOf(".jsp"));
				}else{
					whichPage = currentPage.substring(currentPage.lastIndexOf("/")+1, currentPage.indexOf(".do"));
				}
				String userCode = (String) WebContextFactory.get().getSession().getAttribute(Constants.LOGIN_USER);
				
				//用户编号+请求名作为Map的key，表示一个用户对同一个页面可以有多个scriptSession
				String key = userCode + whichPage; 
				scriptSessionMap.remove(key);
				
				System.out.println("a ScriptSession is distroyed key = "+key);
				System.out.println(scriptSessionMap);
			}
			 
		 };
		 
		 manager.addScriptSessionListener(listener);
		
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		init();
	}
	
}
