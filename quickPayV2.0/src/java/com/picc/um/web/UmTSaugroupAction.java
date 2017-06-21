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

import com.picc.um.schema.model.UmTSaugroup;
import com.picc.um.schema.model.UmTSaugroupId;
import com.picc.um.service.facade.IUmTSaugroupService;


/** 
* @ClassName: UmTSaugroupAction 
* @Description: TODO 团队信息Action
* @author dijie
* @date 2013-12-3 
*  
*/
public class UmTSaugroupAction extends Struts2Action{
	
	private IUmTSaugroupService umTSaugroupService;	
	public void setUmTSaugroupService(IUmTSaugroupService umTSaugroupService) {
		this.umTSaugroupService = umTSaugroupService;
	}

	public IUmTSaugroupService getUmTSaugroupService() {
		return umTSaugroupService;
	}
	
	private UmTSaugroup umTSaugroup;
	
	private UmTSaugroupId id;
	
	/** 操作类型 **/
	private String opreateType;
	/** UmTSaugroup getter/setter **/
	public UmTSaugroup getUmTSaugroup() {
		return this.umTSaugroup;
	}
	
	public void setUmTSaugroup(UmTSaugroup umTSaugroup) {
		this.umTSaugroup = umTSaugroup;
	}
	/** UmTSaugroupId getter/setter **/
	public UmTSaugroupId getId() {
		return this.id;
	}
	
	public void setId(UmTSaugroupId id) {
		this.id = id;
	}
	/** opreateType getter/setter **/
	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}
	
	/** 主键对象 */
	private String teamID;
	public String getTeamID() {
		return this.teamID;
	}
	
	public void setTeamID(String teamID) {
		this.teamID = teamID;
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
	 * 准备同步团队信息查询
	 * 
	 * @return
	 */
	public String prepareQueryOuter() throws Exception {		
		
		String comCode= getRequest().getSession().getAttribute("ComCode").toString();
		return SUCCESS;
	} 
	
	/**
	 * UmTSaugroup查询，根据umTSaugroup带过来的值为查询条件进行查询。
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
			umTSaugroup.setComId(getUserFromSession().getComId());
			Page resultPage = umTSaugroupService.findByUmTSaugroup(umTSaugroup, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTSaugroup信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		umTSaugroup = umTSaugroupService.findUmTSaugroupByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTSaugroup信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		umTSaugroupService.updateUmTSaugroup(umTSaugroup);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTSaugroup信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTSaugroup信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		umTSaugroupService.saveUmTSaugroup(umTSaugroup);
		return SUCCESS;
	}



	/**
	 * 删除UmTSaugroup信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTSaugroupService.deleteByPK(id);
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
	 * 查看UmTSaugroup信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		umTSaugroup = umTSaugroupService.findUmTSaugroupByPK(id);
		return SUCCESS;
	}
}
