<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserHistory" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/user/umtuserhistory/UmTUserHistory.js"></script>
<script type="text/javascript">

</script>

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
	<form name="fm" id="fm" action="/user/umtuserhistory" namespace="/user/umtuserhistory" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改UmTUserHistory信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加UmTUserHistory信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看UmTUserHistory信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		usercode
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.usercode" id="umTUserHistory.usercode" value="${umTUserHistory.usercode}">
		</td>
	<td class="bgc_tt short">
		username
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.username" id="umTUserHistory.username" value="${umTUserHistory.username}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		comcode
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.comcode" id="umTUserHistory.comcode" value="${umTUserHistory.comcode}">
		</td>
	<td class="bgc_tt short">
		telephone
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.telephone" id="umTUserHistory.telephone" value="${umTUserHistory.telephone}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		faxnumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.faxnumber" id="umTUserHistory.faxnumber" value="${umTUserHistory.faxnumber}">
		</td>
	<td class="bgc_tt short">
		mobile
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.mobile" id="umTUserHistory.mobile" value="${umTUserHistory.mobile}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		email
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.email" id="umTUserHistory.email" value="${umTUserHistory.email}">
		</td>
	<td class="bgc_tt short">
		postaddress
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.postaddress" id="umTUserHistory.postaddress" value="${umTUserHistory.postaddress}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		unit
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.unit" id="umTUserHistory.unit" value="${umTUserHistory.unit}">
		</td>
	<td class="bgc_tt short">
		unitaddress
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.unitaddress" id="umTUserHistory.unitaddress" value="${umTUserHistory.unitaddress}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		image
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.image" id="umTUserHistory.image" value="${umTUserHistory.image}">
		</td>
	<td class="bgc_tt short">
		interests
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.interests" id="umTUserHistory.interests" value="${umTUserHistory.interests}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		creatorcode
	</td>
			<td class="long">
${umTUserHistory.creatorcode}			</td>
	<td class="bgc_tt short">
		inserttimeforhis
	</td>
			<td class="long">
${umTUserHistory.inserttimeforhis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		updatercode
	</td>
			<td class="long">
${umTUserHistory.updatercode}			</td>
	<td class="bgc_tt short">
		operatetimeforhis
	</td>
			<td class="long">
${umTUserHistory.operatetimeforhis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		remark
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.remark" id="umTUserHistory.remark" value="${umTUserHistory.remark}">
		</td>
	<td class="bgc_tt short">
		flag
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.flag" id="umTUserHistory.flag" value="${umTUserHistory.flag}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		usertype
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.usertype" id="umTUserHistory.usertype" value="${umTUserHistory.usertype}">
		</td>
	<td class="bgc_tt short">
		usersort
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.usersort" id="umTUserHistory.usersort" value="${umTUserHistory.usersort}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		identitynumber
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.identitynumber" id="umTUserHistory.identitynumber" value="${umTUserHistory.identitynumber}">
		</td>
	<td class="bgc_tt short">
		validstatus
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.validstatus" id="umTUserHistory.validstatus" value="${umTUserHistory.validstatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		sex
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.sex" id="umTUserHistory.sex" value="${umTUserHistory.sex}">
		</td>
	<td class="bgc_tt short">
		birthday
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.birthday" id="umTUserHistory.birthday" value="${umTUserHistory.birthday}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		age
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.age" id="umTUserHistory.age" value="${umTUserHistory.age}">
		</td>
	<td class="bgc_tt short">
		comid
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.comid" id="umTUserHistory.comid" value="${umTUserHistory.comid}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		bindstatus
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.bindstatus" id="umTUserHistory.bindstatus" value="${umTUserHistory.bindstatus}">
		</td>
	<td class="bgc_tt short">
		sourceflag
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.sourceflag" id="umTUserHistory.sourceflag" value="${umTUserHistory.sourceflag}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		custid
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserHistory.custid" id="umTUserHistory.custid" value="${umTUserHistory.custid}">
		</td>
								<td class="bgc_tt short"></td>
							    <td class="long"></td>
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
