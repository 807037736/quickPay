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
	
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document)
			.ready(
					function() {

						//设置有效状态
						$('#state').combobox({
							onSelect : function(record) {
								var flag = $('#state').combobox('getValue');

								var tage = "#umTMENU\\.validStauts";
								var dom = $(tage).val(flag);
							}
						})
						var validStauts = "${umTMENU.validStauts}";
						if (validStauts != "") {
							$("#state").combobox('select',
									"${umTMENU.validStauts}");
						}

						//点击查看时设置全为只读
						if ($('#operateType').val() == 'view') {
							setReadonlyOfAllInput("fm");
						}

						//创建窗口
						$('#win').window({

							width : 500,

							height : 400,

							modal : true,

							closed : true

						});

						//如果点击不是查看绑定双击，显示树
						if ($('#operateType').val() != 'view') {
							$("#umTMENU\\.upperMenuId")
									.dblclick(
											function() {
												var menu = "${menu}";
												creatTree(menu);
												$('#win').window('open');
												$('#tt')
														.tree(
																{
																	onDblClick : function(
																			node) {
																		var id = node.id;
																		var level = node.attributes.level + 1;

																		$(
																				'#umTMENU\\.upperMenuId')
																				.val(
																						id);
																		var input = "<input  type=\"hidden\" class=\'input_w w_15\' name=\"umTMENU.level\" id=\"umTMENU.level\" value=\"${umTMENU.level}\">"
																		$(
																				'#level')
																				.html(
																						level
																								+ input);
																		$(
																				'#umTMENU\\.level')
																				.val(
																						level);
																		$('#win').window('close');

																	}
																});

											});
						}
					});
</script>
</head>

<body>

	<input type="hidden" name="operateType" id="operateType"
		value="${operateType}" />
	<form name="fm" id="fm" action="/um/umtmenu" namespace="/um/umtmenu"
		method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
						<c:choose>
							<c:when test="${operateType == 'update'}">
					修改UmTMENU信息
				</c:when>
							<c:when test="${operateType == 'add'}">
					增加UmTMENU信息
				</c:when>
							<c:when test="${operateType == 'view'}">
					查看UmTMENU信息
				</c:when>
						</c:choose>
					</h2></td>
			</tr>
			<tr>
			<tr>
				<td class="bgc_tt short">菜单ID</td>
				<td class="long"><c:choose>
						<c:when test="${operateType == 'update'}">
${umTMENU.id.menuId}<input type="hidden" name="umTMENU.id.menuId"
								id="umTMENU.id.menuId" value="${umTMENU.id.menuId}">
						</c:when>
						<c:when test="${operateType == 'add'}">
${umTMENU.id.menuId}<input type="hidden" class='input_w w_15'
								name="umTMENU.id.menuId" id="umTMENU.id.menuId"
								value="${umTMENU.id.menuId}">
						</c:when>
						<c:when test="${operateType == 'view'}">
${umTMENU.id.menuId}<input type="hidden" name="umTMENU.id.menuId"
								id="umTMENU.id.menuId" value="${umTMENU.id.menuId}">
						</c:when>
					</c:choose>
				</td>
				<td class="bgc_tt short">上级菜单ID</td>
				<td class="long"><input style="background-color: #F5F5DC"
					class='input_w w_15' name="umTMENU.upperMenuId"
					id="umTMENU.upperMenuId" value="${umTMENU.upperMenuId}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">功能ID</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.taskId" id="umTMENU.taskId" value="${umTMENU.taskId}">
				</td>
				<td class="bgc_tt short">菜单层级</td>
				<td class="long" id="level">${umTMENU.level}<input
					type="hidden" class='input_w w_15' name="umTMENU.level"
					id="umTMENU.level" value="${umTMENU.level}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">适用范围</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.scope" id="umTMENU.scope" value="${umTMENU.scope}">
				</td>
				<td class="bgc_tt short">系统代码</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.systemCode" id="umTMENU.systemCode"
					value="${umTMENU.systemCode}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">菜单名称</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.menuName" id="umTMENU.menuName"
					value="${umTMENU.menuName}">
				</td>
				<td class="bgc_tt short">菜单URL</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.actionUrl" id="umTMENU.actionUrl"
					value="${umTMENU.actionUrl}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">显示序号</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.displayNo" id="umTMENU.displayNo"
					value="${umTMENU.displayNo}">
				</td>
				<td class="bgc_tt short">功能代码</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.taskCode" id="umTMENU.taskCode"
					value="${umTMENU.taskCode}">
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">有效状态</td>
				<td class="long"><select id="state" class="easyui-combobox"
					name="state" panelHeight="45">
						<option value="0">无效</option>
						<option selected="selected" value="1">有效</option>
				</select> <input class='input_w w_15' name="umTMENU.validStauts"
					id="umTMENU.validStauts" value="1" style="display: none;" />
				</td>
				<td class="bgc_tt short">创建人代码</td>
				<td class="long">${umTMENU.creatorCode}</td>
			</tr>
			<tr>
				<td class="bgc_tt short">创建时间</td>
				<td class="long">${umTMENU.insertTimeForHis}</td>
				<td class="bgc_tt short">更新人代码</td>
				<td class="long">${umTMENU.updaterCode}</td>
			</tr>
			<tr>
				<td class="bgc_tt short">更新时间</td>
				<td class="long">${umTMENU.operateTimeForHis}</td>
				<td class="bgc_tt short">标志字段</td>
				<td class="long"><input class='input_w w_15'
					name="umTMENU.flag" id="umTMENU.flag" value="${umTMENU.flag}">
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
					value="关闭" />
				</td>
			</tr>
		</table>
	</form>
	<div id="win" iconCls="icon-save" title="请选择上级菜单"
		data-options="closed:true">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
</body>
</html>
