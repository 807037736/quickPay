<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRolePower"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript"
	src="${ctx}/common/js/MulLine.js"></script>
<script language="javascript"
	src="${ctx}/pages/um/umtrolepower/UmTRolePower.js"></script>
<script type="text/javascript">
	/*页面加载触发*/
	
	var dictionaryName = new Array();
	var index = 1;
	
	$(document).ready(function() {
		if ($('#operateType').val() == 'view') {
			setReadonlyOfAllInput("fm");
		}
	});
	
	function changeValue(val,num){
		if(val.value!=dictionaryName[num]){
			setUpdateFlag(val,'rolePower');
		}
	}
</script>
</head>

<body>
<div class="right_detail_top"><h3><c:choose>
							<c:when test="${operateType == 'update'}">
					修改角色数据权限
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加角色数据权限
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看角色数据权限
				</c:when>
						</c:choose></h3></div>
	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtrolepower"
		namespace="/um/umtrolepower" method="post">
		<div id="wrapper">
			<div id="container">
		<table class="fix_table">
			<tr>
				<td class="bgc_tt short">角色代码</td>
				<td class="long">
					<input type="text" class="input_w w_20" readonly="readonly" value="${umTRole.roleCode}" disabled="true" />
				</td>
			
				<td class="bgc_tt short">角色名称</td>
				<td class="long">
					<input type="hidden" name="umTRolePower.id.rpId" id="umTRolePower.id.rpId" value="${umTRolePower.id.rpId}" />
					<input type="text" class="input_w w_20" readonly="readonly" value="${umTRole.roleCName}" disabled="true" />
					<input type="hidden" id="umTRolePower.roleId" value="${umTRole.id.roleId}" />
				</td>
			</tr>
		</table>
		 </div>
		</div>
		<!-- 多行录入控件 -->
		<table id="rolePower_Data" style="display: none;"
			class="fix_table">
			<tbody>
				<tr class="common">
					<td>
						<!-- 隐藏Flag -->
						<input type="hidden" name="rolepowerflag"  class="common" value="I"/> 
						<!-- 角色权限ID -->
						<input name="rpId" type="hidden" class="common" onchange="setUpdateFlag(this,'rolePower');" readonly="readonly" />
					
						<!-- 数据权限字典 -->
						<input type="text" 
							class="selectcode input_w" name="dictname"
							ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');"
							onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
							readonly="readonly" required="required"/>
						<input type="hidden" name="dictionaryId" value="" onchange="setUpdateFlag(this,'rolePower');" />
					</td>
					<td>
						<!-- 数据权限维度值 -->
						<ce:codeselect codeType="RolePower" cssClass="input_w w_15" name="powerValue" required="required" />
					</td>
					<td>
						<!-- 删除按钮 -->
						<input type="button" name="deleteCommMain" value="-" style="width:20px" id="deleteCommMain" class="button_ty" onclick="deleteRow(this, 'rolePower')" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="block">
			<h3></h3>
		<table id="rolePower" class="fix_table">
			<thead>
				<tr class="common">
					<th class="bgc_tt short" style="text-align: center;">数据权限字典</th>
					<th class="bgc_tt short" style="text-align: center;">数据权限附加域值</th>
					<th class="bgc_tt short" style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody style="text-align:center;">
				<c:forEach var="umTRolePower" items="${rolePowerList}">
					<script type="text/javascript">
						dictionaryName[index] = "${userPowerVar.dictionaryName}";
					</script>
					<tr class="common">
						<!-- 隐藏Flag -->
						<input type="hidden" name="rolepowerflag"  class="common" value=""/> 
						<!-- 角色权限ID -->
						<input name="rpId" type="hidden" class="common" onchange="setUpdateFlag(this,'rolePower');" readonly="readonly" value="${umTRolePower.id.rpId }" />
						<td >
							<!-- 数据权限字典 -->
							<input type="text" 
								class="input_w w_15 selectcode" name="dictname"
								ondblclick="code_CodeQuery(this,'/common/queryValidDictionary.do', '0,1','y');changeValue(this,index-1)"
								onkeyup="code_CodeSelect(this, '/common/queryValidDictionary.do', '0,1', 'y');"
								onchange="code_CodeChange(this, '/common/queryValidDictionary.do', '0,1', 'y');"
								readonly="readonly" value="${umTRolePower.dictionaryName}" required="required" />
							<input type="hidden" name="dictionaryId" value="${umTRolePower.dictionaryId}"/>
						</td>
						<td >
							<!-- 数据权限维度值 -->
							<ce:codeselect codeType="RolePower" cssClass="input_w w_15" required="required" name="powerValue" onchange="setUpdateFlag(this,'rolePower');" value="${umTRolePower.powerValue}"/>						
						</td>
						<td>
							<!-- 删除按钮 -->
							<input type="button" name="deleteCommMain" style="width:20px" value="-"  id="deleteCommMain" class="button_ty" onclick="deleteRow(this, 'rolePower')" />
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
						class="button_ty" onclick="insertRow('rolePower',this);"
						value="+" /></td>
				</tr>
			</tfoot>
		</table>
		</div>
		 
		<table class="fix_table">
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
