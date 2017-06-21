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

import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.security.DESUtils;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTUserService;


/** 
* @ClassName: UmTAccountAction 
* @Description: TODO 账户管理Action
* @author dijie
* @date 2013-12-3 
*  
*/
public class UmTAccountAction extends Struts2Action{
	private IUmTUserService umTUserService;	
	private IUmTAccountService umTAccountService;	
	public void setUmTAccountService(IUmTAccountService umTAccountService) {
		this.umTAccountService = umTAccountService;
	}

	public IUmTAccountService getUmTAccountService() {
		return umTAccountService;
	}
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	private UmTAccount umTAccount;
	private String isChange;
	private UmTAccountId id;
	private SessionUser sessionUser=(SessionUser)getRequest().getSession().getAttribute("SessionUser");
	
	
	/** 操作类型 **/
	private String opreateType;
	/** UmTAccount getter/setter **/
	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	public UmTAccount getUmTAccount() {
		return this.umTAccount;
	}
	
	public void setUmTAccount(UmTAccount umTAccount) {
		this.umTAccount = umTAccount;
	}
	/** UmTAccountId getter/setter **/
	public UmTAccountId getId() {
		return this.id;
	}
	
	public void setId(UmTAccountId id) {
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
	private String userCode;
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	 * UmTAccount查询，根据umTAccount带过来的值为查询条件进行查询。
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
			umTAccount.setComId(getUserFromSession().getComId());
			Page resultPage = umTAccountService.findByUmTAccount(umTAccount, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTAccount信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		umTAccount = umTAccountService.findUmTAccountByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTAccount信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		logger.info("更新UmTAccount信息:" + isChange);
		if("1".equals(isChange)){
			umTAccount.setPassword(DESUtils.getEncryptStr(umTAccount.getPassword()));
			}else if("2".equals(isChange)){
				umTAccount.setPassword(umTAccount.getPassword());
			}
		umTAccount.setComId(getUserFromSession().getComId());
		umTAccount.setUpdaterCode(getUserFromSession().getUserCode());
		umTAccountService.updateUmTAccount(umTAccount);
		
		
		return SUCCESS;
	}


	/**
	 * 准备增加UmTAccount信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
    /**
     * 新增UmTAccount信息
     * 
     * @return
     */
    public String add() throws Exception {
        UmTUser umTUser = umTUserService.findUmTUserByUserCode(umTAccount.getId().getUserCode());

        if (umTUser != null) {
            if ("1".equals(umTUser.getValidStatus())) {
                umTAccount.setPassword(DESUtils.getEncryptStr(umTAccount.getPassword()));
                umTAccount.setComId(getUserFromSession().getComId());
                umTAccount.setCreatorCode(getUserFromSession().getUserCode());
                umTAccount.setValidStatus("1");
                umTAccountService.saveUmTAccount(umTAccount);
                return SUCCESS;
            } else {
                Map resultMap = new HashMap();
                resultMap.put("errorTitle", "错误信息(ERROR)：");
                resultMap.put("errorMsg", "该用户为无效用户，不能添加账户！");
                this.writeAjaxErrorByMap(resultMap);
                return NONE;
            }
        } else {
            Map resultMap = new HashMap();
            resultMap.put("errorTitle", "错误信息(ERROR)：");
            resultMap.put("errorMsg", "该用户不存在，不能添加账户！");
            this.writeAjaxErrorByMap(resultMap);
            return NONE;
        }
    }



	/**
	 * 删除UmTAccount信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTAccountService.deleteByPK(id);
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
	 * 查看UmTAccount信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		umTAccount = umTAccountService.findUmTAccountByPK(id);
		return SUCCESS;
	}
}
