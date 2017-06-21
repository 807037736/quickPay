var query_action = contextRootPath + "/dms/query.do";
var queryResultTable = "ResultTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}, {
	text : '添加代码类型',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		adminCodeType();
	}
} ];
var page_contentColumnHeaders = [ [ {
	field : 'checkBoxNo',
	checkbox : true
}, {
	field : 'codeCode',
	title : '代码',
	align : 'center',
	sortable : true,
	formatter : function(value, row, index) {
		return row.id.codeCode;
	}
}, {
	field : 'codeCName',
	title : '中文名称',
	align : 'center',
	sortable : true
}, {
	field : 'codeType',
	title : '代码类型',
	align : 'center',
	sortable : true,
	formatter : function(value, row, index) {
		return row.id.codeType;
	}
},
/*
 * { field : 'upperCode', title : '上层代码', align : 'center', sortable : true } ,
 */
{
	field : 'validDate',
	title : '有效时间',
	align : 'center',
	sortable : true
}, {
	field : 'invalidDate',
	title : '过期时间',
	align : 'center',
	sortable : true
}, {
	field : 'validStatus',
	title : '是否有效',
	align : 'center',
	sortable : true
}
/*
 * , { field : 'commonFlag', title : '是否公开', align : 'center', sortable : true } , {
 * field : 'dataSource', title : '数据来源', align : 'center', sortable : true } , {
 * field : 'flag', title : '标志位', align : 'center', sortable : true }
 */

,

{
	field : 'sort',
	title : '排序',
	align : 'center',
	sortable : true
} ] ];

/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#' + queryResultTable).datagrid({
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		singleSelect : true,
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
	if (rows[0]["dataSource"] == 'dms3g:prpdnewcode') {
		$.messager.alert('提示消息', "不允许修改", 'info');
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath + "/dms/prepareUpdate.do?"
			+ getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath + '/dms/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath + "/dms/view.do?" + params;
	editRecord(url);
}

/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	if (rows[0]["dataSource"] == 'dms3g:prpdnewcode') {
		$.messager.alert('提示消息', "不允许删除", 'info');
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r) {
		if (r) {
			var url = contextRootPath + "/dms/delete.do?"
					+ getUrlByJson(rows[0]);
			$.ajax({
				type : "POST",
				url : url,
				success : function(result) {
					var obj = eval("(" + result + ")");
					if (obj.msg == 'success') {
						$.messager.alert('提示信息', '记录删除成功！	', 'info');
						$('#' + queryResultTable).datagrid('reload');
					} else {
						$.messager.alert('错误信息', obj.msg, 'error');
					}
				}
			});
		}
	});
}

function closeWinAndReLoad() {
	try {
		window.opener.reLoadResult();
	} catch (e) {
	}
	window.close();
}
function executeSave() {
	if (!$('#fm').form('validate')) {
		return false;
	} else {
		$.messager
				.confirm(
						'提示',
						'是否确认保存?',
						function(r) {
							if (r) {
								if ($('#opreateType').val() == 'update') {
									$("#fm").attr("action",
											contextRootPath + "/dms/update.do");
									$("#fm").submit();
								} else if ($('#opreateType').val() == 'add') {
									if ($("#mcDNewCode\\.dataSource").val() == "") {
										$.messager
												.alert(
														'提示信息',
														'请双击代码类型获取数据来源;<br/>若无合适的代码类型，请先添加代码类型！',
														'info');
										return false;
									}
									$("#fm").attr("action",
											contextRootPath + "/dms/add.do");
									$("#fm").submit();
								} else {
									return false;
								}
							}
						});
	}
}
// 管理type
function adminCodeType() {
	$('#typeConfigWindow').window('open');
}
