<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
  <title>sinosoft</title>
</head>
<body>
  <button id="weixin" style="display: block;margin: 2em auto">微信接口测试</button>
<button id="upload" style="display: block;margin: 2em auto">上传接口测试</button>
<button id="getServices" style="display: block;margin: 2em auto">获取已上传的图片</button>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
$.ajax({
		async:false,
    url: '${ctx}/qp/qpticpicturegroup/weixin.do',
    type: 'POST',
    data:{appId:'wxb844d812934c2f47',appSecret:'b7ec7cb8929cb34ce959ae1a59e9229c',url:location.href.split('#')[0]},
    dataType: 'json',
    timeout: 1000,
    error: function(){alert('Error');},
    success: function(result){
		    	  wx.config({
		    		  	debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
       appId: result.appId, // 必填，公众号的唯一标识
       timestamp: result.timestamp, // 必填，生成签名的时间戳
       nonceStr: result.nonceStr, // 必填，生成签名的随机串
       signature: result.signature,// 必填，签名，见附录1
       jsApiList: [
                   /*
                    * 所有要调用的 API 都要加到这个列表中
                    * 这里以图像接口为例
                    */
                  "chooseImage",
                  "previewImage",
                  "uploadImage",
                  "downloadImage"
            ]
		    	  });
		    	  var btn = document.getElementById('weixin');
		    	  //定义images用来保存选择的本地图片ID，和上传后的服务器图片ID
		    	  var images = {
		    	      localId: [],
		    	      serverId: []
		    	  };
		    	  wx.ready(function () {
		    		// 在这里调用 API
		    		    btn.onclick = function(){
		    		        wx.chooseImage ({
		    		            success : function(res){
		    		                images.localId = res.localIds;  //保存到images
		    		                // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		    		            }
		    		        });
		    		    }
		    		    document.getElementById('upload').onclick = function(){
		    		        var i = 0, len = images.localId.length;
		    		        function wxUpload(){
		    		            wx.uploadImage({
		    		                localId: images.localId[i], // 需要上传的图片的本地ID，由chooseImage接口获得
		    		                isShowProgressTips: 1, // 默认为1，显示进度提示
		    		                success: function (res) {
		    		                    i++;
		    		                    //将上传成功后的serverId保存到serverid
		    		                    images.serverId.push(res.serverId);
		    		                    if(i < len){
		    		                        wxUpload();
		    		                    }
		    		                }
		    		            });
		    		        }
		    		        wxUpload();
		    		    }
		    		    document.getElementById('getServices').onclick = function(){
		    		        alert(images.serverId);
		    		    }
		    		    
		    		  });
    }
});


</script>
</html>
