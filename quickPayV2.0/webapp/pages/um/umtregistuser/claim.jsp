<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>深圳人保财险</title>

<link rel="shortcut icon" href="${ctx }/pages/image/picc_favicon.png">

<!-- Bootstrap core CSS -->
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css"
	rel="stylesheet">
<script src="${ctx }/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<link href="${ctx }/pages/um/umtregistuser/claim.css" rel="stylesheet">

</head>

<body>
	<form role="form" id="fm" method="post">
		<div class="navbar navbar-default navbar-fixed-top" role="navigation" style="background-color:white;">
			<div class="container">
				<div class="navbar-header">

					<a class="navbar-brand" href="#"><img
						src="${ctx }/pages/image/picc.png" class="img-thumbnail" /> 人保财险</a>
				</div>

			</div>
		</div>
		
		<section style="background: #efefe9;">
			<div class="container">
				<div class="row">
					<div class="board">
						<!-- <h2>Welcome to IGHALO!<sup>™</sup></h2>-->
						<div class="board-inner">
							<ul class="nav nav-tabs" id="myTab">
								<div class="liner"></div>
								<li class="active"><a href="#home" data-toggle="tab"
									title="welcome"> <span class="round-tabs one"> 客户<br>报案 </span>
								</a></li>

								<li><a href="#profile" data-toggle="tab" title="profile">
										<span class="round-tabs two"> 查勘<br>定损 </span>
								</a></li>
								<li><a href="#messages" data-toggle="tab"
									title="bootsnipp goodies"> <span class="round-tabs three">
										资料<br>收集	 </span>
								</a></li>

								<li><a href="#settings" data-toggle="tab" title="blah blah">
										<span class="round-tabs four"> 理算<br>核赔 </span>
								</a></li>

								<li><a href="#doner" data-toggle="tab" title="completed">
										<span class="round-tabs five"> 结案<br>赔款 </span>
								</a></li>

							</ul>
						</div>

						<div class="tab-content">
							<div class="tab-pane fade in active" id="home">

								<div class="alert alert-warning" style="margin-bottom: 0px">
									<a href="#" class="btn btn-xs btn-warning pull-left">报案号：</a> 
									<strong>&nbsp;&nbsp;${claimDetailVo.registno }</strong> 
								</div>

								<div class="alert alert-warning" style="margin-bottom: 0px">
									<a href="#" class="btn btn-xs btn-warning pull-left">车牌号：</a> 
									<strong>&nbsp;&nbsp;${claimDetailVo.licenseno }</strong>
								</div>
								
								<div class="alert alert-warning" style="margin-bottom: 0px">
									<a href="#" class="btn btn-xs btn-warning pull-left">出险日期：</a> 
									<strong>&nbsp;&nbsp;${claimDetailVo.damagedate }</strong> 
								</div>

								<div class="alert alert-warning" style="margin-bottom: 0px">
									<a href="#" class="btn btn-xs btn-warning pull-left">出险地址：</a> 
									<strong>&nbsp;&nbsp;${claimDetailVo.damageaddress }</strong>
								</div>
								
								<div class="alert alert-warning" style="margin-bottom: 0px">
									<a href="#" class="btn btn-xs btn-warning pull-left">出险原因：</a> 
									<strong>&nbsp;&nbsp;${claimDetailVo.damagename }</strong> 
								</div>
							</div>
							<div class="tab-pane fade" id="profile">
								 <div class="alert alert-warning" style="margin-bottom: 0px">
									  <ul class="list-group">
									  	<li class="list-group-item list-group-item-success"> 查勘信息</li>
									  	<li class="list-group-item">
										  	<c:choose>
												<c:when test="${claimDetailVo.checknature == null}">
									  			未查勘或案件已注销
									  			</c:when>
												<c:when test="${fn:trim(claimDetailVo.checknature) == '1'}">
									  			现场查勘
									  			</c:when>
												<c:otherwise>
											  	其它查勘
											  	</c:otherwise>
											</c:choose>
										</li>
									  	<li class="list-group-item"><c:choose>
												<c:when test="${claimDetailVo.damagecasecode == null}">
									  			案件已注销
									  			</c:when>
												<c:when test="${fn:trim(claimDetailVo.damagecasecode) == '01'}">
									  			单方事故
									  			</c:when>
												<c:when test="${fn:trim(claimDetailVo.damagecasecode) == '02'}">
									  			双方事故
									  			</c:when>
												<c:when test="${fn:trim(claimDetailVo.damagecasecode) == '03'}">
									  			多方事故
									  			</c:when>
												<c:otherwise>
											  	其他类型事故
											  	</c:otherwise>
										</c:choose></li>
									  </ul>

								</div>

								<div class="alert alert-warning" style="margin-bottom: 0px">
									<!-- <a href="#" class="btn btn-xs btn-warning pull-left">本车定损信息：</a>  -->
									<ul class="list-group">
										<li class="list-group-item list-group-item-success">本车定损信息</li>
										<c:if test="${claimDetailVo.claimLossList != null}">
											<c:forEach items="${claimDetailVo.claimLossList}" var="claimLoss" >
												<c:if test="${fn:trim(claimLoss.lossfeetype) == '05'&&(fn:substring(claimLoss.underwriteflag,0,1) == '1'||fn:substring(claimLoss.underwriteflag,0,1) == '3')}">
													<li class="list-group-item">定损中心：${claimLoss.defsite}</li>
													<li class="list-group-item">修理厂：${claimLoss.repairfactoryname}</li>
													<li class="list-group-item">定损价格：${claimLoss.sumlossfee}</li>
													<li class="list-group-item">定损时间：${claimLoss.underwriteenddate}</li>
												</c:if> 
											</c:forEach>
										</c:if>
										<c:if test="${claimDetailVo.claimLossList ==null}">
											<li class="list-group-item">待定损...</li>
										</c:if>
									<!-- 	<li class="list-group-item">定损中心：龙岗定损中心</li>
										<li class="list-group-item">修理厂：深圳市龙岗区龙岗镇环宇汽车修配厂</li>
										<li class="list-group-item">定损价格：300.00</li>
										<li class="list-group-item">定损日期：2014-02-05</li> -->
									</ul>
								</div>

								<div class="alert alert-warning" style="margin-bottom: 0px">
									<ul class="list-group">
										<li class="list-group-item list-group-item-success">三者车定损信息</li>
										<c:forEach items="${claimDetailVo.claimLossList}" var="claimLossOth" >
											<c:if test="${fn:trim(claimLossOth.lossfeetype) == '01'&&(fn:substring(claimLossOth.underwriteflag,0,1) == '1'||fn:substring(claimLossOth.underwriteflag,0,1) == '3')}">
												<li class="list-group-item">定损中心：${claimLossOth.defsite}</li>
												<li class="list-group-item">修理厂：${claimLossOth.repairfactoryname}</li>
												<li class="list-group-item">定损价格： ${claimLossOth.sumlossfee}</li>
												<li class="list-group-item">定损时间：${claimLossOth.underwriteenddate}</li>
											</c:if> 
										</c:forEach>
									</ul>
								</div>

							</div>
							<div class="tab-pane fade" id="messages">
								 <div class="alert alert-warning" style="margin-bottom: 0px">
									<ul class="list-group">
										<li class="list-group-item list-group-item-success">单方事故所需资料</li>
										<li class="list-group-item">索赔申请书</li>
										<li class="list-group-item">领款人和被保险人身份证复印件</li>
										<li class="list-group-item">标的车驾驶证/行驶证</li>
									</ul>
								</div>
							</div>
							<div class="tab-pane fade" id="settings">
							
								<div class="alert alert-warning" style="margin-bottom: 0px">
								<c:if test="${claimDetailVo.claimCompensateList == null}">
									资料收集中... 
								</c:if>
									<ul class="list-group">
										<c:forEach items="${claimDetailVo.claimCompensateList}" var="claimCompensate1" >
											<c:if test="${claimCompensate1.compensatetype == '1'&&(claimCompensate1.underwriteflag== '1'||claimCompensate1.underwriteflag == '3')}">
												<li class="list-group-item list-group-item-success">商业险实赔计算</li>
												<li class="list-group-item">计算书序号：${claimCompensate1.compensateno}</li>
												<li class="list-group-item">赔款金额：${claimCompensate1.sumpaid}</li>
												<li class="list-group-item">通过时间： ${claimCompensate1.underwriteenddate}</li>
											</c:if> 
										</c:forEach>
									</ul>
									<ul class="list-group">
										<c:forEach items="${claimDetailVo.claimCompensateList}" var="claimCompensate2" >
											<c:if test="${claimCompensate2.compensatetype == '2'&&(claimCompensate2.underwriteflag== '1'||claimCompensate2.underwriteflag == '3')}">
												<li class="list-group-item list-group-item-success">交强险实赔计算</li>
												<li class="list-group-item">计算书序号：${claimCompensate2.compensateno}</li>
												<li class="list-group-item">赔款金额：${claimCompensate2.sumpaid}</li>
												<li class="list-group-item">通过时间： ${claimCompensate2.underwriteenddate}</li>
											</c:if> 
										</c:forEach>
									</ul>
									<ul class="list-group">
										<c:forEach items="${claimDetailVo.claimCompensateList}" var="claimCompensate4" >
											<c:if test="${claimCompensate4.compensatetype == '4'&&(claimCompensate4.underwriteflag== '1'||claimCompensate4.underwriteflag == '3')}">
												<li class="list-group-item list-group-item-success">商业险费用计算</li>
												<li class="list-group-item">计算书序号：${claimCompensate4.compensateno}</li>
												<li class="list-group-item">赔款金额：${claimCompensate4.sumpaid}</li>
												<li class="list-group-item">通过时间： ${claimCompensate4.underwriteenddate}</li>
											</c:if> 
										</c:forEach>
									</ul>
									<ul class="list-group">
										<c:forEach items="${claimDetailVo.claimCompensateList}" var="claimCompensate5" >
											<c:if test="${claimCompensate5.compensatetype == '5'&&(claimCompensate5.underwriteflag== '1'||claimCompensate5.underwriteflag == '3')}">
												<li class="list-group-item list-group-item-success">交强险费用计算</li>
												<li class="list-group-item">计算书序号：${claimCompensate5.compensateno}</li>
												<li class="list-group-item">赔款金额：${claimCompensate5.sumpaid}</li>
												<li class="list-group-item">通过时间： ${claimCompensate5.underwriteenddate}</li>
											</c:if> 
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="tab-pane fade" id="doner">
								<c:if test="${claimDetailVo.claimPay != null}">
									<div class="alert alert-warning" style="margin-bottom: 0px">
										<a href="#" class="btn btn-xs btn-warning pull-left">收款人：</a> 
										<strong>&nbsp;&nbsp;${claimDetailVo.claimPay.payeename}</strong> 
									</div>
									<div class="alert alert-warning" style="margin-bottom: 0px">
										<a href="#" class="btn btn-xs btn-warning pull-left">赔款日期：</a> 
										<strong>&nbsp;&nbsp;${claimDetailVo.claimPay.endcasedate}</strong> 
									</div>
	
									<div class="alert alert-warning" style="margin-bottom: 0px">
										<a href="#" class="btn btn-xs btn-warning pull-left">结案日期：</a> 
										<strong>&nbsp;&nbsp;${claimDetailVo.claimPay.endcasedate}</strong>
									</div>
	
									<div class="alert alert-warning" style="margin-bottom: 0px">
										<a href="#" class="btn btn-xs btn-warning pull-left">总赔款金额：</a> 
										<strong>&nbsp;&nbsp;${claimDetailVo.claimPay.sumpaid}</strong>
									</div>
								</c:if>
								<c:if test="${claimDetailVo.claimPay == null}">
									<div class="alert alert-warning" style="margin-bottom: 0px">
										<strong>待结案...</strong> 
									</div>
								</c:if>
							</div>
							<div class="clearfix"></div>
						</div>

					</div>
				</div>
			</div>
		</section>

	</form>
<jsp:include page="/pages/main/bottomback2.jsp"></jsp:include>
</body>
<script type="text/javascript">
//全局变量，触摸开始位置  
var startX = 0, startY = 0;  
  
//touchstart事件  
function touchSatrtFunc(evt) {  
    try  
    {  
        //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  

        var touch = evt.touches[0]; //获取第一个触点  
        var x = Number(touch.pageX); //页面触点X坐标  
        var y = Number(touch.pageY); //页面触点Y坐标  
        //记录触点初始位置  
        startX = x;  
        startY = y;  

        var text = 'TouchStart事件触发：（' + x + ', ' + y + '）';  
        document.getElementById("result").innerHTML = text;  
    }  
    catch (e) {  
        alert('touchSatrtFunc：' + e.message);  
    }  
}  

//touchmove事件，这个事件无法获取坐标  
function touchMoveFunc(evt) {  
    try  
    {  
        //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  
        var touch = evt.touches[0]; //获取第一个触点  
        var x = Number(touch.pageX); //页面触点X坐标  
        var y = Number(touch.pageY); //页面触点Y坐标  

        var text = 'TouchMove事件触发：（' + x + ', ' + y + '）';  

        //判断滑动方向  
        if (x - startX != 0) {  
            text += '<br/>左右滑动';  
        }  
        if (y - startY != 0) {  
            text += '<br/>上下滑动';  
        }  

        document.getElementById("result").innerHTML = text;  
    }  
    catch (e) {  
        alert('touchMoveFunc：' + e.message);  
    }  
}  

//touchend事件  
function touchEndFunc(evt) {  
    try {  
        //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  

        var text = 'TouchEnd事件触发';  
        document.getElementById("result").innerHTML = text;  
    }  
    catch (e) {  
        alert('touchEndFunc：' + e.message);  
    }  
}  

//绑定事件  
function bindEvent() {  
    document.addEventListener('touchstart', touchSatrtFunc, false);  
    document.addEventListener('touchmove', touchMoveFunc, false);  
    document.addEventListener('touchend', touchEndFunc, false);  
}  

</script>
</html>