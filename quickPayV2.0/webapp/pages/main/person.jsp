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
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<script src="${ctx }/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>

<link href="${ctx }/pages/um/umtregistuser/registuser.css"
	rel="stylesheet">


<script src="${ctx }/pages/um/umtregistuser/person.js"></script>


</head>

<body>
	<form role="form" id="fm" method="post">

		<input type="hidden" id="param" value="${param1 }" />
		<input type="hidden" id="policynoList" value="" />
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><img
						src="${ctx }/pages/image/picc.png" class="img-thumbnail" /> 人保财险</a>
				</div>
			</div>
		</div>


		<div class="jumbotron">
			<p align="center">
				<img class="img-rounded" alt=""
					src="${ctx }/pages/image/comboimage.png">
			</p>
		</div>

		<div class="container">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li role="presentation" class="active"><a href="#tab1"
					role="tab" data-toggle="tab">个人信息</a></li>
				<li role="presentation"><a href="#tab2" role="tab"
					data-toggle="tab">我的保单</a></li>
				<li role="presentation"><a href="#tab3" role="tab"
					data-toggle="tab">我的理赔</a></li>
				<!-- <li role="presentation"><a href="#tab4" role="tab"
					data-toggle="tab">专属服务</a></li> -->
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<!-- 个人信息 -->
				<div role="tabpanel" class="tab-pane active" id="tab1">
					<ul class="list-group">
						<li class="list-group-item">姓名：<span id="username">${username }</span></li>
						<li class="list-group-item">证件号：<span id="bindvalue">${identifyno}</span></li>
					</ul>
				</div>
				<!-- 我的保单 -->
				<div role="tabpanel" class="tab-pane" id="tab2">
					<ul class="list-group" id="tab2Ul">
					</ul>

					<!-- Button trigger modal -->

					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" data-dismiss="modal">
								<div class="modal-header">
									<h4 class="modal-title"><span id="policynoTitle"></span></h4>
								</div>
								<div class="modal-body">
									<ul class="list-group">
										<li class="list-group-item list-group-item-success">起保日期：<span id="startDate"></span></li>
										<li class="list-group-item list-group-item-info">终保日期:<span id="endDate"></span></li>
										<!-- <li class="list-group-item list-group-item-warning">原保费：4500元</li> -->
										<li class="list-group-item list-group-item-danger">总保费：<span id="sumPremium"></span>元</li>
									</ul>

									<div class="panel  panel-primary">
										<div class="panel-heading">
											<h3 class="panel-title">投保险别</h3>
										</div>
										<div class="panel-body" id ="itemKindDiv">
										</div>
									</div>

								</div>
								<div class="modal-footer" style="text-align: center">
									<button type="button" class="btn btn-default">关闭</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 我的理赔 -->
				<div role="tabpanel" class="tab-pane" id="tab3">
					<ul class="list-group" id="tab3Ul">
					</ul>

				</div>
				<!-- <div role="tabpanel" class="tab-pane" id="tab4">4</div> -->
			</div>

		</div>

		<!-- FOOTER -->
<!-- 		<div class="dropdown-header" align="center" style="margin-top: 2em;">
			<footer>
				<p style="line-heigth: 1em; white-space: normal;">
					<font style="color: gray">PICC(SZ)&nbsp;深圳人保财险版权所有©2014 </font> <br>
					<a href="http://www.miibeian.gov.cn/" target="_blank" id="ba">&nbsp;&nbsp;粤ICP备12029198号</a>
				</p>
			</footer>
		</div> -->
	</form>
 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var tab1Show = true;
	var tab2Show = false;
	var tab3Show = false;
	var tab4Show = false;
	var policyArr = new Array();
	var claimArr = new Array();
	$('#myTab a').click(function(e) {
		var target = e.target.hash;
		if (target == "#tab1"&&!tab1Show) {
		} else if (target == "#tab2"&&!tab2Show) {
			queryPolicy();
		} else if (target == "#tab3"&&!tab3Show) {
			queryClaim();
		} else if (target == "#tab4"&&!tab4Show) {

		}
	});
	
	
	/* 点击我的理赔列表显示详细理赔信息时触发	*/
	$('#tab3Ul li').live('click',function(){
		var serialid = this.getAttribute("serilid");
		var param = document.getElementById("param").value;
		var url = contextRootPath 
		+ "/um/umtuserbind/queryClaimDetail.do?"
		+"openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6"
		+"&param1="+ param
		+"&registno="+claimArr[serialid].registno
		+"&flag="+claimArr[serialid].flag
		+"&licenseno="+claimArr[serialid].licenseno
		+"&damagename="+claimArr[serialid].damagename
		+"&damagedate="+claimArr[serialid].damagedate
		+"&damageaddress="+claimArr[serialid].damageaddress;
		window.open(encodeURI(url));
	});
	
	/* 点击我的保单列表显示详细保单信息时触发	*/
	$('#myModal').on('show.bs.modal', function (e) {
		  var serilid = e.relatedTarget.getAttribute("serilid");
		  var policyObj = eval(policyArr[serilid]);
		  document.getElementById("policynoTitle").innerHTML = policyObj.policyNo;
		  document.getElementById("startDate").innerHTML = policyObj.startDate;
		  document.getElementById("endDate").innerHTML = policyObj.endDate;
		  var p = policyObj.biPremium==0?policyObj.ciPremium:policyObj.biPremium;
		  document.getElementById("sumPremium").innerHTML = p;
		  document.getElementById('itemKindDiv').innerHTML="";
		  var itemkindArr = policyObj.itemKind;
		  var bujiTitle = "不计免赔：";
		  var bujiPremium = 0;
		  for(var i=0;i<itemkindArr.length;i++){
			  if(itemkindArr[i].amount!=0){
				  var ul = document.createElement("ul");
				  ul.setAttribute("class", "list-group");
				  ul.setAttribute("name", "itemKindUl");
				  //创建1
				  var li1 = document.createElement("li");
				  li1.setAttribute("class", "list-group-item");
				  li1.innerHTML = itemkindArr[i].kindName;
				  ul.appendChild(li1);
				  //创建2
				  var li2 = document.createElement("li");
				  li2.setAttribute("class", "list-group-item");
				  li2.innerHTML = "保额:"+itemkindArr[i].amount;
				  ul.appendChild(li2);
				  //创建3
				  var li3 = document.createElement("li");
				  li3.setAttribute("class", "list-group-item");
				  li3.innerHTML = "保费:"+itemkindArr[i].premium;
				  ul.appendChild(li3);
				  
				  document.getElementById('itemKindDiv').appendChild(ul);
			  }else{
				  bujiPremium += itemkindArr[i].premium;
				  bujiTitle += itemkindArr[i].kindName.replace("不计免赔率（","").replace("）","")+"/";
			  }
		  }
		  if(bujiPremium!=0){
			  var ul = document.createElement("ul");
			  ul.setAttribute("class", "list-group");
			  ul.setAttribute("name", "itemKindUl");
			  //创建1
			  var li1 = document.createElement("li");
			  li1.setAttribute("class", "list-group-item");
			  li1.innerHTML = bujiTitle;
			  ul.appendChild(li1);
			  //创建2
			  var li2 = document.createElement("li");
			  li2.setAttribute("class", "list-group-item");
			  li2.innerHTML = "保费:"+bujiPremium;
			  ul.appendChild(li2);
			  
			  document.getElementById('itemKindDiv').appendChild(ul);
		  }
		});
	
	/*
	 * 获取保单
	 */
	function queryPolicy() {
		var param = document.getElementById("param").value;
		var url = contextRootPath + "/um/umtuserbind/queryPolicy.do";
		var data = "openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6&param1="
				+ param;
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			async : true,
			success : function(result) {
				var obj = eval("(" + result + ")");
				var resultCode = obj.head.resultCode;
				var policynoList = "";
				if (resultCode == '1') {
					tab2Show = true;
					policyArr = obj.body;
					for(var i=0;i<policyArr.length;i++){
						addElementLi("tab2Ul",i,policyArr[i].policyNo,policyArr[i].biPremium==0?policyArr[i].ciPremium:policyArr[i].biPremium);
						policynoList += policyArr[i].policyNo+",";
					}
					document.getElementById("policynoList").value = policynoList;
					return true;
				} else {
					//$('#tip_vcode').text('该手机号已经注册过!');
					return false;
				}
			}
		});
	}
	
	/*
	 * 获取理赔
	 */
	function queryClaim() {
		var param = document.getElementById("param").value;
		var policynoList = document.getElementById("policynoList").value;
		if(!tab2Show){
			queryPolicy();
		}
		var url = contextRootPath + "/um/umtuserbind/queryClaimList.do";
		var data = "openToken=3960CBF1F509928C06CB985A3DBBE08945D45FB6&param1="
				+ param+"&policynoList="+policynoList;
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			async : true,
			success : function(result) {
				var obj = eval("(" + result + ")");
				claimArr = obj.rows; 
				if (claimArr != '') {
					tab3Show = true; 
					for(var i=0;i<claimArr.length;i++){
						addClaimElementLi("tab3Ul",i,claimArr[i].licenseno,claimArr[i].reportdate,claimArr[i].claimstatus,claimArr[i].cancelflag);
					}
					return true;
				} else {
					//$('#tip_vcode').text('该手机号已经注册过!');
					return false;
				}
			}
		});
	}
	/**
	 * 创建保单列表
	 * 
	 * @param obj
	 * @returns
	 */
	function addElementLi(obj,serial, policyno, premium) {
		var ul = document.getElementById(obj);
		var li = document.createElement("li");
		li.setAttribute("class", "list-group-item");
		li.setAttribute("data-toggle", "modal");
		li.setAttribute("data-target", "#myModal");
		li.setAttribute("serilid", serial);

		li.innerHTML = policyno;
		var span = document.createElement("span");
		span.setAttribute("class", "badge");
		span.innerHTML = "￥"+premium;
		li.appendChild(span);
		ul.appendChild(li);
	}
	/**
	 * 创建理赔列表
	 * 
	 * @param obj
	 * @returns
	 */
	 
	function addClaimElementLi(obj,serial, licenseno, reportdate,claimstatus,cancelflag) {
		var ul = document.getElementById(obj);
		reportdate = reportdate.substr(0,10);
		var li = document.createElement("li");
		li.setAttribute("class", "list-group-item");
		li.setAttribute("serilid", serial);

		var h5 = document.createElement("h5");
		h5.innerHTML = licenseno;
		var br = document.createElement("br");
		var small = document.createElement("small");
		small.innerHTML = "报案日期："+reportdate;
		h5.appendChild(br);
		h5.appendChild(small);
		var span = document.createElement("span");
		if(cancelflag=="1"){
			span.setAttribute("class", "badge pull-right");
			span.innerHTML = "已注销>";
		}else{
			if(claimstatus=="1"){
				span.setAttribute("class", "badge alert-success pull-right");
				span.innerHTML = "已立案>";
			}else{
				span.setAttribute("class", "badge alert-danger pull-right");
				span.innerHTML = "办理中>";
			}
		}
		h5.appendChild(span);
		li.appendChild(h5);
		ul.appendChild(li);
	}
</script>
</html>