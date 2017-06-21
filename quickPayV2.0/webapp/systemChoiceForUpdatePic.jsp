<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>test</title>
<link rel="shortcut icon" href="${ctx}/favicon.ico" />
</head>
<body>
	<input type="button" onclick="test()" value="启动"/>
</body>
<script type="text/javascript">
	var test = function () {
		var pageNo = 1,
			pageSize = 1000;
		var ttt = 0;
		$.ajax({
			url:contextRootPath+"/qp/qpttpcase/prepareAdd.do",
			dataType:"json",
			async:false,
			success:function(result) {
				console.log(result);
			}
		});
		for (var i=0;i<10;i++) {
			console.log(1);
			$.ajax({
				url:contextRootPath+"/qp/qpttpcase/prepareAdd.do",
				data:{i:i},
				dataType:"json",
				async:false,
				success:function(result) {
					console.log(result);
				}
			});
		}
		
	}
</script>
</html>