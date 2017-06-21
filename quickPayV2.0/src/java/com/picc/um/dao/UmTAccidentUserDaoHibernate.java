package com.picc.um.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTAccidentUser;

@Repository("umTAccidentUserDaoHibernate")
public class UmTAccidentUserDaoHibernate extends GenericDaoHibernate<UmTAccidentUser,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
