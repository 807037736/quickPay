<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRoleCom" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/roleCom/UmTRoleCom.js"></script>
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
		ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTRoleCom.id.roleComId" id="umTRoleCom.id.roleComId" value="${umTRoleCom.id.roleComId}">
		</td>
	<td class="bgc_tt short">
		角色ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.roleId" id="umTRoleCom.roleId" value="${umTRoleCom.roleId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		角色代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.roleCode" id="umTRoleCom.roleCode" value="${umTRoleCom.roleCode}">
		</td>
	<td class="bgc_tt short">
		机构代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.comCode" id="umTRoleCom.comCode" value="${umTRoleCom.comCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.validStatus" id="umTRoleCom.validStatus" value="${umTRoleCom.validStatus}">
		</td>
	<td class="bgc_tt short">
		包含类型
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.includeType" id="umTRoleCom.includeType" value="${umTRoleCom.includeType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		机构名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.comName" id="umTRoleCom.comName" value="${umTRoleCom.comName}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr>
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
	<table id="UmTRoleComTable"></table>
</body>
</html>
