<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

<script language="javascript"
	src="${ctx}/pages/qp/qpttpcase/QpTTPCase.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=iLCaxMjThCmlliN78uu3tbjv"></script>

<script type="text/javascript">
var index  = 0;
<%-- 页面加载触发  --%>
	$(document)
			.ready(
					function() {
						$('#addDriverInfo').bind('click', function() {
							openResUserSetWindow();
						});
						
						//var data = $("#fm").serialize();
						//var urlHref = '${ctx}/qp/qpticaccident/prepareAdd.do?' + data;
					
						$('#addTab').bind('click', function() {
							acciViewTab();
						});
						$('#tt').tabs({ 
								width:$("#tt").parent().width(),
								height:$("#tt").parent().height(),
								onBeforeClose:function(title){ 
					    			if (confirm('是否确认删除？'+ title)) {
					    				/* var tab = $('#tt').tabs('getSelected');
					    		        var url = tab.find('iframe')[0].src;
					    		        var regexpParam = /\??([\w\d%]+)=([\w\d%]*)&?/g; //分离参数的正则表达式
					    		        var paramMap = {};
					    		        var ret;
					    		        while((ret = regexpParam.exec(url)) != null) {
					    		             paramMap[ret[1]] = ret[2];
					    		        }
					    		        var qpAcciId =  paramMap.qpAcciId; */
					    				
					    				var indexSelect = $('#tt').tabs('getTabIndex',$('#tt').tabs('getTab',title));
					    				var acciId = "";
					    				if($(window.frames["iframename_"+indexSelect]).length!=0){
					    					 acciId = $(window.frames["iframename_"+indexSelect].document).find("#addFm").find("#qpTICAccidentAcciId").val();
					    				}
					    		     	var  qpAcciId = acciId;
					    		        if(qpAcciId){
					    		            var url = contextRootPath + "/qp/qpticaccident/deleteTICAccident.do";
					    		    		var data = "qpAcciId=" + qpAcciId;
					    		    				$.ajax({
					    		    					type : "POST",
					    		    					url : url,
					    		    					async : true,
					    		    					cache : false, //缓存
					    		    					data : data,
					    		    					global : false,
					    		    					success : function(result) {
					    		    						var obj = eval("(" + result + ")");
					    		    						if ("Y" == obj.flag) {
					    		    				           // var tabName = $('#tt').tabs('getTabIndex',tab);
					    		    				           // $('#tt').tabs('close',tabName);
					    		    							$.messager.alert('提示信息', '删除当事人信息成功！', 'info');
					    		    						} else {
					    		    							alert(obj.content);
					    		    						}
					    		    					},
					    		    					error : function(XMLHttpRequest, textStatus, errorThrown) {
					    		    						$.messager.alert('提示信息', '提交后台发生错误，请重试！', 'info');
					    		    					}
					    		    				});
					    		        }
					    		        
					    				return true;
					    			}else{
					    				return false;
					    			}
								},
								onClose:function(title){
									//处理当事人总数
				    		        index--;
									//var indexSelect = $('#tt').tabs('getTabIndex',$('#tt').tabs('getTab',title));
									//重置title
									for(var k=0;k<index;k++){
										//alert($('#tt').tabs('getTab',k).panel('options').title+ '=========' +getTitleByIndex(k));
										$('#tt').tabs('getTab',k).panel("setTitle",getTitleByIndex(k));
										//var titles = $('#tt').find('.tabs-header:first').find('.tabs-title');
										//titles.eq(k).text(getTitleByIndex(k));
				    					$('#tt').find("ul li:nth-child("+(k+1)+") a:first-child span:first-child").html(getTitleByIndex(k));
				    					
				    					//alert($('#tt').tabs('getTab',k).find("span.tabs-title").val());
									}
									var j=0;
									//初始化iframe 的序号
									$('#tt').find("iframe").each(function(e){
										//alert($(this).attr("name")+"------>"+"iframename_"+j);
										$(this).attr("name","iframename_"+j);
										j++;
									});
									$("#tt").tabs("select", 0);
								}
						}); 
					
						
						if ("add" == '${actionType}') {
							 window.setTimeout(function(){ 
								 acciViewTab();
								 acciViewTab();
						     },500); 
						}
						window.setTimeout(function(){ 
							$("#tt").tabs("select", 0);
					    },500); 
						
						$('#qpTTPCaseCaseWeather').combobox();

						$('#qpTTPCaseCaseProvince')
								.combobox(
										{
											url : '${ctx}/qp/qptcommon/getProvinceList.do',
											valueField : 'provId',
											textField : 'provName',
											onSelect : function() {
												$('#qpTTPCaseCaseCity')
														.combobox('clear');
												$('#qpTTPCaseCaseDistrict')
														.combobox('clear');
											
												$('#qpTTPCaseCaseCity')
														.combobox(
																'reload',
																'${ctx}/qp/qptcommon/getCityList.do?provId='
																		+ $(
																				'#qpTTPCaseCaseProvince')
																				.combobox(
																						'getValue'));
											},
											onUnselect : function() {
												$('#qpTTPCaseCaseCity')
														.combobox('clear');
												$('#qpTTPCaseCaseDistrict')
														.combobox('clear');
												
												$('#qpTTPCaseCaseCity')
														.combobox(
																'reload',
																'${ctx}/qp/qptcommon/getCityList.do?provId='
																		+ $(
																				'#qpTTPCaseCaseProvince')
																				.combobox(
																						'getValue'));
											}
										});

						$('#qpTTPCaseCaseCity')
								.combobox(
										{
											url : '${ctx}/qp/qptcommon/getCityList.do',
											valueField : 'cityId',
											textField : 'cityName',
											onSelect : function() {
												$('#qpTTPCaseCaseDistrict')
														.combobox('clear');
												
												$('#qpTTPCaseCaseDistrict')
														.combobox(
																'reload',
																'${ctx}/qp/qptcommon/getDistrictList.do?cityId='
																		+ $(
																				'#qpTTPCaseCaseCity')
																				.combobox(
																						'getValue'));
											},
											onUnselect : function() {
												$('#qpTTPCaseCaseDistrict')
														.combobox('clear');
												
												$('#qpTTPCaseCaseDistrict')
														.combobox(
																'reload',
																'${ctx}/qp/qptcommon/getDistrictList.do?cityId='
																		+ $(
																				'#qpTTPCaseCaseCity')
																				.combobox(
																						'getValue'));
											}
										});
						

						$('#qpTTPCaseCaseDistrict')
								.combobox(
										{
											valueField : 'districtId',
											textField : 'districtName',
											onSelect : function() {
												displayMap();
											}
										});

					

						$("#qpTTPCaseCaseNotesText").combobox({
							onChange : function(n, o) {
								$("#qpTTPCaseCaseNotes").attr("value", n);
							}
						});

						$("#qpTTPCaseCaseResultText").combobox({
							onChange : function(n, o) {
								$("#qpTTPCaseCaseResult").attr("value", n);
							}
						});
						
						$("#qpTTPCaseCaseProvince").combobox("setValue",
						'${qpTTPCase.caseProvince}');
				 $('#qpTTPCaseCaseCity').combobox(
						'reload',
						'${ctx}/qp/qptcommon/getCityList.do?provId='
								+ $('#qpTTPCaseCaseProvince')
										.combobox('getValue'));
				$("#qpTTPCaseCaseCity").combobox("setValue",
						'${qpTTPCase.caseCity}');
				$('#qpTTPCaseCaseDistrict').combobox(
						'reload',
						'${ctx}/qp/qptcommon/getDistrictList.do?cityId='
								+ $('#qpTTPCaseCaseCity').combobox(
										'getValue'));
				$("#qpTTPCaseCaseDistrict").combobox("setValue",
						'${qpTTPCase.caseDistrict}');

				$("#printMethod").combobox({
							onChange : function(n, o) {
							 $("#printTemp").attr("value", n);
							}
						});
				//根据快处中心带出办案民警
				$("#qpTTPCaseCenterId").combobox({
					onChange : function(n, o) {
						if($('#qpTTPCaseCenterId'+n).val() == '1'){
							$('#handlePoliceTd').show();
							$('#handlePoliceTh').show();
						}else{
							$('#handlePoliceTd').hide();
							$('#handlePoliceTh').hide();
						}
						
						$('#handlePoliceNO').combobox({
							url : '${ctx}/qp/qptcomhandlepolice/getListByCenterId.do?centerId='+n,
							valueField : 'handlePoliceNO',
							textField : 'handlePoliceName'
						}); 
					}
				});
				
				<%--		让点击combobox就弹出下拉框--%>
			
				$(".combo").click(function(){
					$(this).prev().combobox("showPanel");
					});

						if ("view" == '${actionType}') {
							setReadonlyOfAllInput("fm");
							$("#printCaseView").hide();
							//document.getElementById("printCase").style.display = "";
						}
					});
<%-- 打开增加关系人对话框  --%>
	function openResUserSetWindow() {

		if (!$('#fm').form('validate')) {
			return;
		}
		
		if ($('#qpTTPCaseCenterId').combobox('getValue') == '0') {
			$.messager.alert('提示信息', '请选择快处中心！', 'info');
			return;
		}
		var province = $('#qpTTPCaseCaseProvince').combobox('getText');
		var city = $('#qpTTPCaseCaseCity').combobox('getText');
		var district = $('#qpTTPCaseCaseDistrict').combobox('getText');
        var street = $("#qpTTPCaseCaseStreet").val();
        var detailAddress = province + city + district + street;
        $("#case_street").val(detailAddress);

		var data = $("#fm").serialize();
		$('#resUserSetWindow').window({
			href : '${ctx}/qp/qpticaccident/prepareAdd.do?' + data,
			cache : false
		});
		$('#resUserSetWindow').height(600);
		$('#resUserSetWindow').window('open');
	}
<%-- 打开编辑关系人对话框  --%>
	function openEditUserSetWindow(field) {
		if (!$('#fm').form('validate')) {
			return;
		}

		if ($('#qpTTPCaseCenterId').combobox('getValue') == '0') {
			$.messager.alert('提示信息', '请选择快处中心！', 'info');
			return;
		}

		var order = getElementOrder(field) - 1;
		var qpAcciId = document.getElementsByName('userAcciId1')[order].value;
		var data = $("#fm").serialize();
		$('#resUserSetWindow').window(
				{
					href : '${ctx}/qp/qpticaccident/prepareAdd.do?qpAcciId='
							+ qpAcciId + '&' + data,
					cache : false
				});
		$('#resUserSetWindow').height(600);
		$('#resUserSetWindow').window('open');
	}
	
<%-- 打开查看关系人对话框  --%>
	function openViewUserSetWindow(field) {
		var order = getElementOrder(field) - 1;
		var qpAcciId = document.getElementsByName('userAcciId1')[order].value;
		$('#resUserSetWindow').window(
				{
					href : '${ctx}/qp/qpticaccident/prepareAdd.do?qpAcciId='
							+ qpAcciId + '&actionType=view'+'&businessType=${businessType }',
					cache : false
				});
		$('#resUserSetWindow').height(600);
		$('#resUserSetWindow').window('open');

	}
<%-- 删除关系人  --%>
	function deleteRowUser(field) {
		var order = getElementOrder(field) - 1;
		var qpAcciId = document.getElementsByName('userAcciId1')[order].value;

		var url = contextRootPath + "/qp/qpticaccident/deleteTICAccident.do";
		var data = "qpAcciId=" + qpAcciId;

		$.messager.confirm('提示', '是否确认删除？', function(r) {
			if (r) {
				$.ajax({
					type : "POST",
					url : url,
					async : true,
					cache : false, //缓存
					data : data,
					global : false,
					success : function(result) {
						var obj = eval("(" + result + ")");
						if ("Y" == obj.flag) {
							deleteRowInTable(field, 'userPower', 1, 1);
							$.messager.alert('提示信息', '删除当事人信息成功！', 'info');
						} else {
							alert(obj.content);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.alert('提示信息', '提交后台发生错误，请重试！', 'info');
					}
				});
			}
		});
	}
<%-- 位置  --%>
	function getElementOrder(field) {
		var i = 0;
		var order = 0;
		var elements = document.getElementsByName(field.name);
		var elementsCount = elements.length;
		for (i = 0; i < elementsCount; i++) {
			order++;
			if (elements[i] == field) {
				break;
			}
		}
		return order;
	}
	
<%-- 增加当事人行  --%>
	$(function() {
		$.messager.defaults = {
			ok : "确定",
			cancel : "取消"
		};
	});
	
<%-- 保存案件操作  handleType 操作类型，0-暂存；1-提交给交警，2-审核通过，处理完毕，3-交警暂存，4-打回给协助人员--%>
	function saveAction(handleType) {
		if (!$('#fm').form('validate')) {
			return;
		}
		$("#tpHandleStatus").val(handleType);

		if ($('#qpTTPCaseCenterId').combobox('getValue') == '0') {
			$.messager.alert('提示信息', '请选择快处中心！', 'info');
			return;
		}
	
		//无交警流程(policyPro = 0)新增(tpHandleStatus = T)案件 或者 定责
		if (("${policyPro }" == "0" && handleType == "T") || "${qpTTPCase.tpHandleStatus}" == "5") {
			if ($('#qpTTPCaseCaseNotes').val() == null
					|| $('#qpTTPCaseCaseNotes').val() == "") {
				$.messager.alert('提示信息', '请添加事故详情！', 'info');
				return;
			}

			if ($('#qpTTPCaseCaseResult').val() == null
					|| $('#qpTTPCaseCaseResult').val() == "") {
				$.messager.alert('提示信息', '请添加调解结果！', 'info');
				return;
			}
		}

		var errorMessagesCount = 0;
		//当事人最多为8，不用index作为条件，是因为删除当事人的时候虽然index减了，但是iframename的index不会变了
		for ( var i = 0; i < 8 ; i++) {
	        if(typeof(window.frames["iframename_"+i]) == 'undefined'){
	    	   continue;
	        } 
	        
	        var acciIndex = getTitleByIndex(i);
	        var formAcciData = $(window.frames["iframename_"+i].document).find("#addFm");
	        if(formAcciData.find("input[name='qpTICAccidentCoId']").val()=='0'){
	        	$.messager.alert('提示信息',acciIndex+'请选择保险公司！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("input[name='qpTICAccidentCoId']").val()=='26'){
	        	if(formAcciData.find("input[name='qpTICAccidentCompanyNameOther']").val()==''){
		        	$.messager.alert('提示信息',acciIndex+'请输入其他保险公司！','info');
					errorMessagesCount++;
					break;
	        	}
			}
	        if(formAcciData.find("#qpTICAccidentDriverIDNumber").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写证件号！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentDriverName").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写 姓名！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentBirthDay").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写生日！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("input[name='qpTICAccidentDriverVehicleType']").val()=='0'){
	        	$.messager.alert('提示信息',acciIndex+'请选择车辆类型！','info');
				errorMessagesCount++;
				break;
			}      
	        if(formAcciData.find("input[name='qpTICAccidentPermissionDrive']").val()=='' || formAcciData.find("input[name='qpTICAccidentPermissionDrive']").val()=='0'){
	        	$.messager.alert('提示信息',acciIndex+'请选择准驾车型！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentDriverVehicleNumber").val()=='' || formAcciData.find("#qpTICAccidentDriverVehicleNumber").val()=='湘'){
	        	$.messager.alert('提示信息',acciIndex+'请填写车牌号码！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentDriverMobile").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写手机号码！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentLabelType").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写厂牌型号！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("#qpTICAccidentInsured").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写被保险人！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("input[name='qpTICAccidentDriverDirection']").val()=='0' || formAcciData.find("input[name='qpTICAccidentDriverDirection']").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请选择行驶方向！','info');
				errorMessagesCount++;
				break;
			}
	        if(formAcciData.find("input[name='qpTICAccidentChassisNumber']").val()=='0' || formAcciData.find("input[name='qpTICAccidentChassisNumber']").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写车架号！','info');
				errorMessagesCount++;
				break;
			}
	       if($("#currentCity").val() == '430100' && $('#qpTTPCaseCenterId'+$('#qpTTPCaseCenterId').combobox('getValue')).val() == '1'){
		        var handlePoliceNO = $('#handlePoliceNO').combobox('getValue');
		        if(handlePoliceNO=='请选择' || handlePoliceNO==''){
		        	$.messager.alert('提示信息','请选择办案民警','info');
					errorMessagesCount++;
					break;
				}else{
					var handlePoliceName = $('#handlePoliceNO').combobox('getText');
					$("#handlePoliceName").val(handlePoliceName);
				}
	       }
	       /*  if(formAcciData.find("#qpTICAccidentAcciStreet").val()==''){
	        	$.messager.alert('提示信息',acciIndex+'请填写当前住址！','info');
				errorMessagesCount++;
				break;
			} */
	        
			$(window.frames["iframename_"+i].document).find("#addFm").find("input[name^='qpTICAccident']").each(function(){
			    //单选框，只复制选中的。
				if( $(this).attr("type")=="radio"&&$(this).attr("checked")!="checked")
					{
					    null;
					}else if($(this).attr("name")=='qpTICAccidentRiskTimes' && $(this).val()==''){
					    $("#addFormData").append("<input name="+ $(this).attr("name").replace("qpTICAccident","qpTICAccident["+i+"]")+ " value='0'>");
					}else{
					    $("#addFormData").append("<input name="+ $(this).attr("name").replace("qpTICAccident","qpTICAccident["+i+"]")+ " value=" + $(this).val()+">");
					}
			});
			$(window.frames["iframename_"+i].document).find("#addFm").find("textarea[name^='qpTICAccident']").each(function(){
				$("#addFormData").append("<input name="+ $(this).attr("name").replace("qpTICAccident","qpTICAccident["+i+"]")+ " value=" + $(this).val()+">");
			});
		}
		if(errorMessagesCount>0){
			return false;
		}
							
		$.messager.confirm('提示', '是否确认保存？', function(r) {
			if (r) {
				$("#qpTICAccidentCount").val(index); //设置序号
				
				var data = $("#fm").serialize();
				$.ajax({
					type : "POST",
					url : '${ctx}/qp/qpttpcase/saveCase.do',
					async : true,
					cache : false, //缓存
					data : data,
					global : false,
					success : function(msg) {
						msg = eval('(' + msg + ')');
						//alert(msg);
						if ("Y" == msg.flag) {
							// 更新案件状态
							$("#qpTTPCaseTpHandleStatus").val($("#tpHandleStatus").val());
							$("#qpTTPCaseCaseId").val(msg.qpTTPCaseCaseId);
								if(msg.content=="保存成功，是否打印！"){
									$.messager.defaults = {
											ok : "进入打印",
											cancel : "继续新增"
										};
									$.messager.confirm("操作提示","案件保存处理成功！",function(r){
										if(r){
											printPreview();
										}
										location.reload(true);
									});
									$.messager.defaults = {
											ok : "确定",
											cancel : "取消"
									};
								}else{
									$.messager.alert("操作提示", msg.content, "info", function () {
										location.reload(true);
							        });
								}
						}else{
							$.messager.alert('错误信息',msg.content,'info');
							return;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.alert('提示信息', '提交后台发生错误，请重试！', 'info');
					}
				});
				
				
		     	/* $('#fm').form(
						'submit',
						{
							url : '${ctx}/qp/qpttpcase/saveCase.do',
							success : function(msg) {
								msg = eval('(' + msg + ')');
								//alert(msg);
								if ("Y" == msg.flag) {
									// 更新案件状态
									$("#qpTTPCaseTpHandleStatus").val($("#tpHandleStatus").val());
									$("#qpTTPCaseCaseId").val(msg.qpTTPCaseCaseId);
										if(msg.content=="保存成功，是否打印！"){
											$.messager.defaults = {
													ok : "进入打印",
													cancel : "继续新增"
												};
											$.messager.confirm("操作提示","案件保存处理成功！",function(r){
												if(r){
													printPreview();
												}
												location.reload(true);
											});
											$.messager.defaults = {
													ok : "确定",
													cancel : "取消"
											};
										}else{
											$.messager.alert("操作提示", msg.content, "info", function () {
												location.reload(true);
									        });
										}
								}else{
									$.messager.alert('错误信息',msg.content,'info');
									return;
								}
							}
						}); */
			   }
		});
	}

	function displayMap() {
		var province = $('#qpTTPCaseCaseProvince').combobox('getText');
		var city = $('#qpTTPCaseCaseCity').combobox('getText');
		var district = $('#qpTTPCaseCaseDistrict').combobox('getText');
		//var road = $('#qpTTPCaseCaseRoad').combobox('getText');

		if (province != null && province != "" && city != null && city != ""
				&& district != null && district != "") {
			var detailAddress = province + city + district;
			//$("#qpTTPCaseCaseStreet").val(detailAddress);
			searchByStationName(detailAddress);
		}
	}

	function showIMap() {
		var data = $("#fm").serialize();
		var caseUrl = contextRootPath + "/qp/qpttpcase/prepareShowIMap.do?"
				+ data;
		window
				.open(
						caseUrl,
						"_blank",
						'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}

	function historyGo() {
		if ("${actionType}" == 'add' || "${actionType}" == 'edit') {
			$.messager
					.confirm(
							'提示',
							'确认返回上一页?',
							function(r) {
								if (r) {
									if ("${actionType}" == 'edit') {
										window.location.href = contextRootPath
												+ "/qp/qpttpcase/prepareQuery2.do?businessType=${businessType}";
									} else {
										window.location.href = contextRootPath
												+ "/qp/qpttpcase/prepareQuery.do?businessType=${businessType}";
									}
									//	 				window.history.go(-1);
								}
							});
		} else {
			if ("${CaseFind}" == '1') {
				window.location.href = contextRootPath
						+ "/qp/qpttpcasefind/prepareQuery2.do";
			} else {
				window.location.href = contextRootPath
						+ "/qp/qpttpcase/prepareQuery2.do?businessType=${businessType}";
			}
		}
	}
	
	
	function acciViewTab(){
		var title = getTitleByIndex(index);
		if(index > 7){
	    	  $.messager.alert('提示信息', '最多允许8个当事人！', 'info');
	    	  return;
	      }
		var data = $("#fm").serialize();
		var urlHref = '${ctx}/qp/qpticaccident/prepareAdd.do?' + data;
		  var content = '<iframe scrolling="auto" name="iframename_'+index+'" frameborder="0"  src="'+urlHref+'" style="width:100%;height:100%;"></iframe>';    
	        $('#tt').tabs('add',{    
	            title:title,    
	            content:content,    
	            closable:true
	        });    
	        index++;   
	}
	
	function accidentTabs(accid){
      var title  = getTitleByIndex(index);
      var actionType =  $("#actionType").val();
      var showX = true;
      if(actionType=='view'){
    	  showX = false;
    	  $("#addTab").hide();
      }
	  var urlHref = '${ctx}/qp/qpticaccident/prepareAdd.do?actionType=' + actionType + '&qpAcciId=' + accid;
	  var content = '<iframe scrolling="auto" name="iframename_'+index+'" frameborder="0"  src="'+urlHref+'" style="width:100%;height:100%;"></iframe>';    
	  window.setTimeout(function(){ 
		  $('#tt').tabs('add',{    
	            title:title,    
	            content:content,    
	            closable:showX  
	        });    
	     },500);  
        index++;   
	}
	
	function getTitleByIndex(index){
		 var title  = "";
	      if(index==0){
	    	  title = "当事人（甲）";
	      }else if(index==1){
	    	  title = "当事人（乙）";
	      }else if(index==2){
	    	  title = "当事人（丙）";
	      }else if(index==3){
	    	  title = "当事人（丁）";
	      }else if(index==4){
	    	  title = "当事人（戊）";
	      }else if(index==5){
	    	  title = "当事人（己）";
	      }else if(index==6){
	    	  title = "当事人（庚）";
	      }else if(index==7){
	    	  title = "当事人（辛）";
	      }
	      return title;
	};
	
	function removePanel(){
        var tab = $('#tt').tabs('getSelected');
        var url = tab.find('iframe')[0].src;
        var regexpParam = /\??([\w\d%]+)=([\w\d%]*)&?/g; //分离参数的正则表达式
        var paramMap = {};
        var ret;
        while((ret = regexpParam.exec(url)) != null) {
            //ret[1]是参数名，ret[2]是参数值
             paramMap[ret[1]] = ret[2];
        }
        var qpAcciId =  paramMap.qpAcciId;
        if(tab){
            var url = contextRootPath + "/qp/qpticaccident/deleteTICAccident.do";
    		var data = "qpAcciId=" + qpAcciId;
            $.messager.confirm('提示', '是否确认删除？', function(r) {
    			if (r) {
    				$.ajax({
    					type : "POST",
    					url : url,
    					async : true,
    					cache : false, //缓存
    					data : data,
    					global : false,
    					success : function(result) {
    						var obj = eval("(" + result + ")");
    						if ("Y" == obj.flag) {
    				            var tabName = $('#tt').tabs('getTabIndex',tab);
    				            $('#tt').tabs('close',tabName);

    							$.messager.alert('提示信息', '删除当事人信息成功！', 'info');
    						} else {
    							alert(obj.content);
    						}
    					},
    					error : function(XMLHttpRequest, textStatus, errorThrown) {
    						$.messager.alert('提示信息', '提交后台发生错误，请重试！', 'info');
    					}
    				});
    			}
    		});
        }
    }

</script>

</head>
<body>
	<form name="fm" id="fm">
		<div class="right_detail_top">
			<h3>事故基本资料</h3>
		</div>
		<input type="hidden" id="businessType" name="businessType"
			value="${businessType }"> <input type="hidden"
			id="qpTTPCaseCaseId" name="qpTTPCaseCaseId"
			value="${qpTTPCase.caseId}" /> <input type="hidden"
			id="qpTTPCaseTpHandleStatus" name="qpTTPCaseTpHandleStatus"
			value="${qpTTPCase.tpHandleStatus}" />
		<!-- 用于保存处理状态 -->
		<input type="hidden" id="tpHandleStatus" name="tpHandleStatus"
			value="${qpTTPCase.tpHandleStatus}" /> 
			
			<input type="hidden"
			id="actionType" name="actionType" value="${actionType}" /> 
				<input type="hidden" id="longitude" name="longitude" value="${qpTTPCase.longitude}" />
			<input type="hidden" id="latitude" name="latitude" value="${qpTTPCase.latitude}"/>
            <input type="hidden" id="printTemp" name="printTemp" value="html"/>
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<th class="bgc_tt short"><font color="red">*</font>&nbsp;事故时间：
						</th>
						<td class="short"><input
							class='input_w w_20 easyui-validatebox' required="true"
							name="qpTTPCaseCaseTime" id="qpTTPCaseCaseTime" readonly
							value="${qpTTPCase.caseTime}"
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm', maxDate:'${currentDate }'})" />
						</td>
						<td class="bgc_tt short"><font color="red">*</font>&nbsp;天气情况：
						</td>
						<td class="short"><select id="qpTTPCaseCaseWeather"
							name="qpTTPCaseCaseWeather" editable="false"
							class="input_w w_20 easyui-combobox">
								<c:forEach var="weather" items="${weatherList}">
									<option value="${weather.codeCode}"
										<c:if test="${weather.codeCode==1}">selected</c:if>>${weather.codeCName}</option>
								</c:forEach>
						</select></td>
						 <th class="bgc_tt short"><font color="red">*</font>&nbsp;受理点：
						</th>
						<td><select id="qpTTPCaseCenterId" 
							name="qpTTPCaseCenterId" editable="false"
							class="input_w w_20 easyui-combobox" <c:if test="${not empty qpTTPCase.centerId }">readonly</c:if> >
								<option value="0">请选择</option>
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.id.centerId}"  <c:if test="${fastCenter.id.centerId==qpTTPCase.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
						</select></td>
						<c:forEach var="fastCenter" items="${fastCenterList}">
							<input type='hidden' id="qpTTPCaseCenterId${fastCenter.id.centerId}" value='${fastCenter.cities}' />
						</c:forEach>
					</tr>
					<tr>
						<td class="bgc_tt short" style="vertical-align: top"><font
							color="red">*</font>&nbsp;事故地点：</td>
						<td class="long" colSpan="5"><select
							id="qpTTPCaseCaseProvince" name="qpTTPCaseCaseProvince"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 113px;">
						</select> <select id="qpTTPCaseCaseCity" name="qpTTPCaseCaseCity"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 113px;">
						</select> <select id="qpTTPCaseCaseDistrict" name="qpTTPCaseCaseDistrict"
							editable="false" class="input_w w_15 easyui-validatebox"
							required="true" style="width: 115px;">
						</select> 
						<input class='input_w w_30 easyui-validatebox'  placeholder="必须录入正确的道路" required="true" name="qpTTPCaseCaseStreet" id="qpTTPCaseCaseStreet" value="${qpTTPCase.caseStreet}" >
						<%-- <input class='input_w w_30 easyui-validatebox' id="qpTTPCaseCaseStreet" name="qpTTPCaseCaseStreet" required="true" value="${qpTTPCase.caseStreet}"> --%>
						<input type="button" id="iMapDisplay" class="button_ty" onclick="showIMap();"  value="定位地图"  />		
						<input type="hidden" id="case_street" name="case_street"> 
						<!-- 
						<br> <textarea
								style="margin-top: 8px; margin-bottom: 8px; height: 35px;"
								id="qpTTPCaseCaseStreet" name="qpTTPCaseCaseStreet"
								class="input_w easyui-validatebox" rows="2" cols="100"
								required="true">${qpTTPCase.caseStreet}</textarea>
								 -->
								 </td>
					</tr>
					<tr>
				   <th class="bgc_tt short"><font color="red">*</font>&nbsp;录入时间：</th>
						<td ><input
							class='input_w w_20 '
							name="qpTTPCaseTpHandleTime" id="qpTTPCaseTpHandleTime" 
							readonly="readonly"
							required="true" value="${qpTTPCase.tpHandleTime}" />
					<input type="hidden" value="${qpTTPCase.caseCity}" id="currentCity" name="currentCity">
					</td>
					<c:if test="${qpTTPCase.caseCity == '430100'}">
							<th class="bgc_tt short" id="handlePoliceTh" <c:if test="${qpTTPCase.caseType == '1'}">style="display:none;"</c:if> ><font color="red">*</font>&nbsp;办案民警：</th>
							<td  id="handlePoliceTd" <c:if test="${qpTTPCase.caseType == '1'}">style="display:none;"</c:if> ><select id="handlePoliceNO" 
								name="handlePoliceNO" editable="false"
								class="input_w w_20 easyui-combobox"  >
									<option>请选择</option>
									<c:forEach var="handlePolice" items="${handlePoliceList}">
										<option value="${handlePolice.handlePoliceNO}" <c:if test="${handlePolice.handlePoliceNO==qpTTPCase.handlePoliceNO}">selected</c:if>>${handlePolice.handlePoliceName}</option>
									</c:forEach>
							</select>
							<input type="hidden" name="handlePoliceName" id="handlePoliceName" value="">
							</td>
					</c:if>
				 <c:if test="${actionType != 'add' }">
					<th class="bgc_tt short">办案人员姓名：</th>
					<td>
						<input type="hidden" name="qpTTPCaseAssistorId" id="qpTTPCaseTpLoginId" value="${qpTTPCase.assistorId}" />
						<input class="input_w w_20" name="qpTTPCaseAssistorName" id="qpTTPCaseAssistorName" readonly value="${qpTTPCase.assistorName}" />
					</td>
					<th class="bgc_tt short">估损人员姓名：</th>
					<td >
						<input type="hidden" name="qpTTPCaseEstimaterId" id="qpTTPCaseEstimaterId" value="${qpTTPCase.estimaterId}" />
						<input class="input_w w_20" name="qpTTPCaseEstimaterName" id="qpTTPCaseEstimaterName" readonly value="${qpTTPCase.estimaterName}" />
					</td>
					</c:if>
				</tr> 
				<c:if test="${actionType != 'add' }">
					<tr>
					    <th class="bgc_tt short">认字编号：</th>
						<td ><input type="text" class="input_w w_20" readonly="readonly" name="qpTTPCaseCaseSerialNo"
						 id="qpTTPCaseCaseSerialNo" value="${qpTTPCase.caseSerialNo}" />
						</td>
					</tr>
				</c:if>
				</table>
			</div>
		</div>
		
		<div id="containerMap" style="display: none;"></div>	
		<%--
		<div class="right_detail_top">
			<h3>事故受理方资料</h3>
		</div>
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
				<!--
					  <tr>
						 <th class="bgc_tt short">交警姓名：</th> 
						<th class="bgc_tt short"></th>
						<td class="long"><input type="hidden" name="qpTTPCaseTpLoginId" id="qpTTPCaseTpLoginId" value="${qpTTPCase.tpLoginId}" /> 
							<input type="hidden" name="qpTTPCasePoliceName" id="qpTTPCasePoliceName" value="${qpTTPCase.policeName}" /> 
							<input type="hidden" name="qpTTPCasePoliceEmployeeId" id="qpTTPCasePoliceEmployeeId" value="${qpTTPCase.policeEmployeeId}" /> 
							<input  type="hidden" class='input_r w_30' name="qpTTPCaseTpUserName" id="qpTTPCaseTpUserName" readonly value="${qpTTPCase.tpUserName}" />
						</td>
					 <th class="bgc_tt short">交警编号：</th> 
						<th class="bgc_tt short"></th>
						<td class="long"><input type="hidden" class='input_r w_30' name="qpTTPCaseTpEmployeeId" id="qpTTPCaseTpEmployeeId" readonly value="${qpTTPCase.tpEmployeeId}" /></td>
					</tr>   
					-->
				 <tr>
				   <th class="bgc_tt short"><font color="red">*</font>&nbsp;录入时间：</th>
						<td ><input
							class='input_w w_20 '
							name="qpTTPCaseTpHandleTime" id="qpTTPCaseTpHandleTime" 
							readonly="readonly"
							required="true" value="${qpTTPCase.tpHandleTime}" />
					</td>
				 
					<th class="bgc_tt short">办案人员姓名：</th>
					<td>
						<input type="hidden" name="qpTTPCaseAssistorId" id="qpTTPCaseTpLoginId" value="${qpTTPCase.assistorId}" />
						<input class='input_r w_20' name="qpTTPCaseAssistorName" id="qpTTPCaseAssistorName" readonly value="${qpTTPCase.assistorName}" />
					</td>
					<th class="bgc_tt short">估损人员姓名：</th>
					<td >
						<input type="hidden" name="qpTTPCaseEstimaterId" id="qpTTPCaseEstimaterId" value="${qpTTPCase.estimaterId}" />
						<input class='input_r w_20' name="qpTTPCaseEstimaterName" id="qpTTPCaseEstimaterName" readonly value="${qpTTPCase.estimaterName}" />
					</td>
				</tr> 

					<tr>
					    <th class="bgc_tt short"><font color="red">*</font>&nbsp;受理点：
						</th>
						<td ><select id="qpTTPCaseCenterId" 
							name="qpTTPCaseCenterId" editable="false"
							class="input_w w_20 easyui-combobox" <c:if test="${not empty qpTTPCase.centerId }">readonly</c:if> >
								<option value="0">请选择</option>
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.id.centerId}"
										<c:if test="${fastCenter.id.centerId==qpTTPCase.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
						</select></td>
					
					    <th class="bgc_tt short">认字编号：</th>
						<td ><input type="text" class="input_w w_20" readonly="readonly" name="qpTTPCaseCaseSerialNo"
						 id="qpTTPCaseCaseSerialNo" value="${qpTTPCase.caseSerialNo}" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		 --%>
		
		 
		<div id="tab-tools" >
			 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="addTab">添加当事人</a>
			 <!-- 
		     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick='removePanel()'>删除当事人</a>
		      -->
		</div>
		<div id="wrapper">
			<div id="tt"  data-options="tools:'#tab-tools'" style="width:1000px;height:524px; ">
				
			</div>
		</div>
		 
		 <!--  
		长沙无交警流程，暂时注释掉定责信息。
		<:if
			test="${businessType == '2' or (businessType == '3' and tpHandleStatus == '1') or (businessType == '3' and tpHandleStatus == '2')}">
					
				-->
			<div class="right_detail_top">
				<h3>定责信息</h3>
			</div>
			<div id="wrapper">
				<div id="container">
					<table class="fix_table">
						<tr>
							<td class="bgc_tt short" style="vertical-align: top"><font
								color="red">*</font>&nbsp;事故详情：</td>
							<td class="long"><select id="qpTTPCaseCaseNotesText"
								name="qpTTPCaseCaseNotesText" editable="false"
								class="input_w w_30 easyui-combobox">
									<option value="" selected>请选择</option>
									<option value="相撞，造成甲乙两车受损的交通事故。" title="相撞，造成甲乙两车受损的交通事故。">相撞，造成甲乙两车受损的交通事故。</option>
									<option value="追尾，造成甲乙两车受损的交通事故。" title="追尾，造成甲乙两车受损的交通事故。">追尾，造成甲乙两车受损的交通事故。</option>
									<option value="刮擦，造成甲乙两车受损的交通事故。" title="刮擦，造成甲乙两车受损的交通事故。">刮擦，造成甲乙两车受损的交通事故。</option>
							</select> <br> <textarea style="margin-top: 8px; height: 50px;"
									id="qpTTPCaseCaseNotes" name="qpTTPCaseCaseNotes" rows="3"
									cols="50">${qpTTPCase.caseNotes}</textarea></td>
							<td class="bgc_tt short" style="vertical-align: top"><font
								color="red">*</font>&nbsp;调解结果：</td>
							<td class="long"><select id="qpTTPCaseCaseResultText"
								name="qpTTPCaseCaseResultText" editable="false"
								class="input_w w_30 easyui-combobox">
									<option value="" selected>请选择</option>
									<option value="甲/乙两车同等责任。" title="甲/乙两车同等责任。">甲/乙两车同等责任。</option>
									<option value="甲/乙两车按责承担。" title="甲/乙两车按责承担。">甲/乙两车按责承担。</option>
									<option value="甲/乙两车车损由甲承担。" title="甲/乙两车车损由甲承担。">甲/乙两车车损由甲承担。</option>
									<option value="甲/乙两车车损由乙承担。" title="甲/乙两车车损由乙承担。">甲/乙两车车损由乙承担。</option>
							</select> <br> <textarea style="margin-top: 8px; height: 50px;"
									id="qpTTPCaseCaseResult" name="qpTTPCaseCaseResult" rows="3"
									cols="50">${qpTTPCase.caseResult}</textarea>
									</td>
									
						</tr>

					</table>
				</div>
			</div>
			<!-- 
			</:if>
		 -->
		 <!-- =============print start======= -->
		 <c:if
				test="${actionType == 'view' and (businessType == '3' or businessType == '1' ) and (tpHandleStatus == '2' or tpHandleStatus == '4' or tpHandleStatus == '5')}">
           <div class="right_detail_top">
				<h3>打印模块</h3>
			</div>
			<div id="wrapper">
				<div id="container">
					<table class="fix_table">
						<tr>
							<td class="bgc_tt short" style="vertical-align: top"><font
								color="red">*</font>&nbsp;打印格式：</td>
							<td class="long"><select id="printMethod"
								name="printMethod"  editable="false"
								class="input_w w_30 easyui-combobox">
									<option value="html" title="html" >html</option>
									<c:if test="${qpTTPCase.caseType=='1' or qpTTPCase.centerId=='26'}">
										<option value="pdf" title="pdf">pdf</option>
									</c:if>
							   </select> 
						   </td>
						   <td class="bgc_tt short" style="vertical-align: top"></td>
							<td class="long"></td>
						</tr>
						<tr>
						  <td  colspan=4 align="left">
						  <input id="printCase" name="printCase" type="button"
							class="button_ty" onclick="printPreview();"  value="打印" />
							<input id="printCaseView" name="printCaseView" type="button"
					       class="button_ty" onclick="printPreview();"  value="打印预览" />
						  </td>
						</tr>

					</table>
				</div>
			</div>
		</c:if>	
		 <!-- =============print end======= -->	
		<table class="fix_table">
			<tr>
				<td colspan=4 align="center">
					<c:if test="${actionType != 'view' }">
						<!-- 定责处理 -->
						<c:if test="${businessType == '2' }">
							<c:if test="${tpHandleStatus == '1' }">
								<input type="button" class="button_ty" onclick="saveAction('5');" ind="ind" value="处理完毕" />
								<input type="button" class="button_ty" onclick="saveAction('3');" ind="ind" value="打回" />
							</c:if>
						</c:if>
						<!-- 案件查询 -->
						<c:if test="${businessType == '3' }">
							<c:if test="${tpHandleStatus == null || tpHandleStatus == '0' }">
								<input type="button" class="button_ty" onclick="saveAction('T');" ind="ind" value="提交" />
							</c:if>
							<c:if test="${tpHandleStatus != null && tpHandleStatus != '0' }">
								<input type="button" class="button_ty" onclick="saveAction('${tpHandleStatus }');" ind="ind" value="保存" />
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${businessType != '-1' }">
						<input type="button" class="button_ty" onclick="historyGo();" value="返回">
					</c:if>	
				</td>
			</tr>
		</table>
		
	<div id="addFormData" style="display: none"></div>
	<input type="hidden" name="qpTICAccidentCount" id='qpTICAccidentCount'>	
	
	
	</form>
	<div id="resUserSetWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="添加当事人基本信息"
		style="width: 1000px; top: 200px;"></div>

	<div id="PrintPreviewWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="流水号录入"
		style="width: 300px; top: 800px; height: 150px; text-align: center; display:none;">
		<br>
		<br>
		<font color="red">*</font>&nbsp;8位流水号：<input type="text" maxlength="8"
			name="" value="" id="documentId"><br>
		<br>
		<input style="width: 50px" class="button_ty" type="button" value="确认"
			id="confirmFlag">
	</div>
	
	<c:forEach var="qpTICAccident" items="${qpTICAccidentList}">
			<script type="text/javascript">
			  accidentTabs("${qpTICAccident.acciId}");
			</script>
	</c:forEach>
	

	<script type="text/javascript">
		$('#confirmFlag')
				.bind(
						'click',
						function() {
							if (isNaN($("#documentId").val())) {
								$.messager.alert('提示信息', '请输入8位流水号后再进行打印！',
										'warning');
								return false;
							} else {
								if ($("#documentId").val().length == 8) {
									if ("2" == $("#qpTTPCaseTpHandleStatus")
											.val()) {
										//处理单证
										var url = contextRootPath
												+ "/qp/qpticdocumentdetail/modify.do?qpTTPCaseCaseId="
												+ $("#qpTTPCaseCaseId").val()
												+ "&documentId="
												+ $("#documentId").val()
												+ "&centerId="
												+ $('#qpTTPCaseCenterId').val();
										$.ajax({
													type : "POST",
													url : url,
													success : function(result) {
														var data = eval("("
																+ result + ")");
														if (data.flag
																.toString() == "Y") {
															editRecord(contextRootPath
																	+ "/qp/qpttpcase/printPreview.do?qpTTPCaseCaseId="
																	+ $(
																			"#qpTTPCaseCaseId")
																			.val());
														} else {
															// $.messager.alert('提示信息', data.msg, 'warning'); 
															$.messager
																	.show({
																		title : '提示信息',
																		msg : data.msg,
																		//showType : 'slide',
																		style : {
																		right : '',
																		top : 700,
																			//width:100,   
																			//height:500,   
																		height : 100,
																		bottom : '确定'
																		}
																	});
														}
													},
													error : function(result) {
														alertErrorMsgForEasyUi(result);
													}
												});

									} else {
										$.messager.alert('提示信息',
												'只有已受理的案件才能打印！', 'warning');
									}
								} else {
									$.messager.alert('提示信息', '请输入8位流水号后再进行打印！',
											'warning');
									return false;
								}
							}

						});
	</script>
</body>

<script type="text/javascript">
	var map = new BMap.Map("containerMap");
	var jingdu = $("#longitude").val();
	var weidu = $("#latitude").val();
	if(jingdu!=null && jingdu!=""){
		var pointFind = new BMap.Point(jingdu, weidu);
		map.centerAndZoom(pointFind, 17);
		var markerFind = new BMap.Marker(pointFind); // 创建标注，为要查询的地方对应的经纬度
		map.addOverlay(markerFind); // 将标注添加到地图中
	}else{
		map.centerAndZoom("长沙市", 12); //默认地图显示的标注位置
	}

	map.setDefaultCursor("url('bird.cur')"); //设置地图默认的鼠标指针样式
	map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用

	// map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	// map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl()); //右下角

	var localSearch = new BMap.LocalSearch(map);
	localSearch.enableAutoViewport(); //允许自动调节窗体大小

	function searchByStationName(detailAddress) {

		map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function(searchResult) {
			var poi = searchResult.getPoi(0);
			map.centerAndZoom(poi.point, 17);
			var marker = new BMap.Marker(new BMap.Point(poi.point.lng,
					poi.point.lat)); // 创建标注，为要查询的地方对应的经纬度
			map.addOverlay(marker); // 将标注添加到地图中

			$("#longitude").val(poi.point.lng); //经度
			$("#latitude").val(poi.point.lat); //纬度
			//点击地图获取点击的经纬度
			map.addEventListener("click", function(e) {

				//地址解析器 根据经纬度获取当前地址
				var gc = new BMap.Geocoder();
				var pointAdd = new BMap.Point(e.point.lng, e.point.lat);

				$("#longitude").val(e.point.lng); //经度
				$("#latitude").val(e.point.lat); //纬度

				gc.getLocation(pointAdd, function(rs) {
					var addComp = rs.addressComponents;
					var adr = addComp.province + addComp.city
							+ addComp.district + addComp.street
							+ addComp.streetNumber;

					map.clearOverlays();//清空原来的标注
					var marker1 = new BMap.Marker(pointAdd); // 创建标注
					map.addOverlay(marker1); // 将标注添加到地图中
					//map.centerAndZoom(pointAdd, 16);
					var opts = {
						width : 200, // 信息窗口宽度
						height : 50, // 信息窗口高度
						title : "", // 信息窗口标题
						enableMessage : true,//设置允许信息窗发送短息
						message : "地址无法显示"
					};
					var infoWindow = new BMap.InfoWindow(adr, opts); // 创建信息窗口对象 
					map.openInfoWindow(infoWindow, pointAdd); //开启信息窗口
					//$("#qpTTPCaseCaseStreet").val(infoWindow.getContent());
				});

			});

		});
		localSearch.search(detailAddress);
	}
</script>
</html>
