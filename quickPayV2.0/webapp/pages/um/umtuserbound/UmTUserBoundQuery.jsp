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
	<form name="wxfm" id="wxfm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">查询微信用户</h2>
				</div>
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户姓名
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTUserBound.wxUserName" id="wxUserName">
		</td>
	<td class="bgc_tt short">
		手机号码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBound.mobile" id="mobile" >
		</td>
							</tr>
	<tr>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBound.identityNumber" id="identityNumber" >
		</td>
	<td class="bgc_tt short">
		车牌号码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBound.licenseNo" id="licenseNo" >
		</td>
		
	</tr>
	
					<tr>
						<td colspan="6" align="center">
								
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQueryWX();">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="wxUserTable"></table>
	<div id="ckBoundWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="查询查勘员与绑定"
		style="width:1200px;height:750px;top:100px;"></div>
</body>
</html>
