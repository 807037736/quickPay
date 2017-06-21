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
<meta name="keywords" content="协议书">
<meta name="description" content="协议书">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:window.history.go(-1);"></a>协议书
	</header>
	<div class="protocol-item">
	    <h3><span class="protocol-btn"></span>基本信息</h3>
		<ul class="protocol-box">		
			<li>
				<label>事故时间</label>
				<span>${fn:substring(case1.caseTime, 0, 19)}</span>				
			</li>
			<li>
				<label>事故地点</label>
				<span>${case1.caseStreet }</span>				
			</li>
			<li>
				<label>天气</label>
				<span id="caseWeatherName"></span>				
			</li>
			<li>
				<label>事故形态</label>
				<span>${case1.caseNotes }</span>				
			</li>
		</ul>
	</div>
	<c:forEach var="accident" items="${accidents }">
		<div class="protocol-item">
		    <h3><span class="protocol-btn"></span>当事人信息</h3>
			<ul class="protocol-box">		
				<li>
					<label>姓名</label>
					<span>${accident.driverName }</span>				
				</li>
				<li>
					<label>手机号码</label>
					<span>${accident.driverMobile }</span>				
				</li>
				<li>
					<label>车牌号</label>
					<span>${accident.driverVehicleNumber }</span>				
				</li>
				<li>
					<label>身份证号</label>
					<span>${accident.driverIDNumber }</span>				
				</li>
				<li>
					<label>责任认定</label>
					<span>
						<c:choose>
							<c:when test="${accident.driverLiability == 10}">
								全责
							</c:when>
							<c:when test="${accident.driverLiability == 11}">
								主要责任
							</c:when>
							<c:when test="${accident.driverLiability == 12}">
								次要责任
							</c:when>
							<c:when test="${accident.driverLiability == 13}">
								同等责任
							</c:when>
							<c:when test="${accident.driverLiability == 14}">
								无责
							</c:when>
						</c:choose>
					</span>				
				</li>
			</ul>
		</div>	
	</c:forEach>
	<a class="btn" onclick="submit()">定责完成</a>
	<form id="submitform" action="${ctx }/weixin/case/finish.do" method="POST">
		<input name="wxCaseId" type="hidden" value="${wxCaseId }">
		<input type="hidden" id="param" name="param" value="${param1 }" />
	</form>
	<script type="text/javascript">
		var submit = function () {
			$("#submitform").submit();
		};
		
		$(function () {
			$("#caseWeatherName").text(weather["${case1.caseWeather }"]);
		});
		
		// 先写死,将来使用接口的形式获取数据
		var weather = {1:"晴",3:"阴",5:"雾",31:"雪",33:"雨"};
	</script>
</body>
</html>