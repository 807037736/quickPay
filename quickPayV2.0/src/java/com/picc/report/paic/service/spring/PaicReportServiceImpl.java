package com.picc.report.paic.service.spring;

import ins.framework.common.ServiceFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONObject;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.spring.QpTCOMCityServiceSpringImpl;
import com.picc.qp.service.spring.QpTCOMDistrictServiceSpringImpl;
import com.picc.qp.service.spring.QpTCOMProvinceServiceSpringImpl;
import com.picc.qp.util.Constants;
import com.picc.report.paic.PaicReportUtil;
import com.picc.report.paic.service.facade.IPaicReportService;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.service.facade.IReportService;
import com.picc.report.service.facade.IReportTaskDataService;

@Service("iPaicReportService")
public class PaicReportServiceImpl implements IPaicReportService{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IQpTTPCaseService qpTTPCaseService;

	@Autowired
	private IQpTICAccidentService qpTICAccidentService;

	@Autowired
	private IReportTaskDataService reportTaskDataService;

	@Autowired
	private IQpTICPictureService qpTICPictureService;
	
	@Autowired
	private IReportService reportService;
	
	
	@Override
	public JSONObject toPaicReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccident, String token){
		JSONObject jsonObject = new JSONObject();
		try {
			
			String userName = Constants.getREPORT_INTERFACE().get("PAIC_userName");
			String url = Constants.getREPORT_INTERFACE().get("PAIC_report");
			
			QpTICAccident myQpTICAccident = qpTICAccident.get(0);
	        Map<String, Object>param = new HashMap<String, Object>();
	        param.put("token", token);
	        param.put("orgCode", "PAIC");//保险公司
	        param.put("carMark", myQpTICAccident.getDriverVehicleNumber());//车牌号码
	        param.put("avoidRepeatData", myQpTICAccident.getAcciId());//当事人编号、唯一编号
//	        param.put("policyNo", "10516003900010595543");//保单号 N
	        param.put("vinNo", (myQpTICAccident.getChassisNumber().length()==17)?myQpTICAccident.getChassisNumber().toUpperCase():"");//车架号 N
	        if(myQpTICAccident.getChassisNumber().length()!=17){
	        	logger.info("平安报案车架号不符合，默认为空:"+myQpTICAccident.getChassisNumber());
	        }
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        param.put("accidentDate", simpleDateFormat.format(qpTTPCase.getCaseTime()));//出险时间
	        // 省份
			String province = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseProvince())) {
				QpTCOMProvinceId queryProcinceID = new QpTCOMProvinceId();
				queryProcinceID.setProvId(qpTTPCase.getCaseProvince());
				QpTCOMProvinceServiceSpringImpl qpTCOMProvinceService = (QpTCOMProvinceServiceSpringImpl)ServiceFactory.getService("qpTCOMProvinceService");
				QpTCOMProvince currentProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(queryProcinceID);
				if (currentProvince != null) {
					province = currentProvince.getProvName();
				}
			}
			// 城市
			String city = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseCity())) {
				QpTCOMCityId queryCityID = new QpTCOMCityId();
				queryCityID.setCityId(qpTTPCase.getCaseCity());
				QpTCOMCityServiceSpringImpl qpTCOMCityService = (QpTCOMCityServiceSpringImpl)ServiceFactory.getService("qpTCOMCityService");
				QpTCOMCity currentCity = qpTCOMCityService.findQpTCOMCityByPK(queryCityID);
				if (currentCity != null) {
					city = currentCity.getCityName();
				}
			}
			// 地区
			String area = "";
			if (StringUtils.isNotEmpty(qpTTPCase.getCaseDistrict())) {
				QpTCOMDistrictId queryAreaID = new QpTCOMDistrictId();
				queryAreaID.setDistrictId(qpTTPCase.getCaseDistrict());
				QpTCOMDistrictServiceSpringImpl qpTCOMDistrictService = (QpTCOMDistrictServiceSpringImpl)ServiceFactory.getService("qpTCOMDistrictService");
				QpTCOMDistrict currentArea = qpTCOMDistrictService.findQpTCOMDistrictByPK(queryAreaID);
				if (currentArea != null) {
					area = currentArea.getDistrictName();
				}
			}
	        param.put("accidentPlaceProvince", StringUtils.isEmpty(province+city)?"长沙市":province+city);//出险地点城市
	        param.put("accidentPlaceDistrict", StringUtils.isEmpty(area)?"区":area);//出险地点行政区
	        param.put("accidentPlaceDetail", qpTTPCase.getCaseStreet());//出险地点地址
	        param.put("accidentType", "2");//事故类型  1：单方   2：多方
	        param.put("isNorDriving", "1");//车辆能否正常行驶  0 否  1 是
	        param.put("isReportOnPort", "1");//是否现场报案    非现场0，现场为1
	        param.put("accidentCause", "99");//出险原因
//	       	 编码	事故情形				出险原因1级			出险原因2级			出险原因3级
//	        01	停车					停放受损			被车撞			三者未逃逸
//	        02	倒车					行驶受损			倒车				与机动车撞
//	        03	逆行					行驶受损			左转弯			与机动车撞
//	        04	溜车					行驶受损			直行				与机动车撞
//	        05	开关车门				停放受损			被车撞			三者未逃逸
//	        06	违反交通信号灯			行驶受损			直行				与机动车撞
//	        07	变更车道与其他车辆刮擦		行驶受损			直行				与机动车撞
//	        08	未保持安全车距与前车追尾	行驶受损			直行				与机动车撞
//	        99	其他					其他				其他				其他
//	        param.put("accidentCauseMark", qpTTPCase.getCaseNotes());//出险原因备注 N
	        param.put("reportName", myQpTICAccident.getDriverName());//报案人姓名
	        param.put("reportTelNo", myQpTICAccident.getDriverMobile());//报案人联系方式
	        param.put("reportMode", userName);//报案渠道
	        param.put("driverName", myQpTICAccident.getDriverName());//驾驶员姓名
	        param.put("channelTypes", "01,02");//损失类型
//	                        编码	损失类型描述
//	        01	本车（标的）车损
//	        02	三者车损
//	        03	本车（标的）物损
//	        04	三者车内物损
//	        05	三者车外物损
//	        06	三者车内人
//	        07	三者车外人
//	        08	司机
//	        09	乘客
//	        param.put("channelTypesDesc", "本车,三者车,有物损,有人伤");//损失类型中文描述 N
	        param.put("accidentPlaceLongtitude", qpTTPCase.getLongitude());//GPS的经度坐标
	        param.put("accidentPlaceLatitude", qpTTPCase.getLatitude());//GPS的纬度坐标
	        if(!"26".equals(qpTTPCase.getCenterId())){
	        	//线下
	        	param.put("dataSource", "03");//数据来源  此数据提供方01-微信,02-APP,03-交警
	        }else {
	        	//微信
	        	param.put("dataSource", "01");//数据来源  此数据提供方01-微信,02-APP,03-交警
			}
	        
//	        param.put("clientId", "fe678402-4b44-48ce-a1da-b320f6011e7d");//客户号 N
//	        param.put("dutyCoefficient", "100");//dutyCoefficient N  责任系数
	        param.put("dutyCoefficientDesc", myQpTICAccident.getDriverLiabilityDesc());//dutyCoefficientDesc  责任描述
	        List<Map<String,String>>thirdParamList = new ArrayList<Map<String,String>>();//三者信息列表
	        if(qpTICAccident != null && qpTICAccident.size() > 1){
	        	for (int i = 1; i < qpTICAccident.size(); i++) {
	        		Map<String,String> thirdParam = new HashMap<String,String>();
	    	        thirdParam.put("thirdCarDriverName", qpTICAccident.get(i).getDriverName());//三者驾驶员姓名
	    	        thirdParam.put("thirdCarDriverTel", qpTICAccident.get(i).getDriverMobile());//三者驾驶员联系方式
	    	        thirdParam.put("thirdCarMark", qpTICAccident.get(i).getDriverVehicleNumber());//三者车牌号   不带杠
	    	        thirdParamList.add(thirdParam);
				}
	        }
//	        Map<String,String> thirdParam1 = new HashMap<String,String>();
//	        thirdParam1.put("thirdCarDriverName", "小红");//三者驾驶员姓名
//	        thirdParam1.put("thirdCarDriverTel", "13209375442");//三者驾驶员联系方式
//	        thirdParam1.put("thirdCarMark", "粤B-35Y7M");//三者车牌号   不带杠
//	        thirdParamList.add(thirdParam1);
//	        Map<String,String> thirdParam2 = new HashMap<String,String>();
//	        thirdParam2.put("thirdCarDriverName", "小小");
//	        thirdParam2.put("thirdCarDriverTel", "18723768901");
//	        thirdParam2.put("thirdCarMark", "粤B-79B5F");
//	        thirdParamList.add(thirdParam2);
	        param.put("thirdCarInfoList", thirdParamList);
	        logger.info("平安报案参数:"+com.alibaba.fastjson.JSONObject.toJSONString(param));
	        HttpResponse response = PaicReportUtil.sendPiccHttpRequest(url, com.alibaba.fastjson.JSONObject.toJSONString(param));
	        if(response != null){
	        	String responseStr = EntityUtils.toString(response.getEntity());
		        logger.info("----------------PICC报案结果："+responseStr);
		        JSONObject resultDate = JSONObject.parseObject(responseStr);
		        logger.info("报案结果："+resultDate);
		        jsonObject = this.isCheckResule(resultDate);
	        }else {
	        	jsonObject.put("code", "999");
				jsonObject.put("msg", "请求超时 ,请稍后在试");
			}
	        
		} catch (Exception e) {
			logger.error("报案异常:", e);
			jsonObject.put("code", "999");
			jsonObject.put("msg", "报案异常,请稍后在试");
		}
		return jsonObject;
	}
	
	
	
	//		000000	校验通过，附件解析失败
	//		901003	上传附件失败
	//		900001	系统发生未知错误
	//		999999	处理成功
	@Override
	public JSONObject reportPaicUpload(String taskDataId,String token, String taskType){
		JSONObject resultObject = new JSONObject();
		
		//获取任务所需数据
		ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);
		String caseId = reportTaskData.getCaseId();
		//认定书集合
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		
		try {
			String userName = Constants.getREPORT_INTERFACE().get("PAIC_userName");
			String uploadFileBatchURL = Constants.getREPORT_INTERFACE().get("PAIC_upload");
			
			
			//上传单证参数
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("reportNo", reportTaskData.getReportNo());
			param.put("token", token);
			param.put("orgCode", "PAIC");
			if("2".equals(taskType) || "4".equals(taskType)){
				boolean isModify = "4".equals(taskType); 
				//获取责任认定书Base64
				JSONObject caseInfoPic = this.getCaseInfoPic(fileList, caseId, userName, isModify);
				if(!"0".equals(caseInfoPic.getString("code"))){
					resultObject.put("code", "999");
					resultObject.put("msg", "获取责任认定书图片失败");
					return resultObject;
				}
			}
			if(!"4".equals(taskType)){
				JSONObject picUrlObject= this.getAccidentPicUrl(fileList, caseId, userName, taskType);
				if(!"0".equals(picUrlObject.getString("code"))){
					logger.info("获取当事人查勘图片失败:"+picUrlObject);
					resultObject.put("code", "999");
					resultObject.put("msg", "获取当事人查勘图片失败");
					return resultObject;
				}
			}
			
			param.put("fileList", fileList);
			
			logger.info("平安责任认定书上传参数:"+param);
			if(null != fileList && fileList.size()>0){
				HttpResponse response = PaicReportUtil.sendPiccHttpRequest(uploadFileBatchURL,com.alibaba.fastjson.JSONObject.toJSONString(param));
				JSONObject resultDate = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
				logger.info("平安责任认定书上传结果:"+resultDate);
				resultObject = this.isCheckResule(resultDate);
			}else{
				resultObject.put("code","0");
				resultObject.put("msg", "无图片，不上传单证，当做任务完成处理。");
			}
		} catch (Exception e) {
			logger.error("上传单证异常:", e);
			resultObject.put("code", "999");
			resultObject.put("msg", "上传单证异常,请稍后在试");
		}
		return resultObject;
	}
	
	/**
	 * 获取责任认定书
	 * @param 需要存放图片的对象 List<Map<String,Object>>
	 * @param caseId
	 * @param userName
	 * @param isModify 是否是修改资料后补传责任认定书
	 * @return
	 */
	public JSONObject getCaseInfoPic(List<Map<String,Object>> fileList, String caseId, String userName, boolean isModify){
		JSONObject jsonObject = new JSONObject();
		try {
			//获取责任认定书Base64
			JSONObject caseInfoPic = reportService.getCaseInfoPic(caseId);
			if("0".equals(caseInfoPic.getString("code"))){
				Map<String,Object> reportMap = new HashMap<String,Object>();
				String reportData = caseInfoPic.getString("data");
				reportMap.put("fileName", "reportPic"+caseId);
				if(isModify){
					reportMap.put("fileName", "修改后的责任认定书reportPic"+caseId);
				}
				reportMap.put("fileData", reportData);
				reportMap.put("fileMD5", PaicReportUtil.MD5Sign(reportData));
				reportMap.put("fileSource", userName);  
				fileList.add(reportMap);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "OK");
			}else{
				logger.info("获取责任认定书失败:" + caseInfoPic);
				jsonObject.put("code", "999");
				jsonObject.put("msg", "获取责任认定书图片失败");
			}
		} catch (Exception e) {
			logger.info("获取责任认定书异常", e);
			jsonObject.put("code", "999");
			jsonObject.put("msg", "获取责任认定书异常");
		}
		return jsonObject;
	}
	
	/**
	 * 获取当事人图片 和 查勘图片
	 * @param caseId
	 * @param userName
	 * @return
	 */
	public JSONObject getAccidentPicUrl(List<Map<String,Object>> fileList, String caseId, String userName, String taskType){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject picUrlObject= reportService.getAccidentPicUrl(caseId, taskType);
			if("0".equals(picUrlObject.get("code"))){
				List<String> urls = (List<String>) picUrlObject.get("data");
				if(urls != null && urls.size() >0 ){
					for (int j = 0; j < urls.size(); j++) {
						String accidentPicUrl = Constants.getIMAGEHTTPQUERY()+urls.get(j);
						Map<String,Object> fileMap = new HashMap<String,Object>();
						String filedata ="data:image/png;base64,";
						filedata += PaicReportUtil.GetUrlImageToBase64(accidentPicUrl);
						fileMap.put("fileName", caseId+j);
						fileMap.put("fileData", filedata.replaceAll("\r\n", ""));
						fileMap.put("fileMD5", PaicReportUtil.MD5Sign(filedata));
						fileMap.put("fileSource", userName);  
						fileList.add(fileMap);
					}
					jsonObject.put("code", 0);
					jsonObject.put("msg", "OK");
				}else {
					jsonObject.put("code", "0");
					jsonObject.put("msg", "图片为空");
				}
				
			}else{
				logger.info("获取当事人url图片失败:"+picUrlObject);
				jsonObject.put("code", "999");
				jsonObject.put("msg", "获取当事人查勘图片失败");
			}
		} catch (Exception e) {
			logger.info("获取当事人查勘图片失败", e);
			jsonObject.put("code", "999");
			jsonObject.put("msg", "获取当事人查勘图片失败");
		}
		return jsonObject;
	}
	
	
	public JSONObject isCheckResule(JSONObject resultDate){
		JSONObject jsonObject = new JSONObject();
		if(resultDate.isEmpty()){
			jsonObject.put("code", -1);
			jsonObject.put("msg", "操作失败,无返回结果");
			return jsonObject;
		}
		
		JSONObject result = resultDate.getJSONObject("result");
		if(result.isEmpty()){
			jsonObject.put("code", -1);
			jsonObject.put("msg", "操作失败,调用结果不存在");
			return jsonObject;
		}
		
		if("999999".equals(result.getString("code")) || "917003".equals(result.getString("code"))){
			//成功
			jsonObject.put("code", 0);
			jsonObject.put("msg", "OK");
			jsonObject.put("data", resultDate.getJSONObject("data"));
		}else {
			jsonObject.put("code", result.getString("code"));
			jsonObject.put("msg", result.getString("message"));
		}
		
		return jsonObject;
	}
	
}
