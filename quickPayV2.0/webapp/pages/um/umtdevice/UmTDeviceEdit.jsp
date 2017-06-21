<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTDevice" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtdevice/UmTDevice.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3><c:choose>
			<c:when test="${opreateType == 'update'}">
					修改用户设备
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加用户设备
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看用户设备
				</c:when>
		</c:choose>			</h3></div>
	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/um/umtdevice" namespace="/um/umtdevice" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
			
							<tr>
	<td class="bgc_tt short">
		用户代码
		
							<input type="hidden" name="umTDevice.id.deviceId" id="umTDevice.id.deviceId" value="${umTDevice.id.deviceId}" />
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${opreateType == 'update'}">
${umTDevice.userCode}	<input type='hidden' class='input_w w_15' name="umTDevice.userCode" id="umTDevice.userCode" value="${umTDevice.userCode}" >
			</c:when>
			<c:when test="${opreateType == 'add'}">
			<input class='easyui-validatebox input_w w_15' name="umTDevice.userCode" id="umTDevice.userCode" value="${umTDevice.userCode}" required="true" validType="queryCondition">
			</c:when>
			<c:when test="${opreateType == 'view'}">
			${umTDevice.userCode}	<input type='hidden' class='input_w w_15' name="umTDevice.userCode" id="umTDevice.userCode" value="${umTDevice.userCode}" >
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		设备类型
	</td>
		<td class="long">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
			   <ce:select name="umTDevice.deviceType" id="umTDevice.deviceType" list="#@java.util.LinkedHashMap@{'01':'电脑设备','02':'移动设备'}" value="umTDevice.deviceType" cssClass='input_w w_15'/>
		
			</c:when>
			<c:when test="${opreateType == 'add'}">
			   <ce:select name="umTDevice.deviceType" id="umTDevice.deviceType" list="#@java.util.LinkedHashMap@{'':'请选择','01':'电脑设备','02':'移动设备'}" value="umTDevice.deviceType" cssClass='input_w w_15'/>
		</c:when>
			<c:when test="${opreateType == 'view'}">
			  <c:if test="${umTDevice.deviceType == '01'}">
			    电脑设备   <input type="hidden" name="umTDevice.deviceType" id="umTDevice.deviceType" value="${umTDevice.deviceType}">
			 
			  </c:if>
			 <c:if test="${umTDevice.deviceType == '02'}">
			 移动设备   <input type="hidden" name="umTDevice.deviceType" id="umTDevice.deviceType" value="${umTDevice.deviceType}">
			 </c:if>
		     
			</c:when>
		</c:choose>
			
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		设备名称
	</td>
		<td class="long">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
			<input class='easyui-validatebox input_w w_15' name="umTDevice.deviceName" id="umTDevice.deviceName" value="${umTDevice.deviceName}" required="true" validType="maxLength[50]">
			</c:when>
			<c:when test="${opreateType == 'add'}">
			<input class='easyui-validatebox input_w w_15' name="umTDevice.deviceName" id="umTDevice.deviceName" value="${umTDevice.deviceName}" required="true" validType="maxLength[50]">
			</c:when>
			<c:when test="${opreateType == 'view'}">
			${umTDevice.deviceName}	<input type='hidden' class='input_w w_15' name="umTDevice.deviceName" id="umTDevice.deviceName" value="${umTDevice.deviceName}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<c:choose>
			<c:when test="${opreateType == 'update'}">
			    <ce:select name="umTDevice.validStatus" id="umTDevice.validStatus" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="umTDevice.validStatus" cssClass='input_w w_15'/>
			    
			</c:when>
			<c:when test="${opreateType == 'add'}">
			   <ce:select name="umTDevice.validStatus" id="umTDevice.validStatus" list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}" value="umTDevice.validStatus" cssClass='input_w w_15'/>
			</c:when>
			<c:when test="${opreateType == 'view'}">
			  <c:if test="${umTDevice.validStatus == '1'}">
			    有效   <input type="hidden" name="umTDevice.validStatus" id="umTDevice.validStatus" value="${umTDevice.validStatus}">
			 
			  </c:if>
			 <c:if test="${umTDevice.validStatus == '0'}">
			 无效   <input type="hidden" name="umTDevice.validStatus" id="umTDevice.validStatus" value="${umTDevice.validStatus}">
			 </c:if>
		     
			</c:when>
		</c:choose>
		</td>
	                  </tr>
							
							
							
		</table>
		<br>
		<table class="fix_table">
			<tr>
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
		</div>
		</div>
	</form>
</body>
</html>
