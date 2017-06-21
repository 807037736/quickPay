<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/user/UserQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $('#fm input').each(function () {
           if ($(this).attr('required') || $(this).attr('validType'))
               $(this).validatebox();
       });
	 
});
</script>
</head>

<body>
<div class="right_detail_top"><h3>用户同步</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="userMsgResInfo.USERCODE" id="userMsgResInfo.USERCODE" value="${userMsgResInfo.USERCODE}" required="true" >
		</td>
	
							</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<!-- <input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加"> -->
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTUserTable"></table>
	</div>
</body>
</html>