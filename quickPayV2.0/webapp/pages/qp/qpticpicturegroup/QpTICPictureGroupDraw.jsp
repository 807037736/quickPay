<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

</head>
<body>
    <form name="fm" id="fm">
	    <input type="hidden" id="mobile" name="mobile" value="${mobile }" >
	    <input type="hidden" id="licenseNo" name="licenseNo" value="${licenseNo }" >
	</form>
	<table id="QpTICPictureGroupTable"></table>
	
<script language="javascript">

var query_action = contextRootPath + "/qp/qpticpicturegroup/queryForDrawSelect.do";

$(document).ready(function() {
	executeQuery();
	
});

/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	data = data + '&mobile=' + "${mobile }" + '&licenseNo=' + "${licenseNo }";
	var send_url = query_action + "?" + data;
	$('#QpTICPictureGroupTable').datagrid({
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns:[[
				    {field : 'checkBoxNo',checkbox : true},
					{field : 'groupId',title : '照片组号',align : 'center',sortable : true},
					{field : 'uploadTimeForHis',title : '上传时间',align : 'center',sortable : true},	
					
	        ]],
	    toolbar:[ {
	    	text : '选择提取',
	    	align : 'right',
	    	iconCls : 'icon-edit',
	    	handler : function() {
	    		select();
	    	}
	    }],
		onLoadSuccess : function(data) {
			$('#QpTICPictureGroupTable').datagrid('selectRow',0);
		},
	    singleSelect:true
	});
}

/* 选择 */
function select() {
	var rows = $('#QpTICPictureGroupTable').datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$('#qpTICAccidentGroupId').val(rows[0].groupId);
	$('#uploadPhotoWindow').window('close');
}

</script>
	
</body>
</html>
