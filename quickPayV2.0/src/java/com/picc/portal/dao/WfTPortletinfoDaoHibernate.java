/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.dao;
import ins.framework.dao.GenericDaoHibernate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.portal.schema.model.WfTPortletinfo;

@Repository("wfTPortletinfoDao")
public class WfTPortletinfoDaoHibernate extends GenericDaoHibernate<WfTPortletinfo,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "commonDao")
	private CommonDaoHibernate commonDao;

	public void setCommonDao(CommonDaoHibernate commonDao) {
		this.commonDao = commonDao;
	}

	public CommonDaoHibernate getCommonDao() {
		return commonDao;
	}

}
