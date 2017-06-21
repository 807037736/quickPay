<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionary"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtdictionary/UmTDictionary.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	
	<div class="right_detail_top"><h3>权限字典管理</h3></div>

	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">字典代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTDictionary.dictionaryCode"
							id="umTDictionary.dictionaryCode"
							value="${umTDictionary.dictionaryCode}" maxlength="30"></td>
						<td class="bgc_tt short">字典名称</td>
						<td class="long"><input class='input_w w_15'
							name="umTDictionary.ditionaryName"
							id="umTDictionary.ditionaryName"
							value="${umTDictionary.ditionaryName}" maxlength="50"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">机构代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTDictionary.comCode" id="umTDictionary.comCode"
							value="${umTDictionary.comCode}" maxlength="50"></td>
						<td class="bgc_tt short">有效状态</td>
						<td class="long">
							<ce:select name="umTDictionary.validStatus"  cssStyle="width:80px"
								id="umTDictionary.validStatus" list="#@java.util.LinkedHashMap@{'':'','1':'有效','0':'无效'}" 
								value="umTDictionary.validStatus" />
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
		<table id="UmTDictionaryTable"></table>
	</div>
</body>
</html>
