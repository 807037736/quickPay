/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTRoleDime;
import com.picc.um.schema.model.UmTRoleDimeId;
import com.picc.um.schema.model.UmTRoleTask;

/**
 * 角色功能自定义扩展接口
 * @author 姜卫洋
 *
 */
public interface IUmTRoleDimeService{

	/**
	 * 根据主键对象UmTRoleDimeId获取UmTRoleDime信息
	 * @param UmTRoleDimeId
	 * @return UmTRoleDime
	 */
	public UmTRoleDime findUmTRoleDimeByPK(UmTRoleDimeId id) throws Exception;

	/**
	 * 根据umTRoleDime和页码信息，获取Page对象
	 * @param umTRoleDime
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleDime的Page对象
	 */
	public Page findByUmTRoleDime(UmTRoleDime umTRoleDime, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTRoleDime信息
	 * @param UmTRoleDime
	 */
	public void updateUmTRoleDime(UmTRoleDime umTRoleDime) throws Exception;

	/**
	 * 保存UmTRoleDime信息
	 * @param UmTRoleDime
	 */
	public void saveUmTRoleDime(UmTRoleDime umTRoleDime) throws Exception;

	/**
	 * 根据主键对象，删除UmTRoleDime信息
	 * @param UmTRoleDimeId
	 */
	public void deleteByPK(UmTRoleDimeId id) throws Exception;
	
	
	
	/**
	 * 基于ComId的角度向角色功能维度表中维护关联关系
	 * @param umTRoleTask				角色功能关联实体类
	 * @param operateType				操作类型 1新增 2去除 3还原
	 * @param comId 						更新省分机构
	 * @param operaterCode			操作员代码
	 * @throws Exception
	 * 2013-9-11下午8:46:50
	 * jiangweiyang
	 */
	public void updateRoleTaskByRoleDimeCom(UmTRoleTask umTRoleTask,int operateType,String comId,String operaterCode) throws Exception;
	
	
	
	
	
	/**
	 * 同步更新角色维护信息
	 * @param comId
	 * @throws Exception
	 * 2013-9-13上午11:19:33
	 * jiangweiyang
	 */
	public void reBuildRoleDimeCache(String comId)  throws Exception;
	
	
}
