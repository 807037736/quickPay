<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUserBind" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtbind/umtuserbind/UmTUserBind.js"></script>

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
	<form name="fm" id="fm" action="/um/umtuserbind" namespace="/um/umtuserbind" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改UmTUserBind信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加UmTUserBind信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看UmTUserBind信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
${umTUserBind.id.userCode}					<input type="hidden" name="umTUserBind.id.userCode" id="umTUserBind.id.userCode" value="${umTUserBind.id.userCode}">
			</c:when>
			<c:when test="${operateType == 'add'}">
					<input class='input_w w_15' name="umTUserBind.id.userCode" id="umTUserBind.id.userCode" value="${umTUserBind.id.userCode}">
			</c:when>
			<c:when test="${operateType == 'view'}">
${umTUserBind.id.userCode}					<input type="hidden" name="umTUserBind.id.userCode" id="umTUserBind.id.userCode" value="${umTUserBind.id.userCode}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		客户名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.customerName" id="umTUserBind.customerName" value="${umTUserBind.customerName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定方式
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindType" id="umTUserBind.bindType" value="${umTUserBind.bindType}">
		</td>
	<td class="bgc_tt short">
		绑定值
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindValue" id="umTUserBind.bindValue" value="${umTUserBind.bindValue}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定时间
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindTime" id="umTUserBind.bindTime" value="${umTUserBind.bindTime}" onclick="WdatePicker({skin:'whyGreen'})">
		</td>
	<td class="bgc_tt short">
		绑定结果
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.bindResult" id="umTUserBind.bindResult" value="${umTUserBind.bindResult}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		绑定客户编号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.custId" id="umTUserBind.custId" value="${umTUserBind.custId}">
		</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUserBind.validStatus" id="umTUserBind.validStatus" value="${umTUserBind.validStatus}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${umTUserBind.creatorCode}			</td>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${umTUserBind.insertTimeForHis}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		更新人代码
	</td>
			<td class="long">
${umTUserBind.updaterCode}			</td>
	<td class="bgc_tt short">
		更新时间
	</td>
			<td class="long">
${umTUserBind.operateTimeForHis}			</td>
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
