<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTFLOW" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptflow/QpTFLOW.js"></script>
<script type="text/javascript">
$(document)
.ready(
		function() {
			/* $('#addDriverInfo').bind('click', function() {
				openResUserSetWindow();
			}); */
			/* $("#qpTFLOW_proviceId").append("<option value=''>=请选择=</option>");
			$("#qpTFLOW_cityId").append("<option value=''>=请选择=</option>"); */
			
			$('#qpTFLOW_proviceId')
					.combobox(
							{
								url : '${ctx}/qp/qptcommon/getProvinceList.do',
								valueField : 'provId',
								textField : 'provName',
								onSelect : function() {
							
									$('#qpTFLOW_cityId').combobox('clear'); 
									/* $('#qpTTPCaseCaseDistrict')
											.combobox('clear'); */
									
									$('#qpTFLOW_cityId').combobox(
													'reload',
													'${ctx}/qp/qptcommon/getCityList.do?provId='
															+ $(
																	'#qpTFLOW_proviceId')
																	.combobox(
																			'getValue'));
									
								},
								onUnselect : function() {
									$('#qpTFLOW_cityId')
											.combobox('clear');
									/* $('#qpTTPCaseCaseDistrict')
											.combobox('clear'); */
									
									$('#qpTFLOW_cityId')
											.combobox(
													'reload',
													'${ctx}/qp/qptcommon/getCityList.do?provId='
															+ $(
																	'#qpTFLOW_proviceId')
																	.combobox(
																			'getValue'));
								}
							});
			
			$('#qpTFLOW_cityId')
			.combobox(
					{
						url : '${ctx}/qp/qptcommon/getCityList.do',
						valueField : 'cityId',
						textField : 'cityName'
					});
			
		
			
					/*  $("#qpTFLOW_proviceId").combobox("setValue",'${qpTTPCase_caseProvince}');
					 $('#qpTFLOW_cityId').combobox(
							'reload',
							'${ctx}/qp/qptcommon/getCityList.do?provId='+ $('#qpTFLOW_proviceId').combobox('getValue'));
					$("#qpTFLOW_cityId").combobox("setValue",'${qpTTPCase_caseCity}'); */
		});
</script>
</head>
<body>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
<%-- 							<tr>
	<td class="bgc_tt short">
		流程id
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
		</td>
	<td class="bgc_tt short">
		省份id
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.proviceId" id="qpTFLOW.proviceId" value="${qpTFLOW.proviceId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		城市id
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.cityId" id="qpTFLOW.cityId" value="${qpTFLOW.cityId}">
		</td>
	<td class="bgc_tt short">
		交警处理
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.policePro" id="qpTFLOW.policePro" value="${qpTFLOW.policePro}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		录入人
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.inputPerson" id="qpTFLOW.inputPerson" value="${qpTFLOW.inputPerson}">
		</td>
	<td class="bgc_tt short">
		录入时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTFLOW.inputTime" id="qpTFLOW.inputTime" value="${qpTFLOW.inputTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.updatePerson" id="qpTFLOW.updatePerson" value="${qpTFLOW.updatePerson}">
		</td>
	<td class="bgc_tt short">
		修改时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTFLOW.updateTime" id="qpTFLOW.updateTime" value="${qpTFLOW.updateTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTFLOW.validStatus" id="qpTFLOW.validStatus" value="${qpTFLOW.validStatus}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
							 </tr> --%>
							 
		<tr>
			<td class="bgc_tt short">
				省份
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.proviceId" id="qpTFLOW.proviceId" value="${qpTFLOW.proviceId}"> --%>
				
				<select class="input_w w_15 easyui-validatebox" name="qpTFLOW.proviceId" id="qpTFLOW_proviceId">
						<option value="">-请选择-</option>   
						 <%--<c:forEach var="provice" items="${provicelist }">
							<option value="${provice.provId}">${provice.provName}</option>
						</c:forEach> --%> 
				</select>
			</td>
			
			<td class="bgc_tt short">
				城市
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.cityId" id="qpTFLOW.cityId" value="${qpTFLOW.cityId}"> --%>
				<select class='input_w w_15' name="qpTFLOW.cityId" id="qpTFLOW_cityId">
						<%--<option value="">-请选择-</option>   
						 <c:forEach var="city" items="${citylist }">
							<option value="${city.cityId}">${city.cityName}</option>
						</c:forEach> --%>
				</select>
			</td>
		</tr>
		
		<tr>
			<td class="bgc_tt short">
				交警处理
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.policePro" id="qpTFLOW.policePro" value="${qpTFLOW.policePro}"> --%>
				<select class='input_w w_15' name="qpTFLOW.policePro" id="qpTFLOW.policePro" value="${qpTFLOW.policePro}">
					<option value="">-选择状态-</option>
					<option value="1">有</option>
					<option value="0">无</option>
				</select>
			</td>
			
			<td class="bgc_tt short">
				有效状态
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.validStatus" id="qpTFLOW.validStatus" value="${qpTFLOW.validStatus}"> --%>
				<select class='input_w w_15' name="qpTFLOW.validStatus" id="qpTFLOW.validStatus" value="${qpTFLOW.policePro}">
					<option value="">-选择状态-</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
		</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTFLOWTable"></table>
</body>
</html>
