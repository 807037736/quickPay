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

$("#umTSauuser\\.comCode").dblclick( function () { 
	createTree('tt',0,'');  
	$('#win').window('open'); 
	$('#tt').tree({
		onDblClick: function(node){
			var id=node.id;
			$('#umTSauuser\\.comCode').val(id);
			$('#win').window('close'); 
			
					
		}
	});

	});
});
</script>
</head>
<body>
<div class="right_detail_top"><h3>团队成员信息</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTSauuser.userCode" id="umTSauuser.userCode" value="${umTSauuser.userCode}">
		</td>
	<td class="bgc_tt short">
		用户姓名
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.userName" id="umTSauuser.userName" value="${umTSauuser.userName}">
		</td>
							</tr>
							<tr>
	
	<td class="bgc_tt short">
		人员属性
	</td>
		<td class="long">
		<ce:codeselect  riskCode="" codeType="sauUserType" cssClass="easyui-combobox w_20" name="umTSauuser.userType" id="umTSauuser.userType" value=""
																emptyOption="true" withCode="true" editable="false" ></ce:codeselect>
			<%-- <input class='input_w w_15' name="umTSauuser.userType" id="umTSauuser.userType" value="${umTSauuser.userType}"> --%>
		</td>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.identifyNumber" id="umTSauuser.identifyNumber" value="${umTSauuser.identifyNumber}">
		</td>
							</tr>
							<tr>
	
	<td class="bgc_tt short">
		团队编码
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTSauuser.teamCode" id="umTSauuser.teamCode" value="${umTSauuser.teamCode}">
		</td>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
			<input  class='input_w w_15 selectcode' name="umTSauuser.comCode" id="umTSauuser.comCode" value="${umTSauuser.comCode}">
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
	<table id="UmTSauuserTable"></table>
	</div>
</body>
</html>
