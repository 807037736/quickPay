<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" src="${ctx}/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/pages/portal/js/ui/jquery-ui.min.js"></script>

<%-- <script language="javascript" src="${ctx}/pages/portal/js/jquery-1.7.1.min.js"></script>
<script language="javascript" src="${ctx}/pages/portal/js/jquery-fallr-1.3.pack.js"></script> --%>
	<script type="text/javascript" src="${ctx}/widgets/jquery-easyui/jquery.easyui.min.js"></script>
<%-- <script language="javascript" src="${ctx}/pages/portal/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${ctx}/pages/portal/js/ui/ui.core.min.js"></script>
<script type="text/javascript" src="${ctx}/pages/portal/js/ui/ui.sortable.min.js"></script> --%>

<link rel="stylesheet" type="text/css" href="${ctx}/widgets/jquery-easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/widgets/jquery-easyui/demo/demo.css" />
<link href="${ctx}/pages/portal/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/pages/portal/css/jquery-fallr-1.3.css" rel="stylesheet" type="text/css" />	
<link rel="stylesheet" type="text/css" href="${ctx}/widgets/jquery-easyui/themes/icon.css"/>
<script language="javascript" src="${ctx}/pages/portal/js/init.js"></script>
<style type="text/css">
.tabs-wrap{ width:100% !important}
.icon-add,.icon-remove{margin: 5px 0 0 5px !important;}
.tabs-tool{height:42px !important;}
</style>
<script>	
//var code=findWfTPortlettemplate();
/*  $(document).ready(function () {	
	
	var topmenu="${portalTopResult}";
	$("#baseTop").append(topmenu);
	var zhou=${result};
 	var ding= zhou.appM;
 	if(ding==null || ding ==""){
 		document.getElementById('tt').style.display='none';
 	}
//	Jh.Tools.init(zhou);
	Jh.button.init(zhou);
//	Jh.fn.init(zhou);
//  Jh.Tab.init(zhou);
	Jh.Portal.init(zhou);
	//layoutFu(code);
	for (var portalid in ding){
		
			$('#tt').tabs('add',{
				id: portalid,
				title: ding[portalid]["title"],
				//content: "<iframe scrolling='yes' frameborder='0'  src="+ding[portalid]["url"]+" style='width:100%;height:100%;'></iframe>",
	//			href: ding[portalid]["url"],
				closable: false
			});
	};
	
	$(button).hide();
	Jh.button._bindEdit($(editbt));// 绑定按钮事件
	Jh.button._bindSave($(savebt));
	 $('#tt').tabs({
		onSelect : function(title,index) {
			var tab = $('#tt').tabs('getSelected'); 
			var i=0;
			for (var portalid in ding){
				if(i==index){
					 $("#tt").tabs('update',{  
					        tab:tab,  
					        options:{content:"<iframe scrolling='auto' frameborder='0'  src="+ding[portalid]["url"]+" style='width:100%;height:100%;'></iframe>"},
					 		closable:false 
					         });  
					break;
				}
				i++;
			}
			}
	}); 
	 $('#tab-tools').hide();
	 
	
}); */
 </script>
	<script type="text/javascript">  
		function addPanel(){
			var url = contextRootPath + '/portal/userAdd.do';
			var handle = window.showModalDialog(url,window,"dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:350px;dialogHeight:450px;resizable:yes");
			var	left = $("#"+Jh.Layout.location.left).sortable('toArray'),
		    right = $("#"+Jh.Layout.location.right).sortable('toArray'),
		    lens = $('#tt').tabs('tabs').length;
			var middle=[];
			for(var i=0;i<lens;i++){
				var tabId=$('#tt').tabs('tabs')[i].panel('options').id;
				middle[i] = tabId;
			}
			if(handle!=null){
				for(var i=0;i<left.length;i++)
					if(handle['key']==left[i]){
						alert("请不要重复添加！");
						return false;
					}
				for(var i=0;i<right.length;i++)
					if(handle['key']==right[i]){
						alert("请不要重复添加！");
						return false;
					}
				for(var i=0;i<middle.length;i++)
					if(handle['key']==middle[i]){
						alert("请不要重复添加！");
						return false;
					}	
				$('#tt').tabs('add',{
					id: handle['key'],
					title: handle['name']['title'],
					content: "<iframe scrolling='yes' frameborder='0'  src="+handle['name']['url']+" style='width:100%;height:100%;'></iframe>",
					closable: true
				});
			}
		}
		function removePanel(){
			var tab = $('#tt').tabs('getSelected');
			if (tab){
				var index = $('#tt').tabs('getTabIndex', tab);
				$('#tt').tabs('close', index);
			}
		}
	</script>
  
<title>首页</title>
</head>
<body>
<form name="fm" id="fm">
   <c:choose>
        <c:when test="${SessionUser.userSort != '07' && SessionUser.userSort != '08' }">
		<div class="detail_head detail_cent" > 
			<!-- <ul id="baseTop">
			</ul> -->
			 <h1 style="font-size: 20px;color: #4075aa">欢迎进入${serverName}</h1> 
			<!-- <font size="50%" color="red" >欢迎进入气象风险控制系统</font> -->
		</div> 
	    </c:when> 
		<c:when test="${SessionUser.userSort == '07' || SessionUser.userSort == '08' }">
			<div class="detail_head2  detail_cent2" > 
			<!-- <ul id="baseTop">
			</ul> -->
			
			 <img  align="bottom" height="185" width="802" src="${ctx}/pages/portal/images/gsjj_index.jpg" />
			
			<!-- <ul id="baseTop">
			</ul> -->
			<!--  
			 <h1 style="font-weight:bold;font-family:宋体;font-size: 20px;color: white">欢迎进入湖南省高速公路交通警察局长沙支队</h1> 
			 <h1 style="font-weight:bold;font-family:宋体;font-size: 20px;color: white">道路交通事故快处快赔中心</h1> 
			--> 
			<!-- <font size="50%" color="red" >欢迎进入气象风险控制系统</font> -->
		</div> 
		</c:when> 
	  </c:choose>
	
	<br/>
	<!-- <div id='button'>
		<table class="form-list">
		<tr>
		<td class="single">
		<a class='button b' href='#' id="addbt">添加显示模块</a>
		<a class='button b' href='#' id="savebt">保存配置</a>
		</td>
		</tr>
		</table>
	</div>  
	<div id="tab-tools" >
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPanel()" style="margin: 5px 0 0 5px;"></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removePanel()" style="margin: 5px 0 0 5px;"></a>
	</div>
  	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:800px;height:500px;margin:10px auto 0;border:none;">	
  	</div> -->
  	<center>
  		<c:choose>
  			<c:when test="${null == title }">
			  	<div class="easyui-layout" style="width:805px;height:505px;">
					<div id="center" region="center" title="系统公告"   style="height: 20px;">
						<p align="center" style="color:#4060aa ;font-size: 20px;" >暂无公告</p>
					</div>
				</div>
			</c:when>
			<c:when test="${null != title }">
			  	<div class="easyui-layout" style="width:805px;height:505px;"  word-wrap="break-word;" white-space="pre;" >
					<div id="center" region="center" title="系统公告"   style="height: 20px;">
						<p align="center" style="color:#4060aa ;font-size: 20px;" >${title }</p>
						<c:forEach items="${contents }" var="contents">
							<p style="color:#4075aa ;font-size: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${contents}</p>
						</c:forEach>
						
					</div>
				</div>
			</c:when>
		</c:choose>
	</center>
	
</form>	
</body>
</html>