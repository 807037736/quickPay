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
	<title>个人信息</title>	
</head>
<body>
<form name="fm"  method="post" id="fm">
	<div class="toolBar"><a href="javascript:backToWechat()" class="back"></a>个人信息</div>
	<div class="prompt" style="display: none" id="msgdiv">提示框内容</div>
	
	<input type="hidden" id="param1" name="param1" value="${param1 }" />
	<input type="hidden" id="registSource" name="umTRegistuser.registSource" value="" />
	<input type="hidden" id="userType" name="umTRegistuser.userType" value="00" />
	<input type="hidden" id="activityname" value="个人信息" />
	<input type="hidden" id="smsId" />
	<input type="hidden" id="confirm_vCode" value="" />
	
	<div class="main">
		<div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_man1.png"></span>
				<div class="bg_white input_div">
					<input type="text" class="text name" readonly placeholder="姓名" name="umTRegistuser.userName" id="userName" value="${umTUserBindVo.userName }" />
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_ID1.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" readonly maxlength="18" placeholder="身份证号" name="identifyno" id="identifyno" value="${umTUserBindVo.identityNumber }"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_car.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" readonly maxlength="10" placeholder="车牌号" name="licenseno" id="licenseno" value="${umTUserBindVo.licenseNo }"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_addr.png"></span>
				<div class="bg_white input_div"> 
					<input type="text" class="text idcard" readonly placeholder="住址" name="postAddress" id="postAddress" value="${umTUserBindVo.postAddress }"/>
				</div>
			</div>
			<div class="input_box">
				<span class="icon"><img src="${ctx}/mweb/mweb_images/icon_phone1.png"></span>
				<div class="bg_white input_div">
					<input type="text" class="text mobile" readonly placeholder="手机号码" name="umTRegistuser.mobile" id="phoneNumber" value="${umTUserBindVo.mobileNo }"/>
				</div>
			</div>	
		</div>
		<div class="in_div" style="margin-top:50px; margin-bottom:0px;">
		    <input data-role="none" type="button" value="修改" class="xb_input" style="float:center; width:100%;" onclick="modify();">
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

<%-- 修改 --%>
function modify() {
	var param = document.getElementById("param1").value;
	var data = "openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6&param=" + param;
	var url = contextRootPath + "/um/umtregistuser/preEdit.do?"+data;
	window.open(url);
}

function upStep() {
	showLoading(false);
	fm.action=contextRootPath+"/wechat.do?index";
	fm.submit();
	showLoading(false);
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

</script>
</body>
</html>