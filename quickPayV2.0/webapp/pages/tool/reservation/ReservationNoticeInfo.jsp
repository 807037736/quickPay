<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<html>
<head>
<script src="${ctx}/pages/wf/common/formatter.js"></script>
<script type="text/javascript">
 function showDealingTaskInfo(){
	 var taskID = document.getElementById("reservationNotice.taskID").value;
	 var reservationStatus = document.getElementById("reservationNotice.reservationStatus").value;
		if(taskID==""||taskID==null){ 
//			return alert("该预约属于简单的预约提醒，并无关联的待办任务");
			$.messager.alert('信息提示', '该预约属于日程提醒，无关联的待办任务', 'error');
			return false;
		}
		else {
			 if(reservationStatus=="2"){
//				return alert("该预约关联的待办任务已经跃迁到别的操作员名下，无法查看");
				$.messager.alert('信息提示', '该预约关联的待办任务已经跃迁到别的操作员名下，无法查看', 'error');
				return false;
				
			}else{/*链接该预约对应的待办任务  */
				var editUrl = contextRootPath+'/wf/wfttaskdealing/showTaskDealingDetail.do?taskId='+taskID;
				editRecord(editUrl);
			}  
		}
}
</script>
</head>
<body>
<div class="right_detail_top"><h3>预约任务详情</h3></div>
    <div id="" title="设置预约提醒">
    <div id="wrapper">
	<form name="reservationUpdate" id="reservationUpdate">
			 <table class="fix_table">
			  <tr>
			     <td><input type ="hidden" name= "reservationNotice.id.reservationId" id = "reservationNotice.id.reservationId" value="${reservationNotice.id.reservationId}" ></td>
			     </tr>
			   <tr>
				   <td class="bgc_tt short">预约起始时间：</td>
			       <td class="long"><input name="reservationNotice.apointmentTimeStart" id="reservationNotice.apointmentTimeStart" 
			        value="<fmt:formatDate value='${reservationNotice.apointmentTimeStart}' pattern='yyyy-MM-dd hh:mm:ss'/>"
			        onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					class='easyui-validatebox  selectcode' style='width:227px' validType="datetime" type="text"></td>
				</tr>	
			     <tr>
				   <td  class="bgc_tt short">预约结束时间：</td>
			       <td class="long"><input name="reservationNotice.apointmentTimeEnd" 
					id="reservationNotice.apointmentTimeEnd" 
					 value="<fmt:formatDate value='${reservationNotice.apointmentTimeEnd}' pattern='yyyy-MM-dd hh:mm:ss'/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class='easyui-validatebox  selectcode' style='width:227px' validType="datetime" type="text"></td>
				</tr>	
			     <tr>
				   <td align="center" class="bgc_tt short">地点：</td>
				    <td class="long"><textarea class="w_30" name="reservationNotice.appointmentAddress" id="reservationNotice.appointmentAddress" 
				       rows="4">${reservationNotice.appointmentAddress}</textarea>
				   </td>
			    </tr> 
			     <tr>
					<td align="center" class="bgc_tt short">事件：</td>
					<td class="long"><textarea class="w_30" name="reservationNotice.appointmentRemark" id="reservationNotice.appointmentRemark" 
					    rows="4">${reservationNotice.appointmentRemark}</textarea>
					</td>
				</tr>
			    <tr>
			        <td  align="center" class="bgc_tt short">
			           <input type="hidden" name="reservationNotice.taskID" id="reservationNotice.taskID" 
			                  value ="${reservationNotice.taskID}">
			            <input type="hidden" name="reservationNotice.reservationStatus" id="reservationNotice.reservationStatus" 
			                   value ="${reservationNotice.reservationStatus}">
			        </td>
			     </tr> 
				 <tr> 
					<td colspan="2" align="center"><input type="button" class="button_ty" value="关联任务详情" onclick="showDealingTaskInfo();">
					<input type="button" class="button_ty" value="关闭"  onclick="window.close();">
					</td>
				</tr>
			 </table>
		</form>
		</div>		 
	</div>
</body>
</html>