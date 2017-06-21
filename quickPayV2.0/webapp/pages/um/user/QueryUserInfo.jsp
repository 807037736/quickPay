<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">
	
</script>
</head>
<body style='width:80%;height:300px'>
	<form name="fm" id="fm">
		<div >
		<table >
							<tr><td align="right"><font>姓&nbsp;&nbsp;&nbsp;&nbsp;名</font></td><td style="padding-left:10px;">
							<input  style="height:20px;" name="umTUser.userName" id="umTUser.userName" value="${umTUser.userName}">
							</td></tr>
							<br>
							<tr><td align="right"><font>手&nbsp;机&nbsp;号</font></td><td style="padding-left:10px;"><!-- <input value="" style="height:20px;"/> -->
							<input  class="easyui-validatebox " style="height:20px;" name="umTUser.mobile" id="umTUser.mobile" value="${umTUser.mobile}">
							</td></tr>
							<br>
							<tr><td align="right"><font>深&nbsp;&nbsp;份&nbsp;&nbsp;证</font></td><td style="padding-left:10px;">
							<input  class="easyui-validatebox " style="height:20px;" name="umTUser.identityNumber" id="umTUser.identityNumber" value="${umTUser.identityNumber}">
							</td></tr>
						</table>
		</div>
	</form>
</body>
</html>