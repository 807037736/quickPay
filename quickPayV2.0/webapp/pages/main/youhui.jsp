<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>精彩活动</title>
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
				href="${ctx }/pages/pa/buycheapcar/BuyCheepCarForWX_m.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv01.png" />
					</div>
					<h2>推荐购车优惠</h2>
					<p class="onlyheight"></p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="http://mp.weixin.qq.com/s?__biz=MjM5MjQyNTY2Mg==&mid=206273714&idx=1&sn=01604613c6ef5349f2dab09f2271b160#rd">
					<div class="img">
						<img src="../image/sv01.png" />
					</div>
					<h2>人保客户节！你投保，我买单！</h2>
					<p class="onlyheight">活动截止日期：2015年5月31日</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('活动已结束')">
					<div class="img">
						<img src="../image/sv02.png" />
					</div>
					<h2>天竺山自驾游</h2>
					<p class="onlyheight">活动截止日期：2015年3月5日</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('活动已结束')">
					<div class="img">
						<img src="../image/sv03.png" />
					</div>
					<h2>4S店特惠车型，年终大放送</h2>
					<p class="onlyheight">活动截止日期：2015年3月1日</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('活动已结束')">
					<div class="img">
						<img src="../image/sv04.png" />
					</div>
					<h2>加油卡送给您</h2>
					<p class="onlyheight">活动截止日期：2015年2月1日</p> <span
					class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>

		</ul>
	</div>
	 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>
</html>