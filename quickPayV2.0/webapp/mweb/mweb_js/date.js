/*累加时间,天*/
  function getNewDay(dateTemp, days) { 
    //alert(dateTemp+'+'+days)
    var dateTemp = dateTemp.split("-");  
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
    var rDate = new Date(millSeconds);  
    var year = rDate.getFullYear();  
    var month = rDate.getMonth() + 1;  
    if (month < 10) month = "0" + month;  
    var date = rDate.getDate();  
    if (date < 10) date = "0" + date;
    return (year + "-" + month + "-" + date);
  }
  /*累加时间,月*/
  //var new_mm=getNewMonth('2014-08-12',11)
  function getNewMonth(dateTemp,months){
    var dateTemp = dateTemp.split("-");    
    var new_y=Number(dateTemp[0]);
    var new_m=Number(dateTemp[1])+months;
    var new_d=Number(dateTemp[2]);
    if(new_m>12){
      new_m=new_m-12;
      new_y=Number(dateTemp[0])+1;
    }
    if (new_m < 10) new_m = "0" + new_m;    
    return (new_y + "-" + new_m + "-" + new_d);
  }
  /*累加时间,年*/
  //var new_yy=getNewYear('2014-08-12',10)
  function getNewYear(dateTemp,years){
    var dateTemp = dateTemp.split("-");    
    var new_y=Number(dateTemp[0])+years;
    var new_m=Number(dateTemp[1]);
    var new_d=Number(dateTemp[2]);    
    if (new_m < 10) new_m = "0" + new_m;    
    return (new_y + "-" + new_m + "-" + new_d);
  }
  
  /*当前时间*/
  function curentTime(){ 
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + "";
    return(clock); 
  }

  /*日期相减 日期格式为 YYYY-MM-dd   */
  function daysBetween(DateOne,DateTwo)  
  {   
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
  
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
  
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);  
    //return Math.abs(cha); 
    return cha; 
  }  

  /*字符串转成日期类型*/
  function StringToDate(DateStr)  
  {
    var now = new Date(DateStr);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + "";
    return clock;
  } 
  