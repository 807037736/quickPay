/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.List;

import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserBound;
import com.picc.um.schema.model.UmTUserBoundId;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.service.facade.IUmTUserBoundService;
import com.picc.um.service.facade.IUmTUserService;


public class UmTUserBoundAction extends Struts2Action{
	IUmTUserBoundService umTUserBoundService;
	IUmTUserService umTUserService;
	UmTUser umTUser;
	UmTUserBound umTUserBound;
	String operateType;
	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public UmTUserBound getUmTUserBound() {
		return umTUserBound;
	}

	public void setUmTUserBound(UmTUserBound umTUserBound) {
		this.umTUserBound = umTUserBound;
	}

	public IUmTUserBoundService getUmTUserBoundService() {
		return umTUserBoundService;
	}

	public void setUmTUserBoundService(IUmTUserBoundService umTUserBoundService) {
		this.umTUserBoundService = umTUserBoundService;
	}

	public UmTUser getUmTUser() {
		return umTUser;
	}

	public void setUmTUser(UmTUser umTUser) {
		this.umTUser = umTUser;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 */
	public String prepareQueryWX() throws Exception {		
		return SUCCESS;
	}
	
	public String prepareQueryCK() throws Exception {
		return SUCCESS;
	}
	
	public String prepareUpdate() throws  Exception {	
		return SUCCESS;
	}
	
	/**
	 * 微信用户绑定查勘用户
	 * @throws Exception 
	 * 
	 */
	public String addUmTUserBound() {
		if(!"".equals(umTUserBound) && null != umTUserBound){
			try {
				UmTUser ckUser = umTUserService.findUmTUserByPK(new UmTUserId(umTUserBound.getCkUserCode()));
				umTUserBound.setCkUserName(ckUser.getUserName()) ;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			umTUserBound.setId(new UmTUserBoundId(0));
			umTUserBound.setIsBound("1");
			umTUserBound.setCreateTime(new Date());
			umTUserBound.setCreater(this.getUserFromSession().getUserCode());
			try {
				umTUserBoundService.saveUmTUserBound(umTUserBound);
				return NONE;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		return ERROR;
	}
	
	/**
	 * 解除绑定
	 * @throws Exception 
	 * 
	 */
	public void delete() throws Exception{
		umTUserBoundService.deleteByPK(umTUserBound.getBoundId());
	}
	
	/**
	 * 重新绑定
	 * @throws Exception 
	 * 
	 */
	public void update() throws Exception{
		System.out.println("umTUserBound:"+umTUserBound);
		UmTUser ckUser = umTUserService.findUmTUserByPK(new UmTUserId(umTUserBound.getCkUserCode()));
		if(null != ckUser && !"".equals(ckUser)){
			umTUserBound = umTUserBoundService.findUmTUserBoundByPk(new UmTUserBoundId(umTUserBound.getBoundId()));
			umTUserBound.setUpdateCode(this.getUserFromSession().getUserCode());
			umTUserBound.setUpdateTime(new Date());
			umTUserBound.setCkUserCode(ckUser.getId().getUserCode());
			umTUserBound.setCkUserName(ckUser.getUserName());
			umTUserBoundService.updateUmTUserBound(umTUserBound);
		}
	}
	
	/**
	 * UmTUser查询，根据UmTUser带过来的值为查询条件进行查询。
	 * 
	 */
	public String queryWX() throws Exception {
		operateType = "prepareBound";
		if (page == 0) {
			page = 1;
		}
		if(rows == 0){
			rows = 10 ;
		}
		try {
			Page resultPage = umTUserBoundService.findWXUserPageByUmTUser(umTUserBound, page, rows);
			
//			resultPage.getResult().set(index, element);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
	
	/**
	 * UmTUser查询，根据UmTUser带过来的值为查询条件进行查询。
	 * 
	 */
	public String queryCK() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if(rows == 0){
			rows = 10 ;
		}
		List<UmTUserBound> findByUserBound = umTUserBoundService.findByUserBound(umTUserBound);
		try {
			Page resultPage = umTUserBoundService.findCKUserPageByUmTUser(umTUserBound, page, rows);
			if(findByUserBound != null && findByUserBound.size()>0){
				umTUserBound = findByUserBound.get(0);
			}
			if(null != resultPage && !"".equals(resultPage)){
				List<UmTUserBound> umTUserBounds = (List<UmTUserBound>) resultPage.getResult();
				for (int i = 0; i < umTUserBounds.size(); i++) {
					umTUserBounds.get(i).setWxUserCode(umTUserBound.getWxUserCode());
					if(findByUserBound != null && findByUserBound.size()>0){
						umTUserBounds.get(i).setCkUserCode(umTUserBound.getCkUserCode());
					}
					
					if(umTUserBounds.get(i).getUserCode().equals(umTUserBound.getCkUserCode())){
						umTUserBounds.get(i).setCkUserCode("-1");
						umTUserBounds.get(i).setBoundId(umTUserBound.getBoundId());
					}
					
				}
				this.writeEasyUiData(resultPage,umTUserBounds);
			}else{
				this.writeEasyUiData(resultPage);
			}
			
			
//			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
}
