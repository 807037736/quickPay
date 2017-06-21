<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>交通事故远程定责事故认定书下载</title>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"  href="${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/common.css">
	<link rel="stylesheet"  href="${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/business.css">
	<script type="text/javascript" src="${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/jquery-1.11.2.min.js"></script>
	
	<style>
	.logdiv{height:607px;width:1000px;background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/denglu.png) no-repeat; background-size:100%;position: absolute;top:-42px;left:5px;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="denglu.png", sizingMethod='scale');}
	.dldiv{background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/divdl.png) no-repeat; width:489px;height:359px;margin:0px auto;padding-top:20px;}
	.title{width:100%;text-align: center;margin-top:11px;color:#fff;height:38px;line-height:38px;}
	.title span{height:38px;display:inline-block;line-height:38px; } 
	.sp1{font-size:20px;font-weight: bold;}
	
	/* 登录样式 */
	.login_ul{width:315px;height:auto;margin:25px auto 0 auto;}
	.login_ul li{float:left;width:100%;margin-bottom:15px;position: relative;}
	.login_ul li .zh{background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/zh.png) no-repeat left;color:#5a5a5a;padding-left:25px}
	.login_ul li input{width:295px;height:30px;line-height:30px;padding:0 10px;}
	.login_ul li .yzm{background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/yzm.png) no-repeat left;color:#5a5a5a;padding-left:25px;}
	.login_ul li #btnsubmit{background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/btndl.png) no-repeat;color:#fff;font-size:18px;width:140px;height:39px;border:1px solid #f1f1f1;cursor: pointer;}
	.login_ul li #msg{color:#f00;font-size:12px;position: absolute;top:-10px;left:0;background:url(${ctx}/pages/qp/qpweixincase/ResponsibilityConfirmation/msgs.png) no-repeat left;padding-left:20px;}
	
	.writing{width:500px;height:auto;margin:0 auto;padding-left:15px;}
	.writing .p1{color:#0d8be4;font-size:14px;padding-bottom:5px;}
	.writing .p2{font-size:12px;}
	
	#errormsg{display:none;}
	</style>


  </head>
  
  <body>
   <div class="businessbody">
   	<div class="header">
   		
   	</div>
   	<div class="content">

   		<div class="logdiv">
   		<div style="height:20px"></div>
   			<div class="dldiv">
	   			<div>
	   			<p class="title"><span class="sp1">交通事故远程定责事故认定书下载</span> </p>
		   			<form id="myform" name="form" action="${ctx}/weixin/rc/ResponsibilityConfirmationDown.do" method="post" >
			   			<ul class="login_ul">
				   			<li><span><font color="red">*</font>事故编号:</span></li>
				   			<li><input type="text" id="caseId" name="caseId"  value="${caseId }" placeholder="请输入事故编号" tabindex="1"/></li>
				   			<li><span><font color="red">*</font>车牌编号:</span></li>
							<li><input type="text" id="driverVehicleNumber" value="${driverVehicleNumber }" name="driverVehicleNumber" placeholder="请输入车牌编号" tabindex="2" /></li>
							<c:if test="${empty errorMsg}">
								<li><em id="msg" style="display: none">${errorMsg }</em></li>
							</c:if>
				   			<c:if test="${not empty errorMsg }">
								<li><em id="msg" >${errorMsg }</em></li>
							</c:if>
				   			<li style="text-align: center;margin-top:10px;">
				   				<input type="button" value="下 载" tabindex="3" id="btnsubmit" tabindex="3" />
				   			</li>
			   			</ul>
		   			</form>
	   			</div>
   			</div>
   			<div class="writing">
   			<p class="p1">温馨提示：</p>
   			<p class="p2">目前该查询只支持微信远程定损案件查询。</p>
   			<p class="p2">下载的文件为pdf格式，需自行下载安装pdf相关软件。例：<a target="_blank" href="https://www.baidu.com/link?url=x8Nj-fOPOb2eOcMFIOMloSgzv5nQ-1qLTgXP4sXv9GP_xkaSu488-RqDML1oMi61ScsTHt0bzoUivtad0zXhA_yZYC-zcUXvIYb-5Lo5ukC&wd=&eqid=c56549900001e8ab0000000657bfff79">福昕PDF阅读器</a></p>
<!--    			<p class="p2">工行、农行、中行、建行、邮储、兴业、光大、中信、交行、平安（含原深发）、上海银行、浦发、北京银行、长沙银行、湖南农信、华融湘江。</p> -->
<!-- 			<p class="p2">· 全国性信用卡银行：</p> -->
<!-- 			<p class="p2">中行、建行、邮储、兴业、民生、广发、华夏、浦发、平安（含原深发）、上海银行、光大、长沙银行。</p> -->
   			</div>
   			
   			
   			</div>
   			
   			
   		</div>
		    <br clear="all">  
			<br clear="all">    
			<br clear="all">   
			<br clear="all">  
			<br clear="all">   
			<br clear="all">   
							
   	</div>
  </body>
  <script type="text/javascript">
  		$("#btnsubmit").click(function(){
  			$("#msg").hide();
// 			var caseId = $("#caseId").val();
// 			var driverVehicleNumber = $("#driverVehicleNumber").val();
// 			if(caseId == null || caseId == ''){
// 				$("#msg").text("请输入事故编号");
// 				$("#msg").show();
// 				return;
// 			}
// 			if(driverVehicleNumber == null || driverVehicleNumber == ''){
// 				$("#msg").text("请输入车牌编号");
// 				$("#msg").show();
// 				return;
// 			}
			$("#myform").submit();
//   			$.ajax({
//   				type: "POST",
// 				url: "${ctx}/weixin/rc/ResponsibilityConfirmationDown.do",
// 				data : $("#myform").serialize(),
// 				dataType:'json', 
// 				success: function(result){
// 					if(result.code != 0){
// 						$("#msg").text(result.msg);
// 						$("#msg").show();
// 					}
// 				}
  				
//   			})
  		})
  </script>
</html>
