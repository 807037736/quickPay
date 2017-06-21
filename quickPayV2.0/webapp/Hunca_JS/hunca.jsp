<%
/*
引入湖南CA控件公共包含JSP，主要完成以下两个工作。
*/
 %>

<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<script type="text/javascript" src="<%=basePath%>/Hunca_JS/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Hunca_JS/jquery.sprintf.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Hunca_JS/objectclass.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Hunca_JS/netonex.base.src.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Hunca_JS/hunca.js"></script> 
  </head>
  <body>
  
     <!--声明CA控件-->
	<div action="netonex" style="width:0px; height:0px;" netonexid="netonex" activex32_codebase="JS/NetONEX32.v1.3.4.0.cab" activex64_codebase="JS/NetONEX64.v1.3.4.0.cab" npapi_codebase="JS/NetONEX.v1.3.4.0.msi" version="1.3.4.0">
	<object width="1" height="1" classid="CLSID:EC336339-69E2-411A-8DE3-7FF7798F8307"></object>
	<object width="1" height="1" classid="CLSID:EC336339-69E2-411A-8DE3-7FF7798F8307"></object>
	</div>

  </body>
</html>
