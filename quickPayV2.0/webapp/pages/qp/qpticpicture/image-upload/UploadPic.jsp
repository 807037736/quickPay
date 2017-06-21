<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase" %>
<html>
<head>
<title>图片上传</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<%-- <script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script> --%>
<%-- <link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet"> --%>
<link rel="stylesheet" type="text/css" href="${ctx }/pages/qp/qpticpicture/image-upload/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/pages/qp/qpticpicture/image-upload/style.css" />
 <link rel="stylesheet" type="text/css" href="${ctx}/pages/qp/qpticpicturegroup/css/photoswipe.css"> 
<link rel="stylesheet" type="text/css" href="${ctx}/pages/qp/qpticpicturegroup/css/default-skin/default-skin.css"> 


<script type="text/javascript">
	$(function(){
		jQuery.fn.extend({delayLoading:function(a){function g(d){var b,c;if(a.container===undefined||a.container===window){b=$(window).scrollTop();c=$(window).height()+$(window).scrollTop()}else{b=$(a.container).offset().top;c=$(a.container).offset().top+$(a.container).height()}return d.offset().top+d.height()+a.beforehand>=b&&c>=d.offset().top-a.beforehand}function h(d){var b,c;if(a.container===undefined||a.container===window){b=$(window).scrollLeft();c=$(window).width()+$(window).scrollLeft()}else{b=$(a.container).offset().left;
		c=$(a.container).offset().left+$(a.container).width()}return d.offset().left+d.width()+a.beforehand>=b&&c>=d.offset().left-a.beforehand}function f(){e.filter("img["+a.imgSrcAttr+"]").each(function(d,b){if($(b).attr(a.imgSrcAttr)!==undefined&&$(b).attr(a.imgSrcAttr)!==null&&$(b).attr(a.imgSrcAttr)!==""&&g($(b))&&h($(b))){var c=new Image;c.onload=function(){$(b).attr("src",c.src);a.duration!==0&&$(b).hide().fadeIn(a.duration);$(b).removeAttr(a.imgSrcAttr);a.success($(b))};c.onerror=function(){$(b).attr("src",
		a.errorImg);$(b).removeAttr(a.imgSrcAttr);a.error($(b))};c.src=$(b).attr(a.imgSrcAttr)}})}a=jQuery.extend({defaultImg:"",errorImg:"",imgSrcAttr:"originalSrc",beforehand:0,event:"scroll",duration:"normal",container:window,success:function(){},error:function(){}},a||{});if(a.errorImg===undefined||a.errorImg===null||a.errorImg==="")a.errorImg=a.defaultImg;var e=$(this);if(e.attr("src")===undefined||e.attr("src")===null||e.attr("src")==="")e.attr("src",a.defaultImg);f();$(a.container).bind(a.event,function(){f()})}});
	})
</script>   
  
</head>
<script type="text/javascript">
$(function(){
	$(".queryImg").delayLoading({
		defaultImg: "${ctx }/pages/qp/qpticpicturegroup/images/loading.jpg",           // 预加载前显示的图片
		errorImg: "",                        // 读取图片错误时替换图片(默认：与defaultImg一样)
		imgSrcAttr: "originalsrc",           // 记录图片路径的属性(默认：originalSrc，页面img的src属性也要替换为originalSrc)
		beforehand: 0,                       // 预先提前多少像素加载图片(默认：0)
		event: "scroll",                     // 触发加载图片事件(默认：scroll)
		duration: "normal",                  // 三种预定淡出(入)速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认:"normal"
		container: window,                   // 对象加载的位置容器(默认：window)
		success: function (imgObj) { },      // 加载图片成功后的回调函数(默认：不执行任何操作)
		error: function (imgObj) { }         // 加载图片失败后的回调函数(默认：不执行任何操作)
	});
})

	function delImg(obj){
		//影藏图片	
		//执行删除--图片
// 		alert("您单击了删除" + $(obj).attr("value"));
		 $.ajax({
			type : "POST",
			url : '${ctx}/qp/qpticpicture/deleteQpTICPicture.do?picId=' + $(obj).attr("value") + "&fileName=" + $(obj).attr("filename"),
			success : function(result) {
				var obj2 = eval("(" + result + ")");
				if(obj2.status == 0){
					$(obj).parent().parent('.imgDiv').hide();
					alert("删除成功");
				}else{
					alert(obj2.msg);
				}
// 				$.messager.alert('提示信息', obj2.msg, 'info');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('提示信息', '删除照片失败，请重试！', 'info');
			}
		});
	}
</script>


 <style>
.pnav{margin-top:30px;text-align:center;line-height:24px; font-size:16px}
.pnav a{padding:4px}
.pnav a.cur{background:#007bc4;color:#fff;}
.demo{width:80%; margin:10px auto}

/*必要样式*/
.my-gallery {
  width: 100%;
  float: left;
}
.my-gallery img {
  width: 150px;
  height: 110px;
}
.my-gallery figure {
  display: block;
  float: left;
  margin: 0 5px 5px 0;
  width: 150px;
}
.my-gallery figcaption {
  display: none;
}
.xb_panel{
	font-size:16px;
	color:#FFF;
	border:none;
	border-radius:5px;
	-moz-border-radius:5px;
	-webkit-border-radius:5px;
	background:#08CFA8;
	font-family: "Microsoft Yahei";
	-webkit-appearance: none !important; 
	}
img{
	height: 200px;
	width: 350px;
}
	
.imgDiv{
	display : inline-block;
	position : relative;
}

.imgDiv .delete{
	position : absolute;
	top : -10px;
	right : -3px;
	width : 20px;
	height : 20px;
}
	
</style>

<body>
	<input type="hidden" value ='${dataTime }' id="dataTime">
	<input type="hidden" value ='${uploadImgUrl }' id="uploadImgUrl">
	<input type="hidden" value ='${imgUser }' id="imgUser">
	<input type="hidden" value ='${imgHeader }' id="imgHeader">
	<input type="hidden" value ='${mac }' id="mac">
	<div id="wrapper" >
			<div id="container">
				<div class="panel xb_panel " >
					<div class="panel-heading">
						<h4 class="panel-title">图片组号${SurveyGroupId }</h4>
					</div>
					<div class="panel-body" >
						<div id="main">
							<div class="demo"  >
								<c:if test="${empty filePathList }"><h3>暂无图片&nbsp;</h3></c:if>
								<input type="hidden" name="groupId" value="${SurveyGroupId}" id="groupId">
								<div class="my-gallery">
									<s:iterator value="filePathList">
										<div class="imgDiv" >
											<figure  >
												<img class="queryImg" originalsrc="${imgHeader }${fileName}" style="z-index:0" alt="Image description" />
												<img  class="delete" value='${id.picId}' filename='${fileName }' onclick="delImg(this)"  style="width: 20px; height: 20px;vertical-align: middle;border: 0;" src="${ctx }/pages/qp/qpticpicture/image-upload/close.png" >
												<figcaption itemprop="caption description">${picDesc}</figcaption>
											</figure>
										</div>
							 		</s:iterator>
								</div>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


    <div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->
			<input type="hidden" name="accidentAcciId" id="accidentAcciId" value="${acciId }">
			<input type="hidden" name="surveyGroupId" id="surveyGroupId" value="${SurveyGroupId}">
            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ctx }/pages/qp/qpticpicture/image-upload/jquery.js"></script>
    <script type="text/javascript" src="${ctx }/pages/qp/qpticpicture/image-upload/webuploader.js"></script>
    <script type="text/javascript" src="${ctx }/pages/qp/qpticpicture/image-upload/upload.js"></script>
</body>
</html>
