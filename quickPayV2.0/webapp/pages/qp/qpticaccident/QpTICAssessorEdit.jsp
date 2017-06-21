<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICAccident" %>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

</head>
<body>
	<form name="assessorFm" id="assessorFm">
		<div class="block">
			<h3>查勘估损</h3>
				<table class="fix_table"  >
					<tr>
						<td class="bgc_tt short">
							<font color="red">*</font>&nbsp;估损金额： 
						</td>
						<td class="long">
							<input class='input_w w_15 easyui-validatebox' required="true"  validType="intOrFloat" name="qpTICAccidentEstimateLossSum" id="qpTICAccidentEstimateLossSum" value="${qpTICAccident.estimateLossSum}">
						</td>	
						
					    <td class="bgc_tt short">
							定损价格:
						</td>
						<td class="long">
							<input class='input_w w_15 easyui-validatebox' validType="intOrFloat" name="qpTICAccidentFixedLossPrice" id="qpTICAccidentFixedLossPrice" value="${qpTICAccident.fixedLossPrice}">
						</td>					
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="bgc_tt short" style="vertical-align:top"> -->
<!-- 							<font color="red">*</font>&nbsp;车架号：  -->
<!-- 						</td> -->
<!-- 						<td class="long" colSpan="3"> -->
<%-- 							<input class='input_w w_15 easyui-validatebox' required="true"  name="qpTICAccidentChassisNumber" id="qpTICAccidentChassisNumber" value="${qpTICAccident.chassisNumber}"> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					
					<tr>
						<td class="bgc_tt short" style="vertical-align:top">
							&nbsp;备注： 
						</td>
						<td class="long" colSpan="3">
							<textarea class="input_w easyui-validatebox" style="margin-top:8px;height:35px;" id="qpTICAccidentSurveyNotes" name="surveyNotes" rows="2" cols="95" >${qpTICAccident.surveyNotes}</textarea>
						</td>
					</tr>
					
					
					<tr style="margin-top:210px;">
					    <td class="bgc_tt short">
							查勘照片：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' readonly  name="qpTICAccidentSurveyGroupId" id="qpTICAccidentSurveyGroupId" value="${qpTICAccident.surveyGroupId}" >
						</td>
						<td colspan=4 align="center">
						      <input type="button" class="button_ty" onclick="uploadPictures();" value="上传图片"> 
						      <input type="button" class="button_ty" onclick="showPicture()" value="查看照片" />
						</td>
					</tr>		
					<tr style="margin-top:210px;" name='addreplace' id="replaceProject">
					    <td class="bgc_tt short">
							更换项目：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' name="replaceProjects"  value="${replaceProject[0].accidentProject }" >
						</td>
						<td class="bgc_tt short">
							核定金额：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'  name="replaceProjectsMoney"  value="${replaceProject[0].accidentProjectMoney }" >
							<input type="button" class="button_ty" onclick="replaceProject()" value="添加一行" />
						</td>
					</tr>
					<c:if test="${not empty replaceProject }">
						<c:if test="${(replaceProject)!= null && fn:length(replaceProject) > 1}">
							<c:forEach var="q" items="${replaceProject}" varStatus="status">
								<c:if test="${status.index > 0 }">
									<tr name='addreplace'>
										<td>&nbsp;</td>
										<td>
											<input class='input_w w_30 easyui-validatebox' required='true' name='replaceProjects' value='${q.accidentProject }'>
										</td>
										<td>&nbsp;</td>
										<td>
											<input class='input_w w_30 easyui-validatebox' validType='intOrFloat' required='true' name='replaceProjectsMoney' value='${q.accidentProjectMoney }'> 
											<input type=button class=button_ty onclick=replaceDelete(this) value='删除' />
										</td>
									</tr>
								</c:if>
							</c:forEach>				
						</c:if>
					</c:if>
					
					
					<tr style="margin-top:210px;" name='addrepair' id="repairProject">
					    <td class="bgc_tt short">
							修理项目：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox'   name="repairProjects" value="${repairProject[0].accidentProject }" >
						</td>
						<td class="bgc_tt short">
							核定金额：
						</td>
						<td class="long">
							<input class='input_w w_30 easyui-validatebox' validType='intOrFloat'  name="repairProjectsMoney" value="${repairProject[0].accidentProjectMoney }" >
							<input type="button" class="button_ty" onclick="repairProject()" value="添加一行" />
						</td>
					</tr>
					<c:if test="${not empty repairProject }">
						<c:if test="${(repairProject)!= null && fn:length(repairProject) > 1}">
							<c:forEach var="q" items="${repairProject}" varStatus="status">
								 <c:if test="${status.index > 0 }">
									<tr name='addrepair'>
										<td>&nbsp;</td>
										<td>
											<input class='input_w w_30 easyui-validatebox' required='true' name='repairProjects' value='${q.accidentProject }'>
										</td>
										<td>&nbsp;</td>
										<td>
											<input class='input_w w_30 easyui-validatebox' validType='intOrFloat' required='true' name='repairProjectsMoney' value='${q.accidentProjectMoney }'> 
											<input type=button class=button_ty onclick=repairDelete(this) value='删除' />
										</td>
									</tr>
								</c:if>
							</c:forEach>				
						</c:if>
					</c:if>
					
					 <tr id="subut">
						<td colspan=4 align="center">
							 <input type="button" class="button_ty" onclick="saveAssessorInfo();" value="保存"> 
				            <input type="button" class="button_ty" onclick="closeUserWindow();" value="关闭" />
						</td>
					</tr> 
				</table>
		</div>
		
		<input type="hidden" id="qpTICAccidentAcciId" name="qpTICAccidentAcciId" value="${qpTICAccident.acciId}" />
		<input type="hidden" id="cid" name="cid" value="${cid}" />
	</form>

	<script language="javascript">
	<%-- 估损信息保存 --%>
	function saveAssessorInfo() {
		if (!$('#assessorFm').form('validate')) {
			return ;
		}
		
		if(addReplaceOrRePair() == false){
			return ;
		}
// 		$.messager.confirm('提示', '是否确认保存估损信息？', function(r){
// 			if (r) {
// 				$('#assessorFm').form('submit', {
// 					url: '${ctx}/qp/qpticaccident/saveAssessorInfo.do?cid='+$('#cid').val(),
// 					success: function(msg) {
// 						var obj = eval('(' + msg + ')');
// 						$.messager.alert('提示信息',obj.content,'info');
// 					}
// 				});
// 			}
// 		});
		$('#assessorFm').form('submit', {
			url: '${ctx}/qp/qpticaccident/saveAssessorInfo.do?cid='+$('#cid').val(),
			success: function(msg) {
				var obj = eval('(' + msg + ')');
				$.messager.alert('提示信息',obj.content,'info');
				if(obj.flag == 'Y'){
					closeUserWindow();
				}
			}
		});
	}
	
	<%-- 上传图片 --%>
	function uploadPictures(){
		var url="${ctx}/qp/qpticpicture/prepareUploadPic.do?acciId=" + $("#qpTICAccidentAcciId").val() + "&SurveyGroupId=" + $("#qpTICAccidentSurveyGroupId").val();
		window.open(url,"_blank",'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
    } 
	
	<%-- 查看图片 --%>
	function showPicture(){
		var caseUrl = contextRootPath + "/qp/qpticpicturegroup/viewPictureGroupPC.do?groupId=" +$("#qpTICAccidentSurveyGroupId").val() + "&acciId=" + $("#qpTICAccidentAcciId").val();
		window.open(caseUrl,"_blank","top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no");
    }
	
	<%-- 关闭窗口 --%>
	function closeUserWindow() {
		$('#guSunWindow').window('close');
	}
	
	function addReplaceOrRePair(){
		var addreplace = $("tr[name=addreplace]");
		var replaceProjects = $("input[name=replaceProjects]");
		var replaceProjectsMoney = $("input[name=replaceProjectsMoney]");
		for(var i = 1; i < addreplace.length; i ++){
			var replaceProject = replaceProjects[i];
			var replaceProjectMoney = replaceProjectsMoney[i];
			if(($(replaceProject).val() == null || $(replaceProject).val() == '') && ($(replaceProjectMoney).val() == null || $(replaceProjectMoney).val() == '') ){
				$.messager.alert('提示信息',"请完整填写第"+(i+1)+"条更换项目",'info');
				return false;
			}
		}
		
		
		var addrepair = $("tr[name=addrepair]");
		var repairProjects = $("input[name=repairProjects]");
		var repairProjectsMoney = $("input[name=repairProjectsMoney]");
		for(var i = 1; i < addrepair.length; i ++){
			var repairProject = repairProjects[i];
			var repairProjectMoney = repairProjectsMoney[i];
			if(($(repairProject).val() == null || $(repairProject).val() == '') && ($(repairProjectMoney).val() == null || $(repairProjectMoney).val() == '') ){
				$.messager.alert('提示信息',"请完整填写第"+(i+1)+"条修理项目",'info');
				return false;
			}
		}
		
	}
	
	//更换项目添加行
	function replaceProject(){
		if($("tr[name=addreplace]").length >= 5){
			$.messager.alert('提示信息',"最多只能存在5条更换项目",'info');
			return false;
		}
		$("#repairProject").before("<tr name='addreplace' >"+
		"<td>&nbsp;</td>"+
		"<td><input class='input_w w_30 easyui-validatebox' required='true' name='replaceProjects'  value='' ></td>"+
		"<td>&nbsp;</td>"+
		"<td><input class='input_w w_30 easyui-validatebox' validType='intOrFloat' required='true' name='replaceProjectsMoney'  value='' > "+
		"<input type=button class=button_ty onclick=replaceDelete(this) value='删除' /></td>"+
		"</tr>");
	}
	//修理项目添加行
	function repairProject(){
		if($("tr[name=addrepair]").length >= 5){
			$.messager.alert('提示信息',"最多只能存在5条修理项目",'info');
			return false;
		}
		$("#subut").before("<tr name='addrepair' >"+
		"<td>&nbsp;</td>"+
		"<td><input class='input_w w_30 easyui-validatebox' required='true' name='repairProjects'  value='' ></td>"+
		"<td>&nbsp;</td>"+
		"<td><input class='input_w w_30 easyui-validatebox' validType='intOrFloat' required='true' name='repairProjectsMoney'  value='' > "+
		"<input type=button class=button_ty onclick=repairDelete(this) value='删除' /></td>"+
		"</tr>");
	}
	function replaceDelete(j){
		$(j).parent().parent().remove();
	}
	
	function repairDelete(k){
		$(k).parent().parent().remove();
	}
	</script>
</body>
</html>