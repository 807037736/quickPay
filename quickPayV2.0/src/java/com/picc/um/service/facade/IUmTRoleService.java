/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.vo.UmTRoleVo;

/**
 * 角色对象自定义接口
 * @author 沈一婵
 */
public interface IUmTRoleService{

	/**
	 * 根据主键对象UmTRoleId获取UmTRole信息
	 * @param UmTRoleId
	 * @return UmTRole
	 * @author shenyichan
	 */
	public UmTRole findUmTRoleByPK(UmTRoleId id) throws Exception;

	/**
	 * 根据umTRole和页码信息，获取Page对象
	 * @param umTRole
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRole的Page对象
	 * @author shenyichan
	 */
	public Page findByUmTRole(UmTRole umTRole, int pageNo, int pageSize, String comCode) throws Exception;

	/**
	 * 更新UmTRole信息
	 * @param UmTRole
	 * @author shenyichan
	 */
	public void updateUmTRole(UmTRole umTRole) throws Exception;

	/**
	 * 保存UmTRole信息
	 * @param UmTRole
	 * @auth shenyichan
	 */
	public void saveUmTRole(UmTRole umTRole) throws Exception;

	/**
	 * 根据主键对象，删除UmTRole信息
	 * @param UmTRoleId
	 * @author shenyichan
	 */
	public void deleteByPK(UmTRoleId id) throws Exception;


	/**
	 * 根据角色ID指删除角色
	 * @param roleIds
	 * @author shenyichan
	 */
	public void deleteByPKList(List<String> roleIds);
	
	/**
	 * 保存角色时，关联保存对应的机构和功能
	 * @param umTRole
	 * @param comCodes
	 * @param comNames
	 * @param tasks
	 * @param taskNames 
	 * @author shenyichan
	 */
	public void saveRoleComTask(UmTRole umTRole, List<String> comCodes,
			List<String> comNames, List<String> tasks, List<String> taskNames,String comId) throws Exception;

	
	/**
	 * 修改角色信息、机构角色关联信息、角色功能关联信息
	 * @param umTRole
	 * @param roleComList
	 * @param roleTaskList
	 * @author shenyichan
	 */
	public void updateRoleComTask(UmTRole umTRole,
			List<UmTRoleCom> roleComList, List<UmTRoleTask> roleTaskList,String comId) throws Exception;
	/**
	 * 获取所有有效的角色列表
	 * @return
	 * 2013-8-13下午2:32:03
	 * jiangweiyang
	 */
	public List<UmTRole> findValidUmTRoleList(); 


/**
	 * 根据人员代码查询该人员的角色
	 * @param userCode
	 * @return
	 */
	public List<UmTRole> findByUserCode(String userCode);

	/**
	 * 根据机构代码查询当前机构下的角色
	 * @return
	 */
	public Page findByComCode(String comCode,int page,int rows);

	/**
	 * 更改角色的标识为Cognos角色
	 * @author shenyichan
	 */
	public void updateToCognosRole(UmTRole role);
	
	
	/**
	 * 查询有效的角色信息并封装成Page对象进行返回
	 * @param page
	 * @param rows
	 * @return
	 * 2013-9-10下午5:06:32
	 * jiangweiyang
	 */
	public Page findValidUmTRole(int page,int rows,String comCode);

	/**
	 * 判断当前角色是否是总公司角色
	 * @param id
	 * @return
	 * @author shenyichan
	 */
	public boolean isHeadRole(UmTRoleId id);

	/**
	 * 查询当前省级机构下所有角色名称
	 * @param comId
	 * @return
	 */
	public List<Object[]> findAllRole(String comId);
	/***
	 * 根据serverCode查找角色
	 * @param serverCode
	 * @return
	 * @throws Exception
	 * @author yuwenhui 2014-08-26
	 */
	public List<UmTRoleVo> findRoleByServercode(String serverCode) throws Exception;
	
	
	/***
	 * 根据roleCode查 找角色
	 * @param serverCode
	 * @return
	 * @throws Exception
	 * @author yuwenhui 2014-08-26
	 */
	public List<UmTUserRole> findRoleByRoleCode(String roleCode) throws Exception;

}
