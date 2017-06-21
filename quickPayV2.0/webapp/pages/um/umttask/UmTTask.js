var query_action = contextRootPath + "/um/umttask/query.do";
var queryResultTable = "UmTTaskTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		}
			,	
		{
			field : 'taskName',
			title : '功能名称',
			align : 'center'
		}
			,	
		{
			field : 'upperTaskName',
			title : '上级功能名称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'methodCode',
			title : '访问URL',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'svrCode',
			title : '系统代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'taskType',
			title : '功能类型',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == "menu"){
					return "菜单";
				}if(value == "button"){
					return "按钮";
				}else{
					return "门户";
				}
			}
		},	
		{
			field : 'userType',
			title : '用户类型',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == "01"){
					return "内部";
				}else{
					return "外部";
				}
			}
		},	
		{
			field : 'isOpen',
			title : '是否公开',
			align : 'center',
			sortable : true ,
			formatter : function(value,row,index){
				if(value == "1"){
					return "是";
				}else{
					return "否";
				}
			}
		},	
		{
			field : 'openLevel',
			title : '开放等级',
			align : 'center',
			sortable : true ,
			formatter : function(value,row,index){
				if(value == "00"){
					return "都可用";
				}if(value == "01"){
					return "注册可用";
				}else{
					return "绑定可用";
				}
			}
		}
			,	
		{
			field : 'validStatus',
			title : '有效状态',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == "1"){
					return "有效";
				}else{
					return "无效";
				}
			}
		},	
		{
			field : 'taskCode',
			title : '功能代码',
			align : 'center'
		}
			,	
		{
			field : 'upperTaskCode',
			title : '上级功能代码',
			align : 'center',
			sortable : true
		},	
		{
			field : 'expireDate',
			title : '失效日期',
			align : 'center',
			sortable : true
		}
		
		] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}
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
	var url = contextRootPath+"/um/umttask/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umttask/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umttask/view.do?" + params;
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
			var url = contextRootPath+"/um/umttask/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
						   $.messager.alert('提示信息','记录删除成功！ ','info');
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


function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg,
		text:''
	});
}

	function executeSave(){
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
					//校验功能代码是否存在
					var validURL=contextRootPath+"/um/umttask/checkTaskCode.do?opreateType="+$('#opreateType').val()+"&initTaskCode="+document.getElementById("umTTask.taskCode").value;
					var param = $("#fm").serialize();
					
					$.ajax({
						type:"POST",
						url:validURL,
						data:param,
						beforeSend:function(){
							showProcess(true,"温馨提示","正在验证功能代码的有效性...请稍候...");
						},
						success:function(data){
							showProcess(false);
							if(data=="true"){
								if($('#opreateType').val()=='update'){
							 		$.ajax({
							 			type:"POST",
							 			url:contextRootPath+"/um/umttask/update.do",
							 			data:param,
							 			dataType:"json",
							 			beforeSend:function(){
							 				showProcess(true,"温馨提示","正在更新功能代码...请稍候...");
							 			},
							 			success:function(msg){
							 				showProcess(false);
							 				if(msg.status == 'success'){
							 					location = contextRootPath+"/pages/Success.jsp";
							 				}else{
							 					$.messager.alert("提示信息",msg.content,"info");
							 				}
							 			}
							 		});
							 	}else if($('#opreateType').val()=='add'){
							 		$("#fm").attr("action",contextRootPath+"/um/umttask/add.do");
							 		$("#fm").submit();
							 	}else{
							 		return false;
							 	}		
							}else{
								$.messager.alert("提示信息","功能代码已存在","error");
							}
						}
					});
				}
			});
	    }	    
	}

$(function(){
	//设置默认的失效日期
	if ($('#opreateType').val() == 'add') {
		document.getElementById("umTTask.expireDate").value = "2099-12-31";
	}
});