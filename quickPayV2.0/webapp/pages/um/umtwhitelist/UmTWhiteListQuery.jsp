<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTWhiteList" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtwhitelist/UmTWhiteList.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="right_detail_top"><h3>白名单管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		白名单ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTWhiteList.id.wlId" id="umTWhiteList.id.wlId" value="${umTWhiteList.id.wlId}">
		</td>
	<td class="bgc_tt short">
		访问URL
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTWhiteList.visitUrl" id="umTWhiteList.visitUrl" value="${umTWhiteList.visitUrl}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		访问地址描述
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTWhiteList.visitDesc" id="umTWhiteList.visitDesc" value="${umTWhiteList.visitDesc}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<ce:select list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" name="umTWhiteList.validStatus" 
						id="umTWhiteList.validStatus" value="${umTWhiteList.validStatus}" />
			<!-- 
			<input class='input_w w_15' name="umTWhiteList.validStatus" id="umTWhiteList.validStatus" value="${umTWhiteList.validStatus}">
			 -->
		</td>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTWhiteListTable"></table>
	</div>
</body>
</html>
