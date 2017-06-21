<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json" charset="utf-8">
    <title>自助定责</title>
    <meta name="keywords" content="办理流程">
    <meta name="description" content="办理流程">
    <meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
    <link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/common.css">
	<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:history.go(-1)" ></a>办理流程
	</header>
	<input type="hidden" id="param" name="param" value="${param1 }" />
	<div class="tipsImg"><img src="${ctx}/pages/qp/weixin/resources/img/step.jpg"/></div>	
	<a class="btn" id="nextPage">下一步<img src="${ctx}/pages/qp/weixin/resources/img/icon_next_arrow.png"/></a>
</body>
<script src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	/* 跳转至第三页 */
	$("#nextPage").bind("click", function(event) {
		var url = "/weixin/case/prepareUploadPictureGroup.do?param=${param1}";
		self.location = url;
	});
</script>


</html>
