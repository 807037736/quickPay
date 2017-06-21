var query_action = contextRootPath + "/qp/qpttpresponsible/query.do";
var queryResultTable = "QpTTPResponsibleTable";


/* 查询 */
function executeQuery() {
	
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	
	$('#'+queryResultTable).datagrid({
		title : "定责信息",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns:[[
					{field : 'coname',title : '保险公司名称',align : 'center',sortable : true},
					{field : 'total',title : '总量',align : 'center',sortable : true},
					{field : 'mainResponsibility',title : '主责',align : 'center',sortable : true},
					{field : 'mainResponsibilityPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}	
					},	
					{field : 'secondaryResponsibility',title : '次责',align : 'center',sortable : true},
					{field : 'secondaryResponsibilityPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}		
					},	
					{field : 'sameResponsibility',title : '同责',align : 'center',sortable : true},
					{field : 'sameResponsibilityPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}		
					},		
					{field : 'allResponsibility',title : '全责',align : 'center',sortable : true},
					{field : 'allResponsibilityPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}		
					},
					{field : 'noResponsibility',title : '无责',align : 'center',sortable : true},
					{field : 'noResponsibilityPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}	
					},
					{field : 'afterEthig',title : '20:00以后的案件量',align : 'center',sortable : true},
					{field : 'afterEthigPro',title : '占比',align : 'center',sortable : true,
						formatter : function(value, row, index) {
							return transformation(value);
						}		
					}
	        ]],
	        onClickRow: function (rowIndex, rowData) {
                $(this).datagrid('unselectRow', rowIndex);
            },
	});
	
	
}

function transformation(value){
	return (Math.round(value * 10000)/100).toFixed(2) + '%';
}

function expExcel(){
	var data = $("#fm").serialize();
	var url = contextRootPath + "/qp/qpttpresponsible/exportResponsibleExcel.do?" + data;
	fm.action = url;
	fm.submit();
}