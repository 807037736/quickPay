
package com.picc.qp.schema.vo;

import java.util.Date;



public class QpTTPCaseStatVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
    //案件ID
	private String caseId;
  	/** 案件编号 **/
	private String caseSerialNo;
  	/** CaseTime **/
	private Date caseTime;
  	/** 天气情况 **/
	private String caseWeather;
  	/** 省份 **/
	private String caseProvince;
  	/** 城市 **/
	private String caseCity;
  	/** 区县 **/
	private String caseDistrict;
  	/** 道路 **/
	private String caseRoad;
  	/** 街区 **/
	private String caseStreet;
  	/** 交警ID **/
	private String tpLoginId;
  	/** 交警姓名 **/
	private String tpUserName;
  	/** 警员编号 **/
	private String tpEmployeeId;
  	/** 交警处理结果 **/
	private String tpHandleStatus;
  	/** TPHandleTime **/
	private Date tpHandleTime;
  	/** 调解结果 **/
	private String tpHandleNotes;
  	/** 案件结果 **/
	private String caseResult;
  	/** 事故详情 **/
	private String caseNotes;
  	/** RelatedPersons **/
	private String relatedPersons;
  	/** 警员姓名 **/
	private String policeName;
  	/** 警员编号 **/
	private String policeEmployeeId;
  	/** 快处中心ID **/
	private String centerId;
	private String centerName;
  	/** SpanId **/
	private String spanId;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus;
	/**********************统计专业**********************/
	/** 日期*/
	private String casedDateT;
	/**时间区间*/
	private String caseTimeT;
	
	//案件时间起期
	private String  caseTimeStart;
	//案件时间止期
	private String  caseTimeEnd ;
	
	//交警处理止期
	private String TPHandleTimeStart;
	//交警处理止期
	private String TPHandleTimeEnd;
	
	//所属保险公司
	private String coId;
	private String coName;
	//法律法规
	private String lawId;
	public String getLawId() {
		return lawId;
	}
	public void setLowId(String lawId) {
		this.lawId = lawId;
	}
	public String getLawWords() {
		return lawWords;
	}
	public void setLawWords(String lawWords) {
		this.lawWords = lawWords;
	}
	private String lawWords;
	/**统计方式1*/
	private String  statType_1;
	/**统计方式2*/
	private String  statType_2;
	/**证件类型*/
	private String  driverIDType;
	/**证件类号码*/
	private String  driverIDNumber;
	/* 驾驶员名称*/
	private String  driverName;
	/*车牌号*/
	private String  driverVehicleNumber;
	
	//警员名称
	private String tPUserName;
	
	//估损时间起期
	private String  estimateLossTimeStart;
	//估损时间止期
	private String  estimateLossTimeEnd ;
	/** 查勘员代码 **/
    private String lossAssessorCode;

	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseSerialNo() {
		return caseSerialNo;
	}
	public void setCaseSerialNo(String caseSerialNo) {
		this.caseSerialNo = caseSerialNo;
	}
	public Date getCaseTime() {
		return caseTime;
	}
	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}
	public String getCaseWeather() {
		return caseWeather;
	}
	public void setCaseWeather(String caseWeather) {
		this.caseWeather = caseWeather;
	}
	public String getCaseProvince() {
		return caseProvince;
	}
	public void setCaseProvince(String caseProvince) {
		this.caseProvince = caseProvince;
	}
	public String getCaseCity() {
		return caseCity;
	}
	public void setCaseCity(String caseCity) {
		this.caseCity = caseCity;
	}
	public String getCaseDistrict() {
		return caseDistrict;
	}
	public void setCaseDistrict(String caseDistrict) {
		this.caseDistrict = caseDistrict;
	}
	public String getCaseRoad() {
		return caseRoad;
	}
	public void setCaseRoad(String caseRoad) {
		this.caseRoad = caseRoad;
	}
	public String getCaseStreet() {
		return caseStreet;
	}
	public void setCaseStreet(String caseStreet) {
		this.caseStreet = caseStreet;
	}
	public String getTpLoginId() {
		return tpLoginId;
	}
	public void setTpLoginId(String tpLoginId) {
		this.tpLoginId = tpLoginId;
	}
	public String getTpUserName() {
		return tpUserName;
	}
	public void setTpUserName(String tpUserName) {
		this.tpUserName = tpUserName;
	}
	public String getTpEmployeeId() {
		return tpEmployeeId;
	}
	public void setTpEmployeeId(String tpEmployeeId) {
		this.tpEmployeeId = tpEmployeeId;
	}
	public String getTpHandleStatus() {
		return tpHandleStatus;
	}
	public void setTpHandleStatus(String tpHandleStatus) {
		this.tpHandleStatus = tpHandleStatus;
	}
	public Date getTpHandleTime() {
		return tpHandleTime;
	}
	public void setTpHandleTime(Date tpHandleTime) {
		this.tpHandleTime = tpHandleTime;
	}
	public String getTpHandleNotes() {
		return tpHandleNotes;
	}
	public void setTpHandleNotes(String tpHandleNotes) {
		this.tpHandleNotes = tpHandleNotes;
	}
	public String getCaseResult() {
		return caseResult;
	}
	public void setCaseResult(String caseResult) {
		this.caseResult = caseResult;
	}
	public String getCaseNotes() {
		return caseNotes;
	}
	public void setCaseNotes(String caseNotes) {
		this.caseNotes = caseNotes;
	}
	public String getRelatedPersons() {
		return relatedPersons;
	}
	public void setRelatedPersons(String relatedPersons) {
		this.relatedPersons = relatedPersons;
	}
	public String getPoliceName() {
		return policeName;
	}
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	public String getPoliceEmployeeId() {
		return policeEmployeeId;
	}
	public void setPoliceEmployeeId(String policeEmployeeId) {
		this.policeEmployeeId = policeEmployeeId;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getSpanId() {
		return spanId;
	}
	public void setSpanId(String spanId) {
		this.spanId = spanId;
	}
	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	public Date getInsertTimeForHis() {
		return insertTimeForHis;
	}
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	public String getUpdaterCode() {
		return updaterCode;
	}
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	public Date getOperateTimeForHis() {
		return operateTimeForHis;
	}
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getCasedDateT() {
		return casedDateT;
	}
	public void setCasedDateT(String casedDateT) {
		this.casedDateT = casedDateT;
	}
	public String getCaseTimeT() {
		return caseTimeT;
	}
	public void setCaseTimeT(String caseTimeT) {
		this.caseTimeT = caseTimeT;
	}
	public String getCaseTimeStart() {
		return caseTimeStart;
	}
	public void setCaseTimeStart(String caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}
	public String getCaseTimeEnd() {
		return caseTimeEnd;
	}
	public void setCaseTimeEnd(String caseTimeEnd) {
		this.caseTimeEnd = caseTimeEnd;
	}
	public String getTPHandleTimeStart() {
		return TPHandleTimeStart;
	}
	public void setTPHandleTimeStart(String tPHandleTimeStart) {
		TPHandleTimeStart = tPHandleTimeStart;
	}
	public String getTPHandleTimeEnd() {
		return TPHandleTimeEnd;
	}
	public void setTPHandleTimeEnd(String tPHandleTimeEnd) {
		TPHandleTimeEnd = tPHandleTimeEnd;
	}
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getStatType_1() {
		return statType_1;
	}
	public void setStatType_1(String statType_1) {
		this.statType_1 = statType_1;
	}
	public String getStatType_2() {
		return statType_2;
	}
	public void setStatType_2(String statType_2) {
		this.statType_2 = statType_2;
	}
	public String getDriverIDType() {
		return driverIDType;
	}
	public void setDriverIDType(String driverIDType) {
		this.driverIDType = driverIDType;
	}
	public String getDriverIDNumber() {
		return driverIDNumber;
	}
	public void setDriverIDNumber(String driverIDNumber) {
		this.driverIDNumber = driverIDNumber;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverVehicleNumber() {
		return driverVehicleNumber;
	}
	public void setDriverVehicleNumber(String driverVehicleNumber) {
		this.driverVehicleNumber = driverVehicleNumber;
	}
	public String getTPUserName() {
		return tPUserName;
	}
	public void setTPUserName(String tPUserName) {
		this.tPUserName = tPUserName;
	}
	public String getEstimateLossTimeStart() {
		return estimateLossTimeStart;
	}
	public void setEstimateLossTimeStart(String estimateLossTimeStart) {
		this.estimateLossTimeStart = estimateLossTimeStart;
	}
	public String getEstimateLossTimeEnd() {
		return estimateLossTimeEnd;
	}
	public void setEstimateLossTimeEnd(String estimateLossTimeEnd) {
		this.estimateLossTimeEnd = estimateLossTimeEnd;
	}
	public String gettPUserName() {
		return tPUserName;
	}
	public void settPUserName(String tPUserName) {
		this.tPUserName = tPUserName;
	}
	public String getLossAssessorCode() {
		return lossAssessorCode;
	}
	public void setLossAssessorCode(String lossAssessorCode) {
		this.lossAssessorCode = lossAssessorCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterIName(String centerName) {
		this.centerName = centerName;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	
}