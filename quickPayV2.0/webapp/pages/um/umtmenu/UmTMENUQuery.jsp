<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTMENU"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtmenu/UmTMENU.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#state').combobox({
			onSelect : function(record) {
				var flag = $('#state').combobox('getValue');
				var tage = "#umTMENU\\.validStauts";
				var dom = $(tage).val(flag);
			}
		});

	});
</script>
</head>
<body>
	
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">菜单ID</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.id.menuId" id="umTMENU.id.menuId"
							value="${umTMENU.id.menuId}"></td>
						<td class="bgc_tt short">菜单名称</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.menuName" id="umTMENU.menuName"
							value="${umTMENU.menuName}"></td>
					</tr>
					<tr>
						<td class="bgc_tt short">功能代码</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.taskCode" id="umTMENU.taskCode"
							value="${umTMENU.taskCode}"></td>
						<td class="bgc_tt short">菜单层级</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.level" id="umTMENU.level" value="${umTMENU.level}">
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">适用范围</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.scope" id="umTMENU.scope" value="${umTMENU.scope}">
						</td>
						<td class="bgc_tt short">菜单URL</td>
						<td class="long"><input class='input_w w_15'
							name="umTMENU.actionUrl" id="umTMENU.actionUrl"
							value="${umTMENU.actionUrl}"></td>
					</tr>

					<tr>
						<td class="bgc_tt short">有效状态</td>
						<td class="long"><select id="state" class="easyui-combobox"
							name="state" panelHeight="65">
								<option value="">所有</option>
								<option value="0">无效</option>
								<option value="1">有效</option>
						</select> <input class='input_w w_15' name="umTMENU.validStauts"
							id="umTMENU.validStauts" value="${umTMENU.validStauts}"
							style="display: none;" /></td>
						<td class="bgc_tt short">标志字段</td>
						<td class="long"></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="查 询" onclick="executeQuery();">
							<input type="button" id="addButton" onclick="prepareAdd();"
							class="button_ty" value="增 加"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<br>
	<table id="UmTMENUTable"></table>
</body>
</html>
