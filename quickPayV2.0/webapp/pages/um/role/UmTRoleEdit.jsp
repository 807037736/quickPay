<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.picc.um.schema.model.UmTRole"%>
<html>
<head>
<%@ include file="/common/meta_css.jsp"%>
<%@ include file="/common/i18njs.jsp"%>
<%@ include file="/common/meta_js.jsp"%>
<script language="javascript" src="${ctx}/pages/um/role/UmTRole.js"></script>
<script language="javascript" src="${ctx}/pages/um/common/systemSelect.js"></script>
<script type="text/javascript">
	$(function() {
		$.extend($.fn.tree.methods,{
	        getCheckedExt: function(jq){//扩展getChecked方法,使其能实心节点也一起返回
	            var checked = $(jq).tree("getChecked");
	            var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
	            $.each(checkbox2,function(){
	                var node = $.extend({}, $.data(this, "tree-node"), {
	                    target : this
	                });
	                checked.push(node);
	            });
	            return checked;
	        },
	        getSolidExt:function(jq){//扩展一个能返回实心节点的方法
	            var checked =[];
	            var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
	            $.each(checkbox2,function(){
	                var node = $.extend({}, $.data(this, "tree-node"), {
	                    target : this
	                });
	                checked.push(node);
	            });
	            return checked;
	        }
	    });
		/* $.extend($.fn.validatebox.defaults.rules,{
			checkRoleCode:{
				validator:function(value,param){
					if(value.length>30 || value.length<=0){
						return false;
					}else{
						var ok = $.ajax({
							type:"POST",
							url:contextRootPath + "/um/umtrole/checkRoleCode.do",
							data:"roleCode="+$('#umTRole\\.roleCode').val()+"&opreateType="+$('#opreateType').val()+"&initRoleCode="+$('#initRoleCode').val(),
							dataType:"json",
							async:false
						}).responseText;
						if(ok == 'success'){
							return true;
						}else{
							return false;
						}
					}
				},
				message:'角色代码必须为0-30, 且不能重复!'
			}
		}); */
		
		function isEmpty(s) {
			return ((s == undefined || s == null || s == "" ? true : false));
		}
		//加载机构树
		var comTree = "${comTree}";
		if (!isEmpty(comTree)) {
			var comArray = eval('(' + comTree + ')');
			$('#comTree').tree({
				data : comArray,
				cascadeCheck : false,
				checkbox : true,
				animate : true,
				onCheck : function(node, check) {
					if (check == node.attributes.initChecked) {
						//如果状态没有改变，将当前从数据中删除
						var len = changedComArray.length;
						for ( var i = 0; i < len; i++) {
							if (changedComArray[i].id == node.id) {
								changedComArray.splice(i, 1);
								break;
							}
						}
					} else {
						var valid;
						if (node.attributes.initChecked == false) {
							//操作为增加
							//							alert("操作为增加");
							valid = '1';
						} else {
							//操作为删除
							//							alert("操作为删除");
							valid = '0';
						}
						var obj = new Object();
						obj.id = node.id;
						obj.text = node.text;
						obj.validStatus = valid;
						changedComArray.push(obj);
					}
				}
			});
		}

		//加载功能树
		//var taskData = $("#taskStr").val();
		//add by liuyatao 2014年8月1日
		var serverCode = '${serverCode}';
		var objectId = $('#roleId').val();
		createTree('taskTree', 4, '&objectId='+objectId, true,serverCode);
		
		//是否是总公司用户的标识
		var isHeadOffice = "${isHeadOffice}";
		if (isHeadOffice == 'yes') {
			//如果是总公司用户则显示机构树
			$('#comtr').css('display', 'block');
		}
	});
</script>

<script type="text/javascript">
	/*页面加载触发*/
	$(document).ready(

	function() {
		var updatable = "${updatable}";
		if (($('#opreateType').val() == 'view')) {
			setReadonlyOfAllInput("fm");
		}
		if (($('#opreateType').val() == 'update') && (updatable == 'no')) {
			setReadonlyOfAllInput2("fm");
		}
	});
	
	function changeTaskTree() {
		//加载功能树
		//var taskData = $("#taskStr").val();
		//add by liuyatao 2014年8月1日
		var serverCode = $("#svrCode").find("option:selected").val();
		var objectId = $('#roleId').val();
		createTree('taskTree', 4, '&objectId='+objectId, true,serverCode);
	}

	/**只读控制**/
	function setReadonlyOfAllInput2(formID)
	{
	  var form = document.getElementById(formID);
	  if(form!=undefined){
		  var length = form.elements.length;
		  for(var i=0;i<length;i++)
		  {
		    setReadonly2(form.elements[i]);
		  }
	  }
	}

	function setReadonly2(iElement){
		 if(iElement.tagName == "A"){//for easyui <a>tag button
			  setReadonlyOfA(iElement);
		  }else{
			  setReadonlyOfElement(iElement);
		  }
		}
</script>
<style type="text/css">
#comtr {
	display: none;
}
</style>
</head>

<body>
<div class="right_detail_top"><h3>
<c:choose>
				<c:when test="${opreateType == 'update'}">
					修改角色信息
				</c:when>
							<c:when test="${opreateType == 'add'}">
					增加角色信息
				</c:when>
							<c:when test="${opreateType == 'view'}">
					查看角色信息
				</c:when>
						</c:choose>
						</h3></div>
	<input type="hidden" name="opreateType" id="opreateType"
		value="${opreateType}" />
	<form name="fm" id="fm" action="/um/umtrole" namespace="/um/umtrole"
		method="post">
		<div id="wrapper">
	<div id="container">
				<input type="hidden" name="umTRole.id.roleId" value="${umTRole.id.roleId}" id="roleId"/>
				<input type="hidden" name="comStr" value="${comStr}" id="comStr" />
				<input type="hidden" name="taskStr" value="${taskStr}" id="taskStr" />
				<input type="hidden" name="umTRole.flag" value="${umTRole.flag}" />
				<input type="hidden" name="initRole.roleCName" value="${umTRole.roleCName}" />
				<input type="hidden" name ="initRole.roleCode" value="${umTRole.roleCode}"/> 
				<input type="hidden" name ="initRole.id.roleId" value="${umTRole.id.roleId}"/>
				<input type="hidden" name ="initRole.flag" value="${umTRole.flag}"/>
		<table class="fix_table">
			<tr>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>角色代码</td>
				<td class="long"><input
					class='easyui-validatebox input_w w_15 myc' name="umTRole.roleCode"
					id="umTRole.roleCode" value="${umTRole.roleCode}" required="true"
					validType="length[0,30]" >
				</td>
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>角色名称</td>
				<td class="long"><input
					class='easyui-validatebox input_w w_15 myc'
					name="umTRole.roleCName" id="umTRole.roleCName"
					value="${umTRole.roleCName}" required="true"
					validType="length[0,20]" />
				</td>
			</tr>
			<tr>
				<%--
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>关联用户类型</td>
				<td class='long'>
				<c:choose>
					<c:when test="${opreateType == 'add'}">
					<ce:radio cssClass="easyui-validatebox" name="umTRole.userType"
						list="#@java.util.LinkedHashMap@{'01':'内部','02':'外部'}"
						value="01" required="true"/>
					</c:when>
					<c:when test="${opreateType == 'update'}">
					<ce:radio cssClass="easyui-validatebox" name="umTRole.userType"
							list="#@java.util.LinkedHashMap@{'01':'内部','02':'外部'}"
							value="${umTRole.userType}" required="true"/>
					</c:when>
				</c:choose>
				</td>
				 --%>
				<td class="bgc_tt short">所属系统</td>
				<td class="long">
				<select class="input_w w_15" id="svrCode" name="umTRole.serverCode" onchange="changeTaskTree()"></select>
					<c:choose>
						<c:when test="${opreateType == 'add'}">
						<input type="hidden"  id="svrCodeOld" value="${serverCode}"  />
						</c:when>
						<c:when test="${opreateType == 'update' || opreateType == 'view'}">
						<input type="hidden"  id="svrCodeOld" value="${umTRole.serverCode}"  />
						</c:when>
					</c:choose>
				</td>
				<td class="bgc_tt short">备注</td>
				<td class="long" colspan='3'><input class='input_w w_100 myc'
					name="umTRole.remark" id="umTRole.remark" value="${umTRole.remark}" />
				</td>
			</tr>
			<tr id="comtr">
				<td class="bgc_tt short"><span style="color: red">*&nbsp;</span>机构</td>
				<td class="long" colspan='3' style="padding-left: 5em;"><ul
						id="comTree" class="easyui-tree"></ul></td>
			</tr>
		</table>
		</div>
		</div>
		<div class="block" style="text-align:left;">
			<h3>功能</h3>
			
			<table class="fix_table">
				<tr>
					<td><ul id="taskTree" class="easyui-tree"></ul></td>
				</tr>
			</table>
				
		</div>
		<table class="fix_table">
			<tr>
				<td colspan=4 align="center"><input type="button"
					class="button_ty" onclick="executeSave();" ind="ind" value="保存" />
					<input type="reset" class="button_ty" ind="ind" value="重置" /> <input
					type="button" class="button_ty" onclick="closeWinAndReLoad();"
					value="关闭" />
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
