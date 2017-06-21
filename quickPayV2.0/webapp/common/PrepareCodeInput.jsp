<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/common/CodeInput.js"></script>
<html>
<base target="_self">
<body class="interface">
<form name="fm" id="fm" action="${ctx}/common/codeInput.do">
	<span id="condition" style="display:none">
	    <input type="hidden" id="fieldIndex" name="fieldIndex"/>
	    <input type="hidden" id="targetFormName" name="targetFormName"/>
	    <input type="hidden" id="fieldValue" name="fieldValue"/>
	    <input type="hidden" id="fieldNameValue" name="fieldNameValue"/>
	    <input type="hidden" id="codeMethod" name="codeMethod"/>
	    <input type="hidden" id="codeType" name="codeType"/>
	    <input type="hidden" id="codeRelation" name="codeRelation"/>
	    <input type="hidden" id="isClear" name="isClear"/>
	    <input type="hidden" id="otherCondition" name="otherCondition"/>
	    <input type="hidden" id="typeParam" name="typeParam">
	    <input type="hidden" id="callBackMethod" name="callBackMethod"/>
	    <input type="hidden" id="getDataMethod" name="getDataMethod"/>
	    <input type="hidden" id="totalRecords" name="totalRecords"/>
	    <input type="hidden" id="pageNo" name="pageNo"/>
	    <input type="hidden" id="pageSize" name="pageSize"/>
	    <input type="hidden" id="elementOrder" name="elementOrder"/>
	    <input type="hidden" id="elementLength" name="elementLength"/>
	</span>
	<div id="Result" style="width:100%">
		<span id="buttonGroup" style="align:center">
	      	<input name="SelectIt" class="button_ty" type="button" value="确定"
	        	onclick='setFieldValue()'>&nbsp;&nbsp;&nbsp;&nbsp;
	        <input name="CancelIt" class="button_ty" type="button" value="取消"
	        	onclick='cancelFieldValue()'>
	    </span>
		<select id="codeselect" name="codeselect" multiple 
		      	ondblclick="setFieldValue()" onkeydown="fieldOnKeyPress()" 
		      	size="20" style="width:100%;align:center;height:317px" class="one" >
		</select>
		<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>      	
    </div>
</form>
</body>
</html>