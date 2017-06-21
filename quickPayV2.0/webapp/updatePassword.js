/**
 * @author      limingguo03
 * @date        2013-10-15 16:10
 * @description 修改登陆密码
 * 
 */
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

function countIntervalPW(){
	//$("#savePassWordBt").removeAttr("disabled");
	var count = 60*60*24; 
	var countdown = setInterval(countDown, 1000); 
	
	function countDown() { 
		$("#sendNewPassWord").attr("disabled", true); 
		$("#sendNewPassWord").val("验证码24小时有效"); 
		if (count == 0) { 
		$("#sendNewPassWord").val("发送验证码").removeAttr("disabled"); 
		//$("#savePassWordBt").attr("disabled", true);
		clearInterval(countdown); 		
		}
		count--;
	}
}

function isNumber(s){ 
	var reg1=/^\d{10}$/;
	var reg2=/^\d{8}$/;
	if (reg1.exec(s)||reg2.exec(s)) {
	 return true; 
	}
	return false; 
}

function saveUserPassWord(){
	//if(isNumber($('#userCode').val())&&safePassword($('#newPassWord').val())){
	if(isNumber($('#userCode').val())){
	$('#upwf').form('submit', {  
	    url:contextRootPath+"/um/umtuser/updatePassWord.do",  
	    onSubmit: function(){ 
	    	//$('#oldPassWord').val(SHA1($('#oldPassWord').val()));
	    	$('#newPassWord').val(SHA1($('#newPassWord').val()));
	    	$('#comfirmPassWord').val(SHA1($('#comfirmPassWord').val()));
	    	$("#savePassWordBt").attr("disabled", true); //避免重复提交 
	    	return $("#upwf").form('validate');	    	
	    },  	
	    success:function(result){
	    	var data = eval('(' + result + ')');
	    	if(data.msg=='success'){
	    		//$.messager.alert('提示信息','修改成功');
	    		//document.location.reload();//当前页面
	    		$.messager.confirm('提示信息', '修改成功', function(r){
	    			if (r){
	    				window.opener=null;
	    		    	window.close();
	    				}else{
	    					document.location.reload();
	    				}
	    			});
	    	}else{
	    		$.messager.confirm('提示信息', data.msg, function(r){
	    			if (r){
	    				window.opener=null;
	    		    	window.close();
	    				}else{
	    					document.location.reload();
	    				}
	    			});

	    	}
	    },
	error:function(result){
		   alertErrorMsgForEasyUi(result);
		   window.opener=null;
		   window.close();

	}   
	});
	}else{
		//$.messager.alert('提示信息','用户代码或密码不正确！','info');
		$.messager.alert('提示信息','用户代码不正确！','info');
	}
}

function sendSMSUpdatePassWord(){

	if(isNumber($('#userCode').val())){
	countIntervalPW();
	$.ajax({
		   type: "POST",
		   url: contextRootPath + "/um/umtuser/sendvCode.do?userCode="+$('#userCode').val(),
		   success: function(result){
			   $.messager.alert('提示信息','已发送验证码到手机！','info');
		   },
		   error:function(result){
			   alertErrorMsgForEasyUi(result);
		}
	});
	}else{
		$.messager.alert('提示信息','用户代码不正确！','info');
	}

}