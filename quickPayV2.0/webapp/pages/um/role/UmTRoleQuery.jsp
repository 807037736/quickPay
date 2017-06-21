<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRole"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/role/UmTRole.js"></script>
<script language="javascript" src="${ctx}/pages/um/common/systemSelect.js"></script>
<script type="text/javascript">
	$(function() {
		$('#win').window({

			width : 400,

			height : 400,

			modal : true,

			closed : true

		});
	});
</script>
<style type="text/css">
.myul {
	margin-left: 30px;
}
</style>
</head>
<body>
<div class="right_detail_top"><h3>角色管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">

					<tr>

						<td class="bgc_tt short">所属系统</td>
						<td class="long">
							<select class="input_w w_15" id="svrCode" name="umTRole.serverCode"></select>
							<input type="hidden"  id="svrCodeOld" value="${serverCode}"  />
						</td>
						<td class="bgc_tt short">角色名称</td>
						<td class="long"><input class='input_w w_15'
							name="umTRole.roleCName" id="umTRole.roleCName"
							value="${umTRole.roleCName}"></td>
					</tr>
					<%--
					<tr>
						<td class="bgc_tt short">用户类型</td>
						<td class="long"><ce:select name="umTRole.userType"
								cssClass='input_w w_15' value="${umTRole.userType}"
								list="#@java.util.LinkedHashMap@{'01':'内部','02':'外部'}"
								cssStyle="width:9em;" /></td>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><ce:select name="umTRole.validStatus"
								cssClass='input_w w_15' value="${umTRole.validStatus}"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
								cssStyle="width:9em;" /></td>
					</tr>
					--%>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"> 
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div id="win" iconCls="icon-save" title="请选择Cognos菜单"
		data-options="closed:true">
		<form id="of">
			<input type="hidden" id="curRoleId" name="roleId" />
			<input type="hidden" id="curRoleName" name="roleCName" />
			<input
				type="checkbox" name="oc" value="read" checked/>读 <input
				type="checkbox" name="oc" value="write" />写 <input type="checkbox"
				name="oc" value="traverse" checked/>遍历 <input type="checkbox" name="oc"
				value="execute" checked/>执行
		</form>
		<ul id="cmenu" class="easyui-tree myul"></ul>
		<center>
			<input type="button" id="" name="roleId"
				onclick="addMenuToCognosRole();" class="button_ty" value="确定">
			<input type="button" id=""
				onclick="javascript:$('#win').window('close');" class="button_ty"
				value="关闭">

		</center>
	</div>
	<div class="margin-control">
	<table id="UmTRoleTable"></table>
	</div>
	<input type="hidden" value="${cognosMenu}" id="cognosMenu"/>
</body>
</html>
