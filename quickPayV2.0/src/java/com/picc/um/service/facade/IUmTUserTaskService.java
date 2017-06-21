/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTUserTask;
import com.picc.um.schema.model.UmTUserTaskId;

/**
 * 用户功能自定义接口
 * @author 姜卫洋
 */
public interface IUmTUserTaskService{

	/**
	 * 根据主键对象UmTUserTaskId获取UmTUserTask信息
	 * @param UmTUserTaskId
	 * @return UmTUserTask
	 */
	public UmTUserTask findUmTUserTaskByPK(UmTUserTaskId id) throws Exception;

	/**
	 * 根据umTUserTask和页码信息，获取Page对象
	 * @param umTUserTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserTask的Page对象
	 */
	public Page findByUmTUserTask(UmTUserTask umTUserTask, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTUserTask信息
	 * @param UmTUserTask
	 */
	public void updateUmTUserTask(UmTUserTask umTUserTask) throws Exception;

	/**
	 * 保存UmTUserTask信息
	 * @param UmTUserTask
	 */
	public void saveUmTUserTask(UmTUserTask umTUserTask) throws Exception;

	/**
	 * 根据主键对象，删除UmTUserTask信息
	 * @param UmTUserTaskId
	 */
	public void deleteByPK(UmTUserTaskId id) throws Exception;
	
	/**
	 * 根据用户代码删除此用户下的所有功能
	 * @param userCode
	 * @throws Exception
	 */
	public void deleteByUserCode(String userCode) throws Exception;
	/**
	 * 操作用户功能关联数据
	 * @param userCode						授权用户代码
	 * @param taskIdArray					功能ID List集合
	 * @param creatorCode					操作员代码
	 * @param operateType				操作类型(1:添加功能 2:取消功能)
	 * @throws Exception					程序运行时抛出的异常信息
	 * 2013-8-9上午10:01:53
	 * jiangweiyang
	 */
	public void addTaskToUser(String userCode,String[] taskIdArray,String creatorCode,int operateType) throws Exception;
	
	
	/**
	 * 根据用户代码返回配置给该用户的功能权限List
	 * @param userCode						用户代码
	 * @return										功能权限List
	 * @throws Exception					程序运行时抛出的异常信息
	 * 2013-8-9上午11:32:01
	 * jiangweiyang
	 */
	public List<UmTUserTask> findUserTaskByUserCode(String userCode) throws Exception;
	
	
	
	
	/**
	 * 根据分配的用户代码、功能ID获取其唯一的用户功能对应关系
	 * @param userCode					用户代码
	 * @param taskId						功能ID
	 * @return								用户功能对应关系
	 * @throws Exception
	 * 2013-8-9上午11:57:56
	 * jiangweiyang
	 */
	public UmTUserTask findUserTaskByUserCodeAndTaskId(String userCode,String taskId) throws Exception;

	
}
