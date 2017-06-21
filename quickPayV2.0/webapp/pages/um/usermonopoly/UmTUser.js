var query_action = contextRootPath + "/um/umtuser/query.do";
var queryResultTable = "UmTUserTable";
var page_toolBar = [ {
	text : '用户推荐送修码配置',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareAddMonopoly();
	}
} ];
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
				return  row.id.userCode;
			}
			
		},
		{
			field : 'userName',
			title : '用户名称',
			align : 'center'
		}
			,	
		{
			field : 'comCode',
			title : '归属机构',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'mobile',
			title : '手机号码',
			hidden: true,
			align : 'center'
		}
			,	
		{
			field : 'email',
			title : '邮件地址',
			align : 'center',
			hidden: true,
			sortable : true
		}
			,
		{
			field : 'flag',
			title : '标志字段',
			align : 'center',
			hidden: true,
			sortable : true
		}
			,	
		{
			field : 'userType',
			title : '用户类型',
			align : 'center',
			formatter : function(value, row, index) {
				if(value=='01'){
					return '非销售人员';
				}else if(value=='02'){
					return '销售人员';
				}
			}
		}
			,	
		{
			field : 'userSort',
			title : '用户分类',
			align : 'center',
			hidden: true,
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
			field : 'identityNumber',
			title : '身份证号',
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
			,	
		{
			field : 'sex',
			title : '性别',
			align : 'center',
			hidden: true,
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='1'){
					return '男';
				}else if(value=='2'){
					return '女';
				}
			}
		}
			,	
		{
			field : 'birthday',
			title : '生日',
			align : 'center',
			hidden: true,
			formatter : function(value, row, index) {
				if(value){
					//IE不能转"2009-09-09"的格式
					var time = new Date(value.replace(/-/g,'/'));
		            return time.toLocaleDateString();
					return value.subString(0,10);
				}
				return "";
			}
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
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		singleSelect: true,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
    }
}
function prepareAddMonopoly(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/um/umtuser/prepareUserMonopoly.do?userCode="+ rows[0].id.userCode;
	editRecord(url);
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
			var url = contextRootPath+"/um/umtuser/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
						   var obj=eval("(" + result + ")");
						  if(obj.msg=='success'){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
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
				 		
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}