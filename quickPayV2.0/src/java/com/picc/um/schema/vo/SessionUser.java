package com.picc.um.schema.vo;

import ins.framework.utils.StringUtils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.utils.AppConfig;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTUser;

@SuppressWarnings("serial")
public class SessionUser extends UmTUser {
	
	private static Logger logger = LoggerFactory.getLogger(SessionUser.class);
	
	private static String sigleCityCode = null;							//单列市配置的字符串
	
	public SessionUser() {
	}
	
	public SessionUser(UmTUser umTUser){
		super.setAge(umTUser.getAge());
		super.setComCode(umTUser.getComCode());
		super.setBirthday(umTUser.getBirthday());
		super.setCreatorCode(umTUser.getCreatorCode());
		super.setEmail(umTUser.getEmail());
		super.setFaxNumber(umTUser.getFaxNumber());
		super.setFlag(umTUser.getFlag());
		super.setId(umTUser.getId());
		super.setIdentityNumber(umTUser.getIdentityNumber());
		super.setImage(umTUser.getImage());
		super.setInsertTimeForHis(umTUser.getInsertTimeForHis());
		super.setInterests(umTUser.getInterests());
		super.setMobile(umTUser.getMobile());
		super.setOperateTimeForHis(umTUser.getOperateTimeForHis());
		super.setPostAddress(umTUser.getPostAddress());
		super.setRemark(umTUser.getRemark());
		super.setSex(umTUser.getSex());
		super.setTelePhone(umTUser.getTelePhone());
		super.setUnit(umTUser.getUnit());
		super.setUnitAddress(umTUser.getUnitAddress());
		super.setUpdaterCode(umTUser.getUpdaterCode());
		super.setUserName(umTUser.getUserName());
		super.setUserSort(umTUser.getUserSort());
		super.setUserType(umTUser.getUserType());
		super.setValidStatus(umTUser.getValidStatus());
		this.userCode = umTUser.getId().getUserCode();
		this.comId = getComIdByComCode(umTUser.getComCode());
//		this.comId = umTUser.getComCode();
		
		super.setEmployeeId(umTUser.getEmployeeId());
		super.setPoliceTeamId(umTUser.getPoliceTeamId());
		super.setCenterId(umTUser.getCenterId());
		super.setProvince(umTUser.getProvince());
		super.setCity(umTUser.getCity());
		super.setDistrict(umTUser.getDistrict());
		super.setRoad(umTUser.getRoad());
		super.setStreet(umTUser.getStreet());
	}
	
	private String userCode;
	
	private String comId;
	
	private String userPass;
	
	/**用户登录IP**/
	private String ipAddress;
	
	private List<UmTGroup> groupCodeList; 

	public List<UmTGroup> getGroupCodeList() {
		return groupCodeList;
	}

	public void setGroupCodeList(List<UmTGroup> groupCodeList) {
		this.groupCodeList = groupCodeList;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}	
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setComId(String comId) {
		this.comId = getComIdByComCode(getComCode());
	}
	
	public String getComId() {
		return comId;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public static List<String> getGroupCode(String userCode){
		
		return null;
	}
	
	public static String getComIdByComCode(String comCode){
		if(StringUtils.isNotEmpty(comCode)&&comCode.length()==8){
			if(StringUtils.isEmpty(sigleCityCode))
				sigleCityCode = AppConfig.get("sysconst.SINGLECITY");
			if(StringUtils.isNotEmpty(sigleCityCode)){
				if(sigleCityCode.indexOf(comCode.substring(0, 4))!=-1){					//截取机构代码前4位进行判断
					return comCode.substring(0, 4).concat("0000");									//直接返回单列市接后4位代码
				}else {
					return comCode.substring(0, 2).concat("000000");
				}
			}else {
				logger.warn("没有读取到Common-SINGLECITY常量");
			}
		}else {
			logger.warn("传入的机构代码为空或不符合8位代码格式");
		}
		return null;
	}
	
	public static String getComId4ByComCode(String comCode){
		if(StringUtils.isNotEmpty(comCode)&&comCode.length()==8){
			if(StringUtils.isEmpty(sigleCityCode))
				sigleCityCode = AppConfig.get("sysconst.SINGLECITY");
			if(StringUtils.isNotEmpty(sigleCityCode)){
				if(sigleCityCode.indexOf(comCode.substring(0, 4))!=-1){					//截取机构代码前4位进行判断
					return comCode.substring(0, 4);									//直接返回单列市接后4位代码
				}else {
					return comCode.substring(0, 2).concat("00");
				}
			}else {
				logger.warn("没有读取到Common-SINGLECITY常量");
			}
		}else {
			logger.warn("传入的机构代码为空或不符合8位代码格式");
		}
		return null;
	}
}
