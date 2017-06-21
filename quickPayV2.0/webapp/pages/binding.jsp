<%@ include file="/common/taglibs_mobile.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" /> 
<link rel="stylesheet" href="${ctx}/widgets/jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.css">
<script src="${ctx}/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
<script src="${ctx}/widgets/jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.js"></script>

<script type="text/javascript">
	/*发送微信消息*/
		function binding() {
			var formdata = $('#fm').serialize();
			var url = contextRootPath + '/um/umtuserrelation/binding.do';
			$.ajax({
				type : "POST",
				url : url,
				data : formdata,
				success : function(result) {
					result = $.parseJSON(result);
					if (result.resCode == '0000') {
						$('#userName').attr("disabled",true); 
						$('#userCode').attr("disabled",true); 
						$('#mobileNo').attr("disabled",true); 
						$('#subBtn').attr("disabled",true); 
						$('#subBtn').attr("value","已签到"); 
					}else if(result.resCode == '0001') {
						//$('#userName').attr("disabled",true); 
						//$('#userCode').attr("disabled",true); 
						//$('#mobileNo').attr("disabled",true); 
						//$('#subBtn').attr("disabled",true); 
						//document.getElementById("subBtn").value="修改";
						$('#subBtn').attr("value","修改").button(); 
					}
					$('#subBtn').button("refresh"); 
					$("#tips").html(result.resCode+":"+result.resMsg);

				},
				error : function(result) {
					$("#tips").html('签到失败，请稍后再试！');
				}
			});
		}
</script>
</head>

<body>
<div data-role="page" >
<div data-role="content">
    <form name="fm" id="fm" method="post">
    	 <input type="hidden" name="platId" id="platId"	 value='${platId}' >
    	 <input type="hidden" name="userId" id="userId"	 value='${userId}' >
      <c:if test="${userName ==''}">
      <label for="userName">姓名 ：</label>
      <input type="text" name="userName" id="userName" value=''  >
      <label for="userCode">工号：</label>
      <input type="text" name="userCode" id="userCode" value='' >
      <label for="userCode">手机：</label>
      <input type="text" name="mobileNo" id="mobileNo" value='' >
      <input type="button" id="subBtn" data-inline="true" value="签到" onclick="binding()">
      <label for=""><font color="red"><span id="tips"></span></font> </label>
      </c:if>
      
      <c:if test="${userName !=''}">
      <label for="userName">姓名 ：</label>
      <input type="text" name="userName" id="userName" value='${userName}' disabled >
      <label for="userCode">工号：</label>
      <input type="text" name="userCode" id="userCode" value='${userCode}' disabled >
      <label for="comCode">归属：</label>
      <input type="text" name="comCode" id="comCode" value='${comCode}' disabled >
      <label for="userCode">手机：</label>
      <input type="text" name="mobileNo" id="mobileNo" value='${mobileNo}' disabled >
	      <c:if test="${validStatus =='1'}">
	      <input type="submit" data-inline="true" value="已签到" onClick="" disabled>
	      </c:if>
      
	      <c:if test="${validStatus =='0'}">
	      <input type="submit" data-inline="true" value="审核中" onClick="" disabled>
	      </c:if>
      <label for=""><font color="red"><span id="tips">如有归属错误或其它问题，请微信回复在线客服</span></font> </label>
      </c:if>
    </form>
  </div>
</div>


</body>
</html>
