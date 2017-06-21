/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.qp.schema.model.QpTTPPolice;

@Repository("qpTTPPoliceDao")
public class QpTTPPoliceDaoHibernate extends GenericDaoHibernate<QpTTPPolice,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
