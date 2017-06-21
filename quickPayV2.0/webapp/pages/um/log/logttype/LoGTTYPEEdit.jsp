<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.log.schema.model.LoGTTYPE" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/log/logttype/LoGTTYPE.js"></script>
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
<div class="right_detail_top"><h3>
<c:choose>
			<c:when test="${operateType == 'update'}">
					修改日志类型信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加日志类型信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看日志类型信息
				</c:when>
		</c:choose>			</h3></div>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/um/logttype" namespace="/um/logttype" method="post">

		<table class="fix_table">
			
			<tr>
							<tr>
	<td class="bgc_tt short">
		操作类型ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${loGTTYPE.id.operateTypeId}					<input type="hidden" name="loGTTYPE.id.operateTypeId" id="loGTTYPE.id.operateTypeId" value="${loGTTYPE.id.operateTypeId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="loGTTYPE.id.operateTypeId" id="loGTTYPE.id.operateTypeId" value="${loGTTYPE.id.operateTypeId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${loGTTYPE.id.operateTypeId}					<input type="hidden" name="loGTTYPE.id.operateTypeId" id="loGTTYPE.id.operateTypeId" value="${loGTTYPE.id.operateTypeId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		操作类型英文名
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateTypeEName" id="loGTTYPE.operateTypeEName" value="${loGTTYPE.operateTypeEName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		操作类型中文名
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateTypeCName" id="loGTTYPE.operateTypeCName" value="${loGTTYPE.operateTypeCName}">
		</td>
	<td class="bgc_tt short">
		操作方法
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateMethod" id="loGTTYPE.operateMethod" value="${loGTTYPE.operateMethod}">
		</td>
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
