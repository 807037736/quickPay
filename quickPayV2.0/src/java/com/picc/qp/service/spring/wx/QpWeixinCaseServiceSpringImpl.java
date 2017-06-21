package com.picc.qp.service.spring.wx;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.StringUtils;
import com.picc.qp.dao.wxdao.QpWeixinAccidentDaoHibernate;
import com.picc.qp.dao.wxdao.QpWeixinCaseDaoHibernate;
import com.picc.qp.dao.wxdao.WxAccidentDaoHibernate;
import com.picc.qp.dao.wxdao.WxCaseDaoHibernate;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.service.wx.facade.QpWeixinCaseService;

@Service("qpWeixinCaseService")
public class QpWeixinCaseServiceSpringImpl implements QpWeixinCaseService {

    @Autowired
    private WxAccidentDaoHibernate wxAccidentDao;

    @Autowired
    private WxCaseDaoHibernate wxCaseDao;

    @Autowired
    private QpWeixinCaseDaoHibernate qpWeixinCaseDao;

    @Autowired
    private QpWeixinAccidentDaoHibernate qpWeixinAccidentDao;

    @Autowired
    private SysCommonDaoHibernate sysCommonDao;

    @Autowired
    private CommonDaoHibernate commonDao;

    @Override
    public Page findQpWeixinCasePageBySql(WxAccident wxAccident, int pageNo, int pageSize) {
	// qpWeixinCaseDao.find(queryRule, pageNo, pageSize)
	QueryRule queryRule = QueryRule.getInstance();
	boolean paramFlag = true;
	if (!StringUtils.isEmptyStr(wxAccident)) {
	    if (!StringUtils.isEmptyStr(wxAccident.getCaseid())) {
		queryRule.addEqual("caseid", wxAccident.getCaseid());
		paramFlag = false;
	    }
	    if (!StringUtils.isEmptyStr(wxAccident.getAccidentname())) {
		queryRule.addEqual("accidentname", wxAccident.getAccidentname());
		paramFlag = false;
	    }
	    if (!StringUtils.isEmptyStr(wxAccident.getIdentfinynumber())) {
		queryRule.addEqual("identfinynumber", wxAccident.getIdentfinynumber());
		paramFlag = false;
	    }
	    if (!StringUtils.isEmptyStr(wxAccident.getLicensenumber())) {
		queryRule.addEqual("licensenumber", wxAccident.getLicensenumber());
		paramFlag = false;
	    }
	}
	if (paramFlag) {
	    queryRule.addEqual("caseid", "-999");
	}
	List<WxAccident> wxAccidents = qpWeixinAccidentDao.find(queryRule);
	if (wxAccidents == null || wxAccidents.size() <= 0) {
	    return null;
	}
	Set<String> caids = new HashSet<String>();
	for (WxAccident wxAccident2 : wxAccidents) {
	    caids.add(String.valueOf(wxAccident2.getCaseid()));
	}
	List<String> caseids = new ArrayList<String>();
	for (String string : caids) {
	    caseids.add(string);
	}
	queryRule = QueryRule.getInstance();
	queryRule.addIn("id.id", caseids);
	Page pages = qpWeixinCaseDao.find(queryRule, pageNo, pageSize);
	return pages;
    }

}
