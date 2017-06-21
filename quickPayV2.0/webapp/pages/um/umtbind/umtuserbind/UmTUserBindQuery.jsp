<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBind" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtbind/umtuserbind/UmTUserBind.js"></script>
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
		用户代码
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTUserBind.id.userCode" id="umTUserBind.id.userCode" value="${umTUserBind.id.userCode}">
		</td>
	<td class="bgc_tt short">
		客户名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.customerName" id="umTUserBind.customerName" value="${umTUserBind.customerName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定方式
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindType" id="umTUserBind.bindType" value="${umTUserBind.bindType}">
		</td>
	<td class="bgc_tt short">
		绑定值
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindValue" id="umTUserBind.bindValue" value="${umTUserBind.bindValue}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="umTUserBind.bindTime" id="umTUserBind.bindTime" value="${umTUserBind.bindTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
	<td class="bgc_tt short">
		绑定结果
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindResult" id="umTUserBind.bindResult" value="${umTUserBind.bindResult}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定客户编号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.custId" id="umTUserBind.custId" value="${umTUserBind.custId}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.validStatus" id="umTUserBind.validStatus" value="${umTUserBind.validStatus}">
		</td>
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
	<table id="UmTUserBindTable"></table>
</body>
</html>
