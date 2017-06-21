<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs_m.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@ include file="/common/meta_m_css.jsp"%>
	<style type="text/css">
		.panneli img {
			width: 100%;
			max-width: 50%
		}
		#flightNo,#engineNo,#frameNo {
			text-transform : uppercase;
		}
	</style>
	<title>HPV报告</title>	
</head>
<body>
<form name="fm"  method="post" id="fm">
	<div class="toolBar"><a href="javascript:backToWechat()" class="back"></a>HPV报告</div>
	<div class="prompt" style="display: none" id="msgdiv">提示框内容</div>
	
	
	
	<div class="xb">一、基本信息</div>
	<div class="xb_dl" style="border: 0;">
		<section class="box_last">
		<div class="bottom_safety">
			<table class="bg_white">
				<tr>
					<td class="pr_top">姓&#12288;&#12288;名：</td>
					<td>张三</td>
				</tr>
				<tr>
					<td class="pr_top">年&#12288;&#12288;龄：</td>
					<td>23岁</td>
				</tr>
				<tr>
					<td class="pr_top">电&#12288;&#12288;话：</td>
					<td>13121212121</td>
				</tr>
				<tr>
					<td class="pr_top">送检单位：</td>
					<td>华大医学</td>
				</tr>
				<tr>
					<td class="pr_top">申请医生：</td>
					<td>华佗</td>
				</tr>
				<tr>
					<td class="pr_top">送检科室：</td>
					<td>送检科室</td>
				</tr>
				<tr>
					<td class="pr_top">门诊号&#12288;：</td>
					<td>111111</td>
				</tr>
				<tr>
					<td class="pr_top">床&#12288;&#12288;号：</td>
					<td>123456</td>
				</tr>
				<tr>
					<td class="pr_top">样本类型：</td>
					<td>HPV</td>
				</tr>
				<tr>
					<td class="pr_top">采集日期：</td>
					<td>2015-11-12</td>
				</tr>
				<tr>
					<td class="pr_top">样本编号：</td>
					<td>111111</td>
				</tr>
			</table>
		</div>
		</section>
	</div>
	<div class="xb add">二、检测结果</div>
	<div class="xb_dl" style="border: 0;">
		<section class="box_last">
		<div class="bottom_safety">
			<table class="bg_white">
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV基因型</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">结&#12288;果</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">参考值</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV16</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV16</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV16</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV16</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;width:33%;">HPV16</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
					<td style="text-align:center;vertical-align:middle;width:33%;">阴性(-)</td>
				</tr>
			</table>
		</div>
		</section>
	</div>
	<div class="xb add">三、检测说明</div>
	<div class="xb_dl" style="border: 0;">
		<section class="box_last">
		<div class="bottom_safety">
			<table class="bg_white">
				<tr>
					<td>1. 本检测共检测以上16种型别，并可具体分型.</td>
				</tr>
				<tr>
					<td>2. 国际癌症研究署   （IARC）2005年报告： 16、 18、31、33、35、39、45、51、52、56、58、59、66、68共
						14种HPV型别被列为高危致癌型HPV型别。HPV6、11型为低危型，是引起生殖道疣的主要致病病毒。</td>
				</tr>
				<tr>
					<td>3. HPV阳性代表您现在正处于HPV感染期，并可能存在“宫颈上皮内瘤变（CIN）”或癌症。详情请咨询临
    					床医生，作进一步确诊，如果医生确认您不需要临床处理，意味着您还没有此类病变，或病变程度较低，您
    					的身体有可能会自行清除病变，这种情况下，您需要临床确诊后一年内，再次接受HPV检测复查，以确定您
    					是否康复。</td>
				</tr>
				<tr>
					<td>4. 本结果只对本次送检样品负责。如对结果有疑义，请在收到结果后7个工作日与华大医学联系。</td>
				</tr>
			</table>
		</div>
		</section>
	</div>
	<div class="xb add">四、其他信息</div>
	<div class="xb_dl" style="border: 0;">
		<section class="box_last">
		<div class="bottom_safety">
			<table class="bg_white">
				<tr>
					<td class="pr_top">操作者&#12288;：</td>
					<td>张三</td>
				</tr>
				<tr>
					<td class="pr_top">审核者&#12288;：</td>
					<td>张三</td>
				</tr>
				<tr>
					<td class="pr_top">报告日期：</td>
					<td>2015-11-12</td>
				</tr>
				<tr>
					<td class="pr_top">地&#12288;&#12288;址：</td>
					<td>广东省深圳市盐田区北山工业区11栋（邮编：518083）</td>
				</tr>
				<tr>
					<td class="pr_top">客服电话：</td>
					<td>400-605-6655</td>
				</tr>
				<tr>
					<td class="pr_top">网&#12288;&#12288;址：</td>
					<td>www.bgidx.cn</td>
				</tr>
			</table>
		</div>
		</section>		
	</div>
</form>

<%@ include file="/common/meta_m_js.jsp"%>
<script type="text/javascript" src="${ctx }/widgets/form_validate.js"></script>
<script type="text/javascript" src="${ctx }/widgets/validatecode.js"></script>
<script type="text/javascript">

<%-- 初始化入口 --%>
$(function() {

	
	$('.xb:first').next().show();
	$('.xb').click(function(){
		$(this).toggleClass('add');
		$(this).next().slideToggle();
	});
	
});

<%-- 返回微信窗口 --%>
function backToWechat(){
	WeixinJSBridge.invoke('closeWindow',{},function(res){
	    //alert(res.err_msg);
		//关闭成功返回“close_window:ok”，关闭失败返回“close_window:error”
	});
}

 <%-- 消息提示框隐藏 --%>
function hideMsg() {
	$("#msgdiv").hide();
}

</script>
</body>
</html>