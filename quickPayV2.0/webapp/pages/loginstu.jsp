<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>
<%@ page import="org.springframework.security.BadCredentialsException"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx}/widgets/jquery-easyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="<c:url value='/style/login.css'/>"
	type="text/css" media="all" />

<title>学幼险理赔统计系统</title>

<script language="javascript">	
//	if (self!=top){
//    top.location=self.location;
//  }
function submitForm(){
  return true;
}
function init(){
  fm.j_username.focus();
  fm.j_username.select();
}

function Judge(obj){
	if(obj[1].value=='3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d'){
		if(confirm('为了保证用户信息的安全，请修改初始密码！')){
			resetPassword();
		}else{
			obj.submit();
		}
	}else{obj.submit();}
}
function fsubmit(obj){
	
	
	$("input[name='b1']").attr("disabled","disabled");
	obj[1].value = SHA1(obj[1].value);
	//alert(obj[1].value);
	obj.submit();
	//Judge(obj);
	$("#login_b1").attr("class","login_c");
	
	//$('a').removeAttr('href');
	return false;
}
function resetPassword(){
	editRecord(contextRootPath+'/um/umtuser/resetPassWord.do');
}
</script>
</head>
<body class="logon" onload="init();">


	<form name="fm" action="<c:url value='j_spring_security_check'/>"
		method="post">
		<%-- <table width="1440" border="0" cellspacing="0" cellpadding="0">
	<tr align="center">
		<td colspan="2">用户名：<input type="text" name="j_username" value="admin" maxlength="10" tabindex="1" />&nbsp;&nbsp;</td>
	</tr>
	<tr align="center">
		<td colspan="2">密码：&nbsp;&nbsp;<input type="password" name="j_password" value="111111" tabindex="2" />&nbsp;&nbsp; <input
			type="hidden" name="lt" value="${flowExecutionKey}" /> <input
			type="hidden" name="_eventId" value="submit" /></td>
	</tr>
	<tr>
	<td colspan="2">&nbsp;</td>
	</tr>
	<tr align="center">
	<td></td>
	<td><input type="submit" value="登录" style="height: 30px;width: 60px"/></td>
	</tr>

	<c:if test="${not empty param.login_error}">
		<tr>
			<td align="left" valign="middle"><font color="red"> <%
			AuthenticationException ex = (AuthenticationException) request.getSession().getAttribute(
 				AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
 %> 登录失败，错误原因：<%=ex.getLocalizedMessage()%></font></td> 
		</tr>
	</c:if>
	
</table> --%>
		<div class="login_con">
			<div class="content">
				<a href="#" class="logo_a" title="中国人保财险"> </a>
			</div>
			<div class="login_bg">
				<div class="content">
					<div class="enter">
						<h1>学幼险理赔统计系统</h1>
						<input name="j_username" type="text" value=""
							class="input_1 input" /> <input name="j_password"
							type="password" value=""  class="input_2 input" /> <input
							type="hidden" name="lt" value="${flowExecutionKey}" /> <input
							type="hidden" name="_eventId" value="submit" />
						<div class="code">
							<a href="javascript:resetPassword();">忘记密码？</a>
						</div>
						<!-- 
              <a href="javascript:fsubmit(document.fm);" title="登录" class="login_a" > 登录 </a>
			   -->
						<input type="submit" id="login_b1" name="b1" class="login_b"
							onClick="fsubmit(document.fm);" />
						<ul class="bulletin">
							<li><span> 公告 </span> <i>...</i></li>
						</ul>
					</div>
					<div class="footer">
						<ul>
							<li><a href="#">平台简介</a>&nbsp;|&nbsp;</li>
							<li><a href="#">联系我们</a>&nbsp;|&nbsp;</li>
							<li><a href="#">友情链接</a>&nbsp;|&nbsp;</li>
							<li><a href="#">产品答疑</a></li>
						</ul>
						<p class="fr">&copy; PICC</p>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${not empty login_error}">
			<%
				String errorMsg = "";
				AuthenticationException ex = (AuthenticationException) request.getSession().getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
				errorMsg = ex.getMessage();
				if(errorMsg!=null&&!"".equals(errorMsg)){
					request.setAttribute("errorMsg", errorMsg);
				}
			%>
		</c:if>
	</form>
</body>
<script>
window.onload = function() {
	if ("${errorMsg}" != null && "${errorMsg}" != "") {
		$.messager.alert("提示信息", "${errorMsg}", "info");
	}
}
</script>
</html>
