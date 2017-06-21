var bindAction = contextRootPath + "/um/umtuserbind/userBind.do";

function executeBind(){
	//数据校验
//	if(!checkForm()) {
//		document.getElementById('tip').style.display = '';
//		return false;
//	}
	document.getElementById('tip').style.display = 'none';
	
	//将数据传送到后他
	var data = $("#fm").serialize();
	var send_url = bindAction + "?" + data;
	//alert(send_url);
	$.ajax({
		   type: "POST",
		   url: send_url,
		   success: function(result){
			   if(result != null){
				  var obj=eval("(" + result + ")");
				  //alert(obj.umTUserBind.returnPageBindResult);
				  if(obj.umTUserBind.returnPageBindResult == 'hasBindSuccess'){
					  window.parent.window.$.messager.alert('提示信息','你已经是人保绑定的客户,不需要再绑定！','info');
				  }else if(obj.umTUserBind.returnPageBindResult == 'success'){
					  window.parent.window.$.messager.alert('提示信息','恭喜您绑定成功！','info');
				  }else if(obj.umTUserBind.returnPageBindResult == 'fail'){
					  window.parent.window.$.messager.alert('错误信息','绑定失败!','error');
				  }else{
					  window.parent.window.$.messager.alert('错误信息','绑定失败!','error');
				  }
			   }else{
				   window.parent.window.$.messager.alert('绑定结果','绑定不成功');
			   }
		   },
		   error:function(result){
			   alertErrorMsgForEasyUi(result);
		   }

	});
}


function checkForm() {
	var name = $("#umTUserBind\\.customerName");
	var identityno = $("#umTUserBind\\.bindValue");
	
	var tip = $("#tip>span");
	
	if(name.val().trim().empty()) {
		tip.text('姓名不能为空！请填写真实姓名。');
		name.focus();
		return false;
	}
	if(!nameValidate(name.val().trim())){
		tip.text('姓名有误！请填写真实姓名。');
		name.focus();
		return false;
	}
	if(identityno.val().trim().empty()) {
		tip.text('身份证号码不能为空！请填写真实身份证号码。');
		identityno.focus();
		return false;
	}
	if(!idCardValidate(identityno.val().trim())){
		tip.text('身份证号码不能为空！请填写真实身份证号码。');
		identityno.focus();
		return false;
	}
	return true;	
}