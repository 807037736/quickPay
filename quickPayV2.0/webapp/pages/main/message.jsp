<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>消息列表</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<script type="text/javascript">
function changMsgType(messageId){
	var heading = $('#heading'+messageId);
	if(heading!=null&&heading!=undefined){
		var msgType = $('#msgType'+messageId);
		if(msgType.val()=="1"){
			$(msgType).val("0");
			$("h4",heading).each(function(index,object){
				 $(object).css('color','gray');
				 var url = "${pageContext.request.contextPath}"+"/wx/message/changeMsgType.do";
				 var formData = "messageId="+messageId+"&msgType=0";
				 $.ajax({
						type : "POST",
						url  : url,
						data : formData,
						success : function(result) {
							
						}
					});
			});
		}
	}
}
</script>
</head>
<body id="cate4">
<input type="hidden" id="param1" name="param1" value="${param1 }">
<input type="hidden" id="page" name="page" value="${page}">
<c:if test="${msgList== null || fn:length(msgList) == 0}">
<div class="panel-body">
        暂无消息
</div>
</c:if>
<c:forEach var="msg" items="${msgList}">
  <div class="panel panel-default" style="margin-bottom:0px;">
    <c:if test="${msg.msgType == '0'}">
    <div class="panel-heading" role="tab" id="heading${msg.id.messageId}">
      <input type="hidden" id="msgType${msg.id.messageId}" value="${msg.msgType}">
      <span class="collapsed" data-toggle="collapse"  data-parent="#accordion" onclick="changMsgType('${msg.id.messageId}');" href="#collapse${msg.id.messageId}" aria-expanded="false" aria-controls="collapse${msg.id.messageId}"  >
	      <h4 class="panel-title" style="color:gray">
	          ${msg.title}<small style="padding-left:20px">${msg.insertTimeForHis}</small>
	      </h4>
      </span>
    </div>
    <div id="collapse${msg.id.messageId}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${msg.id.messageId}">
      <div class="panel-body">
        ${msg.content}
      </div>
    </div>
    </c:if>
    <c:if test="${msg.msgType == '1'}">
    <div class="panel-heading" role="tab" id="heading${msg.id.messageId}">
      <input type="hidden" id="msgType${msg.id.messageId}" value="${msg.msgType}">
      <span class="collapsed"  data-toggle="collapse" data-parent="#accordion" onclick="changMsgType('${msg.id.messageId}');" href="#collapse${msg.id.messageId}" aria-expanded="false" aria-controls="collapse${msg.id.messageId}"  >
	      <h4 class="panel-title">
	          ${msg.title}<small style="padding-left:20px">${msg.insertTimeForHis}</small>
	      </h4>
      </span>
    </div>
    <div id="collapse${msg.id.messageId}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${msg.id.messageId}">
      <div class="panel-body">
        ${msg.content}
      </div>
    </div>
    </c:if>
  </div>
</c:forEach>
	 <jsp:include page="./bottomback.jsp"></jsp:include>
</body>

<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script> 
</html>