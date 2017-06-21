<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRoleCom" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/roleCom/UmTRoleCom.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/um/umtrolecom" namespace="/um/umtrolecom" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
					修改UmTRoleCom信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加UmTRoleCom信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看UmTRoleCom信息
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
			<c:when test="${opreateType == 'update'}">
${umTRoleCom.id.roleComId}					<input type="hidden" name="umTRoleCom.id.roleComId" id="umTRoleCom.id.roleComId" value="${umTRoleCom.id.roleComId}">
			</c:when>
			<c:when test="${opreateType == 'add'}">
					<input class='input_w w_15' name="umTRoleCom.id.roleComId" id="umTRoleCom.id.roleComId" value="${umTRoleCom.id.roleComId}">
			</c:when>
			<c:when test="${opreateType == 'view'}">
${umTRoleCom.id.roleComId}					<input type="hidden" name="umTRoleCom.id.roleComId" id="umTRoleCom.id.roleComId" value="${umTRoleCom.id.roleComId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		角色ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.roleId" id="umTRoleCom.roleId" value="${umTRoleCom.roleId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		角色代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.roleCode" id="umTRoleCom.roleCode" value="${umTRoleCom.roleCode}">
		</td>
	<td class="bgc_tt short">
		机构代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.comCode" id="umTRoleCom.comCode" value="${umTRoleCom.comCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${umTRoleCom.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${umTRoleCom.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${umTRoleCom.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${umTRoleCom.operateTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.validStatus" id="umTRoleCom.validStatus" value="${umTRoleCom.validStatus}">
		</td>
	<td class="bgc_tt short">
		包含类型
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.includeType" id="umTRoleCom.includeType" value="${umTRoleCom.includeType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		机构名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleCom.comName" id="umTRoleCom.comName" value="${umTRoleCom.comName}">
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
