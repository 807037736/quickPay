/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.web.Struts2Action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;
import com.picc.qp.schema.model.QpTICPictureId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICPictureGroupService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.util.CodeUtils;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;
import com.picc.um.schema.vo.SessionUser;

@SuppressWarnings("serial")
public class QpTICPictureAction extends Struts2Action {
    // 上传文件对象(和表单type=file的name值一致)
    private File file;
    private String accidentAcciId;
    private IQpTICPictureService qpTICPictureService;
    private String fileName;
    private IQpTICAccidentService qpTICAccidentService;
    private IQpTICPictureGroupService qpTICPictureGroupService;
    private IQpTTPCaseService qpTTPCaseService;

    public IQpTTPCaseService getQpTTPCaseService() {
	return qpTTPCaseService;
    }

    public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
	this.qpTTPCaseService = qpTTPCaseService;
    }

    public File getFile() {
	return file;
    }

    public void setFile(File file) {
	this.file = file;
    }

    public String getAccidentAcciId() {
	return accidentAcciId;
    }

    public void setAccidentAcciId(String accidentAcciId) {
	this.accidentAcciId = accidentAcciId;
    }

    public IQpTICPictureService getQpTICPictureService() {
	return qpTICPictureService;
    }

    public void setQpTICPictureService(IQpTICPictureService qpTICPictureService) {
	this.qpTICPictureService = qpTICPictureService;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public IQpTICAccidentService getQpTICAccidentService() {
	return qpTICAccidentService;
    }

    public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
	this.qpTICAccidentService = qpTICAccidentService;
    }

    public IQpTICPictureGroupService getQpTICPictureGroupService() {
	return qpTICPictureGroupService;
    }

    public void setQpTICPictureGroupService(IQpTICPictureGroupService qpTICPictureGroupService) {
	this.qpTICPictureGroupService = qpTICPictureGroupService;
    }

    public void createSurveyGroup() throws Exception {

	String surveyGroupId = (String) this.getRequest().getSession().getAttribute("surveyGroupId");
	String userCode = (String) this.getRequest().getSession().getAttribute("userCode_weixin");
	String pictureType = null;
	if (ToolsUtils.isEmpty(userCode)) {
	    SessionUser sessionUser = (SessionUser) getRequest().getSession().getAttribute("SessionUser");
	    userCode = sessionUser.getUserCode();
	    pictureType = "02"; // PC端
	} else {
	    pictureType = "01"; // 微信端
	}

	QpTICPictureGroupId qpTICPictureGroupId = null;
	QpTICPictureGroup qpTICPictureGroupSave = null;
	// String currentTime = new SimpleDateFormat("yyyyMMdd").format(new
	// Date());
	// Random random = new Random();
	// 组号（P + '时间，精确到秒' + 四位随机数）
	// 照片组号为空
	if (ToolsUtils.isEmpty(surveyGroupId)) {
	    String groupId = qpTICPictureService.getGroupID();
	    if (!ToolsUtils.isEmpty(accidentAcciId) && pictureType.equals("02")) {
		QpTICAccident qpTICAccident = new QpTICAccident();

		QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
		qpTICAccidentId.setAcciId(accidentAcciId);
		qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);
		Date currentDate = new Date();
		qpTICPictureGroupId = new QpTICPictureGroupId();

		if (ToolsUtils.isEmpty(qpTICAccident.getSurveyGroupId())) {
		    qpTICPictureGroupId.setGroupId(groupId);
		    qpTICPictureGroupSave = new QpTICPictureGroup();
		    qpTICPictureGroupSave.setId(qpTICPictureGroupId);

		    qpTICPictureGroupSave.setCreatorCode(userCode);
		    qpTICPictureGroupSave.setInsertTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadUserCode(userCode);
		    qpTICPictureGroupSave.setValidStatus("1");

		    qpTICPictureGroupSave.setPictureType(pictureType);

		    qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);

		    qpTICAccident.setSurveyGroupId(groupId);
		    qpTICAccident.setOperateTimeForHis(currentDate);

		    // 查勘估损保存后把状态改成 查勘处理中 状态 wyq
		    QpTTPCaseId caseId = new QpTTPCaseId();
		    caseId.setCaseId(qpTICAccident.getCaseId());
		    QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(caseId);
		    qpTTPCase.setTpHandleStatus("4");
		    qpTTPCaseService.updateQpTTPCasePicGroupId(qpTTPCase);// 更新QpTTPCase信息

		    qpTICAccidentService.updateQpTICAccident(qpTICAccident);
		    surveyGroupId = groupId;
		} else {
		    surveyGroupId = qpTICAccident.getSurveyGroupId();
		}
	    }
	    this.writeJSONMsg(surveyGroupId);
	} else {
	    this.writeJSONMsg(surveyGroupId);
	}

    }

    public String doUpload() throws Exception {
	Map<String, Object> rMap = new HashMap<String, Object>();
	try {
	    HttpServletRequest request = this.getRequest();
	    ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
	    String userCode = (String) request.getSession().getAttribute("userCode_weixin");
	    String surveyGroupId = request.getParameter("surveyGroupId");
	    accidentAcciId = request.getParameter("accidentAcciId");
	    String fileName = request.getParameter("name");
	    String url = request.getParameter("url");
	    String originalUrl = request.getParameter("resource_url");
	    //	String surveyGroupId = (String) this.getRequest().getParameter("surveyGroupId");
	    logger.info("上传照片时间:" + DateUtil.dateFormat(new Date(), DateUtil.FULL_DATE_STR) + " 照片组号：" + surveyGroupId);
	    if (ToolsUtils.isEmpty(surveyGroupId)) {
		logger.info("上传文件出错！组号不存在");
		throw new Exception("上传文件出错！");
	    }
	    SessionUser sessionUser = null;
	    if (ToolsUtils.isEmpty(userCode)) {
		sessionUser = (SessionUser) getRequest().getSession().getAttribute("SessionUser");
		if(sessionUser != null)
		userCode = sessionUser.getUserCode();
	    }
	    QpTICPicture qpTICPicture = new QpTICPicture();
	    qpTICPicture.setAcciId(accidentAcciId);
	    qpTICPicture.setFileName(url);
	    qpTICPicture.setGroupId(surveyGroupId);
	    qpTICPicture.setOriginalFileName(originalUrl);
	    qpTICPicture.setValidStatus("4");// 新增状态为4
	    qpTICPicture.setPicDesc(fileName);
	    qpTICPictureService.saveQpTICPicture(qpTICPicture, userCode);
	    logger.info("用户[" + sessionUser != null ? sessionUser.getUserName() : "微信用户" + "]在" + new Date() + "上传图片:组号:" + surveyGroupId + ",data:"+ new JSONObject().fromObject(qpTICPicture));
	    rMap.put("msg", surveyGroupId);
	    rMap.put("uploadStatus", "success");
	} catch (Exception e) {
	    rMap.put("uploadStatus", "fail");
	}
	this.writeAjaxByMap(rMap);
	return NONE;
    }

    public String prepareUploadPic() {
	HttpServletRequest httpServletRequest = this.getRequest();
	String acciId = httpServletRequest.getParameter("acciId");
	String surveyGroupId = httpServletRequest.getParameter("SurveyGroupId");
	httpServletRequest.setAttribute("acciId", acciId);
	httpServletRequest.setAttribute("SurveyGroupId", surveyGroupId);
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("t", new Date().getTime());
	httpServletRequest.setAttribute("dataTime", jsonObject);
	httpServletRequest.setAttribute("imgUser", Constants.getIMG_USER());
	httpServletRequest.setAttribute("uploadImgUrl", Constants.getIMAGEHTTPPATH() + "/rest/file/uploadImgByForm/"+Constants.getIMG_PLAT()+"/");
	httpServletRequest.setAttribute("mac", CodeUtils.getSign(Constants.getIMG_USER_KEY(), jsonObject));
	// 上传图片时--查看图片
	try {
	    List<QpTICPicture> ticPicturesList = qpTICPictureService.findQpTICPictureByGroupId(surveyGroupId);
	    ActionContext.getContext().put("SurveyGroupId", surveyGroupId);
	    ActionContext.getContext().put("accidentGroupId", surveyGroupId);
	    ActionContext.getContext().put("filePathList", ticPicturesList);
	    ActionContext.getContext().put("imgHeader", Constants.getIMAGEHTTPQUERY());
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	}
	return SUCCESS;
    }

    /**
     * 删除图片
     * @return
     */
    public String deleteQpTICPicture() {
	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    HttpServletRequest httpServletRequest = this.getRequest();
	    String picId = httpServletRequest.getParameter("picId");
	    boolean flag = true;
	    if (StringUtils.isNotEmpty(picId)) {
		QpTICPictureId qpTICPictureId = new QpTICPictureId();
		qpTICPictureId.setPicId(picId);
		QpTICPicture qpTICPicture = qpTICPictureService.findQpTICPictureByPK(qpTICPictureId);
		if (qpTICPicture != null) {
		    //TODO  删除图片服务器图片
		    JSONObject param = new JSONObject();
		    JSONArray imgs = new JSONArray();
		    JSONObject imgs1 = new JSONObject();
		    JSONObject img = new JSONObject();
		    img.put("url", qpTICPicture.getFileName());
		    imgs.add(img);
		    imgs1.put("imgs", imgs);
		    param.put("u", Constants.getIMG_USER());
		    param.put("data", imgs1);
		    param.put("mac", CodeUtils.getSign(Constants.getIMG_USER_KEY(), imgs1));
		    Map<String, Object> httpResult = HttpClientUtils.HttpClientJsonPost(Constants.getIMAGEHTTPPATH() + "/rest/file/delImg", param.toString(), "UTF-8");
		    logger.info("删除图片result:" + httpResult);
		    JSONObject result = new JSONObject();
		    if("200".equals(httpResult.get("code"))){
			    result = JSONObject.fromObject(httpResult.get("info"));
			if("00".equals(result.getString("state"))){
			    qpTICPictureService.deleteByPK(qpTICPictureId);
			}else {
			    flag = false;
			}
		    }else {
			flag = false;
		    }
		    
		}
	    }
	    if(flag){
		map.put("status", 0);
		map.put("msg", "删除成功");
	    }else {
		map.put("status", -1);
		map.put("msg", "删除失败,请稍后在试");
	    }
	    
	} catch (Exception e) {
	    logger.error("删除图片出现异常" + e);
	    map.put("status", -1);
	    map.put("msg", "删除出现异常,请稍后在试");
	}
	this.writeAjaxByMap(map);
	return NONE;
    }

    public String prepareUpdateFileName() {
	try {
	    List<QpTICPicture> qpTICPictures = qpTICPictureService.findQpTICPictureAll();
	    this.getRequest().setAttribute("count", qpTICPictures.size());
	} catch (Exception e) {
	    logger.error("查询图片出现异常" + e);
	}

	return SUCCESS;
    }

    public String updatePicFileName() {
	Map<String, Object> map = new HashMap<String, Object>();
	int page = Integer.valueOf(this.getRequest().getParameter("page").toString());
	int pageSize = Integer.valueOf(this.getRequest().getParameter("pageSize").toString());
	try {
	    if (page == 0) {
		// 查询出所有图片 按组号排序
		List<QpTICPicture> qpTICPictures = qpTICPictureService.findQpTICPictureAll();
		System.out.println("总图片数量:" + qpTICPictures.size() + ",每次修改" + pageSize + "条数据,一共需要修改" + qpTICPictures.size() / pageSize + "次");
	    }
	    System.out.println("第" + (page + 1) + "次修改开始。。。");
	    qpTICPictureService.updateFileName(page * pageSize, pageSize);
	    map.put("status", 0);
	    map.put("msg", "第" + (page + 1) + "次修改成功。。。");
	    System.out.println("第" + (page + 1) + "次修改成功。。。");
	} catch (Exception e) {
	    logger.error("同步图片地址出现异常 " + e);
	    map.put("status", -1);
	    map.put("msg", "第" + (page + 1) + "次出现异常，数据已回滚。。。");
	}
	this.writeAjaxByMap(map);
	return NONE;
    }

}
