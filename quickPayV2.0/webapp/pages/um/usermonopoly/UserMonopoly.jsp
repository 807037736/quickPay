<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>送修码配置</title>
		<meta name="keyword" content="送修码配置页面" />
		<%@ include file="/common/meta_css.jsp"%>
	
	</head>
	
	<body>
	<div id="ctitle" class="right_detail_top"><h3>送修码配置</h3></div>
	
		<!-- <div id="tab" class="easyui-tabs"> -->
		<div class="margin-control">			
			<!-- <div title="车行推荐送修码"> -->
				<form id="fm_monopoly" name="fm_monopoly" method="post">
					<div id="wrapper">
						<div id="container">
						<table class="fix_table">
						
                   			<tr>
								<td class="bgc_tt short">机构：</td>
								<td class="long">
									<input " class="input_w w_15 codeselect_code" name="comCName" id='comCName' 
                   ondblclick="code_CodeQuery(this,'/common/queryAllSubCompany.do', '1,0', 'y');"
                   onkeyup="code_CodeSelect(this, '/common/queryAllSubCompany.do', '1,0', 'y');"
                   onchange="code_CodeChange(this, '/common/queryAllSubCompany.do', '1,0', 'y');" ><input name="comCode" id="comCode" class='input_w w_15 codeselect_name' type="text"  />
                   			</td>
							</tr>
							<tr>
								<td class="bgc_tt short">推荐送修码：</td>
								<td class="long">
									<input name="qtUserMonopoly.id.monopolyCode" class="input_w codeselect_code" "
						       			   ondblclick="code_CodeSelect(this,'/common/queryResource.do', '0,1', 'y', 'comCode=fm_monopoly[\'comCode\'].value');"
						       			   onkeyup   ="code_CodeSelect(this,'/common/queryResource.do', '0,1', 'y', 'comCode=fm_monopoly[\'comCode\'].value');"
						       			   onchange  ="code_CodeChange(this,'/common/queryResource.do', '0,1', 'y', 'comCode=fm_monopoly[\'comCode\'].value');"/><input name="qtUserMonopoly.monopolyName"  class='input_w w_15 codeselect_name' type="text" />
								</td>
								<td class="long"><input type="checkbox" name="qtUserMonopoly.flag" style="float:left;"/>设置为默认</td>
								<td class="long"><input type="button" class="button_ty" value="&nbsp;增加&nbsp;" onclick="monopolyAdd('${userCode}');"/></td>
							</tr>
						</table>
						</div>
					 </div>
	             </form>
	             <div class="margin-control">
	             <table id="monopolyResults"></table>
	             </div>
			<!-- </div> -->			
		</div>
		<%@ include file="/common/i18njs.jsp"%>
		<%@ include file="/common/meta_js.jsp"%>
		<script language="javascript" src="${ctx}/pages/um/usermonopoly/UserMonopoly.js"></script>
		<script language="javascript" >
		$(function(){
			var UserCode="${userCode}";
			$("#ctitle > h3").text(UserCode+"-用户推荐送修码配置");
			
			var query_action = contextRootPath + "/qt/myinfo/monopoly/queryUserMonopoly.do?userCode="+UserCode;
			$('#monopolyResults').datagrid({
				url          : query_action,
				//rownumbers   : true,
				nowrap       : true,
				striped      : true,
				remoteSort   : false,
				pageNumber   : 1,
				pageList     : [10,20,30],
				pagination   : true,
				singleSelect : true,
				sortName     : 'flag',
				sortOrder    : 'desc',
				fitColumns   : true,
				columns      : monopoly_contentColumnHeaders,
				toolbar      : monopoly_toolBar
			});
		});
		</script>
	</body>
</html>