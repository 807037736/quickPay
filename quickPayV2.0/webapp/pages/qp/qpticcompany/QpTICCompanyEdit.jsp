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
	<form name="fm" id="fm" action="/qp/qpticcompany" namespace="/qp/qpticcompany" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改保险公司信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加保险公司信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看保险公司信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short" style="display: none">
		保险公司ID
	</td>
	  	<td class="long" style="display: none">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTICCompany.id.coId}					<input type="hidden" name="qpTICCompany.id.coId" id="qpTICCompany.id.coId" value="${qpTICCompany.id.coId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTICCompany.id.coId" id="qpTICCompany.id.coId" value="${qpTICCompany.id.coId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTICCompany.id.coId}					<input type="hidden" name="qpTICCompany.id.coId" id="qpTICCompany.id.coId" value="${qpTICCompany.id.coId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		保险公司名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coName" id="qpTICCompany.coName" value="${qpTICCompany.coName}">
		</td>
							
	<td class="bgc_tt short">
		公司电话
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coPhone" id="qpTICCompany.coPhone" value="${qpTICCompany.coPhone}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		备注信息
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coNotes" id="qpTICCompany.coNotes" value="${qpTICCompany.coNotes}">
		</td>
	<td class="bgc_tt short" style="display: none">
		创建人代码
	</td>
			<td class="long" style="display: none">
${qpTICCompany.creatorCode}			</td>
							
	<td class="bgc_tt short" style="display: none">
		创建时间
	</td>
			<td class="long" style="display: none">
${qpTICCompany.insertTimeForHis}			</td>
	<td class="bgc_tt short" style="display: none">
		修改人代码
	</td>
			<td class="long" style="display: none">
${qpTICCompany.updaterCode}			</td>
							
	<td class="bgc_tt short" style="display: none">
		修改时间
	</td>
			<td class="long" style="display: none">
${qpTICCompany.operateTimeForHis}			</td>
	<%-- <td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.validStatus" id="qpTICCompany.validStatus" value="${qpTICCompany.validStatus}">
		</td> --%>
		<td class="bgc_tt short">
		排列序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICCompany.coOrder" id="qpTICCompany.coOrder" value="${qpTICCompany.coOrder}">
		</td>
		
							 </tr> 	
		<tr>
		    <td class="bgc_tt short">是否有效</td>
		<td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
				cssClass='input_w w_15'  value="qpTICCompany.validStatus" 
				name="qpTICCompany.validStatus" id="qpTICCompany.validStatus" />
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
