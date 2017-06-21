package com.picc.common.utils;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle10gDialect;

public class OracleForPrpallDialect extends Oracle10gDialect {
	
	public OracleForPrpallDialect() {
		super();
		registerColumnType(Types.CHAR, 255, "char($l)"); 
		registerHibernateType(Types.CHAR, Hibernate.STRING.getName());
	}
	
}
