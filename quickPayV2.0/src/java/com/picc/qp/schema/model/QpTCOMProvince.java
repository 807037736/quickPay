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
@Table(name = "Qp_T_COM_Province")
public class QpTCOMProvince implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_PROV_ID = "省份ID";
	public static final String ALIAS_PROV_NAME = "省份名称";
	public static final String ALIAS_PROV_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTCOMProvince() {
	}
	
	/** 主键对象 **/
	private QpTCOMProvinceId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "provId", column = @Column(name = "provId"))
    })
	public QpTCOMProvinceId getId() {
		return this.id;
	}

	public void setId(QpTCOMProvinceId id) {
		this.provId = id.getProvId();
		this.id = id;
	}
	
	private String provId;
	/**属性*/
  	/** 省份名称 **/
	private String provName;
  	/** 序号 **/
	private Integer provOrder;
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
	
	/** 省份车牌前缀 **/
	private String provVehicleNumPre;
	
	/**getter setter方法*/
	@Column(name = "ProvName")
	public String getProvName() {
		return this.provName;
	}
	
	public void setProvName(String provName) {
		this.provName = provName;
	}
	@Column(name = "ProvOrder")
	public Integer getProvOrder() {
		return this.provOrder;
	}
	
	public void setProvOrder(Integer provOrder) {
		this.provOrder = provOrder;
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

	@Column(name = "ProvVehicleNumPre")
	public String getProvVehicleNumPre() {
        return provVehicleNumPre;
    }

    public void setProvVehicleNumPre(String provVehicleNumPre) {
        this.provVehicleNumPre = provVehicleNumPre;
    }

    @Transient
	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}
	
}