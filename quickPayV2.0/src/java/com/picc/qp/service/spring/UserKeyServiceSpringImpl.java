package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.StringUtils;
import com.picc.qp.dao.UserKeyDaoHibernate;
import com.picc.qp.schema.model.UserKey;
import com.picc.qp.service.facade.UserKeyService;

@Service("userKeyService")
public class UserKeyServiceSpringImpl implements UserKeyService {
    
    @Autowired
    private UserKeyDaoHibernate userKeyDao;

    @Override
    public void save(UserKey userKey) throws Exception {
        userKeyDao.save(userKey);
    }

    @Override
    public void delete(Integer id) {
        userKeyDao.deleteByPK(id);
    }

    @Override
    public Page page(UserKey userKey, Integer pageNo, Integer pageSize) throws Exception {
        return null;
    }

	@Override
	public UserKey findByUserCode(String userCode) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (StringUtils.isNotEmpty(userCode)) {
			queryRule.addEqual("userCode", userCode);
		}
		return userKeyDao.findUnique(queryRule);
	}

}
