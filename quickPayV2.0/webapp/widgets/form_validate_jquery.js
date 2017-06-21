/**
 *  jquery validate 
 */



/*
 * phone number
 */


$.validator.addMethod("v_phoneNo", function(value, element) {
	var myreg = /^(0?(13|14|15|18|17))\d{9}$/;
	return this.optional(element) || myreg.test(value);
}, "请输入真实的手机号码");


/*
 * identity number
 */
$.validator.addMethod("v_identityNumber", function(num, element) {
	
	num = num.toUpperCase();
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		
		return false;
	}
	// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	// 下面分别分析出生日期和校验位
	var len, re;
	len = num.length;
	if (len == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);

		// 检查生日日期是否正确
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3]
				+ '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
				&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
				&& (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			
			return false;
		} else {
			// 将15位身份证转成18位
			// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5',
					'4', '3', '2');
			var nTemp = 0, i;
			num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			num += arrCh[nTemp % 11];
			
			return true;
		}
	}
	if (len == 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);

		// 检查生日日期是否正确
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/"
				+ arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
				&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
				&& (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			
			return false;
		} else {
			// 检验18位身份证的校验码是否正确。
			// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5',
					'4', '3', '2');
			var nTemp = 0, i;
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != num.substr(17, 1)) {
				// alert('18位身份证的校验码不正确！应该为：' + valnum);
				
				return false;
			}
			return true;
		}
	}
	return false;
}, "请输入真实的证件号码");


/*
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


/*
 * name
 */
$.validator.addMethod("v_name", function(value, element) {
	
	var reg1 = /^[\u4e00-\u9fa5]+$/; 	//中文
	var reg2 = /^[a-zA-Z ]+$/; 	        //英文，空格

	return this.optional(element) || reg1.test(value) || reg2.test(value);
}, "请输入真实姓名");

$.validator.addMethod("v_commandKey", function(value, element) {
	var commmandId =$('#wxMpCommand\\.id\\.commandId').val();
	var ok = this.optional(element) || (/^([a-zA-Z0-9_]+)$/.test(value));
	var url = contextRootPath+"/wwadmin/wxmpcommand/validCommandKey.do?commandKey=" + value+"&commandId="+commmandId;
	if (ok)
	$.ajax({
			   type: "POST",
			   url: url,
			   async: false, 
			   success: function(result){
				  if(result=='1'){
					  ok= false;
				  }else{
					  ok= true;
				  }
			   }
	});
	
	return ok;
}, "请输入真实的手机号码");

$.validator.addMethod("v_licenseNo", function(value, element) {
//	var commmandId =$('#wxMpCommand\\.id\\.commandId').val();
//	var ok = this.optional(element) || (/^([a-zA-Z0-9_]+)$/.test(value));
	
	var url = contextRootPath+"/pa/patactivityjoininfo/validDWLicenseNo.do";
	url = encodeURI(url + '?licenseNo=' + value);
	var ok = false;
	$.ajax({
			   type: "POST",
			   url: url,
			   async: false, 
			   success: function(result){
				  if(result=='1'){
					  ok= true;
				  }else{
					  ok= false;
				  }
			   }
	});
	return ok;
}, "请输入承保人保电网销渠道的车牌号");



