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

import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.model.UmTWhiteListId;
import com.picc.um.service.facade.IUmTWhiteListService;

/**
 * 白名单处理Action层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTWhiteListAction extends Struts2Action{
	
	private IUmTWhiteListService umTWhiteListService;	
	
	public void setUmTWhiteListService(IUmTWhiteListService umTWhiteListService) {
		this.umTWhiteListService = umTWhiteListService;
	}

	public IUmTWhiteListService getUmTWhiteListService() {
		return umTWhiteListService;
	}
	
	private UmTWhiteList umTWhiteList;
	
	private UmTWhiteListId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTWhiteList getter/setter **/
	public UmTWhiteList getUmTWhiteList() {
		return this.umTWhiteList;
	}
	
	public void setUmTWhiteList(UmTWhiteList umTWhiteList) {
		this.umTWhiteList = umTWhiteList;
	}
	/** UmTWhiteListId getter/setter **/
	public UmTWhiteListId getId() {
		return this.id;
	}
	
	public void setId(UmTWhiteListId id) {
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
	private String wlId;
	public String getWlId() {
		return this.wlId;
	}
	
	public void setWlId(String wlId) {
		this.wlId = wlId;
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
	 * UmTWhiteList查询，根据umTWhiteList带过来的值为查询条件进行查询。
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
			Page resultPage = umTWhiteListService.findByUmTWhiteList(umTWhiteList, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTWhiteList信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTWhiteList = umTWhiteListService.findUmTWhiteListByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTWhiteList信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTWhiteList.setUpdaterCode(getUserFromSession().getUserCode());
		umTWhiteListService.updateUmTWhiteList(umTWhiteList);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTWhiteList信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTWhiteList信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTWhiteList.setCreatorCode(getUserFromSession().getUserCode());
		umTWhiteListService.saveUmTWhiteList(umTWhiteList);
		return SUCCESS;
	}



	/**
	 * 删除UmTWhiteList信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				umTWhiteListService.deleteByPK(id);
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
	 * 查看UmTWhiteList信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTWhiteList = umTWhiteListService.findUmTWhiteListByPK(id);
		return SUCCESS;
	}
}
