document.getElementsByTagName('body')[0].style.height = window.innerHeight+'px'; 

var respResult;
var openId;

/*$(function() {
	queryOpenIDIsBinding();
});*/

$("#wxregist").bind("click", function(event) {
	   event.stopPropagation();
	if (vaildParam()&& toVaildPhone() && toVaild()) {
		var jsonData = new Object();
		jsonData.userName = $("#userName").val(); //姓名
		jsonData.identityNumber = $("#identityNumber").val(); //身份证
		jsonData.licenseNo = $("#licenseNo").val(); //车牌号
		jsonData.mobile = $("#mobile").val(); //用户注册手机号
		jsonData.postAddress = $("#postAddress").val(); //住址
		jsonData.yzm = $("#yzm").val(); //验证码
		jsonData.param = $("#param").val(); //openId
// 		respResult.isFirstAccess = "false"; //是否第一次访问
		$.ajax({
			url: "/weixin/regist/wxRegist.do",
			type: "post",
			dataType: "json",
			data: jsonData,
			success: function(data) {
				respResult = JSON.parse(JSON.stringify(data));
				if (respResult.state == 200) {
					$("#show").show();
					$("#show").html("").html(respResult.msg);
					setTimeout(function() {
						$("#show").hide();
					}, 1000);
					$("#lasttr,#wxregist").remove();
					$('item-box').css('top', '8%').css('height', '63%');
					$('input').attr("disabled", "disabled"); 
					$("item-box #titleId").html("").html("您的信息如下");
				}else if (respResult.state == 100){
					$("#show").show();
					$("#show").html("").html(respResult.msg);
					setTimeout(function() {
						$("#show").hide();
					}, 5000);
					return false;
				}else if (respResult.state == 4010) {
					$("#userName").val(respResult.userName);// 用户名
					$("#identityNumber").val(respResult.identityNumber);// 身份证
					$("#mobile").val(respResult.mobile);	// 用户注册手机号
					$("#licenseNo").val(respResult.licenseNo);	// 车牌号
					$("#postAddress").val(respResult.postAddress);	// 车牌号
					$('item-box').css('top', '12%');
					$("#statusHtml").html("<span>您的手机号已注册！</span>");
					$("#statusHtml").css({
						"position":"absolute",
						"top":"5%",
						"color": "#00bcd4",
						"text-align": "center",
						"font-size": "1.8rem",
						"width": "100%"
					});
					$("item-box #titleId").html("").html("您的信息如下");
					$("#lasttr,#wxregist").remove();
					$('input').attr("disabled", "disabled"); 
				} 
				/*else if (respResult.code == 4011) {
					$("#show").show();
					$("#show").html("").html("该车牌号码已经注册。");
					setTimeout(function() {
						$("#show").hide();
					}, 2000);
					return false;
				}*/
				else if (respResult.state == 4012) {
					$("#show").show();
					$("#show").html("").html(respResult.msg);
					setTimeout(function() {
						$("#show").hide();
					}, 3000);
					return false;
				}else{
					$("#show").show();
					$("#show").html("").html("服务器又偷懒了，请稍后再试!");
					setTimeout(function() {
						$("#show").hide();
					}, 3000);
					return false;
				}
				
			},
			error: function() {
				console.info("error");
			}
		});
		}
	});

$("#update").bind("click", function(event) {
	if($('#state').val()=='0'){
		$("#updateW").html("").html("个人信息如下");
		$("#update").html("").html("修 改");
		$('#identityNumber').removeAttr("readonly");
		$('#licenseNo').removeAttr("readonly");
		$('#userName').removeAttr("readonly");
		$('#postAddress').removeAttr("readonly");
		$("#state").val('1');
		$(".input1").css("color","black");
		return ;
	}
	if(toVaild()&&vaildSame()){
		
		var jsonData = new Object();
		jsonData.userName = $("#userName").val(); //姓名
		jsonData.mobile = $("#mobile").val(); //手机
		jsonData.identityNumber = $("#identityNumber").val(); //身份证
		jsonData.licenseNo = $("#licenseNo").val(); //车牌号
		jsonData.postAddress = $("#postAddress").val(); //住址		
		jsonData.param = $("#param").val(); //openId
		jsonData.userCode = $("#userCode").val();//userCode
		
		$.ajax({
			url: "/weixin/regist/update.do",
			type: "post",
			dataType: "json",
			data: jsonData,
			success: function(data) {
				respResult = JSON.parse(JSON.stringify(data));
				if (respResult.code == 200) {
					$("#show").show();
					$("#show").html("").html(respResult.msg);
					setTimeout(function() {
						$("#show").hide();
					}, 3000);
					$("#lasttr,#buttonSubmit,#update,#updateW").remove();
					$('.li2').css('line-height', '50px').css('height', '50px');
					$('.item-box').css('margin-top', '7%')
					$("#mobile").css("color","black");
					$('input').attr("disabled", "disabled"); 
					$(".item-box #titleId").html("").html("您的信息如下");
				} else if(respResult.code == '-1'){
					$("#show").show();
					$("#show").html("").html(respResult.msg);
					setTimeout(function() {
						$("#show").hide();
					}, 3000);
				}
			},
			error: function() {
				console.info("error");
			}
		});
	}
	
});

/*验证手机号码是否已经注册，如未注册则发送验证码*/
$("#buttonyzm").bind("click", function(event) {
//	getParam(document.location.href)
    // event.stopPropagation();
	if (vaildParam() && toVaildPhone()) {
		settime($("#buttonyzm"));
		$("#show").show();
		$("#show").html("").html("正在获取验证码");
		setTimeout(function() {
			$("#show").hide();
		}, 3000);

		var rtn = '0';
		var jsonData = new Object();
		jsonData.mobile = $("#mobile").val();
		$.ajax({
			url: "/weixin/regist/sendCode.do",
			type: "post",
			dataType: "json",
			data: jsonData,
			success: function(obj) {
				if(obj.state=='-1'){
					$("#show").show();
					$("#show").html("").html(obj.msg);
					setTimeout(function() {
						  $("#show").hide();
					  }, 3000);
					countdown = 0;
					rtn = "0";
					return ;
				} else if(obj.state=='0' || obj.state=='4' || obj.state=='999'){
					  $("#show").show();
					  $("#show").html("").html(obj.msg);
					  setTimeout(function() {
						  $("#show").hide();
					  }, 3000);
				}
//				console.log(result);
//				$("#show").show();
//				$("#show").html("").html(result.msg);
//				 setTimeout("$('#show').hide()" , 3000);
//				if(result.code == -1){
//					countdown = 0;
//					return false;	
//				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$("#show").show();
				$("#show").html("").html("服务器又偷懒了，请稍后再试!");
			}
		});
		
	}
	
	

});

/*验证短信发送倒计时*/
var countdown = 60;

function settime(obj) {
	obj.attr("class","vilidateNoBtn-time");
	if (countdown == 0) {
		obj.attr("class","vilidateNoBtn");
		obj.attr("disabled", false);
		obj.html("获取验证码");
		obj.css({
			"background": "#eaad0d"
		});
		countdown = 60;
	} else {
		obj.attr("disabled", true);
		obj.css({
			color: "white",
			background: "#aaa"
		});
		obj.html("(" + countdown + "s)");
		countdown--;
		setTimeout(function() {
			settime(obj);
		}, 1000);
	}

}

/*验证手机正确性*/
function toVaildPhone() {
	
	if ($("#mobile").val() == "") {
		$("#show").show();
		$("#show").html("").html("请输入手机号");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	} else if (!(/^1[3|4|5|7|8][0-9]\d{8}$/.test($("#mobile").val()))) {
		$("#show").show();
		$("#show").html("").html("请正确输入手机号");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	} 
	return true;
}
/*验证除手机外的全部输入项*/
function toVaild() {
	var regId = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
	
	if ($("#userName").val() == "") {
		$("#show").show();
		$("#show").html("").html("请输入姓名");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	}
	
	if ($("#licenseNo").val() == "") {
		$("#show").show();
		$("#show").html("").html("请输入车牌号");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	} else if(!(/^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\d{7}$/.test($('#licenseNo').val()))){
		$("#show").show();
		$("#show").html("").html("请输入正确的车牌号");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	}
	
	if ($("#identityNumber").val() == "") {
		$("#show").show();
		$("#show").html("").html("请输入身份证");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	} else if (!(regId.test($("#identityNumber").val()))) {
		$("#show").show();
		$("#show").html("").html("请输入正确的身份证号码");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	}
	if ($("#yzm").val() == "") {
		$("#show").show();
		$("#show").html("").html("请输入验证码");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	}
	if (!/^[0-9]{6}$/.test($("#yzm").val())) {
		$("#show").show();
		$("#show").html("").html("验证码错误");
		setTimeout(function() {
			$("#show").hide();
		}, 2000);
		return false;
	}

	return true;
	
}

function vaildParam(){
	if($('#param').val()==""){
		$("#show").show();
		$("#show").html("").html("请返回微信主页面重新进入。");
		setTimeout(function() {
			$("#show").hide();
		}, 5000);
		return false;
	}else{
		return true;
	}
}



/**
* 每次访问先调用该方法，查询openid是否被绑定
*/
function queryOpenIDIsBinding() {
	var json = new Object();
//	getParam(document.location.href);
	json.param = getParam(document.location.href).param;
	json.isFirstAccess = "true";
	console.info("获取url参数对象：" + JSON.stringify(json));
	$.ajax({
		url: "/weixin/regist/checkOpenId.do",
		type: "post",
		dataType: "json",
		data: json,
		success: function(data) {
			if (data.code == 0) {
				return ;
			}else if(data.code == 1){
				$("#show").show();
				$("#show").html("").html("数据不齐，请返回微信主页面重新进入。");
				setTimeout(function() {
					$("#show").hide();
				}, 2000);
				return ;
			}else if(data.code == 3){
				$("#show").show();
				$("#show").html("").html("数据有误，请返回微信主页面重新进入。");
				setTimeout(function() {
					$("#show").hide();
				}, 2000);
				return ;
			}
			else if(data.code == 2){				
				// 账号已经绑定
				$("#mobile").val(data.mobile);	// 用户注册手机号
				$("#licenseNo").val(data.licenseNo);	// 车牌号
				$("#identityNumber").val(data.identityNumber);	// 身份证
				$("#userName").val(data.userName);	// 车牌号
				$("#statusHtml").html("<span>您的手机号已经注册！</span");
				$("#statusHtml").css({
					"position":"absolute",
					"top":"5%",
					"color": "#00bcd4",
					"text-align": "center",
					"font-size": "1.8rem",
					"width": "100%"
				});

				$("#lasttr,#buttonSubmit,#update,#updateW").remove();
				$('item-box').css('top', '18%');
				$('input').attr("disabled", "disabled"); 
				$("item-box #titleId").html("").html("您的信息如下");
			}
		},
		error: function() {
			console.info("error");
		}
	});

}



/**
* 解析url获取get请求参数
*/
function getParam(url) {
	url = decodeURI(url);
	var params = url.split("?");
	var res = {};
	if (typeof(params[1]) == "string") {
		params = params[1].split("&");
		for (var i in params) {
			var param = params[i].split("=");
			res[param[0]] = param[1];
		}
	}
	return res;
}


