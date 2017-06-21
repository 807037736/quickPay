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

import com.picc.um.log.schema.model.LoGTTYPE;

/**
 * 日志类型数据层DAO
 * @author 杨联
 */
@Repository("loGTTYPEDao")
public class LoGTTYPEDaoHibernate extends GenericDaoHibernate<LoGTTYPE,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
