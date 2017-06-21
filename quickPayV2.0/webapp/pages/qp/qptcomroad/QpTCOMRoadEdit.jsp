<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMRoad" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomroad/QpTCOMRoad.js"></script>

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
	<form name="fm" id="fm" action="/qp/qptcomroad" namespace="/qp/qptcomroad" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTCOMRoad信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTCOMRoad信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTCOMRoad信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		道路ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTCOMRoad.id.roadId}					<input type="hidden" name="qpTCOMRoad.id.roadId" id="qpTCOMRoad.id.roadId" value="${qpTCOMRoad.id.roadId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTCOMRoad.id.roadId" id="qpTCOMRoad.id.roadId" value="${qpTCOMRoad.id.roadId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTCOMRoad.id.roadId}					<input type="hidden" name="qpTCOMRoad.id.roadId" id="qpTCOMRoad.id.roadId" value="${qpTCOMRoad.id.roadId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		道路名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMRoad.roadName" id="qpTCOMRoad.roadName" value="${qpTCOMRoad.roadName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属区县ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMRoad.districtId" id="qpTCOMRoad.districtId" value="${qpTCOMRoad.districtId}">
		</td>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMRoad.roadOrder" id="qpTCOMRoad.roadOrder" value="${qpTCOMRoad.roadOrder}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTCOMRoad.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTCOMRoad.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTCOMRoad.updaterCode}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTCOMRoad.operateTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMRoad.validStatus" id="qpTCOMRoad.validStatus" value="${qpTCOMRoad.validStatus}">
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
