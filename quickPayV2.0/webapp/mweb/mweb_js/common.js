
$(function(){




// $("#slt_top").click(function (event) { 
//     $("#topslt_pop").fadeIn(); 
//     $(document).one("click", function () {//对document绑定一个影藏Div方法 
//         $("#topslt_pop").hide(); 
//     });
//     event.stopPropagation();//点击Button阻止事件冒泡到document 
// }); 
// $("#topslt_pop").click(function (event) { 
//     event.stopPropagation();//在Div区域内的点击事件阻止冒泡到document 
// }); 
// 点击弹窗以外的地方 弹窗隐藏


$("#slt_top").click(function(){ 
$(this).blur(); 
$("#topslt_pop").show(); 
return false; 
}); 


$("#topslt_pop a").click(function(){ 
$(this).blur(); 
var value = $(this).attr("rel"); 
var txt = $(this).text(); 
$("#abc").val(value); 
$("#abc_CRtext").val(txt); 
$(".slt_val").text(txt);   
$("#topslt_pop").hide(); 
return false; 
}); 

/*点击任何地方关闭层*/ 
$(document).click(function(event){ 
if( $(event.target).attr("id") != "slt_top" ){ 
$("#topslt_pop").hide(); 
} 
}); 






//tab
  $('.tabox_con').hide().eq(0).show();
$('.tabox_tab li').click(function(){
                       $(this).addClass('cur').siblings().removeClass('cur');
                       var num=$(this).index();
                       $('.tabox_con').hide().eq(num).show();
                       })
//横向切换tab end




});













