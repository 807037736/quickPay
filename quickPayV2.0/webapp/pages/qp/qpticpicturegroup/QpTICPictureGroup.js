var query_action = contextRootPath + "/qp/qpticpicturegroup/query.do";
var queryResultTable = "QpTICPictureGroupTable";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}, {
	text : '删除',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		executeDelete();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		{
			field : 'groupId',
			title : '组号ID',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ getUrlByJson(row) + '\')">' + row.id.groupId + '</a>';
			}
		},
		{
			field : 'uploadTimeForHis',
			title : '上传时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'uploadUserCode',
			title : '上传用户代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'creatorCode',
			title : '创建人代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'insertTimeForHis',
			title : '创建时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'updaterCode',
			title : '修改人代码',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'operateTimeForHis',
			title : '修改时间',
			align : 'center',
			sortable : true
		}
			,	
		{
			field : 'validStatus',
			title : '有效状态',
			align : 'center',
			sortable : true
		}
				
		] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		title : "查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		onLoadError: function(result){
			alertErrorMsgForEasyUi(result);
		},
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
}
/* 修改 */
function prepareUpdate() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	var url = contextRootPath+"/qp/qpticpicturegroup/prepareUpdate.do?" + getUrlByJson(rows[0]);
	editRecord(url);
}
/* 新增 */
function prepareAdd() {
	editRecord(contextRootPath+'/qp/qpticpicturegroup/prepareAdd.do');
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/qp/qpticpicturegroup/view.do?" + params;
	editRecord(url);
}

/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/qp/qpticpicturegroup/delete.do?" + getUrlByJson(rows[0]);
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
					   },
					   error:function(result){
						   alertErrorMsgForEasyUi(result);
					   }
			});
		}
	});
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}

	function executeSave(){
		if(!$('#fm').form('validate')){
	    	return false; 
	    }else{
	    	$.messager.confirm('提示', '是否确认保存?', function(r){
				if (r){
				 	if($('#operateType').val()=='update'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticpicturegroup/update.do");
				 		$("#fm").submit();
				 	}else if($('#operateType').val()=='add'){
				 		$("#fm").attr("action",contextRootPath+"/qp/qpticpicturegroup/add.do");
				 		$("#fm").submit();
				 	}else{
				 		return false;
				 	}				 	
				}
			});
	    }	    
	}
	
	function getFile(f,fileindex) {
		//var size=f.size; if(size < 1024*1024){ //upload return ; } //to compress //upload }
		var file = f.files;
		var imageNames = '';
		for ( var i = 0; i < file.length; i++) {
			if (imageNames == '') {
				imageNames = file[i].name;
			} else {
				imageNames = imageNames + ';' + file[i].name;
			}
		}
		document.getElementById('imagesName' +fileindex ).value = imageNames;
	}
	
	var fileIndex = 0;
	function addTr(trObj) {

		$(trObj)
				.parent()
				.parent()
				.before(
						'<tr id="input'
								+ fileIndex
								+ '"><td >'
								+ ' <input type="button" data-toggle="tooltip" data-placement="right" value="浏览" onclick="file'+fileIndex+'.click()" class="btn btn-default btn-sm xb_panel" style=" height:25px;width:50px;"> <input type="file" style="display:none" name="images['
								+ fileIndex
								+ ']" id="file'
								+ fileIndex
								+ '"'
								+ ' 	accept="image/*" onchange="getFile(this,'+fileIndex+')" /> <input	readonly class="inputstyle"	type="text"   name="imagesName[' + fileIndex + ']" id="imagesName'+fileIndex+'" value=""/>'
								+ '</td>'
								+ '<td>'
								+ '<button type="button" class="btn btn-default btn-sm xb_panel"'
								+ '	data-toggle="tooltip" data-placement="right" ' 
								+ '	title="删除附件"  onclick="deleteTr('
								+ fileIndex
								+ ')">'
								+ '<span class="glyphicon glyphicon-remove"></span>	 删除'
								+ '</button>' + '</td>' + '</tr> ');
		fileIndex++;
	}
	
	function deleteTr(id) {
		if(id == 0) {
			$.alert({
                title: '提示信息',
                content: '至少一个上传位',
                confirmButton: 'okay',
                confirmButtonClass: 'xb_panel',
                animation: 'bottom',
                icon: 'fa fa-check',
                backgroundDismiss: false
            });
			//alert("至少一个上传位");
			return false;
		}
		var index;
		var idValue = id + 1;
		for ( var i = idValue; i < $("#files button[title='删除附件']").length; i++) {
			index = i - 1;
			if ($("#input" + i) != null && $("#input" + i).size() > 0) {
				$("#input" + i + " input[type='file']").attr("name",
						"images[" + index + "]");
				$("#input" + i + " input[type='file']").attr("id", "file" + index);
				$("#input" + i + " input[type='file']").attr("onchange", "getFile(this," + index + ")");
				$("#input" + i + " input[type='hidden']").attr("name",
						"imagesName[" + index + "]");
				$("#input" + i + " input[type='hidden']").attr("id",
						"imagesName[" + index + "]");
				$("#input" + i + "  button").attr("onclick",
						"deleteTr(" + index + ")");
				$("#input" + i + " input[type='button']").attr("onclick",
						"file" + index + ".click()");
				$("#input" + i + " input[class='inputstyle']").attr("id",
						"imagesName" + index);
				$("#input" + i + " input[class='inputstyle']").attr("name",
						"imagesName[" + index + "]");
				$("#input" + i).attr("id", "input" + index);
			}
		}
		$("#input" + id).remove();
		fileIndex--;
	}
	
	function doAjaxForm() {
		var imageArray = $('#uploadTable input[type="file"]');
		
		for(var i=0;i<imageArray.size();i++){
			var url =imageArray[i].value;
			var suffix=url.substring(url.lastIndexOf('.')+1).toLowerCase();
			if(imageArray[i].value==null||imageArray[i].value==""){
				$.alert({
	                title: '提示信息',
	                content: '请浏览需要上传的图片',
	                confirmButton: 'okay',
	                confirmButtonClass: 'xb_panel',
	                animation: 'bottom',
	                icon: 'fa fa-check',
	                backgroundDismiss: false
	            });
				//alert('请浏览需要上传的图片。');
				return false;
			}
			
			if(suffix!="jpg"&&suffix!="png"&&suffix!="jpeg"){
				//alert("非常抱歉目前本系统只开放了“JPG”，“PNG”格式的图片上传，如需帮助请联系管理员！");
				$.alert({
	                title: '提示信息',
	                content: '非常抱歉目前本系统只开放了“jpg”，“png"，”jpeg“格式的图片上传，如需帮助请联系管理员',
	                confirmButton: 'okay',
	                confirmButtonClass: 'xb_panel',
	                animation: 'bottom',
	                icon: 'fa fa-check',
	                backgroundDismiss: false
	            });
				//alert('非常抱歉目前本系统只开放了“jpg”，“png"，”jpeg“格式的图片上传，如需帮助请联系管理员！');
				return false;
			}
			
		}
		var opts = {
				lines: 12, // 画线数
				length: 11, // 每条线的长度
				width: 5, // 线厚度
				radius: 17, // 内圆半径
				corners: 1, // 角圆度 (0..1)
				rotate: 0, // 旋转偏移
				color: '#FFF', // #rgb or #rrggbb
				speed: 1, // 每秒轮
				trail: 60, // 余辉百分率
				shadow: false, // 是否渲染一个阴影
				hwaccel: false, // 是否使用硬件加速
				className: 'spinner1', // CSS类分配给纺织
				zIndex: 2e9, // The z-index (defaults to 2000000000)
				top: 'auto', // Top position relative to parent in px
				left: 'auto' // Left position relative to parent in px
			};
			var target = document.createElement("div");
			document.body.appendChild(target);
			var spinner = new Spinner(opts).spin(target);
			var overlay = iosOverlay({
				text: "上传中...",
				spinner: spinner
			});
			
			$("#uploadBody").addClass("btn disabled" );
			$('#fm').ajaxSubmit({ 
				type: 'post',  
	            url: contextRootPath + "/qp/qpticpicturegroup/uploadPictureGroup.do?groupId=" + $("#accidentGroupId").val(),
				success:function(data){  
					var obj = eval("(" + data + ")");
					if (obj.msg == 'error') {
						//alert("上传失败");
							overlay.update({
								icon: contextRootPath + "/pages/qp/qpticpicturegroup/images/cross.png",
								text: "上传失败"
							});
							window.setTimeout(function() {
								$("#uploadBody").removeClass("btn disabled" );
								overlay.hide();
								location.href='javascript:history.go(-1)';
							}, 2e3);
					}
					if (obj.msg == 'success') {
						//alert("上传成功！");
						overlay.update({
							icon: contextRootPath + "/pages/qp/qpticpicturegroup/images/check.png",
							text: "上传成功"
						});
						window.setTimeout(function() {
							$("#uploadBody").removeClass("btn disabled" );
							overlay.hide();
							location.href='javascript:history.go(-1)';
							//window.location.reload(true);
						}, 2e3);
					}
					//$( "#fm").resetForm();
	            },  
	            error: function(XmlHttpRequest, textStatus, errorThrown){  
						overlay.update({
							icon: contextRootPath + "/pages/qp/qpticpicturegroup/images/cross.png",
							text: "上传异常"
						});
						window.setTimeout(function() {
							$("#uploadBody").removeClass("btn disabled" );
							overlay.hide();
							location.href='javascript:history.go(-1)';
						}, 2e3);
	            }  
				//$('#uploadModal').css('display','none');
		     }); 
			

		return false;

	}
	
	/** form数据封装 */
	function formToString(formObj) {
		var allStr = "";
		if (formObj) {
			var elementsObj = formObj.elements;
			var obj;
			if (elementsObj) {
				for ( var i = 0; i < elementsObj.length; i += 1) {
					obj = elementsObj[i];
					if (obj.name != undefined && obj.name != "") {
						allStr += "&" + obj.name + "="
								+ encodeURIComponent(obj.value);
					}
				}
			} else {
				alert("没有elements对象！");
				return;
			}
		} else {
			alert("form不存在！");
			return;
		}
		return allStr;
	}
	
	
	