<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTTPLaw" %>
<html>
<head>
<title>法律法规增加</title>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
	
	<%-- 保存法规 --%>
	function executeSaveLawInfo(){
		if(!$('#addLawFm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
						$('#addLawFm').form('submit', {
							url: '${ctx}/qp/qpttplaw/add.do?lawName=' + $('#qpTTPLaw_lawType').combobox('getText'),
							success: function(msg) {
								try{
									//window.opener.$("#qpTICAccidentDriverLawId").combobox('reload','${ctx}/qp/qptcommon/getLawList.do');
																		//window.opener.$('#qpTTPLaw_lawName').val($('#qpTTPLaw_lawType').combobox('getText'));
									window.opener.$('#qpTICAccidentDriverLawId').combobox({
										url: '${ctx}/qp/qptcommon/getLawList.do',
										valueField: 'lawId',
										textField: 'lawWords'
								 	}); 
								}catch(e){}
								closeAddLawWinAndReLoad();
							}
						});
				}
			});
	    }	    
	}
	
	<%-- 关闭窗口 --%>
	function closeAddLawWinAndReLoad() {
		window.close();
	}
</script>
</head>

<body>
	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	<form name="addLawFm" id="addLawFm" action="/qp/qpttplaw" namespace="/qp/qpttplaw" method="post">
		<table class="fix_table" >
			<tr>
				<td class="bgc_tt short">
					法律法规内容:
				</td>
				<td class="long" colspan="4">
		        	<select id="qpTTPLaw_lawType" name="qpTTPLaw.lawType"  editable="false" class="input_w w_40 easyui-combobox">
		        		<option value="1">《中华人民共和国道路交通安全法》</option>
		        		<option value="2">《中华人民共和国道路交通安全法实施条例》</option>
		        		<option value="3">《湖南省实施【中华人民共和国道路交通安全】办法》</option>
		        	</select>
		        	第<input class='input_w w_15 easyui-validatebox' style="width: 30px;"  name="qpTTPLaw.lawItem" id="qpTTPLaw_lawItem" value="${qpTTPLaw.lawItem}"  required="true">		条
		        	第<input class='input_w w_15'  style="width: 30px;"  name="qpTTPLaw.lawSegment" id="qpTTPLaw_awSegment" value="${qpTTPLaw.lawSegment}">		款
		        	第<input class='input_w w_15' style="width: 30px;" name="qpTTPLaw.lawSection" id="qpTTPLaw_lawSection" value="${qpTTPLaw.lawSection}">		项
				</td>
			</tr>
			<tr>
				<td class="bgc_tt short">
					关键字描述:
				</td>
				<td class="long">
					<input class='input_w w_30 easyui-validatebox' name="qpTTPLaw.lawWords" id="qpTTPLaw_lawWords" value="${qpTTPLaw.lawWords}"  required="true">
				</td>
			</tr>
			<tr align="center">
				<td colspan=4 align="center">
						<input type="button" class="button_ty" onclick="executeSaveLawInfo();" ind="ind"  value="确定" />
						<input type="button" class="button_ty" onclick="closeAddLawWinAndReLoad();" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>