/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.model.UmTUserRelationId;

/** 
* @ClassName: IUmTUserService 
* @Description: TODO用户管理、我的信息Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTUserRelationService{

	/**
	 * 根据主键对象UmTUserId获取UmTUser信息
	 * @param UmTUserId
	 * @return UmTUser
	 */
	public UmTUserRelation findUmTUserRelationByPK(UmTUserRelationId id) throws Exception;
	
	/**
	 * 根据主键对象userCode获取UmTUserRelation信息
	 * @param UmTUserRelationId
	 * @return UmTUserRelation
	 */
	public UmTUserRelation findUmTUserRelationByUserId(String  openid,String userid) throws Exception;
	public List<UmTUserRelation> findUmTUserRelationByUsercode(String usercode) throws Exception;
	
	/**
	 * 根据UmTUserRelation和页码信息，获取Page对象
	 * @param UmTUserRelation
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserRelation的Page对象
	 */
	public Page findByUmTUserRelation(UmTUserRelation umTUserRelation, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTUserRelation信息
	 * @param UmTUserRelationVo
	 */
	public void updateUmTUserRelation(UmTUserRelation umTUserRelation) throws Exception;

	/**
	 * 保存UmTUserRelation信息
	 * @param UmTUserRelationVo
	 */
	public void saveUmTUserRelation(UmTUserRelation umTUserRelation) throws Exception;
	/**
	 * 根据主键对象，删除UmTUser信息
	 * @param UmTUserId
	 */
	public void deleteByPK(UmTUserRelationId id) throws Exception;
	
	public Map validInnerUser(UmTUserRelation umTUserRelation) throws Exception;
	
	public List<UmTUser> findUserRelationByOpenID(String openid) throws Exception;
	/**
	 * 根据userId，查找UmTUserRelation信息
	 * @param UmTUserId
	 */
	public List<UmTUserRelation> getUmTUserRelationByUserId(String userId)throws Exception;
}
