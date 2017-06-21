<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<h1>fieldErrors</h1>
<pre>
<%
	ActionContext actionContext = ServletActionContext.getContext();
	ValueStack valueStack = actionContext.getValueStack();

	Map allFieldErrors = (Map) valueStack
			.findValue("fieldErrors");
	if(allFieldErrors!=null){
		java.util.Set keys = allFieldErrors.keySet();
		for(Object key:keys){
			System.out.println("[fieldErrors]===="+key+"="+allFieldErrors.get(key));
			out.println("[fieldErrors]===="+key+"="+allFieldErrors.get(key));
		}
	} else {
		System.out.println("[fieldErrors] is null");
		out.println("[fieldErrors] is null");
	}
%>
</pre>
