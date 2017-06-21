package com.picc.report.schema.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
 * 类名称：ReportTaskEntity
 * 描    述：任务表
 * 创建时间：2017-02-21
 */
@Entity
@Table(name = "report_task")
public class ReportTask implements Serializable {


	private static final long serialVersionUID = 1L;


	/**
	 * 主键 
	 */
	private String id ;
	/**
	 * 类型
	 */
	private String taskType ;
	/**
	 * 类型
	 */
	@Transient
	private String notTaskType ;
	/**
	 * 关联id 
	 */
	private String taskDataId ;
	/**
	 * 最后操作时间 
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

	/**
	 * 获取：主行业 
	 */
	@Column(name = "taskType")
	public String getTaskType() {
		return taskType;
	}

	/**
	 * 设置：主行业 
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Transient
	public String getNotTaskType() {
		return notTaskType;
	}

	public void setNotTaskType(String notTaskType) {
		this.notTaskType = notTaskType;
	}

	/**
	 * 获取：副行业 
	 */
	@Column(name = "taskDataId")
	public String getTaskDataId() {
		return taskDataId;
	}

	public void setTaskDataId(String taskDataId) {
		this.taskDataId = taskDataId;
	}

	/**
	 * 获取：最后操作时间 
	 */
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}


	/**
	 * 设置：最后操作时间 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
