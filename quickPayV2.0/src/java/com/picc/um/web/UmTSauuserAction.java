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
import com.picc.um.schema.model.UmTSauuser;
import com.picc.um.schema.model.UmTSauuserId;
import com.picc.um.service.facade.IUmTSaugroupService;
import com.picc.um.service.facade.IUmTSauuserService;


/** 
* @ClassName: UmTSauuserAction 
* @Description: TODO 团队成员Action
* @author dijie
* @date 2013-12-3 
*  
*/
public class UmTSauuserAction extends Struts2Action{
	
	private IUmTSauuserService umTSauuserService;	
	private IUmTSaugroupService umTSaugroupService;	
	public IUmTSaugroupService getUmTSaugroupService() {
		return umTSaugroupService;
	}

	public void setUmTSaugroupService(IUmTSaugroupService umTSaugroupService) {
		this.umTSaugroupService = umTSaugroupService;
	}

	public void setUmTSauuserService(IUmTSauuserService umTSauuserService) {
		this.umTSauuserService = umTSauuserService;
	}

	public IUmTSauuserService getUmTSauuserService() {
		return umTSauuserService;
	}
	
	private UmTSauuser umTSauuser;
	
	private UmTSaugroup umTSaugroup;
	
	private UmTSauuserId id;
	
	/** 操作类型 **/
	private String opreateType;
	
	/** UmTSaugroup getter/setter **/
	public UmTSaugroup getUmTSaugroup() {
		return umTSaugroup;
	}

	public void setUmTSaugroup(UmTSaugroup umTSaugroup) {
		this.umTSaugroup = umTSaugroup;
	}

	/** UmTSauuser getter/setter **/
	public UmTSauuser getUmTSauuser() {
		return this.umTSauuser;
	}
	
	public void setUmTSauuser(UmTSauuser umTSauuser) {
		this.umTSauuser = umTSauuser;
	}
	/** UmTSauuserId getter/setter **/
	public UmTSauuserId getId() {
		return this.id;
	}
	
	public void setId(UmTSauuserId id) {
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
	private String saleUserId;
	public String getSaleUserId() {
		return this.saleUserId;
	}
	
	public void setSaleUserId(String saleUserId) {
		this.saleUserId = saleUserId;
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
	* added by Jay 2013-8-14
	* @Title: prepareQueryMyTeam 
	* @Description: 准备查询我的团队
	* @return String 
	* @throws 
	*/
	public String prepareQueryMyTeam() throws Exception {		
		
		/*String userCode="12129118";*/
		//获取Session中的用户代码
		String userCode = String.valueOf(getRequest().getSession().getAttribute("UserCode"));
		UmTSauuser umTSauuser = umTSauuserService.findUmTSauuserByUsercode(userCode,getUserFromSession().getComId());
		if(umTSauuser!=null){
			String teamCode= umTSauuser.getTeamCode();
			umTSaugroup = umTSaugroupService.findUmTSaugroupByTeamCode(teamCode,getUserFromSession().getComId());
			if(umTSaugroup!=null){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}
	/** 
	* added by Jay 2013-8-14
	* @Title: queryMyTeam 
	* @Description: 查询团队成员
	* @return String 
	* @throws 
	*/
	public String queryMyTeam() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {
			UmTSauuser umTSauuser1 =new UmTSauuser();
			umTSauuser1.setTeamCode(umTSaugroup.getTeamCode());
			umTSauuser1.setComId(getUserFromSession().getComId());
			Page resultPage = umTSauuserService.findByUmTSauuser(umTSauuser1, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
	/**
	 * UmTSauuser查询，根据umTSauuser带过来的值为查询条件进行查询。
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
			umTSauuser.setComId(getUserFromSession().getComId());
			Page resultPage = umTSauuserService.findByUmTSauuser(umTSauuser, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTSauuser信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		umTSauuser = umTSauuserService.findUmTSauuserByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTSauuser信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		umTSauuserService.updateUmTSauuser(umTSauuser);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTSauuser信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTSauuser信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		umTSauuserService.saveUmTSauuser(umTSauuser);
		return SUCCESS;
	}



	/**
	 * 删除UmTSauuser信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTSauuserService.deleteByPK(id);
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
	 * 查看UmTSauuser信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		umTSauuser = umTSauuserService.findUmTSauuserByPK(id);
		return SUCCESS;
	}
}
