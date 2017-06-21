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
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
	
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
				
			
				
						 $("#qpTFLOW_proviceId").combobox("setValue",'${qpTFLOW.proviceId}');
						  $('#qpTFLOW_cityId').combobox(
								'reload',
								'${ctx}/qp/qptcommon/getCityList.do?provId='+ $('#qpTFLOW_proviceId').combobox('getValue'));  
						$("#qpTFLOW_cityId").combobox("setValue",'${qpTFLOW.cityId}'); 
				
			});
				
						
						
	
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/qp/qptflow" namespace="/qp/qptflow" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTFLOW信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTFLOW信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTFLOW信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
			
		<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTFLOW.id.flowId}					<input type="hidden" name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input type="hidden" class='input_w w_15' name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTFLOW.id.flowId}					<input type="hidden" name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
		</c:choose>
			
			<%--
							<tr>
	<td class="bgc_tt short">
		流程id
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTFLOW.id.flowId}					<input type="hidden" name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input type="hidden" class='input_w w_15' name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTFLOW.id.flowId}					<input type="hidden" name="qpTFLOW.id.flowId" id="qpTFLOW.id.flowId" value="${qpTFLOW.id.flowId}">
			</c:when>
		</c:choose>
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
		<td class="long">
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
		<td class="long">
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
				
				<select class='input_w w_15' name="qpTFLOW.proviceId" id="qpTFLOW_proviceId" >
						<%-- <option value="">-请选择-</option>   
						<c:forEach var="provice" items="${provicelist }">
							<option value="${provice.provId}" <c:if test="${provice.provId==qpTFLOW.proviceId}">selected</c:if>>${provice.provName}</option>
						</c:forEach> --%>
				</select>
			</td>
			
			<td class="bgc_tt short">
				城市
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.cityId" id="qpTFLOW.cityId" value="${qpTFLOW.cityId}"> --%>
				<select class='input_w w_15' name="qpTFLOW.cityId" id="qpTFLOW_cityId">
						<%-- <option value="">-请选择-</option>   
						<c:forEach var="city" items="${citylist }">
							<option value="${city.cityId}" <c:if test="${city.cityId==qpTFLOW.cityId}">selected</c:if>>${city.cityName}</option>
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
					<option value="1" <c:if test="${qpTFLOW.policePro==1}">selected</c:if>>有</option>
					<option value="0" <c:if test="${qpTFLOW.policePro==0}">selected</c:if>>无</option>
				</select>
			</td>
			
			<td class="bgc_tt short">
				有效状态
			</td>
			<td class="long">
				<%-- <input class='input_w w_15' name="qpTFLOW.validStatus" id="qpTFLOW.validStatus" value="${qpTFLOW.validStatus}"> --%>
				<select class='input_w w_15' name="qpTFLOW.validStatus" id="qpTFLOW.validStatus" value="${qpTFLOW.validStatus}">
					<option value="">-选择状态-</option>
					<option value="1" <c:if test="${qpTFLOW.validStatus==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${qpTFLOW.validStatus==0}">selected</c:if>>无效</option>
				</select>
			</td>
		</tr>				 
							 
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
