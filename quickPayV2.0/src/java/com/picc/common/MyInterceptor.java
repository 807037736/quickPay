package com.picc.common;

import ins.framework.web.Struts2Action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.picc.um.schema.vo.SessionUser;

public class MyInterceptor extends Struts2Action implements Interceptor {  
		  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override  
	    public void destroy() {  
	          
	    }  
	  
	    @Override  
	    public void init() {  
	          
	    }  
	  
	    @Override  
	    public String intercept(ActionInvocation invocation) throws Exception {  
	        SessionUser user = getUserFromSession();
			if(null == user){
				this.getResponse().sendRedirect("/login.jsp");
				return "none";
			}else{
				return invocation.invoke();  
			}
	    }
	  
	}
