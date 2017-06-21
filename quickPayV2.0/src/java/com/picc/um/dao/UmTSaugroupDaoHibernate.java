/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTSaugroup;

/**
 * 销管团队数据层DAO
 * @author 邸杰
 */
@Repository("umTSaugroupDao")
public class UmTSaugroupDaoHibernate extends GenericDaoHibernate<UmTSaugroup,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
