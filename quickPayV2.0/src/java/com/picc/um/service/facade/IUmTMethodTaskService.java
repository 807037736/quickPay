/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.HashMap;
import java.util.List;

import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTMethodTaskId;
import com.picc.um.schema.model.UmTTask;

/**
 * 方法功能自定义接口
 * @author 姜卫洋
 */
public interface IUmTMethodTaskService{

	/**
	 * 根据主键对象UmTMethodTaskId获取UmTMethodTask信息
	 * @param UmTMethodTaskId
	 * @return UmTMethodTask
	 */
	public UmTMethodTask findUmTMethodTaskByPK(UmTMethodTaskId id) throws Exception;

	public List<UmTMethodTask> getOpenedMethodTaskList();
	
	/**
	 * 根据umTMethodTask和页码信息，获取Page对象
	 * @param umTMethodTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTMethodTask的Page对象
	 */
	public Page findByUmTMethodTask(UmTMethodTask umTMethodTask, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTMethodTask信息
	 * @param UmTMethodTask
	 */
	public void updateUmTMethodTask(UmTMethodTask umTMethodTask) throws Exception;

	/**
	 * 保存UmTMethodTask信息
	 * @param UmTMethodTask
	 */
	public void saveUmTMethodTask(UmTMethodTask umTMethodTask) throws Exception;

	/**
	 * 根据主键对象，删除UmTMethodTask信息
	 * @param UmTMethodTaskId
	 */
	public void deleteByPK(UmTMethodTaskId id) throws Exception;
	
	
	/**
	 * 根据用户代码返回方法功能的请求URL
	 * @param userCode						用户代码
	 * @return										请求URL形成的List集合
	 * @throws Exception
	 * 2013-8-12下午4:50:58
	 * jiangweiyang
	 */
	//public List<String> getMethodCodeByUserCode(String userCode) throws Exception;
	
	
	
	/**
	 * 根据角色代码返回方法功能的请求URL
	 * @param roleId				角色ID
	 * @return								请求URL形成的List集合
	 * @throws Exception
	 * 2013-8-12下午4:55:15
	 * jiangweiyang
	 */
	public List<String> getMethodCodeByRoleCode(String roleId) throws Exception;
	
	
	
	/**
	 * 获取所有有效的功能方法集合
	 * @return
	 * 2013-8-13下午2:39:41
	 * jiangweiyang
	 */
	public List<UmTMethodTask> getValidMethodTaskList();
	
	
	
//	public void initPowerCache();
	
	
	/**
	 * 
	 * @param umTMethodTask
	 * @return
	 * @throws Exception
	 */
	public List<UmTMethodTask> findByUmTMethodTaskByTaskId(UmTMethodTask umTMethodTask) throws Exception;

	/**
	 * 当portal页面添加portal链接时在task表methodtask表同步添加
	 * @param url
	 * @throws Exception
	 */
	public void saveUmTMethodTask4portal(WfTPortletclassfy wfTPortletclassfy,String userCode)throws Exception;

	/**
	 * 根据功能ID查询功能的URL
	 * @param taskId
	 * @return
	 * 2013-9-3
	 * shenyichan 
	 */
	public UmTMethodTask findByTaskId(String taskId);

	/**
	 * 修改功能的同时修改方法
	 * @param umTTask
	 * 2013-9-3
	 * shenyichan
	 */
	public void updateUmTMethodTaskasUmTTask(UmTTask umTTask);

	
	/**
	 * 增加功能的同时增加方法
	 * @param umTTask
	 * 2013-9-3
	 * shenyichan
	 */
	public void saveUmTMethodTaskasUmTTask(UmTTask umTTask);
	
	
	
	/**
	 *  根据访问MethodCode返回其所分配的角色代码
	 * @param methodCode				访问点MethodCode
	 * @return							角色代码(以逗号分隔)
	 * 2013-9-8下午8:15:35
	 * jiangweiyang
	 */
	public List<String> getRoleCodeListByMethodCode(String methodCode);
	
	@SuppressWarnings("unchecked")
	public HashMap<String,UmTMethodTask> getMethodTaskMapByUserCode(String userCode)
			throws Exception;
	
	
}
