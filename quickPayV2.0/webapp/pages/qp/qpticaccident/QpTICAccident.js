var query_action = contextRootPath + "/qp/qpticaccident/query.do";
var queryResultTable = "QpTICAccidentTable";
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
			field : 'acciId',
			title : '事故ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.acciId + '</a>';
			}
		},
		{
			field : 'acciTime',
			title : '发生时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciWeather',
			title : '天气情况',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciProvince',
			title : '省份',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciCity',
			title : '城市',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciDistrict',
			title : '区县',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciRoad',
			title : '道路',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciStreet',
			title : '街道',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverIDType',
			title : '证件类型',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverIDNumber',
			title : '证件号',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverName',
			title : '姓名',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverSex',
			title : '性别',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverPhone',
			title : '固定电话',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverMobile',
			title : '手机号码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverAddress',
			title : '当前住址',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverVehicleType',
			title : '车辆类型',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverVehicleNumber',
			title : '车牌号码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverInsuNumber',
			title : '保险凭证',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverDirection',
			title : '行驶方向',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverIp',
			title : 'DriverIp',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'liveChannelId',
			title : 'LiveChannelId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'liveStatus',
			title : 'LiveStatus',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'liveSavedChannels',
			title : 'LiveSavedChannels',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'liveDesc',
			title : 'LiveDesc',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCLoginId',
			title : 'CCLoginId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCUserName',
			title : 'CCUserName',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCEmployeeId',
			title : 'CCUserName',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCHandleStatus',
			title : 'CCUserName',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCHandleTime',
			title : 'CCHandleTime',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cCHandleNotes',
			title : 'CCHandleNotes',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'infoSource',
			title : '信息来源',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'caseId',
			title : '从属案件ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverLawId',
			title : '违反法律法规',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverLiability',
			title : '需承担的责任',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'driverLoss',
			title : '损失',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCLoginId',
			title : 'ICLoginId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCUserName',
			title : 'ICUserName',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCEmployeeId',
			title : 'ICEmployeeId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCHandleStauts',
			title : 'ICHandleStauts',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCHandleTime',
			title : 'ICHandleTime',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'iCHandleNotes',
			title : 'ICHandleNotes',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'coId',
			title : '所属保险公司ID',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPRemoveStatus',
			title : 'TPRemoveStatus',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciLng',
			title : 'AcciLng',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciLat',
			title : 'AcciLat',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaAVehicleNumber',
			title : 'RelaAVehicleNumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaACoId',
			title : 'RelaACoId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaBVehicleNumber',
			title : 'RelaBVehicleNumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaBCoId',
			title : 'RelaBCoId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaCVehicleNumber',
			title : 'RelaCVehicleNumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaCCoId',
			title : 'RelaCCoId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaDVehicleNumber',
			title : 'RelaDVehicleNumber',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'relaDCoId',
			title : 'RelaDCoId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'lockStatus',
			title : 'LockStatus',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'lockLoginName',
			title : 'LockLoginName',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'lockLoginId',
			title : 'LockLoginId',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'tPDriverCode',
			title : 'TPDriverCode',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'acciInputCaseTime',
			title : '事故录入时间',
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
			title : '修改人代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operateTimeForHis',
			title : '修改时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStatus',
			title : '有效状态',
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
	var url = contextRootPath+"/qp/qpticaccident/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpticaccident/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpticaccident/view.do?" + params;
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
			var url = contextRootPath+"/qp/qpticaccident/delete.do?" + getUrlByJson(rows[0]);
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
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticaccident/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticaccident/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
