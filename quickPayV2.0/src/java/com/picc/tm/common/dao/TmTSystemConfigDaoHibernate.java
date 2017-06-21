/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.tm.common.schema.model.TmTSystemConfig;

@Repository("tmTSystemConfigDao")
public class TmTSystemConfigDaoHibernate extends GenericDaoHibernate<TmTSystemConfig,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
