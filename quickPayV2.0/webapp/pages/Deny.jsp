<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="/common/meta_css.jsp"%>
<script type="text/javascript">
	function closeMethod(){
		window.location.href = "/login.jsp";
	}
</script>
</head>
<body>
	<%
		response.addHeader("_forbidden", "true");
	%>
	
	<table class=common align=center width="100%">
	    <tr>
	      <td style="text-align:center;" ><img src='${ctx}/pages/image/error.png'
	          style='cursor:hand' alt='详细信息'></td>
	    </tr>
	    <tr>
	    	<td>
		    	<pre>
		    		<h3 style="text-align:center;font-size:18px;font-weight: bold;">您没有权限访问请求，请联系管理员！</h3>
		    		<h3 style="text-align:center;font-size:18px;font-weight: bold;">${errormessage}</h3>
<!-- 		    		<h3 style="text-align:center;font-size:18px;font-weight: bold;"><a href="/login.jsp"><span id="mmm">3</span>秒钟后跳转到登陆界面,点击我直接跳转</a></h3> -->
		    	</pre>
		    </td>
	    </tr>
	    <tr>
	    	<td style="text-align:center;"><input type="button"  value="关  闭 " onclick="closeMethod()" class="button_closed"></td>
	    </tr>
    </table>
	<script type="text/javascript">
// 		var countdown = function(){
// 			var mmm = document.getElementById("mmm").innerHTML;
// 			if(parseInt(mmm) > 0){
// 				document.getElementById("mmm").innerHTML = (parseInt(mmm) - 1) ;
// 				setTimeout(function(){
// 					countdown();
// 				}, 1000);
// 			}else{
// 				window.location.href = "/login.jsp";
// 			}
			
// 		}
		
// 		setTimeout(function(){
// 			countdown();
// 		}, 1000);
	</script> 
</body>
</html>
