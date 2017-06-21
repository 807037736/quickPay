<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<meta http-equiv="Content-Type" content="application/json" charset="utf-8">
<title>自助定责</title>
<meta name="keywords" content="当事人信息">
<meta name="description" content="当事人信息">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/index.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/main.css">
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/mui.min.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/jweixin-1.0.0.js"></script>
<script src="${ctx }/pages/qp/qpticpicturegroup/js/weixin/myapp.js"></script>
<script src="${ctx }/pages/qp/weixin/resources/js/jquery.min.js"></script> 
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:window.history.go(-1);"></a>当事人信息
	</header>
	<div class="identify-tips"></div>
	<form id="submitform">
		<div class="info-item">
		    <h3>本人信息</h3>
			<ul class="item-box">		
				<li>
					<label>驾驶员姓名</label>
					<input readonly="readonly" name="accident1.accidentname" value="${accident1.accidentname }" type="text" placeholder="请输入驾驶员姓名"/>
				</li>
				<li>
					<label>手机号</label>
					<input readonly="readonly" name="accident1.mobile" value="${accident1.mobile }" type="text" placeholder="请输入手机号"/>
				</li>
				<li>
					<label>车牌号</label>
					<input name="accident1.licensenumber" value="${accident1.licensenumber }" type="text" placeholder="请输入车牌号"/>
				</li>
				<li>
					<label>身份证号</label>
					<input readonly="readonly" name="accident1.identfinynumber" value="${accident1.identfinynumber }" type="text" placeholder="请输入身份证号"/>
				</li>
				<li id="resp1">
					<label>承担责任</label>
					<input id="acc1respName" type="text" disabled="disabled" placeholder="请选择责任认定"/>
					<input name="accident1.responsibilitycode" id="acc1respCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="company1">
					<label>保险公司</label>
					<input id="acc1companyName" type="text" disabled="disabled" placeholder="请选择保险公司"/>
					<input name="accident1.coid" id="acc1companyCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="cartype1">
					<label>车辆类型</label>
					<input id="acc1cartypeName" type="text" disabled="disabled" placeholder="请选择车辆类型"/>
					<input name="accident1.cartype" id="acc1cartypeCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="drivetype1">
					<label>准驾车型</label>
					<input id="acc1drivetypeName" type="text" disabled="disabled" placeholder="请选择准驾车型"/>
					<input name="accident1.drivetype" id="acc1drivetypeCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
			</ul>
		</div>
		 <a class="btn" onclick="getAcc2()">扫一扫对方微信名片</a>
		<div class="info-item">
		    <h3>对方信息 </h3>
		    
			<ul class="item-box">		
				<li>
					<label>驾驶员姓名</label>
					<input readonly="readonly" id="acc2name" name="accident2.accidentname" type="text" placeholder="请输入驾驶员姓名"/>
				</li>
				<li>
					<label>手机号</label>
					<input readonly="readonly" id="acc2mobile" name="accident2.mobile" type="text" placeholder="请输入手机号"/>
				</li>
				<li>
					<label>车牌号</label>
					<input id="acc2licenseno" name="accident2.licensenumber" type="text" placeholder="请输入车牌号"/>
				</li>
				<li>
					<label>身份证号</label>
					<input readonly="readonly" id="acc2identno" name="accident2.identfinynumber" value="${accident2.identfinynumber }" type="text" placeholder="请输入身份证号"/>
				</li>
				<li id="resp2">
					<label>承担责任</label>
					<input id="acc2respName" type="text" disabled="disabled" placeholder="请选择责任认定"/>
					<input name="accident2.responsibilitycode" id="acc2respCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="company2">
					<label>保险公司</label>
					<input id="acc2companyName" type="text" disabled="disabled" placeholder="请选择保险公司"/>
					<input name="accident2.coid" id="acc2companyCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="cartype2">
					<label>车辆类型</label>
					<input id="acc2cartypeName" type="text" disabled="disabled" placeholder="请选择车辆类型"/>
					<input name="accident2.cartype" id="acc2cartypeCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
				<li id="drivetype2">
					<label>准驾车型</label>
					<input id="acc2drivetypeName" type="text" disabled="disabled" placeholder="请选择准驾车型"/>
					<input name="accident2.drivetype" id="acc2drivetypeCode" type="hidden" />
					<span class="personInfo-choose"><img src="${ctx }/pages/qp/weixin/resources/img/icon_in_arrow.png" /></span>
				</li>
			</ul>
		</div>
		<input name="wxCaseId" type="hidden" value="${wxCaseId }">
		<input name="param" type="hidden" value="${param1 }" />
		<input id="isDue" name="isDue" type="hidden" />
	</form>
	<a class="btn" onclick="submit(true)">责任认定无争议</a>
	<a class="btn" onclick="submit(false)">责任认定有争议，提交交警远程指导吧</a>
	<div class="bottom-tips">
		如无法协商请拔打110请警方协助
	</div>
	<div class="modal-bg"></div>
	<!--责任弹出框 正常隐藏-->
	<div class="choose-box" id="zr1">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="responsibilityType" items="${responsibilityTypeList}" >
				<li value="${responsibilityType.codeCode}">${responsibilityType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--保险公司弹出框 正常隐藏-->
	<div class="choose-box" id="bxgs1">
		<h3><span class="wc" ><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="qpTICCompany" items="${qpTICCompanyList}" >
				<li value="${qpTICCompany.coId}">${qpTICCompany.coName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--车辆类型弹出框 正常隐藏-->
	<div class="choose-box" id="cllx1">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="vehicleType" items="${vehicleTypeList}" >
				<li value="${vehicleType.codeCode}">${vehicleType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--准驾车型弹出框 正常隐藏-->
	<div class="choose-box" id="zjcx1">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="permissionDriveType" items="${permissionDriveTypeList}" >
				<li value="${permissionDriveType.codeCode}">${permissionDriveType.codeCode}-${permissionDriveType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	
	<!--责任弹出框 正常隐藏-->
	<div class="choose-box" id="zr2">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="responsibilityType" items="${responsibilityTypeList}" >
				<li value="${responsibilityType.codeCode}">${responsibilityType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--保险公司弹出框 正常隐藏-->
	<div class="choose-box" id="bxgs2">
		<h3><span class="wc" ><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="qpTICCompany" items="${qpTICCompanyList}" >
				<li value="${qpTICCompany.coId}">${qpTICCompany.coName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--车辆类型弹出框 正常隐藏-->
	<div class="choose-box" id="cllx2">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="vehicleType" items="${vehicleTypeList}" >
				<li value="${vehicleType.codeCode}">${vehicleType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	<!--准驾车型弹出框 正常隐藏-->
	<div class="choose-box" id="zjcx2">
		<h3><span class="wc"><img src="${ctx}/pages/qp/weixin/resources/img/icon_close-.png" /></span><a class="wc">完成</a></h3>
		<ul>
			<c:forEach var="permissionDriveType" items="${permissionDriveTypeList}" >
				<li value="${permissionDriveType.codeCode}">${permissionDriveType.codeCode}-${permissionDriveType.codeCName}</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		// 选择完成
		$(".wc").click(function(){
			$(".modal-bg").hide();
			$(this).parent().parent().hide();
			//$("#zr").hide();
		});
		
		// 责任认定框处理
		$("#resp1").click(function(){
			$(".modal-bg").show();
			$("#zr1").show();
		});
		
		$("#zr1").find("li").click(function(){
			$("#acc1respName").val($(this).text());
			$("#acc1respCode").val($(this).attr("value"));
			$("#zr1").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#zr1").hide();
		});
		
		// 保险公司框处理
		$("#company1").click(function(){
			$(".modal-bg").show();
			$("#bxgs1").show();
		});
		
		$("#bxgs1").find("li").click(function(){
			$("#acc1companyName").val($(this).text());
			$("#acc1companyCode").val($(this).attr("value"));
			$("#bxgs1").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#bxgs1").hide();
		});
		
		// 车辆类型框处理
		$("#cartype1").click(function(){
			$(".modal-bg").show();
			$("#cllx1").show();
		});
		
		$("#cllx1").find("li").click(function(){
			$("#acc1cartypeName").val($(this).text());
			$("#acc1cartypeCode").val($(this).attr("value"));
			$("#cllx1").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#cllx1").hide();
		});
		
		// 准驾车型框处理
		$("#drivetype1").click(function(){
			$(".modal-bg").show();
			$("#zjcx1").show();
		});
		
		$("#zjcx1").find("li").click(function(){
			var text = $(this).text();
			var textarr = text.split("-");
			$("#acc1drivetypeName").val(textarr[1]);
			$("#acc1drivetypeCode").val(textarr[0]);
			$("#zjcx1").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#zjcx1").hide();
		});
		
		// 责任认定框处理
		$("#resp2").click(function(){
			$(".modal-bg").show();
			$("#zr2").show();
		});
		
		$("#zr2").find("li").click(function(){
			$("#acc2respName").val($(this).text());
			$("#acc2respCode").val($(this).attr("value"));
			$("#zr2").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#zr2").hide();
		});
		
		// 保险公司框处理
		$("#company2").click(function(){
			$(".modal-bg").show();
			$("#bxgs2").show();
		});
		
		$("#bxgs2").find("li").click(function(){
			$("#acc2companyName").val($(this).text());
			$("#acc2companyCode").val($(this).attr("value"));
			$("#bxgs2").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#bxgs2").hide();
		});
		
		// 车辆类型框处理
		$("#cartype2").click(function(){
			$(".modal-bg").show();
			$("#cllx2").show();
		});
		
		$("#cllx2").find("li").click(function(){
			$("#acc2cartypeName").val($(this).text());
			$("#acc2cartypeCode").val($(this).attr("value"));
			$("#cllx2").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#cllx2").hide();
		});
		
		// 准驾车型框处理
		$("#drivetype2").click(function(){
			$(".modal-bg").show();
			$("#zjcx2").show();
		});
		
		$("#zjcx2").find("li").click(function(){
			var text = $(this).text();
			var textarr = text.split("-");
			$("#acc2drivetypeName").val(textarr[1]);
			$("#acc2drivetypeCode").val(textarr[0]);
			$("#zjcx2").find("li").removeClass("active");
			$(this).addClass("active");
			$(".modal-bg").hide();
			$("#zjcx2").hide();
		});
		
		// isNeed是否需要校验责任字段
		var submit = function (isNeed) {
			if (!validatorByType(isNeed)) {
				return false; 
			}
			if (isNeed) {
				$("#isDue").val("0");
			} else {
				$("#isDue").val("1");
			}
			$.ajax({
				url : "${ctx }/weixin/case/saveAccident.do",
				type : "post",
				dataType : "json",
				data : $("#submitform").serialize(),
				success : function(data) {
					if (data.state && data.state == "success") {
						window.location.href = "${ctx }/weixin/case/toVerifCode.do?wxCaseId=${wxCaseId }&param=${param1 }";
					} else {
						showTips(data.msg);
					}
				},
				error : function (data) {
					
				}
			});
		};
		
		/**
		 * hasResp 是否校验责任
		 */
		var validatorByType = function (hasResp) {
			var phoneReg = /^1[3|4|5|7|8][0-9]\d{8}$/,
				carNoReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/,
				idReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
			if (!$("input[name='accident1.accidentname']").val()
					|| !$("input[name='accident2.accidentname']").val()) {
				showTips("请输入姓名");
				return false;
			}
			if (!phoneReg.test($("input[name='accident1.mobile']").val())
					|| !phoneReg.test($("input[name='accident2.mobile']").val())) {
				showTips("请输入正确的手机号");
				return false;
			}
			if ($("input[name='accident1.mobile']").val() 
					== $("input[name='accident2.mobile']").val()) {
				showTips("双方当事人手机号相同");
				return false;
			}
			if (!carNoReg.test($("input[name='accident1.licensenumber']").val())
					|| !carNoReg.test($("input[name='accident2.licensenumber']").val())) {
				showTips("请输入正确的车牌号");
				return false;
			}
			if ($("input[name='accident1.licensenumber']").val()
					== $("input[name='accident2.licensenumber']").val()) {
				showTips("双方当事人车牌号相同");
				return false;
			}
			if (!idReg.test($("input[name='accident1.identfinynumber']").val())
					|| !idReg.test($("input[name='accident2.identfinynumber']").val())) {
				showTips("请输入正确的身份证号");
				return false;
			}
			if ($("input[name='accident1.identfinynumber']").val()
					== $("input[name='accident2.identfinynumber']").val()) {
				showTips("双方当事人身份证号相同");
				return false;
			}
			if (hasResp && (!$("#acc1respCode").val() || !$("#acc2respCode").val())) {
				showTips("请选择承担责任");
				return false;
			}
			if (!$("#acc1companyCode").val() || !$("#acc2companyCode").val()) {
				showTips("请选择保险公司");
				return false;
			}
			if (!$("#acc1cartypeCode").val() || !$("#acc2cartypeCode").val()) {
				showTips("请选择车辆类型");
				return false;
			}
			if (!$("#acc1drivetypeCode").val() || !$("#acc2drivetypeCode").val()) {
				showTips("请选择准驾车型");
				return false;
			}
			return true;
		};
		
		var showTips = function (content) {
			$(".identify-tips").text(content);
			$(".identify-tips").fadeIn();
			setTimeout(function () {
				$(".identify-tips").fadeOut();
				$(".identify-tips").empty();
			}, 1500);
		};
		
		mui.init();
		
		$.ajax({
			async : false,
			url : '${ctx}/weixin/case/weixin.do',
			type : 'POST',
			data : {
				url : location.href.split('#')[0]
			},
			dataType : 'json',
			timeout : 1000,
			error : function() {
				alert('Error');
			},
			success : function(result) {
				wx.config({
					debug : false,
					appId : result.appId,
					timestamp : result.timestamp,
					nonceStr : result.nonceStr,
					signature : result.signature,
					jsApiList : ['scanQRCode']
				});
			}
		});
		
		wx.ready(function() {
			
		});
		
		var getAcc2 = function () {
			wx.scanQRCode({
		   		needResult: 1,
		    	success: function (res) {
		    		$.ajax({
		    			url: "${ctx}/weixin/regist/getInfo.do",
		    			type: "post",
		    			dataType: "json",
		    			data: JSON.parse(res.resultStr),
		    			success: function(data) {
		    				if (data && data.code == "0") {
		    					$("#acc2name").val(data.data.userName);
		    					$("#acc2mobile").val(data.data.mobileNo);
		    					$("#acc2licenseno").val(data.data.licenseNo);
		    					$("#acc2identno").val(data.data.identityNumber);
		    				}
		    			},
		    			error: function() {
		    			}
		    		});
				}
			});
		};
		
		$(function () {
			var a = "${errMsg}";
			if (a) {
				alert(a);
			}
		});
	</script>
</body>
</html>