<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserRole" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtuserrole/UmTUserRole.js"></script>
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
	<td class="bgc_tt short">
		用户角色ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTUserRole.id.userRoleId" id="umTUserRole.id.userRoleId" value="${umTUserRole.id.userRoleId}">
		</td>
	<td class="bgc_tt short">
		角色ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.roleId" id="umTUserRole.roleId" value="${umTUserRole.roleId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.userCode" id="umTUserRole.userCode" value="${umTUserRole.userCode}">
		</td>
	<td class="bgc_tt short">
		角色代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.roleCode" id="umTUserRole.roleCode" value="${umTUserRole.roleCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.validStatus" id="umTUserRole.validStatus" value="${umTUserRole.validStatus}">
		</td>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="UmTUserRoleTable"></table>
</body>
</html>
