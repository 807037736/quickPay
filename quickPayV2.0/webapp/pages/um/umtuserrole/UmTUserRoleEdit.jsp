<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserRole" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtuserrole/UmTUserRole.js"></script>
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
	<form name="fm" id="fm" action="/um/umtuserrole" namespace="/um/umtuserrole" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改UmTUserRole信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加UmTUserRole信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看UmTUserRole信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		用户角色ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${umTUserRole.id.userRoleId}					<input type="hidden" name="umTUserRole.id.userRoleId" id="umTUserRole.id.userRoleId" value="${umTUserRole.id.userRoleId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="umTUserRole.id.userRoleId" id="umTUserRole.id.userRoleId" value="${umTUserRole.id.userRoleId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${umTUserRole.id.userRoleId}					<input type="hidden" name="umTUserRole.id.userRoleId" id="umTUserRole.id.userRoleId" value="${umTUserRole.id.userRoleId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		角色ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.roleId" id="umTUserRole.roleId" value="${umTUserRole.roleId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.userCode" id="umTUserRole.userCode" value="${umTUserRole.userCode}">
		</td>
	<td class="bgc_tt short">
		角色代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.roleCode" id="umTUserRole.roleCode" value="${umTUserRole.roleCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserRole.validStatus" id="umTUserRole.validStatus" value="${umTUserRole.validStatus}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${umTUserRole.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建日期
	</td>
			<td class="long">
${umTUserRole.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		更新人代码
	</td>
			<td class="long">
${umTUserRole.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		更新日期
	</td>
			<td class="long">
${umTUserRole.operateTimeForHis}			</td>
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
