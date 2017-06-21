var queryWX_action = contextRootPath + "/weixin/qpweixincase/queryWxTask.do";
var queryResultTable = "QpWeixinTaskTable";

var page_toolBar = [ {
	text : '刷新任务',
	align : 'right',
	iconCls : 'icon-reload',
	handler : function() {
		reloadT();
	}}];

var page_contentColumnHeaders = [[
	{
		field : 'taskId',
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
		width: 120,
		formatter:function(value,row,index){
			if (value =='') {
				return "暂无领取";
			} else {
				return value;
			} 
		}	
	},
	{
		field : 'receiveTime',
		title : '案件领取时间',
		align : 'center',
		sortable : true,
		width: 160,
		formatter:function(value,row,index){
			if (value == null) {
				return "未领取";
			} else {
				return value;
			} 
		}	
	},
	{
		field : 'fallbackCode',
		hidden:true,
		sortable : true
	},
//	{
//		field : 'fallbackName',
//		title : '回退人员',
//		align : 'center',
//		sortable : true,
//		formatter:function(value,row,index){
//			if (value =='') {
//				return "<font color='#AAAAAA'>非回退案件</font>";
//			} else {
//				return value;
//			} 
//		}	
//	},
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
		field : 'caseStatus',
		title:'案件状态',
		align:'center',
		sortable : true,
		width: 120,
		formatter:function(value,row,index){
			var op = '';
			var caseStatus = row.status;
			if (caseStatus=='1') {
				op += '&nbsp;待定责&nbsp;';
			} else if(caseStatus=='3'){
      			op += '&nbsp;定责处理中&nbsp;';
			} else if(caseStatus=='2'){
      			op += '&nbsp;回滚案件&nbsp;';
			} else if(caseStatus=='4'){
      			op += '&nbsp;已完结案件&nbsp;';
			} else if(caseStatus=='0'){
      			op += '&nbsp;<font color=orange>当前操作人员处理中</font>&nbsp;';
			} 
				return op;
		}	
	},
	{
		field : 'status',
		title:'操作',
		align:'center',
		sortable : true,
		width: 80,
		formatter:function(value,row,index){
			var op = '';
			if (value=='1' || value=='2') {
				op = '<a name="bound" href="#" onclick="javascript:dealWxCase(this)" style=color:red;>&nbsp;定责&nbsp;</a>';
			} else if (value == '0'){
				op = '<a name="bound" href="#" onclick="javascript:dealWxCase(this)" style=color:blue;>&nbsp;继续定责&nbsp;</a>';
			} else if(value=='3'){
      			op = '&nbsp;定责处理中&nbsp;';
			} else if(value=='4'){
      			op += '<a name="checkFinishCase" href="#" onclick="javascript:checkFinishCase(this)" style=color:green;>&nbsp;查看定责结果&nbsp;</a>';
			} 
				return op;
		}	
	}
	]];
var dTable;
var reloadN = 0 ;
/* 查询微信用户 */
$(function () {
	var data = $("#fm").serialize();
	var send_url = queryWX_action + "?" + data;
	dTable = $('#'+queryResultTable).datagrid({
		title : "待定责案件",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		singleSelect: true,
		scrollbarSize : 0 ,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		/*sortName:'status',
	    sortOrder:'asc',*/
	    fitColumns:"false",
		columns : page_contentColumnHeaders,
		pageSize: 20,
		rowStyler: function(index,row){
			if (row.status == 3){
				return 'color:#AAAAAA;font-weight:bold;';
			}
		},
		toolbar : page_toolBar
	});
	reloadN = 1;
	reload();
});

function reloadT() {
	reloadN = 1;
	dTable.datagrid("load");
}

function reload() {
	if(reloadN == 1){
		reloadN =0;
		setTimeout('reload()', 60000);
		return ;
	}
	dTable.datagrid("load");
//	dTable.datagrid("unselectAll");
//	dTable.datagrid('load');
//	dTable.datagrid('insertRow',{
//			index: 0, 
//			row: {
//				"status": index+1,
//				"createTime": index * 2,
//				"createName": "测试" + index
//				}
//	});
//	index++;
    setTimeout('reload()', 60000);
}

function dealWxCase(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpWeixinTaskTable').datagrid('getRows')[index];
	$.ajax({
		   type: "POST",
		   dataType: "json",
		   url: contextRootPath + "/weixin/qpweixincase/querySingleWxTask.do?taskId="+row.taskId,
		   success: function(data){
		   	   respResult = data;
		   	   if(data.msg != '1'){
		   		   $.messager.alert('提示信息',data.msg,'info');
		   		   $('#'+queryResultTable).datagrid('reload');
		   	   }else{
		   		var caseUrl = "weixin/qpweixincase/prepareQueryCase.do?caseId="+row.caseId+"&taskId="+row.taskId;
//		   		alert(caseUrl);
		   		window.location.href = caseUrl;
		   	   }
		   },
		   error:function(result){
			   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
		   }
	});
}

function roolbackWxCase(obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpWeixinTaskTable').datagrid('getRows')[index];
	var caseUrl = "weixin/qpweixincase/roolbackWxCase.do?caseId="+row.caseId;
	$.messager.confirm('提示', '是否确认回退至待处理状态?', function(r){
		if (r){
			 $.ajax({
					   type: "POST",
					   url: caseUrl,
					   dataType: "json",
					   success: function(result){
							  $.messager.alert('提示信息','案件回滚成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
					   },
					   error:function(result){
						   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
					   }
			});
		}
	});
}

function checkFinishCase(obj){
	$.messager.alert('温馨提示','该功能暂未开放,敬请期待！','info');
}
