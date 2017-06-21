var query_action = contextRootPath + "/um/umtuser/query.do";
var queryResultTable = "UmTUserTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
} ];
var page_contentColumnHeaders = [ [
		    {
		    	field : 'checkBoxNo',
		    	checkbox : true
		    },
		    {
			field : 'userCode',
			title : '用户代码',
			align : 'center',
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.usercode + '</a>';
			}
			
		    },
		    {
		    	field : 'userName',title : '用户名称',align : 'center'
		    }
		    	,	
		    {
		    	field : 'mobile',title : '手机号码',align : 'center'
		    }
			,	
			{
				field : 'userType',
				title : '用户类型',
				align : 'center',
				formatter : function(value, row, index) {
					if(value=='01'){
						return '内部用户';
					}else if(value=='02'){
						return '外部用户';
					}
				}
			}
				,	
			{
				field : 'userSort',
				title : '用户分类',
				align : 'center',
				formatter : function(value, row, index) {
					//'01':'其他','02':'地市交警','07':'高速交警','03':'地市辅警','08':'高速辅警','04':'查勘员','05':'理赔经理','06':'保协管理人员' 
					if(value=='01'){
						return '其他';
					}else if(value=='02'){
						return '地市交警';
					}else if(value=='03'){
						return '辅警';
					}else if(value=='04'){
						return '查勘员';
					}else if(value=='05'){
						return '理赔经理';
					}else if(value=='06'){
						return '保协管理人员';
					}else if(value=='07'){
						return '高速交警';
					}else if(value=='08'){
						return '高速辅警';
					}
				}
			}
			,
			{
				field : 'sex',
				title : '性别',
				align : 'center',
				sortable : true,
				formatter : function(value, row, index) {
					if(value=='1'){
						return '男';
					}else if(value=='2'){
						return '女';
					}
				}
			}
			,	
			{
				field : 'birthday',
				title : '生日',
				align : 'center',
				formatter : function(value, row, index) {
					if(value){
						//IE不能转"2009-09-09"的格式
						var time = new Date(value.replace(/-/g,'/'));
			            return time.toLocaleDateString();
						/*return value.subString(0,10);*/
					}
					return "";
				}
			}
			,	
	    	{
	    		field : 'identityNumber',
	    		title : '身份证号',
	    		align : 'center',
	    		sortable : true
	    	}
			,	
			{
				field : 'policeTeamName',
				title : '所属交警大队',
				align : 'center',
				sortable : true
			}
			,	
			{
				field : 'centerName',
				title : '所属受理点',
				align : 'center',
				sortable : true
			}
			,	
			{
				field : 'coName',
				title : '所属保险公司',
				align : 'center',
				sortable : true
			}
			,	
			{
				field : 'provinceDesc',
				title : '省',
				align : 'center',
				sortable : true
			}
			,	
			{
				field : 'cityDesc',
				title : '市',
				align : 'center',
				sortable : true
			}
			,	
		    {
		    	field : 'validStatus',
		    	title : '有效状态',
		    	align : 'center',
		    	formatter : function(value, row, index) {
		    		if(value=='1'){
		    			return '有效';
		    		}else if(value=='0'){
		    			return '无效';
		    		}
		    	}
		    }
		] ];


/* 查询 */
function executeQuery() {
	if(!$('#fm').form('validate')){
    	return false; 
    }else{
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	//alert(send_url);
	$('#'+queryResultTable).datagrid({
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar,
		singleSelect : true
	});
    }
}
/* 修改 */
function prepareUpdate() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/um/umtuser/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/um/umtuser/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/um/umtuser/view.do?" + params;
	editRecord(url);
}

/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/um/umtuser/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
						   var obj=eval("(" + result + ")");
						  if(obj.msg=='success'){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
						  }else{
							  $.messager.alert('错误信息',obj.msg,'error');
						  }
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

	function executeSaveUser(){
		if(!$('#userfm').form('validate')){
	    	return false; 
	    }else{
	    	var umTUserUserSort = document.getElementById('umTUser.userSort').value;
	    	debugger;
	    	if(umTUserUserSort == "01") {
	    		$('#surveyCenterId').combobox('clear');
	    	}
	    	// 交警：特殊控制必录
	    	if(umTUserUserSort == "02") {
	    		if($('#policeTeamId').combobox('getValue') == "" || $('#policeTeamId').combobox('getValue') == '0' || typeof($('#policeTeamId').combobox('getValue')) == 'undefined') {
	    			$.messager.alert('提示信息','请选择所属交警大队！','info');
	    			return ;
	    		}
	    		if(typeof($('#surveyCenterId').combobox('getValue')) == 'undefined') {
	    			$.messager.alert('提示信息','请选择所属受理点！','info');
	    			return ;
	    		}
	    		if($('#umTUserProvince').combobox('getValue') == null || $('#umTUserProvince').combobox('getValue') == "") {
	    			$.messager.alert('提示信息','请选择现住址省份！','info');
	    			return ;
	    		}
	    		if($('#umTUserCity').combobox('getValue') == null || $('#umTUserCity').combobox('getValue') == "") {
	    			$.messager.alert('提示信息','请选择现住址城市！','info');
	    			return ;
	    		}
	    	}
	    	
	    	if(umTUserUserSort == "07") {
	    		if($('#policeTeamId').combobox('getValue') == "" || $('#policeTeamId').combobox('getValue') == '0' || typeof($('#policeTeamId').combobox('getValue')) == 'undefined') {
	    			$.messager.alert('提示信息','请选择所属交警大队！','info');
	    			return ;
	    		}
	    		if($('#centerId').combobox('getValue') == '0') {
	    			$.messager.alert('提示信息','请选择所属受理点！','info');
	    			return ;
	    		}
	    		if($('#umTUserProvince').combobox('getValue') == null || $('#umTUserProvince').combobox('getValue') == "") {
	    			$.messager.alert('提示信息','请选择现住址省份！','info');
	    			return ;
	    		}
	    		if($('#umTUserCity').combobox('getValue') == null || $('#umTUserCity').combobox('getValue') == "") {
	    			$.messager.alert('提示信息','请选择现住址城市！','info');
	    			return ;
	    		}
	    	}
	    	if(umTUserUserSort == "03" || umTUserUserSort == "08") {
	    		if($('#centerId').combobox('getValue') == '0') {
	    			$.messager.alert('提示信息','请选择所属受理点！','info');
	    			return ;
	    		}
	    	}
	    	if(umTUserUserSort == "04") {
	    		if($('#surveyCenterId').combobox('getValue') == '0') {
	    			$.messager.alert('提示信息','请选择所属受理点！','info');
	    			return ;
	    		}
	    		if($('#coId').combobox('getValue') == '0') {
	    			$.messager.alert('提示信息','请选择所属保险公司！','info');
	    			return ;
	    		}
	    	}
	        if(umTUserUserSort == "05") {
	    		if($('#coId').combobox('getValue') == '0') {
	    			$.messager.alert('提示信息','请选择所属保险公司！','info');
	    			return ;
	    		}
	    	}

	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
					if($('#opreateType').val()=='add'){
						var url = contextRootPath+"/um/umtuser/add.do?"+$("#userfm").serialize();
						$.ajax({
							type:"POST",
							url: url,
							success:function(result){
								if(result == 'success'){
									$("#umTAccount\\.id\\.userCode").val($('#umTUser\\.id\\.userCode').val());
									$('#submitone').val('yes');
									$('#mytab').tabs('select',1);
								}else{
									return;
								}
							}
						});
					}else if($('#opreateType').val()=='update'){
						var url = contextRootPath+"/um/umtuser/update.do?"+$("#userfm").serialize();
						$.ajax({
							type:"POST",
							url: url,
							success:function(result){
								if(result == 'success'){
									$("#umTAccount\\.id\\.userCode").val($('#umTUser\\.id\\.userCode').val());
									$('#submitone').val('yes');
									$('#mytab').tabs('select',1);
								}else{
									return;
								}
							}
						});
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
	
	function checkIdNo() {
		var resultMsg = isCardID(document.getElementById('umTUser.identityNumber').value);
		if(resultMsg != "") {
			$.messager.alert("提示信息",resultMsg,"info");
			return ;
		}
	}
	
	function isCardID(sId){  
	    if(!(sId.length >=15 && sId.length <=18)){
	        return "身份证长度错误！";  
	    }
	    switch(sId.length){
	        case 15:
	            return "";
	            break;
	        case 18:
	              var iSum=0 ;  
	              if(!/^\d{17}(\d|x)$/i.test(sId)){
	                  return "身份证长度或格式错误！";  
	              }
	              sId=sId.replace(/x$/i,"a");  
	              sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));  
	              var d=new Date(sBirthday.replace(/-/g,"/")) ;  
	              if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
	                  return "身份证上的出生日期非法！";  
	              }
	              for(var i = 17;i>=0;i --) {
	                  iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
	              }
	              if(iSum%11!=1){
	                  return "身份证号非法！";  
	              }
	              return "";
	           break;
	       default:   
	           return ""; 
	    }
	 }
	
	
	function executeSaveAccount(){
		if(!$('#accountfm').form('validate')){
	    	return false;
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
					if($('#opreateType').val()=='add'){
						var password = $('#umTAccount\\.password').val();
						$("#umTAccount\\.password").val(SHA1(password));
						$("#accountfm").attr("action",contextRootPath+"/um/umtaccount/add.do");
				 		$("#accountfm").submit();
					}else if($('#opreateType').val()=='update'){
						var password = $('#umTAccount\\.password').val();
						if(password.length != 96) {
							$("#isChange").val("1");
							$("#umTAccount\\.password").val(SHA1(password));
						}else {
							$("#isChange").val("2");
						}
				 		$("#accountfm").attr("action",contextRootPath+"/um/umtaccount/update.do");
				 		$("#accountfm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }
	}
	
	var hideText = function(){
		if($('#policeTeamId').combobox('getValue') != '0'){
			$("#policeTeamId").combobox("setValue",'0');
		}
		if($('#coId').combobox('getValue') != '0'){
			$("#coId").combobox("setValue",'0');
		}
		if($('#centerId').combobox('getValue') != '0'){
			$("#centerId").combobox("setValue",'0');
		}
		$('#surveyCenterId').combobox('clear');
		
		$("#tdTeam").hide();
		$("#tdTeamText").hide();
		$("#tdCenter").hide();
		$("#tdCenterText").hide();
		$("#tdCompany").hide();
		$("#tdCompanyText").hide();
		$("#tdSurveyCenter").hide();
		$("#tdSurveyCenterText").hide();
	};
	$(function (){
		$('#policeTeamId').combobox({ 
			url: contextRootPath+'/qp/qptcommon/getTeamList.do?isHighway=' + "0", 
			valueField:'teamId', 
			textField:'teamName'
		});
	});
	function showTr(){
		var umTUserUserSort = document.getElementById('umTUser.userSort').value;
		hideText();
		//'01':'其他','02':'地市交警','07':'高速交警','03':'地市辅警','08':'高速辅警','04':'查勘员','05':'理赔经理','06':'保协管理人员' 
		if(umTUserUserSort=='02'){
			$("#tdTeam").show();
			$("#tdTeamText").show();
			$("#tdSurveyCenter").show();
			$("#tdSurveyCenterText").show();
			$('#policeTeamId').combobox('clear');
			
			var teamUrl = contextRootPath+'/qp/qptcommon/getTeamList.do?isHighway=' + "0";
			$('#policeTeamId').combobox(
					'reload',
					teamUrl);
			return;
		}
		
		if(umTUserUserSort=='07'){
			$("#tdTeam").show();
			$("#tdTeamText").show();
			$("#tdCenter").show();
			$("#tdCenterText").show();
			
			$('#policeTeamId').combobox('clear');
			$('#policeTeamId').combobox({ 
				url: contextRootPath+'/qp/qptcommon/getTeamList.do?isHighway=' + "1", 
				valueField:'teamId', 
				textField:'teamName'
			});
			return;
		}
		
		if(umTUserUserSort=='03' || umTUserUserSort=='08'){
			$("#tdCenter").show();
			$("#tdCenterText").show();
			return;
		}
		
		if(umTUserUserSort=='04'){
			$("#tdSurveyCenter").show();
			$("#tdSurveyCenterText").show();
			$("#tdCompany").show();
			$("#tdCompanyText").show();
			return;
		}
		
		if(umTUserUserSort=='05'){
			$("#tdCompany").show();
			$("#tdCompanyText").show();
			return;
		}
	};
	