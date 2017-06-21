<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保险业反欺诈平台</title>
<link href="${ctx}/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/DwrPushAction.js"></script>
<link href="${ctx}/widgets/jquery-easyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
<script language="javascript">
	
	function createIframe(url){
		 $("#logoutForm").attr("src",url);
	}

	$(function(){
		var cognosUrl = "${COGNOS_URL}";				//记录当前cognos访问的地址信息
		var logouturl = "${COGNOS_URL}?b_action=xts.run&m=portal/logoff.xts&h_CAM_action=logoff";
		$("#logout").on("click",function(){
			$.post(contextRootPath+"/logout.do",{},function(){
				var defaultUrl = contextRootPath+"/j_spring_security_logout";
				if(cognosUrl==null||cognosUrl==''){
					//本地还未搭建Cognos环境
					setTimeout("location.href='"+defaultUrl+"'",100);
					return;
				}else {
					var logouturl = "${COGNOS_URL}?b_action=xts.run&m=portal/logoff.xts&h_CAM_action=logoff";
					if (window.addEventListener){
						try{
							window.addEventListener("load", createIframe(logouturl));
							setTimeout("location.href='"+defaultUrl+"'",100);
							return;
						}catch(e){
							setTimeout("location.href='"+defaultUrl+"'",100);
							return;
						}
					} else if (window.attachEvent){
						try{
						    var iframe1 = document.getElementById('logoutForm');
					        iframe1.src = logouturl;
					        setTimeout("location.href='"+defaultUrl+"'",20);
							return;
						}catch(e){
							setTimeout("location.href='"+defaultUrl+"'",20);
							return;
						}
					} else {
						try{
							window.onload = createIframe(logouturl);
							location.href = defaultUrl;
							return;
						}catch(e){
							location.href = defaultUrl;
							return;
						}
					}
				}
			});
		});
	});
	
</script>
</head>
<body style="overflow:-Scroll;overflow-x:hidden">
	<!--TopBar:begin-->
	<div id="topNavWrapper">
		<div id="topNav">
			<div id="navbar" class="navbar">
				<div class="logo_con fl">
					<%-- <a href="#" class="fl"> <img src="${ctx}/pages/images/logo.png"
						width="141" height="20" alt="深圳人保财险" /> </a> --%>
					<h3 class="fl yh">${serverName}</h3>
				</div>
				<div class="login fr">
					<ul>
						<li><i class="spr folk"> </i> 欢迎您:${user.userName}</li>
						<!--<li id="mail">  <a href="#" onclick="viewAllMessage()"><i  class="spr mail"></i></a>
						</li> -->
						<li><a id="logout" href="#" style="color:#fff;">注销</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!--TopBar:end-->
	<!--content:begin-->
	
	<div class="content">
		<!--left_nav:begin-->
		<div class="left_nav fl" id="left_head">
			<ul id="years">

			</ul>
			<div class="bottom_height"></div>
		</div>
		<!--left_nav:end-->
		<!--right_detail:begin-->
		<div id="rightDetail" class="right_detail fl">

		<script type="text/javascript">
			function showMessage(msg){
				if(msg!=''){
					$("div.panel.window").remove();
					var jsonStr = eval('(' + msg + ')');
					$.messager.show({
						title:'新消息',
						msg: "<a href='#' onclick='viewMessage(\""+jsonStr.url+"\")' >"+jsonStr.messageContent+"</a>",
						showType:'slide',
						timeout:0
					});
					
					$("#mail").remove();
					getCount();
					$("#mail").append("("+count+")");
				};
			}
			
			function viewMessage(actionURL){
				var url=contextRootPath+actionURL;
				editRecord(url);
			}
			
			function viewAllMessage(){
				var url=contextRootPath+"/wf/wftusermessage/getMessage.do";
				editRecord(url);
			}
			
			function setHight(){
				//var h=$("#mainwindow").contents().height();
				var iframeH=$(window.frames["mainwindow"].document).height();
				//alert(iframeH);
				$("#mainwindow").height(iframeH< 500 ? 500 : 1024);
				
			}
		</script>

			<iframe name="mainwindow" id="mainwindow"
				src="${ctx}/portal/display.do?serverCode=${serverCode}&serverName=${serverName}"  width="100%" height="100%"
				scrolling="yes" marginwidth="0" marginheight="0" frameborder="0"></iframe>
			<div class="clear"></div>
	 	<div class="footer cluster">
		    <ul>
			  <li><a href="#"> 平台简介 </a>&nbsp;|&nbsp;</li>
			  <li><a href="#"> 联系我们 </a>&nbsp;|&nbsp;</li>
			  <li><a href="#"> 友情链接 </a>&nbsp;|&nbsp;</li>
			  <li><a href="#"> 产品答疑 </a></li>
		    </ul>
		  </div>
		</div>
		
		<!--right_detail:end-->
	</div>
	<!--content:end-->
	<script type="text/javascript" src="${ctx}/widgets/left_head.js"></script>
	<script language="javascript" type="text/javascript">
		
	var count;
	//window.onload = init();   //加载页面事件
	function init(){
		dwr.engine.setActiveReverseAjax(true); // 激活反转 
		dwr.engine.setNotifyServerOnPageUnload(true); //在刷新或关闭页面时销毁对应的ScriptSession对象
	}
	function testCall(messages){
	   //alert(messages);
	     $.messager.show({
			title:'新短消息',
			msg: messages,
			timeout:10000,
			showType:'slide'
		});
		var au = document.createElement("audio");
        au.preload="auto";
        au.src = "${ctx}/voice/4130.mp3";
        au.play();
	}
	
	function getCount(){
		var url=contextRootPath + "/wf/wftusermessage/getMessageCount.do";
		$.ajax({
			type:"POST",
			url:url,
			async:false,
			success: function(result) {
	           count=result;
	        }
			}
		);
	};
	
	function subYears(){ 
		var liAll=$("#years li a");
		for(var i=0;i<liAll.length;i++){
			var liContent=liAll[i].innerText;
			var tempContent;
			if(liContent.length>8){
				tempContent=liContent.substring(0,8);
				tempContent=tempContent+"...";
				liAll[i].innerText=tempContent;
				liAll[i].title=liContent;
			}
			
		}
		} 
	
    	//当浏览器窗口大小改变时，设置显示内容的高度  
    	window.onresize=function(){
    		document.getElementById("topNavWrapper").style.width= document.body.clientWidth-20+'px';
    		var rightDetailWidth = document.body.clientWidth - 170;
			document.getElementById("rightDetail").style.width= rightDetailWidth+'px';
    	} 
	
		$(function() {
					
			init();
			getCount();
			$("#years").delegate("a","click",function(event){
				event.stoppropagation();
				$(this).addClass("active").parent().sbilings().children().removeClass("active");
			});
			
			$("#mail").append("("+count+")");
			/********加载菜单预处理START********/
			//菜单头
			var menuHead = "<li><a href=\""+contextRootPath+"/mainPage.do?serverCode=QuickPay&serverName=快处快赔系统\" class=\"active\">首页</a></li>";
			//清空菜单栏
			$("#years").empty();
			//加入菜单头
			$("#years").append(menuHead); 
			//渲染菜单栏样式
			$("#left_head").left_head();
			/********加载菜单预处理END********/

			/********加载营销系统菜单START********/
			var menu = "${menu}";
			var menuArray = eval('(' + menu + ')');
			var html = creatHtml(menuArray,1);
			$("#years").append(html); 
			$("#left_head").left_head();
			/********加载营销系统菜单END********/
			
			document.getElementById("topNavWrapper").style.width= document.body.clientWidth-20+'px';
			var rightDetailWidth = document.body.clientWidth - 170;
			document.getElementById("rightDetail").style.width= rightDetailWidth+'px';
			
			var requestHost = window.location.host;
			  
			/********异步加载Cognos菜单START*********/
			//Cognos登录错误提示信息
				window.onload = function(){
					var cognos_err_msg = "${cognos_err_msg}";
					if(cognos_err_msg=='success'){
						$.ajax({
							type:'POST',
							url:contextRootPath +'/um/cognos/findMenuByUser.do',
							data:{
								host:requestHost
							},
							success:function(msg){
								if(msg){
									var cognosMenuArray = eval('(' + msg + ')');
									var html = creatHtml(cognosMenuArray,2);
									$("#years").append(html);
									$("#left_head").left_head();
									subYears(); 
								}
							}
						});
					}
				};
			
			/********异步加载Cognos菜单END********/
			
			
			
			/********抽象出分级画菜单的函数START********/
			function creatHtml(menuArray,type) {
				var html = "";
				for ( var i = 0; i < menuArray.length; i++) {
					
					if(null==menuArray[i]){
						continue;
					}

					if(menuArray[i].children.length > 0) {
						html += "<li><a href=\"#\">" + menuArray[i].text + "</a>";
						html += expand(menuArray[i],type);
					}
					else {
						html += "<li><a href=\""+contextRootPath + menuArray[i].attributes.url +"\" target=\"mainwindow\" >" + menuArray[i].text + "</a>";
						html +="</li>";
					}
					
				}
				return html;
			}

			function expand(menuArray,type) {
				var html = "";
				if (menuArray.children.length > 0) {
					html = "<ul>";
					for ( var j = 0; j < menuArray.children.length; j++) {
						var url=menuArray.children[j].attributes.url;
						
						if(url!=null&&url.indexOf("http:")==-1&&url.indexOf("https:")==-1&&url.indexOf(contextRootPath)==-1){
							url = contextRootPath + url;
						}
						
						var target = "";
						
						if(type==1)
							target="target=\"mainwindow\"";
						else
							target="target=\"_blank\"";
						if(menuArray.children[j].attributes.url==null)
							{
								url="#";
								target="";
							}
						html += "<li><a href=\""+url+"\" "+target+">"
								+ menuArray.children[j].text + "</a>";
								if(menuArray.children[j].children.length>0)
								html +=expand(menuArray.children[j], type);
								else{html +="</li>";}
					}
					html += "</ul></li>";
				}
				return html;
			}
			/********抽象出分级画菜单的函数END********/
			
			$("#mainwindow").load(function(){
				var iframeH=$(window.frames["mainwindow"].document).height();
				//var mainheight = $(this).contents().height();
				
				$(this).height(iframeH < 500 ? 500 : 1024);
				setInterval("setHight()",15000);
				$("html, body").scrollTop(0);
				});
			
			
			    
		});
		
		
	</script>
	<iframe id="logoutForm" name="logoutForm" src="" style="display: none;"></iframe>
</body>
</html>
