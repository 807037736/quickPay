$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
        validator: function(value, param){   
            return value.replace(/[^\x00-\xff]/g, "xxx").length >= param[0];   
        },
        message: '请输入至少 {0} 字符,中文占3个字符,英文数字占1个字符.'  
    },
    minLen: {
        validator: function(value, param){   
            return value.replace(/[^\x00-\xff]/g, "xxx").length >= param[0];   
        },
        message: '请输入至少 {0} 个字符.'  
    },
	maxLength: {
	    validator: function(value, param){   
	        return value.replace(/[^\x00-\xff]/g, "xxx").length < param[0];   
	    },
	    message: '输入最多字符不超过 {0},中文占3个字符,英文数字占1个字符.'  
	},
	equalsLength: {		//要求=长度
        validator: function(value, param){   
            return value.replace(/[^\x00-\xff]/g, "xxx").length == param[0];   
        },   
        message: '必须为 {0} 字符,中文占3个字符,英文数字占1个字符.'  
    },
    hasExists : { //同种类型证件不允许多个
   	 validator : function(value,param) {
   		var currentIndex = param[2];
//   		var paramValue = document.getElementsByName(param[0])[param[2]].value;
        	var flag = 0;
//        	if(value!=null&&value!=""&&paramValue=="01"){
        	if(value!=null&&value!=""){
        		var identifyTypes = document.getElementsByName(param[0]);
        		var identifyNumbers = document.getElementsByName(param[1]);
        		//当证件类型和证件号码完全相等时，即证件号码已经存在
        		for(var i=1; identifyNumbers!=null&&i<=identifyNumbers.length-1; i++){
//        			if(i!=currentIndex && value==identifyNumbers[i].value && identifyTypes[currentIndex].value == identifyTypes[i].value)
        			if(i!=currentIndex && identifyTypes[currentIndex].value==identifyTypes[i].value)
        			{
        				flag = 1;
        			}
        		}
        		if(flag == 0){
        			return true;
        		}else{
        			message : '同类型证件不允许多次录入';
        		}
        	}else{
        		return true;
        	}
        }, 
        message : '同类型号码已存在，请重新输入'
   },
   hasMobileExists : { //同种通信不允许多个
	   	 validator : function(value,param) {
	   		var currentIndex = param[2];
//	   		var paramValue = document.getElementsByName(param[0])[param[2]].value;
	        	var flag = 0;
//	        	if(value!=null&&value!=""&&paramValue=="01"){
	        	if(value!=null&&value!=""){
	        		var identifyTypes = document.getElementsByName(param[0]);
	        		var identifyNumbers = document.getElementsByName(param[1]);
	        		//当证件类型和证件号码完全相等时，即证件号码已经存在
	        		for(var i=1; identifyNumbers!=null&&i<=identifyNumbers.length-1; i++){
	        			if(i!=currentIndex && value==identifyNumbers[i].value && identifyTypes[currentIndex].value == identifyTypes[i].value)
	        			{
	        				flag = 1;
	        			}
	        		}
	        		if(flag == 0){
	        			return true;
	        		}else{
	        			message : '同类型证件不允许多次录入';
	        		}
	        	}else{
	        		return true;
	        	}
	        }, 
	        message : '类型相同的号码不可以重复录入'
	   },
    idcard : {// 验证身份证 
        validator : function(value) { 
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
        }, 
        message : '身份证号码格式不正确' 
    },
    stuNewsTitleLength : {// 验证学幼险发布新闻标题长度不能大于40个字
        validator : function(value) { 
            var len=$.trim(value).length; 
            return len<=40;
        }, 
        message : '标题长度过长，不能超过40个字' 
    },
    length:{
    	validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
        message:"输入内容长度必须介于{0}和{1}之间." 
    }, 
    phone : {// 验证电话号码 
        validator : function(value) { 
//            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        	return /^(((\d{2,3})|(\d{3}\-))?|((0\d{2,3})|(0\d{2,3}-))?)[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '格式不正确,请使用下面格式010-8 888888' 
    }, 
    phoneShenz : {// 验证 
        validator : function(value) { 
//            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        	return /^(((\d{2,3})|(\d{3}\-))?|((0\d{2,3})|(0\d{2,3}-))?)[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '固定电话格式不正确,格式为:0755-81111111/8111111' 
    }, 
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(13|14|15|17|18|19)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确' 
    }, 
    identifyNumber : {// 验证身份证后六位
        validator : function(value) { 
            return /^\d{5}(\d{1}|x|X)$/i.test(value); 
        }, 
        message : '身份证后六位格式不正确' 
    }, 
    dataYear : {// 年
        validator : function(value) { 
            return /^(20(0|1|2|3)\d{1})|(199\d{1})$/i.test(value); 
        }, 
        message : '投保年度无效，有效期为1990-2039年' 
    }, 
    phoneOrmobile: {// 验证电话号码（可以是手机，座机） 
        validator : function(value) {         	
        	var s = /^(((\d{2,3})|(\d{3}\-))?|((0\d{2,3})|(0\d{2,3}-))?)[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        	var s1 = /^(13|14|15|18|17)\d{9}$/i.test(value); 
            return s||s1; 
        }, 
        message : '电话号码格式不正确,格式为8-12位的数字' 
    }, 
    phoneOrMobile: {// 验证电话号码（可以是手机，座机） 
        validator : function(value) {         	
        	var s = /^(((\d{2,3})|(\d{3}\-))?|((0\d{2,3})|(0\d{2,3}-))?)[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        	var s1 = /^(13|14|15|18|17)\d{9}$/i.test(value); 
            return s||s1; 
        }, 
        message : '固定电话格式不正确,格式为0755-8111111或13111111111' 
    }, 
    intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '请输入数字，并确保格式正确' 
    }, 
    currency : {// 验证货币 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '货币格式不正确' 
    }, 
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,9}$/i.test(value); 
        }, 
        message : 'QQ号码格式不正确' 
    }, 
    integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[1-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    }, 
    integerAndZero : {// 验证0和正整数
    	validator : function(value) { 
            return /^[+]?[0-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    },
    age : {// 验证年龄
        validator : function(value) { 
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value); 
        }, 
        message : '年龄必须是0到120之间的整数' 
    }, 
    chinese : {// 验证中文 
        validator : function(value) { 
            return /^[\Α-\￥]+$/i.test(value); 
        }, 
        message : '请输入中文' 
    }, 
    english : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '请输入英文' 
    }, 
    unnormal : {// 验证是否包含空格和非法字符 
        validator : function(value) { 
            return /.+/i.test(value); 
        }, 
        message : '输入值不能为空和包含其他非法字符' 
    }, 
    username : {// 验证用户名 
        validator : function(value) { 
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value); 
        }, 
        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）' 
    }, 
    queryCondition : {// 请输入10位以内数字的组合,首字母允许大写字母
        validator : function(value) { 
       return /^([A-Z0-9][0-9]{9})$|^([0-9]{8})$/i.test(value); 
        //	return /^\d{8}$|^\d{10}$/i.test(value);
        }, 
        message : '请输入8位或10位数字，其中10位数允许首位为大写字母' 
    }, 
    userCodeCondition : {// 请输入10位以内数字的组合,首字母允许大写字母
        validator : function(value) { 
       return /^([A-Z0-9][0-9]{9})$|^([0-9]{8})|^([0-9]{6})$/i.test(value); 
        //	return /^\d{8}$|^\d{10}$/i.test(value);
        }, 
        message : '请输入6位/8位/10位数字，如是10位数允许首位为大写字母' 
    }, 
    faxno : {// 验证传真 
        validator : function(value) { 
//	            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '传真号码不正确' 
    }, 
    zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确' 
    }, 
    ip : {// 验证IP地址 
        validator : function(value) { 
            return /d+.d+.d+.d+/i.test(value); 
        }, 
        message : 'IP地址格式不正确' 
    }, 
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入有效姓名' 
    },
    date : {// 验证姓名，可以是中文或英文 
        validator : function(value) { 
         //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
        },
        message : '请输入合适的日期格式'
    },
    msn:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)' 
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'    
    } ,
    identifyByType:{
    	validator : function(value,param) {
    		var identifyNo = document.getElementsByName(param[0])[param[1]].value;
    		if(value !=null && value !="") {
    			if(identifyNo != "" && identifyNo == "01") {//证件类型为身份证
    				 //////////////////身份证准确性校验///////////////
    				if(!isChinaIDCard(value)) {
    					return false;
    				}else {
    					return true;
    				}
    				 ////////////////////////////////////////////////
    			}else {
    				return true;
    			}
    		}else {
				return true;
			}
    		
    	},
    	message : '身份证号码格式不正确！' 
    },
    mobileByType: {
    	validator : function(value, param){ 
    		var mobile = document.getElementsByName(param[0])[param[1]].value;
    		if(value !=null && value !="") {
    			if(mobile != null && mobile != "" && mobile == "01") {//联系类型为手机
    				return /^(13|14|15|18)\d{9}$/i.test(value); 
    			}else if(mobile != null && mobile != "" && mobile == "02"){//联系类型为座机
//    				return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
    	        	return /^((\d{2,3})|(\d{3}\-))?((0\d{2,3})|(0\d{2,3}-))?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
    			}else if(mobile != null && mobile != "" && mobile == "05") {//联系类型为电子邮件
    				return /^[0-9A-Za-z]+@([0-9a-zA-Z]+.){1,2}(com|net|cn|com.cn)/i.test(value);
    			}else {
    				return true;
    			}
    		}else{ 
                return true; 
            }
        },
        message : '手机号码11位;固定电话如:07558765678;电子邮件：以@**.com结束' 
    },
    accountNumber : {// 验证银行卡号
        validator : function(value) { 
            return /^\d{0,25}$/i.test(value); 
        }, 
        message : '账户卡号格式不正确，为25位以下的整数' 
    },
    requiredOne : {
    	validator : function(value, param){  
    		 var paramLength = param.length;
    		 var result = true;
    		 var paramValue = "";
    		 for(var i=0; i < paramLength;i++) {
    			 paramValue = param[i];
    			 if(paramValue ==null || paramValue =="") {
    				 result = false;
    			 }
    		 }
             return result; 
         }
    },
    email: {
    	 validator : function(value) { 
             return /[\w-]+@{1}[\w-]+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig.test(value); 
         }, 
         message : '电子邮件地址有误' 
    },
    noIntOrFloat : {// 非数字
        validator : function(value) { 
            var flag = /^\d+(\.\d+)?$/i.test(value); 
            var returnflag = !flag;
            return returnflag;
        }, 
        message : '不允许为数字！' 
    },
    custType :{//客户类型校验
    	validator : function(value) { 
    		var flag = false;
    		if(value =="1" || value =="2") {
    			flag = true;
    		}
            return flag;
        }, 
        message : '客户类型为1，即个人；客户类型为2，即团体；' 
    },
    dateSection:{//日期区间合法性校验
    	validator : function(value, param) { 
    		var startDate = document.getElementsByName(param[0])[param[1]].value;
    		if(value !=null && value !=""){
    			return startDate <= value;
    		}
        }, 
        message : '不合法的日期区间范围，请重新输入；' 
    },
    compareCurrentDate:{//参数param 为系统当前日期，日期格式为 yyyy-MM-dd
    	validator : function(value, param) {
    		var currentDate = document.getElementById(param[0]).value;
    		if(value !=null && value !="" && currentDate !=null && currentDate !="") {
    			var flag = true;
    			if(value < currentDate) {
    				flag = false;
    			}
    			return flag;
    		}
    	},
    	message : '输入的日期不允许在当前日期之前；' 
    }
}); 

/*---身份证号码的规则

1、15位身份证号码组成：
ddddddyymmddxxs共15位，其中：
dddddd为6位的地方代码，根据这6位可以获得该身份证号所在地。
yy为2位的年份代码，是身份证持有人的出身年份。
mm为2位的月份代码，是身份证持有人的出身月份。
dd为2位的日期代码，是身份证持有人的出身日。
这6位在一起组成了身份证持有人的出生日期。
xx为2位的顺序码，这个是随机数。
s为1位的性别代码，奇数代表男性，偶数代表女性。

2、18位身份证号码组成：
ddddddyyyymmddxxsp共18位，其中：
其他部分都和15位的相同。年份代码由原来的2位升级到4位。最后一位为校验位。
校验规则是：
（1）十七位数字本体码加权求和公式
S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
Ai:表示第i位置上的身份证号码数字值
Wi:表示第i位置上的加权因子
Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
（2）计算模
Y = mod(S, 11)
（3）通过模得到对应的校验码
Y: 0 1 2 3 4 5 6 7 8 9 10
校验码: 1 0 X 9 8 7 6 5 4 3 2

也就是说，如果得到余数为1则最后的校验位p应该为对应的0.如果校验位不是，则该身份证号码不正确。 
 */

/**
 * 判断身份证号码格式函数 公民身份号码是特征组合码， 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码
 */
function isChinaIDCard(StrNo) {
  	var num = StrNo;
  	num = num.toUpperCase();  
	StrNo = StrNo.toString();
	if (StrNo.length == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
    	var arrSplit = num.match(re); 
    	// 检查生日日期是否正确
    	var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
    	//getAge(dtmBirth);
		if (!isValidDate("19" + StrNo.substr(6, 2), StrNo.substr(8, 2), StrNo
				.substr(10, 2))) {
			return false;
		}
		
	} else if (StrNo.length == 18) {
	  	re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
      	var arrSplit = num.match(re); 
      	// 检查生日日期是否正确
     	var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
    	//getAge(dtmBirth);	
		if (!isValidDate(StrNo.substr(6, 4), StrNo.substr(10, 2), StrNo.substr(
				12, 2))) {
			return false;
		}
	} else {
		//alert("输入的身份证号码必须为15位或者18位！");
		return false;
	}

	if (StrNo.length == 18) {
		var a, b, c
		if (!isNumber(StrNo.substr(0, 17))) {
			//alert("身份证号码错误,前17位不能含有英文字母！");
			return false;
		}
		a = parseInt(StrNo.substr(0, 1)) * 7 + parseInt(StrNo.substr(1, 1)) * 9
				+ parseInt(StrNo.substr(2, 1)) * 10;
		a = a + parseInt(StrNo.substr(3, 1)) * 5 + parseInt(StrNo.substr(4, 1))
				* 8 + parseInt(StrNo.substr(5, 1)) * 4;
		a = a + parseInt(StrNo.substr(6, 1)) * 2 + parseInt(StrNo.substr(7, 1))
				* 1 + parseInt(StrNo.substr(8, 1)) * 6;
		a = a + parseInt(StrNo.substr(9, 1)) * 3
				+ parseInt(StrNo.substr(10, 1)) * 7
				+ parseInt(StrNo.substr(11, 1)) * 9;
		a = a + parseInt(StrNo.substr(12, 1)) * 10
				+ parseInt(StrNo.substr(13, 1)) * 5
				+ parseInt(StrNo.substr(14, 1)) * 8;
		a = a + parseInt(StrNo.substr(15, 1)) * 4
				+ parseInt(StrNo.substr(16, 1)) * 2;
		b = a % 11;
		if (b == 2) // 最后一位为校验位
		{
			c = StrNo.substr(17, 1).toUpperCase(); // 转为大写X
		} else {
			c = parseInt(StrNo.substr(17, 1));
		}
		switch (b) {
		case 0:
			if (c != 1) {
				//alert("身份证号码校验位错:最后一位应该为:1");
				return false;
			}
			break;
		case 1:
			if (c != 0) {
				//alert("身份证号码校验位错:最后一位应该为:0");
				return false;
			}
			break;
		case 2:
			if (c != "X") {
				//alert("身份证号码校验位错:最后一位应该为:X");
				return false;
			}
			break;
		case 3:
			if (c != 9) {
				//alert("身份证号码校验位错:最后一位应该为:9");
				return false;
			}
			break;
		case 4:
			if (c != 8) {
				//alert("身份证号码校验位错:最后一位应该为:8");
				return false;
			}
			break;
		case 5:
			if (c != 7) {
				//alert("身份证号码校验位错:最后一位应该为:7");
				return false;
			}
			break;
		case 6:
			if (c != 6) {
				//alert("身份证号码校验位错:最后一位应该为:6");
				return false;
			}
			break;
		case 7:
			if (c != 5) {
				//alert("身份证号码校验位错:最后一位应该为:5");
				return false;
			}
			break;
		case 8:
			if (c != 4) {
				//alert("身份证号码校验位错:最后一位应该为:4");
				return false;
			}
			break;
		case 9:
			if (c != 3) {
				//alert("身份证号码校验位错:最后一位应该为:3");
				return false;
			}
			break;
		case 10:
			if (c != 2) {
				//alert("身份证号码校验位错:最后一位应该为:2");
				return false;
			}
		}
	} else {// 15位身份证号
		if (!isNumber(StrNo)) {
			//alert("身份证号码错误,前15位不能含有英文字母！");
			return false;
		}
	}
	return true;

}

function isValidDate(iY, iM, iD) {
	if (iY > 2200 || iY < 1900 || !isNumber(iY)) {
		//alert("输入身份证号,年度" + iY + "非法！");
		return false;
	}
	if (iM > 12 || iM <= 0 || !isNumber(iM)) {
		//alert("输入身份证号,月份" + iM + "非法！");
		return false;
	}
	if (iD > 31 || iD <= 0 || !isNumber(iD)) {
		//alert("输入身份证号,日期" + iD + "非法！");
		return false;
	}
	return true;
}
/**
 * 验证是不是数字
 */
function isNumber(oNum) {
	if (!oNum)
		return false;
	var strP = /^\d+(\.\d+)?$/;
	if (!strP.test(oNum))
		return false;
	try {
		if (parseFloat(oNum) != oNum)
			return false;
	} catch (ex) {
		return false;
	}
	return true;
}
