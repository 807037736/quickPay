/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.log.schema.model.LoGTINFO;

/**
 * 日志信息数据层DAO
 * @author 杨联
 *
 */
@Repository("loGTINFODao")
public class LoGTINFODaoHibernate extends GenericDaoHibernate<LoGTINFO,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
