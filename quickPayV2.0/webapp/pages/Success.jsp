<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@include file="/common/meta_css.jsp"%>
<%@include file="/common/meta_js.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#close")
				.click(
						function() {
							try {
								//alert($("#returnUrl").val());
								if($("#returnUrl").val() != "" && $("#returnUrl").val() != undefined) {
									window.location = contextRootPath + $("#returnUrl").val();
								}else if (parent.window != null
										&& parent.window != undefined) {
									if (parent.window._pageClose) {
										parent.window._pageClose();
									} else {
										parent.window.close();
									}
								}else {
									window.close();
								}
							} catch (E) {

							}
							if (parent.window.opener != undefined
									&& parent.window.opener.editRecordOverCustom != undefined) {
								parent.window.opener.editRecordOverCustom();
							}
						});

	});
</script>
</head>
<body>
	<input type="hidden" name="returnUrl" value="${returnUrl}"
		id="returnUrl" />
	<table class=common align=center>
		<tr>
			<td align="center"><img
				src='${pageContext.request.contextPath}/pages/image/success.gif' />
			</td>
			<td class="common">保存成功!</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="button"
				class="button_ty" value="关   闭" id="close" ></td>
		</tr>
	</table>
</body>
</html>

