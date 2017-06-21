<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttpresponsible/QpTTPResponsibleQuery.js"></script>

</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入统计时间</h2>
				</div>
				<table class="fix_table" style="width: 100%;" >
					<tr>
						<td width="10%" class="bgc_tt short" align="right">统计时间：</td>
						<td width="10%"><input class='input_w w_30'
							name="qpTTPResponsible.acciStartTime" id="qpTTPResponsible.acciStartTime" 
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" readonly></td>
						<td  width="10%" class="bgc_tt short"  align="right">至：</td>
						<td  width="10%"><input class='input_w w_30'
							name="qpTTPResponsible.acciEndTime" id="qpTTPResponsible.acciEndTime" 
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" readonly></td>
					</tr>
					
					<tr>
						<td colspan="4" style="text-align: center;">
						    <input type="button"  onclick="executeQuery();" class="button_ty" value="查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询"> 
							<input type="button"  onclick="expExcel();" class="button_ty" value="导出Excel"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTTPResponsibleTable"></table>
</body> 

</html>
