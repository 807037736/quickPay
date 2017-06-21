<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>理赔服务</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<link href="css/cate6_0.css" rel="stylesheet" type="text/css" />

<style>
  img.img-thumbnail {
  	max-height:26px;
  	width:auto;
  	height:auto;
  }
.todayList li.only4 a {
	padding: 5px 40px 4px 5px;
}

.todayList li .img {
	border-radius: 3px;
	height: 60px; /*图片高度*/
	width: 60px; /*图片宽度*/
	margin: 0 10px 0 0;
}

.todayList li img {
	width: 60px; /*图片宽度*/
}

.todayList li p.onlyheight {
	height: 32px; /*分类描述*/
}

.todayList li.only4 p {
	white-space: normal;
}
</style>

</head>
<body id="cate4">
	<div id="todayList">
		<ul class="todayList" style="margin-left: 0px;padding-left: 0px;">
				<li class="only4" style="padding: 10px 0 0 0"><a
				href="${ctx }/pages/wx/wxcarregist/regist.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv01.png" />
					</div>
					<h2>车险报案</h2>
					<p class="onlyheight">私家车小事故现场微信报案：便捷、省心、高效</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('功能建设中，敬请期待')">
					<div class="img">
						<img src="../image/sv02.png" />
					</div>
					<h2>自助查勘</h2>
					<p class="onlyheight">报案现场自助拍照，上传通过即刻开车走人，详细操作请回复12</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="${ctx }/pages/wx/wxjiuyuan/jiuyuan.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv03.png" />
					</div>
					<h2>免费救援</h2>
					<p class="onlyheight">半路车没油了，找我深圳人保免费救援</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>

		</ul>
	</div>
<jsp:include page="./bottomback.jsp"></jsp:include>
</body>
</html>