
package com.picc.qp.schema.model;

import java.util.Date;

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
@Table(name = "qp_t_async_task_data")
public class QpTAsyncTaskData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**	构造函数	**/
	public QpTAsyncTaskData() {
	}
	
	/** 主键对象 **/
	private QpTAsyncTaskDataId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "taskDataId", column = @Column(name = "taskDataId"))
    })
	public QpTAsyncTaskDataId getId() {
		return this.id;
	}

	public void setId(QpTAsyncTaskDataId id) {
		this.taskDataId = id.getTaskDataId();
		this.id = id;
	}
	
  	/** 主键 **/
	private String taskDataId;
  	/** 任务Id **/
	private String taskId;
  	/** 业务数据key **/
	private String dataKey;
  	/** 业务数据value **/
	private String dateValue;
  	/** 创建时间 **/
	private Date createTime;
	
	@Column(name = "taskId")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "dataKey")
	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
	@Column(name = "dataValue")
	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
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
	public String getTaskDataId() {
		return taskDataId;
	}

	public void setTaskDataId(String taskDataId) {
		this.taskDataId = taskDataId;
	}
		
}