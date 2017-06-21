/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "Qp_T_IC_Accident")
public class QpTICAccident implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ACCI_ID = "事故ID";
	public static final String ALIAS_ACCI_TIME = "发生时间";
	public static final String ALIAS_ACCI_WEATHER = "天气情况";
	public static final String ALIAS_ACCI_PROVINCE = "省份";
	public static final String ALIAS_ACCI_CITY = "城市";
	public static final String ALIAS_ACCI_DISTRICT = "区县";
	public static final String ALIAS_ACCI_ROAD = "道路";
	public static final String ALIAS_ACCI_STREET = "街道";
	public static final String ALIAS_DRIVER_I_D_TYPE = "证件类型";
	public static final String ALIAS_DRIVER_I_D_NUMBER = "证件号";
	public static final String ALIAS_DRIVER_NAME = "姓名";
	public static final String ALIAS_DRIVER_SEX = "性别";
	public static final String ALIAS_DRIVER_PHONE = "固定电话";
	public static final String ALIAS_DRIVER_MOBILE = "手机号码";
	public static final String ALIAS_DRIVER_ADDRESS = "当前住址";
	public static final String ALIAS_DRIVER_VEHICLE_TYPE = "车辆类型";
	public static final String ALIAS_DRIVER_VEHICLE_NUMBER = "车牌号码";
	public static final String ALIAS_DRIVER_INSU_NUMBER = "保险凭证";
	public static final String ALIAS_DRIVER_DIRECTION = "行驶方向";
	public static final String ALIAS_DRIVER_IP = "DriverIp";
	public static final String ALIAS_LIVE_CHANNEL_ID = "LiveChannelId";
	public static final String ALIAS_LIVE_STATUS = "LiveStatus";
	public static final String ALIAS_LIVE_SAVED_CHANNELS = "LiveSavedChannels";
	public static final String ALIAS_LIVE_DESC = "LiveDesc";
	public static final String ALIAS_C_C_LOGIN_ID = "CCLoginId";
	public static final String ALIAS_C_C_USER_NAME = "CCUserName";
	public static final String ALIAS_C_C_EMPLOYEE_ID = "CCUserName";
	public static final String ALIAS_C_C_HANDLE_STATUS = "CCUserName";
	public static final String ALIAS_C_C_HANDLE_TIME = "CCHandleTime";
	public static final String ALIAS_C_C_HANDLE_NOTES = "CCHandleNotes";
	public static final String ALIAS_INFO_SOURCE = "信息来源";
	public static final String ALIAS_CASE_ID = "从属案件ID";
	public static final String ALIAS_DRIVER_LAW_ID = "违反法律法规";
	public static final String ALIAS_DRIVER_LIABILITY = "需承担的责任";
	public static final String ALIAS_DRIVER_LOSS = "损失";
	public static final String ALIAS_I_C_LOGIN_ID = "ICLoginId";
	public static final String ALIAS_I_C_USER_NAME = "ICUserName";
	public static final String ALIAS_I_C_EMPLOYEE_ID = "ICEmployeeId";
	public static final String ALIAS_I_C_HANDLE_STAUTS = "ICHandleStauts";
	public static final String ALIAS_I_C_HANDLE_TIME = "ICHandleTime";
	public static final String ALIAS_I_C_HANDLE_NOTES = "ICHandleNotes";
	public static final String ALIAS_CO_ID = "所属保险公司ID";
	public static final String ALIAS_T_P_REMOVE_STATUS = "TPRemoveStatus";
	public static final String ALIAS_ACCI_LNG = "AcciLng";
	public static final String ALIAS_ACCI_LAT = "AcciLat";
	public static final String ALIAS_RELA_A_VEHICLE_NUMBER = "RelaAVehicleNumber";
	public static final String ALIAS_RELA_A_CO_ID = "RelaACoId";
	public static final String ALIAS_RELA_B_VEHICLE_NUMBER = "RelaBVehicleNumber";
	public static final String ALIAS_RELA_B_CO_ID = "RelaBCoId";
	public static final String ALIAS_RELA_C_VEHICLE_NUMBER = "RelaCVehicleNumber";
	public static final String ALIAS_RELA_C_CO_ID = "RelaCCoId";
	public static final String ALIAS_RELA_D_VEHICLE_NUMBER = "RelaDVehicleNumber";
	public static final String ALIAS_RELA_D_CO_ID = "RelaDCoId";
	public static final String ALIAS_LOCK_STATUS = "LockStatus";
	public static final String ALIAS_LOCK_LOGIN_NAME = "LockLoginName";
	public static final String ALIAS_LOCK_LOGIN_ID = "LockLoginId";
	public static final String ALIAS_T_P_DRIVER_CODE = "TPDriverCode";
	public static final String ALIAS_ACCI_INPUT_CASE_TIME = "事故录入时间";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTICAccident() {
	}
	
	/** 主键对象 **/
	private QpTICAccidentId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "acciId", column = @Column(name = "acciId"))
    })
	public QpTICAccidentId getId() {
		return this.id;
	}

	public void setId(QpTICAccidentId id) {
		this.acciId = id.getAcciId();
		this.id = id;
	}
	
	private String acciId;
	/**属性*/
  	/** 发生时间 **/
	private Date acciTime;
  	/** 天气情况 **/
	private String acciWeather;
  	/** 省份 **/
	private String acciProvince;
  	/** 城市 **/
	private String acciCity;
  	/** 区县 **/
	private String acciDistrict;
  	/** 道路 **/
	private String acciRoad;
  	/** 街道 **/
	private String acciStreet;
  	/** 证件类型 **/
	private String driverIDType;
  	/** 证件号 **/
	private String driverIDNumber;
  	/** 姓名 **/
	private String driverName;
  	/** 性别 **/
	private String driverSex;
  	/** 固定电话 **/
	private String driverPhone;
  	/** 手机号码 **/
	private String driverMobile;
  	/** 当前住址 **/
	private String driverAddress;
  	/** 车辆类型 **/
	private String driverVehicleType;
  	/** 车牌号码 **/
	private String driverVehicleNumber;
  	/** 保险凭证 **/
	private String driverInsuNumber;
  	/** 行驶方向 **/
	private String driverDirection;
  	/** DriverIp **/
	private String driverIp;
  	/** LiveChannelId **/
	private String liveChannelId;
  	/** LiveStatus **/
	private String liveStatus;
  	/** LiveSavedChannels **/
	private String liveSavedChannels;
  	/** LiveDesc **/
	private String liveDesc;
  	/** CCLoginId **/
	private String ccLoginId;
  	/** CCUserName **/
	private String ccUserName;
  	/** CCUserName **/
	private String ccEmployeeId;
  	/** CCUserName **/
	private String ccHandleStatus;
  	/** CCHandleTime **/
	private Date ccHandleTime;
  	/** CCHandleNotes **/
	private String ccHandleNotes;
  	/** 信息来源 **/
	private String infoSource;
  	/** 从属案件ID **/
	private String caseId;
  	/** 违反法律法规 **/
	private String driverLawId;
  	/** 需承担的责任 **/
	private String driverLiability;
  	/** 损失 **/
	private String driverLoss;
  	/** ICLoginId **/
	private String icLoginId;
  	/** ICUserName **/
	private String icUserName;
  	/** ICEmployeeId **/
	private String icEmployeeId;
  	/** ICHandleStauts **/
	private String icHandleStauts;
  	/** ICHandleTime **/
	private Date icHandleTime;
  	/** ICHandleNotes **/
	private String icHandleNotes;
  	/** 所属保险公司ID **/
	private String coId;
	/** 其他保险公司 **/
	private String companyNameOther;
  	/** TPRemoveStatus **/
	private String tpRemoveStatus;
  	/** AcciLng **/
	private String acciLng;
  	/** AcciLat **/
	private String acciLat;
  	/** RelaAVehicleNumber **/
	private String relaAVehicleNumber;
  	/** RelaACoId **/
	private String relaACoId;
  	/** RelaBVehicleNumber **/
	private String relaBVehicleNumber;
  	/** RelaBCoId **/
	private String relaBCoId;
  	/** RelaCVehicleNumber **/
	private String relaCVehicleNumber;
  	/** RelaCCoId **/
	private String relaCCoId;
  	/** RelaDVehicleNumber **/
	private String relaDVehicleNumber;
  	/** RelaDCoId **/
	private String relaDCoId;
  	/** LockStatus **/
	private String lockStatus;
  	/** LockLoginName **/
	private String lockLoginName;
  	/** LockLoginId **/
	private String lockLoginId;
  	/** TPDriverCode **/
	private String tpDriverCode;
  	/** 事故录入时间 **/
	private Date acciInputCaseTime;
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
	/** 行驶主干道 **/
    private String driverRoad;
    /** 行驶街道 **/
    private String driverStreet;
    /** 估损金额 **/
    private BigDecimal estimateLossSum;
    /** 定损价格 **/
    private BigDecimal fixedLossPrice;
    /** 查勘员代码 **/
    private String lossAssessorCode;
    /** 估损时间**/
    private Date estimateLossTime;
    /** 组号ID**/
    private String groupId;
    
    private String reportCaseStatus;
    
    private String reportNo;
	
	/**翻译字段**/
	private String driverSexDesc;
	private String coName;
	private String driverDirectionDesc;
	private String driverLawDesc;
	private String driverLiabilityDesc;
	private String driverVehicleTypeDesc;
	private String estimateLossSumDesc;
	private String fixedLossPriceDesc;
	private BigInteger picCount;
	
	/** 经度**/
	private String longitude;
	/** 纬度**/
	private String latitude;
	/** 查勘groupId**/
	private String surveyGroupId;

	/** 出险时间 **/
	private Date caseTime;
	/** 出险详细地点 **/
	private String caseAddress;
	/** 快赔处理时间 **/
	private Date tpHandleTime;
	/** 案件编号 **/
	private String caseSerialNo;
	/** 出险次数 **/
	private String riskTimes;
	/** 查勘备注 **/
	private String surveyNotes;
	/** 厂牌型号 **/
	private String labelType;
	/** 被保险人 **/
	private String insured;
	/** 车架号 **/
	private String chassisNumber;
	
	public String getSurveyNotes() {
		return surveyNotes;
	}

	public void setSurveyNotes(String surveyNotes) {
		this.surveyNotes = surveyNotes;
	}

	/**getter setter方法*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AcciTime")
	public Date getAcciTime() {
		return this.acciTime;
	}
	
	public void setAcciTime(Date acciTime) {
		this.acciTime = acciTime;
	}
	@Column(name = "AcciWeather")
	public String getAcciWeather() {
		return this.acciWeather;
	}
	
	public void setAcciWeather(String acciWeather) {
		this.acciWeather = acciWeather;
	}
	@Column(name = "AcciProvince")
	public String getAcciProvince() {
		return this.acciProvince;
	}
	
	public void setAcciProvince(String acciProvince) {
		this.acciProvince = acciProvince;
	}
	@Column(name = "AcciCity")
	public String getAcciCity() {
		return this.acciCity;
	}
	
	public void setAcciCity(String acciCity) {
		this.acciCity = acciCity;
	}
	@Column(name = "AcciDistrict")
	public String getAcciDistrict() {
		return this.acciDistrict;
	}
	
	public void setAcciDistrict(String acciDistrict) {
		this.acciDistrict = acciDistrict;
	}
	@Column(name = "AcciRoad")
	public String getAcciRoad() {
		return this.acciRoad;
	}
	
	public void setAcciRoad(String acciRoad) {
		this.acciRoad = acciRoad;
	}
	@Column(name = "AcciStreet")
	public String getAcciStreet() {
		return this.acciStreet;
	}
	
	public void setAcciStreet(String acciStreet) {
		this.acciStreet = acciStreet;
	}
	@Column(name = "DriverIDType")
	public String getDriverIDType() {
		return this.driverIDType;
	}
	
	public void setDriverIDType(String driverIDType) {
		this.driverIDType = driverIDType;
	}
	@Column(name = "DriverIDNumber")
	public String getDriverIDNumber() {
		return this.driverIDNumber;
	}
	
	public void setDriverIDNumber(String driverIDNumber) {
		this.driverIDNumber = driverIDNumber;
	}
	@Column(name = "DriverName")
	public String getDriverName() {
		return this.driverName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	@Column(name = "DriverSex")
	public String getDriverSex() {
		return this.driverSex;
	}
	
	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}
	@Column(name = "DriverPhone")
	public String getDriverPhone() {
		return this.driverPhone;
	}
	
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	@Column(name = "DriverMobile")
	public String getDriverMobile() {
		return this.driverMobile;
	}
	
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	@Column(name = "DriverAddress")
	public String getDriverAddress() {
		return this.driverAddress;
	}
	
	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}
	@Column(name = "DriverVehicleType")
	public String getDriverVehicleType() {
		return this.driverVehicleType;
	}
	
	public void setDriverVehicleType(String driverVehicleType) {
		this.driverVehicleType = driverVehicleType;
	}
	@Column(name = "DriverVehicleNumber")
	public String getDriverVehicleNumber() {
		return this.driverVehicleNumber;
	}
	
	public void setDriverVehicleNumber(String driverVehicleNumber) {
		this.driverVehicleNumber = driverVehicleNumber;
	}
	@Column(name = "DriverInsuNumber")
	public String getDriverInsuNumber() {
		return this.driverInsuNumber;
	}
	
	public void setDriverInsuNumber(String driverInsuNumber) {
		this.driverInsuNumber = driverInsuNumber;
	}
	@Column(name = "DriverDirection")
	public String getDriverDirection() {
		return this.driverDirection;
	}
	
	public void setDriverDirection(String driverDirection) {
		this.driverDirection = driverDirection;
	}
	@Column(name = "DriverIp")
	public String getDriverIp() {
		return this.driverIp;
	}
	
	public void setDriverIp(String driverIp) {
		this.driverIp = driverIp;
	}
	@Column(name = "LiveChannelId")
	public String getLiveChannelId() {
		return this.liveChannelId;
	}
	
	public void setLiveChannelId(String liveChannelId) {
		this.liveChannelId = liveChannelId;
	}
	@Column(name = "LiveStatus")
	public String getLiveStatus() {
		return this.liveStatus;
	}
	
	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}
	@Column(name = "LiveSavedChannels")
	public String getLiveSavedChannels() {
		return this.liveSavedChannels;
	}
	
	public void setLiveSavedChannels(String liveSavedChannels) {
		this.liveSavedChannels = liveSavedChannels;
	}
	@Column(name = "LiveDesc")
	public String getLiveDesc() {
		return this.liveDesc;
	}
	
	public void setLiveDesc(String liveDesc) {
		this.liveDesc = liveDesc;
	}
	@Column(name = "CCLoginId")
	public String getCcLoginId() {
		return this.ccLoginId;
	}
	
	public void setCcLoginId(String ccLoginId) {
		this.ccLoginId = ccLoginId;
	}
	@Column(name = "CCUserName")
	public String getCcUserName() {
		return this.ccUserName;
	}
	
	public void setCcUserName(String ccUserName) {
		this.ccUserName = ccUserName;
	}
	@Column(name = "CCEmployeeId")
	public String getCcEmployeeId() {
		return this.ccEmployeeId;
	}
	
	public void setCcEmployeeId(String ccEmployeeId) {
		this.ccEmployeeId = ccEmployeeId;
	}
	@Column(name = "CCHandleStatus")
	public String getCcHandleStatus() {
		return this.ccHandleStatus;
	}
	
	public void setCcHandleStatus(String ccHandleStatus) {
		this.ccHandleStatus = ccHandleStatus;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCHandleTime")
	public Date getCcHandleTime() {
		return this.ccHandleTime;
	}
	
	public void setCcHandleTime(Date ccHandleTime) {
		this.ccHandleTime = ccHandleTime;
	}
	@Column(name = "CCHandleNotes")
	public String getCcHandleNotes() {
		return this.ccHandleNotes;
	}
	
	public void setCcHandleNotes(String ccHandleNotes) {
		this.ccHandleNotes = ccHandleNotes;
	}
	@Column(name = "InfoSource")
	public String getInfoSource() {
		return this.infoSource;
	}
	
	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}
	@Column(name = "CaseId")
	public String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "DriverLawId")
	public String getDriverLawId() {
		return this.driverLawId;
	}
	
	public void setDriverLawId(String driverLawId) {
		this.driverLawId = driverLawId;
	}
	@Column(name = "DriverLiability")
	public String getDriverLiability() {
		return this.driverLiability;
	}
	
	public void setDriverLiability(String driverLiability) {
		this.driverLiability = driverLiability;
	}
	@Column(name = "DriverLoss")
	public String getDriverLoss() {
		return this.driverLoss;
	}
	
	public void setDriverLoss(String driverLoss) {
		this.driverLoss = driverLoss;
	}
	@Column(name = "ICLoginId")
	public String getIcLoginId() {
		return this.icLoginId;
	}
	
	public void setIcLoginId(String icLoginId) {
		this.icLoginId = icLoginId;
	}
	@Column(name = "ICUserName")
	public String getIcUserName() {
		return this.icUserName;
	}
	
	public void setIcUserName(String icUserName) {
		this.icUserName = icUserName;
	}
	@Column(name = "ICEmployeeId")
	public String getIcEmployeeId() {
		return this.icEmployeeId;
	}
	
	public void setIcEmployeeId(String icEmployeeId) {
		this.icEmployeeId = icEmployeeId;
	}
	@Column(name = "ICHandleStauts")
	public String getIcHandleStauts() {
		return this.icHandleStauts;
	}
	
	public void setIcHandleStauts(String icHandleStauts) {
		this.icHandleStauts = icHandleStauts;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ICHandleTime")
	public Date getIcHandleTime() {
		return this.icHandleTime;
	}
	
	public void setIcHandleTime(Date icHandleTime) {
		this.icHandleTime = icHandleTime;
	}
	@Column(name = "ICHandleNotes")
	public String getIcHandleNotes() {
		return this.icHandleNotes;
	}
	
	public void setIcHandleNotes(String icHandleNotes) {
		this.icHandleNotes = icHandleNotes;
	}
	@Column(name = "CoId")
	public String getCoId() {
		return this.coId;
	}
	
	public void setCoId(String coId) {
		this.coId = coId;
	}
	@Column(name = "companyNameOther")
	public String getCompanyNameOther() {
	    return companyNameOther;
	}

	public void setCompanyNameOther(String companyNameOther) {
	    this.companyNameOther = companyNameOther;
	}

	@Column(name = "TPRemoveStatus")
	public String getTpRemoveStatus() {
		return this.tpRemoveStatus;
	}
	
	public void setTpRemoveStatus(String tpRemoveStatus) {
		this.tpRemoveStatus = tpRemoveStatus;
	}
	@Column(name = "AcciLng")
	public String getAcciLng() {
		return this.acciLng;
	}
	
	public void setAcciLng(String acciLng) {
		this.acciLng = acciLng;
	}
	@Column(name = "AcciLat")
	public String getAcciLat() {
		return this.acciLat;
	}
	
	public void setAcciLat(String acciLat) {
		this.acciLat = acciLat;
	}
	@Column(name = "RelaAVehicleNumber")
	public String getRelaAVehicleNumber() {
		return this.relaAVehicleNumber;
	}
	
	public void setRelaAVehicleNumber(String relaAVehicleNumber) {
		this.relaAVehicleNumber = relaAVehicleNumber;
	}
	@Column(name = "RelaACoId")
	public String getRelaACoId() {
		return this.relaACoId;
	}
	
	public void setRelaACoId(String relaACoId) {
		this.relaACoId = relaACoId;
	}
	@Column(name = "RelaBVehicleNumber")
	public String getRelaBVehicleNumber() {
		return this.relaBVehicleNumber;
	}
	
	public void setRelaBVehicleNumber(String relaBVehicleNumber) {
		this.relaBVehicleNumber = relaBVehicleNumber;
	}
	@Column(name = "RelaBCoId")
	public String getRelaBCoId() {
		return this.relaBCoId;
	}
	
	public void setRelaBCoId(String relaBCoId) {
		this.relaBCoId = relaBCoId;
	}
	@Column(name = "RelaCVehicleNumber")
	public String getRelaCVehicleNumber() {
		return this.relaCVehicleNumber;
	}
	
	public void setRelaCVehicleNumber(String relaCVehicleNumber) {
		this.relaCVehicleNumber = relaCVehicleNumber;
	}
	@Column(name = "RelaCCoId")
	public String getRelaCCoId() {
		return this.relaCCoId;
	}
	
	public void setRelaCCoId(String relaCCoId) {
		this.relaCCoId = relaCCoId;
	}
	@Column(name = "RelaDVehicleNumber")
	public String getRelaDVehicleNumber() {
		return this.relaDVehicleNumber;
	}
	
	public void setRelaDVehicleNumber(String relaDVehicleNumber) {
		this.relaDVehicleNumber = relaDVehicleNumber;
	}
	@Column(name = "RelaDCoId")
	public String getRelaDCoId() {
		return this.relaDCoId;
	}
	
	public void setRelaDCoId(String relaDCoId) {
		this.relaDCoId = relaDCoId;
	}
	@Column(name = "LockStatus")
	public String getLockStatus() {
		return this.lockStatus;
	}
	
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	@Column(name = "LockLoginName")
	public String getLockLoginName() {
		return this.lockLoginName;
	}
	
	public void setLockLoginName(String lockLoginName) {
		this.lockLoginName = lockLoginName;
	}
	@Column(name = "LockLoginId")
	public String getLockLoginId() {
		return this.lockLoginId;
	}
	
	public void setLockLoginId(String lockLoginId) {
		this.lockLoginId = lockLoginId;
	}
	@Column(name = "TPDriverCode")
	public String getTpDriverCode() {
		return this.tpDriverCode;
	}
	
	public void setTpDriverCode(String tpDriverCode) {
		this.tpDriverCode = tpDriverCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AcciInputCaseTime")
	public Date getAcciInputCaseTime() {
		return this.acciInputCaseTime;
	}
	
	public void setAcciInputCaseTime(Date acciInputCaseTime) {
		this.acciInputCaseTime = acciInputCaseTime;
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

	@Column(name = "DriverRoad")
    public String getDriverRoad() {
        return driverRoad;
    }

    public void setDriverRoad(String driverRoad) {
        this.driverRoad = driverRoad;
    }

    @Column(name = "DriverStreet")
    public String getDriverStreet() {
        return driverStreet;
    }

    public void setDriverStreet(String driverStreet) {
        this.driverStreet = driverStreet;
    }
    @Column(name = "EstimateLossSum")
    public BigDecimal getEstimateLossSum() {
		return estimateLossSum;
	}

	public void setEstimateLossSum(BigDecimal estimateLossSum) {
		this.estimateLossSum = estimateLossSum;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EstimateLossTime")
	public Date getEstimateLossTime() {
		return estimateLossTime;
	}

	public void setEstimateLossTime(Date estimateLossTime) {
		this.estimateLossTime = estimateLossTime;
	}
	@Column(name = "FixedLossPrice")
	public BigDecimal getFixedLossPrice() {
		return fixedLossPrice;
	}

	public void setFixedLossPrice(BigDecimal fixedLossPrice) {
		this.fixedLossPrice = fixedLossPrice;
	}
	@Column(name = "LossAssessorCode")
	public String getLossAssessorCode() {
		return lossAssessorCode;
	}

	public void setLossAssessorCode(String lossAssessorCode) {
		this.lossAssessorCode = lossAssessorCode;
	}

	@Column(name = "groupId")
	public String getGroupId() {
		return groupId;
	}
	@Column(name = "riskTimes")
	public String getRiskTimes() {
		return riskTimes;
	}

	public void setRiskTimes(String riskTimes) {
		this.riskTimes = riskTimes;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Transient
	public String getAcciId() {
		return acciId;
	}

	public void setAcciId(String acciId) {
		this.acciId = acciId;
	}
	
	@Transient
	public String getDriverSexDesc() {
        return driverSexDesc;
    }
	
    public void setDriverSexDesc(String driverSexDesc) {
        this.driverSexDesc = driverSexDesc;
    }

    @Transient
    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    @Transient
    public String getDriverDirectionDesc() {
        return driverDirectionDesc;
    }

    public void setDriverDirectionDesc(String driverDirectionDesc) {
        this.driverDirectionDesc = driverDirectionDesc;
    }

    @Transient
    public String getDriverLawDesc() {
        return driverLawDesc;
    }

    public void setDriverLawDesc(String driverLawDesc) {
        this.driverLawDesc = driverLawDesc;
    }

    @Transient
    public String getDriverLiabilityDesc() {
        return driverLiabilityDesc;
    }

    public void setDriverLiabilityDesc(String driverLiabilityDesc) {
        this.driverLiabilityDesc = driverLiabilityDesc;
    }

    @Transient
    public String getDriverVehicleTypeDesc() {
        return driverVehicleTypeDesc;
    }

    public void setDriverVehicleTypeDesc(String driverVehicleTypeDesc) {
        this.driverVehicleTypeDesc = driverVehicleTypeDesc;
    }

    @Transient
	public Date getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}

	@Transient
	public String getCaseAddress() {
		return caseAddress;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	@Transient
	public Date getTpHandleTime() {
		return tpHandleTime;
	}

	public void setTpHandleTime(Date tpHandleTime) {
		this.tpHandleTime = tpHandleTime;
	}

	@Transient
	public String getEstimateLossSumDesc() {
		return estimateLossSumDesc;
	}

	public void setEstimateLossSumDesc(String estimateLossSumDesc) {
		this.estimateLossSumDesc = estimateLossSumDesc;
	}

	@Transient
	public String getFixedLossPriceDesc() {
		return fixedLossPriceDesc;
	}

	public void setFixedLossPriceDesc(String fixedLossPriceDesc) {
		this.fixedLossPriceDesc = fixedLossPriceDesc;
	}

	@Transient
	public String getCaseSerialNo() {
		return caseSerialNo;
	}

	public void setCaseSerialNo(String caseSerialNo) {
		this.caseSerialNo = caseSerialNo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getSurveyGroupId() {
		return surveyGroupId;
	}

	public void setSurveyGroupId(String surveyGroupId) {
		this.surveyGroupId = surveyGroupId;
	}	
	
	private Date birthDay;

	@Column(name = "BIRTHDAY")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	private String permissionDrive;

	@Column(name = "PERMISSIONDRIVE")
	public String getPermissionDrive() {
		return permissionDrive;
	}

	public void setPermissionDrive(String permissionDrive) {
		this.permissionDrive = permissionDrive;
	}

	@Transient
	public BigInteger getPicCount() {
		return picCount;
	}

	public void setPicCount(BigInteger picCount) {
		this.picCount = picCount;
	}
	
	private String accidentNotes;

	@Column(name = "accidentNotes")
	public String getAccidentNotes() {
		return accidentNotes;
	}

	public void setAccidentNotes(String accidentNotes) {
		this.accidentNotes = accidentNotes;
	}
	
	private String openID;

	@Column(name = "openID")
	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	@Column(name = "labelType")
	public String getLabelType() {
	    return labelType;
	}

	public void setLabelType(String labelType) {
	    this.labelType = labelType;
	}

	@Column(name = "insured")
	public String getInsured() {
	    return insured;
	}

	public void setInsured(String insured) {
	    this.insured = insured;
	}

	public String getChassisNumber() {
	    return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
	    this.chassisNumber = chassisNumber;
	}

	@Transient
	public String getReportCaseStatus() {
		return reportCaseStatus;
	}

	public void setReportCaseStatus(String reportCaseStatus) {
		this.reportCaseStatus = reportCaseStatus;
	}

	@Transient
	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	
	public static void copyAccident(QpTICAccident sourceQpTICAccident, QpTICAccident qpTICAccident){
		sourceQpTICAccident.setAcciCity(qpTICAccident.getAcciCity());
		sourceQpTICAccident.setAccidentNotes(qpTICAccident.getAccidentNotes());
		sourceQpTICAccident.setAcciId(qpTICAccident.getAcciId());
		sourceQpTICAccident.setAcciInputCaseTime(qpTICAccident.getAcciInputCaseTime());
		sourceQpTICAccident.setAcciLat(qpTICAccident.getAcciLat());
		sourceQpTICAccident.setAcciLng(qpTICAccident.getAcciLng());
		sourceQpTICAccident.setAcciProvince(qpTICAccident.getAcciProvince());
		sourceQpTICAccident.setAcciRoad(qpTICAccident.getAcciRoad());
		sourceQpTICAccident.setAcciTime(qpTICAccident.getAcciTime());
		sourceQpTICAccident.setAcciWeather(qpTICAccident.getAcciWeather());
		sourceQpTICAccident.setBirthDay(qpTICAccident.getBirthDay());
		sourceQpTICAccident.setCaseAddress(qpTICAccident.getCaseAddress());
		sourceQpTICAccident.setCaseId(qpTICAccident.getCaseId());
		sourceQpTICAccident.setCaseSerialNo(qpTICAccident.getCaseSerialNo());
		sourceQpTICAccident.setCaseTime(qpTICAccident.getCaseTime());
		sourceQpTICAccident.setCcEmployeeId(qpTICAccident.getCcEmployeeId());
		sourceQpTICAccident.setCcHandleNotes(qpTICAccident.getCcHandleNotes());
		sourceQpTICAccident.setCcHandleStatus(qpTICAccident.getCcHandleStatus());
		sourceQpTICAccident.setCcHandleTime(qpTICAccident.getCcHandleTime());
		sourceQpTICAccident.setCcLoginId(qpTICAccident.getCcLoginId());
		sourceQpTICAccident.setCcUserName(qpTICAccident.getCcUserName());
		sourceQpTICAccident.setChassisNumber(qpTICAccident.getChassisNumber());
		sourceQpTICAccident.setCoId(qpTICAccident.getCoId());
		sourceQpTICAccident.setCompanyNameOther(qpTICAccident.getCompanyNameOther());
		sourceQpTICAccident.setCoName(qpTICAccident.getCoName());
		sourceQpTICAccident.setCreatorCode(qpTICAccident.getCreatorCode());
		sourceQpTICAccident.setDriverAddress(qpTICAccident.getDriverAddress());
		sourceQpTICAccident.setDriverDirection(qpTICAccident.getDriverDirection());
		sourceQpTICAccident.setDriverDirectionDesc(qpTICAccident.getDriverDirectionDesc());
		sourceQpTICAccident.setDriverIDNumber(qpTICAccident.getDriverIDNumber());
		sourceQpTICAccident.setDriverIDType(qpTICAccident.getDriverIDType());
		sourceQpTICAccident.setDriverInsuNumber(qpTICAccident.getDriverInsuNumber());
		sourceQpTICAccident.setDriverIp(qpTICAccident.getDriverIp());
		sourceQpTICAccident.setDriverLawDesc(qpTICAccident.getDriverLawDesc());
		sourceQpTICAccident.setDriverLawId(qpTICAccident.getDriverLawId());
		sourceQpTICAccident.setDriverLiability(qpTICAccident.getDriverLiability());
		sourceQpTICAccident.setDriverLiabilityDesc(qpTICAccident.getDriverLiabilityDesc());
		sourceQpTICAccident.setDriverLoss(qpTICAccident.getDriverLoss());
		sourceQpTICAccident.setDriverMobile(qpTICAccident.getDriverMobile());
		sourceQpTICAccident.setDriverName(qpTICAccident.getDriverName());
		sourceQpTICAccident.setDriverPhone(qpTICAccident.getDriverPhone());
		sourceQpTICAccident.setDriverRoad(qpTICAccident.getDriverRoad());
		sourceQpTICAccident.setDriverSex(qpTICAccident.getDriverSex());
		sourceQpTICAccident.setDriverSexDesc(qpTICAccident.getDriverSexDesc());
		sourceQpTICAccident.setDriverStreet(qpTICAccident.getDriverStreet());
		sourceQpTICAccident.setDriverVehicleNumber(qpTICAccident.getDriverVehicleNumber());
		sourceQpTICAccident.setDriverVehicleType(qpTICAccident.getDriverVehicleType());
		sourceQpTICAccident.setDriverVehicleTypeDesc(qpTICAccident.getDriverVehicleTypeDesc());
		sourceQpTICAccident.setEstimateLossSum(qpTICAccident.getEstimateLossSum());
		sourceQpTICAccident.setEstimateLossSumDesc(qpTICAccident.getEstimateLossSumDesc());
		sourceQpTICAccident.setEstimateLossTime(qpTICAccident.getEstimateLossTime());
		sourceQpTICAccident.setFixedLossPrice(qpTICAccident.getFixedLossPrice());
		sourceQpTICAccident.setFixedLossPriceDesc(qpTICAccident.getFixedLossPriceDesc());
		sourceQpTICAccident.setGroupId(qpTICAccident.getGroupId());
		sourceQpTICAccident.setIcEmployeeId(qpTICAccident.getIcEmployeeId());
		sourceQpTICAccident.setIcHandleNotes(qpTICAccident.getIcHandleNotes());
		sourceQpTICAccident.setIcHandleStauts(qpTICAccident.getIcHandleStauts());
		sourceQpTICAccident.setIcHandleTime(qpTICAccident.getIcHandleTime());
		sourceQpTICAccident.setIcLoginId(qpTICAccident.getIcLoginId());
		sourceQpTICAccident.setIcUserName(qpTICAccident.getIcUserName());
		sourceQpTICAccident.setInfoSource(qpTICAccident.getInfoSource());
		sourceQpTICAccident.setInsertTimeForHis(qpTICAccident.getInsertTimeForHis());
		sourceQpTICAccident.setInsured(qpTICAccident.getInsured());
		sourceQpTICAccident.setLabelType(qpTICAccident.getLabelType());
		sourceQpTICAccident.setLatitude(qpTICAccident.getLatitude());
		sourceQpTICAccident.setLiveChannelId(qpTICAccident.getLiveChannelId());
		sourceQpTICAccident.setLiveDesc(qpTICAccident.getLiveDesc());
		sourceQpTICAccident.setLiveSavedChannels(qpTICAccident.getLiveSavedChannels());
		sourceQpTICAccident.setLiveStatus(qpTICAccident.getLiveStatus());
		sourceQpTICAccident.setLockLoginId(qpTICAccident.getLockLoginId());
		sourceQpTICAccident.setLockLoginName(qpTICAccident.getLockLoginName());
		sourceQpTICAccident.setLockStatus(qpTICAccident.getLockStatus());
		sourceQpTICAccident.setLongitude(qpTICAccident.getLongitude());
		sourceQpTICAccident.setLossAssessorCode(qpTICAccident.getLossAssessorCode());
		sourceQpTICAccident.setOpenID(qpTICAccident.getOpenID());
		sourceQpTICAccident.setOperateTimeForHis(qpTICAccident.getOperateTimeForHis());
		sourceQpTICAccident.setPermissionDrive(qpTICAccident.getPermissionDrive());
		sourceQpTICAccident.setPicCount(qpTICAccident.getPicCount());
		sourceQpTICAccident.setRelaACoId(qpTICAccident.getRelaACoId());
		sourceQpTICAccident.setRelaAVehicleNumber(qpTICAccident.getRelaAVehicleNumber());
		sourceQpTICAccident.setRelaBCoId(qpTICAccident.getRelaBCoId());
		sourceQpTICAccident.setRelaBVehicleNumber(qpTICAccident.getRelaBVehicleNumber());
		sourceQpTICAccident.setRelaCCoId(qpTICAccident.getRelaCCoId());
		sourceQpTICAccident.setRelaCVehicleNumber(qpTICAccident.getRelaCVehicleNumber());
		sourceQpTICAccident.setRelaDCoId(qpTICAccident.getRelaDCoId());
		sourceQpTICAccident.setRelaDVehicleNumber(qpTICAccident.getRelaDVehicleNumber());
		sourceQpTICAccident.setReportCaseStatus(qpTICAccident.getReportCaseStatus());
		sourceQpTICAccident.setReportNo(qpTICAccident.getReportNo());
		sourceQpTICAccident.setRiskTimes(qpTICAccident.getRiskTimes());
		sourceQpTICAccident.setSurveyGroupId(qpTICAccident.getSurveyGroupId());
		sourceQpTICAccident.setSurveyNotes(qpTICAccident.getSurveyNotes());
		sourceQpTICAccident.setTpDriverCode(qpTICAccident.getTpDriverCode());
		sourceQpTICAccident.setTpHandleTime(qpTICAccident.getTpHandleTime());
		sourceQpTICAccident.setTpRemoveStatus(qpTICAccident.getTpRemoveStatus());
		sourceQpTICAccident.setUpdaterCode(qpTICAccident.getUpdaterCode());
		sourceQpTICAccident.setValidStatus(qpTICAccident.getValidStatus());
	}
}