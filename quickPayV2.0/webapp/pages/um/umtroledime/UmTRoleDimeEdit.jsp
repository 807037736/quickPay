<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRoleDime" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtroledime/UmTRoleDime.js"></script>

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
					修改UmTRoleDime信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加UmTRoleDime信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看UmTRoleDime信息
				</c:when>
		</c:choose>		</h3></div>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/um/umtroledime" namespace="/um/umtroledime" method="post">

		<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		主键ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${umTRoleDime.id.roleDimeId}					<input type="hidden" name="umTRoleDime.id.roleDimeId" id="umTRoleDime.id.roleDimeId" value="${umTRoleDime.id.roleDimeId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="umTRoleDime.id.roleDimeId" id="umTRoleDime.id.roleDimeId" value="${umTRoleDime.id.roleDimeId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${umTRoleDime.id.roleDimeId}					<input type="hidden" name="umTRoleDime.id.roleDimeId" id="umTRoleDime.id.roleDimeId" value="${umTRoleDime.id.roleDimeId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		角色ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.roleId" id="umTRoleDime.roleId" value="${umTRoleDime.roleId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		维度代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.dimensionCode" id="umTRoleDime.dimensionCode" value="${umTRoleDime.dimensionCode}">
		</td>
	<td class="bgc_tt short">
		维度值
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.dimensionValue" id="umTRoleDime.dimensionValue" value="${umTRoleDime.dimensionValue}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		功能ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.taskId" id="umTRoleDime.taskId" value="${umTRoleDime.taskId}">
		</td>
	<td class="bgc_tt short">
		操作类型
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.operateType" id="umTRoleDime.operateType" value="${umTRoleDime.operateType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${umTRoleDime.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${umTRoleDime.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${umTRoleDime.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${umTRoleDime.operateTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.validStatus" id="umTRoleDime.validStatus" value="${umTRoleDime.validStatus}">
		</td>
	<td class="bgc_tt short">
	</td>
		<td class="long">
		</td>
							 </tr> 				 
		</table>
		<br>
		<table class="fix_table">
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
