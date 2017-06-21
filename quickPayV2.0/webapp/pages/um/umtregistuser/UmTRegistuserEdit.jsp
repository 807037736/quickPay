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
    
  
    <link rel="shortcut icon" href="${ctx }/pages/image/picc_favicon.png">
   

    <title>深圳人保财险</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx }/pages/um/umtregistuser/registuser.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <script src="${ctx }/widgets/form_validate.js"></script>
    <script src="${ctx }/widgets/validatecode.js"></script>
    <script src="${ctx }/pages/um/umtregistuser/UmTRegistuser.js"></script>
    
<script>
    	
    
    $(function () {
    	$('#continue1Btn').on("click",function(){
    		  
    	});
    	
    	$("#link_getValidateCode").on("click",function () { 
    		prepareSendVCode();
		});
    	
    	$("#validate_code").on("blur",function () { 
		    if($('#validate_code').val() != "")
		    	preConfirmVCode();
		});
    });
</script>
   
  <!-- End SlidesJS Optional-->
  </head>

  <body>

      <div class="navbar navbar-default navbar-fixed-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
             
              <a class="navbar-brand" href="#"><img src="${ctx }/pages/image/picc.png" class="img-thumbnail" height="18" width="40"/> 人保财险</a>
            </div>
            
          </div>
 </div>



  
	<div class="container">

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" action="${ctx }/um/umtregistuser/add.do" method="post" >
			<h2>请填写注册信息</h2>
			<hr class="colorgraph">
			<div class="form-group">
				<input type="text" name="umTRegistuser.userName" id="name" class="form-control input-lg" placeholder="姓名" tabindex="4">
			</div>
			<div class="form-group">
				<input type="text" name="umTRegistuser.mobile" id="phoneNo" class="form-control input-lg" placeholder="手机号" tabindex="4">
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="text" name="validate_code" id="validate_code" class="form-control input-lg" placeholder="验证码" tabindex="5">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<span class="btn btn-success" id="link_getValidateCode" onclick="" style="font-weight:bold;line-height:2;width:50%;">获取验证码</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="umTRegistuser.password" id="password" class="form-control input-lg" placeholder="密码" tabindex="5">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="rePassword" id="rePassword" class="form-control input-lg" placeholder="密码确认" tabindex="6">
					</div>
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-8 col-sm-12 col-md-12">
					 同意 <a href="#" data-toggle="modal" data-target="#t_and_c_m">《深圳人保财险网站用户使用条款》</a> 
				</div>
			</div>
			
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-12">
					<input type="button" class="btn btn-primary btn-block btn-lg " value="注&emsp;&emsp;册" tabindex="7" onclick="goSubmit()">
				</div>
			</div>
		</form>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="t_and_c_m" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
			</div>
			<div class="modal-body">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">I Agree</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>


       <!-- FOOTER -->
    <div class="dropdown-header" align="center" style="margin-top:2em;">
      <footer >
        <p style="line-heigth:1em;white-space:normal;"><font style="color:gray">PICC(SZ)&nbsp;深圳人保财险版权所有©2014 </font>
        <a href="http://www.miibeian.gov.cn/" target="_blank" id="ba">&nbsp;&nbsp;粤ICP备12029198号</a>
        </p>
      </footer>
	</div>
	
	
		<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${ctx }/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
    <script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
    <!-- SlidesJS Required: Initialize SlidesJS with a jQuery doc ready -->
  <script>
 
  </script>
  <!-- End SlidesJS Required -->
  </body>
</html>