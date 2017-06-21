<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PICC深圳人保财险</title>
<link href="${ctx}/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type='text/javascript' src="${ctx}/dwr/interface/DwrPushAction.js"></script>
<script type='text/javascript' src="${ctx}/widgets/jquery-easyui/jquery-1.8.0.min.js"></script>
<script type='text/javascript' src="${ctx}/pages/main/js/bootstrap.min.js"></script>
<script type='text/javascript' src="${ctx}/widgets/jquery-easyui/jquery.easyui.min.js"></script>
<link herf="${ctx}/pages/main/css/bootstrap.css" tyepe="text/css" rel="stylesheet"/>
<link href="${ctx}/widgets/jquery-easyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/widgets/jquery-easyui/themes/icon.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/widgets/jquery-easyui/demo/demo.css" type="text/css" rel="stylesheet"/>
</head>

<script>
		$(function(){
			$('#test').treegrid({
				title:'TreeGrid',
				iconCls:'icon-ok',
				width:1000,
				height:488,
				rownumbers: true,
				animate:true,
				collapsible:true,
				fitColumns:true,
				url:contextRootPath + '/qp/urbanroadmanagement/queryUrbanRoad.do',
				idField:'id',
				treeField:'name',
				showFooter:true,
				rowStyler:function(row){
					if (row.persons > 1){
						return 'background:#AAD684;color:#fff';
					}
				},
				
				   onBeforeExpand:function(row){
					   $(this).treegrid('options').url = contextRootPath + '/qp/urbanroadmanagement/queryChildrenJson.do';
				   },  
				  
				 columns:[[
	                {title:'名称',field:'name',width:250},
	                {field:'carpai',title:'车牌前缀',width:250,align:'right'},
					{field:'id',title:'行政区划编码',width:250,align:'right'},
	                {field:'order',title:'序号',width:250,align:'right'},
	                
					
				]] 
				
				
			});
		});
		
		
		function reload(){
         var node = $('#test').treegrid('getSelected');
       if (node){
                $('#test').treegrid('reload', node.code);
            } else {
                $('#test').treegrid('reload');
             } 
        }
        function getChildren(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                var nodes = $('#test').treegrid('getChildren', node.code);
            } else {
                var nodes = $('#test').treegrid('getChildren');
            }
            var s = '';
            for(var i=0; i<nodes.length; i++){
                s += nodes[i].code + ',';
            }
            alert(s);
        }
        function getSelected(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                alert(node.code+":"+node.name);
            }
        }
        function collapse(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                $('#test').treegrid('collapse', node.code);
            }
        }
        function expand(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                $('#test').treegrid('expand', node.code);
            }
        }
        function collapseAll(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                $('#test').treegrid('collapseAll', node.code);
            } else {
                $('#test').treegrid('collapseAll');
            }
        }
        function expandAll(){
            var node = $('#test').treegrid('getSelected');
            if (node){
                $('#test').treegrid('expandAll', node.code);
            } else {
                $('#test').treegrid('expandAll');
            }
        }
        function expandTo(){
            $('#test').treegrid('expandTo', '02013');
            $('#test').treegrid('select', '02013');
        }
        var codeIndex = 1000;
        function append(){
        	var node = $('#test').treegrid('getSelected');
        	node.id
        	
        	
            var data = [{
                id: null,
                name: null,
                col4: 'col4 data'
            }];
            var node = $('#test').treegrid('getSelected');
            $('#test').treegrid('append', {
                parent: (node?data.code:null),
                data: data
            });
            $('#test').treegrid('beginEdit',data.code);
        }
        function remove(){
        	var node = $('#test').treegrid('getSelected');
            if (node){
            	var nodelev = $('#test').treegrid('getLevel',node.id);
            	window.location.href=contextRootPath+'/qp/urbanroadmanagement/deleteNode.do?id='+node.id+'&nodelev='+nodelev;
            	
            } 
            
        }
        
        function deletePrompt(){
        	var node=$('#test').treegrid('getSelected');
        	if(node){
	        	if(node.id!=0){
	        		var nodelev = $('#test').treegrid('getLevel',node.id);
	        		document.getElementById("nodelev").value=nodelev;
		        	var url = contextRootPath + "/qp/urbanroadmanagement/prompt.do?nodeId="+node.id+"&nodelev="+nodelev;
		            var child=window .open(url=url,"child","height=90,width=110,status=yes,toolbar=no,menubar=no,location=no"); 
		            document.getElementById('nodeId').value=node.id;
		            
	        	}
        	}
        }
        function beginEdit(){
        	var node = $('#test').treegrid('getSelected');
        	if(node!=null){
        	if(node.id!=0){
        		var nodelev = $('#test').treegrid('getLevel',node.id);
        		var nodeId = node.id;
        		var str = nodeId.toString();
        		var nodeName=node.name;
        		
        		var len = str.length;
        		document.getElementById('type').value='edit';
        		
        		document.getElementById('nodelev').value=nodelev;
        		document.getElementById('nodeId').value=node.id;
        		document.getElementById('fatherNodeName').value=node.name;
        		if(nodelev==1){
        			document.getElementById('operateName').value='修改省份';
        			document.getElementById('addNodeName').value='省份名称';
		
        		}else if(nodelev==2){
        			document.getElementById('operateName').value='修改城市';
        			document.getElementById('addNodeName').value='城市名称';
        			
        		}else if(nodelev==3){
        			document.getElementById('operateName').value='修改区/县';
        			document.getElementById('addNodeName').value='区/县名称';
        			
        		}else if(nodelev==4){
        			document.getElementById('operateName').value='修改主干道';
        			document.getElementById('addNodeName').value='主干道名称';
        		}
        	var url = contextRootPath + "/qp/urbanroadmanagement/prepareEdit.do?id="+nodeId+"&nodelev="+nodelev+"&nodeName="+nodeName;
            var child=window .open(url=url,"child","height=600,width=700,status=yes,toolbar=no,menubar=no,location=no"); 
        	}else{
        		alert('难道您要造反？');
        		
        	}
        	}else{
        		alert('请选择您需要修改的行！');
        	}
        }
        
        function reLoad(){
        	location.reload();
        }
	</script>
</head>
<body>
	<h2>市区干道管理</h2>
	<div class="demo-info" style="margin-bottom:10px;width:975px">
		<div class="demo-tip icon-tip"></div>
		<div>
			<input type="button" class="button_ty" value="    修改     " onclick="beginEdit()">
			<input type="button" class="button_ty" value="    添加     " onclick="show_child()">
			<input type="button" class="button_ty" value="    删除    " onclick="deletePrompt()">
			<input type="button" class="button_ty" value="    重新加载    " onclick="reLoad()">
		</div>
	</div>
	<div>
		<table id="test" style="display:block"></table>
	</div>
	<script> 
function show_child() 
{ 
	var node = $('#test').treegrid('getSelected');
	
	if(node!=null){
	var nodelev = $('#test').treegrid('getLevel',node.id);
	var nodeId = node.id;
	var str = nodeId.toString();
	var len = str.length;
	var tianjia;
	var mingcheng;

	var fatherNodeName = node.name;
	if(nodelev!=4){
		
	document.getElementById('type').value='add';
	
	document.getElementById('nodeId').value=node.id;
	document.getElementById('fatherNodeName').value=node.name;
	document.getElementById('nodelev').value=nodelev;
	if(nodelev==0){
		document.getElementById('operateName').value='添加省份';
		document.getElementById('addNodeName').value='省份名称';
		
		tianjia='添加省份';
		mingcheng='省份名称';
	
	}else if(nodelev==1){
		document.getElementById('operateName').value='添加城市';
		document.getElementById('addNodeName').value='城市名称';

		tianjia='添加城市';
		mingcheng='城市名称';
		
	}else if(nodelev==2){
		document.getElementById('operateName').value='添加区/县';
		document.getElementById('addNodeName').value='区/县名称';
		tianjia='添加区/县';
		mingcheng='区/县名称';
	}else if(nodelev==3){
		document.getElementById('operateName').value='添加主干道';
		document.getElementById('addNodeName').value='主干道名称';
		tianjia='添加主干道';
		mingcheng='主干道名称';
	}
	
	var url = contextRootPath + "/qp/urbanroadmanagement/nullMethod.do?nodeId="+nodeId+"&fatherNodeName="+fatherNodeName+"&addModle="+tianjia+"&addNodeName="+mingcheng;
    var child=window .open(url=url,"child","height=600,width=700,status=yes,toolbar=no,menubar=no,location=no"); 
	}else{
		alert('主干道之下无法添加下级区划！');
	}
	}else{
		 alert('请选择您需要添加下级行政区划的行！');
	}
	

} 
</script> 

	<input type = "text" id = "nodeId" style="display:none">
	<input type = "text" id = "fatherNodeName" style="display:none">
	<input type = "text" id = "operateName" style="display:none">
	<input type = "text" id = "addNodeName" style="display:none">
	<input type = "text" id = "nodelev" style="display:none">
	<input type = "text" id = "type" style="display:none">
</body>
</html>
