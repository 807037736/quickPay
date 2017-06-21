<!doctype html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
<link href="${ctx }/pages/qp/weixin/resources/css/common.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/index.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/main.css" rel="stylesheet" >
<!-- 微信图片上传 -->
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/mui.min.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<script
	src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/myapp.js"></script>
<title>自助定责</title>
</head>
<body>
<form action="" method="post" id="subform" >
<header>
    <a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>上传照片
</header>

<c:if test="${errorMsg!=null&&errorMsg!=''}">
	<div class="caseDetail div-search">
		<input class="inputNo" readonly="readonly" style="color: red;" value="${errorMsg }" >
	</div>
</c:if>

<c:if test="${code==null || code==''}">

	<div id="tips" class="caseDetail div-search" <c:if test="${errorMsg==null || errorMsg==''}">style='display:none'</c:if> >
		<input id="tipsText" class="inputNo" style="color: red;" value="${errorMsg }" >
	</div>

<!--上传照片列表-->
	<input type="hidden" name="groupId" value="${groupId}" id="groupId">
	<input type="hidden" name="acciId" value="${acciId}" id="acciId">
	<div class="surveyUploadDiv">
		<c:if test="${not empty  filePathList }">
			<c:forEach var="q" items="${filePathList}" varStatus="status">
				<a class=picLink><img  src="${q.fileName }"  alt="${q.picOrder }" /></a> 
			</c:forEach>
		</c:if>
	</div>
	<div style="clear:both"></div>
	<div id="imgval"></div>
	<a href="javascript:void(0)" class="btn" style="margin-bottom: 15px;" id="chooseImage">添加图片</a>
	<a class="btn" style="margin-top: 0px" id="subPic" >上传</a>

</c:if>
</form>
</body>
<script type="text/javascript">
<%-- 初始化入口 --%>
$.ajax({
	async : false,
	url : '${ctx}/weixin/case/weixin.do',
	type : 'POST',
	data : {
		url : location.href.split('#')[0]
	},
	dataType : 'json',
	timeout : 1000,
	error : function() {
		alert('Error');
	},
	success : function(result) {
		wx.config({
			debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : result.appId, // 必填，公众号的唯一标识
			timestamp : result.timestamp, // 必填，生成签名的时间戳
			nonceStr : result.nonceStr, // 必填，生成签名的随机串
			signature : result.signature,// 必填，签名，见附录1
			jsApiList : [
			/*
			 * 所有要调用的 API 都要加到这个列表中
			 * 这里以图像接口为例
			 */
			'getLocation', 'chooseImage', 'uploadImage', 'downloadImage',
					'hideOptionMenu' ]
		});
	}
});
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideToolbar');
	WeixinJSBridge.call('hideOptionMenu');
})

wx.ready(function() {
	wx.hideOptionMenu();//隐藏分享等按钮
});

/******************************** 前5张图片 *********************************************/
var imgIndex = 0 ;
$("#chooseImage").click(function(){
	$("#tips").hide();
	wx.chooseImage( {
//				count : 1,//查勘员支持上传多张
//				isShowProgressTips: 1, //是否显示进度条
			success : function(res) {
				syncUpload(res.localIds , imgIndex);
			}
	});
});


var syncUpload = function(localIds, xh){
	var localId = localIds.pop();//当次上传图片的localId 取localIds最后一个参数 并从localIds中删除
		$(".surveyUploadDiv").append("<a class=picLink><img src="+localId+" id=img"+imgIndex+" ></a>");
		$("#imgval").append("<input type=hidden id=servId"+imgIndex+"  name=servId>");
	wx.uploadImage({//选择图片后自动上传到微信服务端
		localId : localId,
		success : function(res) {
			 $("#servId"+imgIndex).val(res.serverId);
			 imgIndex++;
			 if(localIds.length > 0){
		          syncUpload(localIds, imgIndex);
		      }else{
		    	  showTips("添加完成", 1500);
		      }
		},
		fail : function(res) {
			showTips("上传失败", 1000);
		}
	});
}

//提交图片
$("#subPic").click(function() {
	$("#tips").hide();
	$.ajax({
			url : '${ctx}/weixin/case/getPictureWeixinSurvry.do',
			async : false,
			type : 'POST',
			data : $("#subform").serialize(),
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
				if(data.code == -999){
					//返回注册界面
					window.location.href = "${ctx}/weixin/regist/personCenter.do?param=${param1}";
				}else if (data.code == -1) {
					showTips(data.msg, 2500);
					$("#tips").show();
					$("#tipsText").val(data.msg);
				} else {
					$("input[name=servId]").remove();
					showTips("上传成功", 2500);
// 					window.location.href = "${ctx}/weixin/case/getPictureWeixinSuccess.do"
				}
			},
			error : function(data) {
				showTips(data.msg, 3000);
			}
		});
	});
</script>
</html>