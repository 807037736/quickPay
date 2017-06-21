<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML >
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<title>自助定责</title>
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
<link href="${ctx }/pages/qp/weixin/resources/css/common.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/index.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/main.css" rel="stylesheet" >
</head>
<body>
	
	<form action="${ctx}/weixin/case/basicInfo.do" method="post">
		<input type="hidden" name="param" id="param" value="${param1 }">
		<input type="hidden" name="wxCaseId" id="wxCaseId" value="${wxCaseId }">
		<header>
			<a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>挪车提醒
		</header>
		<div class="car-remaind">
		   <span class="car-remaind-img">
		   		<img src="${ctx}/pages/qp/weixin/resources/img/picture_police.png"/>
		   </span>
		   <span>
		   		<img src="${ctx}/pages/qp/weixin/resources/img/picture_front.png"/>
		   </span>
		</div>
		<div class="tipsImg">
			<img src="${ctx}/pages/qp/weixin/resources/img/picture_cry.png"/>
		</div>	
		<a href="javascript:void(0)" class="btn" onclick="toBaseInfo()">
			已挪车<img src="${ctx}/pages/qp/weixin/resources/img/icon_next_arrow.png"/>
		</a>
	</form>
	
</body>
<script type="text/javascript">
	function toBaseInfo(){
		$("form").submit();
	}
</script>
</html>
