package com.picc.cxf.service.impl;

import java.util.Date;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.picc.common.utils.StringUtils;
import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.cxf.service.ReportCaseForeign;
import com.picc.report.schema.model.ReportCaseData;
import com.picc.report.service.facade.IReportCaseDataService;

@WebService(endpointInterface = "com.picc.cxf.service.ReportCaseForeign")
public class ReportCaseForeignImpl implements ReportCaseForeign {

	protected final Logger logger = (Logger) LoggerFactory.getLogger(ReportCaseForeignImpl.class);
	
	@Autowired
	private IReportCaseDataService reportCaseDataService;
	
	@Override
	public String paicCallbackClaimsResult(String json) {
		logger.info("quick:"+json);
		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isNotEmpty(json)) {
			try {
				//案件号
				JSONObject param = JSONObject.parseObject(json);
				String reportNo = param.getString("reportNo");
				if(StringUtils.isNotEmpty(reportNo)){
					ReportCaseData reportCaseData = reportCaseDataService.findReportCaseDataByReportNo(reportNo);
					if(reportCaseData!= null){
						reportCaseData.setReportData(new Date());
						reportCaseData.setEndCaseTime(param.getString("endCaseTime"));
						if(StringUtils.isNotEmpty(param.getString("reportCaseStatus"))){
							reportCaseData.setReportCaseStatus(param.getString("reportCaseStatus"));
						}
						reportCaseData.setReportModo(param.getString("reportModo"));
						reportCaseData.setCaseTimes(param.getString("caseTimes"));
						reportCaseData.setPayMentAmount(param.getString("payMentAmount"));
						reportCaseData.setReportModo("湖南快处快赔");
						//1-赔付，2-零结，3-商业险拒赔，4-整案拒赔，5-注销
						reportCaseData.setIndemnityConclusion(param.getString("indemnityConclusion"));
						reportCaseData.setPolicyInfoList(param.getString("policyInfoList"));
						reportCaseDataService.update(reportCaseData);
						jsonResult.setJsonResult(CommonEnum.SUCCESS, null);
					}else {
						jsonResult.setJsonResult(CommonEnum.ERROR_REPORTNO, null);
					}
				}else {
					logger.error("回填理赔信息错误，无报案号:"+param);
					jsonResult.setJsonResult(CommonEnum.ERROR_REPORTNO, null);
				}
			} catch (Exception e) {
				logger.error("保存理赔结果异常"+e);
				jsonResult.setJsonResult(CommonEnum.ERROR_APP, null);
			}
		}else {
			jsonResult.setJsonResult(CommonEnum.EMPTY_PARAM, null);
		}
		logger.info("返回结果:"+jsonResult.toJsonString());
		return jsonResult.toJsonString();
	}

	public enum IndemnityConclusion {
		INDEMNITYCONCLUSION_1("1", "赔付"),
		INDEMNITYCONCLUSION_2("2", "零结"),
		INDEMNITYCONCLUSION_3("3", "商业险拒赔"),
		INDEMNITYCONCLUSION_4("4", "整案拒赔"),
		INDEMNITYCONCLUSION_5("5", "注销");
		
		// 构造方法
		private IndemnityConclusion(String index, String desc) {
			this.index = index;
			this.desc = desc;
		}

		private String index;
		private String desc;
		
		// 普通方法
		public static String getSmsModel(String index) {
			for (IndemnityConclusion indemnityConclusion : IndemnityConclusion.values()) {
				if (index.equals(indemnityConclusion.getIndex())) {
					return indemnityConclusion.desc;
				}
			}
			return "";
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}
}
