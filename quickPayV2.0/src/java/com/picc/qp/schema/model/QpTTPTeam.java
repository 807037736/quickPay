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
@Table(name = "Qp_T_TP_Team")
public class QpTTPTeam implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_TEAM_ID = "大队ID";
	public static final String ALIAS_TEAM_NAME = "交警大队";
	public static final String ALIAS_TEAM_ORDER = "序号";
	public static final String ALIAS_TEAM_PHONE = "联系电话";
	public static final String ALIAS_TEAM_NOTES = "备注信息";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_ISHIGHWAY = "高速或地市";
	
	
	/**	构造函数	**/
	public QpTTPTeam() {
	}
	
	/** 主键对象 **/
	private QpTTPTeamId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "teamId", column = @Column(name = "teamId"))
    })
	public QpTTPTeamId getId() {
		return this.id;
	}

	public void setId(QpTTPTeamId id) {
		this.id = id;
	}
	
	
	/**属性*/
	
	private String teamId;
  	/** 交警大队 **/
	private String teamName;
  	/** 序号 **/
	private Integer teamOrder;
  	/** 联系电话 **/
	private String teamPhone;
  	/** 备注信息 **/
	private String teamNotes;
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
	/** 城市Id **/
	private String cityId;
	/** 城市名称 **/
	private String cityName;
	/** 地市或高速 **/
	private String IsHighway;

	/**getter setter方法*/
	@Column(name = "TeamName")
	public String getTeamName() {
		return this.teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Column(name = "TeamOrder")
	public Integer getTeamOrder() {
		return this.teamOrder;
	}
	
	public void setTeamOrder(Integer teamOrder) {
		this.teamOrder = teamOrder;
	}
	@Column(name = "TeamPhone")
	public String getTeamPhone() {
		return this.teamPhone;
	}
	
	public void setTeamPhone(String teamPhone) {
		this.teamPhone = teamPhone;
	}
	@Column(name = "TeamNotes")
	public String getTeamNotes() {
		return this.teamNotes;
	}
	
	public void setTeamNotes(String teamNotes) {
		this.teamNotes = teamNotes;
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name = "IsHighway")
	public String getIsHighway() {
		return IsHighway;
	}
	
	public void setIsHighway(String IsHighway) {
		this.IsHighway = IsHighway;
	}
    
	@Transient
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}	
}