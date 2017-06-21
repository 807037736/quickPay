<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<title>自助定责</title>
<meta name="keywords" content="当事人信息">
<meta name="description" content="当事人信息">
<meta name="viewport" id="viewport" content="width = device-width, initial-scale = 1, minimum-scale = 1, maximum-scale = 1">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/common.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/index.css">
<link rel="stylesheet" href="${ctx }/pages/qp/weixin/resources/css/main.css">
</head>
<body>
	<header>
		<a class="leftBtn" href="javascript:window.history.go(-1);"></a>当事人信息
	</header>
	<form id="submitform">
		<div class="info-item">
		    <h3>本人信息</h3>
			<ul class="item-box">		
				<li>
					<label>驾驶员姓名</label>
					<span>${accidents[0].accidentname }</span>
					<input name="accident1.accidentname" value="${accidents[0].accidentname }" type="hidden">
				</li>
				<c:if test="${wxcase.isDue == 0}">
					<li>
						<label>责任类型</label>
						<span id="respDesc1"></span>
						<input name="accident1.responsibilitycode" id="resp1" type="hidden">
					</li>
				</c:if>
				<c:if test="${wxcase.isDue == 1}">
				</c:if>
				<li>
					<label>车牌号</label>
					<span>${fn:substring(accidents[0].licensenumber, 0, 2)}***${fn:substring(accidents[0].licensenumber, 6, 7)}</span>
					<input name="accident1.licensenumber" value="${accidents[0].licensenumber }" type="hidden">
				</li>
				<li>
					<label>手机号码</label>
					<span>${fn:substring(accidents[0].mobile, 0, 3)}****${fn:substring(accidents[0].mobile, 7, 11)}</span>
					<input id="mobile1" name="accident1.mobile" value="${accidents[0].mobile }" type="hidden">
				</li>					
				<li class="identify">
					<label>验证码</label>
					<input id="verifCode1" name="verifCode1" type="text" placeholder="请输入验证码"/>
					<!-- 
					<span class="delete deletes"><img src="img/icon_delete.png" /></span>
					 -->
					<!--正常状态隐藏删除图标，当输入内容时显示删除图标，点击删除图标，隐藏-->
					<a onclick="sendCode($('#mobile1').val())">获取验证码</a>
				</li>		
			</ul>
		</div>
		<div class="info-item">
		    <h3>对方信息</h3>
			<ul class="item-box">		
				<li>
					<label>驾驶员姓名</label>
					<span>${accidents[1].accidentname }</span>
					<input name="accident2.accidentname" value="${accidents[1].accidentname }" type="hidden">
				</li>
				<c:if test="${wxcase.isDue == 0}">
					<li>
						<label>责任类型</label>
						<span id="respDesc2"></span>
						<input name="accident2.responsibilitycode" id="resp2" type="hidden">
					</li>
				</c:if>
				<c:if test="${wxcase.isDue == 1}">
				</c:if>
				<li>
					<label>车牌号</label>
					<span>${fn:substring(accidents[1].licensenumber, 0, 2)}***${fn:substring(accidents[1].licensenumber, 6, 7)}</span>
					<input name="accident2.licensenumber" value="${accidents[1].licensenumber }" type="hidden">
				</li>
				<li>
					<label>手机号码</label>
					<span>${fn:substring(accidents[1].mobile, 0, 3)}****${fn:substring(accidents[1].mobile, 7, 11)}</span>
					<input id="mobile2" name="accident2.mobile" value="${accidents[1].mobile }" type="hidden">
				</li>					
				<li class="identify">
					<label>验证码</label>
					<input id="verifCode2" name="verifCode2" type="text" placeholder="请输入验证码"/>
					<!-- 
					<span class="delete deletes"><img src="img/icon_delete.png" /></span>
					 -->
					<!--正常状态隐藏删除图标，当输入内容时显示删除图标，点击删除图标，隐藏-->
					<a onclick="sendCode($('#mobile2').val())">获取验证码</a>
				</li>		
			</ul>
		</div>
		<input name="wxCaseId" type="hidden" value="${wxCaseId }">
		<input type="hidden" id="param" name="param" value="${param1 }" />
		<input id="isDue" name="isDue" type="hidden" value="${wxcase.isDue }">
	</form>
	<a class="btn" onclick="submit()">确定</a>
	<div class="identify-tips"></div>
	<script type="text/javascript">
		var submit = function () {
			if (!submitValidator()) {
				alert("请输入验证码");
				return;
			}
			$.ajax({
				url : "${ctx }/weixin/case/prepareFinish.do",
				type : "post",
				dataType : "json",
				data : $("#submitform").serialize(),
				success : function(data) {
					if (data.state == "success") {
						if ($("#isDue") && $("#isDue").val() == 1) {
							window.location.href = "${ctx }/pages/qp/weixin/case/waitForPoliceDoing.jsp?wxCaseId=${wxCaseId }&param=${param1 }";
						} else {
							window.location.href = "${ctx }/weixin/case/toFinish.do?wxCaseId=${wxCaseId }&param=${param1 }";
						}
					} else {
						alert(data.msg);						
					};
				},
				error : function (data) {
				}
			});
		};
		
		var submitValidator = function () {
			return $("#verifCode1").val() && $("#verifCode2").val(); 
		};
		
		var phoneValidator = function (phone) {
			return /^1[3|4|5|7|8][0-9]\d{8}$/.test(phone);
		};
		
		var sendCode = function (phone, isDue) {
			if (!phoneValidator(phone)) {
				showTips("请输入正确的手机号！");
				return false;
			}
			var data = $("#submitform").serialize() + "&phone=" + phone;
			$.ajax({
				url : "${ctx }/weixin/case/sendDingzeCode.do",
				type : "post",
				dataType : "json",
				data : data,
				success : function(data) {
					if (data.state == "success" && data.data.code && data.data.code == "200" && data.data.info && data.data.info.code == "0") {
						showTips("验证码已发送,请注意查收！");
					} else {
						showTips(data.msg)
					};
				},
				error : function (data) {
					showTips("网络错误！");
				}
			});
		};
		
		$(function () {
			// 短信要发送责任，将责任code改为对应字符串
			$("#resp1").val(rep["${accidents[0].responsibilitycode }"]);
			$("#respDesc1").text(rep["${accidents[0].responsibilitycode }"]);
			$("#resp2").val(rep["${accidents[1].responsibilitycode }"]);
			$("#respDesc2").text(rep["${accidents[1].responsibilitycode }"]);
		});
		
		// 先写死,将来使用接口的形式获取数据
		var rep = {10:"全责",11:"主要责任",12:"次要责任",13:"同等责任",14:"无责"};
		
		var showTips = function (content) {
			$(".identify-tips").text(content);
			$(".identify-tips").fadeIn();
			setTimeout(function () {
				$(".identify-tips").fadeOut();
				$(".identify-tips").empty();
			}, 1500);
		};
	</script>
</body>
</html>