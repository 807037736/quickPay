<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionary"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/datapower/umtdictionary/UmTDictionary.js"></script>

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
					修改权限字典信息
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加权限字典信息
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看权限字典信息
				</c:when>
						</c:choose></h3></div>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtdictionary" namespace="/um/umtdictionary" method="post">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">字典名称</td>
						<td class="long">
							<c:choose>
							<c:when test="${operateType == 'view'}">
								${umTDictionary.ditionaryName}
								<input class='input_w w_15' type="hidden"
										name="umTDictionary.ditionaryName" id="umTDictionary.ditionaryName"
										value="${umTDictionary.ditionaryName}">
							</c:when>
							<c:when test="${operateType=='add' || operateType=='update' }">
								<input class='input_w w_15'
										name="umTDictionary.ditionaryName" id="umTDictionary.ditionaryName"
										value="${umTDictionary.ditionaryName}" required="true" maxlength="30">	
							</c:when>
							</c:choose>
						</td>
						<td class="bgc_tt short">机构代码</td>
						<td class="long">
							${umTDictionary.comCode}
							<input class='input_w w_15' type="hidden"
								name="umTDictionary.comCode" id="umTDictionary.comCode"
								value="${umTDictionary.comCode}" />
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><c:choose>
								<c:when test="${operateType == 'update'}">
									<ce:radio name="umTDictionary.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTDictionary.validStatus}" />
								</c:when>
								<c:when test="${operateType == 'add'}">
									<ce:radio name="umTDictionary.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="1" />
								</c:when>
								<c:when test="${operateType == 'view'}">
									<ce:radio name="umTDictionary.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTDictionary.validStatus}"
										disabled="true" />
								</c:when>
							</c:choose>
						</td>
						<td colspan="2">
							<input type="hidden" name="id.dictionaryId" value="${umTDictionary.id.dictionaryId}" />
						</td>
					</tr>
				</table>
		<table class="fix_table">
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
