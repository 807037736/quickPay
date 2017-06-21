/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTUserPower;
import com.picc.um.schema.model.UmTUserPowerId;
import com.picc.um.schema.vo.UmTUserPowerDictName;

/**
 * 用户数据权限自定义接口
 * @author 姜卫洋
 */
public interface IUmTUserPowerService{
	
	/**
	 * 根据主键对象UmTUserPowerId获取UmTUserPower信息
	 * @param UmTUserPowerId
	 * @return UmTUserPower
	 */
	public UmTUserPower findUmTUserPowerByPK(UmTUserPowerId id) throws Exception;

	/**
	 * 根据umTUserPower和页码信息，获取Page对象
	 * @param umTUserPower
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserPower的Page对象
	 */
	public Page findByUmTUserPower(UmTUserPower umTUserPower, int pageNo, int pageSize) throws Exception;
	
	/**
	 * 根据用户代码查询配置在用户UserCode名下的数据权限列表
	 * @param userCode					查询的用户代码UserCode
	 * @return									配置在用户名下的数据权限列表
	 * @throws Exception					程序运行过程中抛出的异常信息
	 * 2013-8-23下午3:06:43
	 * jiangweiyang
	 */
	public List<UmTUserPowerDictName> findUserPowerByUserCode(String userCode,String comId) throws Exception;
	

	/**
	 * 更新UmTUserPower信息
	 * @param UmTUserPower
	 */
	public void updateUmTUserPower(UmTUserPower umTUserPower) throws Exception;

	/**
	 * 保存UmTUserPower信息
	 * @param UmTUserPower
	 */
	public void saveUmTUserPower(UmTUserPower umTUserPower) throws Exception;

	/**
	 * 根据主键对象，删除UmTUserPower信息
	 * @param UmTUserPowerId
	 */
	public void deleteByPK(UmTUserPowerId id) throws Exception;
	
	
	/**
	 * 往数据库中批量插入数据权限明细数据
	 * @param list						数据权限明细数据List
	 * @throws Exception
	 * 2013-8-6下午3:20:07
	 * jiangweiyang
	 */
	public void insertUmTUserPowerList(List<UmTUserPower> list) throws Exception;
	
	
	
	/**
	 * 通过传入对象List更新数据明细信息
	 * @param list
	 * @param userCode
	 * @throws Exception
	 * 2013-8-6下午5:17:30
	 * jiangweiyang
	 */
	public void updateUmTUserPowerList(List<UmTUserPower> list) throws Exception;
	
	
	
	
	/**
	 * 根据传入UmTDictDetail列表删除数据权限明细数据
	 * @param list
	 * @throws Exception
	 * 2013-8-6下午6:26:24
	 * jiangweiyang
	 */
	public void deleteUmTUserPowerList(List<UmTUserPower> list) throws Exception;
	
	
	/**
	 * 根据传入的UmTDictDetail对象删除数据权限明细
	 * @param umtd
	 * @throws Exception
	 * 2013-8-6下午6:26:56
	 * jiangweiyang
	 */
	public void deleteUmTUserPower(UmTUserPower umtd) throws Exception;
	
	
	
	/**
	 * 根据配置的操作者代码返回其对target目标的操作条件SQL(或操作值)
	 * 如果targetName instanceof String(单表查询,直接传入单表的表名)
	 * 如果targetName instanceof Map(多表查询,传入操作表名与别名之间的映射关系)
	 * @param operateCode		操作者代码
	 * @param targetObject		操作目标对象
	 * @param exceptFields		排除其它的字段
	 * @return								操作条件SQL
	 * @throws Exception
	 * 2013-8-7上午10:29:31
	 * jiangweiyang
	 */
	public String getCondSQLByUserTable(String operateCode,Object targetObject,String ...exceptFields) throws Exception;
	
	
	
	
	/**
	 * 根据构造的UmTUserPower对象查询 数据库中的对象信息
	 * @param umTUserPower  构造的UmTUserPower对象
	 * @return
	 * @throws Exception
	 * 2013-9-11上午9:58:08
	 * jiangweiyang
	 */
	public List<UmTUserPower> findUserTUserPower(UmTUserPower umTUserPower) throws Exception;
	
	
	
	
	/**
	 * 根据用户代码、请求访问URL判断该用户是否可以访问
	 * @param userCode				用户代码
	 * @param requestUrl				请求访问URL
	 * @param comCode				用户所在的机构代码
	 * @return							判断请求是可以访问
	 * @throws Exception			程序运行过程中抛出的异常信息
	 * 2013-10-28下午6:08:06
	 * jiangweiyang
	 */
	public boolean checkUserPowerByRequestUrl(String userCode,String comCode,String requestUrl) throws Exception;
	
	/**
	 * 根据机构查找机构下的人员
	 * @param comCode
	 * @param comId
	 * @param start
	 * @param limit
	 * @return
	 */
	/*public String getUserListByComCode(String comCode, String comId,
			int start, int limit,String requestUrl);*/
}
