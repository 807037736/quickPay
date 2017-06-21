/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.portal.dao.WfTPortletclassfyDaoHibernate;
import com.picc.portal.dao.WfTPortletinfoDaoHibernate;
import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.portal.schema.model.WfTPortletclassfyId;
import com.picc.portal.schema.model.WfTPortletinfo;
import com.picc.portal.service.facade.IWfTPortletclassfyService;
import com.picc.portal.service.facade.IWfTPortletinfoService;
import com.picc.um.service.facade.IUmTTaskService;
import com.picc.um.service.facade.IUmTUserPowerService;

@Service("wfTPortletclassfyService")
public class WfTPortletclassfyServiceSpringImpl extends GenericDaoHibernate<WfTPortletclassfy, Integer> implements IWfTPortletclassfyService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IWfTPortletinfoService wfTPortletinfoService;

	@Autowired
	private WfTPortletclassfyDaoHibernate wfTPortletclassfyDao;

	@Autowired
	private WfTPortletinfoDaoHibernate wfTPortletinfoDao;

	@Autowired
	private IUmTTaskService umTTaskService;
	
	@Autowired
	private IUmTUserPowerService umTUserPowerService;

	/**
	 * 根据主键对象WfTPortletclassfyId获取WfTPortletclassfy信息
	 * 
	 * @param WfTPortletclassfyId
	 * @return WfTPortletclassfy
	 */
	public WfTPortletclassfy findWfTPortletclassfyByPK(WfTPortletclassfyId id) throws Exception {

		return wfTPortletclassfyDao.get(WfTPortletclassfy.class, id);
	}

	/**
	 * 根据wfTPortletclassfy和页码信息，获取Page对象
	 * 
	 * @param wfTPortletclassfy
	 * @param pageNo
	 * @param pageSize
	 * @return 包含WfTPortletclassfy的Page对象
	 */
	public Page findByWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy, int pageNo, int pageSize) throws Exception {

		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		// 根据wfTPortletclassfy生成queryRule

		return wfTPortletclassfyDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新WfTPortletclassfy信息 
	 * @author ding 
	 * date 2013-8-27
	 * @param WfTPortletclassfy
	 */
	public void updateWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy) throws Exception {
		wfTPortletclassfyDao.update(wfTPortletclassfy);
		BigDecimal validstatus = wfTPortletclassfy.getValidstatus();
		String portletId = wfTPortletclassfy.getId().getPortletid().toString();		
		String sql = "update wf_t_Portletinfo set validstatus=? where portletid=?";		
		wfTPortletclassfyDao.getCommonDao().execute(sql, new Object[] { validstatus,portletId });
	}

	/**
	 * 保存WfTPortletclassfy信息
	 * @author ding 
	 * date 2013-8-27
	 * @param WfTPortletclassfy
	 */
	public void saveWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy, String comId, String comCode) throws Exception {

		WfTPortletclassfyId Portletclassfyid = new WfTPortletclassfyId();
		String WfTPortletclassfyId = wfTPortletclassfyDao.getCommonDao().generateID("PORC", "WF_SEQ_Portletclassfy", 16);// 自动生成主键
		Portletclassfyid.setPortletid(WfTPortletclassfyId);
		wfTPortletclassfy.setId(Portletclassfyid);
		wfTPortletclassfy.setComid(comId);
		wfTPortletclassfy.setComcode(comCode);
		wfTPortletclassfyDao.save(wfTPortletclassfy);

	}

	/**
	 * 根据主键对象，删除WfTPortletclassfy信息
	 * 
	 * @param WfTPortletclassfyId
	 */
	public void deleteByPK(WfTPortletclassfyId id) throws Exception {

		wfTPortletclassfyDao.deleteByPK(WfTPortletclassfy.class, id);
	}

	/**
	 * 返回页面展示的portlet信息，包括title，url
	 * 
	 * @author zhou
	 * @throws Exception
	 */
	public JSONObject getPortletInfo(String userCode,String comId,String comCode,String serverCode) throws Exception {
		List<WfTPortletinfo> wfTPortletinfoList = new ArrayList<WfTPortletinfo>();
		wfTPortletinfoList = wfTPortletinfoService.findWfTPortletinfoByUserCode(userCode,comId,comCode,serverCode);
		Iterator<WfTPortletinfo> it = wfTPortletinfoList.iterator();
		WfTPortletinfo wfTPortletinfo = new WfTPortletinfo();
		WfTPortletclassfy wfTPortletclassfy = new WfTPortletclassfy();
		WfTPortletclassfyId id = new WfTPortletclassfyId();
		JSONObject parentJson = new JSONObject();
		JSONObject childrenJson = null;
		String temp = "";
		String portletcol = "";
		while (it.hasNext()) {
			JSONObject subJson = new JSONObject();
			wfTPortletinfo = (WfTPortletinfo) it.next();
			portletcol = wfTPortletinfo.getPorletcol();
			id.setPortletid(wfTPortletinfo.getPortletid());
			if (temp.isEmpty() || !temp.equals(portletcol)) {
				childrenJson = new JSONObject();
				temp = portletcol;
			}
			wfTPortletclassfy = super.get(WfTPortletclassfy.class, id);
			try {
				subJson.put("title", wfTPortletclassfy.getPortletname());
				subJson.put("url", wfTPortletclassfy.getActionurl());
				childrenJson.put(wfTPortletinfo.getPortletid(), subJson);
				parentJson.put(portletcol, childrenJson);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("", e);
			}
		}
		try {
			if (!parentJson.has("appL"))
				parentJson.put("appL", "");
			if (!parentJson.has("appM"))
				parentJson.put("appM", "");
			if (!parentJson.has("appR"))
				parentJson.put("appR", "");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return parentJson;
	}

	/**
	 * 保存添加模块的portlet信息，包括title，url
	 * @author ding
	 * @param portletName
	 * @param url
	 * date:2013-8-9
	 */
	public void savePortletclassfy(String portletName, String url) {

		WfTPortletclassfy wfTPortletclassfy = new WfTPortletclassfy();
		WfTPortletclassfyId Portletclassfyid = new WfTPortletclassfyId();
		// StringBuffer sqlbuf = new
		// StringBuffer("select max(portletid) from WF_T_PORTLETCLASSFY");//
		// 取最大的ID自动加一
		// List PortletclassfyMax = super.findBySql(sqlbuf.toString());
		// int Portletclassfycount =
		// Integer.valueOf(PortletclassfyMax.get(0).toString());
		// Portletclassfyid.setPortletid(String.valueOf(Portletclassfycount+1));

		String WfTPortletclassfyId = wfTPortletclassfyDao.getCommonDao().generateID("PORC", "WF_SEQ_Portletclassfy", 16);// 自动生成主键
		Portletclassfyid.setPortletid(WfTPortletclassfyId);
		wfTPortletclassfy.setId(Portletclassfyid);
		wfTPortletclassfy.setActionurl(url);
		wfTPortletclassfy.setPortletname(portletName);
		wfTPortletclassfy.setPorletheight("0");
		wfTPortletclassfy.setPorletwidth("0");
		wfTPortletclassfyDao.save(wfTPortletclassfy);// 新增wfTPortletclassfy一行记录

		// WfTPortletinfo wfTPortletinfo = new WfTPortletinfo();
		// WfTPortletinfoId Portletinfoid = new WfTPortletinfoId();
		// // sqlbuf.setLength(0);
		// // sqlbuf.append("select max(id) from WF_T_PORTLETINFO");
		// // List PortletinfoMax = super.findBySql(sqlbuf.toString());
		// // int Portletinfocount =
		// Integer.valueOf(PortletinfoMax.get(0).toString());
		// // Portletinfoid.setId(String.valueOf(Portletinfocount+1));
		// String WfTPortletinfoId =
		// wfTPortletinfoDao.getCommonDao().generateID(
		// "PORI", "WF_SEQ_Portletinfo", 5);
		// Portletinfoid.setId(WfTPortletinfoId);
		// wfTPortletinfo.setId(Portletinfoid);
		// wfTPortletinfo.setPortletid(WfTPortletclassfyId);//
		// 往wfTPortletinfo插入wfTPortletclassfy的主键
		//
		// String porletCol = new String();
		// if ("left".equals(layout)){
		// porletCol = "appL";
		// }else if("center".equals(layout)){
		// porletCol = "appM";
		// }else if("right".equals(layout)){
		// porletCol = "appR";
		// }
		// wfTPortletinfo.setPorletcol(porletCol);// 新增位置信息
		// wfTPortletinfoDao.save(wfTPortletinfo);// 新增wfTPortletinfo一行记录
	}

	/**
	 * 获取WfTPortletclassfy的list 
	 * @author ding 
	 * date:2013-8-12
	 */
	public List<WfTPortletclassfy> findPortletClassfyAll() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
		List<WfTPortletclassfy> wftPortletclassfyList = new ArrayList<WfTPortletclassfy>();
		wftPortletclassfyList = wfTPortletclassfyDao.find(queryRule);
		return wftPortletclassfyList;
	}

	/**
	 * 根据用户权限获取添加模块显示的模块Id
	 * @author ding 
	 * date:2013-8-20
	 */
	public List<String> findWfTPortletinfoByUserCode(String userCode,String comId,String comCode,String serverCode) throws Exception {
		List<String> portleIdList = umTTaskService.getPorletidByUserCode(userCode,comId,serverCode);
		List<WfTPortletinfo> wfTPortletInfoList = new ArrayList<WfTPortletinfo>();
		// List<W7fTPortletinfo> resultList=new ArrayList<WfTPortletinfo>();
		List<String> resultList = new ArrayList<String>();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("usercode", userCode);
		queryRule.addEqual("comid", comId);
		//queryRule.addEqual("comcode", comCode);
		queryRule.addEqual("isclosed", BigDecimal.valueOf(1));
		queryRule.addEqual("validstatus", BigDecimal.valueOf(1));
		wfTPortletInfoList = wfTPortletinfoDao.find(queryRule);

		List<WfTPortletinfo> wfTPortletInfoListAll = new ArrayList<WfTPortletinfo>();
		QueryRule queryRuleAll = QueryRule.getInstance();
		queryRuleAll.addEqual("usercode", userCode);
		queryRuleAll.addEqual("comid", comId);
		//queryRuleAll.addEqual("comcode", comCode);
		queryRuleAll.addEqual("validstatus", BigDecimal.valueOf(1));
		wfTPortletInfoListAll = wfTPortletinfoDao.find(queryRuleAll);
		Boolean flag = true;

		if (portleIdList != null) {
			if (wfTPortletInfoList != null && !wfTPortletInfoList.isEmpty()) {
				for (int i = 0; i < wfTPortletInfoList.size(); i++) {
					WfTPortletinfo wfTPortletinfo = wfTPortletInfoList.get(i);
					for (int j = 0; j < portleIdList.size(); j++) {
						String portletId = portleIdList.get(j);
						if (portletId.equals(wfTPortletinfo.getPortletid())) {
							resultList.add(wfTPortletinfo.getPortletid());
							break;
						}
					}
				}
			}
			// if(wfTPortletInfoListAll!=null){
			// for(int i=0;i<wfTPortletInfoListAll.size();i++){
			// WfTPortletinfo wfTPortletinfoAll=wfTPortletInfoListAll.get(i);
			// if(!portleIdList.contains(wfTPortletInfoListAll.get(i).getPortletid())){
			// resultList.add(wfTPortletinfoAll);
			// }
			//
			// }
			// }
			if (wfTPortletInfoListAll != null && !wfTPortletInfoListAll.isEmpty()) {
				for (int i = 0; i < portleIdList.size(); i++) {
					String portletId = portleIdList.get(i);
					for (int j = 0; j < wfTPortletInfoListAll.size(); j++) {
						flag = false;
						WfTPortletinfo wfTPortletinfoAll = wfTPortletInfoListAll.get(j);
						if (wfTPortletinfoAll.getPortletid().equals(portletId)) {
							flag = true;
							break;
						}
					}
					if (flag == false) {
						resultList.add(portletId);
					}
				}

			}
			if (wfTPortletInfoListAll.isEmpty()) {
				for (int i = 0; i < portleIdList.size(); i++) {
					String portletId = portleIdList.get(i);
					resultList.add(portletId);
				}				
			}
		}
		return resultList;
	}

	public List<WfTPortletinfo> findWfTPortletinfoByUserCode() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Page findWfTPortletclassfy(QueryRule queryRule, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return wfTPortletclassfyDao.find(queryRule, pageNo, pageSize);
	}
	
	public String findPortalTop(String userCode, String comCode) throws Exception{
		//车险报价器
		String quoteURL="/khyx/price/prepareQuote.do";
		//车型信息
		String vehicleURL="/khyx/pages/qt/jyVehicle/JyVehicleQuery.jsp";
		//我的消息
		String messageURL="/khyx/wf/wftusermessage/getMessage.do";
		//预约任务
		String reservationURL="/khyx/tool/reservation/prepareReservation.do";
		//客户视图
		String myCustURL="/khyx/custquery/prepareQuery.do";
		//我的佣金
		String commissionURL="/khyx/mp/commission/prepareQueryMyCommission.do";
		//报价单
		String quoteDetailURL="/khyx/pages/qt/quotesearch/QTQuoteSearch.jsp";
		
		
		StringBuffer topMenuBuffer=new StringBuffer();
		
		String tempString="";
		
		JSONObject jsonMenu=new JSONObject();
		jsonMenu.put("车险报价器", quoteURL);
		jsonMenu.put("车型信息", vehicleURL);
		jsonMenu.put("我的消息", messageURL);
		jsonMenu.put("报价单", quoteDetailURL);
		jsonMenu.put("我的佣金", commissionURL);
		jsonMenu.put("客户视图", myCustURL);
		jsonMenu.put("预约任务", reservationURL);
		
		Iterator<String> keys=jsonMenu.keys();
		String url="";
		String key="";
		int i=1;
		while(keys.hasNext()){
			
			key=keys.next();
			url=jsonMenu.getString(key);
			if(umTUserPowerService.checkUserPowerByRequestUrl(userCode, comCode, url)){
				tempString="<li class='nav1_line'><a href='"+url+"'class='a1'> <i class='spr nav"+i+"'></i> "+key+"</a> </li>";
				topMenuBuffer.append(tempString);
			}
			i++;
		}
		topMenuBuffer.append("<li class='system spr2' id='editbt' style='left: 781px; position: absolute;'><a href='##' class='a2' ></a></li>");
		return topMenuBuffer.toString();
	}
}
