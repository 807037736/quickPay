<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTSauuser" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtsauuser/MyTeamQuery.js"></script>
<script type="text/javascript">
/*页面加载触发*/
$(document).ready(function(){
	/* $("input.input_w").css('border','#FFF'); */
		setReadonlyOfAllInput("fm");
});
</script>
</head>
<body>
<div class="right_detail_top"><h3>我的团队</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
							
	<td class="bgc_tt short">
		团队代码
		<input type='hidden' name="umTSaugroup.id.teamID" id="umTSaugroup.id.teamID" value="${umTSaugroup.id.teamID}">
	</td>
		<td class="short">
		${umTSaugroup.teamCode}
			<input type='hidden' name="umTSaugroup.teamCode" id="umTSaugroup.teamCode" value="${umTSaugroup.teamCode}">
		</td>
	<td class="bgc_tt short">
		团队名称
	</td>
		<td class="long">
		${umTSaugroup.teamName}
			<input type='hidden'  name="umTSaugroup.teamName" id="umTSaugroup.teamName" value="${umTSaugroup.teamName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
		${umTSaugroup.comCode}
			<input  type='hidden' name="umTSaugroup.comCode" id="umTSaugroup.comCode" value="${umTSaugroup.comCode}">
		</td>
	<td class="bgc_tt short">
		团队类型
	</td>
		<td class="long">
		${umTSaugroup.teamType}
			<input type='hidden' name="umTSaugroup.teamType" id="umTSaugroup.teamType" value="${umTSaugroup.teamType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队经理名称
	</td>
		<td class="long">
		${umTSaugroup.managerName}
			<input type='hidden' name="umTSaugroup.managerName" id="umTSaugroup.managerName" value="${umTSaugroup.managerName}">
		</td>
	<td class="bgc_tt short">
		团队经理工号
	</td>
		<td class="long">
		${umTSaugroup.managerCode}
			<input type='hidden' name="umTSaugroup.managerCode" id="umTSaugroup.managerCode" value="${umTSaugroup.managerCode}">
		</td>
							</tr>
							
							
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询 团 队 成 员"
								onclick="executeQuery();"> 
							<!-- <input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加"> -->
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTSauuserTable"></table>
	</div>
</body>
</html>
