<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICCompany" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticcompany/QpTICCompany.js"></script>
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
		保险公司ID
	</td>
	  	<td  class="long" style="display: none">
			<input class='input_w w_15' name="qpTICCompany.id.coId" id="qpTICCompany.id.coId" value="${qpTICCompany.id.coId}">
		</td>
	<td class="bgc_tt short">
		保险公司名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coName" id="qpTICCompany.coName" value="${qpTICCompany.coName}">
		</td>
							
	<td class="bgc_tt short" style="display: none">
		序号
	</td>
		<td class="long" style="display: none">
			<input class='input_w w_15' name="qpTICCompany.coOrder" id="qpTICCompany.coOrder" value="${qpTICCompany.coOrder}">
		</td>
	<td class="bgc_tt short">
		公司电话
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coPhone" id="qpTICCompany.coPhone" value="${qpTICCompany.coPhone}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short" style="display: none">
		备注信息
	</td>
		<td class="long" style="display: none">
			<input class='input_w w_15' name="qpTICCompany.coNotes" id="qpTICCompany.coNotes" value="${qpTICCompany.coNotes}">
		</td>
	<%-- <td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.validStatus" id="qpTICCompany.validStatus" value="${qpTICCompany.validStatus}">
		</td> --%>
		
		<td class="bgc_tt short">是否有效</td>
		<td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
				cssClass='input_w w_15'  value="qpTICCompany.validStatus" 
				name="qpTICCompany.validStatus" id="qpTICCompany.validStatus" />
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
	<table id="QpTICCompanyTable"></table>
</body>
</html>
