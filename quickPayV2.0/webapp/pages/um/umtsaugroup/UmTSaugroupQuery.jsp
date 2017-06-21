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
/*页面加载触发*/
$(document).ready(function(){
//创建窗口
$('#win').window({   

      width:500,   

      height:400,   

      modal:true,
      
      closed:true

  });   
//如果点击不是查看绑定双击，显示树

$("#umTSaugroup\\.comCode").dblclick( function () { 
	createTree('tt',0,'');  
	$('#win').window('open'); 
	$('#tt').tree({
		onDblClick: function(node){
			var id=node.id;
			$('#umTSaugroup\\.comCode').val(id);
			$('#win').window('close'); 
			
					
		}
	});

	});
});
</script>
</head>
<body>
<div class="right_detail_top"><h3>团队信息</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							
							<tr>
	<td class="bgc_tt short">
		团队编码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSaugroup.teamCode" id="umTSaugroup.teamCode" value="${umTSaugroup.teamCode}">
		</td>
	<td class="bgc_tt short">
		团队名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSaugroup.teamName" id="umTSaugroup.teamName" value="${umTSaugroup.teamName}">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
			<input  class='input_w w_15 selectcode' name="umTSaugroup.comCode" id="umTSaugroup.comCode" value="${umTSaugroup.comCode}">
		</td>
	<td class="bgc_tt short">
		团队类型
	</td>
		<td class="long">
			<ce:codeselect  riskCode="" codeType="teamType" cssClass="easyui-combobox w_15" name="umTSaugroup.teamType" id="umTSaugroup.teamType" value=""
																emptyOption="true" withCode="true" editable="false" ></ce:codeselect>
			<%-- <input class='input_w w_15' name="umTSaugroup.teamType" id="umTSaugroup.teamType" value="${umTSaugroup.teamType}"> --%>
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		团队经理名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSaugroup.managerName" id="umTSaugroup.managerName" value="${umTSaugroup.managerName}">
		</td>
	<%-- <td class="bgc_tt short">
		团队经理工号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSaugroup.managerCode" id="umTSaugroup.managerCode" value="${umTSaugroup.managerCode}">
		</td> --%>
		<td class="bgc_tt short">
		有效状态
	</td>
		<td class="long">
			<ce:select name="umTSaugroup.validStatus" id="umTSaugroup.validStatus" list="#@java.util.LinkedHashMap@{'':'请选择','1':'有效','0':'无效'}" value="umTSaugroup.validStatus" cssClass='input_w w_15'/>
		</td>
							</tr>
							
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<!-- <input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加"> -->
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div id="win" iconCls="icon-save" title="请选择机构" data-options="closed:true">
	<ul id="tt" class="easyui-tree"></ul>
	</div>
	<div class="margin-control">
	<table id="UmTSaugroupTable"></table>
	</div>
</body>
</html>
