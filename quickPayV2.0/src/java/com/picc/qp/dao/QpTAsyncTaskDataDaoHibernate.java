package com.picc.qp.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.qp.schema.model.QpTAsyncTaskData;

@Repository("qpTAsyncTaskDataDao")
public class QpTAsyncTaskDataDaoHibernate extends GenericDaoHibernate<QpTAsyncTaskData,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
