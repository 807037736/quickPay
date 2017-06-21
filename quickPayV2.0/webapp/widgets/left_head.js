(function(a){
    a.fn.left_head=function(p){
        var p=p||{};

        var f=p&&p.menu_text_size?p.menu_text_size:"14";
        var g=p&&p.menu_text_color?p.menu_text_color:"fff";
        var h=p&&p.menu_border_size?p.menu_border_size:"0";
        var i=p&&p.menu_background_color?p.menu_background_color:"#929292";
        var j=p&&p.menu_border_color?p.menu_border_color:"blue";
        var k=p&&p.menu_border_style?p.menu_border_style:"solid";
        var l=p&&p.menu_width?p.menu_width:"130";
        var n=p&&p.menu_height?p.menu_height:"51";
        var r=p&&p.menu_margin?p.menu_margin:"0";
        var v=p&&p.menu_background_hover_color?p.menu_background_hover_color:"#929292";
        var m=p&&p.directory?p.directory:"images"; 
        var w=a(this);
        f += 'px';
        h += 'px';
        l += 'px';
        n += 'px';
        r += 'px';
        if(w.children("ul").length==0||w.find("li").length==0){
            dom.append("Require menu content");
            return null
        }
        init();
        function init(){
            w.children("ul").find("a").css("color",g).css("font-size",f).css("line-height",n).css("display","block");
            w.children("ul").children("li").css("border",h+" "+k+" "+j).css("margin-bottom",r).css("background-color",i);
            w.find("li").children("ul").css("border",h+" "+k+" "+j).css("background-color",i);
            w.find("li").css("width",l).css("height",n);
            w.find("li:has(ul)").addClass("down_drop");
           // w.find("li:has(ul)").css(""," ");
            w.children("ul").children("li").find("ul").css("left",l).css("top","0px");
        }
//        s_sub_l(w.find("ul").children("li").children("ul").children("li").children("ul"),h);
        w.find("li").hover(function(){
            $(this).css("background-color",v);
            $(this).children("ul").show()
            },function(){
            $(this).css("background-color",i);
            $(this).children("ul").hide()
            });



            function s_u_t(a){
            l_t_b_s=a.outerHeight(true)-a.css("border-top-width").replace("px","")*2+"px";
            a.children("ul").css("top",l_t_b_s);
            a.children("ul").css("left","-"+a.css("border-top-width"));
            li_hieght = w.children("ul").children("li").outerHeight(true);
            //li_width = w.children("ul").children("li").outerWidth(true);
            a.children("ul").find("a").css("line-height",li_hieght+"px");
            //a.children("ul").find("li").width(li_width);
            }
            function s_sub_l(a,b){
            boder_width=b.replace("px","");
            a.css("left",a.parent("li").parent("ul").outerWidth(true)-boder_width*2);
            a.css("top","-"+b)
            }
        }
})(jQuery);
window.onload=function(){var demo=document.getElementById("years");var children=demo.getElementsByTagName("a");var num=0;for(var i=0;i<children.length;i++){children[i].index=i;children[i].onclick=function(){children[num].className="";this.className="active";num=this.index}}}
function setTab(conId,cursel){obj=document.getElementById("menu_tab").getElementsByTagName("a");for(i=0;i<obj.length;i++){var menu=document.getElementById("menu_tab"+i);var con=document.getElementById("conTab_"+i);menu.className=i==cursel?"current":"";con.style.display=i==cursel?"block":"none"}}$(document).ready(function(){$('#teb tr:even').addClass('even')});