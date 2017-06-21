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
<div class="right_detail_top"><h3><c:choose>
			<c:when test="${operateType == 'update'}">
					修改日志信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加日志信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看日志信息
				</c:when>
		</c:choose>		</h3></div>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/um/logtinfo" namespace="/um/logtinfo" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		日志ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${loGTINFO.id.logId}					<input type="hidden" name="loGTINFO.id.logId" id="loGTINFO.id.logId" value="${loGTINFO.id.logId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="loGTINFO.id.logId" id="loGTINFO.id.logId" value="${loGTINFO.id.logId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${loGTINFO.id.logId}					<input type="hidden" name="loGTINFO.id.logId" id="loGTINFO.id.logId" value="${loGTINFO.id.logId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		操作类型ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateTypeId" id="loGTINFO.operateTypeId" value="${loGTINFO.operateTypeId}">
		</td>
							</tr>
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
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateTime" id="loGTINFO.operateTime" value="${loGTINFO.operateTime}" onclick="WdatePicker()">
		</td>
	<td class="bgc_tt short">
		操作类型名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateTypeName" id="loGTINFO.operateTypeName" value="${loGTINFO.operateTypeName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		操作方法
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateMethod" id="loGTINFO.operateMethod" value="${loGTINFO.operateMethod}">
		</td>
	<td class="bgc_tt short">
		操作结果
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTINFO.operateResult" id="loGTINFO.operateResult" value="${loGTINFO.operateResult}">
		</td>
							 </tr> 				 
		</table>
		<table  class="fix_table">
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
		</div>
		</div>
	</form>
</body>
</html>
