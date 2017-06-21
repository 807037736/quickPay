<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.portal.schema.model.WfTPortletclassfy" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/portal/WfTPortletclassfy.js"></script>
<script language="javascript" src="${ctx}/pages/wf/common/formatter.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="right_detail_top"><h3>Portal管理</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">
							模块名称
						</td>
						<td class="long">
							<input class='input_w w_30' name="wfTPortletclassfy.portletname" id="wfTPortletclassfy.portletname" value="${wfTPortletclassfy.portletname}">
						</td>
						<td class="bgc_tt short">
							模块地址
						</td>
						<td class="long">
							<input class='input_w w_30' name="wfTPortletclassfy.actionurl" id="wfTPortletclassfy.actionurl" value="${wfTPortletclassfy.actionurl}">
						</td>

					</tr>					
					<tr>
						<td class="bgc_tt short">
							机构代码
						</td>
						<td class="long">
							<input class='input_w w_30' name="wfTPortletclassfy.comcode" id="wfTPortletclassfy.comcode" value="${wfTPortletclassfy.comcode}">
						</td>
						<td class="bgc_tt short">
							是否有效
						</td>
						<td class="long">
							<select class="easyui-combobox input_w w_30" name="wfTPortletclassfy.validstatus" id="wfTPortletclassfy.validstatus" editable="false">
								  <option value="2">请选择
				                  </option>
				                  <option value="1">1-有效
				                  </option>
				                  <option value="0">2-无效
				                  </option>
							</select>
						</td>
					</tr> 			 
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询" id="portalQuery"
								onclick="executeQuery();"> 
							<input type="reset" class="button_ty" value="重  置" />	
							<input type="button" id="addButton" onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
				
			</div>
		</div>
	</form>
	<div class="margin-control">
		<table id="WfTPortletclassfyTable"></table>	
	</div>
</body>
</html>
