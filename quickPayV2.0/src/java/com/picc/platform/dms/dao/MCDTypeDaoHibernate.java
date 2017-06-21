package com.picc.platform.dms.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.platform.dms.schema.model.MCDType;

@Repository(value = "mcDTypeDao")
public class MCDTypeDaoHibernate extends GenericDaoHibernate<MCDType, String> {
	
}
