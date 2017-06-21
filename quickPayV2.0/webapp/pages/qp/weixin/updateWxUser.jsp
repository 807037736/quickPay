<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json"
	charset="utf-8">
<title>个人中心</title>
<meta name="keywords" content="注册信息修改">
<meta name="description" content="注册信息修改">
<meta name="viewport" id="viewport"
	content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet"
	href="${ctx}/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet"
	href="${ctx}/pages/qp/weixin/resources/css/index.css">
<style type="text/css">
	#show {
		display: none;
		text-align: center;
		color: #fff;
		background: #000000;
		border-radius: 10px 10px;
		position: absolute;
		top: 50%;
		left: 30%;
		width: 40%;
		padding-top: 5%;
		padding-bottom: 5%;
		background-color: #aaa;
		z-index: 2;
		font-size: 1.2rem;
		opacity: 0.9;
	}
	
	.item-box .li2{
	    line-height:30px;
	    height:30px;
  	}
	
	.item-box input{
	   position:absolute;
	   left:38%;
	   width: 51%;
	   color: #AAAAAA;
	   margin-top: 3%;
	}
</style>

</head>
<body>
	<!-- <header> <a class="leftBtn"></a>信息修改 </header> -->
	<header>信息修改 </header>
	<div class="bannerDiv">
		<img class="banner"
			src="${ctx}/pages/qp/weixin/resources/img/banner.jpg" alt="">
	</div>
	<h2 style="color: #ff9600;font-size: 22px;margin-top: 5px;" align="center" id="updateW">您已经注册!</h2>
	<div class="info-item" style="margin-top: 5px">
		<input  type="hidden" id="state" value="0"></input>
		<input type="hidden" id="param" name="param" value="${param1 }" />
		<input type="hidden" id="yzm" name="yzm" value="123456" />
		<p id="statusHtml"></p>
		<ul class="item-box">
			<h3 id="titleId" class="li2" style="color:#ffb900;font-size:1.8rem;" ></h3>
			<li><label><font color="red">*</font>姓名</label> <input class="input1" type="text" placeholder="必填"
				readonly="ture" maxlength="7" id="userName" name="userName"
				value="${userName }" /><!--正常状态隐藏删除图标，当输入内容时显示删除图标，点击删除图标，隐藏-->
			</li>
			<li><label><font color="red">*</font>身份证</label> <input class="input1" type="text"
				placeholder="必填" maxlength="18" id="identityNumber"
				name="identityNumber" value="${identityNumber }" readonly="ture" /></li>
			<li><label><font color="red">*</font>车牌号</label> <input class="input1" type="text" placeholder="必填"
				readonly="ture" maxlength="10" id="licenseNo" name="licenseNo"
				value="${licenseNo }" /></li>
			<li><label>家庭住址</label> <input class="input1" type="text" placeholder="未填写" readonly="ture" 
				maxlength="24" id="postAddress" name="postAddress" value="${postAddress }" /></li>
			<li><label><font color="red">*</font>手机号</label> <input type="text" placeholder="必填"
				readonly="ture" maxlength="11" id="mobile" name="mobile" 
				value="${mobile }" /></li>
		</ul>
	</div>
	<a class="btn" style="margin-top: 10px;" id="update">点击可修改</a>
	<!--正常状态隐藏，有错误输入提示-->
	<div id="show"></div>

	<script src="${ctx}/pages/qp/weixin/resources/js/jquery.min.js"></script>
	<script src="${ctx}/pages/qp/weixin/resources/js/wxregist.js"></script>
	<script type="text/javascript">
		function vaildSame() {
			if ($('#userName').val()=="${userName }"&& $('#licenseNo').val()=="${licenseNo }"&& $('#identityNumber').val()=="${identityNumber }"&& $('#postAddress').val()=="${postAddress }") {
				$("#show").show();
				$("#show").html("").html("信息无改动，无需修改。");
				setTimeout(function() {
					$("#show").hide();
				}, 3000);
				return false;
			} else {
				return true;
			}
		}
	</script>
	
	
</body>
</html>
