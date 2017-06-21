var query_action = contextRootPath + "/um/umtregistuser/query.do";
var queryResultTable = "UmTRegistuserTable";
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
			field : 'userCode',
			title : '用户注册编号',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.userCode + '</a>';
			}
		},
		{
			field : 'userName',
			title : '用户姓名',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relatedCode',
			title : '关联代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'comCode',
			title : '机构代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'password',
			title : '注册密码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'nickName',
			title : '昵称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'mobile',
			title : '手机号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'email',
			title : '安全邮箱',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'registSource',
			title : '注册来源',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'pwdModifyTime',
			title : '密码最近修改时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'pwdModifyTimes',
			title : '密码修改次数',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'userType',
			title : '用户类型（00-外部客户，01-员工，02-渠道）',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'insertTimeForHis',
			title : '注册时间',
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
			field : 'updaterCode',
			title : '更新人代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operateTimeForHis',
			title : '最近操作时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStatus',
			title : '有效性',
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
	var url = contextRootPath+"/um/umtregistuser/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtregistuser/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtregistuser/view.do?" + params;
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
			var url = contextRootPath+"/um/umtregistuser/delete.do?" + getUrlByJson(rows[0]);
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
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtregistuser/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtregistuser/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
	
	
	function goSubmit() {
		
		if(!checkForm())
			return false;
		
		
	}
	
	function checkForm() {
		
		var name = $("#name").val();
		var phoneNo = $("#phoneNo").val();
		var validate_code = $("#validate_code").val();
		var password = $("#password").val();
		var rePassword = $("#rePassword").val();
		
		var validatecodeOK = $("#").val();
		
	}