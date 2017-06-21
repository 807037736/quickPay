/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.schema.model.TmTApplicationConfigId;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.security.SecurityUtils;

public class TmTApplicationConfigAction extends Struts2Action{
	
	private ITmTApplicationConfigService tmTApplicationConfigService;	
	public void setTmTApplicationConfigService(ITmTApplicationConfigService tmTApplicationConfigService) {
		this.tmTApplicationConfigService = tmTApplicationConfigService;
	}

	public ITmTApplicationConfigService getTmTApplicationConfigService() {
		return tmTApplicationConfigService;
	}
	
	private TmTApplicationConfig tmTApplicationConfig;
	
	private TmTApplicationConfigId id;
	
	/** 操作类型 **/
	private String operateType;
	/** TmTApplicationConfig getter/setter **/
	public TmTApplicationConfig getTmTApplicationConfig() {
		return this.tmTApplicationConfig;
	}
	
	public void setTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig) {
		this.tmTApplicationConfig = tmTApplicationConfig;
	}
	/** TmTApplicationConfigId getter/setter **/
	public TmTApplicationConfigId getId() {
		return this.id;
	}
	
	public void setId(TmTApplicationConfigId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		logger.debug("正在执行TmTApplicationConfigAction.prepareQuery，准备进入TmTApplicationConfig查询方法");
		
		return SUCCESS;
	}
	
	/**
	 * TmTApplicationConfig查询，根据tmTApplicationConfig带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		logger.debug("正在执行TmTApplicationConfigAction.query，查询满足条件的TmTApplicationConfig信息");
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = tmTApplicationConfigService.findByTmTApplicationConfig(tmTApplicationConfig, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新TmTApplicationConfig信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		logger.debug("正在执行TmTApplicationConfigAction.prepareUpdate，准备更新TmTApplicationConfig信息");
		operateType = "update";
		tmTApplicationConfig = tmTApplicationConfigService.findTmTApplicationConfigByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新TmTApplicationConfig信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		logger.debug("正在执行TmTApplicationConfigAction.update，更新TmTApplicationConfig信息");
		tmTApplicationConfigService.updateTmTApplicationConfig(tmTApplicationConfig);
		return SUCCESS;
	}


	/**
	 * 准备增加TmTApplicationConfig信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		logger.debug("正在执行TmTApplicationConfigAction.prepareAdd，准备新增TmTApplicationConfig信息");
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增TmTApplicationConfig信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		logger.debug("正在执行TmTApplicationConfigAction.prepareAdd，新增TmTApplicationConfig信息");
		if(tmTApplicationConfig!=null){
			tmTApplicationConfig.setCreatorCode(getUserFromSession().getUserCode());
			tmTApplicationConfig.setInsertTimeForHis(new Date());
		}
		tmTApplicationConfigService.saveTmTApplicationConfig(tmTApplicationConfig);
		return SUCCESS;
	}



	/**
	 * 删除TmTApplicationConfig信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				logger.debug("正在执行TmTApplicationConfigAction.delete，删除" +id + "TmTApplicationConfig信息");
				tmTApplicationConfigService.deleteByPK(id);
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
	 * 查看TmTApplicationConfig信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		logger.debug("正在执行TmTApplicationConfigAction.view,查看TmTApplicationConfig信息");
		operateType = "view";
		tmTApplicationConfig = tmTApplicationConfigService.findTmTApplicationConfigByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 获取系统环境的所有信息列表，以备配置角色权限使用
	 * @author chenwu
	 * modified by liuyatao
	 * 2014年8月4日
	 */
	public String selectSerCode() {

		try {
			List<TmTApplicationConfig> servercodeList = tmTApplicationConfigService.selectSerCode();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("servercodeList", servercodeList);
			JSONObject json = JSONObject.fromObject(map);
			this.writeJsonResultData(json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
	public void writeJsonResultData(JSONObject result) {
			
			List list = new ArrayList();
			list.add(result);
			List filterList = SecurityUtils.outputfilter(list);
			JSONObject filterObject = (JSONObject)filterList.get(0);
			
			try {
	//			System.out.println(filterObject.toString());
				renderText(filterObject.toString());
			} catch (Exception e) {
				writeJSONMsg(e.getMessage());
			}
	
		}
		
	}
