var queryWX_action = contextRootPath + "/weixin/qpweixincase/queryFinishWxTask.do";
var queryResultTable = "QpWeixinFinishTaskTable";

var page_toolBar = [ {
	text : '刷新数据',
	align : 'right',
	iconCls : 'icon-reload',
	handler : function() {
		reloadT();
	}}];

var page_contentColumnHeaders = [[
	{
		field : 'finishId',
		hidden:true,
		sortable : true
	},
	{
		field : 'caseId',
		hidden:true,
		sortable : true
	},
	{
		field : 'createName',
		title : '创建人',
		align : 'center',
		sortable : true,
		width: 100
	},
	{
		field : 'createTime',
		title : '创建时间',
		align : 'center',
		sortable : true,
		width: 160
		
	},
	{
		field : 'receiveCode',
		hidden:true,
		sortable : true,
		width: 120
	},
	{
		field : 'receiveName',
		title : '案件领取人',
		align : 'center',
		sortable : true,
		width: 120
	},
	{
		field : 'receiveTime',
		title : '案件领取时间',
		align : 'center',
		sortable : true,
		width: 160	
	},
	{
		field : 'completeName',
		title : '案件完成人',
		align : 'center',
		sortable : true,
		width: 120	
	},
	{
		field : 'completeTime',
		title : '案件完成时间',
		align : 'center',
		sortable : true,
		width: 160
	},
	{
		field : 'status',
		title : '操作类型',
		align : 'center',
		sortable : true,
		formatter:function(value,row,index){
			if (value =='9') {
				return "<font color='red' class='easyui-tooltip' title='"+row.remark+"' >撤销案件</font>";
			} else {
				return "定责案件";
			} 
		}
	},
//	{
//		field : 'fallbackTime',
//		title : '回退时间',
//		align : 'center',
//		sortable : true,
//		formatter:function(value,row,index){
//			if (value ==null) {
//				return "<font color='#AAAAAA'>无</font>";
//			} else {
//				return value;
//			} 
//		}	
//	},
	
	{
		field : '操作',
		title:'操作',
		align:'center',
		sortable : true,
		width: 80,
		formatter:function(value,row,index){
			var op = '';
			op = "<a target=_blank href='/qp/qpttpcase/prepareAdd.do?&&actionType=view&businessType=-1&qpTTPCaseCaseId="+row.caseId+"' >&nbsp;查看详情&nbsp;</a>";
			return op;
		}	
	}
	]];
var dTable;



/* 查询微信用户 */
$(function () {
	var send_url = queryWX_action;
	dTable = $('#'+queryResultTable).datagrid({
		url : send_url,
		title : "已完成案件",
		pagination : true,
		loadMsg:null,
		pagePosition : 'bottom',
		pageSize: 20,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		singleSelect: true,
		scrollbarSize : 0 ,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		sortName:'status',
	    sortOrder:'asc',
	    fitColumns:"false",
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
});

//条件搜索
function executeQuery() {
	var searchForm = $('#fm').form();//条件搜索框
	dTable.datagrid("unselectAll");
	dTable.datagrid('load', serializeObject(searchForm));
}

function resetInput(){
	$("#fm")[0].reset();  
}

var serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};


function reloadT() {
	dTable.datagrid("load");
}


