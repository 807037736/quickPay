/***************************************************************************/

var queryGroup_action = contextRootPath + "/um/umtgroup/query.do";
var queryResultTable = "UmTGroupManageTable";
var pageGroup_toolBar = [{
	iconCls:'icon-edit',
	text : '编辑流程角色',
	handler:function(){
		prepareUpdateGroup();		
	}
},'-',
{
	text : '编辑流程角色所属机构',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		groupComManage();
	}
} 
];
var pageGroup_contentColumnHeaders = [ [
                                		{
                                			field : 'checkBoxNo',
                                			checkbox : true
                                		},
                                		{
                                			field : 'groupId',
                                			title : '用户自定义组ID',
                                			align : 'center',
                                			hidden: true,
                                			sortable : true,
                                			formatter : function(value, row, index) {
                                				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
                                						+ getUrlByJson(row) + '\')">' + row.id.groupId + '</a>';
                                			}
                                		},
                                		{
                                			field : 'groupType',
                                			title : '自定义角色类型',
                                			align : 'center',
                                			sortable : true,
                                			formatter : function(value, row, index) {
                                				if(value=='1'){
                                					return '流程角色';
                                				}else if(value=='2'){
                                					return '车行';
                                				}
                                			}
                                		}
                                			,	
                                		{
                                			field : 'groupCode',
                                			title : '流程角色代码',
                                			align : 'center',
                                			sortable : true
                                		}
                                			,	
                                		{
                                			field : 'groupName',
                                			title : '流程角色名称',
                                			align : 'center',
                                			sortable : true
                                		}
                                			,	
                                		{
                                			field : 'creatorCode',
                                			title : '创建人代码',
                                			align : 'center',
                                			sortable : true
                                		}
                                			,	
                                		{
                                			field : 'insertTimeForHis',
                                			title : '创建时间',
                                			align : 'center',
                                			sortable : true
                                		}
                                			,	
                                		{
                                			field : 'updaterCode',
                                			title : '修改人代码',
                                			align : 'center',
                                			sortable : true
                                		}
                                		,	
                                		{
                                			field : 'operateTimeForHis',
                                			title : '修改时间',
                                			align : 'center',
                                			sortable : true
                                		}
                                		,	
                                		{
                                			field : 'validStatus',
                                			title : '状态',
                                			align : 'center',
                                			sortable : false,
                                			formatter : function(value, row, index) {
                                				if(value=='1'){
                                					return 'Y';
                                				}else if(value=='0'){
                                					return 'N';
                                				}
                                			}
                                		}
                                				
                                		] ];
/*角色所属机构列表*/
var pageGroupCom_toolBar = [ {
	text : '查询关联流程角色机构',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		connectGroupCom();
	}
},'-',  
{
	text : '注销关联',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		connectGroupDelete();
	}
}];

var pageGroupCom_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'groupComId',
			title : '组机构ID',
			align : 'center',
			hidden: true,
			sortable : true
		}
		,	
		{
			field : 'groupId',
			title : '角色ID',
			align : 'center',
			sortable : true
		},			
			{
				field : 'comCode',
				title : '机构代码',
				align : 'center',
				sortable : true
			},
			{
				field : 'creatorCode',
				title : '创建人代码',
				align : 'center',
				sortable : true
			}
				,	
			{
				field : 'insertTimeForHis',
				title : '创建时间',
				align : 'center',
				sortable : true
			}
				,	
			{
				field : 'updaterCode',
				title : '修改人代码',
				align : 'center',
				hidden: true,
				sortable : true
			}
				,	
			{
				field : 'updateTimeForHis',
				title : '修改时间',
				align : 'center',
				hidden: true,
				sortable : true
			}
				,

		{
			field : 'validStatus',
			title : '有效状态',
			align : 'center',
			sortable : false,
			formatter : function(value, row, index) {
				if(value=='1'){
					return 'Y';
				}else if(value=='0'){
					return 'N';
				}
			}
		}
				
		] ];
/*查询所有流程角色所属关系*/
function groupComManage(){
	var rows = $('#UmTGroupManageTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else{
		$('#EditAllGroupComWindow').window('open');
		$('#AllGroupComTable').datagrid({
			//title : "角色",
			url : contextRootPath + "/um/umtgroupcom/queryByGroupId.do?umTGroupCom.groupId="+rows[0].id.groupId,
			nowrap : true,
			striped : true,
			remoteSort : false,
			fit : true,
			pageNumber : 1,
			pagination : true,
			singleSelect: true,
			columns : pageGroupCom_contentColumnHeaders,
			toolbar : pageGroupCom_toolBar
		});
	}
	
}

/* 查询 */
function executeGroupQuery() {
	var data = $("#fmGroup").serialize();
	var send_url = queryGroup_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		//title : "查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		singleSelect: true,
		columns : pageGroup_contentColumnHeaders,
		toolbar : pageGroup_toolBar
	});
}

/* 更改流程角色 */
function prepareUpdateGroup() {
	var rows = $('#UmTGroupManageTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else{

		if(rows[0].groupType=='1'){
		$('#UpdateGroupWindow').window('open');
		$('#UpdateGroupWindow').window('setTitle','修改自定义流程角色');
		//$('#operateType').val("updategroup");
		$('#uumTGroup\\.groupCode').val(rows[0].groupCode);
		$('#uumTGroup\\.groupName').val(rows[0].groupName);
		$('#uumTGroup\\.groupType').val(rows[0].groupType);
		$('#uumTGroup\\.validStatus').val(rows[0].validStatus);
		//$('#umTGroup\\.insertTimeForHis').val(rows[0].insertTimeForHis);
		$('#uumTGroup\\.id\\.groupId').val(rows[0].id.groupId);
		}else{
			$.messager.alert('提示信息','不能修改车行角色','info');
		}
		
		
	}

}

//更新流程角色
function updateSaveGroup(comId){
	if(!$('#ufm').form('validate')){
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
			if (r){
			 		//$("#fm").attr("action",contextRootPath+"/um/umtgroup/update.do");
			 		//$("#fm").submit();
			 		$('#ufm').form('submit', {  
			 			    url:contextRootPath+"/um/umtgroup/update.do",  
			 			    onSubmit: function(){  
			 			        // do some check  
			 			        // return false to prevent submit; 
			 			    	$('#UpdateGroupWindow').window('close');
			 			    },  
			 			    success:function(data){
			 			    	//console.info(Msg);
			 			    	var data = eval('(' + data + ')');
			 			    	if(data.msg=='success'){
			 			    		$.messager.alert('提示','更新成功');
			 			    		$("#UmTGroupManageTable").datagrid('clearSelections');
			 			    		$("#UmTGroupManageTable").datagrid('reload');
			 			    	}else{
			 			    		$.messager.alert('提示',data.msg,'info');
			 			    	}			 			    	
 
			 			    }  
			 			});
			 		$('#uumTGroup\\.groupCode').val(comId+"_");
					$('#uumTGroup\\.groupName').val("");

			}else{
			 		return false;			 					
			}
		});
    }	    
}
/* 新增流程角色 */
function addGroup() {

	$('#AddGroupWindow').window('open');
	$('#AddGroupWindow').window('setTitle','添加流程角色');	
	/*$('#ComcodeCTree').combotree({
		required:true,
		editable: false,
		onSelect: function(node){  
		},
		url: contextRootPath+"/um/umtgroup/queryComTree.do?queryType=0&treeType=0&nodeId="+comId

	});
	$('#ComcodeCTree').combotree('tree').tree({
		onExpand:function(node){
			$('#ComcodeCTree').combotree('tree').tree('reload');
		}
	});*/
}
/*流程角色增加关联机构*/
function connectGroupCom(){
	
	if($('#UmTGroupManageTable').datagrid('getSelections')[0].groupType=='1'){
		$('#ConnectGroupComWindow').window('open');
		$('#cumTGroupCom\\.groupId').val($('#UmTGroupManageTable').datagrid('getSelections')[0].id.groupId);
		
	}else{
		$.messager.alert('提示信息','不能修改车行角色','info');
	}
}


/* 删除角色关联 */
function connectGroupDelete() {
	if($('#UmTGroupManageTable').datagrid('getSelections')[0].groupType=='1'){
	var rows = $("#AllGroupComTable").datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/um/umtgroupcom/delete.do?id.groupComId="+rows[0].id.groupComId; //getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){						   
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#AllGroupComTable').datagrid('reload');			  
					   },
					   error:function(result){
						   alertErrorMsgForEasyUi(result);
						}
			});
		}
	});
	}else{
		$.messager.alert('提示信息','不能修改车行角色','info');
	}
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}

/*角色保存增加的机构*/
function groupComAdd(){
	if(!$('#cgcf').form('validate')){
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
			if (r){
			 		
			 		$('#cgcf').form('submit', {  
			 			    url:contextRootPath+"/um/umtgroupcom/add.do",  
			 			    onSubmit: function(){  
			 			    	$('#ConnectGroupComWindow').window('close');
			 			    },  
			 			    success:function(data){
			 			    		//console.info(Msg);			 			    	
			 			    		$.messager.alert('提示信息','关联成功');
			 			    		$("#AllGroupComTable").datagrid('reload');
			 			    },
							error:function(result){
								   alertErrorMsgForEasyUi(result);

							}  
			 			});
				
			}
		});
    }
}

//创建保存流程角色
function executeSaveGroup(comId){
	if(!$('#agfm').form('validate')){
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
    		if (r){
    			$('#agfm').form('submit', {  
	 			    url:contextRootPath+"/um/umtgroup/add.do",  
	 			    onSubmit: function(){  
	 			        // do some check  
	 			        // return false to prevent submit; 		 			    	
	 			    	$('#AddGroupWindow').window('close');
	 			    },  
	 			    success:function(data){
	 			    	var data = eval('(' + data + ')');
	 			    	if(data.msg=='success'){
	 			    		//$.messager.alert('提示信息',data.groupid,'info');
	 			    		//console.info($('#ComcodeCTree').combotree('tree').tree('getSelected').id);
	 			    		var url = contextRootPath+"/um/umtgroupcom/add.do?umTGroupCom.groupId="+data.groupid+"&umTGroupCom.comCode="+comId;
	 						 $.ajax({
	 								   type: "POST",
	 								   url: url,
	 								   success: function(result){						   
	 										  $.messager.alert('提示信息','成功！','info');		  
	 								   },
	 								   error:function(result){
	 									   alertErrorMsgForEasyUi(result);
	 									}
	 						});
	 			    		$("#UmTGroupManageTable").datagrid('reload');

	 			    	}else{
	 			    		$.messager.alert('提示信息','错误','info');
	 			    	}			 			    	

	 			    }  
	 			}); 
		 		$('#agumTGroup\\.groupCode').val(comId+"_");
				$('#agumTGroup\\.groupName').val("");    			
    		}else{
    			return false;
    		}
    		
    	});
    }
	
}

function clearAddGroup(comId){
	$('#agumTGroup\\.groupCode').attr("value",comId+"_");
	$('#agumTGroup\\.groupName').attr("value","");
	$('#agumTGroup\\.validStatus').combobox('select','1');
	//$('#ComcodeCBox').combobox('clear');
}