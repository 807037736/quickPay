package com.picc.qp.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="qp_t_com_inform")
public class QpTCOMInform implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int informId;
	private String creator;
	private String createTime;
	private String endTime;
	private String startTime;
	private String title;
	private String content;
	private String type;
	private String state;
	private long compareTime;
	
	@Id
	@Column(name="informId")
	public int getInformId() {
		return informId;
	}
	public void setInformId(int informId) {
		this.informId = informId;
	}
	@Column(name="creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(name="createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="endTime")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name="startTime")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/*用来比较的时间值*/
	@Transient
	public long getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(long compareTime) {
		this.compareTime = compareTime;
	}
	
	@Override
	public String toString() {
		return "QpTCOMInform [informId=" + informId + ", creator=" + creator
				+ ", createTime=" + createTime + ", title=" + title
				+ ", content=" + content + ", type=" + type + ", state="
				+ state + "]";
	}
	
}
