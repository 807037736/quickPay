package com.picc.qp.service.wx.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.WxAccident;

public interface QpWeixinCaseService {

    
    	/**
    	 * 查询菜单微信案件 -- 快处快赔微信案件处理用
    	 * @param wxAccident
    	 * @param pageNo
    	 * @param pageSize
    	 * @return
    	 */
    	Page findQpWeixinCasePageBySql(WxAccident wxAccident, int pageNo, int pageSize); 
}
