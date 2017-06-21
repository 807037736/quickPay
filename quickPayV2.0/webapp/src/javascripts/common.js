var regMobile = /^0?(13[0-9]|15[012356789]|17[02356789]|18[02356789]|14[57])[0-9]{8}$/; //手机号码正则
var regLicense = /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5,}$/; //车牌正则
var regLicenseArray = "京";//允许投保的拍照头
function checkIdcard(idcard) {
    var Errors = new Array(
                            "true",
                            "身份证号码位数不对!",
                            "身份证号码出生日期超出范围或含有非法字符!",
                            "身份证号码校验错误!",
                            "身份证地区非法!"
                            );
    var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" }
    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //地区检验 
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
    //身份号码位数及格式检验 
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; //测试出生日期的合法性 
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; //测试出生日期的合法性 
            }
            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
            //18位身份号码检测 
            //出生日期的合法性检查  
            //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9])) 
            //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8])) 
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式 
            } else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式 
            }
            if (ereg.test(idcard)) {//测试出生日期的合法性 
                //计算校验位 
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
+ parseInt(idcard_array[7]) * 1
+ parseInt(idcard_array[8]) * 6
+ parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1); //判断校验位 
                if (M == idcard_array[17]) return Errors[0]; //检测ID的校验位 
                else return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}

/**
 * 通过身份证号获取性别
 * 返回1：男，2：女
 * @param value
 * @returns
 */
function getSexByIdcardNo(value) {
    if (!value) {
        return "未知";
    } else if (value.length == 15) {
        return parseInt(value.substr(14, 1))%2?"1":"2";
    } else if (value.length == 18) {
        return parseInt(value.substr(16, 1))%2?"1":"2";
    } else {
       return "未知";
    }
}


function AddDays(date, days) {//日期加减处理
    var nd = new Date(date);

    nd = nd.valueOf();
    var a = days * 24 * 60 * 60 * 1000;

    nd = nd + days * 24 * 60 * 60 * 1000;
    nd = new Date(nd);

    var y = nd.getFullYear();

    var m = nd.getMonth() + 1;
    var d = nd.getDate();
    if (m <= 9) m = "0" + m;
    if (d <= 9) d = "0" + d;

    var cdate = y + "-" + m + "-" + d;

    return cdate;
}
// 判断闰年  

function isLeapYear(year) {
    return (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0)));
}

//加一年的情况
function setEndDate(obj, enddate) {

    if (isLeapYear($(obj).val().split("-")[0])) {
        adddays = 365;
    } else {
        adddays = 364;
    }
    var m = $(obj).val().split("-")[1];
    var d = $(obj).val().split("-")[2];
    if (m.substring(0, 1) == "0") m = m.substring(1);
    if (d.substring(0, 1) == "0") d = d.substring(1);

    var dd = new Date($(obj).val().split("-")[0], (parseInt(m) - 1), d);
    dd = dd.valueOf();
    $(enddate).html(AddDays(dd, adddays));


}

/**
计算两个日期之间相差天数
*/
function getDateDiff(date1, date2) {
	var arr1 = date1.split('-');
	var arr2 = date2.split('-');
	var d1 = new Date(arr1[0], arr1[1], arr1[2]);
	var d2 = new Date(arr2[0], arr2[1], arr2[2]);
	return((d2.getTime() - d1.getTime()) / (1000 * 3600 * 24));
}

Date.prototype.dateAdd = function (interval, number) {
    var d = this;
    var k = { 'y': 'FullYear', 'q': 'Month', 'm': 'Month', 'w': 'Date', 'd': 'Date', 'h': 'Hours', 'n': 'Minutes', 's': 'Seconds', 'ms': 'MilliSeconds' };
    var n = { 'q': 3, 'w': 7 };
    //  alert(k[interval] + ":" + n[interval] + ":" + (n[interval] || 1) * number + ":" + (parseInt(d.getDate()) + 1));
    eval('d.set' + k[interval] + '(d.get' + k[interval] + '()+' + ((n[interval] || 1) * number) + ')');
    return d;
}
Date.prototype.format = function (formatStr) {
    var date = this;
    /*  
    函数：填充0字符  
    参数：value-需要填充的字符串, length-总长度  
    返回：填充后的字符串  
    */
    var zeroize = function (value, length) {
        if (!length) {
            length = 2;
        }
        value = new String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++) {
            zeros += '0';
        }
        return zeros + value;
    };
    return formatStr.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|M{1,4}|yy(?:yy)?|([hHmstT])\1?|[lLZ])\b/g, function ($0) {
        switch ($0) {
            case 'd': return date.getDate();
            case 'dd': return zeroize(date.getDate());
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][date.getDay()];
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][date.getDay()];
            case 'M': return date.getMonth() + 1;
            case 'MM': return zeroize(date.getMonth() + 1);
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][date.getMonth()];
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][date.getMonth()];
            case 'yy': return new String(date.getFullYear()).substr(2);
            case 'yyyy': return date.getFullYear();
            case 'h': return date.getHours() % 12 || 12;
            case 'hh': return zeroize(date.getHours() % 12 || 12);
            case 'H': return date.getHours();
            case 'HH': return zeroize(date.getHours());
            case 'm': return date.getMinutes();
            case 'mm': return zeroize(date.getMinutes());
            case 's': return date.getSeconds();
            case 'ss': return zeroize(date.getSeconds());
            case 'l': return date.getMilliseconds();
            case 'll': return zeroize(date.getMilliseconds());
            case 'tt': return date.getHours() < 12 ? 'am' : 'pm';
            case 'TT': return date.getHours() < 12 ? 'AM' : 'PM';
        }
    });
}
function showLoading(haveBgd) {
    var div = document.getElementById('loading');
    if(haveBgd){//是否创建遮罩
    	var backgd = document.getElementById('backgd');
    	if(!backgd){
    		backgd = document.createElement('div');
    		backgd.id = 'backgd';
    		backgd.className='backgroundcls';
    		document.querySelector('body').appendChild(backgd);
    	}else{
    		backgd.style.display = 'block';
    	}
	}
    if (!div) {
        div = document.createElement('div');
        div.id = 'loading';
        div.innerHTML = '<div class="bk"></div><div class="cont"><img src="'+contextRootPath+'/images/loading.gif" alt="loading..."/>正在加载...</div>';
        document.querySelector('body').appendChild(div);
    } else {
        div.style.display = 'block';
    }

}

function hideLoading() {
	//cl.hide(); // Hidden by default
//	$("#backgd").hide();
    setTimeout(function () {
        var backgd = document.getElementById('backgd');
        if (backgd) {
        	backgd.style.display = 'none';
		}
        var div = document.getElementById('loading');
        if (div){
        	div.style.display = 'none';
        }
    }, 50);
}

/*###############创建模态化窗口 begin create by liweijie ##################*/
/**
 * @author liweijie
 * @param field 容器id
 * @param titleMsg 提示框标题
 * @param content 提示内容
 * @param comfirmFunction 确定回调函数
 * @param cancelFunction 取消回调函数
 * @param closeViaDimmer 点击遮罩是否可以取消弹窗
 * @param modalType 窗口类型：no-btn, confirm, alert
 */
var initModalDialog = function (field, titleMsg, content, comfirmFunction, cancelFunction, closeViaDimmer, modalType) {
	var div = $('#'+field);
	if(typeof(div.val()) == 'undefined') {
		div = $('<div id = "' + field + '" class = "am-modal am-modal-'+ modalType + '" tabindex="-1"></div>');
		
		var htmlContent = '';
		htmlContent = '<div class="am-modal-dialog"><div class="am-modal-hd">';
		//head & body
		if(modalType == 'no-btn') {
			htmlContent = htmlContent + '<div>' + titleMsg + '</div><a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div><div class="am-modal-bd">' + content + '</div> ';
		} else {
			htmlContent = htmlContent + '<div>' +titleMsg + '</div></div><div class="am-modal-bd">' + content + '</div> ';
		}
		
		//footer
		if(modalType == 'alert') {
			htmlContent = htmlContent + '<div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-confirm>确定</span></div>';
		} else if(modalType == 'confirm') {
			htmlContent = htmlContent + '<div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-cancel>取消</span><span class="am-modal-btn" data-am-modal-confirm>确定</span></div>';
		}
		htmlContent = htmlContent + '</div>';
		div.html(htmlContent);
		$('body').append(div);
	} else {
		$("#"+field).find("div .am-modal-hd div").html(titleMsg);
		$("#"+field).find("div .am-modal-bd").html(content);
	}
	
	if(modalType == 'alert') {
		$('#'+field).modal({
	        relatedTarget: this,
	        closeViaDimmer:closeViaDimmer,
	        onConfirm: function(options) {
	        	if(comfirmFunction) {
	        		comfirmFunction();
	        	}
	        	$(this).removeData('am.modal');
	        }
		});
	} else if(modalType == 'confirm') {
		$('#'+field).modal({
	        relatedTarget: this,
	        closeViaDimmer:closeViaDimmer,
	        onConfirm: function(options) {
	        	if(comfirmFunction) {
	        		comfirmFunction();
	        	}
	        	$(this).removeData('am.modal');
	        },
	        onCancel: function() {
	        	if(cancelFunction) {
	        		cancelFunction();
	        	}
	        	$(this).removeData('am.modal');
	        }
	     });
	} else if(modalType == 'no-btn') {
		$('#'+field).modal('open');
	}
}

function licenseNoCheck(licenseNo){
	if(!regLicense.test(licenseNo)||licenseNo.length!=7){
		return "车牌号码输入不正确，参考：京NH1666";
	}
	var liNo=licenseNo.substr(0,1);
	var r = regLicenseArray.search(liNo);
	if(r==-1){
		return "很抱歉！当前我们仅承保以：“"+regLicenseArray+"”开头的牌照车辆";
	}else{
		return true
	}
}
/**
 * 校验字符串中包含几个汉字
 * @param str
 * @return
 */
function chineseCheck(str){
	var chinese = /^[\u4e00-\u9fa5]{1}$/;
	var count=0;
	for(var i=0;i<str.length;i++){
		if(chinese.test(str.charAt(i))){
			count++;
		}
	}
	return count;
}

/**
 * 定义HashMap数据结构
 */
function HashMap(){
    //定义长度
    var length = 0;
    //创建一个对象
    var obj = new Object();

    /**
    * 判断Map是否为空
    */
    this.isEmpty = function(){
        return length == 0;
    };

    /**
    * 判断对象中是否包含给定Key
    */
    this.containsKey=function(key){
        return (key in obj);
    };

    /**
    * 判断对象中是否包含给定的Value
    */
    this.containsValue=function(value){
        for(var key in obj){
            if(obj[key] == value){
                return true;
            }
        }
        return false;
    };

    /**
    *向map中添加数据
    */
    this.put=function(key,value){
        if(!this.containsKey(key)){
            length++;
        }
        obj[key] = value;
    };

    /**
    * 根据给定的Key获得Value
    */
    this.get=function(key){
        return this.containsKey(key)?obj[key]:null;
    };

    /**
    * 根据给定的Key删除一个值
    */
    this.remove=function(key){
        if(this.containsKey(key)&&(delete obj[key])){
            length--;
        }
    };

    /**
    * 获得Map中的所有Value
    */
    this.values=function(){
        var _values= new Array();
        for(var key in obj){
            _values.push(obj[key]);
        }
        return _values;
    };

    /**
    * 获得Map中的所有Key
    */
    this.keySet=function(){
        var _keys = new Array();
        for(var key in obj){
            _keys.push(key);
        }
        return _keys;
    };

    /**
    * 获得Map的长度
    */
    this.size = function(){
        return length;
    };

    /**
    * 清空Map
    */
    this.clear = function(){
        length = 0;
        obj = new Object();
    };
}