package com.picc.common;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class InsertHistoryListener implements PreInsertEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(InsertHistoryListener.class);

	private String currentSQL;
	private String currentHQL;

	public boolean onPreInsert(PreInsertEvent _event) {
		String[] names = _event.getPersister().getPropertyNames();
		Object _obj = null;
		for (int i = 0; i < names.length; i++) {
			if ("inserttimeforhis".equalsIgnoreCase(names[i])) {
//				_event.getState()[i] = retrieveCurrentTimestamp(_event
//						.getPersister().getFactory());
				_event.getState()[i] = new Date(System.currentTimeMillis());
				_obj = _event.getEntity();
				for (Field _field : _obj.getClass().getDeclaredFields()) {
					if ("inserttimeforhis".equalsIgnoreCase(_field.getName())) {
						try {
							_field.setAccessible(true);
							_field.set(_obj, _event.getState()[i]);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							logger.error("", e);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							logger.error("", e);
						}
						break;
					}
				}
				break;
			}
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	private Timestamp retrieveCurrentTimestamp(SessionFactoryImplementor factory) {
		Session tempSession = factory.openSession();
		Timestamp current = null;
		List list = null;
		try {
			if (this.currentHQL == null) {
				if (this.currentSQL == null)
					try {
						String dbName = tempSession.connection().getMetaData()
								.getDatabaseProductName()
								.toLowerCase(Locale.US);

						if (dbName.indexOf("informix") != -1) {
							this.currentSQL = "select current from systables";
						} else {
							if (dbName.indexOf("oracle") != -1) {
								this.currentSQL = "select sysdate from dual";
								SQLQuery query = tempSession
										.createSQLQuery(this.currentSQL);
								query.setMaxResults(1);
								list = query.list();
								current = new Timestamp(
										((Timestamp) list.get(0)).getTime());
								Timestamp localTimestamp1 = current;

								tempSession.close();
								return localTimestamp1;
							}
							if (dbName.indexOf("postgresql") != -1)
								this.currentSQL = "select now() from information_schema.tables";
						}
					} catch (HibernateException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (SQLException e) {
						e.printStackTrace();
						logger.error("", e);
					}

				SQLQuery query = tempSession.createSQLQuery(this.currentSQL);
				query.setMaxResults(1);
				list = query.list();
				current = (Timestamp) list.get(0);
			} else {
				Query query = tempSession.createQuery(this.currentHQL);
				query.setMaxResults(1);
				list = query.list();
				current = (Timestamp) list.get(0);
			}
		} finally {
			tempSession.close();
		}
		return current;
	}
}
