/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTDictionaryDetail;

/**
 * 数据权限字典明细对应数据层DAO
 * @author 姜卫洋
 */
@Repository("umTDictionaryDetailDao")
public class UmTDictionaryDetailDaoHibernate extends GenericDaoHibernate<UmTDictionaryDetail,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Object getMax(String dictionaryId){
		final String sql = "select max(serialno)+1 from um_t_dictionarydetail where dictionaryid = '"+dictionaryId+"'";
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.uniqueResult();
			}
		});
	}
	
}
