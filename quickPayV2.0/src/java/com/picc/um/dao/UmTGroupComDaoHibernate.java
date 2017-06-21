/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.schema.model.UmTGroupCom;

/**
 * 自定义组与机构关联数据层DAO
 * @author 李明果
 */
@Repository("umTGroupComDao")
public class UmTGroupComDaoHibernate extends GenericDaoHibernate<UmTGroupCom,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "commonDao")
	private CommonDaoHibernate commonDao;

	public CommonDaoHibernate getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoHibernate commonDao) {
		this.commonDao = commonDao;
	}
	
	public int getNextSeqIDForSerialNo() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(super.getSessionFactory());
		return (Integer)hibernateTemplate.execute(
		new org.springframework.orm.hibernate3.HibernateCallback()
		{
		public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException, SQLException {
			return session.createSQLQuery("select UM_T_DICTDETAIL_SQ.NEXTVAL as id from dual")
					.addScalar("id", Hibernate.INTEGER).uniqueResult();
			}
		});
	}

}
