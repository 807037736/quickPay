package com.picc.common.handler;

import ins.framework.common.ServiceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.service.facade.IQpTCOMDictionaryService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.util.CodeUtils;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;

/**
 * 上传案件到一路通通用代码
 * @author obba
 *
 */
public class UploadCaseToTashHandlerCommon {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String ENCODING = "UTF-8";
	private static final String USERNAME = "test";
	private static final String PASSWORD = "1234";
	
	/**
	 * 默认的上传逻辑
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JsonResult uploadCaseToJob(Map<String,String> params){
		JsonResult jsonResult = new JsonResult();
		String caseId = params.get("caseId");
		IQpTTPCaseService qpTTPCaseService = (IQpTTPCaseService) ServiceFactory.getService("qpTTPCaseService");
		IQpTCommonService qpTCommonService = (IQpTCommonService) ServiceFactory.getService("qpTCommonService");
		IQpTICAccidentService iQpTICAccidentService = (IQpTICAccidentService) ServiceFactory.getService("qpTICAccidentService");
		IQpTTPLawService qpTTPLawService = (IQpTTPLawService) ServiceFactory.getService("qpTTPLawService");
		try{
			// 案件信息
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase  qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			
			// 添加案件天气
			String weatherDesc = qpTCommonService.getSelectCodeName(SelectTypeEnum.WEATHER.getCode(), qpTTPCase.getCaseWeather());
			qpTTPCase.setCaseWeatherDesc(weatherDesc);
			
			// 当事人信息
			QpTICAccident query = new QpTICAccident();
			query.setCaseId(caseId);
			List<QpTICAccident> accidents = iQpTICAccidentService.findByQpTICAccident(query, 0, Integer.MAX_VALUE).getResult();
			// 添加必要信息
			for (QpTICAccident accident : accidents) {
				
				// 驾驶人性别
				if("0".equals(accident.getDriverSex())) {
					accident.setDriverSexDesc("男");
				}else {
					accident.setDriverSexDesc("女");
				}
				
				// 驾驶人责任
				if("10".equals(accident.getDriverLiability())) {
					accident.setDriverLiabilityDesc("全部责任");
				}else if("11".equals(accident.getDriverLiability()))  {
					accident.setDriverLiabilityDesc("主要责任");
				}else if("12".equals(accident.getDriverLiability()))  {
					accident.setDriverLiabilityDesc("次要责任");
				}else if("13".equals(accident.getDriverLiability()))  {
					accident.setDriverLiabilityDesc("同等责任");
				}else if("14".equals(accident.getDriverLiability()))  {
					accident.setDriverLiabilityDesc("无责任");
				}else{
					accident.setDriverLiabilityDesc("\\");
				}
				
				// 交通法规
				QpTTPLawId qpTTPLawId = new QpTTPLawId();
				qpTTPLawId.setLawId(accident.getDriverLawId());
				QpTTPLaw qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(qpTTPLawId);
				if(qpTTPLaw != null) {
					// 法律法规处理
					accident.setDriverLawDesc(qpTTPLaw.getLawName() + qpTTPLaw.getLawWords());
				}
				
				// 方向 
				String directionDesc = qpTCommonService.getSelectCodeName(SelectTypeEnum.DIRECTION.getCode(), accident.getDriverDirection());
				accident.setDriverDirectionDesc(directionDesc);
				
				// 车辆类型
				String vehicleTypeDesc = qpTCommonService.getSelectCodeName(SelectTypeEnum.VEHICLE.getCode(), accident.getDriverVehicleType());
				switch (vehicleTypeDesc) {
				case "小型客车":
					vehicleTypeDesc = "小型汽车";
					break;
				case "小型货车":
					vehicleTypeDesc = "轻型货车";
					break;
				case "重型货车":
					vehicleTypeDesc = "大型货车";
					break;
				case "摩托车":
					vehicleTypeDesc = "两轮摩托车";
					break;
				case "半挂车":
					vehicleTypeDesc = "其他车型";
					break;

				default:
					break;
				}
				accident.setDriverVehicleTypeDesc(vehicleTypeDesc);
			}
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				@Override
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return process(value);
				}

				@Override
				public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					return process(value);
				}

				private Object process(Object value) {
					try {
						return value == null ? "" : dateFormat.format((Date) value);
					} catch (Exception e) {
						return "";
					}
				}
			});
			
			JSONObject param = new JSONObject();
			JSONObject data = new JSONObject();
			data.put("caseInfo", JSONObject.fromObject(qpTTPCase, jsonConfig));
			data.put("accidents", JSONArray.fromObject(accidents, jsonConfig));
			data.put("username", CodeUtils.parseByteToHexStr(USERNAME.getBytes()));
			data.put("password", CodeUtils.parseByteToHexStr(PASSWORD.getBytes()));
			String mac = CodeUtils.getSign("eGovernment", data);
			param.put("mac", mac);
			param.put("data", data);
			JSONObject json = JSONObject.fromObject(param, jsonConfig);
			///rest/case/uploadCase job项目 TODO
			///rest/caseService/transferCase
			Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(Constants.getASYNC_URL() + "/rest/case/uploadCase", json.toString(), ENCODING);
			logger.info(qpTTPCase.getCaseId()+"UploadCaseToTashHandlerCommon:177---sendMap:"+sendMap);
			
			if (sendMap != null && !sendMap.isEmpty() && "200".equals(sendMap.get("code"))) {
				JSONObject result = JSONObject.fromObject(sendMap.get("info"));
				jsonResult.setState(result.getString("state"));
				jsonResult.setMsg(result.getString("msg"));
				jsonResult.setData(result.get("data"));
			} else {
				jsonResult.setJsonResult(CommonEnum.FAIL, "");
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.setJsonResult(CommonEnum.ERROR_APP2, "");
		}
		return jsonResult;
	}
	
	protected boolean isOpen() {
		IQpTCOMDictionaryService  iQpTCOMDictionaryService = (IQpTCOMDictionaryService) ServiceFactory.getService("iQpTCOMDictionaryService");
		if (iQpTCOMDictionaryService != null) {
			try {
				List<QpTCOMDictionary> list = iQpTCOMDictionaryService.getImagePath("EGOVERMENT");
				for (QpTCOMDictionary d : list) {
					if ("UPLOAD_SWITCH".equals(d.getId().getCode())) {
						return "1".equals(d.getValue());
					}
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return false;
	}
}
