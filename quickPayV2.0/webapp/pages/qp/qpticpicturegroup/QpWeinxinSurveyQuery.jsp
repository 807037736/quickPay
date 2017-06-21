<!doctype html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
<link href="${ctx }/pages/qp/weixin/resources/css/common.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/index.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/main.css" rel="stylesheet" >
<title>自助定责</title>
</head>
<body>

<header>
    <a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>查勘员上传
</header>

<c:if test="${errorMsg!=null&&errorMsg!=''}">
	<div class="caseDetail div-search">
		<input class="inputNo" readonly="readonly" style="color: red;" value="${errorMsg }" >
	</div>
</c:if>

<c:if test="${code==null || code==''}">

<form id="fm" action="${ctx }/weixin/case/prepareSurveyView.do" method="post">
	<div class="caseDetail div-search">
	    <input autocomplete="off" class="inputNo" name="qpTTPCase.caseSerialNo" id="qpTTPCaseCaseSerialNo" value="${qpTTPCase.caseSerialNo }" type="text" placeholder="请输入认字编号" />
	    <img class="img-search" src="${ctx}/pages/qp/weixin/resources/img/icon_search_normal.png" />
	</div>
</form>


	<!--有事故，则显示事故详情列表-->
	<c:if test = "${not empty  qpTTPCase}" >
		<s:iterator value="qpTTPCase">
			<c:if test = "${not empty  qpTICAccident }" >
				<c:forEach var="b" items="${qpTICAccident}" varStatus="status">
					<div class="caseDetail caseRes">
					    <table class="caseRes">
					        <tr>
					            <td class="caseName caseRes">当事人</td>
					            <td class="caseResTd-caseNo">${b.driverName }</td>
					            <td class="caseResUpload" rowspan="2">
					            	<div  onclick="UploadSurvey('${b.caseId}','${b.acciId}')">
						            	<img src="${ctx}/pages/qp/weixin/resources/img/icon_update.png" alt="上传照片" />
						                <div>上传照片</div>
					                </div>
					            </td>
					        </tr>
					        <tr>
					            <td>车牌号</td>
					            <td>${b.driverVehicleNumber }</td>
					        </tr>
					    </table>
					</div>
				</c:forEach>
			</c:if>
		</s:iterator>
	</c:if>
	
	<form id="ffa" action="${ctx}/weixin/case/prepareSurveyUploadPictureGroup.do" method="post">
		<input type="hidden" name="acciId" id="acciId" >
	</form>
</c:if>
</body>
<script type="text/javascript">
function UploadSurvey(caseId, acciId){
	console.log("案件ID：" + caseId + "||当事件ID：" + acciId);
	//		showTips("案件ID和当事人ID不能为空", 2000);
	if(caseId == '' || caseId == undefined || acciId == '' || acciId == undefined ){
		showTips("案件ID和当事人ID不能为空", 2000);
	}
	$("#acciId").val(acciId);
	$("#ffa").submit();
// 	window.location.href = "${ctx}/weixin/case/prepareSurveyUploadPictureGroup.do?acciId=" + acciId;
// 	window.location.href = "${ctx}/pages/qp/qpticpicturegroup/QpWeinxinSurveyUpload.jsp?acciId=" + acciId;
	
}
$(".img-search").click(function(){
	var seriaNo = $("#qpTTPCaseCaseSerialNo").val();//认字编号
	if($.trim(seriaNo) != ''){
		$("#fm").submit();
	}
})
</script>
</html>