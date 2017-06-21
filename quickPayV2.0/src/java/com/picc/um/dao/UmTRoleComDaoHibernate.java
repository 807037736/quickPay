/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTRoleCom;

/**
 * 角色机构数据层DAO
 * @author 沈一婵
 */
@Repository("umTRoleComDao")
public class UmTRoleComDaoHibernate extends GenericDaoHibernate<UmTRoleCom,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public List<UmTRoleCom> findByRoleId(String roleId) {
		String hql = "from UmTRoleCom utc where utc.roleId='"+roleId+"'";
		return this.findByHql(hql);
	}

}
