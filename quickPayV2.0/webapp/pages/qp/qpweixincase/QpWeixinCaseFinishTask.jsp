<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBind"%>
<html>
<head>
<title>微信定责已完成任务列表</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpweixincase/QpWeixinCaseFinishTask.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form name="fm" id="fm" method="post" action="${ctx}/weixin/qpweixincase/queryFinishWxTask.do">
		<div class="right_detail_top">
			<h3>定责完成案件</h3>
		</div>
		   <div id="wrapper" style="margin-bottom:2px">
			  <div id="container">
				<table class="fix_table"  style="width: 100%;">
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">完成时间：</td>
						<td width="10%">
							<input class='input_w w_22' name="wxTaskFinish.startCompleteTime" id="wxTaskFinishStartCompleteTime" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${wxTaskFinish.startCompleteTime }"  >
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">至：</td>
						<td  width="10%">
							<input class='input_w w_22' name="wxTaskFinish.endCompleteTime" id="wxTaskFinishEndCompleteTime" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${wxTaskFinish.endCompleteTime }"  >
						</td>
						<td width="10%" class="bgc_tt short"  align="right">领取人姓名：</td>
						<td width="10%">
							<input class='input_w w_22' name="wxTaskFinish.completeName" value="${wxTaskFinish.completeName }">
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">领取人编号：</td>
						<td  width="10%" >
							<input class='input_w w_22' name="wxTaskFinish.completeCode" value="${wxTaskFinish.completeCode}">
						</td>
					</tr>
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">完成人姓名：</td>
						<td width="10%">
							<input class='input_w w_22' name="wxTaskFinish.receiveName" value="${wxTaskFinish.receiveName }">
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">完成人编号：</td>
						<td  width="10%" >
							<input class='input_w w_22' name="wxTaskFinish.receiveCode" value="${wxTaskFinish.receiveCode}">
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">案件类型：</td>
						<td  width="10%" >
<%-- 							<input class='input_w w_22' name="wxTaskFinish.status" value="${wxTaskFinish.status}"> --%>
							<select  name="wxTaskFinish.status" class="input_w w_22 easyui-combobox" >
								<option <c:if test="${wxTaskFinish.status == '-1'}">selected</c:if>  value="-1" >全部</option>
								<option <c:if test="${wxTaskFinish.status == ''}">selected</c:if> value=''>已定责</option>
								<option <c:if test="${wxTaskFinish.status == '9'}">selected</c:if> value='9'>已撤销</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<input type="button" class="button_ty" value="查 询" onclick="executeQuery();"> 
							<input type="button" id="addButton"  onclick="resetInput();" class="button_ty" value="重置">
						</td>
					</tr>
				</table>
			</div>
			</div>
	</form>
	
	
	<div class="margin-control" >
		<table id="QpWeixinFinishTaskTable"></table>
	</div>
	<input type="hidden" id="userCode" name="userCode" value="${SessionUser.userCode}">
</body>
</html>
