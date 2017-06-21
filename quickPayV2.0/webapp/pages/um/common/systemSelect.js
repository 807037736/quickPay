/*
 *多系统选择的下拉框
 *add by chenwu 2014年8月1日
 *modified by liuyatao 2014年8月4日
 */
function selectSevCode() {
    var url = contextRootPath + '/tm/tmtapplicationconfig/selectSerCode.do';
    var servercodeList = $('#svrCode');
    var svrCodeOld = document.getElementById('svrCodeOld');

    $.ajax({
        type: "POST",
        url: url,
        success: function(result) {
            var obj = eval("(" + result + ")");
            var len = obj.servercodeList.length;
            for (var i = 0; i < len; i++) {
                $('#svrCode').append('<option value=' + obj.servercodeList[i].id.serverCode + '>' + obj.servercodeList[i].serverName + '</option>');
            }
            if(svrCodeOld != null&& ""!=svrCodeOld){
            	servercodeList.attr("value", svrCodeOld.value);
            }
        },
        error: function(msg) {
            var obj = eval("(" + msg + ")");
            window.parent.window.$.messager.alert('出现错误', obj.msg, 'error');
        }
    });
}

$(document).ready(function(){
	selectSevCode();
});