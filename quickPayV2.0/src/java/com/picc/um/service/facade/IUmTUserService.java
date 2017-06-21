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
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;

/** 
* @ClassName: IUmTUserService 
* @Description: TODO用户管理、我的信息Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTUserService{

	/**
	 * 根据主键对象UmTUserId获取UmTUser信息
	 * @param UmTUserId
	 * @return UmTUser
	 */
	public UmTUser findUmTUserByPK(UmTUserId id) throws Exception;
	
	/**
	 * 根据主键对象userCode获取UmTUser信息
	 * @param UmTUserId
	 * @return UmTUser
	 */
	public UmTUser findUmTUserByUserCode(String  userCode) throws Exception;
	
	/**
	 * 根据主键对象comCode获取UmTUser List
	 * @param comCode
	 * @return List<UmTUser>
	 */
	public List<UmTUser> findUmTUserByComCode(String comCode,String comId) throws Exception;
	/** 
	* added by Jay 2013-8-21
	* @Title: findUserPageByComCode 
	* @Description: 根据comCode获取userPage
	* @return Page 
	* @throws 
	*/
	public Page findUserPageByComCode(String comCode,String comId,int pageNo, int pageSize);
	
	/** 
	* added by Jay 2013-10-17
	* @Title: findUserByAccountAndComcode 
	* @Description: 根据机构查询user、account联合表的用户list
	* @return List<UmTUser> 
	* @throws 
	*/
	public List<UmTUser> findUserByAccountAndComcode(String comCode,String comId);
	
	/** 
	* @Title: findUserByRole 
	* @Description: 根据角色获取UmTUser列表 
	* @param @param roleCode
	* @return List<UmTUser>    返回类型 
	* @throws 
	*/
	public List<UmTUser> findUserByRole(String roleId,String comId);
	/** 
	* added by Jay 2013-8-20
	* @Title: findUserByGroup 
	* @Description:  根据流程角色查找用户列表
	* @return List<UmTUser> 
	* @throws 
	*/
	public List<UmTUser> findUserByGroup(String groupId,String comId);
	/** 
	* added by Jay 2013-8-20
	* @Title: findUserPageByGroup 
	* @Description: 根据流程角色查找用户Page
	* @return Page 
	* @throws 
	*/
	public Page findUserPageByGroup(String groupId,int pageNo, int pageSize,String comId);
	/** 
	* @Title: findUserByRoleAndCom 
	* @Description: 根据角色和机构获取UmTUser列表 
	* @param @param roleCode
	* @return List<UmTUser>    返回类型 
	* @throws 
	*/
	
	public List<UmTUser> findUserByRoleAndCom(String comCode,String roleId,String comId);
	
	/**
	 * 根据umTUser和页码信息，获取Page对象
	 * @param umTUser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUser的Page对象
	 */
	public Page findByUmTUser(UmTUser umTUser, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据umTUser和页码信息，获取Page对象
     * @param umTUser
     * @param pageNo
     * @param pageSize
     * @return 包含UmTUser的Page对象
     */
    public Page findByUmTUserSec(UmTUser umTUser, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTUser信息
	 * @param UmTUserVo
	 */
	public void updateUmTUser(UmTUser umTUser) throws Exception;

	/**
	 * 保存UmTUser信息
	 * @param UmTUserVo
	 */
	public void saveUmTUser(UmTUser umTUser) throws Exception;
	/**
	 * 保存UmTUser、umTAccount信息
	 * @param UmTUserVo
	 */
	public void saveUmTUserAndAccount(UmTUser umTUser,UmTAccount umTAccount) throws Exception;
	/**
	 * 根据主键对象，删除UmTUser信息
	 * @param UmTUserId
	 */
	public void deleteByPK(UmTUserId id) throws Exception;
	/**
	 * 用户识别接口
	 * @param umtUser
	 * @return
	 * @throws Exception
	 */
	public UmTUser recognizedUser(UmTUser umtUser)throws Exception;
	
	public UmTUser findUserMcbc(String piid) throws Exception ;
	
	public UmTUser findUserRecognized(String userName, String identityNumber, String mobile ) throws Exception ;
	
	public String generateUsercode() throws Exception;
	
	public List<UmTUser> findUserListByRule(QueryRule rule) throws Exception;
}
