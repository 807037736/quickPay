/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTMethodTask;

/**
 * 功能方法数据层DAO
 * @author 姜卫洋
 */
@Repository("umTMethodTaskDao")
public class UmTMethodTaskDaoHibernate extends GenericDaoHibernate<UmTMethodTask,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
