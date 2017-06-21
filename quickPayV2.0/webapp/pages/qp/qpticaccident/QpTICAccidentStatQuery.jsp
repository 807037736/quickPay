<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
	
	initVehicle();
	$("#qpTICAccidentDriverVehicleNumberPre").combobox({
		onChange : function(n,o) {
			$("#qpTICAccidentDriverVehicleNumber").attr("value",n);
		}
	});

	$("#qpTICAccidentDriverVehicleNumberPreS").combobox({
		onSelect : function(n) {
			$("#qpTICAccidentDriverVehicleNumber").attr("value",$("#qpTICAccidentDriverVehicleNumberPre").combobox('getText') + n.value);
		}
	});

	$("#qpTICAccidentDriverVehicleNumberPreE").combobox({
		onChange : function(n,o) {
			$("#qpTICAccidentDriverVehicleNumber").attr("value",$('#qpTICAccidentDriverVehicleNumber').val() + n);
		}
	});
	$("#executeQueryClick").click(function(){
		var value = $("#driverIDNumber").val() + $("#qpTICAccidentDriverVehicleNumber").val();
		if(value){
			executeQuery();
		}else{
			$.messager.alert('提示信息', '证件号码和车牌号至少填写一个！', 'info');
		} 
	});
});
<%-- 重置 --%>
function resetInput() {
    $("#qpTTPCaseDriverIDType").combobox("setValue",'6');
	document.getElementById('driverIDNumber').value = "";
	document.getElementById('qpTICAccidentDriverVehicleNumber').value = "";
	$("#qpTICAccidentDriverVehicleNumberPre").combobox("setValue",'');
	$("#qpTICAccidentDriverVehicleNumberPreS").combobox("setValue",'');
	$("#qpTICAccidentDriverVehicleNumberPreE").combobox("setValue",'');
}

<%-- 初始化车牌选项信息 --%>
function initVehicle() {
	var cfg_VehicleNo_Addr = ["湘","京","津","沪","渝","蒙","新","藏","宁","桂","港","澳","黑","吉","辽","晋","冀","青","鲁","豫","苏","皖","浙","闽","赣","鄂","粤","琼","甘","陕","黔","滇","川","贵","云"];
	var cfg_VehicleNo_AddrSpec = ["军","空","海","北","沈","兰","济","南","广","成","甲","午","未","庚","己","辛","壬","寅","戌","辰","WJ"];
	var cfg_VehicleNo_AddrNext = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	var cfg_VehicleNo_AddrEnd = ["临","警","学","挂","领","试","警备"];  
	
	$("#qpTICAccidentDriverVehicleNumberPre").append("<option value=''></option>");
	for(var i=0;i<cfg_VehicleNo_Addr.length;i++) {
		$("#qpTICAccidentDriverVehicleNumberPre").append('<option value="' + cfg_VehicleNo_Addr[i] + '">' + cfg_VehicleNo_Addr[i] + '</option>');
	}
	for(var i=0;i<cfg_VehicleNo_AddrSpec.length;i++) {
		$("#qpTICAccidentDriverVehicleNumberPre").append('<option value="' + cfg_VehicleNo_AddrSpec[i] + '">' + cfg_VehicleNo_AddrSpec[i] + '</option>');
	}
	
	$("#qpTICAccidentDriverVehicleNumberPreS").append("<option value=''></option>");
	for(var i=0;i<cfg_VehicleNo_AddrNext.length;i++) {
		$("#qpTICAccidentDriverVehicleNumberPreS").append('<option value="' + cfg_VehicleNo_AddrNext[i] + '">' + cfg_VehicleNo_AddrNext[i] + '</option>');
	}
	
	$("#qpTICAccidentDriverVehicleNumberPreE").append("<option value=''></option>");
	for(var i=0;i<cfg_VehicleNo_AddrEnd.length;i++) {
		$("#qpTICAccidentDriverVehicleNumberPreE").append('<option value="' + cfg_VehicleNo_AddrEnd[i] + '">' + cfg_VehicleNo_AddrEnd[i] + '</option>');
	}
	
}

/* 查询 */
function executeQuery() {
	var query_action=contextRootPath + "/qp/qpticaccident/queryAcciDriverstat.do";
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$("#fm").attr("action",send_url);
	$("#fm").submit();
}

</script>
</head>
		<body>
			<form name="fm" id="fm">
				<!-- <input type="hidden" name="qpTTPCase.driverVehicleNumber" id="driverVehicleNumber" > -->
				<div class="right_detail_top">
					<h3>查询条件</h3>
				</div>
				<div id="wrapper" style="margin-bottom:2px">
					<div id="container">
						<table class="fix_table" style="width: 100%;">
							<tr>
								<td width="10%" class="bgc_tt short"  align="right">证件类型：</td>
								<td width="10%">
									<select id="qpTTPCaseDriverIDType"
										name="qpTTPCase.driverIDType"  
										class="input_w w_22 easyui-combobox">
										<%-- <option value="0">请选择</option> --%>
										<c:forEach var="identifyType"
											items="${identifyTypeList}">
											<option value="${identifyType.codeCode}"<c:if test="${identifyType.codeCode==6}">selected</c:if>>${identifyType.codeCName}
											</option>
										</c:forEach>
									</select>
								</td>
								<td width="10%" class="bgc_tt short"  align="right">证件号码：</td>
								<td width="10%">
									<input class='input_w w_22'
										name="qpTTPCase.driverIDNumber" id="driverIDNumber" <c:if test="${ !empty driverIDNumber}">value=${driverIDNumber}</c:if>>
								</td>

								<!--<td width="10%" align="right">当事人：</td>
									<td width="10%">
									<input class='input_w w_22' name="qpTTPCase.driverName" id="qpTTPCase.driverName">
									</td>
								-->

								<td width="10%" class="bgc_tt short"  align="right">车牌号：</td>
								<td class="long">
									<select
										id="qpTICAccidentDriverVehicleNumberPre"
										name="qpTICAccidentDriverVehicleNumberPre"
										class="input_w w_3 easyui-combobox" style="width:50px;"
										editable="false">
									</select>
									<select
										id="qpTICAccidentDriverVehicleNumberPreS"
										name="qpTICAccidentDriverVehicleNumberPreS" editable="false"
										class="input_w w_3 easyui-combobox" style="width:45px;">
									</select>
									<input
										class='input_w easyui-validatebox' style="width:80px;"
										required="true" name="qpTTPCase.driverVehicleNumber"
										id="qpTICAccidentDriverVehicleNumber" <c:if test="${ !empty driverVehicleNumber}">value=${driverVehicleNumber}</c:if>>
									<select
										id="qpTICAccidentDriverVehicleNumberPreE"
										name="qpTICAccidentDriverVehicleNumberPreE" editable="false"
										class="input_w w_3 easyui-combobox" style="width:50px;">
									</select>
								</td>

							</tr>

							<tr>
								<td colspan="8" align="center">
									<input type="button"
										class="button_ty" value="查 询" id="executeQueryClick" >
									<input type="button" id="addButton"
										onclick="resetInput();" class="button_ty" value="重置">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<br>
			<div class="right_detail_top">
				<h3>出险情况</h3>
			</div>

			<table id="QpTICAccidentStat" class="fix_table"
				style="text-align: center;width:50%;margin-top:15px;" >
				<tr>
					<td ></td>
					<td class="bgc_tt short" >出险次数</td>
					<td class="bgc_tt short" >估损金额</td>
					<td class="bgc_tt short" >定损金额</td>
				</tr>
				<tr>
					<td class="bgc_tt short" >有责任</td>
					<td >${ liability_RISKTIME}<c:if test="${ empty liability_RISKTIME}">0</c:if>次</td>
					<td>${ liability_ESTIMATELOSSSUM}<c:if test="${ empty liability_ESTIMATELOSSSUM}">0</c:if>元</td>
					<td >${ liability_FIXEDLOSSPRICE}<c:if test="${ empty liability_FIXEDLOSSPRICE}">0</c:if>元</td>
				</tr>
				<tr>
					<td class="bgc_tt short" >无责任</td>
					<td>${ noliability_RISKTIME}<c:if test="${ empty noliability_RISKTIME}">0</c:if>次</td>
					<td>${ noliability_ESTIMATELOSSSUM}<c:if test="${ empty noliability_ESTIMATELOSSSUM}">0</c:if>元</td>
					<td >${ noliability_FIXEDLOSSPRICE}<c:if test="${ empty noliability_FIXEDLOSSPRICE}">0</c:if>元</td>
				</tr>
				<tr>
					<td class="bgc_tt short" >总：</td>
					<td >${noliability_RISKTIME + liability_RISKTIME}次</td>
					<td >${noliability_ESTIMATELOSSSUM + liability_ESTIMATELOSSSUM}元</td>
					<td >${noliability_FIXEDLOSSPRICE + liability_FIXEDLOSSPRICE}元</td>
				</tr>
			</table>
		</body>
</html>