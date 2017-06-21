/**
 * 段鹏2014-05-07
 * 放置一些公共js
 * **/
;LES={}
//页面内部页面处理类
LES.page={
		showPage:function(pageId,callback){
			$('div[data-role="sf-page" ]').hide();
	    	$("#"+pageId).show();    	
	    	if(null!=callback){
				if (typeof callback == 'string'){
					eval(callback+"()");
				}else{
					callback.call();
				}				
			}    	
		},
		//自动赋值到自定义显示json标签数据
		autoShowPageJsonView : function(jsonData){			
			$("span[data-rule='jsonView']").each(function(){				
				var objName=$(this).attr("forName");				
				var names=objName.split(".");
				var val="";
				if(names.length==1){
					 val=jsonData[objName];
					 $(this).html(val);
				}else if(names.length>1){
					var obj=null;
					for(var i=0,size=names.length;i<size;i++){
						var name=names[i];
						if(i==0){							
							obj=jsonData[name];
							if(obj==null){
								return ;
							}
						}else{							
							obj=obj[name];
						}
						
					}
					$(this).html(obj);
				}
				
				
			});
		}
}
LES.loadHtml={
		//功能说明:加载指定url对应的页面到指定的地方显示
		//参数说明：url:请求的地址;loadingMessage:加载页面的提示消息;disObject:显示结果的目标对象ID;callback:加载完后回调的函数
		loadUrlToObject:function(url,loadingMessage,params,disObjectId,callback){	
			var disObj=$("#"+disObjectId).html($('<div class="loading"></div>').html(loadingMessage));
				jQuery.ajax({
					url: url,
					type: "post",
					dataType: "html",
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data: params,          					
					complete: function( res, status ) {          						
						if ( status === "success" || status === "notmodified" ) { 					
							disObj.html( res.responseText );
							//$.parser.parse(disObj);
							if(null!=callback){
								if (typeof callback == 'string'){
									eval(callback+"()");
								}else{
									callback.call();
								}
								
								}
						}
					}
			});
		},
		//功能说明:提交表单加载页面到指定的地方显示
		//参数说明：formId:表单ID;loadingMessage:加载页面的提示消息;disObject:显示结果的目标对象ID
		submitFormToObject:function(formId,loadingMessage,disObjectID){
			var form=$("#"+formId);
			var params=form.serialize();
			UW.loadHtml.loadUrlToObject(form.attr("action"),loadingMessage,params,disObjectID);
		}
		
}
//功能说明:ie会缓存请求，通过加一个随机数字来解决此问题
//参数说明:url请求地址
//checkIdcard:
LES.util={
  addRandomTermToUrl : function(url){	
	var date=new Date();
	if(url.indexOf('?')==-1){
		url=url+"?urlsendtime="+date.getTime();
	}else{
		url=url+"&urlsendtime="+date.getTime();
	}
	return url;
	},
	//获取浏览器的查询参数对应的值 eg http://UNAN_AN1.html?itemId=1111 获取itemId值 用getUrlParam("itemId")
	getUrlParam:function (name)
	{
		// alert(name);
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		if (r!=null) {
			return unescape(r[2]); 
		}
		return null; //返回参数值
	}
}
LES.date={
		//获取当天的日期格式
		getCurrDateStr:function(){
			var date = new Date();
			return LES.date.dateToStr(date);
		},
		//功能说明： Date类型转换成字符串
		//参数说明:date,js日期对象
		 dateToStr:function(date){
			var year = date.getFullYear();
			var month = date.getMonth()+1;
			var dateVal = date.getDate();
			return year + '-' + (month > 9 ? month : '0' + month) + '-' + (dateVal > 9 ? dateVal : '0' + dateVal);
		},
		 objToDateStr : function(value){
				if(value!=null){
			        var date =new Date();
			       	date.setTime(value.time);
					return LES.date.dateToStr(date);
			    }else{
					return "";
			    }
		}
}

LES.check={
		checkIdcard:function(idcard){
		var Errors=new Array(
				"1",
				"身份证号码位数不对!",
				"身份证号码出生日期超出范围或含有非法字符!",
				"身份证号码校验错误!",
				"身份证地区非法!"
				);
				var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}


				var idcard,Y,JYM;
				var S,M;
				var idcard_array = new Array();
				idcard_array = idcard.split("");
				//地区检验
				if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
				//身份号码位数及格式检验
				switch(idcard.length){
				case 15:
				if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
				} else {
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
				}
				if(ereg.test(idcard)) return Errors[0];
				else return Errors[2];
				break;
				case 18:
				//18位身份号码检测
				//出生日期的合法性检查
				//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
				//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
				if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
				ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
				} else {
				ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
				}
				if(ereg.test(idcard)){//测试出生日期的合法性
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
				+ parseInt(idcard_array[9]) * 3 ;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y,1);//判断校验位
				if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
				else return Errors[3];
				}
				else return Errors[2];
				break;
				default:
				return Errors[1];
				break;
				}
	}
}
/**
 * 放置输入控制相关函数
 */
LES.input={
		//只能输入数字
		numberInput:function(input){						
				var val=event.keyCode;			
				if(val>=48&&val<=57){				
					window.event.returnValue = true;
				}else{			
					window.event.returnValue = false;
				}
		},//只能输入电话号码
		phoneInput:function (input){						
			var val=event.keyCode;			
			if(val>=48&&val<=57||val==45){				
				window.event.returnValue = true;
			}else{			
				window.event.returnValue = false;
			}
	},//自动剔除输入框的左右空格
	trimInputValue:function(input){		
		var inputObj=$(input);
		var inputVal=inputObj.val();		
		if(inputVal!=null){
			inputObj.val($.trim(inputVal));
		}
	},//checkbox全选按钮checkObj:控件本身,objName:需要选中的checkbox name名称
	 checkBoxAll:function(checkObj,objName){	
		if(checkObj.checked){
			$(":input[name='"+objName+"']").attr("checked","checked");
		}else{
			$(":input[name='"+objName+"']").attr("checked","");
		}
	},//证件类型选择idTypeSleId证件类型选择Id,idNoId,bridayId,sexName
	idNoInput:function(idTypeSleId,idNoId,bridayId,sexName){
		$("#"+idTypeSleId).bind("change",function(){
			var idType=$("#"+idTypeSleId).val();
			var $idNo=$("#"+idNoId);
			var $briday=$("#"+bridayId);
			var $sex=$(":input[name='"+sexName+"']");
			//如果是身份证再进行验证
			if(idType=='01'){			
				//$briday.attr("readOnly","readOnly");
				//$sex.attr("readOnly","readOnly");
			}else{				
				//$briday.removeAttr("readOnly");
				//$sex.removeAttr("readOnly");
			}
		});
	},
	initRadioValue:function(inputName,value){
		$(":input[name='"+inputName+"']").each(function(i){			
			if($(this).val()==value){
				$(this).attr("checked","checked");
			}else{
				$(this).removeAttr("checked");
			}
		});
	}
}
/**
 * 表单验证相关函数
 */
LES.form={
		//objId 验证id下所有验证控件
		validate:function(objId){
			var canFalg=false;
			var obj=$("#"+objId);
			obj.find(".sfValidatebox-text:not(:disabled)").each(function(index){			
				$(this).sfValidatebox("validate");				
			});
			var invalidbox = obj.find('.sfValidatebox-invalid');
			var t=invalidbox.filter(':not(:disabled):first');
			
			if(invalidbox.length>0){
				canFalg= false;	
				if(t){				
					t.focus();
				}
			}else{			
				 canFalg=true;
			}
			return canFalg;
	}

}

//遮罩
$(function(){
	initModalWrap();
})

function initModalWrap() {
	if($(".j-modal-wrap").length > 0) {
		var _DH = $(document).height();
		$(".j-modal-wrap").css({"height": _DH + "px"});
	}
}

