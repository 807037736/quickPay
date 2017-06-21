<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPTeam" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttpteam/QpTTPTeam.js"></script>
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
							<tr>
	<td class="bgc_tt short" style="display: none">
		大队ID
	</td>
	  	<td  class="long" style="display: none">
			<input class='input_w w_15' name="qpTTPTeam.id.teamId" id="qpTTPTeam.id.teamId" value="${qpTTPTeam.id.teamId}">
		</td>
	<td class="bgc_tt short">
		交警大队
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPTeam.teamName" id="qpTTPTeam.teamName" value="${qpTTPTeam.teamName}">
		</td>
							
	<td class="bgc_tt short" style="display: none">
		序号
	</td>
		<td class="long" style="display: none">
			<input class='input_w w_15' name="qpTTPTeam.teamOrder" id="qpTTPTeam.teamOrder" value="${qpTTPTeam.teamOrder}">
		</td>
	<td class="bgc_tt short">
		联系电话
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPTeam.teamPhone" id="qpTTPTeam.teamPhone" value="${qpTTPTeam.teamPhone}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short" style="display: none">
		备注信息
	</td>
		<td class="long" style="display: none">
			<input class='input_w w_15' name="qpTTPTeam.teamNotes" id="qpTTPTeam.teamNotes" value="${qpTTPTeam.teamNotes}">
		</td>
		
		
		<td class="bgc_tt short">所属城市</td>
		<td class="long">			
			<select class='input_w w_15 easyui-combobox' editable="false" name="qpTTPTeam.cityId" id="qpTTPTeam.cityId" 
			<c:if test="${not empty qpTTPTeam.cityId }">readonly</c:if>    >
			 
				<option value="">请选择</option>   
				<c:forEach var="city" items="${citylist }">
					<option value="${city.cityId}" <c:if test="${city.cityId==qpTTPTeam.cityId}">selected</c:if>>${city.cityName}</option>
				</c:forEach>
				
			</select>
		</td>
		
		<%-- <td  width="10%" align="right">受理点：</td>
						<td  width="10%" >
							<select id="qpTTPCaseCenterId" name="qpTTPCase.centerId" editable="false" class="input_w w_22 easyui-combobox"
							<c:if test="${not empty qpTTPCase.centerId }">readonly</c:if> >
									<option value="0">请选择</option> 
								<c:forEach var="fastCenter" items="${fastCenterList}">
									<option value="${fastCenter.id.centerId}" <c:if test="${fastCenter.id.centerId==qpTTPCase.centerId}">selected</c:if>>${fastCenter.centerName}</option>
								</c:forEach>
							</select>
						</td> --%>
		
		

		<td class="bgc_tt short">是否有效</td>
		<td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
				cssClass='input_w w_15'  value="qpTTPTeam.validStatus" 
				name="qpTTPTeam.validStatus" id="qpTTPTeam.validStatus" />
		</td>
		<td class="bgc_tt short"></td>
		<td class="long"></td>
	
			
	<%-- <td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPTeam.validStatus" id="qpTTPTeam.validStatus" value="${qpTTPTeam.validStatus}">
		</td> --%>
		</tr> 	
		<tr><td class="bgc_tt short">是否地市</td>
		<td class="long">
			<select class='input_w w_15' name="qpTTPTeam.IsHighway" id="qpTTPTeam.IsHighway" <%-- value="${qpTTPTeam.IsHighway}" --%>>
			<option value="0">地市</option>
			<option value="1">高速</option>
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
	<table id="QpTTPTeamTable"></table>
</body>
</html>
