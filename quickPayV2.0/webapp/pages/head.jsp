<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<script language="vbscript" src="${ctx}/common/js/urlencode.vbs"></script>
<script language="javascript" src="${ctx}/common/js/sinosoft.js"></script>
<script language="javascript" src="${ctx}/common/js/Common.js"></script>
<script language="javascript" src="${ctx}/common/js/Application.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/yahoo-dom-event/yahoo-dom-event.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/connection/connection-min.js"></script>
<link href="${ctx}/style/mian_demo.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#shutShow:HOVER{
	cursor: pointer;
	color: blue;
	stress: inherit;
}
</style>
<script type="text/javascript">
	function doExit() {
		if (confirm("确定退出吗")) {
			window.location.href = "${ctx}/common/exitframe.do";
			parent.window.opener = null;
			parent.window.close();
		} else {

		}
	}

	function doClear() {
		window.location.href = "${ctx}/common/clearMemory.do";
	}

	function setWinSize(url) {
		window
				.open(
						url,
						"newwindow",
						"height=580, width=400, top=300, left=300, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");

	}

	function judgeLength() {
		var comMar = document.getElementById('comMar');
		var content = comMar.innerHTML;
		var len = content.length;
		if (len > 20) {
			comMar.innerHTML = "<marquee direction=left scrollamount=3 width=200 title='${ComCName}(${ComCode})'>${ComCName}(${ComCode})</marquee>";
		}
		 init();
	} 

	function switchLoginCompany() {
		window
				.open(
						"${ctx}/common/switchLoginCompany.do",
						"切换机构",
						"height=200, width=400, top=300, left=300, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
		//var comMar = document.getElementById('comMar');
		//var oriContent = comMar.innerHTML;
		//var content = "<div id=comCodeAc class='selectui-indiv'><div class='selectConfig'><div class='codeType'>ComCode</div><div class='type'>inputLoad</div><div class='display'>code</div><div class='inputHint'>登录机构代码或名称</div></div><input name='claimCondition.groupName' id='claimCondition.groupName' class='selectui-input input_w w_22'/><div id='claimCondition.groupName_Container' class='selectui-container'></div></div>";
		//comMar.innerHTML = content;
	}

	var styChangeTimes = 0;
	function changeSty(id) {
		obj = document.getElementById(id);
		styChangeTimes = styChangeTimes + 1;
		if ((styChangeTimes % 2) == 0) {
			obj.style.display = "none";
		} else {
			obj.style.display = "";
		}
		if (styChangeTimes < 200) {
			window.setTimeout("changeSty('" + id + "')", 200);
		} else {
			styChangeTimes = 0;
			obj.style.display = "";
		}
	}
	

	function init(){
		//dwr.engine.setActiveReverseAjax(true); // 激活反转 
		//dwr.engine.setNotifyServerOnPageUnload(true); //在刷新或关闭页面时销毁对应的ScriptSession对象
	}
	
	/* 显示消息  */
	function scroll(msg){
		document.getElementById('scrollShow').style.display = 'block';
		document.getElementById('scrollShow').style.backgroundColor = '#eeeeee';
		document.getElementById('show').innerHTML = msg;
	}
	
	function displayShow(){
		document.getElementById('scrollShow').style.display = 'none';
	}
	
</script>
<style type="text/css">
<!--
TD.logo {
	width: 250px;
	background-image: url(../pages/image/logo_n2.gif);
	background-repeat: no-repeat;
	background-position: center center;
}
-->
</style>
</head>
<body>
	<div id="header_d1">
		<div class="mian_head F_3F6293">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td rowspan="2" class="logo" nowrap>&nbsp;</td>
					<td align="left" nowrap="nowrap">
						<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="2">
							<tr>
								<td nowrap><img src="images/icon_user.gif" border="0" /> 当前操作员：${UserName}(${UserCode})&nbsp; <img
									src="images/icon_co.gif" border="0" /> 登录机构： <span id='comMar'>${ComCName}(${ComCode})</span></td>
								<input type="hidden" id="currentUser" value="${UserCode}" />
							</tr>
						</table></td>
				</tr>
				<tr>
					<td align="left" nowrap="nowrap">
						<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="2">
							<tr>
								<td nowrap>您当前的位置: <span align="left" id="CurrentPositionSpan">首页 </span></td>

								<td nowrap width="30%" align="right"></td>
								<td><div id="count">
								</td>
								<!-- 
								<td nowrap width="30%" align="right"><a href="${ctx}/user/logout.do" target="_parent" class="F_3F6293">[重新登录]</a>&nbsp;
								 -->
								 <td nowrap width="30%" align="right"><a href="${ctx}/j_spring_security_logout" target="_parent" class="F_3F6293">[重新登录]</a>&nbsp;
								</td>

							</tr>
						</table></td>
				</tr>
			</table>
			<table width="100%" id="scrollShow" style="display:none;" >
				<tr><td id="show" style="font-size:1.5em" align="center"></td></tr>
				<tr><td align="right"><a onclick="displayShow();" id="shutShow" style="background-color: #eeeeee;"><small>关闭</small></a></td></tr>
			</table>
		</div>
	</div>
</body>
</html>
