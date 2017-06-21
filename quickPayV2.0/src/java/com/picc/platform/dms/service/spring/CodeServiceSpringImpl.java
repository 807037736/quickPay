package com.picc.platform.dms.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.platform.dms.schema.vo.Code;
import com.picc.platform.dms.service.facade.CodeService;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.model.PrpDcompany;
import com.sinosoft.dmsdriver.service.common.DictPage;
import com.sinosoft.dmsdriver.service.server.PageService;

@Service(value = "codeService")
public class CodeServiceSpringImpl extends GenericDaoHibernate implements
		CodeService {
	/**
	 * 初始缓存实例
	 */
	@Autowired
	private CommonDaoHibernate commonDao;
	private static CacheService cacheManager = CacheManager.getInstance("codeSelectCache");
	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @param order
	 *            排序(asc:升序/desc:降序)
	 * @param matches
	 *            匹配字符串
	 * @return 代码List
	 */
	public Page listCodeSelect(String codeType, String riskCode,
			String language, String matches, int pageNo, int pageSize,
			String userCode, String typeParam, String extraCond) {
		String key = cacheManager.generateCacheKey("listCodeSelect", codeType,
				riskCode, language, matches, pageNo, pageSize, typeParam,
				extraCond);
		Object result = cacheManager.getCache(key);
		if ((Page) result != null) {
			return (Page) result;
		}
		Page page = null;
		String hql = generateCodeSelectHql(codeType, riskCode, language, "asc",
				matches, userCode, typeParam, extraCond);
		page = this.findByHql(hql, pageNo, pageSize, matches, matches);
		cacheManager.putCache(key, page);
		return page;
	}

	/**
	 * 翻译代码
	 * 
	 * @param codeType
	 *            代码类型
	 * @param codeCode
	 *            代码
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	@SuppressWarnings("rawtypes")
	public String translateCode(String codeType, String codeCode,
			String riskCode, String language) {
		if (codeCode == null) {
			return "";
		}
		String key = cacheManager.generateCacheKey("translateCode", codeType,
				codeCode, riskCode, language);
		Object result = cacheManager.getCache(key);
		if (result != null) {
			return (String) result;
		}
		String codeName = null;
		StringBuilder buffer = new StringBuilder();
		if (codeCode.indexOf(",") > -1) {
			String[] codes = StringUtils.split(codeCode, ",");
			for (String code : codes) {
				String hql = generateTranslateHql(codeType, code.trim(),
						riskCode, language);
				List nameList = this.findByHql(hql, code.trim());
				if (nameList.size() > 0) {
					codeName = nameList.get(0) + "";
					codeName = codeName.trim();
				}
				buffer.append(codeName);
				if (!code.equals(codes[codes.length - 1])) {
					buffer.append(",");
				}
			}
			codeName = buffer.toString();
		} else {
			String hql = generateTranslateHql(codeType, codeCode, riskCode,
					language);
			List nameList = this.findByHql(hql, codeCode);
			if (nameList.size() > 0) {
				codeName = nameList.get(0) + "";
				codeName = codeName.trim();
			}
		}
		if (codeName == null) {
			codeName = codeCode;
		}
		cacheManager.putCache(key, codeName);
		return codeName;
	}

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码Map
	 */
	public Map<String, String> listCodes(String codeType, String riskCode,
			String language) {
		String key = cacheManager.generateCacheKey("listCodes", codeType,
				riskCode, language);
		Object result = cacheManager.getCache(key);
		if (result != null) {
			return (Map<String, String>) result;
		}
		TreeMap<String, String> map = new TreeMap<String, String>();
		String hql = generateListHql(codeType, riskCode, language, "asc");
		List list = this.findByHql(hql);
		for (int i = 0; i < list.size(); i++) {
			Object[] arrValue = (Object[]) list.get(i);
			String code = (arrValue[0] + "").trim();
			String name = (arrValue[1] + "").trim();
			map.put(code, name);
		}
		cacheManager.putCache(key, map);
		return map;
	}

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @param otherCondition
	 *            其它条件
	 * @return 代码Map
	 */
	public Map<String, String> listCodes(String codeType, String riskCode,
			String language, String otherCondition) {
		String key = cacheManager.generateCacheKey("listCodes", codeType,
				riskCode, language, otherCondition);
		Object result = cacheManager.getCache(key);
		if (result != null) {
			return (Map<String, String>) result;
		}
		TreeMap<String, String> map = new TreeMap<String, String>();
		String hql = generateListHql(codeType, riskCode, language, "");
		if (otherCondition != null && !otherCondition.trim().equals("")) {
			hql = hql + " and " + otherCondition;
		}
		List list = this.findByHql(hql);
		for (int i = 0; i < list.size(); i++) {
			Object[] arrValue = (Object[]) list.get(i);
			String code = (arrValue[0] + "").trim();
			String name = (arrValue[1] + "").trim();
			map.put(code, name);
		}
		cacheManager.putCache(key, map);
		return map;
	}

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码List
	 */
	public List<Code> listCodeList(String codeType, String riskCode,
			String language) {
		List<Code> codes = this.listOrderCodeList(codeType, riskCode, language,
				"");
		return codes;
	}

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @param order
	 *            排序(asc:升序/desc:降序)
	 * @return 代码List
	 */
	public List<Code> listOrderCodeList(String codeType, String riskCode,
			String language, String order) {
		String key = cacheManager.generateCacheKey("listOrderCodeList",
				codeType, riskCode, language, order);
		Object result = cacheManager.getCache(key);
		if (result != null) {
			return (List<Code>) result;
		}
		List<Code> codes = new ArrayList<Code>();
		String hql = generateListHql(codeType, riskCode, language, order);
		List list = this.findByHql(hql);
		for (int i = 0; i < list.size(); i++) {
			Object[] arrValue = (Object[]) list.get(i);
			String code = (arrValue[0] + "").trim();
			String name = (arrValue[1] + "").trim();
			codes.add(new Code(code, name));
		}
		cacheManager.putCache(key, codes);
		return codes;
	}

	/**
	 * 生成HQL
	 * 
	 * @param codeType
	 *            代码类型
	 * @param codeCode
	 *            代码
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return HQL语句
	 */
	private String generateTranslateHql(String codeType, String codeCode,
			String riskCode, String language) {
		String hql = null;
		if (codeType.equals(USERCODE)) {
			hql = "select a.userName from UmTUser a where a.userCode = ?";
		} else if (codeType.equals(COMCODE)) {
			if (language.equals("E")) {
				hql = "select a.comename from PrpDcompany a where a.comCode = ?";
			} else {
				hql = "select a.comCName from PrpDcompany a where a.comCode = ?";
			}
		} else {
			if (language.equals("E")) {
				hql = "select a.codeEName from MCDNewCode a,MCDType b where a.id.codeType=b.newCodeType and a.id.codeType='"
						+ codeType
						+ "' and a.id.codeCode = ?";
			} else {
				hql = "select a.codeCName from MCDNewCode a,MCDType b where a.id.codeType=b.newCodeType and a.id.codeType='"
						+ codeType
						+ "' and a.id.codeCode = ?";
			}
		}
		return hql;
	}

	/**
	 * 生成HQL
	 * 
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return HQL语句
	 */
	private String generateListHql(String codeType, String riskCode, String language, String order) {
		String hql = null;
		if (riskCode != null && !"".equals(riskCode.trim())) {
			riskCode = riskCode.replaceAll(",", "','");
		}
		if (codeType.equals(USERCODE)) {
			hql = "select a.id.userCode,a.userName from UmTUser a where a.validStatus='1'";
		} else if (codeType.equals(COMCODE)) {
			if (language.equals("E")) {
				hql = "select a.comCode,a.comEName from PrpDcompany a where a.validStatus='1'";
			} else {
				hql = "select a.comCode,a.comCName from PrpDcompany a where a.validStatus='1'";
			}
		}else if(codeType.equals(OPERATIONSYMBOL)){
			if(language.equals("E")){
				hql = "select a.codeCode,a.codeEName from UM_T_CODE a where a.validStatus='1'";
			}else{
				hql = "select a.codeCode,a.codeCName from UM_T_CODE a where a.validStatus='1'";
			}
		}  else {
			if (language.equals("E")) {
				hql = "select a.id.codeCode,a.codeEName from MCDNewCode a,MCDType b where a.id.codeType=b.newCodeType and a.id.codeType='"
						+ codeType + "' and a.validStatus='1'";
			} else {
				hql = "select a.id.codeCode,a.codeCName from MCDNewCode a,MCDType b where a.id.codeType=b.newCodeType and a.id.codeType='"
						+ codeType + "' and a.validStatus='1'";
			}
		}
		if (order != null && (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))) {
			hql = hql + " order by 1 " + order;
		}
		return hql;
	}

	/**
	 * @param codeType
	 * @param riskCode
	 * @param language
	 * @param order
	 * @param matches
	 * @param typeParam
	 * @return
	 */
	private String generateCodeSelectHql(String codeType, String riskCode,
			String language, String order, String matches, String userCode,
			String typeParam, String extraCond) {
		if (riskCode != null && !"".equals(riskCode.trim())) {
			riskCode = riskCode.replaceAll(",", "','");
		}
		String hql = null;
		if (codeType.equals(USERCODE)) {
			hql = "select a.id.userCode,a.userName from UmTUser a where a.validStatus='1' and (a.userCode like ? or a.userName like ?)";
			// hql= hql +" and "+powerService.addPower(userCode,
			// "UserCode_Query", "a.userCode", "", "");
		} else if (codeType.equals(COMCODE)) {
			if (language.equals("E")) {
				hql = "select a.comCode,a.comeName,a.upperComCode,a.faxNumber,'' from PrpDcompany a where a.validStatus='1' and (a.comCode like ? or a.comeName like ?) ";
			} else {
				hql = "select a.comCode,a.comCName,a.upperComCode,a.faxNumber,'' from PrpDcompany a where a.validStatus='1' and (a.comCode like ? or a.comCName like ?) ";
			}
			hql = hql + " order by a.upperComCode,a.comCode asc";
		} else if ("SelectAllRiskCodeTree".equals(codeType)) {
			hql = "select risk.riskCode,risk.riskCName from SaaRisk risk where risk.validStatus = '1' and (risk.riskCode like ? or risk.riskCName like ?)";
		} else {
			if (language.equals("E")) {
				hql = "select distinct(a.id.codeCode),a.codeEName from PrpDcode a,PrpDcodeRisk b where a.id.codeType=b.id.codeType and a.id.codeCode=b.id.codeCode and a.id.codeType='"
						+ codeType
						+ "' and (b.id.riskCode in('PUB', '"
						+ riskCode
						+ "') or b.id.riskCode in( select risk.prpDclass.classCode from PrpDrisk risk where risk.riskCode in('"
						+ riskCode
						+ "'))) and a.validStatus='1' and (a.id.codeCode like ? or a.codeEName like ?)";
			} else {
				hql = "select distinct(a.id.codeCode),a.codeCName from PrpDcode a,PrpDcodeRisk b where a.id.codeType=b.id.codeType and a.id.codeCode=b.id.codeCode and a.codeType='"
						+ codeType
						+ "' and (b.id.riskCode in('PUB', '"
						+ riskCode
						+ "') or b.id.riskCode in( select risk.prpDclass.classCode from PrpDrisk risk where risk.riskCode in('"
						+ riskCode
						+ "')))  and a.validStatus='1' and (a.id.codeCode like ? or a.codeCName like ?)";
			}
		}
		if (!(typeParam == null || "null".equals(typeParam) || ""
				.equals(typeParam.trim()))) {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.typeParam", typeParam);
			queryRule.addEqual("id.codeType", codeType);
			queryRule.addEqual("validstate", VALIDFLAG_Y);
		}
		if (order != null
				&& (order.equalsIgnoreCase("asc") || order
						.equalsIgnoreCase("desc"))) {
			hql = hql + " order by 1 " + order;
			
		}
		return hql;
	}

	public String translateCode(String codeType, String codeEName,
			String riskCode) {
		String codeCName = "";
		String hql = "select a.codeCName from MCDNewCode a,MCDType b where a.id.codeType=b.newCodeType and a.id.codeType='"
				+ codeType
				+ "' and a.validStatus='1' and a.codeEName like ?";
		
		List nameList = this.findByHql(hql, codeEName);
		if (nameList.size() > 0) {
			codeCName = nameList.get(0) + "";
			codeCName = codeCName.trim();
		}
		return codeCName;
	}

	public List listNodes(String query, String currentNodeName) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"select distinct (t.name_),t.description_ from jbpm_taskinstance t where t.name_ in(")
				.append(" select n.name_ from jbpm_node n where n.class_ = 'K'")
				.append(" and n.id_ < (")
				.append("select n1.id_ from jbpm_node n1 where n1.name_ = '")
				.append(currentNodeName).append("')").append(")")
				.append("and (t.name_ like '").append(query)
				.append("' or t.description_ like '").append(query)
				.append("')").append("and t.name_ <> '")
				.append(currentNodeName).append("'");
		List nodeList = super.getSession().createSQLQuery(builder.toString())
				.list();
		return nodeList;
	}

	@SuppressWarnings("rawtypes")
	public String translateUserCode(String codeType, String codeEName,
			String riskCode,String language) {
	
		String codeCName = "";
		String sql = "select USERNAME from um_t_user where userCode= ?";
		List nameList = this.commonDao.findBySql(sql, codeEName);
		if (nameList.size() > 0) {
			codeCName = nameList.get(0) + "";
			codeCName = codeCName.trim();
		}
		return codeCName;
	}

	public String translateComCode(String codeType, String codeEName,
			String riskCode,String language) throws Exception {
		
		String codeCName = "";
		PrpDcompany prpcompany = null;
		if(null==codeEName||"".equals(codeEName))
		{
			codeEName = "";
		}
		else
		{
			//存放缓存
			String key = cacheManager.generateCacheKey("translateCode", codeType,
					codeEName, riskCode, language);
			Object result = cacheManager.getCache(key);
			if (result != null) {
				return (String) result;
			}else{
				//prpcompany  = (PrpDcompany)PageService.getCompanys(SystemCode.DMS, codeEName, "", "", "1", "", 1, Integer.MAX_VALUE).getData().get(0);
				DictPage page=PageService.getCompanys(SystemCode.DMS, codeEName, "", "", "1", "", 1, Integer.MAX_VALUE);
				if(page!=null && page.getData()!=null && page.getData().size()>0){
					//存放缓存
					prpcompany =(PrpDcompany)page.getData().get(0);
					if (null!=prpcompany) {
						codeCName = prpcompany.getComCName().trim();
						cacheManager.putCache(key, codeCName);
					}
				}
			}
		}
		return codeCName;
	}
	

}
