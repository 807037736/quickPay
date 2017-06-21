/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleComId;

/**
 * 角色机构关联自定义接口
 * @author 沈一婵
 */
public interface IUmTRoleComService{

	/**
	 * 根据主键对象UmTRoleComId获取UmTRoleCom信息
	 * @param UmTRoleComId
	 * @return UmTRoleCom
	 */
	public UmTRoleCom findUmTRoleComByPK(UmTRoleComId id) throws Exception;

	/**
	 * 根据umTRoleCom和页码信息，获取Page对象
	 * @param umTRoleCom
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleCom的Page对象
	 */
	public Page findByUmTRoleCom(UmTRoleCom umTRoleCom, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTRoleCom信息
	 * @param UmTRoleCom
	 */
	public void updateUmTRoleCom(UmTRoleCom umTRoleCom) throws Exception;

	/**
	 * 保存UmTRoleCom信息
	 * @param UmTRoleCom
	 */
	public void saveUmTRoleCom(UmTRoleCom umTRoleCom) throws Exception;

	/**
	 * 根据主键对象，删除UmTRoleCom信息
	 * @param UmTRoleComId
	 */
	public void deleteByPK(UmTRoleComId id) throws Exception;

	/**
	 * 根据机构查询机构下的角色
	 * @param comCode
	 * @param queryType
	 * @param start
	 * @param limit
	 * @return
	 */
	public String getRoleListByComCode(String comCode, String queryType,
			int start, int limit);
	
	
	/**
	 * 根据角色ID查询出与该角色对应的所有的机构关联
	 * @param roleId
	 * @return
	 */
	public List<UmTRoleCom> findRoleComByRoleId(String roleId);

}
