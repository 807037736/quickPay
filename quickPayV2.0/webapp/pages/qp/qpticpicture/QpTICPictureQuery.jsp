<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPicture" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticpicture/QpTICPicture.js"></script>
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
		图片ID
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="qpTICPicture.id.picId" id="qpTICPicture.id.picId" value="${qpTICPicture.id.picId}">
		</td>
	<td class="bgc_tt short">
		图片描述
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.picDesc" id="qpTICPicture.picDesc" value="${qpTICPicture.picDesc}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.picOrder" id="qpTICPicture.picOrder" value="${qpTICPicture.picOrder}">
		</td>
	<td class="bgc_tt short">
		图片来源
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.picSource" id="qpTICPicture.picSource" value="${qpTICPicture.picSource}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		文件路径
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.fileName" id="qpTICPicture.fileName" value="${qpTICPicture.fileName}">
		</td>
	<td class="bgc_tt short">
		服务ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.serverId" id="qpTICPicture.serverId" value="${qpTICPicture.serverId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		上传时间
	</td>
		<td  class="long">
			<input class='input_w w_15' name="qpTICPicture.uploadTime" id="qpTICPicture.uploadTime" value="${qpTICPicture.uploadTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
	<td class="bgc_tt short">
		所属事故ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.acciId" id="qpTICPicture.acciId" value="${qpTICPicture.acciId}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		确认状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.checkStatus" id="qpTICPicture.checkStatus" value="${qpTICPicture.checkStatus}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPicture.validStatus" id="qpTICPicture.validStatus" value="${qpTICPicture.validStatus}">
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
	<table id="QpTICPictureTable"></table>
</body>
</html>
