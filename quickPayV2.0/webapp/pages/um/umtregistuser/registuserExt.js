 
function bangding(){
	if(check()){
		document.getElementById('tip').style.display = 'none';
		fm.action = contextRootPath+"/um/umtregistuser/bangding.do";
		fm.submit();
	}
	
}
 
	function check() {
		
		var identifyno = $("#identifyno");
		var licenseno = $("#licenseno");
		
		var tipDiv = $("#tip");
		var tip = $("#tip>span");
		
		if(licenseno.val().trim()=='') {
			tip.text('车牌不能为空！请填写车牌。');
			licenseno.focus();
			tipDiv.show();
			return false;
		}
		
		if(identifyno.val().trim()=='') {
			tip.text('证件号不能为空！请填写被保险人身份证。');
			tipDiv.show();
			identifyno.focus();
			return false;
		}
		 
    	tip.text('');
    	tipDiv.hide();
		return true;	
	}
 
	
 

	/*
	 * 判断是否已经投保过
	 */
	function goPerson(){
		var param = document.getElementById("param1").value;
		fm.action = contextRootPath+"/um/umtregistuser/personCenter.do?param="+param;
		fm.submit();	 
	}

	 