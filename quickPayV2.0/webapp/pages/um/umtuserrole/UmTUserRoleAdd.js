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
	
	/**
	 * 判断字符串数组中是否存在字符串
	 */
	isHave = function(strArray, str){
		var len = strArray.length;
		if(len >0 ){
			for(var i=0;i<len;i++){
				if(strArray[i] == str){
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
		
	};
	
	/** 注册CheckBox选择事件* */

	var roleArray = new Array();						// 定义用户本身的RoleArray数组
		
	var addArray = new Array();
	var delArray = new Array();
	
	/**
	 * 保存用户角色关联信息
	 */
	saveUserRole = function(){
		var effectRow = new Object();
		effectRow["inserted"] = JSON.stringify(addArray.toString());						// 获取新加入的角色列表
		effectRow["deleted"] = JSON.stringify(delArray.toString());							// 获取减少的角色列表
		/** 向后台递交Ajax请求处理* */
		$.ajax({
			url:'addRoleToUser.do?userCode='+document.getElementById("umTUser.id.userCode").value,
			data:effectRow,
			beforeSend:function(){
				showProcess("温馨提示","正在保存用户的角色信息...请稍候...");
			},
			success:function(data){
				showProcess(false);
				var result = JSON.parse(data);
				if(result.success){
					$.messager.alert("提示",result.message,"info",function(){
						history.go(0);
					});
				}else {
					$.messager.alert("提示",result.message,"error",function(){
						history.go(0);
					});
				}
			}
		});
		
		
		
//		$.post("addRoleToUser.do?userCode="+document.getElementById("umTUser.id.userCode").value+"",
//				effectRow,
//		function(data){
//			
//		});
	};
	
	
	var selectTable = $("#roleList");
	// post请求至后台进行数据请求

	$.ajax({
		type:"POST",
		url:'getRoleListByComCode.do',
		beforeSend:function(){
			showProcess(true, '温馨提示', '正在查询本机构角色信息...请稍候...');
		},
		success:function(result){
			showProcess(false);
			$.ajax({
				type:'POST',
				url:contextRootPath +"/um/umtuserrole/query.do",
				data:{
					"umTUserRole.userCode":document.getElementById("umTUser.id.userCode").value,
					"umTUserRole.validStatus":"1"
				},
				beforeSend:function(){
					showProcess(true, '温馨提示', '正在查询该用户角色信息...请稍候...');
				},
				success:function(value){
					showProcess(false);
					var jsonValue = $.parseJSON(value);
					$.each(jsonValue.rows,function(idx,itm){
						roleArray.push(itm.roleId);
					});
					result =  $.parseJSON(result);
					$.each(result.data,function(index,item){
						if(isHave(roleArray,item['id.roleId'])){
							selectTable.append("<tr><td style='text-align:center'><input readonly='readonly' type='text' name='roleId' value='"+item['id.roleId']+"' style='width:250px;border:#fff;text-align:center'/></td>" +
									"<td style='text-align:center'><input  readonly='readonly' type='text' name='rolecname' value='"+item['roleCName']+"' style='width:400px;border:#fff;text-align:center' /></td>" +
									"<td style='text-align:center'><input type='checkbox' name='roleSelectValue' id='rolechecked"+index+"' checked='checked'/></td></tr>");
						}else {
							selectTable.append("<tr><td style='text-align:center'><input  readonly='readonly'  type='text' name='roleId' value='"+item['id.roleId']+"' style='width:250px;border:#fff;text-align:center'/></td>" +
									"<td style='text-align:center'><input readonly='readonly' type='text' name='rolecname' value='"+item['roleCName']+"' style='width:400px;border:#fff;text-align:center' /></td>" +
									"<td style='text-align:center'><input type='checkbox' name='roleSelectValue' id='rolechecked"+index+"' /></td></tr>");
						}
					});
			
					$("input[type=checkbox]").change(function(){
						var roleId = $(this).parent().prev().prev().children().val();
						if(isHave(roleArray,roleId)&&this.checked==false){
							// 取消角色
							delArray.push(roleId);		// 加入取消的角色
						}else if(isHave(roleArray,roleId)&&this.checked==true){
							if(isHave(delArray,roleId)){
								// 之前如已加入 则取消
								for(var i=0;i<delArray.length;i++){
									if(delArray[i]==roleId){
										delArray.splice(i, 1);
									}
								}
							}
						}else if(!isHave(roleArray,roleId)&&this.checked==true){
							// 添加角色
							addArray.push(roleId);
						}else if(!isHave(roleArray,roleId)&&this.checked==false){
							if(isHave(addArray,roleId)){
								for(var i=0;i<addArray.length;i++){
									if(addArray[i]==roleId){
										addArray.splice(i, 1);
									}
								}
							}
						}
					});
				}
			});
		}
	});
});