<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人中心</title>
<meta name="keywords" content="二维码名片">
<meta name="description" content="二维码名片">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1,maximum-scale = 1">
<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/common.css">
<script type="text/javascript" src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/pages/qp/weixin/resources/js/jquery.qrcode.min.js"></script>
<style type="text/css">
body{
background-color: #585353;
background-image: url("");
}
.qrcode{
	text-align: center;
    margin: 15% 5%;
    padding: 40px;
    background-color: #fff;
}
</style>
</head>
<body>
	<header>
		<a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>二维码名片
	</header>
	<div class="qrcode">
		<div id="qrcode" ></div>
		<div style="margin-top: 10px;">扫一扫上面的二维码,查看我的信息</div>
	</div>
	
	
</body>
<script type="text/javascript">
	function utf16to8(str) {
	    var out, i, len, c;
	    out = "";
	    len = str.length;
	    for (i = 0; i < len; i++) {
	        c = str.charCodeAt(i);
	        if ((c >= 0x0001) && (c <= 0x007F)) {
	            out += str.charAt(i);
	        } else if (c > 0x07FF) {
	            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
	            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
	            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	        } else {
	            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
	            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	        }
	    }
	    return out;
	}
	jQuery('#qrcode').qrcode(utf16to8('{"openId":"${openId}","platId":"${platId}"}'));
</script>
</html>
