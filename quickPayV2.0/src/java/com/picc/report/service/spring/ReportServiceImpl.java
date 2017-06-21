package com.picc.report.service.spring;

import ins.framework.common.ServiceFactory;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.enums.CompanyCode;
import com.picc.common.utils.Md5;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.spring.QpTCOMCityServiceSpringImpl;
import com.picc.qp.service.spring.QpTCOMDistrictServiceSpringImpl;
import com.picc.qp.service.spring.QpTCOMProvinceServiceSpringImpl;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;
import com.picc.qp.util.SealUtil;
import com.picc.report.paic.PaicReportUtil;
import com.picc.report.paic.service.facade.IPaicReportService;
import com.picc.report.picc.service.facade.IPiccReportService;
import com.picc.report.schema.model.ReportCaseData;
import com.picc.report.schema.model.ReportTask;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.schema.model.ReportTaskFail;
import com.picc.report.schema.model.ReportTaskFinish;
import com.picc.report.service.facade.IReportCaseDataService;
import com.picc.report.service.facade.IReportService;
import com.picc.report.service.facade.IReportTaskDataService;
import com.picc.report.service.facade.IReportTaskFailService;
import com.picc.report.service.facade.IReportTaskFinishService;
import com.picc.report.service.facade.IReportTaskService;
import com.picc.report.util.ReportConstants;

/**
 * 报案service
 * 2017年2月21日
 */
@Service("iReportService")
public class ReportServiceImpl implements IReportService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private IQpTTPCaseService qpTTPCaseService;

	@Autowired
	private IQpTICAccidentService qpTICAccidentService;

	@Autowired
	private IReportCaseDataService reportCaseDataService;

	@Autowired
	private IReportTaskDataService reportTaskDataService;

	@Autowired
	private IReportTaskFailService reportTaskFailService;

	@Autowired
	private IReportTaskFinishService reportTaskFinishService;

	@Autowired
	private IReportTaskService reportTaskService;
	
	@Autowired
	private IQpTICPictureService qpTICPictureService;

	@Autowired
	private IPaicReportService paicReportService;
	
	@Autowired
	private IPiccReportService piccReportService;
	

	@Override
	public void report() {
		logger.info("开始报案");
		try {
				ReportTask reportTask = new ReportTask();
				reportTask.setTaskType("1");
				//每次查询10条需要报案的任务
				List<ReportTask> reportTasks = reportTaskService.findReportTaskList(reportTask);
				if(reportTasks == null || reportTasks.size() <= 0){
					logger.info("无案件任务");
					return ;
				}else {
					logger.info("任务列表:"+JSONArray.toJSONString(reportTasks));
					logger.info("任务列表数量:"+reportTasks.size());
				}
				for (int i = 0; i < reportTasks.size(); i++) {
					ReportTask reportTask2 = reportTasks.get(i);
					Date now = new Date();
					String taskDataId = reportTask2.getTaskDataId();
					//获取任务关联数据
					ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);
					String caseId = reportTaskData.getCaseId();
					//设置默认错误信息和默认是否继续报案
					String errorMsg = "";
					String isTask = "0";//默认报案
					try {
						//获取案件信息 
						QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
						qpTTPCaseId.setCaseId(caseId);
						QpTTPCase qpTTPCase = new QpTTPCase();
						qpTTPCase.setId(qpTTPCaseId);
						qpTTPCase = qpTTPCaseService.findQpTTPCaseById(qpTTPCase);
						//报案的案件不等于已退回 或者已注销的案件
						if(qpTTPCase != null && !"3".equals(qpTTPCase.getTpHandleStatus()) && !"6".equals(qpTTPCase.getTpHandleStatus()) ){
							//获取当事人信息
							List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
							if(qpTICAccidents != null && qpTICAccidents.size() > 0){
								//把标的当事人放到位置1
								for (int j = 0; j < qpTICAccidents.size(); j++) {
									QpTICAccident qpTICAccident = qpTICAccidents.get(j);
									if(reportTaskData.getAccId().equals(qpTICAccident.getAcciId())){
										qpTICAccidents.remove(j);
										qpTICAccidents.add(0, qpTICAccident);
									}
								}
								JSONObject reportResult = new JSONObject();
								if("PAIC".equals(reportTaskData.getOrgCode())){//平安的案件才处理
									JSONObject tokenObject = PaicReportUtil.getPaicToken();
									if("0".equals(tokenObject.getString("code"))){
										reportResult = paicReportService.toPaicReport(qpTTPCase, qpTICAccidents, tokenObject.getString("data"));
									}else {
										logger.error("平安报案token获取token信息:" + tokenObject);
										logger.error("平安token获取异常"+JSON.toJSONString(reportTaskData));
									}
									
								}else if("PICC".equals(reportTaskData.getOrgCode())){
									reportResult = piccReportService.toPiccReport(qpTTPCase, qpTICAccidents);
								}else if(StringUtils.isNotEmpty(reportTaskData.getOrgCode())){
									reportResult = this.toGeneralReport(qpTTPCase, qpTICAccidents);
								}else {
									logger.error("无对接保险公司"+JSON.toJSONString(reportTaskData));
									reportResult.put("msg", "无对接保险公司" + reportTaskData.getOrgCode());
									isTask = "1";
								}
								if(reportResult != null && !reportResult.isEmpty() && "0".equals(reportResult.getString("code"))){
									//报案成功返回的案件编号(reportNo)和事故编号(damageId)
									String data = reportResult.getString("data");
									JSONObject dataJson = JSONObject.parseObject(data);
									if(dataJson != null && !dataJson.isEmpty()){
										String reportNo = dataJson.getString("reportNo");

										String damageId = "";
										if(dataJson.containsKey("damageId")){
											damageId = dataJson.getString("damageId");
										}
									
										//添加报案完成
										ReportTaskFinish reportTaskFinish = new ReportTaskFinish();
										reportTaskFinish.setId("BA"+taskDataId);
										reportTaskFinish.setTaskDataId(taskDataId);
										reportTaskFinish.setCreateTime(now);
										reportTaskFinish.setTaskType("1");
										reportTaskFinishService.add(reportTaskFinish);
										//删除任务表
										reportTaskService.deleteById(reportTask2.getId());
										//添加单证上传任务  1:报案  2单证上传
										ReportTask reportTaskUpload = new ReportTask();
										reportTaskUpload.setId("DZ"+taskDataId);
										reportTaskUpload.setTaskDataId(reportTaskData.getTaskDataId());;
										reportTaskUpload.setTaskType("2");
										reportTaskUpload.setCreateTime(now);
										reportTaskService.add(reportTaskUpload);
										//修改数据表   添加报案返回案件号用户单证上传
										reportTaskData.setReportNo(reportNo);
										reportTaskDataService.update(reportTaskData);
										//添加报案数据返回表
										ReportCaseData reportCaseData = new ReportCaseData();
										reportCaseData.setId(taskDataId);
										reportCaseData.setTaskDataId(taskDataId);
										reportCaseData.setReportCaseStatus("已报案");
										reportCaseData.setCaseId(caseId);
										reportCaseData.setAccId(reportTaskData.getAccId());
										reportCaseData.setDamageId(damageId);
										reportCaseData.setReportNo(reportNo);
										reportCaseData.setOrgCode(reportTaskData.getOrgCode());
										reportCaseData.setCreateTime(now);
										reportCaseDataService.add(reportCaseData);
										continue;
									}else {
										logger.error("报案失败,data无数据");
										errorMsg = "报案失败，data无数据:"+data;
									}
								}else {
									logger.error("报案失败");
									errorMsg = reportResult.getString("msg");
								}
							}else {
								errorMsg = "当事人信息不存在，记录失败任务";
								logger.error(caseId+"当事人信息不存在，记录失败任务");
							}
						}else{
							errorMsg = "案件信息不存在，记录失败任务";
							logger.error(caseId+"案件信息不存在，记录失败任务");
						}
						
					} catch (Exception e) {
						logger.error("报案失败"+taskDataId, e);
						errorMsg = "报案失败，程序出现异常" + e;
					}
					//添加到失败任务 并删除任务表
					ReportTaskFail taskFail = new ReportTaskFail();
					taskFail.setId(reportTask2.getId());
					taskFail.setTaskDataId(reportTaskData.getTaskDataId());
					taskFail.setErrorCount(1);
					taskFail.setIsTask(isTask);
					taskFail.setRemark(errorMsg);
					taskFail.setTaskType("1");
					Calendar nowTime = Calendar.getInstance();
					nowTime.add(Calendar.MINUTE, ReportConstants.ONE_ERROR_TIME);//当前时间+1分钟 1分钟后重试
					taskFail.setErrorTime(nowTime.getTime());
					reportTaskFailService.add(taskFail);
					reportTaskService.deleteById(reportTask2.getId());
				}
//			}else{
//				logger.error("报案token获取失败,任务无法开始");
//			}
		} catch (Exception e) {
			logger.error("报案任务执行异常异常1:" , e);
		}
	}
	
	
	
	@Override
	public void reportsUpload() {
		logger.info("开始单证上传");
			//获取上传单证任务（1.报案 2.上传单证）
			ReportTask findParam = new ReportTask();
//			findParam.setTaskType("2");
			findParam.setNotTaskType("1");
			List<ReportTask> reportTaskList = reportTaskService.findReportTaskList(findParam);
			logger.info("单证上传数量:"+reportTaskList.size());
			Date now = new Date();
			
			if(null != reportTaskList && reportTaskList.size()>0){
				for (int i = 0; i < reportTaskList.size(); i++) {
					
					//本次任务数据
					ReportTask reportTask = reportTaskList.get(i);
					//数据关联id
					String taskDataId = reportTask.getTaskDataId();
					//获取关联数据
					ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);
					
					//失败表
					ReportTaskFail fail = new ReportTaskFail();
					fail.setId(reportTask.getId());
					fail.setTaskDataId(taskDataId);
					fail.setErrorCount(1);
					fail.setErrorTime(now);
					fail.setTaskType(reportTask.getTaskType());
					JSONObject resultObject = new JSONObject();
					if("PAIC".equals(reportTaskData.getOrgCode())){//平安
						JSONObject tokenObject = PaicReportUtil.getPaicToken();
						if("0".equals(tokenObject.getString("code"))){
							String token = tokenObject.getString("data");
							resultObject = paicReportService.reportPaicUpload(taskDataId, token, reportTask.getTaskType());
						}else {
							logger.error("平安报案token获取token信息:" + tokenObject);
							logger.error("上传单证失败，平安token获取失败"+JSON.toJSONString(reportTaskData));
						}
					}else if ("PICC".equals(reportTaskData.getOrgCode())) {//人
						resultObject = piccReportService.reportPiccUpload(taskDataId, reportTask.getTaskType());
					}else {
						resultObject = this.toGeneralUpload(taskDataId, reportTask.getTaskType());
						logger.error("通用上传单证结果"+resultObject);
					}
					
					
					if(resultObject != null && !resultObject.isEmpty() && "0".equals(resultObject.getString("code"))){
						//成功表
						ReportTaskFinish finish = new ReportTaskFinish();
						finish.setId("DZ"+taskDataId);
						finish.setTaskDataId(taskDataId);
						finish.setCreateTime(now);
						finish.setTaskType("2");
						reportTaskFinishService.add(finish);
						//删除关联数据 TODO
//						reportTaskDataService.deleteById(reportTaskData.getId());
						ReportCaseData reportCaseData = reportCaseDataService.findReportCaseDataByTaskDataId(taskDataId);
						reportCaseData.setReportCaseStatus("报案完成");
						reportCaseDataService.update(reportCaseData);
					}else{
						//获取图片 或 当事人图片URL失败，不调上传接口（0.继续重试 1.终止）
						fail.setIsTask("0");
						fail.setRemark(resultObject.getString("code")+":"+resultObject.getString("msg"));
						Calendar nowTime = Calendar.getInstance();
						nowTime.add(Calendar.MINUTE, ReportConstants.ONE_ERROR_TIME);//5分钟后重试单证上传
						fail.setErrorTime(nowTime.getTime());
						//失败添加一条数据
						reportTaskFailService.add(fail);
					}
					//失败成功都会删除ReportTask中这条数据
					reportTaskService.deleteById(reportTask.getId());
				}
			}else {
				logger.info("无单证上传任务");
			}
	}
	
	
	
	@Override
	public void reportFailTask() {
		logger.info("开始处理失败案件PAIC");
		try {
				//查询所有需要重新执行的任务
				ReportTaskFail reportTaskFailQuery = new ReportTaskFail();
				reportTaskFailQuery.setIsTask("0");
				List<ReportTaskFail> reportTaskFails = reportTaskFailService.findReportTaskFailList(reportTaskFailQuery);
				if(reportTaskFails == null || reportTaskFails.size() <= 0){
					logger.info("无失败任务");
					return ;
				}else {
					logger.info("失败任务列表:"+JSONArray.toJSONString(reportTaskFails));
				}
				for (ReportTaskFail reportTaskFail : reportTaskFails) {
					if("0".equals(reportTaskFail.getIsTask())){
						Date now = new Date();
						Calendar nowTime = Calendar.getInstance();
						//获取关联数据
						String taskDataId = reportTaskFail.getTaskDataId();
						ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);
						String caseId = reportTaskData.getCaseId();
						String errorMsg = "";
						String isTask = "0";//默认报案
						if("1".equals(reportTaskFail.getTaskType()) && reportTaskFail.getErrorTime().before(now) ){//报案任务
							try {
								//获取案件信息 
								QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
								qpTTPCaseId.setCaseId(caseId);
								QpTTPCase qpTTPCase = new QpTTPCase();
								qpTTPCase.setId(qpTTPCaseId);
								qpTTPCase = qpTTPCaseService.findQpTTPCaseById(qpTTPCase);
								if(qpTTPCase != null){
									//获取当事人信息
									List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
									if(qpTICAccidents != null && qpTICAccidents.size() > 0){
										//标的当事人放在1
										for (int i = 0; i < qpTICAccidents.size(); i++) {
											QpTICAccident qpTICAccident = qpTICAccidents.get(i);
											if(reportTaskData.getAccId().equals(qpTICAccident.getAcciId())){
												qpTICAccidents.remove(i);
												qpTICAccidents.add(0, qpTICAccident);
											}
										}
										 
										JSONObject reportResult = new JSONObject();
										if("PAIC".equals(reportTaskData.getOrgCode())){
											JSONObject tokenObject = PaicReportUtil.getPaicToken();
											if(tokenObject != null && !tokenObject.isEmpty() && "0".equals(tokenObject.getString("code"))){
												String token = tokenObject.getString("data");
												reportResult = paicReportService.toPaicReport(qpTTPCase, qpTICAccidents, token);
											}else {
												logger.error("平安报案token获取token信息:" + tokenObject);
												logger.error("平安报案token获取失败"+JSON.toJSONString(reportTaskData));
												continue;
											}
											
										}else if("PICC".equals(reportTaskData.getOrgCode())){
											reportResult = piccReportService.toPiccReport(qpTTPCase, qpTICAccidents);
										}else {
											reportResult = this.toGeneralReport(qpTTPCase, qpTICAccidents);
											logger.error("通用报案接口结果："+reportResult);
//											logger.error("无保险公司,报案失败"+JSON.toJSONString(reportTaskData));
//											continue;
										}
										logger.info("------------------报案结果："+reportResult);
										if(reportResult != null && !reportResult.isEmpty() && "0".equals(reportResult.getString("code"))){
											//报案成功返回的案件编号(reportNo)和事故编号(damageId)
											String data = reportResult.getString("data");
											JSONObject dataJson = JSONObject.parseObject(data);
											if(dataJson != null && !dataJson.isEmpty()){
												String reportNo = dataJson.getString("reportNo");

												String damageId = "";
												if(dataJson.containsKey("damageId")){
													damageId = dataJson.getString("damageId");
												}
												
												//添加报案完成
												ReportTaskFinish reportTaskFinish = new ReportTaskFinish();
												reportTaskFinish.setId("BA"+taskDataId);
												reportTaskFinish.setTaskDataId(taskDataId);
												reportTaskFinish.setCreateTime(now);
												reportTaskFinish.setTaskType("1");
												reportTaskFinishService.add(reportTaskFinish);
												//删除失败任务表
												reportTaskFailService.deleteById(reportTaskFail.getId());
												//添加单证上传任务  1:报案  2单证上传
												ReportTask reportTaskUpload = new ReportTask();
												reportTaskUpload.setId("DZ"+taskDataId);
												reportTaskUpload.setTaskDataId(taskDataId);
												reportTaskUpload.setTaskType("2");
												reportTaskUpload.setCreateTime(now);
												reportTaskService.add(reportTaskUpload);
												//修改数据表 添加增加报案返回案件编号 用户单证上传
												reportTaskData.setReportNo(reportNo);
												reportTaskDataService.update(reportTaskData);
												//添加报案数据返回表
												ReportCaseData reportCaseData = new ReportCaseData();
												reportCaseData.setId(taskDataId);
												reportCaseData.setTaskDataId(taskDataId);
												reportCaseData.setReportCaseStatus("已报案");
												reportCaseData.setCaseId(caseId);
												reportCaseData.setAccId(reportTaskData.getAccId());
												reportCaseData.setDamageId(damageId);
												reportCaseData.setReportNo(reportNo);
												reportCaseData.setOrgCode(reportTaskData.getOrgCode());
												reportCaseData.setCreateTime(now);
												reportCaseDataService.add(reportCaseData);
												continue;
											}else{
												logger.error("报案失败,返回data为空");
												errorMsg = "报案失败,返回data为空("+reportResult.getString("msg")+")";
											}
											
										}else {
											logger.error("报案失败");
											errorMsg = reportResult.getString("msg");
										}
									}else {
										errorMsg = "当事人信息不存在，记录失败任务";
										logger.error(caseId+"当事人信息不存在，记录失败任务");
									}
								}else{
									errorMsg = "案件信息不存在，记录失败任务";
									logger.error(caseId+"案件信息不存在，记录失败任务");
								}
							} catch (Exception e) {
								errorMsg = "报案异常，记录失败任务";
								logger.error("失败任务循环报案失败,执行下一条", e);
							}
							//添加到失败任务   所有走完之后在添加  成功会跳出
							reportTaskFail.setErrorCount(reportTaskFail.getErrorCount()+1);
							if(reportTaskFail.getErrorCount() >= 3){
								isTask = "1";
							}
							if(reportTaskFail.getErrorCount() == 2){
								nowTime.add(Calendar.MINUTE, ReportConstants.TWO_ERROR_TIME);//30分钟后重试第2次单证上传
								reportTaskFail.setErrorTime(nowTime.getTime());
							}
							if(reportTaskFail.getErrorCount() == 3){
								nowTime.add(Calendar.MINUTE, ReportConstants.THREE_ERROR_TIME);//60分钟后重试第3次单证上传
								reportTaskFail.setErrorTime(nowTime.getTime());
								//添加报案数据返回表
								ReportCaseData reportCaseData = reportCaseDataService.findReportCaseDataByTaskDataId(taskDataId);
								if(reportCaseData != null){
									reportCaseData.setId(taskDataId);
									reportCaseData.setTaskDataId(taskDataId);
									reportCaseData.setReportCaseStatus("报案失败");
									reportCaseData.setCaseId(caseId);
									reportCaseData.setAccId(reportTaskData.getAccId());
									reportCaseData.setPolicyInfoList(errorMsg);
									reportCaseData.setOrgCode(reportTaskData.getOrgCode());
									reportCaseDataService.update(reportCaseData);
								}else{
									reportCaseData = new ReportCaseData();
									reportCaseData.setId(taskDataId);
									reportCaseData.setTaskDataId(taskDataId);
									reportCaseData.setReportCaseStatus("报案失败");
									reportCaseData.setCaseId(caseId);
									reportCaseData.setAccId(reportTaskData.getAccId());
									reportCaseData.setDamageId("");
									reportCaseData.setReportNo("");
									reportCaseData.setOrgCode(reportTaskData.getOrgCode());
									reportCaseData.setCreateTime(now);
									reportCaseDataService.add(reportCaseData);
								}
							}
							reportTaskFail.setIsTask(isTask);
							reportTaskFail.setRemark(reportTaskFail.getRemark() + "-" + errorMsg);
							reportTaskFailService.update(reportTaskFail);
							//报案结束
						}else if(!"1".equals(reportTaskFail.getTaskType())  && reportTaskFail.getErrorTime().before(now) ){//单证上传任务
							//开始单证上传
							JSONObject resultObject = new JSONObject();
							try {
								if("PAIC".equals(reportTaskData.getOrgCode())){
									//平安报案
									JSONObject tokenObject = PaicReportUtil.getPaicToken();
									if("0".equals(tokenObject.getString("code"))){
										String token = tokenObject.getString("data");
										resultObject = paicReportService.reportPaicUpload(taskDataId, token, reportTaskFail.getTaskType());
									}else {
										logger.error("平安上传单证token获取失败"+JSON.toJSONString(reportTaskData));
										continue;
									}
								}else if("PICC".equals(reportTaskData.getOrgCode())){
									//人保报案
									resultObject = piccReportService.reportPiccUpload(taskDataId, reportTaskFail.getTaskType());
								}else {
									resultObject = this.toGeneralUpload(taskDataId, reportTaskFail.getTaskType());
									logger.error("通用单证上传接口结果："+resultObject);
								}
								if(resultObject != null && !resultObject.isEmpty() && "0".equals(resultObject.getString("code"))){
									//成功添加一条成功数据成功表
									ReportTaskFinish finish = new ReportTaskFinish();
									finish.setId("DZ"+taskDataId);
									finish.setTaskDataId(taskDataId);
									finish.setCreateTime(now);
									finish.setTaskType("2");
									reportTaskFinishService.add(finish);
									//删除失败任务
									reportTaskFailService.deleteById(reportTaskFail.getId());
									//删除关联数据  TODO
//									reportTaskDataService.deleteById(reportTaskData.getId());
									continue;
								}
								//结束单证上传
							} catch (Exception e) {
								logger.error("失败任务循环单证上传失败,执行下一条", e);
							}
							
							//记录失败信息
							logger.error(now+":上传单证失败"+resultObject.toJSONString());
							//获取图片 或 当事人图片URL失败，不调上传接口（0.继续重试 1.终止）
							reportTaskFail.setErrorCount(reportTaskFail.getErrorCount()+1);
							if(reportTaskFail.getErrorCount() >= 3){
								isTask = "1";
							}
							if(reportTaskFail.getErrorCount() == 2){
								nowTime.add(Calendar.MINUTE, ReportConstants.TWO_ERROR_TIME);//5分钟后重试第2次单证上传
								reportTaskFail.setErrorTime(nowTime.getTime());
							}
							if(reportTaskFail.getErrorCount() == 3){
								nowTime.add(Calendar.MINUTE, ReportConstants.THREE_ERROR_TIME);//30分钟后重试第3次单证上传
								reportTaskFail.setErrorTime(nowTime.getTime());
							}
							reportTaskFail.setIsTask(isTask);
							reportTaskFail.setRemark(reportTaskFail.getRemark() + "-" + resultObject.getString("code")+":"+resultObject.getString("msg"));
							//失败修改失败任务
							reportTaskFailService.update(reportTaskFail);
						}
					}
				}
		} catch (Exception e) {
			logger.error("报案任务执行异常异常:" , e);
		}
		
	}
	
	
	
	/**
	 * @param caseId 
	 * @param qpTTPCaseService
	 * 		根据caseId生成Base64 责任认定书图片
	 * @return JSONObject (state,fmsg,data)
	 */
	@Override
	public JSONObject getCaseInfoPic(String caseId){
		
		JSONObject result = new JSONObject();
		try{
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			if (null == qpTTPCase ) {
				result.put("code", "0");
				result.put("msg", "案件不存在");
				result.put("data", "");
				return result;
			}
			Integer status = Integer.parseInt(qpTTPCase.getTpHandleStatus());
			// 1已定责,0未定责
			if (status > 1) {
				StringBuffer buffer1 = new StringBuffer();
				buffer1.append(System.getProperty("webapp.root"))
				.append("printTemplate").append(File.separator)
				.append("wxjj_report_sc.jasper");
				File reportFile = new File(buffer1.toString());
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("reportTitle", "This is a client report");
				JRDataSource dataSource = qpTTPCaseService.getDataSourcePrint(qpTTPCase);
				String policyCode = qpTTPCase.getPoliceEmployeeId();
				String outPdfPath =  SealUtil.csWechatStampedSeal(reportFile.getPath(), parameters, dataSource, caseId,policyCode);

				PDDocument doc = PDDocument.load(new File(outPdfPath));

				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				StringBuffer buffer2 = new StringBuffer();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImageWithDPI(i, 96);
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					ImageIO.write(image, "png", bao);
					byte[] pngBytes = bao.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String imgStr = encoder.encode(pngBytes);
					buffer2.append("data:image/png;base64,");
					buffer2.append(imgStr);
					if (i < pageCount - 1) {
						buffer2.append(";");
					}
				}
				result.put("code", "0");
				result.put("msg", "查询成功");
				result.put("data", buffer2.toString().replaceAll("\r\n", ""));
			} else {
				result.put("code", "-1");
				result.put("msg", "案件暂无定责");
			}

		} catch (Exception e) {
			logger.error("获取定责图片出错,请示后在试",e);
			result.put("code", "-1");
			result.put("msg", "查询失败,请稍后在试");
		}
		return result;
	}
    
	/**
     * @param caseId
     * @param qpTICAccidentService
     * @param qpTICPictureService
     * 		 根据caseId获取当事人图片URL
     * @return JSONObject (state,msg,data)
     */
	@Override
    public JSONObject getAccidentPicUrl(String caseId ,String taskType){
		JSONObject result = new JSONObject();
		try {
			List<QpTICAccident> accidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
			System.out.println("accidents:"+accidents.size());
			List<String> urls = new ArrayList<String>();
			if(null != accidents && accidents.size()>0){
				for (int i = 0; i < accidents.size(); i++) {
					if("3".equals(taskType)){//查勘完成后的任务
						if(StringUtils.isNotEmpty(accidents.get(i).getSurveyGroupId())){
							List<QpTICPicture> qpTICPictures = qpTICPictureService.findQpTICPictureByGroupId(accidents.get(i).getSurveyGroupId());
							System.out.println("groupId"+(i+1) + ":"+accidents.get(i).getGroupId());
							for (int j = 0; j < qpTICPictures.size(); j++) {
								String fileUrl = qpTICPictures.get(j).getFileName();
								urls.add(fileUrl);
							}
						}
					}else{
						if(StringUtils.isNotEmpty(accidents.get(i).getGroupId())){
							List<QpTICPicture> qpTICPictures = qpTICPictureService.findQpTICPictureByGroupId(accidents.get(i).getGroupId());
							System.out.println("groupId"+(i+1) + ":"+accidents.get(i).getGroupId());
							for (int j = 0; j < qpTICPictures.size(); j++) {
								String fileUrl = qpTICPictures.get(j).getFileName();
								urls.add(fileUrl);
							}
						}
					}
				}
			}
//			if(null != urls && urls.size()>0){
				result.put("code", "0");
				result.put("msg", "获取图片Url成功");
				result.put("data", urls);
//			}else{
//				logger.error("查找当事人图片为空");
//				result.put("code", "-1");
//				result.put("msg", "获取当事人图片失败,请稍后在试");
//			}
		} catch (Exception e) {
			logger.error("获取图片失败,请示后在试", e);
			result.put("code", "-1");
			result.put("msg", "获取图片失败,请稍后在试");
		}
		return result;
	}
	
	
	
	/**
	 * 通用报案
	 * @param qpTTPCase
	 * @param qpTICAccident
	 * @param token
	 * @return
	 */
	@Override
	public JSONObject toGeneralReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccidents){
		JSONObject jsonObject = new JSONObject();
		try {
			
			QpTICAccident myQpTICAccident = qpTICAccidents.get(0);
			String orgCode = CompanyCode.Company.getCompanyCode(myQpTICAccident.getCoId());
			String reportUrl = Constants.getREPORT_INTERFACE().get(orgCode+"_report").toString();
//	        Map<String, Object> param = new HashMap<String, Object>();
////	        param.put("token", token);
//	        param.put("orgCode", orgCode);//保险公司
//	        param.put("carMark", myQpTICAccident.getDriverVehicleNumber());//车牌号码
////	        param.put("policyNo", "10516003900010595543");//保单号 N
//	        param.put("vinNo", (myQpTICAccident.getChassisNumber().length()==15)?myQpTICAccident.getChassisNumber().toUpperCase():"");//车架号 N
//	        if(myQpTICAccident.getChassisNumber().length()!=15){
//	        	logger.info("平安报案车架号不符合，默认为空:"+myQpTICAccident.getChassisNumber());
//	        }
//	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        param.put("accidentDate", simpleDateFormat.format(qpTTPCase.getCaseTime()));//出险时间
//	        // 省份
//			String province = "";
//			if (StringUtils.isNotEmpty(qpTTPCase.getCaseProvince())) {
//				QpTCOMProvinceId queryProcinceID = new QpTCOMProvinceId();
//				queryProcinceID.setProvId(qpTTPCase.getCaseProvince());
//				QpTCOMProvinceServiceSpringImpl qpTCOMProvinceService = (QpTCOMProvinceServiceSpringImpl)ServiceFactory.getService("qpTCOMProvinceService");
//				QpTCOMProvince currentProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(queryProcinceID);
//				if (currentProvince != null) {
//					province = currentProvince.getProvName();
//				}
//			}
//			// 城市
//			String city = "";
//			if (StringUtils.isNotEmpty(qpTTPCase.getCaseCity())) {
//				QpTCOMCityId queryCityID = new QpTCOMCityId();
//				queryCityID.setCityId(qpTTPCase.getCaseCity());
//				QpTCOMCityServiceSpringImpl qpTCOMCityService = (QpTCOMCityServiceSpringImpl)ServiceFactory.getService("qpTCOMCityService");
//				QpTCOMCity currentCity = qpTCOMCityService.findQpTCOMCityByPK(queryCityID);
//				if (currentCity != null) {
//					city = currentCity.getCityName();
//				}
//			}
//			// 地区
//			String area = "";
//			if (StringUtils.isNotEmpty(qpTTPCase.getCaseDistrict())) {
//				QpTCOMDistrictId queryAreaID = new QpTCOMDistrictId();
//				queryAreaID.setDistrictId(qpTTPCase.getCaseDistrict());
//				QpTCOMDistrictServiceSpringImpl qpTCOMDistrictService = (QpTCOMDistrictServiceSpringImpl)ServiceFactory.getService("qpTCOMDistrictService");
//				QpTCOMDistrict currentArea = qpTCOMDistrictService.findQpTCOMDistrictByPK(queryAreaID);
//				if (currentArea != null) {
//					area = currentArea.getDistrictName();
//				}
//			}
//	        param.put("accidentPlaceProvince", StringUtils.isEmpty(province+city)?"长沙市":province+city);//出险地点城市
//	        param.put("accidentPlaceDistrict", StringUtils.isEmpty(area)?"区":area);//出险地点行政区
//	        param.put("accidentPlaceDetail", qpTTPCase.getCaseStreet());//出险地点地址
//	        param.put("accidentType", "2");//事故类型  1：单方   2：多方
//	        param.put("isNorDriving", "1");//车辆能否正常行驶  0 否  1 是
//	        param.put("isReportOnPort", "1");//是否现场报案    非现场0，现场为1
//	        param.put("accidentCause", "99");//出险原因
////	       	 编码	事故情形				出险原因1级			出险原因2级			出险原因3级
////	        01	停车					停放受损			被车撞			三者未逃逸
////	        02	倒车					行驶受损			倒车				与机动车撞
////	        03	逆行					行驶受损			左转弯			与机动车撞
////	        04	溜车					行驶受损			直行				与机动车撞
////	        05	开关车门				停放受损			被车撞			三者未逃逸
////	        06	违反交通信号灯			行驶受损			直行				与机动车撞
////	        07	变更车道与其他车辆刮擦		行驶受损			直行				与机动车撞
////	        08	未保持安全车距与前车追尾	行驶受损			直行				与机动车撞
////	        99	其他					其他				其他				其他
////	        param.put("accidentCauseMark", qpTTPCase.getCaseNotes());//出险原因备注 N
//	        param.put("reportName", myQpTICAccident.getDriverName());//报案人姓名
//	        param.put("reportTelNo", myQpTICAccident.getDriverMobile());//报案人联系方式
//	        param.put("reportMode", Constants.getPAIC_USERNAME());//报案渠道
//	        param.put("driverName", myQpTICAccident.getDriverName());//驾驶员姓名
//	        param.put("channelTypes", "01,02");//损失类型
////	                        编码	损失类型描述
////	        01	本车（标的）车损
////	        02	三者车损
////	        03	本车（标的）物损
////	        04	三者车内物损
////	        05	三者车外物损
////	        06	三者车内人
////	        07	三者车外人
////	        08	司机
////	        09	乘客
////	        param.put("channelTypesDesc", "本车,三者车,有物损,有人伤");//损失类型中文描述 N
//	        param.put("accidentPlaceLongtitude", qpTTPCase.getLongitude());//GPS的经度坐标
//	        param.put("accidentPlaceLatitude", qpTTPCase.getLatitude());//GPS的纬度坐标
//	        param.put("dataSource", "01");//数据来源  此数据提供方01-微信,02-APP,03-交警
////	        param.put("clientId", "fe678402-4b44-48ce-a1da-b320f6011e7d");//客户号 N
////	        param.put("dutyCoefficient", "100");//dutyCoefficient N  责任系数
//	        param.put("dutyCoefficientDesc", myQpTICAccident.getDriverLiabilityDesc());//dutyCoefficientDesc  责任描述
////	        List<Map<String,String>>thirdParamList = new ArrayList<Map<String,String>>();//三者信息列表
////	        if(qpTICAccident != null && qpTICAccident.size() > 1){
////	        	for (int i = 1; i < qpTICAccident.size(); i++) {
////	        		Map<String,String> thirdParam = new HashMap<String,String>();
////	    	        thirdParam.put("thirdCarDriverName", qpTICAccident.get(i).getDriverName());//三者驾驶员姓名
////	    	        thirdParam.put("thirdCarDriverTel", qpTICAccident.get(i).getDriverMobile());//三者驾驶员联系方式
////	    	        thirdParam.put("thirdCarMark", qpTICAccident.get(i).getDriverVehicleNumber());//三者车牌号   不带杠
////	    	        thirdParamList.add(thirdParam);
////				}
////	        }
////	        param.put("thirdCarInfoList", thirdParamList);
//	        HttpResponse response = PaicReportUtil.sendPiccHttpRequest(reportUrl, com.alibaba.fastjson.JSONObject.toJSONString(param));
//	        JSONObject resultDate = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
	        
	        
	        // 省份
			String province = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseProvince())) {
				QpTCOMProvinceId queryProcinceID = new QpTCOMProvinceId();
				queryProcinceID.setProvId(qpTTPCase.getCaseProvince());
				QpTCOMProvinceServiceSpringImpl qpTCOMProvinceService = (QpTCOMProvinceServiceSpringImpl)ServiceFactory.getService("qpTCOMProvinceService");
				QpTCOMProvince currentProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(queryProcinceID);
				if (currentProvince != null) {
					province = currentProvince.getProvName();
				}
			}
			// 城市
			String city = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseCity())) {
				QpTCOMCityId queryCityID = new QpTCOMCityId();
				queryCityID.setCityId(qpTTPCase.getCaseCity());
				QpTCOMCityServiceSpringImpl qpTCOMCityService = (QpTCOMCityServiceSpringImpl)ServiceFactory.getService("qpTCOMCityService");
				QpTCOMCity currentCity = qpTCOMCityService.findQpTCOMCityByPK(queryCityID);
				if (currentCity != null) {
					city = currentCity.getCityName();
				}
			}
			// 地区
			String area = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseDistrict())) {
				QpTCOMDistrictId queryAreaID = new QpTCOMDistrictId();
				queryAreaID.setDistrictId(qpTTPCase.getCaseDistrict());
				QpTCOMDistrictServiceSpringImpl qpTCOMDistrictService = (QpTCOMDistrictServiceSpringImpl)ServiceFactory.getService("qpTCOMDistrictService");
				QpTCOMDistrict currentArea = qpTCOMDistrictService.findQpTCOMDistrictByPK(queryAreaID);
				if (currentArea != null) {
					area = currentArea.getDistrictName();
				}
			}
	        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> param = new HashMap<String, Object>();
	        param.put("caseId", qpTTPCase.getCaseId());
	        param.put("accId", myQpTICAccident.getAcciId());
	        param.put("caseTime", sf.format(qpTTPCase.getCaseTime()));
	        param.put("reportTime", sf.format(new Date()));
	        param.put("caseAddress", province+city+area+qpTTPCase.getCaseStreet());
	        param.put("caseWeatherCode", qpTTPCase.getCaseWeather());
	        param.put("reportUserName", myQpTICAccident.getDriverName());
	        param.put("reportUserMobile", myQpTICAccident.getDriverMobile());
	        param.put("reportUserCardId", myQpTICAccident.getDriverIDNumber());
	        param.put("reportUserInsuredName", StringUtils.isEmpty(myQpTICAccident.getInsured())?myQpTICAccident.getDriverName():myQpTICAccident.getInsured());
	        
	        param.put("vehicleNo", myQpTICAccident.getDriverVehicleNumber().toUpperCase());
	        param.put("chassisNumber", myQpTICAccident.getChassisNumber());
	        param.put("driverLiabilityCode", myQpTICAccident.getDriverLiability());
	        param.put("comId", orgCode);
	        
	        param.put("longitude", qpTTPCase.getLongitude());
	        param.put("latitude", qpTTPCase.getLatitude());
	        param.put("vehicleTypeCode", myQpTICAccident.getDriverVehicleType());
	        param.put("permissionDriveCode", myQpTICAccident.getPermissionDrive());
	        param.put("labelType", myQpTICAccident.getLabelType());
	        param.put("infoSource", "湖南快处快赔");
	        
	        List<Map<String,String>>thirdParamList = new ArrayList<Map<String,String>>();//三者信息列表
	        if(qpTICAccidents != null && qpTICAccidents.size() > 1){
	        	for (int i = 1; i < qpTICAccidents.size(); i++) {
	        		Map<String,String> thirdParam = new HashMap<String,String>();
	    	        thirdParam.put("thirdUserName", qpTICAccidents.get(i).getDriverName());//三者驾驶员姓名
	    	        thirdParam.put("thirdUserMobile", qpTICAccidents.get(i).getDriverMobile());//三者驾驶员联系方式
	    	        thirdParam.put("thirdUserVehicleNo", qpTICAccidents.get(i).getDriverVehicleNumber().toUpperCase());//三者车牌号   不带杠
	    	        thirdParam.put("thirdUserCardId", qpTICAccidents.get(i).getDriverIDNumber());//三者车牌号   不带杠
	    	        thirdParam.put("thirdUserInsuredName", qpTICAccidents.get(i).getInsured());//三者被保险人姓名
	    	        thirdParam.put("thirdUserChassisNumber", qpTICAccidents.get(i).getChassisNumber().toUpperCase());//三者车架号
	    	        thirdParam.put("thirdUserDriverLiabilityCode", qpTICAccidents.get(i).getDriverLiability());//三者责任人代码
	    	        thirdParam.put("thirdUserComId", CompanyCode.Company.getCompanyCode(qpTICAccidents.get(i).getCoId()));//三者责任人代码
	    	        thirdParamList.add(thirdParam);
				}
	        }
	        param.put("thirdCarInfoList", thirdParamList);
	        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
	        //TODO各保险公司特定参数
	        if("AICS".equals(orgCode)){
	        	String dataTime = sf2.format(new Date());
	        	param.put("token", Md5.encodeByMd5("AICJBJL"+"aic910yc"+dataTime));
	        	param.put("dataTime", dataTime);
	        }
	        
	        logger.info("通用报案接口参数："+JSON.toJSONString(param));
	        JSONObject resultDate = new JSONObject();
	        Map<String, Object> result= HttpClientUtils.HttpClientJsonPost(reportUrl, JSONObject.toJSONString(param), 60000, 50000);
	        logger.info("通用报案接口结果："+result);
	        if(result != null && !result.isEmpty() && "200".equals(result.get("code"))){
	        	String jsonStr = String.valueOf(result.get("info")).replaceAll("\\\\", "");
	        	resultDate = JSONObject.parseObject(jsonStr);
	        }else {
	        	logger.info("报案返回非200，记录失败:"+result );
	        	jsonObject.put("code", "999");
				jsonObject.put("msg", "报案返回非200,请稍后在试");
			}
//	        HttpResponse response = PaicReportUtil.sendPiccHttpRequest(reportUrl, JSONObject.toJSONString(param));
//	        String responseStr = EntityUtils.toString(response.getEntity());
//	        logger.info("报案结果未转换json："+responseStr);
//	        resultDate = JSONObject.parseObject(responseStr);
	        jsonObject = this.isCheckResule(resultDate);
		} catch (Exception e) {
			logger.error("报案异常:", e);
			jsonObject.put("code", "999");
			jsonObject.put("msg", "报案异常,请稍后在试");
		}
		return jsonObject;
	}
	
	public static void main(String[] args) throws Exception {
		System.err.println(Md5.encodeByMd5("AICJBJLaic910yc20170419"));
	}
	
	@Override
	public JSONObject toGeneralUpload(String taskDataId, String taskType){
		JSONObject resultObject = new JSONObject();
		
		try {
			
			//获取任务所需数据
			ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);
			QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
			qpTICAccidentId.setAcciId(reportTaskData.getAccId());
			QpTICAccident qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);
			//获取保险公司
			String comId =  CompanyCode.Company.getCompanyCode(qpTICAccident.getCoId());
			//获取单证上传链接
			String uploadFileBatchURL = Constants.getREPORT_INTERFACE().get(comId+"_upload");
			//案件id
			String caseId = reportTaskData.getCaseId();
			//认定书集合
			List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
			
			//上传单证参数
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("reportNo", reportTaskData.getReportNo());
			param.put("comId", comId);
			
			if("2".equals(taskType) || "4".equals(taskType)){
				boolean isModify = "4".equals(taskType); 
				//获取责任认定书Base64
				JSONObject caseInfoPic = this.getCaseInfoPic(caseId);
				if("0".equals(caseInfoPic.getString("code"))){
					Map<String,Object> reportMap = new HashMap<String,Object>();
					String reportData = caseInfoPic.getString("data");
					reportMap.put("fileName", "reportPic"+caseId);
					if(isModify){
						reportMap.put("fileName", "修改后的责任认定书reportPic"+caseId);
					}
					reportMap.put("fileData", reportData.replaceAll("\r\n", ""));
					reportMap.put("fileMD5", PaicReportUtil.MD5Sign(reportData));
					reportMap.put("fileUrl", "");  
					fileList.add(reportMap);
				}else{
					resultObject.put("code", "999");
					resultObject.put("msg", "获取责任认定书图片失败");
					return resultObject;
				}
			}
			
			if(!"4".equals(taskType)){
				JSONObject picUrlObject= this.getAccidentPicUrl(caseId, taskType);
				if("0".equals(picUrlObject.get("code"))){
					List<String> urls = (List<String>) picUrlObject.get("data");
					logger.info("图片:"+urls);
					for (int j = 0; j < urls.size(); j++) {
						String accidentPicUrl = Constants.getIMAGEHTTPQUERY()+urls.get(j);
						System.err.println(accidentPicUrl);
						Map<String,Object> fileMap = new HashMap<String,Object>();
						String filedata ="data:image/png;base64,";
						filedata += PaicReportUtil.GetUrlImageToBase64(accidentPicUrl);
						fileMap.put("fileName", caseId+j);
						fileMap.put("fileData", filedata.replaceAll("\r\n", ""));
						fileMap.put("fileMD5", PaicReportUtil.MD5Sign(filedata));
						fileMap.put("fileUrl", accidentPicUrl);  
						fileList.add(fileMap);
					}
				}else{
					logger.info("获取当事人url图片失败:"+picUrlObject);
					resultObject.put("code", "999");
					resultObject.put("msg", "获取当事人图片URL失败");
					return resultObject;
				}
			}
			
			
			 SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
	        //TODO各保险公司特定参数
	        if("AICS".equals(reportTaskData.getOrgCode())){
	        	String dataTime = sf2.format(new Date());
	        	param.put("token", Md5.encodeByMd5("AICJBJL"+"aic910yc"+dataTime));
	        	param.put("dataTime", dataTime);
	        }
			
			param.put("fileList", fileList);
			logger.info("通用单证上传链接:"+uploadFileBatchURL);
			logger.info("通用单证上传参数:"+param);
			if(null != fileList && fileList.size()>0){
				JSONObject resultDate = new JSONObject();
				Map<String, Object> result= HttpClientUtils.HttpClientJsonPost(uploadFileBatchURL, JSONObject.toJSONString(param), 50000, 30000);
		        logger.info("通用单证上传接口结果："+result);
		        if(result != null && !result.isEmpty() && "200".equals(result.get("code"))){
		        	resultDate = JSONObject.parseObject(String.valueOf(result.get("info")));
		        	resultObject = this.isCheckResule(resultDate);
		        }else {
		        	logger.info("通用单证上传接口返回非200，记录失败:"+result );
		        	resultObject.put("code", "999");
		        	resultObject.put("msg", "通用单证上传接口非200,请稍后在试");
				}
		        
//				HttpResponse response = PaicReportUtil.sendPiccHttpRequest(uploadFileBatchURL, JSONObject.toJSONString(param));
//				logger.info("单证上传结果:"+response);
//				if(response != null){
//					String rsult = EntityUtils.toString(response.getEntity());
//					logger.info("上传结果String:"+rsult);
//					resultDate = JSONObject.parseObject(rsult);
//					resultObject = this.isCheckResule(resultDate);
//				}else{
//					resultObject.put("code","9999");
//					resultObject.put("msg", "单证上传返回null无如何结果");
//				}
				
//				resultObject.put("code",jsonObject.getString("code"));
//				resultObject.put("msg", jsonObject.getString("msg"));
			}else{
				resultObject.put("code","0");
				resultObject.put("msg", "无图片，不上传单证，当做任务完成处理。");
			}
		} catch (Exception e) {
			logger.error("上传单证异常:", e);
			resultObject.put("code", "999");
			resultObject.put("msg", "上传单证异常,请稍后在试");
		}
		return resultObject;
	}
	
	
	
	public JSONObject isCheckResule(JSONObject result){
		JSONObject jsonObject = new JSONObject();
//		if(resultDate.isEmpty()){
//			jsonObject.put("code", -1);
//			jsonObject.put("msg", "操作失败,无返回结果");
//			return jsonObject;
//		}
//		JSONObject result = resultDate.getJSONObject("result");
		if(result == null || result.isEmpty()){
			jsonObject.put("code", -1);
			jsonObject.put("msg", "操作失败,调用结果不存在");
			return jsonObject;
		}
		if("0".equals(result.getString("code"))){
			//成功
			jsonObject.put("code", 0);
			jsonObject.put("msg", "OK");
			jsonObject.put("data", result.getString("data"));
		}else {
			jsonObject.put("code", result.getString("code"));
			jsonObject.put("msg", result.getString("message"));
		}
		return jsonObject;
	}
}
