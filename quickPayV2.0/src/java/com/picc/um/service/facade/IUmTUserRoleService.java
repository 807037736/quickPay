/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.model.UmTUserRoleId;

/**
 * 用户角色关联自定义接口
 * @author 姜卫洋
 */
public interface IUmTUserRoleService{

	/**
	 * 根据主键对象UmTUserRoleId获取UmTUserRole信息
	 * @param UmTUserRoleId
	 * @return UmTUserRole
	 */
	public UmTUserRole findUmTUserRoleByPK(UmTUserRoleId id) throws Exception;

	/**
	 * 根据umTUserRole和页码信息，获取Page对象
	 * @param umTUserRole
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserRole的Page对象
	 */
	public Page findByUmTUserRole(UmTUserRole umTUserRole, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTUserRole信息
	 * @param UmTUserRole
	 */
	public void updateUmTUserRole(UmTUserRole umTUserRole,String comId) throws Exception;

	/**
	 * 保存UmTUserRole信息
	 * @param UmTUserRole
	 */
	public void saveUmTUserRole(UmTUserRole umTUserRole,String comId) throws Exception;

	/**
	 * 根据主键对象，删除UmTUserRole信息
	 * @param UmTUserRoleId
	 */
	public void deleteByPK(UmTUserRoleId id) throws Exception;
	
	
	/**
	 * 根据机构代码返回其机构下的岗位列表Page对象
	 * @param comCode					机构代码
	 * @return
	 * @throws Exception
	 * 2013-8-9下午2:53:08
	 * jiangweiyang
	 */
	public Page getRoleListByComCode(String comCode) throws Exception; 
	
	public Page getRoleListByComCode(String comCode,String serverCode) throws Exception;
	
	
	/**
	 * 给用户赋予角色信息
	 * @param userCode				用户代码
	 * @param roleArray				角色数组
	 * @param creatorCode			创建人代码
	 * @param operateType		操作类型
	 * @throws Exception			程序运行过程中抛出的异常信息
	 * 2013-8-9下午10:20:36
	 * jiangweiyang
	 */
	public void addRoleToUser(String comId,String userCode,String[] roleArray,String creatorCode,int operateType) throws Exception;
	
	public List<UmTUserRole> findUserRoleByUserCode(String userCode) throws Exception;
}
