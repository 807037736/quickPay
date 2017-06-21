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

import com.picc.um.schema.model.UmTAccount;

/**
 * 用户帐户生成数据层DAO
 * @author 邸杰
 */
@Repository("umTAccountDao")
public class UmTAccountDaoHibernate extends GenericDaoHibernate<UmTAccount,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
