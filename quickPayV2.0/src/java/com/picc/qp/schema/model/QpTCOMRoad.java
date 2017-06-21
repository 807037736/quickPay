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
@Table(name = "Qp_T_COM_Road")
public class QpTCOMRoad implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ROAD_ID = "道路ID";
	public static final String ALIAS_ROAD_NAME = "道路名称";
	public static final String ALIAS_DISTRICT_ID = "所属区县ID";
	public static final String ALIAS_ROAD_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTCOMRoad() {
	}
	
	/** 主键对象 **/
	private QpTCOMRoadId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "roadId", column = @Column(name = "roadId"))
    })
	public QpTCOMRoadId getId() {
		return this.id;
	}

	public void setId(QpTCOMRoadId id) {
		this.roadId = id.getRoadId();
		this.id = id;
	}
	
	private String roadId;
	/**属性*/
  	/** 道路名称 **/
	private String roadName;
  	/** 所属区县ID **/
	private String districtId;
  	/** 序号 **/
	private Integer roadOrder;
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
	@Column(name = "RoadName")
	public String getRoadName() {
		return this.roadName;
	}
	
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	@Column(name = "DistrictId")
	public String getDistrictId() {
		return this.districtId;
	}
	
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	@Column(name = "RoadOrder")
	public Integer getRoadOrder() {
		return this.roadOrder;
	}
	
	public void setRoadOrder(Integer roadOrder) {
		this.roadOrder = roadOrder;
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
	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	
	
}