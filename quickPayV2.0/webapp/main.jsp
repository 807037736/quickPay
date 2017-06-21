<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${serverName}</title>
<link rel="shortcut icon" href="${ctx}/favicon.ico" />
<script language="javascript">
	function quit() {
		var event=window.event?window.event:evt;
		if (event.clientX <= 0 || event.clientY < 0) {
			event.returnValue = "是否确定要关闭页面?";
		}
	}
	
	
	
	function show(msg){
		/*  $.messager.show({
			title:'新短消息',
			msg: msg,
			timeout:10000,
			showType:'slide'
		});  */
		 alert(msg);
	}
</script>
</head>

	<frameset id="mainFrame" rows="*" cols="1113" framespacing="0"
		frameborder="no" border="0" onbeforeunload="quit()">
	
		<!--<frame src="${ctx}/pages/head.do" name="head" scrolling="no" id="head"  />-->
		<frame
			src="${ctx}/um/umtmenu/prepareAll.do?serverCode=${serverCode}&serverName=${serverName}"
			name="main" scrolling="yes" id="main"/>
			<!--
		<frame style="display: none" 
			src="${COGNOS_URL}?b_action=xts.run&m=portal/bridge.xts&c_env=${CRNENV_PATH}&CAMNamespace=dbAuth&h_CAM_action=logonAs&CAMUsername=${SessionUser.userCode}&CAMPassword=${SessionUser.userPass}"
			name="cognos" scrolling="yes" id="cognos"/>
			-->
		<noframes>
		您的浏览器不支持框架!
		</noframes>
	</frameset>
</html>
