<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTSaugroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtsaugroup/UmTSaugroup.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
			$("input").css('border','#FFF');
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3>
<c:choose>
			<c:when test="${opreateType == 'update'}">
					修改团队信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加团队信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看团队信息
				</c:when>
		</c:choose>		
</h3></div>	
	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/um/umtsaugroup" namespace="/um/umtsaugroup" method="post">
<div id="wrapper">
	<div id="container">
		<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		团队ID
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${opreateType == 'update'}">
${umTSaugroup.id.teamID}					<input type="hidden" name="umTSaugroup.id.teamID" id="umTSaugroup.id.teamID" value="${umTSaugroup.id.teamID}">
			</c:when>
			<c:when test="${opreateType == 'add'}">
					<input class='input_w w_25' name="umTSaugroup.id.teamID" id="umTSaugroup.id.teamID" value="${umTSaugroup.id.teamID}">
			</c:when>
			<c:when test="${opreateType == 'view'}">
${umTSaugroup.id.teamID}					<input type="hidden" name="umTSaugroup.id.teamID" id="umTSaugroup.id.teamID" value="${umTSaugroup.id.teamID}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		上级团队ID
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.upperTeamID" id="umTSaugroup.upperTeamID" value="${umTSaugroup.upperTeamID}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队编号
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.teamCode" id="umTSaugroup.teamCode" value="${umTSaugroup.teamCode}">
		</td>
	<td class="bgc_tt short">
		团队名称
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.teamName" id="umTSaugroup.teamName" value="${umTSaugroup.teamName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		标志
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.flag" id="umTSaugroup.flag" value="${umTSaugroup.flag}">
		</td>
	<td class="bgc_tt short">
		团队类型
	</td>
		<td class="long">
			<ce:codeselect  riskCode="" codeType="teamType" cssClass="easyui-combobox w_15" name="umTSaugroup.teamType" id="umTSaugroup.teamType" value="${umTSaugroup.teamType}"
																 withCode="true" editable="false" ></ce:codeselect>
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队外部类型
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.branchType" id="umTSaugroup.branchType" value="${umTSaugroup.branchType}">
		</td>
	<td class="bgc_tt short">
		团队经理工号
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.managerCode" id="umTSaugroup.managerCode" value="${umTSaugroup.managerCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队经理名称
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.managerName" id="umTSaugroup.managerName" value="${umTSaugroup.managerName}">
		</td>
	<td class="bgc_tt short">
		团队分管副经理工号
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.chargeMgrCode" id="umTSaugroup.chargeMgrCode" value="${umTSaugroup.chargeMgrCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队分管副经理姓名
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.chargeMgrName" id="umTSaugroup.chargeMgrName" value="${umTSaugroup.chargeMgrName}">
		</td>
	<td class="bgc_tt short">
		团队组训经理工号
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.trainMgrCode" id="umTSaugroup.trainMgrCode" value="${umTSaugroup.trainMgrCode}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队组训经理姓名
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.trainMgrName" id="umTSaugroup.trainMgrName" value="${umTSaugroup.trainMgrName}">
		</td>
	<td class="bgc_tt short">
		地址
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.address" id="umTSaugroup.address" value="${umTSaugroup.address}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.comCode" id="umTSaugroup.comCode" value="${umTSaugroup.comCode}">
		</td>
	<td class="bgc_tt short">
		所有上级团队路径
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.upperPath" id="umTSaugroup.upperPath" value="${umTSaugroup.upperPath}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队联系方式
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.branchPhone" id="umTSaugroup.branchPhone" value="${umTSaugroup.branchPhone}">
		</td>
	<td class="bgc_tt short">
		网点创建时间
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.createDate" id="umTSaugroup.createDate" value="${umTSaugroup.createDate}" onclick="WdatePicker()">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		网点失效日期
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.expireDate" id="umTSaugroup.expireDate" value="${umTSaugroup.expireDate}" onclick="WdatePicker()">
		</td>
	<td class="bgc_tt short">
		审核状态
	</td>
		<td class="long">
			<input class='input_w w_25' name="umTSaugroup.checkDate" id="umTSaugroup.checkDate" value="${umTSaugroup.checkDate}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<c:choose>
			<c:when test="${opreateType == 'update'}">
			    <ce:select name="umTSaugroup.validStatus" id="umTSaugroup.validStatus" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="umTSaugroup.validStatus" cssClass='input_w w_25'/>
			    
			</c:when>
			
			<c:when test="${opreateType == 'view'}">
			  <c:if test="${umTSaugroup.validStatus == '1'}">
			    有效   <input type="hidden" name="umTSaugroup.validStatus" id="umTSaugroup.validStatus" value="${umTSaugroup.validStatus}">
			 
			  </c:if>
			 <c:if test="${umTUser.validStatus == '0'}">
			 无效   <input type="hidden" name="umTSaugroup.validStatus" id="umTSaugroup.validStatus" value="${umTSaugroup.validStatus}">
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
