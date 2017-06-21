<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/updatePassword.js"></script>
<script type="text/javascript">
$(function(){
	//$("#savePassWordBt").attr("disabled", true);
	$('#upwf input').each(function () {
    if ($(this).attr('required') || $(this).attr('validType'))
        $(this).validatebox();
	});
});
</script>
</head>
<body>	
		<div id="wrapper">
			<div id="container">
			<form name="upwf" id="upwf">
				<div class="right_detail_top"><h3>重置密码</h3></div>
				<table class="fix_table">
			<tr><td class="bgc_tt short">用户代码</td>
			<td class="long"><input name="userCode" id="userCode"  class='input_w w_20' required="true" value="">
			</td></tr>
		
			<tr><td class="bgc_tt short">新密码</td>
			<td class="long"><input validType="minLen[5]" required="true" id="newPassWord" class='input_w w_20' type="password" name="newPassWord" > 
			</td></tr>
		
			<tr><td class="bgc_tt short">确认密码</td>
			<td class="long"><input validType="equalTo['#newPassWord']" required="true" class='input_w w_20' type="password" id="comfirmPassWord" name="comfirmPassWord"> 
			</td></tr>
			
			<tr ><td class="bgc_tt short">验证码</td>
			<td class="long">
				<input id="verification" name="verification" style="vertical-align:middle;" class='input_w w_20' required="true">
				<input id="sendNewPassWord" type="button" style="vertical-align:middle;" class="button_ty" onclick="sendSMSUpdatePassWord();" ind="ind"  value="发送验证码到手机" /> 
			</td>
			</tr>
				</table>
			<table class="fix_table">
			<tr>
			<td class=" bgc_tt short" colspan="6" >
				<input id="savePassWordBt" type="button" class="button_ty" onclick="saveUserPassWord()" ind="ind"  value="重置密码" />
				<input id="reset" type="button" class="button_ty" ind="ind"  value="重置" onclick="document.location.reload();"/>				
			</td>
			</tr>
			</table>
			</form>
			</div>
		</div>	
	 
</body>
</html>
