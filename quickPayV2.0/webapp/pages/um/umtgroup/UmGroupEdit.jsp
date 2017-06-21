<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>自定义组</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<!-- <link rel="stylesheet" type="text/css" href="easyui.css" />
<link rel="stylesheet" type="text/css" href="tree.css" /> -->

<script language="javascript" src="${ctx}/pages/um/umtgroup/UmGroupEdit.js"></script>
<script type="text/javascript">
$(function(){
	/**默认情况 选中机构**/
	
	var ComCode="${comCode}"; //根据用户机构生成树
	showCom(ComCode);
	var flag_4s="${flag_4s}"; //是否显示车行
	if(flag_4s=='1'){
		
		document.getElementById("mb").style.display = "none";
		//document.getElementById("mb4s").style.display = "none";
		//$("#ComTabs").tabs();
        //$("#ComTabs").tabs('close','归属车行');

	}
	$("#searcher_tab").hide(); 
	
	$('#ssGroup').searchbox({  
	    searcher:function(value,name){  
	    	$("#ComUserTable").datagrid({url:encodeURI(contextRootPath + "/um/umtusergroup/queryUserPageByComCode.do?userValue="+value+"&flag_ValueType="+name+"&comCode="+$("#ctitle > h3").text().substr(0, 8))});
	    },  
	    menu:'#mmGroup',  
	    prompt:'人员名查询'  
	});
    	
	$('#comTree').panel({
		width:418,
		height:986
	});
	
	$('#comTreePermit').tree({
		onClick:function(node){
			$("#ctitle > h3").text(node.id+"-关联管理");
			$("#searcher_tab").show("slow");

					var send_url = query_action + "?umTGroupCom.comCode=" + node.id;
					$('#'+queryResultTable).datagrid({
						//title : "机构"+node.id,
						url : send_url,
						nowrap : true,
						//fit:true,
						striped : true,
						remoteSort : false,
						pageNumber : 1,
						pagination : true,
						singleSelect: true,
						columns : page_contentColumnHeaders,
						toolbar : page_toolBar
					});
					
					 /* $('#UmTGroupCom4sTable').datagrid({						
						url : contextRootPath + "/um/umtgroupcom/queryGroupComByComCode.do?umTGroupCom.comCode="+ node.id,
						nowrap : true,
						striped : true,
						remoteSort : false,
						pageNumber : 1,
						pagination : true,
						singleSelect: true,
						columns : com4sPage_contentColumnHeaders,
						toolbar : com4sPage_toolBar
					}); */ 
					//if(flag_4s=='1'){
					   $('#ComUserTable').datagrid({
							url : contextRootPath + "/um/umtusergroup/queryUserPageByComCode.do?comCode="+ node.id,
							nowrap : true,
							striped : true,
							remoteSort : false,
							pageNumber : 1,
							pagination : true,
							singleSelect: true,
							columns : userComPage_contentColumnHeaders,
							toolbar : userGroupComPage_toolBar
						});
					 // }else{
						  /* $('#ComUserTable').datagrid({
								url : contextRootPath + "/um/umtusergroup/queryUserPageByComCode.do?comCode="+ node.id,
								nowrap : true,
								striped : true,
								remoteSort : false,
								pageNumber : 1,
								pagination : true,
								singleSelect: true,
								columns : userComPage_contentColumnHeaders,
								toolbar : userComPage_toolBar
							}); */
					 // }

		},
		checkbox:false
	});
	

});
</script>
</head>

<body class="easyui-layout">

<!-- 机构树 -->
<div style="width:420px;" region="west" >
	<a href="#" id="mb" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="javascript:groupManage()">流程角色管理</a>
	<!-- <a href="#" id="mb4s" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="javascript:com4sManage()">车行查询</a> -->
		<div class="easyui-panel" id="comTree" border="false" >	
		<!-- <div class="easyui-tabs" id="comTree" border="false">
		<div  title="机构树" id="comTree" style="overflow:auto;"> -->
				<ul id="comTreePermit" class="easyui-tree" ></ul>		
				<!-- </div> -->		
		</div>
</div>

<div id="center_div" region="center" border="false">
<div id="ctitle" class="right_detail_top"><h3>关联管理</h3></div>
<div class="easyui-tabs" id="ComTabs">

		<div  title="流程角色" id="DefinedGroup" style="overflow:auto;"> 		
		<table id="UmTGroupComTable"></table>
		</div>
		<!-- 车行tab页面
		<div  id="Com4sGroup" title="归属车行" style="overflow:auto;" >
		<table id="UmTGroupCom4sTable"></table>
		</div> -->
		
		<div title="机构人员" id="ComUser" style="overflow:auto;">
		<!-- <input id="ssGroup" style="width:100px;text-align:center"></input>  -->
		<table id="searcher_tab" class="fix_table" >
		<tr>
		<td class="bgc_tt short">
		人员查询
		</td>
		<td class="long">
		<input id="ssGroup" class='input_w w_15' style="height:28px;width:220px;text-align:center" value="">
		<div id="mmGroup">  
		    <div data-options="name:'1'">中文名</div>  
		    <div data-options="name:'2'">人员代码</div>  
		</div>
		</td>
		</tr>
		</table>
		<table id="ComUserTable"></table>
		</div>
</div>
</div>

<!-- 角色人员管理列表窗口 -->
<div id="EditUserGroupWindow" class="easyui-window" title="流程角色人员" style="width:590px;height:390px" data-options="modal:true,closed:true,minimizable:false,resizable:false,top:'100px'">
		<table id="UmTUserGroupTable"></table>
</div>  

<!-- 人员关联角色窗口 -->
<div id="ConnectUserGroupWindow" class="easyui-window" title="关联角色" style="width:560px;height:240px" data-options="modal:true,closed:true,minimizable:false,resizable:false,top:'100px',onClose:function(){$('#UserGroupIdCBox').combobox('clear');}">
<form name="cugf" id="cugf" action="/um/umtusergroup" namespace="/um/umtusergroup" method="post">
	<table class="fix_table">
			<tr>
	<td class="bgc_tt short">
			人员代码
		</td>
			<td class="long">
				<input readonly class='input_w w_15 readonly' name="umTUserGroup.userCode" id="cumTUserGroup.userCode" value="">
			</td>
			</tr>
		<tr>
	<td class="bgc_tt short">
			有效状态
		</td>
			<td class="long">
				<select id="cumTUserGroup.validStatus" class="easyui-combobox input_w w_15" name="umTUserGroup.validStatus" panelHeight="45" data-options="required:true,editable:false">  
				<option value="1">有效</option>  
				<option value="0">无效</option>  
				</select> 
			</td>
					</tr>
					<tr>
		<td class="bgc_tt short">
			流程角色
		</td>
			<td class="long">
				<input id="UserGroupIdCBox" name="umTUserGroup.groupId" value=""> 
			</td>
			</tr>
			</table>

		<table class="fix_table">
			<tr>
				<td colspan="6" style="text-align:center">
						<input align="center" type="button" class="button_ty" onclick="userGroupComSave()" ind="ind"  value="关联" />
						<input type="button" class="button_ty" ind="ind"  value="重置" onclick="$('#UserGroupIdCBox').combobox('clear');$('#cumTUserGroup\\.validStatus').combobox('select','1');"/>
				</td>
			</tr>
		</table>
</form>
</div>

<!-- 关联人员窗口 -->
<div id="ConnectUserWindow" class="easyui-window" title="关联人员" style="width:560px;height:250px" data-options="modal:true,closed:true,minimizable:false,resizable:false,top:'100px',onClose:function(){$('#UserGroupCBox').combobox('clear');}">
<form name="cuf" id="cuf" action="/um/umtusergroup" namespace="/um/umtusergroup" method="post">
	<table class="fix_table">
			<tr>
	<td class="bgc_tt short">
			流程角色ID
		</td>
			<td class="long">
				<input readonly class="input_w w_35 readonly" name="umTUserGroup.groupId" id="umTUserGroup.groupId" value="">
			</td>
					</tr>
		<tr>
	<td class="bgc_tt short">
			流程角色
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
				<!-- <select id="GroupCBox" class="easyui-combobox" name="umTGroupCom.groupId" style="width:300px;" panelHeight="180">  
				</select>  -->
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
</div>		
		
</body>
<script type="text/javascript">
</script>
</html>
