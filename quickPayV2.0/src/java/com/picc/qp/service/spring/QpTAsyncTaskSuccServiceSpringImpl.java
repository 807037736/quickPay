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
import com.picc.qp.dao.QpTAsyncTaskSuccDaoHibernate;
import com.picc.qp.schema.model.QpTAsyncTaskSucc;
import com.picc.qp.schema.model.QpTAsyncTaskSuccId;
import com.picc.qp.service.facade.IQpTAsyncTaskSuccService;

@Service("qpTAsyncTaskSuccService")
public class QpTAsyncTaskSuccServiceSpringImpl implements IQpTAsyncTaskSuccService {
protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTAsyncTaskSuccDaoHibernate qpTAsyncTaskSuccDao;
	
	@Override
	public QpTAsyncTaskSucc findQpTAsyncTaskSuccByPK(QpTAsyncTaskSuccId id)
			throws Exception {
		// TODO Auto-generated method stub
		return qpTAsyncTaskSuccDao.get(QpTAsyncTaskSucc.class,id);
	}

	@Override
	public Page findByQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc, int pageNo,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskSucc.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTaskSucc.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskSuccDao.find(queryRule, pageNo, pageSize);
	}

	@Override
	public List<QpTAsyncTaskSucc> findByQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc)
			throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		//根据QpTAsyncTask生成queryRule
		if(ToolsUtils.notEmpty(qpTAsyncTaskSucc.getTaskId())) {
			queryRule.addEqual("taskId", qpTAsyncTaskSucc.getTaskId());
		}
		queryRule.addAscOrder("createTime");
		return qpTAsyncTaskSuccDao.find(queryRule);
	}

	@Override
	public void updateQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskSuccDao.update(qpTAsyncTaskSucc);
	}

	@Override
	public void saveQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskSuccDao.save(qpTAsyncTaskSucc);
	}

	@Override
	public void deleteByPK(QpTAsyncTaskSuccId id) throws Exception {
		// TODO Auto-generated method stub
		qpTAsyncTaskSuccDao.deleteByPK(QpTAsyncTaskSucc.class,id);
	}

	@Override
	public List<QpTAsyncTaskSucc> findAllInfo() throws Exception {
		// TODO Auto-generated method stub
		QpTAsyncTaskSucc qpTAsyncTaskSucc = new QpTAsyncTaskSucc();
		
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTAsyncTaskSucc);
		List<QpTAsyncTaskSucc> qpTAsyncTaskSuccList = qpTAsyncTaskSuccDao.find(rule);
		return qpTAsyncTaskSuccList;
	}

}
