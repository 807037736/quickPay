<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRegistuser" %>


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
    <script src="${ctx }/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
    <script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>

   <%--  <link href="${ctx }/pages/um/umtregistuser/registuser.css" rel="stylesheet"> --%>
    
  
    <!--
   <script type="text/javascript" src="${ctx }/widgets/jquery-validation-1.13.0/dist/jquery.validate.min.js" ></script>
   <script src="${ctx }/widgets/jquery-validation-1.13.0/dist/localization/messages_zh.min.js" type="text/javascript"></script>
   
   <link href="${ctx }/widgets/form_validate_jquery.css" rel="stylesheet"> 
   <script src="${ctx }/widgets/form_validate_jquery.js"></script>

     Custom styles for this template 
    <script src="${ctx }/widgets/form_validate.js"></script>
    <script src="${ctx }/widgets/validatecode.js"></script>
    <script src="${ctx }/pages/um/umtregistuser/registuserExt.js"></script>
    -->
    
<script>
    	
    
    $(function () {
    	
    	var userAgent = navigator.userAgent.toLowerCase(); 
    	var registSource = $("#registSource");
        if(userAgent.match(/ipad/i) == 'ipad')
        	registSource.val("ipad");
        else if(userAgent.match(/iphone os/i) == 'iphone os')
        	registSource.val("iphone");
        else if(userAgent.match(/windows ce/i) == 'windows ce')
        	registSource.val("windows ce");
        else if(userAgent.match(/windows mobile/i) == 'windows mobile')
        	registSource.val("windows mobile");
        else 
        	registSource.val("unknown");
        
    });
</script>

<style >
body {
	padding-top: 50px;
	font-size: 12px
}

.main {
	max-width: 320px;
	margin: 0 auto;
}

img.img-thumbnail {
	max-height: 26px;
	width: auto;
	height: auto;
}

.colorgraph {
	height: 5px;
	border-top: 0;
	background: #c4e17f;
	border-radius: 5px;
	background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
}

#tip {
	height: 30px;
	margin: 0.5em auto 0 auto;
}

#tip img {
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

#tip_vcode {
	color: red;
	padding-top: 3px;
	margin: 0 0 0 0.35em;
}
</style>
   
  <!-- End SlidesJS Optional-->
  </head>

  <body>

      <div class="navbar navbar-default navbar-fixed-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
             
              <a class="navbar-brand" href="#"><img src="${ctx }/pages/image/picc.png" class="img-thumbnail" /> 人保财险</a>
            </div>
            
          </div>
      </div>

<div class="container">

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" id="fm" method="post" >
		<input type="hidden" id="param1" name="param1" value="${param1}" />
		<input type="hidden" id="carid" name="carid" value="<%=request.getParameter("param1")%>" />
		<input type="hidden" id="usercode" name="usercode" value="${umTUserBindVo.userCode}" />
		<input type="hidden" id="mobileno" name="mobileno" value="${umTUserBindVo.mobileNo}" />
		 
			<h2>完善个人信息</h2>
			<hr class="colorgraph">
			
			<div id="tip" align="center" style="display:none;"><img src="${ctx}/pages/image/icon_error.gif"/><span>错误信息提示</span></div>
     
			
			<div class="form-group">
				<input type="text" name="username" id="username"  value="${umTUserBindVo.userName}" class="form-control input-lg  v_name" placeholder="被保险人姓名" tabindex="4">
			</div>
			
			<div class="form-group">
				<input type="text" name="licenseno" id="licenseno" value="${umTUserBindVo.licenseNo}"  class="form-control input-lg  v_name" placeholder="车牌" tabindex="4">
			</div>
			
			<div class="form-group">
				<input type="text" name="identifyno" id="identifyno" value="${umTUserBindVo.bindValue}"  class="form-control input-lg v_phoneNo" placeholder="被保险人身份证" tabindex="4">
			</div>
			
			<div style="display:none;" id="tip_vcode" >
				<span ></span>
			</div>
			
			<hr class="colorgraph">
			
			
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-12">
					<input type="button" onclick="goPerson()" class="btn btn-primary btn-block btn-lg " value="返&emsp;&emsp;回" tabindex="7" >
					<input type="button" onclick="bangding()" class="btn btn-primary btn-block btn-lg " value="保&emsp;&emsp;存" tabindex="7" >
				</div>
			</div>
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
	
	
<input type="hidden" id="activityname" value="用户注册" />
<input type="hidden" id="smsId" />
<input type="hidden" id="confirm_vCode" value="" />
	
  </body>
  <script type="text/javascript">
  
  function bangding(){
  	if(check()){
  		document.getElementById('tip').style.display = 'none';
  		fm.action = contextRootPath+"/um/umtregistuser/edit.do";
  		fm.submit();
  	}
  	
  }
   
  	function check() {
  		
  		var username = $("#username");
  		//var identifyno = $("#identifyno");
  		//var licenseno = $("#licenseno");
  		
  		var tipDiv = $("#tip");
  		var tip = $("#tip>span");
  		
  		if(username.val().trim()=='') {
  			tip.text('姓名不能为空！请填写被保险人姓名。');
  			username.focus();
  			tipDiv.show();
  			return false;
  		}
  		/* 	
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
  		*/
  		 
      	tip.text('');
      	tipDiv.hide();
  		return true;	
  	}
   
  	
   

  	/*
  	 * 返回
  	 */
  	function goPerson(){
  		var param = document.getElementById("param1").value;
  		fm.action = contextRootPath+"/um/umtregistuser/personCenter.do?param="+param;
  		fm.submit();	 
  	}

  	 
  </script>
</html>