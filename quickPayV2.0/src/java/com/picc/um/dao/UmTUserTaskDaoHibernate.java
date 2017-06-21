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

import com.picc.um.schema.model.UmTUserTask;

/**
 * 用户功能关联数据层在DAO
 * @author 姜卫洋
 */
@Repository("umTUserTaskDao")
public class UmTUserTaskDaoHibernate extends GenericDaoHibernate<UmTUserTask,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
