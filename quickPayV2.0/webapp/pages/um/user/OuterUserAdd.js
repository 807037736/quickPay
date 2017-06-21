var len = -1;
$(function(){
	$('#addOneMore').click(function(){
		len++;
		var newNode = '<tr><td class="bgc_tt"><input type="text" class="input_y w_15" name="umTUserpowers['+len+'].id.dictionarycode"/></td><td class="bgc_tt"><input type="text" name="dictionaryName" readOnly /></td><td class="bgc_tt"><ce:select  inputName="OperationSymbol"/></td><td class="bgc_tt"><input type="text" name="umTUserpowers['+len+'].powervalue"/></td><td class="bgc_tt"><input type="button" class="button_ty" value="查看表信息"/></td><td class="bgc_tt"><input type="button" class="button_ty" value="删除"/>';	
		$('#userpower_table').append(newNode);
	});
		
	$('#userpower_table tr td:nth-child(6) .button_ty').live('click',function(){
		var idx = 0;
		$(this).parent().parent().remove();
		      
		/*循环遍历删除后的每个UserPower组，重新调整其name值 ，使之是连贯的*/
		$('#userpower_table tr:gt(0)').each(function(){
			$(this).children().eq(0).find("input").attr('name','umTUserpowers['+idx+'].id.dictionarycode');
			$(this).children().eq(2).find("input").attr('name','umTUserpowers['+len+'].operationsymbol');
			$(this).children().eq(3).find("input").attr('name','umTUserpowers['+len+'].powervalue');
			idx++;
		});
	});

});


function executeSave(){
	alert("haha!");
}