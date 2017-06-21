/**
* [showTips 閿欒鎻愮ず娴煄]
* @param  {[string]} text [閿欒鎻忚堪]
* @param  {[int]} time [寤惰繜鏃堕棿]
* 瀹炰緥锛歴howTips("aaa", 1000);
*/
	function showTips(text, time) {
		var text = text, time = time;
		var wrap_div = '<div class="myTips" id="myTips" style="position: fixed;top: 0; left: 0;width: 100%;height: 100%;z-index: 10000;"><div style="position: absolute;padding: 20px;background: rgba(0, 0, 0, 0.7);position: absolute; left:50%; top:50%; -webkit-transform:translate(-50%,-50%); transform:translate(-50%,-50%);font-size: 18px;border-radius: 10px;color:#fff;opacity: 0.9;">' + text + '</div>';
		if ($('.myTips').length > 0) {
        	$('.myTips').find('div').text(text);
    	} else {
        	$("body").append(wrap_div);
    	}

    	if (time != undefined && time) {
        	$("#myTips").show();
        	setTimeout(function() {
            	$("#myTips").hide();
        	}, time);
    	}
	}
	
	
	