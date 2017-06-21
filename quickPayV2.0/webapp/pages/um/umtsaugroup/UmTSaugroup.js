var query_action = contextRootPath + "/um/umtsaugroup/query.do";
var queryResultTable = "UmTSaugroupTable";
var page_toolBar = [ {
	text : '修改',
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
			field : 'teamID',
			title : '团队ID',
			align : 'center',
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.teamID + '</a>';
			}
		},
		{
			field : 'upperTeamID',
			title : '上级团队ID',
			align : 'center'
		}
			,	
		{
			field : 'teamCode',
			title : '团队编码',
			align : 'center'
			
		}
			,	
		{
			field : 'teamName',
			title : '团队名称',
			align : 'center'
			
		}
			,	
		{
			field : 'teamType',
			title : '团队类型',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value=='10100'){
					return '直销';
				}else if(value=='10201'){
					return '车商';
				}else if(value=='10202'){
					return '银保';
				}else if(value=='10203'){
					return '专业代理';
				}else if(value=='10204'){
					return '经纪';
				}else if(value=='10205'){
					return '交叉销售';
				}else if(value=='10206'){
					return '其他兼业代理';
				}else if(value=='10300'){
					return '营销';
				}
			}
		}
			,	
		{
			field : 'branchType',
			title : '团队外部类型',
			align : 'center'
			
		}
			,	
		{
			field : 'managerCode',
			title : '团队经理工号',
			align : 'center'
		}
			,	
		{
			field : 'managerName',
			title : '团队经理名称',
			align : 'center'
		}
			,	
		{
			field : 'comCode',
			title : '归属机构',
			align : 'center'
		}
			,	
		{
			field : 'branchPhone',
			title : '团队联系方式',
			align : 'center'
		}
			,	
		{
			field : 'createDate',
			title : '网点创建时间',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if(value){
					//IE不能转"2009-09-09"的格式
					var time = new Date(value.replace(/-/g,'/'));
		            return time.toLocaleDateString();
					/*return value.subString(0,10);*/
				}
				return "";
			}
		}
			,	
		{
			field : 'expireDate',
			title : '网点失效日期',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'checkDate',
			title : '审核状态',
			align : 'center'
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
			align : 'center'
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
	var url = contextRootPath+"/um/umtsaugroup/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtsaugroup/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtsaugroup/view.do?" + params;
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
			var url = contextRootPath+"/um/umtsaugroup/delete.do?" + getUrlByJson(rows[0]);
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
				 		$("#fm").attr("action",contextRootPath+"/um/umtsaugroup/update.do");
				 		$("#fm").submit();
				 	}else if($('#opreateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/um/umtsaugroup/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}