<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserPower"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtuserpower/UmTUserPower.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="right_detail_top"><h3>用户数据权限维护</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">操作者类型</td>
						<td class="long"><ce:codeselect codeType="PowerOperatorType"
								name="umTUserPower.operatorType"
								value="${umTUserPower.operatorType}" /></td>
						<td class="bgc_tt short">字典ID</td>
						<td class="long"><input type="text" style="width: 300px"
							class="input_w w_15 selectcode" name="dictionaryId2"
							ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
							onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							readonly="readonly" value="${dictionaryName}" /> <input
							type="hidden" name="umTUserPower.dictionaryId"
							id="umTUserPower.dictionaryId"
							value="${umTUserPower.dictionaryId}" /></td>
					</tr>
					<tr>
						<td class="bgc_tt short">用户代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTUserPower.userCode" id="umTUserPower.userCode"
							value="${umTUserPower.userCode}" maxlength="8"></td>
						<td class="bgc_tt short">操作符</td>
						<td class="long"><ce:codeselect codeType="SymbolType" emptyOption="true"
								name="umTUserPower.operationSymbol"
								id="umTUserPower.operationSymbol"
								value="${umTUserPower.operationSymbol}" />
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">限制域值</td>
						<td class="long"><input class='input_w w_15'
							name="umTUserPower.powerValue" id="umTUserPower.powerValue"
							value="${umTUserPower.powerValue}" maxlength="40" /></td>
						<td class="bgc_tt short">有效状态</td>
						<td class="long">
							<ce:select name="umTUserPower.validStatus"  cssStyle="width:80px"
								id="umTUserPower.validStatus" list="#@java.util.LinkedHashMap@{'':'','1':'有效','0':'无效'}" 
								value="umTUserPower.validStatus" />
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTUserPowerTable"></table>
	</div>
</body>
</html>
