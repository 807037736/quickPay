var page_contentColumnHeaders = [ [
		
		{
			field : 'apointmentTimeStart',
			title : '开始时间',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'apointmentTimeEnd',
			title : '结束时间',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'noticeTime',
			title : '提醒时间',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'appointmentAddress',
			title : '地点',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'reservationStatus',
			title : '是否过期',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			},
			formatter : function(value, row, index) {
				if(!value)  return '---';
				else if(value=='0')  return '是';
				else if(value=='1') return '否';
				else return '是';
				
			}
		
		},
		{
			field : 'appointmentRemark',
			title : '事件',
			align : 'center',
			sortable : true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'taskCode',
			title : '绑定自动任务ID',
			align : 'center',
			sortable : true,
			hidden:true,
			sorter : function(a, b) {
				return (a > b ? 1 : -1);
			}
		},
		{
			field : 'reservationId',
			 title: '操作',
		        align: 'center',
		        sortable: true,
		        sorter: function(a, b) {
		            return (a > b ? 1 : -1);
		        },
		        formatter: function(value, row, index) {
		            return '<a style="cursor:pointer;text-decoration: underline;" href="javascript:void(0)" ' 
		            + ' onclick="prepareUpdateReservation(\'' 
		            + row.id.reservationId+'\',\''+row["reservationStatus"]+ '\')">修改</a>' 
		            + '    ' 
		            + '<a style="cursor:pointer;text-decoration: underline;" href="javascript:void(0)" ' 
		            + ' onclick="deleteReservation(\''+ row.id.reservationId+ '\')">删除</a>';
		        }
		}
		
		 ] ];

/* 预约查询 */
function queryReservation() {
	
// 时间  输入校验
	var startTimeSt_val = document.getElementById("querySelectStartTime").value;
	var startTimeEn_val = document.getElementById("querySelectEndTime").value;
	
   //验证开始时间起止区间
	if((startTimeSt_val != null && startTimeSt_val.trim() != "") && 
		(startTimeEn_val != null && startTimeEn_val.trim() != ""))
		if(startTimeSt_val>startTimeEn_val) {
//			alert("开始时间的起止区间错误，请修改之后再查询！");
			$.messager.alert('输入错误', '开始时间的起止区间错误，请修改之后再查询', 'error');
			return false;
		} 
	//多条件 查询
	var data = $("#reservationQuery").serialize();
	var send_Queryurl = contextRootPath+"/tool/reservation/queryByReservation.do?"+ data;
	
	$('#queryReservationTable').datagrid({
		url : send_Queryurl,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : null
	});
}

/*设置预约提醒*/
function addReservation(){
	var noticeTime = document.getElementById("reservationNotice.noticeTime").value;
	 var conment = document.getElementById("reservationNotice.appointmentRemark").value;
	// 时间  输入校验
		var saveTimeSt_val = document.getElementById("reservationNotice.apointmentTimeStart").value;
		var saveTimeEn_val = document.getElementById("reservationNotice.apointmentTimeEnd").value;
	//验证开始时间起止区间
		if((saveTimeSt_val == null || saveTimeSt_val.trim() == "") && 
				(saveTimeEn_val != null && saveTimeEn_val.trim() != "")){
//					return alert("请先输入<开始时间>的起始时间");
					$.messager.alert('输入错误', '请先输入<开始时间>的起始时间', 'error');
					return false;
			}
		
		if((saveTimeSt_val != null && saveTimeSt_val.trim() != "") && 
				(saveTimeEn_val == null || saveTimeEn_val.trim() == "")){
			    if(noticeTime != null && noticeTime.trim() != "" && noticeTime>saveTimeSt_val){
//				       alert("<提醒时间>请设置在<开始时间>之前！");
				       $.messager.alert('输入错误', '<提醒时间>请设置在<开始时间>之前', 'error');
				       return false;
			    }
			}
		
		if((saveTimeSt_val != null && saveTimeSt_val.trim() != "") && 
			(saveTimeEn_val != null && saveTimeEn_val.trim() != "")){
			if(saveTimeSt_val>saveTimeEn_val) {
//				alert("开始时间的起止区间错误，请修改！");
				 $.messager.alert('输入错误', '<开始时间>的起止区间错误，请修改', 'error');
				return false;
			} 
			if(noticeTime != null && noticeTime.trim() != ""&&noticeTime>saveTimeSt_val){
//				alert("<提醒时间>请设置在<开始时间>之前！");
				$.messager.alert('输入错误', '<提醒时间>请设置在<开始时间>之前', 'error');
				return false;
			}
		}
			
	//至少输入提醒时间
	if(noticeTime == null || noticeTime.trim() == "" || conment== null || conment.trim() == ""){
//				alert("请至少输入提醒时间和事件 ");
				$.messager.alert('输入错误', '请至少输入提醒时间和事件', 'error');
				return false;
			} 

	lock("addReservationBt");
	var data = $("#reservationSet").serialize();
	var setUrl = contextRootPath+"/tool/reservation/addReservation.do?"+ data;
	showProcess(true, '温馨提示', '正在进行本次操作，请稍候...');
	 $.ajax({
		   type: "POST",
		   url: setUrl,
		   success: function(result){
			   showProcess(false);
			   
			        var info = "系统失败";;
					var obj=eval("(" + result + ")");
					if(obj.result == 'success') {
						info = "预约设置成功！";
					}
					if(obj.result == 'fail') {
						info = "系统失败，预约设置失败！";
					}
				   setTimeout("dislock(\"addReservationBt\")",2000);
				$.messager.alert('提示消息', info, 'info');
			},
			error: function(msg) {
				showProcess(false);
				setTimeout("dislock(\"addReservationBt\")",2000);
				$.messager.alert('出现错误', msg, 'error');
			}
    });
}

/*准备修改页面  预约内容*/
function prepareUpdateReservation(reservationid,reservationStatus){
	if(reservationStatus!=1){
//		return alert("预约已过期，不可修改。");
		$.messager.alert('错误', '预约已过期，不可修改', 'error');
		return false;
	}
	 var editUrl = contextRootPath + "/tool/reservation/prepareUpdateReservation.do?reservationId=" + reservationid;
	    editRecord(editUrl);
}
/*修改  预约内容*/
function updateReservation(){
	// 时间  输入校验
	var noticeTime = document.getElementById("reservationNotice.noticeTime").value;
	var conment = document.getElementById("reservationNotice.appointmentRemark").value;
	var saveTimeSt_val = document.getElementById("reservationNotice.apointmentTimeStart").value;
	var saveTimeEn_val = document.getElementById("reservationNotice.apointmentTimeEnd").value;
	//验证开始时间起止区间
	if((saveTimeSt_val == null || saveTimeSt_val.trim() == "") && 
			(saveTimeEn_val != null && saveTimeEn_val.trim() != "")){
//				return alert("请先输入<开始时间>的起始时间");
				$.messager.alert('输入错误', '请先输入<开始时间>的起始时间', 'error');
				return false;
		}
	
	if((saveTimeSt_val != null && saveTimeSt_val.trim() != "") && 
			(saveTimeEn_val == null || saveTimeEn_val.trim() == "")){
		    if(noticeTime != null && noticeTime.trim() != ""&&noticeTime>saveTimeSt_val){
//			       alert("<提醒时间>请设置在<开始时间>之前！");
			       $.messager.alert('输入错误', '<提醒时间>请设置在<开始时间>之前', 'error');
			       return false;
		    }
		}
	
	if((saveTimeSt_val != null && saveTimeSt_val.trim() != "") && 
		(saveTimeEn_val != null && saveTimeEn_val.trim() != "")){
		if(saveTimeSt_val>saveTimeEn_val) {
//			alert("开始时间的起止区间错误，请修改！");
			$.messager.alert('输入错误', '开始时间的起止区间错误，请修改', 'error');
			return false;
		} 
		if(noticeTime != null && noticeTime.trim() != ""&&noticeTime>saveTimeSt_val){
//			alert("<提醒时间>请设置在<开始时间>之前！");
			$.messager.alert('输入错误', '<提醒时间>请设置在<开始时间>之前', 'error');
			return false;
		}
	}
		
			
	//至少输入提醒时间
	if(noticeTime == null || noticeTime.trim() == "" || conment== null || conment.trim() == ""){
//				alert("请至少输入提醒时间和事件 ");
				$.messager.alert('输入错误', '请至少输入提醒时间和事件', 'error');
				return false;
			} 
	
	var data = $("#reservationUpdate").serialize();
	var setUpdateUrl = contextRootPath+"/tool/reservation/updateReservation.do?"+ data;
//	alert(setUpdateUrl);
	showProcess(true, '温馨提示', '正在进行本次操作，请稍候...');
	 $.ajax({
		   type: "POST",
		   url: setUpdateUrl,
		   success: function(result){
			   showProcess(false);
			       var info = "修改失败！";
				   var obj=eval("(" + result + ")");
					if(obj.result == 'success') {
						info = "修改成功！";
					}
					if(obj.result == 'fail') {
						info = "系统失败，修改失败！";
					}
				$.messager.alert('提示消息', info, 'info');
				//刷新页面列表
				 $('#queryReservationTable').datagrid("reload");
			},
			error: function(msg) {
				showProcess(false);
				$.messager.alert('出现错误', msg, 'error');
			}
    });
}

/*删除  预约内容*/
function deleteReservation(value){
	
	 var deleteUrl = contextRootPath + "/tool/reservation/deleteReservation.do?reservationId=" + value;
//	alert(deleteUrl);
	 showProcess(true, '温馨提示', '正在进行本次操作，请稍候...');
		 $.ajax({
			   type: "POST",
			   url: deleteUrl,
			   success: function(result){
				      showProcess(false);
				       var info = "删除失败！";
					   var obj=eval("(" + result + ")");
						if(obj.result == 'success') {
							info = "删除成功！";
						}
						if(obj.result == 'fail') {
							info = "系统失败，删除失败！";
						}
					$.messager.alert('提示消息', info, 'info');
					//刷新页面列表
					$('#queryReservationTable').datagrid("reload");
				},
				error: function(msg) {
					showProcess(false);
					$.messager.alert('出现错误', msg, 'error');
				}
	    });
}

/*进度条显示*/	
function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg,
		text:''
	});
}	




