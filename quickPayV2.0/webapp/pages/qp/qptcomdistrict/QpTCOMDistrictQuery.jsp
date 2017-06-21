<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMDistrict" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomdistrict/QpTCOMDistrict.js"></script>
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
		区县ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.id.districtId" id="qpTCOMDistrict.id.districtId" value="${qpTCOMDistrict.id.districtId}">
		</td>
	<td class="bgc_tt short">
		区县名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.districtName" id="qpTCOMDistrict.districtName" value="${qpTCOMDistrict.districtName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属城市ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.cityId" id="qpTCOMDistrict.cityId" value="${qpTCOMDistrict.cityId}">
		</td>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.districtOrder" id="qpTCOMDistrict.districtOrder" value="${qpTCOMDistrict.districtOrder}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.validStatus" id="qpTCOMDistrict.validStatus" value="${qpTCOMDistrict.validStatus}">
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
	<table id="QpTCOMDistrictTable"></table>
</body>
</html>
