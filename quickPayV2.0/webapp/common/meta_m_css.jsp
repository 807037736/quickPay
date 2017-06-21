<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  //for global use
  var contextRootPath = "${ctx}";

  function killerrors() {
	return true;
  }
  window.onerror = killerrors;
</script>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" type="text/css" href="${ctx}/mweb/mweb_css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/mweb/mweb_css/style.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/src/stylesheets/css.css" />

