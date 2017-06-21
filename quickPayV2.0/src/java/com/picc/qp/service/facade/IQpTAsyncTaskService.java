package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import com.picc.qp.schema.model.QpTAsyncTask;
import com.picc.qp.schema.model.QpTAsyncTaskId;

public interface IQpTAsyncTaskService{

	/**
	 * 根据主键对象QpTAsyncTaskId获取QpTAsyncTask信息
	 * @param QpTAsyncTaskId
	 * @return QpTAsyncTask
	 */
	public QpTAsyncTask findQpTAsyncTaskByPK(QpTAsyncTaskId id) throws Exception;
	/**
	 * 根据qpTAsyncTask和页码信息，获取Page对象
	 * @param qpTAsyncTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTAsyncTask的Page对象
	 */
	public Page findByQpTAsyncTask(QpTAsyncTask qpTAsyncTask, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTAsyncTask获取查询列表
     * @param qpTAsyncTask
     * @return 包含QpTAsyncTask
     */
    public List<QpTAsyncTask> findByQpTAsyncTask(QpTAsyncTask qpTAsyncTask) throws Exception;

	/**
	 * 更新QpTAsyncTask信息
	 * @param qpTAsyncTask
	 */
	public void updateQpTAsyncTask(QpTAsyncTask qpTAsyncTask) throws Exception;

	/**
	 * 保存QpTAsyncTask信息
	 * @param qpTAsyncTask
	 */
	public void saveQpTAsyncTask(QpTAsyncTask qpTAsyncTask) throws Exception;

	/**
	 * 根据主键对象，删除qpTAsyncTask信息
	 * @param QpTAsyncTaskId
	 */
	public void deleteByPK(QpTAsyncTaskId id) throws Exception;
		
	public List<QpTAsyncTask> findAllInfo() throws Exception;
	
	public List<QpTAsyncTask> getTaskListByType(String type,Integer pageSize);
	
	public void createTask(String type,Map<String,String> params);
	
}
