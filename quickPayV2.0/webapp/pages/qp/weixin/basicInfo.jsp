<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.WxCase"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<title>自助定责</title>
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
<script language="javascript" src="${ctx }/pages/qp/weixin/resources/js/zepto.min.js"></script> 
<link href="${ctx }/pages/qp/weixin/resources/css/common.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/index.css" rel="stylesheet" >
<link href="${ctx }/pages/qp/weixin/resources/css/main.css" rel="stylesheet" >
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=iLCaxMjThCmlliN78uu3tbjv"></script>
<%@ include file="dataJs.jsp"%>
</head>
<style>
.anchorBL{display:none}
</style>
<script>
		//日期选取空间
        $(function () {

            function init() {
                // Date & Time demo initialization
                $('#accidentdate').mobiscroll().datetime({
                    theme: '',     // Specify theme like: theme: 'ios' or omit setting to use default 
                    mode: 'scroller',       // Specify scroller mode like: mode: 'mixed' or omit setting to use default 
                    display: 'bottom', // Specify display mode like: display: 'bottom' or omit setting to use default 
                    lang: 'zh',       // Specify language like: lang: 'pl' or omit setting to use default
                    minDate: new Date(2000,1,1,0,0),  // More info about minDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-minDate
                    maxDate: new Date(2099,12,31,23,59),   // More info about maxDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-maxDate
                    stepMinute: 5  // More info about stepMinute: http://docs.mobiscroll.com/2-14-0/datetime#!opt-stepMinute
//                     dateFormat: 'yy-mm-dd HH:ii:ss'
                });
            }    
            init();
        });
    </script>
    
<body >
	<div class="identify-tips" style=""></div>
	<form method="post" id="fm" >
		<input type="hidden" value="${wxCaseId }" name="wxCaseId" id="wxCaseId">
		<input type="hidden" name="param" id="param" value="${param1 }">
		<header>
			<a onclick="javascript:window.history.go(-1);" class="leftBtn"></a>基本信息
		</header>
		<div style="margin: 20px 15px 0px;font-size: 1.5rem;color:red;">
			<span id="errormsg">
				${errormsg }
			</span>
		</div>
		
		
		<div class="caseDetail ">
			<table>
				<tr>
					<td class="basicInfo-caseName">事故时间</td>
					<td><input  autocomplete="off" class="basicInfo-input" readonly="readonly" type="text" id="accidentdate" name="wxCase.accidentdate"  placeholder="请选择事故时间" value="<fmt:formatDate value="${wxCase.accidentdate }" pattern="yyyy-MM-dd HH:mm:ss" />" /></td>
				</tr>
				<tr>
					<td>天气</td>
					<td class="tipText" id="aeathercode"><span>请选择天气</span>
						<img src="${ctx}/pages/qp/weixin/resources/img/icon_in_arrow.png" />
						<input  autocomplete="off" type="hidden" name="wxCase.weathercode" id="wxCaseWeathercode">
					</td>
				</tr>
				<tr>
					<td>事故形态</td>
					<td class="tipText" id="accidentform"><span>请选择事故形态</span>
						<img src="${ctx}/pages/qp/weixin/resources/img/icon_in_arrow.png" />
						<input  autocomplete="off" type="hidden"  name="wxCase.accidentform" id="wxCaseAccidentform">
					</td>
				</tr>
				<tr>
					<td>事故地点</td>
					<td>
						<input autocomplete="off"  type="hidden" id="province" name="wxCase.province" placeholder="省" value="${wxCase.province }" />
						<input autocomplete="off"  type="hidden" id="city" name="wxCase.city" placeholder="市" value="${wxCase.city }" />
						<input autocomplete="off"  type="hidden" id="area" name="wxCase.area" placeholder="区" value="${wxCase.area }" />
						<input autocomplete="off"  type="hidden" id="address" name="wxCase.address" placeholder="请输入事故地点" value="${wxCase.address }" />
						<input autocomplete="off" class="basicInfo-input"  type="text" name="showAddress" id="showAddress" readonly="readonly"  placeholder="请输入事故地点" value="${wxCase.province }${wxCase.city }${wxCase.area }${wxCase.address }" />
					</td>
				</tr>
			</table>
		</div>
	     <input type="hidden" id="x" value="${x}" />
		<input type="hidden" id="y" value="${y}"/>
		<div  id="containerMap1" style="width: 100%; height: 300px;margin-top: 20px;"></div>
		
	    <a class="btn"  <c:if test="${errormsg==null}">id="subBasicInfo"</c:if> style="margin-top: 15%">下一步<img src="${ctx}/pages/qp/weixin/resources/img/icon_next_arrow.png"/></a>
		
		<!--80%透明度遮罩层 正常隐藏-->
		<div class="modal-bg"></div>
		<!--天气弹出框 正常隐藏-->
		<div class="choose-box" id="tq">
			<h3><span class="tqwcClose"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a  class="tqwcClose">完成</a></h3>
			<ul>
				<c:forEach var="weather" items="${weatherList}" >
					<li value="${weather.codeCode}">${weather.codeCName}</li>
				</c:forEach>
			</ul>
		</div>
		<!--事故形态弹出框 正常隐藏-->
		<div class="choose-box" id="xt">
			<h3><span class="xtwcClose"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a  class="xtwcClose">完成</a></h3>
			<ul>
				<li value="相撞，造成甲乙两车受损的交通事故">碰撞</li>
				<li value="追尾，造成甲乙两车受损的交通事故">追尾</li>
				<li value="刮擦，造成甲乙两车受损的交通事故">刮擦</li>			
			</ul>
		</div>
		</form>
</body>


<script type="text/javascript">

	
// 	searchByStationName("广东深圳");
	
$(function(){
	
// 	window.addEventListener( "load", function() {
// 	    FastClick.attach( document.body );
// 	}, false );
// 	newFastClick(document.body);
	$("#subBasicInfo").click(function(){
		var weathercode = $("#wxCaseWeathercode").val();
		var accidentform = $("#wxCaseAccidentform").val();
		var showAddress = $("#showAddress").val();
		if(showAddress == ""){
			showTip("请选择事故地址");
			return;
		}
		if(weathercode == ""){
			showTip("请选择天气");
			return;
		}
		if(accidentform == ""){
			showTip("请选择事故形态");
			return;
		}
		$.ajax({
			url: "${ctx }/weixin/case/saveBasicInfo.do",
			type: "post",
			dataType: "json",
			data: $("#fm").serialize(),
			success: function(data) {
				if(data.code == -999){
					window.location.href = "${ctx}/weixin/regist/personCenter.do?param=${param1}";
				}else if(data.code == -1){
					showTip(data.msg);
				}else{
					//成功跳转
					window.location.href="${ctx}/weixin/case/prepareAccidentEdit.do?wxCaseId=${wxCaseId}&param=${param1}";
				}
			},
			error :function(data){
				showTip(data.msg);
			}
		});
	});
	
	$("#toNext").click(function(){
		window.location.href = "${ctx}/weixin/case/prepareAccidentEdit.do";
	});
	
	//单击选择天气
	$("#aeathercode").click(function(){
		$(".modal-bg").show();
		$("#tq").show();
	});
	
	//单击选择事故形态形态
	$("#accidentform").click(function(){
		$(".modal-bg").show();
		$("#xt").show();
	});
	
	$("#tq").find("li").click(function(){
		$("#aeathercode").find("span").text($(this).text());
		$("#wxCaseWeathercode").val($(this).attr("value"));
		$("#tq").find("li").removeClass("active");
		$(this).addClass("active");
		$(".modal-bg").hide();
		$("#tq").hide();
		$("#aeathercode").find("span").css("color", "#333333");
	});
	
	$(".tqwcClose").click(function(){
		$(".modal-bg").hide();
		$("#tq").hide();
	});
	
	$("#xt").find("li").click(function(){
		$("#accidentform").find("span").text($(this).text());
		$("#wxCaseAccidentform").val($(this).attr("value"));
		$("#xt").find("li").removeClass("active");
		$(this).addClass("active");
		$(".modal-bg").hide();
		$("#xt").hide();
		$("#accidentform").find("span").css("color", "#333333");
	});
	
	$(".xtwcClose").click(function(){
		$(".modal-bg").hide();
		$("#xt").hide();
	});
	
	function showTip(content){
		$(".identify-tips").text(content);
		$(".identify-tips").fadeIn();
		setTimeout(function(){
			$(".identify-tips").fadeOut();
		}, 1500);
	}
	console.log($("#x").val());
	
	var map = new BMap.Map("containerMap1");
	map.setDefaultCursor("url('bird.cur')"); //设置地图默认的鼠标指针样式
	map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用

	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl()); //右下角

	var localSearch = new BMap.LocalSearch(map);
	localSearch.enableAutoViewport(); //允许自动调节窗体大小
	
	
	function searchByStationName() {
		detailAddress = $("#showAddress").val();
		map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function(searchResult) {
			console.log(searchResult);
			var poi = searchResult.getPoi(0);
			if(poi != undefined){
				map.centerAndZoom(poi.point, 17);
				var marker = new BMap.Marker(new BMap.Point(poi.point.lng,poi.point.lat)); // 创建标注，为要查询的地方对应的经纬度
				map.addOverlay(marker); // 将标注添加到地图中
				$("#x").val(poi.point.lng); //经度
				$("#y").val(poi.point.lat); //纬度
				//点击地图获取点击的经纬度......
			}else{
				showTip("无法定位到该地址");
			}
		});
		localSearch.search(detailAddress);
	}
	
	//输入文本框的值查询地图，不保存坐标
	 function searchIMapAddress(IMapAddress){  
	        map.clearOverlays();//清空原来的标注
	        localSearch.setSearchCompleteCallback(function(rs){  
	            if (localSearch.getStatus() == BMAP_STATUS_SUCCESS){  
	                    for(var j=0;j<rs.getCurrentNumPois();j++)  
	                    {  
	                        var poi=rs.getPoi(j);  
	                        map.centerAndZoom(poi.point, 11);
	                        map.addOverlay(new BMap.Marker(poi.point)); //如果查询到，则添加红色marker  
	                        //document.getElementById("txtResult").value+= poi.title+":" +poi.point.lng+","+poi.point.lat+'\n';  
	                    }  
	                   /*  if(rs.getPageIndex!=rs.getNumPages())    
	                    {    
	                    	localSearch.gotoPage(i);  
	                        i=i+1;  
	                    }   */
	         }});
	        localSearch.search(IMapAddress);
	 }  
	
	var opts = {
			width : 150, // 信息窗口宽度
			height : 30, // 信息窗口高度
			title : "", // 信息窗口标题
			enableMessage : true,//设置允许信息窗发送短息
			message : "地址无法显示"
		};
	function clickMap(mapObject){
		mapObject.addEventListener("click", function(e) {
			$("#showAddress").blur();
			//地址解析器 根据经纬度获取当前地址
			var gc = new BMap.Geocoder();
			var pointAdd = new BMap.Point(e.point.lng, e.point.lat);
			$("#x").val(e.point.lng); //经度
			$("#y").val(e.point.lat); //纬度
			gc.getLocation(pointAdd, function(rs) {
				var addComp = rs.addressComponents;
				var adr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
				mapObject.clearOverlays();//清空原来的标注
				var marker1 = new BMap.Marker(pointAdd); // 创建标注
				mapObject.addOverlay(marker1); // 将标注添加到地图中
				var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
				mapObject.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
				$("#province").val(addComp.province);
				$("#city").val(addComp.city);
				$("#area").val(addComp.district);
				$("#address").val(addComp.street + addComp.streetNumber);
				$("#showAddress").val(adr);
			});
		});
		var x,y,x1,y1;
		mapObject.addEventListener("touchstart", function(e) {
			x = event.changedTouches[0].clientX;
			y = event.changedTouches[0].clientY;
			console.log("touchstart"+event.changedTouches[0].clientX);
			console.log("touchstart"+event.changedTouches[0].clientY);
		})
		mapObject.addEventListener("touchend", function(e) {
			x1 = event.changedTouches[0].clientX;
			y1 = event.changedTouches[0].clientY;
			x = x-x1;
			y = y-y1;
			x = Math.abs(x);
			y = Math.abs(y);
			if(0<=x &&  x<=5 && 0<=y && y<=5){
				var gc = new BMap.Geocoder();
				var pointAdd = new BMap.Point(e.point.lng, e.point.lat);
				$("#x").val(e.point.lng); //经度
				$("#y").val(e.point.lat); //纬度
				gc.getLocation(pointAdd, function(rs) {
					var addComp = rs.addressComponents;
					var adr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
					mapObject.clearOverlays();//清空原来的标注
					var marker1 = new BMap.Marker(pointAdd); // 创建标注
					mapObject.addOverlay(marker1); // 将标注添加到地图中
					var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
					console.log(infoWindow);
					mapObject.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
					$("#province").val(addComp.province);
					$("#city").val(addComp.city);
					$("#area").val(addComp.district);
					$("#address").val(addComp.street + addComp.streetNumber);
					$("#showAddress").val(adr);
				});
			}
		})
// 		mapObject.addEventListener("touchmove", function(e) {
// 			console.log(event.changedTouches[0].clientX);
// 			console.log(event.changedTouches[0].clientY);
			
// 			var gc = new BMap.Geocoder();
// 			var pointAdd = new BMap.Point(e.point.lng, e.point.lat);
// 			$("#x").val(e.point.lng); //经度
// 			$("#y").val(e.point.lat); //纬度
// 			gc.getLocation(pointAdd, function(rs) {
// 				var addComp = rs.addressComponents;
// 				var adr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
// 				mapObject.clearOverlays();//清空原来的标注
// 				var marker1 = new BMap.Marker(pointAdd); // 创建标注
// 				mapObject.addOverlay(marker1); // 将标注添加到地图中
// 				//map.centerAndZoom(pointAdd, 16);
// 				var opts = {
// 					width : 150, // 信息窗口宽度
// 					height : 30, // 信息窗口高度
// 					title : "", // 信息窗口标题
// 					enableMessage : true,//设置允许信息窗发送短息
// 					message : "地址无法显示"
// 				};
// 				var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
// 				mapObject.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
// 				//$("#qpTTPCaseCaseStreet").val(infoWindow.getContent());
// 				console.log(addComp);
// 				$("#address").val(adr);
// 			});
// 		});
	}
	
	// 百度地图API功能
// 	var map = new BMap.Map("containerMap1");
// 	var point = new BMap.Point(116.331398,39.897445);
// 	map.centerAndZoom(point,17);
// 	map.enableScrollWheelZoom(true);
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			var gc = new BMap.Geocoder();
			var pointAdd = new BMap.Point(r.point.lng, r.point.lat);
			$("#x").val(r.point.lng); //经度
			$("#y").val(r.point.lat); //纬度
			gc.getLocation(pointAdd, function(rs) {
				var addComp = rs.addressComponents;
				var adr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
				map.clearOverlays();//清空原来的标注
				var marker1 = new BMap.Marker(pointAdd); // 创建标注
				map.addOverlay(marker1); // 将标注添加到地图中
				map.centerAndZoom(pointAdd, 17);
				var opts = {
					width : 150, // 信息窗口宽度
					height : 30, // 信息窗口高度
					title : "", // 信息窗口标题
					enableMessage : true,//设置允许信息窗发送短息
					message : "地址无法显示"
				};
				var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
				map.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
				$("#province").val(addComp.province);
				$("#city").val(addComp.city);
				$("#area").val(addComp.district);
				$("#address").val(addComp.street + addComp.streetNumber);
				$("#showAddress").val(adr);
			});
		}
		else {
			showTip('定位失败，请打开GPS并允许获取位置在试');
		}        
	},{enableHighAccuracy: true});
	
	clickMap(map); //点击地图获取点击的经纬度
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
	


	
 //http://api.gpsspg.com/convert/coord/?oid=3073&from=0&to=2&latlng=22.525882,113.9351
})
</script>
</html>
