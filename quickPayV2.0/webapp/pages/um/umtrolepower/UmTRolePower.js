var query_action = contextRootPath + "/um/umtrolepower/query.do";
var queryResultTable = "UmTRolePowerTable";
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
			field : 'rpId',
			title : '主键ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.rpId + '</a>';
			}
		},
		{
			field : 'roleId',
			title : '角色ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'dictionaryId',
			title : '字典ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operationSymbol',
			title : '权限操作符',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'powerValue',
			title : '权限域值',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'comId',
			title : '8位机构号',
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
			title : '更新人代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operateTimeForHis',
			title : '更新时间',
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
	var url = contextRootPath+"/um/umtrolepower/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtrolepower/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtrolepower/view.do?" + params;
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
			var url = contextRootPath+"/um/umtrolepower/delete.do?" + getUrlByJson(rows[0]);
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


function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg
	});
}

function executeSave(){
	
	showProcess(true,"温馨提示","正在修改角色附加权限数据信息..");
	var count = $("#rolePower tbody tr").length;								//当前数据权限明细所对应的Table
	if(count<1){
		$.messager.alert("提示","没有需要处理的数据");
		showProcess(false);
	}else {
		var flag = true;
		var rows = document.getElementById("rolePower").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
		var inputElements = null;
		
		for(var index=0;index<rows.length;index++){											//排除第一行处理
			inputElements = rows[index].getElementsByTagName("input"); 
			for(var pos = 0;pos<inputElements.length;pos++){
				//处理IE8无法访问属性的异常
				if($(inputElements[pos]).attr("type")!="hidden"&&$(inputElements[pos]).attr("required")=="required"){
					//只对需要校验的字段进行处理
					if(inputElements[pos].value==""){
				        $.messager.alert("错误提示","第"+(index+1)+"行,第"+(pos-1)+"列:"+inputElements[pos].name+"录入数据错误,不能为空");
				        flag = false;
				        showProcess(false);
				        return false;
					}
					if(inputElements[pos].validity!=undefined){
						if (!inputElements[pos].validity.valid) {
					        $.messager.alert("错误提示","第"+(index+1)+"行,第"+(pos-1)+"列:"+inputElements[pos].name+"录入数据错误");
					        flag = false;
					        showProcess(false);
					        return false;
					    }
					}
				}
			}
		}
		
		if(!flag){
			return;
		}
		var effectRow = new Object();
		var inserted = new Array();
		var updated = new Array();
		var deleted = new Array();
		var insert = null,update = null,del = null;
		var rows = null,flaginput = null;
		for(var index = 1;index<=count;index++){
			rows = document.getElementById("rolePower").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
			flaginput = rows[index-1].getElementsByTagName("input"); 
			if(flaginput[0].value=="I"){
				//添加数据
				insert = new Object();
				insert.roleId = document.getElementById("umTRolePower.roleId").value;						//获取需要配置附加数据权限的角色ID
				insert.dictionaryId = $("input[name='dictionaryId']")[index].value;												//获取配置的数据权限字典
				insert.powerValue = $("select[name='powerValue'] option:selected")[index].value;												//获取配置的数据权限附加值
				inserted.push(insert);
			}else if(flaginput[0].value=="U"){
				update = new Object();
				update.rpId =  $("input[name='rpId']")[index].value;
				update.roleId = document.getElementById("umTRolePower.roleId").value;						//获取需要配置附加数据权限的角色ID
				update.dictionaryId = $("input[name='dictionaryId']")[index].value;												//获取配置的数据权限字典
				update.powerValue = $("select[name='powerValue'] option:selected")[index].value;												//获取配置的数据权限附加值
				updated.push(update);
			}else if(flaginput[0].value=="D"){
				del = new Object();
				del.rpId = $("input[name='rpId']")[index].value;
				deleted.push(del);
			}
		}
		effectRow["inserted"] = JSON.stringify(inserted);
		effectRow["updated"] = JSON.stringify(updated);
		effectRow["deleted"] = JSON.stringify(deleted);
		
		if("[]"==effectRow["inserted"]&&"[]"==effectRow["updated"]&&"[]"==effectRow["deleted"]){
			$.messager.alert("提示", "没有修改数据,无须提交!","info");
			showProcess(false);
		}else {
			$.post(
					contextRootPath
							+ "/um/umtrolepower/rowEdit.do",
					effectRow, function(rsp) {
						if (rsp.status=="true") {
							$.messager.alert("提示", "操作成功!","info",function(){
								showProcess(false);
								history.go(0);
							});
						}else {
							$.messager.alert("提示", rsp.errormsg,"error",function(){
								showProcess(false);
								history.go(0);
							});
						}
					}, "JSON").error(function() {
				$.messager.alert("提示", "操作失败");
			});
		}
	}
}