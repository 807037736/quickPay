<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPPolice" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttppolice/QpTTPPolice.js"></script>
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
		交警ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTTPPolice.id.policeId" id="qpTTPPolice.id.policeId" value="${qpTTPPolice.id.policeId}">
		</td>
	<td class="bgc_tt short">
		交警姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.policeName" id="qpTTPPolice.policeName" value="${qpTTPPolice.policeName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属大队
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.teamId" id="qpTTPPolice.teamId" value="${qpTTPPolice.teamId}">
		</td>
	<td class="bgc_tt short">
		警员编号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.employeeId" id="qpTTPPolice.employeeId" value="${qpTTPPolice.employeeId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.policeOrder" id="qpTTPPolice.policeOrder" value="${qpTTPPolice.policeOrder}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.validStatus" id="qpTTPPolice.validStatus" value="${qpTTPPolice.validStatus}">
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
	<table id="QpTTPPoliceTable"></table>
</body>
</html>
