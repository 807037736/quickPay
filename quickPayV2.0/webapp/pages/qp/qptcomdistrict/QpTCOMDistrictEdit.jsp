<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMDistrict" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomdistrict/QpTCOMDistrict.js"></script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/qp/qptcomdistrict" namespace="/qp/qptcomdistrict" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTCOMDistrict信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTCOMDistrict信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTCOMDistrict信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		区县ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTCOMDistrict.id.districtId}					<input type="hidden" name="qpTCOMDistrict.id.districtId" id="qpTCOMDistrict.id.districtId" value="${qpTCOMDistrict.id.districtId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTCOMDistrict.id.districtId" id="qpTCOMDistrict.id.districtId" value="${qpTCOMDistrict.id.districtId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTCOMDistrict.id.districtId}					<input type="hidden" name="qpTCOMDistrict.id.districtId" id="qpTCOMDistrict.id.districtId" value="${qpTCOMDistrict.id.districtId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		区县名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.districtName" id="qpTCOMDistrict.districtName" value="${qpTCOMDistrict.districtName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属城市ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.cityId" id="qpTCOMDistrict.cityId" value="${qpTCOMDistrict.cityId}">
		</td>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.districtOrder" id="qpTCOMDistrict.districtOrder" value="${qpTCOMDistrict.districtOrder}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTCOMDistrict.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTCOMDistrict.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTCOMDistrict.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTCOMDistrict.operateTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDistrict.validStatus" id="qpTCOMDistrict.validStatus" value="${qpTCOMDistrict.validStatus}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
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
