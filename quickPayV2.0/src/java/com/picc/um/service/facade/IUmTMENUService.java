/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTMENU;
import com.picc.um.schema.model.UmTMENUId;
import com.picc.um.schema.model.UmTTask;

/**
 * 菜单服务自定义接口
 * @author 杨联
 *
 */
public interface IUmTMENUService {

	/**
	 * 根据主键对象UmTMENUId获取UmTMENU信息
	 * 
	 * @param UmTMENUId
	 * @return UmTMENU
	 * yanglian
	 */
	public UmTMENU findUmTMENUByPK(UmTMENUId id) throws Exception;

	/**
	 * 根据umTMENU和页码信息，获取Page对象
	 * 
	 * @param umTMENU
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTMENU的Page对象
	 * yanglian
	 */
	public Page findByUmTMENU(UmTMENU umTMENU, int pageNo, int pageSize)
			throws Exception;

	/**
	 * 更新UmTMENU信息
	 * 
	 * @param UmTMENU
	 * yanglian
	 */
	public void updateUmTMENU(UmTMENU umTMENU, String userCode)
			throws Exception;

	/**
	 * 保存UmTMENU信息
	 * 
	 * @param UmTMENU
	 * yanglian
	 */
	public void saveUmTMENU(UmTMENU umTMENU, String userCode) throws Exception;

	/**
	 * 通过task对象保存UmTMENU信息
	 * 
	 * @param UmTMENU
	 * yanglian
	 */
	public void saveUmTMENUasUmTTask(UmTTask umttask, String userCode,
			String url) throws Exception;
	/**
	 * 通过task对象更改UmTMENU信息
	 * @param umttask
	 * @param userCode
	 * @param url
	 * @throws Exception
	 * yanglian
	 */
	public void updateUmTMENUasUmTTask(UmTTask umttask, String userCode,
			String url) throws Exception;

	/**
	 * 根据主键对象，删除UmTMENU信息
	 * 
	 * @param UmTMENUId
	 * yanglian
	 */
	public void deleteByPK(UmTMENUId id) throws Exception;

	/**
	 * 查询出所有菜单，创建成树
	 * 
	 * @return
	 * yanglian
	 */
	public String findAllMenu(boolean flag);

	/**
	 * 通过用户代码查询出菜单
	 * 
	 * @return
	 * yanglian
	 */
	public String findMnueByUsercode(String usercode,String comId,String serverCode,boolean flag) throws Exception;
	
	public String findMenuByUsercode(String usercode,String comId,String serverCode,String serverType,boolean flag)
	throws Exception;
	
	@SuppressWarnings("static-access")
	/**
	 * 根据功能ID查询出相应的菜单
	 * @param taskId
	 * @return
	 * 2013-9-2
	 * shenyichan
	 */
	public UmTMENU findByTaskId(String taskId);
	/**
	 * 根据userID查询可操作的系统集
	 * @param usercode
	 * @param comId
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public String findSystemCodesByUsercode(String usercode,String comId, boolean flag)
		throws Exception;

}
