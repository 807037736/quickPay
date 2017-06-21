package com.picc.common.web;

import ins.framework.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.platform.dms.schema.vo.Code;
import com.picc.platform.dms.schema.vo.CodeCondition;
import com.picc.platform.dms.service.facade.CodeService;
import com.picc.um.schema.vo.SessionUser;

 
public class DwrInvokeDataAction {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final int MAX_RECORDS = 30;// 代码查询的最大记录数
	private CodeService codeService;
	@SuppressWarnings("unchecked")
	public List<Code> getDcode(CodeCondition cond) {
		String riskCode = cond.getRiskCode();
		String language = cond.getLanguage();
		String codeType = cond.getCodeType();
		String typeParam = cond.getTypeParam();
		String extraCond = cond.getExtraCond();
		String query = cond.getQuery();
		if ("*".equals(query) || "".equals(query.trim())) {
			query = "%";
		} else {
			query = "%" + query + "%";
		}
		int maxRecords = MAX_RECORDS;
		if ("firstLoad".equals(cond.getType())) {
			maxRecords = 100; // 如果是firstLoad方式，应该不限个数，但为了防止展现太多，最多显示100条
		}
		List<Object[]> codeList = null;
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		String userCode = ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
//		String comCode = (String) session.getAttribute("ComCode");
		try {
			if("NodeNameTree".equals(codeType)){
				codeList = codeService.listNodes(query, extraCond);
			}else{
				codeList = codeService.listCodeSelect(codeType, riskCode, language, query, 1, maxRecords, "001",
						typeParam, extraCond).getResult();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("", e);
		}
		List<Code> retList = new ArrayList<Code>(0);
		for (Object[] objects : codeList) {
			if (objects[1] != null) {
				Code co = new Code();
				if (objects[1] != null) {
					co.setName(StringUtils.trimToEmpty(objects[1].toString()));
				}
				if (objects[0] != null) {
					co.setCode(objects[0].toString());
				}
				retList.add(co);
			}
		}
		return retList;
	}
	
	
	 
	@SuppressWarnings("unchecked")
	public Map getAllCode(CodeCondition[] conds) {
		Map retMaps = new HashMap<String, List<Code>>(0);
		for (CodeCondition cond : conds) {
			if (cond != null) {
				if ("firstLoad".equals(cond.getType())) {
					retMaps.put(cond.getCodeType(), getDcode(cond));
				}
			}
		}
		return retMaps;
	}
	
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
}
