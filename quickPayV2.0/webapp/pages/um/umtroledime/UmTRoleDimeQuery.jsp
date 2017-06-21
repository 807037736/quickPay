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

</script>
</head>
<body>
<div class="right_detail_top"><h3>角色维度</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		主键ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTRoleDime.id.roleDimeId" id="umTRoleDime.id.roleDimeId" value="${umTRoleDime.id.roleDimeId}">
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
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRoleDime.validStatus" id="umTRoleDime.validStatus" value="${umTRoleDime.validStatus}">
		</td>
	<td class="bgc_tt short">
	</td>
		<td class="long"></td>
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
	<div class="margin-control">
	<table id="UmTRoleDimeTable"></table>
	</div>
</body>
</html>
