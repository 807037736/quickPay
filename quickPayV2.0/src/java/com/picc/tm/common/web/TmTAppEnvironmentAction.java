/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.tm.common.schema.model.TmTAppEnvironment;
import com.picc.tm.common.schema.model.TmTAppEnvironmentId;
import com.picc.tm.common.service.facade.ITmTAppEnvironmentService;

/**
 * 环境变量action类
 * @author moxiaoguang 2013-12-12
 *
 */
public class TmTAppEnvironmentAction extends Struts2Action{
	
	private ITmTAppEnvironmentService tmTAppEnvironmentService;	
	public void setTmTAppEnvironmentService(ITmTAppEnvironmentService tmTAppEnvironmentService) {
		this.tmTAppEnvironmentService = tmTAppEnvironmentService;
	}

	public ITmTAppEnvironmentService getTmTAppEnvironmentService() {
		return tmTAppEnvironmentService;
	}
	
	private TmTAppEnvironment tmTAppEnvironment;
	
	private TmTAppEnvironmentId id;
	
	/** 操作类型 **/
	private String operateType;
	/** TmTAppEnvironment getter/setter **/
	public TmTAppEnvironment getTmTAppEnvironment() {
		return this.tmTAppEnvironment;
	}
	
	public void setTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment) {
		this.tmTAppEnvironment = tmTAppEnvironment;
	}
	/** TmTAppEnvironmentId getter/setter **/
	public TmTAppEnvironmentId getId() {
		return this.id;
	}
	
	public void setId(TmTAppEnvironmentId id) {
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
	private String environmentCode;
	public String getEnvironmentCode() {
		return this.environmentCode;
	}
	
	public void setEnvironmentCode(String environmentCode) {
		this.environmentCode = environmentCode;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String prepareQuery() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * TmTAppEnvironment查询，根据tmTAppEnvironment带过来的值为查询条件进行查询。
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String query() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = tmTAppEnvironmentService.findByTmTAppEnvironment(tmTAppEnvironment, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新TmTAppEnvironment信息
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		tmTAppEnvironment = tmTAppEnvironmentService.findTmTAppEnvironmentByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新TmTAppEnvironment信息
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String update() throws Exception { 
		tmTAppEnvironmentService.updateTmTAppEnvironment(tmTAppEnvironment);
		return SUCCESS;
	}


	/**
	 * 准备增加TmTAppEnvironment信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增TmTAppEnvironment信息
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String add() throws Exception {
		tmTAppEnvironmentService.saveTmTAppEnvironment(tmTAppEnvironment);
		return SUCCESS;
	}



	/**
	 * 删除TmTAppEnvironment信息
	 * 
	 * @return
	 * @author moxiaoguang 2013-12-12
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				tmTAppEnvironmentService.deleteByPK(id);
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
	 * 查看TmTAppEnvironment信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		tmTAppEnvironment = tmTAppEnvironmentService.findTmTAppEnvironmentByPK(id);
		return SUCCESS;
	}
}
