/**

 * 生成角色表格
 */
var query_action = contextRootPath + "/um/umtgroupcom/query.do";
var queryResultTable = "UmTGroupComTable";
var page_toolBar = [ 
{
	text : '关联人员',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		connectUser();
	}
} , '-',
{
	text : '管理已有人员',
	align : 'right',
	iconCls : 'icon-save',
	handler : function() {
		userGroupManage();
	}
} 
//,  
//{
//	text : '注销关联',
//	align : 'right',
//	iconCls : 'icon-remove',
//	handler : function() {
//		connectGroupDelete();
//	}
//}
];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
//		{
//			field : 'groupComId',
//			title : 'ID',
//			align : 'center',
//			sortable : true,
//			formatter : function(value, row, index) {
//				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
//						+ getUrlByJson(row) + '\')">' + row.id.groupComId + '</a>';
//			}
//		},
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
			hidden: true,
			sortable : true
		}
			,	
		{
			field : 'groupName',
			title : '流程角色',
			width: 110,
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'groupCode',
			title : '角色代码',
			align : 'center',
			width:120,
			sortable : true
		}
			,		
			{
				field : 'comCode',
				title : '机构代码',
				align : 'center',
				width:80,
				sortable : true
			},
			{
				field : 'creatorCode',
				title : '创建人代码',
				align : 'center',
				width:80,
				sortable : true
			}
				,	
			{
				field : 'insertTimeForHis',
				title : '创建时间',
				align : 'center',
				width:120,
				sortable : true
			}
				,	
			{
				field : 'updaterCode',
				title : '修改人代码',
				align : 'center',
				hidden: true,
				width:90,
				sortable : true
			}
				,	
			{
				field : 'updateTimeForHis',
				title : '修改时间',
				align : 'center',
				hidden: true,
				width:120,
				sortable : true
			}
				,

		{
			field : 'validStatus',
			title : '状态',
			align : 'center',
			width:50,
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
/*车行列*/
var com4sPage_toolBar = [ {
	text : '关联人员',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		com4sConnectUser();
	}
} , '-',
{
	text : '管理已有人员',
	align : 'right',
	iconCls : 'icon-save',
	handler : function() {
		userGroupCom4sManage();
	}
}];

var com4sPage_contentColumnHeaders = [ [
	{
		field : 'checkBoxNo',
		checkbox : true
	},
//              {
//                field : 'groupComId',
//                title : 'ID',
//                align : 'center',
//                sortable : true,
//                formatter : function(value, row, index) {
//                           	return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
//                           	+ getUrlByJson(row) + '\')">' + row.id.groupComId + '</a>';
//                           }
//               },
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
		hidden: true,
		sortable : true
	}
		,	
	{
		field : 'groupName',
		title : '车行',
		align : 'center',
		width:200,
		sortable : true
	}
		,	
	{
		field : 'groupCode',
		title : '车行代码',
		align : 'center',
		width:100,
		sortable : true
	}
	,		
	{
		field : 'comCode',
		title : '机构代码',
		align : 'center',
		width:80,
		sortable : true
	},
	{
		field : 'creatorCode',
		title : '创建人代码',
		align : 'center',
		width:80,
		sortable : true
	}
		,	
	{
		field : 'insertTimeForHis',
		title : '创建时间',
		align : 'center',
		width:120,
		sortable : true
	}
		,	
	{
		field : 'updaterCode',
		title : '修改人代码',
		align : 'center',
		width:90,
		hidden: true,
		sortable : true
	}
		,	
	{
		field : 'updateTimeForHis',
		title : '修改时间',
		align : 'center',
		hidden: true,
		width:120,
		sortable : true
	}
		,

	{
		field : 'validStatus',
		title : '状态',
		align : 'center',
		width:50,
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

/*流程角色人员列表*/
var userPage_toolBar = [{
	text : '注销关联',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		cancelUserGroup();
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
			title : '角色名称',
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

/*机构所属人员列表
var userComPage_toolBar = [{
	text : '关联流程角色',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		connectUserGroup('流程');
	}
},{
	text : '关联车行',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		connectUserGroup('车行');
	}
}];
*/
var userGroupComPage_toolBar = [{
	text : '关联流程角色',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		connectUserGroup('流程');
	}
}];

var userComPage_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'userCode',
			title : '人员代码',
			align : 'center',
			width:80,
			sortable : true,
			formatter : function(value, row, index) {
				return  row.id.userCode;
			}
		}
		,	
		{
			field : 'userName',
			title : '姓名',
			align : 'center',
			width:80,
			sortable : true
		},			
		{
			field : 'comCode',
			title : '所属机构',
			align : 'center',
			width: 80,
			sortable : true
		},
		{
			field : 'identityNumber',
			title : '身份证',
			align : 'center',
			width: 130,
			sortable : true
		}
			,		
			{
				field : 'validStatus',
				title : '状态',
				align : 'center',
				width: 40,
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

/*流程角色表格*/
var GroupTable = "UmTGroupTable";
var groupPage_toolBar = [{
	iconCls:'icon-add',
	text : '创建流程角色',
	handler:function(){
		addGroup();
	}
},
{
	text : '编辑流程角色',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdateGroup();
	}
} 
];
var GroupPage_contentColumnHeaders = [ [
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

/*管理人员流程角色*/
function userGroupManage(){
	var rows = $('#UmTGroupComTable').datagrid('getSelections');
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
			//title : "角色",
			//url : contextRootPath + "/um/umtusergroup/queryByGroupIdAndCom.do?groupId="+rows[0].groupId+"&comCode="+$('#UmTGroupComTable').datagrid('options').title.substr(2, 10),
			url : contextRootPath + "/um/umtusergroup/queryByGroupIdAndCom.do?groupId="+rows[0].groupId+"&comCode="+$("#ctitle > h3").text().substr(0, 8),
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

function userGroupCom4sManage(){
	var rows = $('#UmTGroupCom4sTable').datagrid('getSelections');
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
			//title : "角色",
			//url : contextRootPath + "/um/umtusergroup/queryByGroupIdAndCom.do?groupId="+rows[0].groupId+"&comCode="+$('#UmTGroupComTable').datagrid('options').title.substr(2, 10),
			url : contextRootPath + "/um/umtusergroup/queryByGroupIdAndCom.do?groupId="+rows[0].groupId+"&comCode="+$("#ctitle > h3").text().substr(0, 8),
			nowrap : true,
			striped : true,
			remoteSort : false,
			pageNumber : 1,
			pagination : true,
			columns : userPage_contentColumnHeaders,
			toolbar : userPage_toolBar
		});
	}
}

//关联人员窗口
function connectUser(){
	
	var rows = $('#UmTGroupComTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else {			
		$('#ConnectUserWindow').window('open');
		$('#umTUserGroup\\.groupId').val(rows[0].groupId);
		$('#groupName').val(rows[0].groupName);
		//$('#umTUserGroup\\.validStatus').val(rows[0].validStatus);
		/*$('#ComcodeCTree').combotree({
			required:true,
			editable: false,
			onSelect: function(node){  
				$('#UserGroupCBox').combobox({ 
					required:true,
					multiple:true,
				    panelHeight:'auto',
				    url:contextRootPath + "/um/umtusergroup/queryUserByCom.do?comCode="+node.id,  
				    valueField: 'userCode',				    
				    textField:'userName'
				});
					},
			url: contextRootPath+"/um/umtgroup/queryComTree.do?comCode=44030000&queryType=0&comCodes=44030000",
	        onBeforeExpand:function(node){
	        	 $('#ComcodeCTree').combotree("tree").tree('options').url = contextRootPath+"/um/umtgroup/queryComTree.do?comCode="+node.id+"&queryType=1&comCodes=44030000";
	         }
		});*/
		//alert($("#ctitle > h3").text().substr(0, 8));
		$('#UserGroupCBox').combobox({ 
			required:true,
			multiple:true,
		    panelHeight:'200',
		    //url:contextRootPath + "/um/umtusergroup/queryUserByCom.do?comCode="+$('#UmTGroupComTable').datagrid('options').title.substr(2, 10),  
		    url:contextRootPath + "/um/umtusergroup/queryUserByCom.do?comCode="+$("#ctitle > h3").text().substr(0, 8),  
		    valueField: 'userCode',				    
		    textField:'userName'
		});
		
	}
	
}

//车行关联人员窗口
function com4sConnectUser(){
	
	var rows = $('#UmTGroupCom4sTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else {
		//console.info(rows[0].id.groupId);				
		$('#ConnectUserWindow').window('open');
		$('#umTUserGroup\\.groupId').val(rows[0].groupId);
		$('#groupName').val(rows[0].groupName);
		$('#UserGroupCBox').combobox({ 
			required:true,
			multiple:true,
		    panelHeight:'200',
		    method:'post',
		    //url:contextRootPath + "/um/umtusergroup/queryUserByCom.do?comCode="+$('#UmTGroupComTable').datagrid('options').title.substr(2, 10), 
		    url:contextRootPath + "/um/umtusergroup/queryUserByCom.do?comCode="+$("#ctitle > h3").text().substr(0, 8),
		    valueField: 'userCode',				    
		    textField:'userName'
		});
		
	}
	
}

//人员关联角色窗口
function connectUserGroup(group_type){
	var rows = $('#ComUserTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}else {	
	$('#ConnectUserGroupWindow').window('open');
	$('#cumTUserGroup\\.userCode').val(rows[0].id.userCode);
	if(group_type=='流程'){
		$('#UserGroupIdCBox').combobox({  
		    //url:contextRootPath + "/um/umtgroupcom/queryGroupAnd4sCombo.do?comCode="+$('#UmTGroupComTable').datagrid('options').title.substr(2, 10), 
		    url:contextRootPath + "/um/umtgroupcom/queryGroupAnd4sCombo.do?comCode="+$("#ctitle > h3").text().substr(0, 8), 
		    multiple:true,
		    panelHeight:'180',
		    width:225,
		    required:true,
		    valueField:'groupId',  
		    textField:'groupName'  
		});
	}
	if(group_type=='车行'){
		$('#UserGroupIdCBox').combobox({   
		    url:contextRootPath + "/um/umtgroupcom/queryCom4sCombo.do?comCode="+$("#ctitle > h3").text().substr(0, 8), 
		    multiple:true,
		    panelHeight:'180',
		    width:225,
		    required:true,
		    valueField:'groupId',  
		    textField:'groupName'  
		});
	}
	}
}

/*新页面带权限管理流程角色*/
function groupManage(){
	editRecord(contextRootPath+'/um/umtgroup/prepareManage.do');
}

function com4sManage(){
	editRecord(contextRootPath+'/um/umtgroup/prepare4sManage.do');
}

//人员关联有权限的流程角色
function userGroupComSave(){
	if(!$('#cugf').form('validate')){		
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
    		if (r){
		 		
		 		$('#cugf').form('submit', {  
		 			    url:contextRootPath+"/um/umtusergroup/addUserGroupIdList.do",  
		 			    onSubmit: function(){  
		 			    	
		 			    },  	
		 			    success:function(data){
		 			    	var data = eval('(' + data + ')');
		 			    	if(data.msg=='success'){
		 			    		$.messager.alert('提示信息','关联成功');
		 			    		$('#UserGroupIdCBox').combobox('clear');
		 			    	}
		 			    	$('#ConnectUserGroupWindow').window('close');
		 			    },
						error:function(result){
							   alertErrorMsgForEasyUi(result);
							   $('#UserGroupIdCBox').combobox('clear');
							   $('#ConnectUserGroupWindow').window('close');
						}   
		 			});
			
		}    		
    	});
    }
}

//关联人员保存
function userGroupSave(){

	if(!$('#cuf').form('validate')){		
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
    		if (r){
		 		
		 		$('#cuf').form('submit', {  
		 			    url:contextRootPath+"/um/umtusergroup/addUserGroupList.do",  
		 			    onSubmit: function(){  
		 			    	
		 			    },  	
		 			    success:function(data){
		 			    	var data = eval('(' + data + ')');
		 			    	if(data.msg=='success'){
		 			    		$.messager.alert('提示信息','关联成功');
		 			    		//$("#cuf").form('clear');
		 			    		$('#UserGroupCBox').combobox('clear');
		 			    		$('#ConnectUserWindow').window('close');
		 			    	}	 			    		
		 			    },
						error:function(result){
							   alertErrorMsgForEasyUi(result);
							   $('#UserGroupCBox').combobox('clear');
						}   
		 			});
			
		}    		
    	});
    }
	
}

//机构关联角色
/*function groupComSave(){
	if(!$('#cgf').form('validate')){
    	return false; 
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
			if (r){
			 		
			 		$('#cgf').form('submit', {  
			 			    url:contextRootPath+"/um/umtgroupcom/addGroupComList.do",  
			 			    onSubmit: function(){  
			 			        // do some check  
			 			        // return false to prevent submit; 
			 			    	$('#ConnectGroupWindow').window('close');
			 			    },  
			 			    success:function(data){
			 			    		//console.info(Msg);
			 			    		//var data = eval('(' + data + ')');			 			    	
			 			    		$.messager.alert('提示信息','关联成功');
			 			    		$("#UmTGroupComTable").datagrid('reload');
			 			    },
							error:function(result){
								   alertErrorMsgForEasyUi(result);

							}  
			 			});
				
			}
		});
    }	    
}*/


/*人员角色*/
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
		//var url = contextRootPath+"/um/umtusergroup/delete.do?id.userGroupId="+rows.userGroupId;
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

/** 展现机构树 **/
function showCom(cc){
	var flag = true;
	
	/*if(!$("#userTreeDiv").is(":hidden")){
		$("#userTreeDiv").hide();
	}*/
	if(flag==true){
		$("#comTree").css("display","block");
		/**展现允许机构**/
		//console.info(cc);
		$('#comTreePermit').tree({
	    	animate:true,
	    	checkbox:true,
	    	url: contextRootPath+"/um/umtgroup/queryComTree.do?queryType=0&treeType=0",
	         onBeforeExpand:function(node,param){  
	        	 $('#comTreePermit').tree('options').url = contextRootPath+"/um/umtgroup/queryComTree.do?nodeId="+node.id+"&queryType=1&treeType=0";
	         }
	    });
		
		flag = false;
	}else {
		$("#comTree").css("display","block");
	}
}
