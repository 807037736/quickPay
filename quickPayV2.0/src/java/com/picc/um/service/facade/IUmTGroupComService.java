/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTGroupCom;
import com.picc.um.schema.model.UmTGroupComId;

/**
 * 自定义分组机构关联自定义接口
 * @author 李明果
 */
public interface IUmTGroupComService{
	
	public List<UmTGroupCom> findUmTGroupComByGroupId(UmTGroupCom umTGroupCom) throws Exception;
	
	public Page findGroupAnd4sByComcode(String comCode)throws Exception;
	
	public Page findCom4sByComcode(String comCode)throws Exception;
	
	public Page findGroupAnd4sByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception;
	
	public Page findGroupAndComByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception;
	
	public Page find4sByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception;

	public List<UmTGroup> findGroupAnd4sByComCodeEqual(String comCode) throws Exception;
	
	/**
	 * 根据主键对象UmTGROUPCOMId获取UmTGROUPCOM信息
	 * @param UmTGroupComId
	 * @return UmTGROUPCOM
	 */
	public UmTGroupCom findUmTGroupComByPK(UmTGroupComId id) throws Exception;

	/**
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param umTGROUPCOM
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTGROUPCOM的Page对象
	 */
	public Page findByUmTGroupCom(UmTGroupCom umTGroupCom, String comcode,int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTGROUPCOM信息
	 * @param UmTGroupCom
	 */
	public void updateUmTGroupCom(UmTGroupCom umTGroupCom) throws Exception;

	/**
	 * 保存UmTGROUPCOM信息
	 * @param UmTGroupCom
	 */
	public void saveUmTGroupCom(UmTGroupCom umTGroupCom) throws Exception;
	
	public void saveUmTGroupComList(List<UmTGroupCom> groupComList) throws Exception;

	/**
	 * 根据主键对象，删除UmTGROUPCOM信息
	 * @param UmTGroupComId
	 */
	public void deleteByPK(UmTGroupComId id) throws Exception;
	
	/*判断车行代码是否存在*/
	public boolean isGroupComCode(String groupCode, String comCode) throws Exception;
	
}
