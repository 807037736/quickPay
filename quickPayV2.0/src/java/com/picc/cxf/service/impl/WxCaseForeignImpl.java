package com.picc.cxf.service.impl;

import ins.framework.common.Page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.picc.common.utils.StringUtils;
import com.picc.common.web.DwrPush;
import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.cxf.service.WxCaseForeign;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;
import com.picc.qp.schema.model.QpTIcCompanyGarage;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetail;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetailShow;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxCaseId;
import com.picc.qp.schema.model.WxTask;
import com.picc.qp.schema.vo.Pair;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTICPictureGroupService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageDetailService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.wx.facade.WxAccidentService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.service.wx.facade.WxTaskService;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;
import com.picc.qp.util.SmsUtil;
import com.picc.qp.web.QpTIcCompanyGarageAction;
import com.picc.qp.web.QpTIcCompanyGarageAction.MyCompartor;
import com.picc.um.schema.model.UmTAccidentUser;
import com.picc.um.schema.model.UmTUserBound;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.service.facade.IUmTAccidentUserService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTUserBoundService;
@WebService (endpointInterface = "com.picc.cxf.service.WxCaseForeign")
public class WxCaseForeignImpl implements WxCaseForeign {

	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WxCaseService wxCaseService;

	@Autowired
	private IUmTUserBoundService umTUserBoundService;

	@Autowired
	private IQpTICAccidentService qpTICAccidentService;

	@Autowired
	private IQpTICPictureGroupService qpTICPictureGroupService;

	@Autowired
	private IQpTICPictureService qpTICPictureService;

	@Autowired
	private IQpTTPCaseService qpTTPCaseService;	

	@Autowired
	private IQpTCommonService qpTCommonService;	

	@Autowired
	private IQpTICCompanyService qpTICCompanyService;	

	@Autowired
	private IQpTTPLawService qpTTPLawService;	

	@Autowired
	private IQpTIcCompanyGarageDetailService qpTIcCompanyGarageDetailService;

	@Autowired
	private IQpTIcCompanyGarageService qpTIcCompanyGarageService;

	@Autowired
	private WxAccidentService wxAccidentService;

	@Autowired
	private WxTaskService wxTaskService;

	@Autowired
	private DwrPush dwrPush;

	@Autowired
	private IUmTRoleService umTRoleService;
	
	@Autowired
	private IUmTAccidentUserService umTAccidentUserService;

	@Override
	public String findWxCaseByWxCaseId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", "");
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(StringUtils.isEmptyStr(jsonObject)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", "");
				return result.toString();
			}
			if(!jsonObject.containsKey("wxCaseId") || StringUtils.isEmptyStr(jsonObject.getString("wxCaseId"))){
				result.put("state", "01");
				result.put("msg", "参数错误(wxCaseId)");
				result.put("data", "");
				return result.toString();
			}
			String wxCaseId = jsonObject.getString("wxCaseId");
			WxCaseId wxCaseIdVo = new WxCaseId();
			wxCaseIdVo.setId(wxCaseId);
			WxCase wxCase = wxCaseService.findById(wxCaseIdVo);
			result.put("state", "00");
			result.put("msg", "查询成功");
			result.put("data", wxCase);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", "");
			return result.toString();
		}
	}


	@Override
	public String updateWxCase(String json) {
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			JSONObject param = JSONObject.fromObject(json);
			//	    WxCase wxCase = JSON.parseObject(param.getString("data"), WxCase.class);
			if(StringUtils.isEmptyStr(param) || param.size() <= 0){
				result.put("state", "01");
				result.put("msg", "参数错误");
				result.put("data", "");
				return result.toString();
			}
			WxCase wxCase = (WxCase)JSONObject.toBean(JSONObject.fromObject(param.getString("data")), WxCase.class);
			wxCaseService.save(wxCase);
			result.put("state", "00");
			result.put("msg", "修改成功");
			result.put("data", wxCase);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "修改异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String SaveWxCase(String json) {
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			JSONObject param = JSONObject.fromObject(json);
			if(!param.containsKey("groupId") || StringUtils.isEmptyStr(param.getString("groupId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(groupId)");
				result.put("data", null);
				return result.toString();
			}
			if(!param.containsKey("userCode") || StringUtils.isEmptyStr(param.getString("userCode"))){
				result.put("state", "01");
				result.put("msg", "参数为空(userCode)");
				result.put("data", null);
				return result.toString();
			}
			String groupId = param.getString("groupId");
			String userCode = param.getString("userCode");
			//	    WxCase wxCase = JSON.parseObject(param.getString("data"), WxCase.class);
			//	    WxCase wxCase = (WxCase)JSONObject.toBean(JSONObject.fromObject(param.getString("data")), WxCase.class);
			String wxCaseId = wxCaseService.getWxCaseId();
			WxCaseId wxCaseIdVo = new WxCaseId();
			wxCaseIdVo.setId(wxCaseId);
			WxCase wxCase = new WxCase();
			wxCase.setId(wxCaseIdVo);
			wxCase.setGroupid(groupId);
			wxCase.setCreatecode(userCode);
			wxCase.setStatus("1");
			wxCase.setCreatedate(new Date());
			wxCaseService.save(wxCase);
			result.put("state", "00");
			result.put("msg", "保存成功");
			result.put("data", wxCaseId);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "保存异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String isCKUser(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject =JSONObject.fromObject(json);
			if(!jsonObject.containsKey("openId") || StringUtils.isEmptyStr(jsonObject.getString("openId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(openId)");
				result.put("data", null);
				return result.toString();
			}
			String openId = jsonObject.getString("openId");
			UmTUserBound umTUserBound = new UmTUserBound();
			umTUserBound.setWxUserCode(openId);
			umTUserBound = umTUserBoundService.isCKUser(umTUserBound);
			jsonObject.put("state", "00");
			jsonObject.put("msg", "查询成功");
			JSONObject resultData = new JSONObject();
			if(null != umTUserBound){
				resultData.put("ckUserCode", umTUserBound.getCkUserCode());
				resultData.put("ckUserName", umTUserBound.getCkUserName());
				resultData.put("wxUserCode", umTUserBound.getWxUserCode());
				resultData.put("wxUserName", umTUserBound.getWxUserName());
			}
			jsonObject.put("data", resultData);
			return jsonObject.toString();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("调用是否是查勘员接口异常" + e);
			jsonObject.put("state", "01");
			jsonObject.put("msg", "查询异常");
			jsonObject.put("data", null);
			return jsonObject.toString();
		}
	}


	@Override
	public String findCaseByCaseId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("caseId") || StringUtils.isEmptyStr(jsonObject.getString("caseId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(caseId)");
				result.put("data", null);
				return result.toString();
			}
			String caseId = jsonObject.getString("caseId");
			QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
			qpTTPCaseId.setCaseId(caseId);
			QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
			result.put("state", "0");
			result.put("msg", "查询成功");
			result.put("data", qpTTPCase);
			return jsonObject.toString();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("调用案件ID(caseId)查询真实案件异常" + e);
			jsonObject.put("state", "01");
			jsonObject.put("msg", "查询异常");
			jsonObject.put("data", null);
			return jsonObject.toString();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public String findCaseByCaseSerialNo(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("caseSerialNo") || StringUtils.isEmptyStr(jsonObject.getString("caseSerialNo"))){
				result.put("state", "01");
				result.put("msg", "参数为空(caseSerialNo)");
				result.put("data", null);
				return result.toString();
			}
			String caseSerialNo = jsonObject.getString("caseSerialNo");
			QpTTPCase qpTTPCase = new QpTTPCase();
			qpTTPCase.setCaseSerialNo(caseSerialNo);
			Page resultPage = qpTTPCaseService.findQpTTPCasePageBySql(qpTTPCase, 1, 10);

			if(resultPage.getTotalCount() < 1){
				result.put("state", "00");
				result.put("data", "");
				result.put("msg", "案件信息不存在");
			}else{
				List<QpTTPCase> qpTTPCases = resultPage.getResult();
				qpTTPCase = qpTTPCases.get(0);
				if(StringUtils.isEmptyStr(qpTTPCase)){
					result.put("state", "00");
					result.put("data", "");
					result.put("msg", "案件信息不存在");
				}else{
					result.put("state", "00");
					result.put("msg", "查询成功");
					result.put("data", qpTTPCase);
				}
			}
			return result.toString();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("调用认字编号(caseSerialNo)查询真实案件异常" + e);
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String findAccidentByCaseId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("caseId") || StringUtils.isEmptyStr(jsonObject.getString("caseId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(caseId)");
				result.put("data", null);
				return result.toString();
			}
			String caseId = jsonObject.getString("caseId");
			List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
			JSONArray array = new JSONArray();
			for (QpTICAccident qpTICAccident : qpTICAccidents) {
				JSONObject o = new JSONObject();
				o.put("acciId", qpTICAccident.getAcciId());
				o.put("driverName", qpTICAccident.getDriverName());
				o.put("driverPhone", qpTICAccident.getDriverPhone());
				o.put("driverVehicleNumber", qpTICAccident.getDriverVehicleNumber());
				o.put("caseId", qpTICAccident.getCaseId());
				array.add(o);
			}
			result.put("state", "00");
			result.put("msg", "查询成功");
			result.put("data", array);
			return result.toString();
		}catch(Exception e){
			logger.error("调用案件ID查询当事人信息异常" + e);
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String findAccidentByAccId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("acciId") || StringUtils.isEmptyStr(jsonObject.getString("acciId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(acciId)");
				result.put("data", null);
				return result.toString();
			}
			String acciId = jsonObject.getString("acciId");
			QpTICAccidentId id = new QpTICAccidentId();
			id.setAcciId(acciId);
			QpTICAccident qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
			JSONObject jsonData = new JSONObject();
			jsonData.put("acciId", qpTICAccident.getAcciId());
			jsonData.put("caseId", qpTICAccident.getCaseId());
			jsonData.put("driverName", qpTICAccident.getDriverName());
			jsonData.put("driverMobile", qpTICAccident.getDriverMobile());
			jsonData.put("surveyGroupId", qpTICAccident.getSurveyGroupId());
			jsonData.put("groupId", qpTICAccident.getGroupId());
			result.put("state", "00");
			result.put("msg", "查询成功");
			result.put("data", jsonData);
			return result.toString();
		}catch(Exception e){
			logger.error("调用当事人ID查询当事人信息异常" + e);
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String findPictrueGroupByGroupId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("groupId") || StringUtils.isEmptyStr(jsonObject.getString("groupId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(groupId)");
				result.put("data", null);
				return result.toString();
			}
			String groupId = jsonObject.getString("groupId");
			QpTICPictureGroupId ticPictureGroupId = new QpTICPictureGroupId();
			ticPictureGroupId.setGroupId(groupId);
			QpTICPictureGroup ticPictureGroupUpdate = qpTICPictureGroupService.findQpTICPictureGroupByPK(ticPictureGroupId);
			result.put("state", "00");
			result.put("msg", "查询成功");
			result.put("data", ticPictureGroupUpdate);
			return result.toString();
		}catch(Exception e){
			logger.error("调用groupId查询图片组(QpTICPictureGroup)信息异常" + e);
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			return result.toString();
		}
	}

	@Override
	public String findPictrueByGroupId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", null);
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(!jsonObject.containsKey("groupId") || StringUtils.isEmptyStr(jsonObject.getString("groupId"))){
				result.put("state", "01");
				result.put("msg", "参数为空(groupId)");
				result.put("data", null);
				return result.toString();
			}
			String groupId = jsonObject.getString("groupId");
			List<QpTICPicture> qpTICPictures =  qpTICPictureService.findQpTICPictureByGroupId(groupId);
			//微信查勘员上传图片时 查看图片
			List<QpTICPicture> filePathList = new ArrayList<QpTICPicture>();
			if (qpTICPictures != null && qpTICPictures.size() > 0) {

				for (int i = 0; i < qpTICPictures.size(); i++) {
					QpTICPicture ticPicture = qpTICPictures.get(i);
					try {
						QpTICPicture qpTICPicturet = new QpTICPicture();
						qpTICPicturet.setFileName(ticPicture.getFileName());
						qpTICPicturet.setPicDesc(ticPicture.getPicDesc()); // 图片预览用到的文件名
						qpTICPicturet.setPicOrder(ticPicture.getId().getPicId());
						filePathList.add(qpTICPicturet);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("微信查勘员上传图片时查看图片出现异常", e);
					}


				}
			}
			result.put("state", "00");
			result.put("msg", "查询成功");
			result.put("data", filePathList);
			return result.toString();
		}catch(Exception e){
			logger.error("调用groupId查询图片信息异常" , e);
			result.put("state", "01");
			result.put("msg", "查询异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String addPictrueGroup(String json) {
		System.out.println("addpictrueGroup8083");
		JSONObject param = new JSONObject();
		JSONObject result = new JSONObject();
		QpTICPictureGroup qpTICPictureGroupSave = new QpTICPictureGroup();
		try {
			try {
				if(!StringUtils.isEmptyStr(json)){
					param = JSONObject.fromObject(json);
					if(!StringUtils.isEmptyStr(param) && param.size() > 0){
						System.out.println("addPictrueGroup:" + param.getString("data"));
						qpTICPictureGroupSave = (QpTICPictureGroup)JSONObject.toBean(JSONObject.fromObject(param.getString("data")), QpTICPictureGroup.class);
						//			    qpTICPictureGroupSave = JSON.parseObject(param.getString("data"), QpTICPictureGroup.class);
						if(!StringUtils.isEmptyStr(qpTICPictureGroupSave)){
							qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
							result.put("state", "00");
							result.put("msg", "修改成功");
							result.put("data", null);
							return result.toString();
						}else {
							result.put("state", "01");
							result.put("msg", "参数有误");
							result.put("data", null);
							return result.toString();
						}
					}

				}
			} catch (Exception e) {
				logger.error("修改图片组失败" + e);
				result.put("state", "01");
				result.put("msg", "修改异常");
				result.put("data", null);
				return result.toString();
			}

			String groupId = qpTICPictureService.getGroupID();
			QpTICPictureGroupId qpTICPictureGroupId = new QpTICPictureGroupId();
			Date currentDate = new Date();
			qpTICPictureGroupId.setGroupId(groupId);

			qpTICPictureGroupSave.setId(qpTICPictureGroupId);

			qpTICPictureGroupSave.setCreatorCode("jiekou");
			qpTICPictureGroupSave.setInsertTimeForHis(currentDate);
			qpTICPictureGroupSave.setUploadTimeForHis(currentDate);
			qpTICPictureGroupSave.setUploadUserCode("jiekou");
			qpTICPictureGroupSave.setValidStatus("0");

			qpTICPictureGroupSave.setPictureType("01");
			qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
			result.put("state", "00");
			result.put("msg", "添加成功");
			result.put("data", groupId);
			return result.toString();
		} catch (Exception e) {
			logger.error("调用添加图片组(QpTICPictureGroup)异常" + e);
			result.put("state", "01");
			result.put("msg", "添加异常");
			result.put("data", null);
			return result.toString();
		}
	}


	@Override
	public String savePictrue(String json) {
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			net.sf.json.JSONObject param = net.sf.json.JSONObject.fromObject(json);
			if (param.containsKey("imgurls")) {
				JSONArray imgs = param.getJSONArray("imgurls");
				if (imgs != null) {
					String groupId = param.getString("groupId");
					if(StringUtils.isNotEmpty(groupId)){
						QpTICPictureGroupId id = new QpTICPictureGroupId();
						id.setGroupId(groupId);
						QpTICPictureGroup qpTICPictureGroup = qpTICPictureGroupService.findQpTICPictureGroupByPK(id);
						if(StringUtils.isEmptyStr(qpTICPictureGroup)){
							result.put("state", "01");
							result.put("msg", "参数错误,图片为空");
							result.put("data", null);
							return result.toString();
						}
					}else {
						groupId = qpTICPictureService.getGroupID();
						QpTICPictureGroupId qpTICPictureGroupId = new QpTICPictureGroupId();
						qpTICPictureGroupId.setGroupId(groupId);
						QpTICPictureGroup qpTICPictureGroupSave = new QpTICPictureGroup();
						qpTICPictureGroupSave.setId(qpTICPictureGroupId);
						Date currentDate = new Date();
						qpTICPictureGroupSave.setInsertTimeForHis(currentDate);
						qpTICPictureGroupSave.setUploadTimeForHis(currentDate);
						qpTICPictureGroupSave.setValidStatus("1");
						qpTICPictureGroupSave.setPictureType("01");
						qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
					}


					for (int i = 0; i < imgs.size(); i++) {
						JSONObject img = imgs.getJSONObject(i);

						QpTICPicture qpTICPicture = new QpTICPicture();
						qpTICPicture.setFileName(img.getString("fileName"));
						qpTICPicture.setPicDesc(img.getString("name"));
						qpTICPicture.setGroupId(groupId);
						qpTICPicture.setOriginalFileName(img.getString("originalFileName"));
						qpTICPicture.setAcciId(param.containsKey("acciId")?param.getString("acciId"):"");
						qpTICPictureService.saveQpTICPicture(qpTICPicture, "interface");
					}
					result.put("state", "00");
					result.put("msg", "上传图片成功");
					result.put("data", groupId);
				} else {
					result.put("state", "01");
					result.put("msg", "参数错误,图片为空");
					result.put("data", null);
				}
			} else {
				result.put("state", "01");
				result.put("msg", "参数错误,图片为空");
				result.put("data", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "01");
			result.put("msg", "参数错误");
			result.put("data", null);
		}
		return result.toString();
	}

	private static JsonResult result = new JsonResult();

	/**
	 * 参数形式
	 * openID : 123
	 */
	@Override
	public String findCaseListByOpenID(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("openID") && StringUtils.isNotEmpty(jsonObject.getString("openID"))) {
					List<QpTTPCase> cases = qpTTPCaseService.findCaseByOpenID(jsonObject.getString("openID"));
					result.setJsonResult(CommonEnum.SUCCESS, cases);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findCaseByID(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") && StringUtils.isNotEmpty(jsonObject.getString("caseID"))) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					QpTTPCase queryCase = new QpTTPCase();
					queryCase.setCaseId(jsonObject.getString("caseID"));
					QpTTPCase case1 = qpTTPCaseService.findQpTTPCaseById(queryCase);
					resultMap.put("case", case1);
					QpTICAccident qpTICAccident = new QpTICAccident();
					qpTICAccident.setCaseId(jsonObject.getString("caseID"));
					List<QpTICAccident> accidents = qpTICAccidentService.findQpTICAccidentPageBySql(qpTICAccident, 1,Integer.MAX_VALUE).getResult();
					resultMap.put("accidents", accidents);
					result.setJsonResult(CommonEnum.SUCCESS, resultMap);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	@Override
	public String uploadImg(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("imgs") && StringUtils.isNotEmpty(jsonObject.getString("imgs"))
						&& jsonObject.containsKey("groupID") && StringUtils.isNotEmpty(jsonObject.getString("groupID"))) {
					List<QpTICPicture> savePics = new ArrayList<QpTICPicture>(); 
					JSONArray imgs = jsonObject.getJSONArray("imgs");
					// 参数检查
					for (int i = 0; i < imgs.size(); i++) {
						JSONObject img = imgs.getJSONObject(i);
						if (!img.containsKey("name") || StringUtils.isEmptyStr(img.getString("name"))
								|| !img.containsKey("url") || StringUtils.isEmptyStr(img.getString("url"))) {
							result.setJsonResult(CommonEnum.NONE_PARAM, null);
							return result.toJsonString();
						} else {
							QpTICPicture savePic = new QpTICPicture();
							savePic.setFileName(img.getString("url"));
							savePic.setGroupId(jsonObject.getString("groupID"));
							savePic.setOriginalFileName(img.getString("name"));
							savePics.add(savePic);
						}
					}
					// 插入记录
					qpTICPictureService.saveQpTICPictures(savePics, "interface");
					result.setJsonResult(CommonEnum.SUCCESS, null);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}



	@Override
	public String getImg(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("groupID") && StringUtils.isNotEmpty(jsonObject.getString("groupID"))) {
					List<QpTICPicture> pictures = qpTICPictureService.findQpTICPictureByGroupId(jsonObject.getString("groupID"));
					//					String root = System.getProperty("webapp.root") + "/pages/qp/qpticpicturegroup/images/temp";
					//					String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
					//					root = root + "/" + currentTime;
					//					File savedir = new File(root);
					//					if (!savedir.exists()) savedir.mkdirs();
					//					root = root + "/" + jsonObject.getString("groupID");
					//					File f = new File(root);
					//					if (!f.exists()) f.mkdir();
					//					
					//					for (QpTICPicture p : pictures) {
					//						String fileNamePath = p.getFileName();
					//						String fileName = fileNamePath.substring(fileNamePath.lastIndexOf("/"), fileNamePath.length());
					//						File file = new File(fileNamePath);
					//						if (!file.exists()) {
					//							logger.info("file:" + file + "不存在");
					//						} else {
					//							File file2 = new File(f + fileName);
					//							if (!file2.exists()) {
					//								FileInputStream fis = new FileInputStream(file);
					//								FileOutputStream fos = new FileOutputStream(f + fileName);
					//								byte[] buffer = new byte[1024];
					//								while (-1 != (fis.read(buffer, 0, buffer.length))) {
					//									fos.write(buffer);
					//								}
					//								fos.close();
					//								fis.close();
					//							}
					//							p.setFileName(Constants.getIMAGEHTTPPATH() + root.replace(System.getProperty("webapp.root"), "") + fileName);
					//						}
					//					}
					result.setJsonResult(CommonEnum.SUCCESS, pictures);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	@Override
	public String getCaseCommonInfo(String json) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			// 车辆类型
			List<QpSelectDataVo> vehicleTypeList = qpTCommonService.getSelectData(SelectTypeEnum.VEHICLE.getCode());
			resultMap.put("vehicleTypeList", vehicleTypeList);
			// 准驾车型
			List<QpSelectDataVo> permissionDriveTypeList = qpTCommonService.getSelectData(SelectTypeEnum.PERMISSION_DRIVE.getCode());
			resultMap.put("permissionDriveTypeList", permissionDriveTypeList);
			// 保险公司
			List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
			resultMap.put("qpTICCompanyList", qpTICCompanyList);
			// 违反法规
			List<QpTTPLaw> qpTTPLawList = qpTTPLawService.findByQpTTPLaw(new QpTTPLaw());
			resultMap.put("qpTTPLawList", qpTTPLawList);
			// 承担责任
			List<QpSelectDataVo> responsibilityTypeList = qpTCommonService.getSelectData(SelectTypeEnum.RESPONSIBILITY.getCode());
			resultMap.put("responsibilityTypeList", responsibilityTypeList);
			result.setJsonResult(CommonEnum.SUCCESS, resultMap);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}



	@SuppressWarnings("unchecked")
	@Override
	public String findCompanyGarageByLatAndLng(String json) {
		JSONObject result = new JSONObject();
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject param = JSONObject.fromObject(json);
				try {
					if(!param.containsKey("caseID")){
						result.put("code", -1);
						result.put("msg", "caseID不存在");
						return result.toString();
					}
					if(!param.containsKey("lat")){
						result.put("code", -1);
						result.put("msg", "lat不存在");
						return result.toString();
					}
					if(!param.containsKey("lng")){
						result.put("code", -1);
						result.put("msg", "lng不存在");
						return result.toString();
					}
					String caseId =  param.getString("caseID");
					String strLat =  param.getString("lat");
					String strLng =  param.getString("lng");
					if(StringUtils.isEmptyStr(caseId)){
						result.put("code", -1);
						result.put("msg", "caseID不存在");
						return result.toString();
					}
					if(StringUtils.isEmptyStr(strLat)){
						result.put("code", -1);
						result.put("msg", "lat不存在");
						return result.toString();
					}
					if(StringUtils.isEmptyStr(strLng)){
						result.put("code", -1);
						result.put("msg", "lng不存在");
						return result.toString();
					}
					double lat = 0d;
					double lng = 0d;
					try {
						lat = Double.valueOf(strLat);
						lng = Double.valueOf(strLng);
					} catch (Exception e) {
						result.put("code", -1);
						result.put("msg", "经纬度格式有误");
						return result.toString();
					}
					QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
					qpTTPCaseId.setCaseId(caseId);
					QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
					if(StringUtils.isEmptyStr(qpTTPCase)){
						result.put("code", -1);
						result.put("msg", "案件不存在");
						return result.toString();
					}

					List<QpTICAccident> accidents =  qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
					if(StringUtils.isEmptyStr(accidents) || accidents.size() <= 0){
						result.put("code", -1);
						result.put("msg", "该案件无当事人");
						return result.toString();
					}
					if(accidents.size() != 2){
						result.put("code", -1);
						result.put("msg", "暂时不支持2个当事人以外的案件查询");
						return result.toString();
					}
					//10 全部责任  11 主要责任  12次要责任  13同等责任 14无责任
					String coId = "";
					for (QpTICAccident qpTICAccident : accidents) {
						if("10".equals(qpTICAccident.getDriverLiability())){
							coId = qpTICAccident.getCoId();
							break;
						}
					}
					if(!StringUtils.isEmptyStr(coId)){
						//主要责任  或 全部责任      快赔点 
						QpTICCompanyId id = new QpTICCompanyId();
						id.setCoId(coId);
						QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(coId);
						List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
						if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
							qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
						}else{
							qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
						}

						for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
							qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
						}
						//Collections.sort(studentList,mc);     //按照age升序 22，23
						//Collections.reverse(studentList,mc);    //按照age降序 23,22  
						MyCompartor mc = new MyCompartor();
						Collections.sort(qpTIcCompanyGarageDetails, mc);

						List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(qpTIcCompanyGarageDetails);

						result.put("code", 0);
						result.put("msg", "查询成功");
						result.put("data", qpTIcCompanyGarageDetailShows.get(0));

					}else{
						//用来去掉重复
						Set<QpTIcCompanyGarageDetail> setQpTIcCompanyGarageDetails = new HashSet<QpTIcCompanyGarageDetail>();
						//同等责任
						for (QpTICAccident qpTICAccident : accidents) {
							QpTICCompanyId id = new QpTICCompanyId();
							id.setCoId(qpTICAccident.getCoId());
							QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(qpTICAccident.getCoId());
							List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
							if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
								qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
							}else{
								qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
							}

							logger.info("主要责任人快赔点:" + JSONArray.fromObject(qpTIcCompanyGarageDetails));
							for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
								qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
								setQpTIcCompanyGarageDetails.add(qpTIcCompanyGarageDetail);
							}

							//			    Collections.sort(studentList,mc);     //按照age升序 22，23
							//			    Collections.reverse(studentList,mc);    //按照age降序 23,22  
						}
						List<QpTIcCompanyGarageDetail> orderByQpTIcCompanyGarageDetails = new ArrayList<QpTIcCompanyGarageDetail>();
						orderByQpTIcCompanyGarageDetails.addAll(setQpTIcCompanyGarageDetails);
						MyCompartor mc = new MyCompartor();
						Collections.sort(orderByQpTIcCompanyGarageDetails, mc);

						List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(orderByQpTIcCompanyGarageDetails);


						result.put("code", 0);
						result.put("msg", "查询成功");
						result.put("data", qpTIcCompanyGarageDetailShows.get(0));
					}
					//分两种情况:
					//1: 只传caseId
					//2： 传caseId  和    经纬度
				} catch (Exception e) {
					result.put("code", -1);
					result.put("msg", "查询失败,请稍后在试");
					logger.error("获取修理厂地址失败", e);
				}
				return result.toString();

			} else {
				result.put("code", 10001);
				result.put("msg", "缺少参数");
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.put("code", 10003);
			result.put("msg", "参数类型错误");
		} catch (Exception e) {
			logger.error("", e);
			result.put("code", 10004);
			result.put("msg", "程序异常");
		}
		return result.toString();
	}


	@SuppressWarnings("unchecked")
	@Override
	public String findCompanyGarageByCaseId(String json) {
		JSONObject result = new JSONObject();
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject param = JSONObject.fromObject(json);
				try {
					if(!param.containsKey("caseID")){
						result.put("code", -1);
						result.put("msg", "caseID不存在");
						return result.toString();
					}
					String caseId =  param.getString("caseID");
					if(StringUtils.isEmptyStr(caseId)){
						result.put("code", -1);
						result.put("msg", "caseID不存在");
						return result.toString();
					}

					QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
					qpTTPCaseId.setCaseId(caseId);
					QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
					if(StringUtils.isEmptyStr(qpTTPCase)){
						result.put("code", -1);
						result.put("msg", "案件不存在");
						return result.toString();
					}

					List<QpTICAccident> accidents =  qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
					if(StringUtils.isEmptyStr(accidents) || accidents.size() <= 0){
						result.put("code", -1);
						result.put("msg", "该案件无当事人");
						return result.toString();
					}
					if(accidents.size() != 2){
						result.put("code", -1);
						result.put("msg", "暂时不支持2个当事人以外的案件查询");
						return result.toString();
					}
					//10 全部责任  11 主要责任  12次要责任  13同等责任 14无责任
					String coId = "";
					for (QpTICAccident qpTICAccident : accidents) {
						if("10".equals(qpTICAccident.getDriverLiability())){
							coId = qpTICAccident.getCoId();
							break;
						}
					}

					double lat = 0d;
					double lng  = 0d;
					if(StringUtils.isEmptyStr(qpTTPCase.getLatitude()) || StringUtils.isEmptyStr(qpTTPCase.getLongitude())){
						String address = qpTTPCase.getCaseStreet();
						if(StringUtils.isEmptyStr(address)){
							result.put("code", -1);
							result.put("msg", "案件地址不存在");
							result.put("data", null);
							return result.toString();
						}else{
							Map<String, Object> map = QpTIcCompanyGarageAction.getLatAndLngByAddress(address);
							if("200".equals(map.get("code"))){
								JSONObject resultData = JSONObject.fromObject(map.get("info"));
								if("0".equals(resultData.getString("status"))){
									lng = resultData.getJSONObject("result").getJSONObject("location").getDouble("lng");
									lat = resultData.getJSONObject("result").getJSONObject("location").getDouble("lat");

								}else{
									//查询失败
									logger.error("返回修理厂时，根据地址查询经纬度失败:" + resultData);
									result.put("code", -1);
									result.put("msg", "查询失败,请稍后在试(地址错误)");
									result.put("data", null);
									return result.toString();
								}
							}else{
								//失败
								//查询失败
								logger.error("返回修理厂时，根据地址查询经纬度失败:" + map);
								result.put("code", -1);
								result.put("msg", "查询失败,请稍后在试(地址错误)");
								result.put("data", null);
								return result.toString();
							}
						}
					}else{
						try {
							lat = Double.valueOf(qpTTPCase.getLatitude());
							lng = Double.valueOf(qpTTPCase.getLongitude());
						} catch (Exception e) {
							logger.error("经纬度格式转换错误:" , e);
							result.put("code", -1);
							result.put("msg", "查询失败(案件经纬度格式错误)");
							result.put("data", null);
							return result.toString();
						}

					}

					if(!StringUtils.isEmptyStr(coId)){
						//主要责任  或 全部责任      快赔点 
						QpTICCompanyId id = new QpTICCompanyId();
						id.setCoId(coId);
						QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(coId);
						List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
						if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
							qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
						}else{
							qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
						}

						for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
							qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
						}
						//Collections.sort(studentList,mc);     //按照age升序 22，23
						//Collections.reverse(studentList,mc);    //按照age降序 23,22  
						MyCompartor mc = new MyCompartor();
						Collections.sort(qpTIcCompanyGarageDetails, mc);

						List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(qpTIcCompanyGarageDetails);

						QpTIcCompanyGarageDetailShow qpTIcCompanyGarageDetailShow =qpTIcCompanyGarageDetailShows.get(0);
						if(qpTIcCompanyGarageDetailShow != null){
							qpTIcCompanyGarageDetailShow.setCaseLat(lat);
							qpTIcCompanyGarageDetailShow.setCaseLng(lng);
						}
						result.put("code", 0);
						result.put("msg", "查询成功");
						result.put("data", qpTIcCompanyGarageDetailShow);

					}else{
						//用来去掉重复
						Set<QpTIcCompanyGarageDetail> setQpTIcCompanyGarageDetails = new HashSet<QpTIcCompanyGarageDetail>();
						//同等责任
						for (QpTICAccident qpTICAccident : accidents) {
							QpTICCompanyId id = new QpTICCompanyId();
							id.setCoId(qpTICAccident.getCoId());
							QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(qpTICAccident.getCoId());
							List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails = null;
							if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
								qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(-1);
							}else{
								qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
							}

							logger.info("主要责任人快赔点:" + JSONArray.fromObject(qpTIcCompanyGarageDetails));
							for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
								qpTIcCompanyGarageDetail.setDistance(QpTIcCompanyGarageAction.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
								setQpTIcCompanyGarageDetails.add(qpTIcCompanyGarageDetail);
							}
							//			    Collections.sort(studentList,mc);     //按照age升序 22，23
							//			    Collections.reverse(studentList,mc);    //按照age降序 23,22  
						}
						List<QpTIcCompanyGarageDetail> orderByQpTIcCompanyGarageDetails = new ArrayList<QpTIcCompanyGarageDetail>();
						orderByQpTIcCompanyGarageDetails.addAll(setQpTIcCompanyGarageDetails);
						MyCompartor mc = new MyCompartor();
						Collections.sort(orderByQpTIcCompanyGarageDetails, mc);

						List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(orderByQpTIcCompanyGarageDetails);

						QpTIcCompanyGarageDetailShow qpTIcCompanyGarageDetailShow =qpTIcCompanyGarageDetailShows.get(0);
						if(qpTIcCompanyGarageDetailShow != null){
							qpTIcCompanyGarageDetailShow.setCaseLat(lat);
							qpTIcCompanyGarageDetailShow.setCaseLng(lng);
						}
						result.put("code", 0);
						result.put("msg", "查询成功");
						result.put("data", qpTIcCompanyGarageDetailShow);
					}
					//分两种情况:
					//1: 只传caseId
					//2： 传caseId  和    经纬度
				} catch (Exception e) {
					result.put("code", -1);
					result.put("msg", "查询失败,请稍后在试");
					logger.error("获取修理厂地址失败", e);
				}
				return result.toString();

			} else {
				result.put("code", 10001);
				result.put("msg", "缺少参数");
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.put("code", 10003);
			result.put("msg", "参数类型错误");
		} catch (Exception e) {
			logger.error("", e);
			result.put("code", 10004);
			result.put("msg", "程序异常");
		}
		return result.toString();
	}

	/**
	 * 保存事故及当事人
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String saveCaseAndAccident(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				Pair<String, String> pair = validatorCaseAndAccident(jsonObject);
				if (pair.getFirst().equals(CommonEnum.SUCCESS.getCode())) {
					// 执行结果
					Map<String, Object> queryResult = new HashMap<String, Object>();
					// 临时案件
					WxCaseId caseid = new WxCaseId();
					caseid.setId(jsonObject.getString("caseID"));
					WxCase wxcase = wxCaseService.findById(caseid);
					if (wxcase != null) {
						Integer status = Integer.parseInt(wxcase.getStatus() == null ? "0" : wxcase.getStatus());
						if (status <= 3) {
							wxcase.setStatus("4");
							wxcase.setIsDue("1".equals(jsonObject.getString("isDue")) ? "1" : "0");
							WxAccident queryAccident = new WxAccident();
							queryAccident.setCaseid(jsonObject.getString("caseID"));
							// 查询事故相关当事人
							List<WxAccident> queryAccidents = wxAccidentService.page(queryAccident, 1, Integer.MAX_VALUE).getResult();
							// 准备保存的当事人列表
							List<WxAccident> accidents = new ArrayList<WxAccident>();
							// 获取当事人甲
							WxAccident accident1 = jsonToWxAccident(jsonObject.getJSONObject("accident_first"));
							accident1.setGroupid(wxcase.getGroupid());
							// 获取当事人乙
							WxAccident accident2 = jsonToWxAccident(jsonObject.getJSONObject("accident_second"));
							if (queryAccidents.size() >= 2) {
								// 修改临时当事人
								WxAccident updateAccident1 = queryAccidents.get(0);
								updateAccident1.mergeFromAccident(accident1);
								accidents.add(updateAccident1);
								WxAccident updateAccident2 = queryAccidents.get(1);
								updateAccident2.mergeFromAccident(accident2);
								accidents.add(updateAccident2);
							} else {
								// 新增临时当事人
								accident1.setCaseid(jsonObject.getString("caseID"));
								accident1.setCreatedate(new Date());
								accidents.add(accident1);
								accident2.setCaseid(jsonObject.getString("caseID"));
								accident2.setCreatedate(new Date());
								accidents.add(accident2);
							}
							Pair<Boolean, String> resultPair = wxCaseService.saveWxCaseAndWxAccident(wxcase, accidents);
							if (resultPair.getFirst()) {
								queryResult.put("state", "success");
								queryResult.put("msg", "保存成功！");
							} else {
								queryResult.put("state", "error");
								queryResult.put("msg", "参数错误!请刷新页面重试");
							}
						} else {
							queryResult.put("state", "error");
							queryResult.put("msg", "案件处理中");
						}
					} else {
						queryResult.put("state", "error");
						queryResult.put("msg", "未拍照上传事故!请刷新页面重试");
					}
					result.setJsonResult(CommonEnum.SUCCESS, queryResult);
				} else {
					result.setState(pair.getFirst());
					result.setMsg(pair.getSecond());
					result.setData(null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	/**
	 * 校验事故及当事人信息
	 * @param json
	 * @return
	 */
	private Pair<String, String> validatorCaseAndAccident(JSONObject json) {
		Pair<String, String> result = new Pair<String, String>();
		List<String> validateCaseField = new ArrayList<String>();
		// 校验事故信息字段
		validateCaseField.add("caseID");
		validateCaseField.add("isDue");
		validateCaseField.add("accident_first");
		validateCaseField.add("accident_second");
		if (validateJsonValues(json, validateCaseField)) {
			// 校验当事人信息字段
			List<String> validateAccidentField = new ArrayList<String>();
			validateAccidentField.add("name");
			validateAccidentField.add("mobile");
			validateAccidentField.add("licenseno");
			validateAccidentField.add("idno");
			// 是否争议两种情况
			if("0".equals(json.get("isDue"))) {
				validateAccidentField.add("respcode");
			}
			validateAccidentField.add("coid");
			validateAccidentField.add("cartype");
			validateAccidentField.add("drivetype");
			try {
				JSONObject accidentFirst = json.getJSONObject("accident_first");
				JSONObject accidentSecond = json.getJSONObject("accident_second");
				if (validateJsonValues(accidentFirst, validateAccidentField) && validateJsonValues(accidentSecond, validateAccidentField)) {
					result.setFirst(CommonEnum.SUCCESS.getCode());
					result.setSecond(CommonEnum.SUCCESS.getMessage());
				} else {
					result.setFirst(CommonEnum.NONE_PARAM.getCode());
					result.setSecond(CommonEnum.NONE_PARAM.getMessage());
				}
			} catch (Exception e) {
				result.setFirst(CommonEnum.ERROR_PARAM.getCode());
				result.setSecond(CommonEnum.ERROR_PARAM.getMessage());
			}
		} else {
			result.setFirst(CommonEnum.NONE_PARAM.getCode());
			result.setSecond(CommonEnum.NONE_PARAM.getMessage());
		}
		return result;
	}

	/**
	 * 校验json多个字段
	 * @param object
	 * @param keys
	 * @return
	 */
	private boolean validateJsonValues(JSONObject object, List<String> keys) {
		boolean flag = true;
		for (String key : keys) {
			flag = flag && validateJsonValue(object, key);
		}
		return flag;
	}

	/**
	 * 校验json单个字段
	 * @param object
	 * @param key
	 * @return
	 */
	private boolean validateJsonValue(JSONObject object, String key) {
		return object.containsKey(key) && StringUtils.isNotEmpty(object.getString(key));
	}

	/**
	 * json对象转换成临时当事人
	 * @param object
	 * @return
	 */
	private WxAccident jsonToWxAccident(JSONObject object) {
		WxAccident result = new WxAccident();
		result.setAccidentname((object.containsKey("name") && StringUtils.isNotEmpty(object.getString("name"))) ? object.getString("name") : "");
		result.setMobile((object.containsKey("mobile") && StringUtils.isNotEmpty(object.getString("mobile"))) ? object.getString("mobile") : "");
		result.setLicensenumber((object.containsKey("licenseno") && StringUtils.isNotEmpty(object.getString("licenseno"))) ? object.getString("licenseno") : "");
		result.setIdentfinynumber((object.containsKey("idno") && StringUtils.isNotEmpty(object.getString("idno"))) ? object.getString("idno") : "");
		result.setResponsibilitycode((object.containsKey("respcode") && StringUtils.isNotEmpty(object.getString("respcode"))) ? object.getString("respcode") : "");
		result.setCoid((object.containsKey("coid") && StringUtils.isNotEmpty(object.getString("coid"))) ? object.getString("coid") : "");
		result.setCompanyNameOther((object.containsKey("companyNameOther") && StringUtils.isNotEmpty(object.getString("companyNameOther"))) ? object.getString("companyNameOther") : "");
		result.setCartype((object.containsKey("cartype") && StringUtils.isNotEmpty(object.getString("cartype"))) ? object.getString("cartype") : "");
		result.setDrivetype((object.containsKey("drivetype") && StringUtils.isNotEmpty(object.getString("drivetype"))) ? object.getString("drivetype") : "");
		result.setOpenID((object.containsKey("openID") && StringUtils.isNotEmpty(object.getString("openID"))) ? object.getString("openID") : "");
		result.setChassisNumber((object.containsKey("chassisNumber") && StringUtils.isNotEmpty(object.getString("chassisNumber"))) ? object.getString("chassisNumber") : "");
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public String findWxCaseByID(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") && StringUtils.isNotEmpty(jsonObject.getString("caseID"))) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					WxCaseId queryCaseID = new WxCaseId();
					queryCaseID.setId(jsonObject.getString("caseID"));
					WxCase wxCase = wxCaseService.findById(queryCaseID);
					resultMap.put("case", wxCase);
					WxAccident queryAccident = new WxAccident();
					queryAccident.setCaseid(jsonObject.getString("caseID"));
					List<WxAccident> accidents = wxAccidentService.page(queryAccident, 1, Integer.MAX_VALUE).getResult();
					resultMap.put("accidents", accidents);
					result.setJsonResult(CommonEnum.SUCCESS, resultMap);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	@Override
	public String verifAccidentCode(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") && StringUtils.isNotEmpty(jsonObject.getString("caseID"))
						&& jsonObject.containsKey("mobile1") && StringUtils.isNotEmpty(jsonObject.getString("mobile1"))
//						&& jsonObject.containsKey("verifCode1") && StringUtils.isNotEmpty(jsonObject.getString("verifCode1"))
						&& jsonObject.containsKey("mobile2") && StringUtils.isNotEmpty(jsonObject.getString("mobile2"))
						&& jsonObject.containsKey("verifCode2") && StringUtils.isNotEmpty(jsonObject.getString("verifCode2"))) {

					WxCaseId querywCaseId = new WxCaseId();
					querywCaseId.setId(jsonObject.getString("caseID"));
					WxCase wxCase = wxCaseService.findById(querywCaseId);
					String isDue = wxCase.getIsDue();
					if (isDue != null && "1".equals(isDue)) {
						JSONArray smsDataItems = new JSONArray();
//						JSONObject smsData1 = new JSONObject();
//						smsData1.put("userName", "本人");
//						smsData1.put("phone", jsonObject.getString("mobile1"));
//						smsData1.put("smsCode", jsonObject.getString("verifCode1"));
//						smsDataItems.add(smsData1);
						JSONObject smsData2 = new JSONObject();
						smsData2.put("userName", "对方");
						smsData2.put("phone", jsonObject.getString("mobile2"));
						smsData2.put("smsCode", jsonObject.getString("verifCode2"));
						smsDataItems.add(smsData2);
						
						JSONObject smsSendResult = verifys(smsDataItems, jsonObject.getInt("smsModel"));
						logger.info("多账号短信校验结果 ：" + smsSendResult);
						// 有争议  //TODO   e路畅通   5  -- 换成 长沙交警  -- 12   -- 14新增默认有争议验证
						if("200".equals(smsSendResult.getString("code"))){
							JSONObject smsResult = smsSendResult.getJSONObject("info");
							if("999".equals(smsResult.get("code").toString())){
								result.setState(CommonEnum.FAIL.getCode());
								result.setMsg("网络异常,请稍后在试");
								result.setData(null);
								return result.toJsonString();
							}else if(!"0".equals(smsResult.get("code").toString())){
								result.setState(CommonEnum.FAIL.getCode());
								result.setMsg(smsResult.getString("message"));
								return result.toJsonString();
							}
						}else {
							result.setState(CommonEnum.FAIL.getCode());
							result.setMsg("网络异常,请稍后在试");
							result.setData(null);
							return result.toJsonString();
						}
//						if (!verify(jsonObject.getString("mobile1"), jsonObject.getString("verifCode1"), 12)) {
//							result.setState(CommonEnum.FAIL.getCode());
//							result.setMsg("本人验证码错误");
//							result.setData(null);
//							return result.toJsonString();
//						}
//						if (!verify(jsonObject.getString("mobile2"), jsonObject.getString("verifCode2"), 12)) {
//							result.setState(CommonEnum.FAIL.getCode());
//							result.setMsg("对方验证码错误");
//							result.setData(null);
//							return result.toJsonString();
//						}
						wxCase.setStatus("6");
					} else {
						
//						JSONObject smsDatas = new JSONObject();
//						
//						JSONArray smsDataItems = new JSONArray();
//						JSONObject smsData1 = new JSONObject();
//						smsData1.put("userName", "本人");
//						smsData1.put("phone", jsonObject.getString("mobile1"));
//						smsData1.put("smsCode", jsonObject.getString("verifCode1"));
//						smsDataItems.add(smsData1);
//						JSONObject smsData2 = new JSONObject();
//						smsData1.put("userName", "对方");
//						smsData1.put("phone", jsonObject.getString("mobile2"));
//						smsData1.put("smsCode", jsonObject.getString("verifCode2"));
//						smsDataItems.add(smsData2);
//						
//						smsDatas.put("smsModelNo", 12);
//						smsDatas.put("smsDatas", smsDataItems);
//						JSONObject smsSendResult = verifys(smsDatas, 12);
//						
//						// 有争议  //TODO   e路畅通   5  -- 换成 长沙交警  -- 12
//						if("200".equals(smsSendResult.getString("code"))){
//							JSONObject smsResult = smsSendResult.getJSONObject("info");
//							if(!"0".equals(smsResult.get("code").toString())){
//								result.setState(CommonEnum.FAIL.getCode());
//								result.setMsg(smsResult.getString("message"));
//								return result.toJsonString();
//							}
//						}else {
//							result.setState(CommonEnum.FAIL.getCode());
//							result.setMsg("网络异常,请稍后在试");
//							result.setData(null);
//							return result.toJsonString();
//						}
						//暂时没有无争议 未改 只需要改 smsModelNo
						// 无争议
						if (!verify(jsonObject.getString("mobile1"), jsonObject.getString("verifCode1"), 6)) {
							result.setState(CommonEnum.FAIL.getCode());
							result.setMsg("本人验证码错误");
							result.setData(null);
							return result.toJsonString();
						}
						if (!verify(jsonObject.getString("mobile2"), jsonObject.getString("verifCode2"), 6)) {
							result.setState(CommonEnum.FAIL.getCode());
							result.setMsg("对方验证码错误");
							result.setData(null);
							return result.toJsonString();
						}
						wxCase.setStatus("5");
					}
					wxCaseService.save(wxCase);
					wxCaseService.saveCaseAndAccident(wxCase);
					addTaskAndPushMessage(wxCase);
					
					
					try {
					//推送模板消息至公众号
					JSONObject parse = new JSONObject();
					
					parse.put("templateId", Constants.getACCEPT_TEMPLATEID());
					
					parse.put("first", "您好，事故已成功受理。\n");
					parse.put("color0", "#9D9D9D");
					parse.put("remark", "感谢您的使用，请留意本公众号最新动态。");
					parse.put("color4", "#9D9D9D");
					String caseID = jsonObject.getString("caseID");
					parse.put("url",Constants.getACCEPTTEMPLATE_URL()+"?wxCaseId="+caseID);
					WxAccident queryAccident = new WxAccident();
					queryAccident.setCaseid(caseID);
					List<WxAccident> accidents = wxAccidentService.page(queryAccident, 1, Integer.MAX_VALUE).getResult();
					logger.info("accidents:"+com.alibaba.fastjson.JSONArray.toJSONString(accidents));
					String caseAddress = wxCase.getCity()+wxCase.getArea()+wxCase.getAddress();
					Date accidentdate = wxCase.getAccidentdate();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String caseTime = formatter.format(accidentdate);
					caseTime = caseTime.replaceFirst("-", "年");
					caseTime = caseTime.replaceFirst("-", "月");
					caseTime = caseTime.replaceFirst(" ", "日 ");
					if(null != caseTime){
						parse.put("keyword2", caseTime);
						parse.put("color2", "#004B97");
					}
					if(null != caseAddress){
						parse.put("keyword3", caseAddress+"\n");
						parse.put("color3", "#004B97");
					}
					String mobile2 = jsonObject.getString("mobile2");
					String mobile1 = jsonObject.getString("mobile1");
						//发送微信模板消息至当事人
						if(null != mobile2){
							for (int i = 0; i < accidents.size(); i++) {
								if(mobile2.equals(accidents.get(i).getMobile())){
									parse.put("keyword1",accidents.get(i).getLicensenumber());
									parse.put("color1", "#004B97");
									for (int j = 0; j < accidents.size(); j++) {
										if(mobile1.equals(accidents.get(j).getMobile())){
											String openID = accidents.get(j).getOpenID();
											if(null != openID){
												parse.put("openId",openID);
												Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(Constants.getTEMPLATEID_URL(), parse.toString(), "UTF-8");
												logger.error("微信模板推送失败qpTICAccident1_"+openID+":"+sendMap);
											}
										}
									}
								}
							}
						}
						//发送微信模板消息至对方微信
						if(null != mobile1){
							for (int i = 0; i < accidents.size(); i++) {
								if(mobile1.equals(accidents.get(i).getMobile())){
									parse.put("keyword1",accidents.get(i).getLicensenumber());
									parse.put("color1", "#004B97");
									for (int j = 0; j < accidents.size(); j++) {
										if(mobile2.equals(accidents.get(j).getMobile())){
											String openID = accidents.get(j).getOpenID();
											if(null != openID){
												parse.put("openId",openID);
												Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(Constants.getTEMPLATEID_URL(), parse.toString(), "UTF-8");
												logger.error("微信模板推送失败qpTICAccident2_"+openID+":"+sendMap);
											}
										}
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
							
					
					
					result.setJsonResult(CommonEnum.SUCCESS, null);
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	/**
	 * 校验验证码  单号吗校验
	 * @param phone
	 * @param code
	 * @param type
	 * @return
	 */
	private boolean verify(String phone, String code, Integer type) {
		String result = SmsUtil.verify(phone, code, type);
		JSONObject json = JSONObject.fromObject(result);
		return "200".equals(json.get("code").toString())
				&& "0".equals(json.getJSONObject("info").get("code").toString());
	}
	
	/**
	 * 校验验证码  多号吗校验
	 * @param smsDatas  {'smsModelNo':'模板代码','smsDatas':'[{'phone':'121212131', 'smsCode':'123456'},{'phone':'121212131', 'smsCode':'123456'}]'}
	 * @return
	 */
	private JSONObject verifys(JSONArray smsDatas, int modelNo) {
		String result = SmsUtil.verifys(smsDatas, modelNo);
		return JSONObject.fromObject(result);
//		return "200".equals(json.get("code").toString()) && "0".equals(json.getJSONObject("info").get("code").toString());
	}

	/**
	 * 新增任务并推送通知
	 * @param wxCase
	 * @throws Exception
	 */
	private void addTaskAndPushMessage(WxCase wxCase) throws Exception {
		// 保存任务
		WxTask task = new WxTask();
		task.setTaskId(0);
		task.setCaseId(wxCase.getRealCaseId());
		task.setCreateTime(new Date());
		task.setCreateCode("admin");
		task.setCreateName("admin");
		task.setStatus("1");
		task.setRegFrom("0");
		task.setIsDue(wxCase.getIsDue());
		wxTaskService.addWxTask(task);
		List<UmTUserRole> userRoles = umTRoleService.findRoleByRoleCode("REMOTE-FEE-POLICE");
		String[] arr = null;
		if (!StringUtils.isEmptyStr(userRoles)) {
			arr = new String[userRoles.size() + 1];
			for (int i = 0; i < userRoles.size(); i++) {
				arr[i] = userRoles.get(i).getUserCode();
			}
			arr[userRoles.size()] = "admin";
			logger.info("存在远程定责警察");
			logger.info("task:" + JSONObject.fromObject(task));
			logger.info("通知人:");
			for (String string : arr) {
				logger.info(string);
			}
		} else {
			arr = new String[1];
			arr[0] = "admin";
			logger.info("不存在远程定责警察");
			logger.info("task:" + JSONObject.fromObject(task));
			logger.info("通知admin");
		}
		dwrPush.doPush("prepareAll", "testCall", "有新定责任务需要处理！", arr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findCaseByWxCaseID(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") 
						&& StringUtils.isNotEmpty(jsonObject.getString("caseID"))) {
					WxCaseId querywCaseId = new WxCaseId();
					querywCaseId.setId(jsonObject.getString("caseID"));
					WxCase wxCase = wxCaseService.findById(querywCaseId);
					if (wxCase != null && StringUtils.isNotEmpty(wxCase.getRealCaseId())) {
						Map<String, Object> resultMap = new HashMap<String, Object>();
						QpTTPCase queryCase = new QpTTPCase();
						queryCase.setCaseId(wxCase.getRealCaseId());
						QpTTPCase case1 = qpTTPCaseService.findQpTTPCaseById(queryCase);
						resultMap.put("case", case1);
						QpTICAccident qpTICAccident = new QpTICAccident();
						qpTICAccident.setCaseId(wxCase.getRealCaseId());
						List<QpTICAccident> accidents = qpTICAccidentService.findQpTICAccidentPageBySql(qpTICAccident, 1, Integer.MAX_VALUE).getResult();
						resultMap.put("accidents", accidents);
						result.setJsonResult(CommonEnum.SUCCESS, resultMap);
					} else {
						result.setJsonResult(CommonEnum.SUCCESS, null);
					}
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}

	@Override
	public String finish(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") 
						&& StringUtils.isNotEmpty(jsonObject.getString("caseID"))) {
					WxCaseId querywCaseId = new WxCaseId();
					querywCaseId.setId(jsonObject.getString("caseID"));
					WxCase wxCase = wxCaseService.findById(querywCaseId);
					if (wxCase != null && StringUtils.isNotEmpty(wxCase.getRealCaseId())) {
						wxCase.setStatus("7");
						wxCaseService.save(wxCase);
						result.setJsonResult(CommonEnum.SUCCESS, null);
					} else {
						result.setJsonResult(CommonEnum.SUCCESS, null);
					}
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}


	@Override
	public String isDone(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("caseID") 
						&& StringUtils.isNotEmpty(jsonObject.getString("caseID"))) {
					WxCaseId querywCaseId = new WxCaseId();
					querywCaseId.setId(jsonObject.getString("caseID"));
					WxCase wxCase = wxCaseService.findById(querywCaseId);
					if (wxCase != null && StringUtils.isNotEmpty(wxCase.getRealCaseId())) {
						WxTask task = wxTaskService.findWxTaskByCaseId(wxCase.getRealCaseId());
						if (task != null && !"4".equals(task.getStatus())) {
							result.setData(null);
							result.setState(CommonEnum.FAIL.getCode());
							result.setMsg("处理中");
						} else {
							result.setJsonResult(CommonEnum.SUCCESS, null);
						}
					} else {
						result.setData(null);
						result.setState(CommonEnum.FAIL.getCode());
						result.setMsg("案件不存在");
					}
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}


	@Override
	public String updateAccidentSurveyGroupIdByAcciId(String json) {
		JSONObject jsonObject = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if(StringUtils.isEmptyStr(json)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", "");
				return result.toString();
			}
			jsonObject = JSONObject.fromObject(json);
			if(StringUtils.isEmptyStr(jsonObject)){
				result.put("state", "01");
				result.put("msg", "参数为空");
				result.put("data", "");
				return result.toString();
			}
			if(!jsonObject.containsKey("acciId") || StringUtils.isEmptyStr(jsonObject.getString("acciId"))){
				result.put("state", "01");
				result.put("msg", "参数错误(acciId)");
				result.put("data", "");
				return result.toString();
			}
			if(!jsonObject.containsKey("groupId") || StringUtils.isEmptyStr(jsonObject.getString("groupId"))){
				result.put("state", "01");
				result.put("msg", "参数错误(groupId)");
				result.put("data", "");
				return result.toString();
			}
			String acciId = jsonObject.getString("acciId");
			String groupId = jsonObject.getString("groupId");
			QpTICAccidentId id = new QpTICAccidentId();
			id.setAcciId(acciId);
			QpTICAccident qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
			if(StringUtils.isEmptyStr(qpTICAccident)){
				result.put("state", "01");
				result.put("msg", "当事人信息不存在");
				return result.toString();
			}
			qpTICAccident.setSurveyGroupId(groupId);
			qpTICAccidentService.updateQpTICAccident(qpTICAccident);
			result.put("state", "00");
			result.put("msg", "修改成功");
			result.put("data", qpTICAccident);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "01");
			result.put("msg", "查询异常,请稍后在试");
			return result.toString();
		}
	}

	@Override
	public String getUserCurrentCase(String json) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("openID") && StringUtils.isNotEmpty(jsonObject.getString("openID"))) {
					WxCase wxCase = wxCaseService.findCurrentCaseByUser(jsonObject.getString("openID"));
					if (wxCase != null) {
						result.setJsonResult(CommonEnum.SUCCESS, wxCase);
					} else {
						result.setData(null);
						result.setState(CommonEnum.FAIL.getCode());
						result.setMsg("案件不存在");
					}
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}


	@Override
	public String getUmTAccidentUserInfo(String json) {
		// TODO Auto-generated method stub
		try {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				if (jsonObject.containsKey("IDNumber") && StringUtils.isNotEmpty(jsonObject.getString("IDNumber"))) {
					UmTAccidentUser umTAccidentUser = new UmTAccidentUser();
					umTAccidentUser.setIDNumber(jsonObject.getString("IDNumber"));
					List<UmTAccidentUser> umTAccidentUsers = umTAccidentUserService.findByUmTAccidentUser(umTAccidentUser);
					if (umTAccidentUsers != null && umTAccidentUsers.size() > 0) {
						result.setJsonResult(CommonEnum.SUCCESS, umTAccidentUsers.get(0));
					} else {
						result.setData(null);
						result.setState(CommonEnum.SUCCESS.getCode());
						result.setMsg("无数据");
					}
				} else {
					result.setJsonResult(CommonEnum.NONE_PARAM, null);
				}
			} else {
				result.setJsonResult(CommonEnum.NONE_PARAM, null);
			}
			
		} catch (JSONException e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_PARAM, null);
		} catch (Exception e) {
			logger.error("", e);
			result.setJsonResult(CommonEnum.ERROR_APP, null);
		}
		return result.toJsonString();
	}


}
