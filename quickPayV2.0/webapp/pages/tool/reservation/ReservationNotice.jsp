<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<html>
<head>
<script language="javascript" src="${ctx}/pages/tool/reservation/ReservationNotice.js"></script>
<script src="${ctx}/pages/wf/common/formatter.js"></script>
</head>
<body>
<div class="right_detail_top"><h3>预约任务</h3></div>
<div id="wrapper">
	<div id="headDiv" class="easyui-tabs" data-options="tools:'#tab-tools'" >
		<div id="" title="预约设置" >
		<div id="wrapper">
			 <form name="reservationSet" id="reservationSet">
			 <table class="fix_table">
			    <tr>
				   <td class="bgc_tt short">预约开始时间：</td>
			       <td class="long"><input name="reservationNotice.apointmentTimeStart" id="reservationNotice.apointmentTimeStart" 
			        onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					class='easyui-validatebox  selectcode' style='width:227px' validType="datetime" type="text"></td>
				</tr>	
				<tr>
				   <td  class="bgc_tt short">预约结束时间：</td>
			       <td class="long"><input name="reservationNotice.apointmentTimeEnd" 
					id="reservationNotice.apointmentTimeEnd" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class='easyui-validatebox  selectcode' style='width:227px' validType="datetime" type="text"></td>
				</tr>	
				<tr>
				   <td  class="bgc_tt short">提醒时间：</td>
				    <td class="long"><input name="reservationNotice.noticeTime" id="reservationNotice.noticeTime" 
				               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class='easyui-validatebox  selectcode' style='width:227px'
				               validType="datetime" type="text">
                    </td>
			    </tr> 
			    <tr>
				   <td align="center" class="bgc_tt short">地点：</td>
				    <td class="long"><textarea class="w_30" name="reservationNotice.appointmentAddress" id="reservationNotice.appointmentAddress" rows="4" ></textarea>
				   </td>
			    </tr> 
			    <tr>
					<td align="center" class="bgc_tt short">事件：</td>
					<td class="long"><textarea class="w_30" name="reservationNotice.appointmentRemark" id="reservationNotice.appointmentRemark" rows="4"></textarea>
					</td>
				</tr>
				
			    <tr>
				   <td align="center" class="bgc_tt short">短信提醒：</td>
				    <td class="long"><input type="radio" name="reservationNotice.smsFlag" value="1" checked="checked" >是
                              <input type="radio" name="reservationNotice.smsFlag" value="0" >否
                    </td>
			    </tr> 
				 <tr>
				    <td align="center" colspan="2"><input type="button" class="button_ty" value="保存" id="addReservationBt" onclick="addReservation();">
					  <input type="reset" class="button_ty" value="重置">
					</td>
					
				</tr>  
			 </table>
			 </form>
		</div>
		</div>
		 <div id="" title="预约查询">
		 <div id="wrapper">
		     <form name="reservationQuery" id="reservationQuery">
		     <table class="fix_table">
		         <tr>
				   <td  align="center" class="bgc_tt short">统计时间：</td>
			       <td class="long"><select class="easyui-combobox input_w w_15" panelHeight="auto" name="querySelectTimeType" >
	    					<option value="0" selected>开始时间</option>
	    					<option value="1" >结束时间</option>
	    					<option value="2">提醒时间</option>
	    			    </select>
	    		        <input name="querySelectStartTime" id="querySelectStartTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					          class='easyui-validatebox selectcode' validType="datetime" type="text"><span class="time_between easyui-numberbox">至</span>
                               <input name="querySelectEndTime" id="querySelectEndTime" 
					          onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class='easyui-validatebox selectcode' validType="datetime" type="text">
				   </td>
				 </tr>
				 <tr>
				   <td align="center"  class="bgc_tt short">显示过期：</td>
			       <td class="long"><input type="radio" name="reservationNotice.reservationStatus" value=""  >是
                                    <input type="radio" name="reservationNotice.reservationStatus" value="1" checked="checked">否
                    </td>               
			    </tr>
			    <tr>
			        <td  align="center" colspan="2"><input type="button" class="button_ty" value="查询" onclick="queryReservation();">
					    <input type="reset" class="button_ty" value="重置">
					</td>
				</tr>
			 </table>
			 </form>
			 </div>
			 <div class="margin-control">
			 <table id="queryReservationTable"></table>	
			 </div>
		</div> 
	</div>
</div>
</body>
</html>