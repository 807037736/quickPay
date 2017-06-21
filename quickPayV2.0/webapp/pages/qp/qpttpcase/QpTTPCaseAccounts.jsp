<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">
var query_action = contextRootPath + "/qp/qpttpcase/queryAssessment.do";
var queryResultTable = "QpTICAccidentTable";

	function expExcel() {
		var data = $("#fm").serialize();
		$("#fm").attr("action",contextRootPath+"/qp/qpttpcase/accountsExpExcel.do?" + data);
 		$("#fm").submit();
	}
	
	/* 查询 */
	function executeQuery() {
		var data = $("#fm").serialize();
		var send_url = query_action + "?" + data;
		$('#'+queryResultTable).datagrid({
			url : send_url,
			title : " 台账信息",
			nowrap : true,
			striped : true,
			remoteSort : false,
			pageNumber : 1,
			pagination : true,
			columns:[[
					    {field : 'acciid',hidden:true},
						{field : 'caseSerialNo',title : '认字编号',align : 'center',sortable : true},
						{field : 'caseTime',title : '出险时间',align : 'center',sortable : true},
						{field : 'caseAddress',title : '出险详细地点',align : 'center',sortable : true},
						{field : 'driverVehicleNumber',title : '主车及三车号',align : 'center',sortable : true},
						{field : 'driverLiabilityDesc',title : '事故责任',align : 'center',sortable : true},	
						{field : 'driverName',title : '驾驶员姓名',align : 'center',sortable : true},
						{field : 'driverMobile',title : '驾驶员联系电话',align : 'center',sortable : true},	
						{field : 'coName',title : '所属公司',align : 'center',sortable : true},	
						{field : 'estimateLossSumDesc',title : '估损金额',align : 'right',sortable : true},
						{field : 'fixedLossPriceDesc',title : '定损价格',align : 'right',sortable : true},		
						{field : 'tpHandleTime',title : '快赔处理时间',align : 'center',sortable : true},
						{field : 'lossAssessorCode',title : '受理人',align : 'center',sortable : true}
		        ]],
		        onClickRow: function (rowIndex, rowData) { 
                    $(this).datagrid('unselectRow', rowIndex); 
                }
		});
	}
	
</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入导出条件</h2>
				</div>
				<table class="fix_table" style="width: 100%;" >
					<tr>
						<td class="bgc_tt short">起始时间：</td>
						<td class="long"><input class='input_w w_30 '  name="qpTTPCaseStatVO.estimateLossTimeStart" id="estimateLossTimeStart" readonly
								value="${qpTTPCaseStatVO.estimateLossTimeStart}" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
						<td   class="bgc_tt short" >结束时间：</td>
						<td  class="long" ><input class='input_w w_30 ' name="qpTTPCaseStatVO.estimateLossTimeEnd" id="estimateLossTimeEnd" readonly
								value="${qpTTPCaseStatVO.estimateLossTimeEnd}" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
									</tr>
						<tr>
						<td  class="bgc_tt short">受理点：</td>
						<td  class="long" >
							<select id="qpTTPCaseCenterId" 
							name="qpTTPCaseStatVO.centerId" editable="false"
							class="input_w w_30 easyui-combobox"  <c:if test="${not empty qpTTPCaseStatVO.centerId }">readonly</c:if>  >
								<option value="0">请选择</option>
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.id.centerId}"
										<c:if test="${fastCenter.id.centerId==qpTTPCaseStatVO.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
						</select>
						</td>
						<td class="bgc_tt short">保险公司：</td>
						<td  class="long" >
							<select id="qpTTPCaseCoId" name="qpTTPCaseStatVO.coId" class="input_w w_30 easyui-combobox" editable="false">
								<option value="0">请选择</option>
								<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
									<option value="${qpTICCompany.coId}" <c:if test="${qpTICCompany.coId==qpTICAccident.coId}">selected</c:if>>${qpTICCompany.coName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">
						    <input type="button" class="button_ty" value="查询" onclick="executeQuery();"> 
							<input type="button" id="expExcelButton" onclick="expExcel();" class="button_ty" value="导出台账"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTICAccidentTable"></table>
</body>
</html>
