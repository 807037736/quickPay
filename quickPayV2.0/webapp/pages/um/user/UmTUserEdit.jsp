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
		
		 /* $("#opreateType").val($("#opreateType").val().substring(0, 1) + "****" + $("#opreateType").val().substring(2, 5)); */
        
		 $('#fm input').each(function () {
	            if ($(this).attr('required') || $(this).attr('validType'))
	                $(this).validatebox();
	        });
		if($('#opreateType').val()=='view'){
			setReadonlyOfAllInput("userfm");
			setReadonlyOfAllInput("accountfm");
			/* $("input").css('border','#FFF'); */
		}
		
		$('#umTUserProvince').combobox({
		 	url: '${ctx}/qp/qptcommon/getProvinceList.do',
			valueField: 'provId',
			textField: 'provName' ,
	 		onSelect: function() {
	 			$('#umTUserCity').combobox('clear');
	 			//$('#umTUserDistrict').combobox('clear');
	 			//$('#umTUserRoad').combobox('clear');
	 			$('#umTUserCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#umTUserProvince').combobox('getValue'));
	 		},
	 		onUnselect: function() {
	 			$('#umTUserCity').combobox('clear');
	 			//$('#umTUserDistrict').combobox('clear');
	 			//$('#umTUserRoad').combobox('clear');
	 			$('#umTUserCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#umTUserProvince').combobox('getValue'));
	 		}
	   	}); 

		$('#umTUserCity').combobox({
		 	//url: '${ctx}/qp/qptcommon/getCityList.do'
			valueField: 'cityId',
			textField: 'cityName' ,
	 		onSelect: function(q) {
	 			//$('#umTUserDistrict').combobox('clear');
	 			//$('#umTUserRoad').combobox('clear');
	 			//$('#umTUserDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?cityId=' +$('#umTUserCity').combobox('getValue'));
	 		},
	 		onUnselect: function() {
	 			//$('#umTUserDistrict').combobox('clear');
	 			//$('#umTUserRoad').combobox('clear');
	 			//$('#umTUserDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?cityId=' +$('#umTUserCity').combobox('getValue'));
	 		}
	   	}); 
		
		 <%--
		$('#umTUserDistrict').combobox({
	   		valueField: 'districtId',
			textField: 'districtName',
			onSelect: function() {
	 			$('#umTUserRoad').combobox('clear');
	 			$('#umTUserRoad').combobox('reload', '${ctx}/qp/qptcommon/getRoadList.do?districtId=' +$('#umTUserDistrict').combobox('getValue'));
	 		},
			onSelect: function() {
 				$('#umTUserRoad').combobox('clear');
 				$('#umTUserRoad').combobox('reload', '${ctx}/qp/qptcommon/getRoadList.do?districtId=' +$('#umTUserDistrict').combobox('getValue'));
 			}
	   	}); 
		
		$('#umTUserRoad').combobox({
	   		valueField: 'roadId',
			textField: 'roadName'
	   	}); 
		--%>
		
		$("#umTUserProvince").combobox("setValue",'${umTUser.province}');
		$('#umTUserCity').combobox('reload', '${ctx}/qp/qptcommon/getCityList.do?provId=' +$('#umTUserProvince').combobox('getValue'));
		$("#umTUserCity").combobox("setValue",'${umTUser.city}');
		//$('#umTUserDistrict').combobox('reload', '${ctx}/qp/qptcommon/getDistrictList.do?cityId=' +$('#umTUserCity').combobox('getValue'));
		//$("#umTUserDistrict").combobox("setValue",'${umTUser.district}');
		//$('#umTUserRoad').combobox('reload', '${ctx}/qp/qptcommon/getRoadList.do?districtId=' +$('#umTUserDistrict').combobox('getValue'));
		//$("#umTUserRoad").combobox("setValue",'${umTUser.road}');
		
		initShow();
	});
	
	var centerCompare = function(){
		 var centercompareName = "${centercompareName}";
         var a = centercompareName.substring(0,centercompareName.length-1);
         $('#surveyCenterId').combobox({
        	 onLoadSuccess :function(){
        	    $('#surveyCenterId').combobox('setValue',a);
        	 }
        });
	};
	
	function initShow(){
		if('${opreateType}' == 'update' || '${opreateType}' == 'view'){
			if("${umTUser.userSort}" == '07'){
				$("#tdTeam").show();
				$("#tdTeamText").show();
				$("#tdCenter").show();
				$("#tdCenterText").show();
				var teamUrl = contextRootPath+'/qp/qptcommon/getTeamList.do?isHighway=' + "1";
				$('#policeTeamId').combobox(
						'reload',
						teamUrl);
				return;
			}
			if("${umTUser.userSort}" == '02'){
				centerCompare();
				$("#tdTeam").show();
				$("#tdTeamText").show();
				$("#tdSurveyCenter").show();
				$("#tdSurveyCenterText").show();
				var teamUrl = contextRootPath+'/qp/qptcommon/getTeamList.do?isHighway=' + "0";
				$('#policeTeamId').combobox(
						'reload',
						teamUrl);
				return;
			}
			if("${umTUser.userSort}" == '03' || "${umTUser.userSort}" == '08'){
				$("#tdCenter").show();
				$("#tdCenterText").show();
				return;
			}
			if("${umTUser.userSort}" == '04'){
				centerCompare();
				$("#tdSurveyCenter").show();
				$("#tdSurveyCenterText").show();
				$("#tdCompany").show();
				$("#tdCompanyText").show();
				return;
			}
			if("${umTUser.userSort}" == '05'){
				$("#tdCompany").show();
				$("#tdCompanyText").show();
				return;
			}
		}
	}
	
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
	
	/*add by shenyichan 2014/03/17*/
	/*捕捉tab页选择事件*/
	$(document).ready(function(){
		$('#mytab').tabs({
			onSelect:function(title,index){
				var opreateType = $('#opreateType').val();
				var submitone = $('#submitone').val();
				if(opreateType == 'add' && title == '账户信息' && submitone == 'no'){
					$('#mytab').tabs('select',0);  //tab依然选择停留在用户信息
					$.messager.alert('提示信息','请先保存用户基本信息！','info');
				}
				return false;
			}
		});
		
	});
</script>
</head>

<body>

<div class="right_detail_top" style="margin-bottom:0px;"><h3><c:choose>
			<c:when test="${opreateType == 'update'}">
					修改用户信息
				</c:when>
			<c:when test="${opreateType == 'add'}">
					增加用户信息
				</c:when>
			<c:when test="${opreateType == 'view'}">
					查看用户信息
				</c:when>
		</c:choose>		</h3></div>


	<input type="hidden" name="opreateType" id="opreateType" value="${opreateType}"/>
	<input type="hidden" name="submitone" id="submitone" value="no"/> <!-- 是否提交第一个tab页的标识 -->
	<div class="easyui-tabs" id="mytab">
			<div title="用户信息">
				<form name="userfm" id="userfm" action="/um/umtuser" namespace="/um/umtuser" method="post">
		<div id="wrapper">
	<div id="container">
		<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		用户代码：
	</td>
	  	<td class="long">
	  	<c:choose>
	  		<c:when test="${opreateType == 'add'}">
	  			<input class='input_w w_15 easyui-validatebox' required="true" name="umTUser.id.userCode" id="umTUser.id.userCode" value="${umTUser.id.userCode}" />
	  		</c:when>
			<c:when test="${opreateType == 'update'}">
				${umTUser.id.userCode}					
				<input type="hidden" name="umTUser.id.userCode" id="umTUser.id.userCode" value="${umTUser.id.userCode}" />
			</c:when>
			<c:when test="${opreateType == 'view'}">
				${umTUser.id.userCode}					
				<input type="hidden" name="umTUser.id.userCode" id="umTUser.id.userCode" value="${umTUser.id.userCode}" />
			</c:when>
		</c:choose>
		</td>
	<td class="bgc_tt short">
		用户名称：
	</td>
		<td class="long">
			<input class='input_w w_15 easyui-validatebox' required="true" name="umTUser.userName" id="umTUser.userName" value="${umTUser.userName}" />
			<%-- <input type="text" name="umTUser.comCode" id="umTUser.comCode" value="${umTUser.comCode}" /> --%>
			<input type="hidden" name="umTUser.comCode" id="umTUser.comCode" value="44030000" />
		</td>
	</tr>
	<tr>
		<td class="bgc_tt short">
	    	手机号码：
	    </td>
	    <td class="long">
	    	<input class='input_w w_15 easyui-validatebox' validType="mobile" name="umTUser.mobile" id="umTUser.mobile" value="${umTUser.mobile}" validType="mobile">
	    </td>
		<td class="bgc_tt short">
			电话号码：
		</td>
		<td class="long">
			<input class='input_w w_15' name="umTUser.telePhone" validType="phoneOrMobile" id="umTUser.telePhone" value="${umTUser.telePhone}"  >
		</td>
	</tr>
	<tr>
		<td class="bgc_tt short">
			用户类型：
		</td>
		<td class="long">
			<ce:select name="umTUser.userType" id="umTUser.userType" list="#@java.util.LinkedHashMap@{'01':'内部用户','02':'外部用户'}" 
				value="umTUser.userType" cssClass='input_w w_15'/>
		</td>
		<td class="bgc_tt short">
			<font color="red">*</font>用户分类：
		</td>
		<td class="long">
			<ce:select name="umTUser.userSort" id="umTUser.userSort" 
			list="#@java.util.LinkedHashMap@{'01':'其他','02':'地市交警','07':'高速交警','03':'地市辅警','08':'高速辅警','04':'查勘员','05':'理赔经理','06':'保协管理人员' }" 
				value="umTUser.userSort" cssClass='input_w w_15' onchange="showTr()"/>
		</td>
	</tr>
	<tr>
	    <td class="bgc_tt short">
	    	性别：
	    </td>
	    	<td class="long">
	    		 <ce:select cssClass='input_w w_15' name="umTUser.sex" id="umTUser.sex" list="#@java.util.LinkedHashMap@{'1':'男','2':'女'}" value="umTUser.sex" />
	    	</td>
	    <td class="bgc_tt short">
	    	生日：
	    </td>
		<td class="long">
		
			  <input class='input_w w_15' name="umTUser.birthday" id="umTUser.birthday" value="${umTUser.birthday}" onclick="WdatePicker()">
		</td>
	</tr>
	<tr>
		<td class="bgc_tt short">
			雇员工号：
		</td>
		<td class="long">
			<input class='input_w w_15 easyui-validatebox' name="umTUser.employeeId" id="umTUser.employeeId" value="${umTUser.employeeId}" />
		</td>
		<td class="bgc_tt short">
			身份证号：
		</td>
		<td class="long">
			<input class='input_w w_15 easyui-validatebox' name="umTUser.identityNumber" id="umTUser.identityNumber" value="${umTUser.identityNumber}" onchange="checkIdNo();" />
		</td>
	</tr>	
	<tr>
		<td class="bgc_tt short" style="vertical-align:top">
			归属地市：
		</td>
		<td class="long" >
			<select id="umTUserProvince" name="umTUserProvince" editable="false" class="input_w w_15" style="width:111px;" readonly>
			</select>
			<c:if test="${admin == 'admin'}">
			    <select id="umTUserCity" name="umTUserCity" editable="false" class="input_w w_15" style="width:111px;" >
			    </select>
			</c:if>
			<c:if test="${admin != 'admin'}">
			    <select id="umTUserCity" name="umTUserCity" editable="false" class="input_w w_15" style="width:111px;" readonly>
			    </select>
			</c:if>
			
			<%-- <select id="umTUserDistrict" name="umTUserDistrict" editable="false" class="input_w w_15" style="width:115px;">
			</select>
			<select id="umTUserRoad" name="umTUserRoad" class="input_w w_30" >
			</select>
			<br>
			<textarea style="margin-top:8px;margin-bottom:8px;height:35px;" id="umTUserStreet" name="umTUserStreet" 
				class="input_w" rows="2" cols="94">${umTUser.street}</textarea> --%>
		</td>
		<td class="bgc_tt short">
			有效状态：
		</td>
		<td class="long">
			<select id="umTUser.validStatus" name="umTUser.validStatus" class="input_w w_30">
					<option value="1" <c:if test="${umTUser.validStatus==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${umTUser.validStatus==0}">selected</c:if>>无效</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="bgc_tt short" style="display: none;" id="tdTeam">
		    <font color="red">*</font>所属交警大队：
		</td>
		<td class="long" style="display: none;" id="tdTeamText">
			<select id="policeTeamId" name="umTUser.policeTeamId"  class="input_w w_30 easyui-combobox">
				<%-- <option value="0">请选择</option>
				<c:forEach var="qpTTPTeam" items="${qpTTPTeamList}">
					<option value="${qpTTPTeam.id.teamId}" <c:if test="${qpTTPTeam.id.teamId==umTUser.policeTeamId}">selected</c:if>>${qpTTPTeam.teamName}</option>
				</c:forEach> --%>
			</select> 
			<!-- <input type="text" id="policeTeamId" name="umTUser.policeTeamId"  class="input_w w_30 easyui-validatebox"> -->
		</td>
		<td class="bgc_tt short" style="display: none;" id="tdCompany">
        	<font color="red">*</font>所属保险公司：
        </td>
   		<td class="long" style="display: none;" id="tdCompanyText">
   			<select id="coId" name="umTUser.coId" class="input_w w_30 easyui-combobox">
   					<option value="0">请选择</option>
   				<c:forEach var="qpTICCompany" items="${qpTICCompanyList}">
   					<option value="${qpTICCompany.id.coId}" <c:if test="${qpTICCompany.id.coId==umTUser.coId}">selected</c:if>>${qpTICCompany.coName}</option>
   				</c:forEach>      			
   			</select>
        </td>
        <td class="bgc_tt short" style="display: none;" id="tdCenter">
			<font color="red">*</font>所属快赔中心：
		</td>
		<td class="long" style="display: none;" id="tdCenterText">
			<select id="centerId" name="umTUser.centerId" class="input_w w_30 easyui-combobox" >
					<option value="0">请选择</option>
				<c:forEach var="qpTTPFastCenter" items="${fastCenterList}">
					<option value="${qpTTPFastCenter.id.centerId}" <c:if test="${qpTTPFastCenter.id.centerId==umTUser.centerId}">selected</c:if>>${qpTTPFastCenter.centerName}</option>
				</c:forEach>
			</select>
		</td>
		
		
		<td class="bgc_tt short" style="display: none;" id="tdSurveyCenter">
			<font color="red">*</font>所属快赔中心：
		</td>
		<td class="long" style="display: none;" id="tdSurveyCenterText">
			<select id="surveyCenterId" name="umTUser.surveyCenterId" class="input_w w_30 easyui-combobox" 
			 data-options="multiple:true">
				<c:forEach var="qpTTPFastCenter" items="${fastCenterList}">
					<option value="${qpTTPFastCenter.id.centerId}" <c:if test="${qpTTPFastCenter.id.centerId==umTUser.centerId}">selected</c:if>>${qpTTPFastCenter.centerName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	</table>
	<table class="fix_table">
		<tr>
			<td colspan=4 align="center">
					<input type="button" class="button_ty" onclick="executeSaveUser();" ind="ind"  value="保存" />
					<input type="reset" class="button_ty" ind="ind"  value="重置" />
					<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
			</td>
		</tr>
	</table>
		</div></div>
	</form>
	</div>
<div title="账户信息">
<form name="accountfm" id="accountfm" action="/um/umtaccount" namespace="/um/umtaccount" method="post">
<div id="wrapper">
	<div id="container">

		<table class="fix_table">
	<tr>
	<td class="bgc_tt short">
		用户代码：
	</td>
	  	<td class="long">
		<input class='easyui-validatebox input_w w_15' name="umTAccount.id.userCode" id="umTAccount.id.userCode" value="${umTAccount.id.userCode}" required="true">
		</td>
	<td class="bgc_tt short">
		密码：
	</td> 
		<td class="long">
			<input class='easyui-validatebox input_w w_15' type='password' name="umTAccount.password" id="umTAccount.password" value="${umTAccount.password}" required="true" validType="minLen[5]" onclick="clearDefaultText(this,'${umTAccount.password}')">
			<input class='input_w w_15' type='hidden' name="password1" id="password1" value="${umTAccount.password}"  >
			<input class='input_w w_15' type='hidden' name="isChange" id="isChange" value="0"  >
		</td>
							</tr>
							<tr>
	<td class="bgc_tt short">
		指纹ID：
	</td>
		<td class="long">
		
		<input class='input_w w_15' name="umTAccount.fingerId" id="umTAccount.fingerId" value="${umTAccount.fingerId}"/>
		</td>
	<td class="bgc_tt short">
		有效状态：
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
						<input type="button" class="button_ty" onclick="executeSaveAccount();" ind="ind"  value="保存" />
						<input type="reset" class="button_ty" ind="ind"  value="重置" />
						<input type="button" class="button_ty" onclick="closeWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
		</div>
		</div>
	</form>
		</div>
	</div>
	<div id="win" iconCls="icon-save" title="请选择机构" data-options="closed:true">
		<div id="tt"></div>
	</div>
</body>
</html>
