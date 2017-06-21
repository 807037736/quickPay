<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMProvince" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomprovince/QpTCOMProvince.js"></script>

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
	<form name="fm" id="fm" action="/qp/qptcomprovince" namespace="/qp/qptcomprovince" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTCOMProvince信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTCOMProvince信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTCOMProvince信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		省份ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTCOMProvince.id.provId}					<input type="hidden" name="qpTCOMProvince.id.provId" id="qpTCOMProvince.id.provId" value="${qpTCOMProvince.id.provId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTCOMProvince.id.provId" id="qpTCOMProvince.id.provId" value="${qpTCOMProvince.id.provId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTCOMProvince.id.provId}					<input type="hidden" name="qpTCOMProvince.id.provId" id="qpTCOMProvince.id.provId" value="${qpTCOMProvince.id.provId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		省份名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMProvince.provName" id="qpTCOMProvince.provName" value="${qpTCOMProvince.provName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMProvince.provOrder" id="qpTCOMProvince.provOrder" value="${qpTCOMProvince.provOrder}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTCOMProvince.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTCOMProvince.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTCOMProvince.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTCOMProvince.operateTimeForHis}			</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMProvince.validStatus" id="qpTCOMProvince.validStatus" value="${qpTCOMProvince.validStatus}">
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
