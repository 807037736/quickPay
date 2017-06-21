<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtgroup/UmTGroupManage.js"></script>
<script type="text/javascript">
$(function(){
	var ComCode="${comCode}";
	var comId="${comId}";
	var url = contextRootPath+"/um/umtgroup/queryComTree.do?queryType=0&treeType=0&nodeId="+comId;
	//流程角色代码初始值
	$('#agumTGroup\\.groupCode').val(comId+"_");

	$('#ComcodeCBox').combobox({
		required:true,
		editable: false,
		valueField: 'id',
		textField:'text',
		readonly:true,
		value:comId,
		url:url
		});
	 
	 $('#cgComcodeCBox').combobox({
			required:true,
			editable: false,
			valueField: 'id',
			textField:'text',
			readonly:true,
			value:comId,
			url:url
			});

});
</script>
</head>
<body>
	<form name="fmGroup" id="fmGroup">
		<div id="wrapper">
			<div id="container">
				<div class="right_detail_top"><h3>配置流程角色</h3></div>
				<table class="fix_table">
				<tr>
				<td class="bgc_tt short">流程角色代码</td>
				<td class="long">
					<input class='input_w w_15' name="umTGroup.groupCode" id="umTGroup.groupCode" value="">
				</td>
				<td class="bgc_tt short">流程角色名称</td>
				<td class="long">
					<input class='input_w w_15' name="umTGroup.groupName" id="umTGroup.groupName" value="">
				</td>
				</tr>
				<tr>

		<input type="hidden" name="umTGroup.groupType" id="umTGroup.groupType" value="1">
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询" onclick="executeGroupQuery();"> 
							<input type="button" id="addButton"
								onclick="addGroup();" class="button_ty" value="创建流程角色">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<div class="margin-control">
	<table id="UmTGroupManageTable"></table>
	</div>
<!-- 创建流程角色窗口 -->
<div id="AddGroupWindow" class="easyui-window" title="流程角色" style="width:550px;height:260px" data-options="modal:true,closed:true,minimizable:false,resizable:false">
	<form name="agfm" id="agfm" action="/um/umtgroup" namespace="/um/umtgroup" method="post">
		<table class="fix_table">
			<tr>
		<td class="bgc_tt short">
			角色代码
		</td>
			<td class="long">
				<input class='easyui-validatebox input_w w_20' name="umTGroup.groupCode" id="agumTGroup.groupCode" data-options="required:true" value="">
			</td>
			</tr>
			<tr>
		<td class="bgc_tt short">
			角色名称
		</td>
			<td class="long">
				<input class='easyui-validatebox input_w w_20' name="umTGroup.groupName" id="agumTGroup.groupName" data-options="required:true" value="">
			</td>
					</tr>
					<tr>
		<td class="bgc_tt short">
			有效状态
		</td>
			<td class="long">
				<select id="agumTGroup.validStatus" class="easyui-combobox input_w w_20" name="umTGroup.validStatus" panelHeight="45" data-options="required:true,editable:false">  
				<option value="1">有效</option>  
				<option value="0">无效</option>  
				</select>
			</td>			
									
		</tr>
		<tr>
		<td class="bgc_tt short">
			机构
		</td>
			<td class="long">
				<!-- <input id="ComcodeCTree" name="umTGroupcomCode" class='input_w w_40' value=""> -->
				<input id="ComcodeCBox" name="umTGroupcomCode" class='easyui-combobox input_w w_40' value=""> 
			</td>
			
		</tr>
		<!-- 测试隐藏 -->
		<input type="hidden" name="umTGroup.groupType" id="umTGroup.groupType" value="1">
		<!-- <input type="hidden" name="umTGroup.creatorCode" value="" /> 
		<input type="hidden" name="umTGroup.insertTimeForHis" value="" />		
		<input type="hidden" name="umTGroup.id.groupId" id="umTGroup.id.groupId" value="" />-->
		<tr>	
		</table>

		<table class="fix_table">
			<tr>
				<td colspan="6" align="center">
						<input type="button" class="button_ty" onclick="executeSaveGroup('${comId}');" ind="ind"  value="保存" />
						<input type="button" class="button_ty" ind="ind"  value="重置" onclick="clearAddGroup('${comId}');" />
				</td>
			</tr>
		</table>
	</form>	
</div>

<!-- 修改流程角色窗口 -->
<div id="UpdateGroupWindow" class="easyui-window" title="流程角色" style="width:550px;height:220px" data-options="modal:true,closed:true,minimizable:false,resizable:false">
	<form name="ufm" id="ufm" action="/um/umtgroup" namespace="/um/umtgroup" method="post">
		<table class="fix_table">
			<tr>
		<td class="bgc_tt short">
			角色代码
		</td>
			<td class="long">
				<input class='easyui-validatebox input_w w_20' name="umTGroup.groupCode" id="uumTGroup.groupCode" data-options="required:true" value="">
			</td>
		<td class="bgc_tt short">
			角色名称
		</td>
			<td class="long">
				<input class='easyui-validatebox input_w w_20' name="umTGroup.groupName" id="uumTGroup.groupName" data-options="required:true" value="">
			</td>
					</tr>
					<tr>
		<td class="bgc_tt short">
			有效状态
		</td>
			<td class="long">
				<select id="uumTGroup.validStatus" class="easyui-combobox input_w w_20" name="umTGroup.validStatus" panelHeight="45" data-options="required:true,editable:false">  
				<option value="1">有效</option>  
				<option value="0">无效</option>  
				</select>
			</td>
			<td >
			
			</td>
			<td class="long">						
			</td>
									
		</tr>
		<!-- 测试隐藏 -->
		<input type="hidden" name="umTGroup.groupType" id="uumTGroup.groupType" value="">
		<!-- <input type="hidden" name="umTGroup.creatorCode" value="" /> 
		<input type="hidden" name="umTGroup.insertTimeForHis" value="" />-->		
		<input type="hidden" name="umTGroup.id.groupId" id="uumTGroup.id.groupId" value="" />
		<tr>	
		</table>

		<table class="fix_table">
			<tr>
				<td colspan="6" align="center">
						<input type="button" class="button_ty" onclick="updateSaveGroup('${comId}');" ind="ind"  value="保存"/>
				</td>
			</tr>
		</table>
	</form>	
</div>

<!-- 增加关联角色窗口 -->
<div id="ConnectGroupComWindow" class="easyui-window" title="关联角色" style="width:550px;height:220px" data-options="modal:true,closed:true,minimizable:false,resizable:false">
<form name="cgcf" id="cgcf" action="/um/umtgroup" namespace="/um/umtgroup" method="post">
	<table class="fix_table">
			<tr>
	<td class="bgc_tt short">
			流程角色
		</td>
			<td class="long">
				<input readonly class='input_w w_35 readonly' name="umTGroupCom.groupId" id="cumTGroupCom.groupId" value="">
			</td>
				</tr>
		<tr>
	<td class="bgc_tt short">
			有效状态
		</td>
			<td class="long">
				<select id="cumTGroupCom.validStatus" class="easyui-combobox input_w w_20" name="umTGroupCom.validStatus" panelHeight="45" data-options="required:true,editable:false">  
				<option value="1">有效</option>  
				<option value="0">无效</option>  
				</select> 
			</td>
					</tr>
					<tr>
		<td class="bgc_tt short">
			机构
		</td>
			<td class="long">
				<!-- <input id="cgComcodeCTree" name="umTGroupCom.comCode" class='input_w w_40' value=""> -->
				<input id="cgComcodeCBox" name="umTGroupCom.comCode" class='easyui-combobox input_w w_40' value="">
			</td>
			</tr>
			</table>

		<table class="fix_table">
			<tr>
				<td colspan="6" align="center">
						<input type="button" class="button_ty" onclick="groupComAdd()" ind="ind"  value="关联" >
						<!-- <input type="button" class="button_ty" ind="ind"  value="重置" onclick="$('#cgComcodeCBox').combobox('clear');" > -->
				</td>
			</tr>
		</table>
</form>
</div>

<div id="EditAllGroupComWindow" class="easyui-window" title="流程角色所属机构" style="width:590px;height:190px" data-options="modal:true,closed:true,minimizable:false,resizable:false">
		<table id="AllGroupComTable"></table>
</div>  
</body>
</html>
