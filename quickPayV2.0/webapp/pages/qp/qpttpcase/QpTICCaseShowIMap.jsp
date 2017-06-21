<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%-- <%@ page import="com.picc.qp.schema.model.QpTICAccident" %> --%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=iLCaxMjThCmlliN78uu3tbjv"></script>
</head>
<body>
	<form name="iMapFm" id="iMapFm">
	    <input type="hidden" id="longitude" name="longitude" value="${qpTTPCase.longitude}" />
		<input type="hidden" id="latitude" name="latitude" value="${qpTTPCase.latitude}"/>
		<div class="block">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short" style="vertical-align: top"><font
							color="red">*</font>&nbsp;事故地点：</td>
						<td class="long" colSpan="3"><select
							id="qpTTPCaseCaseProvince" name="qpTTPCaseCaseProvince"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 113px;">
						</select> <select id="qpTTPCaseCaseCity" name="qpTTPCaseCaseCity"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 113px;">
						</select> <select id="qpTTPCaseCaseDistrict" name="qpTTPCaseCaseDistrict"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 115px;">
						</select>
						  <br/> 
						<textarea
								style="margin-top: 8px; margin-bottom: 8px; height: 35px;"
								id="qpTTPCaseCaseStreet" name="qpTTPCaseCaseStreet"
								class="input_w easyui-validatebox" rows="2" cols="100"
								required="true">${qpTTPCase.caseStreet}
						</textarea>
						  <br/> 
						 
						<input type="button" class="button_ty" onclick="returnAddIMap()"
								ind="ind" value="确定" />
							<input type="button" class="button_ty" onclick="closeWindow()"
								ind="ind" value="关闭" />
								
							<br/>
							<input class='input_w w_30' onkeydown="keydownEvent()" name="iMapAddress" id="iMapAddress"  >
						    <input type="button" class="button_ty" onclick="findIMap()" ind="ind" value="搜索" />		
						   
                         <!-- <br/> <p>显示结果<textarea id="txtResult" rows="10" cols="30" value="" /></textarea>   -->
						    
						</td>
					</tr>
				</table>
			</div>
			<div id="containerMap1" style="width: 730px; height: 400px;"></div>
	</form>
</body>
<script type="text/javascript">
    initSelect();
    selectBindData();
  
    //给下拉框赋值
    function selectBindData(){
         $("#qpTTPCaseCaseProvince").combobox("setValue",'${qpTTPCase.caseProvince}');
		 
         window.setTimeout(function(){ 
        	 $('#qpTTPCaseCaseCity').combobox(
     				'reload',
     				'${ctx}/qp/qptcommon/getCityList.do?provId=' + $('#qpTTPCaseCaseProvince').combobox('getValue'));
     		
     		 $("#qpTTPCaseCaseCity").combobox("setValue",'${qpTTPCase.caseCity}');
     		 
     		 $('#qpTTPCaseCaseDistrict').combobox(
     				'reload',
     				'${ctx}/qp/qptcommon/getDistrictList.do?cityId=' + $('#qpTTPCaseCaseCity').combobox('getValue'));
     		
     		 $("#qpTTPCaseCaseDistrict").combobox("setValue",'${qpTTPCase.caseDistrict}');
	      },500); 
		
    }
   
    //初始下拉框
	function initSelect() {
		$('#qpTTPCaseCaseProvince').combobox(
				{
					url : '${ctx}/qp/qptcommon/getProvinceList.do',
					valueField : 'provId',
					textField : 'provName',
					onSelect : function() {
						$('#qpTTPCaseCaseCity').combobox('clear');
						$('#qpTTPCaseCaseDistrict').combobox('clear');
					
						$('#qpTTPCaseCaseCity').combobox(
								'reload',
								'${ctx}/qp/qptcommon/getCityList.do?provId='
										+ $('#qpTTPCaseCaseProvince').combobox(
												'getValue'));
					},
					onUnselect : function() {
						$('#qpTTPCaseCaseCity').combobox('clear');
						$('#qpTTPCaseCaseDistrict').combobox('clear');
						
						$('#qpTTPCaseCaseCity').combobox(
								'reload',
								'${ctx}/qp/qptcommon/getCityList.do?provId='
										+ $('#qpTTPCaseCaseProvince').combobox(
												'getValue'));
					}
				});

		$('#qpTTPCaseCaseCity').combobox(
				{
					url : '${ctx}/qp/qptcommon/getCityList.do',
					valueField : 'cityId',
					textField : 'cityName',
					onSelect : function() {
						$('#qpTTPCaseCaseDistrict').combobox('clear');
						
						$('#qpTTPCaseCaseDistrict').combobox(
								'reload',
								'${ctx}/qp/qptcommon/getDistrictList.do?cityId='
										+ $('#qpTTPCaseCaseCity').combobox(
												'getValue'));
					},
					onUnselect : function() {
						$('#qpTTPCaseCaseDistrict').combobox('clear');
						
						$('#qpTTPCaseCaseDistrict').combobox(
								'reload',
								'${ctx}/qp/qptcommon/getDistrictList.do?cityId='
										+ $('#qpTTPCaseCaseCity').combobox(
												'getValue'));
					}
				});

		$('#qpTTPCaseCaseDistrict').combobox(
				{
					valueField : 'districtId',
					textField : 'districtName',
					onSelect : function() {
						displayMap();
					}
				});

		
	}

	function displayMap() {
		var province = $('#qpTTPCaseCaseProvince').combobox('getText');
		var city = $('#qpTTPCaseCaseCity').combobox('getText');
		var district = $('#qpTTPCaseCaseDistrict').combobox('getText');

		if (province != null && province != "" && city != null && city != ""
				&& district != null && district != "") {

			var detailAddress = province + city + district;
			//$("#qpTTPCaseCaseStreet").val(detailAddress);
			searchByStationName(detailAddress);
		}
	}

	//赋值给父窗口
	function returnAddIMap(){
	  // $("#longitude",window.opener).val($('#longitude').val());    
	  window.opener.document.getElementById("longitude").value = $('#longitude').val();
	  window.opener.document.getElementById("latitude").value = $('#latitude').val();
	  window.opener.$("#qpTTPCaseCaseProvince").combobox('setValue', $('#qpTTPCaseCaseProvince').combobox('getValue'));
	  window.opener.$("#qpTTPCaseCaseCity").combobox('setValue', $('#qpTTPCaseCaseCity').combobox('getValue'));
	  window.opener.$("#qpTTPCaseCaseDistrict").combobox('setValue', $('#qpTTPCaseCaseDistrict').combobox('getValue'));
	  window.opener.$("#qpTTPCaseCaseDistrict").combobox('setText', $('#qpTTPCaseCaseDistrict').combobox('getText'));
	  window.opener.document.getElementById("qpTTPCaseCaseStreet").value = $('#qpTTPCaseCaseStreet').val();
	  closeWindow();
	 }  
	
	//输入文本框的值查询地图，不保存坐标
	function findIMap(){
		var province = $('#qpTTPCaseCaseProvince').combobox('getText');
		var city = $('#qpTTPCaseCaseCity').combobox('getText');
		var district = $('#qpTTPCaseCaseDistrict').combobox('getText');
		var likeAddress = province + city + district + $("#iMapAddress").val();
		searchIMapAddress(likeAddress);
	}
	
	function keydownEvent(){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){ // enter 键
			findIMap();
        }
	};
	 
	 //关闭本窗口 
	 function closeWindow(){
	     window.close(); 
	 } 
	 
</script>


<script type="text/javascript">
	var map = new BMap.Map("containerMap1");
	var jingdu = $("#longitude").val();
	var weidu = $("#latitude").val();

	map.setDefaultCursor("url('bird.cur')"); //设置地图默认的鼠标指针样式
	map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用

	// map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	// map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl()); //右下角

	var localSearch = new BMap.LocalSearch(map);
	localSearch.enableAutoViewport(); //允许自动调节窗体大小
	clickMap(map); //点击地图获取点击的经纬度	
	if(jingdu!=null && jingdu!=""){
		var pointFind = new BMap.Point(jingdu, weidu);
		map.centerAndZoom(pointFind, 17);
		var markerFind = new BMap.Marker(pointFind); // 创建标注，为要查询的地方对应的经纬度
		map.addOverlay(markerFind); // 将标注添加到地图中
		
	}else{    
		if($("#qpTTPCaseCaseStreet").val()=="" || $("#qpTTPCaseCaseStreet").val()=="						"){
			//map.centerAndZoom("湖南省长沙市芙蓉区五一大道", 12); //默认地图显示的标注位置		
			//定时执行，0.5秒后执行
	        window.setTimeout(function(){ 
	        	var provinceSelect = $('#qpTTPCaseCaseProvince').combobox('getText');
	    		var citySelect = $('#qpTTPCaseCaseCity').combobox('getText');
	    		var districtSelect = $('#qpTTPCaseCaseDistrict').combobox('getText');
	            var selectDataAddress = provinceSelect + citySelect + districtSelect;	
	            searchByStationName(selectDataAddress);
	        },500); 
		}else{
			searchByStationName($("#qpTTPCaseCaseStreet").val()); //无经纬度，弹出页面传入地址初始化地图
		}
	}
	
	function searchByStationName(detailAddress) {
		map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function(searchResult) {
			var poi = searchResult.getPoi(0);
			map.centerAndZoom(poi.point, 17);
			var marker = new BMap.Marker(new BMap.Point(poi.point.lng,poi.point.lat)); // 创建标注，为要查询的地方对应的经纬度
			map.addOverlay(marker); // 将标注添加到地图中
			$("#longitude").val(poi.point.lng); //经度
			$("#latitude").val(poi.point.lat); //纬度
			//点击地图获取点击的经纬度......
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
	
	
	function clickMap(mapObject){
		mapObject.addEventListener("click", function(e) {
			//地址解析器 根据经纬度获取当前地址
			var gc = new BMap.Geocoder();
			var pointAdd = new BMap.Point(e.point.lng, e.point.lat);
			$("#longitude").val(e.point.lng); //经度
			$("#latitude").val(e.point.lat); //纬度
			gc.getLocation(pointAdd, function(rs) {
				var addComp = rs.addressComponents;
				var adr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
				//mapObject.clearOverlays();//清空原来的标注
				var marker1 = new BMap.Marker(pointAdd); // 创建标注
				mapObject.addOverlay(marker1); // 将标注添加到地图中
				//map.centerAndZoom(pointAdd, 16);
				var opts = {
					width : 200, // 信息窗口宽度
					height : 50, // 信息窗口高度
					title : "", // 信息窗口标题
					enableMessage : true,//设置允许信息窗发送短息
					message : "地址无法显示"
				};
				var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
				mapObject.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
				//$("#qpTTPCaseCaseStreet").val(infoWindow.getContent());
				$("#qpTTPCaseCaseStreet").val(addComp.street + addComp.streetNumber);
			});
		});
	}
</script>

</html>