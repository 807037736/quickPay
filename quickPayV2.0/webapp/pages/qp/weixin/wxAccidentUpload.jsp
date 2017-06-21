<!doctype html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>自助定责</title>
<link href="${ctx }/pages/qp/qpticpicturegroup/css/weixin/mui.min.css" rel="stylesheet" />
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/mui.min.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/myapp.js"></script>
<style type="text/css">
.mui-scroll-wrapper {
	top: 20px;
	bottom: 0px;
	overflow: auto;
}

.btns {
	padding: 0px 10px;
}

select.mui-btn {
	font-size: 14px;
	padding: 11px;
}

.uploadImg {
	zoom: 1;
}

.uploadImg .item {
	width: 50%;
	height: 120px;
	float: left;
	position: relative;
	padding-right: 5px;
}

.uploadImg .item .mask {
	position: absolute;
	top: 49.5%;
	left: 0px;
	right: 5px;
	bottom: 0px;
	margin-top: -24px;
	text-align: center;
	color: #fff;
}

.uploadImg .item .mask .mui-icon {
	color: #fff;
}

.uploadImg .item .mask p {
	color: #fff;
	font-size: 11px;
	padding: none;
	margin: 0px;
	text-align: center;
	text-shadow: 0px 0px 1px rgba(0, 0, 0, 1);
}

.uploadImg .item img {
	width: 100%;
	height: 120px;
	margin-right: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.uploadImg .item.add {
	border: 1px solid #ccc;
	height: 120px;
	border-radius: 5px;
	margin-bottom: 10px;
}

.uploadImg .item.add .mask .mui-icon,.uploadImg .item.add .mask p {
	color: #333;
	text-shadow: none;
}

.uploadImg .item.add img {
	display: none;
}

.uploadImg .item span.stats {
	width: 32px;
	height: 32px;
	position: absolute;
	z-index: 99999;
	top: 50%;
	left: 50%;
	margin-top: -16px;
	margin-left: -16px;
	background:
		url(http://weixin.ahjtxx.com/wechat/static/ywbl/img/iconOk.png);
	background-size: 32px 32px;
}

.uploadImg .item .stats.ok {
	
}

.uploadImg .item .stats.error {
	background-image:
		url(http://weixin.ahjtxx.com/wechat/static/ywbl/img/iconError.png);
}

.msgTips {
	border-top: 1px solid #ccc;
	background: #f2f2f2;
}

.mui-table-view-cell {
	font-size: 14px;
}

.msgTips h5 {
	text-align: center;
	color: #333;
	font-size: 16px;
	padding: 5px 0px;
}

.stepTip {
	text-align: center;
	background: #fff;
}

.stepTip img {
	width: 100%;
	max-width: 480px;
}

.xb_input {
	height: 48px;
	font-size: 16px;
	color: #FFF;
	border: none;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	background: #08CFA8;
	width: 100%;
	font-family: "Microsoft Yahei";
	-webkit-appearance: none !important;
}
</style>
</head>
<body>
	<div class="mui-content">
		<div class="mui-scroll-wrapper">
			<div class="wxapi_container">
				<div id="item1" class="mui-control-content mui-active">
					<form action="kckpyw_submitPhoto_subInfo.action" method="post" id="subform">
						<input autocomplete="off" type="hidden" name="groupId" value="${accidentGroupId}" id="groupId">
						<div class="mui-content-padded">
							<h5>第一步：从车头方向拍摄（含车牌，道路标线）</h5>
							<div class="uploadImg" id="imgs">
								<div class="item" >
									<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p01.jpg" /> 
								</div>
								<div class="item" id="chooseImage1">
									<img id="photo1" /> 
									<input type="hidden" id="servId1" name="servId">
									<div class="mask" id="mask1">
										<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						
						<div class="mui-content-padded">
							<h5>第二步：从车尾方向拍摄（含车牌，道路标线）</h5>
							<div class="uploadImg" id="imgs">
								<div class="item" >
									<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p02.jpg"  /> 
								</div>
								<div class="item" id="chooseImage2">
									<img id="photo2" >
									<input type="hidden" id="servId2" name="servId">
									<div class="mask"  id="mask2">
										<span class="glyphicon glyphicon-plus"
											style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						
						<div class="mui-content-padded">
							<h5>第三步：近距离擦碰点拍摄</h5>
							<div class="uploadImg" id="imgs">
								<div class="item" >
									<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p03.jpg"  /> 
								</div>
								<div class="item" id="chooseImage3">
									<img id="photo3" >
									<input type="hidden" id="servId3" name="servId">
									<div class="mask"  id="mask3">
										<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						
						<div class="mui-content-padded">
							<h5>第四步：本方驾照行驶证</h5>
							<div class="uploadImg" id="imgs">
								<div class="item" >
									<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p06.jpg" /> 
								</div>
								<div class="item" id="chooseImage4">
									<img  id="photo4" >
									<input type="hidden" id="servId4" name="servId">
									<div class="mask"  id="mask4">
										<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						
						<div class="mui-content-padded">
							<h5>第五步：对方驾照行驶证</h5>
							<div class="uploadImg" id="imgs">
								<div class="item" >
									<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p07.jpg"  /> 
								</div>
								<div class="item" id="chooseImage5">
									<img  id="photo5" >
									<input type="hidden" id="servId5" name="servId">
									<div class="mask" id="mask5">
										<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						
						<div class="mui-content-padded">
							<h5>其他照片</h5>
							<div class="uploadImg" id="addimgs">
								<div class="item add" id="addImage">
									<div class="mask">
										<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
										<p>添加照片</p>
									</div>
								</div>
							</div>
						</div>
						
						<hr style="clear: both;">
						<div class="btns" style="clear: both;">
							<a id="subPic" class="mui-btn  mui-btn-block xb_input">提交</a>

						</div>
						<div class="msgTips">
							<h5>温馨提示</h5>
							<ul class="mui-table-view">
								<li class="mui-table-view-cell">1、请您在确保自身安全的情况下进行拍照取证</li>
								<li class="mui-table-view-cell">2、事故照片拍照注意清晰度，便于快速划分责任</li>
							</ul>
						</div>
						<br>
						<br> <a href="${ctx}/weixin/case/carRemind.do?wxCaseId=123456"
							class="mui-btn  mui-btn-block xb_input">下一步(挪车提醒-5)</a>

					</form>
				</div>
			</div>
			<div id="item3" class="mui-control-content"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
<%-- 初始化入口 --%>
	$.ajax({
		async : false,
		url : '${ctx}/weixin/case/weixin.do',
		type : 'POST',
		data : {
			//生产
			//     	appId:'wx737f5443b8ea85aa',
			//     	appSecret:'b89f12a66755e79921ebde59771917c0',
			//fff
// 			appId : 'wxc54c7a8be1edee7e',
// 			appSecret : '0c4bb6b13470ce5f6191bfd391048992',
			//qiang
			appId : 'wxb620b2136e9f110e',
			appSecret : 'd4624c36b6795d1d99dcf0547af5443d',
			url : window.location.host
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
	var images = {
		localId : [],
		serverId : []
	};
	var serv_id = "";
	wx.ready(function() {
		wx.hideOptionMenu();//隐藏分享等按钮
		$("#chooseImage1").attr("onclick", "chooseImg(1)");
		$("#chooseImage2").attr("onclick", "chooseImg(2)");
		$("#chooseImage3").attr("onclick", "chooseImg(3)");
		$("#chooseImage4").attr("onclick", "chooseImg(4)");
		$("#chooseImage5").attr("onclick", "chooseImg(5)");
		$("#addImage").attr("onclick", "addImage()");
	});
	//进入页面初始化表单
	window.onload = function() {
		document.getElementById("servId1").value = "";
		document.getElementById("servId2").value = "";
		document.getElementById("servId3").value = "";
		document.getElementById("servId4").value = "";
		document.getElementById("servId5").value = "";
	};
	/******************************** 前5张图片 *********************************************/
// 	<div class="item" >
// 		<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p01.jpg" /> 
// 	</div>
// 	<div class="item add" id="chooseImage1">
// 		<img src="http://weixin.ahjtxx.com/wechat/static/ywbl/img/p01.jpg" id="photo1" /> 
// 		<input type="hidden" id="servId1" name="servId">
// 		<div class="mask">
// 			<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
// 			<p>添加照片</p>
// 		</div>
// 	</div>
	function chooseImg(xh) {
		wx.chooseImage( {
					count : 1,
					success : function(res) {
						images.localId = res.localIds;
						$("#photo" + xh).attr("src", res.localIds[0]);
						wx
								.uploadImage({//选择图片后自动上传到微信服务端
									localId : images.localId[0],
									success : function(res) {
									$("#servId"+xh).val(res.serverId);
									$.ajax({
													url : '${ctx}/weixin/case/getPictureWeixin.do',
													type : 'POST',
													data : {
														serverid : res.serverId,
														groupId : $('#groupId').val()
													},
													dataType : 'json',
													timeout : 1000,
													success : function(data) {
														if (data.msg == "-999") {
															showTips("未登录,请重新录取案件", 1000);
															return;
														} else if (data.msg == "error") {
															showTips("图片不合法", 1000);
															$("#mask" + xh).remove();
															$("#chooseImage" + xh).append( "<span class='stats error'></span>");
														} else {
															$("#mask" + xh).remove();
															$("#chooseImage" + xh).append("<span class='stats ok'></span>");
															$('#groupId').val(data.msg);
														}
													},
													error : function(data) {
														showTips("图片不合法", 1000);
														$("#mask" + xh)
																.remove();
														$("#chooseImage" + xh)
																.append(
																		"<span class='stats error'></span>");
													}
												});
									},
									fail : function(res) {
										showTips("上传失败", 1000);
										$("#mask" + xh).remove();
										$("#chooseImage" + xh)
												.append(
														"<span class='stats error'></span>");
									}
								});
					}
				});

	}
	/******************************** 自定义图片 *********************************************/
	var a = 6;
	function addImage() {//添加图片选择框
		if (a < 34) {
			$("#addImage").remove();
			$("#addimgs").append(
							'<div class="item add" id="chooseImage'
									+ a
									+ '" onclick="zdyImg('
									+ a
									+ ')"> <div class="mask" id="mask'+a+'" > <span class="glyphicon glyphicon-plus"></span> <p>自定义照片'
									+ (a - 5)
									+ '</p> </div> <img id="photo'+a+'" src="" /> <input type="hidden" id="servId'+a+'" name="servId"> </div>');
			a++;
		} else {
			showTips("最多上传28张自定义图片", 1000);
		}
	};
	function zdyImg(xh) {
		wx.chooseImage( {
					count : 1,
					success : function(res) {
						images.localId = res.localIds;
						$('#photo' + xh).attr("src", res.localIds[0]);
						wx.uploadImage({//选择图片后自动上传到微信服务端
									localId : images.localId[0],
									success : function(res) {
										$("#servId" + xh).val(res.serverId);
										if ($("#addImage").val() != ""
												&& $("#chooseImage" + (xh + 1))
														.val() != ""
												&& $("#chooseImage" + (xh + 2))
														.val() != ""
												&& $("#chooseImage" + (xh + 3))
														.val() != ""
												&& $("#chooseImage" + (xh + 4))
														.val() != ""
												&& $("#chooseImage" + (xh + 5))
														.val() != ""
												&& $("#chooseImage" + (xh + 6))
														.val() != "") {
											$("#addimgs").append(
															'<div class="item add" id="addImage" onclick="addImage()"> <div class="mask" > <span class="glyphicon glyphicon-plus"></span> <p>添加照片</p> </div> </div>');
										}
										$.ajax({
													url : '${ctx}/weixin/case/getPictureWeixin.do',
													type : 'POST',
													data : {
														serverid : res.serverId,
														groupId : $('#groupId').val()
													},
													dataType : 'json',
													timeout : 1000,
													success : function(data) {
														if (data.msg == "error") {
															showTips("图片不合法", 1000);
															$("#mask" + xh).remove();
															$("#chooseImage" + xh).append("<span class='stats error'></span>");
														} else {
															$("#mask" + xh).remove();
															$("#chooseImage" + xh).append("<span class='stats ok'></span>");
															$("#chooseImage" + xh).removeClass("add");
															$('#groupId').val(data.msg);
														}
													},
													error : function(data) {
														showTips("图片不合法", 1000);
														$("#mask" + xh).remove();
														$("#chooseImage" + xh).append("<span class='stats error'></span>");
													}
												});
									},
									fail : function(res) {
										showTips("上传失败", 1000);
										$("#mask" + xh).remove();
										$("#chooseImage" + xh).append("<span class='stats error'></span>");
									}
								});
					}
				});
	};
	//提交图片
	$("#subPic").click(function() {
						if ($("#servId1").val() == null
								|| $("#servId1").val() == "") {
							showTips("请上传车头方向的照片", 1000);
						} else if ($("#servId2").val() == null
								|| $("#servId2").val() == "") {
							showTips("请上传车尾方向的照片", 1000);
						} else if ($("#servId3").val() == null
								|| $("#servId3").val() == "") {
							showTips("请上传近距离擦碰点照片", 1000);
						} else if ($("#servId4").val() == null
								|| $("#servId4").val() == "") {
							showTips("请上传本方驾驶证照片", 1000);
						} else if ($("#servId5").val() == null
								|| $("#servId5").val() == "") {
							showTips("请上传第二方驾驶证照片", 1000);
						} else {
							$.ajax({
									async : false,
									url : '${ctx}/weixin/case/updatePictureGroup.do',
									type : 'POST',
									data : {
										groupId : $('#groupId').val()
									},
									dataType : 'json',
									timeout : 1000,
									success : function(data) {
										if (data.code == -1) {
											showTips("上传失败", 1000);
											return;
										} else if (data.code == -999) {
											showTips("未登录", 1000);
											return;
										}else{
											showTips(data.msg, 1500);
											setTimeout(function(){
												window.location.href = '${ctx}/weixin/case/carRemind.do?wxCaseId='+data.data;
											},2000);
										}
									},
									error : function(data) {
										showTips("上传失败", 1000);
									}
							});

						}
					});
</script>
</html>