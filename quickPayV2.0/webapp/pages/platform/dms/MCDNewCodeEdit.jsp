<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.platform.dms.schema.model.MCDNewCode"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/platform/dms/MCDNewCode.js"></script>
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
<div class="right_detail_top"><h3>数据字典信息</h3></div>
	<input type="hidden" name="opreateType" id="opreateType"
		value="${opreateType}" />
	<form name="fm" id="fm" action="/wf/mcDNewCode" namespace="/wf/mcDNewCode"
		method="post">
		<div id="wrapper">
			<div id="container">
		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${opreateType == 'update'}">
						</c:when>
									<c:when test="${opreateType == 'add'}">
						</c:when>
									<c:when test="${opreateType == 'view'}">
						</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="bgc_tt short">代码</td>
				<td class="long"><c:choose>
						<c:when test="${opreateType == 'update'}">
								${mcDNewCode.id.codeCode}<input type="hidden"
								name="mcDNewCode.id.codeCode"
								id="mcDNewCode.id.codeCode"
								value="${mcDNewCode.id.codeCode}">
						</c:when>
						<c:when test="${opreateType == 'add'}">
							<input class='input_w w_30' name="mcDNewCode.id.codeCode"
								id="mcDNewCode.id.codeCode"
								value="${mcDNewCode.id.codeCode}">
						</c:when>
						<c:when test="${opreateType == 'view'}">
							${mcDNewCode.id.codeCode}<input type="hidden"
								name="mcDNewCode.id.codeCode"
								id="mcDNewCode.id.codeCode"
								value="${mcDNewCode.id.codeCode}">
						</c:when>
					</c:choose></td>
				<td class="bgc_tt short">中文名称</td>
				<td class="long"><input class='input_w w_30'
					name="mcDNewCode.codeCName" id="mcDNewCode.codeCName"
					value="${mcDNewCode.codeCName}"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">代码类型</td>
				<td class="long"><!--  <input class='input_w  selectcode w_30'
					name="mcDNewCode.id.codeType"
					id="mcDNewCode.id.codeType"
					value="${mcDNewCode.id.codeType}"
					ondblclick="code_CodeSelect(this,'/common/queryCodeType.do', '0,1', 'y');"
					>
					<input class='input_r w_15' readonly name="mcDNewCode.codeTypeDesc" id="mcDNewCode.codeTypeDesc">
					-->
					<select class='input_w  selectcode w_30' name="mcDNewCode.id.codeType" id="mcDNewCode.id.codeType">
						<option value="">-请选择-</option>   
						<c:forEach var="type" items="${typelist }">
							<option value="${type.codeType}" <c:if test="${type.codeType==mcDNewCode.id.codeType}">selected</c:if>>${type.codeTypeDesc}</option>
						</c:forEach>
					</select>
				</td>
				<%-- <td class="bgc_tt short">数据来源</td>
				<td class="long">
					<input class="input_w w_30" id="mcDNewCode.dataSource"
						name="mcDNewCode.dataSource" value="${mcDNewCode.dataSource}" readOnly/>
				</td> --%>
				<td class="bgc_tt short">是否有效</td>
				<td class="long">
					<select class="input_w w_30 easyui-combobox"
						name="mcDNewCode.validStatus">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="bgc_tt short">排序</td>
				<td class="long"><input class='input_w w_30'
					name="mcDNewCode.sort" id="mcDNewCode.sort"
					value="${mcDNewCode.sort}"></td>
					
			
			</tr>
			<%-- <tr>
				<td class="bgc_tt short">新代码名称</td>
				<td class="long"><input class='input_w w_30'
					name="mcDNewCode.newCodeCode"
					id="mcDNewCode.newCodeCode"
					value="${mcDNewCode.newCodeCode}"></td>
				<td class="bgc_tt short">英文名称</td>
				<td class="long"><input class='input_w w_30'
					name="mcDNewCode.codeEName"
					id="mcDNewCode.codeEName"
					value="${mcDNewCode.codeEName}"></td>	
			</tr>
			<tr>
				<td class="bgc_tt short">是否公开</td>
				<td class="long">
					<select class="input_w w_30 easyui-combobox"
						name="mcDNewCode.commonFlag">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
				<td class="bgc_tt short">是否有效</td>
				<td class="long">
					<select class="input_w w_30 easyui-combobox"
						name="mcDNewCode.validStatus">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr> --%>
			
		
		</table>
		</div>
		</div>
		<table class="fix_table">
			<tr>
				<td colspan="6" align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
