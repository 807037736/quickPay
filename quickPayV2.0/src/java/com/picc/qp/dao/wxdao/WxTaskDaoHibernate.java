package com.picc.qp.dao.wxdao;

import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.qp.schema.model.WxTask;

@Repository("wxTaskDao")
public class WxTaskDaoHibernate extends GenericDaoHibernate<WxTask,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
