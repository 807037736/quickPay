package com.picc.platform.dms.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.platform.dms.schema.model.MCDNewCode;

@Repository(value = "mcDNewCodeDao")
public class MCDNewCodeDaoHibernate extends GenericDaoHibernate<MCDNewCode, String> {
	
}
