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
</head>
<body>
<div class="right_detail_top"><h3>功能管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">功能代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTTask.taskCode" id="umTTask.taskCode"
							value="${umTTask.taskCode}">
						</td>
						<td class="bgc_tt short">功能名称</td>
						<td class="long"><input class='input_w w_15'
							name="umTTask.taskName" id="umTTask.taskName"
							value="${umTTask.taskName}">
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">上级功能代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTTask.upperTaskCode" id="umTTask.upperTaskCode"
							value="${umTTask.upperTaskCode}">
						</td>
						<td class="bgc_tt short">上级功能名称</td>
						<td class="long"><input class='input_w w_15'
							name="umTTask.upperTaskName" id="umTTask.upperTaskName"
							value="${umTTask.upperTaskName}">
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">系统代码</td>
						<td class="long">
							<select class="input_w w_15" id="svrCode" name="umTTask.svrCode"></select>
							<input type="hidden"  id="svrCodeOld" value="${serverCode}"  />
						</td>
						<td class="bgc_tt short">功能类型</td>
						<td class="long">
							<ce:select name="umTTask.taskType"
								list="#@java.util.LinkedHashMap@{'':'未选择','menu':'菜单','button':'按钮','portal':'门户'}" value="${umTTask.taskType}" cssStyle="width:9em;" />
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">失效日期</td>
						<td class="long"><input class='input_w w_15'
							name="umTTask.expireDate" id="umTTask.expireDate"
							value="${umTTask.expireDate}"
							onclick="WdatePicker()">
						</td>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><ce:select name="umTTask.validStatus"
								list="#@java.util.LinkedHashMap@{'':'未选择','1':'有效','0':'无效'}" value="umTTask.validStatus" cssStyle="width:9em;" />
						</td>
					</tr>
					<tr>
					<td class="bgc_tt short">是否公开</td>
					<td class='long'><ce:select name="umTTask.isOpen"
							list="#@java.util.LinkedHashMap@{'':'所有','0':'否','1':'是'}"
							value="umTTask.isOpen" cssStyle="width:9em;" />
					<td class="bgc_tt short">开放等级</td>
					<td class='long'><ce:select name="umTTask.openLevel"
							list="#@java.util.LinkedHashMap@{'':'无限制','00':'全部可用','01':'注册可用','02':'绑定可用'}"
							value="00" cssStyle="width:9em;" />
					</td>
					</tr>
					<tr>
						<td class="bgc_tt short">访问类型</td>
						<td>
						<ce:select list="#@java.util.LinkedHashMap@{'':'所有','01':'内部','02':'外部'}"
								name="umTTask.userType" id="userType" cssStyle="width:9em;" />
						</td>
						<td class="bgc_tt short">访问URL</td>
						<td class="long"><input class='input_w w_15' style="width: 300px"
						name="umTTask.methodCode" id="umTTask.methodCode"
						value="${umTTask.methodCode}">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTTaskTable"></table>
	</div>
</body>
</html>
