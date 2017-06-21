<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPFastCenter"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qpttpfastcenter/QpTTPFastCenter.js"></script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/qp/qpttpfastcenter"
		namespace="/qp/qpttpfastcenter" method="post">

		<table class="fix_table">
			<tr>
				<td colspan="4" align="center">
					<h2 align="center">
						<c:choose>
							<c:when test="${operateType == 'update'}">
					<h2>修改信息</h2>
				</c:when>
							<c:when test="${operateType == 'add'}">
					<h2>增加信息</h2>
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看信息
				</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
 

<!--  
  <div style="display: none;">
	<td class="bgc_tt short">
		中心ID
	</td>
	</div>
-->
	  	<td >
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
				<input type="hidden" name="qpTTPFastCenter.id.centerId" id="qpTTPFastCenter.id.centerId" value="${qpTTPFastCenter.id.centerId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input type="hidden" class='input_w w_15' name="qpTTPFastCenter.id.centerId" id="qpTTPFastCenter.id.centerId" value="${qpTTPFastCenter.id.centerId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
				<input type="hidden" name="qpTTPFastCenter.id.centerId" id="qpTTPFastCenter.id.centerId" value="${qpTTPFastCenter.id.centerId}">
			</c:when>
		</c:choose>
		</td>
		
		
	

	
	<tr>
	<!--  
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.centerNumber" id="qpTTPFastCenter.centerNumber" value="${qpTTPFastCenter.centerNumber}">
		</td>
	-->
	<input type="hidden" class='input_w w_15' name="qpTTPFastCenter.centerNumber" id="qpTTPFastCenter.centerNumber" value="${qpTTPFastCenter.centerNumber}">
	
	<td class="bgc_tt short">
		名称
	</td>
		<td class="long">
			<input class='input_w w_15 easyui-validatebox' required="true"  name="qpTTPFastCenter.centerName" id="qpTTPFastCenter.centerName" value="${qpTTPFastCenter.centerName}">
		</td>
	<td class="bgc_tt short">
		电话
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.centerPhone" id="qpTTPFastCenter.centerPhone" value="${qpTTPFastCenter.centerPhone}">
		</td>
	</tr>
	
	<tr>
	<td class="bgc_tt short">
		备注
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.centerNotes" id="qpTTPFastCenter.centerNotes" value="${qpTTPFastCenter.centerNotes}">
		</td>
	<td class="bgc_tt short">所属城市</td>
		<td class="long">			
			<select class='input_w w_15' name="qpTTPFastCenter.cityId"   id="qpTTPFastCenter.cityId">
				<!-- <option value="">-请选择-</option>  -->  
				<c:forEach var="city" items="${citylist }">
								<c:if test="${city.cityId==userCityId}">
									<option value="${city.cityId}">${city.cityName}</option>
								</c:if>
				</c:forEach>
			</select>
	</td>
	</tr>
	<tr>
		<td class="bgc_tt short">
			快赔点经度
		</td>
			<td class="long">
				<input class='input_w w_15' name="qpTTPFastCenter.longitude" id="qpTTPFastCenter.longitude" value="${qpTTPFastCenter.longitude}">
			</td>
		<td class="bgc_tt short">
		年份
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.curYear" id="qpTTPFastCenter.curYear" value="${qpTTPFastCenter.curYear}">
		</td>
	</tr>
	<tr>
	
	<td class="bgc_tt short">
		快赔点纬度
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.latitude" id="qpTTPFastCenter.latitude" value="${qpTTPFastCenter.latitude}">
		</td>
	<td class="bgc_tt short">
		编码
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPFastCenter.curSerialNo" id="qpTTPFastCenter.curSerialNo" value="${qpTTPFastCenter.curSerialNo}">
		</td>
	</tr>
							
							<!--  
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTTPFastCenter.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTTPFastCenter.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTTPFastCenter.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTTPFastCenter.operateTimeForHis}			</td>
							</tr>
							<tr>
							-->
			<tr>				
	<td class="bgc_tt short">
	有效状态
	</td>
		<td class="long">
		<!--  
			<input class='input_w w_15' name="qpTTPFastCenter.validStatus" id="qpTTPFastCenter.validStatus" value="${qpTTPFastCenter.validStatus}">
		-->
		<select class='input_w w_15' name="qpTTPFastCenter.validStatus" id="qpTTPFastCenterValidStatus" required="true" value="${qpTTPFastCenter.validStatus}">
				<option value="">-选择状态-</option>
				<option value="1" <c:if test="${qpTTPFastCenter.validStatus==1}">selected</c:if>>是</option>
				<option value="0" <c:if test="${qpTTPFastCenter.validStatus==0}">selected</c:if>>否</option>
			</select>
		</td>
	
	<td class="bgc_tt short">
		认字号编号
	</td>
		<td class="long">
			<input class='input_w w_15'   name="qpTTPFastCenter.readNum" id="qpTTPFastCenter.readNum" value="${qpTTPFastCenter.readNum}">
		</td>
	</tr>
	
	<tr>
		<td class="bgc_tt short">
			所属地市
		</td>
		<td>
		<select id="qpTTPFastCenterCities" name="qpTTPFastCenter.cities"    class="input_w w_15">
									<option value="">请选择</option>
								<c:forEach var="cities" items="${citiesList}">
									<option value="${cities.codeCode}" <c:if test="${cities.codeCode==qpTTPFastCenter.cities}">selected</c:if>>${cities.codeCName}</option>
								</c:forEach>
							</select>
		</td>
	</tr>	
	
		</table>
		<br>

	<center>
		<table>
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" />
				</td>
			</tr>
		</table>
	</center>
	</form>
</body>
</html>
