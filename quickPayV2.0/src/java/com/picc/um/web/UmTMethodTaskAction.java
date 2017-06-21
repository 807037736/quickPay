/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTMethodTaskId;
import com.picc.um.service.facade.IUmTMethodTaskService;

/**
 * 功能方法Action处理层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTMethodTaskAction extends Struts2Action{
	
	private IUmTMethodTaskService umTMethodTaskService;	
	public void setUmTMethodTaskService(IUmTMethodTaskService umTMethodTaskService) {
		this.umTMethodTaskService = umTMethodTaskService;
	}

	public IUmTMethodTaskService getUmTMethodTaskService() {
		return umTMethodTaskService;
	}
	
	private UmTMethodTask umTMethodTask;
	
	private UmTMethodTaskId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTMethodTask getter/setter **/
	public UmTMethodTask getUmTMethodTask() {
		return this.umTMethodTask;
	}
	
	public void setUmTMethodTask(UmTMethodTask umTMethodTask) {
		this.umTMethodTask = umTMethodTask;
	}
	/** UmTMethodTaskId getter/setter **/
	public UmTMethodTaskId getId() {
		return this.id;
	}
	
	public void setId(UmTMethodTaskId id) {
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
	private String methodId;
	public String getMethodId() {
		return this.methodId;
	}
	
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		
		return SUCCESS;
	}
	
	/**
	 * UmTMethodTask查询，根据umTMethodTask带过来的值为查询条件进行查询。
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
			Page resultPage = umTMethodTaskService.findByUmTMethodTask(umTMethodTask, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTMethodTask信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		umTMethodTask = umTMethodTaskService.findUmTMethodTaskByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTMethodTask信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		umTMethodTask.setUpdaterCode(String.valueOf(getSession().getAttribute("UserCode")));
		umTMethodTaskService.updateUmTMethodTask(umTMethodTask);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTMethodTask信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTMethodTask信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		try{
			umTMethodTask.setCreatorCode(String.valueOf(getSession().getAttribute("UserCode")));
			umTMethodTaskService.saveUmTMethodTask(umTMethodTask);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		return SUCCESS;
	}



	/**
	 * 删除UmTMethodTask信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTMethodTaskService.deleteByPK(id);
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
	 * 查看UmTMethodTask信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		umTMethodTask = umTMethodTaskService.findUmTMethodTaskByPK(id);
		return SUCCESS;
	}
}
