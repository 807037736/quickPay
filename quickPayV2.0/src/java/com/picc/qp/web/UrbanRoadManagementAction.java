package com.picc.qp.web;

import ins.framework.web.Struts2Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCOMRoadService;
import com.picc.qp.service.facade.UrbanRoadManagementService;

public class UrbanRoadManagementAction extends Struts2Action{
	private IQpTCOMProvinceService qpTCOMProvinceService = null;	
	private IQpTCOMCityService qpTCOMCityService = null;
	private IQpTCOMDistrictService qpTCOMDistrictService = null;
	private IQpTCOMRoadService qpTCOMRoadService =null;
	private UrbanRoadManagementService urbanRoadManagementService = null;
	
	public String queryUrbanRoad(){
		List provinceList = new ArrayList();
		List cityList = new ArrayList();
		List districtList = new ArrayList();
		List roadList = new ArrayList();
		String strJson = "";
		
		try {
			provinceList = qpTCOMProvinceService.findAllInfo();
			cityList = qpTCOMCityService.findAllInfo();
			districtList = qpTCOMDistrictService.findAllInfo();
			roadList = qpTCOMRoadService.findAllInfo();
			strJson = urbanRoadManagementService.queryProvinceJson(provinceList);
			/*urbanRoadManagementService.queryUrbanRoadInfo(provinceList,cityList,districtList,roadList);*/
		} catch (Exception e) {

			e.printStackTrace();
			logger.error("", e);
		}
		this.writeJson(strJson);
		return NONE;
	}
	
	public String queryChildrenJson(){
		List provinceList = new ArrayList();
		List cityList = new ArrayList();
		List districtList = new ArrayList();
		String strJson = null;
		try {
			provinceList = qpTCOMProvinceService.findAllInfo();
			cityList = qpTCOMCityService.findAllInfo();
			districtList = qpTCOMDistrictService.findAllInfo();
			String id= this.getRequest().getParameter("id");
			strJson = urbanRoadManagementService.queryChildrenJson(provinceList, cityList, districtList, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		this.writeJson(strJson);
		return NONE;
		
	}
	
	
	
	public String  nullMethodToPage(){
		return SUCCESS;
		
	}
	public String nullMethod(){
		String id = this.getRequest().getParameter("nodeId");
		String fatherNodeName = this.getRequest().getParameter("fatherNodeName");
		String addModle = this.getRequest().getParameter("addModle");
		String addNodeName = this.getRequest().getParameter("addNodeName");
		
		this.getRequest().setAttribute("nodeId", id);
		this.getRequest().setAttribute("fatherNodeName", fatherNodeName);
		this.getRequest().setAttribute("addModle", addModle);
		this.getRequest().setAttribute("addNodeName", addNodeName);
		this.getRequest().setAttribute("type", 0);
		return SUCCESS;
	}
	
	public String appendNode(){
		Map<String, String> msg = new HashMap<String, String>();
		
		String id = this.getRequest().getParameter("nodeId");
		String name = this.getRequest().getParameter("name");
		String carpai = this.getRequest().getParameter("carpai");
		String validStatus = this.getRequest().getParameter("validStatus");
		String order = this.getRequest().getParameter("order");
		String nodelev = this.getRequest().getParameter("nodelev");
		String fatherid = this.getRequest().getParameter("fatherid");
		try {
			urbanRoadManagementService.addNode(id, name, carpai, validStatus,order,nodelev,fatherid);
			msg.put("Y", "处理成功");
			this.writeJson(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	
	public String deleteNode(){
		Map<String, String> msg = new HashMap<String, String>();
		
		String id = this.getRequest().getParameter("id");
		String nodelev = this.getRequest().getParameter("nodelev");
		String result = null;
		try {
			result = urbanRoadManagementService.deleteNode(id,nodelev);
			msg.put("Y", "处理成功");
			this.writeJson(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
//		if(result.equals(SUCCESS)){
//			return SUCCESS;
//		}else{
//			return "failure";
//		}
		return NONE;
		
	}
	
	public String prompt(){
		String id = this.getRequest().getParameter("nodeId");
		this.getRequest().setAttribute("id", id);
		return SUCCESS;
	}
	
	public String prepareEdit(){
		String id = this.getRequest().getParameter("id");
		String nodelev = this.getRequest().getParameter("nodelev");
		String nodeName = this.getRequest().getParameter("nodeName");
		try {
			Object obj = urbanRoadManagementService.prepareEdit(id,nodelev);
			if(nodelev.equals("1")){
				QpTCOMProvince qpTCOMProvince = (QpTCOMProvince) obj;
				this.getRequest().setAttribute("qpTCOMProvince", qpTCOMProvince);
				this.getRequest().setAttribute("type",2);
				this.getRequest().setAttribute("nodelev",nodelev);
				return SUCCESS;
			}else if(nodelev.equals("2")){
				QpTCOMCity qpTCOMCity =  (QpTCOMCity) obj;
				String provId = qpTCOMCity.getProvId();
				this.getRequest().setAttribute("qpTCOMCity", qpTCOMCity);
				this.getRequest().setAttribute("type",4);
				this.getRequest().setAttribute("nodelev",nodelev);
				return SUCCESS;
			}else if(nodelev.equals("3")){
				QpTCOMDistrict qpTCOMDistrict = (QpTCOMDistrict) obj;
				this.getRequest().setAttribute("qpTCOMDistrict", qpTCOMDistrict);
				this.getRequest().setAttribute("type",6);
				this.getRequest().setAttribute("nodelev",nodelev);
				return SUCCESS;
			}else if(nodelev.equals("4")){
				QpTCOMRoad qpTCOMRoad = (QpTCOMRoad) obj;
				this.getRequest().setAttribute("qpTCOMRoad", qpTCOMRoad);
				this.getRequest().setAttribute("type",9);
				this.getRequest().setAttribute("nodelev",nodelev);
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	public String updateNode(){
		String id = this.getRequest().getParameter("nodeId");
		String name = this.getRequest().getParameter("name");
		String carpai = this.getRequest().getParameter("carpai");
		String validStatus = this.getRequest().getParameter("validStatus");
		int order  = Integer.valueOf(this.getRequest().getParameter("order"));
		String nodelev = this.getRequest().getParameter("nodelev");
		String result=null;
		try {
			result = urbanRoadManagementService.updateNode(id, name, carpai, validStatus, order,nodelev);
			if(result.equals("success")){
				PrintWriter printWriter=this.getResponse().getWriter();
				printWriter.write("Y");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
		
	}
	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}

	public void setQpTCOMProvinceService(
			IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
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

	public UrbanRoadManagementService getUrbanRoadManagementService() {
		return urbanRoadManagementService;
	}
	public void setUrbanRoadManagementService(
			UrbanRoadManagementService urbanRoadManagementService) {
		this.urbanRoadManagementService = urbanRoadManagementService;
	}

	
}
