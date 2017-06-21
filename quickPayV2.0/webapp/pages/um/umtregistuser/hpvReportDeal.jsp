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
	<title>HPV检测信息</title>	
</head>
<body>
<form name="fm"  method="post" id="fm">
	<div class="toolBar"><a href="javascript:backToWechat()" class="back"></a>HPV检测信息</div>
	<div class="prompt" style="display: none" id="msgdiv">提示框内容</div>
	
	<input type="hidden" id="param1" name="param1" value="${param1 }" />
	<input type="hidden" id="registSource" name="umTRegistuser.registSource" value="" />
	<input type="hidden" id="userType" name="umTRegistuser.userType" value="00" />
	<input type="hidden" id="activityname" value="用户注册" />
	<input type="hidden" id="smsId" />
	<input type="hidden" id="confirm_vCode" value="" />
	
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
					<input type="text" class="text idcard" placeholder="HPV码" name="identifyNumber" id="identifyNumber" onfocus="hideMsg();"/>
				</div>
			</div>
		</div>			
	</div>
	
	<div class="warm">
    <p class="warm_ico ta_c"><i class="i-warm"></i></p>
    <p>&#12288;&#12288;&#12288;尊敬的用户，您的HPV检测状态为:<br>&#12288;&#12288;&#12288;&#12288;&#12288;快递已签收;<br>&#12288;&#12288;&#12288;&#12288;&#12288;检测已完成。</p>
  	</div>
  	<div class="in_div2" style="margin-top:0px; margin-bottom:24px;"><input
					data-role="none" type="button" value="检测报告" id="nextSet"
					name="nextSet" class="jsbf xb_input"
					style="margin:10px auto; width:95%; background-image:none;">
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


$(function() {
	
	$('.xb').next().show();
	$('.xb').click(function(){
		$(this).toggleClass('add');
		$(this).next().slideToggle();
	});
	
});


</script>
</body>
</html>