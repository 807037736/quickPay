<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">

$.extend($.fn.validatebox.defaults.rules, {
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '请输入至少5个字符'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一致'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    }
});

/* 密码由字母和数字组成，至少5位 */
var safePassword = function (value) {
	//return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
	return (/^(?=.*[0-9].*)(?=.*[A-Za-z].*).{5,40}$/.test(value));
	//return (/^[A-Za-z0-9].{5,40}$/.test(value));
};

$(function() {
	$('#fm input').each(function () {
	    if ($(this).attr('required') || $(this).attr('validType'))
	        $(this).validatebox();
		});
});

function saveUserPassWord(){
	$('#fm').form('submit', {  
	    url:contextRootPath+"/um/umtuser/resetUserPassWord.do",  
	    onSubmit: function(){
	    	if($('#oldPassWord').val() == null || $('#oldPassWord').val() == "") {
	    		$('#oldPassWord').focus();
	    		return false;
	    	}
	    	
	    	if($('#newPassWord').val() == null || $('#newPassWord').val() == "") {
	    		$('#newPassWord').focus();
	    		return false;
	    	}
	    	
	    	if($('#comfirmPassWord').val() == null || $('#comfirmPassWord').val() == "") {
	    		$('#comfirmPassWord').focus();
	    		return false;
	    	}
	    	
	    	if($('#newPassWord').val() != $('#comfirmPassWord').val()) {
	    		$('#comfirmPassWord').focus();
	    		return false;
	    	}
	    	$('#oldPassWord').val(SHA1($('#oldPassWord').val()));
	    	$('#newPassWord').val(SHA1($('#newPassWord').val()));
	    	$('#comfirmPassWord').val(SHA1($('#comfirmPassWord').val()));
	    	
	    	//$("#savePassWordBt").attr("disabled", true); //避免重复提交 
	    	return $("#fm").form('validate');	    	
	    },  	
	    success:function(result){
			var data = eval('(' + result + ')');
	    	if(data.msg=='success'){
	    		//$.messager.alert('提示信息','修改成功');
	    		//document.location.reload();//当前页面
	 	    	$.messager.alert('提示信息', '修改成功','info');
	 	    	document.getElementById("fm").reset();
	    	}else{
	    		$.messager.alert('提示信息',data.msg,'info');

	    	}
	    	
	    },
	error:function(result){
		   alertErrorMsgForEasyUi(result);
		   window.opener=null;
		   window.close();

	}   
	});
	
}
</script>
</head>
<body>
    <div class="right_detail_top">
		<div class="right_detail_top">
			<h3>修改密码</h3>
		</div>
	</div>
	<form name="fm" id="fm" method="post">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		原密码
	</td>
		<td class="long">
			<input class='input_w w_15' id="oldPassWord" type="password" validType="minLen[5]" required="true" name="oldPassWord">
		</td>
	
							</tr>
							<tr>
	<td class="bgc_tt short">
		新密码
	</td>
		<td class="long">
			<input class='input_w w_15' id="newPassWord" type="password" validType="minLen[5]" required="true" name="newPassWord" >
		</td>
							</tr>
							
							<tr>
	<td class="bgc_tt short">
		确认密码
	</td>
		<td class="long">
			<input class='input_w w_15' id="comfirmPassWord" type="password" validType="equalTo['#newPassWord']" required="true" name="comfirmPassWord"  >
		</td>
							</tr>
							
					<tr>
						<td colspan="6" align="center">
							<input type="button" id="savePassWordBt" class="button_ty" value="保 存"
								onclick="saveUserPassWord();"> 
							<input type="reset" 
								 class="button_ty" value="重 置">
						</td>
					</tr>
				</table>
			</div>
		</div>
		
	</form>
</body>
</html>
