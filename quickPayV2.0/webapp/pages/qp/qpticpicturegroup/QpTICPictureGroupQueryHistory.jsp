<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<script
	src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
.xb_panel {
	font-size: 16px;
	color: #FFF;
	border: none;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	background: #08CFA8;
	font-family: "Microsoft Yahei";
	-webkit-appearance: none !important;
	margin: 0rem;
}

</style>

</head>
<body>
	<form name="fm" id="fm" role="form">
		<div id="wrapper" style="margin:8px ;border: 0px" >
			<div id="container" >
				<div class="panel  xb_panel" id="uploadFile">
					<div class="panel-heading">
						<h4 class="panel-title">历史图片&nbsp;</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" style="overflow-x:hidden;">
							<c:choose>
								<c:when test="${empty qpTICPictureGroupList }">
									<h4 class="panel-title" style="text-align: center;">暂无上传记录&nbsp;</h4>
								</c:when>
								<c:otherwise>
									<table class="table table-condensed">
										<!--    <caption>精简表格布局</caption> -->
										<thead>
											<tr>
												<th style="text-align: center;">事故现场编号</th>
												<th style="text-align: center;">上传时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${qpTICPictureGroupList }" var="picGroup">
												<tr>
													<td>
<%-- 														 <a style="cursor:pointer;text-decoration: underline;" href="" onclick="openViewPicture('${picGroup.id.groupId }')">${picGroup.id.groupId }</a> --%>
														<input type="button" value="${picGroup.id.groupId }" name="uploadButton">
													</td>
													<td><fmt:formatDate
															value="${picGroup.uploadTimeForHis }"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:otherwise>
							</c:choose>

							<br>
							<div class="btn-group" style="text-align: center;">
								<button type="button" class="btn btn-default btn-sm  xb_panel"
									data-toggle="tooltip" data-placement="right" data-role="none"
									title="上传图片" id="uploadPicture">
									<span class="glyphicon glyphicon-upload"></span> 上传图片
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	// /** 打开查看图片窗口 */
	// $("input[name=uploadButton]").click(function() {
	// 	var url="${ctx}/qp/qpticpicturegroup/viewPictureGroup.do?groupId=" + $(this).val();
	// 	window.open(url,"_blank",'height=600,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');

	// });
<%-- 初始化入口 --%>
	$.ajax({
				async : false,
				url : '${ctx}/qp/qpticpicturegroup/weixin.do',
				type : 'POST',
				data : {
					// 					appId : 'wx737f5443b8ea85aa',
					// 					appSecret : 'b89f12a66755e79921ebde59771917c0',
					appId : 'wxc54c7a8be1edee7e',
					appSecret : '0c4bb6b13470ce5f6191bfd391048992',
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
						"chooseImage", "previewImage", "uploadImage",
								"downloadImage" ]
					});

					//定义images用来保存选择的本地图片ID，和上传后的服务器图片ID
					var images = {
						localId : [],
						serverId : [],
						imgUrls : []
					};
					wx.ready(function() {
						// 在这里调用 API

						$("input[name=uploadButton]").click(function() {
							$.ajax({
									async : false,
									url : '${ctx}/qp/qpticpicturegroup/viewPictureGroup.do',
									type : 'POST',
									data : {
										'groupId' : $(this).val()
									},
									dataType : 'json',
									timeout : 1000,
									error : function() {
										alert('Error');
									},
									success : function(result) {
										//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
										var curWwwPath = window.document.location.href;
										//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
										var pathName = window.document.location.pathname;
										var pos = curWwwPath
												.indexOf(pathName);
										//获取主机地址，如： http://localhost:8083
										var localhostPaht = curWwwPath.substring(0, pos);
										for ( var i = 0; i < result.length; i++) {
											images.imgUrls[i] = localhostPaht + result[i];
										}
										wx.previewImage({
													current : images.imgUrls[0], // 当前显示图片的http链接
													urls : images.imgUrls
												// 需要预览的图片http链接列表
												});
										images.imgUrls = [];
									}

								});

							});

					});
				}
			});

	$(function() {
		/** 打开上传图片窗口 */
		$("#uploadPicture")
				.click(
						function() {
							var url = "${ctx}/qp/qpticpicturegroup/prepareUploadPictureGroup.do";
							window
									.open(
											url,
											"_blank",
											'height=600,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');

						});
	});

	/** 打开查看图片窗口 */
	// function openViewPicture(groupId) {
	// var url="${ctx}/qp/qpticpicturegroup/viewPictureGroup.do?groupId=" + groupId;
	// window.open(url,"_blank",'height=600,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	// }
</script>
</html>
