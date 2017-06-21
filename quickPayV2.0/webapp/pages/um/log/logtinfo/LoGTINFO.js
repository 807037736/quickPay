var query_action = contextRootPath + "/um/logtinfo/query.do";
var queryResultTable = "LoGTINFOTable";
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
			field : 'id',
			title : '日志ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.logId + '</a>';
			},
			sorter : function(a, b) {
				var fir = a.logId;
				var sec = b.logId;
				return (fir > sec ? 1 : -1);
			}

		}, {
			field : 'operateTypeName',
			title : '操作类型名称',
			align : 'center',
			sortable : true
		}, {
			field : 'userCode',
			title : '用户代码',
			align : 'center',
			sortable : true
		}, {
			field : 'userName',
			title : '用户名称',
			align : 'center',
			sortable : true
		}, {
			field : 'operateTime',
			title : '操作时间',
			align : 'center',
			sortable : true

		}

		, {
			field : 'operateMethod',
			title : '操作方法',
			align : 'center',
			sortable : true
		}, {
			field : 'operateResult',
			title : '操作结果',
			align : 'center',
			sortable : true
		}

] ];

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
		columns : page_contentColumnHeaders
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
	var url = contextRootPath + "/um/logtinfo/prepareUpdate.do?"
			+ getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath + '/um/logtinfo/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath + "/um/logtinfo/view.do?" + params;
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
	$.messager.confirm('提示', '是否确认删除?', function(r) {
		if (r) {
			var url = contextRootPath + "/um/logtinfo/delete.do?"
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
		$.messager.confirm('提示', '是否确认保存?', function(r) {
			if (r) {
				if ($('#operateType').val() == 'update') {
					$("#fm").attr("action",
							contextRootPath + "/um/logtinfo/update.do");
					$("#fm").submit();
				} else if ($('#operateType').val() == 'add') {
					$("#fm").attr("action",
							contextRootPath + "/um/logtinfo/add.do");
					$("#fm").submit();
				} else {
					return false;
				}
			}
		});
	}
}