package com.picc.qp.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "wx_taskFinish")
public class WxTaskFinish implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer finishId;//任务主键
	private String caseId;//案件ID
	private Date createTime;//创建时间	
	private Date receiveTime;//领取时间
	private Date modifyTime;//修改时间
	private Date fallbackTime;//回滚时间
	private Date completeTime;//完成时间
	private String createCode;//创建人代码
	private String createName;//创建人名称
	private String receiveCode;//领取人代码
	private String receiveName;//领取人姓名
	private String modifyCode;//修改人代码
	private String modifyName;//修改人名称
	private String fallbackCode;//回滚人代码
	private String fallbackName;//回滚人姓名
	private String status;//状态  1:新建  2:已领取  3：已回滚  4：已完成
	private String completeCode;//完成人Code
	private String completeName;//完成人姓名
	private String regFrom;// 来源
	private String remark;// 备注
	private Date startCompleteTime;// 任务开始时间
	private Date endCompleteTime;// 任务结束时间
	
	@Id
	@Column(name = "finishId")
	public Integer getFinishId() {
		return finishId;
	}
	public void setFinishId(Integer finishId) {
		this.finishId = finishId;
	}
	@Column(name = "caseId")
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "receiveTime")
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "fallbackTime")
	public Date getFallbackTime() {
		return fallbackTime;
	}
	public void setFallbackTime(Date fallbackTime) {
		this.fallbackTime = fallbackTime;
	}
	@Column(name = "completeTime")
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	@Column(name = "createCode")
	public String getCreateCode() {
		return createCode;
	}
	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	@Column(name = "createName")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Column(name = "receiveCode")
	public String getReceiveCode() {
		return receiveCode;
	}
	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}
	@Column(name = "receiveName")
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	@Column(name = "modifyCode")
	public String getModifyCode() {
		return modifyCode;
	}
	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}
	@Column(name = "modifyName")
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	@Column(name = "fallbackCode")
	public String getFallbackCode() {
		return fallbackCode;
	}
	public void setFallbackCode(String fallbackCode) {
		this.fallbackCode = fallbackCode;
	}
	@Column(name = "fallbackName")
	public String getFallbackName() {
		return fallbackName;
	}
	public void setFallbackName(String fallbackName) {
		this.fallbackName = fallbackName;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "completeCode")
	public String getCompleteCode() {
		return completeCode;
	}
	public void setCompleteCode(String completeCode) {
		this.completeCode = completeCode;
	}
	@Column(name = "completeName")
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	@Column(name = "regFrom")
	public String getRegFrom() {
		return regFrom;
	}
	public void setRegFrom(String regFrom) {
		this.regFrom = regFrom;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public Date getStartCompleteTime() {
		return startCompleteTime;
	}

	public void setStartCompleteTime(Date startCompleteTime) {
		this.startCompleteTime = startCompleteTime;
	}

	@Transient
	public Date getEndCompleteTime() {
		return endCompleteTime;
	}

	public void setEndCompleteTime(Date endCompleteTime) {
		this.endCompleteTime = endCompleteTime;
	}
	@Override
	public String toString() {
		return "WxTaskFinish [finishId=" + finishId + ", caseId=" + caseId
				+ ", createCode=" + createCode + ", createName=" + createName
				+ ", receiveCode=" + receiveCode + ", receiveName="
				+ receiveName + ", status=" + status
				+ ", completeCode=" + completeCode + ", completeName="
				+ completeName +", regFrom="
						+ regFrom + "]";
	}
}
