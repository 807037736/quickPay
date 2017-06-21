/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.enums.InfoEnum;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICAccidentProject;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentProjectService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.um.schema.vo.SessionUser;
import com.sinosoft.sysframework.common.datatype.DateTime;
import com.sinosoft.sysframework.common.util.ParamUtils;


public class QpTICAccidentAction extends Struts2Action{

	private IQpTICAccidentService qpTICAccidentService;

	private IQpTICAccidentProjectService qpTICAccidentProjectService;	

	private IQpTCommonService qpTCommonService;

	private IQpTICCompanyService qpTICCompanyService;

	private IQpTTPLawService qpTTPLawService;

	private IQpTTPCaseService qpTTPCaseService;

	private IQpTCOMProvinceService qpTCOMProvinceService;

	private IQpTTPFastCenterService qpTTPFastCenterService;

	private IQpTCOMCityService qpTCOMCityService;


	private String tpHandleStatus;


	private String qpAcciId;


	public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
		this.qpTICAccidentService = qpTICAccidentService;
	}

	public IQpTICAccidentService getQpTICAccidentService() {
		return qpTICAccidentService;
	}

	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public IQpTTPCaseService getQpTTPCaseService() {
		return qpTTPCaseService;
	}

	public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
		this.qpTTPCaseService = qpTTPCaseService;
	}

	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}

	public void setQpTCOMProvinceService(IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}



	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}


	public IQpTICAccidentProjectService getQpTICAccidentProjectService() {
		return qpTICAccidentProjectService;
	}

	public void setQpTICAccidentProjectService(IQpTICAccidentProjectService qpTICAccidentProjectService) {
		this.qpTICAccidentProjectService = qpTICAccidentProjectService;
	}

	public String getQpAcciId() {
		return qpAcciId;
	}

	public void setQpAcciId(String qpAcciId) {
		this.qpAcciId = qpAcciId;
	}


	public String getTpHandleStatus() {
		return tpHandleStatus;
	}

	public void setTpHandleStatus(String tpHandleStatus) {
		this.tpHandleStatus = tpHandleStatus;
	}


	private QpTICAccident qpTICAccident;

	private QpTICAccidentId id;

	/** 操作类型 **/
	private String operateType;
	/** QpTICAccident getter/setter **/
	public QpTICAccident getQpTICAccident() {
		return this.qpTICAccident;
	}

	public void setQpTICAccident(QpTICAccident qpTICAccident) {
		this.qpTICAccident = qpTICAccident;
	}
	/** QpTICAccidentId getter/setter **/
	public QpTICAccidentId getId() {
		return this.id;
	}

	public void setId(QpTICAccidentId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/** 主键对象 */
	private String acciId;
	public String getAcciId() {
		return this.acciId;
	}

	public void setAcciId(String acciId) {
		this.acciId = acciId;
	}



	/****************************Function Start*******************************/



	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {

		return SUCCESS;
	}



	/**
	 * QpTICAccident查询，根据qpTICAccident带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = qpTICAccidentService.findQpTICAccidentPageBySql(qpTICAccident, page, rows);
			this.writeEasyUiDataBigDecimal(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTICAccident信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
		return SUCCESS;
	}

	/**
	 * 更新QpTICAccident信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTICAccidentService.updateQpTICAccident(qpTICAccident);
		return SUCCESS;
	}

	/**
	 * 准备增加QpTICAccident信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {

		HttpServletRequest httpServletRequest = this.getRequest();

		// 当事人流水号
		String qpAcciId = httpServletRequest.getParameter("qpAcciId");
		// 操作类型 add|edit|view
		String actionType = httpServletRequest.getParameter("actionType");
		// 业务类型： 1-估损
		httpServletRequest.setAttribute("businessType", httpServletRequest.getParameter("businessType"));

		httpServletRequest.setAttribute("tpHandleStatus", httpServletRequest.getParameter("tpHandleStatus"));


		// 身份证类型
		List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.IDENTIFY.getCode());
		httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);

		// 保险公司
		List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
		httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);

		// 车辆类型
		List<QpSelectDataVo> vehicleTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.VEHICLE.getCode());
		httpServletRequest.setAttribute("vehicleTypeList", vehicleTypeList);

		// 准驾车型
		List<QpSelectDataVo> permissionDriveTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.PERMISSION_DRIVE.getCode());
		httpServletRequest.setAttribute("permissionDriveTypeList", permissionDriveTypeList);

		// 方向类型
		List<QpSelectDataVo> directionTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.DIRECTION.getCode());
		httpServletRequest.setAttribute("directionTypeList", directionTypeList);

		// 责任类型
		List<QpSelectDataVo> responsibilityTypeList =  qpTCommonService.getSelectData(SelectTypeEnum.RESPONSIBILITY.getCode());
		httpServletRequest.setAttribute("responsibilityTypeList", responsibilityTypeList);

		// 违反法律法规
		List<QpTTPLaw> qpTTPLawList = qpTTPLawService.findByQpTTPLaw(new QpTTPLaw());
		httpServletRequest.setAttribute("qpTTPLawList", qpTTPLawList);

		if("view".equals(actionType)) { // 查看:查询完直接返回
			// 当事人信息
			QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
			qpTICAccidentId.setAcciId(qpAcciId); // 当事人流水号
			QpTICAccident qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);
			if(qpTICAccident != null) {
				// 案件信息
				QpTTPCase qpTTPCase = new QpTTPCase();
				QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
				qpTTPCaseId.setCaseId(qpTICAccident.getCaseId());
				qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
				httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);
			}
			// 查询当事人信息
			httpServletRequest.setAttribute("qpTICAccident", qpTICAccident);
			httpServletRequest.setAttribute("actionType", "view");
			return SUCCESS;
		}

		// 获取案件信息
		ParamUtils paramUtils = new ParamUtils(httpServletRequest);
		// 绑定案件信息
		QpTTPCase qpTTPCase = (QpTTPCase) paramUtils.generateObject(QpTTPCase.class, "qpTTPCase");
		// 案件发生时间
		String caseTime = httpServletRequest.getParameter("qpTTPCaseCaseTime");
		qpTTPCase.setCaseTime(new DateTime(caseTime,DateTime.YEAR_TO_MINUTE));
		// 案件处理时间
		String handleTime  = httpServletRequest.getParameter("qpTTPCaseTpHandleTime");
		qpTTPCase.setTpHandleTime(new DateTime(handleTime,DateTime.YEAR_TO_MINUTE));

		qpTTPCase.setCaseStreet(httpServletRequest.getParameter("case_street"));// 街道
		httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);

		if(ToolsUtils.isEmpty(qpAcciId)) {   // 新增初始化
			QpTICAccident qpTICAccident = new QpTICAccident();
			qpTICAccident.setDriverSex("0"); // 新增默认 0-男

			SessionUser sessionUser = getUserFromSession();
			//qpTICAccident.setAcciProvince(sessionUser.getProvince()); // 新增默认省份
			//qpTICAccident.setAcciCity(sessionUser.getCity());         // 新增默认城市

			// 省份车牌前缀
			String province = sessionUser.getProvince();
			String provinceAdd = httpServletRequest.getParameter("qpTTPCaseCaseProvince");
			if(ToolsUtils.notEmpty(province) || ToolsUtils.notEmpty(provinceAdd)) {
				QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
				qpTCOMProvinceId.setProvId(provinceAdd);
				QpTCOMProvince qpTCOMProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(qpTCOMProvinceId);
				if(qpTCOMProvince != null) {
					httpServletRequest.setAttribute("provVehicleNumPre", qpTCOMProvince.getProvVehicleNumPre());
				}else {
					httpServletRequest.setAttribute("provVehicleNumPre", null);
				}
			}else {
				httpServletRequest.setAttribute("provVehicleNumPre", null);
			}
			// 城市车牌前缀
			String city = sessionUser.getCity();
			String cityAdd = httpServletRequest.getParameter("qpTTPCaseCaseCity");
			if(ToolsUtils.notEmpty(city) || ToolsUtils.notEmpty( cityAdd)) {
				QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
				qpTCOMCityId.setCityId(cityAdd);
				QpTCOMCity qpTCOMCity = qpTCOMCityService.findQpTCOMCityByPK(qpTCOMCityId);
				if(qpTCOMCity != null) {
					httpServletRequest.setAttribute("cityVehicleNumPre", qpTCOMCity.getCityVehicleNumPre());
				}else {
					httpServletRequest.setAttribute("cityVehicleNumPre", null);
				}
			}else {
				httpServletRequest.setAttribute("cityVehicleNumPre", null);
			}  
			httpServletRequest.setAttribute("actionType", "add");
			httpServletRequest.setAttribute("qpTICAccident", qpTICAccident);

			qpTICAccident.setAcciProvince(httpServletRequest.getParameter("qpTTPCaseCaseProvince")); // 省
			qpTICAccident.setAcciCity(httpServletRequest.getParameter("qpTTPCaseCaseCity"));         // 市
			qpTICAccident.setAcciDistrict(httpServletRequest.getParameter("qpTTPCaseCaseDistrict")); // 区
			// qpTICAccident.setAcciRoad(httpServletRequest.getParameter("qpTTPCaseCaseRoad"));         // 主干道
			qpTICAccident.setAcciStreet(httpServletRequest.getParameter("qpTTPCaseCaseStreet"));     // 街道


		}else {                              // 编辑初始化
			QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
			qpTICAccidentId.setAcciId(qpAcciId);
			httpServletRequest.setAttribute("qpTICAccident", qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId));
		}

		return SUCCESS;
	}

	/**
	 * 删除当事人
	 * 
	 * @return
	 */
	public String deleteTICAccident() {

		Map<String, Object> msg = new HashMap<String, Object>();

		try {
			String qpAcciId = this.getRequest().getParameter("qpAcciId");
			if(ToolsUtils.isEmpty(qpAcciId)) {
				throw new RuntimeException("处理失败,关系人ID为空！");
			}
			QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
			qpTICAccidentId.setAcciId(qpAcciId);
			qpTICAccidentService.deleteByPK(qpTICAccidentId);

			setMsg(msg, "Y", "当事人删除处理成功！", null);

			this.writeJson(msg);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			setMsg(msg, "N", "处理失败,原因:" + e.getMessage(), null);
			this.writeJson(msg);
		}
		return NONE;
	}

	/**
	 * 保存\更新当事人信息
	 * 
	 * @return
	 */
	public String saveCase() {
		Map<String, Object> msg = new HashMap<String, Object>();
		HttpServletRequest httpServletRequest = this.getRequest();
		try {
			ParamUtils paramUtils = new ParamUtils(httpServletRequest);
			// 绑定当事人信息
			QpTICAccident qpTICAccident = (QpTICAccident) paramUtils.generateObject(QpTICAccident.class,"qpTICAccident");
			// 绑定案件信息
			QpTTPCase qpTTPCase = (QpTTPCase) paramUtils.generateObject(QpTTPCase.class, "qpTTPCase");
			// 案件发生时间
			String caseTime = httpServletRequest.getParameter("qpTTPCaseCaseTime");
			qpTTPCase.setCaseTime(new DateTime(caseTime,DateTime.YEAR_TO_MINUTE));

			qpTICAccident.setAcciTime(new DateTime(caseTime,DateTime.YEAR_TO_MINUTE));


			// 案件处理时间
			//            String handleTime  = httpServletRequest.getParameter("qpTTPCaseTpHandleTime");
			qpTTPCase.setTpHandleTime(new Date());
			// 更新者代码（当前用户代码）
			qpTTPCase.setUpdaterCode(getUserFromSession().getUserCode());
			qpTICAccident.setUpdaterCode(getUserFromSession().getUserCode());
			// 保存\者当事人
			qpTTPCase.setCreatorCode(getUserFromSession().getUserCode());
			qpTTPCase.setInsertTimeForHis(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
			qpTTPCase.setValidStatus("1");

			//保存警员
			SessionUser sessionUser = getUserFromSession();
			qpTTPCase.setTpEmployeeId(sessionUser.getEmployeeId());     // 警员编号（同用户代码）
			qpTTPCase.setPoliceName(sessionUser.getUserName());         // 警员名称（同用户名称）
			qpTTPCase.setPoliceEmployeeId(sessionUser.getEmployeeId()); // 警员编号（同用户代码）

			//协助人员为当前登录用户
			qpTTPCase.setAssistorId(sessionUser.getUserCode());
			qpTTPCase.setAssistorName(sessionUser.getUserName());

			QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
			qpTTPFastCenterId.setCenterId(qpTTPCase.getCenterId());
			QpTTPFastCenter qpTTPFastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(qpTTPFastCenterId);

			if("2".equals(qpTTPFastCenter.getCities()) ){
				qpTTPCase.setCaseType("1");
			}else if("1".equals(qpTTPFastCenter.getCities()) ){
				qpTTPCase.setCaseType("0");
			}else {
				setMsg(msg, "N", "当事人保存失败,该快赔点所属地市未分配<br>请重新添加或修改快赔点所属地市", null);
				this.writeJson(msg);
				return NONE;
			}

			//            String userSort = this.getUserFromSession().getUserSort();
			//            if(userSort != null && !"".equals(userSort) && ("07".equals(userSort) || "08".equals(userSort)) ){
			//        	qpTTPCase.setCaseType("1");
			//            }else {
			//  		qpTTPCase.setCaseType("0");
			//      	    }
			Map<String,String> resultMap = qpTICAccidentService.saveQpTICAccident(qpTTPCase, qpTICAccident);
			setMsg(msg, "Y", "当事人保存处理成功！", resultMap);
			this.writeJson(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			setMsg(msg, "N", "处理失败,原因:" + e.getMessage(), null);
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
	private void setMsg(Map<String, Object> msg, String flag, String content , Map<String,String> result) {
		msg.put("flag", flag);
		msg.put("content", content);
		msg.put("result", result);
	}


	/**
	 * 删除QpTICAccident信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTICAccidentService.deleteByPK(id);
			}
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
	 * 查看QpTICAccident信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
		return SUCCESS;
	}

	/**
	 * 查询历史当事人信息
	 * @return
	 */
	public String queryAcciDriverInfo() {

		HttpServletRequest httpServletRequest = this.getRequest();

		Map<String, Object> msg = new HashMap<String, Object>();

		try {
			// 证件类型
			String driverIDType = httpServletRequest.getParameter("driverIDType");
			// 身份证号
			String driverIDNumber = httpServletRequest.getParameter("driverIDNumber");
			if(ToolsUtils.isEmpty(driverIDType) || ToolsUtils.isEmpty(driverIDNumber)) {
				setMsg(msg, "N", "身份证类型或者身份证号为空！", null);
				this.writeJson(msg);
				return NONE;
			}
			QpTICAccident qpTICAccident = new QpTICAccident();
			qpTICAccident.setDriverIDType(driverIDType);
			qpTICAccident.setDriverIDNumber(driverIDNumber);
			List<QpTICAccident> qpTICAccidentList = qpTICAccidentService.queryAcciDriverInfo(qpTICAccident);
			if(ToolsUtils.isEmpty(qpTICAccidentList)) {
				qpTICAccident.setRiskTimes("0");
				setMsg(msg, "N", "没找到历史当事人信息！", null);
			}else {
				setMsg(msg, "Y", "找到历史当事人信息！", null);
				QpTICAccident qpTICAccident2 = qpTICAccidentList.get(0);
				msg.put("driverInfo", qpTICAccident2);
				qpTICAccident2.setRiskTimes(String.valueOf(qpTICAccidentList.size()));
			}
			this.writeJson(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			setMsg(msg, "N", "处理失败,原因:" + e.getMessage(), null);
			this.writeJson(msg);
		}
		return NONE;
	}


	/**
	 * 准备增加估损信息 wyq
	 * 
	 * @return
	 */
	public String prepareAssessorAdd() throws Exception {
		QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
		qpTICAccidentId.setAcciId(this.getRequest().getParameter("qpAcciId"));
		this.getRequest().setAttribute("qpTICAccident", qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId));
		String cid =this.getRequest().getParameter("cid");
		if(!cid.isEmpty()){
			this.getRequest().setAttribute("cid", cid);
		}
		QpTICAccidentProject qpTICAccidentProject = new QpTICAccidentProject();
		qpTICAccidentProject.setAcciId(this.getRequest().getParameter("qpAcciId"));
		qpTICAccidentProject.setCaseId(cid);
		qpTICAccidentProject.setType("1");
		List<QpTICAccidentProject> qpTICAccidentReplaceProject = qpTICAccidentProjectService.findQpTICAccidentProjectByPK(qpTICAccidentProject);
		this.getRequest().setAttribute("replaceProject", qpTICAccidentReplaceProject);
		qpTICAccidentProject.setType("2");
		List<QpTICAccidentProject> qpTICAccidentRepairProject = qpTICAccidentProjectService.findQpTICAccidentProjectByPK(qpTICAccidentProject);
		this.getRequest().setAttribute("repairProject", qpTICAccidentRepairProject);
		return SUCCESS;
	}


	/**
	 * 保存估损信息 wyq
	 * 
	 * @return
	 */
	public String saveAssessorInfo() {
		HttpServletRequest httpServletRequest = this.getRequest();
		Map<String, Object> msg = new HashMap<String, Object>();
		try {
			String qpAcciId = httpServletRequest.getParameter("qpTICAccidentAcciId");
			QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
			qpTICAccidentId.setAcciId(qpAcciId);
			QpTICAccident qpTICAccidentUpdate = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);

			//保存估损人员
			//        	 String cid=httpServletRequest.getParameter("cid");
			String cid =this.getRequest().getParameter("cid");
			String accidentId =this.getRequest().getParameter("qpTICAccidentAcciId");
			if(!cid.isEmpty()){ 
				QpTTPCaseId caseId=new QpTTPCaseId();
				caseId.setCaseId(cid);
				QpTTPCase qpTTPCase=qpTTPCaseService.findQpTTPCaseByPK(caseId);
				SessionUser sessionUser = getUserFromSession();
				qpTTPCase.setEstimaterId(sessionUser.getUsercode());
				qpTTPCase.setEstimaterName(sessionUser.getUserName());
				//查勘估损保存后把状态改成  查勘处理中 状态 wyq
				qpTTPCase.setTpHandleStatus("4");
				qpTTPCaseService.updateQpTTPCase(qpTTPCase);

				qpTICAccidentProjectService.deleteByCaseId(cid, accidentId);
				logger.info("查勘估损项目信息-删除旧数据：cid:"+cid+",accidentId:"+accidentId);
				String [] replaceProjects = httpServletRequest.getParameterValues("replaceProjects");//更换项目
				String [] replaceProjectsMoney = httpServletRequest.getParameterValues("replaceProjectsMoney");//更换金额
				String [] repairProjects = httpServletRequest.getParameterValues("repairProjects");//修理项目
				String [] repairProjectsMoney = httpServletRequest.getParameterValues("repairProjectsMoney");//修理金额
				logger.info("查勘估损项目信息replaceProjects："+JSONObject.toJSONString(replaceProjects));
				logger.info("查勘估损项目信息replaceProjectsMoney："+JSONObject.toJSONString(replaceProjectsMoney));
				logger.info("查勘估损项目信息repairProjects："+JSONObject.toJSONString(repairProjects));
				logger.info("查勘估损项目信息repairProjectsMoney："+JSONObject.toJSONString(repairProjectsMoney));
				
				if(replaceProjects != null){
					for (int i = 0; i < replaceProjects.length; i++) {
						if (StringUtils.isNotEmpty(replaceProjects[0]) && StringUtils.isNotEmpty(replaceProjectsMoney[0])) {
							QpTICAccidentProject qpTICAccidentProject = new QpTICAccidentProject();
							qpTICAccidentProject.setCaseId(cid);
							qpTICAccidentProject.setAcciId(qpAcciId);
							qpTICAccidentProject.setAccidentProject(replaceProjects[i].toString());
							qpTICAccidentProject.setAccidentProjectMoney(Double.parseDouble(replaceProjectsMoney[i].toString()));
							qpTICAccidentProject.setType("1");
							qpTICAccidentProject.setCreateTime(new Date());
							qpTICAccidentProjectService.saveQpTICAccidentProject(qpTICAccidentProject);
							logger.info("查勘估损项目信息-更换保存成功:"+JSONObject.toJSONString(qpTICAccidentProject));
						}
					}
				}

				if(repairProjects != null){
					for (int i = 0; i < repairProjects.length; i++) {
						if(StringUtils.isNotEmpty(repairProjects[0]) && StringUtils.isNotEmpty(repairProjectsMoney[0])){
							QpTICAccidentProject qpTICAccidentProject = new QpTICAccidentProject();
							qpTICAccidentProject.setCaseId(cid);
							qpTICAccidentProject.setAcciId(qpAcciId);
							qpTICAccidentProject.setAccidentProject(repairProjects[i].toString());
							qpTICAccidentProject.setAccidentProjectMoney(Double.parseDouble(repairProjectsMoney[i].toString()));
							qpTICAccidentProject.setType("2");
							qpTICAccidentProject.setCreateTime(new Date());
							qpTICAccidentProjectService.saveQpTICAccidentProject(qpTICAccidentProject);
							logger.info("查勘估损项目信息-修理保存成功:"+JSONObject.toJSONString(qpTICAccidentProject));
						}
					}
				}
			}

			// 估损信息
			BigDecimal qpTICAccidentEstimateLossSum=new BigDecimal(httpServletRequest.getParameter("qpTICAccidentEstimateLossSum"));
			qpTICAccidentUpdate.setEstimateLossSum(qpTICAccidentEstimateLossSum);
			if(ToolsUtils.notEmpty(httpServletRequest.getParameter("qpTICAccidentFixedLossPrice"))) {
				BigDecimal fixedLossPrice=new BigDecimal(httpServletRequest.getParameter("qpTICAccidentFixedLossPrice"));
				qpTICAccidentUpdate.setFixedLossPrice(fixedLossPrice);
			}
//			qpTICAccidentUpdate.setChassisNumber(httpServletRequest.getParameter("qpTICAccidentChassisNumber"));
			qpTICAccidentUpdate.setEstimateLossTime(new Date());
			qpTICAccidentUpdate.setLossAssessorCode(this.getUserFromSession().getUserCode());
			qpTICAccidentUpdate.setSurveyNotes(httpServletRequest.getParameter("surveyNotes"));

			qpTICAccidentService.updateQpTICAccident(qpTICAccidentUpdate);
			setMsg(msg, "Y", "估损信息保存成功！", null);
			this.writeJson(msg);
		} catch (Exception e) {
			logger.error("估损信息保存失败", e);
			setMsg(msg, "N", "处理失败,原因:" + e.getMessage(), null);
			this.writeJson(msg);
		}
		return NONE;
	}

	/**
	 * 查询当事人信息
	 * @return
	 * @throws Exception 
	 */
	public String queryAcciDriverstat() throws Exception {

		HttpServletRequest httpServletRequest = this.getRequest();


		// 身份证类型
		List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData("IdentifyType");
		httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);

		Integer liability_RISKTIME=0;
		BigDecimal liability_ESTIMATELOSSSUM = new BigDecimal(0);
		BigDecimal liability_FIXEDLOSSPRICE = new BigDecimal(0);;
		Integer noliability_RISKTIME=0;
		BigDecimal noliability_ESTIMATELOSSSUM = new BigDecimal(0);;
		BigDecimal noliability_FIXEDLOSSPRICE = new BigDecimal(0);;
		// 证件类型
		String driverIDType = httpServletRequest.getParameter("qpTTPCase.driverIDType");
		// 身份证号
		String driverIDNumber = httpServletRequest.getParameter("qpTTPCase.driverIDNumber");
		//车牌号
		String driverVehicleNumber = httpServletRequest.getParameter("qpTTPCase.driverVehicleNumber");
		if(driverIDNumber==null&&driverVehicleNumber==null){
			return SUCCESS;
		}else{

			QpTICAccident qpTICAccident = new QpTICAccident();
			if(!driverIDType.isEmpty()&&!driverIDNumber.isEmpty()){
				qpTICAccident.setDriverIDType(driverIDType);
				qpTICAccident.setDriverIDNumber(driverIDNumber);
			}
			if(!driverVehicleNumber.isEmpty()){
				qpTICAccident.setDriverVehicleNumber(driverVehicleNumber);
			}

			List<QpTICAccident> qpTICAccidentList = qpTICAccidentService.queryAcciDriverInfo(qpTICAccident);
			for(int i=0;i<qpTICAccidentList.size();i++){   
				qpTICAccident  = qpTICAccidentList.get(i);   
				if(!(qpTICAccident.getDriverLiability()==null)&&qpTICAccident.getDriverLiability()=="14"){
					noliability_RISKTIME+=1;
					if(qpTICAccident.getEstimateLossSum()==null){
						qpTICAccident.setEstimateLossSum(new BigDecimal("0"));

					}
					//noliability_ESTIMATELOSSSUM+=qpTICAccident.getEstimateLossSum();
					noliability_ESTIMATELOSSSUM = noliability_ESTIMATELOSSSUM.add(qpTICAccident.getEstimateLossSum());
					if(qpTICAccident.getFixedLossPrice()==null){
						qpTICAccident.setFixedLossPrice(new BigDecimal("0"));

					}
					//noliability_FIXEDLOSSPRICE+=qpTICAccident.getFixedLossPrice();
					noliability_FIXEDLOSSPRICE = noliability_FIXEDLOSSPRICE.add(qpTICAccident.getFixedLossPrice());

				}else if(!(qpTICAccident.getDriverLiability()==null)&&!"".equals(qpTICAccident.getDriverLiability())){
					liability_RISKTIME+=1;
					if(qpTICAccident.getEstimateLossSum()==null){
						qpTICAccident.setEstimateLossSum(new BigDecimal("0"));
					}
					//liability_ESTIMATELOSSSUM+=qpTICAccident.getEstimateLossSum();
					liability_ESTIMATELOSSSUM = liability_ESTIMATELOSSSUM.add(qpTICAccident.getEstimateLossSum());
					if(qpTICAccident.getFixedLossPrice()==null){
						qpTICAccident.setFixedLossPrice(new BigDecimal("0"));
					}
					//liability_FIXEDLOSSPRICE+=qpTICAccident.getFixedLossPrice();  
					liability_FIXEDLOSSPRICE.add(qpTICAccident.getFixedLossPrice());
				};
				this.getRequest().setAttribute("noliability_RISKTIME",noliability_RISKTIME);
				this.getRequest().setAttribute("liability_RISKTIME",liability_RISKTIME);
				this.getRequest().setAttribute("noliability_ESTIMATELOSSSUM",noliability_ESTIMATELOSSSUM);
				this.getRequest().setAttribute("noliability_FIXEDLOSSPRICE",noliability_FIXEDLOSSPRICE);
				this.getRequest().setAttribute("liability_ESTIMATELOSSSUM",liability_ESTIMATELOSSSUM);
				this.getRequest().setAttribute("liability_FIXEDLOSSPRICE",liability_FIXEDLOSSPRICE);
				this.getRequest().setAttribute("driverIDNumber",driverIDNumber);
				this.getRequest().setAttribute("driverIDType",driverIDType);
				this.getRequest().setAttribute("driverVehicleNumber",driverVehicleNumber);
			}   
			return SUCCESS;
		}
	}	

	/**
	 * 身份证号48小时内是否重复出险
	 * 200  查询成功(msg为空表示48小时内无出险记录,否则为重复出险)
	 * 201  身份证为空
	 * 203  程序异常
	 * @return
	 */
	public String queryAcciIdBefore() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String number = this.getRequest().getParameter("number");
		if (number == null || "".equals(number.trim())) {
			resultMap.put("msg", "参数错误");
			resultMap.put("code", "201");
		} else {
			try {
				Integer count = qpTICAccidentService.queryAcciDriverInfoCount(number);
				resultMap.put("msg", count > 0 ? InfoEnum.SAVE_ACCIDENT_REPEAT.getMessage() : "");
				resultMap.put("code", "200");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				resultMap.put("msg", "程序异常");
				resultMap.put("code", "202");
			}
		}
		this.writeAjaxByMap(resultMap);
		return NONE;
	}

	/*
	 * 影响定损页面跳转
	 * 
	 */
	public String viewPhotography() throws Exception {
		System.out.println("+++++++++++++影响模式开启++++++++++++++++++++++++++++");
		QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
		qpTICAccidentId.setAcciId(this.getRequest().getParameter("qpAcciId"));
		this.getRequest().setAttribute("qpTICAccident", qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId));
		//	    System.out.print(qpTICAccident.getGroupId());
		String cid =this.getRequest().getParameter("cid");
		System.out.println("+++++++++++++影响模式开启++++++++++++++++++++++++++++");
		if(!cid.isEmpty()){
			this.getRequest().setAttribute("cid", cid);
		}
		return SUCCESS;
	}
}
