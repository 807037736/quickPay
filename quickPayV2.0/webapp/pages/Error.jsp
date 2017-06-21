<%@page import="org.apache.commons.lang.exception.ExceptionUtils"%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.security.AccessDeniedException" %>
<%@page import="com.picc.common.ExceptionHelper"%>
<%@page import="ch.qos.logback.classic.Logger" %>
<%@page import="org.slf4j.LoggerFactory" %>
<html>
<head>
<title>错误信息</title>
<%@ include file="/common/taglibs.jsp"%> 
<%@ include file="/common/meta_css.jsp"%>
<style>
	td{font-size:9pt;}
.button_ty,.button_ty_over{color:#000;border:1px solid #94D8E4;padding:1px 5px 1px 5px;height:20px;}
.button_ty{background: #fff url(${ctx}/pages/image/btbg_blue.gif) repeat-x left left -2px;}
.button_ty_over{background: #fff url(${ctx}/pages/image/btbg_orange.gif) repeat-x left left -2px;}
</style>
<script language=javascript>
function shContent()
{
  if(content.style.display=='')
    content.style.display = 'none';
  else
    content.style.display = '';
}

function closeIFrame() {
	if(document.parentWindow.name=="msgIFrame"){
	  var ifr = document.parentWindow.parent.document.getElementById("msgIFrame"); 
		document.parentWindow.parent.document.body.removeChild(ifr);
	}
}

function loadBody(){
  if(document.parentWindow.name=="msgIFrame"){
    trCloseButton.style.display = "";
  }
}
function closeMethod(){
  if(parent!=null && parent.window!=null){
    if(parent.submitDlg!=null){
       parent.submitDlg.hide();
    }
 	else{
 		if(parent.window!=null){
 			parent.window.close();
 		}else{
 			window.close();
 		}
 	}
  }else{
   	window.location="about:blank";
  }
}


</script>
</head>
<body onload="loadBody()">

  <table class=common align=center>
    <tr>
      <td style="text-align:center;" colspan="2"><img src='${ctx}/pages/image/error.png'
          style='cursor:hand' alt='详细信息'></td>
    </tr>
    <tr>
      <td style="text-align:center;font-size:18px;font-weight: bold;" width="100%"> 
        <pre>
        <h3 style="text-align:center;font-size:18px;font-weight: bold;">
<%
if(exception ==null){
	exception = (Exception)request.getAttribute("javax.servlet.error.exception");
}
if(exception ==null){
	exception = (Exception)request.getAttribute("exception");
}
out.println(ExceptionHelper.getExceptionMessage(exception));
%>

<%
if(exception instanceof AccessDeniedException){
	//如果是权限异常错误
	response.addHeader("_forbidden", "true");
}
%>
		</h3>
        </pre>
      </td>
      <td class="common"> 
      </td>
    </tr>
    
    <%
		UUID uuid = UUID.randomUUID();				
		String idStr = uuid.toString();			//生成UUID
		/**往日志记录中写入异常数据**/
		Logger logger = (Logger) LoggerFactory.getLogger(ExceptionHelper.class); 
		//logger.warn(msg);
		logger.warn("ExceptionNo:"+idStr+"\n"+ExceptionUtils.getFullStackTrace(exception));
	%>
    <tr>
    	<td td style="text-align:center;" colspan="2">错误代码:<%=idStr %></td>
    </tr>
    <tr id="trCloseButton" >
      <td colspan="2" align="center">
		<input type="button"  value="关  闭 " onclick="closeMethod()" class="button_closed">
      </td>
    </tr>
  </table>

</body>
</html>
