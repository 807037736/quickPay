<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPCase"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qpttpcasestat/QpTTPCaseStat.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<input type="hidden" name=fieldList>
				<table class="fix_table" style="width: 100%;">
					<tr>
						<td width="10%" class="bgc_tt short"  align="right">事发时间：</td>
						<td width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.caseTimeStart" id="qpTTPCaseStatVO.caseTimeStart" 
							onclick="WdatePicker({skin:'whyGreen'})"></td>
						<td  width="10%" class="bgc_tt short" align="right">至：</td>
						<td  width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.caseTimeEnd" id="qpTTPCaseStatVO.caseTimeEnd" 
							 onclick="WdatePicker({skin:'whyGreen'})"></td>
						<td width="10%" class="bgc_tt short"  align="right">受理时间：</td>
						<td width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.TPHandleTimeStart" id="qpTTPCaseStatVO.TPHandleTimeStart" 
							value="${qpTTPCaseStatVO.TPHandleTimeStart}" onclick="WdatePicker({skin:'whyGreen'})"></td>
						<td  width="10%" class="bgc_tt short"   align="right">至：</td>
						<td  width="10%" ><input class='input_w w_22'
							name="qpTTPCaseStatVO.TPHandleTimeEnd" id="qpTTPCaseStatVO.TPHandleTimeEnd" 
							onclick="WdatePicker({skin:'whyGreen'})"></td>
					</tr>
					<%-- <tr>
						<td width="10%" align="right">证件类型：</td>
						<td width="10%">
							<select id="qpTTPCaseDriverIDType" name="qpTTPCaseStatVO.driverIDType" editable="false" class="input_w w_22 easyui-combobox">
									<option value="">请选择</option>
								<c:forEach var="identifyType" items="${identifyTypeList}">
									<option value="${identifyType.codeCode}">${identifyType.codeCName}</option>
								</c:forEach>
							</select>
							</td>
						<td  width="10%" align="right">证件号码：</td>
						<td  width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.driverIDNumber" id="qpTTPCaseStatVO.driverIDNumber" ></td>
						<td width="10%" align="right">当事人姓名：</td>
						<td width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.driverName" id="qpTTPCaseStatVO.driverName" ></td>
						<td  width="10%" align="right">车牌号：</td>
						<td  width="10%" ><input class='input_w w_22'
							name="qpTTPCaseStatVO.driverVehicleNumber" id="qpTTPCaseStatVO.driverVehicleNumber" ></td>
					</tr> --%>
					<%-- <tr>
						<td width="10%" align="right">警员姓名：</td>
						<td width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.TPUserName" id="qpTTPCaseStatVO.TPUserName" ></td>
						<td  width="10%" align="right">认字编号：</td>
						<td  width="10%"><input class='input_w w_22'
							name="qpTTPCaseStatVO.CaseSerialNo" id="qpTTPCaseStatVO.CaseSerialNo" ></td>
						<td width="10%" align="right">天气：</td>
						<td width="10%">
							<select id="qpTTPCaseCaseWeather" name="qpTTPCaseStatVO.CaseWeather" editable="false"  class="input_w w_22 easyui-combobox">
							<option value="">请选择</option>
								<c:forEach var="weather" items="${weatherList}">
									<option value="${weather.codeCode}">${weather.codeCName}</option>
								</c:forEach>
							</select>
							</td>
						<td  width="10%" align="right">受理点：</td>
						<td  width="10%" >
							<select id="qpTTPCaseCenterId" name="qpTTPCaseStatVO.CenterId" editable="false" class="input_w w_22 easyui-combobox"
							<c:if test="${not empty qpTTPCaseStatVO.centerId }">readonly</c:if> >
									<option value="">请选择</option>
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.id.centerId}"
									<c:if test="${fastCenter.id.centerId==qpTTPCaseStatVO.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
							</select>
						</td>
					</tr> --%>
					<%-- <tr>
						<td width="10%" align="right">保险公司：</td>
						<td width="10%">
							<select id="qpTTPCaseCoId" name="qpTTPCaseStatVO.coId" class="input_w w_22 easyui-combobox">
								<option value="">请选择</option>
								<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
									<option value="${qpTICCompany.coId}">${qpTICCompany.coName}</option>
								</c:forEach>
							</select></td>
						<td  width="10%" align="right"></td>
						<td  width="10%"></td>
						<td  width="10%" align="right"></td>
						<td  width="10%"></td>
						<td  width="10%" align="right"></td>
						<td  width="10%"></td>
					</tr> --%>
				</table>
					<hr style="BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted" color=#111111 size=1>
				<table class="fix_table"  style="width: 100%;">
				<tr>
						<td class="bgc_tt short"  align="right">统计方式：</td>
						<td>
							<select class="input_w" name="qpTTPCaseStatVO.statType_1" id="qpTTPCaseStatVO.statType_1">
							<option selected="selected" value="">---请选择---</option>
									<option value="ByDate">日期</option>
									<option value="ByCity" selected="selected">市</option>
									<option value="ByDistrict">区</option>
									<!-- <option value="ByRoad">主干道</option> -->
									<!-- <option value="ByStreet">街道</option> -->
									<option value="ByTimeSpan">时间段</option>
									<option value="ByDriverSex">性别</option>
									<option value="ByWeather">天气状况</option>
									<option value="ByfastCenter">受理点</option>
									<option value="ByCompany">保险公司</option>
									<option value="ByLaw">违反法律法规</option>
								</select>
								&nbsp;
						    <select  class="input_w" name="qpTTPCaseStatVO.statType_2" id="qpTTPCaseStatVO.statType_2">
							<option selected="selected" value="">---请选择---</option>
									<option value="ByDate">日期</option>
									<option value="ByCity">市</option>
									<option value="ByDistrict">区</option>
									<!-- <option value="ByRoad">主干道</option> -->
									<!-- <option value="ByStreet">街道</option> -->
									<option value="ByTimeSpan">时间段</option>
									<option value="ByDriverSex">性别</option>
									<option value="ByWeather">天气状况</option>
									<option value="ByfastCenter">受理点</option>
									<option value="ByCompany">保险公司</option>
									<option value="ByLaw">违反法律法规</option>
								
								</select>
								<span style="color: red">*  此处选项为统计案件的必选参数，请至少选择一项后进行统计</span>
					    </td>
						<td>
						<input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="expExcelButton" onclick="expExcel();"
							class="button_ty" value="导出Excel"></td>
					</tr>
					</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTTPCaseTable"></table>
</body>
</html>
