<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPTeam" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttpteam/QpTTPTeam.js"></script>
<script type="text/javascript">
	function yes(){
		var prtW = window.opener;
		var nodeId = prtW.document.getElementById("nodeId").value;
		var nodelev=prtW.document.getElementById("nodelev").value;
		//window.location.href=contextRootPath+'/qp/urbanroadmanagement/deleteNode.do?id='+nodeId+"&nodelev="+nodelev;
		var caseUrl = contextRootPath + "/qp/urbanroadmanagement/deleteNode.do";
		var dataVal = "id="+nodeId+"&nodelev="+nodelev;
		$.ajax({
			type : "POST",
			url : caseUrl,
			async : false,
			cache : false, //缓存
			data: dataVal,
	        dataType: "json",
			success : function(result) {
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
		setTimeout(back(),5000);
	}
	function back(){
		window.opener.location.href = window.opener.location.href;
		  window.close();
	}
	function no(){
		 window.close();
	}
</script>
</head>
<body>
<div style="Width:100%;height:30px;text-align:center"></div>
	<div style="Width:100%;height:20px;text-align:center">确定要删除吗？</div>
	<div style="Width:100%;height:10px;text-align:center"></div>
	<input type="button" class="button_ty" onclick="yes();" value="确定">
	<input type="button" class="button_ty" onclick="no();" value="取消">
    
</body>
</html>