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

import com.picc.um.schema.model.UmTRoleDime;
import com.picc.um.schema.model.UmTRoleDimeId;
import com.picc.um.service.facade.IUmTRoleDimeService;

/**
 * 角色扩展Action处理层
 * @author 姜卫洋
 *
 */
@SuppressWarnings("serial")
public class UmTRoleDimeAction extends Struts2Action{
	
	private IUmTRoleDimeService umTRoleDimeService;	
	public void setUmTRoleDimeService(IUmTRoleDimeService umTRoleDimeService) {
		this.umTRoleDimeService = umTRoleDimeService;
	}

	public IUmTRoleDimeService getUmTRoleDimeService() {
		return umTRoleDimeService;
	}
	
	private UmTRoleDime umTRoleDime;
	
	private UmTRoleDimeId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTRoleDime getter/setter **/
	public UmTRoleDime getUmTRoleDime() {
		return this.umTRoleDime;
	}
	
	public void setUmTRoleDime(UmTRoleDime umTRoleDime) {
		this.umTRoleDime = umTRoleDime;
	}
	/** UmTRoleDimeId getter/setter **/
	public UmTRoleDimeId getId() {
		return this.id;
	}
	
	public void setId(UmTRoleDimeId id) {
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
	private String roleDimeId;
	public String getRoleDimeId() {
		return this.roleDimeId;
	}
	
	public void setRoleDimeId(String roleDimeId) {
		this.roleDimeId = roleDimeId;
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
	 * UmTRoleDime查询，根据umTRoleDime带过来的值为查询条件进行查询。
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
			Page resultPage = umTRoleDimeService.findByUmTRoleDime(umTRoleDime, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTRoleDime信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTRoleDime = umTRoleDimeService.findUmTRoleDimeByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTRoleDime信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTRoleDimeService.updateUmTRoleDime(umTRoleDime);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTRoleDime信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTRoleDime信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTRoleDimeService.saveUmTRoleDime(umTRoleDime);
		return SUCCESS;
	}



	/**
	 * 删除UmTRoleDime信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				umTRoleDimeService.deleteByPK(id);
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
	 * 查看UmTRoleDime信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTRoleDime = umTRoleDimeService.findUmTRoleDimeByPK(id);
		return SUCCESS;
	}
}
