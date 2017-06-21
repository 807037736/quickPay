<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
	<%@ include file="/common/meta_css.jsp"%>
	<%@ include file="/common/i18njs.jsp"%>
	<%@ include file="/common/meta_js.jsp"%>
    <title></title>
    <object classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WebBrowser" width="0"></object>
    <style type="text/css">
    body,div,table,tr,th,td,form,input,ul,li,dl,dt,dd,p,h1,h2,h3,h4,h5,h6{margin:0;padding:0;}
        input{border-bottom-width:0px; border-left-width:0px; border-right-width:0px; border-top-width:0px; color:Blue}        
        .divSet{float:left}
	    body{font-size: 13px;}
	    #divDataValue span{color:Blue}
	    #divDataValue2 span{color:Blue}
	    .divPrintBackCss{visibility:visible;}
	     .brr{ display:block; width:80px;height:35px; vertical-align:bottom; }
	     .withprint{}
    </style>
    <style media="print">
        .Noprint{display:none;}
        .PageNext{page-break-after: always;}
    </style>



<script type="text/javascript">

function printpr()   //预览函数
{
	document.all.WebBrowser.ExecWB(7,1);  //预览 
}

function printTure()   //打印函数
{         
	onbeforeprint();
	hideprintbtn();
    window.print();
    onafterprint();
    
}

function printTure2()   //打印函数
{         
	hideprintbtn();
	window.print();
	onafterprint();
}

function doPage()
{
    layLoading.style.display = "none";//同上
}

function init(){
	var date = new Date();
	document.getElementById("txtdiYear").value = "${txtdiYear}";
	document.getElementById("txtdiMonth").value = "${txtdiMonth}";
	document.getElementById("txtdiDay").value = "${txtdiDay}";
    
    document.getElementById("txtTopYear").value = "${caseYear}";
    document.getElementById("txtTopMonth").value = "${caseMonth}";
    document.getElementById("txtTopDay").value = "${caseDay}";
    document.getElementById("txtTopHour").value = "${caseHH}";
    document.getElementById("txtTopSecond").value = "${casemm}";
}

</script>


<script type="text/javascript">
    
	function PrintCase(){
    	if('111111' =="0"){
        	alert("您还未处理完毕,请处理完毕之后再打印");
    	}
    	else{ 
        	document.all.WebBrowser.ExecWB(6,2);
        	if($("#page2").length>0)
        	{     
           		change();
           		document.all.WebBrowser.ExecWB(6,2);  
       		}
    	}            
	}

	function PrintCase2(){
  		document.all.WebBrowser.ExecWB(7,1);  //预览 
	}

    $(document).ready(function(){
       /*
    
    	if($("#page2").length>0){    //表示有第二页,显示下一面按钮
            $("#nextpage").show();
        } 
        var isprint = getQueryString("isprint");   
        if(isprint != null && isprint=="1"){ //直接打印   
              $("#page1").show();
              $("#pgae2").hide();
              document.all.WebBrowser.ExecWB(6,2);//直接打印
              if($("#page2").length>0){     
                   change();
                   document.all.WebBrowser.ExecWB(6,2);  
              }
              window.close(); 
          }
          else{
             	if('111111' =="0"){
                	alert("您还未处理完毕,请处理完毕之后再打印");
            	}
          }
    	   */
    	 var HKEY_Root,HKEY_Path,HKEY_Key;
    	 HKEY_Root="HKEY_CURRENT_USER";

    	 HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

    	var head,foot,top,bottom,left,right;

    	var Wsh=new ActiveXObject("WScript.Shell");

    	HKEY_Key="header";

    	//设置页眉（为空） 根据你自己要设置的填入 
    	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
    	HKEY_Key="footer";
    	//设置页脚（为空） 根据你自己要设置的填入 
    	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
  //  alert(111);

    });

    
	//window.onbeforeprint(){      
   function onbeforeprint(){    
    	$(".divPrintBackCss").css({"visibility":"hidden"});  
    	$("input").css({"visibility":"visible"});  
    	//$("#Label75").css({"visibility":"visible"});  
    	
	}
   
   function hideprintbtn(){
	   $("#printbtn").css({"visibility":"hidden"});  
   	   $("#printbtn2").css({"visibility":"hidden"});  
   }

	//window.onafterprint(){
		function onafterprint(){
 		$(".divPrintBackCss").css({"visibility":"visible"});
 		$("#printbtn").css({"visibility":"visible"}); 
 		$("#printbtn2").css({"visibility":"visible"}); 
	}
    
    function change()
    {
       if( page1.style.display=="none")
       {
        	page1.style.display="";
        	page2.style.display="none";
       }
       else   
       {
           page1.style.display="none";
           page2.style.display="";
       }
    }
    
	function showDiv()
	{
		$("#divMainContener").show();
		$("#divMainContener2").show();
	}
	
    </script>

</head>
<body onload="init();">

<form id="form1" runat="server">
<div  id="page1" class="PageNext" style=" z-index:100; background-color:Black;">
<!--<input type="button" value="pPPPPPPPPPPPP"  onclick="  showDiv()"/>-->
<div style="position: absolute; left: 10px; top: 8px; width: 160px;height: 125px;">
	<img src="/printTemplate/qrcode_e6ctong.jpg" alt="" width="85%" />
</div>
<div style="position: absolute; left: 26px; top: 14px;" id="divMainContener" class="divPrintBackCss" >
    <table border="0" cellpadding="0" cellspacing="0" style="width: 690px; height: 660px;">
        <tr>
            <td align="center" style="font-size: 24px; letter-spacing: 3px;">
               <span id="Back2" class="HideContrl"> 长沙市公安局交通警察支队</span>
                
                <input type="text" value="122处警" id="txtPoliceTeam" style="width: 120px;height:30px; font-family:@楷体; text-align: center" />
                
                <asp:Label ID="Back3" runat="server" Text="Label" CssClass="HideContrl">交通警察大队</asp:Label><br />
                道路交通事故认定书（简易程序）
            </td>
            <td rowspan="4">
            </td>
        </tr>
        <tr>
            <td style="height: 10px;">
            </td>
        </tr>
        <tr align="right" style="line-height: 27px;">
            <td>
                <div style="margin-right: 254px; font-size: 12px;">
                     <span id="Back4" class="HideContrl">长公交 (</span><input
                        value="${qpTICAcciPrintVo.caseNoA}" id="txtchanggonjiao" style="width: 60px; border-bottom-width: 0px; border-left-width: 0px;
                        border-right-width: 0px; border-top-width: 0px" /><span id="Back5" class="HideContrl">) 认字 </span>[<input value="${qpTICAcciPrintVo.caseNoB}" id="txtAcciId"
                                style="width: 80px; border-bottom-width: 0px; border-left-width: 0px; border-right-width: 0px;
                                border-top-width: 0px" />]
                </div>
                <div id="divSet1" class="divSet" style="margin-top: 4px;margin-left: 150px">
                    <input id="printbtn" type="button" class="noprint" value="打 印" onclick="printTure();" style="background-color: Gray;
                        color: Black; width: 52px; height: 23px; cursor: pointer" />&nbsp;&nbsp; 
                        
					<input id="printbtn2" type="button" class="noprint" value="全 部 打 印" onclick="printTure2();" style="background-color: Gray;
                        color: Black; width: 72px; height: 23px; cursor: pointer" /> 
                    <input id="nextpage" type="button" value="下一页" onclick="change();" style="background-color: Gray;
                        color: Black; width: 52px; height: 23px; cursor: pointer; display:none;" />
                        
                </div>
                <span ID="Back6" runat="server" Text="Label" CssClass="HideContrl" Style="font-size: 16px;
                    letter-spacing: 3px;"> 民警姓名： </span><input value="${qpTTPCase.tpUserName}" type="text" id="txtPoliceName"
                        style="width: 90px; text-align: left" /><span ID="Back7" runat="server" Text="Label"
                            CssClass="HideContrl" Style="font-size: 16px; letter-spacing: 3px;">警号： </span><input
                                value="${qpTTPCase.tpEmployeeId}" type="text" id="txtPoliceEmployeeId" style="width: 100px; text-align: left" />
            </td>
        </tr>
        <tr style="height: 96px;">
            <td>
                <table id="tbMain" cellpadding="0" cellspacing="0" border="1" style="margin-top: 8px !important;
                    margin-top: 20px; width: 960px; height: 538px; font-size: 12px; letter-spacing: 1px;
                    line-height: 18px;">
                    <tr>
                        <td align="center" style="width: 65px;">
                            <asp:Label ID="Back83" runat="server" Text="Label" CssClass="HideContrl">时间</asp:Label></td>
                        <td colspan="7" style="width: 800px; height: 25px;" valign="middle">
                            <table cellpadding="0" cellspacing="0" style="border-color: Black; width: 100%; border-bottom-width: 0px;
                                border-left-width: 0px; border-right-width: 0px; border-top-width: 0px; border-bottom: 0px;
                                border-left: 0px; border-right: 0px; border-top: 0px;" border="0">
                                <tr>
                                    <td style="width: 34%">
                                        <input value="" id="txtTopYear" style="text-align: right; width: 40px;"  /><span ID="Back84" runat="server" Text="Label" CssClass="HideContrl">年</span>
                                        <input value="" id="txtTopMonth" style="width: 28px;" /><span ID="Back89" runat="server" Text="Label" CssClass="HideContrl">月</span>
                                        <input value="" id="txtTopDay" style="width: 28px;" /><span ID="Back85" runat="server" Text="Label" CssClass="HideContrl">日</span>
                                        <input value="" style="width: 26px;" id="txtTopHour" /><span ID="Back86" runat="server" Text="Label" CssClass="HideContrl">时</span>
                                        <input value="" style="width: 26px;" id="txtTopSecond" /><span ID="Back90" runat="server" Text="Label" CssClass="HideContrl">分</span>
                                    </td>
                                    <td style="width: 5%" align="center">
                                        <asp:Label ID="Back87" runat="server" Text="Label" CssClass="HideContrl"> 地点</asp:Label>
                                    </td>
                                    <td style="width: 50%">
                                    	<input value="${qpTTPCase.caseAddress}" id="txtStreet" style="text-align: center; width: 400px;" />
                                    </td>
                                    <td style="width: 5%" align="center">
                                        <asp:Label ID="Back88" runat="server" Text="Label" CssClass="HideContrl"> 天气</asp:Label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td style="width: 100px;">
										<input id="txtWeather" style="width: 50px; text-align: right; vertical-align: bottom;"
                                				value="${qpTTPCase.caseWeatherDesc}" />
                         </td>
                    </tr>
                    <tr align="center" style="width: 100%; height: 32px;">
                        <td style="width: 65px;">
                            <asp:Label ID="Back8" runat="server" Text="Label" CssClass="HideContrl">代码</asp:Label></td>
                        <td style="width: 80px;">
                            <asp:Label ID="Back9" runat="server" Text="Label" CssClass="HideContrl">姓名</asp:Label></td>
                        <td style="width: 40px;">
                            <asp:Label ID="Back10" runat="server" Text="Label" CssClass="HideContrl">性别</asp:Label></td>
                        <td style="width: 135px;">
                            <asp:Label ID="Back11" runat="server" Text="Label" CssClass="HideContrl">驾驶证或身份证号码</asp:Label></td>
                        <td style="width: 189px;">
                            <asp:Label ID="Back12" runat="server" Text="Label" CssClass="HideContrl">现住址</asp:Label></td>
                        <td style="width: 80px;">
                            <asp:Label ID="Back13" runat="server" Text="Label" CssClass="HideContrl">电话</asp:Label></td>
                        <td style="width: 200px;">
                            <asp:Label ID="Back14" runat="server" Text="Label" CssClass="HideContrl">保险凭证</asp:Label></td>
                        <td style="width: 90px;">
                            <asp:Label ID="Back15" runat="server" Text="Label" CssClass="HideContrl">交通方式</asp:Label></td>
                        <td style="width: 100px;">
                            <asp:Label ID="Back16" runat="server" Text="Label" CssClass="HideContrl">车辆牌号</asp:Label></td>
                    </tr>


					<c:forEach var="qpTICAccident" items="${qpTICAccidentList}" varStatus="i">
									<tr align="center" style="width: 100%;">
										<td align="center" >
											<asp:Label ID="Back17" runat="server" Text="Label" CssClass="HideContrl" Style="height: 30px;">
												<c:if test="${0==i.index}">甲</c:if><c:if test="${1==i.index}">乙</c:if><c:if test="${2==i.index}">丙</c:if>
											</asp:Label>
										</td>
										<td><input value="${qpTICAccident.driverName }" type="text" id="" style="width: 60px; text-align: left" /></td>
										<td>
											<input value="${qpTICAccident.driverSexDesc }" type="text" id="" style="width: 30px; text-align: left" />
										</td>
										<td><input value="${qpTICAccident.driverIDNumber }" type="text" id="" style="width: 135px; text-align: left" /></td>
										<td><input value="${qpTICAccident.driverAddress }" type="text" id="" style="width: 189px; text-align: left" /></td>
										<td><input value="${qpTICAccident.driverMobile }" type="text" id="" style="width: 80px; text-align: left" /></td>
										<td><input value="${qpTICAccident.coName }" type="text" id="" style="width: 100px; text-align: left;" /></td>
										<td align="left">
										<input value="${qpTICAccident.driverVehicleTypeDesc}" type="text" id="" style="width: 90px; text-align: left" /></td>
										<td><input value="${qpTICAccident.driverVehicleNumber }" type="text" id="" style="width: 100px; text-align: left" /></td>
									</tr>
					 </c:forEach>

                    <tr style="height: 70px;" align="left">
                        <td colspan="9" style="letter-spacing: 3px; line-height: 22px; position: relative;top: 6px">
                            <asp:Label ID="Back20" runat="server" Text="Label" CssClass="HideContrl">事故事实: 甲沿</asp:Label>
                            <input type="text" value="${qpTICAcciPrintVo.directionRoadA}" id="txt0Str" style="vertical-align: bottom; width: 200px;
                                text-align: left; background-color: transparent; position: absolute; z-index: 9"
                                runat="server" />
                                 &nbsp;
                            <asp:Label ID="Back21" runat="server" Text="Label" CssClass="HideContrl" Style="margin-left: 45px;">由</asp:Label><input
                                value="${qpTICAcciPrintVo.directionFromA}" type="text" id="txt0Dir" style="vertical-align: bottom; width: 23px;
                                text-align: center" runat="server" /><asp:Label ID="Back22" runat="server" Text="Label"
                                    CssClass="HideContrl">向</asp:Label><input value="${qpTICAcciPrintVo.directionToA}" type="text" id="txt0Dir2" style="vertical-align: bottom;
                                        width: 34px; text-align: center" runat="server" /><asp:Label ID="Back23" runat="server"
                                            Text="Label" CssClass="HideContrl">行驶/横穿道路 乙沿</asp:Label>
                            <input value="${qpTICAcciPrintVo.directionRoadB}" type="text" id="txt1Str" style="width: 200px; text-align: left; background-color: transparent;
                                position: absolute" runat="server" />
                                 &nbsp;
                            <asp:Label ID="Back24" runat="server" Text="Label" CssClass="HideContrl" Style="margin-left: 35px;">由</asp:Label><input
                                value="${qpTICAcciPrintVo.directionFromB}" type="text" id="txt1Dir" style="vertical-align: bottom; width: 22px;
                                text-align: center" runat="server" /><asp:Label ID="Back25" runat="server" Text="Label"
                                    CssClass="HideContrl">向</asp:Label><input value="${qpTICAcciPrintVo.directionToB}" type="text" id="txt1Dir2" style="vertical-align: bottom;
                                        width: 32px; text-align: center" runat="server" /><asp:Label ID="Back26" runat="server"
                                            Text="Label" CssClass="HideContrl"> 行驶/横穿道路丙沿 </asp:Label>
                            <input value="${qpTICAcciPrintVo.directionRoadC}" type="text" id="txt2Str" style="vertical-align: bottom; width: 200px;
                                text-align: left; background-color: transparent; position: absolute; vertical-align: bottom;"
                                runat="server" />
                                 &nbsp;
                            <asp:Label ID="Back27" runat="server" Text="Label" CssClass="HideContrl" Style="margin-left: 35px;">由</asp:Label><input
                                value="${qpTICAcciPrintVo.directionFromC}" type="text" id="txt2Dir" style="vertical-align: bottom; width: 16px;
                                text-align: center" runat="server" /><asp:Label ID="Back28" runat="server" Text="Label"
                                    CssClass="HideContrl">向</asp:Label><input value="${qpTICAcciPrintVo.directionToC}" type="text" id="txt2Dir2" style="vertical-align: bottom;
                                        width: 34px; text-align: center" runat="server" /><asp:Label ID="Back29" runat="server"
                                            Text="Label" CssClass="HideContrl">行驶/横穿道路;上述</asp:Label>
                            <br />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="Back82" style=" z-index:103;left: 80px;position: absolute; top: 25px">地点，发生</span><input name="txtCaseDetails" value="${qpTICAcciPrintVo.caseNotes}" type="text" id="txtCaseDetails" style="width: 600px; text-align: left;
                                    vertical-align: bottom;left:180px;position:absolute;" /><br />
                            <asp:Label ID="Back30" runat="server" Text="Label" CssClass="HideContrl">
                            		当事人签字：甲：_______乙：_______丙：______
                            		<%-- <c:forEach var="qpTICAccident" items="${qpTICAccidentList}">
                            			${qpTICAccident.acciId }：_______________
                            		</c:forEach> --%>
                            		</asp:Label>受伤人自认为伤情轻微签字：
                            		<input type="text" style="text-align: center; width: 20px" value="甲：" />
                            		<input type="text" style="text-align: center; width: 150px" value="乙：" />
                            		<input type="text" style="text-align: center; width: 40px" value="丙：" />
                        </td>
                    </tr>
                    <tr align="left">
                        <td colspan="9" style="letter-spacing: 1px; font-size: 11px;">
                            <asp:Label ID="Back1" runat="server" Text="Label" CssClass="HideContrl">当事人责任：
                            <c:forEach var="qpAcciLawDataVo" items="${qpAcciLawDataVoList}" varStatus="i">
                            <c:if test="${1==i.index}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                            <c:if test="${2==i.index}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                            
                            <c:if test="${0==i.index}">甲</c:if><c:if test="${1==i.index}">乙</c:if><c:if test="${2==i.index}">丙</c:if> 违反了《中华人民共和国道路交通安全法》第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawItemA}" type="text" id="txt0OffendLaw1" style="width: 32px; text-align: right" runat="server" />条  第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSegmentA}" type="text" id="txt0OffendLaw2" style="width: 38px; text-align: right" runat="server" />
                            <asp:Label ID="Back32" runat="server" Text="Label" CssClass="HideContrl">款</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSectionA}" type="text" id="txt0OffendLaw3" style="width: 32px; text-align: right" runat="server" />
                            <asp:Label ID="Back33" runat="server" Text="Label" CssClass="HideContrl">项，《中华人民共和国道路交通安全实施条例》第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawItemB}" type="text" id="txt0OffendLaw11" style="width: 30px; text-align: right" runat="server" />
                            <asp:Label ID="Back34" runat="server" Text="Label" CssClass="HideContrl">条第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSegmentB}" type="text" id="txt0OffendLaw22" style="width: 24px; text-align: right" runat="server" />
                            <asp:Label ID="Back35" runat="server" Text="Label" CssClass="HideContrl">款</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSectionB}" type="text" id="txt0OffendLaw33" style="width: 26px; text-align: right" runat="server" />项 ,<br />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="Back37" runat="server" Text="Label" CssClass="HideContrl">《湖南省实施<中华人民共和国道路交通安全>办法》 第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawItemC}" type="text" id="txt0OffendLaw111" style="width: 36px; text-align: right" runat="server" />
                            <asp:Label ID="Back38" runat="server" Text="Label" CssClass="HideContrl">条第</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSegmentC}" type="text" id="txt0OffendLaw222" style="width: 32px; text-align: right" runat="server" />
                            <asp:Label ID="Back39" runat="server" Text="Label" CssClass="HideContrl">款</asp:Label>
                            <input value="${qpAcciLawDataVo.lawSectionC}" type="text" id="txt0OffendLaw333" style="width: 40px; text-align: right" runat="server" />
                            <asp:Label ID="Back40" runat="server" Text="Label" CssClass="HideContrl">项的规定。</asp:Label>&nbsp;&nbsp;
                            <asp:Label ID="Back41" runat="server" Text="Label" CssClass="HideContrl"><c:if test="${0==i.index}">甲</c:if><c:if test="${1==i.index}">乙</c:if><c:if test="${2==i.index}">丙</c:if> 承担</asp:Label>
                            <input value="${qpAcciLawDataVo.driverLiability}" type="text" id="txt0Duty" style="width: 80px; text-align: right" runat="server" />
                            <asp:Label ID="Back42" runat="server" Text="Label" CssClass="HideContrl">责任</asp:Label>
                           
                            <br />
                           
                            <asp:Label ID="Back43" runat="server" Text="Label" CssClass="HideContrl">
                            </c:forEach>
                        </td>
                    </tr>
                    <tr align="left">
                        <td colspan="9" style="height: 100px;">
                            <span id="Back69" class="HideContrl">调解结果：</span><div
                                style="font-family: @宋体; : 20px; margin-top: 0px; height: 90px; color: Blue;
                                float" id="divResult">
                                <!-- 
                                <span id="Label75"  style="display:inline-block;height:22px;width:880px;z-index:103;left:105px;position:absolute;top:480px;word-wrap:break-word;word-break:break-all;"></span>
                                -->
                                <br/>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input value = "${qpTTPCase.caseResult}" type="text"/>
                            </div>
                            &nbsp;
                            <span id="Back70" class="HideContrl">当事人签字： 甲：_______________乙：______________丙：_______________</span>
                        </td>
                    </tr>
                    <tr align="left" style="line-height: 27px;">
                        <td>
                            &nbsp;
                            <asp:Label ID="Back71" runat="server" Text="Label" CssClass="HideContrl">告知</asp:Label></td>
                       <td colspan="8" style="font-size: 11px; letter-spacing: 0px; height: 143px;font-size: 11px;line-height: 18px;">
                            &nbsp;
                            <asp:Label ID="Back72" runat="server" Text="Label" CssClass="HideContrl">一、本道路交通事故认定书只是用简易程序处理交通事故。</asp:Label><br />
                            &nbsp;
                            <asp:Label ID="Back73" runat="server" Text="Label" CssClass="HideContrl">二、当事人不请求交通警察当场调解的，可持本道路交通事故认定书到保险公司索赔或向人民法院提起民事诉讼。</asp:Label><br />
                            &nbsp;
                            <asp:Label ID="Back74" runat="server" Text="Label" CssClass="HideContrl">三、有下列情形之一或者调解未达成及协议调解生成后当事人不履行的，当事人可以向人名法院提起民事诉讼；</asp:Label><br />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="Back75" runat="server" Text="Label" CssClass="HideContrl">1、当事人提供不出交通事故证据，因现场变动、证据灭失，交通警察无法查证交通事故事实的；</asp:Label><br />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="Back76" runat="server" Text="Label" CssClass="HideContrl">2、当事人对道路交通事故认定有异议的；</asp:Label><br />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="Back77" runat="server" Text="Label" CssClass="HideContrl">3、当事人拒绝在道路交通事故认定书上签名的；</asp:Label><br />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="Back78" runat="server" Text="Label" CssClass="HideContrl">4、当事人不同意由交通警察调解的。</asp:Label>
                            <div style="margin-left: 700px; margin-top: -20px;">
                            	<input type="text" value="" style="text-align: right; width: 35px" id="txtdiYear" />
                            		<asp:Label ID="Back79" runat="server" Text="Label" CssClass="HideContrl">年</asp:Label>
                            		&nbsp;
                            	<input type="text" style="text-align: center; width: 35px" value="" id="txtdiMonth" />
                            		<asp:Label ID="Back80" runat="server" Text="Label" CssClass="HideContrl">月</asp:Label>
                            		&nbsp;
                            	<input type="text" style="text-align: center; width: 35px" id="txtdiDay" value="" />
                            		<asp:Label ID="Back81" runat="server" Text="Label" CssClass="HideContrl">日</asp:Label>
                            </div>
                            <input type="text" id="CurrTime" style="visibility: hidden" value="" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<div id="divDataValue">
    <asp:Label ID="Label1" runat="server" Style="z-index: 102; left: 574px; position: absolute;
        top: 12px; letter-spacing:3px; font-family:楷体;" Text="122处警" Font-Size="26px" ></asp:Label>
    <asp:Label ID="Label2" runat="server" Style="z-index: 102; left: 585px; position: absolute;
        top: 84px" Text=""></asp:Label>
    <asp:Label ID="Label3" runat="server" Style="z-index: 102; left: 691px; position: absolute;
        top: 84px" Text=""></asp:Label>
    <asp:Label ID="Label4" runat="server" Style="z-index: 102; left: 158px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label5" runat="server" Style="z-index: 102; left: 219px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label6" runat="server" Style="z-index: 102; left: 261px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label7" runat="server" Style="z-index: 102; left: 305px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label8" runat="server" Style="z-index: 102; left: 345px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label9" runat="server" Style="z-index: 102; left: 440px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label10" runat="server" Style="z-index: 102; left: 950px; position: absolute;
        top: 145px" Text=""></asp:Label>
    <asp:Label ID="Label11" runat="server" Style="z-index: 102; left: 130px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label12" runat="server" Style="z-index: 102; left: 220px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label13" runat="server" Style="z-index: 102; left: 245px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label14" runat="server" Style="z-index: 102; left: 380px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label15" runat="server" Style="z-index: 102; left: 581px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label16" runat="server" Style="z-index: 102; left: 665px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label17" runat="server" Style="z-index: 102; left: 858px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label18" runat="server" Style="z-index: 102; left: 941px; position: absolute;
        top: 204px" Text=""></asp:Label>
    <asp:Label ID="Label19" runat="server" Style="z-index: 102; left: 130px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label20" runat="server" Style="z-index: 102; left: 220px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label21" runat="server" Style="z-index: 102; left: 245px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label22" runat="server" Style="z-index: 102; left: 380px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label23" runat="server" Style="z-index: 102; left: 581px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label24" runat="server" Style="z-index: 102; left: 665px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label25" runat="server" Style="z-index: 102; left: 858px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label26" runat="server" Style="z-index: 102; left: 941px; position: absolute;
        top: 226px" Text=""></asp:Label>
    <asp:Label ID="Label27" runat="server" Style="z-index: 102; left: 130px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label28" runat="server" Style="z-index: 102; left: 220px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label29" runat="server" Style="z-index: 102; left: 245px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label30" runat="server" Style="z-index: 102; left: 380px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label31" runat="server" Style="z-index: 102; left: 581px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label32" runat="server" Style="z-index: 102; left: 665px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label33" runat="server" Style="z-index: 102; left: 858px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <asp:Label ID="Label34" runat="server" Style="z-index: 102; left: 941px; position: absolute;
        top: 248px" Text=""></asp:Label>
    <table style="z-index: 102; left: 175px; position: absolute; top: 257px; vertical-align: bottom;
        width: 50px" class="brr">
        <tr>
            <td valign="bottom" style="color: Blue">
                <asp:Literal ID="Label35" runat="server"></asp:Literal>
                <%--<asp:Label ID="Label35" runat="server" Height="100px" Text="" Width="50px"></asp:Label>--%>
            </td>
        </tr>
    </table>
    <asp:Label ID="Label36" runat="server" Style="z-index: 102; left: 241px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <asp:Label ID="Label37" runat="server" Style="z-index: 102; left: 282px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <table style="z-index: 102; left: 446px; position: absolute; top: 257px; vertical-align: bottom;
        width: 50px" class="brr">
        <tr>
            <td valign="bottom" style="color: Blue">
                <asp:Literal ID="Label38" runat="server"></asp:Literal>
                <%--  <asp:Label ID="Label38" runat="server" Style="z-index: 102; left: 446px; position: absolute;
                    top: 275px" Text=""></asp:Label>--%>
            </td>
        </tr>
    </table>
    <asp:Label ID="Label39" runat="server" Style="z-index: 102; left: 512px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <asp:Label ID="Label40" runat="server" Style="z-index: 102; left: 554px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <table style="z-index: 102; left: 715px; position: absolute; top: 257px; vertical-align: bottom;
        width: 50px" class="brr">
        <tr>
            <td valign="bottom" style="color: Blue">
                <asp:Literal ID="Label41" runat="server"></asp:Literal>
                <%--                        <asp:Label ID="Label41" runat="server" Style="z-index: 102; left: 715px; position: absolute;
                    top: 275px" Text=""></asp:Label>--%>
            </td>
        </tr>
    </table>
    <asp:Label ID="Label42" runat="server" Style="z-index: 102; left: 780px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <asp:Label ID="Label43" runat="server" Style="z-index: 102; left: 827px; position: absolute;
        top: 275px" Text=""></asp:Label>
    <asp:Label ID="Label44" runat="server" Height="22px" Style="z-index: 103; left: 220px;
        position: absolute; top: 296px" Text="" Width="840px"></asp:Label>
    <asp:Label ID="Label45" runat="server" Style="z-index: 102; left: 425px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label46" runat="server" Style="z-index: 102; left: 492px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label47" runat="server" Style="z-index: 102; left: 545px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label48" runat="server" Style="z-index: 102; left: 854px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label49" runat="server" Style="z-index: 102; left: 915px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label50" runat="server" Style="z-index: 102; left: 962px; position: absolute;
        top: 344px" Text=""></asp:Label>
    <asp:Label ID="Label51" runat="server" Style="z-index: 102; left: 488px; position: absolute;
        top: 362px" Text=""></asp:Label>
    <asp:Label ID="Label52" runat="server" Style="z-index: 102; left: 547px; position: absolute;
        top: 362px" Text=""></asp:Label>
    <asp:Label ID="Label53" runat="server" Style="z-index: 102; left: 600px; position: absolute;
        top: 362px" Text=""></asp:Label>
    <asp:Label ID="Label54" runat="server" Style="z-index: 102; left: 758px; position: absolute;
        top: 362px; text-align: center" Width="75px" Text=""></asp:Label>
    <asp:Label ID="Label55" runat="server" Style="z-index: 102; left: 424px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label56" runat="server" Style="z-index: 102; left: 490px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label57" runat="server" Style="z-index: 102; left: 543px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label58" runat="server" Style="z-index: 102; left: 854px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label59" runat="server" Style="z-index: 102; left: 915px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label60" runat="server" Style="z-index: 102; left: 962px; position: absolute;
        top: 380px" Text=""></asp:Label>
    <asp:Label ID="Label61" runat="server" Style="z-index: 102; left: 488px; position: absolute;
        top: 398px" Text=""></asp:Label>
    <asp:Label ID="Label62" runat="server" Style="z-index: 102; left: 547px; position: absolute;
        top: 398px" Text=""></asp:Label>
    <asp:Label ID="Label63" runat="server" Style="z-index: 102; left: 600px; position: absolute;
        top: 398px" Text=""></asp:Label>
    <asp:Label ID="Label64" runat="server" Style="z-index: 102; left: 758px; position: absolute;
        top: 398px; text-align: center" Width="75px" Text=""></asp:Label>
    <asp:Label ID="Label65" runat="server" Style="z-index: 102; left: 415px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label66" runat="server" Style="z-index: 102; left: 481px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label67" runat="server" Style="z-index: 102; left: 534px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label68" runat="server" Style="z-index: 102; left: 852px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label69" runat="server" Style="z-index: 102; left: 915px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label70" runat="server" Style="z-index: 102; left: 962px; position: absolute;
        top: 417px" Text=""></asp:Label>
    <asp:Label ID="Label71" runat="server" Style="z-index: 102; left: 488px; position: absolute;
        top: 435px" Text=""></asp:Label>
    <asp:Label ID="Label72" runat="server" Style="z-index: 102; left: 547px; position: absolute;
        top: 435px" Text=""></asp:Label>
    <asp:Label ID="Label73" runat="server" Style="z-index: 102; left: 600px; position: absolute;
        top: 435px" Text=""></asp:Label>
    <asp:Label ID="Label74" runat="server" Style="z-index: 102; left: 758px; position: absolute;
        top: 435px; text-align: center" Width="75px" Text=""></asp:Label>

    <asp:Label ID="Label76" runat="server" Style="z-index: 100; left: 850px; position: absolute;
        top: 688px" Text=""></asp:Label>
    <asp:Label ID="Label77" runat="server" Style="z-index: 100; left: 914px; position: absolute;
        top: 688px" Text=""></asp:Label>
    <asp:Label ID="Label78" runat="server" Style="z-index: 100; left: 960px; position: absolute;
        top: 688px" Text=""></asp:Label>
    <asp:Label ID="Label79" runat="server" Style="z-index: 102; left: 620px; position: absolute;
        top: 315px;" Text="A:"></asp:Label>
    <asp:Label ID="Label80" runat="server" Style="z-index: 102; left: 750px; position: absolute;
        top: 315px;" Text="B:"></asp:Label>
    <asp:Label ID="Label81" runat="server" Style="z-index: 102; left: 880px; position: absolute;
        top: 315px;" Text="C:"></asp:Label>
</div>
</div>

</form>
</body>
</html>
