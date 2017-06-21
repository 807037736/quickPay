<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPLaw" %>

<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttplaw/QpTTPLaw.js"></script>
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
	<td class="bgc_tt short">
		法律法规ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTTPLaw.id.lawId" id="qpTTPLaw.id.lawId" value="${qpTTPLaw.id.lawId}">
		</td>
	<td class="bgc_tt short">
		法律法规内容
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawName" id="qpTTPLaw.lawName" value="${qpTTPLaw.lawName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		关键字描述
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawWords" id="qpTTPLaw.lawWords" value="${qpTTPLaw.lawWords}">
		</td>
	<td class="bgc_tt short">
		条
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawItem" id="qpTTPLaw.lawItem" value="${qpTTPLaw.lawItem}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		款
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSegment" id="qpTTPLaw.lawSegment" value="${qpTTPLaw.lawSegment}">
		</td>
	<td class="bgc_tt short">
		项
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSection" id="qpTTPLaw.lawSection" value="${qpTTPLaw.lawSection}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawOrder" id="qpTTPLaw.lawOrder" value="${qpTTPLaw.lawOrder}">
		</td>
		
		
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
		 
			<input class='input_w w_15' name="qpTTPLaw.validStatus" id="qpTTPLaw.validStatus" value="${qpTTPLaw.validStatus}">
		
			<select class='input_w w_15' name="qpTTPLaw.validStatus" id="qpTTPLaw.validStatus">
				<option value="1">是</option>
				<option value="1">否</option>
			</select>
		</td>
	</tr> 	
			-->
			
	<tr>
		<td class="bgc_tt short">
			法律法规内容
		</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawName" id="qpTTPLaw.lawName" value="${qpTTPLaw.lawName}">
		</td>
		
		<td class="bgc_tt short">
		关键字描述
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawWords" id="qpTTPLaw.lawWords" value="${qpTTPLaw.lawWords}">
		</td>
	</tr>
		
	<tr>
	<td class="bgc_tt short">
		条
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawItem" id="qpTTPLaw.lawItem" value="${qpTTPLaw.lawItem}">
		</td>
		
	<td class="bgc_tt short">
		款
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSegment" id="qpTTPLaw.lawSegment" value="${qpTTPLaw.lawSegment}">
		</td>
	</tr>
	
	<tr>
	<td class="bgc_tt short">
		项
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSection" id="qpTTPLaw.lawSection" value="${qpTTPLaw.lawSection}">
		</td>
		
		<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawOrder" id="qpTTPLaw.lawOrder" value="${qpTTPLaw.lawOrder}">
		</td>
	</tr>
	
	<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<select class='input_w w_15' name="qpTTPLaw.validStatus" id="qpTTPLaw.validStatus">
				<option selected="selected" value="">---选择状态---</option>
				<option value="1">是</option>
				<option value="0">否</option>
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
	<table id="QpTTPLawTable"></table>
</body>
</html>
