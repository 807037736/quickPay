package com.picc.cxf.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.jws.WebService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.web.DwrPush;
import com.picc.cxf.schema.model.CaseAllData;
import com.picc.cxf.schema.model.CaseInfo;
import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.cxf.schema.model.PersonInfo;
import com.picc.cxf.service.SaveCaseForeign;
import com.picc.qp.enums.CaseHandleStatusEnum;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.WxTask;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.service.wx.facade.WxTaskService;
import com.picc.qp.util.SealUtil;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.service.facade.IUmTRoleService;
import com.sinosoft.sysframework.common.datatype.DateTime;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.json.JSONArray;
import sun.misc.BASE64Encoder;

@WebService(endpointInterface = "com.picc.cxf.service.SaveCaseForeign")
public class SaveCaseForeignImpl implements SaveCaseForeign {

	protected final Logger logger = (Logger) LoggerFactory
			.getLogger(SaveCaseForeignImpl.class);

	@Autowired
	private IQpTTPFastCenterService qpTTPFastCenterService;

	@Autowired
	private IQpTTPCaseService qpTTPCaseService;

	@Autowired
	private IQpTICAccidentService qpTICAccidentService;

	@Autowired
	private WxTaskService wxTaskService;

	@Autowired
	private DwrPush dwrPush;

	@Autowired
	private IQpTICPictureService qpTICPictureService;

	@Autowired
	private IQpTCOMProvinceService qpTCOMProvinceService;

	@Autowired
	private IQpTCOMCityService qpTCOMCityService;

	@Autowired
	private IQpTCOMDistrictService qpTCOMDistrictService;

	@Autowired
	private IQpTICCompanyService qpTICCompanyService;

	@Autowired
	private IUmTRoleService umTRoleService;

	@Autowired
	private IQpTCommonService qpTCommonService;
	
	@Autowired
	private WxCaseService wxCaseService;
	

	@Override
	public String uploadImg(String json) {
		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isNotEmpty(json)) {
			try {
				net.sf.json.JSONObject param = net.sf.json.JSONObject.fromObject(json);
				if (param.containsKey("groupID") && StringUtils.isNotEmpty(param.getString("groupID"))
						&& param.containsKey("imgs") && StringUtils.isNotEmpty(param.getString("imgs"))) {
					JSONArray imgs = param.getJSONArray("imgs");
					for (int i = 0; i < imgs.size(); i++) {
						net.sf.json.JSONObject img = imgs.getJSONObject(i);
						if (img.getBoolean("status")) {
							QpTICPicture qpTICPicture = new QpTICPicture();
							qpTICPicture.setFileName(img.getString("url"));
							qpTICPicture.setGroupId(param.getString("groupID"));
							qpTICPicture.setOriginalFileName(img.getString("resource_url"));
							qpTICPicture.setValidStatus("4");// 新增状态为4
							qpTICPicture.setPicDesc(img.getString("name"));
							qpTICPictureService.saveQpTICPicture(qpTICPicture, "");
						}
					}
					jsonResult.setJsonResult(CommonEnum.SUCCESS, null);
				} else {
					jsonResult.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} catch (Exception e) {
				logger.error("", e);
				jsonResult.setJsonResult(CommonEnum.ERROR_APP, null);
			}
		} else {
			jsonResult.setJsonResult(CommonEnum.NONE_PARAM, null);
		}
		return jsonResult.toJsonString();
	}

	@Override
	public String uploadCase(String json) {
		JSONObject jsonObject = new JSONObject();
		try {
			// 解析json串 组装
			CaseAllData allData = JSON.parseObject(json, CaseAllData.class);
			CaseInfo caseInfo = allData.getCaseInfo();
			List<PersonInfo> personInfos = allData.getPersonInfos();
			String address = caseInfo.getAddress();
			String casedate = caseInfo.getCasedate();
			String cityID = caseInfo.getCityID();
			String distID = caseInfo.getDistID();
			String provID = caseInfo.getProvID();
			String weather = caseInfo.getWeather();
			String latitude = caseInfo.getLatitude();
			String longitude = caseInfo.getLongitude();
			Date caseTime = null;
			try {
				caseTime = DateUtil.parse2Date(casedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QpTTPCase qpTTPCase = new QpTTPCase();
			qpTTPCase.setCaseTime(caseTime);
			qpTTPCase.setCaseProvince(provID);
			qpTTPCase.setCaseCity(StringUtils.isEmptyStr(cityID) ? "430100" : cityID);// 没有城市时默认为长沙
			qpTTPCase.setCaseDistrict(distID);
			qpTTPCase.setCaseStreet(address);
			qpTTPCase.setCaseWeather(weather);
			qpTTPCase.setLatitude(latitude == null ? "" : latitude);
			qpTTPCase.setLongitude(longitude == null ? "" : longitude);
			// 默认值

			List<QpTICAccident> accidentList = new ArrayList<QpTICAccident>();
			for (PersonInfo personInfo : personInfos) {
				String identityno = personInfo.getIdentityno();// 字符 Y 身份证号码
				String name = personInfo.getName();// 字符 Y 姓名
				String mobile = personInfo.getMobile();// 字符 Y 手机号码
				String carno = personInfo.getCarno();// 字符 Y 车牌号
				String cartype = personInfo.getCartype();// 字符 Y 车辆类型,车辆类型,如2
				String drivingtype = personInfo.getDrivingtype();// 字符 Y
																	// 准驾车型,准驾车型,如C1
				String insucompany = personInfo.getInsucompany();// 字符 Y
																	// 保险公司,保险公司,如2
				String dict = personInfo.getDict();// 字符 Y 行驶方向,行驶方向,如2
				String p_provID = personInfo.getProvID();// 字符 Y
															// 住址,省份编码,如广东省编码440000
				String p_cityID = personInfo.getCityID();// 字符 Y
															// 住址,城市编码,如广州市编码440100
				String p_distID = personInfo.getDistID();// 字符 Y
															// 住址,区域编码,如天河区编码440106
				String p_address = personInfo.getAddress();// 字符 Y
															// 住址,地点详细信息,如"天河南101号"
				String resp = personInfo.getResp(); // 字符 Y 责任,责任,如2
				String groupID = personInfo.getGroupID();// 字符 Y 照片组号
				String accidentNotes = personInfo.getAccidentNotes();

				QpTICAccident accident = new QpTICAccident();
				accident.setDriverIDType("6");// 固定类型为身份证
				accident.setDriverIDNumber(identityno);
				accident.setDriverName(name);
				accident.setDriverMobile(mobile);
				accident.setDriverVehicleNumber(carno);
				accident.setDriverVehicleType(cartype);
				accident.setPermissionDrive(drivingtype);
				accident.setCoId(insucompany);
				accident.setDriverDirection(dict);
				accident.setAcciProvince(p_provID);
				accident.setAcciCity(p_cityID);
				accident.setAcciDistrict(p_distID);
				accident.setAcciStreet(p_address);
				accident.setDriverLiability(resp);
				accident.setGroupId(groupID);
				accident.setAccidentNotes(accidentNotes);
				accidentList.add(accident);
				String sex = "";
				Date birthDay = null;
				try {
					birthDay = StringUtils.getBirthdayFromIdCard(identityno);
					String temp = StringUtils.getGenderFromIdCard(identityno);
					if ("M".equals(temp)) {
						sex = "0";
					} else {
						sex = "1";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				accident.setDriverSex(sex);
				accident.setBirthDay(birthDay);

			}
			String caseId = this.saveCase(qpTTPCase, accidentList);

			if (StringUtils.isEmptyStr(caseId)) {
				jsonObject.put("code", "0");
				jsonObject.put("message", "上传失败！");
			} else {
				jsonObject.put("code", "1");
				jsonObject.put("caseId", caseId);
				jsonObject.put("message", "上传成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", "0");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	public String saveCase(QpTTPCase qpTTPCase, List<QpTICAccident> accidentList) {
		// TODO Auto-generated method stub
		// Map<String, String> msg = new HashMap<String, String>();
		String caseId = "";
		try {
			// qpTTPCase.setCaseTime(new DateTime(dealTime,
			// DateTime.YEAR_TO_MINUTE));
			qpTTPCase.setTpLoginId("admin");
			qpTTPCase.setUpdaterCode("admin");
			qpTTPCase.setOperateTimeForHis(new Date());
			qpTTPCase.setAssistorId("admin");
			qpTTPCase.setAssistorName("admin");
			qpTTPCase.setTpHandleTime(new Date());
			qpTTPCase.setTpHandleStatus(CaseHandleStatusEnum.FOR_JUDGE
					.getCode());
			qpTTPCase.setCaseType("0");
			// 暂时默认
			qpTTPCase.setCenterId("26");
			// 生成认字编号
			if (ToolsUtils.isEmpty(qpTTPCase.getCaseSerialNo())) {
				QpTTPFastCenterId fastCenterId = new QpTTPFastCenterId();
				fastCenterId.setCenterId("26");// 暂时默认
				QpTTPFastCenter fastCenter = qpTTPFastCenterService
						.findQpTTPFastCenterByPK(fastCenterId);
				if (fastCenter != null) {
					String serinalNo = qpTTPCaseService
							.generateSerinalNo(fastCenter.getReadNum());
					if (ToolsUtils.notEmpty(serinalNo)) {
						qpTTPCase.setCaseSerialNo(serinalNo);
					}
				}
			}
			qpTTPCase = qpTTPCaseService.saveOrupdateQpTTPCase(qpTTPCase);
			// System.out.println("案件编号==="+qpTTPCase.getCaseId());
			System.out.println("认字编号===" + qpTTPCase.getCaseSerialNo());
			for (QpTICAccident qpTICAccident : accidentList) {
				qpTICAccident.setAcciTime(new DateTime(qpTTPCase.getCaseTime(),
						DateTime.YEAR_TO_MINUTE));
				qpTICAccident.setUpdaterCode("admin");
				qpTICAccidentService
						.saveQpTICAccident(qpTTPCase, qpTICAccident);
			}
			// 保存成功后产生一个交警定责任务
			WxTask task = new WxTask();
			task.setTaskId(0);
			task.setCaseId(qpTTPCase.getCaseId());
			task.setCreateTime(new Date());
			task.setCreateCode("admin");
			task.setCreateName("admin");
			task.setStatus("1");
			task.setRegFrom("1");
			wxTaskService.addWxTask(task);

			// 通知交警
			// List<String> arr=new ArrayList<String>();
			List<UmTUserRole> userRoles = umTRoleService
					.findRoleByRoleCode("REMOTE-FEE-POLICE");
			String[] arr = null;
			if (!StringUtils.isEmptyStr(userRoles)) {
				arr = new String[userRoles.size() + 1];
				for (int i = 0; i < userRoles.size(); i++) {
					arr[i] = userRoles.get(i).getUserCode();
				}
				arr[userRoles.size()] = "admin";
				logger.info("存在远程定责警察");
				logger.info("task:" + net.sf.json.JSONObject.fromObject(task));
				logger.info("通知人:");
				for (String string : arr) {
					logger.info(string);
				}
			} else {
				arr = new String[1];
				arr[0] = "admin";
				logger.info("不存在远程定责警察");
				logger.info("task:" + net.sf.json.JSONObject.fromObject(task));
				logger.info("通知admin");
			}

			dwrPush.doPush("prepareAll", "testCall", "有新定责任务需要处理！", arr);

			caseId = qpTTPCase.getCaseId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return caseId;
	}

	public static void main(String[] args) {
		//SaveCaseForeignImpl fo = new SaveCaseForeignImpl();
		String json = "";
		CaseInfo caseInfo = new CaseInfo();
		caseInfo.setCasedate("2016-07-14 11:10:10");
		caseInfo.setProvID("430000");
		caseInfo.setCityID("430100");
		caseInfo.setDistID("430103");
		caseInfo.setAddress("雨花路1");
		caseInfo.setWeather("1");

		List<PersonInfo> personInfos = new ArrayList<PersonInfo>();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setIdentityno("431003199210050630");
		personInfo.setName("张三");
		personInfo.setMobile("13800000001");
		personInfo.setCarno("湘A12345");
		personInfo.setCartype("9");
		personInfo.setDrivingtype("C1");
		personInfo.setInsucompany("6");
		personInfo.setDict("2");
		personInfo.setProvID("430000");
		personInfo.setCityID("430100");
		personInfo.setDistID("430103");
		personInfo.setAddress("雨花路2");
		personInfo.setResp("2");
		personInfo.setGroupID("G201603243509");

		personInfos.add(personInfo);

		PersonInfo personInfo1 = new PersonInfo();
		personInfo1.setIdentityno("420621199110146013");
		personInfo1.setName("张三");
		personInfo1.setMobile("13800000002");
		personInfo1.setCarno("湘A12366");
		personInfo1.setCartype("9");
		personInfo1.setDrivingtype("C1");
		personInfo1.setInsucompany("6");
		personInfo1.setDict("2");
		personInfo1.setProvID("430000");
		personInfo1.setCityID("430100");
		personInfo1.setDistID("430103");
		personInfo1.setAddress("雨花路3");
		personInfo1.setResp("2");
		personInfo1.setGroupID("");
		personInfos.add(personInfo1);
		CaseAllData all = new CaseAllData();
		all.setCaseInfo(caseInfo);
		all.setPersonInfos(personInfos);
		json = JSON.toJSONString(all);
		System.out.println(json);
		// fo.uploadCase(json);
	}

	@Override
	public String getProvince() {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		try {
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			List<QpTCOMProvince> qpTCOMProvinceList = qpTCOMProvinceService
					.findByQpTCOMProvince(qpTCOMProvince);
			JSONArray provinces = new JSONArray();
			for (QpTCOMProvince province : qpTCOMProvinceList) {
				JSONObject prov = new JSONObject();
				prov.put("code", province.getProvId());
				prov.put("name", province.getProvName());
				provinces.add(prov);
			}
			jsonObject.put("state", "0");
			jsonObject.put("msg", "查询成功");
			jsonObject.put("data", provinces);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("state", "01");
			jsonObject.put("msg", "查询异常");
			jsonObject.put("data", null);
		}
		return jsonObject.toString();
	}

	@Override
	public String getCity(String json) {
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(json);
			if (jsonObject.containsKey("provID")
					&& !StringUtils.isEmptyStr(jsonObject.getString("provID"))) {
				QpTCOMCity qpTCOMCity = new QpTCOMCity();
				qpTCOMCity.setProvId(jsonObject.getString("provID"));
				List<QpTCOMCity> qpTCOMCityList = qpTCOMCityService
						.findByQpTCOMCity(qpTCOMCity);
				JSONArray citys = new JSONArray();
				for (QpTCOMCity city : qpTCOMCityList) {
					JSONObject c = new JSONObject();
					c.put("code", city.getCityId());
					c.put("name", city.getCityName());
					citys.add(c);
				}
				result.put("state", "0");
				result.put("msg", "查询成功");
				result.put("data", citys);
			} else {
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public String getDist(String json) {
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(json);
			if (jsonObject.containsKey("cityID")
					&& !StringUtils.isEmptyStr(jsonObject.getString("cityID"))) {
				QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
				qpTCOMDistrict.setCityId(jsonObject.getString("cityID"));
				qpTCOMDistrict.setType("0");
				List<QpTCOMDistrict> qpTCOMDistrictList = qpTCOMDistrictService
						.findByQpTCOMDistrict(qpTCOMDistrict);
				JSONArray dists = new JSONArray();
				for (QpTCOMDistrict dist : qpTCOMDistrictList) {
					JSONObject d = new JSONObject();
					d.put("code", dist.getDistrictId());
					d.put("name", dist.getDistrictName());
					dists.add(d);
				}
				result.put("state", "0");
				result.put("msg", "查询成功");
				result.put("data", dists);
			} else {
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public String getCompany() {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		try {
			List<QpTICCompany> qpTICCompanyList = qpTICCompanyService
					.findByQpTICCompany(new QpTICCompany());
			JSONArray companys = new JSONArray();
			for (QpTICCompany company : qpTICCompanyList) {
				JSONObject comp = new JSONObject();
				comp.put("code", company.getCoId());
				comp.put("name", company.getCoName());
				companys.add(comp);
			}
			jsonObject.put("state", "0");
			jsonObject.put("msg", "查询成功");
			jsonObject.put("data", companys);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("state", "01");
			jsonObject.put("msg", "查询异常");
			jsonObject.put("data", null);
		}
		return jsonObject.toString();
	}

	@Override
	public String getCaseInfo(String json) {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			jsonObject = net.sf.json.JSONObject.fromObject(json);
			String caseId = jsonObject.getString("caseID");
			if (StringUtils.isEmptyStr(caseId)) {
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", "");
				return result.toString();
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			if (StringUtils.isEmptyStr(qpTTPCase)) {
				result.put("state", "0");
				result.put("msg", "案件不存在");
				result.put("data", "");
				return result.toString();
			}
			// Date caseTime = null;
			// try {
			// caseTime = DateUtil.parseToFormatDate(dateStr,
			// formatStr);"yyyy-MM-dd HH:mm:ss", qpTTPCase.getInsertTimeForHis()
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			CaseInfo caseInfo = new CaseInfo();
			caseInfo.setAddress(qpTTPCase.getCaseStreet());
			caseInfo.setCasedate(DateUtil.parseToFormatString(
					qpTTPCase.getCaseTime(), DateUtil.FULL_DATE_STR));
			caseInfo.setCityID(qpTTPCase.getCaseCity());
			caseInfo.setDistID(qpTTPCase.getCaseDistrict());
			caseInfo.setProvID(qpTTPCase.getCaseProvince());
			caseInfo.setWeather(qpTTPCase.getCaseWeather());
			Integer status = Integer.parseInt(qpTTPCase.getTpHandleStatus());
			// 1已定责,0未定责
			caseInfo.setIsDue(status > 1 ? "1" : "0");
			if (status > 1) {
				StringBuffer buffer1 = new StringBuffer();
				buffer1.append(System.getProperty("webapp.root"))
						.append("printTemplate").append(File.separator)
						.append("wxjj_report_sc.jasper");
				File reportFile = new File(buffer1.toString());
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("reportTitle", "This is a client report");
				JRDataSource dataSource = qpTTPCaseService
						.getDataSourcePrint(qpTTPCase);
//				byte[] bytes = JasperRunManager.runReportToPdf(
//						reportFile.getPath(), parameters, dataSource);
				//
				String policyCode = qpTTPCase.getPoliceEmployeeId();
				String outPdfPath =  SealUtil.csWechatStampedSeal(reportFile.getPath(), parameters, dataSource, caseId,policyCode);
//				String rootPath = Constants.getSEAL_ROOTPATH();
//				String time = DateUtil.parseToFormatString(new Date(),DateUtil.DATE_FORMAT_YYYYMMDD);
//				File rootdir = new File(rootPath);
//				if (!rootdir.exists()) {
//					rootdir.mkdirs();
//				}
//				File savedir = new File(rootPath+"/"+time);
//				if (!savedir.exists()) {
//					savedir.mkdirs();
//				}
//				String uuid = UuidUtil.get32UUID();
//				String destFileName = rootPath+"/"+time+"/"+uuid+".pdf";
//				JasperRunManager.runReportToPdfFile(reportFile.getPath(), destFileName, parameters,dataSource);
//				logger.info("案件号：" + caseId+"开始加盖印章");
//				String outPdfPath = qpTTPCaseService.stampedSeal(destFileName);

				PDDocument doc = PDDocument.load(new File(outPdfPath));
				
				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				StringBuffer buffer2 = new StringBuffer();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImageWithDPI(i, 96);
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					ImageIO.write(image, "png", bao);
					byte[] pngBytes = bao.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String imgStr = encoder.encode(pngBytes);
					buffer2.append("data:image/png;base64,");
					buffer2.append(imgStr);
					if (i < pageCount - 1) {
						buffer2.append(";");
					}
				}
				caseInfo.setResultPicture(buffer2.toString().replaceAll("\r\n", ""));
			} else {
				caseInfo.setResultPicture("");
			}
			List<QpTICAccident> qpTICAccidents = qpTICAccidentService
					.findQpTICAccidentInfoOnly(caseId);
			List<PersonInfo> personInfos = new ArrayList<PersonInfo>();
			for (QpTICAccident qpTICAccident : qpTICAccidents) {
				PersonInfo personInfo = new PersonInfo();
				personInfo.setIdentityno(qpTICAccident.getDriverIDNumber());
				personInfo.setName(qpTICAccident.getDriverName());
				personInfo.setMobile(qpTICAccident.getDriverMobile());
				personInfo.setCarno(qpTICAccident.getDriverVehicleNumber());
				personInfo.setCartype(qpTICAccident.getDriverVehicleType());
				personInfo.setDrivingtype(qpTICAccident.getPermissionDrive());
				personInfo.setInsucompany(qpTICAccident.getCoId());
				personInfo.setDict(qpTICAccident.getDriverDirection());
				personInfo.setProvID(qpTICAccident.getAcciProvince());
				personInfo.setCityID(qpTICAccident.getAcciCity());
				personInfo.setDistID(qpTICAccident.getAcciDistrict());
				personInfo.setAddress(qpTICAccident.getAcciStreet());
				personInfo.setResp(qpTICAccident.getDriverLiability());
				personInfo.setGroupID(qpTICAccident.getGroupId());
				personInfos.add(personInfo);
			}
			net.sf.json.JSONObject data = new net.sf.json.JSONObject();
			data.put("case", caseInfo);
			data.put("accidents", personInfos);
			result.put("state", "0");
			result.put("msg", "案件不存在");
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "01");
			result.put("msg", "参数错误");
			result.put("data", "");
		}
		return result.toString();
	}
	
	
	@Override
	public String getCaseInfoPic(String json) {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			jsonObject = net.sf.json.JSONObject.fromObject(json);
			String caseId = jsonObject.getString("caseID");
			if (StringUtils.isEmptyStr(caseId)) {
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", "");
				return result.toString();
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			if (StringUtils.isEmptyStr(qpTTPCase)) {
				result.put("state", "0");
				result.put("msg", "案件不存在");
				result.put("data", "");
				return result.toString();
			}
			
			
			Integer status = Integer.parseInt(qpTTPCase.getTpHandleStatus());
			// 1已定责,0未定责
			if (status > 1) {
				StringBuffer buffer1 = new StringBuffer();
				buffer1.append(System.getProperty("webapp.root"))
						.append("printTemplate").append(File.separator)
						.append("wxjj_report_sc.jasper");
				File reportFile = new File(buffer1.toString());
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("reportTitle", "This is a client report");
				JRDataSource dataSource = qpTTPCaseService.getDataSourcePrint(qpTTPCase);
				String policyCode = qpTTPCase.getPoliceEmployeeId();
				String outPdfPath =  SealUtil.csWechatStampedSeal(reportFile.getPath(), parameters, dataSource, caseId,policyCode);

				PDDocument doc = PDDocument.load(new File(outPdfPath));
				
				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				StringBuffer buffer2 = new StringBuffer();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImageWithDPI(i, 96);
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					ImageIO.write(image, "png", bao);
					byte[] pngBytes = bao.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String imgStr = encoder.encode(pngBytes);
					buffer2.append("data:image/png;base64,");
					buffer2.append(imgStr);
					if (i < pageCount - 1) {
						buffer2.append(";");
					}
				}
				result.put("state", "0");
				result.put("msg", "查询成功");
				result.put("data", buffer2.toString().replaceAll("\r\n", ""));
			} else {
				result.put("state", "01");
				result.put("msg", "案件暂无定责");
			}
			
		} catch (Exception e) {
			logger.error("获取定责图片出错,请示后在试",e);
			result.put("state", "01");
			result.put("msg", "查询失败,请稍后在试");
		}
		return result.toString();
	}
	
	@Override
	public String getLoseInfoPic(String json) {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			jsonObject = net.sf.json.JSONObject.fromObject(json);
			String caseId = jsonObject.getString("caseID");
			String accId = jsonObject.getString("accID");
			if (StringUtils.isEmptyStr(caseId)) {
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", "");
				return result.toString();
			}
			QpTTPCaseId id = new QpTTPCaseId();
			id.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(id);
			if (StringUtils.isEmptyStr(qpTTPCase)) {
				result.put("state", "0");
				result.put("msg", "案件不存在");
				result.put("data", "");
				return result.toString();
			}
			
			Integer status = Integer.parseInt(qpTTPCase.getTpHandleStatus());
			// 1已定责,0未定责
			if (status > 1) {
				StringBuffer buffer1 = new StringBuffer();
				buffer1.append(System.getProperty("webapp.root"))
				.append("printTemplate").append(File.separator)
				.append("report7.jasper");
				File reportFile = new File(buffer1.toString());
				
				String imgUrl = System.getProperty("webapp.root")+"/printTemplate/604421331541812454.jpg";
				String templateUrl = System.getProperty("webapp.root") + "/printTemplate/report7.jasper";
				boolean lossStampIsPrint = true;//是否打印各快赔点印章，只是图片，无法律效应
				//盖章图片路径无法律效应
				String lossStampUrl = System.getProperty("webapp.root") + "/printTemplate/stamp/"+qpTTPCase.getCenterId()+".PNG";
				JSONObject lossSourceJson = wxCaseService.getLossSourceJson(caseId, accId, imgUrl, templateUrl, true,lossStampUrl,lossStampIsPrint);
				
				JRDataSource dataSource = lossSourceJson.getObject("dataSource", JRDataSource.class);
				String policyCode = qpTTPCase.getPoliceEmployeeId();
				JSONObject params = lossSourceJson.getJSONObject("params");
				String outPdfPath =  SealUtil.lossInfoStampedSeal(reportFile.getPath(),params , dataSource, caseId,policyCode);
				
				PDDocument doc = PDDocument.load(new File(outPdfPath));
				
				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				StringBuffer buffer2 = new StringBuffer();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImageWithDPI(i, 96);
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					ImageIO.write(image, "png", bao);
					byte[] pngBytes = bao.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String imgStr = encoder.encode(pngBytes);
					buffer2.append("data:image/png;base64,");
					buffer2.append(imgStr);
					if (i < pageCount - 1) {
						buffer2.append(";");
					}
				}
				result.put("state", "0");
				result.put("msg", "查询成功");
				result.put("data", buffer2.toString().replaceAll("\r\n", ""));
			} else {
				result.put("state", "01");
				result.put("msg", "案件暂无定责");
			}
			
		} catch (Exception e) {
			logger.error("获取定损清单图片出错,请示后在试",e);
			result.put("state", "01");
			result.put("msg", "查询失败,请稍后在试");
		}
		return result.toString();
	}

	@Override
	public String getWeatherType() {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		try {
			// 天气情况
			List<QpSelectDataVo> weatherList = qpTCommonService.getSelectData("WeatherType");
			System.out.println("天气情况:" + JSONArray.fromObject(weatherList));
			jsonObject.put("state", "0");
			jsonObject.put("msg", "查询成功");
			jsonObject.put("data", JSONArray.fromObject(weatherList));
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("state", "01");
			jsonObject.put("msg", "查询异常");
			jsonObject.put("data", null);
		}
		return jsonObject.toString();
	}

	@Override
	public String getGroupID(String json) {
		JsonResult jsonResult = new JsonResult();
		String groupID = qpTICPictureService.getGroupID();
		return jsonResult.setJsonResult(CommonEnum.SUCCESS, groupID).toJsonString();
	}

}
