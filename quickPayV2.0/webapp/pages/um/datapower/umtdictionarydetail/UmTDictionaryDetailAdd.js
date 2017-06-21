var query_action = contextRootPath + "/um/umtdictionarydetail/query.do";
var queryResultTable = "UmTDictionaryDetailAddTable";

function clearAddForm() {
	$("#addWin").window("close");
}

/** 停止更新* */
function endEdit() {
	var rows = $("#" + queryResultTable).datagrid('getRows');
	for ( var i = 0; i < rows.length; i++) {
		$("#" + queryResultTable).datagrid('endEdit', i);
	}
}

var page_toolBar = [
		{
			text : '添加数据对象',
			align : 'right',
			iconCls : 'icon-add',
			handler : function() {
				$("#" + queryResultTable).datagrid('appendRow', {
						'dictionaryId':""+document.getElementById("umTDictionary.id.dictionaryId").value+"",
						'validStatus':'1',
						'comCode':""+document.getElementById("umTDictionary.comCode").value+""});
				var rows = $("#" + queryResultTable).datagrid('getRows');
				$("#" + queryResultTable)
						.datagrid('beginEdit', rows.length - 1);
			}
		},
		{
			text : '保存数据',
			align : 'right',
			iconCls : 'icon-save',
			handler : function() {
				endEdit();
				if ($("#" + queryResultTable).datagrid('getChanges').length) {
					var inserted = $("#" + queryResultTable).datagrid(
							'getChanges', "inserted");
					var deleted = $("#" + queryResultTable).datagrid(
							'getChanges', "deleted");
					var updated = $("#" + queryResultTable).datagrid(
							'getChanges', "updated");

					var effectRow = new Object();
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					if (deleted.length) {
						effectRow["deleted"] = JSON.stringify(deleted);
					}
					if (updated.length) {
						effectRow["updated"] = JSON.stringify(updated);
					}
					$.post(
									contextRootPath
											+ "/um/umtdictionarydetail/rowEdit.do?dictionaryId="
											+ document
													.getElementById("umTDictionary.id.dictionaryId").value,
									effectRow, function(rsp) {
										if (rsp.status) {
											$.messager.alert("提示", "操作成功!");
											$("#" + queryResultTable).datagrid("reload");
										}
									}, "JSON").error(function() {
								$.messager.alert("提示", "提交错误了!");
							});
				}else {
					$.messager.alert("提示","没有改动的数据需要提交保存");
				}
			}
		},
		{
			text : '修改数据对象',
			align : 'right',
			iconCls : 'icon-edit',
			handler : function() {
				var row = $("#" + queryResultTable).datagrid('getSelected');
				if (row) {
					var rowIndex = $("#" + queryResultTable).datagrid(
							'getRowIndex', row);
					$("#" + queryResultTable).datagrid('beginEdit', rowIndex);
				}else {
					$.messager.alert("提示","请选择需要操作的行数据");
				}
			}
		}, {
			text : '删除数据对象',
			align : 'right',
			iconCls : 'icon-remove',
			handler : function() {
				var row = $("#" + queryResultTable).datagrid('getSelected');
				if (row) {
					var rowIndex = $("#" + queryResultTable).datagrid('getRowIndex', row);
					$("#" + queryResultTable).datagrid('deleteRow', rowIndex);
				}else {
					$.messager.alert("提示","请选择需要操作的行数据");
				}
			}
		} ];

var page_contentColumnHeaders = [ [ {
	field : 'checkBoxNo',
	checkbox : true
}, {
	field : 'id.dictionaryDetailId',
	title : '字典明细ID',
	align : 'center',
	sortable : true,
	width : 200,
	formatter : function(value, rec) {
		if (rec.id == null) {
			return null;
		}
		return rec.id.dictionaryDetailId;
	}
}, {
	field : 'dictionaryId',
	title : '字典ID',
	align : 'center',
	sortable : true,
	width : 200
}, {
	field : 'serialNo',
	title : '序号',
	align : 'center',
	sortable : true,
	width : 30
}, {
	field : 'targetName',
	title : '操作目标',
	align : 'center',
	editor : 'text',
	required : true,
	width : 180
}, {
	field : 'targetField',
	title : '操作域',
	align : 'center',
	width : 150,
	editor : 'text',
	required : true
}, {
	field : 'insertTimeForHis',
	title : '创建时间',
	align : 'center',
	sortable : true,
	width : 130
}, {
	field : 'creatorCode',
	title : '创建人代码',
	align : 'center',
	sortable : true,
	width : 80
}, {
	field : 'operateTimeForHis',
	title : '修改时间',
	align : 'center',
	sortable : true,
	width : 130
}, {
	field : 'updaterCode',
	title : '修改人代码',
	align : 'center',
	sortable : true,
	width : 80
}, {
	field : 'comCode',
	title : '机构代码',
	align : 'center',
	sortable : true,
	width : 80
}, {
	field : 'validStatus',
	title : '有效状态',
	align : 'center',
	sortable : true,
	width : 115,
	editor : {
		type : 'combobox',
		options : {
			valueField : 'id',
			textField : 'text',
			data : [ {
				"id" : "1",
				"text" : "有效"
			}, {
				"id" : "0",
				"text" : "无效"
			} ],
			required : true
		}
	},
	formatter : function(value, rec) {
		if (value == "1") {
			return "有效";
		} else if (value == "0") {
			return "无效";
		} else {
			return "未知状态";
		}
	}
}

] ];

/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#' + queryResultTable).datagrid({
		url : send_url,
		nowrap : true,
		height : 400,
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

$(function() {
//	executeQuery();
	
});