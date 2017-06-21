<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserTask" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtusertask/UmTUserTask.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="right_detail_top"><h3>用户功能配置</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTUserTask.id.userTaskId" id="umTUserTask.id.userTaskId" value="${umTUserTask.id.userTaskId}">
		</td>
	<td class="bgc_tt short">
		功能ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserTask.taskId" id="umTUserTask.taskId" value="${umTUserTask.taskId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserTask.userCode" id="umTUserTask.userCode" value="${umTUserTask.userCode}">
		</td>
	<td class="bgc_tt short">
		适用范围
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserTask.scope" id="umTUserTask.scope" value="${umTUserTask.scope}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		失效日期
	</td>
		<td  class="long">
			<input class='input_w w_15' name="umTUserTask.invalidDate" id="umTUserTask.invalidDate" value="${umTUserTask.invalidDate}" onclick="WdatePicker()">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserTask.validStatus" id="umTUserTask.validStatus" value="${umTUserTask.validStatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		标识
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserTask.flag" id="umTUserTask.flag" value="${umTUserTask.flag}">
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
	<table id="UmTUserTaskTable"></table>
	</div>
</body>
</html>
