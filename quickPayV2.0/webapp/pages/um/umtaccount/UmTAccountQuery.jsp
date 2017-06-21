<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTAccount" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtaccount/UmTAccount.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
var winUrl       = contextRootPath + "/common/PrepareCodeInput.jsp";
	var handle       = window.showModalDialog(winUrl,window,
	"dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:350px;dialogHeight:450px;resizable:yes");
	if(handle!=null && handle!='undefine'){
 alert('test1 = ' + handle['test1']);
	}
	}); */
</script>
</head>
<body>
<div class="right_detail_top"><h3>账户管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTAccount.id.userCode" id="umTAccount.id.userCode" value="${umTAccount.id.userCode}" maxlength="10">
		</td>
	<td class="bgc_tt short">
		指纹ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTAccount.fingerId" id="umTAccount.fingerId" value="${umTAccount.fingerId}" maxlength="30">
		</td>
							</tr>
							<tr>
	
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
		<ce:select name="umTAccount.validStatus" id="umTAccount.validStatus" list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}" value="umTAccount.validStatus" cssClass='input_w w_15' />
		</td>
							 </tr> 			 
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							 <input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加"> 
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="margin-control">
	<table id="UmTAccountTable"></table>
	</div>
</body>
</html>
