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

import com.picc.um.schema.model.UmTSauuser;

/**
 * 销管团队成员数据层DAO
 * @author 邸杰
 */
@Repository("umTSauuserDao")
public class UmTSauuserDaoHibernate extends GenericDaoHibernate<UmTSauuser,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
