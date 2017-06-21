<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>深圳人保欢迎您</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/metro-bootstrap.css">
<link rel="stylesheet" href="css/iconFont.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="js/Slides-3/css/slides.css">	

</head>
<body class="metro">
<input type="hidden" name="param1" id="param1" value="<%=request.getParameter("param")%>">
<!-- 图片轮播  start-->
	<div class="container">
	    <div id="slides">
	      <a href="http://www.epicc.com.cn/wapchexian/201505qianggou/index.html">
	      <img src="../image/2015qianggou.jpg"/></a>
		  <a href="http://www.epicc.com.cn/waplvyouxian/2015fangjiayouhaoli/">
		  <img src="../image/2015fangjiayouhaoli.jpg"/></a>
	    </div>
	</div>
 <!-- 图片轮播  end-->
 
		<div class="container">
			<div class="col-sm-4 col-xs-4">
				<div class="tile bg-amber" href="market.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-cart-2"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">保险商城</span>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-xs-4">
				<div class="tile   bg-darkBlue" href="policy.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-file-pdf"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">保单管理</span>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-xs-4">
				<div class="tile   bg-lightRed"  href="claim.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-wrench"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">理赔服务</span>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="col-sm-4 col-xs-4">
				<div class="tile   bg-amber" href="service.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-umbrella"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">生活服务</span>
					</div>
				</div>
			</div>
			<div class="col-sm-8 col-xs-8">
				
				<div class="tile double bg-darkViolet" href="dx.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-android"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">电商小帮手(电/网销专用)</span>
					</div>
				</div>
			</div>

		</div>

	<div class="container">
		<div class="col-sm-4 col-xs-4">
			<div class="tile   bg-cyan"  href="youhui.jsp?param=<%=request.getParameter("param")%>">
				<div class="tile-content icon">
					<i class="icon-accessibility"></i>
				</div>
				<div class="tile-status">
					<span class="label fg-white">精彩活动</span>
				</div>
			</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="tile   bg-red" href="ketang.jsp?param=<%=request.getParameter("param")%>">
				<div class="tile-content icon">
					<i class="icon-book"></i>
				</div>
				<div class="tile-status">
					<span class="label fg-white">保险学堂</span>
				</div>
			</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="tile   bg-amber" href="${pageContext.request.contextPath}/um/umtregistuser/personCenter.do?param=<%=request.getParameter("param")%>">
				<div class="tile-content icon" id="personId">
					<i class="icon-user" id="personIcon"></i>
				</div>
				<div class="tile-status">
					<span class="label fg-white" id="nickName">个人中心</span>
				</div>
			</div>
		</div>
	</div>
		<div class="container" style="padding-bottom:50px">
			<div class="col-sm-8 col-xs-8">
				<%-- <div class="tile double bg-darkViolet" href="dx.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-android"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">电商小助手</span>
					</div>
				</div> --%>
				<div class="tile double bg-darkViolet" href="packet.jsp?param=<%=request.getParameter("param")%>">
					<div class="tile-content icon">
						<i class="icon-gift-2"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">我的礼包</span>
					</div>
				</div> 
			</div>
			<div class="col-sm-4 col-xs-4">
				<div class="tile bg-amber" >
					<div class="tile-content icon">
						<i class="icon-busy"></i>
					</div>
					<div class="tile-status">
						<span class="label fg-white">功能上线中...</span>
					</div>
				</div>
			</div>

		</div>
<jsp:include page="./bottom.jsp"></jsp:include>
	

<script src="js/vendor/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery/jquery.widget.min.js"></script>
	<script src="js/Slides-3/jquery.slides.min.js"></script>
	<script src="js/metro/metro.min.js"></script>
	<script>
	/*图片轮播初始化*/
    $(function() {
      $('#slides').slidesjs({
        width: 640,
        height: 250,
        navigation: false,
        play: {
          auto: true
        }
      });
    });
	
    /*给div初始化事件*/
	$(document).ready(function(){
		$('.tile').unbind().bind('click', function() {
			if($(this).attr("href")!=null){
				window.location.href=$(this).attr("href");
			}
	    });
	});
	$(document).ready(function(){
		$('.element').unbind().bind('click', function() {
			if($(this).attr("href")!=null){
				window.location.href=$(this).attr("href");
			}
	    });
	});
	</script>
	
<script type="text/javascript">
var ctx = "${pageContext.request.contextPath}";
	function Ajax(url,data){
	    this.url=url;
	    this.data=data;
	    this.browser=(function(){  return "other";  })();
	};
	Ajax.prototype={
	    get:function(){
	        var result;
	        var xmlhttp = new XMLHttpRequest();
	        xmlhttp.onreadystatechange=function(){
	            result = xmlhttp.responseText;//闭包，不能采用this.属性
	        };
	        xmlhttp.open('GET',this.url+'?'+this.data,false);//
	        xmlhttp.send(null);
	        return result;
	    } 
	};
		var carNum = 0;
		window
				.addEventListener(
						"DOMContentLoaded",
						function() {
							var p = document.getElementById('param1').value;
							var a = new Ajax(ctx+"/um/umtuserbind/queryUserInfo.do","param="+p);
							var obj=JSON.parse(a.get());
							var personIcon = document.getElementById("personIcon");
							var personIdObj = document.getElementById("personId");
							personIdObj.removeChild(personIcon);
							var img=document.createElement('img');
							img.setAttribute("src", obj.headImg);
							img.setAttribute("class", "img-circle");
							personIdObj.appendChild(img);
							document.getElementById ("nickName").innerHTML = obj.nickName;
							if(obj.msgCount!=0){
								document.getElementById ("msgCount").innerHTML = obj.msgCount;
							}
						}, false);

	</script>
</body>
</html>
