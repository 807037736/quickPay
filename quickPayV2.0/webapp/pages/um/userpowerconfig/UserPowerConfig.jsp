<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUser" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/userpowerconfig/UserPowerConfig.js"></script>
<script type="text/javascript">
/*页面加载触发*/
$(document).ready(function(){
	/* $('#fm input').each(function () {
        if ($(this).attr('required') || $(this).attr('validType'))
            $(this).validatebox();
    }); */
//创建窗口
$('#win').window({   

      width:500,   
      
      left:200,

      height:400,   

      modal:true,
      
      closed:true

  });   
//如果点击不是查看绑定双击，显示树

$("#umTUser\\.comCode").dblclick( function () { 
	createTree('tt',0,'');  
	$('#win').window('open'); 
	$('#tt').tree({
		onDblClick: function(node){
			var id=node.id;
			$('#umTUser\\.comCode').val(id);
			$('#win').window('close'); 
			
					
		}
	});

	});
});
</script>
</head>
<body>
<div class="right_detail_top"><h3>用户权限配置</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户代码
	</td>
	  	<td  class="long">
			<input class='input_w w_15' name="umTUser.id.userCode" id="umTUser.id.userCode" value="${umTUser.id.userCode}" maxlength="10">
		</td>
	<td class="bgc_tt short">
		用户名称
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUser.userName" id="umTUser.userName" value="${umTUser.userName}" maxlength="20">
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		归属机构
	</td>
		<td class="long">
		<input  class='input_w w_15 selectcode' name="umTUser.comCode" id="umTUser.comCode" value="${umTUser.comCode}" maxlength="8">
		</td>
	<td class="bgc_tt short">
		身份证号
	</td>
		<td class="long">
			<input class='input_w w_15' name="umTUser.identityNumber" id="umTUser.identityNumber" value="${umTUser.identityNumber}" maxlength="18">
		</td>
							</tr>
							
							
							
							
							<tr>
	<td class="bgc_tt short">
		用户类型
	</td>
		<td class="long">
			<ce:select name="umTUser.userType" id="umTUser.userType" list="#@java.util.LinkedHashMap@{'0':'请选择','01':'内部用户','02':'外部用户'}" value="umTUser.userType" cssClass='input_w w_15'/>
		</td>
	<td class="bgc_tt short">
		手机号
	</td>
		<td class="long">
		<input class='input_w w_15' name="umTUser.mobile" id="umTUser.mobile" value="${umTUser.mobile}" maxlength="11">
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
	<table id="UmTUserTable"></table>
	</div>
</body>
</html>
