<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTCOMInform" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<!-- <link href="${ctx}/style/style_all.css" type="text/css" rel="stylesheet"/> -->
<script language="javascript" src="${ctx}/pages/qp/qptcominform/QpTCOMInform.js"></script>

<script type="text/javascript">
	
	/*页面加载触发*/
	$(document).ready(function(){
		if($('#operateType').val()=='view'){
			setReadonlyOfAllInput("fm");
		}
	});
</script>
</head>
<body>
	 
	 <form id="fm" action="" method="post" class="bootstrap-frm">
	 	<input type="hidden" name="operateType" id="operateType" value="${operateType}"/>
	 	<input type="hidden" name="informId" id="informId" value="${informId }"/>
	 	<input type="hidden" name="state" id="state" value=""/>
	 	<input type="hidden" name="type" id="type" value=""/>
		<h1>
			<c:choose>
				<c:when test="${operateType == 'update'}">
						修改系统公告
					</c:when>
				<c:when test="${operateType == 'add'}">
						添加系统公告
					</c:when>
				<c:when test="${operateType == 'view'}">
						查看系统公告
					</c:when>
			</c:choose>		
		</h1>
		
				<label>
					<span>公告标题 :</span>
					<input type="text" id="title" name="title" value="${operateType == ('update'||'view') ? title:''}" 
							"${operateType == 'view' ?  'readonly=ture':''}" />
					<span>公告发布时间:</span>
					<input type="text" required="true" "${operateType == 'view' ?  'readonly=ture':''}"
							name="startTime" id="startTime" value="${operateType == ('update'||'view') ? startTime:'' }"
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<span>创建者 :</span>
					<input type="text" id="creator" name="creator" value="${operateType == ('update'||'view') ? creator:''}" 
							"${operateType == 'view' ?  'readonly=ture':''}"/>
					<span>公告结束时间:</span>
					<input type="text" class='bootstrap-frm easyui-validatebox' required="true" 
							"${operateType == 'view' ?  'readonly=ture':''}"
							name="endTime" id="endTime" value="${operateType == ('update'||'view') ? endTime:'' }"
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><br/><br/>
					<span>公告内容 :</span>
					<textarea id="content" name="content" "${operateType == 'view' ?  'readonly=ture':''}">${operateType == ('update'||'view') ? content:'' }</textarea>
				</label>
				<label>
					<span>&nbsp;</span>
					<c:choose>
						<c:when test="${operateType != 'view' }">
								<input type="button" class="button" value="保存" onclick="executeSave();" />
								<input type="button" class="button" value="保存并发布" onclick="executeSaveAndPublish()" />
						</c:when>
						<c:when test="${operateType == 'view' }">
							<input type="button" class="button" value="返回" onclick="closeWinAndReLoad();"/>
						</c:when>
					</c:choose>
				</label>
				
			
	</form>

</body>
</html>

