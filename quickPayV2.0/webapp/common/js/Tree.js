//add by shenyichan 2013-08-07

var COMPANY_NODE = "1"; // 树的节点类型,机构节点
var ROLE_NODE = "2"; // 树的节点类型,角色节点
var lastNode = null;
var changedComArray = new Array(); // 被改变的机构节点
var changedTaskArray = new Array(); // 被改变的功能节点
var count = 1;
function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title : title,
		msg : msg
	});
}

/* 画树函数 */
function createTree(dom, treeType, checkedStr, isCheckBox,serverCode) {
	//alert(serverCode);
	$("#" + dom)
			.tree(
					{
						animate : true,
						cascadeCheck : false,
						checkbox : isCheckBox,
						url : contextRootPath
								+ '/tree/query.do?queryType=0&treeType='
								+ treeType + '&serverCode=' + serverCode + checkedStr,
						onBeforeExpand : function(node, param) {
							//alert(node.checked);
							var url = contextRootPath
									+ '/tree/query.do?queryType=1&treeType='
									+ treeType + '&serverCode=' + serverCode + '&nodeId=' + node.id
									+ '&nodeChecked=' + node.checked
									+ checkedStr;
							$("#" + dom).tree('options').url = url;
						},
						onSelect:function(node, param){
							if(treeType==15){
								var send_url = contextRootPath + "/wwadmin/wxmpmenu/query.do?id.menuId="+node.id;
								
								$('#wxMenuTreeDetail').datagrid({
									url : send_url,
									remoteSort : false,
									pageNumber : 1,
									gination : true,
									columns : page_contentColumnHeaders,
									toolbar : page_toolBar
								});
								//alert(send_url);
								//window.showModalDialog(send_url,window,"resizable:yes;dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:600px;dialogHeight:500px");
							}
						},
						onLoadSuccess : function(node, data) {
							//inti=$("#" + dom).tree('getCheckedExt').length;
							//flag=true;
							//alert('success');
							showProcess(false);
							if (treeType == 10) {
								var nodes = $(this).find("span.tree-checkbox");
								for ( var i = 0; i < nodes.length; i++) {
									for ( var j = 0; j < data.length; j++) {
										var nodeid = $(nodes[i]).parent().attr(
												"node-id");

										if (nodeid == data[j].id
												&& data[j].attributes.initChecked == true) {

											nodes[i].style.visibility = "hidden";
										}

									}
								}
							}
							
							if (node !== null && node.checked == true) {
								$(this).tree('check', node.target);
							}
							
						},
						
					  onCheck : function(node, check) { 
						  $(this).tree('options').cascadeCheck = false;
						  //alert(node.hasChildren);
						  //alert(node.attributes.subTaskType+"任务类型:"+node.attributes.taskType);
						  if(node.attributes.taskType == 'menu' && node.attributes.subTaskType == "menu"){
							  $(this).tree('expand',node.target);
						  }
						  if(node.attributes.userType == '01'){
							  if(node.attributes.subTaskType == "button"){
								  $(this).tree('options').cascadeCheck = true;
							  }else{
								  $(this).tree('options').cascadeCheck = false;
							  }
							  
						  }
						  else{
							  if(node.attributes.subTaskType == "button" && node.attributes.serverCode == "PUB"){
								  $(this).tree('options').cascadeCheck = true;
							  }else{
								  $(this).tree('options').cascadeCheck = false;
							  }
						  }
						  
						  //$(this).tree('options').cascadeCheck = false;
						  
						  if (check == node.attributes.initChecked) {
								// 如果状态没有改变，将当前从数据中删除
								// alert("状态没有改变，将当前从数据中删除");

								if (treeType == 0) { // 当前是机构树
									var len = changedComArray.length;
									for ( var i = 0; i < len; i++) {
										if (changedComArray[i].id == node.id) {
											// alert("机构删除");
											changedComArray.splice(i, 1);
											break;
										}
									}

								} else if (treeType == 4) { // 当前是功能树

									var len = changedTaskArray.length;
									for ( var i = 0; i < len; i++) {
										if (changedTaskArray[i].id == node.id) {
											// alert("功能删除");
											changedTaskArray.splice(i, 1);
											break;
										}
									}
								} else {
									return;
								}
							} else {
								var valid;
								if (node.attributes.initChecked == false) {
									// 操作为增加
									// alert("操作为增加");
									valid = '1';
								} else {
									// 操作为删除
									// alert("操作为删除");
									valid = '0';
								}

								var obj = new Object();
								// alert('haha');
								obj.id = node.id;
								obj.text = node.text;
								obj.validStatus = valid;
								if (treeType == 0) { // 当前是机构树
									changedComArray.push(obj);
								} else if (treeType == 4) { // 当前是功能树
									changedTaskArray.push(obj);
								} else {
									return;
								}
							}

							 //alert("被改变的机构："+changedComArray.length);
							 //alert("被改变的功能："+changedTaskArray.length);

						
					 }
					  
					  
					});
};

var page_contentColumnHeaders = [ [
                           		{
                           			field : 'checkBoxNo',
                           			checkbox : true
                           		},
                           		{
                           			field : 'menuId',
                           			title : '主键',
                           			align : 'center',
                           			sortable : true,
                           			formatter : function(value, row, index) {
                           				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
                           						+ getUrlByJson(row) + '\')">' + row.id.menuId + '</a>';
                           			}
                           		},
                           		{
                           			field : 'parentId',
                           			title : '父id',
                           			align : 'center',
                           			sortable : true
                           		}
                           		
                           			,	
                           		{
                           			field : 'merId',
                           			title : '企业编号',
                           			align : 'center',
                           			sortable : true
                           		}
                           			
                           			,	
                           		{
                           			field : 'menuType',
                           			title : '菜单类别：click，view',
                           			align : 'center',
                           			sortable : true
                           		}
                           			,	
                           		{
                           			field : 'menuName ',
                           			title : '菜单名字',
                           			align : 'center',
                           			sortable : true
                           		}
                           			
                           			,	
                           		{
                           			field : 'menuKey',
                           			title : '菜单key值',
                           			align : 'center',
                           			sortable : true
                           		}
                           			,	
                           		{
                           			field : 'menuUrl',
                           			title : '网页链接',
                           			align : 'center',
                           			sortable : true
                           		}
                           			,	
                           		{
                           			field : 'menuLevel',
                           			title : '菜单级别，最多为两级菜单',
                           			align : 'center',
                           			sortable : true
                           		}
                           			,	
                           		{
                           			field : 'orderNum',
                           			title : '菜单排序，默认为1',
                           			align : 'center',
                           			sortable : true
                           		}
                    
                           				
                           		] ];
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

/* 修改 */
function prepareUpdate() {
	var rows = $('#wxMenuTreeDetail').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/wwadmin/wxmpmenu/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/wwadmin/wxmpmenu/prepareAdd.do');
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
			var url = contextRootPath+"/wwadmin/wxmpmenu/delete.do?" + getUrlByJson(rows[0]);
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
