package com.picc.report.picc.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.UuidUtil;
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
import com.picc.qp.util.HttpClientUtils;
import com.picc.report.paic.PaicReportUtil;
import com.picc.report.picc.schema.report.model.DamageInfo;
import com.picc.report.picc.schema.report.model.Packet;
import com.picc.report.picc.schema.report.model.PolicyInfo;
import com.picc.report.picc.schema.report.model.Requestbody;
import com.picc.report.picc.schema.report.model.Requesthead;
import com.picc.report.picc.schema.report.model.ThirdCarLossInfo;
import com.picc.report.picc.schema.upload.model.PhotoInfo;
import com.picc.report.picc.schema.upload.model.UploadPacket;
import com.picc.report.picc.schema.upload.model.UploadRequestBody;
import com.picc.report.picc.schema.upload.model.UploadRequestHead;
import com.picc.report.picc.service.facade.IPiccReportService;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.service.facade.IReportService;
import com.picc.report.service.facade.IReportTaskDataService;
import com.ymt.core.XMLUtil;

import ins.framework.common.ServiceFactory;

@Service("iPiccReportService")
public class PiccReportServiceImpl implements IPiccReportService {

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
	public JSONObject toPiccReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccident) {
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = HttpClientUtils.HttpClientXMLPost("www.baidu.com", this.ObjectParserXml(qpTTPCase, qpTICAccident), "UTF-8");
			if("200".equals(map.get("code"))){
				jsonObject = JSON.parseObject(map.get("info").toString());
				jsonObject = this.isCheckResule(jsonObject);
			}else {
				jsonObject.put("code", map.get("code"));
				jsonObject.put("msg", map.get("info"));
			}
		} catch (Exception e) {
			jsonObject.put("code", "999");
			jsonObject.put("msg", "报案异常");
		}

		return jsonObject;
	}

	public String ObjectParserXml(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccidents) throws Exception{
		QpTICAccident qpTICAccident = qpTICAccidents.get(0);
		Packet packet = new Packet();
		Requesthead requesthead = new Requesthead();
		requesthead.setUuid(UuidUtil.getUUID());
		requesthead.setUser("0237");
		requesthead.setServerVersion("serverViersion");
		requesthead.setSender("0237");
		requesthead.setRequestType("01530013");
		requesthead.setPassword("9905e05a-fd4b-43c3-aad7-1f5578190373");
		requesthead.setTimestamp(new Date());//请求时间
		Requestbody requestbody = new Requestbody();
		PolicyInfo policyInfo = new PolicyInfo();
		policyInfo.setLicenseNo(qpTICAccident.getDriverVehicleNumber());//车牌号、交强险保单宝、商业险保单号必传一

		DamageInfo damageInfo = new DamageInfo();
		damageInfo.setComCode("32000000");//承保机构代码
		damageInfo.setReportorName(qpTICAccident.getDriverName());//报案人姓名
		damageInfo.setReportorNumber(qpTICAccident.getDriverMobile());//报案人电话
		damageInfo.setReportDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//报案时间 yyyy-MM-dd
		damageInfo.setReportHour(new SimpleDateFormat("HH:mm:ss").format(new Date()));//报案时间 HH:mm:ss
		damageInfo.setDamageComCode("32010000");//出险机构代码
		//		damageInfo.setDamageDate("2016-08-22");
		//		damageInfo.setDamageHour("10:47:55");
		damageInfo.setDamageDate(new SimpleDateFormat("yyyy-MM-dd").format(qpTTPCase.getCaseTime()));//出险时间 yyyy-MM-dd
		damageInfo.setDamageHour(new SimpleDateFormat("HH:mm:ss").format(qpTTPCase.getCaseTime()));//出险时间 HH:mm:ss
		damageInfo.setDamageAddrProvince(qpTTPCase.getCaseProvince());//出险省份代码
		damageInfo.setDamageAddrCity(qpTTPCase.getCaseCity());//出险城市代码
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

		damageInfo.setDamageAddress(province + city + area + qpTTPCase.getCaseStreet());//出险详细地址
		damageInfo.setSystemCode("01");//数据来源  快处快赔
		damageInfo.setLongitude(qpTTPCase.getLongitude());
		damageInfo.setLatitude(qpTTPCase.getLatitude());
		damageInfo.setRemark("驾驶人张聚甫于2016-09-14在江苏省南京市鼓楼区宁海路街道南京市鼓楼区西康路1号使用被保险机动车过程中,发生碰撞,发生单方车损事故,涉及承保车辆损失;承保车苏A12345受损;当前损失标的位于江苏省南京市鼓楼区宁海路街道南京市鼓楼区西康路1号;客户使用自助查勘");
		damageInfo.setDamageCode("A10029");//出险原因代码
		damageInfo.setDamageName("碰撞");//出险原因
		damageInfo.setLflag("L");
		damageInfo.setDriversName(qpTICAccident.getDriverName());//驾驶人姓名
		damageInfo.setLinkerName(qpTICAccident.getDriverName());//联系人
		damageInfo.setLinkerNumber(qpTICAccident.getDriverMobile());//联系人手机
		damageInfo.setRelationShip("001");
		damageInfo.setMercyFlag("1");
		damageInfo.setAotoSchedueFlag("1");
		damageInfo.setAccidentCause("99");
		damageInfo.setSection("01");
		damageInfo.setWeather("04");
		damageInfo.setCheckCategory("02");
		damageInfo.setCarFlag("1");
		damageInfo.setHurtFlag("0");
		damageInfo.setLoserFlag("1");

		//		List<PersonLossInfo> personLossInfos = new ArrayList<PersonLossInfo>();
		//		PersonLossInfo personLossInfoOne = new PersonLossInfo();
		//		personLossInfoOne.setPersonName("张三");
		//		personLossInfoOne.setLossItemType("010");
		//		personLossInfos.add(personLossInfoOne);
		//		
		//		List<PropLossInfo> propLossInfos = new ArrayList<PropLossInfo>();
		//		PropLossInfo propLossInfoOne = new PropLossInfo();
		//		propLossInfoOne.setLossItemName("行李箱");
		//		propLossInfoOne.setLossItemType("050");
		//		propLossInfos.add(propLossInfoOne);

		List<ThirdCarLossInfo> thirdCarLossInfos = new ArrayList<ThirdCarLossInfo>();
		for (int i = 1; i < qpTICAccidents.size(); i++) {
			ThirdCarLossInfo thirdCarLossInfoOne = new ThirdCarLossInfo();
			thirdCarLossInfoOne.setLossItemType("010");//损失类型 三者车
			thirdCarLossInfoOne.setLicenseNo(qpTICAccidents.get(i).getDriverVehicleNumber());//车牌号
			thirdCarLossInfoOne.setLicenseType("02");//车牌种类 必填 TODO
			thirdCarLossInfoOne.setLinkMobile(qpTICAccidents.get(i).getDriverMobile());
			thirdCarLossInfos.add(thirdCarLossInfoOne);
		}


		requestbody.setPolicyInfo(policyInfo);
		requestbody.setDamageInfo(damageInfo);
		//		requestbody.setPersonLossInfo(personLossInfos);
		//		requestbody.setPropLossInfo(propLossInfos);
		requestbody.setThirdCarLossInfo(thirdCarLossInfos);

		packet.setRequesthead(requesthead);
		packet.setRequestbody(requestbody);

		StringBuffer sBuffer = XMLUtil.objectParserXml(packet,true);
		return XMLUtil.getHead("utf-8","")+"\n"+sBuffer.toString();
	}




	//	000000	校验通过，附件解析失败
	//		901003	上传附件失败
	//		900001	系统发生未知错误
	//		999999	处理成功
	@Override
	public JSONObject reportPiccUpload(String taskDataId, String taskType){
		JSONObject resultObject = new JSONObject();

		//获取任务所需数据
		ReportTaskData reportTaskData = reportTaskDataService.findReportTaskDataByTaskDataId(taskDataId);

		try {
			String data = buildUploadXml(reportTaskData.getCaseId(),reportTaskData.getReportNo(), taskType);
			//上传单证参数
//			String uploadFileBatchURL = Constants.getPICC_URL()+"InterfaceManager/servlet/dispatcherServlet";
			String uploadFileBatchURL = "InterfaceManager/servlet/dispatcherServlet";//TODO
			HttpResponse response = PaicReportUtil.sendPiccHttpRequest(uploadFileBatchURL,data);
			//----------------处理结果
			
			JSONObject resultDate = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
			logger.info("上传结果:"+resultDate);
			resultObject = this.isCheckResule(resultDate);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return resultObject;
	}

	public String buildUploadXml(String caseId,String reportNo, String taskType) throws Exception{
		UploadPacket packet = new UploadPacket();
		String uuid = UuidUtil.get32UUID();
		UploadRequestHead requestHead = new UploadRequestHead();
		UploadRequestBody requestBody = new UploadRequestBody();
		List<PhotoInfo> photoInfos = new ArrayList<PhotoInfo>();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FULL_DATE_STR);
		String format = sdf.format(date);

		requestHead.setUuid(uuid);
		requestHead.setRequestType("post");
		requestHead.setUser("");
		requestHead.setPassword("");
		requestHead.setSender("");
		requestHead.setServerVersion("");
		requestHead.setFlowintime(format);

		if("2".equals(taskType) || "4".equals(taskType)){
			boolean isModify = "4".equals(taskType); 
			//获取责任认定书
			JSONObject caseInfoPic = reportService.getCaseInfoPic(caseId);
			if("0".equals(caseInfoPic.getString("code"))){
				String reportData = caseInfoPic.getString("data");
				PhotoInfo casePic = new PhotoInfo();
				casePic.setPhotoCode(reportNo);//暂时设置为报案号
				casePic.setPhotoName("报案号为:"+reportNo+" 的责任认定书");
				if(isModify){
					casePic.setPhotoName("报案号为:"+reportNo+" 修改后的责任认定书");
				}
				casePic.setPhotoTime(format);
				casePic.setPhotoData64(reportData);
				photoInfos.add(casePic);
			}else{
				logger.info("获取责任认定书图片失败");
				return null;
			}
		}

		if(!"4".equals(taskType)){
			JSONObject picUrlObject= reportService.getAccidentPicUrl(caseId, taskType);
			if("0".equals(picUrlObject.get("code"))){
				List<String> urls = (List<String>) picUrlObject.get("data");
				for (int j = 0; j < urls.size(); j++) {
					String accidentPicUrl = Constants.getIMAGEHTTPQUERY()+urls.get(j);
					String filedata ="data:image/png;base64,"+PaicReportUtil.GetUrlImageToBase64(accidentPicUrl);
					PhotoInfo photoInfo = new PhotoInfo();
					photoInfo.setPhotoCode(reportNo);
					photoInfo.setPhotoName("定责图片");
					photoInfo.setPhotoTime(format);
					photoInfo.setPhotoData64(filedata.replaceAll("\r\n", ""));
					photoInfos.add(photoInfo);
				}
			}else{
				logger.info("获取当事人url图片失败:"+picUrlObject);
				return null;
			}
		}

		//photoInfo.setLicenseNo("");
		//photoInfo.setLossItemId("");
		//photoInfo.setCameraType("");
		//photoInfo.setMacAddr("");
		//photoInfo.setManufacturer("");
		//photoInfo.setOperatorCode("");
		//photoInfo.setOperatorName("");
		//photoInfo.setPhotoDes("10");
		//photoInfo.setPhotoResolution("");
		//photoInfo.setPhotoSize("");

		requestBody.setUuid(uuid);
		requestBody.setPhotoInfoList(photoInfos);
		requestBody.setRegistNo(reportNo);
		requestBody.setSoureFlag("01");//01.快处快赔 02.支付宝 03.微信 
		requestBody.setTaskType("2");//1、查勘照片 2、单证照片 3、待分拣照片

		//requestBody.setRespType("");//责任类型 (1：全责 2：主责 3：同责 4：次责 5：无责 0：不确定)
		//requestBody.setAccidType("");//D:单车 S:双车 M：多车

		packet.setRequestHead(requestHead);
		packet.setRequestBody(requestBody);

		StringBuffer sBuffer = XMLUtil.objectParserXml(packet,false);
		return sBuffer.toString();
	}



	public JSONObject isCheckResule(JSONObject resultDate){
		logger.info("人保报案结果:" + resultDate);
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

		if("999999".equals(result.getString("code"))){
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
