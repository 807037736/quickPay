/**
 * @author      limingguo03
 * @date        2013-10-31 17:15
 * @description 用户配置送修码（javascript）
 * @version     1.0
 */

/* 日期面板的行为 */
$('#validDate').datebox({
	editable : false
});

/* 页查询结果 */
var monopoly_toolBar = [
    {text:'删除推荐送修码',iconCls:'icon-remove',handler:monopolyRemove},
    {text:'设置为默认送修码',iconCls:'icon-edit',handler:monopolyEdit}
];

var monopoly_contentColumnHeaders = [[
	{field : 'checkBoxNo',	checkbox : true},
	{field:'monopolyCode',title:'推荐送修码',align:'center',sortable:true},	
	{field:'monopolyName',title:'修理厂名称',align:'center',sortable:true},	
	{field:'flag',title:'是否默认',align:'center',sortable:true,
	 formatter:function(value, row, index){
		if(value == 1) return "是";
		else if(value == 0) return "否";
	}}
]];


/*$('#tab').tabs({
	onSelect : function(title){
		if(title == "车行推荐送修码") {
			var query_action = contextRootPath + "/qt/myinfo/monopoly/queryUserMonopoly.do?userCode="+$("#ctitle > h3").text().substr(0, 8);
			$('#monopolyResults').datagrid({
				url          : query_action,
				rownumbers   : true,
				nowrap       : true,
				striped      : true,
				remoteSort   : false,
				pageNumber   : 1,
				pageList     : [10,20,30],
				pagination   : true,
				singleSelect : true,
				sortName     : 'flag',
				sortOrder    : 'desc',
				fitColumns   : true,
				columns      : monopoly_contentColumnHeaders,
				toolbar      : monopoly_toolBar
			});
		}else if(title == "报价信息配置") {
			//operatorQuery();
		}
	}
});*/

/* 增加送修码 */
function monopolyAdd(uc){
	//alert($("#ctitle > h3").text().indexOf('-'));

	$('#monopolyResults').datagrid({
		url          : contextRootPath+"/qt/myinfo/monopoly/addUserMonopoly.do?"+$("#fm_monopoly").serialize()+"&userCode="+uc,
		nowrap       : true,
		striped      : true,
		remoteSort   : false,
		pageNumber   : 1,
		pageList     : [10,20,30],
		pagination   : true,
		singleSelect : true,
		fitColumns	 : true,
		columns      : monopoly_contentColumnHeaders,
		toolbar      : monopoly_toolBar
	});
}

/* 点击操作中修改按钮 */
function monopolyEdit(){
	var row = $('#monopolyResults').datagrid('getSelected');
	var msg = "";
	if(row.flag == 0) msg = "设置为默认送修码？";
	else if(row.flag == 1) msg = "取消该默认送修码？";
	$.messager.confirm("", msg, function(choose){
		if(choose){
			var send_url = contextRootPath + "/qt/myinfo/monopoly/editUserMonopoly.do?qtUserMonopoly.id.monopolyCode=";
			send_url = encodeURI(send_url + row.monopolyCode + "&qtUserMonopoly.flag=" + row.flag + "&qtUserMonopoly.monopolyName=" + row.monopolyName+"&userCode="+$("#ctitle > h3").text().substr(0, $("#ctitle > h3").text().indexOf('-')));
			//alert(send_url);
			$('#monopolyResults').datagrid({
				url          : send_url,
				nowrap       : true,
				striped      : true,
				remoteSort   : false,
				pageNumber   : 1,
				pageList     : [10,20,30],
				pagination   : true,
				singleSelect : true,
				fitColumns	 : true,
				columns      : monopoly_contentColumnHeaders,
				toolbar      : monopoly_toolBar
			});
		}
	});
}

/* 点击操作中删除按钮 */
function monopolyRemove(){
	var row = $('#monopolyResults').datagrid('getSelected');
	$.messager.confirm("", "确定要删除该推荐送修码？", function(choose){
		if(choose){
			var send_url = contextRootPath + "/qt/myinfo/monopoly/removeUserMonopoly.do?userCode="+$("#ctitle > h3").text().substr(0, $("#ctitle > h3").text().indexOf('-'))+"&qtUserMonopoly.id.monopolyCode=";
			send_url = send_url + row.monopolyCode;
			$('#monopolyResults').datagrid({
				url          : send_url,
				nowrap       : true,
				striped      : true,
				remoteSort   : false,
				pageNumber   : 1,
				pageList     : [10,20,30],
				pagination   : true,
				singleSelect : true,
				fitColumns	 : true,
				columns      : monopoly_contentColumnHeaders,
				toolbar      : monopoly_toolBar
			});
		}
	});
}

function operatorQuery() {
	var query_action = contextRootPath + "/qt/myinfo/operator/query.do";
	$('#operatorResults').datagrid({
		url          : query_action,
		rownumbers   : true,
		nowrap       : true,
		striped      : true,
		remoteSort   : false,
		pageNumber   : 1,
		pageList     : [10,20,30],
		pagination   : true,
		singleSelect : true,
		sortName     : 'flag',
		sortOrder    : 'desc',
		columns      : operator_contentColumnHeaders,
		toolbar      : operator_toolBar
	});
}

function operatorAdd(){
	var query_action = contextRootPath + "/qt/myinfo/operator/add.do";
	var data = $("#fm_operator").serialize();
	var send_url = query_action + "?" + data;
	$('#operatorResults').datagrid({
		url          : send_url,
		rownumbers   : true,
		nowrap       : true,
		striped      : true,
		remoteSort   : false,
		pageNumber   : 1,
		pageList     : [10,20,30],
		pagination   : true,
		singleSelect : true,
		columns      : operator_contentColumnHeaders,
		toolbar      : operator_toolBar
	});
	operatorReset();
}

function operatorRemove() {
	var row = $('#operatorResults').datagrid('getSelected');
	$.messager.confirm("", "确定要删除该报价信息吗？", function(c1) {
		if(c1){
			if(1 == row.flag) {
				$.messager.confirm("", "这条报价信息是默认信息", function(c2){
					if(c2) {
						var send_url = contextRootPath + "/qt/myinfo/operator/remove.do?qtOperateConfig.id=";
						send_url = send_url + row.id;
						$('#operatorResults').datagrid({
							url          : send_url,
							rownumbers   : true,
							nowrap       : true,
							striped      : true,
							remoteSort   : false,
							pageNumber   : 1,
							pageList     : [10,20,30],
							pagination   : true,
							singleSelect : true,
							columns      : operator_contentColumnHeaders,
							toolbar      : operator_toolBar
						});
						operatorReset();
					}
				});
			} else {
				var send_url = contextRootPath + "/qt/myinfo/operator/remove.do?qtOperateConfig.id=";
				send_url = send_url + row.id;
				$('#operatorResults').datagrid({
					url          : send_url,
					rownumbers   : true,
					nowrap       : true,
					striped      : true,
					remoteSort   : false,
					pageNumber   : 1,
					pageList     : [10,20,30],
					pagination   : true,
					singleSelect : true,
					columns      : operator_contentColumnHeaders,
					toolbar      : operator_toolBar
				});
				operatorReset();
			}
			
		}
	});

}

function operatorReset(){
	$('#qtOperateConfig\\.id').val('');
	$('#qtOperateConfig\\.comCode').val('');
	$('#qtOperateConfig\\.comCName').val('');
	$('#qtOperateConfig\\.handler1Code').val('');
	$('#qtOperateConfig\\.handler1Name').val('');
	$('#qtOperateConfig\\.handlerCode').val('');
	$('#qtOperateConfig\\.handlerName').val('');
	$('#qtOperateConfig\\.businessNature').val('');
	$('#qtOperateConfig\\.businessNatureName').val('');
	$('#qtOperateConfig\\.agentCode').val('');
	$('#qtOperateConfig\\.agentName').val('');
	$('#qtOperateConfig\\.operateCode').val('');
	$('#qtOperateConfig\\.operateName').val('');
	$('#flag').removeAttr('checked');
	$('#validStatus').removeAttr('checked');
}

$('#operatorResults').datagrid({
	onSelect : function(index, row){
		$('#qtOperateConfig\\.handler1Code').val(row.handler1Code);
		$('#qtOperateConfig\\.handler1Name').val(row.handler1Name);
		$('#qtOperateConfig\\.handlerCode').val(row.handlerCode);
		$('#qtOperateConfig\\.handlerName').val(row.handlerName);
		$('#qtOperateConfig\\.operateCode').val(row.operateCode);
		$('#qtOperateConfig\\.operateName').val(row.operateName);
		$('#qtOperateConfig\\.comCode').val(row.comCode);
		$('#qtOperateConfig\\.comCName').val(row.comCName);
		$('#qtOperateConfig\\.riskCode').val(row.riskCode);
		$('#qtOperateConfig\\.businessNature').val(row.businessNature);
		$('#qtOperateConfig\\.businessNatureName').val(row.businessNatureName);
		$('#qtOperateConfig\\.agentCode').val(row.agentCode);
		$('#qtOperateConfig\\.agentName').val(row.agentName);
		$('#qtOperateConfig\\.id').val(row.id);
		if(row.flag == 0) $('#flag').removeAttr("checked");
		else if(row.flag == 1) $('#flag').attr("checked","checked");
		if(row.validStatus == 0) $('#validStatus').removeAttr("checked");
		else if(row.validStatus == 1) $('#validStatus').attr("checked", "checked");
	}
});