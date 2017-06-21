<!doctype html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>查勘员上传照片</title>
<link href="${ctx }/pages/qp/qpticpicturegroup/css/weixin/mui.min.css"
	rel="stylesheet" />
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/mui.min.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<script
	src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/myapp.js"></script>
<style type="text/css">
.mui-scroll-wrapper {
	top: 40px;
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
	width: 33.3333%;
	height: 80px;
	float: left;
	position: relative;
	padding-right: 5px;
}

.uploadImg .item .mask {
	position: absolute;
	top: 50%;
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
	height: 72px;
	margin-right: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.uploadImg .item.add {
	border: 1px solid #ccc;
	height: 72px;
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
a:link{
text-decoration:none;
}
a:visited{
text-decoration:none;
}
a:hover{
text-decoration:none;
}
a:active{
text-decoration:none;
}
</style>
</head>
<body>
	<c:if test="${uploadStatus != 2}">
		<form method="post" action="${ctx }/weixin/case/prepareSurveyView.do">
			<h4>认字编号</h4>
			<input type="text" name="qpTTPCase.caseSerialNo" value="${qpTTPCase.caseSerialNo }" >
			<c:if test="${errerMsg != '' }">
				<span><font color=red>${errerMsg }</font></span>
			</c:if>
			<br>
			<input type="submit" value="查询"  >
			
		</form>
		<c:if test = "${not empty  qpTTPCase && uploadStatus == 1}" >		
			<div class="msgTips">
				<h5>案件信息</h5>
				<ul class="mui-table-view">
					<s:iterator value="qpTTPCase">
						<li class="mui-table-view-cell">案件ID ${caseId }</li>
						<c:if test = "${not empty  qpTICAccident }" >
							<c:forEach var="b" items="${qpTICAccident}" varStatus="status">
								<c:if test = "${caseId == b.caseId }" >
									<li class="mui-table-view-cell" style="margin-left: 50px;">
										<a href="javascript:void(0);" onclick="UploadSurvey('${b.caseId}','${b.acciId}')" style="font-size: 14px;color:#333">
											${b.driverName }_${b.driverVehicleNumber }
										</a>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
					</s:iterator>
				</ul>
			</div>
		</c:if>
		<br><br>
		<div class="msgTips">
			<h5>温馨提示</h5>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">请单击要上传图片的当事人信息进行上传图片</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${uploadStatus == 2}">
		<div class="mui-content">
			<div class="mui-scroll-wrapper" style="top: 3px;">
				<div class="wxapi_container">
					<div id="item1" class="mui-control-content mui-active">
						<form action="kckpyw_submitPhoto_subInfo.action" method="post" id="subform">
							<input type="hidden" name="groupId" value="${groupId}" id="groupId">
							<input type="hidden" name="acciId" value="${acciId}" id="acciId">
							<div class="mui-content-padded">
								<h4>上传照片</h4>
								<div class="uploadImg" style="min-width:300px" id="imgs">
									<c:if test="${not empty  filePathList }">
										<c:forEach var="q" items="${filePathList}" varStatus="status">
											<div class="item"  >
												<img  src="${q.fileName }"  alt="${q.picOrder }" /> 
											</div>
										</c:forEach>
									</c:if>
									<div class="item add" id="chooseImage">
										<div class="mask">
											<span class="glyphicon glyphicon-plus" style="color: rgb(0, 0, 0);"></span>
											<p>添加照片</p>
										</div>
									</div>
								</div>
							</div>
							<div class="btns" style="clear: both;min-width:300px">
								<a id="subPic" class="mui-btn  mui-btn-block xb_input">提交</a>
							</div>
							<div class="msgTips" style="min-width:300px">
								<h5>温馨提示</h5>
								<ul class="mui-table-view">
									<li class="mui-table-view-cell">1、请您在确保自身安全的情况下进行拍照取证</li>
									<li class="mui-table-view-cell">2、事故照片拍照注意清晰度，便于快速划分责任</li>
								</ul>
							</div>
						</form>
					</div>
				</div>
				<div id="item3" class="mui-control-content"></div>
			</div>
		</div>
	</c:if>
<%-- 	</c:choose> --%>
</body>
<c:if test="${uploadStatus == 2}">
	<script type="text/javascript">
	
		<%-- 初始化入口 --%>
		$.ajax({
			async : false,
			url : '${ctx}/weixin/case/weixin.do',
			type : 'POST',
			data : {
				//fff
				//appId : 'wxc54c7a8be1edee7e',
				//appSecret : '0c4bb6b13470ce5f6191bfd391048992',
				//qiang
				appId : 'wxb620b2136e9f110e',
				appSecret : 'd4624c36b6795d1d99dcf0547af5443d',
				url : location.href.split('#')[0]
			},
			dataType : 'json',
			timeout : 1000,
			error : function() {
				alert('Error');
			},
			success : function(result) {
				wx.config({
					debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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
			wx.chooseImage( {
// 					count : 1,//查勘员支持上传多张
// 					isShowProgressTips: 1, //是否显示进度条
					success : function(res) {
						syncUpload(res.localIds , imgIndex);
					}
			});
		});
		
		
		
		var syncUpload = function(localIds, xh){
			var localId = localIds.pop();//当次上传图片的localId 取localIds最后一个参数 并从localIds中删除
			var bq = "<div class='item wxitem' id=chooseImage"+xh+"> <img src="+localId+" /> <input type=hidden id=servId"+xh+"  name=servId></div>";
			$("#chooseImage").before(bq);
// 			$("#chooseImage"+xh).attr("onclick", "chooseImg("+xh+")");
			wx.uploadImage({//选择图片后自动上传到微信服务端
				localId : localId,
				success : function(res) {
					 $("#servId"+xh).val(res.serverId);
					 imgIndex++;
					 $("#chooseImage" + xh).append("<span class='stats ok'></span>");
					 if(localIds.length > 0){
				          syncUpload(localIds, imgIndex);
				      }else{
				    	  showTips("添加完成", 1500);
				      }
				},
				fail : function(res) {
					showTips("上传失败", 1000);
					$("#mask" + xh).remove();
					$("#chooseImage" + xh).append("<span class='stats error'></span>");
				}
			});
		}
		
		//提交图片
		$("#subPic").click(function() {
			$.ajax({
					url : '${ctx}/weixin/case/getPictureWeixinSurvry.do',
					async : false,
					type : 'POST',
					data : $("#subform").serialize(),
					dataType : 'json',
					timeout : 1000,
					success : function(data) {
	// 					showTips("上传完成" + data.code, 1000);
						if (data.code == -1) {
							showTips(data.msg, 2500);
	// 						$("#mask" + xh).remove();
							$(".wxitem").find("span").removeClass("ok").addClass("error");
						} else {
							$("input[name=servId]").remove();
	// 						$("#mask" + xh).remove();
// 							$(".wxitem").append("<span class='stats ok'></span>");
// 							showTips(data.msg, 2500);
// 							$('#groupId').val(data.data);
							window.location.href = "${ctx}/weixin/case/getPictureWeixinSuccess.do"
						}
					},
					error : function(data) {
						showTips(data.msg, 3000);
						$("#mask" + xh).remove();
						$("#chooseImage" + xh).append("<span class='stats error'></span>");
					}
				});
			});
		
	</script>
</c:if>
<script type="text/javascript">
function UploadSurvey(caseId, acciId){
	console.log("案件ID：" + caseId + "||当事件ID：" + acciId);
// 		showTips("案件ID和当事人ID不能为空", 2000);
	if(caseId == '' || caseId == undefined || acciId == '' || acciId == undefined ){
		showTips("案件ID和当事人ID不能为空，请联系管理员", 2000);
	}
	window.location.href = "${ctx}/weixin/case/prepareSurveyUploadPictureGroup.do?acciId=" + acciId;
}
</script>

</html>