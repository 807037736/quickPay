<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>辅助功能</title>
		<%@ include file="/common/i18njs.jsp"%>
		<%@ include file="/common/meta_css.jsp"%>
		<%@ include file="/common/meta_js.jsp"%>
	</head>

	<body id="all_title">

		<div id="wrapper">
			<div id="container">
				<div id="crash_menu">
					<h2 align="center">
						辅助功能
					</h2>
				</div>
				<s:form name="fm" action="task" namespace="/saaPower" method="post">
					<table class="fix_table">
						

						<tr>
							<td colspan="3" align="center">
								<input type="button" class="button_ty" value="机构等级数据重置"
									onclick="rebuildCompanyGrade();">
							</td>
							<!--  
							<td colspan="3" align="center">
								<input type="button" class="button_ty" value="待定功能" onclick="">  
							</td>
							-->
						</tr>
					</table>
				</s:form>

			</div>
			<div id="content_navigation" class="query" align="center"></div>
			<div id="content" class="sort"></div>
			<div id="content_navigation" class="query" align="center"></div>
		</div>
	</body>
</html>
	
	<script type="text/javascript">
	//init on load
		
	</script>
	
	<script type="text/javascript">
		function rebuildCompanyGrade(){
			if(!confirm("本功能用于机构层级关系变更时重置机构等级数据，确定操作？")){
				return;
			}else{
				window.location.href="${ctx}/common/rebuildCompanyGrade.do?";
			}
		}
	</script>
