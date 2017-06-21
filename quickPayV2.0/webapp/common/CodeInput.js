    var send_url   = null;
	//初始化页面的各个隐藏参数，用于保存父页面的信息
	function initParam(){
		var obj;
		if (window.dialogArguments!=undefined)
			obj = window.dialogArguments.prototype;
		else
			obj = window.opener.prototype;
        //若codeType不是以.do为后缀，则表示调用的是查询数据字典的公共方法。
		//比如第二个参数传ApplyStatus，会自动到数据字典表查询CodeType='ApplyStatus'的代码。
        var flag = -1;
        flag = obj.codeType.indexOf('.');
        if(flag<0){
        	send_url = contextRootPath + '/common/queryCodeInput.do';
        	fm.codeType.value=obj.codeType;
        }
        else send_url = contextRootPath + obj.sendUrl;
		fm.fieldIndex.value=obj.fieldIndex;
		fm.targetFormName.value=obj.targetFormName;
        fm.fieldValue.value=obj.fieldValue;
        fm.fieldNameValue.value=obj.fieldNameValue;
        fm.codeMethod.value=obj.codeMethod;
        fm.codeRelation.value=obj.codeRelation;
        fm.isClear.value=obj.isClear;
        fm.otherCondition.value=obj.otherCondition;
        fm.typeParam.value=obj.typeParam;
        fm.callBackMethod.value=obj.callBackMethod;
        fm.getDataMethod.value=obj.getDataMethod;
        fm.pageNo.value=obj.pageNo;
        fm.pageSize.value=obj.pageSize;
        fm.elementOrder.value=obj.elementOrder;
        fm.elementLength.value=obj.elementLength;
        //参数初始化
        if(fm.isClear.value==undefined || fm.isClear.value=="null"){
            fm.isClear.value="Y";
        }
        if(fm.otherCondition.value==undefined || fm.otherCondition.value=="null"){
            fm.otherCondition.value="";
        }
        if(fm.typeParam.value==undefined || fm.typeParam.value=="null"){
            fm.typeParam.value="";
        }
        if(fm.callBackMethod.value==undefined || fm.callBackMethod.value=="null"){
            fm.callBackMethod.value="";
        }
        if(fm.getDataMethod.value==undefined || fm.getDataMethod.value=="null"){
            fm.getDataMethod.value="";
        }
        if(fm.pageNo.value==undefined || fm.pageNo.value=="null"){
            fm.pageNo.value=1;
        }
        if(fm.pageSize.value==undefined || fm.pageSize.value=="null"){
            fm.pageSize.value=20;
        } 
        if(fm.elementOrder.value==undefined || fm.elementOrder.value=="null"){
            fm.elementOrder.value=0;
        } 
        if(fm.elementLength.value==undefined || fm.elementLength.value=="null"){
            fm.elementLength.value=1;
        }
        //判断是否允许多选
        if(fm.codeMethod.value=="select")
        	$('#codeselect').removeAttr('multiple');
    }
	/**
	 * 页面加载时，获取从后台查询方法中的数据信息
	 */
	function initData(){
		//服务器action方法处理的url
		var url = send_url;
		//传往action的参数信息
		var params = {
			fieldIndex : $('#fieldIndex').val(),
		    fieldValue : $('#fieldValue').val(),
		    fieldNameValue : $('#fieldNameValue').val(),
		    codeMethod : $('#codeMethod').val(),
		    codeType : $('#codeType').val(),
		    codeRelation : $('#codeRelation').val(),
		    isClear : $('#isClear').val(),
		    otherCondition : $('#otherCondition').val(),
		    typeParam : $('#typeParam').val(),
		    callBackMethod : $('#callBackMethod').val(),
		    getDataMethod : $('#getDataMethod').val(),
		    pageNo : 1,
		    pageSize : $('#pageSize').val(),
		    elementOrder : $('#elementOrder').val(),
		    elementLength : $('#elementLength').val()
		};
		//处理完成后的回调方法
		function callback(result){
			//清空之前的option内容
 		   $('#codeselect').html('');
 		   //获取总记录数records
  		   var total = result.totalRecords;
  		   if(total == 0){
  			 //alert("代码不存在");
  			 $.messager.alert('提示', '查询无记录！', 'info');
  			//   alert(result.msg);
  			//   window.close();
  		   }
  		   //解析json数组，分别组装成select中option的text和value选项
 		   var optionText = '';
 	       var optionValue = '';
 		   //获取json数组
 		   var jsonArray = result.label;
 		   var jsonValueArray = result.value;
 		   if(jsonArray == null){//默认的select展现方式，来自page的json结果
 			//获取json数组
 	 		   jsonArray = result.data;
 	 		   //解析json数组，分别组装成select中option的text和value选项
 				   $.each(jsonArray,function(idx,item){
 					   var jsonStr = item;
 					   optionText = '';
 		    	       optionValue = '';
 		    	       var k = 0;
 					   $.each(jsonStr,function(i){
 							var key = i;
 							var value = jsonStr[key];
 							//默认第一个为真正的value，其他顺序跟relation保持一致
 							if(optionValue == ''){
 								optionValue = value;
 								optionText = value;
 							}
 							else{
 								optionValue = optionValue + FIELD_SEPARATOR + value;
 								if(k<2)
 									optionText = optionText + '&nbsp;--&nbsp;' +value;
 							}
 							k++;
 						});
 					   $('#codeselect').append('<option value="'+optionValue+'">'+optionText+'</option><br/>');
 				   });
 		   }
 		   else{//另外一种展现方式，来自valueList和labelList modified by liuyatao 2013-10-15
 			  var number = jsonArray.length;
 			  //alert("number:"+number);
 			  for(var i = 0; i< number ;i++){
 	 	    	  optionText = jsonArray[i];
 	 	    	  optionValue = jsonValueArray[i];
 	 	    	 $('#codeselect').append('<option value="'+optionValue+'">'+optionText+'</option><br/>');
 	 	       }
 		   }
			   //加载分页项
		       $('#pp').pagination({ 
		    		total:total, 
		    		pageSize:20,
		    		displayMsg:'',
		    		//当发生页码转换时触发的动作,局部刷新页面信息
		    		onSelectPage: function(pageIndex, pageNumber){
		    			$(this).pagination('loading');
		      			$(this).pagination('loaded');
		    			reloadData(pageIndex,pageNumber);
		    		}
		    	});
		};
		//通过ajax方式从服务器端取出所需数据
		$.post(url,params,callback,'json');
	}
	/**
	 * 分页处理，重载json数据
	 */
	function reloadData(pageIndex,pageNumber){
		//服务器action方法处理的url
		var url = send_url;
		//传往action的参数信息
		var params = {
			fieldIndex : $('#fieldIndex').val(),
		    fieldValue : $('#fieldValue').val(),
		    fieldNameValue : $('#fieldNameValue').val(),
		    codeMethod : $('#codeMethod').val(),
		    codeType : $('#codeType').val(),
		    codeRelation : $('#codeRelation').val(),
		    isClear : $('#isClear').val(),
		    otherCondition : $('#otherCondition').val(),
		    typeParam : $('#typeParam').val(),
		    callBackMethod : $('#callBackMethod').val(),
		    getDataMethod : $('#getDataMethod').val(),
		    pageNo : pageIndex,
		    pageSize : pageNumber,
		    elementOrder : $('#elementOrder').val(),
		    elementLength : $('#elementLength').val()
		};
		//处理完成后的回调方法
		function callback(result){
			//清空之前的option内容
 		   $('#codeselect').html('');
 		   //获取json数组
 		   var jsonArray = result.label;
 		   var jsonValueArray = result.value;
 		   if(jsonArray == null){//默认的select展现方式，来自page的json结果
 			//获取json数组
 	 		   jsonArray = result.data;
			   $.each(jsonArray,function(idx,item){
				   var jsonStr = item;
				   optionText = '';
	    	       optionValue = '';
	    	       var k = 0;
				   $.each(jsonStr,function(i){
						var key = i;
						var value = jsonStr[key];
						//默认第一个为真正的value，其他顺序跟relation保持一致
						if(optionValue == ''){
							optionValue = value;
							optionText = value;
						}
						else{
							optionValue = optionValue + FIELD_SEPARATOR + value;
							if(k<2)
								optionText = optionText + '&nbsp;--&nbsp;' +value;
						}
						k++;
					});
				   $('#codeselect').append('<option value='+optionValue+'>'+optionText+'</option><br/>');
			   });
 		   }
 		   else{//另外一种展现方式，来自valueList和labelList modified by liuyatao 2013-10-15
 			  var number = jsonArray.length;
 			  //alert("number:"+number);
 			  for(var i = 0; i< number ;i++){
 	 	    	  optionText = jsonArray[i];
 	 	    	  optionValue = jsonValueArray[i];
 	 	    	 $('#codeselect').append('<option value="'+optionValue+'">'+optionText+'</option><br/>');
 	 	       }
 		   }
		}
		//通过ajax方式从服务器端取出所需数据
		$.post(url,params,callback,'json');
	}
    /* 页面加载时调用*/
    $(document).ready(function(){
    	//初始化参数
    	initParam();
    	//初始化数据
    	initData();
    });