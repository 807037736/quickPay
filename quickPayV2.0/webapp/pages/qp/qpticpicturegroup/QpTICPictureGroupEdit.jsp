<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpticpicturegroup/QpTICPictureGroup.js"></script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="fm" id="fm" action="/qp/qpticpicturegroup" namespace="/qp/qpticpicturegroup" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTICPictureGroup信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTICPictureGroup信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTICPictureGroup信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		组号ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTICPictureGroup.id.groupId}					<input type="hidden" name="qpTICPictureGroup.id.groupId" id="qpTICPictureGroup.id.groupId" value="${qpTICPictureGroup.id.groupId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTICPictureGroup.id.groupId" id="qpTICPictureGroup.id.groupId" value="${qpTICPictureGroup.id.groupId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTICPictureGroup.id.groupId}					<input type="hidden" name="qpTICPictureGroup.id.groupId" id="qpTICPictureGroup.id.groupId" value="${qpTICPictureGroup.id.groupId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		上传时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.uploadTimeForHis" id="qpTICPictureGroup.uploadTimeForHis" value="${qpTICPictureGroup.uploadTimeForHis}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		上传用户代码
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.uploadUserCode" id="qpTICPictureGroup.uploadUserCode" value="${qpTICPictureGroup.uploadUserCode}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTICPictureGroup.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTICPictureGroup.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTICPictureGroup.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTICPictureGroup.operateTimeForHis}			</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTICPictureGroup.validStatus" id="qpTICPictureGroup.validStatus" value="${qpTICPictureGroup.validStatus}">
		</td>
							 </tr> 				 
		</table>
		<br>
		<table>
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
