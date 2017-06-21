<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMCity" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomcity/QpTCOMCity.js"></script>
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
		城市ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTCOMCity.id.cityId" id="qpTCOMCity.id.cityId" value="${qpTCOMCity.id.cityId}">
		</td>
	<td class="bgc_tt short">
		城市名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.cityName" id="qpTCOMCity.cityName" value="${qpTCOMCity.cityName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属省份ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.provId" id="qpTCOMCity.provId" value="${qpTCOMCity.provId}">
		</td>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.cityOrder" id="qpTCOMCity.cityOrder" value="${qpTCOMCity.cityOrder}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.validStatus" id="qpTCOMCity.validStatus" value="${qpTCOMCity.validStatus}">
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
	<table id="QpTCOMCityTable"></table>
</body>
</html>
