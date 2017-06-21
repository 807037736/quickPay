/**
 *   百度开发接口：获取地址
 */


    var lng;
    var lat;
    var addr;
    function getLocation(id_getloc)
    {
        $("#page_a").hide();
        document.getElementById("mapDiv").style.display = "block";
        document.getElementById("input_getloc").value = id_getloc;
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(showMap,showError,
                    {
                        enableHighAccuracy : true,
                        timeout : 10000 // 10s
                        //maximumAge : 0
                    }
            );
        }
        else{
            document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
            document.getElementById("locationString").style.color="#FF0000";
        }
    }
   
    function showError(error)
    {
        switch(error.code)
        {
            case error.PERMISSION_DENIED:
                document.getElementById("locationString").style.color="#FF0000";
                document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
                break;
            case error.POSITION_UNAVAILABLE:
                document.getElementById("locationString").style.color="#FF0000";
                document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
                break;
            case error.TIMEOUT:
                document.getElementById("locationString").style.color="#FF0000";
                document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
                break;
            case error.UNKNOWN_ERROR:
                document.getElementById("locationString").style.color="#FF0000";
                document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
                break;
        }
    }
    function showMap() {
        var map = new BMap.Map("allmap");
        var geolocation = new BMap.Geolocation();
        var mk;
        var gc = new BMap.Geocoder();

        geolocation.getCurrentPosition(function(r){
            map.centerAndZoom(r.point,30);
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                if(mk) {
                    map.removeOverlay(mk);
                }
                mk = new BMap.Marker(r.point);
                map.addOverlay(mk);
                map.panTo(r.point);
                gc.getLocation(r.point, function(rs){
                    var addComp = rs.addressComponents;
                    addr = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
                    document.getElementById("locationString").innerHTML = addr;
                    lng = r.point.lng;
                    lat = r.point.lat;
                    //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
                });
            }
            else {
                document.getElementById("locationString").style.color="#FF0000";
                document.getElementById("locationString").innerHTML = "您的手机无法定位，请点击确认按钮返回报案页面后手动输入车辆目前位置；";
            }
        },{enableHighAccuracy: true});


        map.addEventListener("click", function(e){
            var pt = e.point;
            if(mk) {
                map.removeOverlay(mk);
            }
            mk = new BMap.Marker(e.point);
            map.addOverlay(mk);
            map.panTo(e.point);
            //alert('您的位置：'+e.point.lng+','+e.point.lat);
            gc.getLocation(pt, function(rs){
                var addComp = rs.addressComponents;
                addr = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
                document.getElementById("locationString").innerHTML = addr;
                lng = r.point.lng;
                lat = r.point.lat;
                //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
            });
        });
    }
    
    function showPosition(position)
    {
        x.innerHTML="Latitude: " + position.coords.latitude +
                "<br>Longitude: " + position.coords.longitude;
    }
    
    // 百度地图API功能
    function sendLocation() {
    	
    	alert(addr + input_getloc);
    	var input_getloc = document.getElementById("input_getloc").value;
        document.getElementById("mapDiv").style.display = "none";
        $("#page_a").show();
        if(addr == undefined) {
        } else {
        	
            document.getElementById(input_getloc).value = addr;
           
           // document.getElementById("lng").value = lng;
            //document.getElementById("lat").value = lat;
        }
    }