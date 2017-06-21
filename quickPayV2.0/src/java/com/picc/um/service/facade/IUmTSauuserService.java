/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTSauuser;
import com.picc.um.schema.model.UmTSauuserId;

/** 
* @ClassName: IUmTSauuserService 
* @Description: TODO团队成员信息Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTSauuserService{

	/**
	 * 根据主键对象UmTSauuserId获取UmTSauuser信息
	 * @param UmTSauuserId
	 * @return UmTSauuser
	 */
	public UmTSauuser findUmTSauuserByPK(UmTSauuserId id) throws Exception;
	/** 
	* added by Jay 2013-8-22
	* @Title: findUmTSauuserByUsercode 
	* @Description: 根据usercode和有效状态选取一条记录
	* @return UmTSauuser 
	* @throws 
	*/
	public UmTSauuser findUmTSauuserByUsercode(String userCode,String comId) throws Exception;
	
	/** 
	* added by Jay 2013-9-3
	* @Title: findSauuserByTeamCode 
	* @Description: 根据团队代码获取该团队下的团队成员List
	* @return List<UmTSauuser> 
	* @throws 
	*/
	public List<UmTSauuser> findSauuserByTeamCode(String teamCode,String comId);
	/** 
	* added by Jay 2013-9-2
	* @Title: getMyTeamMember 
	* @Description: 根据用户代码获取其所在团队的成员Page
	* @return Page 
	* @throws 
	*/
	public Page getMyTeamMember(String userCode,int pageNo,int pageSize,String comId) throws Exception;
	
	/** 
	* added by Jay 2013-10-31
	* @Title: getTeamMemberInLimit 
	* @Description: 根据comcode、teamcode、数据权限查询团队成员
	* @return Page 
	* @throws 
	*/
	public Page getTeamMemberInLimit(String userCode,String comCode,String teamCode,String fieldValue,int pageNo,int pageSize,String comId) throws Exception;
	/** 
	* added by Jay 2013-8-23
	* @Title: findSauuserPageByTeamCode 
	* @Description: 根据团队代码获取该团队下的团队成员page
	* @return Page 
	* @throws 
	*/
	public Page findSauuserPageByTeamCode(String teamCode,int pageNo, int pageSize,String comId);
	/**
	 * 根据umTSauuser和页码信息，获取Page对象
	 * @param umTSauuser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSauuser的Page对象
	 */
	public Page findByUmTSauuser(UmTSauuser umTSauuser, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTSauuser信息
	 * @param UmTSauuser
	 */
	public void updateUmTSauuser(UmTSauuser umTSauuser) throws Exception;

	/**
	 * 保存UmTSauuser信息
	 * @param UmTSauuser
	 */
	public void saveUmTSauuser(UmTSauuser umTSauuser) throws Exception;

	/**
	 * 根据主键对象，删除UmTSauuser信息
	 * @param UmTSauuserId
	 */
	public void deleteByPK(UmTSauuserId id) throws Exception;
	
}
