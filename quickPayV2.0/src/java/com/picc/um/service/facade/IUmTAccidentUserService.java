package com.picc.um.service.facade;

import java.util.List;

import com.picc.um.schema.model.UmTAccidentUser;

public interface IUmTAccidentUserService {

	
	
	/**
     * 根据UmTAccidentUser查询列表
     * @param UmTAccidentUser
     * @return 包含UmTAccidentUser
     */
    public List<UmTAccidentUser> findByUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception;
    
//	 /**
//     * 根据Id查询
//     * @param userID
//     * @return UmTAccidentUser
//     */
//    public UmTAccidentUser findById(Integer userID) throws Exception;

	/**
	 * 更新UmTAccidentUser信息
	 * @param UmTAccidentUser
	 */
	public void updateUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception;

	/**
	 * 保存UmTAccidentUser信息
	 * @param UmTAccidentUser
	 */
	public void saveUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception;

	/**
	 * 根据主键对象，删除UmTAccidentUser信息
	 * @param userId
	 */
	public void deleteByPK(Integer userID) throws Exception;
	
}
