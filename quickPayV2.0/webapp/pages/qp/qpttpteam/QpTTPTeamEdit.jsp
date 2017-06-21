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
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
		
		if($('#operateType').val()=='add'){
			var cName = $("#cityId").find("option:selected").text(); 
			$("#cityName").val(cName);
		} 
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/qp/qpttpteam" namespace="/qp/qpttpteam" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTTPTeam信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTTPTeam信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTTPTeam信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short" style="display: none">
		大队ID
	</td>
	  	<td class="long" style="display: none">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTTPTeam.id.teamId}					<input type="hidden" name="qpTTPTeam.id.teamId" id="qpTTPTeam.id.teamId" value="${qpTTPTeam.id.teamId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTTPTeam.id.teamId" id="qpTTPTeam.id.teamId" value="${qpTTPTeam.id.teamId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTTPTeam.id.teamId}					<input type="hidden" name="qpTTPTeam.id.teamId" id="qpTTPTeam.id.teamId" value="${qpTTPTeam.id.teamId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		交警大队
	</td>
		<td class="long">
			<input class='input_w w_15 easyui-validatebox' required="true"  name="qpTTPTeam.teamName" id="qpTTPTeam.teamName" value="${qpTTPTeam.teamName}">
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
	<td class="bgc_tt short" >
		备注信息
	</td>
		<td class="long" >
			<input class='input_w w_15' name="qpTTPTeam.teamNotes" id="qpTTPTeam.teamNotes" value="${qpTTPTeam.teamNotes}">
		</td>
	<td class="bgc_tt short" style="display: none">
		创建人代码
	</td>
			<td class="long" style="display: none">
${qpTTPTeam.creatorCode}			</td>
							
	<td class="bgc_tt short" style="display: none">
		创建时间
	</td>
			<td class="long" style="display: none">
${qpTTPTeam.insertTimeForHis}			</td>
	<td class="bgc_tt short" style="display: none">
		修改人代码
	</td>
			<td class="long" style="display: none">
${qpTTPTeam.updaterCode}			</td>
							
	<td class="bgc_tt short" style="display: none">
		修改时间
	</td>
			<td class="long" style="display: none"> 
${qpTTPTeam.operateTimeForHis}			</td>
	
	<td class="bgc_tt short">是否有效</td>
		<td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
				cssClass='input_w w_15' value="qpTTPTeam.validStatus"
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
							 
		<tr>
		    <input type="hidden" name="qpTTPTeam.cityName" value="${qpTTPTeam.cityName}" id="cityName"/>
		    <td class="bgc_tt short">所属城市</td>
		<td class="long">			
			<select class='input_w w_15 easyui-combobox' editable="false" name="qpTTPTeam.cityId" id="cityId" value="${qpTTPTeam.cityId}" 
			<c:if test="${not empty qpTTPTeam.cityId }">readonly</c:if>    >
			 
				<option value="">请选择</option>   
				<c:forEach var="city" items="${citylist }">
					<option value="${city.cityId}"  <c:if test="${city.cityId==qpTTPTeam.cityId}">selected</c:if>> ${city.cityName}</option>
				</c:forEach>
				
			</select>
		</td>
		</tr>
		<tr>
		<td class="bgc_tt short">是否地市</td>
		<td class="long">
			<select class='input_w w_15' name="qpTTPTeam.IsHighway" id="qpTTPTeam.IsHighway" >
			<option value=0 <c:if test="${qpTTPTeam.isHighway=='0'}"> selected='selected'</c:if>>地市</option>
			<option value=1 <c:if test="${qpTTPTeam.isHighway=='1'}"> selected='selected'</c:if>>高速</option>
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
