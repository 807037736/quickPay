<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionaryDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/umtuserrole/UmTUserRoleAdd.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="right_detail_top"><h3>用户角色配置</h3></div>
	<form name="fm" id="fm">
		<input type="hidden" id="serverCode" value="${serverCode }" />
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">用户代码</td>
						<td class="">${umTUser.id.userCode}</td>
						<td class="bgc_tt short">用户姓名</td>
						<td class="">${umTUser.userName}</td>
						<td class="bgc_tt short">有效状态</td>
						<td class=""><ce:select name="umTUser.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTUser.validStatus}"
								disabled="true" /> <input type="hidden"
							id="umTUser.id.userCode" value="${umTUser.id.userCode}" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<!-- 岗位列表 -->
	<div class="block">
		<h3>岗位列表</h3>
	<table id="roleList" class="fix_table">
			<thead>
				<tr>
					<th class="bgc_tt short" style="text-align: center">角色ID</th>
					<th class="bgc_tt short" style="text-align: center">角色名称</th>
					<th class="bgc_tt short" style="text-align: center">是否授权</th>
				</tr>
			</thead>
	</table>
	</div>
	<table id="thirdInfo" class="fix_table">
		<thead>
			<tr>
				<td class="long" style="text-align:center"><input type="button"
					class="button_ty" onclick="saveUserRole()"
					value="保存授权信息" /></td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

</body>
</html>
