/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTSaugroup;
import com.picc.um.schema.model.UmTSaugroupId;

/** 
* @ClassName: IUmTSaugroupService 
* @Description: TODO团队信息Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTSaugroupService{

	/**
	 * 根据主键对象UmTSaugroupId获取UmTSaugroup信息
	 * @param UmTSaugroupId
	 * @return UmTSaugroup
	 */
	public UmTSaugroup findUmTSaugroupByPK(UmTSaugroupId id) throws Exception;
	/** 
	* added by Jay 2013-8-22
	* @Title: findUmTSaugroupByTeamCode 
	* @Description: 根据teamcode查找团队信息
	* @return UmTSaugroup 
	* @throws 
	*/
	public UmTSaugroup findUmTSaugroupByTeamCode(String teamCode,String comId) throws Exception;
	
	/** 
	* added by Jay 2013-9-10
	* @Title: findAllTeam 
	* @Description: 根据机构代码查询该机构极其所有下属机构的团队信息
	* @return Page 
	* @throws 
	*/
	public Page findAllTeam(String userCode,String comCode,String fieldValue,int pageNo,int PageSize,String comId) throws Exception;
	
	/** 
	* added by Jay 2013-9-2
	* @Title: isManager 
	* @Description: 判断是否为团队经理
	* @return Boolean 
	* @throws 
	*/
	public Boolean isManager(String userCode,String comId) throws Exception;
	
	/**
	 * 根据umTSaugroup和页码信息，获取Page对象
	 * @param umTSaugroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSaugroup的Page对象
	 */
	public Page findByUmTSaugroup(UmTSaugroup umTSaugroup, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTSaugroup信息
	 * @param UmTSaugroup
	 */
	public void updateUmTSaugroup(UmTSaugroup umTSaugroup) throws Exception;

	/**
	 * 保存UmTSaugroup信息
	 * @param UmTSaugroup
	 */
	public void saveUmTSaugroup(UmTSaugroup umTSaugroup) throws Exception;

	/**
	 * 根据主键对象，删除UmTSaugroup信息
	 * @param UmTSaugroupId
	 */
	public void deleteByPK(UmTSaugroupId id) throws Exception;
	
}
