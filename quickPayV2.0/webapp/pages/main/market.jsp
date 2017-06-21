<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>保险商城</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<link href="css/cate6_0.css" rel="stylesheet" type="text/css" />
<%-- <link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css"
	rel="stylesheet">
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script> --%>
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
				href="iframe.jsp?url=http://www.epicc.com.cn/wap/views/proposal/E/EAK_X/EAK_XIndex.jsp&param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv01.png" />
					</div>
					<h2>国内自驾游保险</h2>
					<p class="onlyheight">适用年龄:1-70周岁，保险期限：1-30天</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="iframe.jsp?url=http://www.epicc.com.cn/wap/views/proposal/E/EAK_U/EAK_UIndex.jsp&param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv02.png" />
					</div>
					<h2>国内旅游保险</h2>
					<p class="onlyheight">适用年龄:1-70周岁，保险期限：1-30天</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="iframe.jsp?url=http://www.epicc.com.cn/wap/views/proposal/E/EJQ_Z/EJQ_ZIndex.jsp&param=<%=request.getParameter("param")%>">
					<div class="img">

						<img src="../image/sv03.png" />
					</div>
					<h2>交通工具意外险</h2>
					<p class="onlyheight">适用人群：经常出差及旅行人士，适用年龄：18-75周岁，保险期限：1天-1年，最高保额：262万</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="iframe.jsp?url=http://www.epicc.com.cn/wap/views/proposal/E/EJQ_H/EJQ_HIndex.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv04.png" />
					</div>
					<h2>航空意外年度险</h2>
					<p class="onlyheight">适用人群：经常出差及旅行人士，适用年龄：0-75周岁，保险期限：12个月，最高保额：200万</p> <span
					class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only5" style="padding: 10px 0 0 0"><a
				href="iframe.jsp?url=http://www.epicc.com.cn/wap/views/proposal/Y/YEJ/YEJIndex.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv05.png" />
					</div>
					<h2>国内公路随车行李保险</h2>
					<p class="onlyheight">还在为车内行李遭遇盗窃、损坏等意外担心吗？行李保险网上开卖了 本产品将为车内行李提供全面的赔偿保障，是您的出行好伴侣。</p> <span
					class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>

		</ul>
	</div>
	 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>
</html>