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

import com.picc.um.schema.model.UmTRoleTask;

/**
 * 角色功能数据层DAO
 * @author 姜卫洋
 */
@Repository("umTRoleTaskDao")
public class UmTRoleTaskDaoHibernate extends GenericDaoHibernate<UmTRoleTask,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<UmTRoleTask> findByRoleId(String roleId) {
		String hql = "from UmTRoleTask urt where urt.roleId='"+roleId+"'";
		return this.findByHql(hql);
	}
}
