/**
 * 放大输入框内字符
 * @author  create by zhangkl at 2014-06-06
 * 事实监听input输入框内容改变并将input框的值赋值到另外一个id内
 * 绑定id为testID 的input框：
 *    $('#testID').zoomListen(
		 {
			 targetHtml_ID:"test123"//需要html到的模块ID
		});
 * targetHtml_ID : 绑定的输入框输入的内容复制到的模块ID
 * html_before_str : 
 * html_after_str : 
 **/
(function($){
	$.fn.zoomListen = function(options){
	var defaults ={
			targetHtml_ID:"ID",
			tip_str_mod:"",//ie下input框内默认提示需要进行判断否则会把默认提示放大 
			html_before_str:"",
			html_after_str:"",
			format_flag:false,
			toUpperCase:true,//是否显示为大写(默认为true)
 			value :0
		};
	var options = $.extend(defaults,options);
	var bind_view_id = this.get(0).id;
	var _offset = $(this).offset();
	var objh = $(this).height();
	var objw = $(this).width()+100;
	 
	   if(/msie/.test(navigator.userAgent.toLowerCase())) { 
			this.get(0).attachEvent("onpropertychange",function (o){  
				  
				var otval = $.trim(o.srcElement.value);
				
				if(options.toUpperCase) {
					otval = otval.toUpperCase();
				}
				if(otval==options.tip_str_mod){
					otval="";
				}
				var otvalLen = otval.length;
	 		     if(options.format_flag && otvalLen>0){
	 		    	otval = _format(otval);
	 		     }
	 		     
				if(options.targetHtml_ID!='ID'&&options.targetHtml_ID!='')document.getElementById( options.targetHtml_ID).innerHTML =options.html_before_str +otval+options.html_after_str;
				
				var X =  _offset.left;
 				var Y =  _offset.top;
				 
				 _setupView(otval,bind_view_id);
	 			   
				 
				 if(otvalLen<1){
					 $("#appendDiv"+bind_view_id).hide();
					 
				 }else{
					 _setupView(otval,bind_view_id);
					 $("#appendDiv"+bind_view_id).show();
				 }
				 
				 $("#"+bind_view_id)
				 //.attr('autocomplete', 'off') // disable IE's autocomplete feature
				 .blur( this._blur = function(){
			            $(document).one('click', function(){
			            	$("#appendDiv"+bind_view_id).hide();
			            });
			        })
			        .on('click', function(){
			        	$("#appendDiv"+bind_view_id).css({"width":objw,"left":X  ,  "top":Y+objh  });
			        	if(otvalLen>0){
			        		_setupView(otval,bind_view_id);
			        		 $("#appendDiv"+bind_view_id).show();
			        	 }else{
			        		 $("#appendDiv"+bind_view_id).html("");
			        		 $("#appendDiv"+bind_view_id).hide();
			        	 }
			        	
			        	 
			        });
				 
				 $("#appendDiv"+bind_view_id).css({"width":objw,"left":X  ,  "top":Y+objh  });
              }); 
			return this;
		}else{ 
 		    this.get(0).addEventListener("input",function(o){
 		     var otval = $.trim(o.target.value);
 		     
 		    if(options.toUpperCase) {
				otval = otval.toUpperCase();
			}
 		     if(options.format_flag && otval.length>0){
 		    	otval = _format(otval);
 		     }
			 var X = 10;//$(this).offset().left-50;
			var Y = $(this).offset().top-2*($(this).height());
 		    if(options.targetHtml_ID!='ID'&&options.targetHtml_ID!='')document.getElementById( options.targetHtml_ID).innerHTML =options.html_before_str +otval+options.html_after_str;
			
			 
			 _setupView(otval,bind_view_id);
 			  
			 
			 if(otval.length<1){
				 $("#appendDiv"+bind_view_id).hide();
			 }else{
				 _setupView(otval,bind_view_id);
				 $("#appendDiv"+bind_view_id).show();
			 }
			 $(this)
			 .attr('autocomplete', 'off') // disable IE's autocomplete feature
			 .blur(this._blur = function(){
		            $(document).one('click', function(){
		            	$("#appendDiv"+bind_view_id).hide();
		            });
		        })
		        .on('click', function(){
		        	$("#appendDiv"+bind_view_id).css({"width":objw,"left":X  ,  "top":Y+objh  });
		        	 if(otval.length>0){
		        		 _setupView(otval,bind_view_id);
			        	 $("#appendDiv"+bind_view_id).show();
			        	 }else{
			        		 $("#appendDiv"+bind_view_id).html("");
			        		 $("#appendDiv"+bind_view_id).hide();
			        	 }
		        });
			 
			 $("#appendDiv"+bind_view_id).css({"width":objw,"left":X  ,  "top":Y+objh  });
		    },false);
 		    return this;
	 }
	
	   
	};
	//格式化输入的数字 加“-” 变为3-4-4格式
	var _format = function(t) {
		var r = [];
		var tz = t.split("");
		var i = function(e, t, i) {
			var n = [];
			var c = e.length;
			i = i ? t + i: c;
			i = Math.min(i, c);
			for (var a = t; a < i; a++) {
				n.push(e[a])
			}
			if (n.length) {
				r.push(n.join(""))
			}
		};

		i(tz, 0, 4);
		i(tz, 4, 4);
		i(tz, 8, 4);
		i(tz, 12, 4);
		i(tz, 16, 4);
		 
		return r.join("-");
	};
	 
	var _setupView = function(t,bind_view_id){
        var that = this;
        var viewHtm = "<div id='appendDiv"+bind_view_id+"' style='position: absolute;z-index: 1;height:26px;background: none repeat scroll 0 0 #FFFFFF;border: 1px solid #AFAFAF;text-align: center;line-height: 24px;color:#E90F0F;font-size:23px;font-weight: bold;'>"+t+"</div>";
        if($("#appendDiv"+bind_view_id).html()!=undefined){
        	$("#appendDiv"+bind_view_id).html(t);
        }else{
        	$("body").prepend(viewHtm);
        }
    };
	
})(jQuery);