package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTAsyncTaskErrDaoHibernate;
import com.picc.qp.schema.model.QpTAsyncTaskData;
import com.picc.qp.schema.model.QpTAsyncTaskErr;
import com.picc.qp.schema.model.QpTAsyncTaskErrId;
import com.picc.qp.service.facade.IQpTAsyncTaskDataService;
import com.picc.qp.service.facade.IQpTAsyncTaskErrService;

@Service("qpTAsyncTaskErrService")
public class QpTAsyncTaskErrServiceSpringImpl implements IQpTAsyncTaskErrService {
protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTAsyncTaskErrDaoHibernate qpTAsyncTaskErrDao;
	
	@Autowired
	private IQpTAsyncTaskDataService qpTAsyncTaskDataService;
	
	@Override
	public QpTAsyncTaskErr findQpTAsyncTaskErrByPK(QpTAsyncTaskErrId id)
			throws Exception {
		// TODO Auto-generated method stub
		return qpTAsyncTaskErrDao.get(QpTAsyncTaskErr.class,id);
	}

	@Override
	public Page findByQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr, int pageNo,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskErr.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTaskErr.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskErrDao.find(queryRule, pageNo, pageSize);
	}

	@Override
	public List<QpTAsyncTaskErr> findByQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr)
			throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskErr.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTaskErr.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskErrDao.find(queryRule);
	}

	@Override
	public void updateQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskErrDao.update(qpTAsyncTaskErr);
	}

	@Override
	public void saveQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskErrDao.save(qpTAsyncTaskErr);
	}

	@Override
	public void deleteByPK(QpTAsyncTaskErrId id) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskErrDao.deleteByPK(QpTAsyncTaskErr.class,id);
	}

	@Override
	public List<QpTAsyncTaskErr> findAllInfo() throws Exception {
		// TODO Auto-generated method stub
		QpTAsyncTaskErr qpTAsyncTaskErr = new QpTAsyncTaskErr();
		
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTAsyncTaskErr);
		List<QpTAsyncTaskErr> qpTAsyncTaskErrList = qpTAsyncTaskErrDao.find(rule);
		return qpTAsyncTaskErrList;
	}
	
	@SuppressWarnings("unchecked")
	public List<QpTAsyncTaskErr> getFailTaskListByType(String type,Integer pageSize){
		List<QpTAsyncTaskErr> list = null;
		try{
			QpTAsyncTaskErr qpTAsyncTaskErr = new QpTAsyncTaskErr();
			qpTAsyncTaskErr.setType(type);
			Page page = this.findByQpTAsyncTaskErr(qpTAsyncTaskErr, 0, pageSize);
			if(page!=null){
				list = page.getResult();
				if(list!=null&&list.size()>0){
					for(QpTAsyncTaskErr err:list){
						String taskId = err.getTaskId();

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
						err.setParams(params);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
