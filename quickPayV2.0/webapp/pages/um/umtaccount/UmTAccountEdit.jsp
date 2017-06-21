<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTAccount" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/umtaccount/UmTAccount.js"></script>
<script type="text/javascript">
function clearDefaultText (el,message)
{
var obj = el;
if(typeof(el) == "string")
obj = document.getElementById(el);
if(obj.value == message)
{
obj.value = "";
}
/* obj.onblur = function()
{
if(obj.value == "")
{
   obj.value = message;
}
} */
}
</script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		/* $('#fm input').each(function () {
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        }); */
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("fm");
			$("input").css('border','#FFF');
		}
	});
</script>
</head>

<body>
<div class="right_detail_top"><h3><c:choose>
			<c:when test="${opreateType == 'update'}">
					修改账户信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加账户信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看账户信息
				</c:when>
		</c:choose>		</h3></div>
	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/um/umtaccount" namespace="/um/umtaccount" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
			
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td class="long">
	  	<c:choose>
			<c:when test="${opreateType == 'update'}">
${umTAccount.id.userCode}					<input type="hidden" name="umTAccount.id.userCode" id="umTAccount.id.userCode" value="${umTAccount.id.userCode}" >
			</c:when>
			<c:when test="${opreateType == 'add'}">
					<input class='easyui-validatebox input_w w_15' name="umTAccount.id.userCode" id="umTAccount.id.userCode" value="${umTAccount.id.userCode}" required="true" validType="queryCondition">
			</c:when>
			<c:when test="${opreateType == 'view'}">
${umTAccount.id.userCode}					<input type="hidden" name="umTAccount.id.userCode" id="umTAccount.id.userCode" value="${umTAccount.id.userCode}">
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		密码
	</td> 
		<td class="long">
			<input class='easyui-validatebox input_w w_15' type='password' name="umTAccount.password" id="umTAccount.password" value="${umTAccount.password}" required="true" validType="minLen[5]" onclick="clearDefaultText(this,'${umTAccount.password}')">
		<input class='input_w w_15' type='hidden' name="password1" id="password1" value="${umTAccount.password}"  >
		<input class='input_w w_15' type='hidden' name="isChange" id="isChange" value="0"  >
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		指纹ID
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTAccount.fingerId" id="umTAccount.fingerId" value="${umTAccount.fingerId}">
		</td>
	
							
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
			    <ce:select name="umTAccount.validStatus" id="umTAccount.validStatus" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="umTAccount.validStatus" cssClass='input_w w_15'/>
			    
			</c:when>
			<c:when test="${opreateType == 'add'}">
			    <ce:select name="umTAccount.validStatus" id="umTAccount.validStatus" list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}" value="umTAccount.validStatus" cssClass='input_w w_15' />
			    
			</c:when>
			<c:when test="${opreateType == 'view'}">
			  <c:if test="${umTAccount.validStatus == '1'}">
			    有效   <input type="hidden" name="umTAccount.validStatus" id="umTAccount.validStatus" value="${umTAccount.validStatus}">
			 
			  </c:if>
			 <c:if test="${umTAccount.validStatus == '0'}">
			 无效   <input type="hidden" name="umTAccount.validStatus" id="umTAccount.validStatus" value="${umTAccount.validStatus}">
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
