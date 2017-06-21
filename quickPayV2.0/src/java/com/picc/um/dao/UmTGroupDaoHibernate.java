/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.schema.model.UmTGroup;

/**
 * 自定义组数据层DAO
 * @author 李明果
 */
@Repository("umTGroupDao")
public class UmTGroupDaoHibernate extends GenericDaoHibernate<UmTGroup,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "commonDao")
	private CommonDaoHibernate commonDao;

	public CommonDaoHibernate getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoHibernate commonDao) {
		this.commonDao = commonDao;
	}
	
}
