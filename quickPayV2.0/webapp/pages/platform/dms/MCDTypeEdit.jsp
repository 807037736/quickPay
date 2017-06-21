<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.platform.dms.schema.model.MCDType"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/platform/dms/MCDType.js"></script>
<script type="text/javascript">
	
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#opreateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="opreateType" id="opreateType"
		value="${opreateType}" />
	<form name="fm" id="fm" action="/wf/mcDType" namespace="/wf/mcDType"
		method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${opreateType == 'update'}">
							修改 通用代码类型 信息
						</c:when>
									<c:when test="${opreateType == 'add'}">
							增加 通用代码类型 信息
						</c:when>
									<c:when test="${opreateType == 'view'}">
							查看 通用代码类型 信息
						</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="bgc_tt short">代码类型</td>
				<td class="long"><c:choose>
						<c:when test="${opreateType == 'update'}">
								${mcDType.codeType}<input type="hidden"
								name="mcDType.codeType"
								id="mcDType.codeType"
								value="${mcDType.codeType}">
						</c:when>
						<c:when test="${opreateType == 'add'}">
							<input class='input_w w_15 easyui-validatebox' name="mcDType.codeType"
								id="mcDType.codeType" required="true"
								value="${mcDType.codeType}">
						</c:when>
					</c:choose></td>
				<td class="bgc_tt short">代码类型描述</td>
				<td class="long"><input class='input_w w_15'
					name="mcDType.codeTypeDesc" id="mcDType.codeTypeDesc"
					value="${mcDType.codeTypeDesc}"></td>
			</tr>
			<%-- <tr>
				<td class="bgc_tt short">新代码类型</td>
				<td class="long"><input class='input_w w_15'
					name="mcDType.newCodeType"
					id="mcDType.newCodeType"
					value="${mcDType.newCodeType}">
				</td>
				<td class="bgc_tt short">数据来源</td>
				<td class="long">
					<select class="input_w w_15 easyui-combobox"
							name="mcDType.dataSource">
							<c:choose>
								<c:when test="${mcDType.dataSource == null}">
									<option value="khst">客户视图</option>
									<option value="khyx" selected>客户营销</option>
								</c:when>
							</c:choose>
							<c:if test="${mcDType.dataSource == 'khyx'}">
								<option value="khst">客户视图</option>
								<option value="khyx" selected>客户营销</option>
							</c:if>
							<c:if test="${mcDType.dataSource == 'khst'}">
								<option value="khst" selected>客户视图</option>
								<option value="khyx">客户营销</option>
							</c:if>
					</select>
				</td>
			</tr> --%>
			<tr>
				<td class="bgc_tt short">过期日期</td>
				<td class="long">
					<input name="mcDType.invalidDate" id="mcDType.invalidDate" 
					class='input_w w_15 selectcode easyui-validatebox' type="text" required="true"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${mcDType.invalidDate}" />
				</td>
				<td class="bgc_tt short">是否有效</td>
				<td class="long">
					<select class="input_w w_15 easyui-combobox"
						name="mcDType.validStatus">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" />
					<input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
