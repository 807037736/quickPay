<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBound" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtuserbound/UmTUserBound.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<form name="ckfm" id="ckfm">
		<input  name="umTUserBound.boundId"    id="boundId"    value="${umTUserBound.boundId}"     hidden="true">
		<input  name="umTUserBound.wxUserCode" id="wxUserCode" value="${umTUserBound.wxUserCode }" hidden="true">
		<input  name="umTUserBound.wxUserName" id="wxUserName" value="${umTUserBound.wxUserName}"  hidden="true">
		<input  name="umTUserBound.ckUserCode" id="ckUserCode" value="${umTUserBound.ckUserCode }" hidden="true">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">绑定查勘员</h2>
				</div>
			<table class="fix_table">
				<tr>
					<td class="bgc_tt short">
						查勘员姓名
					</td>
					  	<td  class="long">
							<input class='input_w w_15' name="umTUserBound.ckUserName" id="umTUserBound.ckUserName" value="${umTUser.userName}">
						</td>
					<td class="bgc_tt short">
						查勘员手机号码
					</td>
						<td class="long">
							<input class='input_w w_15' name="umTUserBound.mobile" id="umTUserBound.mobile" value="${umTUser.mobile}">
						</td>
				</tr>
				
				<tr>
					<td class="bgc_tt short">
						查勘员身份证号
					</td>
						<td class="long">
							<input class='input_w w_15' name="umTUserBound.identityNumber" id="umTUserBound.identityNumber" value="${umTUser.identityNumber}">
						</td>
					<td class="bgc_tt short">
						查勘员车牌号码
					</td>
						<td class="long">
							<input class='input_w w_15' name="umTUserBound.licenseNo" id="umTUserBound.licenseNo" value="${umTUser.licenseNo}">
						</td>
				</tr>
				
				<tr>
					<td colspan="6" align="center">
						<input type="button" class="button_ty" value="查 询"
							onclick="executeQueryCK();"> 
					</td>
				</tr>
			</table>
			</div>
		</div>
	</form>
	<br>
	<table id="ckUserTable"></table>
</body>
</html>
