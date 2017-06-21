<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/portal/js/init.js"></script>
<script type="text/javascript">
	$(function(){
		var ding=${jsonArr};
		var data = [{"id":"","title":"请选择","url":""}].concat(ding);//将请选择设置为第一个选项
		$('#portletname').combobox({
			data:data,
			valueField:'id',
			textField:'title',
			multiple:false,
			editable:false
			});
	})
	function formDataValidate() {
		var portletname = $('#portletname').combobox('getText');
		var key = $('#portletname').combobox('getValue');
		if(key =="" && portletname =="请选择"){
			alert("请选择要添加的模块！");
			return false;
		}
		finishAdd();
		return true; 
	}

	
	function finishAdd(){
		var key = $('#portletname').combobox('getValue');
		var name = $('#portletname').combobox('getText');
		var data = $('#portletname').combobox('getData');
		var selectedUrl;
		for(var i=0;i<data.length;i++){
			var datai = data[i];
			if(datai.id == key){
				selectedUrl = datai.url;
				break;
			}
		}
//		var rows = $('#portletname').combobox('getSelections');
//		var url = contextRootPath + "/portal/finishAdd.do?portletId="+key;
//		$.ajax({
//			type : "POST",
//			url : url,

//			success : function(result) {
				var handle = new Object();
				var one = new Object();
				handle['key']=key;
				handle['name']=one;
				one['title']=name;
				one['url']=selectedUrl;
				window.returnValue = handle;
				window.close();						  
//			},
//			error : function(msg) {
//				$.messager.alert('出现错误', msg, 'error');
//			}
//		});
	};
</script>
</head>
<body>
<div class="right_detail_top"><h3>添加模块</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">模块名</td>
						<td class="long"><input name="portletname" id="portletname" class="easyui-combobox" />					
						</td>				
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="button_ty" value="确  定" onclick="formDataValidate();">
							<input type="button" class="button_ty" value="取  消" onclick="window.close();"/></td>
					</tr>					
				</table>
			</div>
		</div>
	</form>
</body>
</html>