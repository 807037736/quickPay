package com.picc.qp.dao.wxdao;

import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.qp.schema.model.WxAccident;

@Repository("wxAccidentDao")
public class WxAccidentDaoHibernate extends GenericDaoHibernate<WxAccident,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
