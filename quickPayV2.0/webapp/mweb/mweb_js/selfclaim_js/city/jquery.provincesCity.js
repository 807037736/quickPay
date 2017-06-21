/**
 * jQuery :  城市联动插件
 * @author   Jiny
 *
 * @example  $("#test").ProvincesCity();
 * @params   暂无
 */
;(function($){
	$.fn.extend({
		ProvincesCity:function(){
			var _self = this;		//指向该DOM对象被封装过的jQuery对象,插件内部this指向jQuery对象
			//定义三个默认值
			_self.data("province",["请选择",""]);
			_self.data("city1",["请选择",""]);
			// _self.data("city2",["请选择","请选择"]);
			//插入三个空的下拉框
			_self.append("<select name='app.province'><select>");
			_self.append("<select name='app.city'><select>");
			// _self.append("<select><select>");
			//分别获取3个下拉框
			var $sel1 = _self.find('select').eq(0);
			var $sel2 = _self.find('select').eq(1);
			// var $sel3 = _self.find('select').eq(2);
			//默认省级下拉
			if(_self.data("province")){
				$sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");	
			}
			$.each(GP,function(index,data){
				$sel1.append("<option value='"+data+"'>"+data+"</option>");	
			});
			//默认1级城市下拉
			if(_self.data("city1")){
				$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
			}
			//默认2级城市下拉
			// if(_self.data("city2")){
			// 	$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
			// }
			//省级联动控制
			var index1 = "";
			$sel1.change(function(){
				//清空其他两个下拉框
				$sel2[0].options.length = 0;	/////提取实际的DOM元素,而不必使用jQuery的功能。相当于$sel2.get(0)。毕竟经过了jQuery封装，已经不是原来的HTMLElement对象，而是Object。
				// $sel3[0].options.length = 0;
				index1 = this.selectedIndex;
				if(index1 == 0){	//当选择的为“请选择”时
					if(_self.data("city1")){
						$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");	
					}
					if(_self.data("city2")){
						$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
					}
				} else {
					$.each(GT[index1-1],function(index,data){
						$sel2.append("<option value='"+data+"'>"+data+"</option>");
					});
					// $.each(GC[index1-1][0],function(index,data){
					// 	$sel3.append("<option value='"+data+"'>"+data+"</option>");
					// });
				}
			}).change();
			////一级城市联动控制
			// var index2 = "";
			// $sel2.change(function(){
			// 	$sel3[0].options.length = 0;
			// 	index2 = this.selectedIndex;
			// 	$.each(GC[index1-1][index2],function(index,data){
			// 		$sel3.append("<option value='"+data+"'>"+data+"</option>");	
			// 	});
			// });
			return _self;
		}
	});
})(jQuery);