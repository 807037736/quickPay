<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMDriverInfo" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomdriverinfo/QpTCOMDriverInfo.js"></script>
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
		驾驶员ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.id.driverInfoId" id="qpTCOMDriverInfo.id.driverInfoId" value="${qpTCOMDriverInfo.id.driverInfoId}">
		</td>
	<td class="bgc_tt short">
		驾驶员姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverName" id="qpTCOMDriverInfo.driverName" value="${qpTCOMDriverInfo.driverName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverIdNumber" id="qpTCOMDriverInfo.driverIdNumber" value="${qpTCOMDriverInfo.driverIdNumber}">
		</td>
	<td class="bgc_tt short">
		车牌号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverVehicleNumber" id="qpTCOMDriverInfo.driverVehicleNumber" value="${qpTCOMDriverInfo.driverVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		承保保险公司ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.coId" id="qpTCOMDriverInfo.coId" value="${qpTCOMDriverInfo.coId}">
		</td>
	<td class="bgc_tt short">
		录音时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverInfoRecordTime" id="qpTCOMDriverInfo.driverInfoRecordTime" value="${qpTCOMDriverInfo.driverInfoRecordTime}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		身份证号重复次数
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverIdNumberRepeatTime" id="qpTCOMDriverInfo.driverIdNumberRepeatTime" value="${qpTCOMDriverInfo.driverIdNumberRepeatTime}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.validStatus" id="qpTCOMDriverInfo.validStatus" value="${qpTCOMDriverInfo.validStatus}">
		</td>
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
	<table id="QpTCOMDriverInfoTable"></table>
</body>
</html>
