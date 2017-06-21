var query_action = contextRootPath + "/qp/qptcominform/query.do";
var queryResultTable = "informTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}},
	{
	text : '删除',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		executeDelete();
	}},
	{
		text : '发布',
		align : 'right',
		iconCls : 'icon-ok',
		handler : function() {
			executePublish();
		}
	},
	{
		text : '公告下架',
		align : 'right',
		iconCls : 'icon-undo',
		handler : function() {
			executeRepeal();
		}
	}];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'informId',
			title : '公告序号',
			align : 'center',
			sortable : true ,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.informId + '</a>';
			}			
		},
		{
			field : 'title',
			title : '公告标题',
			align : 'center',
			sortable : true
		},
		{
			field : 'content',
			title : '公告内容',
			align : 'center',
			width : 160,
			sortable : true
		},
		{	
			field : 'state',
			title : '发布状态',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value=='1'){
					return '发布中';
				}else if(value=='0'){
					return '待发布';
				}else if(value=='2'){
					return '已失效';
				}else if(value=='3'){
					return '预发布';
				}
			}
		},
		{
			field : 'creator',
			title : '创建者',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == ''){
					return '匿名';
				}else{
					return value;
				}
			}
		},
		{
			field : 'startTime',
			title : '发布时间',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == ''){
					return '暂无设置';
				}else{
					return value.substr(0,19);
				}
			}
		},
		{
			field : 'endTime',
			title : '结束时间',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				return value.substr(0,19);
			}
		},
		{
			field : 'createTime',
			title : '创建时间',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				return value.substr(0,19);
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
		toolbar : page_toolBar,
		singleSelect : true
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
	var url = contextRootPath+"/qp/qptcominform/prepareUpdate.do?informId=" + rows[0].informId;
	editRecord(url);
}
/* 发布*/
function executePublish(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	if (rows[0].state == 1) {
		$.messager.alert('提示消息', "公告发布中，无需再发布。", 'info');
		return;
	}
	
	var date = new Date();
	var endTime = rows[0].endTime;
	var beginTime = date.getTime();
	var endTimes = endTime.substring(0, 10).split('-');
    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
    var a = (Date.parse(endTime) - beginTime) / 3600 / 1000;
	if (a < 0) {
    	$.messager.alert('提示消息', "公告已过期，请调整结束时间！", 'info');
        return false;
    }
	rows[0].startTime = getNowFormatDate();
	rows[0].state=1;
	var url = contextRootPath+"/qp/qptcominform/update.do?" + getUrlByJson(rows[0]);
	 $.ajax({
		   type: "POST",
		   url: url,
		   success: function(result){
			   $.messager.alert('成功提示','已发布至首页!','info');
			   $('#'+queryResultTable).datagrid('reload');
		   },
		   error:function(result){
			   $.messager.alert('发布失败','系统维护中，请稍后再试。','info');
		   }
	 });
		
}

/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qptcominform/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qptcominform/view.do?" + params;
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
			var url = contextRootPath+"/qp/qptcominform/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
					   },
					   error:function(result){
						   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
					   }
			});
		}
	});
}

function executeSave(){
	/*判断结束时间是否有效*/
	var date = new Date();
	var endTime = $("#endTime").val();
	var startTime = $('#startTime').val();
	var beginTime = date.getTime();
	var endTimes = endTime.substring(0, 10).split('-');
	var startTimes = startTime.substring(0, 10).split('-');
    endTime = Date.parse(endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19));
    startTime = Date.parse(startTimes[1] + '-' + startTimes[2] + '-' + startTimes[0] + ' ' + startTime.substring(10, 19));
    var a = (endTime - beginTime) / 3600 / 1000;
	var b = (startTime - beginTime) / 3600 / 1000;
	if($('#state').val() == "1"){
		
	}else if($('#startTime').val() != ''){
    	$('#state').val("3");
    }else{
    	$('#state').val("0");
    }
	if(!$('#fm').form('validate')){
    	return false; 
    }
//	else if (b < 0) {
//    	$.messager.alert('提示消息', "公告开始时间已过，请调整！", 'info');
//        return false;
//    }
	else if (a < 0) {
    	$.messager.alert('提示消息', "公告结束时间已过，请调整！", 'info');
        return false;
    }else if (startTime > endTime) {
    	$.messager.alert('提示消息', "发布时间  应在  结束时间  之前！", 'info');
        return false;
    }else if ($("#title").val()==''|| $("#content").val()=='' ){
    	$.messager.alert('提示消息', "标题与内容不能为空！", 'info');
        return false;
    }else{
    	$.messager.confirm('提示', '是否确认保存?', function(r){
			if (r){
			 	if($('#operateType').val()=='update'){
			 		$("#fm").attr("action",contextRootPath+"/qp/qptcominform/update.do");
			 		$("#fm").submit();
			 	}else if($('#operateType').val()=='add'){
			 		$("#fm").attr("action",contextRootPath+"/qp/qptcominform/add.do");
			 		$("#fm").submit();
			 	}else{
			 		return false;
			 	}				 	
			}
		});
    }	    
}	

function executeSaveAndPublish(){
	
	$("#state").val("1");
	$("#startTime").val(getNowFormatDate());
	executeSave();
//	window.opener.reLoadResult();
}
/*公告下架*/
function executeRepeal(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	if (rows[0].state == 2) {
		$.messager.alert('提示消息', "该公告无需下架。", 'info');
		return;
	}
	rows[0].state=2;
	var url = contextRootPath+"/qp/qptcominform/update.do?" + getUrlByJson(rows[0]);
	 $.ajax({
		   type: "POST",
		   url: url,
		   success: function(result){
			   $.messager.alert('成功提示','公告下架成功!','info');
			   $('#'+queryResultTable).datagrid('reload');
		   },
		   error:function(result){
			   $.messager.alert('下架失败','系统维护中，请稍后再试。','info');
		   }
	 });
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + (date.getSeconds()+3);
    return currentdate;
}


function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}