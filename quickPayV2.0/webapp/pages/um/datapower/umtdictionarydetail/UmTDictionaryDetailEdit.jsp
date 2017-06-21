<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionaryDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtdictionarydetail/UmTDictionaryDetail.js"></script>
<script type="text/javascript">
	
</script>

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
	<div class="right_detail_top">
		<h3>
			<c:choose>
				<c:when test="${operateType == 'update'}">
					修改权限字典详细信息
				</c:when>
				<c:when test="${operateType == 'add'}">
					增加权限字典详细信息
				</c:when>
				<c:when test="${operateType == 'view'}">
					查看权限字典详细信息
				</c:when>
			</c:choose>
		</h3>
	</div>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtdictionarydetail"
		namespace="/um/umtdictionarydetail" method="post">
		<div id="wrapper">
			<div id="container">

				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">字典明细ID</td>
						<td class="long"><c:choose>
								<c:when test="${operateType == 'update'}">
${umTDictionaryDetail.id.dictionaryDetailId}					<input type="hidden"
										name="umTDictionaryDetail.id.dictionaryDetailId"
										id="umTDictionaryDetail.id.dictionaryDetailId"
										value="${umTDictionaryDetail.id.dictionaryDetailId}">
								</c:when>
								<c:when test="${operateType == 'add'}">
									<input class='input_w w_15'
										name="umTDictionaryDetail.id.dictionaryDetailId"
										id="umTDictionaryDetail.id.dictionaryDetailId"
										value="${umTDictionaryDetail.id.dictionaryDetailId}">
								</c:when>
								<c:when test="${operateType == 'view'}">
${umTDictionaryDetail.id.dictionaryDetailId}					<input type="hidden"
										name="umTDictionaryDetail.id.dictionaryDetailId"
										id="umTDictionaryDetail.id.dictionaryDetailId"
										value="${umTDictionaryDetail.id.dictionaryDetailId}">
								</c:when>
							</c:choose></td>
						<td class="bgc_tt short">字典名称</td>
						<td class="long"><c:choose>
								<c:when
									test="${operateType == 'update' || operateType == 'add'}">
									<input type="text" style="width: 250px"
										class="easyui-validatebox  selectcode" name="dictionaryId1"
										required="true"
										ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
										onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
										onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
										readonly="readonly"
										value="${dictionaryName}" />
									<input type="hidden" name="umTDictionaryDetail.dictionaryId"
										id="umTDictionaryDetail.dictionaryId"
										value="${umTDictionaryDetail.dictionaryId}" required="true"
										validType="equalsLength[30]" />
								</c:when>
								<c:when test="${operateType == 'view'}">
									<input type="text" style="width: 250px"
											class="easyui-validatebox  selectcode" name="dictionaryId1"
											required="true"
											ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
											onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
											onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
											readonly="readonly"
											value="${dictionaryName}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td class="bgc_tt short">序列号</td>
						<td class="long">${umTDictionaryDetail.serialNo} <input
							class='input_w w_15' name="umTDictionaryDetail.serialNo"
							type="hidden" id="umTDictionaryDetail.serialNo"
							value="${umTDictionaryDetail.serialNo}" />
						</td>
						<td class="bgc_tt short">操作目标</td>
						<td class="long"><c:choose>
								<c:when
									test="${operateType == 'update' || operateType == 'add'}">
									<input class='easyui-validatebox input_w w_30'
										name="umTDictionaryDetail.targetName"
										id="umTDictionaryDetail.targetName"
										value="${umTDictionaryDetail.targetName}" required="true"
										validType="maxLength[30]" />
								</c:when>
								<c:when test="${operateType == 'view'}">
							${umTDictionaryDetail.targetName}
						</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td class="bgc_tt short">操作域</td>
						<td class="long"><c:choose>
								<c:when
									test="${operateType == 'update' || operateType == 'add'}">
									<input class='easyui-validatebox input_w w_30'
										name="umTDictionaryDetail.targetField"
										id="umTDictionaryDetail.targetField"
										value="${umTDictionaryDetail.targetField}" required="true"
										validType="maxLength[30]" />
								</c:when>
								<c:when test="${operateType == 'view'}">
							${umTDictionaryDetail.targetField}
						</c:when>
							</c:choose></td>
						<td class="bgc_tt short">机构代码</td>
						<td class="long">${umTDictionaryDetail.comCode} <!-- 
					<c:choose>
						<c:when test="${operateType == 'add'}">
							<input class='easyui-validatebox input_w w_15' name="umTDictionaryDetail.comCode" 
										id="umTDictionaryDetail.comCode" value="${umTDictionaryDetail.comCode}" required="true" validType="equalsLength[8]" />
						</c:when>
						<c:when test="${operateType == 'view' || operateType == 'update'}">
							${umTDictionaryDetail.comCode}
						</c:when>
					</c:choose>
					 -->
						</td>
					</tr>
					<c:choose>
						<c:when test="${operateType == 'view'}">
							<tr>
								<td class="bgc_tt short">创建时间</td>
								<td class="long">${umTDictionaryDetail.insertTimeForHis}</td>
								<td class="bgc_tt short">创建人代码</td>
								<td class="long">${umTDictionaryDetail.creatorCode}</td>
							</tr>
							<tr>
								<td class="bgc_tt short">修改时间</td>
								<td class="long">${umTDictionaryDetail.operateTimeForHis}</td>
								<td class="bgc_tt short">修改人代码</td>
								<td class="long">${umTDictionaryDetail.updaterCode}</td>
							</tr>
					</c:when>
					</c:choose>
					<tr>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><c:choose>
								<c:when test="${operateType == 'update'}">
									<ce:radio name="umTDictionaryDetail.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
										value="${umTDictionaryDetail.validStatus}" />
								</c:when>
								<c:when test="${operateType == 'add'}">
									<ce:radio name="umTDictionaryDetail.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="1" />
								</c:when>
								<c:when test="${operateType == 'view'}">
									<ce:radio name="umTDictionaryDetail.validStatus"
										list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
										value="${umTDictionaryDetail.validStatus}" disabled="true" />
								</c:when>
							</c:choose></td>
						<td class="bgc_tt short"></td>
						<td class="long"></td>
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
