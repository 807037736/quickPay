<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.hunan.service.RandomUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/Hunca_JS/hunca.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>湖南CA登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">
  </head>
    <%
//后台产生随机数，作为签名原文（基于随机数的数字签名实现数字证书登录）
//String ca_data=RandomUtil.randomString(6);
//session.setAttribute("ca_data",ca_data);
 %>	
   <body> 
	
	<div align="center"><img alt="" src="${ctx }/images/hunanca_logo.jpg"></div>
	<h1 align="center">数字证书登录</h1>
	<h3 align="center">（基于随机数的数字签名实现数字证书登录）</h3>

	<div align="center">
	<form action="${ctx}/weixin/qpweixincase/caLogin.do" method="post">	
		<!-- 签名结果，js填充：cert是签名证书，signData是签名结果 -->
		<input type="hidden" name="ca_cert" id="ca_cert" value=""/>
		<input type="hidden" name="ca_sign" id="ca_sign" value=""/>
		<input type="hidden" name="ca_data" id="ca_data" value="${ca_data}"/>
		<input type="submit" value="登录" onclick="return (HuncaLogin('${ca_data}')=='1')?true:false" /><br/>
	</form> 
	</div>	
  </body>
</html>
