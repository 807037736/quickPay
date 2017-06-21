<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionaryDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/umtusertask/UmTUserTaskAdd.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="right_detail_top"><h3>用户功能权限配置</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">用户代码</td>
						<td class="">${umTUser.id.userCode}</td>
						<td class="bgc_tt short">用户姓名</td>
						<td class="">${umTUser.userName}</td>
						<td class="bgc_tt short">有效状态</td>
						<td class="">
							<ce:select name="umTUser.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTUser.validStatus}"
								disabled="true" />
							<input type="hidden" id="umTUser.id.userCode" value="${umTUser.id.userCode}" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<!-- 功能权限树 -->
	<div class="block "style="text-align:left">
		<h3></h3>
		<table>
		<tr>
			<td>
				<ul id="tasktree" class="easyui-tree"></ul>
			</td>
		</tr>
		</table>
	</div>
	<input type="button" class="button_ty" onclick="getChecked()" value="保存授权信息" />
	<input type="hidden" id="serverCode" value="${serverCode }" />
	<!-- 
		<button id="btn" onclick="getChecked()">提交信息</button>
	 -->
</body>
</html>
