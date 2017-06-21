<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/pages/main/css/iconFont.css">
<link rel="stylesheet" href="${ctx}/pages/main/css/metro-bootstrap.css">
<%-- <link rel="stylesheet" href="${ctx}/pages/main/css/bootstrap.min.css">
<script src="${ctx}/pages/phoneweb/js/jquery-1.10.1.min.js"></script> --%>
<body class="metro">
		<div class="container">
			<nav class="navigation-bar fixed-bottom bg-darkCyan">
				<nav class="navigation-bar-content">
					<item class="element col-sm-4 col-xs-4 text-center"  href="tel:95518">
					<i class="icon-phone">
					</i>   </item>
					<item class="element  col-sm-4 col-xs-4  text-center" 
					 href="${ctx }/pages/main/index.jsp?param=<%=request.getParameter("param")%>">
					 <i class="icon-home">
					</i> </item>

					<item class="element  col-sm-4 col-xs-4 text-center" 
					 href="javascript:history.back(-1)"><i
						class="icon-undo"> </i> </item>
				</nav>
			</nav>
		</div>
	<script>
	$(document).ready(function(){
		$('.element').unbind().bind('click', function() {
			if($(this).attr("href")!=null){
				window.location.href=$(this).attr("href");
			}
	    });
	});
	</script>
</body>
