<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTSauuser" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtsauuser/UmTSauuser.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
			/* $("input").css('border','#FFF'); */
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3>
<c:choose>
			<c:when test="${opreateType == 'update'}">
					修改团队成员信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加团队成员信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看团队成员信息
				</c:when>
		</c:choose>	
</h3></div>
	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/um/umtsauuser" namespace="/um/umtsauuser" method="post">
<div id="wrapper">
	<div id="container">
		<table class="fix_table">
		
			<tr>
							<tr>
	<td class="bgc_tt short">
		人员代码
		<input type="hidden" name="umTSauuser.id.saleUserId" id="umTSauuser.id.saleUserId" value="${umTSauuser.id.saleUserId}">
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${opreateType == 'update'}">
${umTSauuser.userCode}				<input type="hidden" name="umTSauuser.userCode" id="umTSauuser.userCode" value="${umTSauuser.userCode}">
			</c:when>
			<c:when test="${opreateType == 'add'}">
					<input class='input_w w_15' name="umTSauuser.userCode" id="umTSauuser.userCode" value="${umTSauuser.userCode}">
			</c:when>
			<c:when test="${opreateType == 'view'}">
${umTSauuser.userCode}			<input type="hidden" name="umTSauuser.userCode" id="umTSauuser.userCode" value="${umTSauuser.userCode}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		团队ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.teamID" id="umTSauuser.teamID" value="${umTSauuser.teamID}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		用户姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.userName" id="umTSauuser.userName" value="${umTSauuser.userName}">
		</td>
	<td class="bgc_tt short">
		人员属性
	</td>
		<td class="long">
			<ce:codeselect  riskCode="" codeType="sauUserType" cssClass="easyui-combobox w_20" name="umTSauuser.userType" id="umTSauuser.userType" value="${umTSauuser.userType}"
																 withCode="true" editable="false" ></ce:codeselect>
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSauuser.identifyNumber" id="umTSauuser.identifyNumber" value="${umTSauuser.identifyNumber}">
		</td>
	<td class="bgc_tt short">
		性别
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.sex" id="umTSauuser.sex" value="${umTSauuser.sex}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		手机号码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.mobile" id="umTSauuser.mobile" value="${umTSauuser.mobile}">
		</td>
	<td class="bgc_tt short">
		团队编码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.teamCode" id="umTSauuser.teamCode" value="${umTSauuser.teamCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.comCode" id="umTSauuser.comCode" value="${umTSauuser.comCode}">
		</td>
	<td class="bgc_tt short">
		流失原因
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.dismissReason" id="umTSauuser.dismissReason" value="${umTSauuser.dismissReason}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		审核状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.checkStatus" id="umTSauuser.checkStatus" value="${umTSauuser.checkStatus}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
			    <ce:select name="umTSauuser.validStatus" id="umTSauuser.validStatus" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="umTSauuser.validStatus" cssClass='input_w w_15'/>
			    
			</c:when>
			
			<c:when test="${opreateType == 'view'}">
			  <c:if test="${umTSauuser.validStatus == '1'}">
			    有效   <input type="hidden" name="umTSauuser.validStatus" id="umTSauuser.validStatus" value="${umTSauuser.validStatus}">
			 
			  </c:if>
			 <c:if test="${umTSauuser.validStatus == '0'}">
			 无效   <input type="hidden" name="umTSauuser.validStatus" id="umTSauuser.validStatus" value="${umTSauuser.validStatus}">
			 </c:if>
		     
			</c:when>
		</c:choose>
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		标志
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.flag" id="umTSauuser.flag" value="${umTSauuser.flag}">
		</td>
	<td class="bgc_tt short">
		备注
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.remark" id="umTSauuser.remark" value="${umTSauuser.remark}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${umTSauuser.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${umTSauuser.operateTimeForHis}			</td>
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
