<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMCity" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomcity/QpTCOMCity.js"></script>

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
	<form name="fm" id="fm" action="/qp/qptcomcity" namespace="/qp/qptcomcity" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTCOMCity信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTCOMCity信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTCOMCity信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		城市ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTCOMCity.id.cityId}					<input type="hidden" name="qpTCOMCity.id.cityId" id="qpTCOMCity.id.cityId" value="${qpTCOMCity.id.cityId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTCOMCity.id.cityId" id="qpTCOMCity.id.cityId" value="${qpTCOMCity.id.cityId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTCOMCity.id.cityId}					<input type="hidden" name="qpTCOMCity.id.cityId" id="qpTCOMCity.id.cityId" value="${qpTCOMCity.id.cityId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		城市名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.cityName" id="qpTCOMCity.cityName" value="${qpTCOMCity.cityName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属省份ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.provId" id="qpTCOMCity.provId" value="${qpTCOMCity.provId}">
		</td>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.cityOrder" id="qpTCOMCity.cityOrder" value="${qpTCOMCity.cityOrder}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTCOMCity.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTCOMCity.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTCOMCity.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTCOMCity.operateTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMCity.validStatus" id="qpTCOMCity.validStatus" value="${qpTCOMCity.validStatus}">
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
