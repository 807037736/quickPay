package com.picc.qp.service.wx.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.WxAccident;

public interface WxAccidentService {

	/**
	 * 查询临时当事人列表
	 * 
	 * @param wxAccident
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public List<WxAccident> list(WxAccident wxAccident) throws Exception;

	/**
	 * 查询临时当事人列表分页数据
	 * 
	 * @param wxAccident
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public Page page(WxAccident wxAccident, Integer pageNo, Integer pageSize)
			throws Exception;

	/**
	 * 保存临时当事人
	 * 
	 * @param wxAccident
	 * @throws Exception
	 * @author obba
	 */
	public void save(WxAccident wxAccident) throws Exception;

	/**
	 * 删除临时当事人
	 * 
	 * @param wxAccident
	 * @throws Exception
	 * @author obba
	 */
	public void delete(WxAccident wxAccident) throws Exception;

}
