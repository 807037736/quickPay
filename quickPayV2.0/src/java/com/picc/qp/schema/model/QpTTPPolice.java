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
@Table(name = "Qp_T_TP_Police")
public class QpTTPPolice implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_POLICE_ID = "交警ID";
	public static final String ALIAS_POLICE_NAME = "交警姓名";
	public static final String ALIAS_TEAM_ID = "所属大队";
	public static final String ALIAS_EMPLOYEE_ID = "警员编号";
	public static final String ALIAS_POLICE_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTTPPolice() {
	}
	
	/** 主键对象 **/
	private QpTTPPoliceId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "policeId", column = @Column(name = "policeId"))
    })
	public QpTTPPoliceId getId() {
		return this.id;
	}

	public void setId(QpTTPPoliceId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 交警姓名 **/
	private String policeName;
  	/** 所属大队 **/
	private String teamId;
  	/** 警员编号 **/
	private String employeeId;
  	/** 序号 **/
	private Long policeOrder;
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
	@Column(name = "PoliceName")
	public String getPoliceName() {
		return this.policeName;
	}
	
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	@Column(name = "TeamId")
	public String getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	@Column(name = "EmployeeId")
	public String getEmployeeId() {
		return this.employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	@Column(name = "PoliceOrder")
	public Long getPoliceOrder() {
		return this.policeOrder;
	}
	
	public void setPoliceOrder(Long policeOrder) {
		this.policeOrder = policeOrder;
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