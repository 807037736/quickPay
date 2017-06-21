<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<style type="text/css">

ol,ul{list-style:none;margin:0;padding:0;}
blockquote,q{quotes:none;}
blockquote:before,blockquote:after,q:before,q:after{content:'';content:none;}
table{border-collapse:collapse;border-spacing:0;}
/* start editing from here */
a{text-decoration:none;}
.txt-rt{text-align:right;}/* text align right */
.txt-lt{text-align:left;}/* text align left */
.txt-center{text-align:center;}/* text align center */
.float-rt{float:right;}/* float right */
.float-lt{float:left;}/* float left */
.clear{clear:both;}/* clear float */
.pos-relative{position:relative;}/* Position Relative */
.pos-absolute{position:absolute;}/* Position Absolute */
.vertical-base{	vertical-align:baseline;}/* vertical align baseline */
.vertical-top{	vertical-align:top;}/* vertical align top */
.underline{	padding-bottom:5px;	border-bottom: 1px solid #eee; margin:0 0 20px 0;}/* Add 5px bottom padding and a underline */
nav.vertical ul li{	display:block;}/* vertical menu */
nav.horizontal ul li{	display: inline-block;}/* horizontal menu */
img{max-width:100%;}
/*end reset*/
@font-face {
    font-family: 'ambleregular';
    src:url(../font/Amble-Regular-webfont.ttf) format('truetype');
}
body {
	font-family: Arial, Helvetica, sans-serif;
	background: #FFF;
}
.wrap {
	width:80%;
	margin: 0 auto;
	transition:all .2s linear;
	-moz-transition:all .2s linear;/* firefox */
	-webkit-transition:all .2s linear; /* safari and chrome */
	-o-transition:all .2s linear; /* opera */
	-ms-transition:all .2s linear;
}
.wrap h1{
	font-size:2.5em;
	color:#fff00;
}
.header {
	background: #FFF;
}
.headertop_desc{
	padding:20px 0;
	background:#222;
	border-bottom:1px solid #EEE;
}

.header_top {
	padding:20px 0;
}
.logo {
	float: left;
}
.header_top_right{
	float:right;
	padding:18px 0;
}

   /**** End Cart ****/
.header_slide{
	margin-top:10px;
}
.header_bottom_left{
	float:left;
	width:20%;
}
.categories{
	border:1px solid #EEE;
}


.categoriesul
{	
	text-align:left;
	font-size:2.1em;
	color:#fff;
	height:50px;
	padding:10px;
	background:#000000;
	text-transform:uppercase;
	font-family: 'ambleregular';	
}

.spana{
	float:right;
	font-size:0.6em;
	color:#ffff00;
	padding:6px;
}

.categories li{
	background: url(../images/strip-bg.gif) repeat-x #F6F6F6;
}
.categories li a{
	display:block;
	font-size:1.5em;
	padding:9px 15px;
    color: #000;
    font-family: 'ambleregular';
    margin:0 20px;
    background:url(../images/drop_arrow.png) no-repeat 0;
    border-bottom: 1px solid #F1F1F1;
    text-transform:uppercase;	
}
.categories li:last-child a{
	border:none;
}
.categories li a:hover{
	color:#FC7D01;
}
.header_bottom_right{
	float:left;
	width:60%;
	padding-left:1%;
}

.button_pg {
	height:40px;
	width:90px;
	background-color:#4682b4;
	padding:10px;
	position:relative;
	font-family: 'Open Sans', sans-serif;
	font-size:15px;
	text-decoration:none;
	color:#fff;
	border: solid 1px #831212;
	background-image: linear-gradient(bottom, rgb(171,27,27) 0%, rgb(212,51,51) 100%);
	border-radius: 5px;
}


.button_pg:active {
	padding-bottom:9px;
	padding-left:10px;
	padding-right:10px;
	padding-top:11px;
	top:1px;
	background-image: linear-gradient(bottom, rgb(171,27,27) 100%, rgb(212,51,51) 0%);
}

.button_pg:before {
	background-color:#ffffff;
	content:"";
	display:block;
	position:absolute;
	width:100%;
	height:100%;
	padding:8px;
	left:-8px;
	top:-8px;
	z-index:-1;

}



/* 图片预览滚动 */
 


</style>


</head>
<body>
	<div class="header">
		 <div class="headertop_desc">
			
	  	</div>
  	   <div class="wrap">
				<div class="header_top"><h1>该功能只兼容于IE8以上浏览器 请自行下载 按照操作说明文档使用</h1>
  		</div>     
				
			<div class="header_bottom_left">				
				<div class="categories">
				   <ul>
				  	   <!--  <h3>我的列表<a href="javascript:location.reload();" class="arefresh">刷新列表</a></h3>-->
				  	   <div class="categoriesul">我的列表
					  	   <span class="arefresh">
					  	   		<a href="#" class ="spana">刷新列表</a>
					  	   </span>
					  </div>
				  	   	
					      <li><a href="javascript:StartMedia();">湘潭花石镇快处快赔点</a></li>
					      <li><a href="javascript:StartMedia();">湘潭花石镇快处快赔点2</a></li>
					      <li><a href="javascript:StartMedia();">湘潭花石镇快处快赔点3</a></li>
					   <!--    <li><a href="#">长沙雨花区</a></li> 
					      <li><a href="#">长沙天心区</a></li>
					      <li><a href="#">岳阳城东</a></li>
					      <li><a href="#">岳阳城南</a></li> -->
					      
				  	 </ul>
				  	 
				</div>					
		  	 </div>
			    <div class="header_bottom_right">		111111			 
			 	    <!------ Slider ------------>
					  <div class="slider">22222222
				      	<div class="slider-wrapper theme-default">33333
				            <div id="slider" class="nivoSlider">44444
				                <object classid="CLSID:FCDF3219-6C45-4A6F-A69E-7A1785B7602F" id="SDK2ocx" width=640 height=480 CODEBASE="./HYCsdk2.exe"></object>	   
	             				    <br><br>
	             				    
	             				  
									<input type="hidden" class='input_w w_30 easyui-validatebox' readonly  name="qpTICAccidentSurveyGroupId" id="qpTICAccidentSurveyGroupId" value="${qpTICAccident.surveyGroupId}" >
									
	             				   <!--   <a href="#" class='button_pg'>刷新列表</a>-->
	             				   <!--  < <input type="button" class="button_pg" value="test" onClick="Login()"/>-->
	             				    &nbsp&nbsp&nbsp
	             				    <input type="button" class="button_pg" value="拍照" onClick="LocalSnapshot()"/>
	             				    <input type='button' class='button_pg' value='查看照片' onClick="openFileIIs()"/>
	             				    <input type="button" class="button_pg" value="上传照片" onclick="uploadPictures()" /> 
	             				    &nbsp&nbsp&nbsp&nbsp&nbsp
	             				     <input type='button' class='button_pg' value='断开' onClick="StopMedia()";>
	             				    &nbsp&nbsp&nbsp&nbsp&nbsp
	             				    <input type="button" class="button_pg" value="音量+" onClick="ControlVolume1()"/>
	             				    <input type="button" class="button_pg" value="音量-" onClick="ControlVolume2()"/>
	             				     
	             				    <!--<a href='javascript:Logout();' class='button_pg'>&nbsp&nbsp断开&nbsp&nbsp</a>-->
									<input type="hidden" id="cid" name="cid" value="${cid }" >
									<input type="hidden" id="qpAcciId" name="qpAcciId" value="${qpAcciId }" >
									<input type="hidden" id="qpTICAccidentAcciId" name="qpTICAccidentAcciId" value="${qpTICAccident.acciId}" />
		
				            </div>
				            <br>
							<textarea name="Messagebox" id="Messagebox" cols="120" rows="10" ></textarea>
				        </div>
				   
			   		</div>
			<!------End Slider ------------>
         </div>
      
   			</div>
<script language="JavaScript" for="SDK2ocx" event="Event_Login(status,info)" type="text/javascript">
Event_LoginCB(status,info);
</script>
<script language="JavaScript" for="SDK2ocx" event="Event_MediaStatus(status,info)" type="text/javascript">
Event_MediaStatusCB(status,info);
</script>
<script language="JavaScript" for="SDK2ocx" event="Event_RecvMsg(type, MsgHead,MsgBody)" type="text/javascript">
Event_RecvMsgCB(type,MsgHead,MsgBody);
</script>

<script type="text/javascript">
 var WshShell=new ActiveXObject("WScript.Shell");
 //添加可信站点或IP
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range100\\","");
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range100\\http","2","REG_DWORD");
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range100\\:Range","*.bxfanqizha.com");
 //修改IE ActiveX 安全设置
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\0\\1201","0","REG_DWORD");
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\1\\1201","0","REG_DWORD");
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1201","0","REG_DWORD");
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1201","0","REG_DWORD");

 //禁用弹出窗口阻止程序
 WshShell.RegWrite("HKCU\\Software\\Microsoft\\Internet Explorer\\New Windows\\PopupMgr","no");
//-->
</script>

<script type="text/javascript">
function Login()
{

    var player = document.getElementById("SDK2ocx");
    info = player.Login("120.26.224.36",3081,"xthspc@hyc","12345678");
	
}
window.onload = Login;

function Login1()
{		
    var player = document.getElementById("SDK2ocx");
    info = player.Login("120.26.224.36",3081,"xthspc@hyc","12345678");
    //var player1 = document.getElementById("SDK2ocx");
    lseq= player.StartMedia("xths@hyc");

}

function Logout()
{
    var player = document.getElementById("SDK2ocx");
    player.Logout();
}

function GetDeviceList()
{
    var player = document.getElementById("SDK2ocx");
	var info = player.GetDeviceList();
	if(info == "")
	{
		document.getElementById("Messagebox").value = "GetDeviceList() failed!!!";
	}
	else
	{
		document.getElementById("Messagebox").value = info;
	}
}

function QueryStatus()
{
    var player = document.getElementById("SDK2ocx");
	var info = player.QueryStatus("xths@hyc");
	if(info == "")
	{
		document.getElementById("Messagebox").value = "QueryStatus() failed!!!";
	}
	else
	{
		document.getElementById("Messagebox").value = info;
	}
}

function SubscribeStatus()
{
    var player = document.getElementById("SDK2ocx");
    var info = player.SubscribeStatus("[xths@hyc]");
	if(info != 0)
	{
		document.getElementById("Messagebox").value = "SubscribeStatus() failed!!!";
	}
	else
	{
		document.getElementById("Messagebox").value = "SubscribeStatus() success!!!";
	}
}

var lseq = 0;
function StartMedia()
{	
		
	var player = document.getElementById("SDK2ocx");
	lseq= player.StartMedia("xths@hyc");
	if(lseq == 0)
		{
			document.getElementById("Messagebox").value = "StartMedia() failed!!!";
		}
		else
		{	
			document.getElementById("Messagebox").value = info;
			alert("远程视屏连接已发送，请等待。。。");
			//info = player.GetDeviceList();
		}
  	
}
<%-- 断开 --%>
function StopMedia()
{
	
    var player = document.getElementById("SDK2ocx");
    lseq= player.StopMedia(lseq);
    
}

function SendSipMsg()
{
    var player = document.getElementById("SDK2ocx");
    var reponse= player.SendSipMsg("MESSAGE","cu41@hyc","<?xml version=\"1.0\"?>\r\n<hello>helloworld</hello>","text/xml;charset=utf-8");
	if(reponse == "")
	{
		document.getElementById("Messagebox").value = "SendSipMsg() failed!!!";
	}
	else
	{
		document.getElementById("Messagebox").value = reponse;
	}
}

function ControlVolume1()
{			
	
    var player = document.getElementById("SDK2ocx");
    lseq= player.ControlVolume(8);
}

function ControlVolume2()
{
    var player = document.getElementById("SDK2ocx");
    lseq= player.ControlVolume(-8);
}

function Event_LoginCB(status,info)
{
//	alert(info);
}

function Event_MediaStatusCB(status,info)
{
//	alert(info);
}

function Event_RecvMsgCB(type,MsgHead,MsgBody)
{
	//alert("Event_RecvMsgCB");
//	document.getElementById("Messagebox").value = MsgHead + MsgBody;
}

<%-- 拍照 --%>
function LocalSnapshot()
{
	var date = new Date();

    var seperator1 = "-";

    var month = date.getMonth() + 1;

    var strDate = date.getDate();
    
    var strHour = date.getHours();
    
    var strMinute = date.getMinutes();
    
    var strSecond = date.getSeconds();
 
    var strMSecond = date.getMilliseconds()

    if (month >= 1 && month <= 9) {

        month = "0" + month;

    }

    if (strDate >= 0 && strDate <= 9) {

        strDate = "0" + strDate;

    }
    
    if (strHour >= 0 && strHour <= 9) {

        strHour = "0" + strHour;

    }
    
    if (strMinute >= 0 && strMinute <= 9) {

        strMinute = "0" + strMinute;

    }
    
    if (strSecond >= 0 && strSecond <= 9) {

        strSecond = "0" + strSecond;

    }

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + strHour + seperator1 + strMinute + seperator1 + strSecond + seperator1 + strMSecond;
    var player = document.getElementById("SDK2ocx");
    var cid =  document.getElementById("cid").value; 
    var qpAcciId =  document.getElementById("qpAcciId").value;
  	var path = "D:\\PIC\\"+cid +"\\"+ qpAcciId +"\\" + currentdate +".jpg";
    var ret = player.LocalSnapshot(lseq,path);
    	
	if(ret != "")
	{
		document.getElementById("Messagebox").value = "照片存为：D:\\PIC\\"+cid +"\\"+ qpAcciId;
		alert("拍照成功");
		
	}else{
		document.getElementById("Messagebox").value = "LocalSnapshot() failed!!!";
	}
}
<%-- 原存储方法 --%>
function SetStoragePath()
{
    var player = document.getElementById("SDK2ocx");
    var cid =  document.getElementById("cid").value; 
    var qpAcciId =  document.getElementById("qpAcciId").value;
  	var path = "D:\\PIC\\"+cid +"\\"+ qpAcciId;
    var ret =  player.SetStoragePath(path);
//	var info = player.LocalSnapshot(lseq);
	if(info == "")
	{
		document.getElementById("Messagebox").value = "LocalSnapshot() failed!!!";
	}
	else
	{
		document.getElementById("Messagebox").value = info;
		
	}
	
}

<%-- 查看图片 --%>	
function openFileIIs(){          
       try{   
       	var cid =  document.getElementById("cid").value; 
   	    var qpAcciId =  document.getElementById("qpAcciId").value;
  		var path = "D:\\PIC\\"+cid +"\\"+ qpAcciId;
  	//	alert(path);
        var obj=new ActiveXObject("wscript.shell");  
           if(obj){   
          	    obj.Run(path, 1, false );
              // obj.Run("\""+filename+"\"", 1, false );  
               //obj.run("osk");/*打开屏幕键盘*/  
               //obj.Run('"'+filename+'"');   
               obj=null;   
           }   
       }catch(e){   
           alert("请确定此报案号下是否存在已拍照片！");   
       }   
         
   }  
   
<%-- 上传图片 --%>
	function uploadPictures(){
		var url="${ctx}/qp/qpticpicture/prepareUploadPic.do?acciId=" + $("#qpTICAccidentAcciId").val() + "&SurveyGroupId=" + $("#qpTICAccidentSurveyGroupId").val();
		window.open(url,"_blank",'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
    }    
  
	</script>
	
</body>
</html>


