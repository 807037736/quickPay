<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.log.schema.model.LoGTINFO" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/log/logtinfo/LoGTINFO.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="right_detail_top"><h3>日志查询</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.userCode" id="loGTINFO.userCode" value="${loGTINFO.userCode}">
		</td>
	<td class="bgc_tt short">
		用户名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.userName" id="loGTINFO.userName" value="${loGTINFO.userName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		操作时间
	</td>
		<td  class="long">
			<input class='input_w w_15 selectcode' name="loGTINFO.operateTime" id="loGTINFO.operateTime" value="${loGTINFO.operateTime}" onclick="WdatePicker()"> -
			<input class='input_w w_15 selectcode' name="operateTime2" id="operateTime2" value="${loGTINFO.operateTime}" onclick="WdatePicker()">
		</td>
	<td class="bgc_tt short">
		操作类型名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateTypeName" id="loGTINFO.operateTypeName" value="${loGTINFO.operateTypeName}">
		</td>
					</tr>
							 			 
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="LoGTINFOTable"></table>
	</div>
</body>
</html>
