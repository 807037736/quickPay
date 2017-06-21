/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
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
@Table(name = "Qp_T_COM_Driver_Info")
public class QpTCOMDriverInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_DRIVER_INFO_ID = "驾驶员ID";
	public static final String ALIAS_DRIVER_NAME = "驾驶员姓名";
	public static final String ALIAS_DRIVER_ID_NUMBER = "身份证号";
	public static final String ALIAS_DRIVER_VEHICLE_NUMBER = "车牌号";
	public static final String ALIAS_CO_ID = "承保保险公司ID";
	public static final String ALIAS_DRIVER_INFO_RECORD_TIME = "录音时间";
	public static final String ALIAS_DRIVER_ID_NUMBER_REPEAT_TIME = "身份证号重复次数";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTCOMDriverInfo() {
	}
	
	/** 主键对象 **/
	private QpTCOMDriverInfoId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "driverInfoId", column = @Column(name = "driverInfoId"))
    })
	public QpTCOMDriverInfoId getId() {
		return this.id;
	}

	public void setId(QpTCOMDriverInfoId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 驾驶员姓名 **/
	private String driverName;
  	/** 身份证号 **/
	private String driverIdNumber;
  	/** 车牌号 **/
	private String driverVehicleNumber;
  	/** 承保保险公司ID **/
	private String coId;
  	/** 录音时间 **/
	private String driverInfoRecordTime;
  	/** 身份证号重复次数 **/
	private Integer driverIdNumberRepeatTime;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus;
	
	/**getter setter方法*/
	@Column(name = "DriverName")
	public String getDriverName() {
		return this.driverName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	@Column(name = "DriverIdNumber")
	public String getDriverIdNumber() {
		return this.driverIdNumber;
	}
	
	public void setDriverIdNumber(String driverIdNumber) {
		this.driverIdNumber = driverIdNumber;
	}
	@Column(name = "DriverVehicleNumber")
	public String getDriverVehicleNumber() {
		return this.driverVehicleNumber;
	}
	
	public void setDriverVehicleNumber(String driverVehicleNumber) {
		this.driverVehicleNumber = driverVehicleNumber;
	}
	@Column(name = "CoId")
	public String getCoId() {
		return this.coId;
	}
	
	public void setCoId(String coId) {
		this.coId = coId;
	}
	@Column(name = "DriverInfoRecordTime")
	public String getDriverInfoRecordTime() {
		return this.driverInfoRecordTime;
	}
	
	public void setDriverInfoRecordTime(String driverInfoRecordTime) {
		this.driverInfoRecordTime = driverInfoRecordTime;
	}
	@Column(name = "DriverIdNumberRepeatTime")
	public Integer getDriverIdNumberRepeatTime() {
		return this.driverIdNumberRepeatTime;
	}
	
	public void setDriverIdNumberRepeatTime(Integer driverIdNumberRepeatTime) {
		this.driverIdNumberRepeatTime = driverIdNumberRepeatTime;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "UpdaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
		
}