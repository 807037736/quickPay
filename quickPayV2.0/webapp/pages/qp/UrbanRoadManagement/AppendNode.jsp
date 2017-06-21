<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPTeam" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/qp/qpttpteam/QpTTPTeam.js"></script>
<script type="text/javascript">

function executeSave(){
	
	var nodeId =  document.getElementById("nodeId").value;
	if(nodeId){
		var addNodeName = document.getElementById("name").value;
		if(addNodeName){
			var order  = document.getElementById("order").value;
			var validStatus = document.getElementById("validStatus").value;
			var carpai = document.getElementById("carpai").value;
			var nodelev = document.getElementById("nodelev").value;
			var fatherid = document.getElementById("fatherid").value;
			//document.getElementById("fm").action=contextRootPath + "/qp/urbanroadmanagement/appendNode.do?&name="+addNodeName+"&validStatus="+validStatus+"&nodeId="+nodeId+"&carpai="+carpai+"&order="+order+"&nodelev="+nodelev+"&fatherid="+fatherid;
			//document.getElementById("fm").submit();
			var caseUrl = contextRootPath + "/qp/urbanroadmanagement/appendNode.do";
			var dataVal = "name="+addNodeName+"&validStatus="+validStatus+"&nodeId="+nodeId+"&carpai="+carpai+"&order="+order+"&nodelev="+nodelev+"&fatherid="+fatherid;
			$.ajax({
				type : "POST",
				url : caseUrl,
				async : false,
				cache : false, //缓存
				data: dataVal,
				success : function(result) {
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
			back();
		}else{
			alert('请输入行政区划名称！');
		}
	}else{
		alert('请输入正确的行政区划编码！');
	}
}

function back(){
	window.opener.location.href = window.opener.location.href;
	window.close();
}

function init(){
	var prtW = window.opener;
	var prtA;
	var fatherNodeName;
	var operateName;
	var addNodeName;
	var nodelev;
	var type;
	if(prtW!=null){
		prtA = prtW.document.getElementById("nodeId").value;
		document.getElementById("fatherid").value = prtA;
		
		operateName = prtW.document.getElementById("operateName").value;
		type = prtW.document.getElementById("type").value;
		if(type==('add')){
			fatherNodeName = prtW.document.getElementById("fatherNodeName").value;
			document.getElementById("fatherNodeName").value=fatherNodeName;
			$('#operateName').text(operateName);
			addNodeName = prtW.document.getElementById("addNodeName").value;
			$("#addNodeName").html(addNodeName);
			nodelev = prtW.document.getElementById("nodelev").value;
			document.getElementById("nodelev").value=nodelev;
			if(nodelev==0||nodelev==1){
				$("#carpaitext").html('<input class=\'input_w w_15\' name="carpai" id="carpai" value="${carpai}">');
			}else{
				$("#carpaitext").html('<input class=\'input_w w_15\' name="carpai" id="carpai" readonly="true" value="${carpai}">');
			}
			$("#idcode").html('<input class=\'input_w w_15\' name="nodeId" id="nodeId" placeholder="请输入该区划的编码">');
			
		}else{
			$('#operateName').text(operateName);
			addNodeName = prtW.document.getElementById("addNodeName").value;
			$("#addNodeName").html(addNodeName);
			$("#idcode").html('<c:if test="${type==0}"><input  type="text" class=\'input_w w_15\' name="nodeId" id="nodeId" value="${nodeId}"></c:if><c:if test="${type==2}"><input type="text" class=\'input_w w_15\' name="nodeId" id="nodeId" value="${qpTCOMProvince.provId}"></c:if><c:if test="${type==4}"><input type="text" class=\'input_w w_15\' name="nodeId" id="nodeId"  value="${qpTCOMCity.cityId}"></c:if><c:if test="${type==6}"><input type="text" class=\'input_w w_15\' name="nodeId" id="nodeId"  value="${qpTCOMDistrict.districtId}"></c:if><c:if test="${type==9}"><type="text" class=\'input_w w_15\' name="nodeId" id="nodeId"  value="${qpTCOMRoad.roadId}"></c:if>');
		}
		
		
	}
	
}

function executeUpdate(){
	var id = document.getElementById("nodeId").value;
	if(id){
		var name = document.getElementById("name").value;
		if(name){
			var carpai = document.getElementById("carpai").value;
			var validStatus = document.getElementById("validStatus").value;
			var order = document.getElementById("order").value;
			var nodelev = document.getElementById("nodelev").value;
		    //document.getElementById("fm").action= "${ctx}/qp/urbanroadmanagement/updateNode.do?&name="+name+"&validStatus="+validStatus+"&nodeId="+id+"&carpai="+carpai+"&order="+order+"&nodelev="+nodelev;
			//document.getElementById("fm").submit();
			var caseUrl = contextRootPath + "/qp/urbanroadmanagement/updateNode.do";
			var dataVal = "name="+name + "&validStatus="+validStatus  + "&nodeId="+id + "&carpai="+carpai + "&order="+order + "&nodelev="+nodelev;
			$.ajax({
				type : "POST",
				url : caseUrl,
				async : false,
				cache : false, //缓存
				data: dataVal,
		        dataType: "html",
				success : function(result) {
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
			/* setTimeout(back(),8000); */
			back();
		}else{
			alert('请输入行政区划名称！');
		}
	}else{
		alert('请输入正确的行政区划编码！');
	}
}

</script>
</head>
<body onload="init()">
	<form name="fm" id="fm">
		<div id="wrapper">
			<div id="container">
				<div id="crash_menu"  align=center>
					<span id="operateName"><font size=20>${addModle }</font></span>
				</div>
				<table class="fix_table">
							<tr>
	<td class="bgc_tt short">
		<sapn>上级行政区划</sapn>
	</td>
	  	<td  class="long">
	  		<c:if test="${type==0}">
				<input class='input_w w_15' name="fatherNodeName" id="fatherNodeName" readonly="true" value="${fatherNodeName}">
			</c:if>
			<c:if test="${type==2}">
				<input class='input_w w_15' name="fatherNodeName" id="fatherNodeName" readonly="true" value="中华人民共和国">
			</c:if>
			<c:if test="${type==4}">
				<input class='input_w w_15' name="fatherNodeName" id="fatherNodeName" readonly="true" value="${qpTCOMCity.creatorCode}">
			</c:if>
			<c:if test="${type==6}">
				<input class='input_w w_15' name="fatherNodeName" id="fatherNodeName" readonly="true" value="${qpTCOMDistrict.creatorCode}">
			</c:if>
			<c:if test="${type==9}">
				<input class='input_w w_15' name="fatherNodeName" id="fatherNodeName" readonly="true" value="${qpTCOMRoad.creatorCode}">
			</c:if>
		</td>
	<td class="bgc_tt short">
		<span id="addNodeName"></span>
	</td>
		<td class="long">
				<c:if test="${type==0}">
						<input class='input_w w_15' name="name" id="name" value="${name}">
				</c:if>
				<c:if test="${type==2}">
						<input class='input_w w_15' name="name" id="name" value="${qpTCOMProvince.provName}">
				</c:if>
				<c:if test="${type==4}">
						<input class='input_w w_15' name="name" id="name" value="${qpTCOMCity.cityName}">
				</c:if>
				<c:if test="${type==6}">
						<input class='input_w w_15' name="name" id="name" value="${qpTCOMDistrict.districtName}">
				</c:if>
				<c:if test="${type==9}">
						<input class='input_w w_15' name="name" id="name" value="${qpTCOMRoad.roadName}">
				</c:if>
		</td>					
		</tr>
							<tr>
	<td class="bgc_tt short">
		车牌前缀
	</td>
		<td class="long">
			<span id="carpaitext"></span>
			<c:if test="${type==2}">
						<input class='input_w w_15' name="carpai" id="carpai" value="${qpTCOMProvince.provVehicleNumPre}">
				</c:if>
				<c:if test="${type==4}">
						<input class='input_w w_15' name="carpai" id="carpai" value="${qpTCOMCity.cityVehicleNumPre}">
				</c:if>
				<c:if test="${type==6}">
						<input class='input_w w_15' name="carpai" id="carpai" value="" readonly="true">
				</c:if>
				<c:if test="${type==9}">
						<input class='input_w w_15' name="carpai" id="carpai" value="" readonly="true">
				</c:if>
		</td>

	<td class="bgc_tt short">
		有效状态
	</td>
		
			
				<c:if test="${type==0}">
				        <td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
								cssClass='input_w w_15'  value="${validStatus}" 
								name="validStatus" id="validStatus" />
						</td>
				</c:if>
				<c:if test="${type==2}">
				        <td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
								cssClass='input_w w_15'  value="${qpTCOMProvince.validStatus}" 
								name="validStatus" id="validStatus" />
						</td>
				</c:if>
				<c:if test="${type==4}">
						<td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
								cssClass='input_w w_15'  value="${qpTCOMCity.validStatus}" 
								name="validStatus" id="validStatus" />
						</td>
				</c:if>
				<c:if test="${type==6}">
				        <td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
								cssClass='input_w w_15'  value="${qpTCOMDistrict.validStatus}" 
								name="validStatus" id="validStatus" />
						</td>
				</c:if>
				<c:if test="${type==9}">
				       <td class="long"><ce:select list="#@java.util.LinkedHashMap@{'1':'是','0':'否'}"
							cssClass='input_w w_15'  value="${qpTCOMRoad.validStatus}" 
							name="validStatus" id="validStatus" />
					</td>
				</c:if>

		
	</tr> 	
					<tr>
						<td class="bgc_tt short">序号</td>
						<td>
						<c:if test="${type==0}">
								<input class='input_w w_15' name="order" id="order" >
							</c:if>
							<c:if test="${type==2}">
								<input class='input_w w_15' name="order" id="order" value="${qpTCOMProvince.provOrder}">
							</c:if>
							<c:if test="${type==4}">
								<input class='input_w w_15' name="order" id="order" value="${qpTCOMCity.cityOrder}">
							</c:if>
							<c:if test="${type==6}">
								<input class='input_w w_15' name="order" id="order" value="${qpTCOMDistrict.districtOrder}">
							</c:if>
							<c:if test="${type==9}">
								<input class='input_w w_15' name="order" id="order" value="${qpTCOMRoad.roadOrder}">
							</c:if>
						</td>
						<td class="bgc_tt short">行政区划编码</td>
						<td id="idcode">
							
						</td>
					</tr>		 
					<%-- <tr>
						<td style="text-align: center;" colspan="2">
							<c:if test="${type==0}">
								<input type="button" onclick="executeSave();" class="button_ty" value="保  存">
							</c:if>
							<c:if test="${type!=0}">
								<input type="button" onclick="executeUpdate();" class="button_ty"  value="保存修改">
							</c:if>
						</td>
					</tr> --%>
				</table>
				<div id="crash_menu"  align="center">
					<span id="operateName">
						<c:if test="${type==0}">
								<input type="button" onclick="executeSave();" class="button_ty" value="保  存">
							</c:if>
							<c:if test="${type!=0}">
								<input type="button" onclick="executeUpdate();" class="button_ty"  value="保存修改">
							</c:if>
					</span>
				</div>
			</div>
		</div>
		
		<input type="text" name="nodelev" id="nodelev" style="display:none" value="${nodelev}">
		<input type="text" name="fatherid" id="fatherid" readonly="true" style="display:none"  >
		
				
	</form>
	<br>
	<table id="QpTTPTeamTable"></table>
	<div>
		<div>*添加行政区划时，请在行政区划编码文本框中输入该区划对应的国标编码。</div>
		<div>特别注意：在添加主干道的行政区划编码的时候请使用其所在区/县的行政区划编码加上3位顺序编码的原则添加。</div>
		<div>在修改区划的信息时也可对区划编码进行修改，请保证编码的正确性！</div>
	</div>
	
</body>
</html>
