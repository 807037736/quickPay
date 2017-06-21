var query_action = contextRootPath + "/qp/qpticdocument/query.do";
var queryResultTable = "QpTICDocumentTable";
var page_toolBar = [ /*{
	text : '发放',
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
}*/ ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'batchNo',
			title : '发放批次号',
			align : 'center',
			sortable : true
		},
		{
			field : 'grantor',
			title : '发放人',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerName',
			title : '快出中心id',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'grantTime',
			title : '发放时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'grantCount',
			title : '发放数量',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'startNo',
			title : '单证起始号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'endNo',
			title : '单证结束号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStatus',
			title : '有效状态',
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
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
}
/* 单证发放 */
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
	var url = contextRootPath+"/qp/qpticdocument/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpticdocument/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpticdocument/view.do?" + params;
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
			var url = contextRootPath+"/qp/qpticdocument/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
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

	function executeSave(){
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticdocument/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticdocument/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}