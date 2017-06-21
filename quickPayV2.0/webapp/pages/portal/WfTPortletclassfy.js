var query_action = contextRootPath + "/portal/query.do";
var queryResultTable = "WfTPortletclassfyTable";
var page_toolBar = [ {
    text: '增 加',
    align: 'right',
    iconCls: 'icon-add',
    handler: function() {
        prepareAdd();
    }
}, {
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
			field : 'portletid',
			title : '模块代码',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.portletid + '</a>';
			}
		},
		{
			field : 'portletname',
			title : '模块名称',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'actionurl',
			title : '模块地址',
			align : 'center',
			sortable : true
		}
			,	
//		{
//			field : 'isroll',
//			title : '是否显示滚动条',
//			align : 'center',
//			sortable : true,
//			formatter : function(value,row,index){
//				if(value == "1"){
//					return "显示";
//				}else if(value == "0"){
//					return "不显示";
//				}
//			}
//		}
//			,	
//		{
//			field : 'porletheight',
//			title : '模块高度',
//			align : 'center',
//			sortable : true
//		}
//			,	
//		{
//			field : 'porletwidth',
//			title : '模块宽度',
//			align : 'center',
//			sortable : true
//		}
//			,	
		{
			field : 'comcode',
			title : '机构代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'inserttimeforhis',
			title : '创建时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operatetimeforhis',
			title : '更新时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validstatus',
			title : '是否有效',
			align : 'center',
			sortable : true,
			formatter : function(value,row,index){
				if(value == "1"){
					return "有效";
				}else{
					return "无效";
				}
			}
		}
				
		] ];


/* 查询 */
function executeQuery() {
	lock("portalQuery");
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
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
	setTimeout("dislock(\"portalQuery\")",2000)
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
	var url = contextRootPath+"/portal/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/portal/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/portal/view.do?" + params;
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
			var url = contextRootPath+"/portal/delete.do?" + getUrlByJson(rows[0]);
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
	    }
		if($('#opreateType').val()=='add'){
			if($('#wfTPortletclassfy\\.validstatus').combobox('getValue') == null || $('#wfTPortletclassfy\\.validstatus').combobox('getValue') == ""){
				alert("请选择有效性！");
				return false;
			}
		}
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#opreateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/portal/update.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/portal/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});  
	}
	
	/*为防止重复提交，按钮被点击后置为无效，时间到后解锁*/
	function lock(name){ 
		document.getElementById(name).disabled = true;
		//alert(name);
	} 
	function dislock(name){ 
		document.getElementById(name).disabled = false;
		//alert(name);
	} 