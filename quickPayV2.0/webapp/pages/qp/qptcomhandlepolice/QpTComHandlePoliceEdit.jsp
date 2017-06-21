<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMHandlePolice"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/qp/qptcomhandlepolice/QpTComHandlePolice.js"></script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/qp/qptcomhandlepolice"
		namespace="/qp/qptcomhandlepolice" method="post">

		<input name="qpTComHandlePolice.handlePoliceId" id="handlePoliceId" value="${qpTComHandlePolice.handlePoliceId}" type="hidden">
		<input name="qpTComHandlePolice.createTime" id="createTime" value="${qpTComHandlePolice.createTime}" type="hidden">
		<input name="qpTComHandlePolice.createCode" id="createCode" value="${qpTComHandlePolice.createCode}" type="hidden">
		<input name="qpTComHandlePolice.updaterTime" id="updaterTime" value="${qpTComHandlePolice.updaterTime}" type="hidden">
		<input name="qpTComHandlePolice.updaterCode" id="updaterCode" value="${qpTComHandlePolice.updaterCode}" type="hidden">
		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${operateType == 'update'}">
					修改处理民警人员信息
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加处理民警人员信息
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看处理民警人员信息
				</c:when>
						</c:choose>
					</h2>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="bgc_tt short">处理民警名称</td>
				<td class="long"><input class='input_w w_15'
					name="qpTComHandlePolice.handlePoliceName" id="handlePoliceName"
					value="${qpTComHandlePolice.handlePoliceName}"></td>

				<td class="bgc_tt short">处理民警电话</td>
				<td class="long"><input maxlength="11" class='input_w w_15'
					name="qpTComHandlePolice.handlePolicePhone" id="handlePolicePhone"
					value="${qpTComHandlePolice.handlePolicePhone}" ></td>
			</tr>
			<tr>
				<td class="bgc_tt short">警号</td>
				<td class="long"><input class='input_w w_15'
					name="qpTComHandlePolice.handlePoliceNO" id="handlePoliceNO"
					value="${qpTComHandlePolice.handlePoliceNO}"></td>

				<td class="bgc_tt short">所在机构</td>
				<td class="long"><input class='input_w w_15'
					name="qpTComHandlePolice.organization" id="organization"
					value="${qpTComHandlePolice.organization}"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">是否有效</td>
				<td class="long"><ce:select
						list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
						cssClass='input_w w_15' value="qpTComHandlePolice.validStatus"
						name="qpTComHandlePolice.validStatus" id="qpTComHandlePolice.validStatus" />
				</td>
				<td class="bgc_tt short">快赔中心</td>
				<td width="10%"><select id="centerId" name="qpTComHandlePolice.centerId" class="input_w w_22 easyui-combobox">
						<option value="">请选择</option>
						<c:forEach var="fastCenter" items="${fastCenterList}">
							<option value="${fastCenter.centerId}" 
								<c:if test="${fastCenter.centerId==qpTComHandlePolice.centerId}">selected</c:if>>${fastCenter.centerName}</option>
						</c:forEach>
				</select>
				<input type="hidden" name="qpTComHandlePolice.centerName" id="centerName" value="">
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
