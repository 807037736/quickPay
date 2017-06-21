/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTRole;

/**
 * 角色对象数据层DAO
 * @author 沈一婵
 */
@Repository("umTRoleDao")
public class UmTRoleDaoHibernate extends GenericDaoHibernate<UmTRole,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 根据机构查询角色
	 * @param comCode
	 * @return
	 * @author shenyichan
	 */
	public List<UmTRole> findRoleListByComCode(String comCode){
		String hql = "select ur from UmTRole ur join fetch ur.umRoleComs rc where rc.comCode=?";
		List<UmTRole> roleList = this.findByHql(hql, comCode);
		return roleList;
	}

	/**
	 * 更改角色的标识为 cognos角色
	 * @param roleId
	 * @author shenyichan
	 */
	public void updateToCognosRole(final String roleId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String sql = "update um_t_role set flag='1'||substr(flag,2) where roleid=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString(0, roleId);
				query.executeUpdate();
				session.close();
				return null;
			}
		});
	}
}
