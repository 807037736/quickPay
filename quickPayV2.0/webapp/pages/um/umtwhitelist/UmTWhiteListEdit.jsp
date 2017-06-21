<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTWhiteList"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/umtwhitelist/UmTWhiteList.js"></script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3>
<c:choose>
							<c:when test="${operateType == 'update'}">
					修改白名单
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加白名单
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看白名单
				</c:when>
						</c:choose>
						</h3></div>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtwhitelist"
		namespace="/um/umtwhitelist" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short">白名单ID</td>
				<td class="long"><c:choose>
						<c:when test="${operateType == 'update'}">
${umTWhiteList.id.wlId}					<input type="hidden"
								name="umTWhiteList.id.wlId" id="umTWhiteList.id.wlId"
								value="${umTWhiteList.id.wlId}">
						</c:when>
						<c:when test="${operateType == 'add'}">
							<input class='input_w w_15' name="umTWhiteList.id.wlId" type="hidden"
								id="umTWhiteList.id.wlId" value="${umTWhiteList.id.wlId}">
						</c:when>
						<c:when test="${operateType == 'view'}">
${umTWhiteList.id.wlId}					<input type="hidden"
								name="umTWhiteList.id.wlId" id="umTWhiteList.id.wlId"
								value="${umTWhiteList.id.wlId}">
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td class="bgc_tt short">访问URL</td>
				<td class="long"><input class='input_w w_15'
					name="umTWhiteList.visitUrl" id="umTWhiteList.visitUrl"
					value="${umTWhiteList.visitUrl}" style="width:70%" maxlength="1000" /></td>
			</tr>
			<tr>
				<td class="bgc_tt short">访问地址描述</td>
				<td class="long"><input class='input_w w_15'
					name="umTWhiteList.visitDesc" id="umTWhiteList.visitDesc"
					value="${umTWhiteList.visitDesc}" style="width:70%" maxlength="30" /></td>
			</tr>
			<tr>
				<td class="bgc_tt short">有效状态</td>
				<td class="long">
					<ce:select list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" name="umTWhiteList.validStatus" 
						id="umTWhiteList.validStatus" value="${umTWhiteList.validStatus}" />
				</td>
			</tr>
			<c:choose>
				<c:when test="${operateType == 'view'}">
					<tr>
						<td class="bgc_tt short">创建者代码</td>
						<td class="long">${umTWhiteList.creatorCode}</td>
					</tr>
					<tr>
						<td class="bgc_tt short">创建时间</td>
						<td class="long">${umTWhiteList.insertTimeForHis}</td>
					</tr>
					<tr>
						<td class="bgc_tt short">修改者代码</td>
						<td class="long">${umTWhiteList.updaterCode}</td>
					</tr>
					<tr>
						<td class="bgc_tt short">修改时间</td>
						<td class="long">${umTWhiteList.operateTimeForHis}</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		<table class="fix_table">
			<tr>
				<td colspan=6 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
		</div>
		</div>
	</form>
</body>
</html>
