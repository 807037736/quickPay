<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<title>图片预览</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/jquery.js"></script>
<link type="text/css" href="${ctx}/pages/qp/qpticpicturegroup/css/prettyPhoto.css" rel="stylesheet" />

<link type="text/css" href="${ctx}/pages/qp/qpticpicturegroup/css/style.css" rel="stylesheet" />

</head>

<body>
		<!--图片滚动-->
	<script type="text/javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/script.js"></script>

<div class="infopic">
	<div class="picbox">
		<ul class="gallery piclist">
			<s:iterator value="filePathList">
			<li><a href="${ctx}${fileName}" rel="prettyPhoto[]"><img src="${ctx}${fileName}" /></a></li>
					</s:iterator>
		</ul>
		<c:if test="${empty filePathList }"><h3>暂无图片&nbsp;</h3></c:if>
		图片组号${groupId }
	</div>
	<div class="pic_prev"></div>
	<div class="pic_next"></div>
</div><!--infopic end-->
   	
   	<script type="text/javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/jquery.prettyphoto.js"></script>
   	<script type="text/javascript">
$(document).ready(function(){	
	$("area[rel^='prettyPhoto']").prettyPhoto();
	$(".gallery:first a[rel^='prettyPhoto']").prettyPhoto({animation_speed:'fast',slideshow:10000, hideflash: true});
});
</script>
</body>
</html>
