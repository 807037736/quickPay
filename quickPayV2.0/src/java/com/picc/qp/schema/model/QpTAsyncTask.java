
package com.picc.qp.schema.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "qp_t_async_task")
public class QpTAsyncTask implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**	构造函数	**/
	public QpTAsyncTask() {
	}
	
	/** 主键对象 **/
	private QpTAsyncTaskId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "taskId", column = @Column(name = "taskId"))
    })
	public QpTAsyncTaskId getId() {
		return this.id;
	}

	public void setId(QpTAsyncTaskId id) {
		this.taskId = id.getTaskId();
		this.id = id;
	}
	
  	/** 主键 **/
	private String taskId;
  	/** 任务类型 **/
	private String type;
  	/** 状态（0：待处理 1：处理中 2：执行成功 3：执行失败） **/
	private String status;
  	/** 错误日志 **/
	private String errorLog;
  	/** 上传次数 **/
	private Integer uploadTimes;
  	/** 上传开始时间 **/
	private Date startTime;
  	/** 上传完成时间 **/
	private Date finishTime;
  	/** 创建时间 **/
	private Date createTime;
	
	private Map<String,String> params = null;
	
	@Column(name = "type")
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "errorLog")
	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	@Column(name = "uploadTimes")
	public Integer getUploadTimes() {
		return uploadTimes;
	}

	public void setUploadTimes(Integer uploadTimes) {
		this.uploadTimes = uploadTimes;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startTime")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "finishTime")
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime" , updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Transient
	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
		
}