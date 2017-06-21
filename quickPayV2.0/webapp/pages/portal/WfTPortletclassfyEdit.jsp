<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.portal.schema.model.WfTPortletclassfy" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/portal/WfTPortletclassfy.js"></script>
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
<div class="right_detail_top">
	<h3>
		<c:choose>
			<c:when test="${opreateType == 'update'}">
				修改Portal模块
			</c:when>
			<c:when test="${opreateType == 'add'}">
				新增Portal模块
			</c:when>
			<c:when test="${opreateType == 'view'}">
				查看Portal模块
			</c:when>
		</c:choose>
	</h3>
</div>
<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<form name="fm" id="fm" action="/portal" namespace="/portal" method="post">
		<div id="wrapper">
			<div id="container">
		<table class="fix_table">
<%-- 			<tr>
				<td class="bgc_tt short" colspan="4" align="left">
					<h2 align="center">
		<c:choose>
			<c:when test="${opreateType == 'update'}">
					修改WfTPortletclassfy信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加WfTPortletclassfy信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看WfTPortletclassfy信息
				</c:when>
		</c:choose>						
					</h2></td>
			</tr>
			<tr> --%>

	<tr>						
	<c:choose>
	<c:when test="${opreateType == 'update'}">
	<td class="bgc_tt short">
		模块代码
	</td>
	  	<td class="long">
${wfTPortletclassfy.id.portletid}					<input type="hidden" name="wfTPortletclassfy.id.portletid" id="wfTPortletclassfy.id.portletid" value="${wfTPortletclassfy.id.portletid}">
    </c:when>
	<c:when test="${opreateType == 'view'}">
	<td class="bgc_tt short">
		模块代码
	</td>
	  	<td class="long">
			<input class='input_w w_30' name="wfTPortletclassfy.id.portletid" id="wfTPortletclassfy.id.portletid" value="${wfTPortletclassfy.id.portletid}">
		</td>    
    </c:when> 
    </c:choose>   	  	
		
	<td class="bgc_tt short">
		模块名称
	</td>
		<td class="long">
			<input class='input_w w_30 easyui-validatebox' required="true"
				name="wfTPortletclassfy.portletname" id="wfTPortletclassfy.portletname" value="${wfTPortletclassfy.portletname}">
		</td>
			</tr>
			<tr>
	<td class="bgc_tt short">
		模块地址
	</td>
		<td class="long">		
				<input class='input_w w_30 easyui-validatebox' required="true"
					 name="wfTPortletclassfy.actionurl" id="wfTPortletclassfy.actionurl" value="${wfTPortletclassfy.actionurl}">
		</td>
<%-- 	<td class="bgc_tt short">
		是否显示滚动条
	</td>
		<td class="long">
			<c:choose>
			<c:when test="${opreateType == 'update'}">

				<ce:select name="wfTPortletclassfy.isroll"
						cssClass='input_w w_30' list="#@java.util.LinkedHashMap@{'1':'显示','0':'不显示'}"
						value="${wfTPortletclassfy.isroll}" />
			</c:when>
			<c:when test="${opreateType == 'add'}">	
				<select class="easyui-combobox input_w w_30" name="wfTPortletclassfy.isroll" id="wfTPortletclassfy.isroll" editable="false">
					<option value="">请选择
					</option>					
					<option value="1">1-显示
					</option>
					<option value="0">2-不显示
					</option>
				</select>
			</c:when>
			<c:when test="${opreateType == 'view'}">
				<input class='input_w w_30' name="wfTPortletclassfy.isroll" id="wfTPortletclassfy.isroll" value='<c:if test="${wfTPortletclassfy.isroll=='0'}">不显示</c:if><c:if test="${wfTPortletclassfy.isroll=='1'}">显示</c:if>'>
			</c:when>
			</c:choose>								
		</td> --%>
			</tr>
<%-- 			<tr>
	<td class="bgc_tt short">
		模块高度
	</td>
		<td class="long">
			<input class='input_w w_30' name="wfTPortletclassfy.porletheight" id="wfTPortletclassfy.porletheight" value="${wfTPortletclassfy.porletheight}">
		</td>
	<td class="bgc_tt short">
		模块宽度
	</td>
		<td class="long">
			<input class='input_w w_30' name="wfTPortletclassfy.porletwidth" id="wfTPortletclassfy.porletwidth" value="${wfTPortletclassfy.porletwidth}">
		</td>
			</tr> --%>
			<tr>
	
	<c:choose>
	<c:when test="${opreateType == 'view'}">
	<td class="bgc_tt short">
		机构代码
	</td>
		<td class="long">
			<input class='input_w w_30' name="wfTPortletclassfy.comcode" id="wfTPortletclassfy.comcode" value="${wfTPortletclassfy.comcode}">
		</td>
	</c:when>	
	<c:when test="${opreateType == 'update'}">
	<td class="bgc_tt short">
		机构代码
	</td>
		<td class="long">			
${wfTPortletclassfy.comcode}					<input type="hidden" name="wfTPortletclassfy.comcode" id="wfTPortletclassfy.comcode" value="${wfTPortletclassfy.comcode}">	
		</td>
	</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${opreateType == 'view'}">		
	<td class="bgc_tt short">
		创建时间
	</td>
			<td class="long">
				<input class='input_w w_30' name="wfTPortletclassfy.inserttimeforhis" id="wfTPortletclassfy.inserttimeforhis" value="${wfTPortletclassfy.inserttimeforhis}">
			</td>
			</tr>
			<tr>
	<td class="bgc_tt short">
		更新时间
	</td>
			<td class="long">
				<input class='input_w w_30' name="wfTPortletclassfy.operatetimeforhis" id="wfTPortletclassfy.operatetimeforhis" value="${wfTPortletclassfy.operatetimeforhis}">
			</td>
		</c:when>
	</c:choose>
	<td class="bgc_tt short">
		是否有效
	</td>
		<td class="long">
			<c:choose>
			<c:when test="${opreateType == 'update'}">
				<ce:select name="wfTPortletclassfy.validstatus"
						cssClass='input_w w_30' list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}"
						value="${wfTPortletclassfy.validstatus}" />	
			<input class='input_w w_30' type="hidden" name="wfTPortletclassfy.comid" id="wfTPortletclassfy.comid" value="${wfTPortletclassfy.comid}">
			</c:when>
			<c:when test="${opreateType == 'add'}">	
				<select class="easyui-combobox input_w w_30" name="wfTPortletclassfy.validstatus" id="wfTPortletclassfy.validstatus" editable="false">
					<option value="">请选择
					</option>					
					<option value="1">1-有效
					</option>
					<option value="0">2-无效
					</option>
				</select>
			</c:when>
			<c:when test="${opreateType == 'view'}">
				<input class='input_w w_30' name="wfTPortletclassfy.validstatus" id="wfTPortletclassfy.validstatus" value='<c:if test="${wfTPortletclassfy.validstatus=='0'}">无效</c:if><c:if test="${wfTPortletclassfy.validstatus=='1'}">有效</c:if>'>
			</c:when>						
			</c:choose>	
		</td>
			</tr>
				 
		</table>
		</div>
	</div>
		<table  class="fix_table">
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
