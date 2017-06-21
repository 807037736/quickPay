<%@ include file="/common/taglibs_mobile.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script type="text/javascript">
	
</script>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" href="${ctx}/widgets/jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.css">
<script src="${ctx}/widgets/jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.js"></script>
 
</head>
<body>
	<form id="fm" name="fm" method="post">
		<div class="right_detail_top">
			<h3>绑定提示</h3>
		</div>
		<div id="wrapper">
			<div id="container">
				<table class="fix_table">
					<c:if test="${errorMsg ==''}">
						<tr>
							<td class="bgc_tt_center short"><img
								src="${ctx}/pages/images/imgSuccess.gif"></td>
						</tr>
						<tr>
							<td class="bgc_tt_center short">恭喜您绑定成功！</td>
						</tr>
					</c:if>
					<c:if test="${errorMsg!=''}">
						<tr>
							<td class="bgc_tt_center short" style="color: red">${errorMsg}</td>
						</tr>
					</c:if>

					<tr>
						<td class="bgc_tt_center short"><input type="button"
							id="back" class="button_ty" value="返回"
							onclick="javascript:history.go(-2);history.back().reload();" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>