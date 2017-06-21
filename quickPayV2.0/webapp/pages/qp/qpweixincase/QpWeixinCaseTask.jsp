<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBind"%>
<html>
<head>
<title>微信定责任务列表</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpweixincase/QpWeixinCaseTask.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="margin-control" >
		<table id="QpWeixinTaskTable"></table>
	</div>
	<input type="hidden" id="userCode" name="userCode" value="${SessionUser.userCode}">
</body>
</html>
