var query_action = contextRootPath + "/qp/qptcomhandlepolice/query.do";
var queryResultTable = "qpTComHandlePoliceTable";
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
		field : 'checkBoxNo',
		checkbox : true
	},
	{
		field : 'handlePoliceId',
		title : '主键ID',
		align : 'center',
		sortable : true,
		hidden : true
	},
	{
		field : 'handlePoliceNO',
		title : '处理民警编号',
		align : 'center',
		sortable : true
	},
	{
		field : 'handlePoliceName',
		title : '处理民警姓名',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'handlePolicePhone',
		title : '处理民警电话',
		align : 'center',
		sortable : true
	}  ,	
	{
		field : 'centerName',
		title : '快赔中心',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'organization',
		title : '所在机构',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'createCode',
		title : '创建人代码',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'createTime',
		title : '创建时间',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'updaterCode',
		title : '修改人代码',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'updaterTime',
		title : '修改时间',
		align : 'center',
		sortable : true
	}
	,	
	{
		field : 'validStatus',
		title : '有效状态',
		align : 'center',
		sortable : true,
		formatter : function(value,row,index){
			if(value=='1'){
				return '有效';
			}else{
				return '无效';
			}
		}
	}

	] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	console.log("url:"+send_url);
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
	var url = contextRootPath+"/qp/qptcomhandlepolice/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qptcomhandlepolice/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qptcomhandlepolice/view.do?" + params;
	editRecord(url);
}


/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	console.log("rows[0]:"+rows[0]);
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
			var url = contextRootPath+"/qp/qptcomhandlepolice/delete.do?" + getUrlByJson(rows[0]);
			$.ajax({
				type: "POST",
				url: url,
				success: function(result){
					$.messager.alert('提示信息','记录删除成功！	','info');
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
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}

function executeSave(){
	if(!voidSaveParam()){
		return false;
	}
	var centerName = $('#centerId').combobox('getText');
	console.log("centerName:"+centerName);
	$("#centerName").val(centerName);
	if(!$('#fm').form('validate')){
		return false; 
	}else{
		$.messager.confirm('提示', '是否确认保存?', function(r){
			var formData = $("#fm").serialize();
			if (r){
				if($('#operateType').val()=='update'){
					$("#fm").attr("action",contextRootPath+"/qp/qptcomhandlepolice/update.do");
					$("#fm").submit();
				}else if($('#operateType').val()=='add'){
					var url = contextRootPath+"/qp/qptcomhandlepolice/voildAddParam.do";
					$.ajax({
						type: "POST",
						url: url,
						data:formData,
						success: function(result){
							var json = JSON.parse(result);
							if(json.errorCode=='0'){
								$("#fm").attr("action",contextRootPath+"/qp/qptcomhandlepolice/add.do");
								$("#fm").submit();
							}else{
								alert(json.errorMsg);
							}
						},
						error:function(result){
							alertErrorMsgForEasyUi(result);
						}
					});
				}else{
					return false;
				}				 	
			}
		});
	}	   
}

function voidSaveParam(){
	var handlePolicePhone = $("#handlePolicePhone").val();
	var handlePoliceNO = $("#handlePoliceNO").val();
	var handlePoliceName = $("#handlePoliceName").val();
	var centerId = $('#centerId').combobox('getValue');
	if(!handlePolicePhone){
		alert("请填写手机号码");
		return false;
	}
	if(!handlePoliceNO){
		alert("请填写处理民警编号");
		return false;
	}
	if(!handlePoliceName){
		alert("请填写处理民警姓名");
		return false;
	}
	if(!centerId){
		alert("请选择快赔中心");
		return false;
	}
	return true;
}