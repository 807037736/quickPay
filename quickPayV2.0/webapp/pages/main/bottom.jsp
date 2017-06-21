<%@ include file="/common/taglibs.jsp"%>
<body class="metro">

	<div class="container">
		<nav class="navigation-bar fixed-bottom bg-darkCyan">
			<nav class="navigation-bar-content">
				<item class="element col-sm-4 col-xs-4 text-center" href="tel:95518">
				<i class="icon-phone"> </i> </item>
				<item class="element  col-sm-4 col-xs-4  text-center"
					href="${ctx }/pages/main/index.jsp?param=<%=request.getParameter("param")%>">
				<i class="icon-home"> </i> </item>
				<item class="element  col-sm-4 col-xs-4 text-center" onClick="queryMsg()"> <i
					class="icon-mail"> </i> <span class="badge "
					style="background-color: red" id="msgCount"></span></item>
			</nav>
		</nav>
	</div>

</body>
<script>
function queryMsg(){
	var param1 = document.getElementById("param1").value;
	var url = contextRootPath 
	+ "/wx/message/queryMsg.do?"
	+"openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6"
	+"&param="+ param1 ;
	window.location=encodeURI(url);
	
} 
</script>