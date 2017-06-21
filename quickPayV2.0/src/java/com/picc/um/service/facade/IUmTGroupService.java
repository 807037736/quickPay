/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTGroupId;

/**
 * 自定义分组接口定义
 * @author 李明果
 */
public interface IUmTGroupService{
	
	public String findGroupId(String groupCode) throws Exception;

	/**
	 * 根据主键对象UmTGROUPId获取UmTGROUP信息
	 * @param UmTGroupId
	 * @return UmTGROUP
	 */
	public UmTGroup findUmTGroupByPK(UmTGroupId id) throws Exception;

	/**
	 * 根据umTGROUP和页码信息，获取Page对象
	 * @param umTGROUP
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTGROUP的Page对象
	 */
	public Page findByUmTGroup(UmTGroup umTGroup,String comCode, int pageNo, int pageSize) throws Exception;
	
	public Page findGroupAnd4sComCode(UmTGroup umTGroup, int pageNo, int pageSize) throws Exception;
	
	public Page findByUmTGroupValid(String sql, Class<?> className, int pageNo, int pageSize) throws Exception;
	
	public boolean isGroupCode(String groupCode)throws Exception;

	/**
	 * 更新UmTGROUP信息
	 * @param UmTGroup
	 */
	public void updateUmTGroup(UmTGroup umTGroup) throws Exception;

	/**
	 * 保存UmTGROUP信息
	 * @param UmTGroup
	 */
	public void saveUmTGroup(UmTGroup umTGroup) throws Exception;

	/**
	 * 根据主键对象，删除UmTGROUP信息
	 * @param UmTGroupId
	 */
	public void deleteByPK(UmTGroupId id) throws Exception;

	public String findGroupName(String groupCode);
	
	/**
	 * 根据组类型获取所有组信息
	 * @return
	 * @author shenyichan
	 */
	public List<String> findAllGroupByType(String groupType,String comId);
	
}
