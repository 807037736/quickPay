<!DOCTYPE>
<%@ page session="false"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTAccount" %>

<%@ page import="org.springframework.security.AuthenticationException"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
   
    <title>深圳人保财险</title>
   	
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${ctx}/widgets/html5shiv.min.js"></script>
      <script src="${ctx}/widgets/respond.min.js"></script>
    <![endif]-->

	<link rel="shortcut icon" href="${ctx }/pages/image/picc_favicon.png">
   
    

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/common/js/sha1.js"></script>
    <script src="${ctx }/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
    <script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="${ctx }/widgets/jquery-validation-1.13.0/dist/jquery.validate.min.js" ></script>
    <script src="${ctx }/widgets/jquery-validation-1.13.0/dist/localization/messages_zh.min.js" type="text/javascript"></script>
   
    <script src="${ctx }/widgets/form_validate.js"></script>
    
    <link href="${ctx }/widgets/form_validate_jquery.css" rel="stylesheet"> 
    <link href="${ctx }/pages/um/umtregistuser/login.css" rel="stylesheet"> 
    
    <script>
    
    $.validator.setDefaults({
    	submitHandler: function() {
    		
    		fm[1].value = SHA1(fm[1].value);
    		//alert(fm[1].value);
    		fm.submit();
    	}
    });
    
    $(function(){
    	$(fm).validate({
    		errorLabelContainer: $('div#tip'),
    		errorLabelContainer: $('div#tip'),
    		messages: {
    			j_username: {
    				required: "请输入用户名/手机号"
    				},
    			j_password: { 
    				required: "请输入登录密码"
    			}
    		}
    	});
    });
    
    
    function fsubmit(obj){
    	
    	
    	$("#btn_login").attr("disabled","disabled");
    	/*
    	var username = $("#j_username").val();
    	var password = $("#j_password").val();
    	
    	if(username.trim().empty() || password.empty()){
    		$("#tip").show();
    		$("#tip>span").text("请填写用户名和密码");
    		$("#btn_login").removeAttr("disabled");
    		return false;
    	} 
    	
    	
    	obj[1].value = SHA1(obj[1].value);
    	//alert(obj[1].value);
    	obj.submit();
    	//Judge(obj);
    	//$("#btn_login").attr("class","login_c");
    	$("#btn_login").removeAttr("disabled");
    	//$('a').removeAttr('href');
    	return false;
    	*/
    	
    	
    		
    	$("#btn_login").removeAttr("disabled");
    }
    
    function goSubmit(){

    	$("input[id='btn_login']").attr("disabled","disabled");
    	//obj[1].value = SHA1(obj[1].value);
    	//alert(obj[1].value);
    	
    	var loginuser = $("#loginUser").val();
    	var passw = $("#inputPassword").val();
    	
    	if(loginuser.trim().empty() || passw.empty()){
    		$("#tip>span").text("请填写用户名和密码");
    		return false;
    	} 
    	
    	var url = contextRootPath + "/um/umtregistuser/login.do";
    	var data = "loginuser=" + loginuser + "&password=" + passw;
    	/* $.ajax({
    		type: "post",
    		url: url,
    		data: data,
    		success: function(result){
    			
    			$("input[id='btn_login']").attr("disabled","");
    			
    			var obj = eval("(" + result + ")");
                var msg = obj.msg;
         	   
         	   if(msg == "no user or no password") {
         		   $("#tip>span").text("用户名或密码错误");
         		   document.getElementById("tip").style.display = "";
         	   } else if(msg == "user error") {
         		   $("#tip>span").text("用户名错误");
         		   document.getElementById("tip").style.display = "";
         	   } else if(msg == "password error") {
         		   $("#tip>span").text("密码错误");
         		   document.getElementById("tip").style.display = "";
         	   } else if(msg == "success") {
         		   window.location.href= contextRootPath + "/pages/Success.jsp";
         	   } else {
         		   $("#tip>span").text("登陆失败，请重新登陆");
         		   document.getElementById("tip").style.display = "";
         	   }
    		},
    		error: function(msg) {
    			$("#tip>span").text("登陆失败，请重新登陆");
      		    document.getElementById("tip").style.display = "";
    		}
    	});    	 */
    }
    </script>
   
   
<style >

	#tip label.error {
		color: red;
		display: block;
	}

	#tip {
		height: 30px;
		margin: 0.5em auto 1.5em auto;
	}
	
	#tip img{
		line-height: 30px;
		height: 16px;
		width: 16px;
		display: inline;
	}
	
	#tip span {
		
		line-height: 15px;
		color: red;
		font-size: 0.75em;
		margin: 0 0 0 0.35em;
	}
	
	
</style>
   
  </head>

  <body>
         <div class="navbar navbar-default navbar-fixed-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
              <a class="navbar-brand" href="#"><img src="${ctx }/pages/image/picc.png" class="img-thumbnail" /> 客户俱乐部</a>
            </div>
          </div>
        </div> 

      
 <div class="container">
  <div class="row">

    <div class="main">

<!-- 
      <h3>快速登陆&nbsp;
      	<span style="font-size:0.1em;">没有账号请？马上<a href="#">注册</a></span></h3>
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <a href="#" class="btn btn-lg btn-primary btn-block">微信</a>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <a href="#" class="btn btn-lg btn-info btn-block">QQ</a>
        </div>
      </div>
     -->
     
     
      <h3 style="margin-top:3em;line-height:100%">登录&nbsp;
      	<span style="font-size:12px;">没有账号？马上<a href="${ctx }/pages/um/umtregistuser/registuser.jsp">注册</a></span></h3>
     
      <div class="login-or">
        <hr class="hr-or">
        <!--  <span class="span-or">or</span>  -->
      </div>

		<div id="tip" align="center" style="display:none;"></div>
      
     

      <form name="fm" action="<c:url value='/j_spring_security_check'/>"	method="post">
      
      
        <div class="form-group">
          <label for="inputUsernameEmail">用户名 / 手机号</label>
          <input type="text" class="form-control" id="j_username" name="j_username" required>
        </div>
        <div class="form-group">
          <a class="pull-right" href="#">忘记密码?</a>
          <label for="inputPassword">密码</label>
          <input type="password" class="form-control" id="j_password" name="j_password" required>
        </div>
        <div class="checkbox ">
          <label>
            <input type="checkbox">
            记住密码 </label>
        </div>
        
        
        <button type="submit" class="btn btn btn-primary" style="width:100%;"  id="btn_login">
          <span style="font-weight:bold;">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</span>
        </button>
      </form>
    
    </div>
    
  </div>
</div>



      <!-- FOOTER -->
    <div class="dropdown-header" align="center" style="margin-top:2em;">
      <footer >
        <p style="line-heigth:1em;white-space:normal;"><font style="color:gray">PICC(SZ)&nbsp;深圳人保财险版权所有©2014 </font>
        <a href="http://www.miibeian.gov.cn/" target="_blank" id="ba">&nbsp;&nbsp;粤ICP备12029198号</a>
        </p>
      </footer>
	</div>

<c:if test="${not empty login_error}">
			<%
				String errorMsg = "";
				AuthenticationException ex = (AuthenticationException) request.getSession().getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
				errorMsg = ex.getMessage();
				if(errorMsg!=null&&!"".equals(errorMsg)){
					request.setAttribute("errorMsg", errorMsg);
				}
			%>
		</c:if>
  </body>
  <script>
window.onload = function() {
	if ("${errorMsg}" != null && "${errorMsg}" != "") {
		alert("${errorMsg}");
	}
};
</script>
</html>