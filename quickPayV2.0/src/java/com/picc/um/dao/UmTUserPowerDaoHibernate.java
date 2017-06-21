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

import com.picc.um.schema.model.UmTUserPower;

/**
 * 用户权限数据层DAO
 * @author 姜卫洋
 */
@Repository("umTUserPowerDao")
public class UmTUserPowerDaoHibernate extends GenericDaoHibernate<UmTUserPower,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
