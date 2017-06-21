var query_action = contextRootPath + "/dms/queryAllType.do";
var queryResultTable = "TypeResultTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'codeType',
			title : '代码类型',
			align : 'center',
			sortable : true
		},
		{
			field : 'codeTypeDesc',
			title : '类型描述',
			align : 'center',
			sortable : true
		}
			,	
		/*{
			field : 'newCodeType',
			title : '新代码类型',
			align : 'center',
			sortable : true
		}
				,	*/
						{
							field : 'validDate',
							title : '有效时间',
							align : 'center',
							sortable : true
						}
							,	
						{
							field : 'invalidDate',
							title : '过期时间',
							align : 'center',
							sortable : true
						}
							,	
		{
			field : 'validStatus',
			title : '是否有效',
			align : 'center',
			sortable : true
		}
		
			/*,	
		{
				field : 'dataSource',
				title : '数据来源',
				align : 'center',
				sortable : true
		}
			,
		{
			field : 'flag',
			title : '标志位',
			align : 'center',
			sortable : true
		}*/
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
	if(rows[0]["dataSource"] == 'dms3g:prpdtype'){
		$.messager.alert('提示消息', "不允许修改", 'info');
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/dms/prepareUpdateType.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/dms/prepareAddType.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/dms/view.do?" + params;
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
	if(rows[0]["dataSource"] == 'dms3g:prpdtype'){
		$.messager.alert('提示消息', "不允许删除", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/dms/deleteType.do?" + getUrlByJson(rows[0]);
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
				 	if($('#opreateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/dms/updateType.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/dms/addType.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}