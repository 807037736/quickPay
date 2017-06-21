<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<title>图片预览</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
<%-- 	<script src="${ctx}/pages/qp/qpticpicturegroup/js/jquery-1.6.js" type="text/javascript"></script> --%>
<script src="${ctx}/pages/qp/qpticpicturegroup/js/lyz.delayLoading.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	$("img").delayLoading({
		defaultImg: "${ctx}/pages/qp/qpticpicturegroup/images/loading.jpg",           // 预加载前显示的图片
		errorImg: "",                        // 读取图片错误时替换图片(默认：与defaultImg一样)
		imgSrcAttr: "originalSrc",           // 记录图片路径的属性(默认：originalSrc，页面img的src属性也要替换为originalSrc)
		beforehand: 0,                       // 预先提前多少像素加载图片(默认：0)
		event: "scroll",                     // 触发加载图片事件(默认：scroll)
		duration: "normal",                  // 三种预定淡出(入)速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认:"normal"
		container: window,                   // 对象加载的位置容器(默认：window)
		success: function (imgObj) { },      // 加载图片成功后的回调函数(默认：不执行任何操作)
		error: function (imgObj) { }         // 加载图片失败后的回调函数(默认：不执行任何操作)
	});
	
	/** 打开上传图片窗口 */
	$("#uploadPicture1").click(function() {
		var url="${ctx}/qp/qpticpicturegroup/prepareUploadPictureGroup.do?groupId=" + $("#groupId").val();
		window.open(url,"_blank",'height=600,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
				
			});
	
});
</script>
	
<!--必要样式-->
<link rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/photoswipe.css"> 
<link rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/default-skin/default-skin.css"> 
<style>
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

</style>
<script src="${ctx}/pages/qp/qpticpicturegroup/js/photoswipe.min.js"></script> 
<script src="${ctx}/pages/qp/qpticpicturegroup/js/photoswipe-ui-default.min.js"></script> 
</head>

<body >
<div id="wrapper" >
			<div id="container">
				<div class="panel xb_panel " >
							<div class="panel-heading">
								<h4 class="panel-title">图片组号${accidentGroupId }</h4>
							</div>
							<div class="panel-body" >
							<div id="main">
	<div class="demo"  >
<!-- 		<p style="font-size:16px; margin:20px auto">请点击图片观看大图</p> -->
<%-- <p  style="font-size:16px; margin:20px auto">图片组号${groupId }</p> --%>
		<c:if test="${empty filePathList }"><h3>暂无图片&nbsp;</h3></c:if>
		<input type="hidden"  autocomplete="off" name="groupId" value="${accidentGroupId}" id="groupId">
		<c:if test="${ pictureType == '01'}">
														<button type="button" class="btn btn-default btn-sm  xb_panel"
														data-toggle="tooltip" data-placement="right" data-role="none" title="添加图片"
														id="uploadPicture1" >
														<span class="glyphicon glyphicon-upload"></span> 添加图片													</button>
														</c:if>
														<hr>
		<div class="my-gallery">
		<s:iterator value="filePathList">
			<figure>
				<a href="${imgHeader }${fileName}" ><img originalsrc="${imgHeader }${fileName}" alt="Image description" /></a>
				<figcaption itemprop="caption description">${picDesc}</figcaption>
			</figure>
			 		</s:iterator>
		</div>
	
		<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="pswp__bg"></div>
			<div class="pswp__scroll-wrap">
				<div class="pswp__container">
					<div class="pswp__item"></div>
					<div class="pswp__item"></div>
					<div class="pswp__item"></div>
				</div>
				<div class="pswp__ui pswp__ui--hidden">
				
					<div class="pswp__top-bar">
						<div class="pswp__counter"></div>
						<button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
						<button class="pswp__button pswp__button--share" title="Share"></button>
						<button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
						<button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
						<div class="pswp__preloader">
							<div class="pswp__preloader__icn">
								<div class="pswp__preloader__cut">
									<div class="pswp__preloader__donut"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
						<div class="pswp__share-tooltip"></div> 
					</div>
					<button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
					<button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
					<div class="pswp__caption">
						<div class="pswp__caption__center"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
</div>
							
								</div>
								</div>
								</div>
								</div>


<script type="text/javascript">
var initPhotoSwipeFromDOM = function(gallerySelector) {

    // parse slide data (url, title, size ...) from DOM elements 
    // (children of gallerySelector)
    var parseThumbnailElements = function(el) {
        var thumbElements = el.childNodes,
            numNodes = thumbElements.length,
            items = [],
            figureEl,
            linkEl,
            size,
            item;

        for(var i = 0; i < numNodes; i++) {

            figureEl = thumbElements[i]; // <figure> element

            // include only element nodes 
            if(figureEl.nodeType !== 1) {
                continue;
            }

            linkEl = figureEl.children[0]; // <a> element
			imgEl = linkEl.children[0];
			var img = new Image();
			img.src = imgEl.getAttribute('src');

            // create slide object
            item = {
                src: linkEl.getAttribute('href'),
                w: img.width,
                h: img.height
            };



            if(figureEl.children.length > 1) {
                // <figcaption> content
                item.title = figureEl.children[1].innerHTML; 
            }

            if(linkEl.children.length > 0) {
                // <img> thumbnail element, retrieving thumbnail url
                item.msrc = linkEl.children[0].getAttribute('src');
            } 

            item.el = figureEl; // save link to element for getThumbBoundsFn
            items.push(item);
        }

        return items;
    };

    // find nearest parent element
    var closest = function closest(el, fn) {
        return el && ( fn(el) ? el : closest(el.parentNode, fn) );
    };

    // triggers when user clicks on thumbnail
    var onThumbnailsClick = function(e) {
        e = e || window.event;
        e.preventDefault ? e.preventDefault() : e.returnValue = false;

        var eTarget = e.target || e.srcElement;

        // find root element of slide
        var clickedListItem = closest(eTarget, function(el) {
            return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
        });

        if(!clickedListItem) {
            return;
        }

        // find index of clicked item by looping through all child nodes
        // alternatively, you may define index via data- attribute
        var clickedGallery = clickedListItem.parentNode,
            childNodes = clickedListItem.parentNode.childNodes,
            numChildNodes = childNodes.length,
            nodeIndex = 0,
            index;

        for (var i = 0; i < numChildNodes; i++) {
            if(childNodes[i].nodeType !== 1) { 
                continue; 
            }

            if(childNodes[i] === clickedListItem) {
                index = nodeIndex;
                break;
            }
            nodeIndex++;
        }



        if(index >= 0) {
            // open PhotoSwipe if valid index found
            openPhotoSwipe( index, clickedGallery );
        }
        return false;
    };

    // parse picture index and gallery index from URL (#&pid=1&gid=2)
    var photoswipeParseHash = function() {
        var hash = window.location.hash.substring(1),
        params = {};

        if(hash.length < 5) {
            return params;
        }

        var vars = hash.split('&');
        for (var i = 0; i < vars.length; i++) {
            if(!vars[i]) {
                continue;
            }
            var pair = vars[i].split('=');  
            if(pair.length < 2) {
                continue;
            }           
            params[pair[0]] = pair[1];
        }

        if(params.gid) {
            params.gid = parseInt(params.gid, 10);
        }

        return params;
    };

    var openPhotoSwipe = function(index, galleryElement, disableAnimation, fromURL) {
        var pswpElement = document.querySelectorAll('.pswp')[0],
            gallery,
            options,
            items;

        items = parseThumbnailElements(galleryElement);

        // define options (if needed)
        options = {

            // define gallery index (for URL)
            galleryUID: galleryElement.getAttribute('data-pswp-uid'),

            getThumbBoundsFn: function(index) {
                // See Options -> getThumbBoundsFn section of documentation for more info
                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                    pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                    rect = thumbnail.getBoundingClientRect(); 

                return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
            }

        };

        // PhotoSwipe opened from URL
        if(fromURL) {
            if(options.galleryPIDs) {
                // parse real index when custom PIDs are used 
                // http://photoswipe.com/documentation/faq.html#custom-pid-in-url
                for(var j = 0; j < items.length; j++) {
                    if(items[j].pid == index) {
                        options.index = j;
                        break;
                    }
                }
            } else {
                // in URL indexes start from 1
                options.index = parseInt(index, 10) - 1;
            }
        } else {
            options.index = parseInt(index, 10);
        }

        // exit if index not found
        if( isNaN(options.index) ) {
            return;
        }

        if(disableAnimation) {
            options.showAnimationDuration = 0;
        }

        // Pass data to PhotoSwipe and initialize it
        gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
        gallery.init();
    };

    // loop through all gallery elements and bind events
    var galleryElements = document.querySelectorAll( gallerySelector );

    for(var i = 0, l = galleryElements.length; i < l; i++) {
        galleryElements[i].setAttribute('data-pswp-uid', i+1);
        galleryElements[i].onclick = onThumbnailsClick;
    }

    // Parse URL and open gallery if it contains #&pid=3&gid=1
    var hashData = photoswipeParseHash();
    if(hashData.pid && hashData.gid) {
        openPhotoSwipe( hashData.pid ,  galleryElements[ hashData.gid - 1 ], true, true );
    }
};

// execute above function
initPhotoSwipeFromDOM('.my-gallery');
</script>

</body>
</html>
