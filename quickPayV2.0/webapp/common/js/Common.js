/**
 * common js
 */
/** Gobal bCancel; */
var bCancel = false;
var DATE_DELIMITER = "-";
var DB_INT_LENGTH = 64; //Database Length.defalut64
var MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
var MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
var MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
var MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
var VERBOSE = false;
var FIELD_SEPARATOR = "_FIELD_SEPARATOR_";
var GROUP_SEPARATOR = "_GROUP_SEPARATOR_";
function setDateDelimiter(delimiter) {
    DATE_DELIMITER = delimiter;
}
function setDBIntLength(len) {
    DB_INT_LENGTH = len;
    MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
    MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
    MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
    MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
}
function setVerbose(verbose) {
    VERBOSE = verbose;
}
function isVerbose() {
    return VERBOSE;
}
function log(value) {
    if (isVerbose()) {
        window.status = value;
    }
}
function isNetscape() {
    if (navigator.appName == "Netscape") {
        return true;
    } else {
        return false;
    }
}

function getByteLength(value) {
    var str;
    var count = 0;
    var valueCount = value.length;
    for (var i = 0; i < valueCount; i++) {
        str = escape(value.charAt(i));
        if (str.length == 6) {
            count = count + 2;
        } else {
            count = count + 1;
        }
    }
    return count;
}

function leftTrim(value) {
    var re = /^\s*/;
    if (value == null) {
        return null;
    }
    return value.replace(re, "");
}
function rightTrim(value) {
    var re = /\s*$/;
    if (value == null) {
        return null;
    }
    return value.replace(re, "");
}
function trim(value) {
    return leftTrim(rightTrim(value));
}
function regExpTest(resource, re) {
    var result = false;
    if (resource == null || resource == "") {
        return false;
    }
    if (resource == re.exec(resource)) {
        result = true;
    }
    return result;
}
function replace(str, strFind, strReplaceWith) {
    var strReturn;
    var re = new RegExp(strFind, "g");
    if (str == null) {
        return null;
    }
    strReturn = str.replace(re, strReplaceWith);
    return strReturn;
}
function isEmptyField(field) {
    if (field.value == null || trim(field.value) == "") {
        return true;
    }
    return false;
}
function formatNumberToString(strValue) {
    strValue = trim(strValue);
    strValue = replace(strValue, ",", "");
    return strValue;
}
function formatNumberToDouble(strValue) {
    var dblValue = 0;
    strValue = formatNumberToString(strValue);
    dblValue = parseFloat(strValue);
    if (isNaN(dblValue)) {
        dblValue = 0;
    }
    return dblValue;
}
function fixNumber(cellStr) {
    if (parseFloat(cellStr) == 0) {
        return "0";
    }
    if (cellStr.indexOf(".") == -1) {
        return cellStr;
    }
    var x = parseFloat(cellStr);
    x += (parseFloat(cellStr) < 0) ? -5e-9 : 5e-9;

	// Chop the string to 4 decimal places.
    cellStr = "" + x;
    cellStr = cellStr.substring(0, 5 + cellStr.indexOf("."));

	// Remove trailing zeros beyond the decimal point.
    cellStrArr = cellStr.split("");
    for (k = cellStrArr.length - 1; k > 0 && cellStrArr[k] == "0"; --k) {
        cellStrArr[k] = "X";
    }
    cellStr = "";
    for (k = 0; k < cellStrArr.length && cellStrArr[k] != "X"; ++k) {
        cellStr += cellStrArr[k];
    }
    return cellStr;
}
function setFocus(field) {
    try {
        field.focus();
    }
    catch (E) {
    }
}
function showMessage(message) {
    alert(message);
}
function boundCheckBox(controlField, checkBoxField) {
    var count = 0;
    try {
        count = checkBoxField.length;
    }
    catch (E) {
    }
    if (isNaN(count)) {
	    if(checkBoxField.className!="readonlyCheckBox"){
        checkBoxField.checked = controlField.checked;
        }
    } else {
        for (var i = 0; i < count; i++) {
        	if(checkBoxField[i].className!="readonlyCheckBox"){
            checkBoxField[i].checked = controlField.checked;
        }
    }
}
}
function getElementIndexInForm(form, field) {
    var intElementIndex = -1;
    var elementsCount = form.elements.length;
    for (var i = 0; i < elementsCount; i++) {
        if (form.elements[i] == field) {
            intElementIndex = i;
            break;
        }
    }
    return intElementIndex;
}
function setSelect(field) {
    if (field.type != "hidden") {
        try {
            field.select();
        }
        catch (E) {
        }
    }
}
function isIE6() {
    if (navigator.appVersion.indexOf("MSIE 6") > -1) {
        return true;
    } else {
        return false;
    }
}

function isIE7() {
    if (navigator.appVersion.indexOf("MSIE 7") > -1) {
        return true;
    } else {
        return false;
    }
}

function bindField(source, target) {
    target.value = source.value;
}
function newString(str, times) {
    var value = "";
    for (var i = 0; i < times; i++) {
        value += str;
    }
    return value;
}
function printExcept(obj) {
    obj.style.display = "none";
    window.print();
    obj.style.display = "";
}
function postAction(form, action, target) {
    var oldTarget = form.target;
    if (target != null) {
        form.target = target;
    }
    form.action = action;
    form.submit();
    if (target != null) {
        form.target = oldTarget;
    }
    return true;
}
function postActionWithConfirm(form, action, message, target) {
    if (confirm(message)) {
        var oldTarget = form.target;
        if (target != null) {
            form.target = target;
        }
        form.action = action;
        form.submit();
        if (target != null) {
            form.target = oldTarget;
        }
        return true;
    } else {
        return false;
    }
}
function setFormDisabled(form) {
    if (form == null) {
        form = fm;
    }
    var element;
    for (var i = 0; i < form.elements.length; i++) {
        element = form.elements[i];
        if (element.type == "hidden") {
            continue;
        }
        if (element.name.indexOf("buttonControl") == 0) {
            continue;
        }
        if (element.name == "buttonSubmit") {
            element.style.display = "none";
            continue;
        }
        element.disabled = true;
    }
}
function getFirstElementValue(fieldName) {
    var field;
    if (getElementCount(fieldName) > 1) {
        field = document.getElementsByName(fieldName);
    } else {
        field = document.getElementsByName(fieldName)[0];
    }
    return field.value;
}
function openWindow(urlString, windowName, optionString) {
	var iWidth= window.screen.width; //弹出窗口的宽度;
	var iHeight= window.screen.height; //弹出窗口的高度;
	var iTop = (window.screen.height+10-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.width-20-iWidth)/2; //获得窗口的水平位置;
	
	var config = 'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',alwaysRaised=yes,channelmode=yes,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no'; 

	var newWindow = window.open(urlString, windowName, config);
    try {
        newWindow.focus();
    }
    catch (E) {
    }
    return newWindow;
}
function exportResultDataToExcel(table) {
    var oXL;
    try {
        oXL = GetObject("", "Excel.Application");
    }
    catch (E) {
        try {
            oXL = new ActiveXObject("Excel.Application");
        }
        catch (E2) {
            showMessage("Please confirm:\n1.Microsoft Excel has been installed.\n2.Internet Options=>Security=>Setting \"Enable unsafe ActiveX\"");
            return;
        }
    }
    var oWB = oXL.Workbooks.Add();
    var oSheet = oWB.ActiveSheet;
    var Lenr = 1;
    var maxColumn = 0;
    for (var i = 0; i < Lenr; i++) {
        var Lenc = table.tHead.rows(i).cells.length;
        for (j = 0; j < Lenc; j++) {
            if (maxColumn < j) {
                maxColumn = j;
            }
            oSheet.Columns(j + 1).EntireColumn.NumberFormatLocal = "@";
            oSheet.Cells(i + 1, j + 1).value = table.tHead.rows(i).cells(j).innerText;
        }
    }
    Lenr = table.tBodies(0).rows.length;
    for (var i = 0; i < Lenr; i++) {
        var Lenc = table.tBodies(0).rows(i).cells.length;
        var j = 0;
        var value = table.tBodies(0).rows(i).cells(j).innerText;
        var pos = value.indexOf(" ");
        if (pos > -1) {
            value = trim(value.substring(pos));
        }
        oSheet.Cells(i + 2, j + 1).value = value;
        for (j = 1; j < Lenc; j++) {
            oSheet.Cells(i + 2, j + 1).value = table.tBodies(0).rows(i).cells(j).innerText;
        }
    }
    for (var i = 0; i < maxColumn; i++) {
        oSheet.Columns(i + 1).EntireColumn.AutoFit;
    }
    oXL.Visible = true;
}
function setSameElementValue(field) {
    if (getElementCount(field.name) > 1) {
        var fields = document.getElementsByName(field.name);
        for (var i = 0; i < fields.length; i++) {
            fields[i].value = field.value;
        }
    }
}
function readCookie(name) {
    var cookieValue = "";
    var search = name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) {
                end = document.cookie.length;
            }
            cookieValue = unescape(document.cookie.substring(offset, end));
        }
    }
    return cookieValue;
}
function writeCookie(name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = ";  expires=" + expire.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire;
}

/**
 * unformat number
 * example:123,222.23 ==> 123322.23
 * @since 2006-09-01
 */
function unformatNumber(value){
  var retValue = replace(value+"", ",", "");
  var valueArray = retValue.split(".");
  if(valueArray.length>1 && valueArray[1]=="00"){
    retValue = valueArray[0];
  }
  return retValue;
}

/**
 * format number
 * example:123222.23 ==> 123,322.23
 * if has precision,the return value will have at least precision number after dot.
 * @since 2006-09-01
 */
function formatNumber(value,precision){
  value  =  value+"";
  var pos = value.indexOf('.');
  if(pos>-1){
    var firstValue=value.substring(0,pos);
    var lastValue=value.substring(pos+1);
    var  re=/(-?\d+)(\d{3})/
    while(re.test(firstValue)){
      firstValue=firstValue.replace(re,"$1,$2")
    }
    /*
    re=/(\d{3})(\d+)/
    while(re.test(lastValue)){
      lastValue=lastValue.replace(re,"$1,$2")
    }
    */
    value = firstValue + "." + lastValue;
  }else{
    var re=/(-?\d+)(\d{3})/
    while(re.test(value)){
      value=value.replace(re,"$1,$2")
    } 
  } 
  if(precision!=undefined && !isNaN(precision)){
  	var pos = value.indexOf('.'); 
  	if(pos==-1){
  		value+="."; 
  		pos = value.indexOf('.'); 
  	}
  	
  	var len = value.length-pos-1;
  	
		for(var i=len;i<precision;i++){
			value+="0";
		}  	
  }
  return  value;
}


function upperCaseFirstChar(str){
  if(str == null || str.length < 1 ){
   return str;  
  }
  var convertStr = str.substring(0,1).toUpperCase()+str.substring(1);        
  return convertStr ;    
}

function lowerCaseFirstChar(str){
  if(str == null || str.length < 1 ){
   return str;  
  }
  var convertStr = str.substring(0,1).toLowerCase()+str.substring(1);        
  return convertStr ;    
}

function getElementByTagName(object,elementName,tagName){ 
  var tempField = object.getElementsByTagName(tagName); 
  for (var i = 0; i <tempField.length; i++) {
    if(tempField[i].name == elementName){      
      return tempField[i]; 
    }
  } 
  return null;
}  
function copyOptions(targetSelect,sourceSelect){
  targetSelect.options.length=0;
  for(var i=0; i<sourceSelect.length; i++){
    addOption(targetSelect, sourceSelect.options[i].text, sourceSelect.options[i].value);
  }
}
function addOption(targetSelect,text,value){
  targetSelect.options[targetSelect.length]=new Option(text,value);
}

function setChildElementsDisabled(object,disabledValue){
  var fields = object.getElementsByTagName("INPUT"); 
  var i= 0;
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  }
  fields = object.getElementsByTagName("SELECT"); 
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  } 
  fields = object.getElementsByTagName("TEXTAREA"); 
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  }  
}
//show/hide advanced condition.
function showAdvanced(controlNode,controlButton){ 
	if(controlNode.style.display=="none"){
		setChildElementsDisabled(controlNode,false);
		controlNode.style.display = "";
	}else{
		controlNode.style.display = "none";
		setChildElementsDisabled(controlNode,true);
	}	
}

function round(number,precision)
{
  if(isNaN(number))
    number = 0;
  var prec = Math.pow(10,precision);
  var result = Math.round( number * prec) ;
  result = result/prec;
  return result;
}


function point(number,precision)
{
  if(isNaN(number))
    number = 0;
  var result = number.toString();
  if(result.indexOf(".")==-1)
    result = result + ".";

  result = result + newString("0",precision);
  result = result.substring(0,precision + result.indexOf(".") + 1);
  return result;
}


function checkIsInteger(field){
    var str = field.value;
    if(str == ""|| str ==null){
        return false;
    }
    if(/^(\-?)(\d+)$/.test(str)){
      if(str<0){
         field.value = "";
        return false;
      }
        return true;
    }else{
        field.value = "";
        return false;
    }
  }
 
 function findMessageList(registNo,nodeType){
 	var inputObject;
	var outputObject;
	if(nodeType != null && (nodeType == "CaseDispatch" || nodeType == "Regist")){
		inputObject = "special";
	}
	var messageInfo = registNo + "," + nodeType;
	dwrInvokeData("findMessageList",messageInfo,"callbackSetButtonFlash",inputObject,outputObject,"",true);
 }
 
 function callbackSetButtonFlash(inputObject,outputObject,returnObject){
 	var pmBox = document.getElementById("pmBox");
 	if(returnObject == true && pmBox != null){
 		pmBox.style.backgroundcolor = "transparent";
 		if(inputObject == "special"){
 			pmBox.style.backgroundImage="url(pages/image/btbg_changeColor2.gif)";
 		}else{
 			pmBox.style.backgroundImage="url(../pages/image/btbg_changeColor2.gif)";
 		}
 	}
 }
 //不合格输入
 function incompetent(field){
 	var value = field.value;
 	if(value.indexOf("*")>-1 || value.indexOf("%")>-1 ){
 		return true;
 	}
 	return false;
 }
 

/**
增加字符串长度校验，区分中文字符和英文字符长度，liny，2009-11-13
js提供的length方法针对中文字符，只判断为一位，再验证上不准确，新增以下方法可校验字符串长度
使用方式:
var a = "中文";
a.Blength();
*/
String.prototype.Blength = vs_str_BLength;
function vs_str_BLength(maxlen){
	var i;
	var len = 0;
	var s = this;
	var ch;

	for(i = 0; i < s.length; i++){
		ch = s.charAt(i);
		if(ch < String.fromCharCode(0x100))
		{
			len++;
		}
		else{
			len += 2;
		}
		if(maxlen){
			if(len > maxlen){
				len = i;
				break;
			}
		}
	}
	if(maxlen){
		len = Math.min(i, len);
	}
	return len;
}


//edit record
function editRecord(url){
	var newWindow = openWindow(contextRootPath+"/common/loading/openLoading.html", "_blank", "alwaysRaised,channelmode,status,scrollbars,resizable");
	newWindow.location=url;
}

//show record
function showRecord(url){
	var newWindow = openWindow(contextRootPath+"/common/loading/openLoading.html", "_blank", "alwaysRaised,channelmode,status,scrollbars,resizable");
	newWindow.location=url;
}
 
/**只读控制**/
function setReadonlyOfAllInput(formID)
{
  var form = document.getElementById(formID);
  if(form!=undefined){
	  var length = form.elements.length;
	  for(var i=0;i<length;i++)
	  {
	    setReadonly(form.elements[i]);
	  }
  }
}
function setCheckBoxReadonly(field,flag)
{
  if(flag==null)
  {
    errorMessage("函数setCheckBoxReadonly使用错误，Flag应该为True/Flase!");
    return;
  }

  if(flag==true)
  {
    if(field.setCheckBoxReadonlyFlag!=true)
    {
      field.setCheckBoxReadonlyFlag = true;
      field.oldClassName = field.className;
      field.oldOnclick   = field.onclick;
      field.className = "readonly";
      field.onclick = functionReturnFalse;
    }
  }
  else
  {
    if(field.setCheckBoxReadonlyFlag==true)
    {
      field.className = field.oldClassName;
      field.onclick = field.oldOnclick;
      field.setCheckBoxReadonlyFlag = false;
    }
  }
}

function setRadioReadonly(field,flag)
{
  if(flag==null)
  {
    errorMessage("函数setRadioReadonly使用错误，Flag应该为True/Flase!");
    return;
  }
  if(flag==true)
  {
    if(field.setRadioReadonlyFlag!=true)
    {
      field.setRadioReadonlyFlag = true;
      field.oldClassName = field.className;
      field.oldOnfocus   = field.onfocus;
      field.className = "readonly";
      field.onfocus = functionCancelFocus;
    }
  }
  else
  {
    if(field.setRadioReadonlyFlag==true)
    {
      field.className = field.oldClassName;
      field.onfocus = field.oldOnfocus;
      field.setRadioReadonlyFlag = false;
    }
  }
}
function setReadonly(iElement){
	  if(iElement == null){
		 return;
	  }
	
	  if(iElement.type == "button"||iElement.type == "reset"){
		  setReadonlyOfButton(iElement);
	  }else if(iElement.tagName == "A"){//for easyui <a>tag button
		  setReadonlyOfA(iElement);
	  }else{
		  setReadonlyOfElement(iElement);
	  }
	}
function setReadonlyOfA(iElement){
	  if(iElement.getAttribute("ind") == "ind"){
	    iElement.style.display = "none";
	  }
	}

function setReadonlyOfButton(iElement){
	  if(iElement.getAttribute("ind") == "ind"){
	    iElement.style.display = "none";
	  }
	}

function setReadonlyOfElement(iElement)
{
  if ((iElement.type=="hidden") ||
            (iElement.type=="password") ||
            (iElement.type=="text") ||
            (iElement.type=="textarea"))
  {
    if(iElement.setReadonlyFlag==true)
    {
      return;
    }
    else
    {
      iElement.setReadonlyFlag = true;
    }

    //事件存储在oldXXX里
    iElement.oldOnblur = iElement.onblur;
    iElement.onblur = functionDoNothing;

    iElement.oldOnclick = iElement.onclick;
    
    iElement.onclick = functionDoNothing;
    
    iElement.oldOndblclick = iElement.ondblclick;
    iElement.ondblclick = functionDoNothing;

    iElement.oldOnfocus = iElement.onfocus;
    iElement.onfocus = functionDoNothing;

    iElement.readOnly = true;
    iElement.oldClassName = iElement.className;
    if(iElement.oldClassName != ""){
    	if(iElement.oldClassName.indexOf("easyui-validatebox")>-1
    			||iElement.oldClassName.indexOf("validatebox-text")>-1
    			||iElement.oldClassName.indexOf("validatebox-invalid")>-1){//只读情况下，去除easyui校验
    		$(iElement).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
    		iElement.className = iElement.className + " readonly";
    	}else{
    		iElement.className= iElement.oldClassName + " readonly";
    	}
    }else{
    	iElement.className= "readonly";
    }
  }
  else if(iElement.type=="checkbox")
  {
    setCheckBoxReadonly(iElement,true);
  }
  else if(iElement.type=="radio")
  {
    setRadioReadonly(iElement,true);
  }else if(iElement.type=="select-one")
  {
	    if(iElement.setReadonlyFlag==true)
	    {
	      return;
	    }
	    else
	    {
	      iElement.setReadonlyFlag = true;
	    }

	    var optionTags = new Array();
	    var index = 0;
	    for(var j=iElement.options.length-1;j>=0;j--)
	    {
	      var tag = new Array();
	      tag["value"] = iElement.options[j].value;
	      tag["text"]  = iElement.options[j].text;
	      optionTags[index++] = tag;
	      if(iElement.options[j].value!=iElement.value)
	      {
	        iElement.remove(j);
	      }
	    }
	    iElement.optionTags = optionTags;
	  }
}
function undoSetReadonlyOfElement(iElement)
{
  if(iElement.type=="select-one")
  {
    if(iElement.setReadonlyFlag!=true)
    {
      return;
    }
    else
    {
      iElement.setReadonlyFlag = false;
    }

    var optionTags = iElement.optionTags;
    var currentValue = iElement.value;

    for(var i=iElement.options.length-1;i>=0;i--)
    {
      iElement.remove(i);
    }

    for(var i=optionTags.length-1;i>=0;i--)
    {
      var tag = optionTags[i];
      var op = document.createElement("OPTION");
      op.value = tag.value;
      op.text =  tag.text;
      iElement.add(op);
    }
    iElement.value = currentValue;

  }
  else if ((iElement.type=="hidden") ||
            (iElement.type=="password") ||
            (iElement.type=="text") ||
            (iElement.type=="textarea"))
  {
    if(iElement.setReadonlyFlag!=true)
    {
      return;
    }
    else
    {
      iElement.setReadonlyFlag = false;
    }

    iElement.onblur = iElement.oldOnblur;
    iElement.ondblclick = iElement.oldOndblclick;
    iElement.onclick = iElement.oldOnclick;
    iElement.onfocus = iElement.oldOnfocus;

    iElement.readOnly = false;
    iElement.className = iElement.oldClassName;
  }
  else if(iElement.type=="button")
  {
    if(iElement.setReadonlyFlag!=true)
    {
      return;
    }
    else
    {
      iElement.setReadonlyFlag = false;
    }

    if(iElement.name.indexOf("Delete")>-1 || iElement.name.indexOf("Insert")>-1)
    {
      iElement.disabled = false;
    }
  }
  else if(iElement.type=="checkbox")
  {
    setCheckBoxReadonly(iElement,false);
  }
  else if(iElement.type=="radio")
  {
    setRadioReadonly(iElement,false);
  }

}
//一次给所有的text,textarea的readonly设置为false,select-one恢复初始设置
function undoSetReadonlyOfAllInput(formID)
{
  var form = document.getElementById(formID);
  if(form!=undefined){
	  var length = form.elements.length;
	  for(var i=0;i<length;i++)
	  {
		  undoSetReadonlyOfElement(form.elements[i]);
	  }
  }
}

/**
 * 根据表名、是否多行、标识字段名、忽略字段控制是否只读
 * @param tableName 表名
 * @param isMulti  是否多行
 * @param flagFieldName  控制只读的字段名，0为只读，1为不控制
 * @param ignoreField  忽略字段名
 */
function setReadonlyByCondition(tableName,isMulti,flagFieldName,ignoreField)
{
  if(tableName==null||tableName==""){
	  return;
  }
  var talbes = document.getElementsByName(tableName);
  if(isMulti==null||isMulti==""){
	  isMulti = false;
  }
  var readOnlyFlag = true;
  var fieldOnly = true;
  var ignoreFields=null;
  if(ignoreField!=null&&ignoreField!=""){
	  ignoreFields=ignoreField.split(",");
  }
  if(talbes!=null&&talbes!=undefined){
	  var tableLength = talbes.length;
	  var table;
	  for(var i=0;i<tableLength;i++){
		  table = talbes[i];
		  if(isMulti){//多行
			  var tbodies = $("tbody",table);
			  $("tr",tbodies).each(function(indexTr,trObject){
				  readOnlyFlag = true;
				  if(flagFieldName!=null&&flagFieldName!=""&&$("input[name='"+flagFieldName+"']",trObject).val()=="1"){
					  readOnlyFlag = false;
				  }
				  if(readOnlyFlag){
					  $("[type!='hidden']",trObject).each(function(index,object){
							  fieldOnly = true;
							  if(ignoreFields!=null&&ignoreFields.length>0){
								  for(var ii=0;ii<ignoreFields.length;ii++){
									  if(ignoreFields[ii]==object.name){
										  fieldOnly = false;
										  break;
									  }
								  }
							  }
							  if(fieldOnly){
								  setReadonly(object);
							  }
					  });
				  }
			  });
		  }else{//单行
			  if(flagFieldName!=null&&flagFieldName!=""&&$("input[name='"+flagFieldName+"']",table).val()=="1"){
				  readOnlyFlag = false;
			  }
			  if(readOnlyFlag){
				  $("[type!='hidden']",table).each(function(index,object){
						  fieldOnly = true;
						  if(ignoreFields!=null&&ignoreFields.length>0){
							  for(var ii=0;ii<ignoreFields.length;ii++){
								  if(ignoreFields[ii]==object.name){
									  fieldOnly = false;
									  break;
								  }
							  }
						  }
						  if(fieldOnly){
							  setReadonly(object);
						  }
				  });
			  }
		  }
	  }
  }
}

/**
 * 将对象转为用&拼接的参数
 * add by yaowenfeng
 */
var getUrlByJson = function(json,map,upperKey) {  
    if (json == undefined || typeof json == 'undefined' || json === null) {  
        return '';  
    }
    var tmps; 
    if(map == undefined || map == null){
    	tmps = []; 
    }else{
    	tmps = map; 
    }
    var realKey;
     
    for (var key in json) {
    	if(upperKey==undefined || upperKey==null){
    		realKey = key;
    	}else{
    		realKey = upperKey+"."+key;
    	}
    	if(typeof json[key] == "object"){
    		getUrlByJson(json[key],tmps,realKey);  
    	}else{
    		tmps.push(realKey + '=' + encodeURIComponent(json[key]));  
    	}
        
    }  
    return tmps.join('&');  
};
/**
 * 根据inputName获取input，并根据事件和事件函数初始化input
 * add by yaowenfeng
 */
function initInputEvent(inputName,eventName,eventFunction){
    var object=document.getElementsByName(inputName);
    var elementCount=object.length;
	for(var index = 0;index < elementCount;index++) {
		setEvent(object[index],eventName,eventFunction);
    }
}
/**
 * 根据事件和事件函数初始化object
 * add by yaowenfeng
 */
function setEvent(object, eventName, eventFunction) {
	if(eventFunction==null||eventFunction==""){//注销事件
		$(object).unbind(eventName);
	}else{//增加事件
		$(object).live(eventName,eventFunction);
	}
}

function browserVersion(){
	var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (window.ActiveXObject){
    	Sys.ie = ua.match(/msie ([\d.]+)/)[1];
    }
    else if (window.MessageEvent && !document.getBoxObjectFor){
    	Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1];
    }
    else if (document.getBoxObjectFor){
    	Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
    }
    else if (window.opera){
    	Sys.opera = ua.match(/opera.([\d.]+)/)[1];
    }
    else if (window.openDatabase){
    	Sys.safari = ua.match(/version\/([\d.]+)/)[1];
    }
    //以下进行测试
    /*if(Sys.ie) {
    	alert("ie"); 
    }else if(Sys.chrome) {
    	alert("chrome"); 
    }else if(Sys.firefox) {
    	alert("firefox"); 
    }else if(Sys.opera) {
    	alert("opera"); 
    }else if(Sys.safari) {
    	alert("safari"); 
    }*/
    return Sys;
}
/**
 * 查询结果代码翻译
 * @param codeCode
 * @param codeType
 * @param riskCode
 * @param action
 * @returns {String}
 * add by yaowenfeng
 */
function codeTranslate(codeCode, codeType, riskCode, action) {
    var result = "";
    var url;
    if (action == null||action=="") {
        url = contextRootPath + "/common/codeTranslate.do?codeCode=" + codeCode
                + "&codeType=" + codeType + "&riskCode=" + riskCode;
    }else{
        url = contextRootPath + action + "?codeCode=" + codeCode + "&codeType=" + codeType + "&riskCode="
                + riskCode;
    }
    
    $.ajax({
        type : "GET",
        url : url,
        async : false,
        success : function(data) {
        	if(data!=null&&data!=""){
        		var object = eval('('+data+')');
            	if(object!=null&&object.msg!=null&&object.msg!=""){
            		result = object.msg;
            	}else{
            		result = codeCode;
            	}
        	}else{
        		result = codeCode;
        	}
        },
        error : function() {
        	result = codeCode;
        }
    });

    return result;
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}