

$(function(){
	showRoleCom();
});

/*加载机构角色树*/
function showRoleCom(){
	$('#rc').tree({
		animate:true,
		checkbox:true,
//		url: contextRootPath+'/um/umtrolecom/query.do?queryType=0',
		url: contextRootPath+'/um/umtrolecom/query.do?comCode=44030000&queryType=1',
		onBeforeExpand:function(node,param){
            $('#rc').tree('options').url = contextRootPath+'/um/umtrolecom/query.do?comCode='+node.id+'&queryType=1&start=1&limit=10';
        }
	});
}

