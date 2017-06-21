var query_action = contextRootPath + "/qp/qpttpcasestat/query.do";
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
var page_contentColumnHeaders = [];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	page_contentColumnHeaders = [];
	var fieldList = [];//new Array("区县","晴","阴","雾");
	var url = contextRootPath + "/qp/qpttpcasestat/getFieldList.do";
	 $.ajax({
			   type: "POST",
			   url: url,
			   async:false,
			   dataType: "json",
			   data:data,
			   success: function(result){
				   fieldList = result.fieldList;
			   },
			   error:function(result){
				   alertErrorMsgForEasyUi(result);
			   }
	});

	var tempStr = "";
	for(var i in fieldList){
		if(i != 0){
			tempStr+=",";
		}
		if(i == 30){
			break;
		}
		tempStr += "{field : 'field_"+i+"',title : '"+fieldList[i]+"',align : 'center',width:'150',sortable : true}";
	}
	page_contentColumnHeaders = eval("[["+tempStr+"]]");
	
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
		columns : page_contentColumnHeaders
	});
	
	
}
function expExcel(){
	var data = $("#fm").serialize();
	var url = contextRootPath + "/qp/qpttpcasestat/expExcel.do?" + data;
	fm.action = url;
	fm.submit();
}