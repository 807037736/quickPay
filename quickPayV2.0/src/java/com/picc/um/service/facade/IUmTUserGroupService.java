/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTUserGroup;
import com.picc.um.schema.model.UmTUserGroupId;

/**
 * 用户自定义分组关联接口
 * @author 李明果
 */
public interface IUmTUserGroupService{

///****************************************author limingguo 功能区**********************************************************************/	
	public Page findGroupByComCodeMuilt(String sql, Class<?> className, int pageNo, int pageSize,String comCode)throws Exception;
	
	public Page findGroupByUserMuilt(String sql, Class<?> className, int pageNo, int pageSize,String userCode) throws Exception;
	
	public List<UmTUserGroup> findByUmTGroupId(UmTUserGroup umTUserGroup) throws Exception;
	
	public Page findUserGroupByGroupIdCom(int pageNo, int pageSize, String groupId, String comCode) throws Exception;
	
	public Page find4sByUserCode(int pageNo, int pageSize, String userCode, String comId) throws Exception;
	
	public Page findUmTUserByComCode(String comCode)throws Exception;
	
	public Page findUserPageByComCode(int pageNo, int pageSize, String comCode, String userName, String userCode)throws Exception;
	
	public List<UmTGroup> find4sCodeByUserCode(String userCode,String comId)throws Exception;
	
	public List<UmTUserGroup> find4sUserByGroupCode(String groupCode,String comId)throws Exception;
	
	/*根据人员代码和groupid查询usergroup关系表*/
	public List<UmTUserGroup> find4sUserByGroupIdAndUserCode(String groupId, String userCode)throws Exception;
	/*判断团队经理*/
	public boolean is4sGroupLeader(String groupCode, String userCode) throws Exception;

	public Page find4sUserPageByComCode(int pageNo, int pageSize, String comCode)throws Exception;
	/**
	 * 根据主键对象UmTUserGroupId获取UmTUserGroup信息
	 * @param UmTUserGroupId
	 * @return UmTUserGroup
	 */
	public UmTUserGroup findUmTUserGroupByPK(UmTUserGroupId id) throws Exception;
	
	

	/**
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param umTUserGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findByUmTUserGroup(UmTUserGroup umTUserGroup, int pageNo, int pageSize) throws Exception;

	public List<UmTUserGroup> findByUmTUserCode(UmTUserGroup umTUserGroup) throws Exception;
	/**
	 * 更新UmTUserGroup信息
	 * @param UmTUserGroup
	 */
	public void updateUmTUserGroup(UmTUserGroup umTUserGroup) throws Exception;

	/**
	 * 保存UmTUserGroup信息
	 * @param UmTUserGroup
	 */
	public void saveUmTUserGroup(UmTUserGroup umTUserGroup) throws Exception;
	
	public void saveUmTUserGroupList(List<UmTUserGroup> userGroupList) throws Exception;
	
	

	/**
	 * 根据主键对象，删除UmTUserGroup信息
	 * @param UmTUserGroupId
	 */
	public void deleteByPK(UmTUserGroupId id) throws Exception;
	public List<String> find4sUserByGroupCode2(String groupCode, String comId);
}
