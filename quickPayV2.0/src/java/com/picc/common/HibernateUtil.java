package com.picc.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateUtil extends HibernateDaoSupport {

	private static Configuration hibernateConf = new Configuration();
	
	/**
	 * 获得表名
	 * 
	 * @param clazz 映射到数据库的po类
	 * @return String
	 */
	public static String getTableName(Class<?> clazz) {
		return getPersistentClass(clazz).getTable().getName();
	}

	/**
	 * 获得列名
	 * 
	 * @param clazz 映射到数据库的po类
	 * @param icol 第几列
	 * @return String
	 */
	public static String getColumnName(Class<?> clazz, int icol) {
		return getPersistentClass(clazz).getTable().getColumn(icol).getName();
	}
	
	/**
	 * 获得所有列名
	 * 
	 * @param clazz 映射到数据库的po类
	 * @return List<String> 列名
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getColumnNames(Class<?> clazz) {
		Iterator<Column> itr = getPersistentClass(clazz).getTable().getColumnIterator();
		List<String> columns = new ArrayList<String>();
		while (itr.hasNext()) {
			Column tmp = itr.next();
			columns.add(tmp.getName());
			tmp.getCanonicalName();
			tmp.getQuotedName();
		}
		return columns;
	}
	
	/**
	 * 获得Hibernate持久化类
	 * 
	 * @param clazz
	 * @return PersistentClass
	 */
	private static PersistentClass getPersistentClass(Class<?> clazz) {
		synchronized (HibernateUtil.class) {
			PersistentClass pc = hibernateConf.getClassMapping(clazz.getName());
			if (pc == null) {
				hibernateConf = hibernateConf.addClass(clazz);
				pc = hibernateConf.getClassMapping(clazz.getName());
			}
			return pc;
		}
	}
	
}
