<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICDocumentDetail" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticdocumentdetail/QpTICDocumentDetail.js"></script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/qp/qpticdocumentdetail" namespace="/qp/qpticdocumentdetail" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTICDocumentDetail信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTICDocumentDetail信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTICDocumentDetail信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		所属批次号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.batchNo" id="qpTICDocumentDetail.batchNo" value="${qpTICDocumentDetail.batchNo}">
		</td>
	<td class="bgc_tt short">
		单证号
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTICDocumentDetail.id.documentNo}					<input type="hidden" name="qpTICDocumentDetail.id.documentNo" id="qpTICDocumentDetail.id.documentNo" value="${qpTICDocumentDetail.id.documentNo}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTICDocumentDetail.id.documentNo" id="qpTICDocumentDetail.id.documentNo" value="${qpTICDocumentDetail.id.documentNo}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTICDocumentDetail.id.documentNo}					<input type="hidden" name="qpTICDocumentDetail.id.documentNo" id="qpTICDocumentDetail.id.documentNo" value="${qpTICDocumentDetail.id.documentNo}">
			</c:when>
		</c:choose>
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		快处中心号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.centerId" id="qpTICDocumentDetail.centerId" value="${qpTICDocumentDetail.centerId}">
		</td>
	<td class="bgc_tt short">
		使用时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.usedTime" id="qpTICDocumentDetail.usedTime" value="${qpTICDocumentDetail.usedTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		使用人
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.usedPerson" id="qpTICDocumentDetail.usedPerson" value="${qpTICDocumentDetail.usedPerson}">
		</td>
	<td class="bgc_tt short">
		销毁时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.cancelTime" id="qpTICDocumentDetail.cancelTime" value="${qpTICDocumentDetail.cancelTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		销毁人
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.cancelPerson" id="qpTICDocumentDetail.cancelPerson" value="${qpTICDocumentDetail.cancelPerson}">
		</td>
	<td class="bgc_tt short">
		单证状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.documentFlag" id="qpTICDocumentDetail.documentFlag" value="${qpTICDocumentDetail.documentFlag}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		数据有效标志
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICDocumentDetail.validStatus" id="qpTICDocumentDetail.validStatus" value="${qpTICDocumentDetail.validStatus}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr>	
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
