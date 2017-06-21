<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTUser" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/user/UmTUser.js"></script>
<script type="text/javascript">

/*页面加载触发*/
$(document).ready(function(){
	
	/* $('input[name="umTUser.userName"]').each(function(index,object){
	    $(this).validatebox({ 
	        validType: ['identifyByType[\'umTUser.userType\',\''+(getElementOrder(object)-1)+'\']']
	    }); 
	});  */
	
//创建窗口
$('#win').window({   

      width:500,   

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
<div class="right_detail_top"><h3>用户信息查询</h3></div>
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<tr>
                        <td class="bgc_tt short">
                        	用户代码：
                        </td>
                          	<td  class="long">
                          		<input type="hidden" name="umTUser.comCode" id="umTUser.comCode" value="${umTUser.comCode}" maxlength="8" />
                          		<input type="hidden" name="umTUser.sourceFlag" id="umTUser.sourceFlag" value="" />
								<input type="hidden" name="umTUser.bindStatus" id="umTUser.bindStatus" value="" />
                        		
                        		<input class='input_w w_15' name="umTUser.id.userCode" id="umTUser.id.userCode" value="${umTUser.id.userCode}" maxlength="10">
                        	</td>
                        <td class="bgc_tt short">
                        	用户名称：
                        </td>
						<td class="long">
							<input class='input_w w_15' name="umTUser.userName" id="umTUser.userName" value="${umTUser.userName}" maxlength="20">
						</td>
					</tr>
					<tr>
						<td class="bgc_tt short">
	                		手机号：
	                	</td>
	                	<td class="long">
							<input class='input_w w_15' name="umTUser.mobile" id="umTUser.mobile" value="${umTUser.mobile}"  maxlength="18">
						</td>
						<td class="bgc_tt short">
							身份证号：
						</td>
						<td class="long">
							<input class='input_w w_15' name="umTUser.identityNumber" id="umTUser.identityNumber" value="${umTUser.identityNumber}"  maxlength="18">
						</td>
					</tr>	
					<tr>
                		<td class="bgc_tt short">
                			用户类型：
                		</td>
                		<td class="long">
                			<ce:select name="umTUser.userType" id="umTUser.userType" list="#@java.util.LinkedHashMap@{'0':'请选择','01':'内部用户','02':'外部用户'}" 
                				value="umTUser.userType" cssClass='input_w w_15'/>
                		</td>
                		<td class="bgc_tt short">
                			用户分类：
                		</td>
                		<td class="long">
                			<ce:select name="umTUser.userSort" id="umTUser.userSort" 
                			list="#@java.util.LinkedHashMap@{'':'请选择','01':'其他','02':'地市交警','07':'高速交警','03':'地市辅警','08':'高速辅警','04':'查勘员','05':'理赔经理','06':'保协管理人员' }" 
                				value="umTUser.userSort" cssClass='input_w w_15'/>
                		</td>
                	</tr>
                	<tr>
                		<td class="bgc_tt short">
                			所属交警大队：
                		</td>
                		<td class="long">
                			<select id="umTUser.policeTeamId" name="umTUser.policeTeamId" class="input_w w_30">
                					<option value="0">请选择</option>
                				<c:forEach var="qpTTPTeam" items="${qpTTPTeamList}">
                					<option value="${qpTTPTeam.id.teamId}" <c:if test="${qpTTPTeam.id.teamId==umTUser.policeTeamId}">selected</c:if>>${qpTTPTeam.teamName}</option>
                				</c:forEach>
                			</select>
                		</td>
                		<td class="bgc_tt short">
                			所属受理点：
                		</td>
                		<td class="long">
                			<select id="umTUser.centerId" name="umTUser.centerId" class="input_w w_30">
                					<option value="0">请选择</option>
                				<c:forEach var="qpTTPFastCenter" items="${fastCenterList}">
                					<option value="${qpTTPFastCenter.id.centerId}" <c:if test="${qpTTPFastCenter.id.centerId==umTUser.centerId}">selected</c:if>>${qpTTPFastCenter.centerName}</option>
                				</c:forEach>
                			</select>
                		</td>
                	</tr>
					<tr>
					    <td class="bgc_tt short">
                			所属保险公司：
                		</td>
                		<td class="long">
                			<select id="umTUser.coId" name="umTUser.coId" class="input_w w_30">
                					<option value="0">请选择</option>
                				<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
                					<option value="${qpTICCompany.id.coId}" <c:if test="${qpTICCompany.id.coId==umTUser.coId}">selected</c:if>>${qpTICCompany.coName}</option>
                				</c:forEach>      			
                			</select>
                		</td>
					    
						<td class="bgc_tt short">
	                		有效状态：
	                	</td>
	                	<td class="long">
	                		<ce:select name="umTUser.validStatus" id="umTUser.validStatus" list="#@java.util.LinkedHashMap@{'1':'有效','0':'无效'}" value="umTUser.validStatus" cssClass='input_w w_15'/>
						</td>
					</tr>
													 
					<tr>
						<td colspan="6" align="center">
							<input type="button" class="button_ty" value="查 询"
								onclick="executeQuery();"> 
							<input type="reset" class="button_ty" ind="ind" value="重 置" /> 
							<input type="button" id="addButton"
								onclick="prepareAdd();" class="button_ty" value="增 加">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div id="win" iconCls="icon-save" title="请选择机构" data-options="closed:true">
	<ul id="tt" class="easyui-tree"></ul>
	</div>
	<div class="margin-control" >
	<table id="UmTUserTable"></table>
	</div>
</body>
</html>
