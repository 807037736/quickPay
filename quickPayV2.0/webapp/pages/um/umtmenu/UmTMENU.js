var query_action = contextRootPath + "/um/umtmenu/query.do";
var queryResultTable = "UmTMENUTable";
var page_toolBar =[{
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
			field : 'menuId',
			title : '菜单ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.menuId + '</a>';
			}
		},
		{
			field : 'upperMenuId',
			title : '上级菜单ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'taskId',
			title : '功能ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'level',
			title : '菜单层级',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'scope',
			title : '适用范围',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'systemCode',
			title : '系统代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'menuName',
			title : '菜单名称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'actionUrl',
			title : '菜单URL',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'displayNo',
			title : '显示序号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'taskCode',
			title : '功能代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStauts',
			title : '有效状态',
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
			field : 'flag',
			title : '标志字段',
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
	var url = contextRootPath+"/um/umtmenu/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtmenu/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtmenu/view.do?" + params;
	editRecord(url);
}

/* 删除 */
/*function executeDelete() {
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
			var url = contextRootPath+"/um/umtmenu/delete.do?" + getUrlByJson(rows[0]);
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
}*/

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
				 		$("#fm").attr("action",contextRootPath+"/um/umtmenu/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtmenu/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
	
	//创建菜单树
	function creatTree(menu){
		var menuArray= eval('(' + menu + ')');
		$('#tt').tree(
				{
					data:menuArray,
					checkbox:false,
					animate:true
				});
	}
	
	
	function createCognosMenuTree(cognosMenu){
		var menuArray= eval('(' + cognosMenu + ')');
		$('#cognosMenu').tree(
				{
					data:menuArray,
					checkbox:false,
					animate:true
				});
	}
	//获取被点击菜单树种的节点id
	function getCheckdeNodesId(nodes){
		var s = new Array();
		for(var i=0; i<nodes.length; i++){
			s[i] = nodes[i].id;
		}
		return s;
	}