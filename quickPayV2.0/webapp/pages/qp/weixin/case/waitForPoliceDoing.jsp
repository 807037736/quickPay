<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta http-equiv="Content-Type" content="application/json" charset="utf-8">
<title>自助定责</title>
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/index.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:window.history.go(-1);"></a>交警远程定责
	</header>
	<!--已提交交警远程处理弹出框 正常隐藏-->
	<div class="submit-box-content">
		<p class="submit-box-img" style="text-align: center;"><img src="${ctx}/pages/qp/weixin/resources/img/picture_submitted.png"/></p>
		<p>已提交至交警远程处理，责任认定完成后系统将会短信通知当事人双方</p>
		<a class="btn" onclick="toNext()">下一步</a>
	</div>
	<form id="submitform">
		<input name="wxCaseId" type="hidden" value="${param.wxCaseId }">
		<input name="param" type="hidden" value="${param.param }" />
	</form>
</body>
<script type="text/javascript">
	var toNext = function () {
		$.ajax({
			url : "${ctx }/weixin/case/hasPoliceDone.do",
			type : "post",
			dataType : "json",
			data : $("#submitform").serialize(),
			success : function(data) {
				if (data.state && data.state == "success") {
					window.location.href = "${ctx }/weixin/case/toFinish.do?wxCaseId=${param.wxCaseId }&param=${param.param }";
				} else {
					alert(data.msg);
				}
			},
			error : function (data) {
				
			}
		});
	};
</script>
</html>