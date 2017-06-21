<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticaccident/QpTICAccident.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		事故ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTICAccident.id.acciId" id="qpTICAccident.id.acciId" value="${qpTICAccident.id.acciId}">
		</td>
	<td class="bgc_tt short">
		发生时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICAccident.acciTime" id="qpTICAccident.acciTime" value="${qpTICAccident.acciTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		天气情况
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciWeather" id="qpTICAccident.acciWeather" value="${qpTICAccident.acciWeather}">
		</td>
	<td class="bgc_tt short">
		省份
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciProvince" id="qpTICAccident.acciProvince" value="${qpTICAccident.acciProvince}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		城市
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciCity" id="qpTICAccident.acciCity" value="${qpTICAccident.acciCity}">
		</td>
	<td class="bgc_tt short">
		区县
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciDistrict" id="qpTICAccident.acciDistrict" value="${qpTICAccident.acciDistrict}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		道路
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciRoad" id="qpTICAccident.acciRoad" value="${qpTICAccident.acciRoad}">
		</td>
	<td class="bgc_tt short">
		街道
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciStreet" id="qpTICAccident.acciStreet" value="${qpTICAccident.acciStreet}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		证件类型
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverIDType" id="qpTICAccident.driverIDType" value="${qpTICAccident.driverIDType}">
		</td>
	<td class="bgc_tt short">
		证件号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverIDNumber" id="qpTICAccident.driverIDNumber" value="${qpTICAccident.driverIDNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverName" id="qpTICAccident.driverName" value="${qpTICAccident.driverName}">
		</td>
	<td class="bgc_tt short">
		性别
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverSex" id="qpTICAccident.driverSex" value="${qpTICAccident.driverSex}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		固定电话
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverPhone" id="qpTICAccident.driverPhone" value="${qpTICAccident.driverPhone}">
		</td>
	<td class="bgc_tt short">
		手机号码
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverMobile" id="qpTICAccident.driverMobile" value="${qpTICAccident.driverMobile}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		当前住址
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverAddress" id="qpTICAccident.driverAddress" value="${qpTICAccident.driverAddress}">
		</td>
	<td class="bgc_tt short">
		车辆类型
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverVehicleType" id="qpTICAccident.driverVehicleType" value="${qpTICAccident.driverVehicleType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		车牌号码
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverVehicleNumber" id="qpTICAccident.driverVehicleNumber" value="${qpTICAccident.driverVehicleNumber}">
		</td>
	<td class="bgc_tt short">
		保险凭证
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverInsuNumber" id="qpTICAccident.driverInsuNumber" value="${qpTICAccident.driverInsuNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		行驶方向
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverDirection" id="qpTICAccident.driverDirection" value="${qpTICAccident.driverDirection}">
		</td>
	<td class="bgc_tt short">
		DriverIp
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverIp" id="qpTICAccident.driverIp" value="${qpTICAccident.driverIp}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		LiveChannelId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.liveChannelId" id="qpTICAccident.liveChannelId" value="${qpTICAccident.liveChannelId}">
		</td>
	<td class="bgc_tt short">
		LiveStatus
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.liveStatus" id="qpTICAccident.liveStatus" value="${qpTICAccident.liveStatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		LiveSavedChannels
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.liveSavedChannels" id="qpTICAccident.liveSavedChannels" value="${qpTICAccident.liveSavedChannels}">
		</td>
	<td class="bgc_tt short">
		LiveDesc
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.liveDesc" id="qpTICAccident.liveDesc" value="${qpTICAccident.liveDesc}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		CCLoginId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.cCLoginId" id="qpTICAccident.cCLoginId" value="${qpTICAccident.cCLoginId}">
		</td>
	<td class="bgc_tt short">
		CCUserName
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.cCUserName" id="qpTICAccident.cCUserName" value="${qpTICAccident.cCUserName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		CCUserName
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.cCEmployeeId" id="qpTICAccident.cCEmployeeId" value="${qpTICAccident.cCEmployeeId}">
		</td>
	<td class="bgc_tt short">
		CCUserName
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.cCHandleStatus" id="qpTICAccident.cCHandleStatus" value="${qpTICAccident.cCHandleStatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		CCHandleTime
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICAccident.cCHandleTime" id="qpTICAccident.cCHandleTime" value="${qpTICAccident.cCHandleTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
	<td class="bgc_tt short">
		CCHandleNotes
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.cCHandleNotes" id="qpTICAccident.cCHandleNotes" value="${qpTICAccident.cCHandleNotes}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		信息来源
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.infoSource" id="qpTICAccident.infoSource" value="${qpTICAccident.infoSource}">
		</td>
	<td class="bgc_tt short">
		从属案件ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.caseId" id="qpTICAccident.caseId" value="${qpTICAccident.caseId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		违反法律法规
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverLawId" id="qpTICAccident.driverLawId" value="${qpTICAccident.driverLawId}">
		</td>
	<td class="bgc_tt short">
		需承担的责任
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverLiability" id="qpTICAccident.driverLiability" value="${qpTICAccident.driverLiability}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		损失
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.driverLoss" id="qpTICAccident.driverLoss" value="${qpTICAccident.driverLoss}">
		</td>
	<td class="bgc_tt short">
		ICLoginId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.iCLoginId" id="qpTICAccident.iCLoginId" value="${qpTICAccident.iCLoginId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		ICUserName
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.iCUserName" id="qpTICAccident.iCUserName" value="${qpTICAccident.iCUserName}">
		</td>
	<td class="bgc_tt short">
		ICEmployeeId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.iCEmployeeId" id="qpTICAccident.iCEmployeeId" value="${qpTICAccident.iCEmployeeId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		ICHandleStauts
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.iCHandleStauts" id="qpTICAccident.iCHandleStauts" value="${qpTICAccident.iCHandleStauts}">
		</td>
	<td class="bgc_tt short">
		ICHandleTime
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICAccident.iCHandleTime" id="qpTICAccident.iCHandleTime" value="${qpTICAccident.iCHandleTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		ICHandleNotes
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.iCHandleNotes" id="qpTICAccident.iCHandleNotes" value="${qpTICAccident.iCHandleNotes}">
		</td>
	<td class="bgc_tt short">
		所属保险公司ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.coId" id="qpTICAccident.coId" value="${qpTICAccident.coId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		TPRemoveStatus
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.tPRemoveStatus" id="qpTICAccident.tPRemoveStatus" value="${qpTICAccident.tPRemoveStatus}">
		</td>
	<td class="bgc_tt short">
		AcciLng
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciLng" id="qpTICAccident.acciLng" value="${qpTICAccident.acciLng}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		AcciLat
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.acciLat" id="qpTICAccident.acciLat" value="${qpTICAccident.acciLat}">
		</td>
	<td class="bgc_tt short">
		RelaAVehicleNumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaAVehicleNumber" id="qpTICAccident.relaAVehicleNumber" value="${qpTICAccident.relaAVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		RelaACoId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaACoId" id="qpTICAccident.relaACoId" value="${qpTICAccident.relaACoId}">
		</td>
	<td class="bgc_tt short">
		RelaBVehicleNumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaBVehicleNumber" id="qpTICAccident.relaBVehicleNumber" value="${qpTICAccident.relaBVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		RelaBCoId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaBCoId" id="qpTICAccident.relaBCoId" value="${qpTICAccident.relaBCoId}">
		</td>
	<td class="bgc_tt short">
		RelaCVehicleNumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaCVehicleNumber" id="qpTICAccident.relaCVehicleNumber" value="${qpTICAccident.relaCVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		RelaCCoId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaCCoId" id="qpTICAccident.relaCCoId" value="${qpTICAccident.relaCCoId}">
		</td>
	<td class="bgc_tt short">
		RelaDVehicleNumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaDVehicleNumber" id="qpTICAccident.relaDVehicleNumber" value="${qpTICAccident.relaDVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		RelaDCoId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.relaDCoId" id="qpTICAccident.relaDCoId" value="${qpTICAccident.relaDCoId}">
		</td>
	<td class="bgc_tt short">
		LockStatus
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.lockStatus" id="qpTICAccident.lockStatus" value="${qpTICAccident.lockStatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		LockLoginName
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.lockLoginName" id="qpTICAccident.lockLoginName" value="${qpTICAccident.lockLoginName}">
		</td>
	<td class="bgc_tt short">
		LockLoginId
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.lockLoginId" id="qpTICAccident.lockLoginId" value="${qpTICAccident.lockLoginId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		TPDriverCode
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.tPDriverCode" id="qpTICAccident.tPDriverCode" value="${qpTICAccident.tPDriverCode}">
		</td>
	<td class="bgc_tt short">
		事故录入时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICAccident.acciInputCaseTime" id="qpTICAccident.acciInputCaseTime" value="${qpTICAccident.acciInputCaseTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICAccident.validStatus" id="qpTICAccident.validStatus" value="${qpTICAccident.validStatus}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTICAccidentTable"></table>
</body>
</html>
