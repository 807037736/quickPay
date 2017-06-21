package com.picc.qp.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.qp.schema.model.QpTAsyncTaskSucc;

@Repository("qpTAsyncTaskSuccDao")
public class QpTAsyncTaskSuccDaoHibernate extends GenericDaoHibernate<QpTAsyncTaskSucc,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
