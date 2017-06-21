<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTTask"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umttask/UmTTask.js"></script>
<script language="javascript" src="${ctx}/pages/um/common/systemSelect.js"></script>
<script type="text/javascript">
	/*页面加载触发*/
	$(document)
			.ready(
					function() {
						if ($('#opreateType').val() == 'view') {
							setReadonlyOfAllInput("fm");
						}
						$('#win').window({

							width : 500,
							
							left : 220,

							height : 400,

							modal : true,

							closed : true

						});
						if ($('#opreateType').val() != 'view') {
							$('#upperTaskCode')
									.dblclick(
											function() {
												var taskType = null;
												var serverCode = $("#svrCode").find("option:selected").val();
												if ($('#opreateType').val() == 'add') {
													taskType =  $("input[name='umTTask.taskType']:checked").val();
												}
												else{
													taskType = $('#taskType').val();
												}
												if (taskType == 'menu') {
													createTree('tt', 8, "",
															false,serverCode);
												} else if (taskType == 'portal') {
													createTree('tt', 9, "",
															false,serverCode);
												} else if (taskType == 'button') {
													createTree('tt', 4, "",
															false,serverCode);
												}
												$('#win').window('open');
												$('#tt')
														.tree(
																{
																	onDblClick : function(
																			node) {
																		var taskId = $(
																				'#taskId')
																				.val();
																		var url = contextRootPath
																				+ "/um/umttask/checkUpperTask.do?curTaskId="
																				+ taskId
																				+ "&upperTaskId="
																				+ node.id+ "&serverCode="
																				+ serverCode;
																		//先判断选中的功能是不是当前功能的子级，如果是，则返回出错信息
																		$.ajax({
																					type : "POST",
																					url : url,
																					dataType : "text",
																					success : function(
																							msg) {
																						if (msg == 'success') {
																							$(
																									'#variableTypeId')
																									.val(
																											node.id);
																							$(
																									'#variableTypeName')
																									.val(
																											node.text);
																							$(
																									'#upperTaskCode')
																									.val(
																											node.attributes.taskCode);
																							$(
																									'#win')
																									.window(
																											'close');
																						} else {
																							$.messager
																									.alert(
																											"提示信息",
																											"不能选择本节点和子节点为上级功能！请重新选择！",
																											"info");
																						}
																					}
																				});

																	}
																});
											});
						}
					});
</script>
</head>
<div class="right_detail_top"><h3>
                <c:choose>
							<c:when test="${opreateType == 'update'}">
					修改功能信息
				</c:when>
							<c:when test="${opreateType == 'add'}">
					增加功能信息
				</c:when>
							<c:when test="${opreateType == 'view'}">
					查看功能信息
				</c:when>
						</c:choose></h3></div>
<body>
	<input type="hidden" name="opreateType" id="opreateType"
		value="${opreateType}" />
	<form name="fm" id="fm" action="/um/umttask" namespace="/um/umttask"
		method="post">
		<div id="wrapper">
	<div id="container">
		
		<table class="fix_table">
				
			<tr>
			
				<td class="bgc_tt short">
				<input type="hidden" value="${umTTask.id.taskId}"
					name="umTTask.id.taskId" id="taskId" />
				<span style="color: red">*&nbsp;</span>功能代码</td>
				<td class="long"><input class='easyui-validatebox input_w w_15'
					name="umTTask.taskCode" id="umTTask.taskCode"
					value="${umTTask.taskCode}" required="true"></td>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>功能名称</td>
				<td class="long"><input class='easyui-validatebox input_w w_15'
					name="umTTask.taskName" id="umTTask.taskName"
					value="${umTTask.taskName}" required="true"
					validType="length[0,50]"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">系统代码</td>
				<td class="long">
					<select class="input_w w_15" id="svrCode" name="umTTask.svrCode"></select>
					<c:choose>
						<c:when test="${opreateType == 'add'}">
						<input type="hidden"  id="svrCodeOld" value="${serverCode}"  />
						</c:when>
						<c:when test="${opreateType == 'update' || opreateType == 'view'}">
						<input type="hidden"  id="svrCodeOld" value="${umTTask.svrCode}"  />
						</c:when>
					</c:choose>
				</td> 
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>功能类型</td>
				<td class="long"><c:choose>
						<c:when test="${opreateType == 'add'}">
							<ce:radio cssClass="easyui-validatebox"
								list="#@java.util.LinkedHashMap@{'menu':'菜单','button':'按钮','portal':'门户'}"
								name="umTTask.taskType" value="menu" id="taskType"
								required="true" />
						</c:when>
						<c:when test="${opreateType == 'update' || opreateType == 'view'}">
							<input type="text" name="umTTask.taskType" id="taskType"
								class="input_w w_15" value="${umTTask.taskType}"
								readonly="readonly">
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">上级功能代码</td>
				<td class="long"><input type="text"
					class="input_w w_30 selectcode" name='umTTask.upperTaskCode'
					id='upperTaskCode' value="${umTTask.upperTaskCode}"
					readonly="readonly"></td>
				<td class="bgc_tt short">上级功能名称</td>
				<td class="long"><input type="text" id="variableTypeName"
					name="umTTask.upperTaskName" class="input_w w_15"
					value="${umTTask.upperTaskName}"><input type="hidden"
					id="variableTypeId" name="umTTask.upperTaskId" class="input_w w_15"
					value="${umTTask.upperTaskId}"></td>
			</tr>
			<tr>
				<td class="bgc_tt short">失效日期</td>
				<td class="long"><input class='input_w w_15'
					name="umTTask.expireDate" id="umTTask.expireDate"
					value="${umTTask.expireDate}" onclick="new WdatePicker(this)">
				</td>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>有效状态</td>
				<td class="long"><ce:select name="umTTask.validStatus"
						list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
						value="umTTask.validStatus" />
				</td>
			</tr>
			<c:if test="${operateType == 'view'}">
				<tr>
					<td class="bgc_tt short">创建人代码</td>
					<td class="long">${umTTask.creatorCode}</td>
					<td class="bgc_tt short">创建时间</td>
					<td class="long">${umTTask.insertTimeForHis}</td>
				</tr>
				<tr>
					<td class="bgc_tt short">更新人代码</td>
					<td class="long">${umTTask.updaterCode}</td>
					<td class="bgc_tt short">更新时间</td>
					<td class="long">${umTTask.operateTimeForHis}</td>
				</tr>
			</c:if>
			<tr>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>用户访问类型</td>
				<td class='long'>
				<c:choose>
						<c:when test="${opreateType == 'add'}">
				<ce:radio cssClass="easyui-validatebox"
								list="#@java.util.LinkedHashMap@{'01':'内部','02':'外部'}"
								name="umTTask.userType" value="01" id="userType"
								required="true" />
				</c:when>
						<c:when test="${opreateType == 'update' || opreateType == 'view'}">
				<ce:radio cssClass="easyui-validatebox"
								list="#@java.util.LinkedHashMap@{'01':'内部','02':'外部'}"
								name="umTTask.userType" value="${umTTask.userType}" id="userType"
								required="true" />
				</c:when>
				</c:choose>
				</td>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>是否公开</td>
				<td class='long'>
						<ce:select name="umTTask.isOpen"
						list="#@java.util.LinkedHashMap@{'0':'否','1':'是'}"
						value="${umTTask.isOpen }" />&nbsp;&nbsp;开放等级:
						<c:choose>
							<c:when test="${opreateType == 'add'}">
							<ce:radio name="umTTask.openLevel" cssClass="easyui-validatebox"
							list="#@java.util.LinkedHashMap@{'00':'全部可用','01':'注册可用','02':'绑定可用'}"
							value="00" required="true" />
							</c:when>
							<c:when test="${opreateType == 'update' || opreateType == 'view'}">
							<ce:radio name="umTTask.openLevel" cssClass="easyui-validatebox"
							list="#@java.util.LinkedHashMap@{'00':'全部可用','01':'注册可用','02':'绑定可用'}"
							value="${umTTask.openLevel }" required="true" />
							</c:when>
						</c:choose>
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">访问URL</td>
				<td class="long" colspan="3"><input style="width:600px" class='input_w w_45' name="umTTask.methodCode"
					id="umTTask.methodCode" value="${umTTask.methodCode}"></td>
			</tr>
		</table>

		<br>
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
	<div id="win" iconCls="icon-save" title="请选择上级功能"
		data-options="closed:true">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
</body>
</html>
