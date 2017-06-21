var query_action = contextRootPath + "/um/umtsauuser/queryMyTeam.do";
var queryResultTable = "UmTSauuserTable";
var page_toolBar = [ {
	text : '查看',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'userCode',
			title : '人员代码',
			align : 'center',
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.userCode + '</a>';
			}
		},
		
		{
			field : 'userName',
			title : '用户姓名',
			align : 'center'
		}
			,	
		{
			field : 'userType',
			title : '人员属性',
			align : 'center',
			formatter : function(value, row, index) {
				if(value=='A0'){
					return '合同员工';
				}else if(value=='B0'){
					return '派遣员工';
				}else if(value=='C0'){
					return '城市营销员(非三农)';
				}else if(value=='C1'){
					return '城市营销员(三农)';
				}else if(value=='D0'){
					return '农村营销员(非三农)';
				}else if(value=='D1'){
					return '农村营销员(三农)';
				}else if(value=='E0'){
					return '重庆代办人员(非三农)';
				}else if(value=='E1'){
					return '重庆代办人员(非三农)';
				}
			}
		}
			,	
		{
			field : 'identifyNumber',
			title : '身份证号',
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
			field : 'comCode',
			title : '归属机构',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStatus',
			title : '有效状态',
			align : 'center',
			sortable : true,
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
			field : 'flag',
			title : '标志',
			align : 'center',
			sortable : true
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
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}
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
	var url = contextRootPath+"/um/umtsauuser/view.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtsauuser/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtsauuser/view.do?" + params;
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
			var url = contextRootPath+"/um/umtsauuser/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
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
				 	if($('#opreateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtsauuser/update.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtsauuser/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}