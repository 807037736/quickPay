<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json" charset="utf-8">
    <title>自助定责</title>
    <meta name="keywords" content="温馨提醒">
    <meta name="description" content="温馨提醒">
    <meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
    <link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/common.css">
	<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
	  温馨提醒
	</header>
	<input type="hidden" id="param" name="param" value="${param1 }" />
	<div class="tipsImg" style="margin:10px 15px 10px 15px;" ><img src="${ctx}/pages/qp/weixin/resources/img/tips.jpg"/></div>
	<div class="tipsContent" style="font-size:1.8rem;color:#2d2d2d;text-align:center;line-height:22px;">
		<p>发生事故后</p>
		<p>请确定已打开双闪灯</p>
		<p>夜间还需要开启廊灯和后尾灯</p>
		<p>车辆后方放置警示牌</p>
	</div>
	<a class="btn" style="margin: 10px 27.5px 15px;" id="nextPage" >已确认<img src="${ctx}/pages/qp/weixin/resources/img/icon_next_arrow.png"/></a>
</body>
<script src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
<script type="text/javascript">
/* 跳转至第二页 */
$("#nextPage").bind("click", function(event) {
	var url = "/weixin/case/twoPage.do?param=${param1}";
	self.location = url;
});
</script>
</html>
