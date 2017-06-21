<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMHandlePolice"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qptcomhandlepolice/QpTComHandlePolice.js"></script>
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
						<td class="bgc_tt short">处理民警编号</td>
						<td class="long"><input class='input_w w_15'
							name="qpTComHandlePolice.handlePoliceNO" id="handlePoliceNO"
							value="${qpTComHandlePolice.handlePoliceNO}"></td>
						<td class="bgc_tt short">处理民警姓名</td>
						<td class="long"><input class='input_w w_15'
							name="qpTComHandlePolice.handlePoliceName" id="handlePoliceName"
							value="${qpTComHandlePolice.handlePoliceName}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">电话号码</td>
						<td class="long"><input class='input_w w_15'
							name="qpTComHandlePolice.handlePolicePhone" id="handlePolicePhone"
							value="${qpTComHandlePolice.handlePolicePhone}" maxlength="11"></td>
						<td class="bgc_tt short">快赔中心</td>
						<td width="10%"><select id="centerId"
							name="qpTComHandlePolice.centerId" editable="false"
							class="input_w w_22 easyui-combobox">
								<option value="">请选择</option>
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.centerId}"
										<c:if test="${fastCenter.centerId==qpTComHandlePolice.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="qpTComHandlePoliceTable"></table>
</body>
</html>
