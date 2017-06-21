package ins.framework.web;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.NoArgSQLFunction;

public class InformixDialect extends org.hibernate.dialect.InformixDialect {
	
	public InformixDialect()
	  {
		registerHibernateType(Types.CHAR, Hibernate.STRING.getName()); 
		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName()); 
		registerHibernateType(Types.SMALLINT, Hibernate.INTEGER.getName()); 
	    registerFunction("current_date", new NoArgSQLFunction("current", Hibernate.DATE, false));
	    registerFunction("current_time", new NoArgSQLFunction("current", Hibernate.TIME, false));
	    registerFunction("current_timestamp", new NoArgSQLFunction("current", Hibernate.TIMESTAMP, false));
	  }

}
