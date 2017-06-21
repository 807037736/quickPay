<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDictionaryDetail"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/common/js/MulLine.js"></script>
<script language="javascript"
	src="${ctx}/pages/um/datapower/umtdictionarydetail/UmTDictionaryDetailAddNew.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="right_detail_top"><h3>管理权限明细表信息</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">字典名称</td>
						<td class="short">${umTDictionary.ditionaryName}</td>
						<td class="bgc_tt short">归属机构</td>
						<td class="short">${umTDictionary.comCode}</td>
						<td class="bgc_tt short">有效状态</td>
						<td class="short"><ce:radio name="umTDictionary.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTDictionary.validStatus}"
								disabled="true" /></td>
						<td class="short"><input type="hidden"
							name="umTDictionary.id.dictionaryId"
							id="umTDictionary.id.dictionaryId"
							value="${umTDictionary.id.dictionaryId}" /> <input type="hidden"
							name="umTDictionary.comCode" id="umTDictionary.comCode"
							value="${umTDictionary.comCode}" /></td>
					</tr>
				</table>
			</div>
			</div>
				<!-- 多行录入控件 -->
				<div class="block"><h3></h3>
				<table id="dictionaryDetail_Data" style="display: none;"
					class="fix_table">
					<tbody>
						<tr class="common">
								<!-- 隐藏Flag -->
								<input type="hidden" name="dictdetaiflag"  class="common" value="I"/> 
								<!-- 字典明细ID -->
								<input name="dictionaryDetailId" type="hidden"  />
							<!-- 
							<td>
								<input name="serialNo" class="common" onchange="setUpdateFlag(this,'dictionaryDetail');" style="width:40px" readonly="readonly"  />
							</td>
							 -->							
							<td>
								<!-- 操作目标targetName -->
								<input name="targetName" class="easyui-validatebox input_w w_30" onchange="setUpdateFlag(this,'dictionaryDetail');"  required="true" />
							</td>
							<td>
								<!-- 操作域 targetField -->
								<input  name="targetField"  class="easyui-validatebox input_w w_30" onchange="setUpdateFlag(this,'dictionaryDetail');"  required="true" />
							</td>
							<td>
								<!-- 有效状态 -->
								<ce:select name="validStatus" cssClass="input_w w_10"  list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="1" onchange="setUpdateFlag(this,'dictionaryDetail');" />
							</td>
							<td>
								<!-- 删除按钮 -->
								<input type="button" name="deleteCommMain" value="-" style="width:20px" id="deleteCommMain" class="button_ty" onclick="deleteRow(this, 'dictionaryDetail')" />
							</td>
						</tr>
					</tbody>
				</table>
				<table id="dictionaryDetail" class="fix_table" width="100%" style="text-align: center; font-weight: bold">
					<thead>
						<tr class="common">
							<th class="bgc_tt short" style="text-align: center;">操作目标</th>
							<th class="bgc_tt short" style="text-align: center;">操作域</th>
							<th class="bgc_tt short" style="text-align: center;">有效状态</th>
							<th class="bgc_tt short" style="text-align: center;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="umTDictionaryDetail" items="${umTDictionaryDetailList}">
							<tr class="common">
								<!-- 隐藏Flag -->
								<input type="hidden" name="dictdetaiflag"  class="common" value=""/> 
								<!-- 字典明细ID -->
								<input name="dictionaryDetailId" type="hidden"  value="${umTDictionaryDetail.id.dictionaryDetailId}" />
								<!-- 
								<td style="text-align: center;">
									<input name="serialNo" class="common" style="width:40px"  readonly="readonly"  value="${umTDictionaryDetail.serialNo}" />
								</td>
								 -->
								<td style="text-align: center;">
									<!-- 操作目标targetName -->
									<input name="targetName" class="easyui-validatebox input_w w_30"  onchange="setUpdateFlag(this,'dictionaryDetail');" value="${umTDictionaryDetail.targetName}" required="required" />
								</td>
								<td style="text-align: center;">
									<!-- 操作域 targetField -->
									<input name="targetField"  class="easyui-validatebox input_w w_30"  onchange="setUpdateFlag(this,'dictionaryDetail');" value="${umTDictionaryDetail.targetField}"  required="required" />
								</td>
								<td>
									<!-- 有效状态 -->
									<ce:select name="validStatus" cssClass="input_w w_10" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTDictionaryDetail.validStatus}" onchange="setUpdateFlag(this,'dictionaryDetail');" />
								</td>
								<td>
									<!-- 删除按钮 -->
									<input type="button" name="deleteCommMain" style="width:20px" value="-"  id="deleteCommMain" class="button_ty" onclick="deleteRow(this, 'dictionaryDetail')" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot style="text-align: right;">
						<tr>
							<td class="common" colspan="6"><input type="button"
								class="button_ty" onclick="insertRow('dictionaryDetail',this);"
								value="+" /></td>
						</tr>
					</tfoot>
				</table>
				</div>
				<div style="text-align: center;">
					<input type="button" class="button_ty" value="保存信息" id="saveDictionaryDetail" />
				</div>
	</form>
	<!-- 
	<table id="UmTDictionaryDetailAddTable"></table>
	 -->
</body>
</html>
