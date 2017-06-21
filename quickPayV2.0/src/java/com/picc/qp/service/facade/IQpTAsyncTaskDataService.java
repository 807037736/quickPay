package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTAsyncTaskData;
import com.picc.qp.schema.model.QpTAsyncTaskDataId;

public interface IQpTAsyncTaskDataService{

	/**
	 * 根据主键对象QpTAsyncTaskDataId获取QpTAsyncTaskData信息
	 * @param QpTAsyncTaskDataId
	 * @return QpTAsyncTaskData
	 */
	public QpTAsyncTaskData findQpTAsyncTaskDataByPK(QpTAsyncTaskDataId id) throws Exception;
	/**
	 * 根据QpTAsyncTaskData和页码信息，获取Page对象
	 * @param QpTAsyncTaskData
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTAsyncTaskData的Page对象
	 */
	public Page findByQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据QpTAsyncTaskData获取查询列表
     * @param QpTAsyncTaskData
     * @return 包含QpTAsyncTaskData
     */
    public List<QpTAsyncTaskData> findByQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData) throws Exception;

	/**
	 * 更新QpTAsyncTaskData信息
	 * @param QpTAsyncTaskData
	 */
	public void updateQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData) throws Exception;

	/**
	 * 保存QpTAsyncTaskData信息
	 * @param QpTAsyncTaskData
	 */
	public void saveQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData) throws Exception;

	/**
	 * 根据主键对象，删除QpTAsyncTaskData信息
	 * @param QpTAsyncTaskDataId
	 */
	public void deleteByPK(QpTAsyncTaskDataId id) throws Exception;
		
	public List<QpTAsyncTaskData> findAllInfo() throws Exception;
	
}
