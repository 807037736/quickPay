<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta http-equiv="Content-Type" content="application/json" charset="utf-8">
<title>事故记录</title>
<meta name="keywords" content="事故记录">
<meta name="description" content="事故记录">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/index.css">
<style>
	.caseName {
		width: 15%;
	}
</style>
</head>
<body>
	<header>
    	<a class="leftBtn" onclick="javascript:window.close();"></a>事故处理
	</header>
	<c:choose>
		<c:when test="${empty list }">
			<div class="noCaseRecordDiv">
				<img src="${ctx }/pages/qp/weixin/resources/img/icon_no_record.png" alt="无事故记录"><br> <span>无事故记录</span>
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="case1" items="${list }">
				<div class="caseDetail caseRes">
					<table class="caseRes">
						<tr>
							<td class="caseName caseResponse">时间</td>
							<td class="caseResTd"><span>${fn:substring(case1.caseTime, 0, 10)}</span> <span>${fn:substring(case1.caseTime, 10, 19)}</span></td>
							<td class="caseResTextDetail" rowspan="2" onclick="casedetail('${case1.caseId }')"><img src="${ctx }/pages/qp/weixin/resources/img/icon_details.png" alt="查看详情">
								<div>查看详情</div>
							</td>
						</tr>
						<tr>
							<td>地点</td>
							<td>${case1.caseAddress }</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<form id="caseform" method="POST" action="${ctx}/weixin/case/casedetail.do">
		<input id="caseId" name="caseId" type="hidden">
		<input type="hidden" id="param" name="param" value="${param1 }" />
	</form>
	<script type="text/javascript">
		var casedetail = function(id) {
			$("#caseId").val(id);
			$("#caseform").submit();
		}
	</script>
</body>
</html>
