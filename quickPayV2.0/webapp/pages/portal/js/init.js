/**
 * 初始化port页面
 * 
 * @author zhou&ding
 */

var Jh = {	
	Config:{// CLASS样式配置
		tableCls:"form-list",
		tdCls:"form-text",
		tdCls2:"single",
		ulCls : "tag-list",
		layCls :"layout-list",
		min :"min",
		mintext:"收起",
		max :"max",
		maxtext:"展开",
//		maxwindow:"maxwindow",
//		maxwindowtext:"最大化",
		close :"close",
		closetext :"关闭",
		refreshtext:"刷新",
		refresh :"refresh",
		_groupItemContent : "itemContent",
		_groupItemHead : "itemHeader",
		_groupWrapperClass  : "groupWrapper",
		_groupItemClass : "groupItem",
		toolsClass: "detail_head detail_cent"
	}	
};
Jh.Layout=function(me){
	var _left = "portal_l"	,
//		_center ="portal_m",
		_right ="portal_r";
	return me = {
		location:{// 三列容器
			left:_left,
//			center:_center,
			right:_right		
		},
		locationId : {
			left:"#"+_left,
//			center:"#"+_center,
			right:"#"+_right
		},
		layoutCss : {
			0:"1:1"
//			1:"3:1",
//			2:"1:2:1",
//			3:"1:1:2",
//			4:"2:1:1",
//			5:"1:1:1"
		},
		layoutText : {
			0 :"w250 w250 wnone"
//			1 :""w750 w250 wnone",
//			2 :"w250 w500 w250",
//			3 :"w250 w250 w500",
//			4 :"w500 w250 w250",
//			5 :"w250 w250 w250"
		}
	};
}();

Jh.Util = {// 工具类
	format : function (str, model) {// 格式化指定键值的模板
		for (var k in model) {
			var re = new RegExp("{" + k + "}", "g");
			str = str.replace(re, model[k]);
		}
		return str;
	},
	refresh:function(){// 刷新3个布局
		$("#"+Jh.Layout.left,"#"+Jh.Layout.right).sortable('refresh');
	},
	toBody:function(o){// 往Body添加对象
		$("body").append(o);
	}
};

//Jh.Tools = function(me){// 创建工具条区
//	return me={	
//		init:function(){//初始化
//			me._ele = {};
//			me._create();
//		},
//
//		_create:function(){//创建
//			var _box = $("<div class='detail_head detail_cent'/>");
//			var _edit= $("<li class='system spr2'><a href='#' class='a2'></a></li>");
//			me.box = _box;
//			Jh.button._bindEdit(_edit);
//			Jh.Util.toBody(_box);
//			_box.append("<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav1'></i> 车险报价器</a> </li>" +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav2'></i> 车型信息查询</a> </li>" +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav3'></i> 邮件</a> </li>" +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav4'></i> 费用报销系统</a> </li>" +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav5'></i> 快速投保</a> </li>"  +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav6'></i> 市场地图</a> </li>" +
//					"<li class='nav1_line'><a href='#' class='a1'> <i class='spr nav7'></i> 产品库</a> </li> ").append(_edit);
//		}	
//	};
//}();
Jh.button = function(me){// 按钮区
//	var obj=$(me);
	return me = {
		init:function(data){// 初始化
		    me._ele = {};
//			me._create();
//			me._createWrap(data);
		},
//		_create:function(){// 创建自己
//			var _box = $("<div id='button'/>");
//			me.box = _box; 
//			Jh.Util.toBody(_box);// 往Body里添加自己
//		},
//		
//		_createWrap:function(d){// 创建外层容器
//			var _table = me._createTable(Jh.Config.tableCls);
//			me._ele.table = _table;
//			me._createActionButton();
//			me._addPanel(_table);			
//		},
//		
//		_createTable:function(clsName){	// 创建表格
//			var _t = $("<table/>").addClass(clsName);
//			$("<tbody/>").append(me._createActionTr())
////			             .append(me._createActionTr2())
//						 .appendTo(_t);	
//			return _t; 	
//		},
//		
//		_createActionTr:function(){// 创建按钮tr
//			var _td = me._createTd(Jh.Config.tdCls2),
//				_tr = $("<tr>").append(me._createTd(Jh.Config.tdCls)).append(_td);
//			me._ele.atd = _td;
//			return _tr;
//		},
//		
////		_createActionTr2:function(){// 创建按钮tr
////			var _td2 = me._createTd(Jh.Config.tdCls2),
////				_tr = $("<tr>").append(me._createTd(Jh.Config.tdCls))
////							.append(_td2);
////			me._ele.atd2 = _td2;
////			return _tr;
////		},
//		
//		_createActionButton:function(){// 创建功能按钮
//		    var _edit= $("<a class='button b' href='#' >编辑</a>");
//		//  var _add = $("<a class='button b' href='#' >添加模块</a>").hide();
//		    var _useradd = $("<a class='button b' href='#'>添加显示模块</a>").hide();
//			var _save= $("<a class='button b' href='#' >保存配置</a>").hide();
//			var _back= $("<a class='button b' href='#' >返回</a>").hide();
//		//  me._bindEdit(_edit);
//		//	me._ele.atd.append(_edit);
//			me._ele.atd.append(_useradd).append(_save);
//		//	me._bindAdd(_add);
//			me._bindUseradd(_useradd);
//			me._bindSave(_save);
//		//	me._bindBack(_back);
//			me.edit = _edit;
//		//	me.add = _add;
//			me.save = _save;
//			me.useradd = _useradd;
//		//	me.back = _back;
//		},
//		
//		_createTd:function(clsName,text){// 创建td
//			var t = $("<td>").addClass(clsName);
//			if(text!=undefined){
//				t.text(text);
//			}
//			return  t; 
//		},
//		_addPanel:function(o){
//			me.box.append(o);		
//		},
		
		_bindAdd:function(obj){// 添加模块
			obj.click(function(){
				 var clicked = function(){
					 var form = $(this).children('form'),
						 name = form.children('#modulename').val(),
						 key  = form.children("#modulekey").val();	
//						 layout = form.children("input[name='modulelayout']:checked").val(),
						 position = $("#"+Jh.Layout.location.left) ;
//					if(layout=='left'){
//						position = $("#"+Jh.Layout.location.left);
//					}else if(layout=='center'){
//						position = $("#"+Jh.Layout.location.center);
//					}else{
//						position = $("#"+Jh.Layout.location.right);
//					}
					addPortlet(name,key);// 保存添加模块的name、url
					// Jh.fn._ele.ul.append(Jh.fn._createLi(key,name));// 添加功能标签
					 position.append(Jh.Portal._createPortalOne(key,name));// 添加portal
					 //self.location.reload();
					 $.fallr('hide');
				 };
				 $.fallr('show', {
					 buttons : {
						 button1 : {text: '确定', onclick: clicked},
						 button2 : {text: '取消'}
					 },
					 content : '<form style="margin-left:20px">'+
								 '<p>模块名：</p><input type="text" size="15" id="modulename" />'+
								 '<p>模块Code：</p><input type="text" size="15" id="modulekey" />'+
//								 '<p>模块位置：</p>左:<input type="radio" name="modulelayout" checked="checked" value="left"/>&nbsp&nbsp'+
//												 '中:<input type="radio" name="modulelayout" value="center"/>&nbsp&nbsp'+
//												 '右:<input type="radio" name="modulelayout" value="right"/>'+								
								'</form>',
					 icon    : 'add',
					 position: 'center'
				 });
			});
		
		},
		_bindUseradd:function(obj){// 添加显示模块			
			obj.click(function(){
				var	left = $("#"+Jh.Layout.location.left).sortable('toArray'),
			    right = $("#"+Jh.Layout.location.right).sortable('toArray'),
			    lens = $('#tt').tabs('tabs').length;
				var middle=[];
				for(var i=0;i<lens;i++){
					var tabId=$('#tt').tabs('tabs')[i].panel('options').id;
					middle[i] = tabId;
				}
				
				var url = contextRootPath + '/portal/userAdd.do';
				var handle = window.showModalDialog(url,window,"dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:350px;dialogHeight:450px;resizable:yes");
				if(handle!=null){
					for(var i=0;i<left.length;i++)
						if(handle['key']==left[i]){
							alert("请不要重复添加！");
							return false;
						}
					for(var i=0;i<right.length;i++)
						if(handle['key']==right[i]){
							alert("请不要重复添加！");
							return false;
						}
					for(var i=0;i<middle.length;i++)
						if(handle['key']==middle[i]){
							alert("请不要重复添加！");
							return false;
						}
					 //alert('key = ' + handle['key']);					 
					$("#"+Jh.Layout.location.left).append(Jh.Portal._createPortalOne(handle['key'],handle['name']));// 添加portal	
					$("."+Jh.Config._groupItemHead).children().show();
					// Jh.fn._ele.ul.append(Jh.fn._createLi(handle['key'],handle['name'].title));;// 添加功能标签						 
				}
				//editRecord(contextRootPath + '/portal/userAdd.do');
			});
		},	
		
		_bindSave:function(obj){// 保存模块配置
			obj.click(function(){
				var	left = $("#"+Jh.Layout.location.left).sortable('toArray'),
				//	middle = $("#"+Jh.Layout.location.center).sortable('toArray'),
					right = $("#"+Jh.Layout.location.right).sortable('toArray'),
					_a = $("."+Jh.Config.layCls+" a");
//					_layout ="";
//				_a.each(function(){
//					if($(this).hasClass("active")){
//						_layout = $(this).attr("rel");
//					}
//				});
//				
//				var	baseConfig="<p>left:["+left+"]</p><p>right["+right+"]</p>"+
//							    "<p>当前布局:"+_layout+"</p>";
//					
//				 $.fallr('show', {        
//					 content : baseConfig,
//					 position: 'center'
//				 });
//				updateIsclosed();	// 更新isclosed全部为1
//			    savePortletLeft(left);	// 保存第一列portlet
//			    savePortletMiddle(middle);	// 保存第二列portlet
//			    savePortletRight(right);	// 保存第三列portlet
//			    saveLayout(_layout);		// 保存布局信息

				
				//获取tabs的个数
				var lens = $('#tt').tabs('tabs').length;
				//获取tabs的标题
				if(lens!='0'){
					var title = $('#tt').tabs('tabs')[lens-1].panel('options').title;
				}
				var middle=[];
				for(var i=0;i<lens;i++){
					var tabId=$('#tt').tabs('tabs')[i].panel('options').id;
					middle[i] = tabId;
				}
			    savePortlet(left,middle,right);
			    //alert("保存成功");
			});
		
		},
		
		_bindEdit:function(obj){// 编辑
			var i=0;
			obj.click(function(){
			  if(i==0){
				  me._bindEvent();
				  me._bindEventPortal();
			//	  Jh.fn._ele.layoutTd.show();//显示隐藏布局
			//	  Jh.fn._ele.layoutTr.show();
			//	  Jh.fn._ele.mtd.show();//显示隐藏功能模块
			//	  Jh.fn._ele.mtr.show();
			//	  Jh.button.add.show();//显示隐藏按钮
			//	  Jh.button.save.show();
			//	  Jh.button.useradd.show();
				  $(button).show();
				  $('#tab-tools').show();
				  document.getElementById('tt').style.display='';
				  $("."+Jh.Config._groupItemHead).children().show();
				  i=1;
			  }else if(i==1){
			//	  Jh.fn._ele.layoutTd.hide();//显示隐藏布局
			//	  Jh.fn._ele.layoutTr.hide();
			//	  Jh.fn._ele.mtd.hide();//显示隐藏功能模块
			//	  Jh.fn._ele.mtr.hide();
			//	  Jh.button.add.hide();//显示隐藏按钮
				  $(button).hide();
				  $('#tab-tools').hide();
			//	  Jh.button.save.hide();
			//	  Jh.button.useradd.hide();
				  me._eventDisableMin();
				  me._eventDisableMax();
				  me._eventDisableRemove();
				  me._eventDisableSortable();
				  var lens = $('#tt').tabs('tabs').length;
				  if(lens=='0'){
					  document.getElementById('tt').style.display='none';
				  }
				  //隐藏portlet按钮
				  $("."+Jh.Config._groupItemHead).children().siblings('div.action').hide();
				  i=0;
			  } 
			 // Jh.button.useradd.show();
			 // Jh.button.back.show();
			 //Jh.button.edit.hide();// 隐藏编辑按钮
			});
		},
		
//		_bindBack:function(obj){// 返回
//		    obj.click(function(){
//		      self.location.reload();
//		   });
//		},
		
		_bindEvent:function(){// 事件绑定
			me._moduleLiClick();
			me._layoutAClick();
		},
		
		_bindEventPortal:function(){	// 绑定面板所有事件
			me._eventSortable();
			me._eventRemove();
			me._eventMax();
			me._eventMin();
		},
		
		_moduleLiClick:function(){// 绑定模块LI单击事件
			$("."+Jh.Config.ulCls+" li").live("click",function(){
				var _this = $(this),
					_mName = _this.find("a").attr("rel"),// 获取模块名
					_m = $("#"+_mName), // 模块div
					_d = _this.find(".ok");// 对号
					
				if(_d.is(":visible")){// 判断是否显示
					_d.hide();// 隐藏对号
					_m.hide();// 隐藏模块
				}else{
					_d.show();// 显示对号
					_m.show();// 显示模块
				}
				Jh.Util.refresh();
				
			});
		},
		
		_layoutAClick:function(){// 绑定布局列表A 单击事件
			$("."+Jh.Config.layCls+" a").click(function(){
				var _this = $(this);
				var _v = _this.attr("rel");
				me._ToLayout(_v);
				_this.addClass("active").siblings().removeClass("active");
			});
		},
		
		_ToLayout:function(v){// 刷新布局
			var CssMode= Jh.Layout.layoutCss, // 布局模式
				CssText= Jh.Layout.layoutText,// css
				ModulesId= Jh.Layout.locationId, // 模块id
				CssTextId=0,// 默认css数组编号
				ModuleItems="";// 模块数组
			$.each(CssMode, function(m, mn){
				if(v==mn) CssTextId=m;// css 赋值
			});	
	
			$.each(ModulesId, function(s, sn){					
				var currentModule = $(sn),				
					cssName = CssText[CssTextId],
					ary = cssName.split(/\s+/);// 得到当前布局css数组
				switch(s){
					case "left": s =0;
					break;
				//	case "center": s =1;
				//	break;
					case "right":s = 2;
				}	
				if(ary[s]=='wnone') {// 出现布局由3->2的变化 ,最右边栏目的内容搬到最左边
					ModuleItems=currentModule.sortable('toArray');// 获取最新的的元素
					$.each( ModuleItems, function(m, mn){				
						$("#"+Jh.Layout.location.left).append($("#"+mn));// 注意在两栏三栏间切换的时候
																			// 返回已经丢失的模块,而且只能够逐个添加元素，不可以一次添加多个
					});
					currentModule.empty();// 摧毁原有的元素，以免重复出现冲突
				}		
				currentModule.removeClass("w250 w250 wnone").addClass(ary[s]);// 增加css
			});
	
		},
		
		_eventMin:function(){		
			$("."+Jh.Config.min).live("click",function(){// 关闭面板
				var _me = $(this);
				var _groupItem = _me.parent().parent().parent();
				_groupItem.find("."+Jh.Config._groupItemContent).hide();
				_groupItem.find("."+Jh.Config.max).show();
				_me.hide();
			});	
		},
		
		_eventMax:function(){			
			$("."+Jh.Config.max).live("click",function(){// 展开面板
				var _me = $(this),
					_groupItem = _me.parent().parent().parent();
				_groupItem.find("."+Jh.Config._groupItemContent).show();
				_groupItem.find("."+Jh.Config.min).show();
				_me.hide();
			});
		},
		
		_eventRemove:function(){
			$("."+Jh.Config.close).live("click",function(){// 移除面板
				var _this  = $(this),
					_p  = _this.parent().parent().parent();// 得到当前父级面板
				_p.fadeOut('500',function(){// 500毫秒后移除
					var _this = $(this);
					var _id = _this.attr("id");// 得到模块id
					var _a  = $(".tag-list").find("a[rel='"+_id+"']");
					_this.remove();
					_a.parent().remove();// 移除功能列表中的li
				});			
			});
		},		
		
		
		_eventSortable:function(){// 绑定排序
			$("."+Jh.Config._groupWrapperClass).sortable({
				connectWith: "."+Jh.Config._groupWrapperClass,
				revert: true,
				opacity :"0.6",	
				dropOnEmpty:true
			}).disableSelection();	
		},
		
		_eventDisableMin:function(){// 移除关闭面板事件		
			$("."+Jh.Config.min).die("click");	
		},
		
		_eventDisableMax:function(){// 移除展开面板事件			
			$("."+Jh.Config.max).die("click");
		},
		
		_eventDisableRemove:function(){// 移除移除面板事件
			$("."+Jh.Config.close).die("click");
		},
		
		_eventDisableSortable:function(){// 移除排序事件
			$("."+Jh.Config._groupWrapperClass).sortable('destroy');
		}
    };		
}();

//Jh.fn = function(me){// 功能区
//	return me = {
//		init:function(data){// 初始化
//			me._ele = {};
//			me._create();
//			me._createWrap(data);
//		},
//		
//		_create:function(){// 创建自己
//			var _box = $("<div id='header'/>");
//			me.box = _box; 
//			Jh.Util.toBody(_box);// 往Body里添加自己
//		},
//		
//		_createWrap:function(d){// 创建外层容器
//			var _table = me._createTable(Jh.Config.tableCls);
//			me._ele.table = _table;	
//			me._createModuleList(d);
//			me._addPanel(_table);			
//		},
//		
//		_createTable:function(clsName){	// 创建表格
//			var _t = $("<table/>").addClass(clsName);
//			$("<tbody/>").append(me._createLayoutTr())
//								  .append(me._createBaseTr())					 
//								  .appendTo(_t);	
//			return _t; 	
//		},
//		
//		_createBaseTr:function(){// 创建功能模块tr
//			var	_td = me._createTd(Jh.Config.tdCls2),
//				_tr = $("<tr>").append(me._createTd(Jh.Config.tdCls,"功能模块设置：")).append(_td);
//			me._ele.mtd = _td;
//			me._ele.mtr = _tr;
//            me._ele.mtd.hide();
//			me._ele.mtr.hide();
//			return _tr;
//		},
//		
//		_createLayoutTr:function(){// 创建布局
//			var codeArray=new Array();
//			codeArray=[me._createA('1:1')];			
//			codeArray[0].addClass("active");
//
////			codeArray=[me._createA('1:3'),me._createA('3:1'),me._createA('1:1:2'),me._createA('1:2:1'),me._createA('2:1:1'),me._createA('1:1:1')];
////			if(code=="1:3")
////				codeArray[0].addClass("active");
////			else if(code=="3:1")
////				codeArray[1].addClass("active");
////			else if(code=="1:1:2")
////				codeArray[2].addClass("active");
////			else if(code=="1:2:1")
////				codeArray[3].addClass("active");
////			else if(code=="2:1:1")
////				codeArray[4].addClass("active");
////			else 
////				codeArray[5].addClass("active");
//			var _td = me._createTd(Jh.Config.tdCls2),
//				_div = $("<div/>").addClass(Jh.Config.layCls)
//								  .append(codeArray[0])
////								  .append(codeArray[1])
////							 	  .append(codeArray[2])
////								  .append(codeArray[3])
////								  .append(codeArray[4])
////								  .append(codeArray[5])
//								  .appendTo(_td),
//				_tr = $("<tr>").append(me._createTd(Jh.Config.tdCls,"布局设置：")).append(_td);
//
//			me._ele.layoutTd = _td;
//			me._ele.layoutTr = _tr;
//			me._ele.layoutTd.hide();
//			me._ele.layoutTr.hide();
//			return _tr;
//		},
//		
//		_createModuleList:function(data){// 创建模块list
//			var _ul = $("<ul/>").addClass(Jh.Config.ulCls);
//			me._createLis(data.appL,_ul);
////			me._createLis(data.appM,_ul);
//			me._createLis(data.appR,_ul);
//			me._ele.ul = _ul;
//			_ul.appendTo(me._ele.mtd);
//		},
//		
//		_createLis:function(obj,_ul){// 创建li列表
//			$.each(obj,function(key,name){				
//				_ul.append(me._createLi(key,name.title));
//			});	
//		},
//		
//		_createA:function(text){// 创建A
//			return $("<a href='javascript:void(0);' rel='"+text+"'>"+text+"</a>");
//		},
//		
//		_createLi:function(key,name){// 创建li
//			return $("<li/>").append("<a href='#' rel='"+key+"'>"+name+"</a>")
//						.append("<span class='ok'></span>");
//		},
//		
//		_createTd:function(clsName,text){// 创建td
//			var t = $("<td>").addClass(clsName);
//			if(text!=undefined){
//				t.text(text);
//			}
//			return  t; 
//		},
//		
//		_addPanel:function(o){
//			me.box.append(o);		
//		}				
//	};
//
//}();

//Jh.Tab = function(me){
//	var _box = "<div id='t11' class='easyui-tabs' data-options=\"tools:'#tab-tools'\" style='width:700px;height:250px' />",
//		_itemTools = "<div id='tab-tools' class='tabs-tool'/>",
//		_itemHeader="<div class='paint_left'/>",
//		_itemContent="<div class='"+Jh.Config._groupItemContent+"'/>";			
//	
//	return me={	
//		init:function(op){//初始化
//			me._create();
////			me._bindData(op);
//		},
//		
//		_create:function(){
////			var _box = $("<div class='teb detail_cent' id='teb' />");
////			_itemHeader = $("<div class='paint_left' />");
//			me.box = $(_box);
//			me.itemTools = $(_itemTools);
//			me.itemHeader = $(_itemHeader);
//			me.itemContent = $(_itemContent);
//			me._elements = {};
//			Jh.Util.toBody(me.box);
////			me._createTab();
////			me._createModulesWrap();
////			_box.append(_itemHeader);
////			_itemHeader.append("<ul id='menu_tab'> <li class='focus'><a id='menu_tab0' onclick=\"setTab('conTab0',0)\" href='###' data='conTab0' class='current'> 今日待办</a> </li>" +
////			"<li><a id='menu_tab1' onclick=\"setTab('conTab1',1)\" href='###' data='conTab1'>待办任务</a> </li></ul> ");
//			me.itemTools.append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'icon-add'\" onclick='addPanel()'></a>")
//						.append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'icon-remove'\" onclick='removePanel()'></a>");
//			me.box.append(me.itemTools);
//			//me.box.append(me.itemHeader).append(me.itemContent);
//		}};}();

Jh.Portal = function(me){// Portal对象
	var _box = "<div id='portal'></div>",
		_template = {// 模板
			
			l :"<div id='"+Jh.Layout.location.left+"' class='"+Jh.Config._groupWrapperClass+" w250'/>",
//			m :"<div id='"+Jh.Layout.location.center+"' class='"+Jh.Config._groupWrapperClass+" w250'/>",
			r :"<div id='"+Jh.Layout.location.right+"' class='"+Jh.Config._groupWrapperClass+" w250'/>",
			portalWrap:"<div id='{key}' class='"+Jh.Config._groupItemClass+"'/>",
			itemHeader:"<div class='"+Jh.Config._groupItemHead+"'><h3>{name}</h3></div>",
			itemContent:"<div class='"+Jh.Config._groupItemContent+"'/>"
		};
	return me={	
		init:function(op){// 初始化
			me._create();
			me._bindData(op);
			// me._bindEvent();
		},

		_create:function(){// 创建
			me.box = $(_box);
			me._elements = {};		
			me._createModulesWrap();
			Jh.Util.toBody(me.box);	
		},	
		
		_bindData:function(op){// 绑定数据
			$.each(op,function(key,item){				
				me._createPortal(key,item);
			});
		},
		
		_createModulesWrap:function(){// 创建模块外层容器
			me._elements.m_l = $(_template.l);
//			me._elements.m_m = $(_template.m);
			me._elements.m_r = $(_template.r);
			me._addPanel(me._elements.m_l);
//			me._addPanel(me._elements.m_m);
			me._addPanel(me._elements.m_r);
		},
		
		_addPanel:function(o){// 往容器里添加
			me.box.append(o);
		},
		
		_createPortal:function(key,item){// 创建portal
			var mWrap ;
			switch(key){
			   case "appL":mWrap = me._elements.m_l;
				$.each(item,function(k,o){				
					mWrap.append(me._createPortalOne(k,o));
				});
				break;
//			   case "appM":mWrap = me._elements.m_m;
//				break;
			   case "appR":mWrap = me._elements.m_r;
				$.each(item,function(k,o){				
					mWrap.append(me._createPortalOne(k,o));
				});
				break;
			}
			
//			$.each(item,function(k,o){				
//				mWrap.append(me._createPortalOne(k,o));
//			});
		
		},
		
		_createPortalOne:function(key,name){// 创建单个portal item
			var itemHeader  =  me._createItemHeader(name.title),// header
			    itemContent =  me._createItemContent(name.url),// content
			    portalWrap  = $(Jh.Util.format(_template.portalWrap,{"key":key}))
							.append(itemHeader)
							.append(itemContent);
			
			return portalWrap;
		},
		
		_createItemHeader:function(name){// 创建Head
			var  _itemHeader = $(Jh.Util.format(_template.itemHeader,{"name":name})),// 格式化header
				_actionWrap =  me._createDiv("action").hide().appendTo(_itemHeader);// 创建一个div
			
			
			me._createA(Jh.Config.min,Jh.Config.mintext,true).appendTo(_actionWrap);
			me._createA(Jh.Config.max,Jh.Config.maxtext,false).appendTo(_actionWrap);
			me._createA(Jh.Config.close,Jh.Config.closetext,true).appendTo(_actionWrap);
//			me._createA(Jh.Config.maxwindow,Jh.Config.maxwindowtext,true).appendTo(_actionWrap);
			
//			_itemHeader.hover(function(){// 滑过标题出现删除图标
//				$(this).find(".action").show();			
//			},function(){
//				$(this).find(".action").hide();
//				
//			});
			return _itemHeader;
		},
		
		_createItemContent:function(b){// 创建content
			var _content = $(_template.itemContent);
			// $("<ul
			// style='width:250px;'><li>xiaofanV587</li><li>xiaofanV587</li><li>xiaofanV587</li><li>xiaofanV587</li></ul>").appendTo(_content);
			 $("<iframe src='"+b+"' FrameBorder='0' scrolling='no' width='100%' height='295px' />" ).appendTo(_content);
			return _content;
		},
		
		_createDiv:function(classname){
			var _div = $("<div/>").addClass(classname);
			return _div;
		},
		
		_createA:function(classname,title,isShow){// 创建A
			var _a = $("<a href='javascript:void(0);' class='"+classname+"' title='"+title+"'/>");
			if(!isShow){
				_a.hide();
			}
			return _a ;
		}
	
	};
}();

/*function savePortletLeft(a){
	var url= contextRootPath + "/portal/savePortletPosition.do?a="+a+"&porletcol=appL";
	$.ajax({
		type:"POST",
		url:url,
		async:false
	});
	
	 * $.post(contextRootPath + "/portal/savePortletPosition.do", {a:a,
	 * porletcol:"appL"});
	 
};

function savePortletMiddle(b){
	var url= contextRootPath + "/portal/savePortletPosition.do?a="+b+"&porletcol=appM";
	$.ajax({
		type:"POST",
		url:url,
		async:false
	});
	
	 * $.post(contextRootPath + "/portal/savePortletPosition.do",
	 * {a:b,porletcol:"appM"});
	 
};
function savePortletRight(d){
	var url= contextRootPath + "/portal/savePortletPosition.do?a="+d+"&porletcol=appR";
	$.ajax({
		type:"POST",
		url:url,
		async:false
	});
	
	 * $.post( contextRootPath + "/portal/savePortletPosition.do",
	 * {a:d,porletcol:"appR"} );
	 
};
function updateIsclosed(){
	var url=contextRootPath + "/portal/updateIsclosed.do";
	$.ajax({
		type:"POST",
		url:url,
		async:false
	});
};*/

function layoutFu(a) {// 排版，同刷新布局
    var c = Jh.Layout.layoutText,
    e = Jh.Layout.locationId,
    d = 0,
    f = "";
    $.each(Jh.Layout.layoutCss,
    function(c, j) {
      a == j && (d = c)
    });
    $.each(e,
    function(a, b) {
      var g = $(b),
      h = c[d].split(/\s+/);
      switch (a) {
      case "left":
        a = 0;
        break;
//      case "center":
//        a = 1;
//        break;
      case "right":
        a = 2
      }
      "wnone" == h[a] && (f = g.sortable("toArray"), $.each(f,
      function(a, b) {
        $("#" + Jh.Layout.location.left).append($("#" + b))
      }), g.empty());
      g.removeClass("w250 w250").addClass(h[a])
    })
  };

//function findWfTPortlettemplate(){// 查找code
//	var url=contextRootPath + "/portal/findWfTPortlettemplate.do";
//	var result=$.ajax({
//		type:"POST",
//		url:url,
//		async:false
//		}
//	);
//	var temp=result.responseText;
//	var code=eval("(" + temp + ")");
//	return code.msg;
//};

//function saveLayout(f){// 保存排版
//	var url=contextRootPath + "/portal/saveTemplateCode.do?templateCode="+f;
//	$.ajax({
//		type:"POST",
//		url:url,
//		async:false
//		}
//	);
//	/*
//	 * $.post( contextRootPath + "/portal/saveTemplateCode.do", {templateCode:f} );
//	 */
//};

function savePortlet(left,middle,right){
	lock("savebt");
	var url=contextRootPath + "/portal/savePortletPosition.do?left="+left+"&middle="+middle+"&right="+right;
	$.ajax({
		type:"POST",
		url:url,
		async:false,
        success: function(result) {
            $.messager.alert('提示信息', '保存成功！' , 'info');
            setTimeout("dislock(\"savebt\")",2000);
        },
        error: function(msg) {
            $.messager.alert('保存失败', msg, 'error');
            setTimeout("dislock(\"savebt\")",2000);
        }
	});
};

function addPortlet(name,key){
	var url=contextRootPath + "/portal/savePortletclassfy.do?portletName="+name+"&url="+key;
	$.ajax({
		type:"POST",
		url:url,
		async:false
		}
	);
};

function setTab(conId,cursel){
	obj=document.getElementById("menu_tab").getElementsByTagName("a");
	for(i=0;i<obj.length;i++){
		var menu=document.getElementById("menu_tab"+i);
		var con=document.getElementById("conTab_"+i);
		menu.className=i==cursel?"current":"";
		//con.style.display=i==cursel?"block":"none"
	}
};

//function editRecord(url){
//	var newWindow = openWindow(contextRootPath+"/common/loading/openLoading.html", "_blank", "alwaysRaised,channelmode,status,scrollbars,resizable");
//	newWindow.location=url;
//};
//function openWindow(urlString, windowName, optionString) {
//    var newWindow = window.open(urlString, windowName, optionString);
//    try {
//        newWindow.focus();
//    }
//    catch (E) {
//    }
//    return newWindow;
//};

/*为防止重复提交，按钮被点击后置为无效，时间到后解锁*/
function lock(name){ 
	document.getElementById(name).disabled = true;
	//alert(name);
} 
function dislock(name){ 
	document.getElementById(name).disabled = false;
	//alert(name);
} 