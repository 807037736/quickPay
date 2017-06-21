var query_action = contextRootPath + "/qp/qpttpcase/query.do";
var queryResultTable = "QpTTPCaseTable";
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
			field : 'caseId',
			title : '案件ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.caseId + '</a>';
			}
		},
		{
			field : 'caseSerialNo',
			title : '案件编号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseTime案件时间',
			title : 'CaseTime案件时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseWeather',
			title : '天气情况',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseProvince',
			title : '省份',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseCity',
			title : '城市',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseDistrict',
			title : '区县',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseRoad',
			title : '道路',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseStreet',
			title : '街区',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPLoginId',
			title : '交警ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPUserName',
			title : '交警姓名',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPEmployeeId',
			title : '警员编号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPHandleStatus',
			title : '交警处理结果',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPHandleTime交警处理时间',
			title : 'TPHandleTime交警处理时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPHandleNotes',
			title : '交警处理备注',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseResult',
			title : '调解结果',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseNotes',
			title : '事故详情',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relatedPersons',
			title : 'RelatedPersons',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'policeName',
			title : '警员姓名',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'policeEmployeeId',
			title : '警员编号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerId',
			title : '快处中心ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'spanId',
			title : 'SpanId',
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
	var url = contextRootPath+"/qp/qpttpcase/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpttpcase/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpttpcase/view.do?" + params;
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
			var url = contextRootPath+"/qp/qpttpcase/delete.do?" + getUrlByJson(rows[0]);
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
				 		alert("这里是修改");
				 		$("#fm").attr("action",contextRootPath+"/qp/qpttpcase/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		alert("这里是添加");
				 		$("#fm").attr("action",contextRootPath+"/qp/qpttpcase/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
	
	
	function printPreview() {
		/*$('#PrintPreviewWindow').window('open');*/
		editRecord(contextRootPath
				+ "/qp/qpttpcase/printPreview.do?qpTTPCaseCaseId="
				+ $("#qpTTPCaseCaseId").val()+"&printMethod="+$("#printTemp").val());
	}
	
	