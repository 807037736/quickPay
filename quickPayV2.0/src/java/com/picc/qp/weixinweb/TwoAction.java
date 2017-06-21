package com.picc.qp.weixinweb;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxCaseId;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICPictureGroupService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.wx.facade.WxCaseService;
import com.picc.qp.util.Constants;
import com.picc.qp.util.Sign;
import com.picc.um.schema.model.UmTUserBound;
import com.picc.um.service.facade.IUmTUserBoundService;

@SuppressWarnings("unchecked")
public class TwoAction extends Struts2Action {

    private IQpTCommonService qpTCommonService;

    private WxCaseService wxCaseService;

    private IQpTICPictureService qpTICPictureService;

    private IQpTICPictureGroupService qpTICPictureGroupService;

    private IQpTTPCaseService qpTTPCaseService;

    private IQpTICAccidentService qpTICAccidentService;

    private IUmTUserBoundService umTUserBoundService;

    private WxCase wxCase;

    /** 案件对象  **/
    private QpTTPCase qpTTPCase;

    private String groupId;

    private String acciId;

    private String wxCaseId;

    private String param;

    public IQpTCommonService getQpTCommonService() {
	return qpTCommonService;
    }

    public void setQpTCommonService(IQpTCommonService qpTCommonService) {
	this.qpTCommonService = qpTCommonService;
    }

    public WxCaseService getWxCaseService() {
	return wxCaseService;
    }

    public void setWxCaseService(WxCaseService wxCaseService) {
	this.wxCaseService = wxCaseService;
    }

    public IQpTICPictureService getQpTICPictureService() {
	return qpTICPictureService;
    }

    public void setQpTICPictureService(IQpTICPictureService qpTICPictureService) {
	this.qpTICPictureService = qpTICPictureService;
    }

    public IQpTICPictureGroupService getQpTICPictureGroupService() {
	return qpTICPictureGroupService;
    }

    public void setQpTICPictureGroupService(IQpTICPictureGroupService qpTICPictureGroupService) {
	this.qpTICPictureGroupService = qpTICPictureGroupService;
    }

    public IQpTTPCaseService getQpTTPCaseService() {
	return qpTTPCaseService;
    }

    public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
	this.qpTTPCaseService = qpTTPCaseService;
    }

    public IQpTICAccidentService getQpTICAccidentService() {
	return qpTICAccidentService;
    }

    public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
	this.qpTICAccidentService = qpTICAccidentService;
    }

    public IUmTUserBoundService getUmTUserBoundService() {
	return umTUserBoundService;
    }

    public void setUmTUserBoundService(IUmTUserBoundService umTUserBoundService) {
	this.umTUserBoundService = umTUserBoundService;
    }

    public WxCase getWxCase() {
	return wxCase;
    }

    public void setWxCase(WxCase wxCase) {
	this.wxCase = wxCase;
    }

    public QpTTPCase getQpTTPCase() {
	return qpTTPCase;
    }

    public void setQpTTPCase(QpTTPCase qpTTPCase) {
	this.qpTTPCase = qpTTPCase;
    }

    public String getGroupId() {
	return groupId;
    }

    public void setGroupId(String groupId) {
	this.groupId = groupId;
    }

    public String getAcciId() {
	return acciId;
    }

    public void setAcciId(String acciId) {
	this.acciId = acciId;
    }

    public String getWxCaseId() {
	return wxCaseId;
    }

    public void setWxCaseId(String wxCaseId) {
	this.wxCaseId = wxCaseId;
    }

    public String getParam() {
	return param;
    }

    public void setParam(String param) {
	this.param = param;
    }

    /**
     * 挪车提醒  4
     * 
     * @return
     * @throws Exception 
     */
    public String carRemind() throws Exception {
	this.getRequest().setAttribute("param1", param);
	boolean flag = this.weiXinLogin(param);
	if (!flag) {
	    logger.info("进入挪车提醒界面返回注册界面,param:" + param);
	    return "zhuce";
	}

	return SUCCESS;
    }

    /**
     * 基本信息   6
     * 
     * @return
     */
    public String basicInfo() {
	try {
	    HttpServletRequest request = this.getRequest();

	    request.setAttribute("param1", param);
	    boolean flag = this.weiXinLogin(param);
	    if (!flag) {
		logger.info("进入基本信息界面返回注册界面,param:" + param);
		return "zhuce";
	    }

	    // 天气情况
	    List<QpSelectDataVo> weatherList =  qpTCommonService.getSelectData("WeatherType");
	    request.setAttribute("weatherList", weatherList);

	    WxCaseId wxCaseIdVo = new WxCaseId();
	    wxCaseIdVo.setId(this.wxCaseId);
	    wxCase = wxCaseService.findById(wxCaseIdVo);
	    if(StringUtils.isEmptyStr(wxCase)){
		request.setAttribute("errormsg", "案件信息不存在");
		//TODO
		//案件不存在
	    }else {
		wxCase.setStatus("2");
		wxCaseService.save(wxCase);
		if(!StringUtils.isEmptyStr(wxCase) && StringUtils.isEmptyStr(wxCase.getCreatedate())){
		    wxCase.setCreatedate(new Date());
		}
	    }
	    return SUCCESS;
	} catch (Exception e) {
	    logger.error("进入基本信息界面异常", e);
	}
	return SUCCESS;
    }

    /**
     * 保存临时案件基本信息 6保存
     * @return
     */
    public String saveBasicInfo() throws Exception{
	JSONObject jsonObject = new JSONObject();
	try {
	    this.getRequest().setAttribute("param1", param);
	    String showAddress = this.getRequest().getParameter("showAddress");
	    boolean flag = this.weiXinLogin(param);
	    if (!flag) {
		jsonObject.put("code", -999);
		this.writeJson(jsonObject);
		return NONE;
	    }
	    if(StringUtils.isEmptyStr(wxCaseId)){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "案件不存在");
		this.writeJson(jsonObject);
		return NONE;
	    }
	    if(StringUtils.isEmptyStr(wxCase.getAccidentdate())){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "请选择事故时间");
		this.writeJson(jsonObject);
		return NONE;
	    }
	    if(StringUtils.isEmptyStr(showAddress)){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "请选择事故地址");
		this.writeJson(jsonObject.toString());
		return NONE;
	    }
	    if(StringUtils.isEmptyStr(wxCase.getWeathercode()) || "0".equals(wxCase.getWeathercode())){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "请选择天气");
		this.writeJson(jsonObject.toString());
		return NONE;
	    }
	    if(StringUtils.isEmptyStr(wxCase.getAccidentform())  || "0".equals(wxCase.getAccidentform())){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "请选择事故形态");
		this.writeJson(jsonObject.toString());
		return NONE;
	    }

	    WxCaseId wxCaseId = new WxCaseId();
	    wxCaseId.setId(this.wxCaseId);
	    WxCase wxCase2 = wxCaseService.findById(wxCaseId);
	    if(StringUtils.isEmptyStr(wxCase2)){
		jsonObject.put("code", -1);
		jsonObject.put("msg", "案件不存在");
		this.writeJson(jsonObject);
		return NONE;
	    }
	    wxCase2.setStatus("3");
	    wxCase2.setAccidentform(wxCase.getAccidentform());
	    wxCase2.setProvince(wxCase.getProvince());
	    wxCase2.setCity(wxCase.getCity());
	    wxCase2.setArea(wxCase.getArea());
	    wxCase2.setAddress(wxCase.getAddress());
	    wxCase2.setAccidentdate(wxCase.getAccidentdate());
	    wxCase2.setWeathercode(wxCase.getWeathercode());
	    wxCaseService.save(wxCase2);
	    jsonObject.put("data", this.wxCaseId);
	    this.writeJson(jsonObject.toString());
	} catch (Exception e) {
	    logger.error("案件保存异常", e);
	    jsonObject.put("code", -1);
	    jsonObject.put("msg", "提交失败，请稍后在试");
	    this.writeJson(jsonObject.toString());
	}
	return NONE;
    }


    /******************************************微信查勘员上传图片************************************************/


    /**
     * 	微信查勘员上传图片viwe  1:首界面   输入认字编号
     * uploadStatus   0：认字编号界面    1：当事人信息界面   2：上传图片界面
     * @return
     */
    public String prepareSurveyQuery() throws Exception {
	HttpServletRequest request = this.getRequest();

	request.setAttribute("param1", param);
	boolean flag = this.weiXinLogin(param);
	if (!flag) {
	    logger.info("进入微信查勘员上传图片界面1返回注册界面,param:" + param);
	    return "zhuce";
	}
	String userCode = (String) request.getSession().getAttribute("userCode_weixin");
	if(StringUtils.isEmptyStr(userCode)){
	    return "zhuce";
	}

	UmTUserBound umTUser = new UmTUserBound();
	umTUser.setWxUserCode(userCode);
	umTUser = umTUserBoundService.isCKUser(umTUser);
	if(StringUtils.isEmptyStr(umTUser)){
	    request.setAttribute("errorMsg", "您暂时没有上传权限");
	    request.setAttribute("code", "-1");
	}
	return SUCCESS;
    }


    /**
     * 	微信查勘员上传图片viwe   2：查询当事人界面
     * @return
     */
    public String prepareSurveyView()  {
	HttpServletRequest httpServletRequest = this.getRequest();
	try {
	    httpServletRequest.setAttribute("param1", param);
	    boolean flag = this.weiXinLogin(param);
	    if (!flag) {
		logger.info("进入微信查勘员上传图片界面2返回注册界面,param:" + param);
		return "zhuce";
	    }
	    
	    String userCode = (String) httpServletRequest.getSession().getAttribute("userCode_weixin");
	    if(StringUtils.isEmptyStr(userCode)){
		return "zhuce";
	    }

	    UmTUserBound umTUser = new UmTUserBound();
	    umTUser.setWxUserCode(userCode);
	    umTUser = umTUserBoundService.isCKUser(umTUser);
	    if(StringUtils.isEmptyStr(umTUser)){
		httpServletRequest.setAttribute("errorMsg", "您暂时没有上传权限");
		httpServletRequest.setAttribute("code", "-1");
	    }

	    if(StringUtils.isEmptyStr(qpTTPCase) || StringUtils.isEmptyStr(qpTTPCase.getCaseSerialNo())){
		httpServletRequest.setAttribute("errorMsg", "请输入认字编号");
		return SUCCESS;
	    }
	    Page resultPage = qpTTPCaseService.findQpTTPCasePageBySql(qpTTPCase, page, rows);
	    if(resultPage.getTotalCount() < 1){
		httpServletRequest.setAttribute("errorMsg", "未查询到案件信息");
		return SUCCESS;
	    }
	    List<QpTTPCase> qpTTPCases = resultPage.getResult();
	    qpTTPCase = qpTTPCases.get(0);
	    String caseId = qpTTPCase.getCaseId();

	    List<QpTICAccident> qpTICAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
	    if(qpTICAccidents == null || qpTICAccidents.size() < 1){
		httpServletRequest.setAttribute("errorMsg", "该案件无当事人");
		httpServletRequest.setAttribute("uploadStatus", 999);
		return SUCCESS;
	    }
	    httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);
	    httpServletRequest.setAttribute("qpTICAccident", qpTICAccidents);
	    return SUCCESS;
	} catch (Exception e) {
	    logger.error("微信查勘员上传图片查询当事人失败", e);
	    httpServletRequest.setAttribute("errerMsg", "查询失败,请稍后在试");
	}
	return SUCCESS;
    }

    /**
     * 	微信查勘员上传图片viwe
     * @return
     */
    public String prepareSurveyUploadPictureGroup() throws Exception {
	HttpServletRequest httpServletRequest = this.getRequest();
	httpServletRequest.setAttribute("param1", param);
	boolean flag = this.weiXinLogin(param);
	if (!flag) {
	    logger.info("进入微信查勘员上传图片界面2返回注册界面,param:" + param);
	    return "zhuce";
	}
	   
	String userCode = (String) httpServletRequest.getSession().getAttribute("userCode_weixin");
	if(StringUtils.isEmptyStr(userCode)){
	    return "zhuce";
	}

	UmTUserBound umTUser = new UmTUserBound();
	umTUser.setWxUserCode(userCode);
	umTUser = umTUserBoundService.isCKUser(umTUser);
	if(StringUtils.isEmptyStr(umTUser)){
	    httpServletRequest.setAttribute("errorMsg", "您暂时没有上传权限");
	    httpServletRequest.setAttribute("code", "-1");
	}



	QpTICAccident qpTICAccident = new QpTICAccident();
	QpTICAccidentId id = new QpTICAccidentId();
	id.setAcciId(acciId);
	qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
	if(StringUtils.isEmptyStr(qpTICAccident)){
	    httpServletRequest.setAttribute("errorMsg", "当事人信息未找到");
	}else {
	    groupId = qpTICAccident.getSurveyGroupId();
	}
	if(!StringUtils.isEmptyStr(groupId)){
	    String root = ServletActionContext.getServletContext().getRealPath("/pages/qp/qpticpicturegroup/images/temp");
	    List<QpTICPicture> qpTICPictures = qpTICPictureService.findQpTICPictureByGroupId(groupId);

	    //微信查勘员上传图片时 查看图片
	    if (qpTICPictures != null && qpTICPictures.size() > 0) {
		List<QpTICPicture> filePathList = new ArrayList<QpTICPicture>();
		String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		root = root + "/" + currentTime;
		File savedir = new File(root);
		if (!savedir.exists())
		    savedir.mkdirs();
		root = root + "/" + groupId;
		// System.out.println("拷贝目录：" + root);
		File f = new File(root);
		if (!f.exists()) {
		    f.mkdir();
		}
		for (int i = 0; i < qpTICPictures.size(); i++) {
		    QpTICPicture ticPicture = qpTICPictures.get(i);
		    String fileNamePath = ticPicture.getFileName(); // 获取图片存在数据库的路径    D://image/xxx/xxx.jpg
		    String fileName = fileNamePath.substring(fileNamePath.lastIndexOf("/"), fileNamePath.length());// 获取图片名称   xxx.jpg
		    File file = new File(fileNamePath);
		    if (!file.exists()) {
			logger.info("file:" + file + "不存在");
			//	  			System.out.println("file:" + file + "不存在");
		    } else {
			try {
			    File file2 = new File(f + fileName);
			    if (!file2.exists()) {// 如果发现临时目录不存在图片就去把D盘的图片写进临时目录
				// 把所有文件存进输入流
				FileInputStream fis = new FileInputStream(file);
				// 构建输出流并指定路径
				FileOutputStream fos = new FileOutputStream(f + fileName);
				byte[] buffer = new byte[1024];

				@SuppressWarnings("unused")
				int length = 0;

				while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
				    fos.write(buffer);
				}
				fos.close();
				fis.close();
			    }
			    QpTICPicture qpTICPicturet = new QpTICPicture();
			    qpTICPicturet.setFileName("/pages/qp/qpticpicturegroup/images/temp" + "/" + currentTime + "/" + groupId + fileName);
			    qpTICPicturet.setPicDesc(fileNamePath); // 图片预览用到的文件名
			    qpTICPicturet.setPicOrder(ticPicture.getId().getPicId());
			    filePathList.add(qpTICPicturet);
			} catch (Exception e) {
			    e.printStackTrace();
			    logger.error("微信查勘员上传图片时查看图片出现异常", e);
			}

		    }

		}
		httpServletRequest.setAttribute("filePathList", filePathList);
	    }

	}
	logger.info("查询原图片：时间："+new Date()+",accId："+acciId+",组号：" + groupId);
	return SUCCESS;
    }

    /**
     * 查勘员上传图片  批量	
     * @return
     */
    public String getPictureWeixinSurvry() {
	HttpServletRequest request = getRequest();
	JSONObject jsonObject = new JSONObject();

	if(StringUtils.isEmptyStr(acciId)){
	    jsonObject.put("code", -1);
	    jsonObject.put("msg", "当事人id不存在,请返回重试");
	    this.writeJsonObject(jsonObject);
	    return NONE;
	}
	String userCode = "";
	try {
	    request.setAttribute("param1", param);
	    boolean flag = this.weiXinLogin(param);
	    if (!flag) {
		logger.info("查勘员上传图片返回注册界面,param:" + param);
		jsonObject.put("code", -999);//-999会跳转到注册界面
		this.writeJsonObject(jsonObject);
		return NONE;
	    }
	    userCode = (String) request.getSession().getAttribute("userCode_weixin");
	    if(StringUtils.isEmptyStr(userCode)){
		jsonObject.put("code", -999);
		this.writeJsonObject(jsonObject);
		return NONE;
	    }
	} catch (Exception e) {
	    jsonObject.put("code", -1);
	    jsonObject.put("msg", "上传失败，请稍后在试");
	    this.writeJsonObject(jsonObject);
	    return NONE;
	}
	String [] serverids = request.getParameterValues("servId");
	if (ToolsUtils.notEmpty(serverids)) {
	    try {                                                         
		String pictureType = "01";//02PC

		String ext = ".png";
		String imgPath = Constants.getIMAGEPATH();

		String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		imgPath = imgPath + "/" + currentTime;
		File savedir = new File(imgPath);
		// 获得当前的日期创建文件夹
		if (!savedir.exists()){
		    savedir.mkdirs();
		}
		QpTICPictureGroupId qpTICPictureGroupId = null;
		QpTICPictureGroup qpTICPictureGroupSave = null;
		//如果没有组号 则创建图片组
		if (ToolsUtils.isEmpty(groupId)) {
		    // 组号（P + '时间，精确到秒' + 四位随机数）
		    groupId = qpTICPictureService.getGroupID();
		    qpTICPictureGroupId = new QpTICPictureGroupId();
		    Date currentDate = new Date();
		    qpTICPictureGroupId.setGroupId(groupId);
		    qpTICPictureGroupSave = new QpTICPictureGroup();
		    qpTICPictureGroupSave.setId(qpTICPictureGroupId);

		    qpTICPictureGroupSave.setCreatorCode(userCode);
		    qpTICPictureGroupSave.setInsertTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadUserCode(userCode);
		    qpTICPictureGroupSave.setValidStatus("0");

		    qpTICPictureGroupSave.setPictureType(pictureType);

		    qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
		}

		// 获得当前的ID创建文件夹
		imgPath = imgPath + "/" + groupId;
		File savedir1 = new File(imgPath);
		if (!savedir1.exists()){
		    savedir1.mkdirs();
		}
		org.activiti.engine.impl.util.json.JSONObject accessTokenJsonObj = (org.activiti.engine.impl.util.json.JSONObject)Constants.getWX_ACCESS_TOKEN().get("token");//(org.activiti.engine.impl.util.json.JSONObject) getSession().getAttribute("accessTokenJsonObj");
		String token = accessTokenJsonObj.getString("access_token");
		logger.info("微信图片上传,上传人："+userCode+",时间："+new Date()+"一共"+serverids.length+"张保存路径：" +imgPath);
		for (String serverid : serverids) {
		    // InputStream inputStream = null;
		    QpTICPicture qpTICPictureSave = new QpTICPicture();
		    // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		    byte[] data = readInputStream(Sign.downloadPicture(token, serverid));
		    // new一个文件对象用来保存图片
		    File imageFile = new File(imgPath + "/", serverid + ext);
		    // 创建输出流
		    FileOutputStream outStream = new FileOutputStream(imageFile);
		    // 写入数据
		    outStream.write(data);
		    // 关闭输出流
		    outStream.close();

		    qpTICPictureSave.setFileName(imgPath + "/" + serverid + ext);
		    qpTICPictureSave.setGroupId(groupId);
		    qpTICPictureSave.setUploadTime(new Date());
		    qpTICPictureSave.setOriginalFileName("微信serverid:" + serverid);
		    qpTICPictureSave.setValidStatus("4");
		    qpTICPictureSave.setAcciId(acciId);

		    qpTICPictureService.saveQpTICPicture(qpTICPictureSave, userCode);
		}
		QpTICAccidentId id = new QpTICAccidentId();
		id.setAcciId(acciId);
		QpTICAccident qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(id);
		qpTICAccident.setSurveyGroupId(groupId);
		qpTICAccidentService.updateQpTICAccident(qpTICAccident);
		
		logger.info("上传成功" + serverids.toString() + ",groupId:" + groupId + ",accid:" + acciId);
		jsonObject.put("code", 0);
		jsonObject.put("msg", "上传成功");
		jsonObject.put("data", groupId);
		this.writeJsonObject(jsonObject);
		return NONE;
	    } catch (Exception e) {
		e.printStackTrace();
		logger.error("查勘员批量上传图片异常", e);
		jsonObject.put("code", -1);
		jsonObject.put("msg", "上传失败，请稍后重试");
		this.writeJsonObject(jsonObject);
		return NONE;
	    }
	}
	jsonObject.put("code", -1);
	jsonObject.put("msg", "请添加要上传的图片");
	this.writeJsonObject(jsonObject);
	return NONE;
    }

    public String getPictureWeixinSuccess(){
	this.getRequest().setAttribute("title", "图片上传");
	this.getRequest().setAttribute("butMsg", "图片上传成功！");
	return SUCCESS;
    }

    public String weixin2() throws Exception {
	HttpServletRequest request = getRequest();
	String appId = request.getParameter("appId");
	String appSecret = request.getParameter("appSecret");
	String url = request.getParameter("url");
	Map<String, Object> ret = new HashMap<String, Object>();
	System.out.println(url);
	if(StringUtils.isEmptyStr(getSession().getAttribute(url))){
	    System.out.println("生成session");
	    ret = Sign.sign(appId, appSecret, url);
	    getSession().setAttribute(url, ret);
//	    getSession().setAttribute("accessTokenJsonObj", ret.get("accessTokenJsonObj"));
	}else{
	    System.out.println("存在session");
	    ret = (HashMap<String, Object>)getSession().getAttribute(url);
	}
	System.out.println(ret);
	this.writeAjaxByMap(ret);
	return NONE;
    }

    public String weixin(){
	try {
	    HttpServletRequest request = getRequest();
	    Map<String, Object> ret = new HashMap<String, Object>();
	    //fff
	    //appId : 'wxc54c7a8be1edee7e',
	    //appSecret : '0c4bb6b13470ce5f6191bfd391048992',
	    //qiang
	    //appId : 'wxb620b2136e9f110e',
	    //appSecret : 'd4624c36b6795d1d99dcf0547af5443d',
	    //gang
	    //appId : "wxb45676f77463f5cb",
	    //appSecret : "5d159a729e3701a2daa3e72f52264fc4",
	    //生产
	    //appId:'wx737f5443b8ea85aa',
	    //appSecret:'b89f12a66755e79921ebde59771917c0',
	    String url = request.getParameter("url");
	    ret = Sign.signWithConstants(Constants.getJSSDK_APPID(), Constants.getJSSDK_APPSECRET(), url);
	    this.writeAjaxByMap(ret);
	    return NONE;

	} catch (Exception e) {
	    logger.info("获取token出现异常" + e);
	    e.printStackTrace();
	}
	return NONE;
    }

    /*********************************************当事人上传**************************************************/

    /**
     * 准备上传照片组	微信当事人上传图片viwe   --自主定责
     * 
     * @author chen
     * 
     * @return
     * @throws Exception
     *             createtime：2015-12-11 下午12:04:46
     */
    public String prepareUploadPictureGroup() throws Exception {
	this.getRequest().setAttribute("param1", param);
	boolean flag = this.weiXinLogin(param);
	if (!flag) {
	    logger.info("当事人上传界面:param:" + param);
	    return "zhuce";
	}
	return SUCCESS;
    }


    /**
     * 微信当事人上传图片动作  --自主定责
     * @return
     */
    public String getPictureWeixin() {
	HttpServletRequest request = getRequest();
	String serverid = request.getParameter("serverid");
	if (ToolsUtils.notEmpty(serverid)) {

	    try {
		this.getRequest().setAttribute("param1", param);
		boolean flag = this.weiXinLogin(param);
		if (!flag) {
		    this.writeJSONMsg("-999");
		    return NONE;
		}

		String usercode = (String) getSession().getAttribute("userCode_weixin");
		if(StringUtils.isEmptyStr(usercode)){
		    this.writeJSONMsg("-999");
		    return NONE;
		}
		String pictureType = null;
		//  		if (ToolsUtils.isEmpty(userCode)) {
		//  		    SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("SessionUser");
		//  		    userCode = sessionUser.getUserCode();
		//  		    pictureType = "02"; // PC端
		//  		} else {
		pictureType = "01"; // 微信端
		//  		}

		String ext = ".png";
		String imgPath = Constants.getIMAGEPATH();
		// 获得当前的日期创建文件夹
		String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		imgPath = imgPath + "/" + currentTime;
		File savedir = new File(imgPath);
		if (!savedir.exists())
		    savedir.mkdirs();
		QpTICPictureGroupId qpTICPictureGroupId = null;
		QpTICPictureGroup qpTICPictureGroupSave = null;
		if (ToolsUtils.isEmpty(groupId)) {
		    // 组号（P + '时间，精确到秒' + 四位随机数）
		    groupId = qpTICPictureService.getGroupID();
		    qpTICPictureGroupId = new QpTICPictureGroupId();
		    Date currentDate = new Date();
		    qpTICPictureGroupId.setGroupId(groupId);
		    qpTICPictureGroupSave = new QpTICPictureGroup();
		    qpTICPictureGroupSave.setId(qpTICPictureGroupId);

		    qpTICPictureGroupSave.setCreatorCode(usercode);
		    qpTICPictureGroupSave.setInsertTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadTimeForHis(currentDate);
		    qpTICPictureGroupSave.setUploadUserCode(usercode);
		    qpTICPictureGroupSave.setValidStatus("0");

		    qpTICPictureGroupSave.setPictureType(pictureType);

		    qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
		}

		// 获得当前的ID创建文件夹
		imgPath = imgPath + "/" + groupId;
		File savedir1 = new File(imgPath);
		if (!savedir1.exists())
		    savedir1.mkdirs();

		org.activiti.engine.impl.util.json.JSONObject jSONObject = (org.activiti.engine.impl.util.json.JSONObject)Constants.getWX_ACCESS_TOKEN().get("token");//(org.activiti.engine.impl.util.json.JSONObject) getSession().getAttribute("accessTokenJsonObj");
		// InputStream inputStream = null;
		QpTICPicture qpTICPictureSave = null;
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		InputStream inputStream = Sign.downloadPicture(jSONObject.getString("access_token"), serverid);
		byte[] data = readInputStream(inputStream);
		// new一个文件对象用来保存图片
		File imageFile = new File(imgPath + "/", serverid + ext);
		// 创建输出流
		FileOutputStream outStream = new FileOutputStream(imageFile);
		// 写入数据
		outStream.write(data);
		// 关闭输出流
		outStream.close();

		qpTICPictureSave = new QpTICPicture();
		qpTICPictureSave.setFileName(imgPath + "/" + serverid + ext);
		qpTICPictureSave.setGroupId(groupId);

		// qpTICPictureSave.setAcciId(acciId);
		qpTICPictureService.saveQpTICPicture(qpTICPictureSave, usercode);

		this.writeJSONMsg(groupId);
		return NONE;
	    } catch (Exception e) {
		this.writeJSONMsg(ERROR);
		e.printStackTrace();
		logger.error("", e);
		return NONE;
	    }
	}
	this.writeJSONMsg(ERROR);
	return NONE;
    }

    /**
     * 自助定责 当事人上传图片 绑定照片组号流程
     * @return
     * @throws Exception
     */
    public String updatePictureGroup() throws Exception {
	JSONObject jsonObject = new JSONObject();
	this.getRequest().setAttribute("param1", param);
	boolean flag = this.weiXinLogin(param);
	if (!flag) {
	    jsonObject.put("code", -999);
	    jsonObject.put("msg", "未登录");
	    this.writeJson(jsonObject.toString());
	    return NONE;
	}

	String usercode = (String) getSession().getAttribute("userCode_weixin");
	if(StringUtils.isEmptyStr(usercode)){
	    jsonObject.put("code", -999);
	    jsonObject.put("msg", "未登录");
	    this.writeJson(jsonObject.toString());
	    return NONE;
	}

	String groupId = this.getRequest().getParameter("groupId");
	QpTICPictureGroupId ticPictureGroupId = new QpTICPictureGroupId();
	ticPictureGroupId.setGroupId(groupId);
	QpTICPictureGroup ticPictureGroupUpdate = qpTICPictureGroupService.findQpTICPictureGroupByPK(ticPictureGroupId);
	if (ticPictureGroupUpdate != null) {
	    ticPictureGroupUpdate.setValidStatus("1");
	    ticPictureGroupUpdate.setUploadTimeForHis(new Date());
	    qpTICPictureGroupService.updateQpTICPictureGroup(ticPictureGroupUpdate);

	    WxCaseId wxCaseIdVo = new WxCaseId();
	    wxCaseIdVo.setId(wxCaseService.getWxCaseId());
	    wxCase = new WxCase();
	    wxCase.setId(wxCaseIdVo);
	    wxCase.setGroupid(groupId);
	    wxCase.setCreatecode(usercode);
	    //	    wxCase.setCreatecode("test8888");
	    wxCase.setStatus("1");
	    wxCase.setCreatedate(new Date());
	    wxCaseService.save(wxCase);
	    logger.info("图片上传成功");
	    jsonObject.put("msg", "上传成功");
	    jsonObject.put("data", wxCase.getId().getId());
	    this.writeJson(jsonObject.toString());
	    return NONE;
	} else {
	    jsonObject.put("code", -1);
	    this.writeJson(jsonObject.toString());
	    return NONE;
	}
    }


    /*************************************辅助方法**************************************************/

    public static byte[] readInputStream(InputStream inStream) throws Exception {
	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	// 创建一个Buffer字符串
	byte[] buffer = new byte[1024];
	// 每次读取的字符串长度，如果为-1，代表全部读取完毕
	int len = 0;
	// 使用一个输入流从buffer里把数据读取出来
	while ((len = inStream.read(buffer)) != -1) {
	    // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
	    outStream.write(buffer, 0, len);
	}
	// 关闭输入流
	inStream.close();
	// 把outStream里的数据写入内存
	return outStream.toByteArray();
    }


    private String readAll(Reader rd) throws IOException {
	StringBuilder sb = new StringBuilder();
	int cp;
	while ((cp = rd.read()) != -1) {
	    sb.append((char) cp);
	}
	return sb.toString();
    }

    @SuppressWarnings("static-access")
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	InputStream is = new URL(url).openStream();
	try {
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    JSONObject json = new JSONObject().fromObject(jsonText);
	    return json;
	} finally {
	    is.close();
	    // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
	}
    }


    public String getIpAddr(HttpServletRequest request) {
	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
	    ip = request.getRemoteAddr();
	}
	return ip;
    }
}
