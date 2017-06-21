/*
 * author zhou
 */

package com.picc.portal.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.ToolsUtils;
import com.picc.portal.dao.WfTPortletinfoDaoHibernate;
import com.picc.portal.schema.model.WfTPortletinfo;
import com.picc.portal.schema.model.WfTPortletinfoId;
import com.picc.portal.service.facade.IWfTPortletinfoService;
import com.picc.um.service.facade.IUmTTaskService;

@Service("wfTPortletinfoService")
public class WfTPortletinfoServiceSpringImpl extends GenericDaoHibernate<WfTPortletinfo, Integer> implements IWfTPortletinfoService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WfTPortletinfoDaoHibernate wfTPortletinfoDao;

	private WfTPortletinfo wfTPortletinfo;

	@Autowired
	private IUmTTaskService umTTaskService;

	/**
	 * 根据主键对象WfTPortletinfoId获取WfTPortletinfo信息
	 * 
	 * @param WfTPortletinfoId
	 * @return WfTPortletinfo
	 */
	public WfTPortletinfo findWfTPortletinfoByPK(WfTPortletinfoId id) throws Exception {

		return wfTPortletinfoDao.get(WfTPortletinfo.class, id);
	}

	/**
	 * 根据wfTPortletinfo和页码信息，获取Page对象
	 * 
	 * @param wfTPortletinfo
	 * @param pageNo
	 * @param pageSize
	 * @return 包含WfTPortletinfo的Page对象
	 */
	public Page findByWfTPortletinfo(WfTPortletinfo wfTPortletinfo, int pageNo, int pageSize) throws Exception {

		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		// 根据wfTPortletinfo生成queryRule

		return wfTPortletinfoDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * 
	 */
	public Page find(QueryRule rule, int pageNo, int pageSize) {
		return wfTPortletinfoDao.find(rule, pageNo, pageSize);
	}

	/**
	 * 更新WfTPortletinfo信息
	 * 
	 * @param WfTPortletinfo
	 */
	public void updateWfTPortletinfo(WfTPortletinfo wfTPortletinfo) throws Exception {

		wfTPortletinfoDao.update(wfTPortletinfo);
	}

	/**
	 * 保存WfTPortletinfo信息
	 * 
	 * @param WfTPortletinfo
	 */
	public void saveWfTPortletinfo(WfTPortletinfo wfTPortletinfo) throws Exception {

		wfTPortletinfoDao.save(wfTPortletinfo);
	}

	/**
	 * 根据主键对象，删除WfTPortletinfo信息
	 * 
	 * @param WfTPortletinfoId
	 */
	public void deleteByPK(WfTPortletinfoId id) throws Exception {

		wfTPortletinfoDao.deleteByPK(WfTPortletinfo.class, id);
	}

	/**
	 * 查询页面portlet信息
	 * 
	 * @author zhou
	 * @throws Exception
	 */
	public List<WfTPortletinfo> findWfTPortletinfoByUserCode(String userCode,String comId,String comCode,String serverCode) throws Exception {
		List<String> portleIdList = umTTaskService.getPorletidByUserCode(userCode,comId,serverCode);
		List<WfTPortletinfo> wfTPortletInfoList = new ArrayList<WfTPortletinfo>();
		List<WfTPortletinfo> result = new ArrayList<WfTPortletinfo>();
		QueryRule queryRule = QueryRule.getInstance();

		queryRule.addEqual("usercode", userCode);
		queryRule.addEqual("comid", comId);
		//queryRule.addEqual("comcode", comCode);
		queryRule.addEqual("isclosed", BigDecimal.valueOf(0));
		queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
		// order by porletcol asc;
		queryRule.addAscOrder("porletcol");
		queryRule.addAscOrder("porletrow");
		wfTPortletInfoList = wfTPortletinfoDao.find(queryRule);

		if (portleIdList != null && wfTPortletInfoList != null) {
			for (int i = 0; i < wfTPortletInfoList.size(); i++) {
				WfTPortletinfo wfTPortletinfo = wfTPortletInfoList.get(i);
				for (int j = 0; j < portleIdList.size(); j++) {
					String portletId = portleIdList.get(j);
					if (portletId.equals(wfTPortletinfo.getPortletid())) {
						result.add(wfTPortletinfo);
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 更新porletinfo的isclose全为1
	 * 
	 * @author zhou
	 */
	public void updatePorletIsClose(String userCode,String comId,String comCode) {
		// String sqlstr="update wf_t_portletinfo set isclosed=1";
		List<WfTPortletinfo> wfTPortletinfoList = new ArrayList<WfTPortletinfo>();
		WfTPortletinfo wfTPortletinfo = new WfTPortletinfo();
		// super.getSession().createSQLQuery(sqlstr).executeUpdate();

		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("usercode", userCode);
		queryRule.addEqual("comid", comId);
		//queryRule.addEqual("comcode", comCode);
		queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
		wfTPortletinfoList = wfTPortletinfoDao.find(queryRule);
		if(wfTPortletinfoList!=null && !wfTPortletinfoList.isEmpty()){
			for (int i = 0; i < wfTPortletinfoList.size(); i++) {
				wfTPortletinfo = wfTPortletinfoList.get(i);
				wfTPortletinfo.setIsclosed(BigDecimal.valueOf(1));
			}
			wfTPortletinfoDao.update(wfTPortletinfo);
		}
	}

	// /**
	// * 保存portlet位置
	// * @author zhou
	// */
	// public void savePortletPosition(String a,String userCode,String
	// porletcol){
	// String[] array=convertStrToArray(a);
	// for(int i=0;i<array.length;i++){
	// String
	// sqlstr="update wf_t_portletinfo set porletrow='"+i+"',porletcol='"+
	// porletcol+"',isclosed=0 where portletid='"+array[i]+"' and usercode='"+userCode+"'";
	// super.getSession().createSQLQuery(sqlstr).executeUpdate();
	// }
	// }

	/**
	 * 把以逗号为分隔符的字符串转换成字符串数组
	 * 
	 * @author zhou
	 */
	public static String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}

	public void savePortletPosition(String left, String middle, String right, String userCode, String comId, String comCode) {
		updatePorletIsClose(userCode,comId,comCode);
		if(left!=null && ToolsUtils.notEmpty(left)){
			portletPositionClassfy(left, "appL", userCode, comId, comCode);
		}
		if(middle!=null && ToolsUtils.notEmpty(middle)){
			portletPositionClassfy(middle, "appM", userCode, comId, comCode);
		}
		if(right!=null && ToolsUtils.notEmpty(right)){
			portletPositionClassfy(right, "appR", userCode, comId, comCode);
		}
	}

	/**
	 * 更新或保存用户添加模块到数据库
	 * 
	 * @author zhou&ding 
	 * date:2013-9-9
	 */
	public void portletPositionClassfy(String a, String protletCol, String userCode, String comId, String comCode) {

		// QueryRule queryRule=QueryRule.getInstance();
		// String[] array = convertStrToArray(a);
		// for (int i = 0; i < array.length; i++) {
		// QueryRule queryRule = QueryRule.getInstance();
		// wfTPortletinfo = new WfTPortletinfo();
		// queryRule.addEqual("portletid", array[i]);
		// queryRule.addEqual("usercode", userCode);
		// wfTPortletinfo = wfTPortletinfoDao.findUnique(queryRule);
		// if (wfTPortletinfo != null) {
		// wfTPortletinfo.setPorletcol(protletCol);
		// wfTPortletinfo.setPorletrow(Integer.toString(i));
		// wfTPortletinfo.setIsclosed(BigDecimal.valueOf(0));
		// wfTPortletinfoDao.update(wfTPortletinfo);
		// }
		// }
		List<WfTPortletinfo> wfTPortletInfoList = new ArrayList<WfTPortletinfo>();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("usercode", userCode);
		queryRule.addEqual("comid", comId);
		//queryRule.addEqual("comcode", comCode);
		queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
		wfTPortletInfoList = wfTPortletinfoDao.find(queryRule);
		String[] array = convertStrToArray(a);
		for (int i = 0; i < array.length; i++) {
			Boolean flag = false;
			WfTPortletinfo wfTPortletinfo = new WfTPortletinfo();
			QueryRule queryRuleAll = QueryRule.getInstance();
			queryRuleAll.addEqual("validstatus", BigDecimal.valueOf(1));
			queryRuleAll.addEqual("portletid", array[i]);
			queryRuleAll.addEqual("usercode", userCode);
			queryRuleAll.addEqual("comid", comId);
			//queryRuleAll.addEqual("comcode", comCode);
			wfTPortletinfo = wfTPortletinfoDao.findUnique(queryRuleAll);
			if (wfTPortletInfoList != null) {
				for (int j = 0; j < wfTPortletInfoList.size(); j++) {
					String id = wfTPortletInfoList.get(j).getPortletid();
					if (array[i].equals(id)) {
						flag = true;
						wfTPortletinfo.setPorletcol(protletCol);
						wfTPortletinfo.setPorletrow(Integer.toString(i));
						wfTPortletinfo.setIsclosed(BigDecimal.valueOf(0));
						wfTPortletinfoDao.update(wfTPortletinfo);
						break;
					}
				}
				if (flag == false) {
					WfTPortletinfo wfTPortletinfoone = new WfTPortletinfo();
					WfTPortletinfoId Portletinfoid = new WfTPortletinfoId();
					String WfTPortletinfoId = wfTPortletinfoDao.getCommonDao().generateID("PORI", "WF_SEQ_Portletinfo", 16);// 自动生成主键
					Portletinfoid.setId(WfTPortletinfoId);
					wfTPortletinfoone.setId(Portletinfoid);
					wfTPortletinfoone.setUsercode(userCode);
					wfTPortletinfoone.setPorletrow(Integer.toString(i));
					wfTPortletinfoone.setPorletcol(protletCol);
					wfTPortletinfoone.setPortletid(array[i]);
					wfTPortletinfoone.setIsclosed(BigDecimal.valueOf(0));
					wfTPortletinfoone.setComid(comId);
					wfTPortletinfoone.setComcode(comCode);
					wfTPortletinfoone.setValidstatus(BigDecimal.valueOf(1));
					wfTPortletinfoDao.save(wfTPortletinfoone);
				}
			}
		}

	}

	// /**
	// * 更新或保存用户添加模块到数据库
	// * @author ding
	// * date:2013-8-22
	// */
	// public void addWfTPortletinfo(String userCode,String portletId,String
	// comId){
	// List<WfTPortletinfo> wfTPortletInfoList = new
	// ArrayList<WfTPortletinfo>();
	// QueryRule queryRule=QueryRule.getInstance();
	// queryRule.addEqual("usercode", userCode);
	// queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
	// wfTPortletInfoList = wfTPortletinfoDao.find(queryRule);
	// Boolean flag = false;
	//
	// WfTPortletinfo wfTPortletinfo = new WfTPortletinfo();
	// QueryRule queryRuleAll=QueryRule.getInstance();
	// queryRuleAll.addEqual("validstatus", BigDecimal.valueOf(1));
	// queryRuleAll.addEqual("portletid", portletId);
	// queryRuleAll.addEqual("usercode", userCode);
	// wfTPortletinfo = wfTPortletinfoDao.findUnique(queryRuleAll);
	//
	// StringBuffer sqlbuf = new
	// StringBuffer("select max(porletrow) from WF_T_PORTLETINFO");
	// List Portletinfo = super.findBySql(sqlbuf.toString());
	// int row = Integer.valueOf(Portletinfo.get(0).toString());
	//
	// for(int i=0;i<wfTPortletInfoList.size();i++){
	// String id = wfTPortletInfoList.get(i).getPortletid();
	// if(portletId.equals(id)){
	// flag = true;
	// wfTPortletinfo.setPorletcol("appL");
	// wfTPortletinfo.setPorletrow(String.valueOf(row+1));
	// wfTPortletinfo.setIsclosed(BigDecimal.valueOf(0));
	// wfTPortletinfoDao.update(wfTPortletinfo);
	// break;
	// }
	// }
	// if(flag==false){
	// WfTPortletinfo wfTPortletinfoone = new WfTPortletinfo();
	// WfTPortletinfoId Portletinfoid = new WfTPortletinfoId();
	// String WfTPortletinfoId = wfTPortletinfoDao.getCommonDao().generateID(
	// "PORI", "WF_SEQ_Portletinfo", 16);// 自动生成主键
	// Portletinfoid.setId(WfTPortletinfoId);
	// wfTPortletinfoone.setId(Portletinfoid);
	// wfTPortletinfoone.setUsercode(userCode);
	// wfTPortletinfoone.setPorletrow(String.valueOf(row+1));
	// wfTPortletinfoone.setPorletcol("appL");
	// wfTPortletinfoone.setPortletid(portletId);
	// wfTPortletinfoone.setIsclosed(BigDecimal.valueOf(0));
	// wfTPortletinfoone.setComid(comId);
	// wfTPortletinfoone.setValidstatus(BigDecimal.valueOf(1));
	// wfTPortletinfoDao.save(wfTPortletinfoone);
	// }
	// }

}
