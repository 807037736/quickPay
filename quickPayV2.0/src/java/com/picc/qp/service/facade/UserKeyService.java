package com.picc.qp.service.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.UserKey;

public interface UserKeyService {
    
    public void save(UserKey userKey) throws Exception;
    
    public void delete(Integer id);
    
    public Page page(UserKey userKey, Integer pageNo, Integer pageSize) throws Exception;
    
    public UserKey findByUserCode(String userCode) throws Exception;
    
}
