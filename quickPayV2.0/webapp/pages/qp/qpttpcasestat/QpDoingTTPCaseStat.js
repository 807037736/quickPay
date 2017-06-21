var query_action = contextRootPath + "/qp/qpttpcasestat/doingTaskQuery.do";
var queryResultTable = "QpTTPCaseTable";
var page_toolBar = [];
/*

		{
			field : 'caseSerialNo',
			title : '案件编号',
			align : 'center',
			sortable : true,
			
		}
 */
/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var url = contextRootPath + "/qp/qpttpcasestat/getDoingTaskFieldList.do";
	 $.ajax({
			   type: "POST",
			   url: url,
			   async:false,
			   dataType: "json",
			   data:data,
			   success: function(result){
				   
			   },
			   error:function(result){
				   alertErrorMsgForEasyUi(result);
			   }
	});
	var send_url = query_action;
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
		columns : [[{
			field:'field_0',
			title:'快处中心',
			align : 'center',
			width:'150',
			sortable : false
		},{
			field:'field_1',
			title:'待查勘',
			align : 'center',
			width:'150',
			sortable : false	
		},{
			field:'field_2',
			title:'处理中',
			align : 'center',
			width:'150',
			sortable : false
		},{
			field:'field_3',
			title:'已受理',
			align : 'center',
			width:'150',
			sortable : false
		}]],
		 onClickRow: function (rowIndex, rowData) {
             $(this).datagrid('unselectRow', rowIndex);
         }
	});
}