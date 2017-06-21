function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg
	});
}

$(function(){
	//绑定保存按钮信息
	var effectRow = new Object();
	$("#saveDictionaryDetail").bind("click",function(){
		showProcess(true, '温馨提示', '正在更新数据字典明细表信息...');
		var count = $("#dictionaryDetail tbody tr").length;								//当前数据权限明细所对应的Table
		if(count<1){
			showProcess(false);
			$.messager.alert("提示","没有需要处理的数据");
		}else {
			var flag = true;
			var rows = document.getElementById("dictionaryDetail").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
			var inputElements = null;
			for(var index=0;index<rows.length;index++){
				inputElements = rows[index].getElementsByTagName("input"); 
				for(var pos = 0;pos<inputElements.length;pos++){
					//处理IE8无法访问属性的异常
					if($(inputElements[pos]).attr("type")!="hidden"&&$(inputElements[pos]).attr("required")=="required"){
						//只对需要校验的字段进行处理
						if(inputElements[pos].value==""){
					        $.messager.alert("错误提示","第"+(index+1)+"行,第"+(pos-1)+"列:"+inputElements[pos].name+"录入数据错误,不能为空");
					        flag = false;
					        showProcess(false);
					        return false;
						}
						if(inputElements[pos].validity!=undefined){
							if (!inputElements[pos].validity.valid) {
						        $.messager.alert("错误提示","第"+(index+1)+"行,第"+(pos-1)+"列:"+inputElements[pos].name+"录入数据错误");
						        flag = false;
						        showProcess(false);
						        return false;
						    }
						}
					}
				}
			}
			if(!flag){
				return;
			}
			var inserted = new Array();
			var updated = new Array();
			var deleted = new Array();
			var insert = null,update = null,del = null;
			var rows = null,flaginput = null;
			for(var index = 1;index<=count;index++){
				rows = document.getElementById("dictionaryDetail").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
				flaginput = rows[index-1].getElementsByTagName("input"); 
				if(flaginput[0].value=="I"){
					//添加数据
					insert = new Object();
					insert.dictionaryId = document.getElementById("umTDictionary.id.dictionaryId").value;
					insert.targetName = $("input[name='targetName']")[index].value;
					insert.targetField = $("input[name='targetField']")[index].value;
					insert.validStatus = document.getElementsByName("validStatus")[index].value;
					inserted.push(insert);
				}else if(flaginput[0].value=="U"){
					update = new Object();
					update.dictionaryDetailId = $("input[name='dictionaryDetailId']")[index].value;
					update.dictionaryId = document.getElementById("umTDictionary.id.dictionaryId").value;
					update.serialNo = $("input[name='serialNo']")[index].value;
					update.targetName = $("input[name='targetName']")[index].value;
					update.targetField = $("input[name='targetField']")[index].value;
					update.validStatus = document.getElementsByName("validStatus")[index].value;
					updated.push(update);
				}else if(flaginput[0].value=="D"){
					del = new Object();
					del.dictionaryDetailId = $("input[name='dictionaryDetailId']")[index].value;
					deleted.push(del);
				}
			}
			effectRow["inserted"] = JSON.stringify(inserted);
			effectRow["updated"] = JSON.stringify(updated);
			effectRow["deleted"] = JSON.stringify(deleted);
			
			if("[]"==effectRow["inserted"]&&"[]"==effectRow["updated"]&&"[]"==effectRow["deleted"]){
				showProcess(false);
				$.messager.alert("提示", "没有修改数据,无须提交!","info");
			}else {
				$.post(
						contextRootPath
								+ "/um/umtdictionarydetail/rowEdit.do?dictionaryId="
								+ document
										.getElementById("umTDictionary.id.dictionaryId").value,
						effectRow, function(rsp) {
							if (rsp.status=="true") {
								$.messager.alert("提示", "操作成功!","info",function(){
									showProcess(false);
									history.go(0);
								});
							}else {
								$.messager.alert("提示", rsp.errormsg,"error",function(){
									showProcess(false);
									history.go(0);
								});
							}
						}, "JSON").error(function() {
					$.messager.alert("提示", "操作失败");
				});
			}
		}
	});
});