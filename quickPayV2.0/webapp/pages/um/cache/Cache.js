var query_action = contextRootPath + "/cache/queryAll.do";
var queryResultTable = "CacheTable";
var page_toolBar = [ {
	text : '删除',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		executeDelete();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},	
		{
			field : 'cacheName',
			title : '缓存名字',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'cacheImplClass',
			title : '缓存类型',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'size',
			title : '缓存大小',
			align : 'center',
			sortable : true
		},
		{
			field : 'cacheType',
			title : 'cacheType',
			align : 'center',
			sortable : true
		},
		{
			field : 'statisticsAccuracy',
			title : 'statisticsAccuracy',
			align : 'center',
			sortable : true
		},{
			field : 'cacheHits',
			title : 'cacheHits',
			align : 'center',
			sortable : true
		},{
			field : 'onDiskHits',
			title : 'onDiskHits',
			align : 'center',
			sortable : true
		},
		{
			field : 'inMemoryHits',
			title : 'inMemoryHits',
			align : 'center',
			sortable : true
		},
		{
			field : 'cacheMisses',
			title : 'cacheMisses',
			align : 'center',
			sortable : true
		},
		{
			field : 'memoryStoreSize',
			title : 'memoryStoreSize',
			align : 'center',
			sortable : true
		},
		{
			field : 'diskStoreSize',
			title : 'diskStoreSize',
			align : 'center',
			sortable : true
		},
		{
			field : 'averageGetTime',
			title : 'averageGetTime',
			align : 'averageGetTime',
			sortable : true
		}
				
		] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	
	$('#'+queryResultTable).datagrid({
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
			}

	});
}


/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num <= 0) {
		$.messager.alert('提示消息', "请至少选择一行", 'info');
		return;
	}
	var cachenames= new Array();
	var param = {};
	for(var i=0;i<rows.length;i++){
		cachenames[i]=rows[i].cacheName;
		param["cacheNameArray[" + i + "]"] = cachenames[i];
	}
	
	//alert(JSON.stringify(param));
	
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/cache/delete.do";
			
			$.ajax({
					   type: "POST",
					   url: url,
					   data:param,
					   success: function(result){
						   $.messager.alert('提示信息','记录删除成功！ ','info');
						   $('#'+queryResultTable).datagrid('reload');
						    },
						 error:function(result){
						    alertErrorMsgForEasyUi(result);
						    }

			});
		}
	});
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}

	
