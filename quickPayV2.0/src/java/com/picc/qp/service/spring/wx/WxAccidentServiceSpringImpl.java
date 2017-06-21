package com.picc.qp.service.spring.wx;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.qp.dao.wxdao.WxAccidentDaoHibernate;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.service.wx.facade.WxAccidentService;

@Service("wxAccidentService")
public class WxAccidentServiceSpringImpl implements WxAccidentService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WxAccidentDaoHibernate wxAccidentDao;

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据临时当事人ID查询临时当事人列表<br>
	 * 目前无用
	 * 
	 * @param wxAccident
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public List<WxAccident> list(WxAccident wxAccident) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (wxAccident.getId() != null) {
			queryRule.addEqual("id", wxAccident.getId());
		}
		return wxAccidentDao.find(queryRule);
	}

	/**
	 * 根据临时当事人ID和临时案件ID查询临时当事人列表分页数据<br>
	 * 
	 * @param wxAccident
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public Page page(WxAccident wxAccident, Integer pageNo, Integer pageSize)
			throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (wxAccident.getId() != null) {
			queryRule.addEqual("id", wxAccident.getId());
		}
		if (wxAccident.getCaseid() != null) {
			queryRule.addEqual("caseid", wxAccident.getCaseid());
		}
		queryRule.addAscOrder("id");
		return wxAccidentDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 保存临时当事人
	 * 
	 * @param wxAccident
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public void save(WxAccident wxAccident) throws Exception {
		wxAccidentDao.save(wxAccident);
	}

	/**
	 * 删除临时当事人
	 * 
	 * @param wxAccident
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public void delete(WxAccident wxAccident) throws Exception {
		// TODO Auto-generated method stub
	}

}
