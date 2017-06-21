package com.picc.report.schema.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 类名称：ReportTaskFinishEntity
 * 描    述：成功表
 * 创建时间：2017-02-21
 */
@Entity
@Table(name = "report_task_finish")
public class ReportTaskFinish implements Serializable {


	private static final long serialVersionUID = 1L;


	/**
	 * 主键 
	 */
	private String id ;
	/**
	 * 关联id
	 */
	private String taskDataId ;
	/**
	 * 任务完成时间 
	 */
	private Date createTime ;
	/**
	 * 任务类型 1:报案  2:单证上传 
	 */
	private String taskType ;


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
	 * 获取：任务数据关联id 
	 */
	@Column(name = "taskDataId")
	public String getTaskDataId() {
		return taskDataId;
	}

	/**
	 * 设置：需要报案的案件编号 
	 */
	public void setTaskDataId(String taskDataId) {
		this.taskDataId = taskDataId;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：任务类型 1:报案  2:单证上传 
	 */
	@Column(name = "taskType")
	public String getTaskType() {
		return taskType;
	}

	/**
	 * 设置：任务类型 1:报案  2:单证上传 
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}
