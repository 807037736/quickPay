/**
 *  页面加载等待页面
 *
 * @author yaowenfeng
 *
 */

 function closeLoadingMask(){
	 try{
		 var _mask = document.getElementById('loading_mask');
		 _mask.parentNode.removeChild(_mask);
	 }catch(e){return;}
 }
function showLoadingMask(){
	 var height = window.screen.height-250;
	 var width = window.screen.width;
	 var leftW = 300;
	 if(width>1200){
	 	leftW = 500;
	 }else if(width>1000){
	 	leftW = 350;
	 }else {
	 	leftW = 100;
	 }
	 var top;
	 if(window.innerHeight && window.scrollMaxY) {
		top = window.innerHeight + window.scrollMaxY;
	 }else {
		top = document.documentElement.scrollHeight; // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari }
	 }
	 if(top!=null&&top!=NaN){
		 top = top/2-20;
	 }else{
		 top = 30;
	 }
	 
	 var _html = "<div id='loading_mask' style='position:absolute;left:0;width:100%;height:"+height+"px;top:0;background:#CCCCCC;opacity:0.8;filter:alpha(opacity=90);'>\
	 <div style='position:absolute;	cursor1:wait;left:"+leftW+"px;top:"+top+"px;width:240px;height:16px;padding:12px 5px 10px 30px;\
	 background:#ffffff url("+contextRootPath+"'/common/jquery-easyui/themes/default/images/pagination_loading.gif) no-repeat scroll 5px 10px;border:2px solid #ccc;color:#000;'>\
	 正在加载，请等待... \
	 </div></div>";
	document.write(_html);
}
/*
 * 进度条
 */
function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title: title,
		msg: msg,
		text:''
	});
}
 