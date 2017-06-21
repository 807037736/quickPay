var query_action = contextRootPath + "/um/umtuser/query.do";
var queryResultTable = "UmTUserTable";
var page_toolBar = [{
	text : '角色配置',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		roleConfig();
	}
}, 
    {
	text : '数据权限配置',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		dataPowerConfig();
	}
}, {
	text : '功能权限配置',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		taskPowerConfig();
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
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.usercode + '</a>';
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
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'email',
			title : '邮件地址',
			align : 'center',
			sortable : true
		}
			
			,	
		{
			field : 'userType',
			title : '用户类型',
			align : 'center',
			formatter : function(value, row, index) {
				if(value=='01'){
					return '内部用户';
				}else if(value=='02'){
					return '外部用户';
				}
			}
		}
			,	
		{
			field : 'userSort',
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
			field : 'identityNumber',
			title : '身份证号',
			align : 'center'
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
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='2'){
					return '女';
				}else if(value=='1'){
					return '男';
				}
			}
		}
			,	
		{
			field : 'birthday',
			title : '生日',
			align : 'center',
			formatter : function(value, row, index) {
				if(value){
					//IE不能转"2009-09-09"的格式
					var time = new Date(value.replace(/-/g,'/'));
		            return time.toLocaleDateString();
					/*return value.subString(0,10);*/
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
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		singleSelect : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}
	});
    }
}

/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtuser/view.do?" + params;
	editRecord(url);
}
/* 数据权限配置 */
function dataPowerConfig() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var userCode = getSingleSelectRow(queryResultTable, "usercode", "请选择一行");

	var url = contextRootPath+"/um/umtuserpower/addUserPower.do?userCode=" + userCode;
	editRecord(url);
}

/* 功能权限配置 */
function taskPowerConfig() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var userCode = getSingleSelectRow(queryResultTable, "usercode", "请选择一行");

	var url = contextRootPath+"/um/umtusertask/addUserTask.do?userCode=" + userCode;
	editRecord(url);
}
/* 角色配置 */
function roleConfig() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var userCode = getSingleSelectRow(queryResultTable, "usercode", "请选择一行");

	var url = contextRootPath+"/um/umtuserrole/preparedAddUserRole.do?userCode=" + userCode;
	editRecord(url);
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}
