<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑信息</title>
<%@ include file="/common/meta_js.jsp"%>
<style>
.Layer1 {
	height: 18px;
	font-weight: 600;
	width: 100%;
	background: url(<app:contextPath/>/pages/um/images/bg_menu1.gif) repeat;
}
</style>
<script language="javascript" src="${ctx}/pages/um/user/OuterUserAdd.js""></script>
</head>

<body>
	<form name="fm" action="" method="post">
	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'" >
		<div title="用户基本信息">
			
				<div id="baseInfoDiv" align="left" class="Layer1">基本信息</div>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">用户代码</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox' required="true"/></td>
						<td class="bgc_tt short">用户简体中文名称</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox' required="true"/></td>
					</tr>
					<tr>
						<td class="bgc_tt short">密码</td>
						<td class="long"><input type="password" name="" value="" class='input_w w_30 easyui-validatebox' required="true"/></td>
						<td class="bgc_tt short">身份证号</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox' required="true"/></td>
					</tr>
					<tr>
						<td class="bgc_tt short">密码设置日期</td>
						<td class="long"><input type="text" class="easyui-validatebox Wdate" validType="checkPasswordDate['expire']" name=""  onfocus="WdatePicker();" value=""></td>
						<td class="bgc_tt short">密码过期日期</td>
						<td class="long"><input type="text" class="easyui-validatebox Wdate" validType="checkPasswordDate['expire']" name=""  onfocus="WdatePicker();" value=""></td>
					</tr>
				</table>
				
			<!-- 	<div id="baseInfoDiv" align="left" class="Layer1">用户归属</div>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">归属机构代码</td>
						<td class="long">
							<input type="text"  class="input_y w_15 easyui-validatebox" 
				name="" required="true" style="width: 45%"
				ondblclick="code_CodeSelect(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				onkeyup="code_CodeSelect(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				onchange="code_CodeChange(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				value=""/>
				<input type="text" id="flowTypeName" class="common" style="width: 45%"  value="" readOnly >
						</td>
						<td class="bgc_tt short">车行机构</td>
						<td class="long">
							<input type="text"   class="input_y w_15 easyui-validatebox" 
				name="" required="true" style="width: 45%"
				ondblclick="code_CodeSelect(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				onkeyup="code_CodeSelect(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				onchange="code_CodeChange(this,'companyCode','0,1','Y');document.getElementsByName('ggUserMonopolyCode')[0].value='';"
				value="" />
				<input type="text" id="flowTypeName"  class="input_w w_30" style="width: 45%"  value="" readOnly >
						</td>
					</tr>
				</table>
				 -->
				 
				<div id="baseInfoDiv" align="left" class="Layer1">联系方式</div>
				<table class="fix_table">
					<tr>
						<td class="bgc_tt short">电话号码</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
						<td class="bgc_tt short">手机号码</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
					</tr>
					<tr>
						<td class="bgc_tt short">传真号码</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
						<td class="bgc_tt short">邮箱</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
					</tr>
					<tr>
						<td class="bgc_tt short">邮政编码</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
						<td class="bgc_tt short">通信地址</td>
						<td class="long"><input type="text" name="" value="" class='input_w w_30 easyui-validatebox'/></td>
					</tr>
					<tr>
						<td class="bgc_tt short">备注</td>
						<td class="long" colspan="3"><textarea cols="100" rows="5" class=""></textarea></td>
					</tr>
				</table>
				
				<br/>
				<table>
					<tr>
						<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWin();" value="关闭" />
						</td>
					</tr>
				</table>
		</div>
		<div title="岗位信息">
			<h1>将岗位分层级查询出来并可勾选</h1>
		</div>
		<div title="数据权限">
			<table class="fix_table" id="userpower_table">
				<thead>
					<tr>
						<td class="bgc_tt">字典代码<font color="red">*</font></td>
						<td class="bgc_tt">字典名称<font color="red">*</font></td>
						<td class="bgc_tt">操作符号<font color="red">*</font></td>
						<td class="bgc_tt">权限值<font color="red">*</font></td>
						<td class="bgc_tt">查看表信息</td>
						<td class="bgc_tt">操作</td>
					</tr>
				</thead>
			</table>
			
			<br/>
			<table>
				<tr>
					<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSave();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" id="addOneMore" value="增加" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
</body>