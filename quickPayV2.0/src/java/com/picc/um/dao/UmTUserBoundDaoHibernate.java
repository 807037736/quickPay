package com.picc.um.dao;

import ins.framework.dao.GenericDaoHibernate;

import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.picc.um.schema.model.UmTUserBound;
@Repository("umTUserBoundDao")
public class UmTUserBoundDaoHibernate extends GenericDaoHibernate<UmTUserBound, String>{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 执行原生的SQL语句
	 * @param exeSQL
	 * @param params
	 */
	public void execute(final String exeSQL,final Object[] params){
		
		Assert.notNull(exeSQL,"sql statement is required!");
		
		String newSQL = exeSQL;
		int pos = 0;
		if (params != null){
			for (int i = 0; i < params.length; ++i) {
				pos = newSQL.indexOf(63, pos);
				if (pos == -1){
					break;
				}
				if ((params[i] instanceof Collection) && (pos > -1)) {
					newSQL = newSQL.substring(0, pos) + ":queryParam" + i
							+ newSQL.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnSQL = newSQL;
		
		try{
			this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							SQLQuery query = session.createSQLQuery(fnSQL);
							if(params!=null){
								for (int i = 0; i < params.length; i++) {
									if (params[i] instanceof Collection) {
										query.setParameterList("queryParam" + i,(Collection) params[i]);
									} else {
										query.setParameter(i, params[i]);
									}
								}
							}
							int result = query.executeUpdate();
							return result;
						}
					});
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
