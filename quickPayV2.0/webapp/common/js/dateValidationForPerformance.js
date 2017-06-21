/** 
	* added by dijie 2013-12-3
	* @Description:  页面日期校验公共方法2
	
	*/
function dateValidation(){
	var starDate=document.getElementById("startDate").value;
	var endDate=document.getElementById("endDate").value;
	var datesent=dateDiff(endDate,starDate);
	var now =CurentDate();
	//new Date().toLocaleDateString();
	var datesentStartToNow=dateDiff(now,starDate);
	var datesentEndToNow = dateDiff(now,endDate);
	if(starDate=="")
	  {
	  $.messager.alert('提示消息', '请选择开始日期', 'info');
	  return false;
	  }
	if(endDate=="")
	  {
	  $.messager.alert('提示消息', '请选择结束日期', 'info');
	  return false;
	  }
//	if(datesentEndToNow<0||datesentStartToNow<0)
//		{
//		$.messager.alert('提示消息', '所选日期不能超过今天', 'info');
//		return false;
//		}
	if(datesent<0)
	  {
		$.messager.alert('提示消息', '选择的日期有误，结束日期必须大于开始日期!', 'info');
	  return false;
	  }
//	if(datesentStartToNow>90)
//	{
//		$.messager.alert('提示消息', '只能查询最近90天内的记录！请重新选择日期', 'info');
//		return false;
//	}
	
	else{return true;}
}
//调用该方法(主方法) 
function dateDiff(date1, date2){ 
    var type1 = typeof date1, type2 = typeof date2; 
    if(type1 == 'string') 
    date1 = stringToTime(date1); 
    else if(date1.getTime) 
    date1 = date1.getTime(); 
    if(type2 == 'string') 
    date2 = stringToTime(date2); 
    else if(date2.getTime) 
    date2 = date2.getTime(); 
    return (date1 - date2) / (1000 * 60 * 60 * 24);//除1000是毫秒，不加是秒 
}

//字符串转成Time(dateDiff)所需方法 
function stringToTime(string){ 
    var f = string.split(' ', 2); 
    var d = (f[0] ? f[0] : '').split('-', 3); 
    var t = (f[1] ? f[1] : '').split(':', 3); 
    return (new Date( 
    parseInt(d[0], 10) || null, 
    (parseInt(d[1], 10) || 1)-1, 
    parseInt(d[2], 10) || null, 
    parseInt(t[0], 10) || null, 
    parseInt(t[1], 10) || null, 
    parseInt(t[2], 10) || null 
    )).getTime(); 

}
//获取当前日期,格式为yyyy-MM-dd
function CurentDate(){ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
 
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
   
    return(clock); 
} 
//获取当前时间,格式为yyyy-MM-dd hh:mm:ss
function CurrentTime(){
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1; // 记得当前月是要+1的
	var dt = myDate.getDate();
	var h = myDate.getHours(); //获取当前小时数(0-23) 
	var m = myDate.getMinutes(); //获取当前分钟数(0-59) 
	var s = myDate.getSeconds(); //获取当前秒数(0-59) 
	var today = year + "-" + month + "-" + dt +" "+ h +":" + m +":" + s ;
	return (today); 
}
