<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPPolice" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttppolice/QpTTPPolice.js"></script>

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
	<form name="fm" id="fm" action="/qp/qpttppolice" namespace="/qp/qpttppolice" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTTPPolice信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTTPPolice信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTTPPolice信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		交警ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTTPPolice.id.policeId}					<input type="hidden" name="qpTTPPolice.id.policeId" id="qpTTPPolice.id.policeId" value="${qpTTPPolice.id.policeId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTTPPolice.id.policeId" id="qpTTPPolice.id.policeId" value="${qpTTPPolice.id.policeId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTTPPolice.id.policeId}					<input type="hidden" name="qpTTPPolice.id.policeId" id="qpTTPPolice.id.policeId" value="${qpTTPPolice.id.policeId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		交警姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.policeName" id="qpTTPPolice.policeName" value="${qpTTPPolice.policeName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		所属大队
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.teamId" id="qpTTPPolice.teamId" value="${qpTTPPolice.teamId}">
		</td>
	<td class="bgc_tt short">
		警员编号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.employeeId" id="qpTTPPolice.employeeId" value="${qpTTPPolice.employeeId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.policeOrder" id="qpTTPPolice.policeOrder" value="${qpTTPPolice.policeOrder}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTTPPolice.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTTPPolice.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTTPPolice.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTTPPolice.operateTimeForHis}			</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPPolice.validStatus" id="qpTTPPolice.validStatus" value="${qpTTPPolice.validStatus}">
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
