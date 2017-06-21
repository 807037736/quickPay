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

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/um/umtusertask" namespace="/um/umtusertask" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改UmTUserTask信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加UmTUserTask信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看UmTUserTask信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${umTUserTask.id.userTaskId}					<input type="hidden" name="umTUserTask.id.userTaskId" id="umTUserTask.id.userTaskId" value="${umTUserTask.id.userTaskId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="umTUserTask.id.userTaskId" id="umTUserTask.id.userTaskId" value="${umTUserTask.id.userTaskId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${umTUserTask.id.userTaskId}					<input type="hidden" name="umTUserTask.id.userTaskId" id="umTUserTask.id.userTaskId" value="${umTUserTask.id.userTaskId}">
			</c:when>
		</c:choose>
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
		<td class="long">
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
		创建人代码
	</td>
			<td class="long">
${umTUserTask.creatorCode}			</td>
	<td class="bgc_tt short">
		创建日期
	</td>
			<td class="long">
${umTUserTask.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${umTUserTask.updaterCode}			</td>
	<td class="bgc_tt short">
		修改日期
	</td>
			<td class="long">
${umTUserTask.operateTimeForHis}			</td>
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
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
