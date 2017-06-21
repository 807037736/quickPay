<!DOCTYPE>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.qp.schema.model.QpTICPictureGroup" %>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<title>上传图片</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script language="javascript" src="${ctx}/pages/qp/qpticpicturegroup/QpTICPictureGroup.js"></script>
<script language="javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/jquery.form.js"></script>
<script src="${ctx }/widgets/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<link href="${ctx }/widgets/bootstrap-3.2.0/css/bootstrap.css"
	rel="stylesheet">

	<script language="javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/jquery-confirm.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/jquery-confirm.css">
	
	<link type="text/css" rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/custom.css">
<!--主要样式-->
<link type="text/css" rel="stylesheet" href="${ctx}/pages/qp/qpticpicturegroup/css/iosOverlay.css">
		<style type="text/css">
	.xb_panel{
	font-size:16px;
	color:#FFF;
	border:none;
	border-radius:5px;
	-moz-border-radius:5px;
	-webkit-border-radius:5px;
	background:#08CFA8;
	font-family: "Microsoft Yahei";
	-webkit-appearance: none !important; 
	}
	.inputstyle {
height:25px;
width:120px;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  color: #555;
  background-color: #fff;
  background-image: none;
  border: 1px solid #ccc;
  border-radius: 4px;

          box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
       -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
          transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
	</style>
<script type="text/javascript">
$(function() {
	addTr($("#addFile"));
});
</script>
</head>
<body id="uploadBody">
	<form name="fm" id="fm" 		enctype="multipart/form-data" >
	<input type="hidden" autocomplete="off" name="accidentGroupId" value="${groupId }" id="accidentGroupId">
	<input type="hidden" autocomplete="off" name="acciId" value="${acciId }" id="acciId">
		<div id="wrapper">
			<div id="container">
				<div class="panel " style="	background:#08CFA8;" id="uploadFile">
							<div class="panel-heading">
								<h4 class="panel-title xb_panel" >上传图片&nbsp;</h4>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-condensed" id="uploadTable">
										<!--       <caption>响应式表格布局</caption> -->
										<thead>
											<tr>
											</tr>
										</thead>
										<tbody id="files">
											<tr>
												<td colspan="2">
													<button type="button" class="btn btn-default btn-sm xb_panel"
														data-toggle="tooltip" data-placement="right" title="新增上传位"
														id="addFile" onclick="addTr(this)" >
														<span class="glyphicon glyphicon-plus"></span> 新增
													</button>&nbsp;
													<button type="button"  onclick="doAjaxForm()"  														data-toggle="tooltip" data-placement="right" title="上传图片" class="btn btn-default btn-sm xb_panel">														
													<span class="glyphicon glyphicon-upload"></span> 上传</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
			</div>
		</div>
	</form>
<script type="text/javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/iosOverlay.js"></script>
<script type="text/javascript" src="${ctx}/pages/qp/qpticpicturegroup/js/spin.min.js"></script>
</body>
</html>
