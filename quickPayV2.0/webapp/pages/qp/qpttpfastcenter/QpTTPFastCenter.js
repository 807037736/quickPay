var query_action = contextRootPath + "/qp/qpttpfastcenter/query.do";
var queryResultTable = "QpTTPFastCenterTable";
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
			field : 'centerId',
			title : '中心ID',
			align : 'center',
			sortable : true/*,
			formatter : function(value, row, index) {
				
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.centerId + '</a>';
			}*/
		},
		{
			field : 'centerName',
			title : '名称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerNumber',
			title : '序号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerPhone',
			title : '电话',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'centerNotes',
			title : '备注',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cityId',
			title : '所属城市Id',
			align : 'center',
			sortable : true
		}
			,	
			
		{
			field : 'cityName',
			title : '所属城市',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cities',
			title : '所属地市',//1：普通  2：高速
			align : 'center',
			sortable : true ,
			formatter:function(value,row,index){
				 if(row.cities==1){
					  return "普通";
				  }else if(row.cities==2){
					  return "<font color=green>高速</font>";
				  }else{
					  return "未知";
				  }
			   }
		}
			,	
			
		{
			field : 'curYear',
			title : '年份',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'curSerialNo',
			title : '编码',
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
		}	,	
		{
			field : 'readNum',
			title : '认字号编号',
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
		toolbar : page_toolBar,
		singleSelect:true
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
	var url = contextRootPath+"/qp/qpttpfastcenter/prepareUpdate.do?centerId=" + rows[0].centerId;
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpttpfastcenter/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpttpfastcenter/view.do?" + params;
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
			var url = contextRootPath+"/qp/qpttpfastcenter/delete.do?centerId=" + rows[0].centerId;
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
	    	if ($('#qpTTPFastCenterValidStatus').val() == null
					|| $('#qpTTPFastCenterValidStatus').val() == "") {
				$.messager.alert('提示信息', '请选择快有效状态！', 'info');
				return;
			}
			
			if ($('#qpTTPFastCenterCities').val() == null
					|| $('#qpTTPFastCenterCities').val() == "") {
				$.messager.alert('提示信息', '请选择所属地市！', 'info');
				return;
			}
			
			
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpttpfastcenter/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpttpfastcenter/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}	
				}
			});
	    }	    
	}