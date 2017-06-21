<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs_m.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@ include file="/common/meta_m_css.jsp"%>
	<style type="text/css">
		.panneli img {
			width: 100%;
			max-width: 50%
		}
		#flightNo,#engineNo,#frameNo {
			text-transform : uppercase;
		}
	</style>
	<title>用户注册</title>	
</head>
<body>
<form name="fm"  method="post" id="fm">
	<div class="toolBar"><a href="javascript:backToWechat()" class="back"></a>用户注册</div>
	<div class="prompt" style="display: none" id="msgdiv">提示框内容</div>
	
	<input type="hidden" id="param1" name="param1" value="${param }" />
	<input type="hidden" id="registSource" name="umTRegistuser.registSource" value="" />
	<input type="hidden" id="userType" name="umTRegistuser.userType" value="00" />
	<input type="hidden" id="activityname" value="用户注册" />
	<input type="hidden" id="smsId" />
	<!-- <input type="hidden" id="confirm_vCode" value="" /> -->
	
	<div class="main">
		<div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_man1.png"></span>
				<div class="bg_white input_div">
					<input type="text" class="text name" placeholder="姓名" name="umTRegistuser.userName" id="userName" onfocus="hideMsg();"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_ID1.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" maxlength="18" placeholder="身份证号" name="identifyno" id="identifyno" onfocus="hideMsg();"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_car.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" maxlength="10" placeholder="车牌号" name="licenseno" id="licenseno" value="${licenseno }" onfocus="hideMsg();"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_addr.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" placeholder="住址" name="postAddress" id="postAddress" value="${postAddress }" onfocus="hideMsg();"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_phone1.png"></span>
				<div class="bg_white input_div">
					<input type="text" class="text mobile" placeholder="手机号码" name="umTRegistuser.mobile" id="phoneNumber" onfocus="hideMsg();"/>
				</div>
			</div>	
			
<%-- 			<div class="input_box_mobile">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_code.png"></span>
				<div class="bg_white input_div">
					<input type="text" class="text mobile" name="confirm_vCode" id="confirm_vCode" placeholder="验证码" onfocus="hideMsg();"/>
				</div>
			</div>	
			<input data-role="none" id="link_getValidateCode" class='xb_input3' style="margin-left:3px;width:29% !important;"  type="button" value="获取验证码" > --%>
		</div>
		<div class="in_div" style="margin-top:50px; margin-bottom:0px;">
		    <input data-role="none" type="button" value="注册" class="xb_input" style="float:center; width: 100%" onclick="registUser();">
		    <!-- <input data-role="none" type="button" value="重置" class="xb_input" style="float:right;" onclick="registClear();">  -->
		</div>				
	</div>
</form>

<%@ include file="/common/meta_m_js.jsp"%>
<script type="text/javascript" src="${ctx }/widgets/form_validate.js"></script>
<script type="text/javascript" src="${ctx }/widgets/validatecode.js"></script>
<script type="text/javascript">

<%-- 返回微信窗口 --%>
function backToWechat(){
	WeixinJSBridge.invoke('closeWindow',{},function(res){
	    //alert(res.err_msg);
		//关闭成功返回“close_window:ok”，关闭失败返回“close_window:error”
	});
}

 <%-- 消息提示框隐藏 --%>
function hideMsg() {
	$("#msgdiv").hide();
}

<%-- 重置 --%>
function registClear() {
	hideMsg();
	document.getElementById('userName').value = "";
	document.getElementById('identifyno').value = "";
	document.getElementById('phoneNumber').value = "";
	document.getElementById('validate_code').value = "";
}



<%-- 注册 --%>
function registUser() {
	var userName = $('#userName').val();
	if(userName=='' || !nameValidate(userName)) {
		$("#msgdiv").html("请填写正确的姓名！");
		$("#msgdiv:hidden").show();
		//fm.userName.focus();
		return;
	}
	var identifyno = $('#identifyno').val();
	if(identifyno=='' || isCardID(identifyno)!=''){
		$("#msgdiv").html("请填写正确的身份证号！");
		$("#msgdiv:hidden").show();
		//fm.userName.focus();
		return;
	}
	var licenseno = $('#licenseno').val();
	if(licenseno==null || licenseno==''){
		$("#msgdiv").html("请填写车牌号码！");
		$("#msgdiv:hidden").show();
		//fm.userName.focus();
		return;
	}
	var postAddress = $('#postAddress').val();
	if(postAddress==null || postAddress==''){
		$("#msgdiv").html("请填写住址！");
		$("#msgdiv:hidden").show();
		//fm.userName.focus();
		return;
	}
	
	var phoneNumber = $('#phoneNumber').val();
	if(phoneNumber==null || phoneNumber==''){
		$("#msgdiv").html("请填写手机号码！");
		$("#msgdiv:hidden").show();
		return;
	}
	
    //校验,车牌号且电话号未注册，则进行注册操作
	var checkData = "licenseno="+$("#licenseno").val() + "&mobileno="+$("#phoneNumber").val();
	$.ajax({
		url:contextRootPath+"/um/umtregistuser/check.do",
		type:"POST",
		data: checkData,
		dataType:"html",
		async: true,
		success:function(msg){
			if(msg=='Y'){
				$("#msgdiv").html("车牌号与手机号已同时注册!");
				$("#msgdiv:hidden").show();
			}else{
				showLoading(false);
				fm.action = contextRootPath+"/um/umtregistuser/add.do";
				fm.submit(); 
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
        	$("#msgdiv").html("提交后台发生错误，请重试！");
			$("#msgdiv:hidden").show();
		}
	});

}


//验证姓名只能包含中文、英文和空格，且不能中英文混搭
function nameValidate(str) {
	var reg1 = /^[\u4e00-\u9fa5]+$/; 	//中文，空格
	var reg2 = /^[a-zA-Z ]+$/; 	        //英文，空格

	if(!reg1.test(str)){	
		if(!reg2.test(str)) {
			return false;
		}
	}
	return true;
}

function upStep() {
	showLoading(false);
	fm.action=contextRootPath+"/wechat.do?index";
	fm.submit();
	showLoading(false);
}


//验证手机号码
function mobileValidate(str){
	var myreg = /^(0?(13|14|15|18))\d{9}$/;
	if(!myreg.test(str)){
	      return false;
	}
	return true;
}

function preSendVCode() {
	
	var phoneNo = $('#phoneNumber').val();
	var customerno = "";
	var customername = "";
	var activitycode = "";
	var activityname = $('#activityname').val();
	var remark = "";
	
	if(phoneNo==null || phoneNo.trim()=="") {
		$("#msgdiv").html("手机号不能为空，请填写真实号码！");
		$("#msgdiv:hidden").show();
		//fm.phoneNumber.focus();
		return;
	}
	if(!mobileValidate(phoneNo.trim())) {
		$("#msgdiv").html("手机号不正确，请重新填写！");
		$("#msgdiv:hidden").show();
		//fm.phoneNumber.focus();
		return;
	}
	
	hideMsg();
	var rtn = '0';
	var url = contextRootPath + "/um/umtregistuser/checkMobile.do";
	var data = "mobileno=" + phoneNo ; 
	showLoading(false);
	$.ajax({  
        type:"POST",  
        url:url,  
        async: true,
        cache : false, //缓存
        data: data,
        global:false,   
        success: function(result){
        	hideLoading();
        	var obj=eval("(" + result + ")");
			  //alert(obj.msg);
			  if(obj.msg=='false'){
				  rtn = "1";
				  sendVCode(activitycode, activityname, customerno, customername, phoneNo, remark);
			  } else if(obj.msg=='success'){
				  rtn = "0";
				  $("#msgdiv").html("该手机号已经注册过！");
				  $("#msgdiv:hidden").show();
				  return;
			  } 
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	hideLoading();
        	$("#msgdiv").html("提交后台发生错误，请重试！");
			$("#msgdiv:hidden").show();
		}
    });
	
	checkMobile(phoneNo);
	if(rtn=='1'){
		
	}
	return;
}


/*
 * 判断是否已经注册过
 */
function checkMobile(mobileno){
	var url = contextRootPath + "/um/umtregistuser/checkMobile.do";
	var data = "mobileno=" + mobileno ; 
	showLoading(false);
	$.ajax({
		type: "POST",
		   url: url,
		   data: data,
		   async: true,
		   cache : false, //缓存
		   global:false,
		   success: function(result){
			  hideLoading();
			  var obj=eval("(" + result + ")");
			  //alert(obj.msg);
			  if(obj.msg=='false'){
				  rtn = "1";
			  } else if(obj.msg=='success'){
				  $("#msgdiv").html("该手机号已经注册过！");
				  $("#msgdiv:hidden").show();
				  rtn = "0";
				  return ;
			  } 
		   },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	hideLoading();
	        	$("#msgdiv").html("提交后台发生错误，请重试！");
				$("#msgdiv:hidden").show();
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


$(function() {
	
	$("#link_getValidateCode").on("click",function () { 
		preSendVCode();
	});
	
	$("#validate_code").on("blur",function () { 
	    if($('#validate_code').val() != "")
	    	preConfirmVCode();
	});
	
	$('.xb').next().show();
	$('.xb').click(function(){
		$(this).toggleClass('add');
		$(this).next().slideToggle();
	});
	
});

function isCardID(sId){  
    if(!(sId.length >=15 && sId.length <=18)){
        return "身份证长度错误！";  
    }
    switch(sId.length){
        case 15:
            return "";
            break;
        case 18:
              var iSum=0 ;  
              if(!/^\d{17}(\d|x)$/i.test(sId)){
                  return "身份证长度或格式错误！";  
              }
              sId=sId.replace(/x$/i,"a");  
              sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));  
              var d=new Date(sBirthday.replace(/-/g,"/")) ;  
              if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
                  return "身份证上的出生日期非法！";  
              }
              for(var i = 17;i>=0;i --) {
                  iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
              }
              if(iSum%11!=1){
                  return "身份证号非法！";  
              }
              return "";
           break;
       default:   
           return ""; 
    }
 }

</script>
</body>
</html>