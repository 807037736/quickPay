<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script type="text/javascript">
	function showProcess(isShow, title, msg) {
		if (!isShow) {
			$.messager.progress('close');
			return;
		}
		var win = $.messager.progress({
			title: title,
			msg: msg,
			text:''
		});
	}
	/**同步群组信息**/
	function executeSync(){
		var data = $("#fm").serialize();
		$.ajax({
			type:'POST',
			data:data,
			url:'addCognosGroup.do',
			beforeSend:function(){
				showProcess(true,"温馨提示","正在同步Cognos群组信息");
			},
			success:function(value){
				showProcess(false);
				var jsonValue = $.parseJSON(value);
				if(jsonValue.success==true){
					$.messager.alert("提示",jsonValue.message,"info");
				}else {
					$.messager.alert("提示",jsonValue.message,"error");
				}
			},
			failure:function(){
				show(false);
				$.messager.alert("提示","无法访问,请联系系统管理员","error");
			}
		});
	}
</script>
</head>
<body>
	
	<div class="right_detail_top"><h3>Cognos群组信息同步</h3></div>

	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">同步组类别:</td>
						<td class="long"><ce:codeselect codeType="CognosGroupType" name="groupType" id="groupType" cssClass="input_w"></ce:codeselect></td>
					</tr>
					<tr>
						<td class="bgc_tt short">同步组代码:</td>
						<td class="long"><input class='input_w w_30' name="groupCode" id="groupCode"><span style="color:red">*多个组之间用逗号进行分隔</span></td>
					</tr>
					<tr>
						<td colspan=4 align="center">
							<input type="button" class="button_ty" onclick="executeSync();" ind="ind" value="同步" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
