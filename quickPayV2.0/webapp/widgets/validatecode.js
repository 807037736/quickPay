/**
 *  验证码
 */
var i;

/*
 * 发送短信验证码-有时间限制
 * activityname  填写了该值在短信里体现
 * timeLimit  有效时间 ，以秒为单位
 * isTimeLimit  是否有时间限制
 */
function sendVCode(activitycode, activityname, customerno, customername, phoneno, remark,
		 isTimeLimit, timelimit) {
	
	$("#smsId").val("");
	$("#tip_vcode").val("");
	$("#confirm_vCode").val("");
	$("#r_time").val("");
	
	if(isTimeLimit == undefined)
		isTimeLimit = false;
	if(timelimit == undefined)
		timelimit = 0;
	
	var url = contextRootPath + "/tool/smssendvalidatecode/sendVCode.do";
	var data = "activityCode=" + activitycode + "&activityName=" + activityname 
		+ "&customerNo=" + customerno + "&customerName=" + customername 
		+ "&phoneNo=" + phoneno + "&remark=" + remark + "&timeLimit=" + timelimit;
	
	$.ajax({
		   type: "POST",
		   url: url,
		   data: data,
		   beforeSend: function(XMLHttpRequest) {
			   $("#msgdiv").html("正在发送验证码，请稍候...");
			   $("#msgdiv:hidden").show();
			   lock("link_getValidateCode");
		   },
		   success: function(result){
			   
			  if(isTimeLimit) {
				  dislock("link_getValidateCode");
			  
				  var obj=eval("(" + result + ")");
				  //alert(obj.msg);
				  if(obj.msg=='success'){
					  $('#smsId').val(obj.smsId);
					  $("#msgdiv").html('验证码已发送，'+timelimit+'秒内有效，请注意查收');
					  $("#msgdiv:hidden").show();
					  $("#r_time").val(timelimit+1);
					  lock("link_getValidateCode");
					  i = setInterval(getRTime, 1000);
					  return;
				  }else if(obj.msg=='phoneno error'){
					  $("#msgdiv").html("手机号码有误，请重新输入");
					  $("#msgdiv:hidden").show();
					  return;
				  }else{
					  $("#msgdiv").html("信息发送失败，请稍后重试");
					  $("#msgdiv:hidden").show();
					  return;
				  }
			  } else {
				  setTimeout(dislock('link_getValidateCode'), 10*1000);
				   
				  var obj=eval("(" + result + ")");
				  //alert(obj.msg);
				  if(obj.msg=='success'){
					  $('#smsId').val(obj.smsId);
					  $("#msgdiv").html("验证码已发送，请注意查收");
					  $("#msgdiv:hidden").show();
					  return;
				  }else if(obj.msg=='phoneno error'){
					  $("#msgdiv").html("手机号码有误，请重新输入");
					  $("#msgdiv:hidden").show();
					  return;
				  }else{
					  $("#msgdiv").html("信息发送失败，请稍后重试");
					  $("#msgdiv:hidden").show();
					  return;
				  }
			  }
		   }
	});
}


/*异步验证短信验证码
 * isTimeLimit  是否有时间限制
 */
function confirm_validatecode_async(vCode, smsId, isTimeLimit){
	
	if(isTimeLimit == undefined)
		isTimeLimit = false;
	
	var url = contextRootPath + "/tool/smssendvalidatecode/confirmVCode.do";
	var data = "vCode=" + vCode + "&smsId=" + smsId; 
		
	$.ajax({
		type: "POST",
		   url: url,
		   data: data,
		   async: true,
		   success: function(result){
			  var obj=eval("(" + result + ")");
			  //alert(obj.msg);
			  if(obj.msg=='success'){
				 $('#confirm_vCode').val("true");
				 $('#tip_vcode').text('验证成功');
				 return;
			  } else if(obj.msg=='false'){
				  $('#confirm_vCode').val("false1");
				  if(isTimeLimit)
					  $('#tip_vcode').text('验证失败，验证码错误。剩余' + $("#r_time").val() + '秒有效');
				  else
					  $('#tip_vcode').text('验证失败，验证码错误');
				  return;
			  } else {
				  $('#confirm_vCode').val("false2");
				  $('#tip_vcode').text('验证失败，请重新获取验证码');
				  return;
			  }
		   }
	});
}


//倒计时
function getRTime(){
	
	var r_time = $("#r_time").val();
	var next_r_time = Number(r_time)-1;
	$("#r_time").val(next_r_time);
	
	var confirm_vCode = $('#confirm_vCode').val();
	
	if(next_r_time > 0) {
		
		if(confirm_vCode == "") {
			$('#tip_vcode').text('验证码已发送，' + next_r_time + '秒内有效，请注意查收');
			return;
		}
		
		if(confirm_vCode == "false1") {
			$('#tip_vcode').text('验证失败，验证码错误。剩余' + next_r_time + '秒有效');
			return;
		}
		
		if(confirm_vCode == "true" || confirm_vCode == "false2"  ) {
			dislock("link_getValidateCode");
			clearInterval(i);
			return;
		}
	}else {
		
		if(confirm_vCode == "true" || confirm_vCode == "false2"  ) {
			
		} else {
			$('#tip_vcode').text('验证失败，请重新获取验证码');
		}
		dislock("link_getValidateCode");
		clearInterval(i);
		return;
	}
}