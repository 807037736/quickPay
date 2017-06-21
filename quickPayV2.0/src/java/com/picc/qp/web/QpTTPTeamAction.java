/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTTPTeam;
import com.picc.qp.schema.model.QpTTPTeamId;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTTPTeamService;
import com.picc.um.schema.vo.SessionUser;


public class QpTTPTeamAction extends Struts2Action{
	
	private IQpTTPTeamService qpTTPTeamService;	
	private IQpTCOMCityService qpTCOMCityService;
	
	public void setQpTTPTeamService(IQpTTPTeamService qpTTPTeamService) {
		this.qpTTPTeamService = qpTTPTeamService;
	}

	public IQpTTPTeamService getQpTTPTeamService() {
		return qpTTPTeamService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}
	
	private QpTTPTeam qpTTPTeam;
	
	private QpTTPTeamId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTTPTeam getter/setter **/
	public QpTTPTeam getQpTTPTeam() {
		return this.qpTTPTeam;
	}
	
	public void setQpTTPTeam(QpTTPTeam qpTTPTeam) {
		this.qpTTPTeam = qpTTPTeam;
	}
	/** QpTTPTeamId getter/setter **/
	public QpTTPTeamId getId() {
		return this.id;
	}
	
	public void setId(QpTTPTeamId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	/** 主键对象 */
	private String teamId;
	public String getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		SessionUser sessionUser = getUserFromSession();
		qpTTPTeam = new QpTTPTeam();
		if(ToolsUtils.notEmpty(sessionUser.getCity())){
			qpTTPTeam.setCityId(sessionUser.getCity());
		}
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity);
		this.getRequest().setAttribute("citylist", citylist);
		return SUCCESS;
	}
	
	/**
	 * QpTTPTeam查询，根据qpTTPTeam带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTTPTeam信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity);
		this.getRequest().setAttribute("citylist", citylist);
		
		qpTTPTeam = qpTTPTeamService.findQpTTPTeamByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTTPTeam信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTTPTeamService.updateQpTTPTeam(qpTTPTeam);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTTPTeam信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		SessionUser sessionUser = getUserFromSession();
		qpTTPTeam = new QpTTPTeam();
		if(ToolsUtils.notEmpty(sessionUser.getCity())){
			qpTTPTeam.setCityId(sessionUser.getCity());
		}
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity);
		this.getRequest().setAttribute("citylist", citylist);
		return SUCCESS;
	}
	
	/**
	 * 新增QpTTPTeam信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTTPTeamService.saveQpTTPTeam(qpTTPTeam);
		return SUCCESS;
	}



	/**
	 * 删除QpTTPTeam信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTTPTeamService.deleteByPK(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}



	/**
	 * 查看QpTTPTeam信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTTPTeam = qpTTPTeamService.findQpTTPTeamByPK(id);
		return SUCCESS;
	}
}
