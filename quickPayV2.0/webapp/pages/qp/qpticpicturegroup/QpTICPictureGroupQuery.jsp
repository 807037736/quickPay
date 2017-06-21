<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticpicturegroup/QpTICPictureGroup.js"></script>
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
		组号ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.id.groupId" id="qpTICPictureGroup.id.groupId" value="${qpTICPictureGroup.id.groupId}">
		</td>
	<td class="bgc_tt short">
		上传时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.uploadTimeForHis" id="qpTICPictureGroup.uploadTimeForHis" value="${qpTICPictureGroup.uploadTimeForHis}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		上传用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.uploadUserCode" id="qpTICPictureGroup.uploadUserCode" value="${qpTICPictureGroup.uploadUserCode}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.validStatus" id="qpTICPictureGroup.validStatus" value="${qpTICPictureGroup.validStatus}">
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
	<table id="QpTICPictureGroupTable"></table>
</body>
</html>
