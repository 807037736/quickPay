var selectStr;					//定义全局变量用于记录当前值
var addStr = new Array();
var deleteStr = new Array();

var effectRow = new Object();

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

function getChecked(){
	
	var nodes2 = $('#tasktree').find('.tree-checkbox1,.tree-checkbox2');
	var param = {};
	param["userCode"]=document.getElementById("umTUser.id.userCode").value;
	for ( var i = 0; i < nodes2.length; i++) {
		param["cacheNameArray[" + i + "]"]  = $(nodes2[i]).parent().attr('node-id');
	}
	//alert(JSON.stringify(param));
	$.messager.confirm('提示', '是否更新?', function(r){
		if (r){
			showProcess(true, '温馨提示', '正在加载用户功能数据...');
			var url = contextRootPath+"/um/umtusertask/addTask4User.do";
			//alert(url);
			$.ajax({
					   type: "POST",
					   url: url,
					   data:param,
					   success: function(result){
						   showProcess(false);
						   $.messager.alert('提示信息','提交成功！ ','info');
						   $.post("/khyx/cache/reloadUserTaskCache.do",{userCode:document.getElementById("umTUser.id.userCode").value},function(value){
								if(value.success){
									$.messager.alert("提示","用户功能更新成功","info",function(){
										window.opener='';
										window.close();
									});
								}else {
									$.messager.alert("错误提示","用户功能缓存信息更新失败");
								}
							},"json");
						    },
						 error:function(result){
						    alertErrorMsgForEasyUi(result);
						    }

			});
		}
	});
	
	

	/**POST请求传输后台进行存储**/
	/*effectRow["add"] = JSON.stringify(addStr.toString());																	//添加原来没有的功能
	effectRow["delete"]  = JSON.stringify(deleteStr.toString());															//取消原来有的功能
	$.post("addTaskToUser.do?userCode="+document.getElementById("umTUser.id.userCode").value,effectRow,function(result){
		if(result.success){
			//对用户功能更新完成(同步更新缓存)
			$.post("/khyx/cache/reloadUserTaskCache.do",{userCode:document.getElementById("umTUser.id.userCode").value},function(value){
				if(value.success){
					$.messager.alert("提示","用户功能更新成功","info",function(){
						window.opener='';
						window.close();
					});
				}else {
					$.messager.alert("错误提示","用户功能缓存信息更新失败");
				}
			},"json");
		}else {
			$.messager.alert("错误提示",result.errorMsg);
		}
//		$.messager.alert("提示",data);
	},"json");*/
}

function getValueByCode(data,text){
	for(var index=0;index<data.length;index++){
		if(data[index].text == text){
			return data[index].editable;
		}else if(data[index].children.length>0){
			//进行子节点寻找
			var value =  getValueByCode(data[index].children,text);
			if(value!=undefined){
				return value;
			}else {
				continue;
			}
		}
	}
}



function hasChild(data,text){
	for(var i=0;i<data.length;i++){
		if(data[i].text==text){
			if(data[i].children.length>0){
				return true;
			}else {
				return false;
			}
		}
	}
}


$(function() {
	var objectId=document.getElementById("umTUser.id.userCode").value;
	/*add by liuyatao2014年8月1日*/
	var serverCode = $('#serverCode').val();
	//alert(serverCode);
	createTree('tasktree', 10, '&objectId='+objectId, true,serverCode);
	
	
	//showProcess(true, '温馨提示', '正在加载用户功能数据...');
	
	/*$("#tasktree").tree({
		animate : true,
		checkbox : true,
		url : '../umttask/findTaskTree.do?userCode='+document.getElementById("umTUser.id.userCode").value,
		cascadeCheck : true,
		onlyLeafCheck : false
		,
		onLoadSuccess : function(node, data) {
			//showProcess(false);
			// 循环处理
			var nodes = $(this).find(".tree-checkbox");
			alert(nodes.length);
			for ( var i = 0; i < nodes.length; i++) {
//				if (nodes[i].classList.contains("tree-checkbox1")) {
				if (nodes[i].className.indexOf("tree-checkbox1")) {
//					if(!getValueByCode(data,nodes[i].nextElementSibling.innerText)){
					if(!getValueByCode(data,nodes[i].nextSibling.innerText)){
						nodes[i].style.visibility = "hidden";
						
					}
				}
			}
			//记录当前已选择的项值
			var nodes2 = $('#tasktree').tree('getChecked');
			var s = '';
			for ( var i = 0; i < nodes2.length; i++) {
				if (s != '')
					s += ',';
				s += nodes2[i].id;
			}
			selectStr = s;

		},
		onCheck:function(node,checked){
			if(selectStr!=null){
				if(selectStr.toString().indexOf(node.id)==-1&&checked==true&&addStr.toString().indexOf(node.id)==-1){
					addStr.push(node.id);
				}else if(selectStr.toString().indexOf(node.id)==-1&&checked==false&&addStr.toString().indexOf(node.id)!=-1){
					//如果用户点击取消按,则再重新删除该元素
					for(var i=0;i<addStr.length;i++){
						if(addStr[i]==node.id){
							addStr.splice(i, 1);
						}
					}
				}else if(selectStr.toString().indexOf(node.id)!=-1&&checked==false&&deleteStr.toString().indexOf(node.id)==-1){
					deleteStr.push(node.id);
				}else if(selectStr.toString().indexOf(node.id)!=-1&&checked==true&&deleteStr.toString().indexOf(node.id)!=-1){
					for(var i=0;i<deleteStr.length;i++){
						if(addStr[i]==node.id){
							deleteStr.splice(i, 1);
						}
					}
				}
			}
		}
	});*/
});