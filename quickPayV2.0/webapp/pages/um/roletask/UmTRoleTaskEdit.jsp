<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRole"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/role/UmTRole.js"></script>
<script type="text/javascript">
	
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#opreateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
	
	$(function(){
		var comData = $("#comStr").val();
		var taskData = $("#taskStr").val();
		createTree('comTree',0,comData);	
		createTree('taskTree',4,taskData);
		
	});
	
</script>
</head>

<body>
<div class="right_detail_top"><h3><c:choose>
							<c:when test="${opreateType == 'update'}">
					修改角色信息
				</c:when>
							<c:when test="${opreateType == 'add'}">
					增加角色信息
				</c:when>
							<c:when test="${opreateType == 'view'}">
					查看角色信息
				</c:when>
						</c:choose></h3></div>
	<input type="hidden" name="opreateType" id="opreateType"
		value="${opreateType}" />
	<form name="fm" id="fm" action="/um/umtrole" namespace="/um/umtrole"
		method="post">

		<table class="fix_table">
			<tr>
				<input type="hidden" name="umTRole.id.roleId"
					value="${umTRole.id.roleId}" />
				<input type="hidden" name="comStr" value="${comStr}" id="comStr"/>
				<input type="hidden" name="taskStr" value="${taskStr}" id="taskStr"/>
			<tr>
				<td class="bgc_tt short">角色代码</td>
				<td class="long"><input class='input_w w_15'
					name="umTRole.roleCode" id="umTRole.roleCode"
					value="${umTRole.roleCode}"></td>
				<td class="bgc_tt short">角色名称</td>
				<td class="long"><input class='input_w w_15'
					name="umTRole.roleCName" id="umTRole.roleCName"
					value="${umTRole.roleCName}"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">有效状态</td>
				<td class="long"><ce:select name="umTRole.validStatus"
						list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" cssStyle="width:9em" value="umTRole.validStatus" /></td>
				<td class="bgc_tt short">标识字段</td>
				<td class="long"><input class='input_w w_15'
					name="umTRole.flag" id="umTRole.flag" value="${umTRole.flag}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">备注</td>
				<td class="long" colspan="3"><input class='input_w w_15'
					name="umTRole.remark" id="umTRole.remark" value="${umTRole.remark}">
				</td>
			</tr>
		</table>
		<div class="easyui-tabs" id="tree" style="width:600px;height:500px;">
			<div title="机构">
				<ul id="comTree" class="easyui-tree"></ul>
			</div>
			<div title="功能">
				<ul id="taskTree" class="easyui-tree"></ul>
			</div>
		</div>
		<br>
		<table class="fix_table">
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
