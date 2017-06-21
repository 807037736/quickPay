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
import javax.persistence.Transient;


@Entity
@Table(name = "Qp_T_TP_Case")
public class QpTTPCase implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CASE_ID = "案件ID";
	public static final String ALIAS_CASE_SERIAL_NO = "案件编号";
	public static final String ALIAS_CASE_TIME = "CaseTime";
	public static final String ALIAS_CASE_WEATHER = "天气情况";
	public static final String ALIAS_CASE_PROVINCE = "省份";
	public static final String ALIAS_CASE_CITY = "城市";
	public static final String ALIAS_CASE_DISTRICT = "区县";
	public static final String ALIAS_CASE_ROAD = "道路";
	public static final String ALIAS_CASE_STREET = "街区";
	public static final String ALIAS_T_P_LOGIN_ID = "交警ID";
	public static final String ALIAS_T_P_USER_NAME = "交警姓名";
	public static final String ALIAS_T_P_EMPLOYEE_ID = "警员编号";
	public static final String ALIAS_T_P_HANDLE_STATUS = "交警处理结果";
	public static final String ALIAS_T_P_HANDLE_TIME = "TPHandleTime";
	public static final String ALIAS_T_P_HANDLE_NOTES = "交警处理备注";
	public static final String ALIAS_CASE_RESULT = "调解结果";
	public static final String ALIAS_CASE_NOTES = "事故详情";
	public static final String ALIAS_RELATED_PERSONS = "RelatedPersons";
	public static final String ALIAS_POLICE_NAME = "警员姓名";
	public static final String ALIAS_POLICE_EMPLOYEE_ID = "警员编号";
	public static final String ALIAS_CENTER_ID = "快处中心ID";
	public static final String ALIAS_SPAN_ID = "SpanId";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CATE_TYPE = "类型";
	/**	构造函数	**/
	public QpTTPCase() {
	}
	
	/** 主键对象 **/
	private QpTTPCaseId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "caseId", column = @Column(name = "caseId"))
    })
	public QpTTPCaseId getId() {
		return this.id;
	}

	public void setId(QpTTPCaseId id) {
		this.caseId = id.getCaseId();
		this.id = id;
	}
	
	private String caseId;
	/**属性*/
  	/** 案件编号 **/
	private String caseSerialNo;
  	/** 出险时间 **/
	private Date caseTime;
  	/** 天气情况 **/
	private String caseWeather;
  	/** 省份 **/
	private String caseProvince;
  	/** 城市 **/
	private String caseCity;
  	/** 区县 **/
	private String caseDistrict;
  	/** 道路 **/
	private String caseRoad;
  	/** 街区 **/
	private String caseStreet;
  	/** 交警ID **/
	private String tpLoginId;
  	/** 交警姓名 **/
	private String tpUserName;
  	/** 警员编号 **/
	private String tpEmployeeId;
  	/** 交警处理结果 **/
	private String tpHandleStatus;
  	/** TPHandleTime **/
	private Date tpHandleTime;
  	/** 调解结果 **/
	private String tpHandleNotes;
  	/** 案件结果 **/
	private String caseResult;
  	/** 事故详情 **/
	private String caseNotes;
	/** 事故经过 **/
	private String accidentafter;
  	/** 涉案人数 **/
	private Integer relatedPersons;
  	/** 警员姓名 **/
	private String policeName;
  	/** 警员编号 **/
	private String policeEmployeeId;
  	/** 快处中心ID **/
	private String centerId;
  	/** SpanId **/
	private String spanId;
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
	
	/**翻译字段**/
	private String caseWeatherDesc ;
	private String caseAddress ;
	
	/** 经度**/
	private String longitude;
	/** 纬度**/
	private String latitude;
	
	/** 图片的groupId**/
	private String groupId;
	
	//协助人员编号
	private String assistorId;

	//协助人员姓名
	private String assistorName;
	
	//估损人员编号
	private String estimaterId;
	
	//估损人员姓名
	private String estimaterName;
	
	//类型
	private String caseType;
	//办案民警 姓名
	private String handlePoliceName;
	//办案民警 姓名
	private String handlePoliceNO;
	
	
	/**查询字段**/
	private Date caseTimeStart;
	private Date caseTimeEnd;
	private Date tpHandleTimeStart;
	private Date tpHandleTimeEnd;
	private String driverIDType;
	private String driverIDNumber;
	private String driverName;
	private String driverVehicleNumber;
	private String coId;
	// 业务类型：1-查勘员权限；
	private String businessType;
	
	
	
	/**getter setter方法*/
	@Column(name = "CaseSerialNo")
	public String getCaseSerialNo() {
		return this.caseSerialNo;
	}
	
	public void setCaseSerialNo(String caseSerialNo) {
		this.caseSerialNo = caseSerialNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CaseTime")
	public Date getCaseTime() {
		return this.caseTime;
	}
	
	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}
	@Column(name = "CaseWeather")
	public String getCaseWeather() {
		return this.caseWeather;
	}
	
	public void setCaseWeather(String caseWeather) {
		this.caseWeather = caseWeather;
	}
	@Column(name = "CaseProvince")
	public String getCaseProvince() {
		return this.caseProvince;
	}
	
	public void setCaseProvince(String caseProvince) {
		this.caseProvince = caseProvince;
	}
	@Column(name = "CaseCity")
	public String getCaseCity() {
		return this.caseCity;
	}
	
	public void setCaseCity(String caseCity) {
		this.caseCity = caseCity;
	}
	@Column(name = "CaseDistrict")
	public String getCaseDistrict() {
		return this.caseDistrict;
	}
	
	public void setCaseDistrict(String caseDistrict) {
		this.caseDistrict = caseDistrict;
	}
	@Column(name = "CaseRoad")
	public String getCaseRoad() {
		return this.caseRoad;
	}
	
	public void setCaseRoad(String caseRoad) {
		this.caseRoad = caseRoad;
	}
	@Column(name = "CaseStreet")
	public String getCaseStreet() {
		return this.caseStreet;
	}
	
	public void setCaseStreet(String caseStreet) {
		this.caseStreet = caseStreet;
	}
	@Column(name = "TPLoginId")
	public String getTpLoginId() {
		return this.tpLoginId;
	}
	
	public void setTpLoginId(String tpLoginId) {
		this.tpLoginId = tpLoginId;
	}
	@Column(name = "TPUserName")
	public String getTpUserName() {
		return this.tpUserName;
	}
	
	public void setTpUserName(String tpUserName) {
		this.tpUserName = tpUserName;
	}
	@Column(name = "TPEmployeeId")
	public String getTpEmployeeId() {
		return this.tpEmployeeId;
	}
	
	public void setTpEmployeeId(String tpEmployeeId) {
		this.tpEmployeeId = tpEmployeeId;
	}
	@Column(name = "TPHandleStatus")
	public String getTpHandleStatus() {
		return this.tpHandleStatus;
	}
	
	public void setTpHandleStatus(String tpHandleStatus) {
		this.tpHandleStatus = tpHandleStatus;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TPHandleTime", updatable = false)
	public Date getTpHandleTime() {
		return this.tpHandleTime;
	}
	
	public void setTpHandleTime(Date tpHandleTime) {
		this.tpHandleTime = tpHandleTime;
	}
	@Column(name = "TPHandleNotes")
	public String getTpHandleNotes() {
		return this.tpHandleNotes;
	}
	
	public void setTpHandleNotes(String tpHandleNotes) {
		this.tpHandleNotes = tpHandleNotes;
	}
	@Column(name = "CaseResult")
	public String getCaseResult() {
		return this.caseResult;
	}
	
	public void setCaseResult(String caseResult) {
		this.caseResult = caseResult;
	}
	@Column(name = "CaseNotes")
	public String getCaseNotes() {
		return this.caseNotes;
	}
	
	public void setCaseNotes(String caseNotes) {
		this.caseNotes = caseNotes;
	}
	
	@Column(name = "accidentafter")
	public String getAccidentafter() {
		return accidentafter;
	}

	public void setAccidentafter(String accidentafter) {
		this.accidentafter = accidentafter;
	}

	@Column(name = "RelatedPersons")
	public Integer getRelatedPersons() {
		return this.relatedPersons;
	}
	
	public void setRelatedPersons(Integer relatedPersons) {
		this.relatedPersons = relatedPersons;
	}

	@Column(name = "PoliceName")
	public String getPoliceName() {
		return this.policeName;
	}
	
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	@Column(name = "PoliceEmployeeId")
	public String getPoliceEmployeeId() {
		return this.policeEmployeeId;
	}
	
	public void setPoliceEmployeeId(String policeEmployeeId) {
		this.policeEmployeeId = policeEmployeeId;
	}
	@Column(name = "CenterId")
	public String getCenterId() {
		return this.centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Column(name = "SpanId")
	public String getSpanId() {
		return this.spanId;
	}
	
	public void setSpanId(String spanId) {
		this.spanId = spanId;
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
	
	
	
	
	
	@Transient
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Transient
    public String getCaseWeatherDesc() {
        return caseWeatherDesc;
    }

    public void setCaseWeatherDesc(String caseWeatherDesc) {
        this.caseWeatherDesc = caseWeatherDesc;
    }

    @Transient
    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    @Transient
    public Date getCaseTimeStart() {
        return caseTimeStart;
    }

    public void setCaseTimeStart(Date caseTimeStart) {
        this.caseTimeStart = caseTimeStart;
    }

    @Transient
    public Date getCaseTimeEnd() {
        return caseTimeEnd;
    }

    public void setCaseTimeEnd(Date caseTimeEnd) {
        this.caseTimeEnd = caseTimeEnd;
    }

    @Transient
    public Date getTpHandleTimeStart() {
        return tpHandleTimeStart;
    }

    public void setTpHandleTimeStart(Date tpHandleTimeStart) {
        this.tpHandleTimeStart = tpHandleTimeStart;
    }

    @Transient
    public Date getTpHandleTimeEnd() {
        return tpHandleTimeEnd;
    }

    public void setTpHandleTimeEnd(Date tpHandleTimeEnd) {
        this.tpHandleTimeEnd = tpHandleTimeEnd;
    }

    @Transient
    public String getDriverIDType() {
        return driverIDType;
    }

    public void setDriverIDType(String driverIDType) {
        this.driverIDType = driverIDType;
    }

    @Transient
    public String getDriverIDNumber() {
        return driverIDNumber;
    }

    public void setDriverIDNumber(String driverIDNumber) {
        this.driverIDNumber = driverIDNumber;
    }

    @Transient
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Transient
    public String getDriverVehicleNumber() {
        return driverVehicleNumber;
    }

    public void setDriverVehicleNumber(String driverVehicleNumber) {
        this.driverVehicleNumber = driverVehicleNumber;
    }

    @Transient
    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    @Transient
	public String getBusinessType() {
		return businessType;
	}
    

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Column(name = "longitude", updatable = false )
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude", updatable = false )
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "ASSISTORID")
	public String getAssistorId() {
		return assistorId;
	}

	public void setAssistorId(String assistorId) {
		this.assistorId = assistorId;
	}
	
	@Column(name = "ASSISTORNAME")
	public String getAssistorName() {
		return assistorName;
	}

	public void setAssistorName(String assistorName) {
		this.assistorName = assistorName;
	}
	@Column(name = "ESTIMATERID")
	public String getEstimaterId() {
		return estimaterId;
	}

	public void setEstimaterId(String estimaterId) {
		this.estimaterId = estimaterId;
	}
	@Column(name = "ESTIMATERNAME")
	public String getEstimaterName() {
		return estimaterName;
	}

	public void setEstimaterName(String estimaterName) {
		this.estimaterName = estimaterName;
	}
	
	private Date dingzeTime;
	private Date dingsunTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DINGZE_TIME")
	public Date getDingzeTime() {
		return dingzeTime;
	}

	public void setDingzeTime(Date dingzeTime) {
		this.dingzeTime = dingzeTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DINGSUN_TIME")
	public Date getDingsunTime() {
		return dingsunTime;
	}

	public void setDingsunTime(Date dingsunTime) {
		this.dingsunTime = dingsunTime;
	}

	@Column(name = "CASETYPE", updatable = false )
	public String getCaseType() {
	    return caseType;
	}

	public void setCaseType(String caseType) {
	    this.caseType = caseType;
	}

	@Column(name = "handlePoliceName")
	public String getHandlePoliceName() {
		return handlePoliceName;
	}

	public void setHandlePoliceName(String handlePoliceName) {
		this.handlePoliceName = handlePoliceName;
	}

	@Column(name = "handlePoliceNO")
	public String getHandlePoliceNO() {
		return handlePoliceNO;
	}

	public void setHandlePoliceNO(String handlePoliceNO) {
		this.handlePoliceNO = handlePoliceNO;
	}
	
}