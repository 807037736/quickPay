var query_action = contextRootPath + "/qp/qpticdocumentdetail/query.do";
var queryResultTable = "QpTICDocumentDetailTable";
var page_toolBar = [ /*{
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
} */];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'documentNo',
			title : '单证号',
			align : 'center',
			sortable : true
		},
		{
			field : 'batchNo',
			title : '所属批次号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerName',
			title : '快处中心',
			align : 'center',
			sortable : true
			
		}
			,	
		{
			field : 'usedTime',
			title : '使用时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'usedPerson',
			title : '使用人',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cancelTime',
			title : '销毁时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cancelPerson',
			title : '销毁人',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'documentFlag',
			title : '单证状态',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=="0"){
					return "初始";
				}
				if(value=="1"){
					return "已占用";
				}
				if(value=="2"){
					return "已使用";
				}
			}
		}
			,	
		{
			field : 'validStatus',
			title : '数据有效标志',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=="0"){
					return "无效";
				}
				if(value=="1"){
					return "有效";
				}
			}
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
	var url = contextRootPath+"/qp/qpticdocumentdetail/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpticdocumentdetail/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpticdocumentdetail/view.do?" + params;
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
			var url = contextRootPath+"/qp/qpticdocumentdetail/delete.do?" + getUrlByJson(rows[0]);
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
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticdocumentdetail/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticdocumentdetail/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}