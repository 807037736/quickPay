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
						<td class="bgc_tt short">代码类型</td>
						<td class="long"><input class='input_w w_15'
							name="codeType"
							id="codeType"
							value="${codeType}"></td>
						<!-- <td class="bgc_tt short">数据来源</td>
						<td class="long"><select class="input_w w_15 easyui-combobox"
							name="dataSource">
							<option value="khst">客户视图</option>
							<option value="khyx">客户营销</option>
							<option value="dms3g:prpdtype">三代数据</option>
						</select>
						</td> -->
						<td align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();"></td>
						<td align="center">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="TypeResultTable"></table>
</body>
</html>
