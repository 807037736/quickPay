<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

<script type="text/javascript">
var query_action = contextRootPath + "/qp/qpttpcasefind/query.do";
var queryResultTable = "QpTTPCaseTable";

$(document).ready(function(){
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
				    {field : 'caseId',hidden:true},
					{field : 'driverSexDesc',title : '性别',align : 'center',sortable : true},
					{field : 'driverName',title : '姓名',align : 'center',sortable : true},
					{field : 'driverIDNumber',title : '有效证件',align : 'center',sortable : true},
					/*{field : 'driverMobile',title : '联系电话',align : 'center',sortable : true},*/
					{field : 'driverVehicleTypeDesc',title : '车辆类型',align : 'center',sortable : true},
					{field : 'driverVehicleNumber',title : '车牌号码',align : 'center',sortable : true},	
					{field : 'driverAddress',title : '当事人地址',align : 'center',sortable : true},
					{field : 'driverLiabilityDesc',title : '当事人责任',align : 'center',sortable : true},		
					{field : 'driverLawDesc',title : '触犯的法律法规',align : 'center',sortable : true} ,
					{field :'operate',title:'操作',align:'center',sortable : true,
						formatter:function(value,row,index){
	   		 				return '<a name="edit_data" href="#" onclick="javascript:viewAccident(this)">&nbsp;查看&nbsp;</a>';
	   	 				}	
       				} 
	        ]],
	    singleSelect:true
	});
	
	$(".combo").click(function(){
		$(this).prev().combobox("showPanel");
	});
	
	$("#executeQueryClick").click(function(){
		var value = $("#driverIDNumber").val() + $("#qpTICAccidentDriverVehicleNumber").val();
		if(value && value != '湘'){
			executeQuery();
		}else{
			$.messager.alert('提示信息', '证件号码和车牌号至少填写一个！', 'info');
		} 
	});
	
	if("${isReturn}" == '2'){//是通过返回过来的
		executeQuery();
	 }
});

function fixWidth(percent){  
    return document.body.clientWidth * percent ; 
}
/* 查询 */
function executeQuery() {
	var checkIndex = -1;
	var driverVehicleNumber = $("#qpTICAccidentDriverVehicleNumber").val();
	if (driverVehicleNumber == '湘') {
		$("#qpTICAccidentDriverVehicleNumber").val("");
	}
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		url : send_url,
		title : " 案件信息",
		width:'auto',              
        height:'auto', 
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns:[[
				    {field : 'checkBoxNo',width:fixWidth(0.03),checkbox : true},
				    {field : 'caseId',hidden:true},
					{field : 'caseSerialNo',title : '认字编号',align : 'center',width:fixWidth(0.08),sortable : true},
					/*{field : 'assistorId',title : '办案人员编号',align : 'center',width:fixWidth(0.03),sortable : true},*/
					{field : 'assistorName',title : '办案人员姓名',align : 'center',width:fixWidth(0.1),sortable : true},
					
					{field : 'caseTime',title : '事故发生时间',align : 'center',width:fixWidth(0.1),sortable : true},	
					{field : 'caseAddress',title : '事故地点',align : 'center',width:fixWidth(0.15),sortable : true},
					/*{field : 'caseWeatherDesc',title : '天气情况',align : 'center',width:fixWidth(0.03),sortable : true},*/	
					{field : 'tpHandleTime',title : '录入时间',align : 'center',width:fixWidth(0.1),sortable : true},
					{field : 'caseResult',title : '调解结果',align : 'center',width:fixWidth(0.1),sortable : true},		
					{field : 'caseNotes',title : '事故详情',align : 'center',width:fixWidth(0.15),sortable : true},
					{field : 'tpHandleStatus',title : '案件状态',align : 'center',width:fixWidth(0.05),sortable : true,
						formatter:function(value,row,index){
							if(value=='0'){
       	 						value = '待提交';
       	 					    return value;
       	 					}
	       	 				if(value=='1'){
	   	 						value = '待定责';
	   	 					    return value;
	   	 					}
		       	 			if(value=='2'){
			 					value = '待查勘';
			 					return value;
			 				}
			       	 		if(value=='3'){
								value = '已退回';
								return value;
							}
				       	 	if(value=='4'){
								value = '查勘处理中';
								return value;
							}
				       	    if(value=='5'){
								value = '已受理';
								return value;
							}
				       	 	if(value=='6'){
								value = '已注销';
								return value;
							}
			       	 		return value;
       	 				}		
					} ,
					{field :'operate',title:'操作',align:'center',width:fixWidth(0.13),sortable : true,
       	 				formatter:function(value,row,index){
       	 					return '<a name="edit_data" href="#" onclick="javascript:viewrow(this)">&nbsp;查看&nbsp;</a>';
       	 				}	
       				} 
       				
	        ]],
		onSelect : function(rowIndex, rowData) {
			if(rowData == null){
				$('#QpTICAccidentTable').datagrid('loadData', { total: 0, rows: [] });  
			}
			if(checkIndex != rowData.caseId){
				var accidentUrl = null;
				if(rowData != null && "undefined" != typeof(rowData) && "undefined" != rowData && rowData != "") {
					accidentUrl = contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=" + rowData.caseId;
				}else {
					accidentUrl = contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=none";
				}
				$('#QpTICAccidentTable').datagrid('options').url=accidentUrl;
				$('#QpTICAccidentTable').datagrid('reload');
				checkIndex = rowData.caseId;
			}
		},
		onLoadSuccess : function(data) {
			$('#'+queryResultTable).datagrid('selectRow',0);
		},
	    singleSelect:true
	});
}


function viewrow (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='actionType' id='actionType' value='view'/>");
	$("#fm").append("<input type='hidden' name='qpTTPCaseCaseId' id='qpTTPCaseCaseId' value='"+row.caseId+"'/>");
	$("#fm").append("<input type='hidden' name='CaseFind' id='CaseFind' value='1'/>");//代表是综合查询进入查看详情界面
	
	var caseUrl = contextRootPath + "/qp/qpttpcase/prepareAdd.do?businessType=" + $("#businessType").val();
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

function viewAccident (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTICAccidentTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/qp/qpticaccident/prepareAdd.do?actionType=view&businessType=" + $("#businessType").val() + "&qpAcciId=" + row.acciId;
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

<%-- 重置 --%>
function resetInput() {
	document.getElementById('qpTTPCase.caseTimeStart').value = "";
	document.getElementById('qpTTPCase.caseTimeEnd').value = "";
	document.getElementById('tpHandleTimeStart').value = "";
	document.getElementById('qpTTPCase.tpHandleTimeEnd').value = "";
	$("#qpTTPCaseDriverIDType").combobox("setValue",'0');
	document.getElementById('driverIDNumber').value = "";
	document.getElementById('qpTTPCase.driverName').value = "";
	document.getElementById('qpTICAccidentDriverVehicleNumber').value = "";
}



</script>
</head>
<body>
	<form name="fm" id="fm">
		<input type="hidden" name="qpTTPCase.tpHandleStatus" id="qpTTPCase.tpHandleStatus" value="-6">
	    <!-- <input type="hidden" name="qpTTPCase.driverVehicleNumber" id="driverVehicleNumber" > -->
		<div class="right_detail_top"><h3>出险查询</h3></div>
		   <div id="wrapper" style="margin-bottom:2px">
			  <div id="container">
				<table class="fix_table"  style="width: 100%;">
					<tr>
						<td width="10%" class="bgc_tt short" align="right">事发时间：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseTimeStart" id="qpTTPCase.caseTimeStart" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${qpTTPCase.caseTimeStart }" >
						</td>
						<td  width="10%" class="bgc_tt short" align="right">至：</td>
						<td  width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseTimeEnd" id="qpTTPCase.caseTimeEnd" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${qpTTPCase.caseTimeEnd }" >
						</td>
						<td width="10%" class="bgc_tt short" align="right">受理日期：</td>
						<td width="10%">
						                                                                                                            
							<input class='input_w w_22' name="qpTTPCase.tpHandleTimeStart" id="tpHandleTimeStart" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${qpTTPCase.tpHandleTimeStart }" >
						</td>
						<td  width="10%" class="bgc_tt short" align="right">至：</td>
						<td  width="10%" >
							<input class='input_w w_22' name="qpTTPCase.tpHandleTimeEnd" id="qpTTPCase.tpHandleTimeEnd" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${qpTTPCase.tpHandleTimeEnd }">
						</td>
					</tr>
					<tr>
						<td width="10%" class="bgc_tt short" align="right">证件类型：</td>
						<td width="10%">
							<select id="qpTTPCaseDriverIDType" name="qpTTPCase.driverIDType" editable="false" class="input_w w_22 easyui-combobox">
									<option value="0">请选择</option>
								<c:forEach var="identifyType" items="${identifyTypeList}">
									<option value="${identifyType.codeCode}" <c:if test="${identifyType.codeCode==qpTTPCase.driverIDType}">selected</c:if>>${identifyType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
						<td  width="10%" class="bgc_tt short" align="right">证件号码：</td>
						<td  width="10%">
							<input class='input_w w_22' name="qpTTPCase.driverIDNumber" id="driverIDNumber" value="${qpTTPCase.driverIDNumber }">
						</td>
						
						<td width="10%" class="bgc_tt short" align="right">当事人：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.driverName" id="qpTTPCase.driverName" value="${qpTTPCase.driverName }">
						</td>
						
						
						<td  width="10%" class="bgc_tt short" align="right">车牌号：</td>
						<td width="10%">
							<input class='input_w w_22'  name="qpTTPCase.driverVehicleNumber" id="qpTICAccidentDriverVehicleNumber"  value="${qpTTPCase.driverVehicleNumber }" >
						</td>
						<input type="hidden" id="businessType" name="businessType" value="111" >
					</tr>
					
					<tr>
						<td colspan="8" align="center">
							<input type="button" class="button_ty" value="查 询" id="executeQueryClick"> 
							<input type="button" id="addButton"
								onclick="resetInput();" class="button_ty" value="重置">
						</td>
					</tr>
				</table>
			</div>
			</div>
	</form>
	<br>
	<table id="QpTTPCaseTable"></table>
	<br>
	<table id="QpTICAccidentTable"></table>
	
	<div id="resUserSetWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="当事人基本信息"
		style="width:1000px;height:650px;top:100px;"></div>
		
</body>
</html>