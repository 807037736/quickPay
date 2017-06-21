<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qpttpcasestat/QpDoingTTPCaseStat.js"></script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table" style="width: 100%;">
					<tr>
						<td width="20%" align="right">受理时间：</td>
						<td width="80%">
						<input class='input_w w_22'
							name="qpTTPCaseStatVO.TPHandleTimeStart" id="qpTTPCaseStatVO.TPHandleTimeStart" 
							value="${qpTTPCaseStatVO.TPHandleTimeStart}" onclick="WdatePicker({skin:'whyGreen'})">
						至：
						<input class='input_w w_22'
							name="qpTTPCaseStatVO.TPHandleTimeEnd" id="qpTTPCaseStatVO.TPHandleTimeEnd" 
							onclick="WdatePicker({skin:'whyGreen'})">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
						</td>
					</tr>
				</table>
			</div>	
		</div>
	</form>
	<table id="QpTTPCaseTable"></table>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#QpTTPCaseTable').datagrid({
			title : "查询结果",
			url : null,
			nowrap : true,
			striped : true,
			remoteSort : false,
			pageNumber : 1,
			pagination : true,
			onLoadError: function(result){
				alertErrorMsgForEasyUi(result);
			},
			columns : [[{
				field:'field_0',
				title:'快处中心',
				hidden:true
			},{
				field:'field_1',
				title:'待查勘',
				hidden:true	
			},{
				field:'field_2',
				title:'处理中',
				hidden:true
			},{
				field:'field_3',
				title:'已受理',
				hidden:true
			}]]
		});
	});
	</script>
</body>
</html>
