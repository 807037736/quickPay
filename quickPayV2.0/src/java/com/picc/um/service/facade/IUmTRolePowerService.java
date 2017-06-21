/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTRolePower;
import com.picc.um.schema.model.UmTRolePowerId;
import com.picc.um.schema.model.UmTUserRole;

/**
 * 角色关联数据权限自定义接口
 * @author 姜卫洋
 */
public interface IUmTRolePowerService{

	/**
	 * 根据主键对象UmTRolePowerId获取UmTRolePower信息
	 * @param UmTRolePowerId
	 * @return UmTRolePower
	 */
	public UmTRolePower findUmTRolePowerByPK(UmTRolePowerId id) throws Exception;

	/**
	 * 根据umTRolePower和页码信息，获取Page对象
	 * @param umTRolePower
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRolePower的Page对象
	 */
	public Page findByUmTRolePower(UmTRolePower umTRolePower, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTRolePower信息
	 * @param UmTRolePower
	 */
	public void updateUmTRolePower(UmTRolePower umTRolePower) throws Exception;

	/**
	 * 保存UmTRolePower信息
	 * @param UmTRolePower
	 */
	public void saveUmTRolePower(UmTRolePower umTRolePower) throws Exception;

	/**
	 * 根据主键对象，删除UmTRolePower信息
	 * @param UmTRolePowerId
	 */
	public void deleteByPK(UmTRolePowerId id) throws Exception;
	
	
	
	/**
	 * 根据用户与角色的对应关系,联动配置用户的数据权限
	 * @param umTUserRole				用户角色对应关系
	 * 2013-9-10下午7:24:45
	 * jiangweiyang
	 */
	public void addUserPowerByRolePower(UmTUserRole umTUserRole,String comId) throws Exception;
	
	
	
	
	/**
	 * 根据用户与角色的对应关系,联动删除用户配置的数据权限关联
	 * @param umTUserRole				用户角色对应关系
	 * @throws Exception
	 * 2013-9-11上午9:04:07
	 * jiangweiyang
	 */
	public void removeUserPowerByRolePower(UmTUserRole umTUserRole,String comId) throws Exception;
	
	
	
	
	/**
	 * 根据RoleID查询出配置给该角色的角色权限对象
	 * @param roleID					角色ID
	 * @param comId					
	 * @return							角色权限对象
	 * @throws Exception				程序运行时抛出的异常信息
	 * 2013-9-17下午3:16:58
	 * jiangweiyang
	 */
	public List<UmTRolePower> findUmTRolePowerByRoleID(String roleID,String comId) throws Exception;
	
	
	
	/**
	 * 根据角色ID、数据权限字典获取角色附加数据权限对象
	 * @param roleId						角色ID
	 * @param dictioinaryId			数据权限字典ID
	 * @return								角色附加数据权限对象
	 * @throws Exception				程序运行过程抛出的异常信息
	 * 2013-10-10上午10:25:24
	 * jiangweiyang
	 */
	public UmTRolePower findUmTRolePowerByRoleAndDict(String roleId,String dictioinaryId) throws Exception;
	
	
	
	/**
	 * 添加角色附加数据权限集合对象
	 * @param umTRolePowerList			角色数据权限集合对象
	 * @throws Exception
	 * 2013-10-10上午10:27:00
	 * jiangweiyang
	 */
	public void addUmTRolePowerList(List<UmTRolePower> umTRolePowerList) throws Exception;
	
	
	
	/**
	 * 更新角色附加数据权限集合对象
	 * @param umTRolePowerList		角色附加数据权限集合对象
	 * @throws Exception
	 * 2013-10-10上午10:27:40
	 * jiangweiyang
	 */
	public void updateUmTRolePowerList(List<UmTRolePower> umTRolePowerList) throws Exception;
	
	
	
	/**
	 * 删除角色附加数据权限集合对象
	 * @param umTRolePowerList				集合附加数据权限集合对象
	 * @throws Exception
	 * 2013-10-10上午10:40:35
	 * jiangweiyang
	 */
	public void deleteUmTRolePowerList(List<UmTRolePower>  umTRolePowerList) throws Exception;
	
	
	
	
}
