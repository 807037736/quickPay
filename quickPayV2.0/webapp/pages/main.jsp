<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Arch4Platform</title>
<link href="${ctx}/style/mian_demo.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ctx}/pages/logo/style/basic.css" />
<%@ include file="/widgets/loading/loading.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript" src="${ctx}/pages/js/anylinkvertical.js"></script>
<script type="text/javascript" src="${ctx}/pages/logo/js/yahoo.js"></script>
<script type="text/javascript" src="${ctx}/pages/logo/js/event.js"></script>
<script type="text/javascript" src="${ctx}/pages/logo/js/logger.js"></script>
<script type="text/javascript" src="${ctx}/pages/logo/js/treeview-debug.js"></script>
<script type="text/javascript" src="${ctx}/pages/logo/js/menu.js"></script>
<script type="text/javascript">
function jump(msg){
	using(['dialog','messager'], function(){
		$.messager.show({
			title:'info',
			msg:msg
		});
	});
}

</script>

</head>
<body style="width: 100%; height: 100%;">
<table width="100%" height="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tr height="100%">
		<td width="25" height="100%" background="${ctx}/pages/images/mian_menu_bg.gif">
		${menuContent}</td>
		<td width="100%" height="100%" valign="top"><iframe id="page"
			name="page" width="100%" height="100%" scrolling="no"
			frameborder="0"> </iframe></td>
	</tr>
	
</table>
</body>
</html>
