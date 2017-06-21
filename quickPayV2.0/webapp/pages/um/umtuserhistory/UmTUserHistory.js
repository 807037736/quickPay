var query_action = contextRootPath + "/user/umtuserhistory/query.do";
var queryResultTable = "UmTUserHistoryTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}, {
	text : '删除',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		executeDelete();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'usercode',
			title : 'usercode',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'username',
			title : 'username',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'comcode',
			title : 'comcode',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'telephone',
			title : 'telephone',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'faxnumber',
			title : 'faxnumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'mobile',
			title : 'mobile',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'email',
			title : 'email',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'postaddress',
			title : 'postaddress',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'unit',
			title : 'unit',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'unitaddress',
			title : 'unitaddress',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'image',
			title : 'image',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'interests',
			title : 'interests',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'creatorcode',
			title : 'creatorcode',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'inserttimeforhis',
			title : 'inserttimeforhis',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'updatercode',
			title : 'updatercode',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operatetimeforhis',
			title : 'operatetimeforhis',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'remark',
			title : 'remark',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'flag',
			title : 'flag',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'usertype',
			title : 'usertype',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'usersort',
			title : 'usersort',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'identitynumber',
			title : 'identitynumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validstatus',
			title : 'validstatus',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'sex',
			title : 'sex',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'birthday',
			title : 'birthday',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'age',
			title : 'age',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'comid',
			title : 'comid',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'bindstatus',
			title : 'bindstatus',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'sourceflag',
			title : 'sourceflag',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'custid',
			title : 'custid',
			align : 'center',
			sortable : true
		}
				
		] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		title : "查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
}
/* 修改 */
function prepareUpdate() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/user/umtuserhistory/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/user/umtuserhistory/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/user/umtuserhistory/view.do?" + params;
	editRecord(url);
}

/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
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
			var url = contextRootPath+"/user/umtuserhistory/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
						   var obj=eval("(" + result + ")");
						  if(obj.msg=='success'){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
						  }else{
							  $.messager.alert('错误信息',obj.msg,'error');
						  }
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

	function executeSave(){
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/user/umtuserhistory/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/user/umtuserhistory/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}