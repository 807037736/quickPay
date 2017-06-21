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
	<div class="toolBar"><a href="javascript:goback()" class="back"></a>HPV采集提示</div>
	<div class="main">
		 <div class="warm">
    		<p class="warm_ico ta_c"><i class="i-ok"></i><strong class="wran_font">恭喜您提交成功！</strong></p>
  		</div>			
	</div>
	<div class="in_div" style="margin-top:15px; margin-bottom:0px;">
        <input data-role="none" type="button" id="preiumsum" value="我知道了" class="jsbf3 xb_input" style="float:right;" onclick="backToWechat();"> 
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

function goback() {
	window.history.go(-1);
}
</script>
</body>
</html>