package com.picc.qp.weixinweb;

import ins.framework.common.ServiceFactory;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.picc.common.handler.UploadCaseToCSHandler;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.StringUtils;
import com.picc.common.web.DwrPush;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.UserKey;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxCaseId;
import com.picc.qp.schema.model.WxTask;
import com.picc.qp.schema.vo.Pair;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTAsyncTaskService;
import com.picc.qp.service.facade.IQpTCOMDictionaryService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTICPictureGroupService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.facade.UserKeyService;
import com.picc.qp.service.wx.facade.WxAccidentService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.service.wx.facade.WxTaskService;
import com.picc.qp.util.SmsUtil;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 微信公众号案件逻辑
 * 
 * @author obba
 * 
 */
@SuppressWarnings("serial")
public class CaseAction extends Struts2Action {

	private QpTTPCase qpTTPCase;

	private IQpTTPCaseService qpTTPCaseService;

	private IQpTICAccidentService qpTICAccidentService;

	private IQpTICPictureService qpTICPictureService;

	private IQpTICPictureGroupService qpTICPictureGroupService;

	private IUmTUserService umTUserService;

	private WxCaseService wxCaseService;

	private WxAccidentService wxAccidentService;

	private IQpTCommonService qpTCommonService;

	private IQpTICCompanyService qpTICCompanyService;

	private IQpTTPLawService qpTTPLawService;

	private WxTaskService wxTaskService;

	private DwrPush dwrPush;

	private IUmTRoleService umTRoleService;

	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public QpTTPCase getQpTTPCase() {
		return qpTTPCase;
	}

	public void setQpTTPCase(QpTTPCase qpTTPCase) {
		this.qpTTPCase = qpTTPCase;
	}

	public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
		this.qpTTPCaseService = qpTTPCaseService;
	}

	public IQpTTPCaseService getQpTTPCaseService() {
		return qpTTPCaseService;
	}

	public IQpTICAccidentService getQpTICAccidentService() {
		return qpTICAccidentService;
	}

	public void setQpTICAccidentService(
			IQpTICAccidentService qpTICAccidentService) {
		this.qpTICAccidentService = qpTICAccidentService;
	}

	public IQpTICPictureService getQpTICPictureService() {
		return qpTICPictureService;
	}

	public void setQpTICPictureService(IQpTICPictureService qpTICPictureService) {
		this.qpTICPictureService = qpTICPictureService;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public IQpTICPictureGroupService getQpTICPictureGroupService() {
		return qpTICPictureGroupService;
	}

	public void setQpTICPictureGroupService(
			IQpTICPictureGroupService qpTICPictureGroupService) {
		this.qpTICPictureGroupService = qpTICPictureGroupService;
	}

	public WxCaseService getWxCaseService() {
		return wxCaseService;
	}

	public void setWxCaseService(WxCaseService wxCaseService) {
		this.wxCaseService = wxCaseService;
	}

	public WxAccidentService getWxAccidentService() {
		return wxAccidentService;
	}

	public void setWxAccidentService(WxAccidentService wxAccidentService) {
		this.wxAccidentService = wxAccidentService;
	}

	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public WxTaskService getWxTaskService() {
		return wxTaskService;
	}

	public void setWxTaskService(WxTaskService wxTaskService) {
		this.wxTaskService = wxTaskService;
	}

	public DwrPush getDwrPush() {
		return dwrPush;
	}

	public void setDwrPush(DwrPush dwrPush) {
		this.dwrPush = dwrPush;
	}

	public IUmTRoleService getUmTRoleService() {
		return umTRoleService;
	}

	public void setUmTRoleService(IUmTRoleService umTRoleService) {
		this.umTRoleService = umTRoleService;
	}

	/**
	 * 事故记录
	 * 
	 * @return
	 */
	public String casehistory() {
		try {
			this.getRequest().setAttribute("param1", param);
			boolean flag = this.weiXinLogin(param);
			if (!flag) {
				System.out.println("param:" + param);
				return "zhuce";
			}
			String usercode = (String) this.getRequest().getSession()
					.getAttribute("userCode_weixin");
			if (StringUtils.isEmptyStr(usercode)) {
				return "zhuce";
			}
			// 根据openid查询历史案件
			List<QpTTPCase> list = qpTTPCaseService.findCaseByWxUser(usercode);
			this.getRequest().setAttribute("list", list);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "zhuce";
		}
	}

	/**
	 * 事故详情
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String casedetail() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		String id = this.getRequest().getParameter("caseId");
		QpTTPCase qpTTPCase = new QpTTPCase();
		qpTTPCase.setCaseId(id);
		QpTICAccident qpTICAccident = new QpTICAccident();
		qpTICAccident.setCaseId(id);
		try {
			QpTTPCase resultQpTTPCase = qpTTPCaseService
					.findQpTTPCaseById(qpTTPCase);
			this.getRequest().setAttribute("case1", resultQpTTPCase);
			List<QpTICAccident> accidents = qpTICAccidentService
					.findQpTICAccidentPageBySql(qpTICAccident, 1,
							Integer.MAX_VALUE).getResult();
			this.getRequest().setAttribute("accidents", accidents);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", e.getMessage());
			this.writeAjaxErrorByMap(map);
			return NONE;
		}
	}

	/**
	 * 查看定责图片
	 * 
	 * @return
	 */
	public String casesurveypic() {
		String groupId = this.getRequest().getParameter("surveyGroupId");
		String root = ServletActionContext.getServletContext().getRealPath(
				"/pages/qp/qpticpicturegroup/images/temp");
		try {
			List<QpTICPicture> ticPicturesList = new ArrayList<QpTICPicture>();
			if (StringUtils.isNotEmpty(groupId)) {
				ticPicturesList = qpTICPictureService
						.findQpTICPictureByGroupId(groupId);
			}
			if (ticPicturesList != null && ticPicturesList.size() > 0) {
				List<QpTICPicture> filePathList = new ArrayList<QpTICPicture>();
				String currentTime = new SimpleDateFormat("yyyyMMdd")
						.format(new Date());
				root = root + "/" + currentTime;
				File savedir = new File(root);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				root = root + "/" + groupId;
				File f = new File(root);
				if (!f.exists()) {
					f.mkdir();
				}
				for (int i = 0; i < ticPicturesList.size(); i++) {
					QpTICPicture ticPicture = ticPicturesList.get(i);
					String fileNamePath = ticPicture.getFileName();
					String fileName = fileNamePath.substring(
							fileNamePath.lastIndexOf("/"),
							fileNamePath.length());
					File file = new File(fileNamePath);
					if (!file.exists()) {
						logger.info("file:" + file + "不存在");
					} else {
						try {
							File file2 = new File(f + fileName);
							if (!file2.exists()) {
								// 把所有文件存进输入流
								FileInputStream fis = new FileInputStream(file);
								// 构建输出流并指定路径
								FileOutputStream fos = new FileOutputStream(f
										+ fileName);
								byte[] buffer = new byte[1024];
								while (-1 != (fis
										.read(buffer, 0, buffer.length))) {
									fos.write(buffer);
								}
								fos.close();
								fis.close();
							}
							QpTICPicture qpTICPicturet = new QpTICPicture();
							qpTICPicturet
									.setFileName("/pages/qp/qpticpicturegroup/images/temp"
											+ "/"
											+ currentTime
											+ "/"
											+ groupId
											+ fileName);
							qpTICPicturet.setPicDesc(ticPicture
									.getOriginalFileName() == null ? ""
									: ticPicture.getOriginalFileName()); // 图片预览用到的文件名
							qpTICPicturet.setPicOrder(ticPicture.getId()
									.getPicId());
							filePathList.add(qpTICPicturet);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("", e);
						}
					}
				}
				this.getRequest().setAttribute("filePathList", filePathList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", e.getMessage());
			this.writeAjaxErrorByMap(map);
			return NONE;
		}
	}

	/**
	 * 查看当事人上传图片
	 * 
	 * @return
	 */
	public String caseaccpic() {
		String groupId = this.getRequest().getParameter("groupId");
		String acciId = this.getRequest().getParameter("acciId");
		try {
			this.getResponse().sendRedirect(
					"/weixin/case/casesurveypic.do?surveyGroupId="
							+ (groupId == null ? "" : groupId) + "&acciId="
							+ (acciId == null ? "" : acciId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String caseIn() throws Exception {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			return "zhuce";
		}
		return SUCCESS;
	}

	public String twoPage() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			return "zhuce";
		}
		return SUCCESS;
	}

	public String threePage() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			return "zhuce";
		}
		return SUCCESS;
	}

	public String prepareAccidentEdit() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		// 加载案件当事人信息
		String userCode = (String) this.getRequest().getSession()
				.getAttribute("userCode_weixin");
		if (StringUtils.isEmptyStr(userCode)) {
			return "zhuce";
		}
		try {
			UmTUser resultUser = umTUserService.findUmTUserByUserCode(userCode);
			if (resultUser != null) {
				WxAccident accident1 = new WxAccident();
				accident1.setAccidentname(resultUser.getUserName());
				accident1.setMobile(resultUser.getMobile());
				accident1.setIdentfinynumber(resultUser.getIdentityNumber());
				accident1.setLicensenumber(resultUser.getLicenseNo());
				this.getRequest().setAttribute("accident1", accident1);
			}
			// 车辆类型
			List<QpSelectDataVo> vehicleTypeList = qpTCommonService
					.getSelectData(SelectTypeEnum.VEHICLE.getCode());
			this.getRequest().setAttribute("vehicleTypeList", vehicleTypeList);
			// 准驾车型
			List<QpSelectDataVo> permissionDriveTypeList = qpTCommonService
					.getSelectData(SelectTypeEnum.PERMISSION_DRIVE.getCode());
			this.getRequest().setAttribute("permissionDriveTypeList",
					permissionDriveTypeList);
			// 保险公司
			List<QpTICCompany> qpTICCompanyList = qpTICCompanyService
					.findByQpTICCompany(new QpTICCompany());
			this.getRequest()
					.setAttribute("qpTICCompanyList", qpTICCompanyList);
			// 违反法规
			List<QpTTPLaw> qpTTPLawList = qpTTPLawService
					.findByQpTTPLaw(new QpTTPLaw());
			this.getRequest().setAttribute("qpTTPLawList", qpTTPLawList);
			// 承担责任
			List<QpSelectDataVo> responsibilityTypeList = qpTCommonService
					.getSelectData(SelectTypeEnum.RESPONSIBILITY.getCode());
			this.getRequest().setAttribute("responsibilityTypeList",
					responsibilityTypeList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	// 案件两位当事人
	private WxAccident accident1;
	private WxAccident accident2;
	private String wxCaseId;

	public WxAccident getAccident1() {
		return accident1;
	}

	public void setAccident1(WxAccident accident1) {
		this.accident1 = accident1;
	}

	public WxAccident getAccident2() {
		return accident2;
	}

	public void setAccident2(WxAccident accident2) {
		this.accident2 = accident2;
	}

	public String getWxCaseId() {
		return wxCaseId;
	}

	public void setWxCaseId(String wxCaseId) {
		this.wxCaseId = wxCaseId;
	}
	
	/**
	 * 保存当事人信息并跳转到验证码确认页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveAccident() {
		Map<String, Object> result = new HashMap<String, Object>();
		String isDue = getRequest().getParameter("isDue");
		try {
			WxCaseId caseid = new WxCaseId();
			caseid.setId(wxCaseId);
			WxCase wxcase = wxCaseService.findById(caseid);
			if (wxcase == null) {
				result.put("state", "error");
				result.put("msg", "未拍照上传事故!请刷新页面重试");
				this.writeAjaxByMap(result);
				return NONE;
			}
			// 判断临时案件处理状态,大于3的是之后的处理流程,不做处理
			Integer status = Integer.parseInt(wxcase.getStatus() == null ? "0"
					: wxcase.getStatus());
			if (status > 3) {
				result.put("state", "error");
				result.put("msg", "案件处理中");
				this.writeAjaxByMap(result);
				return NONE;
			}
			// 案件地点修改
			rebuildWxCaseLocation(wxcase);
			// 案件状态修改为待确认
			wxcase.setStatus("4");
			if (isDue != null && "1".equals(isDue)) {
				// 有争议
				wxcase.setIsDue("1");
			} else {
				// 无争议
				wxcase.setIsDue("0");
			}
			// 临时案件关联的临时当事人处理，有则修改无则新增
			accident1.setGroupid(wxcase.getGroupid());
			List<WxAccident> accidents = new ArrayList<WxAccident>();
			WxAccident queryAccident = new WxAccident();
			queryAccident.setCaseid(wxCaseId);
			List<WxAccident> queryAccidents = wxAccidentService.page(
					queryAccident, 1, Integer.MAX_VALUE).getResult();
			if (queryAccidents.size() >= 2) {
				WxAccident updateAccident1 = queryAccidents.get(0);
				updateAccident1.mergeFromAccident(accident1);
				accidents.add(updateAccident1);
				WxAccident updateAccident2 = queryAccidents.get(1);
				updateAccident2.mergeFromAccident(accident2);
				accidents.add(updateAccident2);
			} else {
				accident1.setCaseid(wxCaseId);
				accident1.setCreatedate(new Date());
				accident2.setCaseid(wxCaseId);
				accident2.setCreatedate(new Date());
				accidents.add(accident1);
				accidents.add(accident2);
			}
			Pair<Boolean, String> resultPair = wxCaseService
					.saveWxCaseAndWxAccident(wxcase, accidents);
			if (!resultPair.getFirst()) {
				result.put("state", "error");
				result.put("msg", "参数错误!请刷新页面重试");
			} else {
				result.put("state", "success");
				result.put("msg", "保存成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "error");
			result.put("msg", "参数错误!请刷新页面重试");
		}
		this.writeAjaxByMap(result);
		return NONE;
	}

	/**
	 * 查询案件完成状态
	 * 
	 * @return
	 */
	public String hasPoliceDone() {
		Map<String, Object> result = new HashMap<String, Object>();
		WxCaseId querywCaseId = new WxCaseId();
		querywCaseId.setId(wxCaseId);
		try {
			WxCase wxCase = wxCaseService.findById(querywCaseId);
			if (wxCase != null) {
				String realCaseId = wxCase.getRealCaseId();
				WxTask task = wxTaskService.findWxTaskByCaseId(realCaseId);
				if (task != null && "4".equals(task.getStatus())) {
					result.put("state", "success");
					result.put("msg", "处理完成");
				} else {
					result.put("state", "error");
					result.put("msg", "处理中");
				}
			} else {
				result.put("state", "error");
				result.put("msg", "案件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "error");
			result.put("msg", "网络错误");
		}
		this.writeAjaxByMap(result);
		return NONE;
	}

	@SuppressWarnings("unchecked")
	public String toVerifCode() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		try {
			WxAccident wxAccident = new WxAccident();
			wxAccident.setCaseid(wxCaseId);
			List<WxAccident> accidents = wxAccidentService.page(wxAccident, 1,
					Integer.MAX_VALUE).getResult();
			this.getRequest().setAttribute("accidents", accidents);
			WxCaseId caseid = new WxCaseId();
			caseid.setId(wxCaseId);
			WxCase wxcase = wxCaseService.findById(caseid);
			this.getRequest().setAttribute("wxcase", wxcase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String sendDingzeCode() {
		Map<String, Object> result = new HashMap<String, Object>();
		String phone = this.getRequest().getParameter("phone");
		try {
			WxCaseId querywCaseId = new WxCaseId();
			querywCaseId.setId(wxCaseId);
			WxCase wxCase = wxCaseService.findById(querywCaseId);
			String isDue = wxCase.getIsDue();
			// 发送确认短信
			if (isDue != null && "1".equals(isDue)) {
				// 有争议
				JSONObject json = JSONObject.fromObject(SmsUtil.sendCode(phone));
				result.put("state", "success");
				result.put("data", json);
				result.put("msg", json.get("info"));
			} else {
				// 无争议
				String my = null;
				String other = null;
				if (accident1.getMobile() != null
						&& accident1.getMobile().equals(phone)) {
					my = accident1.getResponsibilitycode();
					other = accident2.getResponsibilitycode();
				}
				if (accident2.getMobile() != null
						&& accident2.getMobile().equals(phone)) {
					my = accident2.getResponsibilitycode();
					other = accident1.getResponsibilitycode();
				}
				if (StringUtils.isEmptyStr(my) || StringUtils.isEmptyStr(other)) {
					result.put("state", "error");
					result.put("msg", "参数错误");
					return NONE;
				}
				JSONObject json = JSONObject.fromObject(SmsUtil
						.sendResultToAccident(phone, DateUtil
								.parseToFormatString(wxCase.getAccidentdate(),
										DateUtil.FULL_DATE_STR), accident1
								.getAccidentname(), accident1
								.getLicensenumber(), my, accident2
								.getAccidentname(), accident2
								.getLicensenumber(), other));
				result.put("state", "success");
				result.put("data", json);
				result.put("msg", json.get("info"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "error");
			result.put("msg", "网络错误");
		}
		this.writeAjaxByMap(result);
		return NONE;
	}

	/**
	 * 校验验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	private boolean verify(String phone, String code, Integer type) {
		String result = SmsUtil.verify(phone, code, type);
		JSONObject json = JSONObject.fromObject(result);
		// return true;
		return "200".equals(json.get("code").toString())
				&& "0".equals(json.getJSONObject("info").get("code").toString());
	}

	public String prepareFinish() {
		Map<String, Object> result = new HashMap<String, Object>();
		String code1 = this.getRequest().getParameter("verifCode1");
		String code2 = this.getRequest().getParameter("verifCode2");
		this.getRequest().setAttribute("wxCaseId", wxCaseId);
		try {
			WxCaseId querywCaseId = new WxCaseId();
			querywCaseId.setId(wxCaseId);
			WxCase wxCase = wxCaseService.findById(querywCaseId);
			String isDue = wxCase.getIsDue();
			// 校验验证码
			boolean flag = false;
			if (isDue != null && "1".equals(isDue)) {
				// 有争议
				flag = verify(accident1.getMobile(), code1, 5) && verify(accident2.getMobile(), code2, 5);
				wxCase.setStatus("6");
			} else {
				// 无争议
				flag = verify(accident1.getMobile(), code1, 6)
						&& verify(accident2.getMobile(), code2, 6);
				wxCase.setStatus("5");
			}
			if (flag) {
				result.put("state", SUCCESS);
				result.put("msg", "");
				rebuildWxCaseLocation(wxCase);
				wxCaseService.save(wxCase);
				wxCaseService.saveCaseAndAccident(wxCase);
				// 保存任务
				WxTask task = new WxTask();
				task.setTaskId(0);
				task.setCaseId(wxCase.getRealCaseId());
				task.setCreateTime(new Date());
				task.setCreateCode("admin");
				task.setCreateName("admin");
				task.setStatus("1");
				task.setRegFrom("0");
				task.setIsDue(isDue);
				wxTaskService.addWxTask(task);
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
					logger.info("task:"
							+ net.sf.json.JSONObject.fromObject(task));
					logger.info("通知人:");
					for (String string : arr) {
						logger.info(string);
					}
				} else {
					arr = new String[1];
					arr[0] = "admin";
					logger.info("不存在远程定责警察");
					logger.info("task:"
							+ net.sf.json.JSONObject.fromObject(task));
					logger.info("通知admin");
				}
				dwrPush.doPush("prepareAll", "testCall", "有新定责任务需要处理！", arr);
			} else {
				this.getRequest().setAttribute("errMsg", "验证码错误！！");
				result.put("state", ERROR);
				result.put("msg", "验证码错误！！");
				result.put("data", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", ERROR);
			result.put("msg", "验证码错误！！");
			result.put("data", null);
		}
		this.writeAjaxByMap(result);
		return NONE;
	}

	public String toFinish() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		WxCaseId queryWxCaseId = new WxCaseId();
		queryWxCaseId.setId(wxCaseId);
		try {
			WxCase queryWxCase = wxCaseService.findById(queryWxCaseId);
			QpTTPCaseId queryCaseId = new QpTTPCaseId();
			queryCaseId.setCaseId(queryWxCase.getRealCaseId());
			QpTTPCase queryCase = qpTTPCaseService
					.findQpTTPCaseByPK(queryCaseId);
			this.getRequest().setAttribute("case1", queryCase);
			List<QpTICAccident> accidents = qpTICAccidentService
					.findQpTICAccidentInfoOnly(queryWxCase.getRealCaseId());
			this.getRequest().setAttribute("accidents", accidents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改临时案件状态
	 * 
	 * @return
	 */
	public String finish() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		WxCase wxCase;
		try {
			WxCaseId caseId = new WxCaseId();
			caseId.setId(wxCaseId);
			wxCase = wxCaseService.findById(caseId);
			wxCase.setStatus("7");
			wxCaseService.save(wxCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 继续操作
	 * 
	 * @return
	 */
	public String continueTo() {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
			System.out.println("param:" + param);
			return "zhuce";
		}
		// 加载案件当事人信息
		String userCode = (String) this.getRequest().getSession()
				.getAttribute("userCode_weixin");
		if (StringUtils.isEmptyStr(userCode)) {
			return "zhuce";
		}
		try {
			WxCase wxCase = wxCaseService.findCurrentCaseByUser(userCode);
			String status = wxCase.getStatus();
			switch (status) {
			case "1":
				// 跳转挪车
				this.getResponse().sendRedirect(
						"/weixin/case/carRemind.do?wxCaseId="
								+ wxCase.getId().getId());
				break;
			case "2":
				// 跳转案件信息
				this.getResponse().sendRedirect(
						"/weixin/case/basicInfo.do?wxCaseId="
								+ wxCase.getId().getId());
				break;
			case "3":
				// 跳转当事人信息
				this.getResponse().sendRedirect(
						"/weixin/case/prepareAccidentEdit.do?wxCaseId="
								+ wxCase.getId().getId());
				break;
			case "4":
				// 跳转确认验证码
				this.getResponse().sendRedirect(
						"/weixin/case/toVerifCode.do?wxCaseId="
								+ wxCase.getId().getId());
				break;
			case "5":
				// 跳转查看协议书
				this.getResponse().sendRedirect(
						"/weixin/case/toFinish.do?wxCaseId="
								+ wxCase.getId().getId());
				break;
			case "6":
				// 跳转交警远程定责中
				this.getResponse().sendRedirect(
						"/pages/qp/weixin/case/waitForPoliceDoing.jsp?wxCaseId="
								+ wxCase.getId().getId() + "&param=" + param);
				break;
			default:
				// 提示无进行中案件
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private UserKeyService userKeyService;
	
	public UserKeyService getUserKeyService() {
		return userKeyService;
	}

	public void setUserKeyService(UserKeyService userKeyService) {
		this.userKeyService = userKeyService;
	}

	// 测试接口
	public String demo() {
		try {
			UserKey userKey = userKeyService.findByUserCode("test");
			System.out.println(userKey.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private WxCase rebuildWxCaseLocation(WxCase wxCase) {
		if (wxCase == null){
			return null;
		}
		wxCase.setProvince("430000");
		wxCase.setCity("430100");
		wxCase.setArea("430104");
		return wxCase;
	}
	
	private IQpTAsyncTaskService qpTAsyncTaskService;
	
	private IQpTTPFastCenterService qpTTPFastCenterService;
    
    public IQpTAsyncTaskService getQpTAsyncTaskService() {
		return qpTAsyncTaskService;
	}

	public void setQpTAsyncTaskService(IQpTAsyncTaskService qpTAsyncTaskService) {
		this.qpTAsyncTaskService = qpTAsyncTaskService;
	}
	
	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}
	
	@SuppressWarnings("unchecked")
	public String initEGovermentCase() {
		IQpTCOMDictionaryService iQpTCOMDictionaryService = (IQpTCOMDictionaryService) ServiceFactory.getService("iQpTCOMDictionaryService");
		try {
			List<QpTCOMDictionary> egoverments = iQpTCOMDictionaryService.getImagePath("EGOVERMENT");
			String initStatus = "";
			QpTCOMDictionary qpTCOMDictionary = new QpTCOMDictionary();
			for (QpTCOMDictionary q : egoverments) {
				if ("async_init".equals(q.getId().getCode())) {
					initStatus = q.getValue();
					qpTCOMDictionary = q;
				}
			}
			if ("1".equals(initStatus)) {
				QpTTPCase query = new QpTTPCase();
				query.setCaseTimeStart(DateUtil.fomatDate("2016-09-01"));
				List<QpTTPCase> qpTTPCases = qpTTPCaseService.findByQpTTPCase(query, 1, Integer.MAX_VALUE).getResult();
				for (QpTTPCase c : qpTTPCases) {
					try {
						if ("0".equals(c.getCaseType())) { //非高速案件
							QpTTPFastCenterId centerID = new QpTTPFastCenterId();
							centerID.setCenterId(c.getCenterId());
							QpTTPFastCenter center = qpTTPFastCenterService.findQpTTPFastCenterByPK(centerID);
							if ("430100".equals(center.getCityId())) { // 长沙市区案件 
								Map<String, String> params = new HashMap<String, String>();
								params.put("caseId", c.getCaseId());
								if("2".equals(c.getTpHandleStatus())
										|| "4".equals(c.getTpHandleStatus()) 
										|| "5".equals(c.getTpHandleStatus())) { // 定责完成案件
									logger.info("---- 新增caseID=" + c.getCaseId() + "的案件到待推送任务列表 ----");
									qpTAsyncTaskService.createTask(UploadCaseToCSHandler.type, params);
								}
							}
						}
					} catch (Exception e) {
						logger.info("---- 新增任务到待推送列表失败, caseID=" + c.getCaseId() + " ----");
						logger.error("", e);
					}
				}
				qpTCOMDictionary.setValue("2");
				iQpTCOMDictionaryService.save(qpTCOMDictionary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.writeJSONMsg("初始化失败");
			return NONE;
		}
		this.writeJSONMsg("初始化完成");
		return NONE;
	}
	
}
