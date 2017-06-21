
/****************************************************************************
 * DESC       :MulLine.js, don't modify. 
 * CREATEDATE :2006-09-11
 * MODIFYLIST :   Name       Date            Reason/Contents
 *          ------------------------------------------------------
 *
 ****************************************************************************/
var recentDeletedRowNo = 0;
var recentDeletedTBody = null;
/**
 u8fd4u56deu6700u8fd1u88abu5220u9664u7684u884cu7684u5e8fu53f7,u5982u679cu662fu4e00u6b21u5220u9664u591au884cuff0cu5219u4e3au5220u9664u7684u7b2cu4e00u884cu7684u5e8fu53f7
 */
function getRecentDeletedRowNo() {
	return recentDeletedRowNo;
}
/**
 u76f4u63a5u8c03u7528u63d2u5165u51fdu6570,u4ec5u4f9bu9ad8u7ea7u7528u6237u4f7fu7528
 */
function directInsertRow(pageCode, dataPageCode, field) {
	return private_insertRow(pageCode, dataPageCode, field);
}
/**
 u76f4u63a5u8c03u7528u5220u9664u51fdu6570,u4ec5u4f9bu9ad8u7ea7u7528u6237u4f7fu7528
 */
function directDeleteRow(field, pageCode, pageDataRowsCount, controlRowsCount) {
	return private_deleteRow(field, pageCode, pageDataRowsCount, controlRowsCount);
}
/**
 u76f4u63a5u8c03u7528u8bbeu7f6eu989cu8272u51fdu6570,u4ec5u4f9bu9ad8u7ea7u7528u6237u4f7fu7528
 */
function directSetRowColor(pageCode, dataPageCode, index, color) {
	private_setRowColor(pageCode, dataPageCode, index, color);
}
/**
 u63d2u5165u51fdu6570,Frameworku4f7fu7528
 */
function insertRow(pageCode, field) {
	var obj;
	var index;

  //Call beforeInsertRow of pageCode
	obj = eval("window.beforeInsert" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		obj.apply(this, arguments);
	}

  //call realy insertRow of pageCode
	obj = eval("window.insert" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		index = obj.apply(this, arguments);
	} else {  //default method
		index = directInsertRow(pageCode, pageCode + "_Data", field);
	}

  //Call afterInsertRow of pageCode
	obj = eval("window.afterInsert" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		obj.apply(this, arguments);
	}


//  directSetRowColor(pageCode,pageCode+"_Data",index,"#EEECFA")
}
/**
 u5220u9664u51fdu6570,Frameworku4f7fu7528
 */
function deleteRow(field, pageCode, pageDataRowsCount, controlRowsCount) {
	var obj;
	var index;
	if (pageDataRowsCount == undefined) {
		pageDataRowsCount = 1;
	}
	if (controlRowsCount == undefined) {
		controlRowsCount = 1;
	}
  //Call beforeDeleteRow of pageCode
	obj = eval("window.beforeDelete" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		obj.apply(this, arguments);
	}

  //call realy insertRow of pageCode
	obj = eval("window.delete" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		index = obj.apply(this, arguments);
	} else {  //u5982u679cu6ca1u6709u81eau5b9au4e49u5220u9664u65b9u6cd5u5219u8c03u7528u9ed8u8ba4u7684u5220u9664u65b9u6cd5
		index = directDeleteRow(field, pageCode, pageDataRowsCount, controlRowsCount);
	}

  //Call afterDeleteRow of pageCode
	obj = eval("window.afterDelete" + upperCaseFirstChar(pageCode));
	if (obj != null) {
		obj.apply(this, arguments);
	}
}
/**
  u5728u8868u683cu4e0bu65b9u6dfbu52a0u4e00u7ec4u6570u636euff0cu7981u6b62u975eu672cu6a21u5757u8c03u7528
  u53c2u6570u4e3au9875u4ee3u7801u540du79f0u548cu9875u539fu59cbu6570u636eu4ee3u7801u540du79f0
  u4f8b:insertRow("Engage","Engage_Data");
  u8fd4u56deu63d2u5165u884cu7684u5e8fu53f7uff08u4ece1u5f00u59cbuff09
  */
function private_insertRow(pageCode, dataPageCode, field) {
	var oTBODY = field;
	while (oTBODY != null && oTBODY.tagName != "TABLE" && oTBODY.id != pageCode) {
		oTBODY = oTBODY.parentElement;
	}
	oTBODY = oTBODY.tBodies[0];
	var oTBODYData = document.getElementById(dataPageCode).tBodies.item(0);
	var rowsCount = oTBODYData.rows.length;
	for (var i = 0; i < rowsCount; i++) {
		oTBODY.appendChild(oTBODYData.rows[i].cloneNode(true));
		/*var rowData = oTBODY.rows[oTBODY.rows.length-1];
    var elements=rowData.getElementsByTagName("INPUT");
    for(var j=0;j<elements.length;j++){   
      var element = elements[j];
      if(element!=null){
        jcv_custom_initElement(element);
      } 
    }
    elements=rowData.getElementsByTagName("SELECT");
    for(var j=0;j<elements.length;j++){   
      var element = elements[j];
      if(element!=null){
        jcv_custom_initElement(element);
      } 
    }
    elements=rowData.getElementsByTagName("TEXTAREA");
    for(var j=0;j<elements.length;j++){   
      var element = elements[j];
      if(element!=null){
        jcv_custom_initElement(element);
      } 
    }*/
	}
	return rowsCount;
}
/**
  u5220u9664u63a7u5236u6309u94aeu63a7u5236u7684u884cuff0cu7981u6b62u975eu672cu6a21u5757u8c03u7528
  u5b57u6bb5uff0cu9875u540du79f0uff0cu6570u636eu9875u4e2du63a7u5236u6309u94aeu7684u4e2au6570uff0cu6570u636eu9875u4e2du6bcfu4e2au63a7u5236u6309u94aeu7684u63a7u5236u7684TRu7684u4e2au6570
  u8fd4u56deu5220u9664u884cu7684u5e8fu53f7uff08u4ece1u5f00u59cbuff09
 */
function private_deleteRow(field, pageCode, pageDataRowsCount, controlRowsCount) {
	var oTBODY = field;
	while (oTBODY != null && oTBODY.parentElement != null && oTBODY.tagName != "TBODY" && oTBODY.parentElement.id != pageCode) {
		oTBODY = oTBODY.parentElement;
	}
	var tempElements = oTBODY.getElementsByTagName(field.tagName);
	var tempElementsCount = tempElements.length;
	var order = 0;
	for (var i = 0; i < tempElementsCount; i++) {
		if (tempElements[i].name == field.name) {
			order++;
		}
		if (tempElements[i] == field) {
			break;
		}
	}
	order = order - pageDataRowsCount;  //u53bbu6389u9690u542bu57dfu4e2du7684u63a7u5236u6309u94aeu7684u4e2au6570
	for (var i = 0; i < controlRowsCount; i++) {
		oTBODY.removeChild(oTBODY.rows[order * controlRowsCount]);
	}
	recentDeletedTBody = oTBODY;
	recentDeletedRowNo = order;
	return order;
}
/**
 * u8bbeu7f6eu4e00u884cu7684u989cu8272
 */
function private_setRowColor(pageCode, dataPageCode, index, color) {
	var i = 0;
	var command = "";
	var elements = getTableElements(dataPageCode);
	var elementsCount = elements.length;
	for (i = 0; i < elementsCount; i++) {
		command = "document.getElementsByName('" + elements[i].name + "')[" + index + "].style.backgroundColor = color;";
		eval(command);
	}
}
function getOrderForMulLine(pageCode, field, parentPageCode) {
	var oTable = private_getMulLineTBody(pageCode, field).parentElement;
	var oTBODY = private_getMulLineTBody(parentPageCode, field);
	var tempTables = oTBODY.getElementsByTagName("TABLE");
	var index = 0;
	for (var i = 0; i < tempTables.length; i++) {
		if (tempTables[i].id == pageCode) {
			index++;
		}
		if (tempTables[i] == oTable) {
			break;
		}
	}
	var tempField = oTBODY.getElementsByTagName("INPUT");
	var order = 0;
	var value;
	for (var i = 0; i < tempField.length; i++) {
		if (tempField[i].name != field.name) {
			continue;
		}
		order++;
		if (order == index) {
			return getElementOrder(tempField[i]);
		}
	}
	return -1;
}
function getFirstOrderForMulLine(pageCode, field, elementName, tagName) {
	var oTable = private_getMulLineTBody(pageCode, field).parentElement;
	var tempField = oTable.getElementsByTagName(tagName);
	for (var i = 0; i < tempField.length; i++) {
		if (tempField[i].name == elementName) {
			return getElementOrder(tempField[i]);
		}
	}
	return -1;
}
function getLastOrderForMulLine(pageCode, field, elementName, tagName) {
	var oTable = private_getMulLineTBody(pageCode, field).parentElement;
	var tempField = oTable.getElementsByTagName(tagName);
	for (var i = tempField.length - 1; i >= 0; i--) {
		if (tempField[i].name == elementName) {
			return getElementOrder(tempField[i]);
		}
	}
	return -1;
}
function getInnerOrderForMulLine(pageCode, field) {
	var oTable = private_getMulLineTBody(pageCode, field).parentElement;
	var tempField = oTable.getElementsByTagName(field.tagName);
	var order = 0;
	for (var i = 0; i < tempField.length; i++) {
		if (tempField[i].name == field.name) {
			order++;
		}
		if (tempField[i] == field) {
			return order;
		}
	}
	return -1;
}
function getTableOrderForMulLine(parentPageCode, pageCode, object) {
	var oTable = private_getMulLineTBody(pageCode, object).parentElement;
	var oParentTable = private_getMulLineTBody(parentPageCode, oTable);
	var tempTables = oParentTable.getElementsByTagName("TABLE");
	var order = 0;
	for (var i = 0; i < tempTables.length; i++) {
		if (tempTables[i].id == oTable.id) {
			order++;
		} else {
			continue;
		}
		if (tempTables[i] == oTable) {
			return order;
		}
	}
	return -1;
}
function private_getMulLineTBody(pageCode, object) {
	var oTBODY = object;
	while (oTBODY != null && oTBODY.parentElement != null && oTBODY.tagName != "TBODY" && oTBODY.parentElement.id != pageCode) {
		oTBODY = oTBODY.parentElement;
	}
	return oTBODY;
}
function getRecentDeletedTBody() {
	return recentDeletedTBody;
}
function deleteRowInTable(field, TableID, args1, args2, args3) {
	var eventSource = null;
	if (window.event != null && window.event.srcElement != null) {
		eventSource = window.event.srcElement.name;
	}
	deleteRow(field, TableID, args1, args2);
}
function insertRowInTable(field, TableID, args1, args2) {
	insertRow(TableID, field);
}
function upperCaseFirstChar(str) {
	if (str == null || str.length < 1) {
		return str;
	}
	var convertStr = str.substring(0, 1).toUpperCase() + str.substring(1);
	return convertStr;
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

function getElementOrder(field) {
    var i = 0;
    var order = 0;
    var elements = document.getElementsByName(field.name);
    var elementsCount = elements.length;
    for (i = 0; i < elementsCount; i++) {
        order++;
        if (elements[i] == field) {
            break;
        }
    }
    return order;
}

function isFirstElement(field) {
    var elements = document.getElementsByName(field.name);
    if (elements[0] == field) {
        return true;
    } else {
        return false;
    }
}
function getElementCount(fieldName) {
    var count = 0;
    count = document.getElementsByName(fieldName).length;
    return count;
}