/**
 * 	表单校验
 */

//给String类加上empty函数
String.prototype.empty = function() {
	return (this == "") || (this == undefined);
};

//给String类加上trim函数
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

//验证手机号码
function mobileValidate(str){
	
	var myreg = /^(0?(13|14|15|18))\d{9}$/;
	if(!myreg.test(str)){
	      return false;
	}
	return true;
}

//验证姓名只能包含中文、英文和空格，且不能中英文混搭
function nameValidate(str) {
	var reg1 = /^[\u4e00-\u9fa5]+$/; 	//中文，空格
	var reg2 = /^[a-zA-Z ]+$/; 	        //英文，空格

	if(!reg1.test(str)){	
		if(!reg2.test(str)) {
			return false;
		}
	}
	return true;
}

//验证邮箱
function emailValidate(str) {
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	
	if(!myreg.test(str))
	 {      
	     return false;
	  }
	  return true;
}

/**
 * 验证身份证
 */
function idCardValidate(value) {
	
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	if (re == null || a.indexOf(re[1]) < 0) return false;
	if (re[2].length == 9) {
	number = number.substr(0, 6) + '19' + number.substr(6);
	d = ['19' + re[4], re[5], re[6]].join('-');
	} else d = [re[9], re[10], re[11]].join('-');
	if (!checkDate(d, 'yyyy-MM-dd',true)) return false;
	for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
		
}

/**
 * 验证日期
 */
function checkDate(value,format, reObj){
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi);
	var f2 = value.split(/\D+/g);
	var f3 = format.split(/[a-z]+/gi);
	var f4 = value.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length) return false;
	for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
	for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
	if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;
}

function s(s1, s2, s3, s4, s5) {
	s4 = s4 || 60, s5 = s5 || 2;
	var reVal = s3;
	if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
	if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
	return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
}

/** 
 * 时间对象的格式化; 
 */  
Date.prototype.format = function(format) {  
    /* 
     * eg:format="yyyy-MM-dd hh:mm:ss"; 
     */  
    var o = {  
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()  // millisecond  
    };  
  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr
        		(4 - RegExp.$1.length));  
    }  
  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}; 


/*验证数字*/
function numberValidate(value) {
			
			var reg = /^[0-9]+$/;
			if(!reg.test(value)){
			      return false;
			} else {
			    return true;
			}
}

/*验证整数*/
function integerValidate(value) {
				if(!/^[-|[1-9]|0][0-9]*$/.test(value)) {
					return false;
				} else {
					return true;
				}
	}


/*验证正整数*/
function positiveIntegerValidate(value) {
				if(!/^[0-9]*[1-9][0-9]*$/.test(value)) {
					return false;
				} else {
					return true;
				}
}

/*验证自然数*/
function naturalNumberValidate(value) {
				if(!/^[0-9]+$/.test(value)) {
					return false;
				} else {
					return true;
				}

	}

/*转化成大写*/
function toUppercase(obj){
    obj.value=obj.value.toUpperCase();
}

//自定义对话框弹出效果
function sAlert(titleStr,str){
    var msgw,msgh,bordercolor;
    msgw=document.body.clientWidth; //dialog hight
    //msgh = height;
    //msgh=130;//dialog width
    titleheight=25;
    bordercolor="#e83b2f";
    titlecolor="#e83b2f";
   
    var sWidth,sHeight;
    sWidth=document.body.clientWidth;
    sHeight= document.body.scrollHeight;

    var bgObj=document.createElement("div");
    bgObj.setAttribute('id','bgDiv');
    bgObj.style.position="absolute";
    bgObj.style.top="0";
    bgObj.style.background="#777";
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
    bgObj.style.opacity="0.6";
    bgObj.style.left="0";
    bgObj.style.width=sWidth + "px";
    bgObj.style.height=sHeight + "px";
    bgObj.style.zIndex = "10000";
    document.body.appendChild(bgObj);
   
    var msgObj=document.createElement("div");
    msgObj.setAttribute("id","msgDiv");
    msgObj.setAttribute("align","center");
    msgObj.style.background="white";
    msgObj.style.border="1px solid " + bordercolor;
    msgObj.style.position = "absolute";
    msgObj.style.left = "0";
    msgObj.style.top = "45%";
    msgObj.style.font="16px/1.6px Verdana, Geneva, Arial, Helvetica, sans-serif";
    //msgObj.style.marginLeft = "-200px" ;
    //msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
    msgObj.style.width = msgw + "px";
    //msgObj.style.height =msgh + "px";
    msgObj.style.textAlign = "center";
    msgObj.style.paddingBottom = "3px";
    msgObj.style.lineHeight ="25px";
    
    msgObj.style.zIndex = "10001";

   var title=document.createElement("h4");
   title.setAttribute("id","msgTitle");
   title.setAttribute("align","center");
   title.style.margin="0";
   title.style.padding="3px";
   title.style.background=bordercolor;
   title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
   title.style.opacity="0.75";
   title.style.border="1px solid " + bordercolor;
   title.style.height="20px";
   title.style.font="16px Verdana, Geneva, Arial, Helvetica, sans-serif";
   title.style.fontWeight="bold";
   title.style.color="white";
   title.style.cursor="pointer";
  
   title.innerHTML=titleStr;
   title.onclick=function(){
        document.body.removeChild(bgObj);
        document.getElementById("msgDiv").removeChild(title);
        document.getElementById("msgDiv").removeChild(txt);
        document.getElementById("msgDiv").removeChild(btn);
        document.body.removeChild(msgObj);
    };
   document.body.appendChild(msgObj);
   document.getElementById("msgDiv").appendChild(title);
   var txt=document.createElement("p");
   txt.style.margin="1px 0";
   txt.setAttribute("id","msgTxt");
   txt.innerHTML=str;
   txt.style.paddingTop = "8px";
   txt.style.paddingLeft = "15px";
   txt.style.paddingRight = "15px";
   txt.style.paddingBottom = "8px";
   txt.style.textAlign = "left";
   document.getElementById("msgDiv").appendChild(txt);
   
   var btn=document.createElement("BUTTON");
   var t=document.createTextNode("确定");
   btn.appendChild(t); 
   btn.setAttribute("align","center");
   btn.onclick=function(){
       document.body.removeChild(bgObj);
       document.getElementById("msgDiv").removeChild(title);
       document.getElementById("msgDiv").removeChild(txt);
       document.getElementById("msgDiv").removeChild(btn);
       document.body.removeChild(msgObj);
   };
   document.getElementById("msgDiv").appendChild(btn);
   
}

/*
 * 验证车牌保单号
 */
function checkPolicyNo(policyno){	
	if(policyno.length == 22){
		var prefix = policyno.trim().substring(0, 4);
		if(prefix == 'PDAA' || prefix == 'PDZA' || prefix == 'PDAT'){
			//验证为数字构成
			if(positiveIntegerValidate(policyno.substring(4,12)) && positiveIntegerValidate(policyno.substring(17,22)))
				return true;
		}
	}
	return false;
}


/*为防止重复提交，按钮被点击后置为无效，时间到后解锁*/
function lock(name){ 
	document.getElementById(name).disabled = true;
	//alert(name);
} 
function dislock(name){ 
	document.getElementById(name).disabled = false;
	//alert(name);
} 