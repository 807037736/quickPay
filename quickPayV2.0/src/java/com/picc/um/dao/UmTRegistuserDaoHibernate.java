/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTRegistuser;

@Repository("umTRegistuserDao")
public class UmTRegistuserDaoHibernate extends GenericDaoHibernate<UmTRegistuser,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
