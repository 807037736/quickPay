var queryWX_action = contextRootPath + "/um/umtuserbound/queryWX.do";
var queryCK_action = contextRootPath + "/um/umtuserbound/queryCK.do";
var wxTable = "wxUserTable";
var ckTable = "ckUserTable";

var WXpage_contentColumnHeaders = [[
	{
		field : 'boundId',
		hidden:true
	}, 
	{
		field : 'isBound',
		hidden:true
	},
    {
    	field : 'wxUserCode',
    	hidden:true
    },
    {
    	field : 'wxUserName',
    	title : '微信用户姓名',
    	align : 'center',
    	sortable : true,
    	width: 120
    },
	{
    	field : 'sex',
    	title : '性别',
    	align : 'center',
    	sortable : true,
    	width: 80,
    	formatter : function(value,row,index){
			if(value=='1'){
				return '男';
			}else if(value=='0'){
				return '女';
			}else{
				return '未知';
			}
		}
    },
	{
    	field : 'mobile',
    	title : '手机号码',
    	align : 'center',
    	width: 160,
    	sortable : true},	
	{
		field : 'identityNumber',
		title : '身份证号码',
		align : 'center',
		width: 180,
		sortable : true
	},
	{
		field : 'licenseNo',
		title : '车牌号码',
		align : 'center',
		width: 100,
		sortable : true
	},
	{
		field : 'ckUserName',
		title : '已绑定查勘员',
		align : 'center',
		sortable : true,
		width: 100,
		formatter : function(value,row,index){
			if(value==''){
				return '暂无绑定';
			}else{
				return value;
			}
		}
	},
	{
		field : 'ckUserCode',
		title:'操作',
		align:'center',
		width: 140,
		sortable : true,
		formatter:function(value,row,index){
			var op = '';
			if (value=='') {
				op += '<a name="bound" href="#" onclick="javascript:prepareQueryCK(this)">&nbsp;绑定查勘员&nbsp;</a>';
			} else{
      			op += '<a name="relieve" href="#" onclick="javascript:remove(this)">&nbsp;解除绑定&nbsp;</a>|';
				op += '<a name="relieve" href="#" onclick="javascript:prepareUpdate(this)">&nbsp;修改绑定&nbsp;</a>';
			}
				return op;
		}	
	}
	]];



var CKpage_contentColumnHeaders = 
	[[
	  {field : 'boundId',	hidden:true}, 
	  {field : 'wxUserName',hidden:true},
//	  {field : 'userCode',hidden:true},
	  {field : 'userCode',title : 'userCode',align : 'center',width: 100,sortable : true},
	  {field : 'wxUserCode',hidden:true},
	  {field : 'isBound',hidden:true},
	  {field : 'userName',title : '查勘员姓名',align : 'center',width: 100,sortable : true},
      {field : 'sex',title : '性别',align : 'center',width: 60,sortable : true,
              formatter : function(value,row,index){
		              			if(value=='1'){
		              				return '男';
		              			}else if(value=='0'){
		              				return '女';
		              			}else{
		              				return '未知';
		              			}
		              		}
		                  },
      {field : 'mobile',title : '手机号码',align : 'center',width: 100,sortable : true},	
      {field : 'identityNumber',title : '身份证号码',align : 'center',width: 120,sortable : true},
      {field : 'licenseNo',title : '车牌号码',align : 'center',width: 80,sortable : true},
      {field : 'boundCount',title : '已绑定微信个数',align : 'center',width: 80,sortable : true,
				formatter : function(value,row,index){
					if(value==''){
						return '暂无绑定';
						}else{
							return value;
						}
					}
	  },
      {field : 'ckUserCode',title:'操作',align:'center',width: 120,sortable : true,
          		formatter:function(value,row,index){
          			var op = '';
          			if (value == '') {
          				op += '<a name="bound" href="#" onclick="javascript:boundCK(this)">&nbsp;绑定该查勘员&nbsp;</a>';
          			}else if(value == '-1'){
          				op += "<font color=red>已绑定该查勘员 </font>";
          			}else {
          				op += '<a name="bound" href="#" onclick="javascript:update(this)">&nbsp;改绑为此查勘员&nbsp;</a>';
          			}
          				return op;
          		}	
      }
     ]];




/* 查询微信用户 */
function executeQueryWX() {
//	if ($("#userName").val()=='' && $("#mobile").val()=='' && $("#identityNumber").val()=='' && $("#licenseNo").val()==''){
//    	$.messager.alert('提示消息', "至少输入一个查询条件！", 'info');
//        return false;
//    }
	var data = $("#wxfm").serialize();
	var send_url = queryWX_action + "?" + data;
	$('#'+wxTable).datagrid({
		title : "微信用户查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		singleSelect: true,
		scrollbarSize : 0 ,
		fitColumns: 'false',
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns : WXpage_contentColumnHeaders
	});
	
}

/* 查询 查勘员用户 */
function executeQueryCK() {
//	if ($("#userName").val()=='' && $("#mobile").val()=='' && $("#identityNumber").val()=='' && $("#licenseNo").val()==''){
//    	$.messager.alert('提示消息', "至少输入一个查询条件！", 'info');
//        return false;
//    }
	var data = $("#ckfm").serialize();
	var send_url = queryCK_action + "?" + data ;
	$('#'+ckTable).datagrid({
		title : "查勘员查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		singleSelect: true,
		scrollbarSize : 0 ,
		fitColumns: 'false',
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns : CKpage_contentColumnHeaders
	});
	
}

function prepareQueryCK(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#wxUserTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/um/umtuserbound/prepareQueryCK.do?umTUserBound.wxUserCode="+row.wxUserCode;
	$('#ckBoundWindow').window(
			{
				href: caseUrl,
				cache: false
			}
		);
	$('#ckBoundWindow').window('open');
}

function boundCK(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#ckUserTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/um/umtuserbound/addUmTUserBound.do?umTUserBound.ckUserCode=" 
				  + row.userCode + "&umTUserBound.wxUserCode=" + row.wxUserCode;
	 $.ajax({
		   type: "POST",
		   url: caseUrl,
		   success: function(result){
				  $.messager.alert('提示信息', '绑定成功', 'info', function(){
					  closeCkBoundWindow();
					  $('#wxUserTable').datagrid('reload');
				  })
		   },
		   error:function(result){
			   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
		   }
	 });
}

function remove(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#wxUserTable').datagrid('getRows')[index];
	$.messager.confirm('提示', '是否确认解除绑定?', function(r){
		if (r){
			var url = contextRootPath+"/um/umtuserbound/delete.do?umTUserBound.boundId=" + row.boundId;
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','解绑成功！	','info');
							  $('#wxUserTable').datagrid('reload');
					   },
					   error:function(result){
						   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
					   }
			});
		}
	});
}

function prepareUpdate(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#wxUserTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/um/umtuserbound/prepareUpdate.do?umTUserBound.boundId="
				  +row.boundId+"&umTUserBound.ckUserCode="+row.ckUserCode+"&umTUserBound.wxUserCode="+row.wxUserCode;
	$('#ckBoundWindow').window(
			{
				href: caseUrl,
				cache: false
			}
		);
	$('#ckBoundWindow').window('open');
}

function update(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#ckUserTable').datagrid('getRows')[index];
	var ckfmData = $("#ckfm").serialize();
	var caseUrl = contextRootPath + "/um/umtuserbound/update.do?umTUserBound.wxUserCode=" 
				  +$('#wxUserCode').val()+"&umTUserBound.ckUserCode="+row.userCode+"&umTUserBound.boundId="+$('#boundId').val();
	var resultUrl = contextRootPath + "/um/umtuserbound/prepareQueryWX.do";
	$.ajax({
		   type: "POST",
		   url: caseUrl,
		   success: function(result){
				  $.messager.alert('提示信息', '改绑成功', 'info', function(){
					  closeCkBoundWindow();
					  $('#wxUserTable').datagrid('reload');
				  })
		   },
		   error:function(result){
			   $.messager.alert('温馨提示','系统维护中，请稍后再试。！	','info');
		   }
	 });
}

/*关闭窗口*/
function closeCkBoundWindow() {
	$('#ckBoundWindow').window('close');
}

