/*

 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;

/** 
* @ClassName: IUmTAccountService 
* @Description: TODO 账户管理Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTAccountService{

	/**
	 * 根据主键对象UmTAccountId获取UmTAccount信息
	 * @param UmTAccountId
	 * @return UmTAccount
	 */
	public UmTAccount findUmTAccountByPK(UmTAccountId id) throws Exception;
	/**
	 * 根据userCode获取UmTAccount信息
	 * @param userCode
	 * @return UmTAccount
	 */
	public UmTAccount findUmTAccountByUserCode(String userCode) throws Exception;

	/**
	 * 根据umTAccount和页码信息，获取Page对象
	 * @param umTAccount
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTAccount的Page对象
	 */
	public Page findByUmTAccount(UmTAccount umTAccount, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTAccount信息
	 * @param UmTAccount
	 */
	public void updateUmTAccount(UmTAccount umTAccount) throws Exception;

	/**
	 * 修改密码
	 * @param usercode,password
	 */
	public void updateUmTAccount(String userCode,String password) throws Exception;
	/**
	 * 保存UmTAccount信息
	 * @param UmTAccount
	 */
	public void saveUmTAccount(UmTAccount umTAccount) throws Exception;

	/**
	 * 根据主键对象，删除UmTAccount信息
	 * @param UmTAccountId
	 */
	public void deleteByPK(UmTAccountId id) throws Exception;
	
	/**
	 * 按规则查找
	 * @param rule
	 * @return
	 * @author xiehui 20140723
	 */
	public List<UmTAccount> findByRule(QueryRule rule);
	
	/**
	 * 验证密码是否符合规范
	 * @param password
	 * @return
	 */
	public boolean validatePassword(String password);
	
}
