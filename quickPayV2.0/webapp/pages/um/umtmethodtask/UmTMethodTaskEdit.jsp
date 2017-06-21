<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTMethodTask"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/umtmethodtask/UmTMethodTask.js"></script>
<script type="text/javascript">
	
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtmethodtask"
		namespace="/um/umtmethodtask" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="2" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${operateType == 'update'}">
					修改UmTMethodTask信息
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加UmTMethodTask信息
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看UmTMethodTask信息
				</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="bgc_tt short">方法ID</td>
				<td class="long"><c:choose>
						<c:when test="${operateType == 'update'}">
${umTMethodTask.id.methodId}					<input type="hidden"
								name="umTMethodTask.id.methodId" id="umTMethodTask.id.methodId"
								value="${umTMethodTask.id.methodId}">
						</c:when>
						<c:when test="${operateType == 'add'}">
						<!-- 
							<input class='input_w w_15' name="umTMethodTask.id.methodId"  style="width:300px" 
								id="umTMethodTask.id.methodId"
								value="${umTMethodTask.id.methodId}">
						 -->
						</c:when>
						<c:when test="${operateType == 'view'}">
${umTMethodTask.id.methodId}					<input type="hidden"
								name="umTMethodTask.id.methodId" id="umTMethodTask.id.methodId"
								value="${umTMethodTask.id.methodId}">
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td class="bgc_tt short">功能ID</td>
				<td class="long">
				<c:choose>
						<c:when test="${operateType == 'add' || operateType == 'update'}">
							<input type="text" style="width:300px"
								class="input_w w_15 selectcode" name="selectCode"
								id='selectCode'
								ondblclick="code_CodeQuery(this,'/common/queryAllUmTask.do', '0,1,2','y');"
								onkeyup="code_CodeSelect(this, '/common/queryAllUmTask.do', '0,1,2', 'y');"
								onchange="code_CodeChange(this, '/common/queryAllUmTask.do', '0,1,2', 'y');"
								readonly="readonly" value='${umTMethodTask.taskId}'>
							<input type="hidden" name="umTMethodTask.taskId1" id="umTMethodTask.taskId1"/>
							<input type="hidden" style="width:300px"
								name="umTMethodTask.taskId"
								id='umTMethodTask.taskId' value='${umTMethodTask.taskId}' />
						</c:when>
						<c:when test="${operateType == 'view'}">
							${umTMethodTask.taskId}
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">方法代码</td>
				<td class="long">
					<c:choose>
						<c:when test="${operateType == 'add' || operateType == 'update'}">
							<input class='input_w w_15' style="width:800px" 
								name="umTMethodTask.methodCode" id="umTMethodTask.methodCode"
								value="${umTMethodTask.methodCode}" />
						</c:when>
						<c:when test="${operateType == 'view'}">
								${umTMethodTask.methodCode}
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">有效状态</td>
				<td class="long">
					<c:choose>
						<c:when test="${operateType == 'update'}">
							<ce:radio name="umTMethodTask.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
								value="${umTMethodTask.validStatus}" />
						</c:when>
						<c:when test="${operateType == 'add'}">
							<ce:radio name="umTMethodTask.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="1" />
						</c:when>
						<c:when test="${operateType == 'view'}">
							<ce:radio name="umTMethodTask.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
								value="${umTMethodTask.validStatus}" disabled="true" />
						</c:when>
					</c:choose>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
