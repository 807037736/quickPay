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

import com.picc.um.schema.model.UmTUserRole;

/**
 * 用户角色关联数据层DAO
 * @author 姜卫洋
 */
@Repository("umTUserRoleDao")
public class UmTUserRoleDaoHibernate extends GenericDaoHibernate<UmTUserRole,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
