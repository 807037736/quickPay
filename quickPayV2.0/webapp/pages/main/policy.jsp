<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>保单管理</title>
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
				href="${ctx }/um/umtregistuser/personCenter.do?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv01.png" />
					</div>
					<h2>保单查询</h2>
					<p class="onlyheight">绑定之后查看已投保车险保单</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="${ctx }/pages/wx/wxordercheck/checkOrder.jsp?param=<%=request.getParameter("param")%>">
					<div class="img">
						<img src="../image/sv02.png" />
					</div>
					<h2>保单验真</h2>
					<p class="onlyheight">仅限手机碎屏险保单验真</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('功能开发中，敬请期待')">
					<div class="img">
						<img src="../image/sv03.png" />
					</div>
					<h2>资料修改</h2>
					<p class="onlyheight">手机号变了，收不到保险提醒，我要修改手机号</p> <span class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>
			<li class="only4" style="padding: 10px 0 0 0"><a
				href="javascript:alert('功能开发中，敬请期待')">
					<div class="img">
						<img src="../image/sv04.png" />
					</div>
					<h2>新车上牌</h2>
					<p class="onlyheight">摇号摇累了，朕不去营业厅了，直接用微信把我的牌照更新</p> <span
					class="icon">&nbsp;</span>
					<div class="clr"></div>
			</a></li>

		</ul>
	</div>
	 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>
</html>