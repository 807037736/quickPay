/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2016
 */

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


@Entity
@Table(name = "Qp_T_FLOW")
public class QpTFLOW implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_FLOW_ID = "流程id";
	public static final String ALIAS_PROVICE_ID = "省份id";
	public static final String ALIAS_CITY_ID = "城市id";
	public static final String ALIAS_POLICE_PRO = "交警处理";
	public static final String ALIAS_INPUT_PERSON = "录入人";
	public static final String ALIAS_INPUT_TIME = "录入时间";
	public static final String ALIAS_UPDATE_PERSON = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTFLOW() {
	}
	
	/** 主键对象 **/
	private QpTFLOWId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "flowId", column = @Column(name = "flowId"))
    })
	public QpTFLOWId getId() {
		return this.id;
	}

	public void setId(QpTFLOWId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 省份id **/
	private String proviceId;
  	/** 城市id **/
	private String cityId;
  	/** 交警处理 **/
	private String policePro;
  	/** 录入人 **/
	private String inputPerson;
  	/** 录入时间 **/
	private Date inputTime;
  	/** 修改人 **/
	private String updatePerson;
  	/** 修改时间 **/
	private Date updateTime;
  	/** 有效状态 **/
	private String validStatus;
	
	/**getter setter方法*/
	@Column(name = "proviceId")
	public String getProviceId() {
		return this.proviceId;
	}
	
	public void setProviceId(String proviceId) {
		this.proviceId = proviceId;
	}
	@Column(name = "cityId")
	public String getCityId() {
		return this.cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name = "policePro")
	public String getPolicePro() {
		return this.policePro;
	}
	
	public void setPolicePro(String policePro) {
		this.policePro = policePro;
	}
	@Column(name = "inputPerson")
	public String getInputPerson() {
		return this.inputPerson;
	}
	
	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inputTime")
	public Date getInputTime() {
		return this.inputTime;
	}
	
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	@Column(name = "updatePerson")
	public String getUpdatePerson() {
		return this.updatePerson;
	}
	
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
		
}