
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

<script type="text/javascript">  

function hasClass(obj, cls) {  
    return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));  
}  
  
function addClass(obj, cls) {  
    if (!this.hasClass(obj, cls)) obj.className += " " + cls;  
}  
  
function removeClass(obj, cls) {  
    if (hasClass(obj, cls)) {  
        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');  
        obj.className = obj.className.replace(reg, ' ');  
    }  
}  
  
function toggleClass(obj,cls){  
    if(hasClass(obj,cls)){  
        removeClass(obj, cls);  
    }else{  
        addClass(obj, cls);  
    }  
}  
function cancelRefresh(){  
	$('.datagrid-view').css("height","36px");
    $('.datagrid-body').css("height","0px");
   var obj1 = $('#QpTICAccidentTable.datagrid-mask');  
    toggleClass(obj1,"datagrid-mask_repl");  
    var obj2 = $('#QpTICAccidentTable.datagrid-mask-msg');  
    toggleClass(obj2,"datagrid-mask-msg_repl");  
} 
</script> 
<style type="text/css">
    /*-- 消除grid屏闪问题 --//*/
    .datagrid-mask_repl{
     opacity:0;
      filter:alpha(opacity=0);
    }
    .datagrid-mask-msg_repl{
      opacity:0;
      filter:alpha(opacity=0);
    }
</style>
<script type="text/javascript">
var query_action = contextRootPath + "/qp/qpttpcase/query.do";
var queryResultTable = "QpTTPCaseTable";
var caseTpHandleStatus = null;

var page_toolBar = [ {
	text : '查勘处理完毕',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		guisunFinnish();
	}
} ];

$(document).ready(function(){
	setTpHandleTimeStart();
	$('#QpTICAccidentTable').datagrid({
		url : null,
		title : "关联的当事人信息",
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns:[[
				    {field : 'checkBoxNo',checkbox : true},
				    {field : 'acciId',hidden:true},
				    {field : 'caseId',hidden:true},
					{field : 'driverSexDesc',title : '性别',align : 'center',sortable : true},
					{field : 'driverName',title : '姓名',align : 'center',sortable : true},
					{field : 'driverIDNumber',title : '有效证件',align : 'center',sortable : true},
					{field : 'driverMobile',title : '联系电话',align : 'center',sortable : true},	
					{field : 'driverVehicleTypeDesc',title : '车辆类型',align : 'center',sortable : true},
					{field : 'driverVehicleNumber',title : '车牌号码',align : 'center',sortable : true},	
					{field : 'labelType',title : '厂牌类型',align : 'center',sortable : true},
					{field : 'insured',title : '被保险人',align : 'center',sortable : true},
					{field : 'driverAddress',title : '当事人地址',align : 'center',sortable : true},
					{field : 'driverLiabilityDesc',title : '当事人责任',align : 'center',sortable : true},		
					{field : 'driverLawDesc',title : '触犯的法律法规',align : 'center',sortable : true},
					{field : 'estimateLossSum',title : '估损金额',align : 'center',sortable : true},
					{field : 'fixedLossPrice',title : '定损价格',align : 'center',sortable : true},	
					{field :'operate',title:'操作',align:'center',sortable : true,
						formatter:function(value,row,index){
	   	 					var op = '<a name="edit_data" href="#" onclick="javascript:viewAccident(this)">&nbsp;查看&nbsp;</a>';
	   	 					if($("#businessType").val() == "1" && (caseTpHandleStatus == "2" || caseTpHandleStatus == "4")) {
	   	 						op += '|<a name="edit_data" href="#" onclick="estimateLoss(this)">&nbsp;查勘估损&nbsp;</a>';
	   	 					}
	   	 					if($("#businessType").val() == "1" && (caseTpHandleStatus == "2" || caseTpHandleStatus == "4")) {
	   	 						op += '|<a name="edit_data" href="#" onclick="javascript:photography(this)">&nbsp;影像定损&nbsp;</a>';
	   	 					}
	   		 				return op;
	   	 				}	
       				}
	        ]],
	    singleSelect:true
	});
	
	 if("${businessType}" != 1){
		 $("#QpTICAccidentTable").datagrid('hideColumn', 'estimateLossSum');
		 $("#QpTICAccidentTable").datagrid('hideColumn', 'fixedLossPrice');
	 }
	 if("${isReturn}" == '1'){//是通过返回过来的
		 executeQuery();
	 }
});

/* 验证车牌号 */
var isVehicleNumber = function (number) {
	// 不验证车牌号,只验证是否为"湘"
	// var reg = /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$/;
	// return reg.test(number);
	return number != "湘";
}

function fixWidth(percent){  
    return document.body.clientWidth * percent ; 
}
/* 查询 */
function executeQuery() {
	var checkIndex = -1;
	var driverVehicleNumber = $("#qpTTPCase\\.driverVehicleNumber").val();
	if (!isVehicleNumber(driverVehicleNumber)) {
		$("#qpTTPCase\\.driverVehicleNumber").val("");
	}
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		url : send_url,
		title : " 案件信息",
		width:'auto',              
        height:'auto', 
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns:[[
				    {field : 'checkBoxNo',width:fixWidth(0.03),checkbox : true},
				    {field : 'caseId',hidden:true},
					{field : 'caseSerialNo',title : '认字编号',align : 'center',width:fixWidth(0.07),sortable : true},
					/* {field : 'tpUserName',title : '警员姓名',align : 'center',sortable : true},
					{field : 'tpLoginId',title : '警员编号',align : 'center',sortable : true}, */
					/**{field : 'assistorId',title : '办案人员编号',align : 'center',sortable : true},*/
					{field : 'assistorName',title : '办案人员姓名',align : 'center',width:fixWidth(0.06),sortable : true},
					
					{field : 'caseTime',title : '事故发生时间',align : 'center',width:fixWidth(0.1),sortable : true},	
					{field : 'caseAddress',title : '事故地点',align : 'center',width:fixWidth(0.14),sortable : true},
					/*{field : 'caseWeatherDesc',title : '天气情况',align : 'center',sortable : true},	*/
					{field : 'tpHandleTime',title : '录入时间',align : 'center',width:fixWidth(0.1),sortable : true},
					{field : 'caseResult',title : '调解结果',align : 'center',width:fixWidth(0.1),sortable : true},
					{field : 'caseNotes',title : '事故详情',align : 'center',width:fixWidth(0.15),sortable : true},
					{field : 'tpHandleStatus',title : '案件状态',align : 'center',width:fixWidth(0.05),sortable : true,
						formatter:function(value,row,index){
       	 					if(value=='0'){
       	 						value = '待提交';
       	 					    return value;
       	 					}
	       	 				if(value=='1'){
	   	 						value = '待定责';
	   	 					    return value;
	   	 					}
		       	 			if(value=='2'){
			 					value = '待查勘';
			 					return value;
			 				}
			       	 		if(value=='3'){
								value = '已退回';
								return value;
							}
				       	 	if(value=='4'){
								value = '查勘处理中';
								return value;
							}
				       	    if(value=='5'){
								value = '已受理';
								return value;
							}
				       	 	if(value=='6'){
								value = '已注销';
								return value;
							}
			       	 		return value;
       	 				}		
					},
					{field :'operate',title:'操作',align:'center',width:fixWidth(0.19),sortable : true,
       	 				formatter:function(value,row,index){
       	 					var op = '';
       	 					if ("2" == $("#businessType").val() && "1" == row.tpHandleStatus) {
       	 						op += '<a name="edit_data" href="#" onclick="javascript:editrow(this)">&nbsp;审核&nbsp;</a>|';
       	 					} else if ("3" == $("#businessType").val()) {
   	 							op += '<a name="edit_data" href="#" onclick="javascript:editrow(this)">&nbsp;修改&nbsp;</a>|';
       	 						if ('${SessionUser.userCode}' == row.assistorId && (row.tpHandleStatus=='0'||row.tpHandleStatus=='1'||row.tpHandleStatus=='2')) {
       	 							op += '<a name="edit_data" href="#" onclick="javascript:delrow(this)">&nbsp;删除&nbsp;</a>|';
       	 						}
       	 					}
       	 					op += '<a name="edit_data" href="#" onclick="javascript:viewrow(this)">&nbsp;查看&nbsp;</a>';
       	 					return op;
       	 				}	
       				}
       				/* {field :'showPicture',title:'已上传图片数量',align:'center',sortable : true,
       					formatter:function(value,row,index){
       						var op='<a name="show_picture" href="#" onclick="javascript:showPicture(this)">&nbsp;查看图片&nbsp;</a>';
       		 				return op;
       					}
       				} */
	        ]],
		onSelect : function(rowIndex, rowData) {
			if(rowData == null){
				$('#QpTICAccidentTable').datagrid('loadData', { total: 0, rows: [] });  
				return;
			}

			if(checkIndex != rowData.caseId ){
				var accidentUrl = null;
				if(rowData != null && "undefined" != typeof(rowData) && "undefined" != rowData && rowData != "") {
					caseTpHandleStatus = rowData.tpHandleStatus;
					accidentUrl = contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=" + rowData.caseId;
				}else {
					//accidentUrl = contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=none";
					//$('#QpTICAccidentTable').datagrid('loadData',{total:0,rows:[]});
	 				var item = $('#QpTICAccidentTable').datagrid('getRows');  
		            if (item) {  
		                for (var i = item.length - 1; i >= 0; i--) {  
		                    var index = $('#QpTICAccidentTable').datagrid('getRowIndex', item[i]);  
		                    $('#QpTICAccidentTable').datagrid('deleteRow', index);  
		                }  
		            } 
		            cancelRefresh();
				}
				$('#QpTICAccidentTable').datagrid('options').url=accidentUrl;
				$('#QpTICAccidentTable').datagrid('reload');
				checkIndex = rowData.caseId;
			}
		},
		onLoadSuccess : function(data) {
			$('#'+queryResultTable).datagrid('selectRow',0);
			if(data.rows.length == 1){
				$('#'+queryResultTable).datagrid('resize',{height:'200',width:'auto'});
			}
		},
		toolbar : page_toolBar,
	    singleSelect:true
	});
	 if("${businessType}" != 1){
	     $('div.datagrid-toolbar').eq(0).hide();
	 }
}

function editrow (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='actionType' id='actionType' value='edit'/>");
	$("#fm").append("<input type='hidden' name='qpTTPCaseCaseId' id='qpTTPCaseCaseId' value='"+row.caseId+"'/>");
	$("#fm").append("<input type='hidden' name='type' id='type' value='shenhe'/>");
	
	var caseUrl = contextRootPath + "/qp/qpttpcase/prepareAdd.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

function delrow(obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/qp/qpttpcase/deleteByCreater.do?caseId=" + row.caseId;
			$.ajax({
				dataType:"json",
				type: "POST",
				url: url,
				success: function(result){
					$.messager.alert('提示消息', result.msg, 'info');
				},
				error:function (result) {
					$.messager.alert('提示消息', "网络错误", 'info');
				},
				complete:function () {
					$('#'+queryResultTable).datagrid('reload');					
				}
			});
		}
	});
}

function viewrow (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='actionType' id='actionType' value='view'/>");
	$("#fm").append("<input type='hidden' name='qpTTPCaseCaseId' id='qpTTPCaseCaseId' value='"+row.caseId+"'/>");
	
	var caseUrl = contextRootPath + "/qp/qpttpcase/prepareAdd.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

function viewAccident (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTICAccidentTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/qp/qpticaccident/prepareAdd.do?actionType=view&businessType=" + $("#businessType").val() + "&qpAcciId=" + row.acciId;
	$('#resUserSetWindow').window(
		{
			href: caseUrl,
			cache: false
		}
	);
	$('#resUserSetWindow').window('open');
}

function estimateLoss (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTICAccidentTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/qp/qpticaccident/prepareAssessorAdd.do?actionType=view&businessType=" + $("#businessType").val() + "&qpAcciId=" + row.acciId+"&cid="+row.caseId;
	$('#guSunWindow').window(
			{
				href: caseUrl,
				cache: false
			}
		);
	$('#guSunWindow').window('open');
}

function photography (obj) {
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTICAccidentTable').datagrid('getRows')[index];
	var caseUrl = contextRootPath + "/qp/qpticaccident/viewPhotography.do?actionType=view&businessType=" + $("#businessType").val() + "&qpAcciId=" + row.acciId + "&cid="+row.caseId+ "&groupId="+row.groupId;
//	$('#photographyWindow').window(
//			{
//				href: caseUrl,
//				cache: false
//			}
//		);
//	$('#photographyWindow').window('open');
	window.open(caseUrl);
}

/* function uploadPictures(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	var url="${ctx}/qp/qpticpicturegroup/prepareUploadPictureGroup.do?pictureType=" +$("#pictureType").val()+  "&groupId=" +$("#groupId").val() + "&caseId=" +row.caseId;
	window.open(url,"_blank",'height=600,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
} */
/* function showPicture(obj){
	var index=$(obj).parent().parent().parent().attr("datagrid-row-index");
	var row = $('#QpTTPCaseTable').datagrid('getRows')[index];
	$("#fm").append("<input type='hidden' name='groupId' id='groupId' value='"+row.groupId+"'/>"); 
	var caseUrl = contextRootPath + "/qp/qpticpicturegroup/viewPictureGroup.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit(); 
}*/
<%-- 关闭窗口 --%>
function closeUserWindow() {
	$('#resUserSetWindow').window('close');
}

<%-- 重置 --%>
function resetInput() {
	document.getElementById('qpTTPCase.caseId').value = "";
	document.getElementById('qpTTPCase.caseTimeStart').value = "";
	document.getElementById('qpTTPCase.caseTimeStart').value = "";
	document.getElementById('qpTTPCase.caseTimeEnd').value = "";
	document.getElementById('tpHandleTimeStart').value = "";
	document.getElementById('qpTTPCase.tpHandleTimeEnd').value = "";
	$("#qpTTPCaseDriverIDType").combobox("setValue",'0');
	document.getElementById('qpTTPCase.driverIDNumber').value = "";
	document.getElementById('qpTTPCase.driverName').value = "";
	document.getElementById('qpTTPCase.driverVehicleNumber').value = "";
	document.getElementById('qpTTPCase.policeName').value = "";
	document.getElementById('qpTTPCase.caseSerialNo').value = "";
	$("#qpTTPCaseCaseWeather").combobox("setValue",'0');
	//$("#qpTTPCaseCenterId").combobox("setValue",'0');
	$("#qpTTPCaseCoId").combobox("setValue",'0');
}

<%-- 导出Excel --%>
function downloadCase() {
	var driverVehicleNumber = $("#qpTTPCase\\.driverVehicleNumber").val();
	if (!isVehicleNumber(driverVehicleNumber)) {
		$("#qpTTPCase\\.driverVehicleNumber").val("");
	}
	var data = $("#fm").serialize();
	var caseUrl = contextRootPath + "/qp/qpttpcase/downloadCase.do";
	$("#fm").attr("action",caseUrl);
	$("#fm").submit();
}

<%-- 准备查询时，把时分设置成00:00:00 --%>
function setTpHandleTimeStart(){
	var tpHandleTime =  '${qpTTPCase.tpHandleTimeStart}';
	if(tpHandleTime != null && tpHandleTime != undefined && tpHandleTime != ''){
		if(tpHandleTime.split(" ")[1]!=="00:00:00"){
			tpHandleTime = tpHandleTime.split(" ")[0]+" 00:00:00";
			$("#tpHandleTimeStart").val(tpHandleTime);
		}
	}
}

function guisunFinnish(){
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	
	var count = 0;
	var count2 = 0;
	//查询案件的所有当事人信息  querySurvey
	var url =  contextRootPath + "/qp/qpticaccident/query.do?qpTICAccident.caseId=" + rows[0].caseId;
	$.ajax({  
        type:"POST",  
        url:url,  
        async: false,
        cache : false,
        data: "",
        dataType:"json",
        global:false,   
        success: function(result) {
        	$.each(result.rows, function(i, item) {
	        	if(item.surveyGroupId!=""&&item.surveyGroupId!=null&&item.estimateLossSum!=null && item.picCount>0){
	        		count++;
	    		}else{
	    			count2++;
	    		}

        	});
        }
	});
	
	if(count>0&&count2==0){
        //执行查勘完毕操作
        changeAccidentStatus(rows[0].caseId);
	}else{
        $.messager.alert('提示信息','请为当事人填写估损金额且上传照片！','info');
	}
}

function changeAccidentStatus(caseId){
	var url =  contextRootPath + "/qp/qpttpcase/updateQpTTPCasePic.do?qpTTPCase.caseId=" + caseId;
	$.ajax({  
        type:"POST",  
        url:url,  
        async: false,
        cache : false,
        data: "",
        dataType:"json",
        global:false,   
        success: function(result) {
        	$.messager.alert('提示信息','处理成功！','info');
        }
	});
}


</script>
</head>
<body>
 
	<form name="fm" id="fm">
	<input type="hidden" id="groupId" name="groupId" value="${groupId }" >
	<input type="hidden" id="pictureType" name="pictureType" value="${pictureType }" >
	<input type="hidden" id="businessType" name="businessType" value="${businessType }" >
		<div class="right_detail_top">
			<c:choose>
			<c:when test="${businessType eq '1'}">
				<h3>估损录入</h3>
			</c:when>
			<c:when test="${businessType eq '2'}">
				<h3>定责处理</h3>
			</c:when>
			<c:when test="${businessType eq '3'}">
				<h3>案件查询</h3>
			</c:when>	
			</c:choose>
		</div>
		   <div id="wrapper" style="margin-bottom:2px">
			  <div id="container">
				<table class="fix_table"  style="width: 100%;">
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">事发时间：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseTimeStart" id="qpTTPCase.caseTimeStart" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${qpTTPCase.caseTimeStart }"  >
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">至：</td>
						<td  width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseTimeEnd" id="qpTTPCase.caseTimeEnd" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${qpTTPCase.caseTimeEnd }"  >
						</td>
						<td width="10%" class="bgc_tt short"  align="right">受理日期：</td>
						<td width="10%">
						                                                                                                            
							<input class='input_w w_22' name="qpTTPCase.tpHandleTimeStart" id="tpHandleTimeStart" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${qpTTPCase.tpHandleTimeStart }"  >
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">至：</td>
						<td  width="10%" >
							<input class='input_w w_22' name="qpTTPCase.tpHandleTimeEnd" id="qpTTPCase.tpHandleTimeEnd" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${qpTTPCase.tpHandleTimeEnd }">
						</td>
					</tr>
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">证件类型：</td>
						<td width="10%">
							<select id="qpTTPCaseDriverIDType" name="qpTTPCase.driverIDType" editable="false" class="input_w w_22 easyui-combobox">
									<option value="0">请选择</option>
								<c:forEach var="identifyType" items="${identifyTypeList}">
									<option value="${identifyType.codeCode}" <c:if test="${identifyType.codeCode==qpTTPCase.driverIDType}">selected</c:if>>${identifyType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">证件号码：</td>
						<td  width="10%">
							<input class='input_w w_22' name="qpTTPCase.driverIDNumber" id="qpTTPCase.driverIDNumber" value="${qpTTPCase.driverIDNumber }">
						</td>
						<td width="10%" class="bgc_tt short"  align="right">当事人姓名：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.driverName" id="qpTTPCase.driverName" value="${qpTTPCase.driverName }">
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">车牌号：</td>
						<td  width="10%" >
							<input class='input_w w_22' name="qpTTPCase.driverVehicleNumber" id="qpTTPCase.driverVehicleNumber" value="${qpTTPCase.driverVehicleNumber}">
						</td>
					</tr>
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">案件编号：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseId" id="qpTTPCase.caseId" value="${qpTTPCase.caseId }">
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">认字编号：</td>
						<td  width="10%">
							<input class='input_w w_22' name="qpTTPCase.caseSerialNo" id="qpTTPCase.caseSerialNo" value="${qpTTPCase.caseSerialNo }">
						</td>
						<td width="10%" class="bgc_tt short"  align="right">天气：</td>
						<td width="10%">
							<select id="qpTTPCaseCaseWeather" name="qpTTPCase.caseWeather" editable="false"  class="input_w w_22 easyui-combobox">
									<option value="0">请选择</option>
								<c:forEach var="weather" items="${weatherList}">
									<option value="${weather.codeCode}" <c:if test="${weather.codeCode==qpTTPCase.caseWeather}">selected</c:if>>${weather.codeCName}</option>
								</c:forEach>
							</select>
						</td>
						<td  width="10%" class="bgc_tt short"  align="right">受理点：</td>
						<td  width="10%" >
							<select id="qpTTPCaseCenterId" name="qpTTPCase.centerId" editable="false" class="input_w w_22 easyui-combobox"  >
									
								<c:if test="${userSort=='02' || userSort=='04'}">
								    <c:forEach var="fastCenter" items="${fastCenterList}">
									   <option value="${fastCenter.centerId}" <c:if test="${fastCenter.centerId==qpTTPCase.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								    </c:forEach>
								</c:if>	
								<c:if test="${userSort!='02' && userSort!='04'}">
								<c:if test="${not empty qpTTPCase.centerId}">
									<script type="text/javascript">  
									     $("#qpTTPCaseCenterId").attr("readonly","readonly");
									</script>
								</c:if>
								    <option value="0">请选择</option> 
								    <c:forEach var="fastCenter" items="${fastCenterList}">
									   <option value="${fastCenter.id.centerId}" <c:if test="${fastCenter.id.centerId==qpTTPCase.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								    </c:forEach>
								</c:if>	
							</select>
						</td>
					</tr>
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">警员姓名：</td>
						<td width="10%">
							<input class='input_w w_22' name="qpTTPCase.policeName" id="qpTTPCase.policeName" value="${qpTTPCase.policeName }">
						</td>
						<td  width="10%" class="bgc_tt short" align="right">保险公司：</td>
						<td  width="10%" >
							<select id="qpTTPCaseCoId" name="qpTTPCase.coId" class="input_w w_22 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
									<option value="${qpTICCompany.coId}" <c:if test="${qpTICCompany.coId==qpTTPCase.coId}">selected</c:if>>${qpTICCompany.coName}</option>
								</c:forEach>
							</select>
						</td>
						
						<td width="10%" class="bgc_tt short"  align="right">案件状态：</td>
						<td width="10%">
							<select id="qpTTPCaseTpHandleStatus" name="qpTTPCase.tpHandleStatus" editable="false" class="input_w w_22 easyui-combobox">
									
									<option value="" selected >请选择</option>      
									<option value="0" <c:if test="${qpTTPCase.tpHandleStatus =='0'}"> selected </c:if> >待提交</option>
								    <option value="1" <c:if test="${qpTTPCase.tpHandleStatus =='1' || (qpTTPCase.tpHandleStatus == null &&  businessType =='2')}"> selected </c:if> >待定责</option>
							        <option value="2" <c:if test="${qpTTPCase.tpHandleStatus =='2' || (qpTTPCase.tpHandleStatus == null &&  businessType =='1') }"> selected </c:if>>待查勘</option>
							        <option value="4" <c:if test="${qpTTPCase.tpHandleStatus =='4'}"> selected </c:if> >查勘处理中</option> 
								    <option value="5" <c:if test="${qpTTPCase.tpHandleStatus =='5'}"> selected </c:if> >已受理</option>
								    <option value="3" <c:if test="${qpTTPCase.tpHandleStatus =='3'}"> selected </c:if>  >已退回</option>
								    <option value="6" <c:if test="${qpTTPCase.tpHandleStatus =='6'}"> selected </c:if> >已注销</option>
							</select>
						</td>
						
						<td  width="10%" align="right"></td>
						<td  width="10%"></td>
						<td  width="10%" align="right"></td>
						<td  width="10%"></td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="button" id="addButton"
								onclick="resetInput();" class="button_ty" value="重置">
							<input type="button" id="addButton"
								onclick="downloadCase();" class="button_ty" value="导出Excel">	
						</td>
					</tr>
				</table>
			</div>
			</div>
	</form>
	<br>
	<table id="QpTTPCaseTable"></table>
	<br>
	<table id="QpTICAccidentTable"></table>
	
	<div id="resUserSetWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="当事人基本信息"
		style="width:1000px;height:650px;top:100px;"></div>
		
    <div id="guSunWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="查勘估损"
		style="width:1000px;height:550px;top:200px;"></div>		
		
</body>
</body>
</html>