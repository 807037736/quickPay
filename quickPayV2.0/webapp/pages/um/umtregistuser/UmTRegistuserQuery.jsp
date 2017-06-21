<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRegistuser" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtregistuser/UmTRegistuser.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户注册编号
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTRegistuser.id.userCode" id="umTRegistuser.id.userCode" value="${umTRegistuser.id.userCode}">
		</td>
	<td class="bgc_tt short">
		用户姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.userName" id="umTRegistuser.userName" value="${umTRegistuser.userName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		关联代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.relatedCode" id="umTRegistuser.relatedCode" value="${umTRegistuser.relatedCode}">
		</td>
	<td class="bgc_tt short">
		机构代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.comCode" id="umTRegistuser.comCode" value="${umTRegistuser.comCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		注册密码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.password" id="umTRegistuser.password" value="${umTRegistuser.password}">
		</td>
	<td class="bgc_tt short">
		昵称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.nickName" id="umTRegistuser.nickName" value="${umTRegistuser.nickName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		手机号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.mobile" id="umTRegistuser.mobile" value="${umTRegistuser.mobile}">
		</td>
	<td class="bgc_tt short">
		安全邮箱
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.email" id="umTRegistuser.email" value="${umTRegistuser.email}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		注册来源
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.registSource" id="umTRegistuser.registSource" value="${umTRegistuser.registSource}">
		</td>
	<td class="bgc_tt short">
		密码最近修改时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="umTRegistuser.pwdModifyTime" id="umTRegistuser.pwdModifyTime" value="${umTRegistuser.pwdModifyTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		密码修改次数
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.pwdModifyTimes" id="umTRegistuser.pwdModifyTimes" value="${umTRegistuser.pwdModifyTimes}">
		</td>
	<td class="bgc_tt short">
		用户类型（00-外部客户，01-员工，02-渠道）
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.userType" id="umTRegistuser.userType" value="${umTRegistuser.userType}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效性
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTRegistuser.validStatus" id="umTRegistuser.validStatus" value="${umTRegistuser.validStatus}">
		</td>
								<td class="bgc_tt short"></td>
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
	<table id="UmTRegistuserTable"></table>
</body>
</html>
