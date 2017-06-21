package com.picc.qp.schema.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.picc.qp.util.Constants;

/**
 * 微信临时案件信息
 * @author ff
 * 2016年7月4日
 */
@Entity
@Table(name = "wx_case")
public class WxCase  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private WxCaseId id;
	private Date createdate;//创建时间	
	private Date accidentdate;
	private Date modifydate;//修改时间
	private String address;//案件地址
	private String weathercode;//天气
	private String accidentform;//事故形态
	private String accidentafter;//事故经过
	private String status;//状态
	private String createcode;//创建人code
	private String groupid;//图片组好
	private String realCaseId;
	private String province;
	private String city;
	private String area;
	private String isDue;//有无争议
	private String caseLat;//用于真是案件的经纬度
	private String caseLng;//用于真是案件的经纬度
	
	@EmbeddedId
	@AttributeOverrides( {
	    @AttributeOverride(name = "id", column = @Column(name = "id"))
	})
	public WxCaseId getId() {
	    return id;
	}
	public void setId(WxCaseId id) {
	    this.id = id;
	}
	
	@Column(name = "createdate")
	public Date getCreatedate() {
	    return createdate;
	}
	
	public void setCreatedate(Date createdate) {
	    this.createdate = createdate;
	}
	@Column(name = "accidentdate")
	public Date getAccidentdate() {
	    return accidentdate;
	}
	public void setAccidentdate(Date accidentdate) {
	    this.accidentdate = accidentdate;
	}
	@Column(name = "modifydate")
	public Date getModifydate() {
	    return modifydate;
	}
	public void setModifydate(Date modifydate) {
	    this.modifydate = modifydate;
	}
	@Column(name = "address")
	public String getAddress() {
	    return address;
	}
	public void setAddress(String address) {
	    this.address = address;
	}
	@Column(name = "weathercode")
	public String getWeathercode() {
	    return weathercode;
	}
	public void setWeathercode(String weathercode) {
	    this.weathercode = weathercode;
	}
	@Column(name = "accidentform")
	public String getAccidentform() {
	    return accidentform;
	}
	public void setAccidentform(String accidentform) {
	    this.accidentform = accidentform;
	}
	@Column(name = "accidentafter")
	public String getAccidentafter() {
	    return accidentafter;
	}
	public void setAccidentafter(String accidentafter) {
	    this.accidentafter = accidentafter;
	}
	@Column(name = "status")
	public String getStatus() {
	    return status;
	}
	public void setStatus(String status) {
	    this.status = status;
	}
	@Column(name = "createcode")
	public String getCreatecode() {
	    return createcode;
	}
	public void setCreatecode(String createcode) {
	    this.createcode = createcode;
	}
	@Column(name = "groupid")
	public String getGroupid() {
	    return groupid;
	}
	public void setGroupid(String groupid) {
	    this.groupid = groupid;
	}
	
	@Column(name = "realCaseId")
	public String getRealCaseId() {
		return realCaseId;
	}
	
	public void setRealCaseId(String realCaseId) {
		this.realCaseId = realCaseId;
	}
	
	@Column(name = "province")
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "isDue")
	public String getIsDue() {
		return isDue;
	}
	
	public void setIsDue(String isDue) {
		this.isDue = isDue;
	}
	
	@Column(name = "caseLat")
	public String getCaseLat() {
		return caseLat;
	}
	public void setCaseLat(String caseLat) {
		this.caseLat = caseLat;
	}
	
	@Column(name = "caseLng")
	public String getCaseLng() {
		return caseLng;
	}
	public void setCaseLng(String caseLng) {
		this.caseLng = caseLng;
	}
	/**
	 * 临时案件转为真实案件(创建)
	 * @return
	 */
	public QpTTPCase changeToCaseFromWxCase() {
		QpTTPCase caseinfo = new QpTTPCase();
		QpTTPCaseId caseinfoid = new QpTTPCaseId();
		caseinfoid.setCaseId(this.getRealCaseId());
		caseinfo.setId(caseinfoid);
		caseinfo.setCaseTime(this.getAccidentdate());
		caseinfo.setCaseStreet(this.getAddress());// 地点
		caseinfo.setCaseWeather(this.getWeathercode());
		caseinfo.setCaseNotes(this.getAccidentform());
		// 默认设置
		caseinfo.setCenterId(Constants.getParams().get("centerID").toString());
		caseinfo.setTpHandleStatus("1");// 待定责
		caseinfo.setCaseProvince("430000");
		caseinfo.setCaseCity(Constants.getParams().get("city").toString()); // 长沙
		if (Constants.getChangshaAreaMap().containsKey(this.getArea())) {
			caseinfo.setCaseDistrict(Constants.getChangshaAreaMap().get(this.getArea()));// 取真实的地点所属区
		}
		caseinfo.setTpHandleTime(new Date());
		//发现微信界面经纬度填反了
		caseinfo.setLatitude(this.getCaseLng());
		caseinfo.setLongitude(this.getCaseLat());
		caseinfo.setAccidentafter(this.getAccidentafter());
		return caseinfo;
	}
	
	/**
	 * 临时案件转为真实案件(修改)
	 * @return
	 */
	public QpTTPCase changeToCaseFromWxCase(QpTTPCase qpTTPCase) {
		if (qpTTPCase == null) {
			return qpTTPCase;
		}
		QpTTPCase caseinfo = qpTTPCase;
		QpTTPCaseId caseinfoid = new QpTTPCaseId();
		caseinfoid.setCaseId(this.getRealCaseId());
		caseinfo.setId(caseinfoid);
		caseinfo.setCaseTime(this.getAccidentdate());
		caseinfo.setCaseStreet(this.getAddress());
		caseinfo.setCaseWeather(this.getWeathercode());
		caseinfo.setCaseNotes(this.getAccidentform());
		//发现微信界面经纬度填反了
		caseinfo.setLatitude(this.getCaseLng());
		caseinfo.setLongitude(this.getCaseLat());
		caseinfo.setAccidentafter(this.getAccidentafter());
		return caseinfo;
	}
	
}
