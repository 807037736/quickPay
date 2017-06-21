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
<meta name="keywords" content="定责完成">
<meta name="description" content="定责完成">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:window.close();"></a>定责完成
	</header>
	<div class="tipsImg"><img src="${ctx }/pages/qp/weixin/resources/img/picture_success.jpg"/></div>
	<div class="finish">自助定责已经完成，请在24小时内前往双方约定的受理点做后续处理。</div>
</body>
</html>