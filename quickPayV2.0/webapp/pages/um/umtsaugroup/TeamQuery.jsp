<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtsaugroup/TeamQuery.js"></script>
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
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">请输入查询条件</h2>
				</div>
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		所属机构
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTSaugroup.comCode" id="umTSaugroup.comCode" value="${umTSaugroup.comCode}" required="true" validType="length[8,8]">
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
	<br>
	<table id="UmTUserTable"></table>
</body>
</html>