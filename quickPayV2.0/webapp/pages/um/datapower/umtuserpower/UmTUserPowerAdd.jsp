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
	src="${ctx}/pages/um/datapower/umtuserpower/UmTUserPowerAddNew.js"></script>
<script type="text/javascript">
	/*页面加载触发*/
	var dictionaryName = new Array();
	var index = 1;
	
	$(document).ready(function() {
		$('#fm input').each(function() {
			if ($(this).attr('required') || $(this).attr('validType'))
				$(this).validatebox();
		});
	});
	
	function changeValue(val,num){
		if(val.value!=dictionaryName[num]){
			setUpdateFlag(val,'userPower');
		}
	}
</script>
<style type="text/css">
div div div table tr {
	width:200px;
}

</style>
</head>
<body>
<div class="right_detail_top"><h3>用户数据权限配置</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">用户代码</td>
						<td class="">${umTUser.id.userCode}</td>
						<td class="bgc_tt short">用户姓名</td>
						<td class="">${umTUser.userName}</td>
						<td class="bgc_tt short">有效状态</td>
						<td class="">
							<ce:select name="umTUser.validStatus"
								list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${umTUser.validStatus}"
								disabled="true" />
							<input type="hidden" name="umTUser.id.userCode"
										id="umTUser.id.userCode" value="${umTUser.id.userCode}" />
						</td>
					</tr>
				</table>
				</div>
			</div>
			
			<!-- 多行录入控件 -->
			<div class="block">
				<h3>用户权限明细列表</h3>
				<table id="userPower_Data" style="display: none;"
					class="fix_table">
					<tbody>
						<tr class="common">
								<!-- 隐藏Flag -->
								<input type="hidden" name="userpowerflag"  class="common" value="I"/> 
								<!-- 用户数据权限ID -->
								<input type="hidden" name="userpowerid" />
							<td>
								<!-- 字典明细ID -->
								<input type="text" 
										class="easyui-validatebox  selectcode" name="dictionaryId1"  required="true"
										ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
										onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
										onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
										readonly="readonly" />
								<input type="hidden" name="dictionaryId" required="true" class="easyui-validatebox" />
							</td>
							<td>
								<ce:codeselect codeType="SymbolType"  cssClass="input_w w_15" name="operationsymbol"  required="true"/>
							</td>
							<td>
								<input name="powervalue"  class="easyui-validatebox input_w w_20" onchange="setUpdateFlag(this,'userPower');"  required="true" />
							</td>
							<td>
								<!-- 有效状态 -->
								<ce:select name="validStatus" cssClass="input_w w_10" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="1" onchange="setUpdateFlag(this,'userPower');" />
							</td>
							<td>
								<!-- 删除按钮 -->
								<input type="button" name="deleteCommMain" value="-" style="width:20px" class="button_ty" onclick="deleteRow(this, 'userPower')" />
							</td>
						</tr>
					</tbody>
				</table>
				<table id="userPower" class="fix_table"  >
					<thead>
						<tr class="common">
							<th class="bgc_tt short" style="text-align: center;">数据权限字典名称</th>
							<th class="bgc_tt short" style="text-align: center;">操作符</th>
							<th class="bgc_tt short" style="text-align: center;">权限域值</th>
							<th class="bgc_tt short" style="text-align: center;">有效状态</th>
							<th class="bgc_tt short" style="text-align: center;">操作</th>
						</tr>
					</thead>
					<tbody style="text-align: center;">
						<c:forEach var="userPowerVar" items="${umTUserPowerList}">
								<script type="text/javascript">
									dictionaryName[index] = "${userPowerVar.dictionaryName}";
								</script>
								<tr class="common">
										<!-- 隐藏Flag -->
										<input type="hidden" name="userpowerflag"  class="common"  /> 
										<!-- 用户权限ID -->
										<input type="hidden" name="userpowerid"  value="${userPowerVar.id.userPowerId}">
									<td >
									<!-- 权限字典ID -->
									<c:choose>
										<c:when test="${userPowerVar.rolePowerId == null}">
											<input type="text"
												class="easyui-validatebox selectcode" name="dictionaryId1"
												ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');changeValue(this,index-1);"
												onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
												onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
												readonly="readonly" value="${userPowerVar.dictionaryName}" required="true" />
											<input type="hidden" name="dictionaryId" value="${userPowerVar.dictionaryId}"  required="true" class="easyui-validatebox" />
										</c:when>
										<c:when test="${userPowerVar.rolePowerId != null}">
											<input type="text"
												class="easyui-validatebox selectcode" name="dictionaryId1"
												ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
												onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
												onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
												readonly="readonly" value="${userPowerVar.dictionaryName} "  required="required" 
												disabled="true" />
											<input type="hidden" name="dictionaryId" value="${userPowerVar.dictionaryId}"  required="true" class="easyui-validatebox" />
										</c:when>
									</c:choose>
									</td>
									<td>
										<!--  操作符  -->
										<c:choose>
											<c:when test="${userPowerVar.rolePowerId == null}">
												<ce:codeselect codeType="SymbolType"  cssClass="input_w w_15" name="operationsymbol"  value="${userPowerVar.operationSymbol}"  onchange="setUpdateFlag(this,'userPower');" />
											</c:when>
											<c:when test="${userPowerVar.rolePowerId != null}">
												<ce:codeselect codeType="SymbolType"  cssClass="input_w w_15" name="operationsymbol"  value="${userPowerVar.operationSymbol}"  onchange="setUpdateFlag(this,'userPower');" disabled="true" />
											</c:when>
										</c:choose>
									</td>
									<td >
										<!-- 用户数据权限域值 -->
										<c:choose>
											<c:when test="${userPowerVar.rolePowerId == null}">
												<input name="powervalue"  class="easyui-validatebox input_w w_20" onchange="setUpdateFlag(this,'userPower');" value="${userPowerVar.powerValue}" required="true" />
											</c:when>
											<c:when test="${userPowerVar.rolePowerId != null}">
												<input name="powervalue"   class="easyui-validatebox input_w w_20" onchange="setUpdateFlag(this,'userPower');" value="${userPowerVar.powerValue}"  readonly="readonly" />
											</c:when>
										</c:choose>
									</td>
									<td>
										<!-- 有效状态 -->
										<c:choose>
											<c:when test="${userPowerVar.rolePowerId == null}">
												<ce:select name="validStatus" cssClass="input_w w_10" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${userPowerVar.validStatus}" onchange="setUpdateFlag(this,'userPower');" />
											</c:when>
											<c:when test="${userPowerVar.rolePowerId != null}">
												<ce:select name="validStatus" cssClass="input_w w_10" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="${userPowerVar.validStatus}" onchange="setUpdateFlag(this,'userPower');" disabled="true"/>
											</c:when>
										</c:choose>
									</td>
									<td>
										<!-- 删除按钮 -->
										<c:choose>
											<c:when test="${userPowerVar.rolePowerId == null}">
												<input type="button" name="deleteCommMain" style="width:20px" value="-" class="button_ty" onclick="deleteRow(this, 'userPower')" />
											</c:when>
											<c:when test="${userPowerVar.rolePowerId != null}">
												<input type="hidden" name="deleteCommMain" style="width:20px" value="-" class="button_ty" onclick="deleteRow(this, 'userPower')" />
											</c:when>
										</c:choose>
									</td>
								</tr>
								<script type="text/javascript">
									index = index+1;
								</script>
						</c:forEach>
					</tbody>
					<tfoot style="text-align: right;">
						<tr>
							<td class="common" colspan="6"><input type="button"
								class="button_ty" onclick="insertRow('userPower',this);"
								value="+" /></td>
						</tr>
					</tfoot>
				</table>
				</div>
				<div style="text-align: center;">
					<input type="button" class="button_ty" value="保存信息" id="saveUserPower" />
				</div>
	</form>
	<div class="margin-control">
	<table id="UmTUserPowerAddTable"></table>
	</div>
</body>
</html>
