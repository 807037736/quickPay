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
@Table(name = "Qp_T_COM_District")
public class QpTCOMDistrict implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_DISTRICT_ID = "区县ID";
	public static final String ALIAS_DISTRICT_NAME = "区县名称";
	public static final String ALIAS_CITY_ID = "所属城市ID";
	public static final String ALIAS_DISTRICT_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_TYPE = "类型";// 0 城区   1高速
	
	
	/**	构造函数	**/
	public QpTCOMDistrict() {
	}
	
	/** 主键对象 **/
	private QpTCOMDistrictId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "districtId", column = @Column(name = "districtId"))
    })
	public QpTCOMDistrictId getId() {
		return this.id;
	}

	public void setId(QpTCOMDistrictId id) {
		this.districtId = id.getDistrictId();
		this.id = id;
	}
	
	private String districtId ;
	/**属性*/
  	/** 区县名称 **/
	private String districtName;
  	/** 所属城市ID **/
	private String cityId;
  	/** 序号 **/
	private Integer districtOrder;
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
	/** 类型 **/
	private String type;
	
	/**getter setter方法*/
	@Column(name = "DistrictName")
	public String getDistrictName() {
		return this.districtName;
	}
	
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name = "CityId")
	public String getCityId() {
		return this.cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name = "DistrictOrder")
	public Integer getDistrictOrder() {
		return this.districtOrder;
	}
	
	public void setDistrictOrder(Integer districtOrder) {
		this.districtOrder = districtOrder;
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
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Column(name = "type")
	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}
	
}