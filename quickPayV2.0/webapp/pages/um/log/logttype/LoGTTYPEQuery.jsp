<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.log.schema.model.LoGTTYPE" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/log/logttype/LoGTTYPE.js"></script>
<script type="text/javascript">

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
		操作类型ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="loGTTYPE.id.operateTypeId" id="loGTTYPE.id.operateTypeId" value="${loGTTYPE.id.operateTypeId}">
		</td>
	<td class="bgc_tt short">
		操作类型英文名
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateTypeEName" id="loGTTYPE.operateTypeEName" value="${loGTTYPE.operateTypeEName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		操作类型中文名
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateTypeCName" id="loGTTYPE.operateTypeCName" value="${loGTTYPE.operateTypeCName}">
		</td>
	<td class="bgc_tt short">
		操作方法
	</td>
		<td class="long">
			<input class='input_w w_15' name="loGTTYPE.operateMethod" id="loGTTYPE.operateMethod" value="${loGTTYPE.operateMethod}">
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
	<br>
	<table id="LoGTTYPETable"></table>
</body>
</html>
