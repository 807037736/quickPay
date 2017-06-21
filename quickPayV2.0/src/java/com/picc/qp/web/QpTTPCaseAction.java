/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.enums.CompanyCode;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.utils.UuidUtil;
import com.picc.qp.enums.CaseHandleStatusEnum;
import com.picc.qp.enums.WebEnum;
import com.picc.qp.schema.model.QpTCOMHandlePolice;
import com.picc.qp.schema.model.QpTFLOW;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.QpTTPFastCentercompare;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.schema.vo.QpAcciLawDataVo;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.schema.vo.QpTICAcciPrintVo;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.qp.service.facade.IQpTAsyncTaskService;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTComHandlePoliceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTFLOWService;
import com.picc.qp.service.facade.IQpTICAccidentProjectService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.util.Constants;
import com.picc.qp.util.SealUtil;
import com.picc.report.schema.model.ReportCaseData;
import com.picc.report.schema.model.ReportTask;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.service.facade.IReportCaseDataService;
import com.picc.report.service.facade.IReportTaskDataService;
import com.picc.report.service.facade.IReportTaskService;
import com.picc.um.schema.model.UmTAccidentUser;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTAccidentUserService;
import com.sinosoft.sysframework.common.datatype.DateTime;
import com.sinosoft.sysframework.common.util.ParamUtils;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@SuppressWarnings("serial")
public class QpTTPCaseAction extends Struts2Action{

	private IQpTTPCaseService qpTTPCaseService;	

	private IQpTCommonService qpTCommonService;

	private IQpTTPFastCenterService qpTTPFastCenterService;

	private IQpTICAccidentService qpTICAccidentService;

	private IQpTICCompanyService qpTICCompanyService;

	private IQpTTPLawService qpTTPLawService;

	private IQpTFLOWService qpTFLOWService;

	private IQpTCOMProvinceService qpTCOMProvinceService;

	private IQpTCOMCityService qpTCOMCityService;

	private IQpTCOMDistrictService qpTCOMDistrictService;

	private IQpTICAccidentProjectService qpTICAccidentProjectService;
	
	private IReportTaskService iReportTaskService;
	
	private IReportTaskDataService iReportTaskDataService;
	
	private IReportCaseDataService iReportCaseDataService;
	
	private WxCaseService wxCaseService;

	private InputStream pdfStream; 
	
	private IQpTComHandlePoliceService qpTComHandlePoliceService;	
	
	private IUmTAccidentUserService umTAccidentUserService;
	
	/** 主键对象 */
	private String caseId;

	/*
	 * 操作类型
	 * 1、查勘员审核
	 * 2、估损录入
	 * 3、协作员录入、修改案件
	 */
	private String businessType;
	private QpTTPCase qpTTPCase;

	private QpTTPCaseId id;

	private List<QpTICAccident> qpTICAccidentList ;

	private List<QpAcciLawDataVo> qpAcciLawDataVoList;

	private List<QpTTPLaw> qpTTPLawList;

	private QpTICAcciPrintVo qpTICAcciPrintVo;

	private QpTTPCaseStatVO qpTTPCaseStatVO;
	/** 操作类型 **/
	private String operateType;
	

	/****************************Function Start*******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		HttpServletRequest httpServletRequest = this.getRequest();
		HttpSession session = httpServletRequest.getSession();
		//如果存在综合查询的session信息就删除掉
		if(session.getAttribute("qpqpttpcasefindprepareQueryqpTTPCase") != null){
			session.removeAttribute("qpqpttpcasefindprepareQueryqpTTPCase");
		}
		// 业务类型： 1-估损
		String businessType = httpServletRequest.getParameter("businessType");
		httpServletRequest.setAttribute("businessType", businessType);
		//根据业务类型，添加默认查询条件

		// 身份证类型
		List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData("IdentifyType");
		httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);
		// 天气情况
		List<QpSelectDataVo> weatherList =  qpTCommonService.getSelectData("WeatherType");
		httpServletRequest.setAttribute("weatherList", weatherList);
		// 快陪中心
		String userSort = getUserFromSession().getUserSort();
		if(ToolsUtils.notEmpty(userSort) && ("02".equals(userSort) || "04".equals(userSort))){
			UmTUser umTUser = new UmTUser();
			String userCode = getUserFromSession().getUserCode();
			umTUser.setUsercode(userCode);
			List<QpTTPFastCentercompare> fastCentercompareList = qpTTPFastCenterService.findQpTTPFastCenterCompare(umTUser);
			httpServletRequest.setAttribute("fastCenterList", fastCentercompareList);

		}else{
			QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
			//			qpTTPFastCenter.setValidStatus("1");
			List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
			httpServletRequest.setAttribute("fastCenterList", fastCenterList);
		}
		httpServletRequest.setAttribute("userSort", userSort);
		// 保险公司
		List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
		httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
		//已上传图片数量

		// 设置默认值
		SessionUser sessionUser = getUserFromSession();
		qpTTPCase = new QpTTPCase();
		qpTTPCase.setCenterId(sessionUser.getCenterId());           // 默认受理点
		qpTTPCase.setTpHandleTimeStart(new DateTime(new DateTime(), DateTime.YEAR_TO_MILLISECOND));
		qpTTPCase.setDriverVehicleNumber("湘");
		//保存的查询条件如果还存在就删除掉
		Object object = session.getAttribute("qpqpttpcaseprepareQueryqpTTPCase");
		if(object != null){
			session.removeAttribute("qpqpttpcaseprepareQueryqpTTPCases");
		}
		// qpTTPCase.setTpHandleTimeEnd(ToolsUtils.getFormatDate(new Date(), "yyyy-MM-dd"));
		return SUCCESS;
	}

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 注： 用于条件查询 中  查看案件信息某条 -- 查看 进入详情界面  -- 点击返回调用方法   
	 * 区别： 获取上一次查询的参数 
	 * 
	 * @return
	 */
	public String prepareQuery2() throws Exception {
		HttpServletRequest httpServletRequest = this.getRequest();
		HttpSession session = httpServletRequest.getSession();

		// 业务类型： 1-估损
		httpServletRequest.setAttribute("businessType", httpServletRequest.getParameter("businessType"));
		//根据业务类型，添加默认查询条件

		// 身份证类型
		List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData("IdentifyType");
		httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);
		// 天气情况
		List<QpSelectDataVo> weatherList =  qpTCommonService.getSelectData("WeatherType");
		httpServletRequest.setAttribute("weatherList", weatherList);
		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
		httpServletRequest.setAttribute("fastCenterList", fastCenterList);
		// 保险公司
		List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
		httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
		//已上传图片数量

		// 设置默认值
		SessionUser sessionUser = getUserFromSession();
		qpTTPCase = new QpTTPCase();
		Object object = session.getAttribute("qpqpttpcaseprepareQueryqpTTPCase");
		if(object != null){
			httpServletRequest.setAttribute("isReturn", "1");
			qpTTPCase = (QpTTPCase)object;
			//格式化时间
			if(qpTTPCase != null){
				if(qpTTPCase.getTpHandleTimeStart() != null && !"".equals(qpTTPCase.getTpHandleTimeStart())){
					qpTTPCase.setTpHandleTimeStart(new DateTime(qpTTPCase.getTpHandleTimeStart(), DateTime.YEAR_TO_MILLISECOND));
				}
				if(qpTTPCase.getTpHandleTimeEnd() != null && !"".equals(qpTTPCase.getTpHandleTimeEnd())){
					qpTTPCase.setTpHandleTimeEnd(new DateTime(qpTTPCase.getTpHandleTimeEnd(), DateTime.YEAR_TO_MILLISECOND));
				}
				if(qpTTPCase.getCaseTimeStart() != null && !"".equals(qpTTPCase.getCaseTimeStart())){
					qpTTPCase.setCaseTimeStart(new DateTime(qpTTPCase.getCaseTimeStart(), DateTime.YEAR_TO_MILLISECOND));
				}
				if(qpTTPCase.getCaseTimeEnd() != null && !"".equals(qpTTPCase.getCaseTimeEnd())){
					qpTTPCase.setCaseTimeEnd(new DateTime(qpTTPCase.getCaseTimeEnd(), DateTime.YEAR_TO_MILLISECOND));
				}
			}
		}else {
			qpTTPCase.setCenterId(sessionUser.getCenterId());           // 默认受理点
			qpTTPCase.setTpHandleTimeStart(new DateTime(new DateTime(), DateTime.YEAR_TO_MILLISECOND));
		}
		if(object != null){
			session.removeAttribute("qpqpttpcaseprepareQueryqpTTPCases");
		}
		// qpTTPCase.setTpHandleTimeEnd(ToolsUtils.getFormatDate(new Date(), "yyyy-MM-dd"));
		return SUCCESS;
	}

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareViewQuery() throws Exception {     
		HttpServletRequest httpServletRequest = this.getRequest();

		httpServletRequest.setAttribute("driverIDNumber", httpServletRequest.getParameter("driverIDNumber"));
		httpServletRequest.setAttribute("driverVehicleNumber", httpServletRequest.getParameter("driverVehicleNumber"));

		return SUCCESS;
	}

	/**
	 * QpTTPCase查询，根据qpTTPCase带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {

		HttpServletRequest httpServletRequest = this.getRequest();
		HttpSession session = httpServletRequest.getSession();

		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		//业务类型： 1估损   2交警  3协助员
		qpTTPCase.setBusinessType(businessType);
		try {		
			qpTTPCase.setCaseCity(getUserFromSession().getCity());
			//每次搜索后保存搜索条件
			session.setAttribute("qpqpttpcaseprepareQueryqpTTPCase", qpTTPCase);
			Page resultPage = qpTTPCaseService.findQpTTPCasePageBySql(qpTTPCase, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTTPCase信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
		return SUCCESS;
	}

	/**
	 * 更新QpTTPCase信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTTPCaseService.updateQpTTPCase(qpTTPCase);
		return SUCCESS;
	}


	/**
	 * 进入新增案件页面
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		HttpServletRequest httpServletRequest = this.getRequest();
		// 案件ID
		String caseId = httpServletRequest.getParameter("qpTTPCaseCaseId"); 
		String CaseFind = httpServletRequest.getParameter("CaseFind"); 
		//表示从综合查询进入
		if("1".equals(CaseFind)){
			httpServletRequest.setAttribute("CaseFind", "1");
		}
		// 操作类型 add|edit|view
		String actionType = "";
		if(ToolsUtils.isEmpty(caseId)) {
			actionType = "add";
		}else {
			actionType = httpServletRequest.getParameter("actionType");
		}

		// 天气情况
		List<QpSelectDataVo> weatherList =  qpTCommonService.getSelectData("WeatherType");
		this.getRequest().setAttribute("weatherList", weatherList);

		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
		this.getRequest().setAttribute("fastCenterList", fastCenterList);

		List<QpTICAccident> qpTICAccidentList = new ArrayList<QpTICAccident>();
		SessionUser sessionUser = getUserFromSession();
		if(ToolsUtils.isEmpty(caseId)) { // 新增
			// 设置默认值
			qpTTPCase = new QpTTPCase();
			qpTTPCase.setCaseTime(new DateTime(new DateTime(), DateTime.YEAR_TO_MINUTE)); // 案件发生时间
			qpTTPCase.setTpHandleTime(new DateTime(new DateTime().addMinute(1), DateTime.YEAR_TO_MINUTE)); // 案件处理时间
			qpTTPCase.setTpLoginId(sessionUser.getUserCode());          // 当前登录用户代码（同用户代码）
			qpTTPCase.setTpUserName(sessionUser.getUserName());         // 当前登录用户名称（同用户代码）

			//	        qpTTPCase.setTpEmployeeId(sessionUser.getEmployeeId());     // 警员编号（同用户代码）
			//	        qpTTPCase.setPoliceName(sessionUser.getUserName());         // 警员名称（同用户名称）
			//	        qpTTPCase.setPoliceEmployeeId(sessionUser.getEmployeeId()); // 警员编号（同用户代码）

			//协助人员为当前登录用户
			qpTTPCase.setAssistorId(sessionUser.getUserCode());
			qpTTPCase.setAssistorName(sessionUser.getUserName());

			qpTTPCase.setCaseProvince(sessionUser.getProvince());       // 默认省份
			qpTTPCase.setCaseCity(sessionUser.getCity());               // 默认城市
			qpTTPCase.setCenterId(sessionUser.getCenterId());           // 默认受理点
			
			//是否高速案件 新增办案民警
			QpTTPFastCenterId id = new QpTTPFastCenterId();
			id.setCenterId(sessionUser.getCenterId());
			qpTTPFastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(id);
			if(qpTTPFastCenter != null && "2".equals(qpTTPFastCenter.getCities())){
				qpTTPCase.setCaseType("1");
			}
			// 新增案件是否走交警流程
			String policyPro = "1";
			QpTFLOW qpTFLOW = qpTFLOWService.getPolicePro(sessionUser.getProvince(), sessionUser.getCity());
			if (qpTFLOW != null) {
				policyPro = qpTFLOW.getPolicePro();
			}
			httpServletRequest.setAttribute("policyPro", policyPro); 
		}else {

			// 案件基本信息
			QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
			// 案件ID
			qpTTPCaseId.setCaseId(caseId);
			qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
			// 格式化案件发生时间 yyyy-MM-dd hh:mm
			qpTTPCase.setCaseTime(new DateTime(qpTTPCase.getCaseTime(), DateTime.YEAR_TO_MINUTE));
			// 格式化案件处理时间 yyyy-MM-dd hh:mm
			qpTTPCase.setTpHandleTime(new DateTime(qpTTPCase.getTpHandleTime(), DateTime.YEAR_TO_MINUTE));
			// 当事人信息
			QpTICAccident qpTICAccident = new QpTICAccident();
			qpTICAccident.setCaseId(caseId);
			qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfo(qpTICAccident);
		}
		if(businessType==null||"".equals(businessType)){
			businessType = "3";
		}
		if(qpTTPCase.getCenterId() != null && !qpTTPCase.getCenterId().equals("") && !"2".equals(qpTTPFastCenter.getCities())){
			QpTCOMHandlePolice qpTCOMHandlePolice = new QpTCOMHandlePolice();
			qpTCOMHandlePolice.setValidStatus("1");
			qpTCOMHandlePolice.setCenterId(qpTTPCase.getCenterId());
			List<QpTCOMHandlePolice> handlePoliceList = qpTComHandlePoliceService.findByHandlePolice(qpTCOMHandlePolice);
			httpServletRequest.setAttribute("handlePoliceList", handlePoliceList); 
		}
		httpServletRequest.setAttribute("tpHandleStatus", qpTTPCase.getTpHandleStatus()); 
		httpServletRequest.setAttribute("businessType", businessType);           // 审核or修改，2-交警审核，3-协助员新增或修改
		httpServletRequest.setAttribute("actionType", actionType);               // 操作类型
		httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);                 // 案件信息
		httpServletRequest.setAttribute("qpTICAccidentList", qpTICAccidentList); // 当事人信息列表
		httpServletRequest.setAttribute("CaseFind", httpServletRequest.getParameter("CaseFind")); // 当事人信息列表
		httpServletRequest.setAttribute("currentDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // 当前时间

		return SUCCESS;
	}


	/**
	 * 更新案件信息
	 * @return
	 */
	public String saveCase() {
		// 是否有交警流程，默认为“有”
		String policyPro = "1";
		HttpServletRequest httpServletRequest = this.getRequest();

		Map<String, String> msg = new HashMap<String, String>();
		try {
			ParamUtils paramUtils = new ParamUtils(httpServletRequest);
			
			// 绑定案件信息
			QpTTPCase qpTTPCase = (QpTTPCase) paramUtils.generateObject(QpTTPCase.class, "qpTTPCase");
			String qpTICAccidentCount = httpServletRequest.getParameter("qpTICAccidentCount");
			String dealTime = httpServletRequest.getParameter("qpTTPCaseCaseTime");
			
			String handlePoliceNO = httpServletRequest.getParameter("handlePoliceNO");
			if(!com.alibaba.druid.util.StringUtils.isEmpty(handlePoliceNO)){
				qpTTPCase.setHandlePoliceNO(handlePoliceNO);
			}
			String handlePoliceName = httpServletRequest.getParameter("handlePoliceName");
			if(!com.alibaba.druid.util.StringUtils.isEmpty(handlePoliceName)){
				qpTTPCase.setHandlePoliceName(handlePoliceName);
			}
			qpTTPCase.setCaseTime(new DateTime(dealTime, DateTime.YEAR_TO_MINUTE));
			qpTTPCase.setUpdaterCode(getUserFromSession().getUserCode());
			qpTTPCase.setOperateTimeForHis(new Date());
			
			String tpHandleStatus = httpServletRequest.getParameter("tpHandleStatus");
			SessionUser sessionUser = getUserFromSession();
			// 新增
			if ("T".equals(tpHandleStatus)) {
				qpTTPCase.setAssistorId(sessionUser.getUserCode());
				qpTTPCase.setAssistorName(sessionUser.getUserName());
				qpTTPCase.setTpHandleTime(new Date());
				QpTFLOW qpTFLOW = qpTFLOWService.getPolicePro(sessionUser.getProvince(), sessionUser.getCity());
				if (qpTFLOW != null) {
					policyPro = qpTFLOW.getPolicePro();
					if ("0".equals(policyPro)) {
						qpTTPCase.setTpHandleStatus(CaseHandleStatusEnum.FOR_VERIFY.getCode());
						qpTTPCase.setDingzeTime(new Date());
					} else {
						qpTTPCase.setTpHandleStatus(CaseHandleStatusEnum.FOR_JUDGE.getCode());
					}
				} else {
					qpTTPCase.setTpHandleStatus(CaseHandleStatusEnum.FOR_JUDGE.getCode());
				}

				// 根据快赔点类型来确定案件类型
				QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
				qpTTPFastCenterId.setCenterId(qpTTPCase.getCenterId());
				QpTTPFastCenter qpTTPFastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(qpTTPFastCenterId);
				qpTTPCase.setLatitude(qpTTPFastCenter.getLatitude());
				qpTTPCase.setLongitude(qpTTPFastCenter.getLongitude());
				if("2".equals(qpTTPFastCenter.getCities()) ){//高速
					qpTTPCase.setCaseType("1");
				}else if("1".equals(qpTTPFastCenter.getCities()) ){//地市  - 添加经纬度
					qpTTPCase.setCaseType("0");
				}else {
					setMsg(msg, "N", "案件保存失败,该快赔点所属地市未分配<br>请重新添加或修改快赔点所属地市");
					this.writeJson(msg);
					return NONE;
				}
			} else {
				// 定责
				if ("2".equals(tpHandleStatus)) {
					qpTTPCase.setDingzeTime(new Date());
				}
				if ("5".equals(tpHandleStatus)) {
					qpTTPCase.setTpEmployeeId(sessionUser.getEmployeeId());     // 警员编号（同用户代码）
					qpTTPCase.setPoliceName(sessionUser.getUserName());         // 警员名称（同用户名称）
					qpTTPCase.setPoliceEmployeeId(sessionUser.getEmployeeId());
				}
				qpTTPCase.setTpHandleStatus(tpHandleStatus);
			}

			// 生成认字编号
			if (ToolsUtils.isEmpty(qpTTPCase.getCaseSerialNo())) {
				QpTTPFastCenterId fastCenterId = new QpTTPFastCenterId();
				fastCenterId.setCenterId(qpTTPCase.getCenterId());
				QpTTPFastCenter fastCenter = qpTTPFastCenterService
						.findQpTTPFastCenterByPK(fastCenterId);
				if (fastCenter != null) {
					String serinalNo = qpTTPCaseService
							.generateSerinalNo(fastCenter.getReadNum());
					if (ToolsUtils.notEmpty(serinalNo)) {
						qpTTPCase.setCaseSerialNo(serinalNo);
					}
				}
			}
			// 更新或者保存案件信息
			//			String userSort = this.getUserFromSession().getUserSort();
			//			if (userSort != null && !"".equals(userSort)
			//					&& ("07".equals(userSort) || "08".equals(userSort))) {
			//				qpTTPCase.setCaseType("1");
			//			} else {
			//				qpTTPCase.setCaseType("0");
			//			}

			qpTTPCase = qpTTPCaseService.saveOrupdateQpTTPCase(qpTTPCase);
			// 处理当事人信息
			if (qpTICAccidentCount == null || qpTICAccidentCount.equals("0")) {
				throw new Exception("缺少当事人信息！！");
			}
			logger.info(getUserFromSession().getUserCode()+"新增案件qpttpcase信息:"+JSONObject.toJSONString(qpTTPCase)+",操作时间"+new Date());
			for (int i = 0; i < 8; i++) {
				QpTICAccident qpTICAccident = (QpTICAccident) paramUtils
						.generateObject(QpTICAccident.class, "qpTICAccident["
								+ i + "]");
				
				if (ToolsUtils.isEmpty(qpTICAccident.getAcciCity())) {
					continue;
				}
				
				try {
					if(StringUtils.isNotEmpty(qpTICAccident.getAcciId()) && !"T".equals(tpHandleStatus)){
						String orgCode = CompanyCode.Company.getCompanyCode(qpTICAccident.getCoId());
						String comChannel = Constants.getREPORT_INTERFACE().get(orgCode+"_channel");
						logger.info("当事人保险公司编号:"+orgCode + "是否报案:"+comChannel);
						if("0".equals(comChannel) && !"1".equals(qpTTPCase.getCaseType()) && "430100".equals(qpTTPCase.getCaseCity())){
							logger.info("报案人责任："+qpTICAccident.getDriverLiability());
							if(!"14".equals(qpTICAccident.getDriverLiability())){
								ReportCaseData reportCaseData = iReportCaseDataService.findReportCaseDataByCaseIdAndAccId(qpTTPCase.getCaseId(), qpTICAccident.getAcciId());
								if(reportCaseData != null){
									//补传修改后的责任认定书
									QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
									qpTICAccidentId.setAcciId(qpTICAccident.getAcciId());
									QpTICAccident qpTICAccident2 = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);
									if(!qpTICAccident.getDriverMobile().equals(qpTICAccident2.getDriverMobile()) || 
											!qpTICAccident.getDriverIDNumber().equals(qpTICAccident2.getDriverIDNumber()) ||
											!qpTICAccident.getCoId().equals(qpTICAccident2.getCoId()) || 
											!qpTICAccident.getDriverVehicleNumber().equals(qpTICAccident2.getDriverVehicleNumber()) || 
											!qpTICAccident.getDriverLiability().equals(qpTICAccident2.getDriverLiability())){
										logger.info("修改了单证信息,添加单证补传任务,taskType:4");
										Date now = new Date();
										ReportTask reportTask = new ReportTask();
										reportTask.setId("UDZ"+reportCaseData.getTaskDataId());
										reportTask.setTaskDataId(reportCaseData.getTaskDataId());
										reportTask.setTaskType("4");
										reportTask.setCreateTime(now);
										iReportTaskService.add(reportTask);
									}
									QpTICAccident.copyAccident(qpTICAccident2, qpTICAccident);
									qpTICAccident = qpTICAccident2;
								}
							}
						}
						
					}
				} catch (Exception e) {
					logger.info("修改用户信息补传单证流程异常，不影响案件修改", e);
				}
				
				
				
				logger.info(getUserFromSession().getUserCode()+"新增案件qpttpcase当事人信息:"+JSONObject.toJSONString(qpTICAccident)+",操作时间"+new Date());
				qpTICAccident.setAcciTime(new DateTime(qpTTPCase.getCaseTime(),
						DateTime.YEAR_TO_MINUTE));
				qpTICAccident
				.setUpdaterCode(getUserFromSession().getUserCode());
				qpTICAccidentService
				.saveQpTICAccident(qpTTPCase, qpTICAccident);
				
				try {
					if ("T".equals(tpHandleStatus)) {
						//TODO 平安报案
						//10 全责  11主要责任 12次要责任  13同等责任  14无责任    循环是为了确定平安保险 - 有责任
						String orgCode = CompanyCode.Company.getCompanyCode(qpTICAccident.getCoId());
						String comChannel = Constants.getREPORT_INTERFACE().get(orgCode+"_channel");
						logger.info("当事人保险公司编号:"+orgCode + "是否报案:"+comChannel);
						if("0".equals(comChannel) && !"1".equals(qpTTPCase.getCaseType()) && "430100".equals(qpTTPCase.getCaseCity())){
							logger.info("报案人责任："+qpTICAccident.getDriverLiability());
							if(!"14".equals(qpTICAccident.getDriverLiability())){
									ReportCaseData reportCaseData = iReportCaseDataService.findReportCaseDataByCaseIdAndAccId(qpTTPCase.getCaseId(), qpTICAccident.getAcciId());
									logger.info("是否报案完成："+(reportCaseData!=null));
									if(reportCaseData == null){
										ReportTaskData reportTaskData = iReportTaskDataService.findReportTaskDataByAccId(qpTICAccident.getAcciId());
										logger.info("是否正在报案中："+(reportTaskData!=null));
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
											reportTaskData.setCaseId(qpTTPCase.getCaseId());
											reportTaskData.setAccId(qpTICAccident.getAcciId());
											reportTaskData.setCreateTime(now);
											reportTaskData.setOrgCode(orgCode);
											iReportTaskDataService.add(reportTaskData);
										}
									}
								
							}
						}
					}
				} catch (Exception e) {
					logger.error("--report--快赔点记录报案异常,案件ID:"+qpTTPCase.getCaseId() + "，标的当事人id:"+ qpTICAccident.getAcciId() + ",异常信息:" + e);
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
			}
				
			// System.out.println("CoId:====="+httpServletRequest.getParameter("qpTICAccident[0]CoId"));
			// 无交警流程，可以进入打印。
			if ("0".equals(policyPro)) {
				setMsg(msg, "Y", "保存成功，是否打印！");
			} else {
				setMsg(msg, "Y", "案件保存处理成功！");
			}
			msg.put("qpTTPCaseCaseId", qpTTPCase.getId().getCaseId());


			// 地市案件才同步到一路通,高速案件无视 TODO
//			try {
//				if ("0".equals(qpTTPCase.getCaseType())) {
//					QpTTPFastCenterId centerID = new QpTTPFastCenterId();
//					centerID.setCenterId(qpTTPCase.getCenterId());
//					QpTTPFastCenter center = qpTTPFastCenterService.findQpTTPFastCenterByPK(centerID);
//					if ("430100".equals(center.getCityId())) {
//						Map<String, String> params = new HashMap<String, String>();
//						params.put("caseId", qpTTPCase.getCaseId());
//						if ("T".equals(tpHandleStatus) && "0".equals(policyPro)) {
//							// 新增案件,无交警就自动定责,案件保存到待推送列表
//							qpTAsyncTaskService.createTask(UploadCaseToCSHandler.type, params);
//						} else if ("2".equals(tpHandleStatus)) {
//							// 案件保存到待推送列表
//							qpTAsyncTaskService.createTask(UploadCaseToCSHandler.type, params);
//						}
//					}
//				}
//			} catch (Exception e) {
//				logger.error("---- 新增任务到待推送列表失败, caseID=" + qpTTPCase.getCaseId() + " ----");
//				e.printStackTrace();
//			}

			this.writeJson(msg);
		} catch (Exception e) {
			logger.error("保存案件异常:", e);
			setMsg(msg, "N", "处理失败,原因:" + e.getMessage());
			this.writeJson(msg);
		}
		return NONE;
	}


	/**
	 * 设置返回的信息
	 * @param msg
	 * @param flag Y/N   
	 * @param content 提示内容        
	 */
	private void setMsg(Map<String, String> msg, String flag, String content) {
		msg.put("flag", flag);
		msg.put("content", content);
	}

	/**
	 * 新增QpTTPCase信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTTPCaseService.saveQpTTPCase(qpTTPCase);
		return SUCCESS;
	}



	/**
	 * 删除QpTTPCase信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTTPCaseService.deleteByPK(id);
			}
			logger.error("删除案件:"+id + ",操作人："+getUserFromSession().getUserCode()+",操作时间:"+new Date());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}



	/**
	 * 查看QpTTPCase信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
		return SUCCESS;
	}

	/**
	 * 打印事故处理记录表
	 * @return
	 */
	public String printPersonCaseDetailPreview() {
		try {
			String accID = this.getRequest().getParameter("accID");
			String caseID = this.getRequest().getParameter("caseID");
			String imgUrl = this.getServletContext().getRealPath("/printTemplate/604421331541812454.jpg");
			String templateUrl = this.getServletContext().getRealPath("/printTemplate/report7.jasper");
			QpTTPCaseId queryCaseID = new QpTTPCaseId();
			queryCaseID.setCaseId(caseID);
			QpTTPCase currentCase = qpTTPCaseService.findQpTTPCaseByPK(queryCaseID);
			String lossStampUrl = System.getProperty("webapp.root") + "printTemplate/stamp/"+currentCase.getCenterId()+".PNG";
			boolean lossStampIsPrint = false;//是否打印各快赔点印章，只是图片，无法律效应
			boolean isPrint = "true".equals(this.getRequest().getParameter("isPrint"));
			if (StringUtils.isNotEmpty(accID) && StringUtils.isNotEmpty(caseID)) {
				JSONObject sourceJson = wxCaseService.getLossSourceJson(caseID, accID, imgUrl, templateUrl, isPrint,lossStampUrl,lossStampIsPrint);	
				File reportFile = new File(templateUrl);
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), sourceJson.getJSONObject("params"), sourceJson.getObject("dataSource", JRDataSource.class)); 	
				this.pdfStream = new ByteArrayInputStream(bytes);
				this.pdfStream.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (pdfStream != null) {
				try {
					this.pdfStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return SUCCESS;
	}
	
	public class UserInfoItemsData{
		public JRBeanCollectionDataSource userInfoItems;
		public UserInfoItemsData() {

		}
		public UserInfoItemsData(JRBeanCollectionDataSource userInfoItems) {
			this.setUserInfoItems(userInfoItems);
		}
		public JRBeanCollectionDataSource getUserInfoItems() {
			return userInfoItems;
		}
		public void setUserInfoItems(JRBeanCollectionDataSource userInfoItems) {
			this.userInfoItems = userInfoItems;
		}
	}

	public class Items {
		private JRBeanCollectionDataSource currentChangeItems;
		private JRBeanCollectionDataSource currentRepairItems;
		private JRBeanCollectionDataSource otherChangeItems;
		private JRBeanCollectionDataSource otherRepairItems;
		public Items() {

		}
		public Items(JRBeanCollectionDataSource currentChangeItems, JRBeanCollectionDataSource currentRepairItems, JRBeanCollectionDataSource otherChangeItems, JRBeanCollectionDataSource otherRepairItems) {
			this.setCurrentChangeItems(currentChangeItems);
			this.setCurrentRepairItems(currentRepairItems);
			this.setOtherChangeItems(otherChangeItems);
			this.setOtherRepairItems(otherRepairItems);
		}
		public JRBeanCollectionDataSource getCurrentChangeItems() {
			return currentChangeItems;
		}
		public void setCurrentChangeItems(JRBeanCollectionDataSource currentChangeItems) {
			this.currentChangeItems = currentChangeItems;
		}
		public JRBeanCollectionDataSource getCurrentRepairItems() {
			return currentRepairItems;
		}
		public void setCurrentRepairItems(JRBeanCollectionDataSource currentRepairItems) {
			this.currentRepairItems = currentRepairItems;
		}
		public JRBeanCollectionDataSource getOtherChangeItems() {
			return otherChangeItems;
		}
		public void setOtherChangeItems(JRBeanCollectionDataSource otherChangeItems) {
			this.otherChangeItems = otherChangeItems;
		}
		public JRBeanCollectionDataSource getOtherRepairItems() {
			return otherRepairItems;
		}
		public void setOtherRepairItems(JRBeanCollectionDataSource otherRepairItems) {
			this.otherRepairItems = otherRepairItems;
		}
	}

	public class Item {
		private String name;
		private String money;
		public Item () {

		}
		public Item(String name, String money) {
			this.setName(name);
			this.setMoney(money);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
	}

	/**
	 *  打印预览page , 数据获取
	 *
	 * @version 创建时间：2015-11-22 上午11:42:44
	 *
	 * @return
	 * @throws Exception
	 */
	public String printPreview()throws Exception {
		//try {
		// 案件ID
		String caseID = this.getRequest().getParameter("qpTTPCaseCaseId");
		// 打印方式
		String printMethod =  this.getRequest().getParameter("printMethod");

		QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
		qpTTPCaseId.setCaseId(caseID);
		//qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
		if(qpTTPCase == null) {
			qpTTPCase = new QpTTPCase();
		}
		qpTTPCase.setCaseId(caseID);
		qpTTPCase = qpTTPCaseService.findQpTTPCaseById(qpTTPCase);

		String caseType = qpTTPCase.getCaseType();


		//高速案件打印
		if(caseType!=null&&"1".equals(caseType)){
			qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfoOnly(caseID);
			File reportFile = new File(this.getServletContext().getRealPath("/printTemplate/gsjj_report_sc.jasper"));   
			Map<String,Object> parameters=new HashMap<String,Object>();  
			parameters.put("reportTitle", "This is a client report");  
			JRDataSource dataSource = qpTTPCaseService.getDataSourcePrint(qpTTPCase);
			if(StringUtils.isNotEmpty(printMethod)&&"pdf".equals(printMethod)){		
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,dataSource); 	
				this.pdfStream = new ByteArrayInputStream(bytes);  
				this.pdfStream.close();
				return "gsp_success";
			}else{
				JasperRunManager.runReportToHtmlFile(reportFile.getPath(), parameters,dataSource);
				//System.out.println("path=="+path);
				return "gsh_success";
			}
		}else{
			//微信上传的采用全打模式
			if("26".equals(qpTTPCase.getCenterId())){
				String policyCode = qpTTPCase.getPoliceEmployeeId();
				//qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfoOnly(caseID);
				File reportFile = new File(this.getServletContext().getRealPath("/printTemplate/wxjj_report_sc.jasper"));   
				Map<String,Object> parameters=new HashMap<String,Object>();  
				parameters.put("reportTitle", "This is a client report");  
				JRDataSource dataSource = qpTTPCaseService.getDataSourcePrint(qpTTPCase);
				if(StringUtils.isNotEmpty(printMethod)&&"pdf".equals(printMethod)){			
					//					    						String rootPath = Constants.getSEAL_ROOTPATH();
					//					    						String time = DateUtil.parseToFormatString(new Date(),DateUtil.DATE_FORMAT_YYYYMMDD);
					//					    						File rootdir = new File(rootPath);
					//					    						if (!rootdir.exists()) {
					//					    							rootdir.mkdirs();
					//					    						}
					//					    						File savedir = new File(rootPath+"/"+time);
					//					    						if (!savedir.exists()) {
					//					    							savedir.mkdirs();
					//					    						}
					//					    						String uuid = UuidUtil.get32UUID();
					//					    						String destFileName = rootPath+"/"+time+"/"+uuid+".pdf";
					//					    						JasperRunManager.runReportToPdfFile(reportFile.getPath(), destFileName, parameters,dataSource);
					//					    						logger.info("案件号：" + caseID+"开始加盖印章");
					//					    						String outPdfPath = qpTTPCaseService.stampedSeal(destFileName,policyCode);
					String outPdfPath =  SealUtil.csWechatStampedSeal(reportFile.getPath(), parameters, dataSource, caseID,policyCode);					
					this.pdfStream = new FileInputStream(new File(outPdfPath));
					return "wxp_success";
				}else{
					JasperRunManager.runReportToHtmlFile(reportFile.getPath(), parameters,dataSource);
					//System.out.println("path=="+path);
					return "wxh_success";
				}
			}else{
				QpTICAccident qpTICAccident = new QpTICAccident();
				qpTICAccident.setCaseId(caseID);
				qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfo(qpTICAccident);

				operateType = "printPreview";
				// 违反法律法规
				qpTTPLawList = new ArrayList<QpTTPLaw>();
				qpAcciLawDataVoList = new ArrayList<QpAcciLawDataVo>();
				qpTICAcciPrintVo = new QpTICAcciPrintVo();
				qpTICAcciPrintVo.setCaseNotes(qpTTPCase.getCaseNotes());
				QpTTPLawId qpTTPLawId = new QpTTPLawId();
				//旧系统为固定三条当事人记录，特殊处理
				if(qpTICAccidentList != null) {
					for(int i = 0; i < 3; i++) {
						String driverDirectionDesc = null;
						String dirverStreet = null;

						if(i < qpTICAccidentList.size()) {
							QpTICAccident qpTICAccidentTmp = qpTICAccidentList.get(i);

							driverDirectionDesc = qpTICAccidentTmp.getDriverDirectionDesc();
							dirverStreet = qpTICAccidentTmp.getDriverStreet();

							if("0".equals(qpTICAccidentTmp.getDriverSex())) {
								qpTICAccidentTmp.setDriverSexDesc("男");
							}else {
								qpTICAccidentTmp.setDriverSexDesc("女");
							}

							// 法律法规打印信息处理
							qpTTPLawId.setLawId(qpTICAccidentTmp.getDriverLawId());
							QpTTPLaw qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(qpTTPLawId);
							QpAcciLawDataVo qpAcciLawDataVo = new QpAcciLawDataVo();
							// 责任信息处理
							if("10".equals(qpTICAccidentTmp.getDriverLiability())) {
								qpAcciLawDataVo.setDriverLiability("全部"); 
							}else if("11".equals(qpTICAccidentTmp.getDriverLiability()))  {
								qpAcciLawDataVo.setDriverLiability("主要");
							}else if("12".equals(qpTICAccidentTmp.getDriverLiability()))  {
								qpAcciLawDataVo.setDriverLiability("次要");
							}else if("13".equals(qpTICAccidentTmp.getDriverLiability()))  {
								qpAcciLawDataVo.setDriverLiability("同等");
							}else if("14".equals(qpTICAccidentTmp.getDriverLiability()))  {
								qpAcciLawDataVo.setDriverLiability("无");
							}else{
								qpAcciLawDataVo.setDriverLiability("\\");
							}
							if(qpTTPLaw != null) {

								// 法律法规处理
								if("1".equals(qpTTPLaw.getLawType())) {
									qpAcciLawDataVo.setLawItemA(qpTTPLaw.getLawItem());
									qpAcciLawDataVo.setLawSegmentA(qpTTPLaw.getLawSegment());
									qpAcciLawDataVo.setLawSectionA(qpTTPLaw.getLawSection());
								}else {
									qpAcciLawDataVo.setLawItemA("\\");
									qpAcciLawDataVo.setLawSegmentA("\\");
									qpAcciLawDataVo.setLawSectionA("\\");
								}
								if("2".equals(qpTTPLaw.getLawType())) {
									qpAcciLawDataVo.setLawItemB(qpTTPLaw.getLawItem());
									qpAcciLawDataVo.setLawSegmentB(qpTTPLaw.getLawSegment());
									qpAcciLawDataVo.setLawSectionB(qpTTPLaw.getLawSection());
								}else {
									qpAcciLawDataVo.setLawItemB("\\");
									qpAcciLawDataVo.setLawSegmentB("\\");
									qpAcciLawDataVo.setLawSectionB("\\");
								}
								if("3".equals(qpTTPLaw.getLawType())) {
									qpAcciLawDataVo.setLawItemC(qpTTPLaw.getLawItem());
									qpAcciLawDataVo.setLawSegmentC(qpTTPLaw.getLawSegment());
									qpAcciLawDataVo.setLawSectionC(qpTTPLaw.getLawSection());
								}else {
									qpAcciLawDataVo.setLawItemC("\\");
									qpAcciLawDataVo.setLawSegmentC("\\");
									qpAcciLawDataVo.setLawSectionC("\\");
								}
								qpAcciLawDataVoList.add(qpAcciLawDataVo);
							}else{
								qpAcciLawDataVo.setLawItemC("\\");
								qpAcciLawDataVo.setLawSegmentC("\\");
								qpAcciLawDataVo.setLawSectionC("\\");
								qpAcciLawDataVoList.add(qpAcciLawDataVo);
							}

						}else {
							QpTICAccident qpTICAccidentTmp = new QpTICAccident();
							qpTICAccidentList.add(qpTICAccidentTmp);
							driverDirectionDesc = qpTICAccidentTmp.getDriverDirectionDesc();

							QpAcciLawDataVo qpAcciLawDataVo = new QpAcciLawDataVo();
							qpAcciLawDataVo.setLawItemA("\\");
							qpAcciLawDataVo.setLawSegmentA("\\");
							qpAcciLawDataVo.setLawSectionA("\\");
							qpAcciLawDataVo.setLawItemB("\\");
							qpAcciLawDataVo.setLawSegmentB("\\");
							qpAcciLawDataVo.setLawSectionB("\\");
							qpAcciLawDataVo.setLawItemC("\\");
							qpAcciLawDataVo.setLawSegmentC("\\");
							qpAcciLawDataVo.setLawSectionC("\\");
							qpAcciLawDataVoList.add(qpAcciLawDataVo);
						}

						// 事故打印信息处理
						// 甲
						if(i == 0) {
							if(!ToolsUtils.isEmpty(dirverStreet)) {
								qpTICAcciPrintVo.setDirectionRoadA(dirverStreet);
							}else {
								qpTICAcciPrintVo.setDirectionRoadA("\\");
							}
							if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
								if("方向不明".equals(driverDirectionDesc)){
									qpTICAcciPrintVo.setDirectionFromA("\\");
									qpTICAcciPrintVo.setDirectionToA("\\");
								}else{
									qpTICAcciPrintVo.setDirectionFromA(driverDirectionDesc.substring(1, 2));
									if(driverDirectionDesc.length()==2){
										qpTICAcciPrintVo.setDirectionToA(driverDirectionDesc.substring(1, 2));
									}else{
										qpTICAcciPrintVo.setDirectionToA(driverDirectionDesc.substring(3, 4));
									}
								}
							}else {
								qpTICAcciPrintVo.setDirectionFromA("\\");
								qpTICAcciPrintVo.setDirectionToA("\\");
							}
						}
						// 乙
						if(i == 1) {
							if(!ToolsUtils.isEmpty(dirverStreet)) {
								qpTICAcciPrintVo.setDirectionRoadB(dirverStreet);
							}else {
								qpTICAcciPrintVo.setDirectionRoadB("\\");
							}
							if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
								if("方向不明".equals(driverDirectionDesc)){
									qpTICAcciPrintVo.setDirectionFromB("\\");
									qpTICAcciPrintVo.setDirectionToB("\\");
								}else{
									qpTICAcciPrintVo.setDirectionFromB(driverDirectionDesc.substring(1, 2));
									if(driverDirectionDesc.length()==2){
										qpTICAcciPrintVo.setDirectionToB(driverDirectionDesc.substring(1, 2));
									}else{
										qpTICAcciPrintVo.setDirectionToB(driverDirectionDesc.substring(3, 4));
									}
								}
							}else {
								qpTICAcciPrintVo.setDirectionFromB("\\");
								qpTICAcciPrintVo.setDirectionToB("\\");
							}
						}
						// 丙
						if(i == 2) {
							if(!ToolsUtils.isEmpty(dirverStreet)) {
								qpTICAcciPrintVo.setDirectionRoadC(dirverStreet);
							}else {
								qpTICAcciPrintVo.setDirectionRoadC("\\");
							}
							if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
								if("方向不明".equals(driverDirectionDesc)){
									qpTICAcciPrintVo.setDirectionFromC("\\");
									qpTICAcciPrintVo.setDirectionToC("\\");
								}else{
									qpTICAcciPrintVo.setDirectionFromC(driverDirectionDesc.substring(1, 2));
									if(driverDirectionDesc.length()==2){
										qpTICAcciPrintVo.setDirectionToC(driverDirectionDesc.substring(1, 2));
									}else{
										qpTICAcciPrintVo.setDirectionToC(driverDirectionDesc.substring(3, 4));
									}
								}

							}else {
								qpTICAcciPrintVo.setDirectionFromC("\\");
								qpTICAcciPrintVo.setDirectionToC("\\");
							}
						}
					}
				}

				String txtdi = ToolsUtils.getDate();

				this.getRequest().setAttribute("txtdiYear", txtdi.substring(0, 4));
				this.getRequest().setAttribute("txtdiMonth", txtdi.substring(5, 7));
				this.getRequest().setAttribute("txtdiDay", txtdi.substring(8, 10));
				if(qpTTPCase != null){
					String caseTimeStr = new DateTime(qpTTPCase.getCaseTime(), DateTime.YEAR_TO_MINUTE).toString();
					this.getRequest().setAttribute("caseYear", caseTimeStr.substring(0, 4));
					this.getRequest().setAttribute("caseMonth", caseTimeStr.substring(5, 7));
					this.getRequest().setAttribute("caseDay", caseTimeStr.substring(8, 10));
					this.getRequest().setAttribute("caseHH", caseTimeStr.substring(11, 13));
					this.getRequest().setAttribute("casemm", caseTimeStr.substring(14, 16));
				}

				// 认字编号
				String caseSerialNo = qpTTPCase.getCaseSerialNo();
				if(caseSerialNo!=null&&!"".equals(caseSerialNo)){
					qpTICAcciPrintVo.setCaseNoA(caseSerialNo.substring(0, 4));
					qpTICAcciPrintVo.setCaseNoB(caseSerialNo.substring(4, caseSerialNo.length()));
				}
				return SUCCESS;
			}
		}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//			logger.error("", e);
		//		}
	}



	/**
	 *  打印预览page , 数据获取
	 *
	 * @version 创建时间：2015-11-22 上午11:42:44
	 *
	 * @return
	 * @throws Exception
	 */
	public String printPreview2()throws Exception {
		//try {
		// 案件ID
		String caseID = this.getRequest().getParameter("qpTTPCaseCaseId");
		// 打印方式
		boolean isPrint = "true".equals(this.getRequest().getParameter("isPrint"));

		QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
		qpTTPCaseId.setCaseId(caseID);
		//qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
		if(qpTTPCase == null) {
			qpTTPCase = new QpTTPCase();
		}
		qpTTPCase.setCaseId(caseID);
		qpTTPCase = qpTTPCaseService.findQpTTPCaseById(qpTTPCase);

		QpTICAccident qpTICAccident = new QpTICAccident();
		qpTICAccident.setCaseId(caseID);
		qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfo(qpTICAccident);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imgUrl", this.getServletContext().getRealPath("/printTemplate/book.jpg"));
		params.put("isPrint", isPrint);
		params.put("Number", "122处警");
		
		// 认字编号  长公交（）认字（）。NO
		String caseSerialNo = qpTTPCase.getCaseSerialNo();
		if(caseSerialNo!=null&&!"".equals(caseSerialNo)){
			params.put("caseSerialNoHead", caseSerialNo.substring(0, 4));
			params.put("caseSerialNoAfter", caseSerialNo.substring(4, caseSerialNo.length()));
		}
		
		if(qpTTPCase != null){
			params.put("policeName", qpTTPCase.getTpUserName());
			params.put("policeNumber", qpTTPCase.getTpEmployeeId());
			
			String caseTimeStr = new DateTime(qpTTPCase.getCaseTime(), DateTime.YEAR_TO_MINUTE).toString();
			params.put("year", caseTimeStr.substring(0, 4));
			params.put("month", caseTimeStr.substring(5, 7));
			params.put("day", caseTimeStr.substring(8, 10));
			params.put("hours", caseTimeStr.substring(11, 13));
			params.put("minutes", caseTimeStr.substring(14, 16));
			//地址
			params.put("caseAddress", qpTTPCase.getCaseStreet());
			//天气
			params.put("weather", qpTTPCase.getCaseWeatherDesc());
			
			//事故事实--发生
			params.put("caseNotes", qpTTPCase.getCaseNotes());
			//调解结果
			params.put("caseResult", qpTTPCase.getCaseResult());
			
		}

		List<UserInfoBean> userInfoItems = new ArrayList<UserInfoBean>();
		
		// 违反法律法规
		qpTTPLawList = new ArrayList<QpTTPLaw>();
		qpAcciLawDataVoList = new ArrayList<QpAcciLawDataVo>();
		qpTICAcciPrintVo = new QpTICAcciPrintVo();
		qpTICAcciPrintVo.setCaseNotes(qpTTPCase.getCaseNotes());
		QpTTPLawId qpTTPLawId = new QpTTPLawId();
		//旧系统为固定三条当事人记录，特殊处理
		if(qpTICAccidentList != null) {
			for(int i = 0; i < 3; i++) {
				String driverDirectionDesc = null;
				String dirverStreet = null;
				String identityDesc = "";
				if(i == 0){
					identityDesc = "a";
				}else if(i == 1){
					identityDesc = "b";
				}else if(i == 2){
					identityDesc = "c";
				}else {
					break;
				}
				if(i < qpTICAccidentList.size()) {
					QpTICAccident qpTICAccidentTmp = qpTICAccidentList.get(i);

					driverDirectionDesc = qpTICAccidentTmp.getDriverDirectionDesc();
					dirverStreet = qpTICAccidentTmp.getDriverStreet();

					if("0".equals(qpTICAccidentTmp.getDriverSex())) {
						qpTICAccidentTmp.setDriverSexDesc("男");
					}else {
						qpTICAccidentTmp.setDriverSexDesc("女");
					}
					
					UserInfoBean uInfo = new UserInfoBean();
					uInfo.setName(qpTICAccidentTmp.getDriverName());
					uInfo.setSex(qpTICAccidentTmp.getDriverSexDesc());
					uInfo.setDriverIDNumber(qpTICAccidentTmp.getDriverIDNumber());
					uInfo.setAddress(qpTICAccidentTmp.getDriverAddress());
					uInfo.setMobile(qpTICAccidentTmp.getDriverMobile());
					uInfo.setDriverInsuNumber("26".equals(qpTICAccidentTmp.getCoId())?qpTICAccidentTmp.getCompanyNameOther():qpTICAccidentTmp.getCoName());
					uInfo.setDriverVehicleType(qpTICAccidentTmp.getDriverVehicleTypeDesc());
					uInfo.setVehicleNo(qpTICAccidentTmp.getDriverVehicleNumber());
					userInfoItems.add(uInfo);

					// 法律法规打印信息处理
					qpTTPLawId.setLawId(qpTICAccidentTmp.getDriverLawId());
					QpTTPLaw qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(qpTTPLawId);
//					QpAcciLawDataVo qpAcciLawDataVo = new QpAcciLawDataVo();
					// 责任信息处理
					String driverLiability = "";
					if("10".equals(qpTICAccidentTmp.getDriverLiability())) {
						driverLiability = "全部"; 
					}else if("11".equals(qpTICAccidentTmp.getDriverLiability()))  {
						driverLiability = "主要";
					}else if("12".equals(qpTICAccidentTmp.getDriverLiability()))  {
						driverLiability = "次要";
					}else if("13".equals(qpTICAccidentTmp.getDriverLiability()))  {
						driverLiability = "同等";
					}else if("14".equals(qpTICAccidentTmp.getDriverLiability()))  {
						driverLiability = "无";
					}else{
						driverLiability = "\\";
					}
					params.put(identityDesc+"13", driverLiability);
					if(qpTTPLaw != null) {
						// 法律法规处理
						if("1".equals(qpTTPLaw.getLawType())) {
//							qpAcciLawDataVo.setLawItemA(qpTTPLaw.getLawItem());
//							qpAcciLawDataVo.setLawSegmentA(qpTTPLaw.getLawSegment());
//							qpAcciLawDataVo.setLawSectionA(qpTTPLaw.getLawSection());
							params.put(identityDesc+"4", qpTTPLaw.getLawItem());
							params.put(identityDesc+"5", qpTTPLaw.getLawSegment());
							params.put(identityDesc+"6", qpTTPLaw.getLawSection());
						}else {
//							qpAcciLawDataVo.setLawItemA("\\");
//							qpAcciLawDataVo.setLawSegmentA("\\");
//							qpAcciLawDataVo.setLawSectionA("\\");
							params.put(identityDesc+"4", "\\");
							params.put(identityDesc+"5", "\\");
							params.put(identityDesc+"6", "\\");
						}
						if("2".equals(qpTTPLaw.getLawType())) {
//							qpAcciLawDataVo.setLawItemB(qpTTPLaw.getLawItem());
//							qpAcciLawDataVo.setLawSegmentB(qpTTPLaw.getLawSegment());
//							qpAcciLawDataVo.setLawSectionB(qpTTPLaw.getLawSection());
							params.put(identityDesc+"7", qpTTPLaw.getLawItem());
							params.put(identityDesc+"8", qpTTPLaw.getLawSegment());
							params.put(identityDesc+"9", qpTTPLaw.getLawSection());
						}else {
//							qpAcciLawDataVo.setLawItemB("\\");
//							qpAcciLawDataVo.setLawSegmentB("\\");
//							qpAcciLawDataVo.setLawSectionB("\\");
							params.put(identityDesc+"7", "\\");
							params.put(identityDesc+"8", "\\");
							params.put(identityDesc+"9", "\\");
						}
						if("3".equals(qpTTPLaw.getLawType())) {
//							qpAcciLawDataVo.setLawItemC(qpTTPLaw.getLawItem());
//							qpAcciLawDataVo.setLawSegmentC(qpTTPLaw.getLawSegment());
//							qpAcciLawDataVo.setLawSectionC(qpTTPLaw.getLawSection());
							params.put(identityDesc+"10", qpTTPLaw.getLawItem());
							params.put(identityDesc+"11", qpTTPLaw.getLawSegment());
							params.put(identityDesc+"12", qpTTPLaw.getLawSection());
						}else {
//							qpAcciLawDataVo.setLawItemC("\\");
//							qpAcciLawDataVo.setLawSegmentC("\\");
//							qpAcciLawDataVo.setLawSectionC("\\");
							params.put(identityDesc+"10", "\\");
							params.put(identityDesc+"11", "\\");
							params.put(identityDesc+"12", "\\");
						}
//						qpAcciLawDataVoList.add(qpAcciLawDataVo);
					}

				}else {
//					QpTICAccident qpTICAccidentTmp = new QpTICAccident();
//					qpTICAccidentList.add(qpTICAccidentTmp);
//					driverDirectionDesc = qpTICAccidentTmp.getDriverDirectionDesc();
//
//					QpAcciLawDataVo qpAcciLawDataVo = new QpAcciLawDataVo();
//					qpAcciLawDataVo.setLawItemA("\\");
//					qpAcciLawDataVo.setLawSegmentA("\\");
//					qpAcciLawDataVo.setLawSectionA("\\");
//					qpAcciLawDataVo.setLawItemB("\\");
//					qpAcciLawDataVo.setLawSegmentB("\\");
//					qpAcciLawDataVo.setLawSectionB("\\");
//					qpAcciLawDataVo.setLawItemC("\\");
//					qpAcciLawDataVo.setLawSegmentC("\\");
//					qpAcciLawDataVo.setLawSectionC("\\");
//					qpAcciLawDataVoList.add(qpAcciLawDataVo);
					params.put(identityDesc+"4", "\\");
					params.put(identityDesc+"5", "\\");
					params.put(identityDesc+"6", "\\");
					params.put(identityDesc+"7", "\\");
					params.put(identityDesc+"8", "\\");
					params.put(identityDesc+"9", "\\");
					params.put(identityDesc+"10", "\\");
					params.put(identityDesc+"11", "\\");
					params.put(identityDesc+"12", "\\");
				}

				// 事故打印信息处理
				// 甲
				if(i == 0) {
					
					
					
					if(!ToolsUtils.isEmpty(dirverStreet)) {
						params.put(identityDesc+"1", dirverStreet);
					}else {
						params.put(identityDesc+"1", "\\");
					}
					if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
						if("方向不明".equals(driverDirectionDesc)){
							params.put(identityDesc+"2", driverDirectionDesc.substring(1, 2));
							if(driverDirectionDesc.length()==2){
								params.put(identityDesc+"3", driverDirectionDesc.substring(1, 2));
							}else{
								params.put(identityDesc+"3", driverDirectionDesc.substring(3, 4));
							}
						}else {
							params.put(identityDesc+"2", "\\");
							params.put(identityDesc+"3", "\\");
						}
					}else {
						params.put(identityDesc+"2", "\\");
						params.put(identityDesc+"3", "\\");
					}
				}
				// 乙
				if(i == 1) {
					if(!ToolsUtils.isEmpty(dirverStreet)) {
						params.put(identityDesc+"1", dirverStreet);
					}else {
						params.put(identityDesc+"1", "\\");
					}
					if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
						params.put(identityDesc+"2", driverDirectionDesc.substring(1, 2));
						if(driverDirectionDesc.length()==2){
							params.put(identityDesc+"3", driverDirectionDesc.substring(1, 2));
						}else{
							params.put(identityDesc+"3", driverDirectionDesc.substring(3, 4));
						}
					}else {
						params.put(identityDesc+"2", "\\");
						params.put(identityDesc+"3", "\\");
					}
				}
				// 丙
				if(i == 2) {
					if(!ToolsUtils.isEmpty(dirverStreet)) {
						params.put(identityDesc+"1", dirverStreet);
					}else {
						params.put(identityDesc+"1", "\\");
					}
					if(!ToolsUtils.isEmpty(driverDirectionDesc)) {
						params.put(identityDesc+"2", driverDirectionDesc.substring(1, 2));
						if(driverDirectionDesc.length()==2){
							params.put(identityDesc+"3", driverDirectionDesc.substring(1, 2));
						}else{
							params.put(identityDesc+"3", driverDirectionDesc.substring(3, 4));
						}

					}else {
						params.put(identityDesc+"2", "\\");
						params.put(identityDesc+"3", "\\");
					}
				}
			}
		}

		String txtdi = ToolsUtils.getDate();

		params.put("txtdiYear", txtdi.substring(0, 4));
		params.put("txtdiMonth", txtdi.substring(5, 7));
		params.put("txtdiDay", txtdi.substring(8, 10));
		
		JRBeanCollectionDataSource userInfoDataSource = new JRBeanCollectionDataSource(userInfoItems, false); 
		UserInfoItemsData items = new UserInfoItemsData(userInfoDataSource);
		Collection<UserInfoItemsData> itemsCollections = new Vector<UserInfoItemsData>(); 
		itemsCollections.add(items);
		JRDataSource dataSource = new JRBeanCollectionDataSource(itemsCollections);
		File reportFile = new File(this.getServletContext().getRealPath("/printTemplate/book.jasper"));
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, dataSource); 	
		this.pdfStream = new ByteArrayInputStream(bytes);
		this.pdfStream.close();
		
		return SUCCESS;
	}
	/**
	 * 导出Excel
	 * 
	 * @return
	 */
	public void downloadCase() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		HttpServletResponse response = getResponse();
		//excel模板路径
		String filePath =getServletContext().getRealPath("/") + "/template/QpTICAccidentInfoTemp.xls";  
		HSSFWorkbook wb = qpTTPCaseService.getDownLoadWB(qpTTPCase, filePath);
		// 页面直接提示下载信息
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + ToolsUtils.changeFileName("*.xls"));
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.close();
	}

	/**
	 * 个人台帐页面准备方法
	 * @throws Exception
	 */
	public String prepareAccountQuery() throws Exception {
		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);

		SessionUser sessionUser = getUserFromSession();
		qpTTPCaseStatVO = new QpTTPCaseStatVO();
		qpTTPCaseStatVO.setCenterId(sessionUser.getCenterId());           // 默认受理点

		this.getRequest().setAttribute("fastCenterList", fastCenterList);
		// 保险公司
		List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
		this.getRequest().setAttribute("qpTICCompanyList", qpTICCompanyList);
		return SUCCESS;
	}

	/**
	 * 导出台帐信息
	 * @throws Exception
	 */
	public void accountsExpExcel() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		HttpServletResponse response = getResponse();
		//excel模板路径
		String filePath =getServletContext().getRealPath("/") + "/template/QpTICAccidentAccountsTemp.xls";
//		qpTTPCaseStatVO.setLossAssessorCode(getUserFromSession().getUserCode());
		HSSFWorkbook wb = qpTTPCaseService.getAccountDownLoadWB(qpTTPCaseStatVO, filePath);
		// 页面直接提示下载信息
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + ToolsUtils.changeFileName("*.xls"));
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.close();
	}

	/**
	 * 个人台账查询
	 * 
	 * @return
	 */
	public String queryAssessment() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}

		try {
//			qpTTPCaseStatVO.setLossAssessorCode(getUserFromSession().getUserCode());
			Page resultPage = qpTICAccidentService.findQpTICAccidentPageBySql(qpTTPCaseStatVO, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}

		return NONE;
	}	

	/**
	 * 地图弹出框
	 * 
	 * @return
	 */
	public String prepareShowIMap() throws Exception {
		HttpServletRequest httpServletRequest = this.getRequest();
		// 获取案件信息
		ParamUtils paramUtils = new ParamUtils(httpServletRequest);
		// 绑定案件信息
		QpTTPCase qpTTPCase = (QpTTPCase) paramUtils.generateObject(QpTTPCase.class, "qpTTPCase");
		// 经度
		String longitude = httpServletRequest.getParameter("longitude");
		qpTTPCase.setLongitude(longitude);
		// 纬度
		String latitude = httpServletRequest.getParameter("latitude");
		qpTTPCase.setLatitude(latitude);
		httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);  
		return SUCCESS;
	}

	/**
	 * 更新QpTTPCase信息
	 * 
	 * @return
	 */
	public String updateQpTTPCasePic() throws Exception { 
		QpTTPCaseId caseId=new QpTTPCaseId();
		caseId.setCaseId(qpTTPCase.getCaseId());
		QpTTPCase qpTTPCase=qpTTPCaseService.findQpTTPCaseByPK(caseId);
		// 已受理状态 wyq
		qpTTPCase.setTpHandleStatus(CaseHandleStatusEnum.FINISH.getCode());
		qpTTPCase.setDingsunTime(new Date());
		qpTTPCaseService.updateQpTTPCasePicGroupId(qpTTPCase);
		
		try {
			List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(qpTTPCase.getCaseId());
			if(qpTICAccidents != null && qpTICAccidents.size() > 0){
				for (QpTICAccident qpTICAccident : qpTICAccidents) {
					//查勘处理完毕后添加任务，上传图片 TODO
					//10 全责  11主要责任 12次要责任  13同等责任  14无责任    循环是为了确定- 有责任才报案 或者 单证上传
					String orgCode = CompanyCode.Company.getCompanyCode(qpTICAccident.getCoId());
					String comChannel = Constants.getREPORT_INTERFACE().get(orgCode+"_channel");
					logger.info("当事人保险公司编号:"+orgCode + "是否报案:"+("0".equals(comChannel)));
					if("0".equals(comChannel) && !"1".equals(qpTTPCase.getCaseType()) && "430100".equals(qpTTPCase.getCaseCity())){
						logger.info("报案人责任："+qpTICAccident.getDriverLiability());
							if(!"14".equals(qpTICAccident.getDriverLiability())){
									ReportCaseData reportCaseData = iReportCaseDataService.findReportCaseDataByCaseIdAndAccId(qpTTPCase.getCaseId(), qpTICAccident.getAcciId());
//									ReportTaskData reportTaskData = iReportTaskDataService.findReportTaskDataByAccId(qpTICAccident.getAcciId());
									logger.info("是否报案完成："+JSON.toJSONString(reportCaseData));
//									logger.info("是否正在报案中："+JSON.toJSONString(reportTaskData));
									if(reportCaseData != null){
											logger.info(new Date() + "报案:保险公司:"+orgCode + ",caseID:"+qpTTPCase.getCaseId() + ",accID:"+qpTICAccident.getAcciId());
											Date now = new Date();
											ReportTask reportTask = new ReportTask();
											reportTask.setId("CK"+reportCaseData.getTaskDataId());
											reportTask.setTaskDataId(reportCaseData.getTaskDataId());
											reportTask.setTaskType("3");
											reportTask.setCreateTime(now);
											iReportTaskService.add(reportTask);
											logger.info("--report--快赔点记录查勘单证上传,案件ID:"+qpTTPCase.getCaseId() + "，标的当事人id:"+ qpTICAccident.getAcciId() );
									}
							}
					}
				}
			}
		} catch (Exception e) {
			logger.error("--report--查勘处理完毕，新增查勘图片上传任务失败,案件ID:"+qpTTPCase.getCaseId() + ",异常信息:" + e);
		}
		
		return NONE;
	}

	public InputStream getPdfStream() {
		return pdfStream;
	}

	public void setPdfStream(InputStream pdfStream) {
		this.pdfStream = pdfStream;
	}

	/**
	 * 创建者删除自己的案件
	 * code 200 删除成功
	 * code 201 缺少案件id参数
	 * code 202 非法案件状态(处理之前的案件才能删除)
	 * code 203 案件创建者与删除操作者不是同一人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteByCreater() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (caseId != null) {
			QpTTPCaseId queryId = new QpTTPCaseId();
			queryId.setCaseId(caseId);
			try {
				qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(queryId);
				if (qpTTPCase == null) {
					resultMap.put("msg", WebEnum.DEL_CASE_SUCCESS.getMessage());
					resultMap.put("code", WebEnum.DEL_CASE_SUCCESS.getCode());
				} else {
					String caseStatus = qpTTPCase.getTpHandleStatus();
					if (CaseHandleStatusEnum.FOR_SUBMIT.getCode().equals(
							caseStatus)
							|| CaseHandleStatusEnum.FOR_JUDGE.getCode().equals(
									caseStatus)
									|| CaseHandleStatusEnum.FOR_VERIFY.getCode()
									.equals(caseStatus)) {
						String caseUserCode = qpTTPCase.getAssistorId();
						SessionUser currenUser = (SessionUser) this
								.getRequest().getSession()
								.getAttribute("SessionUser");
						String currenUserCode = currenUser.getUserCode();
						if (caseUserCode != null
								&& caseUserCode.equals(currenUserCode)) {
							logger.error("删除案件:"+JSONObject.toJSONString(qpTTPCase) + ",操作人："+getUserFromSession().getUserCode()+",操作时间:"+new Date());
							QpTICAccident qpTICAccident = new QpTICAccident();
							qpTICAccident.setCaseId(qpTTPCase.getCaseId());
							qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfo(qpTICAccident);
							if(qpTICAccidentList != null){
								for (int i = 0; i < qpTICAccidentList.size(); i++) {
									QpTICAccident qpTICAccident2 = qpTICAccidentList.get(i);
									qpTICAccident2.setValidStatus("0");
									qpTICAccidentService.updateQpTICAccident(qpTICAccident2);
								}
							}
							// 删除案件(不删除案件对应的当事人信息和图片信息提高效率,以后以脚本的形式定期清理数据库中无用的数据)
							qpTTPCase.setTpHandleStatus("6");
							qpTTPCaseService.updateQpTTPCase(qpTTPCase);
//							qpTTPCaseService.deleteCaseAndAccidentByPK(queryId);
							resultMap.put("msg",
									WebEnum.DEL_CASE_SUCCESS.getMessage());
							resultMap.put("code",
									WebEnum.DEL_CASE_SUCCESS.getCode());
						} else {
							resultMap.put("msg",
									WebEnum.DEL_CASE_ERROR_DEL_USER
									.getMessage());
							resultMap.put("code",
									WebEnum.DEL_CASE_ERROR_DEL_USER.getCode());
						}
					} else {
						resultMap
						.put("msg", WebEnum.DEL_CASE_ERROR_CASE_STATUS
								.getMessage());
						resultMap.put("code",
								WebEnum.DEL_CASE_ERROR_CASE_STATUS.getCode());
					}
				}
			} catch (Exception e) {
				resultMap.put("msg", WebEnum.DEL_CASE_ERROR.getMessage());
				resultMap.put("code", WebEnum.DEL_CASE_ERROR.getCode());
			}
		} else {
			resultMap.put("msg", WebEnum.DEL_CASE_EMPTY_CASE_ID.getMessage());
			resultMap.put("code", WebEnum.DEL_CASE_EMPTY_CASE_ID.getCode());
		}
		this.writeAjaxByMap(resultMap);
		return NONE;
	}
	

	public class UserInfoBean {
		private String name;
		private String sex;
		private String driverIDNumber;
		private String address;
		private String mobile;
		private String driverInsuNumber;
		private String driverVehicleType;
		private String vehicleNo;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getDriverIDNumber() {
			return driverIDNumber;
		}
		public void setDriverIDNumber(String driverIDNumber) {
			this.driverIDNumber = driverIDNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getDriverInsuNumber() {
			return driverInsuNumber;
		}
		public void setDriverInsuNumber(String driverInsuNumber) {
			this.driverInsuNumber = driverInsuNumber;
		}
		public String getDriverVehicleType() {
			return driverVehicleType;
		}
		public void setDriverVehicleType(String driverVehicleType) {
			this.driverVehicleType = driverVehicleType;
		}
		public String getVehicleNo() {
			return vehicleNo;
		}
		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}
	}
	
	public WxCaseService getWxCaseService() {
		return wxCaseService;
	}

	public void setWxCaseService(WxCaseService wxCaseService) {
		this.wxCaseService = wxCaseService;
	}

	public IQpTFLOWService getQpTFLOWService() {
		return qpTFLOWService;
	}

	public void setQpTFLOWService(IQpTFLOWService qpTFLOWService) {
		this.qpTFLOWService = qpTFLOWService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
		this.qpTTPCaseService = qpTTPCaseService;
	}

	public IQpTTPCaseService getQpTTPCaseService() {
		return qpTTPCaseService;
	}

	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(
			IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public IQpTICAccidentService getQpTICAccidentService() {
		return qpTICAccidentService;
	}

	public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
		this.qpTICAccidentService = qpTICAccidentService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}

	public void setQpTCOMProvinceService(
			IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}

	public IQpTCOMDistrictService getQpTCOMDistrictService() {
		return qpTCOMDistrictService;
	}

	public void setQpTCOMDistrictService(
			IQpTCOMDistrictService qpTCOMDistrictService) {
		this.qpTCOMDistrictService = qpTCOMDistrictService;
	}

	public IQpTICAccidentProjectService getQpTICAccidentProjectService() {
		return qpTICAccidentProjectService;
	}

	public void setQpTICAccidentProjectService(
			IQpTICAccidentProjectService qpTICAccidentProjectService) {
		this.qpTICAccidentProjectService = qpTICAccidentProjectService;
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
	
	public IQpTComHandlePoliceService getQpTComHandlePoliceService() {
		return qpTComHandlePoliceService;
	}

	public void setQpTComHandlePoliceService(IQpTComHandlePoliceService qpTComHandlePoliceService) {
		this.qpTComHandlePoliceService = qpTComHandlePoliceService;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/** QpTTPCase getter/setter **/
	public QpTTPCase getQpTTPCase() {
		return this.qpTTPCase;
	}

	public void setQpTTPCase(QpTTPCase qpTTPCase) {
		this.qpTTPCase = qpTTPCase;
	}

	/** QpTTPCaseId getter/setter **/
	public QpTTPCaseId getId() {
		return this.id;
	}

	public void setId(QpTTPCaseId id) {
		this.id = id;
	}

	public List<QpTICAccident> getQpTICAccidentList() {
		return qpTICAccidentList;
	}

	public void setQpTICAccidentList(List<QpTICAccident> qpTICAccidentList) {
		this.qpTICAccidentList = qpTICAccidentList;
	}

	public List<QpAcciLawDataVo> getQpAcciLawDataVoList() {
		return qpAcciLawDataVoList;
	}

	public void setQpAcciLawDataVoList(List<QpAcciLawDataVo> qpAcciLawDataVoList) {
		this.qpAcciLawDataVoList = qpAcciLawDataVoList;
	}

	public List<QpTTPLaw> getQpTTPLawList() {
		return qpTTPLawList;
	}

	public void setQpTTPLawList(List<QpTTPLaw> qpTTPLawList) {
		this.qpTTPLawList = qpTTPLawList;
	}

	public QpTICAcciPrintVo getQpTICAcciPrintVo() {
		return qpTICAcciPrintVo;
	}

	public void setQpTICAcciPrintVo(QpTICAcciPrintVo qpTICAcciPrintVo) {
		this.qpTICAcciPrintVo = qpTICAcciPrintVo;
	}

	public QpTTPCaseStatVO getQpTTPCaseStatVO() {
		return qpTTPCaseStatVO;
	}

	public void setQpTTPCaseStatVO(QpTTPCaseStatVO qpTTPCaseStatVO) {
		this.qpTTPCaseStatVO = qpTTPCaseStatVO;
	}

	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	private IQpTAsyncTaskService qpTAsyncTaskService;

	public IQpTAsyncTaskService getQpTAsyncTaskService() {
		return qpTAsyncTaskService;
	}

	public void setQpTAsyncTaskService(IQpTAsyncTaskService qpTAsyncTaskService) {
		this.qpTAsyncTaskService = qpTAsyncTaskService;
	}

	public IReportCaseDataService getiReportCaseDataService() {
		return iReportCaseDataService;
	}

	public void setiReportCaseDataService(IReportCaseDataService iReportCaseDataService) {
		this.iReportCaseDataService = iReportCaseDataService;
	}

	public IUmTAccidentUserService getUmTAccidentUserService() {
		return umTAccidentUserService;
	}

	public void setUmTAccidentUserService(IUmTAccidentUserService umTAccidentUserService) {
		this.umTAccidentUserService = umTAccidentUserService;
	}
	
	
}
