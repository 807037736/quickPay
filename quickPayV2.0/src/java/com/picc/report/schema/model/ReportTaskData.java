package com.picc.report.schema.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 类名称：ReportTaskDataEntity
 * 描    述：数据表
 * 创建时间：2017-02-21
 */
@Entity
@Table(name = "report_task_data")
public class ReportTaskData implements Serializable {


	private static final long serialVersionUID = 1L;


	/**
	 * 主键 
	 */
	private String id ;
	/**
	 * 关联id 
	 */
	private String TaskDataId;
	/**
	 * 需要报案的案件编号 
	 */
	private String caseId ;
	/**
	 * 标的当事人id
	 */
	private String accId;
	/**
	 * 保险公司编号 
	 */
	private String orgCode ;
	/**
	 * 报案返回案件编号 
	 */
	private String reportNo ;
	/**
	 * 任务创建时间 
	 */
	private Date createTime ;


	/**
	 * 获取：主键 
	 */
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	/**
	 * 设置：主键 
	 */
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "TaskDataId")
	public String getTaskDataId() {
		return TaskDataId;
	}

	public void setTaskDataId(String taskDataId) {
		TaskDataId = taskDataId;
	}

	/**
	 * 获取：需要报案的案件编号 
	 */
	@Column(name = "caseId")
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置：需要报案的案件编号 
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
	 * 获取：任务创建时间 
	 */
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：任务创建时间 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
