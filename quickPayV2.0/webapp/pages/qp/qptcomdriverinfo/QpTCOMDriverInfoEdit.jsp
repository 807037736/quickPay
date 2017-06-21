<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMDriverInfo" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qptcomdriverinfo/QpTCOMDriverInfo.js"></script>

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
	<form name="fm" id="fm" action="/qp/qptcomdriverinfo" namespace="/qp/qptcomdriverinfo" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改QpTCOMDriverInfo信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加QpTCOMDriverInfo信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看QpTCOMDriverInfo信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		驾驶员ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${qpTCOMDriverInfo.id.driverInfoId}					<input type="hidden" name="qpTCOMDriverInfo.id.driverInfoId" id="qpTCOMDriverInfo.id.driverInfoId" value="${qpTCOMDriverInfo.id.driverInfoId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="qpTCOMDriverInfo.id.driverInfoId" id="qpTCOMDriverInfo.id.driverInfoId" value="${qpTCOMDriverInfo.id.driverInfoId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${qpTCOMDriverInfo.id.driverInfoId}					<input type="hidden" name="qpTCOMDriverInfo.id.driverInfoId" id="qpTCOMDriverInfo.id.driverInfoId" value="${qpTCOMDriverInfo.id.driverInfoId}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		驾驶员姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverName" id="qpTCOMDriverInfo.driverName" value="${qpTCOMDriverInfo.driverName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverIdNumber" id="qpTCOMDriverInfo.driverIdNumber" value="${qpTCOMDriverInfo.driverIdNumber}">
		</td>
	<td class="bgc_tt short">
		车牌号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverVehicleNumber" id="qpTCOMDriverInfo.driverVehicleNumber" value="${qpTCOMDriverInfo.driverVehicleNumber}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		承保保险公司ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.coId" id="qpTCOMDriverInfo.coId" value="${qpTCOMDriverInfo.coId}">
		</td>
	<td class="bgc_tt short">
		录音时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverInfoRecordTime" id="qpTCOMDriverInfo.driverInfoRecordTime" value="${qpTCOMDriverInfo.driverInfoRecordTime}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		身份证号重复次数
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.driverIdNumberRepeatTime" id="qpTCOMDriverInfo.driverIdNumberRepeatTime" value="${qpTCOMDriverInfo.driverIdNumberRepeatTime}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTCOMDriverInfo.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTCOMDriverInfo.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTCOMDriverInfo.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTCOMDriverInfo.operateTimeForHis}			</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTCOMDriverInfo.validStatus" id="qpTCOMDriverInfo.validStatus" value="${qpTCOMDriverInfo.validStatus}">
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
