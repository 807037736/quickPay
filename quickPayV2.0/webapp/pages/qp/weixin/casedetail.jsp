<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta http-equiv="Content-Type" content="application/json" charset="utf-8">
<title>案件详情</title>
<meta name="keywords" content="案件详情">
<meta name="description" content="案件详情">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/index.css">
</head>
<body>
	<header>
    	<a class="leftBtn" onclick="javascript:window.history.go(-1);"></a>事故详情
	</header>
	<legend class="caseInfo">基本信息</legend>
	<div class="caseDetail caseDetail-info">
		<table>
			<tr>
				<td class="caseName">事故时间</td>
				<td>${fn:substring(case1.caseTime, 0, 10)}</td>
			</tr>
			<tr>
				<td>事故地点</td>
				<td>${case1.caseAddress }</td>
			</tr>
			<tr>
				<td>天气</td>
				<td>${case1.caseWeatherDesc }</td>
			</tr>
			<tr>
				<td>事故形态</td>
				<td>${case1.caseNotes }</td>
			</tr>
		</table>
	</div>
	<c:forEach var="accident" items="${accidents }">
		<legend class="caseInfo caseInfo-person">当事人信息</legend>
		<div class="caseDetail caseInfo-person-detail">
			<table>
				<tr>
					<td class="caseName">姓名</td>
					<td>${accident.driverName }</td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td>${fn:substring(accident.driverMobile, 0, 3)}****${fn:substring(accident.driverMobile, 7, 11)}</td>
				</tr>
				<tr>
					<td>车牌号码</td>
					<td>${accident.driverVehicleNumber}</td>
				</tr>
				<tr>
					<td>身份证号</td>
					<td>${fn:substring(accident.driverIDNumber, 0, 6)}******${fn:substring(accident.driverIDNumber, 12, 18)}</td>
				</tr>
				<tr>
					<td>保险公司</td>
					<td>${accident.coName }</td>
				</tr>
				<tr>
					<td>车辆类型</td>
					<td>${accident.driverVehicleTypeDesc }</td>
				</tr>
				<tr>
					<td>准驾车型</td>
					<td>${accident.permissionDrive }</td>
				</tr>
				<tr>
					<td>自助上传照片</td>
					<td><button onclick="showAccPic('${accident.groupId }')">查看照片</button></td>
				</tr>
				<tr>
					<td>违反法律法规</td>
					<td>${accident.driverLawDesc }</td>
				</tr>
				<tr>
					<td>责任认定</td>
					<td>${accident.driverLiabilityDesc }</td>
				</tr>
				<tr>
					<td>查勘照片</td>
					<td><button onclick="showSurveyPic('${accident.surveyGroupId }')">查看照片</button></td>
				</tr>
			</table>
		</div>
	</c:forEach>
	<form id="surveyPicform" action="${ctx }/weixin/case/casesurveypic.do" method="POST">
		<input id="surveyGroupId" name="surveyGroupId" type="hidden">
	</form>
	<form id="accPicform" action="${ctx }/weixin/case/caseaccpic.do" method="POST">
		<input id="groupId" name="groupId" type="hidden">
	</form>
	<script type="text/javascript">
		var showSurveyPic = function (groupId) {
			$("#surveyGroupId").val(groupId);
			$("#surveyPicform").submit();
		};
		
		var showAccPic = function (groupId) {
			$("#groupId").val(groupId);
			$("#accPicform").submit();
		};
	</script>
</body>
</html>
