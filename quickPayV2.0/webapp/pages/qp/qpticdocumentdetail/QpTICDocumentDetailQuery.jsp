<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICDocumentDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qpticdocumentdetail/QpTICDocumentDetail.js"></script>
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
					<tr>
						<td class="bgc_tt short">快处中心</td>
						<td class="long">
						<select class='input_w w_15' name="qpTICDocumentDetail.centerId" id="qpTICDocumentDetail.centerId" value="${qpTICDocumentDetail.centerId}">
							<option value="">-请选择-</option>
							<c:forEach var="center" items="${centers }">
								<option value="${center.id.centerId}">${center.centerName}</option>
							</c:forEach>
						</select>
						</td>
							
						<td class="bgc_tt short">单证状态</td>
						<td class="long"><%-- <input class='input_w w_15'
							name="qpTICDocumentDetail.documentFlag"
							id="qpTICDocumentDetail.documentFlag"
							value="${qpTICDocumentDetail.documentFlag}"> --%>
							<select class='input_w w_15'
							name="qpTICDocumentDetail.documentFlag"
							id="qpTICDocumentDetail.documentFlag"
							>
							<option value="0">初始</option>
							<option value="2">已使用</option>
							</select>
							</td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<!-- <input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加"> --></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTICDocumentDetailTable"></table>
</body>
</html>
