<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserRole" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<%@ include file="/common/meta_css.jsp"%>
	<%@ include file="/common/i18njs.jsp"%>
	<%@ include file="/common/meta_js.jsp"%>


	<!-- Bootstrap core CSS -->
	<link href="${ctx}/widgets/bootstrap-3.2.0/css/bootstrap.css" rel="stylesheet">
	<link href="${ctx}/pages/um/umtregistuser/registuser.css" rel="stylesheet">
	<script src="${ctx}/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script src="${ctx}/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
	    
	
	<script src="${ctx}/widgets/form_validate.js"></script>
	<script src="${ctx}/widgets/validatecode.js"></script>
	<script language="javascript" src="${ctx}/pages/um/userBind/UserBindEdit.js"></script>

    
<script type="text/javascript">
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
	    
	    
		$('#continue1Btn').on("click",function(){
			  
		});
		
		$("#link_getValidateCode").on("click",function () { 
			
			preSendVCode();
		});
		
		$("#validate_code").on("blur",function () { 
		    if($('#validate_code').val() != "")
		    	preConfirmVCode();
		});
	});
	/*
	$(window).load(function(){
		//必须录入的校验
		$('input[name="umTUserBind.customerName"]').each(function(index,object){
		    $(this).validatebox({   
		    	required:true
		    });
		});
		$('input[name="umTUserBind.bindValue"]').each(function(index,object){
		    $(this).validatebox({   
		    	required:true,
		    	validType:['idcard[\'umTUserBind.bindValue\']']
		    });
		});
	});
	*/
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>

<style >
	#tip {
		height: 30px;
		margin: 0.5em auto 0 auto;
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
	#tip_vcode {
		line-height: 1.5em;
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
          <a class="navbar-brand" href="#"><img src="${ctx }/pages/image/picc.png" class="img-thumbnail" height="18" width="40"/> 人保财险</a>
        </div>
      </div>
</div>

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form name="fm" id="fm" action="/um/umtuserrole" namespace="/um/userbind" method="post">
			<h2>请填写您的身份信息</h2>
			<div class="form-group">
				<input type="text" name="umTUserBind.customerName" id="umTUserBind.customerName" class="form-control input-lg" placeholder="姓名" tabindex="4">
			</div>
			<div class="form-group">
				<input type="text" name="umTUserBind.bindValue" id="umTUserBind.bindValue" class="form-control input-lg" placeholder="身份证号码" tabindex="4">
			</div>
			
			<div id="tip" align="center" style="display:none;"><img src="${ctx}/pages/image/icon_error.gif"/><span>错误信息提示</span></div>
			
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-12">
					<input type="button" class="btn btn-primary btn-block btn-lg " value="绑&emsp;&emsp;定" tabindex="7" onclick="executeBind()">
				</div>
			</div>
		</form>		
		</div>			
	</div>
</div>
</body>
</html>
