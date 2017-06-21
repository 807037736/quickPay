<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDevice" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtdevice/UmTDevice.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="right_detail_top"><h3>用户设备管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		设备ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15'  name="umTDevice.id.deviceId" id="umTDevice.id.deviceId" value="${umTDevice.id.deviceId}" maxlength="30">
		</td>
	<td class="bgc_tt short">
		用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTDevice.userCode" id="umTDevice.userCode" value="${umTDevice.userCode}" maxlength="8">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		设备类型
	</td>
		<td class="long">
		<ce:select name="umTDevice.deviceType" id="umTDevice.deviceType" list="#@java.util.LinkedHashMap@{'':'请选择','01':'电脑设备','02':'移动设备'}" value="umTDevice.deviceType" cssClass='input_w w_15'/>
		</td>
	<td class="bgc_tt short">
		设备名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTDevice.deviceName" id="umTDevice.deviceName" value="${umTDevice.deviceName}" maxlength="50">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
		<ce:select name="umTDevice.validStatus" id="umTDevice.validStatus" list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}" value="umTDevice.validStatus" cssClass='input_w w_15'/>
	
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
	<div class="margin-control">
	<table id="UmTDeviceTable"></table>
	</div>
</body>
</html>
