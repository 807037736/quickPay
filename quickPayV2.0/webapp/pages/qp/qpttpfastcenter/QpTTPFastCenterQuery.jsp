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
				<!--  
					<tr>
						<td class="bgc_tt short">中心ID</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.id.centerId"
							id="qpTTPFastCenter.id.centerId"
							value="${qpTTPFastCenter.id.centerId}"></td>
						<td class="bgc_tt short">名称</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerName" id="qpTTPFastCenter.centerName"
							value="${qpTTPFastCenter.centerName}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">序号</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerNumber"
							id="qpTTPFastCenter.centerNumber"
							value="${qpTTPFastCenter.centerNumber}"></td>
						<td class="bgc_tt short">电话</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerPhone"
							id="qpTTPFastCenter.centerPhone"
							value="${qpTTPFastCenter.centerPhone}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">备注</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerNotes"
							id="qpTTPFastCenter.centerNotes"
							value="${qpTTPFastCenter.centerNotes}"></td>
						<td class="bgc_tt short">所属城市</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.cityId" id="qpTTPFastCenter.cityId"
							value="${qpTTPFastCenter.cityId}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">年份</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.curYear" id="qpTTPFastCenter.curYear"
							value="${qpTTPFastCenter.curYear}"></td>
						<td class="bgc_tt short">编码</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.curSerialNo"
							id="qpTTPFastCenter.curSerialNo"
							value="${qpTTPFastCenter.curSerialNo}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.validStatus"
							id="qpTTPFastCenter.validStatus"
							value="${qpTTPFastCenter.validStatus}"></td>
						<td class="bgc_tt short"></td>
						<td class="long"></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>
					</tr>
					-->
					
					<tr>
						<td class="bgc_tt short">名称</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerName" id="qpTTPFastCenter.centerName"
							value="${qpTTPFastCenter.centerName}"></td>
									
						<td class="bgc_tt short">备注</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerNotes"
							id="qpTTPFastCenter.centerNotes"
							value="${qpTTPFastCenter.centerNotes}"></td>
					</tr>
					
					<tr>
						<td class="bgc_tt short">电话</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerPhone"
							id="qpTTPFastCenter.centerPhone"
							value="${qpTTPFastCenter.centerPhone}"></td>
							
							<td class="bgc_tt short">序号</td>
						<td class="long"><input class='input_w w_15'
							name="qpTTPFastCenter.centerNumber"
							id="qpTTPFastCenter.centerNumber"
							value="${qpTTPFastCenter.centerNumber}"></td>
					</tr>
				
					<tr>
					<td class="bgc_tt short">所属城市</td>
						<td class="long">			
							<select class='input_w w_15' disabled="disabled" name="qpTTPFastCenter.cityId" id="qpTTPFastCenter.cityId">
								<!-- <option value="">-请选择-</option>   -->
								<c:forEach var="city" items="${citylist }">
								<c:if test="${city.cityId==userCityId}">
									<option value="${city.cityId}">${city.cityName}</option>
								</c:if>
								</c:forEach>
							</select>
							<input type=hidden name="qpTTPFastCenter.cityId" value='${userCityId}'/>
						</td>
					
							<td class="bgc_tt short">有效状态</td>
							<td class="long">
								<select class='input_w w_15' name="qpTTPFastCenter.validStatus" id="qpTTPFastCenter.validStatus" value="${qpTTPFastCenter.validStatus}">
									<option value="">-选择状态-</option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
					</tr>
					<tr>
						<td class="bgc_tt short">所属地市</td>
						<td  class="long">
							<select id="qpTTPFastCenter.cities" name="qpTTPFastCenter.cities"    class="input_w w_15">
								<option value="">请选择</option>
								<c:forEach var="cities" items="${citiesList}">
									<option value="${cities.codeCode}" >${cities.codeCName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr> 
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>	
					</tr>
					
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="QpTTPFastCenterTable"></table>
</body>
</html>
