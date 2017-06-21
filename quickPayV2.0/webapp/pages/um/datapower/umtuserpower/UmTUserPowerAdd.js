var query_action = contextRootPath + "/um/umtuserpower/query.do";
var queryResultTable = "UmTUserPowerAddTable";

/** 停止更新* */
function endEdit() {
	var rows = $("#" + queryResultTable).datagrid('getRows');
	for ( var i = 0; i < rows.length; i++) {
		$("#" + queryResultTable).datagrid('endEdit', i);
	}
}

var page_toolBar = [ {
	text : '添加用户数据权限',
	align : 'right',
	iconCls : 'icon-add',
	handler : function() {
		$("#" + queryResultTable).datagrid('appendRow', {'operatorType':'1','validStatus':'1'});
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
									+ "/um/umtuserpower/rowEdit.do?userCode="
									+ document
											.getElementById("umTUser.id.userCode").value,
							effectRow, function(rsp) {
								if (rsp.status) {
									$.messager.alert("提示", "操作成功!");
									$("#" + queryResultTable).datagrid("reload");
								}
							}, "JSON").error(function() {
						$.messager.alert("提示", "提交错误了!");
					});

		}
	}
},
{
	text : '修改用户数据权限',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		var row = $("#" + queryResultTable).datagrid('getSelected');
		if (row) {
			var rowIndex = $("#" + queryResultTable).datagrid(
					'getRowIndex', row);
			$("#" + queryResultTable).datagrid('beginEdit', rowIndex);
		}
	}
}, {
	text : '删除用户数据对象',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		var row = $("#" + queryResultTable).datagrid('getSelected');
		if (row) {
			var rowIndex = $("#" + queryResultTable).datagrid('getRowIndex', row);
			$("#" + queryResultTable).datagrid('deleteRow', rowIndex);
		}
	}
}  ];

var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'userPowerId',
			title : '用户权限ID',
			align : 'center',
			sortable : true,
			formatter : function(value, rec) {
				if (rec.id == null) {
					return null;
				}
				return rec.id.userPowerId;
			},
			width:200
		},
		{
			field : 'operatorType',
			title : '操作者类型',
			align : 'center',
			sortable : true,
			width:100,
			formatter:function(value,rec){
				if(value=="1"){
					return "用户";
				}else if(value=="2"){
					return "岗位";
				}
			}
		},
		{
			field : 'dictionaryId',
			title : '字典ID',
			align : 'center',
			sortable : true,
			id:'dictionaryId',
			editor : {
				type : 'combobox',
				options : {
					valueField : 'id',
					textField : 'text',
					url:'../umtdictionary/comboValidQuery.do?umTDictionary.validStatus=1',
					required : true
				}
			},
			width:200
		},
		{
			field : 'operationSymbol',
			title : '操作符',
			align : 'center',
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
					valueField : 'id',
					textField : 'text',
					data : [ {
						"id" : ">",
						"text" : "大于(>)"
					}, {
						"id" : "<",
						"text" : "小于(<)"
					},{
						"id" : "=",
						"text" : "等于(=)"
					},{
						"id" : "<",
						"text" : "小于(<)"
					},{
						"id" : "<",
						"text" : "小于(<)"
					} ],
					required : true
				}
			},
			width:100
		}
			,	
		{
			field : 'powerValue',
			title : '限制域值',
			align : 'center',
			sortable : true,
			editor:'text',
			width:135
		}
			,	
		{
			field : 'creatorCode',
			title : '创建人代码',
			align : 'center',
			sortable : true,
			width:90
		}
			,	
		{
			field : 'insertTimeForHis',
			title : '创建时间',
			align : 'center',
			sortable : true,
			width:130
		}
			,	
		{
			field : 'updaterCode',
			title : '修改人代码',
			align : 'center',
			sortable : true,
			width:90
		}
			,	
		{
			field : 'operateTimeForHis',
			title : '修改时间',
			align : 'center',
			sortable : true,
			width:130
		},{
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

$(function(){
	/**初始化字典信息**/
//	$.post("../umtdictionary/query.do?umTDictionary.validStatus=1",{},function(result){
//		result = $.parseJSON(result);
////		dictionaryStore = eval("("+dictionaryStore+")");
//		var rows = result.rows;
//		dictionaryStore = new Array();
//		var index = 0;
//		$.each(rows,function(index,item){
//			dictionaryStore[index] = "{\"id\":\""+item.id.dictionaryId+"\",\"text\":\""+item.ditionaryName+"\"}";
//			index++;
//		});
//		alert("["+dictionaryStore+"]");
//		$("#dictionaryId").combobox("reload","["+dictionaryStore+"]");
//		executeQuery();					//初始化表格项
//	});
//	executeQuery();					//初始化表格项
	/**根据查询的用户代码返回配置给该用户的数据权限列表**/
//	$.post(query_action,{
//		//传入查询的用户代码
//		"umTUser.id.userCode":document.getElementById("umTUser.id.userCode").value
//	},function(result){
//		var userPowerValue = $.parseJSON(result);					//将返回的结果进行JSON转化处理
//		if(userPowerValue==null||userPowerValue.totalCount<1){
//			$.messager.alert("用户数据权限","没有给用户"+document.getElementById("umTUser.id.userCode").value+"配置数据权限");
//		}else {
//			$.each(userPower.rows,function(index,item){
//				//对于查询出来的用户权限数据进行渲染处理
//				
//			});
//		}
//	});
});