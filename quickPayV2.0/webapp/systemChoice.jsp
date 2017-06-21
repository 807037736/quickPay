<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保险业反欺诈平台</title>
<link rel="shortcut icon" href="${ctx}/favicon.ico" />
<link href="${ctx}/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/DwrPushAction.js"></script>
<link href="${ctx}/widgets/jquery-easyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
<script language="javascript">
	$(function(){
		$('.sysTab').click(function(){
			var isAllow = $(this).children().last().prev().prev().val();
			var serverCode = $(this).children().last().prev().val();
			var serverName = $(this).children().last().val();
			if(isAllow == '1'){
				/* $('#serverCode').val(serverCode);
				$('#serverName').val(serverName);
				var win = contextRootPath+"/mainPage.do?serverCode="+serverCode+"&serverName="+serverName;
				window.open(win,'_blank'); */
				/* $.ajax({
					type:"POST",
					url:contextRootPath+"/mainPage.do",
					 async:"false",
					data:"serverCode="+serverCode+"&serverName="+serverName
				}); */
				$('#serverCode').val(serverCode);
				$('#serverName').val(serverName);
				$("#fm").attr("action",contextRootPath+"/mainPage.do");
				$('#fm').submit();  
				setTimeout("CloseWebPage();",1000);  
			}else{
				if(serverCode=="weixin"){
					$("#fm").attr("action",contextRootPath+"/um/umtuserrelation/prepareQuery.do");
					$('#fm').submit();
				}else{
					$.messager.alert('提示消息', "系统建设中，敬请期待！", 'info');
					return;
				}
			}
		});
	});
	function CloseWebPage(){
		window.open('', '_top');
		window.top.close();
		//alert(navigator.userAgent);
		/*
		 if (navigator.userAgent.indexOf("MSIE") > 0) {
		  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		   window.opener = null;
		   window.close();
		  } else {
		   window.open('', '_top');
		   window.top.close();
		  }
		 }
		 else if (navigator.userAgent.indexOf("Firefox") > 0 || navigator.userAgent.indexOf("Chrome") !=-1) {
	 	 // window.location.href = 'about:blank ';
	 	 window.close();
	   } else {
		  window.opener = null;
		  window.open('', '_self', '');
		  window.close();
		 }
		*/
		}
</script>
<style type="text/css">
.sysTab{
	cursor: pointer;
}
</style>
</head>
<body>
<br/>
<center>
<fieldset style="width: 80%;">
<table align="center" cellspacing="80px">
<c:forEach items="${tmTAppServiceConfigTable}" var="tmTAppServiceConfigTableList">
	<tr>
		<c:forEach items="${tmTAppServiceConfigTableList}" var="tmTAppSegjrviceConfig">
			<td class="sysTab" align="center">
			<img src="${ctx}/uploadFiles/images/${tmTAppSegjrviceConfig.methods}" alt="${tmTAppSegjrviceConfig.serverName}" height="250px" width="250px"/>
			<br/><h3 class="yh">${tmTAppSegjrviceConfig.serverName}</h3><input type="hidden" value="${tmTAppSegjrviceConfig.isAllow}"/><input type="hidden" value="${tmTAppSegjrviceConfig.id.serverCode}"/><input type="hidden" value="${tmTAppSegjrviceConfig.serverName}"/>
			</td>
		</c:forEach>
	</tr>
</c:forEach>
</table>
<form id="fm" name="fm" method="post" target="_blank">
	<input type="hidden" id="serverCode" name="serverCode"/>
	<input type="hidden" id="serverName" name="serverName"/>
</form>
</fieldset>
</center>
</body>
</html>