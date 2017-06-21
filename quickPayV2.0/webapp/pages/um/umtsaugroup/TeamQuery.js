var query_action = contextRootPath + "/um/umtsaugroup/query.do";
var queryResultTable = "UmTUserTable";
var page_toolBar = [ {
	text : '保存',
	align : 'right',
	iconCls : 'icon-save',
	handler : function() {
		save();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		}
			,
		{
			field : 'USERCODE',
			title : '用户代码',
			align : 'center'
			}
				,
		{
			field : 'USERNAME',
			title : '用户名称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'COMCODE',
			title : '所属机构',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'MOBILE',
			title : '手机号码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'EMAIL',
			title : '邮件地址',
			align : 'center'
		}
			,
		{
			field : 'FLAG',
			title : '标志字段',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'USERTYPE',
			title : '用户类型',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'USERSORT',
			title : '用户分类',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='01'){
					return '个人用户';
				}else if(value=='02'){
					return '企业用户';
				}
			}
		}
			,	
		{
			field : 'IDENTIFYNUMBER',
			title : '证件号码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'AGE',
			title : '年龄',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'SEX',
			title : '性别',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='1'){
					return '女';
				}else if(value=='0'){
					return '男';
				}
			}
		}
			,	
		{
			field : 'BIRTHDATE',
			title : '生日',
			align : 'center',
			sortable : true
		}
				
		] ];


/* 查询 */
function executeQuery() {
	if(!$('#fm').form('validate')){
    	return false; 
    }else{
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		title : "查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : false,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}
	});
    }
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
	var url = contextRootPath+"/um/umtuser/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtuser/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtuser/view.do?" + params;
	editRecord(url);
}

/* 同步 */
function save() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认同步该用户至营销系统?', function(r){
		if (r){
			var url = contextRootPath+"/um/umtuser/save.do?" ;
			 $.ajax({
					   type: "POST",
					   url: url,
					   data:getUrlByJson(rows[0]),
					   success: function(result){
						   var obj=eval("(" + result + ")");
						  if(obj.msg=='success'){
							  $.messager.alert('提示信息','用户同步成功！	','info');
							 /* $('#'+queryResultTable).datagrid('reload');*/
						  }else{
							  $.messager.alert('错误信息',obj.msg,'error');
						  }
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
				 		$("#fm").attr("action",contextRootPath+"/um/umtuser/update.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtuser/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}