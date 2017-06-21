/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleComId;
import com.picc.um.service.facade.IUmTRoleComService;


/**
 * 角色机构关联Action处理层
 * @author 沈一婵
 */
@SuppressWarnings("serial")
public class UmTRoleComAction extends Struts2Action{
	
	private IUmTRoleComService umTRoleComService;
	
	private String comCode = null;
	
	private String queryType = null;
	
	private int start;
	
	private int limit;
	
	
	
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setUmTRoleComService(IUmTRoleComService umTRoleComService) {
		this.umTRoleComService = umTRoleComService;
	}

	public IUmTRoleComService getUmTRoleComService() {
		return umTRoleComService;
	}
	
	private UmTRoleCom umTRoleCom;
	
	private UmTRoleComId id;
	
	/** 操作类型 **/
	private String opreateType;
	/** UmTRoleCom getter/setter **/
	public UmTRoleCom getUmTRoleCom() {
		return this.umTRoleCom;
	}
	
	public void setUmTRoleCom(UmTRoleCom umTRoleCom) {
		this.umTRoleCom = umTRoleCom;
	}
	/** UmTRoleComId getter/setter **/
	public UmTRoleComId getId() {
		return this.id;
	}
	
	public void setId(UmTRoleComId id) {
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
	private String roleComId;
	public String getRoleComId() {
		return this.roleComId;
	}
	
	public void setRoleComId(String roleComId) {
		this.roleComId = roleComId;
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
	 * UmTRoleCom查询，根据umTRoleCom带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public void query() throws Exception {
		
//		if (page == 0) {
//			page = 1;
//		}
//		if (rows == 0) {
//			rows = 20;
//		}
//
//
//		try {
//			Page resultPage = umTRoleComService.findByUmTRoleCom(umTRoleCom, page, rows);
//			this.writeEasyUiData(resultPage);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("", e);
//			this.writeJSONMsg(e.getMessage());
//		}
		try{
			if(queryType.equals("0")){
				comCode = (String) getSession().getAttribute("ComCode");
			}
			this.renderText(umTRoleComService.getRoleListByComCode(comCode,queryType,start,limit));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
	}	


	/**
	 * 准备更新UmTRoleCom信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		umTRoleCom = umTRoleComService.findUmTRoleComByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTRoleCom信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		umTRoleComService.updateUmTRoleCom(umTRoleCom);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTRoleCom信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTRoleCom信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		umTRoleComService.saveUmTRoleCom(umTRoleCom);
		return SUCCESS;
	}



	/**
	 * 删除UmTRoleCom信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTRoleComService.deleteByPK(id);
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
	 * 查看UmTRoleCom信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		umTRoleCom = umTRoleComService.findUmTRoleComByPK(id);
		return SUCCESS;
	}
}
