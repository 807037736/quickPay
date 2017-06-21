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

$(function() {
	// 绑定保存按钮信息
	var effectRow = new Object();
	$("#saveUserPower")
			.bind(
					"click",
					function() {
						
						showProcess(true, '温馨提示', '正在配置用户数据权限...');
						
						var flag = true;
						var rows = document.getElementById("userPower").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
						var inputElements = null;
						for(var index=0;index<rows.length;index++){
							inputElements = rows[index].getElementsByTagName("input"); 
							for(var pos = 0;pos<inputElements.length;pos++){
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
						
						var count = $("#userPower tbody tr").length; // 当前数据权限明细所对应的Table
						if (count < 1) {
							showProcess(false);
							$.messager.alert("提示", "没有需要处理的数据");
						} else {
							var inserted = new Array();
							var updated = new Array();
							var deleted = new Array();
							var insert = null, update = null, del = null;
							var rows = null, flaginput = null;
							for ( var index = 1; index <= count; index++) {
								rows = document.getElementById("userPower")
										.getElementsByTagName("tbody")[0]
										.getElementsByTagName("tr");
								flaginput = rows[index - 1]
										.getElementsByTagName("input");
								if (flaginput[0].value == "I") {
									// 添加数据
									insert = new Object();
									insert.dictionaryId = $("input[name='dictionaryId']")[index].value;
									insert.operationSymbol = document.getElementsByName("operationsymbol")[index].value;
									insert.userCode = document
											.getElementById("umTUser.id.userCode").value; // 获取分配的用户名
									insert.operatorType = "PERSON";
									insert.powerValue = $("input[name='powervalue']")[index].value;
									insert.validStatus = document
											.getElementsByName("validStatus")[index].value;
									inserted.push(insert);
								} else if (flaginput[0].value == "U") {
									update = new Object();
									update.userPowerId = $("input[name='userpowerid']")[index].value;
									update.operatorType = "PERSON"; // 配置给个人的数据权限信息
									update.dictionaryId = $("input[name='dictionaryId']")[index].value;
									update.userCode = document
											.getElementById("umTUser.id.userCode").value;
									update.operationSymbol = document.getElementsByName("operationsymbol")[index].value;
									update.powerValue = $("input[name='powervalue']")[index].value;
									update.validStatus = document
											.getElementsByName("validStatus")[index].value;
									updated.push(update);
								} else if (flaginput[0].value == "D") {
									del = new Object();
									del.userPowerId = $("input[name='userpowerid']")[index].value;
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
												+ "/um/umtuserpower/rowEdit.do",
										effectRow, function(rsp) {
											if (rsp.status=="true") {
												$.messager.alert("提示","操作成功","info",function(){
													showProcess(false);
													history.go(0);
												});
											}else {
												$.messager.alert("错误提示",rsp.msg,"error",function(){
													showProcess(false);
													history.go(0);
												});
											}
										}, "JSON").error(function() {
									$.messager.alert("提示", "提交错误了!","error",function(){
										history.go(0);
									});
								});
							}
						}
					});
});