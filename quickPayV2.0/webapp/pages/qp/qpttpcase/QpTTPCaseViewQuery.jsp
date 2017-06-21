<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">
var query_action = contextRootPath + "/qp/qpttpcase/query.do";
var queryResultTable = "QpTTPCaseTable";

$(document).ready(function(){
	executeQuery();
	$('#QpTICAccidentTable').datagrid({
		url : null,
		title : "关联的当事人信息",
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns:[[
				    {field : 'checkBoxNo',checkbox : true},
				    {field : 'acciId',hidden:true},
					{field : 'driverName',title : '姓名',align : 'center',sortable : true},
					{field : 'driverSexDesc',title : '性别',align : 'center',sortable : true},
					{field : 'driverIDNumber',title : '有效证件',align : 'center',sortable : true},
					{field : 'driverMobile',title : '联系电话',align : 'center',sortable : true},	
					{field : 'driverVehicleTypeDesc',title : '车辆类型',align : 'center',sortable : true},
					{field : 'driverVehicleNumber',title : '车牌号码',align : 'center',sortable : true},	
					{field : 'driverAddress',title : '当事人地址',align : 'center',sortable : true},
					{field : 'driverLiabilityDesc',title : '当事人责任',align : 'center',sortable : true},		
					{field : 'driverLawDesc',title : '触犯的法律法规',align : 'center',sortable : true}
	        ]],
	    singleSelect:true
	});
	
});
/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		url : send_url,
		title : " 案件信息",
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns:[[
				    {field : 'checkBoxNo',checkbox : true},
				    {field : 'caseId',hidden:true},
					{field : 'caseSerialNo',title : '认字编号',align : 'center',sortable : true},
					/* {field : 'tpUserName',title : '警员姓名',align : 'center',sortable : true},
					{field : 'tpLoginId',title : '警员编号',align : 'center',sortable : true}, */
					{field : 'assistorId',title : '办案人员编号',align : 'center',sortable : true},
					{field : 'assistorName',title : '办案人员姓名',align : 'center',sortable : true},
					
					{field : 'caseTime',title : '事故发生时间',align : 'center',sortable : true},	
					{field : 'caseAddress',title : '事故地点',align : 'center',sortable : true},
					{field : 'caseWeatherDesc',title : '天气情况',align : 'center',sortable : true},	
					{field : 'tpHandleTime',title : '受理时间',align : 'center',sortable : true},
					{field : 'caseResult',title : '调解结果',align : 'center',sortable : true},		
					{field : 'caseNotes',title : '事故详情',align : 'center',sortable : true}
	        ]],
		onSelect : function(rowIndex, rowData) {
			var accidentUrl = contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=" + rowData.caseId;
			$('#QpTICAccidentTable').datagrid('options').url=accidentUrl;
			$('#QpTICAccidentTable').datagrid('reload');
			
		},
		onLoadSuccess : function(data) {
			$('#'+queryResultTable).datagrid('selectRow',0);
		},
	    singleSelect:true
	});
}

function editrow (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='actionType' id='actionType' value='edit'/>");
	$("#fm").append("<input type='hidden' name='qpTTPCaseCaseId' id='qpTTPCaseCaseId' value='"+row.caseId+"'/>");
	
	var caseUrl = contextRootPath + "/qp/qpttpcase/prepareAdd.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

function viewrow (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='actionType' id='actionType' value='view'/>");
	$("#fm").append("<input type='hidden' name='qpTTPCaseCaseId' id='qpTTPCaseCaseId' value='"+row.caseId+"'/>");
	
	var caseUrl = contextRootPath + "/qp/qpttpcase/prepareAdd.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

function viewAccident (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTICAccidentTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/qp/qpticaccident/prepareAdd.do?actionType=view&qpAcciId=" + row.acciId;
	$('#resUserSetWindow').window(
		{
			href: caseUrl,
			cache: false
		}
	);
	$('#resUserSetWindow').window('open');
}

<%-- 关闭窗口 --%>
function closeUserWindow() {
	$('#resUserSetWindow').window('close');
}


</script>
</head>
<body>
	<form name="fm" id="fm" style="margin-bottom:22px;">
		<div class="right_detail_top" ><h3>历史案件</h3></div>
		<input type="hidden" id="qpTTPCaseDriverIDType" name="qpTTPCase.driverIDType" value="0" />
		<input type="hidden" id="qpTTPCaseCaseWeather" name="qpTTPCase.caseWeather" value="0" />
		<input type="hidden" id="qpTTPCaseCenterId" name="qpTTPCase.centerId" value="0" />
		<input type="hidden" id="qpTTPCaseCoId" name="qpTTPCase.coId" value="0" />
		
		<input type="hidden" name="qpTTPCase.driverIDNumber" id="qpTTPCase.driverIDNumber" value="${driverIDNumber}">
		<input type="hidden" name="qpTTPCase.driverVehicleNumber" id="qpTTPCase.driverVehicleNumber" value="${driverVehicleNumber}">
		<input type="hidden" name="qpTTPCase.tpHandleStatus" id="qpTTPCase.tpHandleStatus" value="-6">
	</form>
	<table id="QpTTPCaseTable" style="margin-top:30px;"></table>
	<br>
	<table id="QpTICAccidentTable"></table>
	
	<div id="resUserSetWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="当事人基本信息"
		style="width:500px;height:450px;top:100px;"></div>
</body>
</body>
</html>
