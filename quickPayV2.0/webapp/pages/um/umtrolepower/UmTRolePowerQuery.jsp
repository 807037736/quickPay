<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRolePower"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/umtrolepower/UmTRolePower.js"></script>
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
						<td class="bgc_tt short">主键ID</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.id.rpId" id="umTRolePower.id.rpId"
							value="${umTRolePower.id.rpId}"></td>
						<td class="bgc_tt short">角色ID</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.roleId" id="umTRolePower.roleId"
							value="${umTRolePower.roleId}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">字典ID</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.dictionaryId" id="umTRolePower.dictionaryId"
							value="${umTRolePower.dictionaryId}"></td>
						<td class="bgc_tt short">权限操作符</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.operationSymbol"
							id="umTRolePower.operationSymbol"
							value="${umTRolePower.operationSymbol}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">权限域值</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.powerValue" id="umTRolePower.powerValue"
							value="${umTRolePower.powerValue}"></td>
						<td class="bgc_tt short">8位机构号</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.comId" id="umTRolePower.comId"
							value="${umTRolePower.comId}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><input class='input_w w_15'
							name="umTRolePower.validStatus" id="umTRolePower.validStatus"
							value="${umTRolePower.validStatus}"></td>
						<td class="bgc_tt short"></td>
						<td class="long"></td>
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
	<br>
	<table id="UmTRolePowerTable"></table>
</body>
</html>
