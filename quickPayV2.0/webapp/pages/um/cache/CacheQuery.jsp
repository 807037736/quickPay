<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTTask"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/cache/Cache.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="right_detail_top"><h3>缓存查询</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					
						<td class="bgc_tt short">缓存名字</td>
						<td class="long"><input class='input_w w_15'
							name="cacheManagerInfo.cacheName" id="cacheManagerInfo.cacheName"
							value="${cacheManagerInfo.cacheName}">
						</td>
						
					
					
					
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="CacheTable"></table>
	</div>
</body>
</html>
