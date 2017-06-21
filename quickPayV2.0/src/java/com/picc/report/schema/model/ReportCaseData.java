package com.picc.report.schema.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 类名称：ReportCaseDataEntity
 * 描    述：成功表
 * 创建时间：2017-02-21
 */
@Entity
@Table(name = "report_case_data")
public class ReportCaseData implements Serializable {


	private static final long serialVersionUID = 1L;


	/**
	 * 主键 
	 */
	private String id;
	/**
	 * 关联id
	 */
	private String taskDataId ;
	/**
	 * 快处快赔案件编号 
	 */
	private String caseId ;
	/**
	 * 报案当事人id
	 */
	private String accId;
	/**
	 * 保险公司编号 
	 */
	private String orgCode ;
	/**
	 * 案件受理状态 : 已报案、已理算、已结案 
	 */
	private String reportCaseStatus ;
	/**
	 * 结案时间 
	 */
	private String endCaseTime ;
	/**
	 * 报案返回案件编号 
	 */
	private String reportNo ;
	/**
	 * 事故编号 
	 */
	private String damageId ;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 报案时间
	 */
	private Date reportData;
	
	/**
	 * 报案来源
	 */
	private String reportModo;
	
	/**
	 * 赔付次数
	 */
	private String caseTimes;
	
	/**
	 * 赔付结论 ： 1-赔付，2-零结，3-商业险拒赔，4-整案拒赔，5-注销
	 */
	private String indemnityConclusion;
	
	/**
	 * 保单列表	[{policyAttribute:0(商业险)/1(交抢险),polocyNo:xxxxxx}}]
	 */
	private String policyInfoList;
	
	private String payMentAmount;

	/**
	 * 获取：主键 
	 */
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "taskDataId")
	public String getTaskDataId() {
		return taskDataId;
	}
	public void setTaskDataId(String taskDataId) {
		this.taskDataId = taskDataId;
	}

	/**
	 * 获取：快处快赔案件编号 
	 */
	@Column(name = "caseId")
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置：快处快赔案件编号 
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "accId")
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	/**
	 * 获取：保险公司编号 
	 */
	@Column(name = "orgCode")
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置：保险公司编号 
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取：案件受理状态 : 已报案、已理算、已结案 
	 */
	@Column(name = "reportCaseStatus")
	public String getReportCaseStatus() {
		return reportCaseStatus;
	}

	/**
	 * 设置：案件受理状态 : 已报案、已理算、已结案 
	 */
	public void setReportCaseStatus(String reportCaseStatus) {
		this.reportCaseStatus = reportCaseStatus;
	}


	/**
	 * 获取：结案时间 
	 */
	@Column(name = "endCaseTime")
	public String getEndCaseTime() {
		return endCaseTime;
	}

	/**
	 * 设置：结案时间 
	 */
	public void setEndCaseTime(String endCaseTime) {
		this.endCaseTime = endCaseTime;
	}

	/**
	 * 获取：报案返回案件编号 
	 */
	@Column(name = "reportNo")
	public String getReportNo() {
		return reportNo;
	}

	/**
	 * 设置：报案返回案件编号 
	 */
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	/**
	 * 获取：事故编号 
	 */
	@Column(name = "damageId")
	public String getDamageId() {
		return damageId;
	}

	/**
	 * 设置：事故编号 
	 */
	public void setDamageId(String damageId) {
		this.damageId = damageId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "reportData")
	public Date getReportData() {
		return reportData;
	}
	public void setReportData(Date reportData) {
		this.reportData = reportData;
	}
	
	@Column(name = "reportModo")
	public String getReportModo() {
		return reportModo;
	}
	public void setReportModo(String reportModo) {
		this.reportModo = reportModo;
	}
	
	@Column(name = "caseTimes")
	public String getCaseTimes() {
		return caseTimes;
	}
	public void setCaseTimes(String caseTimes) {
		this.caseTimes = caseTimes;
	}
	
	@Column(name = "indemnityConclusion")
	public String getIndemnityConclusion() {
		return indemnityConclusion;
	}
	public void setIndemnityConclusion(String indemnityConclusion) {
		this.indemnityConclusion = indemnityConclusion;
	}
	
	@Column(name = "policyInfoList")
	public String getPolicyInfoList() {
		return policyInfoList;
	}
	public void setPolicyInfoList(String policyInfoList) {
		this.policyInfoList = policyInfoList;
	}
	@Column(name = "payMentAmount")
	public String getPayMentAmount() {
		return payMentAmount;
	}
	public void setPayMentAmount(String payMentAmount) {
		this.payMentAmount = payMentAmount;
	}

	
}
