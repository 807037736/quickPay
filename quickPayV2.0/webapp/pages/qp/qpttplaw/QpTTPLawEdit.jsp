<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPLaw" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttplaw/QpTTPLaw.js"></script>

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
	<form name="fm" id="fm" action="/qp/qpttplaw" namespace="/qp/qpttplaw" method="post">

		<table class="fix_table">
			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${operateType == 'update'}">
					修改信息
				</c:when>
			<c:when test="${operateType == 'add'}">
					增加信息
				</c:when>
			<c:when test="${operateType == 'view'}">
					查看信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr>
			
			
	<!-- 		    
	<tr>
	 
	<td class="bgc_tt short">
		法律法规ID
	</td>
	-->
	  	<td class="long">
	  	<c:choose>
			<c:when test="${operateType == 'update'}">
				<input type="hidden" name="qpTTPLaw.id.lawId" id="qpTTPLaw.id.lawId" value="${qpTTPLaw.id.lawId}">
			</c:when>
			<c:when test="${operateType == 'add'}">
				<input type="hidden" class='input_w w_15' name="qpTTPLaw.id.lawId" id="qpTTPLaw.id.lawId" value="${qpTTPLaw.id.lawId}">
			</c:when>
			<c:when test="${operateType == 'view'}">
				<input type="hidden" name="qpTTPLaw.id.lawId" id="qpTTPLaw.id.lawId" value="${qpTTPLaw.id.lawId}">
			</c:when>
		</c:choose>
		</td>
		
		<!--
	<td class="bgc_tt short">
		法律法规内容
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawName" id="qpTTPLaw.lawName" value="${qpTTPLaw.lawName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		关键字描述
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawWords" id="qpTTPLaw.lawWords" value="${qpTTPLaw.lawWords}">
		</td>
	<td class="bgc_tt short">
		条
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawItem" id="qpTTPLaw.lawItem" value="${qpTTPLaw.lawItem}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		款
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSegment" id="qpTTPLaw.lawSegment" value="${qpTTPLaw.lawSegment}">
		</td>
	<td class="bgc_tt short">
		项
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawSection" id="qpTTPLaw.lawSection" value="${qpTTPLaw.lawSection}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		序号
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawOrder" id="qpTTPLaw.lawOrder" value="${qpTTPLaw.lawOrder}">
		</td>
	<td class="bgc_tt short">
		创建人代码
	</td>
			<td class="long">
${qpTTPLaw.creatorCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
${qpTTPLaw.insertTimeForHis}			</td>
	<td class="bgc_tt short">
		修改人代码
	</td>
			<td class="long">
${qpTTPLaw.updaterCode}			</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		修改时间
	</td>
			<td class="long">
${qpTTPLaw.operateTimeForHis}			</td>
	<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.validStatus" id="qpTTPLaw.validStatus" value="${qpTTPLaw.validStatus}">
		</td>
 </tr> 	
 -->
<tr>
	<td class="bgc_tt short">
		法律法规内容
	</td>
		<td class="long">
			<input class='input_w w_15' name="qpTTPLaw.lawName" id="qpTTPLaw.lawName" value="${qpTTPLaw.lawName}">
		</td>
		<td colspan="5" class="bgc_tt short"></td>
	</tr>
		
		<tr>					
	<td class="bgc_tt short">
		关键字描述
	</td>
	<td class="long">
		<input class='input_w w_15' name="qpTTPLaw.lawWords" id="qpTTPLaw.lawWords" value="${qpTTPLaw.lawWords}">
	</td>		 
</tr>	
	
<tr>
	<td class="bgc_tt short">条</td>
	<td>
		<input class='input_w' style="width: 30px;" name="qpTTPLaw.lawItem" id="qpTTPLaw.lawItem" value="${qpTTPLaw.lawItem}">
		款<input class='input_w' style="width: 30px;" name="qpTTPLaw.lawSegment" id="qpTTPLaw.lawSegment" value="${qpTTPLaw.lawSegment}">
		项<input class='input_w' style="width: 30px;" name="qpTTPLaw.lawSection" id="qpTTPLaw.lawSection" value="${qpTTPLaw.lawSection}">
		序号<input class='input_w' style="width: 30px;" name="qpTTPLaw.lawOrder" id="qpTTPLaw.lawOrder" value="${qpTTPLaw.lawOrder}">
	</td>
	</tr>
		
	<tr>
		<td class="bgc_tt short">
			有效状态
		</td>
		<td class="long">
		<select class='input_w w_15' name="qpTTPLaw.validStatus" id="qpTTPLaw.validStatus" value="${qpTTPLaw.validStatus}">
			<option value="">-选择状态-</option>
			<option value="1" <c:if test="${qpTTPLaw.validStatus==1}">selected</c:if>>是</option>
			<option value="0" <c:if test="${qpTTPLaw.validStatus==0}">selected</c:if>>否</option>
		</select>
		</td>
		<td colspan="5" class="bgc_tt short"></td>
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
