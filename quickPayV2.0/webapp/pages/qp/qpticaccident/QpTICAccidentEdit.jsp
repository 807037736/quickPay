<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

</head>
<body>
	<form name="addFm" id="addFm">
		<div class="block">
			<h3>基本信息</h3>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">
							证件类别：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverIDType" name="qpTICAccidentDriverIDType" editable="false" class="input_w w_30 easyui-combobox">
								<c:forEach var="identifyType" items="${identifyTypeList}">
									<option value="${identifyType.codeCode}" <c:if test="${identifyType.codeCode==qpTICAccident.driverIDType}">selected</c:if>>${identifyType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;保险公司：
						</td>
						<td class="long">
							<select id="qpTICAccidentCoId" name="qpTICAccidentCoId" class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
									<option value="${qpTICCompany.coId}" <c:if test="${qpTICCompany.coId==qpTICAccident.coId}">selected</c:if>>${qpTICCompany.coName}</option>
								</c:forEach>
							</select>
							<input class="input_w w_22 easyui-validatebox"  name="qpTICAccidentCompanyNameOther" id="qpTICAccidentCompanyNameOther"  value="${qpTICAccident.companyNameOther}" <c:if test="${qpTICAccident.coId != '26' }">style="display: none;"</c:if>  >
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short" >
							<font color="red">*</font>&nbsp;证件号： 
						</td>
						<td class="long" style="font-size:15px;">
							<input class='input_w w_30 easyui-validatebox' required="true" name="qpTICAccidentDriverIDNumber" id="qpTICAccidentDriverIDNumber" value="${qpTICAccident.driverIDNumber}" onchange="queryAcciDriverInfo();">
							<!-- <input type="button" class="button_ty" onclick="viewHistory('1');"  value="详情" />-->
							出险<input style="width:20px;color:red;font-weight:bold;" type="button" onclick="viewHistory('1');" name="qpTICAccidentRiskTimes" readonly="readonly" id="qpTICAccidentRiskTimes" value="${qpTICAccident.riskTimes}" >次
						</td>
						<td class="bgc_tt short">
							保险凭证： 
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' placeholder="末尾四位数" maxlength="4" name="qpTICAccidentDriverInsuNumber" id="qpTICAccidentDriverInsuNumber" value="${qpTICAccident.driverInsuNumber}" >
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;姓名： 
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' required="true" name="qpTICAccidentDriverName" id="qpTICAccidentDriverName" value="${qpTICAccident.driverName}" >
						</td>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;车辆类型：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverVehicleType" name="qpTICAccidentDriverVehicleType" editable="false" class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="vehicleType" items="${vehicleTypeList}">
									<option value="${vehicleType.codeCode}" <c:if test="${vehicleType.codeCode==qpTICAccident.driverVehicleType}">selected</c:if>>${vehicleType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;生日： 
						</td>
						<td class="long">
							<input class="input_w w_22 easyui-validatebox" required="true" name="qpTICAccidentBirthDay" id="qpTICAccidentBirthDay" onclick="WdatePicker({skin:'whyGreen',startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" value="${qpTICAccident.birthDay}">
						</td>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;准驾车型：
						</td>
						<td class="long">
							<select id="qpTICAccidentPermissionDrive" name="qpTICAccidentPermissionDrive" editable="false" class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="permissionDriveType" items="${permissionDriveTypeList}">
									<option value="${permissionDriveType.codeCode}" <c:if test="${permissionDriveType.codeCode==qpTICAccident.permissionDrive}">selected</c:if>>${permissionDriveType.codeCode}-${permissionDriveType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">
							性别：
						</td>
						<td class="long" style="font-size:15px;">
								<input type="radio" id="qpTICAccidentDriverSex0"  name="qpTICAccidentDriverSex" class="qpTICAccidentDriverSex" <c:if test="${qpTICAccident.driverSex=='0'}">checked</c:if> value="0">男 
								<input type="radio" id="qpTICAccidentDriverSex1"  name="qpTICAccidentDriverSex" class="qpTICAccidentDriverSex" <c:if test="${qpTICAccident.driverSex=='1'}">checked</c:if> value="1">女
						</td>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;车牌号码：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverVehicleNumberPre" name="qpTICAccidentDriverVehicleNumberPre"  class="input_w w_3 easyui-combobox" style="width:50px;">
							</select>
							<select id="qpTICAccidentDriverVehicleNumberPreS" name="qpTICAccidentDriverVehicleNumberPreS" editable="false" class="input_w w_3 easyui-combobox" style="width:45px;">
							</select>
							<input class='input_w easyui-validatebox' style="width:80px;" required="true" name="qpTICAccidentDriverVehicleNumber" id="qpTICAccidentDriverVehicleNumber" value="${qpTICAccident.driverVehicleNumber}" >
							<select id="qpTICAccidentDriverVehicleNumberPreE" name="qpTICAccidentDriverVehicleNumberPreE" editable="false" class="input_w w_3 easyui-combobox" style="width:50px;">
							</select>
							<input type="button" class="button_ty" onclick="viewHistory('2');"  value="详情" />
						</td>
					</tr>
					<c:if test="${businessType ne '111'}">
					<tr>
						<td class="bgc_tt short">
							手机号码：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' validType="mobile" placeholder="手机或固定电话 至少填一个"  maxlength="11" name="qpTICAccidentDriverMobile" id="qpTICAccidentDriverMobile" value="${qpTICAccident.driverMobile}" >
						</td>
						<td class="bgc_tt short">
							固定电话：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' validType="phoneOrMobile" placeholder="如：010-88888888或88888888" name="qpTICAccidentDriverPhone" id="qpTICAccidentDriverPhone" value="${qpTICAccident.driverPhone}" >
						</td>
					</tr>
					</c:if>
					
					<tr>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;厂牌型号：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'  placeholder="如：大众"  maxlength="11" name="qpTICAccidentLabelType" id="qpTICAccidentLabelType" value="${qpTICAccident.labelType}" >
						</td>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;被保险人：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'  name="qpTICAccidentInsured" id="qpTICAccidentInsured" value="${qpTICAccident.insured}" >
						</td>
					</tr>
					
					<tr>
						<td class="bgc_tt short" style="vertical-align:top">
							<font color="red">*</font>&nbsp;行驶方向：
						</td>
						<td class="long" style="vertical-align:top">
							<select id="qpTICAccidentDriverDirection" name="qpTICAccidentDriverDirection" editable="false" class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="directionType" items="${directionTypeList}">
									<option value="${directionType.codeCode}" <c:if test="${directionType.codeCode==qpTICAccident.driverDirection}">selected</c:if>>${directionType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
						
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;车架号：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'  name="qpTICAccidentChassisNumber" id="qpTICAccidentChassisNumber" placeholder="请输入十七位车架号" value="${qpTICAccident.chassisNumber}" >
						</td>
						<%-- <td class="bgc_tt short">
							行驶路段：
						</td>
						<td class="long">
							<input class='input_w w_30' name="qpTICAccidentDriverStreet" id="qpTICAccidentDriverStreet" value="${qpTICAccident.driverStreet}" >
						</td> --%>
						<!-- <td class="bgc_tt short">
							出险次数：
						</td>
						<td class="long">
							<input class='input_w w_30' name="qpTICAccidentRiskTimes" readonly="readonly" id="qpTICAccidentRiskTimes" value="${qpTICAccident.riskTimes}" >次
						</td> -->
					</tr>
					<!-- <tr>
						<td class="bgc_tt short">
							行驶主干道：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverRoad" name="qpTICAccidentDriverRoad"  class="input_w w_30">
							</select>
						</td>
						
					</tr> -->
					<tr>
						<td class="bgc_tt short" style="vertical-align:top">&nbsp;当前住址： 
						</td>
						<td class="long" colSpan="3">
							<select id="qpTICAccidentAcciProvince" name="qpTICAccidentAcciProvince"  editable="false" class="input_w easyui-validatebox"  style="width:110px;" >
							</select>
							<select id="qpTICAccidentAcciCity" name="qpTICAccidentAcciCity" editable="false" class="input_w easyui-validatebox"  style="width:111px;" >
							</select>
							<select id="qpTICAccidentAcciDistrict" name="qpTICAccidentAcciDistrict" editable="false" class="input_w easyui-validatebox"  style="width:118px;" >
							</select>
							<input class='input_w w_40 easyui-validatebox' id="qpTICAccidentAcciStreet" name="qpTICAccidentAcciStreet" >
							<!-- 
							<br>
							<textarea class="input_w easyui-validatebox"  style="margin-top:8px;height:35px;" id="qpTICAccidentAcciStreet" name="qpTICAccidentAcciStreet" rows="2" cols="95" ></textarea>
						 -->
						</td>
					</tr>

				</table>
			</div>
			
		
		<!--  让辅助人员录入定责信息，后续增加流程配置。对于无交警流程的，让辅助人员录入定责信息。
		<:if test="${actionType == 'view' or businessType == '2'}">
		 -->
		<div class="block">
			<h3>当事人责任</h3>
				<table class="fix_table" >
					<tr>
						<td class="bgc_tt">
							<font color="red">*</font>&nbsp;违反法律法规：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverLawId" name="qpTICAccidentDriverLawId"  class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="qpTTPLaw" items="${qpTTPLawList}">
									<option value="${qpTTPLaw.lawId}" <c:if test="${qpTTPLaw.lawId==qpTICAccident.driverLawId}">selected</c:if>>${qpTTPLaw.lawWords}</option>
								</c:forEach>
							</select>
							<input type="button" class="button_ty" onclick="openAddLawWindow();" ind="ind"  value="+添加法规" />
						</td>
						<td class="bgc_tt">
							<font color="red">*</font>&nbsp;需承担的责任：
						</td>
						<td class="long">
							<select id="qpTICAccidentDriverLiability" name="qpTICAccidentDriverLiability" editable="false" class="input_w w_30 easyui-combobox">
								<option value="0">请选择</option>
								<c:forEach var="responsibilityType" items="${responsibilityTypeList}">
									<option value="${responsibilityType.codeCode}" <c:if test="${responsibilityType.codeCode==qpTICAccident.driverLiability}">selected</c:if>>${responsibilityType.codeCName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
				</table>
			</div>
			<!-- 
			</:if>
			-->

			<c:if test="${qpTTPCase.tpHandleStatus =='4' || qpTTPCase.tpHandleStatus =='5'}">
			<div class="block">
			<h3>估损信息</h3>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;估损金额： 
						</td>
						<td class="long">
							<input class='input_w w_15 easyui-validatebox' required="true" validType="intOrFloat" name="qpTICAccidentEstimateLossSum" id="qpTICAccidentEstimateLossSum" value="${qpTICAccident.estimateLossSum}">
						</td>	
						
					    <td class="bgc_tt short">
							定损价格:
						</td>
						<td class="long">
							<input class='input_w w_15 easyui-validatebox' validType="intOrFloat" name="qpTICAccidentFixedLossPrice" id="qpTICAccidentFixedLossPrice" value="${qpTICAccident.fixedLossPrice}">
						</td>					
					</tr>
					<tr>
						<td class="bgc_tt short" style="vertical-align:middle">
							&nbsp;备注： 
						</td>
						<td class="long" colSpan="3">
							<textarea class="input_w easyui-validatebox" style="margin-top:8px;height:35px;" id="qpTICAccidentSurveyNotes" name="surveyNotes" rows="2" cols="95" >${qpTICAccident.surveyNotes}</textarea>
						</td>
					</tr>
					<!-- <tr style="margin-top:210px;">
						<td colspan=4 align="center">
						     <input type="button" class="button_ty" onclick="openImageView()" value="查看图片" />
							 <input type="button" class="button_ty" onclick="closeUserWindow();"  value="关闭" />
						</td>
					</tr> -->
				</table>
		    </div>
		    </c:if>
		    <div class="block">
			<h3>照片信息</h3>
				<table class="fix_table" >
					<tr>
						<td class="bgc_tt short">
<!-- 							<font color="red">*</font>&nbsp; -->
							 事故现场编号：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'  name="qpTICAccidentGroupId" id="qpTICAccidentGroupId" value="${qpTICAccident.groupId}" >
						</td>
						<td class="bgc_tt short">
							<input type="button" class="button_ty" onclick="drawPhoto()"  value="提取照片" />
						</td>
						<td class="long">
							<input type="button" class="button_ty" onclick="openImageView()" value="查看照片" />
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">
							查勘照片：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' readonly  name="qpTICAccidentSurveyGroupId" id="qpTICAccidentSurveyGroupId" value="${qpTICAccident.surveyGroupId}" >
						</td>
						<td class="bgc_tt short">
							<input type="button" class="button_ty" onclick="showPicture()" value="查看照片" />
						</td>
					</tr>
				</table>
			</div>
			<!-- 打印处理记录 -->
			<c:if test="${qpTTPCase.tpHandleStatus == '5' || qpTTPCase.tpHandleStatus == '4'}">
				<div class="block">
					<h3>打印模块</h3>
					<table class="fix_table">
						<tr>
							<td class="long">
								<input id="printCase" name="printCase" type="button" class="button_ty" onclick="printPreview(true);"  value="全打处理记录" />
								<input id="printCase" name="printCase" type="button" class="button_ty" onclick="printPreview(false);"  value="套打处理记录" />
							</td>
						</tr>
					</table>
				</div>
			</c:if>
		    		<%--
		    <table class="fix_table" >
			<c:if test="${businessType != '1'}">
					<tr style="margin-top:210px;">
						<td colspan=4 align="center">
							<c:if test="${actionType != 'view'}">
							
							    <input type="button" class="button_ty" onclick="saveAcciAction();"  value="保存" />
<!-- 								<input type="button" class="button_ty" id="upload"  value="上传照片" /> -->
							</c:if>
<!-- 							    <input type="button" class="button_ty" onclick="openImageView()" value="查看图片" /> -->
								<input type="button" class="button_ty" onclick="closeUserWindow();"  value="关闭" />
						</td>
					</tr>
				</c:if>
				
				<c:if test="${businessType == '1'}">
					<tr style="margin-top:210px;">
						<td colspan=4 align="center">
							<input type="button" class="button_ty" onclick="closeUserWindow();"  value="关闭" />
						</td>
					</tr>
				</c:if>
			</table>
		    --%>
		    
			<%-- 隐藏域 --%>
			<input type="hidden" id="qpTICAccidentAcciId" name="qpTICAccidentAcciId" value="${qpTICAccident.acciId}" />
			
			<%-- 案件信息隐藏域 --%>
			<input type="hidden" id="qpTTPCaseCaseId2" name="qpTTPCaseCaseId" value="${qpTTPCase.caseId}" />
			<input type="hidden" id="qpTTPCaseCaseTime2" name="qpTTPCaseCaseTime" value="${qpTTPCase.caseTime}" />
			<input type="hidden" id="qpTTPCaseCaseWeather2" name="qpTTPCaseCaseWeather" value="${qpTTPCase.caseWeather}" />
			<input type="hidden" id="qpTTPCaseCaseProvince2" name="qpTTPCaseCaseProvince" value="${qpTTPCase.caseProvince}" />
			<input type="hidden" id="qpTTPCaseCaseCity2" name="qpTTPCaseCaseCity" value="${qpTTPCase.caseCity}" />
			<input type="hidden" id="qpTTPCaseCaseDistrict2" name="qpTTPCaseCaseDistrict" value="${qpTTPCase.caseDistrict}" />
			<input type="hidden" id="qpTTPCaseCaseStreet2" name="qpTTPCaseCaseStreet" value="${qpTTPCase.caseStreet}" />
			<input type="hidden" id="qpTTPCaseCaseRoad2" name="qpTTPCaseCaseRoad" value="${qpTTPCase.caseRoad}" />
			<input type="hidden" id="qpTTPCaseTpLoginId2" name="qpTTPCaseTpLoginId" value="${qpTTPCase.tpLoginId}" />
			<input type="hidden" id="qpTTPCasePoliceName2" name="qpTTPCasePoliceName" value="${qpTTPCase.policeName}" />
			<input type="hidden" id="qpTTPCasePoliceEmployeeId2" name="qpTTPCasePoliceEmployeeId" value="${qpTTPCase.policeEmployeeId}" />
			<input type="hidden" id="qpTTPCaseTpUserName2" name="qpTTPCaseTpUserName" value="${qpTTPCase.tpUserName}" />
			<input type="hidden" id="qpTTPCaseTpEmployeeId2" name="qpTTPCaseTpEmployeeId" value="${qpTTPCase.tpEmployeeId}" />
			<input type="hidden" id="qpTTPCaseTpHandleTime2" name="qpTTPCaseTpHandleTime" value="${qpTTPCase.tpHandleTime}" />
			<input type="hidden" id="qpTTPCaseCaseSerialNo2" name="qpTTPCaseCaseSerialNo" value="${qpTTPCase.caseSerialNo}" />
			<input type="hidden" id="qpTTPCaseCenterId2" name="qpTTPCaseCenterId" value="${qpTTPCase.centerId}" />
			<input type="hidden" id="qpTTPCaseCaseNotes2" name="qpTTPCaseCaseNotes" value="${qpTTPCase.caseNotes}" />
			<input type="hidden" id="qpTTPCaseTpHandleNotes2" name="qpTTPCaseTpHandleNotes" value="${qpTTPCase.tpHandleNotes}" />
	</form>
	<div id="uploadPhotoWindow" class="easyui-window" collapsible="false"
		resizable="false" minimizable="false" maximizable="false"
		closed="true" modal="true" title="提取照片信息"
		style="width:500px;height:250px;top:350px;"></div>

	<script language="javascript">
	<%-- 页面加载触发  --%>
	$(document).ready(function(){
		$('#upload').bind('click', function() {
			openUploadWindow();
		});
		
		$('#qpTICAccidentCoId').combobox();
		$('#qpTICAccidentDriverVehicleType').combobox();
		$('#qpTICAccidentPermissionDrive').combobox();
		$('#qpTICAccidentDriverDirection').combobox();
		$('#qpTICAccidentDriverLawId').combobox();
		$('#qpTICAccidentDriverLiability').combobox();
		
		if("view" == '${actionType}') {
			setReadonlyOfAllInput("addFm");
		}
		/* $('#qpTICAccidentDriverRoad').combobox({
			url: '${ctx}/qp/qptcommon/getRoadList.do?districtId=' + $('#qpTTPCaseCaseDistrict2').val(),
	   		valueField: 'roadId',
			textField: 'roadName'
	   	}); */
		
		//$("#qpTICAccidentDriverRoad").combobox("setValue",'${qpTICAccident.driverRoad}');

		//alert($("#proNameId").val());
		$('#qpTICAccidentAcciProvince').combobox({
		 	url: '${ctx}/qp/qptcommon/getProvinceList.do',
			valueField: 'provId',
			textField: 'provName',
			value:$("#proId").val(),
			//selected: $("#proName").val(),
	 		onSelect: function() {
	 			$('#qpTICAccidentAcciCity').combobox('clear');
	 			$('#qpTICAccidentAcciDistrict').combobox('clear');
	 			$('#qpTICAccidentAcciCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#qpTICAccidentAcciProvince').combobox('getValue'));
	 		},
	 		onUnselect: function() {
	 			$('#qpTICAccidentAcciCity').combobox('clear');
	 			$('#qpTICAccidentAcciDistrict').combobox('clear');
	 			$('#qpTICAccidentAcciCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#qpTICAccidentAcciProvince').combobox('getValue'));
	 		}
	   	}); 
		
		$('#qpTICAccidentAcciCity').combobox({
		 	url: '${ctx}/qp/qptcommon/getCityList.do',
			valueField: 'cityId',
			textField: 'cityName' ,
	 		onSelect: function() {
	 			$('#qpTICAccidentAcciDistrict').combobox('clear');
	 			$('#qpTICAccidentAcciDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?address=1&cityId=' +$('#qpTICAccidentAcciCity').combobox('getValue'));
	 		},
	 		onUnselect: function() {
	 			$('#qpTICAccidentAcciDistrict').combobox('clear');
	 			$('#qpTICAccidentAcciDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?address=1&cityId=' +$('#qpTICAccidentAcciCity').combobox('getValue'));
	 		}
	   	}); 
		
		$('#qpTICAccidentAcciDistrict').combobox({
	   		valueField: 'districtId',
			textField: 'districtName',
			onSelect: function() {
				displayMap();
	 		}
	   	});  
		
		$("#qpTICAccidentAcciProvince").combobox("setValue",'${qpTICAccident.acciProvince}');

		$("#qpTICAccidentAcciCity").combobox("setValue",'${qpTICAccident.acciCity}');
		$('#qpTICAccidentAcciCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#qpTICAccidentAcciProvince').combobox('getValue'));
		
		$('#qpTICAccidentAcciDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?address=1&cityId=' +$('#qpTICAccidentAcciCity').combobox('getValue'));
		
		
		if("add" != '${actionType}') {
 			$("#qpTICAccidentAcciDistrict").combobox("setValue",'${qpTICAccident.acciDistrict}');
			
			$("#qpTICAccidentAcciStreet").val('${qpTICAccident.acciStreet}');
		}
		
		initIdCard();
		initVehicle();
		
		$("#qpTICAccidentDriverIDType").combobox({
			onChange : function(n,o) {
				if(n == '6') {
					$("#qpTICAccidentDriverIDNumber").attr("maxlength","18");
				}else {
					$("#qpTICAccidentDriverIDNumber").removeAttr("maxlength");
				}
			}
		});
		
		
		$("#qpTICAccidentCoId").combobox({
			onChange : function(n,o) {
				if(n == '26') {
					$("#qpTICAccidentCompanyNameOther").show();
				}else {
					$("#qpTICAccidentCompanyNameOther").hide();
					$("#qpTICAccidentCompanyNameOther").val("");
				}
			}
		});
		
		
		$("#qpTICAccidentDriverVehicleNumberPre").combobox({
			onChange : function(n,o) {
				$("#qpTICAccidentDriverVehicleNumber").attr("value",n);
			}
		});
		
		$("#qpTICAccidentDriverVehicleNumberPreS").combobox({
			onSelect : function(n) {
				//console.log(n.value);
				//alert($("#qpTICAccidentDriverVehicleNumberPre").combobox('getValue'));
				//$("#qpTICAccidentDriverVehicleNumber").attr("value",$("#qpTICAccidentDriverVehicleNumberPre").combobox('getValue') + n);
				$("#qpTICAccidentDriverVehicleNumber").attr("value",$("#qpTICAccidentDriverVehicleNumberPre").combobox('getText') + n.value);
			}
		});
		
		$("#qpTICAccidentDriverVehicleNumberPreE").combobox({
			onChange : function(n,o) {
				$("#qpTICAccidentDriverVehicleNumber").attr("value",$('#qpTICAccidentDriverVehicleNumber').val() + n);
			}
		});
		
		if("add" == '${actionType}') {
			//行驶街道
			//$("#qpTICAccidentDriverStreet").val('${qpTTPCase.caseStreet}');
			//行驶主干道
			//$("#qpTICAccidentDriverRoad").combobox("setValue",'${qpTTPCase.caseRoad}');
			
			$("#qpTICAccidentDriverVehicleNumberPre").combobox("setValue",'${provVehicleNumPre}');
			var cityVal = '${cityVehicleNumPre}';
			var cityText= cityVal.substring(1, 2);
			$("#qpTICAccidentDriverVehicleNumberPreS").combobox("setValue",cityText);
		}
		<%--		让点击combobox就弹出下拉框--%>
		$(".combo").click(function(){
			$(this).prev().combobox("showPanel");
			});
		
	});
	
	<%-- 保存当事人信息 --%>
	function saveAcciAction(){
		if (!$('#addFm').form('validate')) {
			return ;
		}
		
		if($('#qpTICAccidentCoId').combobox('getValue') == '0') {
			$.messager.alert('提示信息','请选择保险公司！','info');
			return ;
		}
		
		if($('#qpTICAccidentCoId').combobox('getValue') == '26'){
			if($('#qpTICAccidentCompanyNameOther').combobox('getValue') =="" || $('#qpTICAccidentCompanyNameOther').combobox('getValue').trim() == ""){
				$.messager.alert("提示信息","其他保险不能为空！","info");
				return ;
			}
		}
		
		var qpTICAccidentDriverIDType= $('#qpTICAccidentDriverIDType').combobox('getValue');
		var qpTICAccidentDriverIDNumber = $('#qpTICAccidentDriverIDNumber').val();
		if(qpTICAccidentDriverIDNumber == null || qpTICAccidentDriverIDNumber.trim() == '') {
			$.messager.alert("提示信息","证件号码不能为空！","info");
			return ;
		}
		if(qpTICAccidentDriverIDType == "6") {
			var resultMsg = isCardID(qpTICAccidentDriverIDNumber);
			if(resultMsg != "") {
				$.messager.alert("提示信息",resultMsg,"info");
				return ;
			}
		}
		
		if($('#qpTICAccidentDriverVehicleType').combobox('getValue') == '0') {
			$.messager.alert('提示信息','请选择车辆类型！','info');
			return ;
		}

		if($('#qpTICAccidentPermissionDrive').combobox('getValue') == '0') {
			$.messager.alert('提示信息','请选择准驾车型！','info');
			return ;
		}
		
		if( ($('#qpTICAccidentDriverMobile').val() == null || $('#qpTICAccidentDriverMobile').val().trim() == "") 
				&& ($('#qpTICAccidentDriverPhone').val() == null || $('#qpTICAccidentDriverPhone').val().trim() == "") ) {
			$.messager.alert('提示信息','手机或固定电话至少填一个！','info');
			return ;
		}
		if($('#qpTICAccidentLabelType').val() == null || $('#qpTICAccidentLabelType').val().trim() == "") {
			$.messager.alert('提示信息','请填写厂牌型号！','info');
			return ;
		}
		if($('#qpTICAccidentInsured').val() == null || $('#qpTICAccidentInsured').val().trim() == "") {
			$.messager.alert('提示信息','请填写被保险人！','info');
			return ;
		}
		if($('#qpTICAccidentDriverDirection').combobox('getValue') == '0') {
			$.messager.alert('提示信息','请选择行驶方向！','info');
			return ;
		}
		if($('#qpTICAccidentChassisNumber').val() == null || $('#qpTICAccidentChassisNumber').val().trim() == "") {
			$.messager.alert('提示信息','请填写车架号！','info');
			return ;
		}
		if("${actionType}" == 'view'||"${businessType}"  == '2'){
			if($('#qpTICAccidentDriverLawId').combobox('getValue') == '0') {
				$.messager.alert('提示信息','请选择违反法律法规！','info');
				return ;
			}
			
			if($('#qpTICAccidentDriverLiability').combobox('getValue') == '0') {
				$.messager.alert('提示信息','请选择需承担的责任！','info');
				return ;
			}
		}
		//填写默认出险次数
		if($('#qpTICAccidentRiskTimes').val() == '') {
			$('#qpTICAccidentRiskTimes').val('0');
		}
		
		/* $.messager.confirm('提示', '是否确认保存？', function(r){
			if (r) { */
				$('#addFm').form('submit', {
					url: '${ctx}/qp/qpticaccident/saveCase.do',
					success: function(msg) {
						msg = eval('(' + msg + ')');
						if(msg.flag == "Y") {
							$('#qpTTPCaseCaseId2').val(msg.result.caseId);
							/* $('#qpTTPCaseCaseSerialNo2').val(msg.result.caseSerialNo); */
							$('#qpTICAccidentAcciId').val(msg.result.acciId);
							resUserSetWindow();
						}else{
							$.messager.alert('错误信息',msg.content,'info');
							return;
						}
						/* $.messager.alert('提示信息',msg.content,'info'); */
					}
				});
			closeUserWindow();
		/* 	}
		}); */
	}
	
	$('#qpTICAccidentDriverVehicleNumber').blur(function(){
		if($('#qpTICAccidentDriverVehicleNumber').val()=="" || $('#qpTICAccidentDriverVehicleNumber').val()=="湘"){
		       return false;
		}
		var re1=/^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\d{7}$/;
		if($('#qpTICAccidentDriverVehicleNumber').val().search(re1)==-1){
		     $.messager.alert("错误信息","输入的车牌号格式不正确","info");
		       //alert("输入的车牌号格式不正确");
		       return false;
		}
		drawPhoto();
	});
	
	$('#qpTICAccidentChassisNumber').blur(function(){
		if($('#qpTICAccidentChassisNumber').val()=="" || $('#qpTICAccidentDriverVehicleNumber').val()==null){
		       return false;
		}
		if($('#qpTICAccidentChassisNumber').val().length != 17){
		     $.messager.alert("错误信息","输入的车架号格式不正确","info");
		       //alert("输入的车牌号格式不正确");
		       return false;
		}
	});
	
	$('#qpTICAccidentDriverMobile').blur(function(){
			if($('#qpTICAccidentDriverMobile').val()==""){
			       return false;
			}
			var re=/^1[3|4|5|7|8]\d{9}$/;
			if($('#qpTICAccidentDriverMobile').val().search(re)==-1){
			   $.messager.alert("错误信息","输入的手机号格式不正确","info");
			     //alert("输入的手机号格式不正确");
			     return false;
  			 }
			drawPhoto();
	});
	
	$("#qpTICAccidentDriverName").blur(function(){
		if($("#qpTICAccidentInsured").val() == ''){
			$("#qpTICAccidentInsured").val($("#qpTICAccidentDriverName").val())
		}
	});
	
	function drawPhoto() {
		/* if($('#qpTICAccidentDriverVehicleNumber').val() == "") {
			$.messager.alert('提示信息','请输入车牌号码！','info');
			return ;
		}
		if($('#qpTICAccidentDriverMobile').val() == "") {
			$.messager.alert('提示信息','请输入手机号码！','info');
			return ;
		} */
		if($('#qpTICAccidentGroupId').val()==""){
			var url = contextRootPath + "/qp/qpticpicturegroup/queryForDraw.do";
			var data = "mobile=" + $('#qpTICAccidentDriverMobile').val() + "&licenseNo=" + $('#qpTICAccidentDriverVehicleNumber').val();
			$.ajax({  
		        type:"POST",  
		        url:url,  
		        async: true,
		        cache : false,
		        data: data,
		        dataType:"json",
		        global:false,   
		        success: function(result) {
					if("1" == result.flag) {
						$('#qpTICAccidentGroupId').val(result.groupId);
					}
					else if("2" == result.flag) {
						//openUploadPhotoWindow();
						$('#qpTICAccidentGroupId').val(result.groupId);
					}
					else if("0" == result.flag) {
						//$.messager.alert("提示信息",result.content,"info");
						return ;
					}
		        }
			});
		}
			
	}
	
	<%-- 打开上传照片对话框  --%>
	function openUploadPhotoWindow() {
		var data = "mobile=" + $('#qpTICAccidentDriverMobile').val() + "&licenseNo=" + $('#qpTICAccidentDriverVehicleNumber').val();
		$('#uploadPhotoWindow').window(
				{
					href: '${ctx}/qp/qpticpicturegroup/prepareDraw.do?'+data,
					cache: false
				}
			);
		$('#uploadPhotoWindow').window('open');
	}
	
	<%-- 打开上传照片对话框  --%>
	function openUploadWindow() {
			//url="${ctx}quote/initUpLoadImage.do?proposalNo="+proposalNo;
			var accidentAcciId = $('#qpTICAccidentAcciId').val();
			if(accidentAcciId == "") {
					$.messager.alert("提示信息","先保存当事人信息才能上传照片。","info");
					return false;
			}
			
			if("view" == '${actionType}') {
				$.messager.alert("提示信息","先保存当事人信息才能上传照片。","info");
				return false;
			}
			
			var url="${ctx}/qp/qpticpicture/prepareUploadPic.do?accidentAcciId="+accidentAcciId+"&SurveyGroupId=" +$('#qpTICAccidentSurveyGroupId').val();
	    	var iWidth= window.screen.width;// window.screen.width*0.5; //弹出窗口的宽度;
	    	var iHeight= window.screen.height; //window.screen.height*0.7; //弹出窗口的高度;
	    	//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
	    	var iTop = 0; //获得窗口的垂直位置;
	    	var iLeft = 0; //获得窗口的水平位置;

			var config = 'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no'; 
			//'help:no;dialogWidth:750px;dialogHeight:500px'
			window.open(url,"",config);
		}
	
	<%-- 打开查看照片对话框  --%>
	function openImageView() {
		//var accidentAcciId = $('#qpTICAccidentAcciId').val();
	var accidentGroupId = $('#qpTICAccidentGroupId').val();
		
		var url = "${ctx}/qp/qpticpicturegroup/viewPictureGroupPC.do?groupId="
			+ accidentGroupId;
	//var iWidth = window.screen.width * 0.4; //弹出窗口的宽度;
	//var iHeight = window.screen.height * 0.4; //弹出窗口的高度;
	//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
	//var iTop = (window.screen.height - 30 - iHeight) / 2; //获得窗口的垂直位置;
	//var iLeft = (window.screen.width - 20 - iWidth) / 2; //获得窗口的水平位置;
	var iWidth = window.screen.width; //弹出窗口的宽度;
	var iHeight = window.screen.height; //弹出窗口的高度;
	//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
	var iTop = 0; //获得窗口的垂直位置;
	var iLeft = 0; //获得窗口的水平位置;

	var config = 'height='
			+ iHeight
			+ ',width='
			+ iWidth
			+ ',top='
			+ iTop
			+ ',left='
			+ iLeft
			+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
	//'help:no;dialogWidth:750px;dialogHeight:500px'
	window.open(url, "", config);
}
	
		<%--  打开添加法规窗口  --%>
		function openAddLawWindow() {
			var iWidth = window.screen.width * 0.6; //弹出窗口的宽度;
			var iHeight = window.screen.height * 0.2; //弹出窗口的高度;
			//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
			var iTop = (window.screen.height - 30 - iHeight) / 2; //获得窗口的垂直位置;
			var iLeft = (window.screen.width - 20 - iWidth) / 2; //获得窗口的水平位置;
			var url = "${ctx}/pages/qp/qpttplaw/AddLaw.jsp";
			var config = 'height='
					+ iHeight
					+ ',width='
					+ iWidth
					+ ',top='
					+ iTop
					+ ',left='
					+ iLeft
					+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
			window.open(url, "", config);
		}
	
	
	<%-- 关闭窗口 --%>
	function closeUserWindow() {
		$('#resUserSetWindow').window('close');
	}
	
	<%-- 返回值 --%>
	function resUserSetWindow() {
		var sexCode = $('.qpTICAccidentDriverSex:checked').val();
		var sexName = sexCode=='0'?'男':'女';
		var accidentDriverLawId = $('#qpTICAccidentDriverLawId').combobox('getText')=='请选择'?'':$('#qpTICAccidentDriverLawId').combobox('getText');
		var accidentDriverLiability =  $('#qpTICAccidentDriverLiability').combobox('getText')=='请选择'?'':$('#qpTICAccidentDriverLiability').combobox('getText');
		if("${actionType}" == 'view'||"${businessType}"  == '2'){
			setData({
				data: {
					qpTTPCaseCaseId:$('#qpTTPCaseCaseId2').val(),
					/* qpTTPCaseCaseSerialNo:$('#qpTTPCaseCaseSerialNo2').val(), */
					qpTICAccidentAcciId:$('#qpTICAccidentAcciId').val(),
					qpTICAccidentDriverIDType:$('#qpTICAccidentDriverIDType').combobox('getText'),
					qpTICAccidentCoId:$('#qpTICAccidentCoId').combobox('getText'),
					qpTICAccidentCompanyNameOther:$('#qpTICAccidentCompanyNameOther').val(),
					qpTICAccidentDriverIDNumber:$('#qpTICAccidentDriverIDNumber').val(),
					qpTICAccidentDriverInsuNumber:$('#qpTICAccidentDriverInsuNumber').val(),
					qpTICAccidentDriverName:$('#qpTICAccidentDriverName').val(),
					qpTICAccidentDriverVehicleType:$('#qpTICAccidentDriverVehicleType').combobox('getText'),
					qpTICAccidentDriverSex:sexName,
					qpTICAccidentDriverVehicleNumber:$('#qpTICAccidentDriverVehicleNumber').val(),
					qpTICAccidentDriverMobile:$('#qpTICAccidentDriverMobile').val(),
					qpTICAccidentDriverPhone:$('#qpTICAccidentDriverPhone').val(),
					qpTICAccidentDriverDirection:$('#qpTICAccidentDriverDirection').combobox('getText'),
					qpTICAccidentAcciProvince:$('#qpTICAccidentAcciProvince').combobox('getText'),
					qpTICAccidentAcciCity:$('#qpTICAccidentAcciCity').combobox('getText'),
					qpTICAccidentAcciDistrict:$('#qpTICAccidentAcciDistrict').combobox('getText'),
					qpTICAccidentAcciStreet:$('#qpTICAccidentAcciStreet').val(),
					qpTICAccidentDriverLawId:$('#qpTICAccidentDriverLawId').combobox('getText'),
					qpTICAccidentDriverLiability:$('#qpTICAccidentDriverLiability').combobox('getText'),
					qpTICAccidentLabelType:$('#qpTICAccidentLabelType').val(),
					qpTICAccidentInsured:$('#qpTICAccidentInsured').val(),
					qpTICAccidentChassisNumber:$('#qpTICAccidentChassisNumber').val()
				}
			});
		}else{
			setData({
				data: {
					qpTTPCaseCaseId:$('#qpTTPCaseCaseId2').val(),
					/* qpTTPCaseCaseSerialNo:$('#qpTTPCaseCaseSerialNo2').val(), */
					qpTICAccidentAcciId:$('#qpTICAccidentAcciId').val(),
					qpTICAccidentDriverIDType:$('#qpTICAccidentDriverIDType').combobox('getText'),
					qpTICAccidentCoId:$('#qpTICAccidentCoId').combobox('getText'),
					qpTICAccidentCompanyNameOther:$('#qpTICAccidentCompanyNameOther').val(),
					qpTICAccidentDriverIDNumber:$('#qpTICAccidentDriverIDNumber').val(),
					qpTICAccidentDriverInsuNumber:$('#qpTICAccidentDriverInsuNumber').val(),
					qpTICAccidentDriverName:$('#qpTICAccidentDriverName').val(),
					qpTICAccidentDriverVehicleType:$('#qpTICAccidentDriverVehicleType').combobox('getText'),
					qpTICAccidentDriverSex:sexName,
					qpTICAccidentDriverVehicleNumber:$('#qpTICAccidentDriverVehicleNumber').val(),
					qpTICAccidentDriverMobile:$('#qpTICAccidentDriverMobile').val(),
					qpTICAccidentDriverPhone:$('#qpTICAccidentDriverPhone').val(),
					qpTICAccidentDriverDirection:$('#qpTICAccidentDriverDirection').combobox('getText'),
					qpTICAccidentAcciProvince:$('#qpTICAccidentAcciProvince').combobox('getText'),
					qpTICAccidentAcciCity:$('#qpTICAccidentAcciCity').combobox('getText'),
					qpTICAccidentAcciDistrict:$('#qpTICAccidentAcciDistrict').combobox('getText'),
					qpTICAccidentAcciStreet:$('#qpTICAccidentAcciStreet').val(),
					qpTICAccidentDriverLawId:accidentDriverLawId,
					qpTICAccidentDriverLiability:accidentDriverLiability,
					qpTICAccidentLabelType:$('#qpTICAccidentLabelType').val(),
					qpTICAccidentInsured:$('#qpTICAccidentInsured').val(),
					qpTICAccidentChassisNumber:$('#qpTICAccidentChassisNumber').val()
				}
			});
		}
	}
	
	<%-- 查看历史案件信息 --%>
	function viewHistory(field) {
		var paramValue = "";
		if(field == '1') {
			if($('#qpTICAccidentDriverIDNumber').val() == null || $('#qpTICAccidentDriverIDNumber').val() == "") {
				$.messager.alert("提示信息","请输入证件号！","info");
				return ;
			}else if($('#qpTICAccidentRiskTimes').val() == null ||$('#qpTICAccidentRiskTimes').val() == "0")
			{
				return;
			}
			paramValue = 'driverIDNumber=' + $('#qpTICAccidentDriverIDNumber').val();
		}else {
			if($('#qpTICAccidentDriverVehicleNumber').val() == null || $('#qpTICAccidentDriverVehicleNumber').val() == "") {
				$.messager.alert("提示信息","请输入车牌号码！","info");
				return ;
			}
			paramValue = 'driverVehicleNumber=' + $('#qpTICAccidentDriverVehicleNumber').val();
		}
		var url="${ctx}/qp/qpttpcase/prepareViewQuery.do?" + paramValue;
    	var iWidth= 1008;// window.screen.width*0.5; //弹出窗口的宽度;
    	var iHeight= 550; //window.screen.height*0.7; //弹出窗口的高度;
    	var iTop = (window.screen.height+10-iHeight)/2; //获得窗口的垂直位置;
    	var iLeft = (window.screen.width+20-iWidth)/2; //获得窗口的水平位置;

		var config = 'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no'; 
		window.open(url,"",config);
	}

	var parseIdentify = function (id) {
		var idxSexStart = id.length == 15 ? 14 : 16;
		var birthYearSpan = id.length == 15 ? 2 : 4;
		var res = {};
		var idxSex = 1 - id.substr(idxSexStart, 1) % 2;
		res.sex = idxSex == '1' ? '女' : '男';
		var year = (birthYearSpan == 2 ? '19' : '') + 
		         id.substr(6, birthYearSpan);
		var month = id.substr(6 + birthYearSpan, 2);
		var day = id.substr(8 + birthYearSpan, 2);
		res.birthday = year + '-' + month + '-' + day;
		return res;
	}
	
	<%-- 查询及带出当事人 --%>
	function queryAcciDriverInfo() {
		var qpTICAccidentDriverIDType= $('#qpTICAccidentDriverIDType').combobox('getValue');
		var qpTICAccidentDriverIDNumber = $('#qpTICAccidentDriverIDNumber').val();
		var res = {};
		if(qpTICAccidentDriverIDNumber == null || qpTICAccidentDriverIDNumber.trim() == '') {
			$.messager.alert("提示信息","证件号码不能为空！","info");
			return ;
		}
		if(qpTICAccidentDriverIDType == "6") {
			var resultMsg = isCardID(qpTICAccidentDriverIDNumber);
			if(resultMsg != "") {
				$.messager.alert("提示信息",resultMsg,"info");
				return ;
			}
			res = parseIdentify(qpTICAccidentDriverIDNumber);
			if (res.birthday) {
				$("#qpTICAccidentBirthDay").val(res.birthday)
			}
			if (res.sex == "女") {
				$("#qpTICAccidentDriverSex1").attr("checked","checked");
			} else {
				$("#qpTICAccidentDriverSex0").attr("checked","checked");
			}
			$.ajax({
				type:"get",
				url:contextRootPath + "/qp/qpticaccident/queryAcciIdBefore.do?number=" + qpTICAccidentDriverIDNumber,
				async:true,
				dataType:"json",
				success:function(result) {
					if (result.code == 200 && result.msg) {
						$.messager.alert("提示信息",result.msg,"info");
					}
				},
				error:function() {

				}
			})
		}
		
		var url = contextRootPath + "/qp/qpticaccident/queryAcciDriverInfo.do";
		var data = "driverIDType=" + qpTICAccidentDriverIDType + "&driverIDNumber=" + qpTICAccidentDriverIDNumber;
		
		$.ajax({  
	        type:"POST",  
	        url:url,  
	        async: true,
	        cache : false,
	        data: data,
	        dataType:"json",
	        global:false,   
	        success: function(result){
	        	if("N" == result.flag){
	        		$("#qpTICAccidentRiskTimes").attr("value","0");
	        	};
	        	
				if("Y" == result.flag) {
					console.log(result);
					$("#qpTICAccidentCoId").combobox("setValue",result.driverInfo.coId);
					$("#qpTICAccidentCompanyNameOther").attr("value",result.driverInfo.companyNameOther);
					
					var resDriverVehicleNumber = result.driverInfo.driverVehicleNumber.substring(1, 2);
					$("#qpTICAccidentDriverVehicleNumberPreS").combobox("setValue",resDriverVehicleNumber);
					
					$("#qpTICAccidentDriverInsuNumber").attr("value",result.driverInfo.driverInsuNumber);
					$("#qpTICAccidentDriverName").attr("value",result.driverInfo.driverName);
					$("#qpTICAccidentDriverVehicleType").combobox("setValue",result.driverInfo.driverVehicleType);
					if(result.driverInfo.driverSex == '0') {
						$("#qpTICAccidentDriverSex0").attr("checked","checked");
					}else {
						$("#qpTICAccidentDriverSex1").attr("checked","checked");
					}
					$("#qpTICAccidentDriverVehicleNumber").attr("value",result.driverInfo.driverVehicleNumber);
					$("#qpTICAccidentDriverMobile").attr("value",result.driverInfo.driverMobile);
					$("#qpTICAccidentDriverPhone").attr("value",result.driverInfo.driverPhone);
					$("#qpTICAccidentDriverDirection").combobox("setValue",result.driverInfo.driverDirection);
					
					$("#qpTICAccidentRiskTimes").attr("value",result.driverInfo.riskTimes);
					$("#qpTICAccidentPermissionDrive").combobox("setValue",result.driverInfo.permissionDrive);
					$('#qpTICAccidentLabelType').val(result.driverInfo.labelType);//厂牌类型
					$('#qpTICAccidentInsured').val(result.driverInfo.insured);//被保险人
					$('#qpTICAccidentChassisNumber').val(result.driverInfo.chassisNumber);//车架号
					
					/* $("#qpTICAccidentAcciProvince").combobox("setValue",result.driverInfo.acciProvince);
					
					$('#qpTICAccidentAcciCity').combobox('clear');
		 			$('#qpTICAccidentAcciDistrict').combobox('clear');
		 			$('#qpTICAccidentAcciRoad').combobox('clear');
		 			$('#qpTICAccidentAcciCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#qpTICAccidentAcciProvince').combobox('getValue'));
		 			
					
					$("#qpTICAccidentAcciCity").combobox("setValue",result.driverInfo.acciCity);
					
					$('#qpTICAccidentAcciDistrict').combobox('clear');
		 			$('#qpTICAccidentAcciRoad').combobox('clear');
		 			$('#qpTICAccidentAcciDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?cityId=' +$('#qpTICAccidentAcciCity').combobox('getValue'));
		 		
					
					$("#qpTICAccidentAcciDistrict").combobox("setValue",result.driverInfo.acciDistrict);
					
					$('#qpTICAccidentAcciRoad').combobox('clear');
		 			$('#qpTICAccidentAcciRoad').combobox('reload', '${ctx}/qp/qptcommon/getRoadList.do?districtId=' +$('#qpTICAccidentAcciDistrict').combobox('getValue'));
		 		
					
					$("#qpTICAccidentAcciRoad").combobox("setValue",result.driverInfo.acciRoad);
					
					$("#qpTICAccidentAcciStreet").attr("value",result.driverInfo.acciStreet); */
				}
	        }
		});
		
	}
	
	<%-- 检验身份证号 --%>
	function isCardID(sId) {
		sId = sId.toUpperCase();
		if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(sId))) {
			return "身份证格式错误！";
		}
		var aCity = {
			11 : "北京",
			12 : "天津",
			13 : "河北",
			14 : "山西",
			15 : "内蒙古",
			21 : "辽宁",
			22 : "吉林",
			23 : "黑龙江 ",
			31 : "上海",
			32 : "江苏",
			33 : "浙江",
			34 : "安徽",
			35 : "福建",
			36 : "江西",
			37 : "山东",
			41 : "河南",
			42 : "湖北",
			43 : "湖南",
			44 : "广东",
			45 : "广西",
			46 : "海南",
			50 : "重庆",
			51 : "四川",
			52 : "贵州",
			53 : "云南",
			54 : "西藏",
			61 : "陕西",
			62 : "甘肃",
			63 : "青海",
			64 : "宁夏",
			65 : "新疆",
			71 : "台湾",
			81 : "香港",
			82 : "澳门",
			91 : "国外"
		};
		if (aCity[parseInt(sId.substr(0, 2))] == null) {
			return "身份证格式错误！";
		}
		var len, re;
		len = sId.length;
		if (len == 15) {
			re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
			var arrSplit = sId.match(re); //检查生日日期是否正确  
			var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3]
					+ '/' + arrSplit[4]);
			var bGoodDay;
			bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
					&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
					&& (dtmBirth.getDate() == Number(arrSplit[4]));
			if (!bGoodDay) {
				return "身份证格式错误！";
			} else {
				var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,
						10, 5, 8, 4, 2);
				var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6',
						'5', '4', '3', '2');
				var nTemp = 0, i;
				sId = sId.substr(0, 6) + '19'
						+ sId.substr(6, sId.length - 6);
				for (i = 0; i < 17; i++) {
					nTemp += sId.substr(i, 1) * arrInt[i];
				}
				sId += arrCh[nTemp % 11];
				return "";
			}
		}
		if (len == 18) {
			re = new RegExp(
					/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
			var arrSplit = sId.match(re); //检查生日日期是否正确  
			var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/"
					+ arrSplit[4]);
			var bGoodDay;
			bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
					&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
					&& (dtmBirth.getDate() == Number(arrSplit[4]));
			if (!bGoodDay) {
				return "身份证格式错误！";
			} else {
				var valnum;
				var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,
						10, 5, 8, 4, 2);
				var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6',
						'5', '4', '3', '2');
				var nTemp = 0, i;
				for (i = 0; i < 17; i++) {
					nTemp += sId.substr(i, 1) * arrInt[i];
				}
				valnum = arrCh[nTemp % 11];
				if (valnum != sId.substr(17, 1)) {
					return "身份证格式错误！";
				}
				return "";
			}
		}
		return "身份证格式错误！";
	}
	
	<%-- 初始化车牌选项信息 --%>
	function initVehicle() {
		var cfg_VehicleNo_Addr = ["湘","京","津","沪","渝","蒙","新","藏","宁","桂","港","澳","黑","吉","辽","晋","冀","青","鲁","豫","苏","皖","浙","闽","赣","鄂","粤","琼","甘","陕","黔","滇","川","贵","云"];
		var cfg_VehicleNo_AddrSpec = ["军","空","海","北","沈","兰","济","南","广","成","甲","午","未","庚","己","辛","壬","寅","戌","辰","WJ"];
		var cfg_VehicleNo_AddrNext = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		var cfg_VehicleNo_AddrEnd = ["临","警","学","挂","领","试","警备"];  
		
		$("#qpTICAccidentDriverVehicleNumberPre").append("<option value=''></option>");
		for(var i=0;i<cfg_VehicleNo_Addr.length;i++) {
			$("#qpTICAccidentDriverVehicleNumberPre").append('<option value="' + cfg_VehicleNo_Addr[i] + '">' + cfg_VehicleNo_Addr[i] + '</option>');
		}
		for(var i=0;i<cfg_VehicleNo_AddrSpec.length;i++) {
			$("#qpTICAccidentDriverVehicleNumberPre").append('<option value="' + cfg_VehicleNo_AddrSpec[i] + '">' + cfg_VehicleNo_AddrSpec[i] + '</option>');
		}
		
		$("#qpTICAccidentDriverVehicleNumberPreS").append("<option value=''></option>");
		for(var i=0;i<cfg_VehicleNo_AddrNext.length;i++) {
			$("#qpTICAccidentDriverVehicleNumberPreS").append('<option value="' + cfg_VehicleNo_AddrNext[i] + '">' + cfg_VehicleNo_AddrNext[i] + '</option>');
		}
		
		$("#qpTICAccidentDriverVehicleNumberPreE").append("<option value=''></option>");
		for(var i=0;i<cfg_VehicleNo_AddrEnd.length;i++) {
			$("#qpTICAccidentDriverVehicleNumberPreE").append('<option value="' + cfg_VehicleNo_AddrEnd[i] + '">' + cfg_VehicleNo_AddrEnd[i] + '</option>');
		}
		
	}
	
	<%-- 初始化证件号码，为身份证时，控制长度为18位 --%>
	function initIdCard() {
		if($('#qpTICAccidentDriverIDType').val() == '6') {
			$("#qpTICAccidentDriverIDNumber").attr("maxlength","18");
		}else {
			$("#qpTICAccidentDriverIDNumber").removeAttr("maxlength");
		}
	}
	
	function displayMap() {
		var province = $('#qpTICAccidentAcciProvince').combobox('getText');
		var city = $('#qpTICAccidentAcciCity').combobox('getText');
		var district = $('#qpTICAccidentAcciDistrict').combobox('getText');
		if (province != null && province != "" && city != null && city != ""
				&& district != null && district != "") {
			var detailAddress = province + city + district;
			$("#qpTICAccidentAcciStreet").val(detailAddress);
		}
	}
	
	<%-- 查看图片 --%>
	function showPicture(){
		var caseUrl = contextRootPath + "/qp/qpticpicturegroup/viewPictureGroupPC.do?groupId=" +$("#qpTICAccidentSurveyGroupId").val();
		window.open(caseUrl,"_blank","top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no");
    }
	
	function printPreview(isPrint) {
		var printUrl = contextRootPath + "/qp/qpttpcase/printPersonCaseDetailPreview.do?accID=" + $("#qpTICAccidentAcciId").val() + "&caseID=" + $("#qpTTPCaseCaseId2").val() + "&isPrint=" + isPrint;
		window.open(printUrl, "__blank");
		//window.open(printUrl, "__blank", "top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no");
	}
	
	</script>

</body>
</html>