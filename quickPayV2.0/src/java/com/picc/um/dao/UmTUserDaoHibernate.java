/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTUser;

/**
 * 用户处理数据层DAO
 * @author 邸杰
 */
@Repository("umTUserDao")
public class UmTUserDaoHibernate extends GenericDaoHibernate<UmTUser,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<UmTUser> findUserListByComCode(String comCode,String comId){
		String hql = "select u from UmTUser u where u.comCode=? and u.comId=? and u.validStatus='1'";
		List<UmTUser> userList = this.findByHql(hql, comCode,comId);
		return userList;
	}
}
