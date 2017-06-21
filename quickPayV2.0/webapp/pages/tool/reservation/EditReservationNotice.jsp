<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<html>
<head>
<script src="${ctx}/pages/wf/common/formatter.js"></script>
<script src="${ctx}/pages/tool/reservation/ReservationNotice.js"></script>	 
</head>
<body>
<div class="right_detail_top"><h3>修改预约任务</h3></div>
    <div id="" title="设置预约提醒">
    <div id="wrapper">
	<form name="reservationUpdate" id="reservationUpdate">
			 <table class="fix_table">
			  <tr>
			     <td><input type ="hidden" name= "reservationNotice.id.reservationId" id = "reservationNotice.id.reservationId" value="${reservationNotice.id.reservationId}" >
			     <input type ="hidden" name= "reservationNotice.taskCode" id = "reservationNotice.taskCode" value="${reservationNotice.taskCode}" >
			     <input type ="hidden" name= "reservationNotice.taskID" id = "reservationNotice.taskID" value="${reservationNotice.taskID}" >
			     </td>
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
				   <td  class="bgc_tt short">提醒时间：</td>
				    <td class="long"><input name="reservationNotice.noticeTime" id="reservationNotice.noticeTime" 
				                 value="<fmt:formatDate value='${reservationNotice.noticeTime}' pattern='yyyy-MM-dd hh:mm:ss'/>"
				               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class='easyui-validatebox  selectcode' style='width:227px' 
				               validType="datetime" type="text">
                    </td>
			    </tr> 
			     <tr>
				   <td class="bgc_tt short" align="center">地点：</td>
				    <td class="long"><textarea class="w_30" name="reservationNotice.appointmentAddress" id="reservationNotice.appointmentAddress" 
				       rows="4">${reservationNotice.appointmentAddress}</textarea>
				   </td>
			    </tr> 
			     <tr>
					<td class="bgc_tt short" align="center">事件：</td>
					<td class="long"><textarea class="w_30" name="reservationNotice.appointmentRemark" id="reservationNotice.appointmentRemark" 
					    rows="4">${reservationNotice.appointmentRemark}</textarea>
					</td>
				</tr>
				 <tr>
				   <td class="bgc_tt short" align="center">短信提醒：</td>
				    <td class="long"><input type="radio" name="reservationNotice.smsFlag" value="1" checked="checked" >是
                              <input type="radio" name="reservationNotice.smsFlag" value="0" >否
                    </td>
			    </tr> 
				 <tr>
				    <td colspan="2" align="center">
				      <input type="button" class="button_ty" value="修改"  onclick="updateReservation();">
					  <input type="reset" class="button_ty" value="重置">
					  <input type="button" class="button_ty" value="关闭"  onclick="window.close();">
					</td>
					
				</tr>   
			 </table>
			 </form>
		</div>	

	</div>
</body>
</html>