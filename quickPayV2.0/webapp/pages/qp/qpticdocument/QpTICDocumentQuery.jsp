<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICDocument"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qpticdocument/QpTICDocument.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
					<!--  
							<tr>
	<td class="bgc_tt short">
		发放批次号
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTICDocument.id.batchNo" id="qpTICDocument.id.batchNo" value="${qpTICDocument.id.batchNo}">
		</td>
	<td class="bgc_tt short">
		发放人
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.grantor" id="qpTICDocument.grantor" value="${qpTICDocument.grantor}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		快出中心id
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.centerId" id="qpTICDocument.centerId" value="${qpTICDocument.centerId}">
		</td>
	<td class="bgc_tt short">
		发放时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICDocument.grantTime" id="qpTICDocument.grantTime" value="${qpTICDocument.grantTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		发放数量
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.grantCount" id="qpTICDocument.grantCount" value="${qpTICDocument.grantCount}">
		</td>
	<td class="bgc_tt short">
		单证起始号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.startNo" id="qpTICDocument.startNo" value="${qpTICDocument.startNo}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		单证结束号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.endNo" id="qpTICDocument.endNo" value="${qpTICDocument.endNo}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocument.validStatus" id="qpTICDocument.validStatus" value="${qpTICDocument.validStatus}">
		</td>
							 </tr> 	
				-->
					<tr>
						<td class="bgc_tt short">快处中心</td>
						<td class="long">
						<select class='input_w w_15' name="qpTICDocument.centerId" id="qpTICDocument.centerId" value="${qpTICDocument.centerId}">
							<option value="">-请选择-</option>
							<c:forEach var="center" items="${centers }">
								<option value="${center.id.centerId}">${center.centerName}</option>
							</c:forEach>
						</select>
						</td>
						
						<td class="bgc_tt short">发放时间</td>
						<td><input class='input_w' style=""
							name="qpTICDocument.grantTimeStar" id="qpTICDocument.grantTimeStar"
							value="${qpTICDocument.grantTimeStar}"
							onclick="WdatePicker({skin:'whyGreen'})"> 
							至
							<input class='input_w' style="" name="qpTICDocument.grantTimeEnd" id="qpTICDocument.grantTimeEnd" value="${qpTICDocument.grantTimeEnd}" onclick="WdatePicker({skin:'whyGreen'})">
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="单证发放"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTICDocumentTable"></table>
</body>
</html>
