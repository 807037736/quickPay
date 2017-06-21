/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.common.Page;
import ins.framework.dao.GenericDaoHibernate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.schema.model.UtiIUser;

/**
 * 三代用户UtiiUser数据层DAO
 * @author 邸杰
 */
@Repository("utiiUserDao")
public class UtiiUserDaoHibernate extends GenericDaoHibernate<UtiIUser,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "commonDao")
	private CommonDaoHibernate commonDao;

	public void setCommonDao(CommonDaoHibernate commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDaoHibernate getCommonDao() {
		return commonDao;
	}

	public Page getUsers(String comId, String comCode, int pageNo, int pageSize) {
		String sql = "SELECT * FROM UM_T_UTIIUSER WHERE VALIDSTATUS='1' and comID = ? and comCode=?";
		try {
			return commonDao.findObjectPageBySql(sql, UtiIUser.class, pageNo, pageSize, comId, comCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}
}
