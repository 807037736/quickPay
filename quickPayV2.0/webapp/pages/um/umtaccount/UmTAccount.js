var query_action = contextRootPath + "/um/umtaccount/query.do";
var queryResultTable = "UmTAccountTable";
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
		},
		{
			field : 'userCode',
			title : '用户代码',
			align : 'center',
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.userCode + '</a>';
			}
		},
		{
			field : 'fingerId',
			title : '指纹ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'creatorCode',
			title : '创建人代码',
			align : 'center'
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
			align : 'center'
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
			formatter : function(value, row, index) {
				if(value=='1'){
					return '有效';
				}else if(value=='0'){
					return '无效';
				}
			}
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
		singleSelect : true,
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
	var url = contextRootPath+"/um/umtaccount/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtaccount/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtaccount/view.do?" + params;
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
			var url = contextRootPath+"/um/umtaccount/delete.do?" + getUrlByJson(rows[0]);
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

	function executeSave(){
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){ 
				 	if($('#opreateType').val()=='update'){
				 		var password1=$("#umTAccount\\.password").val();
				 		if(password1!=($("#password1").val())){
				 			$("#umTAccount\\.password").val(SHA1(password1));
				 			$("#isChange").val("1");
				 		}
				 		$("#fm").attr("action",contextRootPath+"/um/umtaccount/update.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		var password1=$('#umTAccount\\.password').val();
				 		$("#umTAccount\\.password").val(SHA1(password1));
				 		var data = $("#fm").serialize();
				 		var query_action =contextRootPath+"/um/umtaccount/add.do";
				 		var send_url = query_action + "?" + data;
				 		$.ajax({
							   type: "POST",
							   url: send_url,
							   success: function(result){
								   $.messager.alert('提示信息','保存成功！ ','info');
								    },
								 error:function(result){
									 jsonObject = eval('('+result.responseText+')');
									 $.messager.alert(jsonObject.errorTitle,jsonObject.errorMsg);
								    }

					});
				 		
				 		/*$("#fm").attr("action",contextRootPath+"/um/umtaccount/add.do");
				 		$("#fm").submit();*/
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}