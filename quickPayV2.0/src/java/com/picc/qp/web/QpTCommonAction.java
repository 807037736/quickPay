/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.web.Struts2Action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.schema.model.QpTTPTeam;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCOMRoadService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.facade.IQpTTPTeamService;
import com.picc.um.schema.vo.SessionUser;


public class QpTCommonAction extends Struts2Action{
	
	private IQpTCOMProvinceService qpTCOMProvinceService;
	
	private IQpTCOMCityService qpTCOMCityService;	
	
	private IQpTCOMDistrictService qpTCOMDistrictService;
	
	private IQpTCOMRoadService qpTCOMRoadService;
	
	private IQpTTPLawService qpTTPLawService;
	
	private IQpTTPTeamService qpTTPTeamService;	
	
	public void setQpTTPTeamService(IQpTTPTeamService qpTTPTeamService) {
		this.qpTTPTeamService = qpTTPTeamService;
	}

	public IQpTTPTeamService getQpTTPTeamService() {
		return qpTTPTeamService;
	}

	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}

	public void setQpTCOMProvinceService(
			IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}
	
	public IQpTCOMDistrictService getQpTCOMDistrictService() {
		return qpTCOMDistrictService;
	}

	public void setQpTCOMDistrictService(
			IQpTCOMDistrictService qpTCOMDistrictService) {
		this.qpTCOMDistrictService = qpTCOMDistrictService;
	}

	public IQpTCOMRoadService getQpTCOMRoadService() {
		return qpTCOMRoadService;
	}

	public void setQpTCOMRoadService(IQpTCOMRoadService qpTCOMRoadService) {
		this.qpTCOMRoadService = qpTCOMRoadService;
	}
	
	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}
	
	/**
	 * 获取交警列表
	 * @return
	 */
	public String getTeamList() {
		try {
			HttpServletRequest httpServletRequest = this.getRequest();
			String isHighway = httpServletRequest.getParameter("isHighway");
			QpTTPTeam qpTTPTeam = new QpTTPTeam();
			qpTTPTeam.setIsHighway(isHighway);
			List<QpTTPTeam> qpTTPTeamList = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam);
			for(QpTTPTeam t:qpTTPTeamList){
				t.setTeamId(t.getId().getTeamId());
			}
			
			this.writeJson(qpTTPTeamList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}

	/**
	 * 获取省列表
	 * @return
	 */
	public String getProvinceList() {
		try {
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			List<QpTCOMProvince> qpTCOMProvinceList = qpTCOMProvinceService.findByQpTCOMProvince(qpTCOMProvince);
			this.writeJson(qpTCOMProvinceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}

	/**
	 * 获取城市列表
	 * @return
	 */
	public String getCityList() {
		try {
			String provId = this.getRequest().getParameter("provId");
			QpTCOMCity qpTCOMCity = new QpTCOMCity();
			qpTCOMCity.setProvId(provId);
			List<QpTCOMCity> qpTCOMCityList = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity);
			this.writeJson(qpTCOMCityList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}

	/**
	 * 获取区县列表
	 * @return
	 */
	public String getDistrictList() {
		try {
		    String districtId = this.getRequest().getParameter("address");
		    //list="#@java.util.LinkedHashMap@{'01':'其他','02':'地市交警','07':'高速交警','03':'地市辅警','08':'高速辅警','04':'查勘员','05':'理赔经理','06':'保协管理人员' }" 
		    Object object = this.getSession().getAttribute("SessionUser");
		    if(object != null){
    		    	SessionUser sessionUser = (SessionUser)object;
    		    	String sort = sessionUser.getUserSort();
			String cityId = this.getRequest().getParameter("cityId");
    			QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
    			qpTCOMDistrict.setCityId(cityId);
    			if(districtId != null && "1".equals(districtId)){//如果是当事人地址 就不查询高速公路
    			    qpTCOMDistrict.setType("0");
    			}else if(sort != null && !"".equals(sort) && ("02".equals(sort) || "03".equals(sort))){
    			    qpTCOMDistrict.setType("0");
    			}else if(sort != null && !"".equals(sort) && ("07".equals(sort) || "08".equals(sort))){
    			    qpTCOMDistrict.setType("1");
    			}
    			List<QpTCOMDistrict> qpTCOMDistrictList = qpTCOMDistrictService.findByQpTCOMDistrict(qpTCOMDistrict);
    			this.writeJson(qpTCOMDistrictList);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}


	/**
	 * 获取道路列表
	 * @return
	 */
	public String getRoadList() {
		try {
			String districtId = this.getRequest().getParameter("districtId");
			QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
			qpTCOMRoad.setDistrictId(districtId);
			List<QpTCOMRoad> qpTCOMRoadList = qpTCOMRoadService.findByQpTCOMRoad(qpTCOMRoad);
			this.writeJson(qpTCOMRoadList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	/**
	 * 获取违反法律法规列表
	 * @return
	 */
	public String getLawList() {
		try {
			// 违反法律法规
			List<QpTTPLaw> qpTTPLawList = qpTTPLawService.findByQpTTPLaw(new QpTTPLaw());
			this.writeJson(qpTTPLawList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	
	/**
	 * 根据主键lawId获取QpTTPLaw
	 * @return
	 */
	public String getQpTTPLawByPK() {
		try {
			String lawId = this.getRequest().getParameter("lawId");
			QpTTPLawId ttpLawId = new QpTTPLawId();
			ttpLawId.setLawId(lawId);
			// 违反法律法规
			QpTTPLaw qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(ttpLawId);
			if(qpTTPLaw != null) {
				this.writeJSONMsg(qpTTPLaw.getLawName());
			}else {
				this.writeJSONMsg("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	
	
}
