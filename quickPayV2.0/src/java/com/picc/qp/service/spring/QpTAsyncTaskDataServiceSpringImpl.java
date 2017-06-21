package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTAsyncTaskDataDaoHibernate;
import com.picc.qp.schema.model.QpTAsyncTaskData;
import com.picc.qp.schema.model.QpTAsyncTaskDataId;
import com.picc.qp.service.facade.IQpTAsyncTaskDataService;

@Service("qpTAsyncTaskDataService")
public class QpTAsyncTaskDataServiceSpringImpl implements IQpTAsyncTaskDataService {
protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTAsyncTaskDataDaoHibernate qpTAsyncTaskDataDao;
	
	@Override
	public QpTAsyncTaskData findQpTAsyncTaskDataByPK(QpTAsyncTaskDataId id)
			throws Exception {
		// TODO Auto-generated method stub
		return qpTAsyncTaskDataDao.get(QpTAsyncTaskData.class,id);
	}

	@Override
	public Page findByQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData, int pageNo,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskData.getTaskDataId())) {
			queryRule.addEqual("taskDataId", qpTAsyncTaskData.getTaskDataId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskDataDao.find(queryRule, pageNo, pageSize);
	}

	@Override
	public List<QpTAsyncTaskData> findByQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData)
			throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskData.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTaskData.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskDataDao.find(queryRule);
	}

	@Override
	public void updateQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDataDao.update(qpTAsyncTaskData);
	}

	@Override
	public void saveQpTAsyncTaskData(QpTAsyncTaskData qpTAsyncTaskData) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDataDao.save(qpTAsyncTaskData);
	}

	@Override
	public void deleteByPK(QpTAsyncTaskDataId id) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskDataDao.deleteByPK(QpTAsyncTaskData.class,id);
	}

	@Override
	public List<QpTAsyncTaskData> findAllInfo() throws Exception {
		// TODO Auto-generated method stub
		QpTAsyncTaskData qpTAsyncTaskData = new QpTAsyncTaskData();
		
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTAsyncTaskData);
		List<QpTAsyncTaskData> qpTAsyncTaskDataList = qpTAsyncTaskDataDao.find(rule);
		return qpTAsyncTaskDataList;
	}

}
