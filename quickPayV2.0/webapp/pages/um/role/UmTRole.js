var query_action = contextRootPath + "/um/umtrole/query.do";
var queryResultTable = "UmTRoleTable";
var changedComArray = new Array();


function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg,
		text:''
	});
}

var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
},{
	text : '同步Cognos角色',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		addCognosRole();
	}
},{
	text:'添加角色与数据权限关联',
	align:'right',
	iconCls:'icon-edit',
	handler:function(){
		modifyRolePower();
	}
},
{
    text:'报表配置',
    align:'right',
    iconCls:'icon-edit',
    handler:function(){
    	getCognosMenu();
}}];
var page_contentColumnHeaders = [ [ {
	field : 'checkBoxNo',
	checkbox : true
}, {
	field : 'roleId',
	title : '角色ID',
	align : 'center',
	hidden: true,
	sortable : true
}, {
	field : 'roleCode',
	title : '角色代码',
	align : 'center',
	sortable : true
}, {
	field : 'roleCName',
	title : '角色名称',
	align : 'center',
	sortable : true
}, {
	field : 'userType',
	title : '用户类型',
	align : 'center',
	sortable : true,
	formatter : function(value,row,index){
		if(value == "01"){
			return "内部";
		}else{
			return "外部";
		}
	}
}, {
	field : 'validStatus',
	title : '有效状态',
	align : 'center',
	sortable : true,
	formatter : function(value,row,index){
		if(value == "1"){
			return "有效";
		}else{
			return "无效";
		}
	}
}, {
	field : 'remark',
	title : '备注',
	align : 'center',
	sortable : true
}, {
	field : 'flag',
	title : '标识字段',
	align : 'center',
	sortable : true
}, {
	field : 'creatorCode',
	title : '创建人',
	align : 'center',
	sortable : true
}, {
	field : 'insertTimeForHis',
	title : '创建时间',
	align : 'center',
	sortable : true
}, {
	field : 'updaterCode',
	title : '修改人',
	align : 'center',
	sortable : true
}, {
	field : 'operateTimeForHis',
	title : '修改时间',
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
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		singleSelect : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}
	});
}

/* 机构角色的配置 */
function prepareReset() {
	var checkNodes = $("#rc").tree("getChecked");
	if(checkNodes.length!=1){ alert('请选择一个的角色'); return; }
	if(checkNodes[0].attributes.nodeType!=ROLE_NODE){ alert('请选择一个的角色'); return; }
	$('#ifr_l').attr('src',contextRootPath+'/um/umtrole/prepareUpdate.do?'+checkNodes[0].id);
}

/* 修改 */
function prepareUpdate() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		window.parent.window.$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/um/umtrole/prepareUpdate.do?id.roleId="+rows[0].id.roleId;
	editRecord(url);
}

/* 新增 */
function prepareAdd() {
//	$('#ifr_l').attr('src',contextRootPath+'/um/umtrole/prepareAdd.do');
	editRecord(contextRootPath+'/um/umtrole/prepareAdd.do');
}

/* 查看 */
function view(params) {
	var url = contextRootPath + "/um/umtrole/view.do?" + params;
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
			var url = contextRootPath + "/um/umtrole/delete.do?"
					+ getUrlByJson(rows[0]);
			$.ajax({
				type : "POST",
				url : url,
				success: function(result){
					  $.messager.alert('提示信息','记录删除成功！ ','info');
					  $('#'+queryResultTable).datagrid('reload');
					   },
					error:function(result){
					   alertErrorMsgForEasyUi(result);
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

/**
 * 执行保存
 * @returns {Boolean}
 */
function executeSave() {
	if (!$('#fm').form('validate')) {
		return false;
	} else {
		$.messager.confirm('提示', '是否确认保存?', function(r) {
			if (r) {
				if ($('#opreateType').val() == 'update') {
					var roleId = $('#roleId').val();
					var formData = $("#fm").serialize();
					
					//机构树
					var comData='';
					var comLen = changedComArray.length;
					for(var i=0;i<comLen;i++){
						comData+="&roleComList["+i+"].comCode="+changedComArray[i].id+"&roleComList["+i+"].comName="+changedComArray[i].text+"&roleComList["+i+"].roleId="+roleId+"&roleComList["+i+"].validStatus="+changedComArray[i].validStatus;
					}
//					alert(comData);
					
					//功能树
					var taskData='';
					var taskLen = changedTaskArray.length;
					for(var i=0;i<taskLen;i++){
						taskData+="&roleTaskList["+i+"].taskID="+changedTaskArray[i].id+"&roleTaskList["+i+"].roleId="+roleId+"&roleTaskList["+i+"].validStatus="+changedTaskArray[i].validStatus;
					}
					
					
//					alert(formData+comData+taskData);
					
					$.ajax({
						type:"POST",
						url:contextRootPath + "/um/umtrole/update.do",
						data:formData+comData+taskData,
						beforeSend:function(){
							showProcess(true, '温馨提示', '正在保存角色信息...请稍候...');
						},
						success:function(data,text){
							showProcess(false);
							if(data == 'success'){
								$.ajax({
									url:contextRootPath + "/cache/reloadRoleTaskCache.do",
									beforeSend:function(){
										showProcess(true, '温馨提示', '正在更新角色功能缓存数据...请稍候...');
									},
									success:function(data,text){
										showProcess(false);
										if(JSON.parse(data).success==true){
											$.ajax({
												url:contextRootPath + "/um/umtrole/synCache.do",
												beforeSend:function(){
													showProcess(true, '温馨提示', '正在更新角色扩展信息...请稍候...');
												},
												success:function(data,text){
													showProcess(false);
													if(data=="success"){
														$.messager.alert("提示信息","角色信息更新成功","info",function(){
															window.opener='';
															window.close();								//关闭页面
														});
													}else {
														$.messager.alert("提示信息",data,"info");
													}
												}
											});
										}
									}
								});
							}else{
								$.messager.alert("提示信息",data,"info");
							}
							
						}
					});
				} else if ($('#opreateType').val() == 'add') {
					var formData = $("#fm").serialize();
					
					//机构树
					var comChecked = $('#comTree').tree('getCheckedExt');
					var len = comChecked.length;
					var comData = "";
					for(var i=0;i<len;i++){
						comData+="&comCodes="+comChecked[i].id+"&comNames="+comChecked[i].text;
					}
					//功能树
					var taskChecked = $('#taskTree').tree('getCheckedExt');
					var l = taskChecked.length;
					var taskData = "";
					for(var j=0;j<l;j++){
						taskData+="&taskIds="+taskChecked[j].id+"&taskNames="+taskChecked[j].text;
					}
					$.ajax({
						type:"POST",
						url:contextRootPath + "/um/umtrole/add.do",
						data:formData + comData + taskData,
						beforeSend:function(){
							showProcess(true, '温馨提示', '正在更新角色信息...请稍候...');
						},
						success:function(data,text) {
							showProcess(false);
							if(data=='success'){
								//更新角色功能信息
								$.ajax({
									url:contextRootPath + "/cache/reloadRoleTaskCache.do",
									beforeSend:function(){
										showProcess(true, '温馨提示', '正在更新角色功能缓存数据...请稍候...');
									},
									success:function(data,text){
										showProcess(false);
										if(JSON.parse(data).success==true){
											$.ajax({
												url:contextRootPath + "/um/umtrole/synCache.do",
												beforeSend:function(){
													showProcess(true, '温馨提示', '正在更新角色扩展信息...请稍候...');
												},
												success:function(data,text){
													showProcess(false);
													if(data=="success"){
														$.messager.alert("提示信息","角色信息更新成功","info",function(){
															window.opener='';
															window.close();								//关闭页面
														});
													}else {
														$.messager.alert("提示信息",data,"info");
													}
												}
											});
										}
									}
								});
//								location = contextRootPath+"/pages/Success.jsp";
							}else{
								$.messager.alert("提示信息",data,"info");
							}
							
						}
					});
				} else {
					return false;
				}
			}
		});
	}
}

/**
 * 维护角色信息与数据权限之间的关联关系
 */
function modifyRolePower() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	editRecord(contextRootPath+'/um/umtrolepower/prepareAdd.do?umTRolePower.roleId='+rows[0].id.roleId);
}



/**
 * 添加至Cognos角色
 */
function addCognosRole(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/um/cognos/addCognosRole.do?roleId="+rows[0].id.roleId;
	$.ajax({
		type:"POST",
		url:url,
		beforeSend:function(){
			showProcess(true,"温馨提示","正在与Cognos报表环境同步角色...请稍候...");
		},
		success:function(msg){
			showProcess(false);
			if(msg == 'success'){
				$.messager.alert('提示消息', "添加Cognos角色成功！", 'info');
			}else{
				$.messager.alert('提示消息', msg, 'info');
			}
		}
	});
}

/**
 * 获取Cognos报表菜单并以树显示
 */
var changedArray = new Array();
var addArray = new Array();
var delArray = new Array();
function getCognosMenu(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/um/cognos/findAllMenu.do?roleId="+rows[0].id.roleId;
	$.ajax({
		type:"POST",
		url:url,
		dataType:"json",
		beforeSend:function(){
			showProcess(true, '温馨提示', '正在加载菜单信息...加载时间较长...请稍候...');
		},
		success:function(msg){
			showProcess(false);
			if(msg.status == 'success'){
				changedArray = new Array();
				$('#win').window('open');
				//var cognosMenu = eval('(' + msg.content + ')');
				$('#cmenu').tree({
					data:eval(msg.content),
					cascadeCheck:false,
					checkbox:true,
					animate:true,
					onCheck : function(node,check){
						if(check == node.attributes.initChecked){
							//如果状态没有改变，将当前从数据中删除
							var changedArrayLen = changedArray.length;
							for(var i=0;i<changedArrayLen;i++){
								if(changedArray[i].id == node.id){
									changedArray.splice(i,1);
									return;
								}
							}
						}else{
							var obj = new Object();
							if(node.attributes.initChecked == false){
								//表示选中增加菜单
								obj.id = node.id;
								obj.value = node.attributes.searchPath;
								obj.status = 'add';
							}else{
								//表示取消删除菜单
								obj.id = node.id;
								obj.value = node.attributes.searchPath;
								obj.status = 'del';
							}
							changedArray.push(obj);
						}
					}
				});
				$('#curRoleId').val(rows[0].id.roleId);
				$('#curRoleName').val(rows[0].roleCName);
			}else{
				$.messager.alert("提示信息",msg.content,"info");
			}
		}
	
	});
}

/**
 * 给Cognos角色加菜单
 */
function addMenuToCognosRole(){
	var param = $('#of').serialize();
	var addSearchPath = '';
	var delSearchPath = '';
	for(var i=0;i<changedArray.length;i++){
		if(changedArray[i].status == 'add'){
			addSearchPath += '&addSearchPath='+changedArray[i].value;
		}else if(changedArray[i].status == 'del'){
			delSearchPath += '&delSearchPath='+changedArray[i].value;
		}
	}
	var url = contextRootPath+"/um/cognos/addMenuToCognosRole.do?"+param+encodeURI(addSearchPath)+encodeURI(delSearchPath);
	$.ajax({
		type:"POST",
		url:url,
		dataType:"json",
		beforeSend:function(){
			showProcess(true, '温馨提示', '正在保存Cognos菜单信息...请稍候...');
		},
		success:function(msg){
			showProcess(false);
			$.messager.alert('提示信息',msg.content,'info');
			if(msg.status == 'success'){
				$('#win').window('close');
			}
		}
	});
}

