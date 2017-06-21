/**
 *   格式化js文件
 *   creator: xiehui
 */

//给String类加上trim函数
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

//日期类型比较的校验扩展函数
$.extend($.fn.validatebox.defaults.rules,{
	TimeCheck:{
	validator:function(value,param){
	var s = $("input[name="+param[0]+"]").val();
	//因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
	return value>=s;
	},
	message:'非法数据'
	}
});


//验证手机号码
function mobileCheck(item){
	var str = item.val();
	
	var myreg = /^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\d{8}$/;
	if(!myreg.test(str)){
	      alert('手机号码格式不正确！');
	      item.val("");
	      item.focus();
	      return false;
	}
	return true;
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

////验证 E-mail
//function validate_email(field,alerttxt)
//{
//with (field)
//{
//apos=value.indexOf("@")
//dotpos=value.lastIndexOf(".")
//if (apos<1||dotpos-apos<2) 
//  {alert(alerttxt);return false}
//else {return true}
//}
//}

/*验证数字*/
$.extend($.fn.validatebox.defaults.rules, {
	isNumber: {
		validator: function(value, param) {
			
			/*
			for(var i = 0; i < value.length; i++) {
				//alert(value.charAt(i));
				var temp = parseInt(value.charAt(i));
				if(isNaN(temp)) {
					//alert('不是数字哦*&*');
					return false;
				}
			}
			return true;
			*/
			
			var reg = /^[0-9]*$/;
			if(!reg.test(value)){
			      return false;
			} else {
			    return true;
			}
		},
		message:'只能输入数字0-9'
	}
});

/*验证整数*/
$.extend($.fn.validatebox.defaults.rules, {
	isInteger: {
		validator: function(value, param) {
		
				if(!/^[-|[1-9]][0-9]*$/.test(value)) {
					return false;
				} else {
					return true;
				}
		},
		message: '只能输入整数且不能以0开头'
	}
});

/*验证正整数*/
$.extend($.fn.validatebox.defaults.rules, {
	isPositiveInteger: {
		validator: function(value, param) {
		
				if(!/^[0-9]*[1-9][0-9]*$/.test(value)) {
					return false;
				} else {
					return true;
				}
		},
		message: '只能输入正整数'
	}
});

/*验证自然数*/
$.extend($.fn.validatebox.defaults.rules, {
	isNatureInteger: {
		validator: function(value, param) {
		
				if(!/^[0-9]+$/.test(value)) {
					return false;
				} else {
					return true;
				}
		},
		message: '只能输入正整数或者0'
	}
});

/*为防止重复提交，按钮被点击后置为无效，时间到后解锁*/
function lock(name){ 
	document.getElementById(name).disabled = true;
	//alert(name);
} 
function dislock(name){ 
	document.getElementById(name).disabled = false;
	//alert(name);
} 
