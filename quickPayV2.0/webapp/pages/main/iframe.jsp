<%@ include file="/common/taglibs.jsp"%>
<head>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
</head>
<body>
 	<iframe
		src="<%=request.getParameter("url")%>"
		id="iframepage" name="iframepage" frameBorder=0 scrolling=no
		width="100%" height="1600px" ></iframe>
		
	 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>
