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
</head>
<body>
<div class="right_detail_top"><h3>数据字典管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">代码类型</td>
						<td class="long"><input class='input_w w_15'
							name="codeType"
							id="codeType"
							value="${codeType}">
							<input class='input_r w_15'
							name="codeTypeName"
							id="codeTypeName"
							value="${codeTypeName}">
						</td>
						
						<!-- <td class="bgc_tt short">是否有效</td>
				        <td class="long"><select class="input_w w_30 easyui-combobox"
						    name="validStatus">
						    <option value="1">是</option>
						    <option value="0">否</option>
					    </select>
				        </td> -->
						<!-- <td class="bgc_tt short">数据来源</td>
						<td class="long"><select class="input_w w_15 easyui-combobox"
							name="dataSource">
							<option value="khst">客户视图</option>
							<option value="khyx">客户营销</option>
							<option value="dms3g:prpdnewcode">三代数据</option>
						</select>
						</td> -->
					</tr>
					<tr>
						<td colspan="6" align="center">
						<input type="button" class="button_ty" value="查 询" onclick="executeQuery();">
						<input type="button" id="addButton" onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	
	<div class="margin-control">
	<table id="ResultTable"></table>
	</div>
	<div id="typeConfigWindow" class="easyui-window" title="类型配置" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:480px;padding:10px;">
		<iframe id="typeConfigFrame" width="100%" height="100%" style="border:0" src="${ctx}/pages/platform/dms/MCDTypeQuery.jsp"></iframe>
	</div>
</body>
</html>
