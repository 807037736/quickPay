package com.picc.report.schema.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 类名称：ReportTaskFailEntity
 * 描    述：失败表
 * 创建时间：2017-02-21
 */
@Entity
@Table(name = "report_task_fail")
public class ReportTaskFail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键 
    */
	private String id ;
    /**
     * 关联数据表Id 
    */
	private String taskDataId ;
    /**
     * 任务类型 1:报案  2:单证上传 
    */
	private String taskType ;
    /**
     * 失败备注 
    */
	private String remark ;
    /**
     * 记录失败的次数 
    */
	private int errorCount ;
    /**
     * 0:继续  1:终止 
    */
	private String isTask ;
    /**
     * 最后一次失败时间 
    */
	private Date errorTime ;
	
	
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
     * 获取：关联数据表caseId 
    */
    @Column(name = "taskDataId")
	public String getTaskDataId() {
	  return taskDataId;
    }

   /**
     * 设置：关联数据表caseId 
     */
    public void setTaskDataId(String taskDataId) {
	   this.taskDataId = taskDataId;
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

   /**
     * 获取：失败备注 
    */
    @Column(name = "remark")
	public String getRemark() {
	  return remark;
    }

   /**
     * 设置：失败备注 
     */
    public void setRemark(String remark) {
	   this.remark = remark;
    }

   /**
     * 获取：记录失败的次数 
    */
    @Column(name = "errorCount")
	public int getErrorCount() {
	  return errorCount;
    }

   /**
     * 设置：记录失败的次数 
     */
    public void setErrorCount(int errorCount) {
	   this.errorCount = errorCount;
    }

   /**
     * 获取：0:继续  1:终止 
    */
    @Column(name = "isTask")
	public String getIsTask() {
	  return isTask;
    }

   /**
     * 设置：0:继续  1:终止 
     */
    public void setIsTask(String isTask) {
	   this.isTask = isTask;
    }

   /**
     * 获取：最后一次失败时间 
    */
    @Column(name = "errorTime")
	public Date getErrorTime() {
	  return errorTime;
    }

   /**
     * 设置：最后一次失败时间 
     */
    public void setErrorTime(Date errorTime) {
	   this.errorTime = errorTime;
    }

}
