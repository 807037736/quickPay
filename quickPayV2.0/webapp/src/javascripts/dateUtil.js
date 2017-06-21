// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 


/**
 * 初始化日期控件方法
 * 可通过最大日期以及最小日期来界定
 * 如果是空串，则以当前日期后一天为最小日期，下一年今天为最大日期
 * maxDate：2014-11-02
 * minDate：2014-11-02
 * author：lwj
 */
var initDatePlug = function (maxDate, minDate) {
	var currYear = new Date().getFullYear();
	var currMonth = new Date().getMonth()+1;
	var currDay = new Date().getDate();
	
	var maxDateArr = new Array(currYear+1, currMonth, currDay);
	var minDateArr = new Array(currYear, currMonth, currDay+1);
	
	if(maxDate != "") {
		maxDateArr = maxDate.split("-");
	}
	if(minDate != "") {
		minDateArr = minDate.split("-");
	}
	
	var opt={
		preset:'date',
		theme:'jqm',
		display:'modal',
		mode:'chickpick',
		dateFormat:'yy-mm-dd',
		setText:'确定',
		cancelText:'取消',
		dateOrder:'yymmdd',
		dayText:'日',monthText:'月',yearText:'年',
		maxDate:new Date(maxDateArr[0], maxDateArr[1]-1, maxDateArr[2]),
		minDate:new Date(minDateArr[0], minDateArr[1]-1, minDateArr[2]),
// 		startYear:currYear-10,
 		endYear:currYear+10
	}
	return opt;
}