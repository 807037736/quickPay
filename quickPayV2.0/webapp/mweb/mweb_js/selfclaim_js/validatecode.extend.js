//以下为自定义方法，validate方法中没有的

//姓名必须是中文或者中文加字母
jQuery.validator.addMethod("isRelName", function (value, element) {
    var RegExp = /^[A-Za-z\u4e00-\u9fa5]+$/;
    return RegExp.test(value);
}, $.validator.format("姓名必须是中文或者中文加字母"));

// 身份证号码验证（加强验证）
jQuery.validator.addMethod("idCardNo", function (value, element, param) {
    if($(param).val() == "01") {
        return this.optional(element) || /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);
    } else {
        return true;
    }
}, "请正确输入您的身份证号码");


//判断两个值是否相等
jQuery.validator.addMethod("notEqualTo", function (value, element, param) {
    return value != $(param).val();
}, $.validator.format("两次输入不能相同!"));


//只能输入数字
jQuery.validator.addMethod("isNum", function (value, element) {
    var RegExp = /^\d+$/;
    return RegExp.test(value);
}, $.validator.format("只能为数字!"));


//规则名：buga,value检测对像的值  
$.validator.addMethod("buga", function (value) {
    return value == "buga";
}, 'Please enter "buga"!');


//规则名：chinese，value检测对像的值，element检测的对像  
$.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return (chinese.test(value)) || this.optional(element);
}, "只能输入中文");


//规则名：byteRangeLength，value检测对像的值，element检测的对像,param参数  
jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));


// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function (value, element) {
    var length = value.length;
    var mobile = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var tel = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "请正确填写您的联系电话");


// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function (value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");


// 字符验证
jQuery.validator.addMethod("string", function (value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "不允许包含特殊符号!");


// 必须以特定字符串开头验证
jQuery.validator.addMethod("begin", function (value, element, param) {
    var begin = new RegExp("^" + param);
    return this.optional(element) || (begin.test(value));
}, $.validator.format("必须以 {0} 开头!"));


// 验证两次输入值是否不相同
jQuery.validator.addMethod("notEqualTo", function (value, element, param) {
    return value != $(param).val();
}, $.validator.format("两次输入不能相同!"));


// 验证值不允许与特定值等于
jQuery.validator.addMethod("notEqual", function (value, element, param) {
    return value != param;
}, $.validator.format("输入值不允许为{0}!"));


// 验证值必须大于特定值(不能等于)
jQuery.validator.addMethod("gt", function (value, element, param) {
    return value > param;
}, $.validator.format("输入值必须大于{0}!"));

// 验证值必须大于特定值(可以等于)
jQuery.validator.addMethod("gtAndEq", function (value, element, param) {
    return value >= $(param).val();
}, $.validator.format("输入值必须大于{0}!"));

// 验证值小数位数不能超过两位
jQuery.validator.addMethod("decimal", function (value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    return this.optional(element) || (decimal.test(value));
}, $.validator.format("小数位数不能超过两位!"));


//字母数字
jQuery.validator.addMethod("alnum", function (value, element) {
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");


// 汉字
jQuery.validator.addMethod("chcharacter", function (value, element) {
    var tel = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (tel.test(value));
}, "请输入汉字");


// 身份证号码验证（加强验证）
jQuery.validator.addMethod("isIdCardNo", function (value, element) {
    return this.optional(element) || /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);
}, "请正确输入您的身份证号码");


// 手机号码验证
jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");


// 电话号码验证
jQuery.validator.addMethod("isTel", function (value, element) {
    var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码");