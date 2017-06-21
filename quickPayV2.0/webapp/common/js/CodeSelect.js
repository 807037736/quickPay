/**
 * Code Input
 */
/** vars for codechange */ 
var codeSelectFieldIndex = null;
var codeSelectFieldValue = null;
//添加该FieldName字段，为了支持名称的模糊查询
var codeSelectFieldNameValue = null;
var codeSelectCodeMethod = null;
var codeSelectCodeType = null;
var codeSelectCodeRelation = null;
var codeSelectIsClear = null;
var codeSelectRealCondition = null;
var codeSelectTypeParam = null;
var codeSelectCallBackMethod = null;
var codeSelectGetDataMethod = null;
var codeSelectElementOrder = 0;
var codeSelectElementLength = 1;
var targetFormName = null;
var codeSelectHasSubmit = false;
 
/**
 * prepare select data,for codeinput
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value,key=fm.policyNo.value,key=fm.itemkindNo[].value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeSelect(field, codeType, codeRelation, isClear, otherCondition, typeParam, varParamField, callBackMethod, getDataMethod) {
	//如果是IE浏览器，则判断keyup方法
	//if(navigator.appName.indexOf("Microsoft") != -1 ){
	if (typeof(event) != 'undefined' && event.type == "keyup") {
		var charCode = window.event.keyCode;
		if (!(charCode == 13 & window.event.ctrlKey)) {
		     return;
		}
	}
    if(varParamField != undefined && trim(varParamField)!= ""){
    	var woundCode  = document.getElementsByName(varParamField)[0].value;
    	typeParam = getWoundType(trim(woundCode));
    }
    inCodeQuery = true; 
    private_Code_CallServiceByDialog(field,"select", codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
    inCodeQuery = false;
}
/**
 * prepare query data,for codequery,can select many value
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value,key=fm.policyNo.value,key=fm.itemkindNo[].value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeQuery(field, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
	if (typeof(event) != 'undefined' && event.type == "keyup") {
		var charCode = window.event.keyCode;
		if (!(charCode == 13 & window.event.ctrlKey)) {
		     return;
		}
	}
    inCodeQuery = true; 
    private_Code_CallServiceByDialog(field, "query", codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);    
    inCodeQuery = false;
}
//function code_CodeQuery(event, field, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
//	if (event.type == "keyup") {
//        var charCode = window.event.keyCode;
//        if (!(charCode == 13 & window.event.ctrlKey)) {
//            return;
//        }
//    }
//    inCodeQuery = true; 
//    private_Code_CallServiceByDialog(field, "query", codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);    
//    inCodeQuery = false;
//}
/**
 * only for parse params,set the value into public vars.
 */
function private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
	var targetForm = field.form;
	var elementOrder = getElementOrder(field)-1;
    codeSelectElementLength=getElementCount(field.name);    
    codeSelectElementOrder=elementOrder;
    var fieldIndex = getElementIndexInForm(targetForm, field);
    var fieldValue = field.value;
    if (fieldValue != null) {
        fieldValue = fieldValue.replace("*", "%");
    }
    var relations = new Array();
    if (codeRelation.indexOf(",") > -1) {
        relations = codeRelation.split(",");
        //添加通过relation对字段名称的处理，默认是relation[1]
        relations[1] = trim(relations[1]);
        var fieldName = null;         
        var relation = parseInt(relations[1], 10);
        if(isNaN(relation)){ 
        	fieldName = eval(targetForm+"."+relations[1]);
         }else{
            fieldName = targetForm.elements[fieldIndex + relation];
        } 
        codeSelectFieldNameValue = fieldName.value;
    } else {
        relations[0] = codeRelation;
    }
   
    targetFormName = targetForm.name;
    var conditions = new Array();
    if(otherCondition==null || otherCondition==undefined ||  otherCondition=="null"){
      otherCondition="";
    } 
    if (otherCondition.indexOf(",") > -1) {
        conditions = otherCondition.split(",");
    } else {
        conditions[0] = otherCondition;
    } 
    var conditionsCount = conditions.length;
    var realCondition = ""; 
    
    for (var i = 0; i < conditionsCount; i++) { 
    	  if(conditions[i]==null||conditions[i]==""){
            continue;
        }
        var equalPos = conditions[i].indexOf("=");
        var condName = "";
        var condStatement = conditions[i];
        if(equalPos>0){
          condName = conditions[i].substring(0,equalPos) + "=";
          condStatement = conditions[i].substring(equalPos+1);
        }
        var condValue = "";
        if(condStatement.indexOf("[]")==-1){
            try{
                if(condStatement.indexOf(".value")>-1){
                  condValue = eval(condStatement);
                }else{
                  condValue = condStatement;
                }
            }catch (E) {
                condValue = condStatement;
            }
        }else{
            var startPos = condStatement.indexOf("[");
            var endPos = condStatement.indexOf("]");
            if(startPos==endPos-1){
                condStatement = condStatement.substring(0,startPos+1) + elementOrder + condStatement.substring(endPos);
            }
            try{
                condValue = eval(condStatement);
            }catch (E) {
                condValue = condStatement;
            }
        }
        realCondition += condName + condValue;
        if(i<conditionsCount-1){
            realCondition+=",";
        }
    }
 
    if(isClear==undefined || isClear=="null"){
        isClear="Y";
    }
    if(otherCondition==undefined || otherCondition=="null"){
        otherCondition="";
    }
    if(typeParam==undefined || typeParam=="null"){
        typeParam="";
    } 
    if(callBackMethod==undefined || callBackMethod=="null"){
        callBackMethod="";
    }
    if(getDataMethod==undefined || getDataMethod=="null"){
        getDataMethod="";
    }
    codeSelectFieldIndex= fieldIndex;
    codeSelectFieldValue=fieldValue;
    codeSelectCodeMethod= codeMethod;
    codeSelectCodeType = codeType;
    codeSelectCodeRelation= codeRelation;
    codeSelectIsClear= isClear;
    codeSelectRealCondition = realCondition;
    codeSelectTypeParam = typeParam;
    codeSelectCallBackMethod=callBackMethod;
    codeSelectGetDataMethod=getDataMethod;
}

function private_Code_CallServiceByDialog(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
	private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
	var url = contextRootPath + "/common/PrepareCodeInput.jsp";
    var obj = new Object();  
    
    obj.pageNo="1";
    obj.pageSize="20";
    obj.targetFormName=targetFormName;
    obj.fieldIndex=codeSelectFieldIndex;
    obj.fieldValue=codeSelectFieldValue;
    obj.fieldNameValue=codeSelectFieldNameValue;
    obj.codeMethod=codeSelectCodeMethod;
    obj.codeType=codeSelectCodeType;
    obj.sendUrl=codeSelectCodeType;
    obj.codeRelation=codeSelectCodeRelation;
    obj.isClear=codeSelectIsClear;
    obj.otherCondition=codeSelectRealCondition;
    obj.typeParam=codeSelectTypeParam;
    obj.callBackMethod=codeSelectCallBackMethod;
    obj.getDataMethod=codeSelectGetDataMethod;
    obj.elementOrder=codeSelectElementOrder;
    obj.elementLength=codeSelectElementLength;
    window.prototype=obj;
    
    var iWidth=560; //弹出窗口的宽度;
    var iHeight=400; //弹出窗口的高度;
    var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
    var winOption = "height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+',scroll=yes,status=no,resizable:yes,help:no';
//    window.showModalDialog(url,window,"resizable:yes;dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:600px;dialogHeight:380px");
//    window.open(url, window,winOption);
    window.open(url,"",winOption);
    try {
        field.focus();
    }
    catch (E) {
    	alert("error:"+E.message);
    }
    //alert("callBack:"+codeSelectCallBackMethod);
    //do after window close
    private_Code_CallBack(codeSelectCallBackMethod);   
    
} 
 
/**
 * code input for data change
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeChange(field, codeType, codeRelation, isClear, otherCondition, typeParam, varParamField,callBackMethod, getDataMethod) {
	var codeMethod = "change";
    if(varParamField != undefined && trim(varParamField)!= ""){
    	var woundCode  = document.getElementsByName(varParamField)[0].value;
    	typeParam = getWoundType(trim(woundCode));
    }
    private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
    var url = contextRootPath + "/common/queryCodeInput.do"; 
    //进行url判断
    var flag = -1;
    flag = codeType.indexOf('.');
    if(flag > -1){
    	url = contextRootPath + codeType;
    }
    //json格式的参数信息
    /*
    var params={
    	actionType : "query",
    	fieldIndex : codeSelectFieldIndex,
	    fieldValue : codeSelectFieldValue,
	    codeMethod : codeSelectCodeMethod,
	    codeType : codeSelectCodeType,
	    codeRelation : codeSelectCodeRelation,
	    isClear : codeSelectIsClear,
	    otherCondition : codeSelectRealCondition,
	    typeParam : codeSelectTypeParam,
	    callBackMethod : codeSelectCallBackMethod,
	    getDataMethod : codeSelectGetDataMethod
    };
    */
    //字符串格式的参数信息
    var pars="?actionType=query";
    pars=pars+"&fieldIndex="+codeSelectFieldIndex;
    pars=pars+"&fieldValue="+codeSelectFieldValue;
    pars=pars+"&codeMethod="+codeSelectCodeMethod;
    pars=pars+"&codeType="+codeSelectCodeType;
    pars=pars+"&codeRelation="+codeSelectCodeRelation;
    pars=pars+"&isClear="+codeSelectIsClear;
    pars=pars+"&otherCondition="+codeSelectRealCondition;
    pars=pars+"&typeParam="+codeSelectTypeParam;
    pars=pars+"&callBackMethod="+codeSelectCallBackMethod;
    pars=pars+"&getDataMethod="+codeSelectGetDataMethod; 
    //ajax 同步调用
    /*
    var myAjax = new Ajax.Request(
        url,{method:'get',asynchronous:'false',parameters:pars,onComplete:setFieldValueForCodeChange}
    );
    */
    //最基础的ajax调用方法
    ajaxRequest(url+pars,"get",false,'',setFieldValueForCodeChange);
}
/** for query */
function setFieldValue() {
    inCodeQuery = false; 
    var elementOrder = parseInt(document.forms[0].elementOrder.value, 10);
    var elementLength = parseInt(document.forms[0].elementLength.value, 10);
    targetFormName = document.forms[0].targetFormName.value;
    //alert('order:'+elementOrder+'length:'+elementLength);
    //update safari不支持window.dialogArguments
    var callerWindowObj = null;
    if (window.dialogArguments){           
    	callerWindowObj = window.dialogArguments;       
    }else{                    
    	callerWindowObj = window.opener;            
    }
    //eval('callerWindowObj.'+document.forms[0].callBackMethod.value);
    //alert("openForm"+callerWindowObj.document.forms[targetFormName].name);
    var openerFm = callerWindowObj.document.forms[targetFormName];
    var relations = new Array();
    if (document.forms[0].codeRelation.value.indexOf(",") > -1) {
        relations = document.forms[0].codeRelation.value.split(",");
    } else {
        relations[0] = document.forms[0].codeRelation.value;
    }
    var fieldIndex = parseInt(document.forms[0].fieldIndex.value, 10);
    if (document.forms[0].codeselect.selectedIndex < 0) {
        document.forms[0].codeselect.selectedIndex = 0;
        return false;
    }
    //alert("selectIndex:"+document.forms[0].codeselect.selectedIndex);
    var value = ""; 
    var rowValues = new Array();
    var values = new Array();
    var selectedCount = 0;
    for (var i = 0; i < document.forms[0].codeselect.length; i++) {
        if (document.forms[0].codeselect.options[i].selected == true) {
            rowValues = new Array();
            var selectedValue = document.forms[0].codeselect.options[i].value;
            if (selectedValue.indexOf(FIELD_SEPARATOR) > -1) {
                rowValues = selectedValue.split(FIELD_SEPARATOR);
            } else {
                rowValues[0] = selectedValue;
            }
            values[selectedCount++] = rowValues;
        }
    }
    var relationsCount = relations.length;
    
    for (var i = 0; i < relationsCount; i++) {
        relations[i] = trim(relations[i]);
        if(relations[i]==null||relations[i]==""){
            continue;
        }
        value = values[0][i];
        if(i >= values[0].length) {
          break;
        }
        for (var j = 1; j < selectedCount; j++) {
            if (i >= values[j].length) {
                value = value + "," + values[j][values.length - 1];
            } else {
                value = value + "," + values[j][i];
            }
        } 
        var field = null;         
        var relation = parseInt(relations[i], 10);
        if(isNaN(relation)){ 
        	 	field = eval("openerFm."+relations[i]);
            if(elementLength>1){
                field = field[elementOrder];
            }
        }else{
            field = openerFm.elements[fieldIndex + relation];
            //alert("back to field:"+field.name);
        } 
        field.value=value;
    }
    window.close();
}
function cancelFieldValue() {
    inCodeQuery = false;
    var elementOrder = parseInt(document.forms[0].elementOrder.value, 10);
    var elementLength = parseInt(document.forms[0].elementLength.value, 10);
    targetFormName = document.forms[0].targetFormName.value;
    var callerWindowObj = null;
    if (window.dialogArguments){           
    	callerWindowObj = window.dialogArguments;       
    }else{                    
    	callerWindowObj = window.opener;            
    }
    
    var openerFm = callerWindowObj.document.forms[targetFormName];
    if (document.forms[0].isClear.value == "Y"||document.forms[0].isClear.value == "y") {
        var relations = new Array();
        if (document.forms[0].codeRelation.value.indexOf(",") > -1) {
            relations = document.forms[0].codeRelation.value.split(",");
        } else {
            relations[0] = document.forms[0].codeRelation.value;
        }
        var fieldIndex = parseInt(document.forms[0].fieldIndex.value, 10);
        var relationsCount = relations.length;
        for (var i = 0; i < relationsCount; i++) {
            relations[i] = trim(relations[i]);
            if(relations[i]==null||relations[i]==""){
                continue;
            }
            var field = null;         
            var relation = parseInt(relations[i], 10);
            if(isNaN(relation)){ 
                field = eval("openerFm."+relations[i]);
                if(elementLength>1){
                    field = field[elementOrder];
                }
            }else{
                field = openerFm.elements[fieldIndex + relation];
            } 
            field.value=""; 
        }
    }

    window.close();
}

function fieldOnKeyPress() {
    var charCode = window.event.keyCode;
    if (charCode == 13) { //enter
        setFieldValue();
    } else if (charCode == 27) { //escape 
        cancelFieldValue();     
	}
/*
    } else if (charCode == 38) { //up arrow
        if((fm.codeselect.selectedIndex==0)){
        		var currentPageNo = parseInt(fm.pageNo.value,10);
        		if(codeSelectHasSubmit){
        			  return;
        		}
          	if(currentPageNo>1){
          			locate(currentPageNo-1);
          			codeSelectHasSubmit = true;
          	}
        }
	} else if (charCode == 40) { //down arrow
        if((fm.codeselect.selectedIndex==fm.codeselect.options.length-1)){
        		var currentPageNo = parseInt(fm.pageNo.value,10);
        		if(codeSelectHasSubmit){
        			  return;
        		}
          	if(parseInt(fm.pagesCount.value,10)>currentPageNo){
          			locate(currentPageNo+1);
          			codeSelectHasSubmit = true;
          	}
        }        
    }  
*/
}

/** only for onchange */
function setFieldValueForCodeChange(xmlhttp){
    //记录输入域所在的form索引位置和元素顺序
	var inputValue = document.forms[targetFormName].elements[codeSelectFieldIndex].value;
    var elementOrder = getElementOrder(document.forms[targetFormName].elements[codeSelectFieldIndex])-1;
    //取得ajax调用返回的响应结果
    var value = trim(xmlhttp.responseText);
    var relations = new Array();
    //判断onChange方法改变后，要set值的相对位置关系列表
    if (codeSelectCodeRelation.indexOf(",") > -1) {
        relations = codeSelectCodeRelation.split(",");
    } else {
        relations[0] = codeSelectCodeRelation;
    }
    var relationsCount = relations.length; 
    var values = new Array();
    //处理响应结果为字符串数组
    if (value.indexOf(FIELD_SEPARATOR) > -1) {
        values = value.split(FIELD_SEPARATOR);
    } else {
        values[0] = value;
    }
    //当用户输入值不为空，而返回结果为空时的用户提示
    if((value == null || value == "") && inputValue != "")
    {
      alert(inputValue + " " + "\u4ee3\u7801\u4e0d\u5b58\u5728\uff01");
    }
    for (var i = 0; i < relationsCount; i++) { 
        relations[i] = trim(relations[i]);
        if(relations[i]==null||relations[i]==""){
            continue;
        }  
        var field = null;         
        var relation = parseInt(relations[i], 10);
        if(isNaN(relation)){ 
            field = eval("document.forms[targetFormName]."+relations[i]);
            if(field.length>1){
                field = field[elementOrder];
            }
        }else{
            field = document.forms[targetFormName].elements[codeSelectFieldIndex + relation];
        } 
        if(trim(value)!=""){
    			if (i < values.length) { 
							field.value = values[i];
						}
				}else{
		        if (codeSelectIsClear == "Y"||codeSelectIsClear == "y"){
		            field.value = "";
		        }else if(codeSelectIsClear == "H" || codeSelectIsClear == "h"){
		        		 //do nothing
		        }else if(codeSelectIsClear == "N" || codeSelectIsClear == "n"){
		        		if(i == 0){
		        				field.value = "";
		        		}
		      	}
    		} 
    }
    //触发回调函数
    private_Code_CallBack(codeSelectCallBackMethod);         
}
 
/**
 * eval callback method
 */ 
function private_Code_CallBack(callBackMethodValue){
    if (callBackMethodValue != "") {
	      var callbackValues = new Array(); 
	      if (callBackMethodValue.indexOf(";") > -1) {
	          callbackValues = callBackMethodValue.split(";");
	      }else{
	        	callbackValues[0] = callBackMethodValue;
	      }
	      var callbackCount = callbackValues.length;
	      for (var i = 0; i < callbackCount; i++) {
	          callbackValues[i] = trim(callbackValues[i]);
	          if(callbackValues[i]==null||callbackValues[i]==""){
	              continue;
	          }
	          //带参数的,解析动态参数。如果传递的是this，由于this是object类型，无法在此正确解析
	          //因此通过变相的方式传递一个常量this。根据是否为常量this获取当前输入域的索引位置。然后在回调函数中可根据索引位置获取当前输入域的值。
	          var functionName = callbackValues[i];
	          if(functionName.indexOf("()")<0){
	        	  var param  = functionName.substr(functionName.indexOf("(")+1,functionName.indexOf(")")-functionName.indexOf("(")-1);
	        	  if(param == 'this');
	        	  functionName = functionName.substr(0,functionName.indexOf("(")+1)+codeSelectFieldIndex+functionName.substr(functionName.indexOf(")"));
	          }
	          eval(functionName);
	      }
	  }
}

/**
 * trim
 */
 function trim(str) { 
 	if(str == undefined){
 		return "";
 	}
	return str.replace(/^\s*(.*?)[\s\n]*$/g, '$1'); 
 }
 
 function getWoundType(woundCode){
 	var woundType = "";
 	if("02" == woundCode){
 		woundType = "WoundDamage";
 	}else if("03" == woundCode){
 		woundType = "WoundDead";
 	}else{
 		woundType = "WoundInjure";
 	}
 	return woundType;
 }
 /**
  * 得到ajax对象
  */
 function getAjaxHttp() {
 	var xmlHttp;
 	try {
 		// Firefox, Opera 8.0+, Safari
 		xmlHttp = new XMLHttpRequest();
 		} catch (e) {
 			// Internet Explorer
 			try {
 				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
 			} catch (e) {
 				try {
 					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
 			} catch (e) {
 				alert("您的浏览器不支持AJAX！");
 				return false;
 			}
 		}
 	}
 	return xmlHttp;
 }
 /**
  * 发送ajax请求
  * url--url
  * methodtype(post/get)
  * con (true(异步)|false(同步))
  * parameter(参数)
  * functionName(回调方法名，不需要引号,这里只有成功的时候才调用)
  * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象)
  * obj需要到回调方法中处理的对象
  */
 function ajaxRequest(url,methodtype,con,parameter,functionName,obj){
 	var xmlhttp=getAjaxHttp();
 	xmlhttp.onreadystatechange=function(){
 		if(xmlhttp.readyState==4){
 			//HTTP响应已经完全接收才调用
 			functionName(xmlhttp,obj);
 		}
 	};
 	xmlhttp.open(methodtype,url,con);
 	xmlhttp.send(parameter);
 }
