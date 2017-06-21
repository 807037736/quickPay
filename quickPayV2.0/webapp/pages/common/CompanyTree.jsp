<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRole" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/role/UmTRole.js"></script>
<!-- 
<script language="javascript" src="${ctx}/pages/um/role/RcTree.js"></script>
 -->
<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
	
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true">

		<!-- 机构树 -->
		<ul id="comTree" class="easyui-tree"></ul>

	</div>
</body>
</html>
