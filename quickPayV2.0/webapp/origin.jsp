<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<html>
<head>
<title></title>
</head>

<body>

	<iframe id="loginForm" name="loginForm" src="" style="display: none;"></iframe>

	<!-- 
	<form id="cognosLogin" action="" method="get" style="display: none">
		<input name="b_action" value="xts.run" />
		<input name="m" value="portal/bridge.xts" />
		<input name="c_env" />
		<input name="CAMNamespace" value="dbAuth" />
		<input name="h_CAM_action" value="logonAs" />
		<input name="CAMUsername" />
		<input name="CAMPassword"  />
	</form>
	 -->
	 
</body>
</html>
<script type="text/javascript">

	function createIframe(url){
		 $("#loginForm").attr("src",url);
	}


	$(window).load(function(){
		var cognos_err_msg = "${cognos_err_msg}";
		if(cognos_err_msg == 'success'){
			var url = "${COGNOS_URL}?b_action=xts.run&m=portal/bridge.xts&c_env=${CRNENV_PATH}&CAMNamespace=dbAuth&h_CAM_action=logonAs&CAMUsername=${SessionUser.userCode}&CAMPassword=${SessionUser.userPass}";
			if (window.addEventListener){
				try{
					window.addEventListener("load", createIframe(url));
				}catch(e){
					location.href="${ctx}/systemChoice.do";
				}
				setTimeout("location.href='${ctx}/systemChoice.do'",500);
			} else if (window.attachEvent){
				try{
					window.attachEvent("onload", createIframe(url));
				}catch(e){
					location.hef="${ctx}/systemChoice.do";	
				}
				setTimeout("location.href='${ctx}/systemChoice.do'",500);
			} else {
				try{
					window.onload = createIframe(url);
				}catch(e){
					location.href="${ctx}/systemChoice.do";
				}
				setTimeout("location.href='${ctx}/systemChoice.do'",500);
			}
			/*
			document.write("<iframe scrolling='no' noresize='noresize'  name='cognosLogonIframe' id='cognosLogonIframe' style='display: none' src='${COGNOS_URL}?b_action=xts.run&m=portal/bridge.xts&c_env=${CRNENV_PATH}&CAMNamespace=dbAuth&h_CAM_action=logonAs&CAMUsername=${SessionUser.userCode}&CAMPassword=${SessionUser.userPass}'></iframe>");
			var frame = $("#cognosLogonIframe");				//获取登录Cognos触发frame
			try{
				frame.load(function(){
					location.href="${ctx}/systemChoice.do";
				});
			}catch(err){}
			setTimeout("location.href='${ctx}/systemChoice.do'",200);
			*/
		}else {
			location.href = "${ctx}/systemChoice.do";
		}
	});
</script>