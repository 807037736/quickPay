/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import java.math.BigDecimal;
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
@Table(name = "Qp_T_TP_Fast_Center")
public class QpTTPFastCenter implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CENTER_ID = "中心ID";
	public static final String ALIAS_CENTER_NAME = "名称";
	public static final String ALIAS_CENTER_NUMBER = "序号";
	public static final String ALIAS_CENTER_PHONE = "电话";
	public static final String ALIAS_CENTER_NOTES = "备注";
	public static final String ALIAS_CITY_ID = "所属城市";
	public static final String ALIAS_CUR_YEAR = "年份";
	public static final String ALIAS_CUR_SERIAL_NO = "编码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_READNUM = "认字号编号";
	public static final String ALIAS_CITIES = "所属地市";
	
	
	/**	构造函数	**/
	public QpTTPFastCenter() {
	}
	
	/** 主键对象 **/
	private QpTTPFastCenterId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "centerId", column = @Column(name = "centerId"))
    })
	public QpTTPFastCenterId getId() {
		return this.id;
	}

	public void setId(QpTTPFastCenterId id) {
		this.centerId = id.getCenterId();
		this.id = id;
	}
	
	private String centerId;
	
	@Transient
	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	/**属性*/
  	/** 名称 **/
	private String centerName;
  	/** 序号 **/
	private BigDecimal centerNumber;
  	/** 电话 **/
	private String centerPhone;
	/**
	 * 快赔点经度
	 */
	private String longitude;
	/**
	 * 快赔点纬度
	 */
	private String latitude;
  	/** 备注 **/
	private String centerNotes;
  	/** 所属城市 **/
	private String cityId;
  	/** 年份 **/
	private String curYear;
  	/** 编码 **/
	private String curSerialNo;
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
	/** 所属城市姓名 **/
	private String cityName;
	/** 认字号编号 **/
	private String readNum;
	/** 所属地市 **/
	private String cities;
	/**getter setter方法*/
	@Column(name = "CenterName")
	public String getCenterName() {
		return this.centerName;
	}
	
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	@Column(name = "CenterNumber")
	public BigDecimal getCenterNumber() {
		return this.centerNumber;
	}
	
	public void setCenterNumber(BigDecimal centerNumber) {
		this.centerNumber = centerNumber;
	}
	@Column(name = "CenterPhone")
	public String getCenterPhone() {
		return this.centerPhone;
	}
	
	public void setCenterPhone(String centerPhone) {
		this.centerPhone = centerPhone;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "CenterNotes")
	public String getCenterNotes() {
		return this.centerNotes;
	}
	
	public void setCenterNotes(String centerNotes) {
		this.centerNotes = centerNotes;
	}
	@Column(name = "CityId")
	public String getCityId() {
		return this.cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name = "CurYear")
	public String getCurYear() {
		return this.curYear;
	}
	
	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}
	@Column(name = "CurSerialNo")
	public String getCurSerialNo() {
		return this.curSerialNo;
	}
	
	public void setCurSerialNo(String curSerialNo) {
		this.curSerialNo = curSerialNo;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name="ReadNum")
	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
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
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name = "cities")
	public String getCities() {
	    return cities;
	}

	public void setCities(String cities) {
	    this.cities = cities;
	}
		
}