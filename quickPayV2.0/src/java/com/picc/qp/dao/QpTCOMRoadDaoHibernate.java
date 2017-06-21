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

import com.picc.qp.schema.model.QpTCOMRoad;

@Repository("qpTCOMRoadDao")
public class QpTCOMRoadDaoHibernate extends GenericDaoHibernate<QpTCOMRoad,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
