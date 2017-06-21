<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<title>修改图片fileName</title>

</head>

<body>
	<input type="button" id="btn" onclick="test()" value="启动"/>
	每次执行条数：<input type="text" id="pageSize" >		<br>
图片总数${count }   									<br>
修改链接:/qp/qpticpicture/updatePicFileName.do			<br>
需要参数：page , pageSize								<br>
用处        ： sql ... limit page, pageSize					<br>
<div id="div1">
	</div>
</body>
<script type="text/javascript">
var pageNo = 0, pageSize = 0, index = 0;

	var successCallBack = function(result){
		$("#div1").append(result.msg + "<br>");
		pageNo ++;
		if(pageNo < index ){
			updateFile(successCallBack, errorCallBack, pageNo , pageSize);
		}
	};
	
	var errorCallBack = function(result){
		$("#div1").append(result.msg + "<br>");
	};
	var test = function () {
		if($("#pageSize").val() == undefined || $("#pageSize").val() == ''){
			alert("请输入每次执行条数");
			return;
		}
		$("#btn").attr('disabled', 'disabled');
		pageSize = $("#pageSize").val();
		if ("${count }" % pageSize) {
			index = "${count }"/pageSize + 1;
		} else {
			index = "${count }"/pageSize;
		}
		$("#div1").append("需要执行:" + index + "次<br>");
		updateFile(successCallBack, errorCallBack, pageNo, pageSize); 
	}
	
	function updateFile(successCallBack, errorCallBack, pageNo , pageSize){
		$.ajax({
			url:contextRootPath+"/qp/qpticpicture/updatePicFileName.do",
			data:{
				page:pageNo,
				pageSize:pageSize
			},
			dataType:"json",
// 			async : false,
			success : successCallBack,
			error: errorCallBack
		});
	}
	
	
</script>
</html>
