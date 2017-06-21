<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json" charset="utf-8">
    <title>自助定责</title>
    <meta name="keywords" content="适用范围">
    <meta name="description" content="适用范围">
    <meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
    <link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/common.css">
	<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/main.css">
	
	<style type="text/css">
		.fontCss {
			font-size: 20;
			color: 2d2d2d;
		}
	</style>
	
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:history.go(-1)"></a>适用范围
	</header>
	<input type="hidden" id="param" name="param" value="${param1 }" />
	<div class="tipsImg"><img src="${ctx}/pages/qp/weixin/resources/img/picture_range.jpg"/></div>	
		<!-- <p class="fontCss"  align="center" style="margin-top: 10px">发生事故后</p>
		<p class="fontCss"  align="center" >请确定已打开双闪灯</p>
		<p class="fontCss"  align="center" >夜间还需要开启示廓灯和后尾灯</p>
		<p class="fontCss"  align="center" >车辆后方放置警示牌</p> -->
	<a class="btn"  id="nextPage">下一步<img src="${ctx}/pages/qp/weixin/resources/img/icon_next_arrow.png"/></a>
</body>
<script src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	/* 跳转至第三页 */
	$("#nextPage").bind("click", function(event) {
		var url = "/weixin/case/threePage.do?param=${param1}";
		self.location = url;
	});
</script>
</html>
