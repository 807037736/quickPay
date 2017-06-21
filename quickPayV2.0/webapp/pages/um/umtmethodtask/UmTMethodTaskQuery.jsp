<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTMethodTask" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtmethodtask/UmTMethodTask.js"></script>
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
		方法ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTMethodTask.id.methodId" id="umTMethodTask.id.methodId" value="${umTMethodTask.id.methodId}">
		</td>
	<td class="bgc_tt short">
		功能ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTMethodTask.taskId" id="umTMethodTask.taskId" value="${umTMethodTask.taskId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		方法代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTMethodTask.methodCode" id="umTMethodTask.methodCode" value="${umTMethodTask.methodCode}">
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
	<table id="UmTMethodTaskTable"></table>
</body>
</html>
