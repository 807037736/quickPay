<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json"
	charset="utf-8">
<title>个人中心</title>
<meta name="keywords" content="个人注册">
<meta name="description" content="个人注册">
<meta name="viewport" id="viewport"
	content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx}/pages/qp/weixin/resources/css/index.css">
<style type="text/css">
	 #show{ 
  	display:none;
  	text-align:center;
  	color:#fff;
  	background:#000000;
  	border-radius: 10px 10px; 
  	position: absolute;  
  	top: 50%; 
  	left:30%;
  	width: 40%;  
  	padding-top:5%;
  	padding-bottom: 5%;    
  	background-color: #aaa;  
  	z-index:2;  
  	font-size:1.2rem;
  	opacity: 0.9;
  } 
  .item-box input{
  	margin-left: 10px;
  }
  .item-box .li2{
    border-bottom:1px solid #ececec;
    line-height:24px;
    height:24px;
    padding:0px 25px 0px 80px;
    position:relative;
  }
  
</style>

</head>
<body>
	<header>个人注册 </header>
	<div class="bannerDiv">
		<img class="banner" src="${ctx}/pages/qp/weixin/resources/img/banner.jpg" alt="">
	</div>
	<div class="info-item" style="margin-top: 10px">
		<p id="statusHtml"></p>
		<!-- <h3>本人信息</h3> -->
		<ul class="item-box">
			<input type="hidden" id="param" name="param" value="${param1 }" />
			<li id="titleId" style="color:#ffb900;text-align:center;" value="填写信息"  class="li2"></li>
			<li><label><font color="red">*</font>姓名</label> <input type="text" placeholder="姓名"
				maxlength="7" id="userName" name="userName" /></li>
			<li><label><font color="red">*</font>身份证号</label> <input type="text"
				placeholder="身份证号码" maxlength="18" id="identityNumber" name="identityNumber" /></li>
			<li><label><font color="red">*</font>车牌号码</label> <input type="text" placeholder="车牌号码"
				maxlength="10" id="licenseNo" name="licenseNo" /></li>
			<li><label><font color="red">*</font>手机号码</label> <input type="text" placeholder="手机号码"
				maxlength="11" id="mobile" name="mobile" /></li>
			<li><label>家庭住址</label> <input type="text" placeholder="详细地址"
				maxlength="24" id="postAddress" name="postAddress" /></li>
			<li class="vilidateNo" id="lasttr"><label>验证码</label> <input
				type="text" placeholder="验证码" maxlength="6" id="yzm" name="yzm" />
				<button class="vilidateNoBtn" id="buttonyzm">获取验证码</button>
			</li>
		</ul>
	</div>
	<a class="btn" style="margin-top: 12px;" id="wxregist">注册</a>
	<!--正常状态隐藏，有错误输入提示-->
	<div id="show"></div>
	
	<script src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
	<script src="${ctx}/pages/qp/weixin/resources/js/wxregist.js"></script>
</body>
</html>
