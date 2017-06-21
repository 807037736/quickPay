/***************************************************************************/
var queryGroup_action = contextRootPath + "/um/umtgroup/queryGroupCom4s.do";
var queryResultTable = "Com4sManageTable";

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
                                			sortable : true
                                		},	
                                		{
                                			field : 'groupCode',
                                			title : '车行代码',
                                			align : 'center',
                                			sortable : true
                                		}
                                			,	
                                		{
                                			field : 'groupName',
                                			title : '车行名称',
                                			align : 'center',
                                			sortable : true
                                		}
                                		,	
                                    	{
                                    			field : 'comCode',
                                    			title : '所属机构',
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
                                			field : 'updateTimeForHis',
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
var com4sPage_toolBar = [
{
	text : '车行已有人员',
	align : 'right',
	iconCls : 'icon-save',
	handler : function() {
		userGroupCom4sManage();
	}
}];

/*流程角色人员列表*/
var userPage_toolBar = [{
	text : '注销关联',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		cancelUserGroup();
	}
}, '-',
{
	text : '设置团队经理',
	align : 'right',
	iconCls : 'icon-save',
	handler : function() {
		setLeader();
	}
}];
var userPage_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'userGroupId',
			title : 'ID',
			align : 'center',
			hidden: true,
			sortable : true
		}
		,	
		{
			field : 'groupId',
			title : '角色ID',
			align : 'center',
			hidden: true,
			sortable : true
		},			
		{
			field : 'groupName',
			title : '车行',
			align : 'center',
			sortable : true
		},
		{
			field : 'userCode',
			title : '人员代码',
			align : 'center',
			sortable : true
		},
		{
			field : 'userName',
			title : '姓名',
			align : 'center',
			sortable : true
		},
		{
			field : 'comCode',
			title : '所属机构',
			align : 'center',
			sortable : true
		},
		{
			field : 'isleader',
			title : '团队经理',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='1'){
					return 'Y';
				}else{
					return 'N';
				}
			}
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

/* 查询 */
function executeCom4sQuery() {
	var data = $("#fmCom4s").serialize();
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
		toolbar : com4sPage_toolBar
	});
}

function userGroupCom4sManage(){
	var rows = $('#Com4sManageTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else{
		$('#EditUserGroupWindow').window('open');
		$('#UmTUserGroupTable').datagrid({
			url : contextRootPath + "/um/umtusergroup/query4sByGroupIdAndComCode.do?groupId="+rows[0].groupId,
			nowrap : true,
			striped : true,
			fit : true,
			remoteSort : false,
			pageNumber : 1,
			pagination : true,
			columns : userPage_contentColumnHeaders,
			toolbar : userPage_toolBar
		});
	}
}

/*设置团队经理*/
function setLeader(){
	var rows = $('#UmTUserGroupTable').datagrid('getSelections');
	var leader_message;
	var isLeader_value;
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else{
		if(rows[0].isleader == '1')
		{
			leader_message = "取消该团队经理";
			isLeader_value = "0";
		}else{
			leader_message = "设置为团队经理";
			isLeader_value = "1";
		}
		$.messager.confirm('提示', leader_message, function(r){
			if (r){
				var url = contextRootPath+"/um/umtusergroup/updateLeader.do?isLeader="+isLeader_value+"&id.userGroupId="+rows[0].userGroupId;
				 $.ajax({
						   type: "POST",
						   url: url,
						   success: function(result){
								  $.messager.alert('提示信息','修改成功！','info');
								  $('#UmTUserGroupTable').datagrid('reload');						  
						   },
						   error:function(result){
							   alertErrorMsgForEasyUi(result);
							   }
				});
			}
		});
	}
	
}

function cancelUserGroup() {
	var rows = $('#UmTUserGroupTable').datagrid('getSelections');
	if (rows == null||rows.length == 0) {
		$.messager.alert('提示', '您没有选择!', 'info'); 
		return;
	}
	var num = rows.length;
	$.each(rows, function (i, n) { 
		if (i == 0) { 
		 parm = "id.userGroupId=" + n.userGroupId; 
		 } else { 
		 parm += "&id.userGroupId=" + n.userGroupId; 
		 } 
		 }); 

	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/um/umtusergroup/delete.do?"+parm;
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#UmTUserGroupTable').datagrid('reload');						  
					   },
					   error:function(result){
						   alertErrorMsgForEasyUi(result);
						   }
			});
		}
	});
	
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}
