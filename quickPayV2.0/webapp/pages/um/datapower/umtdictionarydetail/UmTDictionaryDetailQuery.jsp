<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionaryDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtdictionarydetail/UmTDictionaryDetail.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="right_detail_top">
		<h3>权限字典详细信息</h3>
	</div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">字典名称</td>
						<td class="long"><input type="text"
							class="easyui-validatebox  selectcode" name="dictionaryId1"
							ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
							onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							readonly="readonly" value="${umTDictionaryDetail.dictionaryId}" />
							<input type="hidden" name="umTDictionaryDetail.dictionaryId"
							id="umTDictionaryDetail.dictionaryId"
							value="${umTDictionaryDetail.dictionaryId}" /></td>
						<td class="bgc_tt short">操作目标</td>
						<td class="long"><input class='input_w w_30'
							name="umTDictionaryDetail.targetName"
							id="umTDictionaryDetail.targetName"
							value="${umTDictionaryDetail.targetName}" maxlength="50"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">操作域</td>
						<td class="long"><input class='input_w w_30'
							name="umTDictionaryDetail.targetField"
							id="umTDictionaryDetail.targetField"
							value="${umTDictionaryDetail.targetField}" maxlength="30"></td>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><ce:select cssClass="input_w w_15"
								name="umTDictionaryDetail.validStatus"
								id="umTDictionaryDetail.validStatus"
								list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}"
								value="umTDictionaryDetail.validStatus" /></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
		<table id="UmTDictionaryDetailTable"></table>
	</div>
</body>
</html>
