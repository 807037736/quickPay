<!doctype html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>自助定责</title>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/mui.min.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/myapp.js"></script>
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
<link href="${ctx }/pages/qp/weixin/resources/css/common.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/main.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/index.css" rel="stylesheet" >
</head>
<body>
<form action="">
		<header>
			<a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>拍照上传
		</header>
		
		<input type="hidden" autocomplete="off" name="groupId" value="${accidentGroupId}" id="groupId">
		<input type="hidden" autocomplete="off" name="param" value="${param1}" id="param">
		
		<div class="photo-tips">
			<h3>温馨提示</h3>
			<p>1、请您在确保自身安全的情况下进行拍照取证</p>
			<p>2、事故照片拍照注意清晰度，便于快速划分责任</p>
		</div>
		
		<div class="upload-step">
			<h3>请按照指引完成以下操作</h3>
			<ul>
				<li>
					<p>第一步：从车头方向拍摄（含车牌，道路标线）</p>
					<section class="item">
						<div>
							<img id="y1" src="${ctx}/pages/qp/weixin/resources/img/picture_1.jpg"/>
						</div>
						<div id="chooseImage1" class="before-upload">
							<img id="photo1" style="display: none;" src="" />
							<input type="hidden" id="servId1" name="servId">
							<img id="reelect1" class="after-upload-tip" style="display: none;"  src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
						</div>
					</section>
				</li>
				<li>
				    <p>第二步：从车尾方向拍摄（含车牌，道路标线）</p>
					<section class="item">
						<div>
							<img id="y2" src="${ctx}/pages/qp/weixin/resources/img/picture_2.jpg"/>
						</div>
						<div id="chooseImage2" class="before-upload">
							<img id="photo2" style="display: none;" src="" />
							<input type="hidden" id="servId2" name="servId">
							<img id="reelect2"  class="after-upload-tip"  style="display: none;"  src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
						</div>
					</section>
				</li>
				<li>
					<p>第三步：近距离擦碰点拍摄</p>
					<section class="item">
						<div><img id="y3" src="${ctx}/pages/qp/weixin/resources/img/picture_3.jpg"/></div>
						<div id="chooseImage3" class="before-upload">
							<img id="photo3" style="display: none;" src="" />
							<input type="hidden" id="servId3" name="servId">
							<img id="reelect3"  class="after-upload-tip"  style="display: none;"  src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
						</div>
					</section>
				</li>
				<li>
					<p>第四步：本方驾照和行驶证</p>
					<section class="item">
						<div><img id="y4" src="${ctx}/pages/qp/weixin/resources/img/picture_4.jpg"/></div>
						<div id="chooseImage4" class="before-upload">
							<img id="photo4" style="display: none;" src="" />
							<input type="hidden" id="servId4" name="servId">
							<img id="reelect4"  class="after-upload-tip"  style="display: none;"  src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
						</div>
					</section>
				</li>
				<li>
					<p>第五步：对方驾照和行驶证</p>
					<section class="item">
						<div><img id="y5" src="${ctx}/pages/qp/weixin/resources/img/picture_5.jpg"/></div>
						<div id="chooseImage5" class="before-upload">
							<img id="photo5" style="display: none;" src="" />
							<input type="hidden" id="servId5" name="servId">
							<img id="reelect5"  class="after-upload-tip"   style="display: none;" src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
						</div>
					</section>
				</li>
			</ul>
		</div>
		<section class="other-photo">
			<h3>其他照片</h3>
			<ul>
				<li id="add"><div class="other-photo-upload"></div></li>
			</ul>
		</section>
		
		<a class="btn" id="subPic" >下一步</a>
	
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
	var images = {
		localId : [],
		serverId : []
	};
	var serv_id = "";
	wx.ready(function() {
		wx.hideOptionMenu();//隐藏分享等按钮
		$("#chooseImage1").attr("onclick", "chooseImg(1)");
		$("#reelect1").attr("onclick", "chooseImg(1)");
		$("#chooseImage2").attr("onclick", "chooseImg(2)");
		$("#reelect2").attr("onclick", "chooseImg(2)");
		$("#chooseImage3").attr("onclick", "chooseImg(3)");
		$("#reelect3").attr("onclick", "chooseImg(3)");
		$("#chooseImage4").attr("onclick", "chooseImg(4)");
		$("#reelect4").attr("onclick", "chooseImg(4)");
		$("#chooseImage5").attr("onclick", "chooseImg(5)");
		$("#reelect5").attr("onclick", "chooseImg(5)");
		$("#add").attr("onclick", "addImage()");
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
// 	<section class="item">
// 		<div><img id="y4" src="${ctx}/pages/qp/weixin/resources/img/picture_4.jpg"/></div>
// 		<div id="chooseImage4" class="before-upload">
// 			<img id="photo4" style="display: none;" src="" />
// 			<input type="hidden" id="servId4" name="servId">
// 			<img id="reelect4"  class="after-upload-tip"  style="display: none;"  src="${ctx}/pages/qp/weixin/resources/img/icon_camera.png"/>
// 		</div>
// 	</section>
	function chooseImg(xh) {
		wx.chooseImage( {
					count : 1,
					success : function(res) {
						images.localId = res.localIds;
						var imgHeight = $("#y1").height();
						$("#photo" + xh).attr("src", res.localIds[0]);//上传的图片 添加到要显示的区域
						$("#photo" + xh).attr("height", imgHeight);//高度设置成例图的高度
						$("#photo" + xh).show();//显示图片
						$("#chooseImage" + xh).removeAttr("onclick");//删除div单击选择图片事件
						$("#chooseImage" + xh).addClass("after-upload");//添加after-upload  重新图片样式
						$("#reelect" + xh).show();//显示出重新图片按钮
						wx.uploadImage({//选择图片后自动上传到微信服务端
									localId : images.localId[0],
									success : function(res) {
										$("#servId"+xh).val(res.serverId);
										$.ajax({
												url : '${ctx}/weixin/case/getPictureWeixin.do',
												type : 'POST',
												data : {
													serverid : res.serverId,
													groupId : $('#groupId').val(),
													param : '${param1}'
												},
												dataType : 'json',
												timeout : 1000,
												success : function(data) {
													if(data.msg == "-999"){
														window.location.href = "${ctx}/weixin/regist/personCenter.do?param=${param1}";
													}else if (data.msg == "error") {
														showTips("图片不合法", 1500);
														$("#mask" + xh).show();
													}if(data.msg == "-999"){
														showTips("未登录，请重新从菜单进入", 1500);
														$("#mask" + xh).show();
													}else {
														$("#mask" + xh).show();
														$('#groupId').val(data.msg);
													}
												},
												error : function(data) {
													showTips("图片不合法", 1500);
													$("#mask" + xh).show();
													$("#chooseImage" + xh).removeAttr("onclick");
												}
										});
									},
									fail : function(res) {
										showTips("上传失败", 1000);
										$("#mask" + xh).show();
									}
							});
					}
				});

	}
	
	//重选图片
	function reelect(xg){
		wx.chooseImage( {
			count : 1,
			success : function(res) {
				images.localId = res.localIds;
				var imgHeight = $("#y" + xh).height();
				$("#photo" + xh).attr("src", res.localIds[0]);//上传的图片 添加到要显示的区域
				$("#photo" + xh).attr("height", imgHeight);//高度设置成例图的高度
				wx.uploadImage({//选择图片后自动上传到微信服务端
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
											if(data.msg == "-999"){
												window.location.href = "${ctx}/weixin/regist/personCenter.do?param=${param1}";
											}else if (data.msg == "error") {
												showTips("图片不合法", 1500);
												$("#mask" + xh).show();
											} else {
												$("#mask" + xh).show();
												$('#groupId').val(data.msg);
											}
										},
										error : function(data) {
											showTips("图片不合法", 1500);
											$("#mask" + xh).show();
											$("#chooseImage" + xh).removeAttr("onclick");
										}
								});
							},
							fail : function(res) {
								showTips("上传失败", 1000);
								$("#mask" + xh).show();
							}
					});
			}
		});
	}
	/******************************** 自定义图片 *********************************************/
	var a = 6;
// 	function addImage() {//添加图片选择框
// 		if (a < 34) {
// 			$("#addImage").remove();
// 			$("#addimgs").append(
// 							'<div class="item add" id="chooseImage'
// 									+ a
// 									+ '" onclick="zdyImg('
// 									+ a
// 									+ ')"> <div class="mask" id="mask'+a+'" > <span class="glyphicon glyphicon-plus"></span> <p>自定义照片'
// 									+ (a - 5)
// 									+ '</p> </div> <img id="photo'+a+'" src="" /> <input type="hidden" id="servId'+a+'" name="servId"> </div>');
// 			a++;
// 		} else {
// 			showTips("最多上传28张自定义图片", 1000);
// 		}
// 	};
// <li><img src="${ctx}/pages/qp/weixin/resources/img/picture_4.jpg"/></li>
	function addImage() {
		if (a < 34) {
			wx.chooseImage( {
				count : 1,
				success : function(res) {
					images.localId = res.localIds;
					var img = "<li><img src="+res.localIds[0]+" /><input type=hidden id=servId"+a+" name=servId></li>";
					$("#add").before(img);
					
					wx.uploadImage({//选择图片后自动上传到微信服务端
								localId : images.localId[0],
								success : function(res) {
									$("#servId" + a).val(res.serverId);
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
												if(data.msg == "-999"){
													window.location.href = "${ctx}/weixin/regist/personCenter.do?param=${param1}";
												}else if (data.msg == "error") {
													showTips("图片不合法", 1000);
												} else {
													$('#groupId').val(data.msg);
												}
											},
											error : function(data) {
												showTips("图片不合法", 1000);
											}
										});
								},
								fail : function(res) {
									showTips("上传失败", 1000);
									$("#mask" + xh).show();
									$("#chooseImage" + xh).removeAttr("onclick");
								}
							});
				}
			});
		}else{
			showTips("最多上传28张自定义图片", 1000);
		}
		
	};
	//提交图片
	$("#subPic")
			.click(
					function() {
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

											location.href = 'javascript:history.go(-1)';
											location.reload();
										}if(data.code == -999){{
											showTips("未登录，请重新录取", 1000);
											return;
										}
										}else {
											showTips(data.msg, 1000);
											location.href = '${ctx}/weixin/case/carRemind.do?wxCaseId='+data.data+"&param=${param1}";
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