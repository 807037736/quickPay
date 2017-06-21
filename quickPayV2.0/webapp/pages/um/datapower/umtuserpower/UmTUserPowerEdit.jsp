<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserPower"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtuserpower/UmTUserPower.js"></script>
<script type="text/javascript">
	
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(function() {
		$('#fm input').each(function() {
			if ($(this).attr('required') || $(this).attr('validType'))
				$(this).validatebox();
		});
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3><c:choose>
							<c:when test="${operateType == 'update'}">
					修改用户数据权限
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加用户数据权限
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看用户数据权限
				</c:when>
						</c:choose></h3></div>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtuserpower"
		namespace="/um/umtuserpower" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
			
			<tr>
				<td class="bgc_tt short">操作者类型</td>
				<td class="long">
					<input type="hidden" name="umTUserPower.id.userPowerId" id="umTUserPower.id.userPowerId" value="${umTUserPower.id.userPowerId}" />
					<ce:codeselect codeType="PowerOperatorType"
						name="umTUserPower.operatorType"
						value="${umTUserPower.operatorType}" /></td>
				<td class="bgc_tt short">字典ID</td>
				<td class="long"><input type="text" style="width: 300px"
					class="input_w w_15 selectcode" name="dictionaryId2"
					ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
					onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
					onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
					readonly="readonly" value="${dictionaryName}" required="true" /> <input
					type="hidden" name="umTUserPower.dictionaryId"
					id="umTUserPower.dictionaryId" value="${umTUserPower.dictionaryId}" />
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">用户代码</td>
				<td class="long"><input class='input_w w_15' type="text"
					name="umTUserPower.userCode" id="umTUserPower.userCode"
					value="${umTUserPower.userCode}" maxlength="15" required="true">
				</td>
				<td class="bgc_tt short">操作符</td>
				<td class="long"><ce:codeselect codeType="SymbolType"
						name="umTUserPower.operationSymbol"
						id="umTUserPower.operationSymbol"
						value="${umTUserPower.operationSymbol}" /></td>
			</tr>
			<tr>
				<td class="bgc_tt short">限制域值</td>
				<td class="long"><input class='input_w w_15'
					name="umTUserPower.powerValue" id="umTUserPower.powerValue"
					value="${umTUserPower.powerValue}" required="true" maxlength="30" />
				</td>
				<td class="bgc_tt short">有效状态</td>
				<td class="long">
					<ce:select name="umTUserPower.validStatus"  cssStyle="width:80px"
							id="umTUserPower.validStatus" list="#@java.util.LinkedHashMap@{'':'','1':'有效','0':'无效'}" 
							value="umTUserPower.validStatus" />
				</td>
			</tr>

			<c:choose>
				<c:when test="${operateType == 'view'}">
					<tr>
						<td class="bgc_tt short">机构代码</td>
						<td class="long">${umTUserPower.comCode}</td>
						<td class="bgc_tt short">创建人代码</td>
						<td class="long">${umTUserPower.creatorCode}</td>
					</tr>
					<tr>
						<td class="bgc_tt short">创建时间</td>
						<td class="long">${umTUserPower.insertTimeForHis}</td>
						<td class="bgc_tt short">修改人代码</td>
						<td class="long">${umTUserPower.updaterCode}</td>
					</tr>
					<tr>
						<td class="bgc_tt short">修改时间</td>
						<td class="long">${umTUserPower.operateTimeForHis}</td>
						<td class="bgc_tt short"></td>
						<td class="long"></td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		<br>
		<table  class="fix_table">
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" /></td>
			</tr>
		</table>
		</div>
		</div>
	</form>
</body>
</html>
