<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBind"%>
<html>
<head>
<title>微信定责详情</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
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
});
</script>
<!--必要样式-->
<link rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/photoswipe.css"> 
<link rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/default-skin/default-skin.css"> 
<script src="${ctx}/pages/qp/qpticpicturegroup/js/photoswipe.min.js"></script> 
<script src="${ctx}/pages/qp/qpticpicturegroup/js/photoswipe-ui-default.min.js"></script> 
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
/*
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
 	*/
	img{
		height: 200px;
		width: 350px;
	}
.right_detail_top h3{
	font-size: 15px;
    font-weight: bold;
    float: left;
    height: 23px;
    margin: 5px 0 0 15px;
    border-left: 0px;
    color: #4075aa;
    text-indent: 10px;
    line-height: 25px;
    text-align: left;
}
#wrapper, div.second {
    margin-left: 0px; 
    margin-right: 0px; 
}
</style>
</head>
<body>
	<form action="" method="post">
	<input type="hidden" name="taskId" id="taskId" value="${taskId }">
	<div class="right_detail_top"><h3>微信案件信息 >>> <a target="_black" href="/qp/qpttpcase/prepareAdd.do?qpTTPCaseCaseId=${caseId }" >(查看详情)</a></h3></div>
		<div id="wrapper">
			<div id="container">
				<c:if test="${empty qpTTPCase }">
					<h2>案件不存在</h2>${caseId }
				</c:if>
				<c:if test="${not empty qpTTPCase }">
					<table class="fix_table">
						<tr>
	                        <td class="bgc_tt short">
	                        	事故时间：
	                        </td>
	                        <td  class="long">
	                       		<fmt:formatDate value="${qpTTPCase.caseTime }"  pattern="yyyy-MM-dd HH:mm:ss"  />
	                       	</td>
	                        <td class="bgc_tt short">
	                        	事故地点：
	                        </td>
							<td class="long">
								湖南省长沙市${qpTTPCase.caseStreet }
							</td>
						</tr>
						<tr>
							<td class="bgc_tt short">
		                		认字编号：
		                	</td>
		                	<td class="long">
								${qpTTPCase.caseSerialNo }
							</td>
							<td class="bgc_tt short">
<!-- 								<a href="javascript:void(0)" onclick=>查看详情</a> -->
							</td>
							<td class="long">
<%-- 								<fmt:formatDate value="${qpTTPCase.caseTime }"  pattern="yyyy-MM-dd HH:mm:ss"  /> --%>
							</td>
						</tr>	
					</table>
				</c:if>
			</div>
		</div>
	<c:if test="${not empty qpTTPCase }">	
		<!-- 当事人信息 -->
		<div class="right_detail_top"><h3>当事人信息</h3></div>
		<div id="wrapper">
			<c:if test="${empty qpTICAccidents }">
				<h3>&nbsp;&nbsp;&nbsp;&nbsp;无当事人</h3>
			</c:if>
			<c:forEach var="qpTICAccident" items="${qpTICAccidents}" varStatus="status">
				<div id="container">
						<h4 style="margin-left: 50px;">
							当事人
							<c:if test="${status.index+1 == 1}" >(甲)：</c:if>
							<c:if test="${status.index+1 == 2}" >(乙)：</c:if>
							<c:if test="${status.index+1 == 3}" >(丙)：</c:if>
							<c:if test="${status.index+1 == 4}" >(丁)：</c:if>
						</h4>
<!-- 						<hr style="margin-left: 50px;height: 0px; border:0.1px solid #666;"> -->
					<table class="fix_table">
						<tr>
	                        <td class="bgc_tt short">
	                        	姓名：
	                        </td>
	                        <td  class="long">
	                       		${qpTICAccident.driverName }
	                       	</td>
	                        <td class="bgc_tt short">
	                        	手机号码：
	                        </td>
							<td class="long">
								${qpTICAccident.driverMobile }
							</td>
						</tr>
						<tr>
							<td class="bgc_tt short">
		                		车牌号码：
		                	</td>
		                	<td class="long">
								${qpTICAccident.driverVehicleNumber } 
							</td>
							<td class="bgc_tt short">
								证件号码：
							</td>
							<td class="long">
								${qpTICAccident.driverIDNumber }
							</td>
						</tr>	
						<tr>
							<td class="bgc_tt short">
		                		保险公司：
		                	</td>
		                	<td class="long">
		                		<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
									 <c:if test="${qpTICCompany.coId==qpTICAccident.coId}">${qpTICCompany.coName}</c:if>
								</c:forEach>
								<c:if test="${'26'==qpTICAccident.coId}">-- ${qpTICAccident.companyNameOther}</c:if>
							</td>
							<td style="vertical-align: top" class="bgc_tt short">
		                		准驾车型：
		                	</td>
		                	<td class="long">
								<select id="permissionDriveType${status.index+1 }" name="permissionDriveType${status.index+1 }"  class="input_w w_30 easyui-combobox">
									<option value="0">请选择</option>
									<c:forEach var="permissionDriveType" items="${permissionDriveTypeList}">
										<option value="${permissionDriveType.codeCode}" 
											<c:if test="${qpTICAccident.permissionDrive == permissionDriveType.codeCode }"> selected </c:if>>${permissionDriveType.codeCode}-${permissionDriveType.codeCName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="bgc_tt short">
								<font color="red">*</font>&nbsp;违反法律法规：
							</td>
							<td class="long">
								<select id="qpTICAccidentDriverLawId${status.index+1 }" name="qpTICAccidentDriverLawId${status.index+1 }"  class="input_w w_30 easyui-combobox">
									<option value="0">请选择</option>
									<c:forEach var="qpTTPLaw" items="${qpTTPLawList}">
										<option value="${qpTTPLaw.lawId}" >${qpTTPLaw.lawWords}</option>
									</c:forEach>
								</select>
							</td>
	                        <td class="bgc_tt short">
	                        	<input type="hidden" name="acciId${status.index+1 }" value="${qpTICAccident.acciId }" >
								<font color="red">*</font>&nbsp;承担的责任:
	                        </td>
	                        <td  class="long">
	                       		<select id="responsibilitycode${status.index+1 }" name="responsibilitycode${status.index+1 }"  class="input_w w_30 easyui-combobox responsibilitycode">
	                					<option value="0">请选择</option>
		                				<c:forEach var="responsibilityTypeList" items="${responsibilityTypeList}">
		                					<option value="${responsibilityTypeList.codeCode}" <c:if test="${qpTICAccident.driverLiability==responsibilityTypeList.codeCode }"> selected </c:if>>${responsibilityTypeList.codeCName}</option>
		                				</c:forEach>
	                			</select>
	                       	</td>
	                    </tr>
					</table>
				</div>
			</c:forEach>
			<c:if test="${isDue==0 }">
				<div style="color: red;font-size: 30;text-align: center;" >当事人双方已自行确认责任,没有争议</div>
			</c:if>
			<c:if test="${isDue==1 }">
				<div style="color: red;font-size: 30;text-align: center;" >当事人双方对责任有争议,结果以定责为准</div>
			</c:if>
		</div>
			
		<div class="right_detail_top" ><h3>图片组号${accidentGroupId }</h3></div>
		<div id="wrapper">
			<div id="container">
				<div class="panel xb_panel "  style="margin-left: 0px;margin-bottom: 0px">
					<div class="panel-body" >
					<div id="main">
						<div class="demo"  >
							<c:if test="${empty filePathList }"><h3>暂无图片&nbsp;</h3></c:if>
							<hr>
							<div class="my-gallery">
								<s:iterator value="filePathList">
									<figure>
										<a href="${imgHeader}${fileName}" ><img originalsrc="${imgHeader}${fileName}" alt="Image description" /></a>
										<figcaption itemprop="caption description">${picDesc}</figcaption>
									</figure>
						 		</s:iterator>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	
            <div class="right_detail_top">
				<h3>责任认定</h3>
			</div>
			<div id="wrapper">
				<div id="container">
					<table class="fix_table">
						<tr>
							<td class="bgc_tt short" style="vertical-align: top">
								<font color="red">*</font>&nbsp;事故详情：
							</td>
							<td class="long">
								<select id="qpTTPCaseCaseNotesText" name="qpTTPCaseCaseNotesText" editable="false" class="input_w w_30 easyui-combobox">
									<option value="0" selected>请选择</option>
									<option value="变更车道" <c:if test="${qpTTPCase.accidentafter =='变更车道'}">selected</c:if>	title="变更车道"  >变更车道</option>    
									<option value="追尾" <c:if test="${qpTTPCase.accidentafter =='追尾'}">selected</c:if> 	title="追尾"  >追尾</option>    
									<option value="倒车" <c:if test="${qpTTPCase.accidentafter =='倒车'}">selected</c:if> 	title="倒车"  >倒车</option>    
									<option value="开关车门" <c:if test="${qpTTPCase.accidentafter =='开关车门'}">selected</c:if> 	title="开关车门"  >开关车门</option>    
									<option value="交叉路口未减速" <c:if test="${qpTTPCase.accidentafter =='交叉路口未减速'}">selected</c:if> 	title="交叉路口未减速"  >交叉路口未减速</option>    
									<option value="不按信号灯行驶" <c:if test="${qpTTPCase.accidentafter =='不按信号灯行驶'}">selected</c:if> 	title="不按信号灯行驶"  >不按信号灯行驶</option>    
									<option value="违反标志标线" <c:if test="${qpTTPCase.accidentafter =='违反标志标线'}">selected</c:if> 	title="违反标志标线"  >违反标志标线</option>    
									<option value="逆向行驶" <c:if test="${qpTTPCase.accidentafter =='逆向行驶'}">selected</c:if> 	title="逆向行驶"  >逆向行驶</option>    
									<option value="不按规定超车" <c:if test="${qpTTPCase.accidentafter =='不按规定超车'}">selected</c:if> 	title="不按规定超车"  >不按规定超车</option>    
									<option value="玩手机" <c:if test="${qpTTPCase.accidentafter =='玩手机'}">selected</c:if> 	title="玩手机"  >玩手机</option>    
									<option value="溜车" <c:if test="${qpTTPCase.accidentafter =='溜车'}">selected</c:if>	 	title="溜车"  >溜车</option>     
								</select> 
								<br> 
								<textarea style="margin-top: 8px; height: 50px;" id="qpTTPCaseCaseNotes" name="qpTTPCaseCaseNotes" rows="3" cols="50">${qpTTPCase.accidentafter }，造成甲乙两车受损的交通事故。</textarea>
							</td>
							<td class="bgc_tt short" style="vertical-align: top">
								<font color="red">*</font>&nbsp;调解结果：
							</td>
							<td class="long">
								<select id="qpTTPCaseCaseResultText" name="qpTTPCaseCaseResultText" editable="false" class="input_w w_30 easyui-combobox">
									<option value="0" selected>请选择</option>
									<option value="甲/乙两车同等责任。" title="甲/乙两车同等责任。">甲/乙两车同等责任。</option>
									<option value="甲/乙两车按责承担。" title="甲/乙两车按责承担。">甲/乙两车按责承担。</option>
									<option value="甲/乙两车车损由甲承担。" title="甲/乙两车车损由甲承担。">甲/乙两车车损由甲承担。</option>
									<option value="甲/乙两车车损由乙承担。" title="甲/乙两车车损由乙承担。">甲/乙两车车损由乙承担。</option>
								</select> 
								<br> 
								<textarea style="margin-top: 8px; height: 50px;" id="qpTTPCaseCaseResult" name="qpTTPCaseCaseResult" rows="3" cols="50">${qpTTPCase.caseResult}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="button" class="button_ty" id="btn" value="定 责" /> 
								<input type="button" id="addButton" onclick="toCancel();" class="button_ty" value="取 消">
								<input type="button" id="toUndo"  class="button_ty" value="撤 销 案 件">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</c:if>			
	</form>
	
		<!-- 图片预览 -->
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
			
</body>

<script type="text/javascript">
	function toCancel(){
		$.messager.confirm('提示', '您已领取该案件,取消后别人无法操作,是否确定取消?', function(r){
			if(r){
				window.location.href="${ctx}/weixin/qpweixincase/prepareQueryWxTask.do";
			}
		});
	}
	
	$("#toUndo").click(function(){
// 		$.messager.confirm('提示', "是否确定撤销案件?", function(r){
// 			if(r){
				$.messager.prompt('填写撤销原因', '请输入原因:', function(val){
					console.log(val)
					if(val != undefined){
						 $.ajax({
							   type: "POST",
							   url: "${ctx}/weixin/qpweixincase/prepareQueryCaseUndo.do?remark="+val,
							   dataType:'json', 
							   data : $("form").serialize(),
							   success: function(result){
								  if(result.code == -1){
									  $.messager.alert('提示信息',result.msg,'info');
									  return;
								  }else{
									  //成功  跳转到列表
									  $.messager.alert('提示信息',result.msg,'info', function(r){
										  window.location.href="${ctx}/weixin/qpweixincase/prepareQueryWxTask.do";
									  });
								  }
							   },
							   error:function(result){
								   $.messager.alert('提示信息','撤销失败,请稍后在试','info');
							   }
						 })
					}
				})
// 			}
// 		});
	});
	
	
	$("#btn").click(function(){
		for(var i=1; i <= 8; i++){
			if($("#responsibilitycode"+i).length == 0 ){
		    	   break;
	        } 
			var acciIndex = getTitleByIndex(i);
			var code = $("#responsibilitycode"+i).combobox('getValue');// 承担责任
			var DriverLawId = $("#qpTICAccidentDriverLawId"+i).combobox('getValue');// 法律法规
			
			if(code == 0){
				$.messager.alert('提示信息','请选择'+acciIndex+'承担的责任！	','info');
				return;
			}
			
			if(code != 14){
				if(DriverLawId == 0){
					$.messager.alert('提示信息','请选择'+acciIndex+'违反的法律法规！	','info');
					return;
				}
			}
		}
		var qpTTPCaseCaseNotes = $("#qpTTPCaseCaseNotes").val();//事故详情
		var qpTTPCaseCaseResult =  $("#qpTTPCaseCaseResult").val();//调试结果
		if($.trim(qpTTPCaseCaseNotes) == ""){
			$.messager.alert('提示信息','请选择事故详情！','info');
			return;
		}
		if($.trim(qpTTPCaseCaseResult) == ""){
			$.messager.alert('提示信息','请选择调试结果！','info');
			return;
		}
		
		$.messager.confirm('提示', '是否确认保存?', function(r){
			if(r){
				 $.ajax({
					   type: "POST",
					   url: "${ctx}/weixin/qpweixincase/prepareQueryCaseConfirm.do",
					   data : $("form").serialize(),
					   dataType:'json', 
					   success: function(result){
						   console.log(result);
							  if(result.code == -1){
								  $.messager.alert('提示信息',result.msg,'info');
								  return;
							  }else{
								  //成功  跳转到
								  $.messager.alert('提示信息',result.msg,'info', function(r){
									  window.location.href="${ctx}/weixin/qpweixincase/prepareQueryWxTask.do";
								  });
							  }
					   },
					   error:function(result){
						   alertErrorMsgForEasyUi(result);
					   }
				});
			}
		});
	})
	
		$("#qpTTPCaseCaseNotesText").combobox({
			onChange : function(n, o) {
				$("#qpTTPCaseCaseNotes").attr("value", n+"，造成甲乙两车受损的交通事故。");
			}
		});

		$("#qpTTPCaseCaseResultText").combobox({
			onChange : function(n, o) {
				$("#qpTTPCaseCaseResult").attr("value", n);
			}
		});
		
		function getTitleByIndex(index){
			 var title  = "";
		      if(index==1){
		    	  title = "当事人（甲）";
		      }else if(index==2){
		    	  title = "当事人（乙）";
		      }else if(index==3){
		    	  title = "当事人（丙）";
		      }else if(index==4){
		    	  title = "当事人（丁）";
		      }else if(index==5){
		    	  title = "当事人（戊）";
		      }else if(index==6){
		    	  title = "当事人（己）";
		      }else if(index==7){
		    	  title = "当事人（庚）";
		      }else if(index==8){
		    	  title = "当事人（辛）";
		      }
		      return title;
		};
		
		function toCaseInfo(caseId){
			alert(caseId);
		}
</script>

<script type="text/javascript">
var initPhotoSwipeFromDOM = function(gallerySelector) {

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
</html>
