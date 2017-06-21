package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.utils.UuidUtil;
import com.picc.qp.dao.QpTAsyncTaskDaoHibernate;
import com.picc.qp.schema.model.QpTAsyncTask;
import com.picc.qp.schema.model.QpTAsyncTaskData;
import com.picc.qp.schema.model.QpTAsyncTaskDataId;
import com.picc.qp.schema.model.QpTAsyncTaskId;
import com.picc.qp.service.facade.IQpTAsyncTaskDataService;
import com.picc.qp.service.facade.IQpTAsyncTaskService;

@Service("qpTAsyncTaskService")
public class QpTAsyncTaskServiceSpringImpl implements IQpTAsyncTaskService {
protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTAsyncTaskDaoHibernate qpTAsyncTaskDao;
	
	@Autowired
	private IQpTAsyncTaskDataService qpTAsyncTaskDataService;
	
	@Override
	public QpTAsyncTask findQpTAsyncTaskByPK(QpTAsyncTaskId id)
			throws Exception {
		// TODO Auto-generated method stub
		return qpTAsyncTaskDao.get(QpTAsyncTask.class,id);
	}

	@Override
	public Page findByQpTAsyncTask(QpTAsyncTask qpTAsyncTask, int pageNo,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTask.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTask.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskDao.find(queryRule, pageNo, pageSize);
	}

	@Override
	public List<QpTAsyncTask> findByQpTAsyncTask(QpTAsyncTask qpTAsyncTask)
			throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTask.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTask.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskDao.find(queryRule);
	}

	@Override
	public void updateQpTAsyncTask(QpTAsyncTask qpTAsyncTask) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDao.update(qpTAsyncTask);
	}

	@Override
	public void saveQpTAsyncTask(QpTAsyncTask qpTAsyncTask) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDao.save(qpTAsyncTask);
	}

	@Override
	public void deleteByPK(QpTAsyncTaskId id) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDao.deleteByPK(QpTAsyncTask.class,id);
	}

	@Override
	public List<QpTAsyncTask> findAllInfo() throws Exception {
		// TODO Auto-generated method stub
		QpTAsyncTask qpTAsyncTask = new QpTAsyncTask();
		
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTAsyncTask);
		List<QpTAsyncTask> qpTAsyncTaskList = qpTAsyncTaskDao.find(rule);
		return qpTAsyncTaskList;
	}
	
	@SuppressWarnings("unchecked")
	public List<QpTAsyncTask> getTaskListByType(String type,Integer pageSize){
		List<QpTAsyncTask> taskList = null;
		try{
			QpTAsyncTask qpTAsyncTask = new QpTAsyncTask();
			qpTAsyncTask.setType(type);
			Page page = this.findByQpTAsyncTask(qpTAsyncTask, 1, pageSize);
			if(page!=null){
				taskList = page.getResult();
				if(taskList!=null&&taskList.size()>0){
					for(QpTAsyncTask task:taskList){
						String taskId = task.getTaskId();

						QpTAsyncTaskData qpTAsyncTaskData = new QpTAsyncTaskData();
						qpTAsyncTaskData.setTaskId(taskId);
						List<QpTAsyncTaskData> taskDataList = qpTAsyncTaskDataService.
								findByQpTAsyncTaskData(qpTAsyncTaskData);
						Map<String,String> params = new HashMap<String,String>();
						for(QpTAsyncTaskData taskData:taskDataList){
							String key = taskData.getDataKey();
							String value =  taskData.getDateValue();
							params.put(key, value);
						}
						task.setParams(params);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return taskList;
	}

	@Override
	public void createTask(String type, Map<String, String> params){
		try{
			QpTAsyncTask task = new QpTAsyncTask();
			String taskId = UuidUtil.get32UUID();
			QpTAsyncTaskId qpTAsyncTaskId = new QpTAsyncTaskId();
			qpTAsyncTaskId.setTaskId(taskId);
			task.setId(qpTAsyncTaskId);
			task.setType(type);
			task.setStatus("0");
			task.setCreateTime(new Date());
			task.setUploadTimes(0);
			this.saveQpTAsyncTask(task);
//			Iterator<String> it = params.keySet().iterator();
//			while(it.hasNext()){
//				String dataKey = it.next();
//				QpTAsyncTaskData taskData = new QpTAsyncTaskData();
//				QpTAsyncTaskDataId qpTAsyncTaskDataId = new QpTAsyncTaskDataId();
//				qpTAsyncTaskDataId.setTaskDataId(UuidUtil.get32UUID());
//				taskData.setId(qpTAsyncTaskDataId);
//				taskData.setTaskId(taskId);
//				taskData.setCreateTime(new Date());
//				taskData.setDataKey(dataKey);
//				taskData.setDateValue(params.get(dataKey));
//				qpTAsyncTaskDataService.saveQpTAsyncTaskData(taskData);
//			}
			QpTAsyncTaskData taskData = new QpTAsyncTaskData();
			QpTAsyncTaskDataId qpTAsyncTaskDataId = new QpTAsyncTaskDataId();
			qpTAsyncTaskDataId.setTaskDataId(UuidUtil.get32UUID());
			taskData.setId(qpTAsyncTaskDataId);
			taskData.setTaskId(taskId);
			taskData.setCreateTime(new Date());
			taskData.setDataKey("caseId");
			taskData.setDateValue(params.get("caseId"));
			qpTAsyncTaskDataService.saveQpTAsyncTaskData(taskData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
