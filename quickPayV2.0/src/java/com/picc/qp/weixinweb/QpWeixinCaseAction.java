package com.picc.qp.weixinweb;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.hunan.service.RandomUtil;
import com.hunan.service.SVSUtil;
import com.opensymphony.xwork2.ActionContext;
import com.picc.common.enums.CompanyCode;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.UuidUtil;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTCOMHandlePolice;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTIcCompanyGarage;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetail;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.UserKey;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxTask;
import com.picc.qp.schema.model.WxTaskFinish;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTAsyncTaskService;
import com.picc.qp.service.facade.IQpTComHandlePoliceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageDetailService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.facade.UserKeyService;
import com.picc.qp.service.spring.wx.WxTaskFinishServiceSpringImpl;
import com.picc.qp.service.wx.facade.QpWeixinCaseService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.service.wx.facade.WxTaskService;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;
import com.picc.qp.util.SealUtil;
import com.picc.qp.web.QpTIcCompanyGarageAction;
import com.picc.qp.web.QpTIcCompanyGarageAction.MyCompartor;
import com.picc.report.schema.model.ReportCaseData;
import com.picc.report.schema.model.ReportTask;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.service.facade.IReportCaseDataService;
import com.picc.report.service.facade.IReportTaskDataService;
import com.picc.report.service.facade.IReportTaskService;
import com.picc.um.schema.model.UmTAccidentUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTAccidentUserService;

/**
 * 微信案件处理
 * @author ff
 * 2016年7月6日
 */
/**
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
public class QpWeixinCaseAction  extends Struts2Action{

	private WxCase wxCase;

	private WxAccident wxAccident;

	private QpWeixinCaseService qpWeixinCaseService;

	private IQpTICPictureService qpTICPictureService;

	private IQpTCommonService qpTCommonService;

	private IQpTTPCaseService qpTTPCaseService;

	private IQpTICAccidentService qpTICAccidentService;

	private IQpTTPLawService qpTTPLawService;

	private WxTaskService wxTaskService;

	private WxTask wxTask;

	private WxTaskFinishServiceSpringImpl wxTaskFinishService;

	private WxTaskFinish wxTaskFinish;

	private WxCaseService wxCaseService;
	
	private IQpTIcCompanyGarageDetailService qpTIcCompanyGarageDetailService;
	private IQpTIcCompanyGarageService qpTIcCompanyGarageService;
	private IQpTICCompanyService qpTICCompanyService;
	
	private IReportTaskService iReportTaskService;
	
	private IReportTaskDataService iReportTaskDataService;
	
	private IReportCaseDataService iReportCaseDataService;

	private UserKeyService userKeyService;
	
	private IQpTComHandlePoliceService qpTComHandlePoliceService;
	
	private IUmTAccidentUserService umTAccidentUserService;

	/**
	 * 进入微信案件任务列表界面
	 * @return
	 */
	public String prepareQuery(){

		return SUCCESS;
	}

	/**
	 * 微信案件任务列表查询 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String query(){
		if(page <= 0){
			page = 1;
		}
		if(rows <= 5){
			rows = 5;
		}
		wxAccident = new WxAccident();
		wxAccident.setIdentfinynumber("431003199210050630");
		Page resultPage = qpWeixinCaseService.findQpWeixinCasePageBySql(wxAccident, page, rows);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", resultPage.getTotalCount());
		jsonObject.put("rows", new JSONArray().fromObject(resultPage.getResult()));
		this.writeJson(jsonObject.toString());
		return NONE;
	}

	/**
	 * 微信案件详情界面
	 * @return
	 */
	public String prepareQueryCase(){
		HttpServletRequest request = this.getRequest();
		try {
			SessionUser sessionUser = this.getUserFromSession();
			String caseId = request.getParameter("caseId");
			String taskId = request.getParameter("taskId");
			request.setAttribute("taskId", taskId);
			request.setAttribute("caseId", caseId);

			wxTask =  wxTaskService.findWxTaskById(Integer.parseInt(taskId));
			wxTask.setStatus("3");
			wxTask.setReceiveCode(sessionUser.getUserCode());
			wxTask.setReceiveName(sessionUser.getUserName());
			wxTask.setReceiveTime(new Date());

			wxTaskService.updateWxTask(wxTask);
			caseId = wxTask.getCaseId();
			if(StringUtils.isEmptyStr(caseId)){
				return NONE;
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);	
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			List<QpTICAccident> qpTICAccidents =  qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);


			// 承担责任
			List<QpSelectDataVo> responsibilityTypeList = qpTCommonService.getSelectData(SelectTypeEnum.RESPONSIBILITY.getCode());
			request.setAttribute("responsibilityTypeList", responsibilityTypeList);

			// 违反法律法规 
			List<QpTTPLaw> qpTTPLawList = qpTTPLawService.findByQpTTPLaw(new QpTTPLaw());
			request.setAttribute("qpTTPLawList", qpTTPLawList);

			// 准驾车型
			List<QpSelectDataVo> permissionDriveTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.PERMISSION_DRIVE.getCode());
			request.setAttribute("permissionDriveTypeList", permissionDriveTypeList);

			// 保险公司
			List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
			request.setAttribute("qpTICCompanyList", qpTICCompanyList);

			List<QpTICPicture> filePathList = new ArrayList<QpTICPicture>();
			for (QpTICAccident qpTICAccident : qpTICAccidents) {
				String  groupId =  qpTICAccident.getGroupId();
				if(StringUtils.isEmptyStr(groupId)){
					continue;
				}
				List<QpTICPicture> ticPicturesList = qpTICPictureService.findQpTICPictureByGroupId(groupId);
				if (ticPicturesList != null && ticPicturesList.size() > 0) {
					for (int i = 0; i < ticPicturesList.size(); i++) {
						QpTICPicture ticPicture = ticPicturesList.get(i);
						filePathList.add(ticPicture);
					}
				}
			}
			ActionContext.getContext().put("filePathList", filePathList);
			ActionContext.getContext().put("imgHeader", Constants.getIMAGEHTTPQUERY());
			ActionContext.getContext().put("qpTTPCase", qpTTPCase);
			ActionContext.getContext().put("qpTICAccidents", qpTICAccidents);
			request.setAttribute("taskId", taskId);
			request.setAttribute("caseId", caseId);
			request.setAttribute("isDue", wxTask.getIsDue());
		} catch (Exception e) {
			logger.error("微信交警定责界面图片查询异常", e);
		}

		return SUCCESS;
	}

	private IQpTAsyncTaskService qpTAsyncTaskService;

	public IQpTAsyncTaskService getQpTAsyncTaskService() {
		return qpTAsyncTaskService;
	}

	public void setQpTAsyncTaskService(IQpTAsyncTaskService qpTAsyncTaskService) {
		this.qpTAsyncTaskService = qpTAsyncTaskService;
	}

	/**
	 * 确认定责
	 * @return
	 */
	public String prepareQueryCaseConfirm(){
		JSONObject jsonObject = new JSONObject();
		try {
			SessionUser sessionUser = this.getUserFromSession();
			HttpServletRequest request = this.getRequest();
			boolean sendFlag = false;
			String taskId = request.getParameter("taskId");//案件ID
			wxTask = wxTaskService.findWxTaskById(Integer.valueOf(taskId));
			if(StringUtils.isEmptyStr(wxTask)){
				//任务不存在
				jsonObject.put("code", -1);
				jsonObject.put("msg", "任务不存在，请刷新重试");
				this.writeJson(jsonObject);
				return NONE;
			}
			if("3".equals(wxTask.getStatus()) && !StringUtils.isEmptyStr(wxTask.getReceiveCode()) && !wxTask.getReceiveCode().equals(sessionUser.getUserCode())){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "不能完成别人领取的任务");
				this.writeJson(jsonObject);
				return NONE;
			}
			if("4".equals(wxTask.getStatus())){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "该任务已完成");
				this.writeJson(jsonObject);
				return NONE;
			}

			String caseId = wxTask.getCaseId();//案件ID
			String acciId1 = request.getParameter("acciId1");//当事人1 ID
			String acciId2 = request.getParameter("acciId2");//当事人2 ID
			String qpTICAccidentDriverLawId1 = request.getParameter("qpTICAccidentDriverLawId1");//当事人1	违反的法律法规 :未保护事故现场
			String qpTICAccidentDriverLawId2 = request.getParameter("qpTICAccidentDriverLawId2");//当事人2	违反的法律法规  :
			String responsibilitycode1 = request.getParameter("responsibilitycode1");//当事人1	承担的 责任  : 主要责任
			String responsibilitycode2 = request.getParameter("responsibilitycode2");//当事人2	承担的责任  :
			String permissionDriveType1 = request.getParameter("permissionDriveType1");//当事人1	准驾车型
			String permissionDriveType2 = request.getParameter("permissionDriveType2");//当事人2	准驾车型
			String qpTTPCaseCaseNotes = request.getParameter("qpTTPCaseCaseNotes");//当事人2	事故详情:相撞，造成甲乙两车受损的交通事故。
			String qpTTPCaseCaseResult = request.getParameter("qpTTPCaseCaseResult");//当事人2	调解结果 :甲/乙两车同等责任。

			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase =  qpTTPCaseService.findQpTTPCaseByPK(id);
			qpTTPCase.setCaseNotes(qpTTPCaseCaseNotes);//案件事故详情
			qpTTPCase.setCaseResult(qpTTPCaseCaseResult);//案件 调解结果
			qpTTPCase.setTpHandleStatus("2");//状态改为待查勘
			qpTTPCase.setDingzeTime(new Date());
			qpTTPCase.setPoliceName(sessionUser.getUserName());
			qpTTPCase.setPoliceEmployeeId(sessionUser.getUserCode());
			//默认快处中心26的民警
			QpTCOMHandlePolice qpTCOMHandlePolice = new QpTCOMHandlePolice();
			qpTCOMHandlePolice.setCenterId("26");
			List<QpTCOMHandlePolice> findByHandlePolice = qpTComHandlePoliceService.findByHandlePolice(qpTCOMHandlePolice);
			if(null != findByHandlePolice && findByHandlePolice.size()>0){
				qpTTPCase.setHandlePoliceNO(findByHandlePolice.get(0).getHandlePoliceNO());
				qpTTPCase.setHandlePoliceName(findByHandlePolice.get(0).getHandlePoliceName());
			}
			
			qpTTPCaseService.saveOrupdateQpTTPCase(qpTTPCase);

			// 案件保存到 TODO 一路通案件提交先关闭
//			try {
//				Map<String, String> params = new HashMap<String, String>();
//				params.put("caseId", qpTTPCase.getCaseId());
//				qpTAsyncTaskService.createTask(UploadCaseToCSHandler.type, params);
//			} catch (Exception e) {
//				logger.error("---- 新增任务到待推送列表失败, caseID=" + qpTTPCase.getCaseId() + " ----");
//			}

			List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
			QpTICAccident qpTICAccident1 = null;
			QpTICAccident qpTICAccident2 = null;
			for (QpTICAccident qpTICAccident : qpTICAccidents) {
				if(acciId1.equals(qpTICAccident.getAcciId())){
					qpTICAccident.setDriverLiability(responsibilitycode1);//承担的责任
					qpTICAccident.setDriverLawId(qpTICAccidentDriverLawId1);
					qpTICAccident.setPermissionDrive(permissionDriveType1);
					qpTICAccidentService.saveQpTICAccident(qpTTPCase, qpTICAccident);
					qpTICAccident1 = qpTICAccident;
				}
				if(acciId2.equals(qpTICAccident.getAcciId())){
					qpTICAccident.setDriverLiability(responsibilitycode2);
					qpTICAccident.setDriverLawId(qpTICAccidentDriverLawId2);
					qpTICAccident.setPermissionDrive(permissionDriveType2);
					qpTICAccidentService.saveQpTICAccident(qpTTPCase, qpTICAccident);
					qpTICAccident2 = qpTICAccident;
				}
			}

			String refFrom = wxTask.getRegFrom();
			JSONObject garage = this.queryCompanyGarage(caseId);
			if("0".equals(refFrom)){//推送自己公众号短信
				// 承担责任
				String rep_1 = "";
				String rep_2 = "";
				List<QpSelectDataVo> responsibilityTypeList = qpTCommonService.getSelectData(SelectTypeEnum.RESPONSIBILITY.getCode());
				for (QpSelectDataVo qpSelectDataVo : responsibilityTypeList) {
					if(qpTICAccident1.getDriverLiability().equals(qpSelectDataVo.getCodeCode())){
						rep_1 = qpSelectDataVo.getCodeCName();
					}
					if(qpTICAccident2.getDriverLiability().equals(qpSelectDataVo.getCodeCode())){
						rep_2 = qpSelectDataVo.getCodeCName();
					}
				}
				
				if("0".equals(garage.getString("code"))){
					JSONObject jsonObject2 = garage.getJSONObject("data");
					sendFlag = qpTICAccidentService.sendSmsToClient2(qpTICAccidents, jsonObject2, "QuickPay");
				}else {
					jsonObject.put("code", 0);
					jsonObject.put("msg", "定责失败,定损点查询失败,短信未推送！");
					this.writeJson(jsonObject);
					return NONE;
				}
				
				// 修改临时案件状态
				WxCase wxCase = wxCaseService.findCaseBuRealID(caseId);
				if (wxCase != null) {
					wxCase.setStatus("7");
					wxCaseService.save(wxCase);
				}
				if("0".equals(garage.getString("code"))){
					JSONObject jsonObject2 = garage.getJSONObject("data");
					//推送模板消息至公众号
					JSONObject parse = new JSONObject();
					parse.put("templateId", Constants.getFINISH_TEMPLATEID());
					parse.put("first", "您好，事故已完成定责。\n");
					parse.put("color0", "#9D9D9D");
					parse.put("keyword1", wxTask.getCaseId());
					parse.put("color1", "#004B97");
					parse.put("keyword2", qpTICAccident1.getDriverName()+" : "+rep_1+","+qpTICAccident2.getDriverName()+" : "+rep_2);
					parse.put("color2", "#FF0000");
					parse.put("keyword3",jsonObject2.getString("companyGarageName"));
					parse.put("color3", "#004B97");
					parse.put("keyword4", jsonObject2.getString("servicePhone"));
					parse.put("color4", "#004B97");
					parse.put("keyword5", jsonObject2.getString("address")+"\n");
					parse.put("color5", "#004B97");
					parse.put("remark", "谢谢使用，请留意本公众号最新动态。");
					parse.put("color6", "#9D9D9D");
					parse.put("url", Constants.getFINISHTEMPLATE_URL()+"?wxCaseId="+caseId);
					if(null != qpTICAccident1.getOpenID() && !"".equals(qpTICAccident1.getOpenID())){
						parse.put("openId",qpTICAccident1.getOpenID());
						Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(Constants.getTEMPLATEID_URL(), parse.toString(), "UTF-8");
						logger.info("qpTICAccident1_"+qpTICAccident1.getOpenID()+":"+sendMap);
					}
					if(null != qpTICAccident2.getOpenID() && !"".equals(qpTICAccident2.getOpenID())){
						parse.put("openId",qpTICAccident2.getOpenID());
						Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(Constants.getTEMPLATEID_URL(), parse.toString(), "UTF-8");
						logger.info("qpTICAccident2_"+qpTICAccident2.getOpenID()+":"+sendMap);
					}
				}else {
					jsonObject.put("code", 0);
					jsonObject.put("msg", "定责失败,定损点查询失败！");
					this.writeJson(jsonObject);
					return NONE;
				}
				
			}else{
				//推送短信  长沙交警
				//		qpTICAccidentService.sendSmsToClient(qpTICAccidents);
				//110902
				//【长沙公安交警】您好，您的事故已完成定责，事故编号为{1},您的责任为:{2},对方责任为:{3};为您推荐的定损点为:{4},地址:{5},电话:{6}。感谢您使用长沙公安交警微信快处快赔。
				//		qpTICAccidentService.sendSmsToClient2(qpTICAccidents);
				if("0".equals(garage.getString("code"))){
					JSONObject jsonObject2 = garage.getJSONObject("data");
					sendFlag = qpTICAccidentService.sendSmsToClient2(qpTICAccidents, jsonObject2, "DXW");
				}else {
					jsonObject.put("code", 0);
					jsonObject.put("msg", "定责失败,定损点查询失败,短信未推送！");
					this.writeJson(jsonObject);
					return NONE;
				}

			}
			
			//TODO 平安报案
			//10 全责  11主要责任 12次要责任  13同等责任  14无责任    循环是为了确定平安保险 - 有责任
			for (int i = 0; i < qpTICAccidents.size(); i++) {
				QpTICAccident qpTICAccident = qpTICAccidents.get(i);
//				if("3".equals(qpTICAccident.getCoId())){//3是平安
				String orgCode = CompanyCode.Company.getCompanyCode(qpTICAccident.getCoId());
				String comChannel = Constants.getREPORT_INTERFACE().get(orgCode+"_channel");
					if("0".equals(comChannel)){
						if(!"14".equals(qpTICAccident.getDriverLiability())){
							//平安报案
							try {
								ReportCaseData reportCaseData = iReportCaseDataService.findReportCaseDataByCaseIdAndAccId(qpTTPCase.getCaseId(), qpTICAccident.getAcciId());
								logger.info("是否报案完成："+JSON.toJSONString(reportCaseData));
								if(reportCaseData == null){
									ReportTaskData reportTaskData = iReportTaskDataService.findReportTaskDataByAccId(qpTICAccident.getAcciId());
									logger.info("是否正在报案中："+JSON.toJSONString(reportTaskData));
									if(reportTaskData == null){
										logger.info(new Date() + "报案:保险公司:"+orgCode + ",caseID:"+qpTTPCase.getCaseId() + ",accID:"+qpTICAccident.getAcciId());
										String uuid = UuidUtil.get32UUID();
										Date now = new Date();
										ReportTask reportTask = new ReportTask();
										reportTask.setId("BA"+uuid);
										reportTask.setTaskDataId(uuid);
										reportTask.setTaskType("1");
										reportTask.setCreateTime(now);
										iReportTaskService.add(reportTask);
										reportTaskData = new ReportTaskData();
										reportTaskData.setId(uuid);
										reportTaskData.setTaskDataId(uuid);
										reportTaskData.setCaseId(caseId);
										reportTaskData.setAccId(qpTICAccident.getAcciId());
										reportTaskData.setCreateTime(now);
										reportTaskData.setOrgCode(orgCode);
										iReportTaskDataService.add(reportTaskData);
									}
								}
							} catch (Exception e) {
								logger.error("--report--微信记录报案异常,案件ID:"+qpTTPCase.getCaseId() + "，标的当事人id:"+ qpTICAccident.getAcciId()+"保险公司:"+orgCode + ",异常信息:",e);
							}
						}
					}
					
					
					//新增案件添加当事人
					try {
						UmTAccidentUser umTAccidentUser = new UmTAccidentUser();
						umTAccidentUser.setIDNumber(qpTICAccident.getDriverIDNumber());
						List<UmTAccidentUser> umTAccidentUsers = umTAccidentUserService.findByUmTAccidentUser(umTAccidentUser);
						if(umTAccidentUsers == null || umTAccidentUsers.size() <= 0){
							umTAccidentUser = new UmTAccidentUser();
							umTAccidentUser.setUserName(qpTICAccident.getDriverName());
							umTAccidentUser.setUserMobile(qpTICAccident.getDriverMobile());
							umTAccidentUser.setIDNumber(qpTICAccident.getDriverIDNumber());
							umTAccidentUser.setCoID(qpTICAccident.getCoId());
							umTAccidentUser.setLicenseNo(qpTICAccident.getDriverVehicleNumber());
							umTAccidentUser.setChassisNumber(qpTICAccident.getChassisNumber());
							umTAccidentUser.setAddress(qpTICAccident.getAcciStreet());
							umTAccidentUser.setVehicleCode(qpTICAccident.getDriverVehicleType());
							umTAccidentUser.setPermissionDriveCode(qpTICAccident.getPermissionDrive());
							umTAccidentUser.setLableType(qpTICAccident.getLabelType());
							umTAccidentUser.setCreateTime(new Date());
							umTAccidentUserService.saveUmTAccidentUser(umTAccidentUser);
						}else {
							umTAccidentUser = umTAccidentUsers.get(0);
							umTAccidentUser.setUserName(qpTICAccident.getDriverName());
							umTAccidentUser.setUserMobile(qpTICAccident.getDriverMobile());
							umTAccidentUser.setIDNumber(qpTICAccident.getDriverIDNumber());
							umTAccidentUser.setCoID(qpTICAccident.getCoId());
							umTAccidentUser.setLicenseNo(qpTICAccident.getDriverVehicleNumber());
							umTAccidentUser.setChassisNumber(qpTICAccident.getChassisNumber());
							umTAccidentUser.setAddress(qpTICAccident.getAcciStreet());
							umTAccidentUser.setVehicleCode(qpTICAccident.getDriverVehicleType());
							umTAccidentUser.setPermissionDriveCode(qpTICAccident.getPermissionDrive());
							umTAccidentUser.setLableType(qpTICAccident.getLabelType());
							umTAccidentUser.setCreateTime(new Date());
							umTAccidentUserService.updateUmTAccidentUser(umTAccidentUser);
						}
					} catch (Exception e) {
						logger.error("新增或修改案件处理案件当事人信息异常", e);
					}
//				}
			}


			wxTaskFinish = new WxTaskFinish();
			wxTaskFinish.setFinishId(wxTask.getTaskId());
			wxTaskFinish.setCaseId(wxTask.getCaseId());
			wxTaskFinish.setCreateCode(wxTask.getCreateCode());
			wxTaskFinish.setCreateName(wxTask.getCreateName());
			wxTaskFinish.setCreateTime(wxTask.getCreateTime());
			wxTaskFinish.setReceiveCode(wxTask.getReceiveCode());
			wxTaskFinish.setReceiveName(wxTask.getReceiveName());
			wxTaskFinish.setReceiveTime(wxTask.getReceiveTime());

			wxTaskFinish.setCompleteCode(sessionUser.getUserCode());
			wxTaskFinish.setCompleteName(sessionUser.getUserName());
			wxTaskFinish.setCompleteTime(new Date());

			wxTaskFinish.setRegFrom(refFrom);
			//	    wxTaskFinish.setStatus("");//预留分类用。
			wxTaskFinishService.addWxTaskFinish(wxTaskFinish);//把完结案件放入另一张表，在原表中删除。
			if(sendFlag){
				jsonObject.put("code", 0);
				jsonObject.put("msg", "定责完成,继续查看任务！");
			}else {
				jsonObject.put("code", -1);
				jsonObject.put("msg", "定责完成,<font color=red>短信发送失败</font>");
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("msg", "定责失败，请刷新重试");
			logger.error("wx任务案件交警定责出现异常", e);
		}
		this.writeJson(jsonObject);
		return NONE;
	}
	
	
	/**
	 * 撤销案件
	 * @return
	 */
	public String prepareQueryCaseUndo(){
		JSONObject jsonObject = new JSONObject();
		try {
			SessionUser sessionUser = this.getUserFromSession();
			HttpServletRequest request = this.getRequest();
			String taskId = request.getParameter("taskId");//案件ID
			wxTask = wxTaskService.findWxTaskById(Integer.valueOf(taskId));
			if(StringUtils.isEmptyStr(wxTask)){
				//任务不存在
				jsonObject.put("code", -1);
				jsonObject.put("msg", "任务不存在，请刷新重试");
				this.writeJson(jsonObject);
				return NONE;
			}
			if("3".equals(wxTask.getStatus()) && !StringUtils.isEmptyStr(wxTask.getReceiveCode()) && !wxTask.getReceiveCode().equals(sessionUser.getUserCode())){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "不能完成别人领取的任务");
				this.writeJson(jsonObject);
				return NONE;
			}
			if("4".equals(wxTask.getStatus())){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "该任务已完成");
				this.writeJson(jsonObject);
				return NONE;
			}

			String caseId = wxTask.getCaseId();//案件ID
			String remark = request.getParameter("remark");//当事人1 ID
			if(StringUtils.isEmptyStr(remark)){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "请填写撤销原因在提交");
				this.writeJson(jsonObject);
				return NONE;
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase =  qpTTPCaseService.findQpTTPCaseByPK(id);
			qpTTPCase.setTpHandleStatus("3");//状态改为已退回
			qpTTPCaseService.saveOrupdateQpTTPCase(qpTTPCase);

			if (wxCase != null) {
				wxCase.setStatus("9");
				wxCaseService.save(wxCase);
			}

			wxTaskFinish = new WxTaskFinish();
			wxTaskFinish.setFinishId(wxTask.getTaskId());
			wxTaskFinish.setCaseId(wxTask.getCaseId());
			wxTaskFinish.setCreateCode(wxTask.getCreateCode());
			wxTaskFinish.setCreateName(wxTask.getCreateName());
			wxTaskFinish.setCreateTime(wxTask.getCreateTime());
			wxTaskFinish.setReceiveCode(wxTask.getReceiveCode());
			wxTaskFinish.setReceiveName(wxTask.getReceiveName());
			wxTaskFinish.setReceiveTime(wxTask.getReceiveTime());

			wxTaskFinish.setCompleteCode(sessionUser.getUserCode());
			wxTaskFinish.setCompleteName(sessionUser.getUserName());
			wxTaskFinish.setCompleteTime(new Date());

			wxTaskFinish.setRegFrom(wxTask.getRegFrom());
			wxTaskFinish.setStatus("9");//9  撤销。
			wxTaskFinish.setRemark("撤销原因:"+remark);
			wxTaskFinishService.addWxTaskFinish(wxTaskFinish);//把完结案件放入另一张表，在原表中删除。
			jsonObject.put("code", 0);
			jsonObject.put("msg", "撤销完成,继续定责！");
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("msg", "撤销失败，请刷新重试");
			logger.error("wx任务案件交警撤销出现异常", e);
		}
		this.writeJson(jsonObject);
		return NONE;
	}
	/**
	 * 任务状态改回滚
	 * @return
	 */
	public String roolbackWxCase(){
		String taskId = this.getRequest().getParameter("taskId");
		wxTask =  wxTaskService.findWxTaskById(Integer.parseInt(taskId));
		wxTask.setStatus("2");
		wxTask.setReceiveTime(new Date());

		wxTaskService.updateWxTask(wxTask);
		return NONE;
	}

	public String prepareQueryWxTask() throws Exception{
		SessionUser sessionUser = this.getUserFromSession();
		String userCode = sessionUser.getUserCode();
		//此处判断是否需要u盾登录
		if(!"admin".equals(userCode) && !"JL000003".equals(userCode)){
			String ca_sn = (String)this.getSession().getAttribute("ca_sn");
			//session中没有证书序列号的需要登录
			if(StringUtils.isEmptyStr(ca_sn)){
				//后台产生随机数，作为签名原文（基于随机数的数字签名实现数字证书登录）
				String ca_data=RandomUtil.randomString(6);
				this.getRequest().setAttribute("ca_data",ca_data);
				return "hunanca_login";
			}
		}

		return SUCCESS;
	}

	public String caLogin()throws Exception{
		HttpServletRequest request = this.getRequest();
		String ca_cert=(String)request.getParameter("ca_cert");//获取数字证书
		String ca_sign=(String)request.getParameter("ca_sign");//获取签名结果
		String ca_data=(String)request.getParameter("ca_data");/*获取签名原文（后台产生的随机数）*/

		//验证证书有效性
		SVSUtil svs=new SVSUtil();
		Boolean isCheck=svs.checkByCert(ca_cert,ca_sign,ca_data);
		String failText = "";
		if(!isCheck){
			failText = "远程定责需要证书登录,您的证书已过期，请联系系统管理员!";
			//return "fail";
		}

		//解析证书SN号（证书唯一标识项）
		String ca_sn=svs.HuncaGetSn(ca_cert);
		logger.info("证书的序列号为:"+ca_sn);
		// 数字证书与系统用户绑定功能（此处由系统开发商完成）
		//   * 1、在数据库用户表增加一个字段certSN（varchar2(50)）保存证书唯一标识SN号，建立证书与系统用户的对应关系；
		// * 2、登陆时根据获取的证书SN号匹配对应用户权限进行登录
		//根据登录用户查出绑定证书的序列号进行比较

		SessionUser sessionUser = this.getUserFromSession();
		String userCode = sessionUser.getUserCode();
		UserKey userKey = userKeyService.findByUserCode(userCode);
		String key = "";
		if (userKey != null) {
			key = userKey.getUserKey();
			if(!key.equals(ca_sn)){
				failText = "远程定责需要证书登录,您使用的快处快赔用户绑定证书和插入的证书不一致，请核实!";
			}
		} else {
			//			key = "7629661178280696352";
			failText = "远程定责需要证书登录,您使用的快处快赔用户未绑定证书，请联系管理员!";
		}
		if(StringUtils.isNotEmpty(failText)){
			request.setAttribute("failText", failText);
			return "fail";
		}else{
			this.getSession().setAttribute("ca_sn", ca_sn);
		}

		return SUCCESS;
	}
	public String queryWxTask() throws Exception{
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 10;
		}
		try {
			//			Page resultPage = wxTaskService.getPage(wxTask, page, rows);
			Page resultPage = wxTaskService.getUserCodePage(wxTask, page, rows,this.getUserFromSession().getUserCode());

			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 任务领取前，任务状态判断
	 * @return
	 */
	public String querySingleWxTask(){
		Map<String, String> resultMap = new HashMap<String, String>();
		SessionUser sessionUser = this.getUserFromSession();
		String taskId = this.getRequest().getParameter("taskId");
		wxTask =  wxTaskService.findWxTaskById(Integer.parseInt(taskId));
		if(StringUtils.isEmptyStr(wxTask)){
			resultMap.put("msg", "无该案件记录！");
		}else{
			String status = wxTask.getStatus();
			String sessionUserCode = sessionUser.getUserCode();
			if("3".equals(status) && !wxTask.getReceiveCode().equals(sessionUserCode)){
				resultMap.put("msg", "案件定责处理中！");
			}else if("1".equals(status) || "3".equals(status)){
				resultMap.put("msg", "1");
			}
		}
		this.writeJson(resultMap);
		return NONE;
	}


	@SuppressWarnings("unchecked")
	public JSONObject queryCompanyGarage(String caseId){
		JSONObject result = new JSONObject();

		try {
			if(StringUtils.isEmptyStr(caseId)){
				result.put("code", -1);
				result.put("msg", "caseId不存在");
				return result;
			}
			QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
			qpTTPCaseId.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
			if(StringUtils.isEmptyStr(qpTTPCase)){
				result.put("code", -1);
				result.put("msg", "案件不存在");
				return result;
			}

			List<QpTICAccident> accidents =  qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
			if(StringUtils.isEmptyStr(accidents) || accidents.size() <= 0){
				result.put("code", -1);
				result.put("msg", "该案件无当事人");
				return result;
			}
			if(accidents.size() != 2){
				result.put("code", -1);
				result.put("msg", "暂时不支持2个当事人以外的案件查询");
				return result;
			}
			//10 全部责任  11 主要责任  12次要责任  13同等责任 14无责任
			//		    QpTICAccident qpTICAccidentA = accidents.get(0);
			//		    QpTICAccident qpTICAccidentB = accidents.get(1);
			String coId = "";
			for (QpTICAccident qpTICAccident : accidents) {
				if("10".equals(qpTICAccident.getDriverLiability())){
					coId = qpTICAccident.getCoId();
					break;
				}
			}

			double lat = 0d;
			double lng  = 0d;
			if(StringUtils.isEmptyStr(qpTTPCase.getLatitude()) || StringUtils.isEmptyStr(qpTTPCase.getLongitude())){
				String address = qpTTPCase.getCaseStreet();
				if(StringUtils.isEmptyStr(address)){
					result.put("code", -1);
					result.put("msg", "案件地址不存在");
					result.put("data", null);
					return result;
				}else{
					Map<String, Object> map = QpTIcCompanyGarageAction.getLatAndLngByAddress(address);
					if("200".equals(map.get("code"))){
						JSONObject resultData = JSONObject.fromObject(map.get("info"));
						if("0".equals(resultData.getString("status"))){
							lng = resultData.getJSONObject("result").getJSONObject("location").getDouble("lng");
							lat = resultData.getJSONObject("result").getJSONObject("location").getDouble("lat");

						}else{
							//查询失败
							logger.error("返回修理厂时，根据地址查询经纬度失败:" + map);
							result.put("code", -1);
							result.put("msg", "查询失败,请稍后在试(地址错误)");
							result.put("data", null);
							return result;
						}
					}else{
						//失败
						//查询失败
						logger.error("返回修理厂时，根据地址查询经纬度失败:" + map);
						result.put("code", -1);
						result.put("msg", "查询失败,请稍后在试(地址错误)");
						result.put("data", null);
						return result;
					}
				}
			}else{
				try {
					lat = Double.valueOf(qpTTPCase.getLatitude());
					lng = Double.valueOf(qpTTPCase.getLongitude());
				} catch (Exception e) {
					logger.error("经纬度格式转换错误:" , e);
					result.put("code", -1);
					result.put("msg", "查询失败(案件经纬度格式错误)");
					result.put("data", null);
					return result;
				}

			}

			if(!StringUtils.isEmptyStr(coId)){
				//主要责任  或 全部责任      快赔点 
				QpTICCompanyId id = new QpTICCompanyId();
				id.setCoId(coId);
				QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(coId);
				List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
				if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
					qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
				}else{
					qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
				}

				for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
					qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
				}
				//			    Collections.sort(studentList,mc);     //按照age升序 22，23
				//			    Collections.reverse(studentList,mc);    //按照age降序 23,22  
				MyCompartor mc = new MyCompartor();
				Collections.sort(qpTIcCompanyGarageDetails, mc);


				result.put("code", 0);
				result.put("msg", "查询成功");
				result.put("data", qpTIcCompanyGarageDetails.get(0));

			}else{
				//用来去掉重复
				Set<QpTIcCompanyGarageDetail> setQpTIcCompanyGarageDetails = new HashSet<QpTIcCompanyGarageDetail>();
				//同等责任
				for (QpTICAccident qpTICAccident : accidents) {
					QpTICCompanyId id = new QpTICCompanyId();
					id.setCoId(qpTICAccident.getCoId());
					QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(qpTICAccident.getCoId());
					List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
					if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
						qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
					}else{
						qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
					}
					logger.info("主要责任人快赔点:" + JSONArray.fromObject(qpTIcCompanyGarageDetails));
					for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
						qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
						setQpTIcCompanyGarageDetails.add(qpTIcCompanyGarageDetail);
					}
					//			    Collections.sort(studentList,mc);     //按照age升序 22，23
					//			    Collections.reverse(studentList,mc);    //按照age降序 23,22  
				}
				List<QpTIcCompanyGarageDetail> orderByQpTIcCompanyGarageDetails = new ArrayList<QpTIcCompanyGarageDetail>();
				orderByQpTIcCompanyGarageDetails.addAll(setQpTIcCompanyGarageDetails);
				MyCompartor mc = new MyCompartor();
				Collections.sort(orderByQpTIcCompanyGarageDetails, mc);

				result.put("code", 0);
				result.put("msg", "查询成功");
				result.put("data", orderByQpTIcCompanyGarageDetails.get(0));
			}
			//分两种情况:
			//1: 只传caseId
			//2： 传caseId  和    经纬度
		} catch (Exception e) {
			result.put("code", -1);
			result.put("code", "查询失败,请稍后在试");
			logger.error("获取修理厂地址失败", e);
		}
		return result;
	}


	//进入责任认定书查询首界面
	public String ResponsibilityConfirmationQuery(){
		HttpServletRequest request = this.getRequest();
		request.setAttribute("errorMsg", "");
		return SUCCESS;
	}


	//进入责任认定书查询首界面
	public String ResponsibilityConfirmationDown(){
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		try {
			String caseId = request.getParameter("caseId");
			String driverVehicleNumber = request.getParameter("driverVehicleNumber");
			request.setAttribute("caseId", caseId);
			request.setAttribute("driverVehicleNumber", driverVehicleNumber);

			if(StringUtils.isEmptyStr(caseId)){
				request.setAttribute("errorMsg", "请输入事故编号");
				return SUCCESS;
			}
			if(StringUtils.isEmptyStr(driverVehicleNumber)){
				request.setAttribute("errorMsg", "请输入车牌编号");
				return SUCCESS;
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			if(StringUtils.isEmptyStr(qpTTPCase)){
				request.setAttribute("errorMsg", "下载失败,案件不存在");
				return SUCCESS;
			}
			List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
			if(StringUtils.isEmptyStr(qpTICAccidents) || qpTICAccidents.size() <= 0){
				request.setAttribute("errorMsg", "下载失败,无效案件");
				return SUCCESS;
			}
			if(!"26".equals(qpTTPCase.getCenterId())){
				request.setAttribute("errorMsg", "下载失败,不是远程案件");
				return SUCCESS;
			}
			if(!"2".equals(qpTTPCase.getTpHandleStatus()) && !"4".equals(qpTTPCase.getTpHandleStatus()) && !"5".equals(qpTTPCase.getTpHandleStatus())){
				request.setAttribute("errorMsg", "下载失败,案件处理中");
				return SUCCESS;
			}
			boolean flag = false;
			for (QpTICAccident qpTICAccident : qpTICAccidents) {
				if(qpTICAccident.getDriverVehicleNumber().equals(driverVehicleNumber)){
					flag = true;
				}
			}
			if(!flag){
				request.setAttribute("errorMsg", "下载失败,车牌号有误");
				request.setAttribute("driverVehicleNumber", "");
				//		this.writeJson(jsonObject);
				return SUCCESS;
			}


			StringBuffer buffer1 = new StringBuffer();
			buffer1.append(System.getProperty("webapp.root")).append("printTemplate").append(File.separator).append("wxjj_report_sc.jasper");
			File reportFile = new File(buffer1.toString());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("reportTitle", "This is a client report");  
			JRDataSource dataSource = qpTTPCaseService.getDataSourcePrint(qpTTPCase);
			String policyCode = qpTTPCase.getPoliceEmployeeId();
			String outPdfPath =  SealUtil.csWechatStampedSeal(reportFile.getPath(), parameters, dataSource, caseId,policyCode);
			InputStream is = new FileInputStream(new File(outPdfPath)); 
			byte[] bytes = this.input2byte(is);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/force-download; charset=UTF-8");  
			response.addHeader("Content-Disposition", "attachment; filename="+caseId+".pdf");  
			response.setContentLength(bytes.length);  
			ServletOutputStream ouputStream = response.getOutputStream();  
			ouputStream.write(bytes, 0, bytes.length);  
			ouputStream.flush();  
			ouputStream.close(); 

		} catch (Exception e) {
			logger.error("下载交通事故远程定责责任认定书出现异常", e);
			request.setAttribute("errorMsg", "下载失败,请稍后在试");
			return SUCCESS;
		}
		return NONE;
	}

	public final byte[] input2byte(InputStream inStream)  
			throws IOException {  
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
		byte[] buff = new byte[100];  
		int rc = 0;  
		while ((rc = inStream.read(buff, 0, 100)) > 0) {  
			swapStream.write(buff, 0, rc);  
		}  
		byte[] in2b = swapStream.toByteArray();  
		return in2b;  
	}

	/**
	 * 跳转任务列表
	 * @return
	 */
	public String prepareQueryFinishWxTask() {
		return SUCCESS;
	}

	/**
	 * 任务完成分页列表
	 * @return
	 */
	public String queryFinishWxTask() {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {
			Page resultPage = wxTaskFinishService.getPage(wxTaskFinish, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	public UserKeyService getUserKeyService() {
		return userKeyService;
	}

	public void setUserKeyService(UserKeyService userKeyService) {
		this.userKeyService = userKeyService;
	}

	public IQpTIcCompanyGarageDetailService getQpTIcCompanyGarageDetailService() {
		return qpTIcCompanyGarageDetailService;
	}

	public void setQpTIcCompanyGarageDetailService(IQpTIcCompanyGarageDetailService qpTIcCompanyGarageDetailService) {
		this.qpTIcCompanyGarageDetailService = qpTIcCompanyGarageDetailService;
	}

	public IQpTIcCompanyGarageService getQpTIcCompanyGarageService() {
		return qpTIcCompanyGarageService;
	}

	public void setQpTIcCompanyGarageService(IQpTIcCompanyGarageService qpTIcCompanyGarageService) {
		this.qpTIcCompanyGarageService = qpTIcCompanyGarageService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public WxTask getWxTask() {
		return wxTask;
	}

	public void setWxTask(WxTask wxTask) {
		this.wxTask = wxTask;
	}

	public WxCase getWxCase() {
		return wxCase;
	}

	public void setWxCase(WxCase wxCase) {
		this.wxCase = wxCase;
	}

	public WxAccident getWxAccident() {
		return wxAccident;
	}

	public void setWxAccident(WxAccident wxAccident) {
		this.wxAccident = wxAccident;
	}

	public QpWeixinCaseService getQpWeixinCaseService() {
		return qpWeixinCaseService;
	}

	public void setQpWeixinCaseService(QpWeixinCaseService qpWeixinCaseService) {
		this.qpWeixinCaseService = qpWeixinCaseService;
	}

	public IQpTICPictureService getQpTICPictureService() {
		return qpTICPictureService;
	}

	public void setQpTICPictureService(IQpTICPictureService qpTICPictureService) {
		this.qpTICPictureService = qpTICPictureService;
	}

	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}


	public IQpTTPCaseService getQpTTPCaseService() {
		return qpTTPCaseService;
	}

	public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
		this.qpTTPCaseService = qpTTPCaseService;
	}

	public IQpTICAccidentService getQpTICAccidentService() {
		return qpTICAccidentService;
	}

	public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
		this.qpTICAccidentService = qpTICAccidentService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public WxTaskService getWxTaskService() {
		return wxTaskService;
	}

	public void setWxTaskService(WxTaskService wxTaskService) {
		this.wxTaskService = wxTaskService;
	}

	public WxTaskFinishServiceSpringImpl getWxTaskFinishService() {
		return wxTaskFinishService;
	}

	public void setWxTaskFinishService(
			WxTaskFinishServiceSpringImpl wxTaskFinishService) {
		this.wxTaskFinishService = wxTaskFinishService;
	}

	public WxTaskFinish getWxTaskFinish() {
		return wxTaskFinish;
	}

	public void setWxTaskFinish(WxTaskFinish wxTaskFinish) {
		this.wxTaskFinish = wxTaskFinish;
	}

	public WxCaseService getWxCaseService() {
		return wxCaseService;
	}

	public void setWxCaseService(WxCaseService wxCaseService) {
		this.wxCaseService = wxCaseService;
	}


	public IReportTaskService getiReportTaskService() {
		return iReportTaskService;
	}

	public void setiReportTaskService(IReportTaskService iReportTaskService) {
		this.iReportTaskService = iReportTaskService;
	}

	public IReportTaskDataService getiReportTaskDataService() {
		return iReportTaskDataService;
	}

	public void setiReportTaskDataService(IReportTaskDataService iReportTaskDataService) {
		this.iReportTaskDataService = iReportTaskDataService;
	}

	public IReportCaseDataService getiReportCaseDataService() {
		return iReportCaseDataService;
	}

	public void setiReportCaseDataService(IReportCaseDataService iReportCaseDataService) {
		this.iReportCaseDataService = iReportCaseDataService;
	}
	
	public IQpTComHandlePoliceService getQpTComHandlePoliceService() {
		return qpTComHandlePoliceService;
	}

	public void setQpTComHandlePoliceService(IQpTComHandlePoliceService qpTComHandlePoliceService) {
		this.qpTComHandlePoliceService = qpTComHandlePoliceService;
	}

	public IUmTAccidentUserService getUmTAccidentUserService() {
		return umTAccidentUserService;
	}

	public void setUmTAccidentUserService(IUmTAccidentUserService umTAccidentUserService) {
		this.umTAccidentUserService = umTAccidentUserService;
	}
	
}

