/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.schema.model.UmTRoleTaskId;

/**
 * 角色功能关联自定义接口
 * @author 沈一婵
 */
public interface IUmTRoleTaskService{

	/**
	 * 根据主键对象UmTRoleTaskId获取UmTRoleTask信息
	 * @param UmTRoleTaskId
	 * @return UmTRoleTask
	 */
	public UmTRoleTask findUmTRoleTaskByPK(UmTRoleTaskId id) throws Exception;

	/**
	 * 根据umTRoleTask和页码信息，获取Page对象
	 * @param umTRoleTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleTask的Page对象
	 */
	public Page findByUmTRoleTask(UmTRoleTask umTRoleTask, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTRoleTask信息
	 * @param UmTRoleTask
	 */
	public void updateUmTRoleTask(UmTRoleTask umTRoleTask) throws Exception;

	/**
	 * 保存UmTRoleTask信息
	 * @param UmTRoleTask
	 */
	public void saveUmTRoleTask(UmTRoleTask umTRoleTask) throws Exception;

	/**
	 * 根据主键对象，删除UmTRoleTask信息
	 * @param UmTRoleTaskId
	 */
	public void deleteByPK(UmTRoleTaskId id) throws Exception;
	
	/**
	 * 根据角色ID查询出与该角色对应的所有的功能关联
	 * @param roleId
	 * @return
	 */
	public List<UmTRoleTask> findRoleTaskByRoleId(String roleId);

}
