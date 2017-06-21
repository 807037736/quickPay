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
@Table(name = "Qp_T_COM_City")
public class QpTCOMCity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CITY_ID = "城市ID";
	public static final String ALIAS_CITY_NAME = "城市名称";
	public static final String ALIAS_PROV_ID = "所属省份ID";
	public static final String ALIAS_CITY_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTCOMCity() {
	}
	
	/** 主键对象 **/
	private QpTCOMCityId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "cityId", column = @Column(name = "cityId"))
    })
	public QpTCOMCityId getId() {
		return this.id;
	}

	public void setId(QpTCOMCityId id) {
		this.cityId = id.getCityId();
		this.id = id;
	}
	
	
	/**属性*/
  	/** 城市名称 **/
	private String cityName;
  	/** 所属省份ID **/
	private String provId;
  	/** 序号 **/
	private Integer cityOrder;
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
	
	/** 城市车牌前缀**/
	private String cityVehicleNumPre;
	
	private String cityId;
	
	/**getter setter方法*/
	@Column(name = "CityName")
	public String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name = "ProvId")
	public String getProvId() {
		return this.provId;
	}
	
	public void setProvId(String provId) {
		this.provId = provId;
	}
	@Column(name = "CityOrder")
	public Integer getCityOrder() {
		return this.cityOrder;
	}
	
	public void setCityOrder(Integer cityOrder) {
		this.cityOrder = cityOrder;
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
	
	@Column(name = "CityVehicleNumPre")
	public String getCityVehicleNumPre() {
        return cityVehicleNumPre;
    }

    public void setCityVehicleNumPre(String cityVehicleNumPre) {
        this.cityVehicleNumPre = cityVehicleNumPre;
    }

    @Transient
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
		
}