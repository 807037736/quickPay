<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtgroup/Com4sManage.js"></script>
<script type="text/javascript">
$(function(){
});
</script>
</head>
<body>
	<form name="fmCom4s" id="fmCom4s">
		<div id="wrapper">
			<div id="container">
				<div class="right_detail_top"><h3>车行查询</h3></div>
				<table class="fix_table">
				<tr>
		<td class="bgc_tt short">车行代码
		</td>
		<td class="long">
			<input class='input_w w_15' name="umTGroup.groupCode" id="umTGroup.groupCode" value="">
		</td>
		<td class="bgc_tt short">车行名称
		</td>
		<td class="long">
			<input class='input_w w_15' name="umTGroup.groupName" id="umTGroup.groupName" value="">
		</td>
			</tr>
			<tr>

		<input type="hidden" name="umTGroup.groupType" id="umTGroup.groupType" value="2"> 
					<td class="bgc_tt short"></td>
					<td class="long"></td>
			</tr>
			<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询" onclick="executeCom4sQuery();">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<div class="margin-control">
	<table id="Com4sManageTable"></table>
	</div>
	
	<!-- 角色人员管理列表窗口 -->
	<div id="EditUserGroupWindow" class="easyui-window" title="车行人员" style="width:590px;height:390px" data-options="modal:true,closed:true,minimizable:false,resizable:false,top:'100px'">
			<table id="UmTUserGroupTable"></table>
	</div> 
	
<!-- 车行关联人员窗口 
<div id="ConnectUserWindow" class="easyui-window" title="关联人员" style="width:560px;height:250px" data-options="modal:true,closed:true,minimizable:false,resizable:false,top:'100px',onClose:function(){$('#UserGroupCBox').combobox('clear');}">
<form name="cuf" id="cuf" action="/um/umtusergroup" namespace="/um/umtusergroup" method="post">
	<table class="fix_table">
			<tr>
	<td class="bgc_tt short">
			车行ID
		</td>
			<td class="long">
				<input readonly class="input_w w_35 readonly" name="umTUserGroup.groupId" id="umTUserGroup.groupId" value="">
			</td>
					</tr>
		<tr>
	<td class="bgc_tt short">
			车行
		</td>
			<td class="long">
				<input readonly class='input_w w_35 readonly' id="groupName" value="">
			</td>
			</tr>
		<tr>
	<td class="bgc_tt short">
			有效状态
		</td>
			<td class="long">
				<select id="umTUserGroup.validStatus" class="easyui-combobox input_w w_15" name="umTUserGroup.validStatus" panelHeight="45" data-options="required:true,editable:false">  
				<option value="1">有效</option>  
				<option value="0">无效</option>  
				</select> 
			</td>
			</tr>
		<tr>
		<td class="bgc_tt short">
			人员
		</td>
			<td class="long">
				<input id="UserGroupCBox" name="umTUserGroup.userCode" style="width:260px;" value=""> 
			</td>
			</tr>
			</table>
		<table class="fix_table">
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="userGroupSave()" ind="ind"  value="关联人员" />
						<input type="button" class="button_ty" ind="ind"  value="重置" onclick="$('#UserGroupCBox').combobox('clear');$('#umTUserGroup\\.validStatus').combobox('select','1');"/>
				</td>
			</tr>
		</table>
</form>
</div>	-->
 
</body>
</html>
