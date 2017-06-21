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
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/qp/qpticdocument"
		namespace="/qp/qpticdocument" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${operateType == 'update'}">
					修改QpTICDocument信息
				</c:when>
							<c:when test="${operateType == 'add'}">
					单证发放
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看QpTICDocument信息
				</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
			
			<tr>
				<!-- <td class="bgc_tt short">发放批次号</td>  -->
				<td class="long"><c:choose>
						<c:when test="${operateType == 'update'}">
${qpTICDocument.id.batchNo}					<input type="hidden"
								name="qpTICDocument.id.batchNo" id="qpTICDocument.id.batchNo"
								value="${qpTICDocument.id.batchNo}">
						</c:when>
						<c:when test="${operateType == 'add'}">
							<input class='input_w w_15' type="hidden"
								name="qpTICDocument.id.batchNo" id="qpTICDocument.id.batchNo"
								value="${qpTICDocument.id.batchNo}">
						</c:when>
						<c:when test="${operateType == 'view'}">
${qpTICDocument.id.batchNo}					<input type="hidden"
								name="qpTICDocument.id.batchNo" id="qpTICDocument.id.batchNo"
								value="${qpTICDocument.id.batchNo}">
						</c:when>
					</c:choose></td>
				<%-- <td class="bgc_tt short">发放人</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.grantor" id="qpTICDocument.grantor"
					value="${qpTICDocument.grantor}"></td> --%>
			</tr>
		<%-- 	<tr>
				<td class="bgc_tt short">快出中心id</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.centerId" id="qpTICDocument.centerId"
					value="${qpTICDocument.centerId}"></td>
				<td class="bgc_tt short">发放时间</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.grantTime" id="qpTICDocument.grantTime"
					value="${qpTICDocument.grantTime}"
					onclick="WdatePicker({skin:'whyGreen'})"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">发放数量</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.grantCount" id="qpTICDocument.grantCount"
					value="${qpTICDocument.grantCount}"></td>
				<td class="bgc_tt short">单证起始号</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.startNo" id="qpTICDocument.startNo"
					value="${qpTICDocument.startNo}"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">单证结束号</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.endNo" id="qpTICDocument.endNo"
					value="${qpTICDocument.endNo}"></td>
				<td class="bgc_tt short">有效状态</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.validStatus" id="qpTICDocument.validStatus"
					value="${qpTICDocument.validStatus}"></td>
			</tr>
 --%>

			<tr>

				<td class="bgc_tt short">快出中心</td>
				<td class="long"><select class='input_w w_15' name="qpTICDocument.centerId" id="qpTICDocument.centerId" value="${qpTICDocument.centerId}">
							<option value="">-请选择-</option>
							<c:forEach var="center" items="${centers }">
								<option value="${center.id.centerId}">${center.centerName}</option>
							</c:forEach>
						</select></td>

				<td class="bgc_tt short">发放数量</td>
				<td class="long"><input class='input_w w_15'
					name="qpTICDocument.grantCount" id="qpTICDocument.grantCount"
					value="${qpTICDocument.grantCount}"></td>
			</tr>

		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="发放" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">

</script>
</html>
