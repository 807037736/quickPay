/**
 * 
 */

/*
function goSubmit() {
		
		if(!checkForm()) {
			document.getElementById('tip').style.display = '';
			return false;
		}
		
		document.getElementById('tip').style.display = 'none';
		fm.action = contextRootPath+"/um/umtregistuser/add.do";
		fm.submit();	
	}
*/

$.validator.methods.v_validateCode_sms = function(value, element, param) {
	
	var smsId = $("#smsId").val();
	return (smsId != undefined && smsId != "");
};

$.validator.methods.v_validateCode = function(value, element, param) {
	
	var confirm_vCode = $('#confirm_vCode').val();
	return (confirm_vCode != undefined && confirm_vCode == "true");	
};


$.validator.setDefaults({
	submitHandler: function() {
		
		document.getElementById('tip').style.display = 'none';
		fm.action = contextRootPath+"/um/umtregistuser/add.do";
		fm.submit();
	}
});


$(function(){
	
	$(fm).validate({
		rules: {
			/*validate_code: {
				v_validateCode_sms: true,
				required: true,
				v_validateCode: true
			} */
		},
		messages: {
			
			"umTRegistuser.userName": {
				required: "请输入您的姓名"
			},
			"umTRegistuser.mobile": {
				required: "请输入您的手机号"
			}
	/*		,	
			
			"umTRegistuser.password": {
				required: "请设置您的登录密码(6位及以上)",
				minlength: "密码长度需要6位及以上"
			},
			rePassword: {
				required: "请确认您的登录密码",
				equalTo: "您输入的密码不一致，请重新输入"
			}*/
		}
	});	
});


/*
	function checkForm() {
		
		var name = $("#name");
		var phoneNo = $("#phoneNo");
		var validate_code = $("#validate_code");
		var password = $("#password");
		var rePassword = $("#rePassword");
		
		var tip = $("#tip>span");
		
		if(name.val().trim().empty()) {
			tip.text('姓名不能为空！请填写真实姓名。');
			name.focus();
			return false;
		}
		if(!nameValidate(name.val().trim())){
			tip.text('姓名有误！请填写真实姓名。');
			name.focus();
			return false;
		}
		
		
		if(phoneNo.val().trim().empty()) {
			tip.text('手机号不能为空！请填写真实手机号码。');
			phoneNo.focus();
			return false;
		}
		if(!mobileValidate(phoneNo.val().trim())){
			tip.text('手机号有误！请填写真实手机号码。');
			phoneNo.focus();
			return false;
		}
		
		
		if(password.val().empty()) {
			tip.text('密码不能为空！请设置6位及以上密码。');
			password.focus();
			return false;
		}
		if(password.val().length < 6) {
			tip.text('请设置6位及以上密码。');
			password.focus();
			return false;
		}
		if(rePassword.val().empty()) {
			tip.text('请确认密码！');
			rePassword.focus();
			return false;
		}
		if(password.val() != rePassword.val()) {
			tip.text('密码不一致，请重新确认密码！');
			rePassword.focus();
			return false;
		}
		
		if(validate_code.val().trim().empty()) {
    		tip.text('请输入您收到的手机验证码。点击[获取验证码]获取。');
    		validate_code.focus();
    		return false;
    	}
		
		var smsId = $('#smsId').val();
		var confirm_vCode = $('#confirm_vCode').val();
		
		if( smsId == ""){
    		tip.text('请先点击[获取验证码]进行手机号验证。');
    		return false;
    	} 
    	if( confirm_vCode == "false1" || confirm_vCode == "false2") {
    		tip.text('验证码错误，请重新输入。点击[获取验证码]获取。');
    		validate_code.focus();
    		return false;
    	}
    	if( confirm_vCode == "") {
    		return false;
    	}
    	tip.text('');
			
		return true;	
	}
*/	


	function preSendVCode() {
		
		var phoneNo = $('#phoneNo').val();
		var customerno = "";
		var customername = "";
		var activitycode = "";
		var activityname = $('#activityname').val();
		var remark = "";
		
		if(phoneNo.trim().empty()) {
			$('#tip_vcode').text('手机号不能为空，请填写真实号码');
			return;
		}
		if(!mobileValidate(phoneNo.trim())) {
			$('#tip_vcode').text('手机号不正确，请重新填写');
			return;
		}
		
		$('#tip_vcode').text('');
		var rtn = '0';
		var url = contextRootPath + "/um/umtregistuser/checkMobile.do";
		var data = "openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6"+"&mobileno=" + phoneNo ; 
			
		$.ajax({
			type: "POST",
			   url: url,
			   data: data,
			   async: true,
			   success: function(result){
				  var obj=eval("(" + result + ")");
				  //alert(obj.msg);
				  if(obj.msg=='false'){
					  rtn = "1";
					  sendVCode(activitycode, activityname, customerno, customername, phoneNo, remark);
				  } else if(obj.msg=='success'){
					  $('#tip_vcode').text('该手机号已经注册过!');
					  rtn = "0";
				  } 
			   }
		});
		//checkMobile(phoneNo);
		if(rtn=='1'){
			
		}
		return;
	}

	/*
	 * 判断是否已经注册过
	 */
	function checkMobile(mobileno){
		var url = contextRootPath + "/um/umtregistuser/checkMobile.do";
		var data = "openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6"+"&mobileno=" + mobileno ; 
			
		$.ajax({
			type: "POST",
			   url: url,
			   data: data,
			   async: true,
			   success: function(result){
				  var obj=eval("(" + result + ")");
				  //alert(obj.msg);
				  if(obj.msg=='false'){
					  rtn = "1";
				  } else if(obj.msg=='success'){
					  $('#tip_vcode').text('该手机号已经注册过!');
					  rtn = "0";
				  } 
			   }
		});
	}

	
	function preConfirmVCode(){
		
		var vCode = $('#validate_code').val();
		var smsId = $('#smsId').val();
		
		if(vCode.trim().empty() || smsId.trim().empty()) {
			return;
		}
		
		confirm_validatecode_async(vCode, smsId, false);
	}