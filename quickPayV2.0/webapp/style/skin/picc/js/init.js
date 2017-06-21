var x = typeof $ === "function" ? window.$ : {};x.config = {jsFile: "style/js/",cssFile: "style/css/"};x.getName=function(b){return document.getElementsByName(b)};x.getID=function(a){return document.getElementById(a)};x.getTag=function(a){return document.getElementsByTagName(a)};x.ct=function(a){return document.createTextNode(a)};x.ce=function(a){return document.createElement(a)};x.stopBubble=function(a){a.stopPropagation?a.stopPropagation():a.cancelBubble=true};x.stopDefault=function(a){a.preventDefault?a.preventDefault():a.returnValue=false};x.getStyle=function(a){return a.currentStyle||document.defaultView.getComputedStyle(a,null)};x.exid=function(b){var a=document.getElementById(b);if(a){return true}else{return false}};x.bind=function(c,b,a){if(c.attachEvent){c["e"+b+a]=a;c[b+a]=function(){c["e"+b+a](window.event)};c.attachEvent("on"+b,c[b+a])}else{c.addEventListener(b,a,false)}};x.unbind=function(d,c,b){if(d.detachEvent){try{d.detachEvent("on"+c,d[c+b]);d[c+b]=null}catch(a){}}else{d.removeEventListener(c,b,false)}};x.Browser=function(){var d=navigator.userAgent.toLowerCase();var c={};c.isStrict=document.compatMode=="CSS1Compat";c.isFirefox=d.indexOf("firefox")>-1;c.isOpera=d.indexOf("opera")>-1;c.isSafari=(/webkit|khtml/).test(d);c.isSafari3=c.isSafari&&d.indexOf("webkit/5")!=-1;c.isIE=!c.isOpera&&d.indexOf("msie")>-1;c.isIE6=!c.isOpera&&d.indexOf("msie 6")>-1;c.isIE7=!c.isOpera&&d.indexOf("msie 7")>-1;c.isIE8=!c.isOpera&&d.indexOf("msie 8")>-1;c.isGecko=!c.isSafari&&d.indexOf("gecko")>-1;c.isMozilla=document.all!=undefined&&document.getElementById!=undefined&&!window.opera!=undefined;return c}();x.pageSize={get:function(){var i=x.Browser.isStrict?document.documentElement:document.body;var h=["clientWidth","clientHeight","scrollWidth","scrollHeight"];var k={};for(var j in h){k[h[j]]=i[h[j]]}k.scrollLeft=document.body.scrollLeft||document.documentElement.scrollLeft;k.scrollTop=document.body.scrollTop||document.documentElement.scrollTop;return k}};x.getPosition=function(i){if(typeof(i)=="string"){i=x.getID(i)}var k=0;var j=0;var a=i.offsetWidth;var b=i.offsetHeight;do{j+=i.offsetTop||0;k+=i.offsetLeft||0;i=i.offsetParent}while(i);return{x:k,y:j,width:a,height:b}};x.safeRange=function(l){var v=x.getID(l);var u,t,r,q,o,m,i,a;i=v.offsetWidth;a=v.offsetHeight;p=x.pageSize.get();u=0;r=p.clientWidth-i;o=r/2;t=0;q=p.clientHeight-a;var n=p.clientHeight*0.382-a/2;m=(a<p.clientHeight/2)?n:q/2;if(o<0){o=0}if(m<0){m=0}return{width:i,height:a,minX:u,minY:t,maxX:r,maxY:q,centerX:o,centerY:m}};x.setXY=function(j,k,a,i){var c=x.pageSize.get(),d=x.safeRange(j),b=x.getID(j);if(a){s=x.safeRange(a);rp=x.getPosition(a)}var l=k,n=i===true?0:c.scrollTop;if(a!=undefined&&a!=""){var h=!l.right?parseInt(l.left):c.clientWidth-s.width-parseInt(l.right);var m=!l.bottom?parseInt(l.top):c.clientHeight-s.height-parseInt(l.bottom);left1=rp.x+parseInt(l.left);left2=rp.x+parseInt(l.left)+s.width;right1=rp.x+s.width-d.width-parseInt(l.right);right2=rp.x-d.width-parseInt(l.right);top1=rp.y+parseInt(l.top);top2=rp.y+parseInt(l.top)+s.height;bottom1=rp.y+s.height-d.height-parseInt(l.bottom);bottom2=rp.y-d.height-parseInt(l.bottom);h=!l.right?(l.lin?left1:left2):(l.rin?right1:right2);m=!l.bottom?(l.tin?top1:top2):(l.bin?bottom1:bottom2);b.style.left=h+"px";b.style.top=m+"px"}else{if(!l.left&&!l.right){b.style.left=d.centerX+"px"}else{if(!l.right){b.style.left=parseInt(l.left)+"px"}else{b.style.right=parseInt(l.right)+"px"}}if(!l.top&&!l.bottom){b.style.top=d.centerY+n+"px"}else{if(!l.bottom){b.style.top=parseInt(l.top)+n+"px"}else{b.style.top=c.clientHeight-b.offsetHeight-parseInt(l.bottom)+"px"}}}};x.setIframHeight=function(b){var a=function(k){var l=document.getElementById(k);try{var d=l.contentWindow.document.body.scrollHeight;var c=l.contentWindow.document.documentElement.scrollHeight;var j=Math.max(d,c);l.height=j}catch(i){}};window.setInterval(a,200)};Array.prototype.removeRepeat=function(){var h,a=[],k=this.length;for(var d=0;d<k-1;d++){for(var c=d+1;c<k;c++){if(this[c]===this[d]){this.splice(c,1);if(this[d]!==h){h=this[d],a.push(this[d])}d--,k--}}}return a};Array.prototype.min=function(){return Math.min.apply({},this)};Array.prototype.max=function(){return Math.max.apply({},this)};Array.prototype.indexOf=function(b){for(var a=0;a<this.length;a++){if(this[a]==b){return a}}return -1};Array.prototype.remove=function(b){var a=this.indexOf(b);if(a>-1){this.splice(a,1)}};x.hasClass=function(b,a){return b.className.match(new RegExp("(\\s|^)"+a+"(\\s|$)"))};x.addClass=function(b,a){if(!this.hasClass(b,a)){b.className+=" "+a}};x.removeClass=function(c,a){if(hasClass(c,a)){var b=new RegExp("(\\s|^)"+a+"(\\s|$)");c.className=c.className.replace(b," ")}};x.siblings=function(d){var b=[];var c=d.previousSibling;while(c){if(c.nodeType===1){b.push(c)}c=c.previousSibling}b.reverse();var h=d.nextSibling;while(h){if(h.nodeType===1){b.push(h)}h=h.nextSibling}return b};x.getLength=function(a){return a.replace(/[^\x00-\xff]/g,"**").length};x.strlen=function(d){var a=0;for(var c=0;c<d.length;c++){var b=d.substr(c,1);if(escape(b).substr(0,2)=="%u"){a+=3}else{a+=1}}return a};x.addCSS=function(c){var a=this.style;if(!a){a=this.style=document.createElement("style");a.setAttribute("type","text/css");document.getElementsByTagName("head")[0].appendChild(a)}a.styleSheet&&(a.styleSheet.cssText+=c)||a.appendChild(document.createTextNode(c))};x.loadCSS=function(i,h){if(!i){return}var a=x.getTag("link");for(var k in a){if(a[k].href==i){return}}var j=document.createElement("link");j.id=h;j.rel="stylesheet";j.media="screen";j.type="text/css";j.href=x.config.cssFile+i;x.getTag("HEAD").item(0).appendChild(j)};x.loadJS=function(i,a,h,b){b=b||"utf-8";var c=document.createElement("script");c.charset=b;c.type="text/javascript";c.id=i;c.src=x.config.jsFile+i;var d=x.getTag("HEAD").item(0);if(x.Browser.isIE){c.onreadystatechange=function(){if(!(/loaded|complete/i.test(c.readyState))){return}if("function"==typeof a){a()}c.onreadystatechange=null;c.parentNode.removeChild(c);c=null}}else{c.onload=function(){if("function"==typeof a){a()}c.parentNode.removeChild(c);c=null}}if("function"==typeof h){c.onerror=function(){if("function"==typeof h){h()}c.parentNode.removeChild(c);c=null}}d.appendChild(c)};x.random=function(n,l,j,m){if(!l&&!j&&!m){l=j=m=true}var h=[["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"],["0","1","2","3","4","5","6","7","8","9"]];var d=[];var o="";d=l?d.concat(h[0]):d;d=j?d.concat(h[1]):d;d=m?d.concat(h[2]):d;for(var k=0;k<n;k++){o+=d[Math.round(Math.random()*(d.length-1))]}return o};x.getUrlKey=function(c,b){var b=b?b:location.href;var a="";var d=b.indexOf(c+"=");if(d!=-1){d+=c.length+1;e=b.indexOf("&",d);if(e==-1){e=b.length}a=b.substring(d,e)}return a};x.fixed=function(c){var i=x.getID(c);if(!x.Browser.isIE6){i.style.position="fixed"}else{var a=function(l,j){var l=x.getTag(l);var d=[];for(var k=0;k<l.length;k++){if(l[k].className==j){d.push(l[k])}}return d};var h=a("div","ui_dialog_fixed");if(x.getStyle(x.getID("page"))["backgroundImage"]!="none"){x.addCSS(".ui_dialog_fixed{width:100%; height:1px; position:absolute; z-index: 891201; left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth); top:expression(documentElement.scrollTop)}.body-fixed{background-attachment:fixed;}")}else{x.addCSS(".ui_dialog_fixed{width:100%; height:1px; position:absolute; z-index: 891201; left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth); top:expression(documentElement.scrollTop)}.body-fixed{background-attachment:fixed;background-image:url(about:blank);}")}if(h.length==0){var b=x.ce("div");b.className="ui_dialog_fixed";b.appendChild(i);document.body.appendChild(b);x.addClass(x.getTag("html")[0],"body-fixed")}else{h[0].appendChild(i)}}};x.callBack={ok:function(a){return"<div class='ui_box_callback'><span class='ui_box_callback_ok'></span>"+a+"</div>"},error:function(a){return"<div class='ui_box_callback'><span class='ui_box_callback_error'></span>"+a+"</div>"},tips:function(a){return"<div class='ui_box_callback'><span class='ui_box_callback_tips'></span>"+a+"</div>"}};x.Cookie={get:function(i){var h="";var k=i+"=";var j=document.cookie;if(j.length>0){g=j.indexOf(k);if(g!=-1){g+=k.length;f=j.indexOf(";",g);if(f==-1){f=j.length}h=unescape(j.substring(g,f))}}return h},set:function(j,i,m,l){var n="";var k=x.config.cookieHours||24*30;if(k!=null){n=new Date((new Date()).getTime()+k*3600000);n="; expires="+n.toGMTString()}document.cookie=j+"="+escape(i)+n+(m?"; path="+m:"; path=/")+(l?";domain="+l:"")},del:function(b){document.cookie=b+"=;path=/;expires="+(new Date(0)).toGMTString()}};x.animate=function(r,v,n,C,m){var A=x.getID(r),k=A.children,q=k[0].offsetWidth,D=k[0].offsetHeight,l=k.length,j;var u=0,G=parseInt(x.getStyle(x.getID(r))[v]),H=v=="left"?-Math.abs(q)*n:-Math.abs(D)*n,F=H-G,E=C;var z=function(){clearTimeout(j);if(F&&u<E){A.style[v]=Math.round(m(u++,G,F,E))+"px";j=setTimeout(z,10)}else{A.style[v]=H+"px"}};if(v=="left"){k[n].style.cssFloat="left";k[n].style.display="block";A.style.position="absolute";A.style.width=l*q+"px";A.style.height=D+"px";return z()}else{if(v=="top"){k[n].style.display="block";A.style.position="absolute";A.style.height=l*D+"px";return z()}else{k[n].style.display="block";var y=x.siblings(k[n]);for(var B=0;B<y.length;B++){y[B].style.display="none"}}}};
(function(a){x.Dialog=function(b){defaults=a.extend({type:"dialog",theme:"defaults",title:"",boxID:x.random(10),referID:"",content:"text:loading text",width:"",height:"",time:"",drag:true,lock:true,fixed:false,showbg:true,showborder:true,showtitle:true,position:"",arrow:"left",tips:"",yesBtn:null,noBtn:null,cfns:"",ofns:""},b);x.Dialog.init(defaults)};a.extend(x.Dialog,{data:{_this:null,winarr:[],zindex:870618},init:function(c){if(x.getID(c.boxID)){return}x.Dialog.create(c);x.Dialog.loadContent(c);if(c.yesBtn){x.Dialog.yesBtn(c)}if(c.noBtn){x.Dialog.noBtn(c)}if(c.fixed){x.fixed(c.boxID);x.fixed(c.boxID+"_move_temp")}if(typeof c.time==="number"){setTimeout(function(){x.Dialog.close(c.boxID,c.cfns)},c.time)}if(!x.Browser.isIE){a(window).resize(function(){x.setXY(c.boxID,c.position,c.referID,c.fixed)})}a(".ui_btn_close",_this).live("click",function(){x.Dialog.close(c.boxID,c.cfns);return false});var b=x.Dialog.data.winarr;_this.live("mousedown",function(){this.style.zIndex=x.Dialog.data.zindex+=1;for(var d=0;d<b.length;d++){if(b[d][0]==c.boxID){b[d][1]=this.style.zIndex}}});document.onkeydown=function(k){k=k||window.event;if(k.keyCode==27){var g=[];for(var f=0;f<b.length;f++){g.push(b[f][1])}for(var d=0;d<g.length;d++){if(b[d][1]==g.max()){x.Dialog.close(b[d][0],c.cfns);g.remove(g.max());b.remove(b[d])}}}}},create:function(c){var b='<div class="ui_dialog_wrap"><div id="'+c.boxID+'" class="ui_dialog">';b+='<table class="ui_table_wrap" cellspacing="0" cellpadding="0" border="0"><tbody>';b+='<tr><td class="ui_border ui_td_00"></td><td class="ui_border ui_td_01"></td><td class="ui_border ui_td_02"></td></tr>';b+='<tr><td class="ui_border ui_td_10"></td><td class="ui_td_11"><table class="ui_dialog_main" cellspacing="0" cellpadding="0" border="0"><tbody>';b+='<tr><td><div class="ui_title_wrap"><div class="ui_title"><div class="ui_title_text"><span class="ui_title_icon"></span>'+c.title+'</div><div class="ui_btn_wrap"><span class="ui_btn_close">close</span></div></div></div></td></tr>';b+='<tr><td><div class="ui_content" id="'+c.boxID+'_content"></div></td></tr>';b+='<tr><td><div class="ui_button_wrap"><div class="ui_resize"></div></div></td></tr></tbody></table>';b+='</td><td class="ui_border ui_td_12"></td></tr>';b+='<tr><td class="ui_border ui_td_20"></td><td class="ui_border ui_td_21"></td><td class="ui_border ui_td_22"></td></tr></tbody></table>';b+='<iframe src="about:blank" class="ui_iframe" style="position:absolute;left:0;top:0; filter:alpha(opacity=0);opacity:0; scrolling=no;border:none;z-index:10714;"></iframe>';b+='</div><div class="ui_move_temp" id="'+c.boxID+'_move_temp"></div><div class="ui_overlay"><iframe src="about:blank" style="width:100%;height:'+a(document).height()+'px;filter:alpha(opacity=50);opacity:0.5;scrolling=no;border:none;z-index:870611;"></iframe></div></div>';a(b).appendTo("body");_this=a("#"+c.boxID);_this.css("zIndex",x.Dialog.data.zindex+=1).addClass("ui_dialog_restore").parent().addClass("ui_dialog_theme_"+c.theme);if(c.type=="tips"){c.showtitle=false}if(c.showtitle!=true){a(".ui_title_wrap",_this).remove()}if(c.showbg){_this.parent().find(".ui_overlay").css("visibility","visible")}if(!c.showborder){_this.find(".ui_border").css({width:"0px",height:"0px",fontSize:"0",lineHeight:"0",visibility:"hidden",overflow:"hidden"});_this.find(".ui_resize").css({right:"5px",bottom:"5px"});if(c.type=="dialog"){_this.find(".ui_dialog_main").addClass("ui_box_shadow")}}x.Dialog.setPosition(c)},loadContent:function(j){var e=a(".ui_content",_this),g=x.Dialog.data.winarr;var f='<em class="ui_arrow arrow-'+j.arrow+'" style="z-index:1;"></em><span class="ui_arrow arrow-'+j.arrow+'-in" style="z-index:2;"></span><i class="ui_tips_close">x</i>';$contentType=j.content.substring(0,j.content.indexOf(":"));$content=j.type=="tips"?"<div class='ui_tips_content'><i class=\"ui_tips_content_ico\"></i>"+j.content.substring(j.content.indexOf(":")+1,j.content.length)+"</div>"+f:j.content.substring(j.content.indexOf(":")+1,j.content.length);a.ajaxSetup({global:false});var b=j.width!=""?j.width:"auto",c=j.height!=""?j.height:"auto";e.css({width:b,height:c});if(j.drag){dragBox=true}var d=function(k){g.push([j.boxID,x.getID(j.boxID).style.zIndex,e.width(),e.height()]);if(!k){return}var l=x.safeRange(j.boxID);var m=l.width>400||l.height>300?".ui_move_temp":"";x.loadJS("dialog/x.drag.js",function(){x.drag({obj:j.boxID,handle:".ui_title_text",lock:j.lock,fixed:j.fixed,temp:m})})};switch($contentType){case"text":e.html($content);x.Dialog.setPosition(j);d(dragBox);if(j.ofns!=""&&a.isFunction(j.ofns)){j.ofns(this)}break;case"img":a.ajax({beforeSend:function(){e.html("<img src='style/images/loading.gif' class='ui_box_loading' alt='loading...' />");x.Dialog.setPosition(j)},error:function(){e.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>loading Error</p>");x.Dialog.setPosition(j)},success:function(k){x.loadJS("dialog/x.img.js",function(){x.Img.ready($content,function(){e.html("<img src="+$content+" alt='' />");x.Dialog.setPosition(j);d(dragBox)})});if(j.ofns!=""&&a.isFunction(j.ofns)){j.ofns(this)}}});break;case"swf":a.ajax({beforeSend:function(){e.html("<img src='style/images/loading.gif' class='ui_box_loading' alt='loading...' />");x.Dialog.setPosition(j)},error:function(){e.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>loading Error</p>");x.Dialog.setPosition(j)},success:function(k){x.loadJS("dialog/x.swf.js",function(){e.html("<div id='"+j.boxID+'swf\'><h1>Alternative content</h1><p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p></div><script type="text/javascript">swfobject.embedSWF(\''+$content+"', '"+j.boxID+"swf', '"+j.width+"', '"+j.height+"', '9.0.0', 'expressInstall.swf');<\/script>");a("#"+j.boxID+"swf").css({position:"absolute",left:"0",top:"0",textAlign:"center"});x.Dialog.setPosition(j);d(dragBox)});if(j.ofns!=""&&a.isFunction(j.ofns)){j.ofns(this)}}});break;case"url":var i=$content.split("?");a.ajax({beforeSend:function(){e.html("<img src='style/images/loading.gif' class='ui_box_loading' alt='loading...' />");x.Dialog.setPosition(j)},type:i[0],url:i[1],data:i[2],error:function(){e.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>loading Error</p>");x.Dialog.setPosition(j)},success:function(k){e.html(k);x.Dialog.setPosition(j);d(dragBox);if(j.ofns!=""&&a.isFunction(j.ofns)){j.ofns(this)}}});break;case"iframe":a.ajax({beforeSend:function(){e.html("<img src='style/images/loading.gif' class='ui_box_loading' alt='loading...' />");x.Dialog.setPosition(j)},error:function(){e.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>loading Error</p>");x.Dialog.setPosition(j)},success:function(k){e.html('<iframe src="'+$content+'" style="width:100%;height:100%;" id="'+j.boxID+'frame" scrolling="auto" frameborder="0"></iframe>');a("#"+j.boxID+"frame").bind("load",function(){var m=document.getElementById(j.boxID+"frame");if(j.width==""||j.height==""){try{m=m.contentWindow.document,width=Math.max(m.body.scrollWidth,m.documentElement.scrollWidth),height=Math.max(m.body.scrollHeight,m.documentElement.scrollHeight);_this.find(".ui_content").css({width:width+"px",height:height+"px"})}catch(l){}}else{_this.find(".ui_content").css({width:b+"px",height:c+"px"})}x.Dialog.setPosition(j);d(dragBox);if(j.ofns!=""&&a.isFunction(j.ofns)){j.ofns(this)}})}})}},setPosition:function(d){x.setXY(d.boxID,d.position,d.referID,d.fixed);var f=x.safeRange(d.boxID);a(".ui_iframe",_this).css({width:f.width+"px",height:f.height+"px"});if(d.type=="tips"){var m=d.tips,i=d.arrow=="left"||d.arrow=="right"?"top":"left";var e=m.val||"10";var b=m.style||"default";var j=m.radius||"0";var l=m.auto||true;_this.find(".ui_button_wrap").hide().end().find(".ui_dialog_main").css({border:"none",background:"none"}).find(".ui_content").addClass("ui_tips_style_"+b).css({borderRadius:j+"px",textAlign:"left"}).find(".ui_arrow").css(i,e+"px").end().find(".ui_tips_close").click(function(){x.Dialog.close(d.boxID,d.cfns)});var c=x.getPosition(d.boxID),g=x.getPosition(d.referID),n=x.safeRange(d.referID),k=document.body.scrollTop||document.documentElement.scrollTop;switch(d.arrow){case"left":_this.css({left:c.x+8+"px",top:c.y+"px"});if(l=true&&p.clientWidth-c.x<_this.outerWidth()){_this.css({left:g.x-_this.outerWidth()-8}).find(".ui_arrow").removeClass("ui_arrow_mode_left").addClass("ui_arrow_mode_right")}break;case"right":_this.css({left:c.x-10+"px",top:c.y+"px"});if(l=true&&c.x<0){_this.css({left:g.x+n.width+8}).find(".ui_arrow").removeClass("ui_arrow_mode_right").addClass("ui_arrow_mode_left")}break;case"bottom":_this.css({left:c.x+"px",top:c.y-8+"px"});if(l=true&&c.y<0){_this.css({top:g.y+n.height+8}).find(".ui_arrow").removeClass("ui_arrow_mode_bottom").addClass("ui_arrow_mode_top")}break;case"top":_this.css({left:c.x+"px",top:c.y+8+"px"});if(l=true&&p.clientHeight-c.y+k<_this.outerHeight()){_this.css({top:g.y-_this.outerHeight()-8}).find(".ui_arrow").removeClass("ui_arrow_mode_top").addClass("ui_arrow_mode_bottom")}break}}},yesBtn:function(e){var c=e.yesBtn[1]||function(){},d=e.yesBtn[0]||"\u786E\u5B9A";var b='<button class="ui_box_btn ui_box_btn_yes">'+d+"</button>";_this.find(".ui_button_wrap").append(b);if(c!=""&&a.isFunction(c)){_this.find(".ui_box_btn_yes").click(function(){var g=c();if(g!=false){x.Dialog.close(e.boxID,e.cfns)}})}},noBtn:function(e){var b=e.noBtn[1]||function(){},d=e.noBtn[0]||"\u53D6\u6D88";var c='<button class="ui_box_btn ui_box_btn_no">'+d+"</button>";_this.find(".ui_button_wrap").append(c);if(b!=""&&a.isFunction(b)){_this.find(".ui_box_btn_no").click(function(){var g=b();if(g!=false){x.Dialog.close(e.boxID,e.cfns)}})}},min:function(b){var c=a("#"+b.boxID);a(".ui_btn_min",c).live("click",function(){c.find(".ui_content").css({width:"0",height:"0",display:"none",visibility:"hidden"}).end().find(".ui_button_wrap").hide();var d=x.safeRange(b.boxID);a(".ui_iframe",c).css({width:d.width+"px",height:d.height+"px"});c.addClass("ui_dialog_min").removeClass("ui_dialog_restore ui_dialog_max");if(b.drag){x.config.drag=true}return false})},max:function(b){var c=a("#"+b.boxID);a(".ui_btn_max",c).live("click",function(){var d=x.pageSize.get();w=d.clientWidth-(b.showborder?10:2);h=d.clientHeight-(b.showtitle?34:2)-(b.button?36:0);c.find(".ui_content").css({width:w+"px",height:h+"px"});x.Dialog.setPosition(b);c.addClass("ui_dialog_max").removeClass("ui_dialog_restore ui_dialog_min");if(b.drag){x.config.drag=false;c.find(".ui_title_text").css("cursor","default")}return false})},restore:function(c){var d=a("#"+c.boxID);var b=x.Dialog.data.winarr;a(".ui_btn_restore",d).live("click",function(){for(var e=0;e<b.length;e++){if(c.boxID==b[e][0]){d.find(".ui_content").css({width:b[e][2]+"px",height:b[e][3]+"px",display:"block",visibility:"visible"}).end().find(".ui_button_wrap").show();x.Dialog.setPosition(c);d.addClass("ui_dialog_restore").removeClass("ui_dialog_min ui_dialog_max")}}if(c.drag){x.config.drag=true;d.find(".ui_title_text").css("cursor","move")}return false})},close:function(d,c){if(typeof d==="string"){box=a("#"+d)}else{alert("\u7487\u950b\u5bda\u7039\u6c2c\u810a\u9351\u8679\u7365\u9359\uff47\u6b91ID\u951b\ufffd");return}if(box.length!=0){box.parent().remove();a("#XYTipsWindowBg").animate({opacity:"0"},100,function(){a(this).remove()});for(var b=0;b<x.Dialog.data.winarr.length;b++){if(d==x.Dialog.data.winarr[b][0]){x.Dialog.data.winarr.remove(x.Dialog.data.winarr[b])}}if(c!=""&&a.isFunction(c)){c(this)}}}})})(jQuery);
(function(a){a.fn.placeholder=function(b){var d={labelMode:false,labelStyle:{},labelAlpha:false,labelAcross:false};var e=a.extend({},d,b||{});var c=function(g,f){if(g.val()===""){f.css("opacity",0.4).html(g.data("placeholder"))}else{f.html("")}};a(this).each(function(){var h=a(this),g="placeholder" in document.createElement("input"),j=h.attr("placeholder");if(!j||(!e.labelMode&&g)||(e.labelMode&&!e.labelAcross&&g)){return}h.data("placeholder",j);if(e.labelMode){var f=h.attr("id"),i=null;if(!f){f="placeholder"+Math.random();h.attr("id",f)}i=a('<label for="'+f+'" class="placeholder" id="'+f+'For"></label>').css(a.extend({position:"absolute",color:"graytext",cursor:"text"},e.labelStyle)).insertBefore(h);if(e.labelAlpha){h.bind({focus:function(){c(a(this),i)},input:function(){c(a(this),i)},blur:function(){if(this.value===""){i.css("opacity",1).html(j)}}});if(!window.screenX){h.bind("keyup",function(){c(a(this),i)});h.get(0).onpaste=function(){setTimeout(function(){c(h,i)},30)}}i.get(0).oncontextmenu=function(){h.trigger("focus");return false}}else{h.bind({focus:function(){i.html("")},blur:function(){if(a(this).val()===""){i.html(j)}}})}if(e.labelAcross){h.removeAttr("placeholder")}if(h.val()===""){i.html(j)}}else{h.bind({focus:function(){if(a(this).val()===j){a(this).val("")}a(this).css("color","")},blur:function(){if(a(this).val()===""){a(this).val(j).css("color","graytext")}}});if(h.val()===""){h.val(j).css("color","graytext")}}})}})(jQuery);
(function(a){a.extend({ImportBasePath:"",fileinfo:function(c){c=c.replace(/^\s|\s$/g,"");var b;if(/\.\w+$/.test(c)){b=c.match(/([^\/\\]+)\.(\w+)$/);if(b){if(b[2]=="js"){return{filename:b[1],ext:b[2],tag:"script"}}else{if(b[2]=="css"){return{filename:b[1],ext:b[2],tag:"link"}}else{return{filename:b[1],ext:b[2],tag:null}}}}else{return{filename:null,ext:null}}}else{b=c.match(/([^\/\\]+)$/);if(b){return{filename:b[1],ext:null,tag:null}}else{return{filename:null,ext:null,tag:null}}}},fileExist:function(c,e,b){var f=document.getElementsByTagName(e);for(var d=0;d<f.length;d++){if(f[d].getAttribute(b)==a.ImportBasePath+c){return true}}return false},createElement:function(c,d){switch(d){case"script":if(!a.fileExist(c,d,"src")){var e=document.createElement(d);e.setAttribute("language","javascript");e.setAttribute("type","text/javascript");e.setAttribute("src",a.ImportBasePath+c);return e}else{return false}break;case"link":if(!a.fileExist(c,d,"href")){var b=document.createElement(d);b.setAttribute("type","text/css");b.setAttribute("rel","stylesheet");b.setAttribute("href",a.ImportBasePath+c);return b}else{return false}break;default:return false;break}},cssReady:function(d,e){function c(){if(document.styleSheets[d]){if(typeof e=="function"){e()}window.clearInterval(b)}}var b=window.setInterval(c,200)},include:function(e,h){var g=document.getElementsByTagName("head")[0];var b=[];typeof e=="string"?b[0]=e:b=e;for(var d=0;d<b.length;d++){var c=a.fileinfo(b[d]).tag;var f=[];if(c!==null){f[d]=a.createElement(b[d],c);if(f[d]){g.appendChild(f[d]);if(a.browser.msie){f[d].onreadystatechange=function(){if(this.readyState==="loaded"||this.readyState==="complete"){if(typeof h=="function"){h()}}}}else{if(c=="link"){a.cssReady(d,h)}else{f[d].onload=function(){if(typeof h=="function"){h()}}}}}}else{return false}}}})})(jQuery);
(function(a){a.fn.checkboxAll=function(b){var d={chkName:"chkItem",chkClass:"chkbg",splitObj:",",callback:function(){}},b=a.extend(d,b),f=a(this),e=a("input[name='"+b.chkName+"']"),c=0;e.click(function(){var i=0;var g="";var h="";a("[name="+b.chkName+"]").each(function(){if(a(this).attr("checked")){i+=Number(a(this).attr("extVal"));g+=a(this).attr("extVal")+b.splitObj;h+=a(this).attr("extHtml")+b.splitObj;a(this).parent().parent().addClass(b.chkClass)}else{a(this).parent().parent().removeClass(b.chkClass)}if(e.filter(":checked").length==e.length){f.attr("checked",true)}else{f.removeAttr("checked")}});c=e.filter(":checked").length;if(typeof b.callback==="function"){b.callback(g,c,i,h)}});return f.each(function(){a(this).click(function(){var i=0;var g="";var h="";a("[name="+b.chkName+"]").each(function(){if(f.attr("checked")){i+=Number(a(this).attr("extVal"));g+=a(this).attr("extVal")+b.splitObj;h+=a(this).attr("extHtml")+b.splitObj;e.attr("checked",true);e.parent().parent().addClass(b.chkClass)}else{e.removeAttr("checked");e.parent().parent().removeClass(b.chkClass)}});c=e.filter(":checked").length;if(typeof b.callback==="function"){b.callback(g,c,i,h)}})})}})(jQuery);
jQuery.fn.fixedtableheader=function(a){var c=jQuery.extend({headerrowsize:1,highlightrow:false,highlightclass:"highlight"},a);this.each(function(g){var j=$(this);var e=j.find("tr:lt("+c.headerrowsize+")");var f="th";if(e.find(f).length==0){f="td"}if(e.find(f).length>0){e.find(f).each(function(){$(this).css("width",$(this).width()+1)});var h=j.clone().empty();var d=b(j);h.attr("id","fixedtableheader"+g).css({position:"fixed",marginTop:"0px",top:"0",left:j.offset().left}).append(e.clone()).width(d).hide().appendTo($("body"));if(c.highlightrow){$("tr:gt("+(c.headerrowsize-1)+")",j).hover(function(){$(this).addClass(c.highlightclass)},function(){$(this).removeClass(c.highlightclass)})}$(window).scroll(function(){if(jQuery.browser.msie&&jQuery.browser.version=="6.0"){h.css({position:"absolute",marginTop:"0px",top:$(window).scrollTop(),left:j.offset().left})}else{h.css({position:"fixed",marginTop:"0px",top:"0",left:j.offset().left-$(window).scrollLeft()})}var i=$(window).scrollTop();var k=e.offset().top;if(i>k&&i<=(k+j.height()-e.height())){h.show()}else{h.hide()}});$(window).resize(function(){if(h.outerWidth()!=j.outerWidth()){e.find(f).each(function(k){var i=$(this).width();$(this).css("width",i);h.find(f).eq(k).css("width",i)});h.width(j.outerWidth())}h.css("left",j.offset().left)})}});function b(e){var d=e.outerWidth();return d}};
x.dropdown=function(e,c,f){if($(c).length>0){$(e+" "+c).hover(function(){$(this).addClass(f)},function(){$(this).removeClass(f)})}else{return}};(function(a){a.fn.extend({dropDown:function(b){var e={event:"mouseover",className:"cur",dropDownBox:".ui-dropdown",timer:1000,fadeSpeed:100,duration:500};if(b){a.extend(e,b)}var d=this,c=a(e.dropDownBox);d.each(function(){$this=a(this);$this.hover(function(){clearTimeout(e.timer);c.fadeIn(e.fadeSpeed);c.parent().addClass(e.className)},function(){e.timer=setTimeout(function(){c.fadeOut(e.fadeSpeed);c.parent().removeClass(e.className)},e.duration)});c.hover(function(){clearTimeout(e.timer);c.show();c.parent().addClass(e.className)},function(){e.timer=setTimeout(function(){c.fadeOut(e.fadeSpeed);c.parent().removeClass(e.className)},e.duration)})})}})})(jQuery);
(function(a){a.extend(a.fn,{validate:function(b){if(!this.length){b&&b.debug&&window.console&&console.warn("nothing selected, can't validate, returning nothing");return}var c=a.data(this[0],"validator");if(c){return c}c=new a.validator(b,this[0]);a.data(this[0],"validator",c);if(c.settings.onsubmit){this.find("input, button").filter(".cancel").click(function(){c.cancelSubmit=true});this.submit(function(d){if(c.settings.debug){d.preventDefault()}function e(){if(c.settings.submitHandler){c.settings.submitHandler.call(c,c.currentForm);return false}return true}if(c.cancelSubmit){c.cancelSubmit=false;return e()}if(c.form()){if(c.pendingRequest){c.formSubmitted=true;return false}return e()}else{c.focusInvalid();return false}})}return c},valid:function(){if(a(this[0]).is("form")){return this.validate().form()}else{var c=false;var b=a(this[0].form).validate();this.each(function(){c|=b.element(this)});return c}},removeAttrs:function(d){var b={},c=this;a.each(d.split(/\s/),function(e,f){b[f]=c.attr(f);c.removeAttr(f)});return b},rules:function(e,b){var g=this[0];if(e){var d=a.data(g.form,"validator").settings;var i=d.rules;var j=a.validator.staticRules(g);switch(e){case"add":a.extend(j,a.validator.normalizeRule(b));i[g.name]=j;if(b.messages){d.messages[g.name]=a.extend(d.messages[g.name],b.messages)}break;case"remove":if(!b){delete i[g.name];return j}var h={};a.each(b.split(/\s/),function(k,l){h[l]=j[l];delete j[l]});return h}}var f=a.validator.normalizeRules(a.extend({},a.validator.metadataRules(g),a.validator.classRules(g),a.validator.attributeRules(g),a.validator.staticRules(g)),g);if(f.required){var c=f.required;delete f.required;f=a.extend({required:c},f)}return f}});a.extend(a.expr[":"],{blank:function(b){return !a.trim(b.value)},filled:function(b){return !!a.trim(b.value)},unchecked:function(b){return !b.checked}});a.format=function(b,c){if(arguments.length==1){return function(){var d=a.makeArray(arguments);d.unshift(b);return a.format.apply(this,d)}}if(arguments.length>2&&c.constructor!=Array){c=a.makeArray(arguments).slice(1)}if(c.constructor!=Array){c=[c]}a.each(c,function(d,e){b=b.replace(new RegExp("\\{"+d+"\\}","g"),e)});return b};a.validator=function(b,c){this.settings=a.extend({},a.validator.defaults,b);this.currentForm=c;this.init()};a.extend(a.validator,{defaults:{messages:{},groups:{},rules:{},successClass:"txt-success",errorClass:"txt-error",errorTipClass:"tip-error",errorElement:"span",focusInvalid:true,errorContainer:a([]),errorLabelContainer:a([]),onsubmit:true,ignore:[],ignoreTitle:false,onfocusin:function(b){this.lastActive=b;if(this.settings.focusCleanup&&!this.blockFocusCleanup){this.settings.unhighlight&&this.settings.unhighlight.call(this,b,this.settings.errorClass);this.errorsFor(b).hide()}},onfocusout:function(b){if(!this.checkable(b)&&(b.name in this.submitted||!this.optional(b))){this.element(b)}},onkeyup:function(b){if(b.name in this.submitted||b==this.lastElement){this.element(b)}},onclick:function(b){if(b.name in this.submitted){this.element(b)}},highlight:function(c,b,d){a(c).parent().addClass(b).removeClass(d)},unhighlight:function(c,b,d){a(c).parent().removeClass(b).addClass(d)}},setDefaults:function(b){a.extend(a.validator.defaults,b)},messages:{required:"This field is required.",remote:"Please fix this field.",email:"Please enter a valid email address.",url:"Please enter a valid URL.",date:"Please enter a valid date.",dateISO:"Please enter a valid date (ISO).",dateDE:"Bitte geben Sie ein g\u7709ltiges Datum ein.",number:"Please enter a valid number.",numberDE:"Bitte geben Sie eine Nummer ein.",digits:"Please enter only digits",creditcard:"Please enter a valid credit card number.",equalTo:"Please enter the same value again.",accept:"Please enter a value with a valid extension.",maxlength:a.format("Please enter no more than {0} characters."),minlength:a.format("Please enter at least {0} characters."),rangelength:a.format("Please enter a value between {0} and {1} characters long."),range:a.format("Please enter a value between {0} and {1}."),max:a.format("Please enter a value less than or equal to {0}."),min:a.format("Please enter a value greater than or equal to {0}.")},autoCreateRanges:false,prototype:{init:function(){this.labelContainer=a(this.settings.errorLabelContainer);this.errorContext=this.labelContainer.length&&this.labelContainer||a(this.currentForm);this.containers=a(this.settings.errorContainer).add(this.settings.errorLabelContainer);this.submitted={};this.valueCache={};this.pendingRequest=0;this.pending={};this.invalid={};this.reset();var b=(this.groups={});a.each(this.settings.groups,function(e,f){a.each(f.split(/\s/),function(h,g){b[g]=e})});var d=this.settings.rules;a.each(d,function(e,f){d[e]=a.validator.normalizeRule(f)});function c(f){var e=a.data(this[0].form,"validator");e.settings["on"+f.type]&&e.settings["on"+f.type].call(e,this[0])}a(this.currentForm).delegate("focusin focusout keyup",":text, :password, :file, select, textarea",c).delegate("click",":radio, :checkbox",c);if(this.settings.invalidHandler){a(this.currentForm).bind("invalid-form.validate",this.settings.invalidHandler)}},form:function(){this.checkForm();a.extend(this.submitted,this.errorMap);this.invalid=a.extend({},this.errorMap);if(!this.valid()){a(this.currentForm).triggerHandler("invalid-form",[this])}this.showErrors();return this.valid()},checkForm:function(){this.prepareForm();for(var b=0,c=(this.currentElements=this.elements());c[b];b++){this.check(c[b])}return this.valid()},element:function(c){c=this.clean(c);this.lastElement=c;this.prepareElement(c);this.currentElements=a(c);var b=this.check(c);if(b){delete this.invalid[c.name]}else{this.invalid[c.name]=true}if(!this.numberOfInvalids()){this.toHide=this.toHide.add(this.containers)}this.showErrors();return b},showErrors:function(c){if(c){a.extend(this.errorMap,c);this.errorList=[];for(var b in c){this.errorList.push({message:c[b],element:this.findByName(b)[0]})}this.successList=a.grep(this.successList,function(d){return !(d.name in c)})}this.settings.showErrors?this.settings.showErrors.call(this,this.errorMap,this.errorList):this.defaultShowErrors()},resetForm:function(){if(a.fn.resetForm){a(this.currentForm).resetForm()}this.submitted={};this.prepareForm();this.hideErrors();this.elements().removeClass(this.settings.errorClass)},numberOfInvalids:function(){return this.objectLength(this.invalid)},objectLength:function(d){var c=0;for(var b in d){c++}return c},hideErrors:function(){this.addWrapper(this.toHide).hide()},valid:function(){return this.size()==0},size:function(){return this.errorList.length},focusInvalid:function(){if(this.settings.focusInvalid){try{a(this.findLastActive()||this.errorList.length&&this.errorList[0].element||[]).filter(":visible").focus()}catch(b){}}},findLastActive:function(){var b=this.lastActive;return b&&a.grep(this.errorList,function(c){return c.element.name==b.name}).length==1&&b},elements:function(){var c=this,b={};return a([]).add(this.currentForm.elements).filter(":input").not(":submit, :reset, :image, [disabled]").not(this.settings.ignore).filter(function(){!this.name&&c.settings.debug&&window.console&&console.error("%o has no name assigned",this);if(this.name in b||!c.objectLength(a(this).rules())){return false}b[this.name]=true;return true})},clean:function(b){return a(b)[0]},errors:function(){return a(this.settings.errorElement+"."+this.settings.errorTipClass,this.errorContext)},reset:function(){this.successList=[];this.errorList=[];this.errorMap={};this.toShow=a([]);this.toHide=a([]);this.formSubmitted=false;this.currentElements=a([])},prepareForm:function(){this.reset();this.toHide=this.errors().add(this.containers)},prepareElement:function(b){this.reset();this.toHide=this.errorsFor(b)},check:function(c){c=this.clean(c);if(this.checkable(c)){c=this.findByName(c.name)[0]}var h=a(c).rules();var d=false;for(method in h){var g={method:method,parameters:h[method]};try{var b=a.validator.methods[method].call(this,c.value.replace(/\r/g,""),c,g.parameters);if(b=="dependency-mismatch"){d=true;continue}d=false;if(b=="pending"){this.toHide=this.toHide.not(this.errorsFor(c));return}if(!b){this.formatAndAdd(c,g);return false}}catch(f){this.settings.debug&&window.console&&console.log("exception occured when checking element "+c.id+", check the '"+g.method+"' method");throw f}}if(d){return}if(this.objectLength(h)){this.successList.push(c)}return true},customMetaMessage:function(b,d){if(!a.metadata){return}var c=this.settings.meta?a(b).metadata()[this.settings.meta]:a(b).metadata();return c&&c.messages&&c.messages[d]},customMessage:function(c,d){var b=this.settings.messages[c];return b&&(b.constructor==String?b:b[d])},findDefined:function(){for(var b=0;b<arguments.length;b++){if(arguments[b]!==undefined){return arguments[b]}}return undefined},defaultMessage:function(b,c){return this.findDefined(this.customMessage(b.name,c),this.customMetaMessage(b,c),!this.settings.ignoreTitle&&b.title||undefined,a.validator.messages[c],"<strong>Warning: No message defined for "+b.name+"</strong>")},formatAndAdd:function(b,d){var c=this.defaultMessage(b,d.method);if(typeof c=="function"){c=c.call(this,d.parameters,b)}this.errorList.push({message:c,element:b});this.errorMap[b.name]=c;this.submitted[b.name]=c},addWrapper:function(b){if(this.settings.wrapper){b=b.add(b.parents(this.settings.wrapper))}return b},defaultShowErrors:function(){for(var c=0;this.errorList[c];c++){var b=this.errorList[c];this.settings.highlight&&this.settings.highlight.call(this,b.element,this.settings.errorClass,this.settings.successClass);this.showLabel(b.element,b.message)}if(this.errorList.length){this.toShow=this.toShow.add(this.containers)}if(this.settings.success){for(var c=0;this.successList[c];c++){this.showLabel(this.successList[c])}}if(this.settings.unhighlight){for(var c=0,d=this.validElements();d[c];c++){this.settings.unhighlight.call(this,d[c],this.settings.errorClass,this.settings.successClass)}}this.toHide=this.toHide.not(this.toShow);this.hideErrors();this.addWrapper(this.toShow).show()},validElements:function(){return this.currentElements.not(this.invalidElements())},invalidElements:function(){return a(this.errorList).map(function(){return this.element})},showLabel:function(c,d){var b=this.errorsFor(c);this.errorsFor(c).next().hide();if(b.length){b.removeClass().addClass(this.settings.errorTipClass);b.attr("generated")&&b.html(d)}else{b=a("<"+this.settings.errorElement+"/>").attr({"for":this.idOrName(c),generated:true}).addClass(this.settings.errorTipClass).html(d||"");if(this.settings.wrapper){b=b.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()}if(!this.labelContainer.append(b).length){this.settings.errorPlacement?this.settings.errorPlacement(b,a(c)):b.insertAfter(a(c).parent())}}if(!d&&this.settings.success){b.text("");typeof this.settings.success=="string"?b.addClass(this.settings.success):this.settings.success(b)}this.toShow=this.toShow.add(b)},errorsFor:function(b){return this.errors().filter("[htmlfor='"+this.idOrName(b)+"']")},idOrName:function(b){return this.groups[b.name]||(this.checkable(b)?b.name:b.id||b.name)},checkable:function(b){return/radio|checkbox/i.test(b.type)},findByName:function(b){var c=this.currentForm;return a(document.getElementsByName(b)).map(function(d,e){return e.form==c&&e.name==b&&e||null})},getLength:function(c,b){switch(b.nodeName.toLowerCase()){case"select":return a("option:selected",b).length;case"input":if(this.checkable(b)){return this.findByName(b.name).filter(":checked").length}}return c.length},depend:function(c,b){return this.dependTypes[typeof c]?this.dependTypes[typeof c](c,b):true},dependTypes:{"boolean":function(c,b){return c},string:function(c,b){return !!a(c,b.form).length},"function":function(c,b){return c(b)}},optional:function(b){return !a.validator.methods.required.call(this,a.trim(b.value),b)&&"dependency-mismatch"},startRequest:function(b){if(!this.pending[b.name]){this.pendingRequest++;this.pending[b.name]=true}},stopRequest:function(b,c){this.pendingRequest--;if(this.pendingRequest<0){this.pendingRequest=0}delete this.pending[b.name];if(c&&this.pendingRequest==0&&this.formSubmitted&&this.form()){a(this.currentForm).submit()}else{if(!c&&this.pendingRequest==0&&this.formSubmitted){a(this.currentForm).triggerHandler("invalid-form",[this])}}},previousValue:function(b){return a.data(b,"previousValue")||a.data(b,"previousValue",previous={old:null,valid:true,message:this.defaultMessage(b,"remote")})}},classRuleSettings:{required:{required:true},email:{email:true},url:{url:true},date:{date:true},dateISO:{dateISO:true},dateDE:{dateDE:true},number:{number:true},numberDE:{numberDE:true},digits:{digits:true},creditcard:{creditcard:true}},addClassRules:function(b,c){b.constructor==String?this.classRuleSettings[b]=c:a.extend(this.classRuleSettings,b)},classRules:function(c){var d={};var b=a(c).attr("class");b&&a.each(b.split(" "),function(){if(this in a.validator.classRuleSettings){a.extend(d,a.validator.classRuleSettings[this])}});return d},attributeRules:function(c){var e={};var b=a(c);for(method in a.validator.methods){var d=b.attr(method);if(d){e[method]=d}}if(e.maxlength&&/-1|2147483647|524288/.test(e.maxlength)){delete e.maxlength}return e},metadataRules:function(b){if(!a.metadata){return{}}var c=a.data(b.form,"validator").settings.meta;return c?a(b).metadata()[c]:a(b).metadata()},staticRules:function(c){var d={};var b=a.data(c.form,"validator");if(b.settings.rules){d=a.validator.normalizeRule(b.settings.rules[c.name])||{}}return d},normalizeRules:function(c,b){a.each(c,function(f,e){if(e===false){delete c[f];return}if(e.param||e.depends){var d=true;switch(typeof e.depends){case"string":d=!!a(e.depends,b.form).length;break;case"function":d=e.depends.call(b,b);break}if(d){c[f]=e.param!==undefined?e.param:true}else{delete c[f]}}});a.each(c,function(d,e){c[d]=a.isFunction(e)?e(b):e});a.each(["minlength","maxlength","min","max"],function(){if(c[this]){c[this]=Number(c[this])}});a.each(["rangelength","range"],function(){if(c[this]){c[this]=[Number(c[this][0]),Number(c[this][1])]}});if(a.validator.autoCreateRanges){if(c.min&&c.max){c.range=[c.min,c.max];delete c.min;delete c.max}if(c.minlength&&c.maxlength){c.rangelength=[c.minlength,c.maxlength];delete c.minlength;delete c.maxlength}}if(c.messages){delete c.messages}return c},normalizeRule:function(c){if(typeof c=="string"){var b={};a.each(c.split(/\s/),function(){b[this]=true});c=b}return c},addMethod:function(b,d,c){a.validator.methods[b]=d;a.validator.messages[b]=c;if(d.length<3){a.validator.addClassRules(b,a.validator.normalizeRule(b))}},methods:{required:function(d,c,e){if(!this.depend(e,c)){return"dependency-mismatch"}switch(c.nodeName.toLowerCase()){case"select":var b=a("option:selected",c);return b.length>0&&(c.type=="select-multiple"||(a.browser.msie&&!(b[0].attributes.value.specified)?b[0].text:b[0].value).length>0);case"input":if(this.checkable(c)){return this.getLength(d,c)>0}default:return a.trim(d).length>0}},remote:function(f,c,g){if(this.optional(c)){return"dependency-mismatch"}var d=this.previousValue(c);if(!this.settings.messages[c.name]){this.settings.messages[c.name]={}}this.settings.messages[c.name].remote=typeof d.message=="function"?d.message(f):d.message;g=typeof g=="string"&&{url:g}||g;if(d.old!==f){d.old=f;var b=this;this.startRequest(c);var e={};e[c.name]=f;a.ajax(a.extend(true,{url:g,mode:"abort",port:"validate"+c.name,dataType:"json",data:e,success:function(i){if(i){var h=b.formSubmitted;b.prepareElement(c);b.formSubmitted=h;b.successList.push(c);b.showErrors()}else{var j={};j[c.name]=i||b.defaultMessage(c,"remote");b.showErrors(j)}d.valid=i;b.stopRequest(c,i)}},g));return"pending"}else{if(this.pending[c.name]){return"pending"}}return d.valid},minlength:function(c,b,d){return this.optional(b)||this.getLength(a.trim(c),b)>=d},maxlength:function(c,b,d){return this.optional(b)||this.getLength(a.trim(c),b)<=d},rangelength:function(d,b,e){var c=this.getLength(a.trim(d),b);return this.optional(b)||(c>=e[0]&&c<=e[1])},min:function(c,b,d){return this.optional(b)||c>=d},max:function(c,b,d){return this.optional(b)||c<=d},range:function(c,b,d){return this.optional(b)||(c>=d[0]&&c<=d[1])},email:function(c,b){return this.optional(b)||/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(c)},url:function(c,b){return this.optional(b)||/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(c)},date:function(c,b){return this.optional(b)||!/Invalid|NaN/.test(new Date(c))},dateISO:function(c,b){return this.optional(b)||/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(c)},dateDE:function(c,b){return this.optional(b)||/^\d\d?\.\d\d?\.\d\d\d?\d?$/.test(c)},number:function(c,b){return this.optional(b)||/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(c)},numberDE:function(c,b){return this.optional(b)||/^-?(?:\d+|\d{1,3}(?:\.\d{3})+)(?:,\d+)?$/.test(c)},digits:function(c,b){return this.optional(b)||/^\d+$/.test(c)},creditcard:function(f,c){if(this.optional(c)){return"dependency-mismatch"}if(/[^0-9-]+/.test(f)){return false}var g=0,e=0,b=false;f=f.replace(/\D/g,"");for(n=f.length-1;n>=0;n--){var d=f.charAt(n);var e=parseInt(d,10);if(b){if((e*=2)>9){e-=9}}g+=e;b=!b}return(g%10)==0},accept:function(c,b,d){d=typeof d=="string"?d:"png|jpe?g|gif";return this.optional(b)||c.match(new RegExp(".("+d+")$","i"))},equalTo:function(c,b,d){return c==a(d).val()}}})})(jQuery);(function(c){var b=c.ajax;var a={};c.ajax=function(e){e=c.extend(e,c.extend({},c.ajaxSettings,e));var d=e.port;if(e.mode=="abort"){if(a[d]){a[d].abort()}return(a[d]=b.apply(this,arguments))}return b.apply(this,arguments)}})(jQuery);(function(a){a.each({focus:"focusin",blur:"focusout"},function(c,b){a.event.special[b]={setup:function(){if(a.browser.msie){return false}this.addEventListener(c,a.event.special[b].handler,true)},teardown:function(){if(a.browser.msie){return false}this.removeEventListener(c,a.event.special[b].handler,true)},handler:function(d){arguments[0]=a.event.fix(d);arguments[0].type=b;return a.event.handle.apply(this,arguments)}}});a.extend(a.fn,{delegate:function(d,c,b){return this.bind(d,function(e){var f=a(e.target);if(f.is(c)){return b.apply(f,arguments)}})},triggerEvent:function(b,c){return this.triggerHandler(b,[a.event.fix({type:b,target:c})])}})})(jQuery);
(function($){$.extend({metadata:{defaults:{type:"class",name:"metadata",cre:/({.*})/,single:"metadata"},setType:function(type,name){this.defaults.type=type;this.defaults.name=name},get:function(elem,opts){var settings=$.extend({},this.defaults,opts);if(!settings.single.length){settings.single="metadata"}var data=$.data(elem,settings.single);if(data){return data}data="{}";if(settings.type=="class"){var m=settings.cre.exec(elem.className);if(m){data=m[1]}}else{if(settings.type=="elem"){if(!elem.getElementsByTagName){return undefined}var e=elem.getElementsByTagName(settings.name);if(e.length){data=$.trim(e[0].innerHTML)}}else{if(elem.getAttribute!=undefined){var attr=elem.getAttribute(settings.name);if(attr){data=attr}}}}if(data.indexOf("{")<0){data="{"+data+"}"}data=eval("("+data+")");$.data(elem,settings.single,data);return data}}});$.fn.metadata=function(opts){return $.metadata.get(this[0],opts)}})(jQuery);
jQuery.extend(jQuery.validator.messages, {
	required: "此项不为空",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: $.format("请输入一个 长度最多是 {0} 的字符串"),
	minlength: $.format("请输入一个 长度最少是 {0} 的字符串"),
	rangelength: $.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
	range: $.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: $.format("请输入一个最大为{0 的值"),
	min: $.format("请输入一个最小为{0}的值")
});
(function($){
var _dropBox;
$.fn.dropBox = function(options){
	var defaults = {
		width:"300",
		height:"",
		url:"",
		content:"hello word",
		left:"",
		act:"mouseover"
	};
	var opts = $.extend(defaults, options);
	this.bind(opts.act, function(event){
		showdropBox(event, opts);
	});
	this.bind("mouseout", function(event){
		if (!_dropBox) {
			_dropBox.hide();
		}
	});
	this.css("cursor", "pointer");
};
function showdropBox(event, opts){
	target = $(event.target);
	if (!_dropBox) {
		_dropBox = $("<div/>");
		_dropBox.addClass("dropBox")
		.css({
			width:opts.width,
			height:opts.height,
			position:"absolute"
		})
		.appendTo($("body"));
		_dropBox.append($("<iframe class='ie6iframe'></iframe>").css("width", _dropBox.width()).css("height", _dropBox.outerHeight(true)));
		_dropBox.bind("mouseenter", function(event){
			$(this).show();
		});
		_dropBox.bind("mouseleave", function(event){
			$(this).hide();
		});
	}
	if(opts.url){
		$.ajax({
			dataType:"html",
			url:opts.url,
			//cache:false,
			async: false,
			success: function(data){
				_dropBox.html("<div class='mod-drop-box'>"+data+"</div>");
			},
			error: function(){
				_dropBox.html("<div class='mod-drop-box'>数据加载错误</div>");
			}
		});
	}else{
		_dropBox.html("<div class='mod-drop-box'><div class='x-operation-drop'>"+opts.content+"</div></div>");
	}
	_dropBox.css({
		top:target.offset().top-10,
		left:target.offset().left +target.width()
	}).show();


};
})(jQuery);
jQuery(function ($) {
    if (x.Browser.isIE6 || x.Browser.isIE7) {
        $("input,textarea").focus(function () {
            $(this).addClass("txt-focus");
        }).blur(function () {
            $(this).removeClass("txt-focus");
        });
    };
});

/*
	折叠
*/
x.toggleTag = function(toggleId,obj){
	 var obj=$(toggleId).attr("relId");
	 if($("#"+obj).is(":hidden")){
		$(toggleId).removeClass("toggle_down").addClass("toggle_up");
		$("#"+obj).show();
	 }else{
		 $(toggleId).removeClass("toggle_up").addClass("toggle_down");
		 $("#"+obj).hide();
	 }
};

/*
	添加到收藏夹
	url		地址
	title		标题
*/
x.addFav = function(url, title) {
	try {
		window.external.addFavorite(url, title);
	} catch(e) {
		try {
			window.sidebar.addPanel(title, url, "");
		} catch(e) {
			alert("加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
};

/*
	设为首页
	url		地址
	title		标题
*/
x.setHome = function(obj, url) {
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(url);
	} catch(e) {
		if(window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch(e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			};
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', url);
		}
	}
};

/*
	返回TOP
*/
x.scrollToTop = function (name,e) {
	if ($(e).length){
		$(e).hide();
		if ($(window).scrollTop() != "0") {
			$(e).fadeIn("slow")
		}
		var scrollDiv = $(e);
		$(window).scroll(function () {
			if ($(window).scrollTop() == "0") {
				$(scrollDiv).fadeOut("slow");
				$(name).fadeOut("slow");
			} else {
				$(scrollDiv).fadeIn("slow");
				$(name).fadeIn("slow");
			}
		});
	}
	$(e).click(function () {
		$("html,body").stop().animate({
			scrollTop: 0
		}, {
			duration: 200,
			queue: false
		});
		return false;
	})
};

/*
	@设置字体大小
	@id		设置的ID
	@obj		被设置的对像
	@callback	回调函数
*/
x.setfont = function(id, obj, callback) {
	var o = obj == "html" || obj == "body" ? $(obj) : $("#" + obj);
	$("#" + id).find("a").each(function() {
		var size = $(this).attr("rel");
		$(this).css("font-size", size + "px").click(function() {
			o.css("font-size", size + "px");
			$(this).addClass("curFont").siblings().removeClass("curFont").end().css("font-size", size + "px");
			x.cookie.set("fontSize", size);
			x.cookie.set("curFontSizeName", $(this).attr("class"));
			if(o.callback != "" && $.isFunction(o.callback)) o.callback($(this));
			return false;
		});
	});
	var _size = x.cookie.get("fontSize");
	var _name = x.cookie.get("curFontSizeName");
	if(_name != "") {
		o.css("font-size", _size + "px");
		switch(_name) {
		case "smallFont curFont":
			$(".smallFont").addClass("curFont");
			break;
		case "midFont curFont":
			$(".midFont").addClass("curFont");
			break;
		case "bigFont curFont":
			$(".bigFont").addClass("curFont");
			break;
		default:
			$(".midFont").addClass("curFont");
		}
	} else {
		o.css("font-size", $("#" + id).find("a:first").attr("rel") + "px");
		$(".midFont").addClass("curFont");
	};
};

/*
	高级查询
*/
x.advanceSearch = function(id, obj) {
	var _cookie = x.cookie.get("adv-search");
	if(_cookie != "") {
		$(obj).show();
		$(id).html("隐藏高级查询");
		$("#btn-advance-td").addClass("v-bottom");
	} else {
		$(obj).hide();
		 $(id).html("高级查询");
		 $("#btn-advance-td").removeClass("v-bottom");
	}
	$(id).click(function() {
	if($(obj).is(":hidden")){
		$(obj).show();
		$(id).html("隐藏高级查询");
		 $("#btn-advance-td").addClass("v-bottom");
		 x.cookie.set("adv-search", $(this).attr("class"));

	 }else{
		 $(obj).hide();
		 $(id).html("高级查询");
		 $("#btn-advance-td").removeClass("v-bottom");
		 x.cookie.del("adv-search");
	 }
	})
};

/*
	基本信息详情录入
*/
x.advanceBase = function(id, obj) {
	var _cookie = x.cookie.get("adv-base");
	if(_cookie != "") {
		$(obj).show();
		$(id).html("隐藏更多信息录入");
	} else {
		$(obj).hide();
		 $(id).html("更多信息录入");

	}
	$(id).click(function() {
	if($(obj).is(":hidden")){
		$(obj).show();
		$(id).html("隐藏更多信息录入");
		 x.cookie.set("adv-base", $(this).attr("class"));
	 }else{
		 $(obj).hide();
		 $(id).html("更多信息录入");
		 x.cookie.del("adv-base");
	 }
	});
};

/*
	临时添加车辆驾驶员/外包单位/中转/录入
*/
x.advanceCarDriver = function(id, obj) {
	var title =$(id).attr("relTitle");
	var _cookie = x.cookie.get("adv-CarDriver");
	if(_cookie != "") {
		$(obj).show();
		$(id).html("隐藏添加"+title);
	} else {
		$(obj).hide();
		 $(id).html("添加"+title);

	}
	$(id).click(function() {
	if($(obj).is(":hidden")){
		$(obj).show();
		$(id).html("隐藏添加"+title);
		 x.cookie.set("adv-CarDriver", $(this).attr("class"));
	 }else{
		 $(obj).hide();
		 $(id).html("添加"+title);
		 x.cookie.del("adv-CarDriver");
	 }
	});
};

/*
	Cookie操作
*/
x.cookie = {
	get: function(a) {
		var b = "";
		var c = a + "=";
		var d = document.cookie;
		if(d.length > 0) {
			g = d.indexOf(c);
			if(g != -1) {
				g += c.length;
				f = d.indexOf(";", g);
				if(f == -1) f = d.length;
				b = unescape(d.substring(g, f));
			};
		};
		return b;
	},
	set: function(a, b, t, d, e) {
		var c = "";
		var h = t || 24 * 30;
		if(h != null) {
			c = new Date((new Date()).getTime() + h * 3600000);
			c = "; expires=" + c.toGMTString();
		};
		document.cookie = a + "=" + escape(b) + c + (d ? "; path=" + d : "; path=/") + (e ? ";domain=" + e : "")
	},
	del: function(a) {
		document.cookie = a + "=;path=/;" + "expires=" + (new Date(0)).toGMTString();
	}
};

//x.tabs("#msg-tab","a","#msg-tab-main",".msg-tab-con","click");
x.tabs =function (o,otag,e,eclass,act){
  $(o+" "+otag).each(function(index){
	$(this).bind(act,function(){
		if($(this).parent().hasClass("cur")){
			return;
		}
		$(o+" "+otag).parent().removeClass("cur");
		$(this).parent().addClass("cur");
		$(e).find(eclass).eq(index).show().siblings(eclass).hide();
	});
 });
};

//检查整数
x.chkInt=function(o){
		var v = $(o).val();
		var def_v =$(o).attr("defaultVal");
		if(v=="")
			$(o).val();
		else if(isNaN(v))
			$(o).val(def_v);
		else if(Number($(o).val())<0)
			$(o).val(def_v);
		$(o).val(parseInt(Number($(o).val())));
		return parseInt(Number($(o).val()));
	};
//检查2位小数金额
x.chkMoney =function(o){
	var v=$(o).val();
	if(v==""){
		$(o).val()
	}else if(isNaN(v)){
		$(o).val("0.00")
	}else  if(Number($(o).val())<0)
		$(obj).val("0.00")
	$(o).val(Number($(o).val()).toFixed(2))
	return Number($(o).val()).toFixed(2);
};



//预加载图片
jQuery.preloadImages = function() {
	var cache = [],arg_len = arguments.length;
　　for(var i = 0; i < arg_len; i++) {
		var cacheImage = $("<img />");
		cacheImage.src = arguments[i];
		cache.push(cacheImage);
　　}
};

/*
	文本框文字focus
*/
x.focusTxt=function(obj){
	if(obj){
		var def_val=$(obj).attr("placeholder");
		$(obj).val(def_val).focus(function(){
			var val = $(this).val();
			if(val == def_val){
				$(this).val('');
			}
		}).blur(function(){
			var val = $(this).val();
			if(val == ''){
				$(this).val(def_val);
			}
		})
	}
};
/*
	@限制输入字符个数
	@obj 		对像ID
	@length 	可输入的最大个数(中文等于2个字符)
	@btn 		表单按钮
*/
x.maxInput = function(obj, length, btn) {
	var o = $(obj);
	o.after("<div class=\"chack_textarea_tips_wrap hide\"></div>");
	var _tips = $(".chack_textarea_tips_wrap");
	setInterval(function() {
		var len = $.trim(o.val()).len();
		if(len > 0) {
			if(len > length * 2) {
				_tips.html("已超出<strong style=\"position:relative;font-family:Georgia,Arial;font-size:24px;color:#DF5400;\">" + parseInt((len - length * 2) / 2) + "</strong>个字!").show();
				if(btn) $(btn).attr("disabled", "disabled");
			} else {
				_tips.html("还能输入<strong style=\"position:relative;font-family:Georgia,Arial;font-size:24px;color:#DF5400;\">" + parseInt((length * 2 - len) / 2) + "</strong>个字!").show();
				if(btn) $(btn).attr("disabled", "");
			};
		}
	}, 100)
};

//取时间范围
x.getRangTime= function (yt,date){
	if(date){
		var a_date=date.split("-");
		date=new Date(a_date[0],a_date[1]-1,a_date[2]);
	}else{
		date=new Date();
	}
	var i_milliseconds=date.getTime();
	if(yt=="y"){
		i_milliseconds-=1000*60*60*24;
	}else if(yt=="t"){
		i_milliseconds+=1000*60*60*24;
	}else if(yt=="d"){
		i_milliseconds=date.getTime();
	}else{
		i_milliseconds-=1000*60*60*24*7;
	}
	var t_date = new Date();
	t_date.setTime(i_milliseconds);
	var i_year = t_date.getFullYear();
	var i_month = ("0"+(t_date.getMonth()+1)).slice(-2);
	var i_day = ("0"+t_date.getDate()).slice(-2);
	return i_year+"-"+i_month+"-"+i_day;

};
//x.getEod("y");//获取昨天日期 即当前时间前一天
//x.getEod("y","2012-6-1");//获取2012-6-1前一天的日期
//x.getEodgetEod("t");//获取明天日期 即当前时间后一天
//x.getEod("t","2012-6-30");//获取2012-6-30后一天的日期


//删除 停用 启用
x.confirmInfo = function (id,formId,hId,hIdVal){
	$("#"+hId).attr("value",$(id).attr("id"));
	var delName = $(id).attr("rel");
	var title = $(id).attr("title");
	x.Dialog({
		title:"系统提示",
		callback:function(){
			x.Dialog.removeBox();
			$("#"+formId).submit();
		},
		button:["确定","取消"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'>确认要"+title+"<em>"+delName+"</em>此条记录吗？</p></div>",
		ofns:function (){
			$(id).parent().parent().addClass("chkTd");
		},
		cfns:function (){
			$(id).parent().parent().removeClass("chkTd");
		}
	});
};


//删除 停用 启用
x.confirmInfo = function (id,formId,hId,statusId){
	var vId =$(id).attr("id");
	var vStatu = $(id).attr("relStatu");
	$("#"+hId).attr("value",vId);
	$("#"+statusId).attr("value",vStatu);
	var delName = $(id).attr("rel");
	var title = $(id).attr("title");
	x.Dialog({
		title:"系统提示",
		callback:function(){
			$("#"+formId).submit();
		},
		button:["确定","取消"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'>确认要"+title+"<em>"+delName+"</em>此条记录吗？</p></div>",
		ofns:function (){
			$(id).parent().parent().addClass("chkTd");
		},
		cfns:function (){
			$(id).parent().parent().removeClass("chkTd");
		}
	});
};
x.alertInfo = function (id,_title){
	x.Dialog({
		title:"系统提示",
		callback:function(){
			x.Dialog.removeBox();
		},
		button:["确定"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'><em>"+_title+"</em></p></div>"
	});
};
//显示详情
x.showDetails = function(url){
	var _url=$(url).attr("relUrl");
	x.Dialog({
		title:"详情",
		width:1100,
		height:600,
		showbg:true,
		drag:false,
		content:"iframe:"+_url
	});
};
//消息
x.messageInfo = function (_title){
	x.Dialog({
		title:"系统提示",
		callback:function(){
			x.Dialog.removeBox();
		},
		button:["确定"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'><em>"+_title+"</em></p></div>"
	});
};
/*唯一性验证
x.chkOnlyName=function(o){
	var url=$(o).attr("date-url");
	var v=$.trim($(o).val());
	if (v!="")) {
		$("#chkOnlyName").empty();
		return false;
	}else{
		var _url = url + "=" + $(o).val();
		$("#chkOnlyName").html("唯一性验证中");
		$.ajax({
			type: "post",
			url: _url,//请求数据
			data: "",
			success: function(data){//请求成功 返回数据和添加数据
				$("#chkOnlyName").html("<em>" + data + "</em>");
			}
		});
	}
};
*/

x.regEx ={
	Strs:/^[\u0391-\uFFE5\w]+$/, //中文字、英文字母、数字和下划线
	NumEn:/^[_a-z0-9]+$/,//检查数字英文
	Int:/^[0-9]*[0-9][0-9]*$/,//检查非0正整数
	IntZero:/^[0-9]*[1-9][0-9]*$/,//检查非0正整数
	Int6:/^\d{6}$/,//检查6位数字
	Amount:/^(0\.\d{1,2}|[1-9]\d{0,8}(\.\d{1,2})?)$/,//检查金额数字 0.00 和 小数的后两位
	T:/^(0\.\d{1,3}|[1-9]\d{0,8}(\.\d{1,3})?)$/,//检查金额数字 0.000 和 小数的后三位
	Tel:/^1(3|4|5|8)\d{9}$/,//检查手机号码
	Mobile:/^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,//检查固定电话 //0471-1234567
	PostCode:/^[1-9]\d{5}(?!\d)$/,//检查中国邮政编码
	Email:/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/,//检查电子邮件
	ChineWord:/^[\u4e00-\u9fa5]+$/,//检查汉字
	SimpCardId:/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$/,//简单检查身份证方法
	ChineseEnNum:/^[\u4e00-\u9fa5a-zA-Z0-9]+$/,//检查中文和英文 数字
	Time:/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/ //检查时间格式YYYY-MM-DD
};

// 添加验证方法 (复杂密码)
jQuery.validator.addMethod("isPassword", function(value, element) {
	return this.optional(element) || x.chkUserPwd(value);
}, "密码为6-16位数字、字母或符号的组合");
// 添加验证方法 (验证码)
jQuery.validator.addMethod("isImgCode", function(value, element) {
	return this.optional(element) || x.chkImgCode(value);
}, "请输入6数字验证码");
// 添加验证方法 (服务协议)
jQuery.validator.addMethod("isUserImgCode", function(value, element) {
	return this.optional(element) || x.chkServie(value);
}, "请勾选服务协议");

// 添加验证方法 (金额数字)
jQuery.validator.addMethod("isAmount", function(value, element) {
	return this.optional(element) || x.isAmount(value);
}, "金额格式错误");

// 添加验证方法 (金额数字)
jQuery.validator.addMethod("isT", function(value, element) {
	return this.optional(element) || x.isT(value);
}, "吨数格式错误");

// 添加验证方法 (整数数字)
jQuery.validator.addMethod("isIntNum", function(value, element) {
	return this.optional(element) || x.isIntNum(value);
}, "格式不正确");


// 添加验证方法 (手机号码)
jQuery.validator.addMethod("isTel", function(value, element) {
	return this.optional(element) || x.isTel(value);
}, "手机号格式错误");

// 添加验证方法 (邮箱地址)
jQuery.validator.addMethod("isEmail", function(value, element) {
	return this.optional(element) || x.isEmail(value);
}, "邮箱地址错误");

// 添加验证方法 (固定电话)
jQuery.validator.addMethod("isMobile", function(value, element) {
	return this.optional(element) || x.isMobile(value);
}, "电话号码格式错误");

// 添加验证方法 (手机号/固定电话)
jQuery.validator.addMethod("isTelMobile", function(value, element) {
	return this.optional(element) || x.isMobile(value);
}, "号码格式错误");


// 添加验证方法 (邮政编码)
jQuery.validator.addMethod("isPostCode", function(value, element) {
	return this.optional(element) || x.isPostCode(value);
}, "邮政编码错误");

// 添加验证方法 (身份证号)
jQuery.validator.addMethod("isCardId", function(value, element) {
	return this.optional(element) || x.isSimpCardId(value);
}, "身份证号错误!");

// 添加验证方法 (数量总数)
jQuery.validator.addMethod("isIntZero", function(value, element) {
	return this.optional(element) || x.isIntZero(value);
}, "格式错误");

// 添加验证方法 (数量总数)
jQuery.validator.addMethod("isInt", function(value, element) {
	return this.optional(element) || x.isInt(value);
}, "格式错误");


// 添加验证方法 (真实姓名)
jQuery.validator.addMethod("isChinaName", function(value, element) {
	return this.optional(element) || x.isChineWord(value);
}, "请输入中文姓名");

// 添加验证方法 (公司名称)
jQuery.validator.addMethod("isCompanyName", function(value, element) {
	return this.optional(element) || x.isChineseEnNum(value);
}, "请输入正确公司名称");

// 添加验证方法 (银行卡号)
jQuery.validator.addMethod("isCardNumber", function(value, element) {
	return this.optional(element) || x.isIntZero(value);
}, "请输入正确银行卡号");

// 添加验证方法 (银行卡开户行)
jQuery.validator.addMethod("isCardCompany", function(value, element) {
	return this.optional(element) || x.isStrs(value);
}, "请输入正的银行开户行");

// 添加验证方法 (备注)
jQuery.validator.addMethod("isRemark", function(value, element) {
	return this.optional(element) || x.isStrs(value);
}, "请勿输入特殊字符");
// 添加验证方法 (邮箱和手机号)
jQuery.validator.addMethod("isRadioCheck", function(value, element) {
	return this.optional(element) || x.chkChkBoxRadio(value);
}, "请选择一项");
// 不等于
jQuery.validator.addMethod("notEqualToVal", function(value, element, param) {
	return value != param;
}, "不能输入相同的值");

//车牌号
jQuery.validator.addMethod("isCarNum", function(value, element) {
  return this.optional(element) || /^[\u0391-\uFFE5\d]+$/.test(value);
}, "请输入正确车牌号");

//检查字符
x.isStrs=function(s){
	if(x.regEx.Strs.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查数字英文
x.isNumEn =function(s){
	if(x.regEx.NumEn.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查非0正整数
x.isIntZero=function(s){
	if(x.regEx.IntZero.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查0正整数
x.isIntZero=function(s){
	if(x.regEx.Int.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查纯数字
x.isInt=function(s){
	if(x.regEx.Int.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查纯数字
x.isIntNum=function(s){
	if(x.regEx.IntZero.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查6位数字
x.isInt6=function(s){
	if(x.regEx.Int6.test(s)){
		return true;
	}else{
		return false;
	}
};

//检查金额数字 0.00 和 小数的后两位
x.isAmount=function (s){
	if(x.regEx.Amount.test(s)&&s!=0.00){
		return true;
	}else{
		return false;
	}
};

//检查金额数字 0.00 和 小数的后三位
x.isT=function (s){
	if(x.regEx.T.test(s)&&s!=0.000){
		return true;
	}else{
		return false;
	}
};

//检查手机号码
x.isTel=function (s){
	if(x.regEx.Tel.test(s)){
		return true;
	}else{
		return false;
	}
};

//检查固定电话 //0471-1234567
x.isMobile=function(s){
   if(x.regEx.Mobile.test(s)){
	   return true;
   }else{
	  return false;
	}
};
//检查中国邮政编码
x.isPostCode=function (s){
   if(x.regEx.PostCode.test(s)){
	   return true;
   }else{
	  return false;
	}
};
//检查电子邮件
x.isEmail=function (s){
	if(x.regEx.Email.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查密码 6-16位英文字母、数字或者符号至少2种的组合的登录密码
x.isPwd=function(s){
	var reg=/^[\a-\z\A-\Z0-9\`\-\=\,\.\/\;\'\[\]\\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:\"\<\>\?\\\@\.]+$/;
	var onlyabc=/^[A-Za-z]+$/;
	var onlynum=/^[0-9]+$/;
	var onlyspc =/^[\`\-\=\,\.\/\;\'\[\]\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:"\<\>\?\@]+$/;
	if(onlynum.test(s)){
		return false;
	}
	if(onlyabc.test(s)){
		return false;
	}
	if(onlyspc.test(s)){
		return false;
	}
	if(s.length<6||s.length>16){
		return false;
	}else if(reg.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查汉字
x.isChineWord=function(s){
	if(x.regEx.ChineWord.test(s)){
		return true;
	}else{
		return false;
	}
};
//检查身份证
x.isCardId=function(s){
	var s=x.personCard(s);
	if(s=="OK"||s=="ok"){
		return true;
	}else {
		return false;
	}
};
//简单检查身份证方法
x.isSimpCardId=function(s){
	if(x.regEx.SimpCardId.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查中文和英文
x.isChineseEnNum=function(s){
	if(x.regEx.ChineseEnNum.test(s)){
		return true;
	}else{
		return false;
	}
};

//检查时间格式YYYY-MM-DD
x.isDateTime=function(s){
	if(x.regEx.Time.test(s)){
		return true;
	}else{
		return false;
	}
};

//检查联系电话手机号/固话
x.isTelMobile=function (v){
	if(!x.isMobile(v)&&!x.isTel(v)){
		return false
	}else{
		return true;
	}
};
(function($){
		$.fn.formSubmit = function(options){
			var defaults = {
				loadHtml:"正在保存数据",
				loading:"正在加载数据，请稍等",
				formError:"#formError",
				form:"",
				callback : "",
				callbefore :"",
				btnType:"normal"
			};
			var opts = $.extend(defaults, options);
			$(opts.form+" :text,"+opts.form+" :password").focus(function(){
				var v = $(this).val();
				if (v == '' && !$(this).is('.form-error')){
				}
				$(this).parent().parent().addClass("form-focus");
			}).blur(function(){
				$(this).valid();
			});
			$(opts.form+" select").change(function(){
				$(this).valid();
			});
			$(opts.form+" .x-calendar").select(function(){
				$(this).valid();
			});
			this.bind("click", function(){
				jQuery.validator.messages.required = "";
				$(opts.form).validate({
					onfocusout:false,
					invalidHandler: function(e, validator) {
						var errors = validator.numberOfInvalids();
							if (errors) {
							var message = errors == 1
							? '您有1项需要填写'
							: '您有' + errors + '项需要填写';
							$(opts.formError+" em").html(message);
							$(+opts.formError).show();
						} else {
							$(opts.formError).hide();
						}
					}
				});
				if(opts.callbefore != "" && $.isFunction(opts.callbefore)) {

					opts.callbefore(this);
				};
				if ($(opts.form).valid()){
					if(opts.callback != "" && $.isFunction(opts.callback)) {
						opts.callback(this);
						return false;
					};
					if(opts.btnType != "" && opts.btnType == "alert") {
						setTimeout(function(){
							x.alert(opts.loading,function(){
								$(opts.form).submit();
							});
						},0);
					}else if(opts.btnType != "" && opts.btnType == "all"){
						$(this).hide();
						$("<span class='loadFormSubmit'><span><em>"+opts.loadHtml+"</em></span></span>").insertAfter(this);
						setTimeout(function(){
							x.alert(opts.loading,function(){
								$(opts.form).submit();
							});
						},0);
					}else{
						$(this).hide();
						$("<span class='loadFormSubmit'><span><em>"+opts.loadHtml+"</em></span></span>").insertAfter(this);
						setTimeout(function(){
							$(opts.form).submit();
						},0);
					}
				}

			});
		};

})(jQuery);



(function($){
	$.fn.formAjxaSubmit = function(options){
		var defaults = {
			ajxaFn:"",
			formError:"#formError",
			form:"",
			callback : "",
			callbefore :""
		};
		var opts = $.extend(defaults, options);
		$(opts.form+" :text,"+opts.form+" :password").focus(function(){
			var v = $(this).val();
			if (v == '' && !$(this).is('.form-error')){
			}
			$(this).parent().parent().addClass("form-focus");
		}).blur(function(){
			$(this).valid();
		});

		$(opts.form+" select").change(function(){
				$(this).valid();
		});
		this.bind("click", function(){
			jQuery.validator.messages.required = "";
			$(opts.form).validate({
				onfocusout:false,
				invalidHandler: function(e, validator) {
					var errors = validator.numberOfInvalids();
						if (errors) {
						var message = errors == 1
						? '您有1项需要填写'
						: '您有' + errors + '项需要填写';
						$(opts.formError+" em").html(message);
						$(+opts.formError).show();
					} else {
						$(opts.formError).hide();
					}
				}
			});
			if(opts.callbefore != "" && $.isFunction(opts.callbefore)) {
				opts.callbefore(this);
			};
			if ($(opts.form).valid()){
				if(opts.callback != "" && $.isFunction(opts.callback)) {
						opts.callback(this);
					};
				if(opts.ajxaFn != "" && $.isFunction(opts.ajxaFn)) {
					opts.ajxaFn(this);
				};
			}

		});
	};

})(jQuery);
(function(a){a.fn.tooltip=function(b){var c={cssClass:"",delay:0,duration:500,xOffset:15,yOffset:15,opacity:0,fadeDuration:400};var b=a.extend(c,b);return this.each(function(d){var e=a(this);$tooltip=a("#divTooltip");if($tooltip.length==0){$tooltip=a('<div id="divTooltip"></div>');a("body").append($tooltip);$tooltip.hide()}e.mouseover(function(g){g=g?g:window.event;clearTimeout($tooltip.data("hideTimeoutId"));$tooltip.removeClass($tooltip.attr("class"));$tooltip.css("width","");$tooltip.css("height","");$tooltip.addClass(b.cssClass);$tooltip.css("opacity",1-b.opacity/100);$tooltip.css("position","absolute");$tooltip.data("title",e.attr("title"));e.attr("title","");$tooltip.data("alt",e.attr("alt"));e.attr("alt","");$tooltip.html($tooltip.data("title"));var f=e.attr("href");if(f!=undefined&&f!="#"){$tooltip.html(a.ajax({url:e.attr("href"),async:false}).responseText)}winw=a(window).width();w=$tooltip.width();xOffset=b.xOffset;if(w+xOffset+50<winw-g.clientX){$tooltip.css("left",a(document).scrollLeft()+g.clientX+xOffset)}else{if(w+xOffset+50<g.clientX){$tooltip.css("left",a(document).scrollLeft()+g.clientX-(w+xOffset))}else{if(g.clientX>winw/2){$tooltip.width(g.clientX-50);$tooltip.css("left",a(document).scrollLeft()+25)}else{$tooltip.width((winw-g.clientX)-50);$tooltip.css("left",a(document).scrollLeft()+g.clientX+xOffset)}}}winh=a(window).height();h=$tooltip.height();yOffset=b.yOffset;if(h+yOffset+50<g.clientY){$tooltip.css("top",a(document).scrollTop()+g.clientY-(h+yOffset))}else{if(h+yOffset+50<winh-g.clientY){$tooltip.css("top",a(document).scrollTop()+g.clientY+yOffset)}else{$tooltip.css("top",a(document).scrollTop()+10)}}$tooltip.data("showTimeoutId",setTimeout("$tooltip.fadeIn("+b.fadeDuration+")",b.delay))});e.mouseout(function(f){e.attr("title",$tooltip.data("title"));e.attr("alt",$tooltip.data("alt"));clearTimeout($tooltip.data("showTimeoutId"));$tooltip.data("hideTimeoutId",setTimeout("$tooltip.fadeOut("+b.fadeDuration+")",b.duration))});e.click(function(f){f.preventDefault()})})}})(jQuery);