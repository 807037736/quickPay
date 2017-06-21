
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/widgets/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/widgets/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/pages/um/umtmenu/UmTMENU.js"></script>



<script type="text/javascript">
$(document).ready(function(){
	//营销系统菜单
	var menu="${menu}";
	creatTree(menu);
	
	//Cognos菜单
	var cognosMenu="${cognosMenu}";
	if(cognosMenu){
		createCognosMenuTree(cognosMenu);
	}
	
	
	//监听菜单树点击动作
	$('.easyui-tree').tree({
		onClick: function(node){
			var URL=node.attributes.url;
			var nodes = $('#tt').tree('getChecked');
			// alert node text property when clicked
			// call 'submit' method of form plugin to submit the form 
			var nodesIdArray=getCheckdeNodesId(nodes);
			//在iframe中打开页面
			$("#mainwindow").attr("src",URL);		
		}
	});
		
});





</script>
<title>Insert title here</title>
</head>
<body>
<div class="easyui-layout" fit="true" style="width:100%;height:100%;overflow:hidden;">
		<div data-options="region:'west',split:true" title="菜单" style="width:180px;"><ul id="tt" class="easyui-tree"></ul>
		<ul id="cognosMenu" class="easyui-tree"></ul>
		</div>
		<div id="main" data-options="region:'center',title:'主窗口'" style="width:100%;height:100%;overflow:hidden;"><iframe id="mainwindow" src="/khyx/portal/display.do" width="100%" height="100%" scrolling="yes"></iframe></div>

</div>

</body>
</html>