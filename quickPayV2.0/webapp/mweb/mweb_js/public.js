$(function(){	
	$('input').keyup(function(){
        $(this).val($(this).val().replace(/[ ]/g,""));
    })	
});
function setJsonData(json){//给span标签加个属性data <span data="policyNo" ></span>
	for(var key in json){
		$("span[data]").each(function(i){
			   if(this.data==key){
				   (this).html(json[key])
			   }
			 });
	}
}
function ajaxErrorCallBack(XMLHttpRequest, textStatus, errorThrown) {
	   /* if(XMLHttpRequest.status==0){
	var myifr = document.createElement('div');
 	myifr.style.display = "none";
	myifr.innerHTML = '<iframe id="myifr" src ="/SL_LES/mwebPos/addIframe.do" width="200px" height="200px" onload=""></iframe>';
	document.body.appendChild(myifr);
	window.setTimeout('location.reload()',5000);
	//location.reload();
}else{
	var div="<div class='security_con'>"
        +"<div class='sy_con'>"
    	+"<br/><br/>"
        +"<div class='certificate_note3'>操作不成功！<br/>有任何疑问，请致电本公司统一客服电话95535咨询。"
       +" </div>"
        +"</div>"
      +"</div>";
	$("#maindiv").html(div);	
}*/
/*var myifr = document.createElement('div');
	myifr.style.display = "none";
myifr.innerHTML = '<iframe id="myifr" src ="/SL_LES/mwebPos/addIframe.do" width="200px" height="200px" onload=""></iframe>';
document.body.appendChild(myifr);
window.setTimeout('location.reload()',5000);*/
//location.reload();
}
function goMenberCenter(){
	location.href=viewEFSPath+"/mweb/member/login/member_center.shtml";
};

/*check is browser is weixin or is AlipayClient*/
function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger"||ua.match(/AlipayClient/i)=="alipayclient") {
        return true;
    } else {
        return false;
    }
};

function is_app(){
	 var ua = navigator.userAgent.toLowerCase();	
	 if(ua.indexOf("com.sinolife.app")!=-1) {
	        return true;
	    } else {
	        return false;
	    }
};

function is_ipa(){
	 var ua = navigator.userAgent.toLowerCase();	
	 if(ua.indexOf("com.sinolife.iphone")!=-1) {
	        return true;
	    } else {
	        return false;
	    }
}
/*check is linked from app shotcut*/
function is_menu(){
	 var ua = navigator.userAgent.toLowerCase();
	 if(ua.indexOf("sinolife.main.menu")!=-1) {
	        return true;
	    } else {
	        return false;
	    }
}

/* Alipay bridge */
function callAlipayBridge() {
    var args = arguments,
            fn = function () {
                var bridge = window.AlipayJSBridge;
                bridge.call.apply(bridge, args);
            };
    window.AlipayJSBridge ? fn() : document.addEventListener("AlipayJSBridgeReady", function () {
        fn();
    }, !1);
};


/* close weixin browser */
function closeWeixinWindow(){
	try{
		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/MicroMessenger/i)=="micromessenger") {
			WeixinJSBridge.call('closeWindow');
		} else	if (ua.match(/AlipayClient/i) == "alipayclient") {
			callAlipayBridge('closeWebview');
		}
	}catch(e){
		alert(e.message);
	}
};
/* close iphone browser */
function closeIphoneWindow(){
	window.location = "/click/hiddenNav";
}

$(function(){	
	 resizeEvents();
    $(window).resize(function() {
        resizeEvents();
    });	
});

function resizeEvents(){
	if ($('.layer').length > 0) {
        $('.layer').height($(document).height());
    };
}
function appendVersion(){
	return "version=1.0";
};

function addCookie(objName,objValue,objHours){      //添加cookie
	   
	    var str = objName + "=" + escape(objValue);
	 
	    if(objHours > 0){                               //为时不设定过期时间，浏览器关闭时cookie自动消失
	 
	        var date = new Date();
	 
	        var ms = objHours*3600*1000;
	 
	        date.setTime(date.getTime() + ms);
	 
	        str += "; expires=" + date.toGMTString();
	 
	   }
	 
	   document.cookie = str;
	 
	}

function getCookie(objName){//获取指定名称的cookie的值
	   
	    var arrStr = document.cookie.split("; ");
	 
	    for(var i = 0;i < arrStr.length;i ++){
	 
	        var temp = arrStr[i].split("=");
	 
	        if(temp[0] == objName) return unescape(temp[1]);
	 
	   } 
	 
	}
/*build ad cookie.js*/
(function () {		
	if (document.cookie.indexOf("ad_cookie") == -1) {
		var curl = document.location.href;
		var from = document.referrer;
		var cookieId;
		if ((from == "" || from.indexOf("sino-life.com") != -1) && curl.indexOf("qdid=") == -1) {
			cookieId = "direct";
		} else if (curl.indexOf("qdid=") > 0) {
			cookieId = curl.split("qdid=")[1];
			var pos = cookieId.indexOf('&');
			if (pos > 0) {
				cookieId = cookieId.substr(0, pos);
			}
		} else {
			var search = from.match("baidu") || from.match("google") || from.match("soso") || from.match("yahoo") || from.match("114search") || from.match("Aol") || from.match("bing") || from.match("sogou") || from.match("360.cn") || from.match("live.com");
			if (search) {
				cookieId = "seo_" + search;
			} else {
				cookieId = "freelink";
			}
		}
		var name = escape("ad_cookie");
	    var value = escape(cookieId);
	    var domain = document.domain ;				   
	    var topdomain = domain.match("\.[-a-zA-Z0-9]{0,62}\.com") ||  domain;
		document.cookie = name + "=" + value + ";path=/;domain="+topdomain;
	}
})();

/*google analytics*/
(function(i, s, o, g, r, a, m) {
	i['GoogleAnalyticsObject'] = r;
	i[r] = i[r] || function() {
		(i[r].q = i[r].q || []).push(arguments)
	}, i[r].l = 1 * new Date();
	a = s.createElement(o), m = s.getElementsByTagName(o)[0];
	a.async = 1;
	a.src = g;
	m.parentNode.insertBefore(a, m)
})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

ga('create', 'UA-52847138-1', 'sino-life.com');
ga('require', 'displayfeatures');
ga('require', 'linkid', 'linkid.js');
ga('send', 'pageview');

