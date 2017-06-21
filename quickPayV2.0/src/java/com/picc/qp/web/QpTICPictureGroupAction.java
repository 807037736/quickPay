/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICPictureGroupService;
import com.picc.qp.service.facade.IQpTICPictureService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.util.Constants;
import com.picc.qp.util.Sign;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserBindService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class QpTICPictureGroupAction extends Struts2Action {

    private List<File[]> images;

    private List<String> imagesName;

    private IQpTICPictureService qpTICPictureService;

    private IQpTICPictureGroupService qpTICPictureGroupService;

    private IUmTUserBindService umTUserBindService;

    private IQpTTPCaseService qpTTPCaseService;

    private IQpTICAccidentService qpTICAccidentService;
    

    public IQpTICAccidentService getQpTICAccidentService() {
	return qpTICAccidentService;
    }

    public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
	this.qpTICAccidentService = qpTICAccidentService;
    }

    public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
	this.qpTTPCaseService = qpTTPCaseService;
    }

    public IQpTTPCaseService getQpTTPCaseService() {
	return qpTTPCaseService;
    }

    public void setQpTICPictureGroupService(IQpTICPictureGroupService qpTICPictureGroupService) {
	this.qpTICPictureGroupService = qpTICPictureGroupService;
    }

    public IQpTICPictureGroupService getQpTICPictureGroupService() {
	return qpTICPictureGroupService;
    }

    public IUmTUserBindService getUmTUserBindService() {
	return umTUserBindService;
    }

    public void setUmTUserBindService(IUmTUserBindService umTUserBindService) {
	this.umTUserBindService = umTUserBindService;
    }

    public List<File[]> getImages() {
	return images;
    }

    public void setImages(List<File[]> images) {
	this.images = images;
    }

    public List<String> getImagesName() {
	return imagesName;
    }

    public void setImagesName(List<String> imagesName) {
	this.imagesName = imagesName;
    }

    public IQpTICPictureService getQpTICPictureService() {
	return qpTICPictureService;
    }

    public void setQpTICPictureService(IQpTICPictureService qpTICPictureService) {
	this.qpTICPictureService = qpTICPictureService;
    }

    private QpTICPictureGroup qpTICPictureGroup;

    private QpTICPictureGroupId id;

    /** 用户代码 **/
    private String userCode;

    /** 手机号码 **/
    private String mobile;

    /** 车牌号 **/
    private String licenseNo;

    /** 操作类型 **/
    private String operateType;

    /** 案件id **/
    private String caseId;

    /** 查勘groupId **/
    private String surveyGroupId;
    
    /** 案件对象  **/
    private QpTTPCase qpTTPCase;

    public String getSurveyGroupId() {
	return surveyGroupId;
    }

    public void setSurveyGroupId(String surveyGroupId) {
	this.surveyGroupId = surveyGroupId;
    }

    public String getCaseId() {
	return caseId;
    }

    public void setCaseId(String caseId) {
	this.caseId = caseId;
    }

    /** QpTICPictureGroup getter/setter **/
    public QpTICPictureGroup getQpTICPictureGroup() {
	return this.qpTICPictureGroup;
    }

    public void setQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup) {
	this.qpTICPictureGroup = qpTICPictureGroup;
    }

    /** QpTICPictureGroupId getter/setter **/
    public QpTICPictureGroupId getId() {
	return this.id;
    }

    public void setId(QpTICPictureGroupId id) {
	this.id = id;
    }

    /** operateType getter/setter **/
    public String getOperateType() {
	return operateType;
    }

    public void setOperateType(String operateType) {
	this.operateType = operateType;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getLicenseNo() {
	return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
	this.licenseNo = licenseNo;
    }

    /** 主键对象 */
    private String groupId;

    public String getGroupId() {
	return this.groupId;
    }

    public void setGroupId(String groupId) {
	this.groupId = groupId;
    }
    
    private String acciId;//案件iD

    public String getAcciId() {
        return acciId;
    }

    public void setAcciId(String acciId) {
        this.acciId = acciId;
    }

    
    public QpTTPCase getQpTTPCase() {
	return qpTTPCase;
    }
    
    public void setQpTTPCase(QpTTPCase qpTTPCase) {
	this.qpTTPCase = qpTTPCase;
    }
    
    /**************************** Function Start *******************************/
  

    /**
     * 准备查询方法，可以根据需要对需要初始化的文本赋值
     * 
     * @return
     */
    public String prepareQuery() throws Exception {
    	String param = this.getRequest().getParameter("param");
    	boolean flag = this.weiXinLogin(param);
    	if(flag){
    		userCode = (String) this.getRequest().getSession().getAttribute("userCode_weixin");
    		List<QpTICPictureGroup> qpTICPictureGroupList = qpTICPictureGroupService.findQpTICPictureGroupByUserCode(userCode);
    		this.getRequest().setAttribute("qpTICPictureGroupList", qpTICPictureGroupList);
    	}else{
    		return "zhuce";
    	}
    	/**
	if (ToolsUtils.notEmpty(param)) {
	    this.getSession().setAttribute("WX_USER_PARAM", param);
	} else {
	    param = (String) this.getSession().getAttribute("WX_USER_PARAM");
	}

	String platId = "";
	String openId = "";
	if (ToolsUtils.notEmpty(param)) {
	    String[] params = param.split("-");
	    platId = params[0];
	    openId = params[1];
	}
	List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
	if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
	    userList = umTUserBindService.findCustIdByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
	    // userList = umTUserBindService.findCustIdByOpenid(platId, openId);
	}
	if (!ToolsUtils.isEmpty(userList)) {
	    UmTUserBindVo umTUserBindVo = userList.get(0);

	    userCode = umTUserBindVo.getUserCode();
	    List<QpTICPictureGroup> qpTICPictureGroupList = qpTICPictureGroupService.findQpTICPictureGroupByUserCode(userCode);
	    this.getRequest().getSession().setAttribute("userCode_weixin", userCode);
	    this.getRequest().setAttribute("qpTICPictureGroupList", qpTICPictureGroupList);
	} else {
//		this.getRequest().setAttribute("param", param);
	    return "zhuce";
	}
    	 */
    	return SUCCESS;
    }

    /**
     * QpTICPictureGroup查询，根据qpTICPictureGroup带过来的值为查询条件进行查询。
     * 
     * @return
     */
    public String query() throws Exception {
	if (page == 0) {
	    page = 1;
	}
	if (rows == 0) {
	    rows = 20;
	}

	try {
	    Page resultPage = qpTICPictureGroupService.findByQpTICPictureGroup(qpTICPictureGroup, page, rows);
	    this.writeEasyUiData(resultPage);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	    this.writeAjaxErrorByMap(null);
	}
	return NONE;
    }

    /**
     * 准备查询方法，可以根据需要对需要初始化的文本赋值
     * 
     * @return
     */
    public String prepareDraw() throws Exception {

	return SUCCESS;
    }

    /**
     * QpTICPictureGroup查询，根据qpTICPictureGroup带过来的值为查询条件进行查询。
     * 
     * @return
     */
    public String queryForDraw() throws Exception {
	if (page == 0) {
	    page = 1;
	}
	if (rows == 0) {
	    rows = 20;
	}

	Map<String, Object> msg = new HashMap<String, Object>();

	try {
		if(StringUtils.isEmptyStr(mobile)&&StringUtils.isEmptyStr(licenseNo)){
			setMsg(msg, "0", "手机号和车牌号都为空！", null);
		}else{
			if (qpTICPictureGroup == null) {
				qpTICPictureGroup = new QpTICPictureGroup();
			}
			qpTICPictureGroup.setMobile(mobile);
			qpTICPictureGroup.setLicenseNo(licenseNo);

			Page resultPage = qpTICPictureGroupService.findByQpTICPictureGroupDraw(qpTICPictureGroup, page, rows);
			if (resultPage.getTotalCount() == 0) {
				setMsg(msg, "0", "未找到提取照片，请通知当事人及时上传照片！", null);
			} else if (resultPage.getTotalCount() == 1) {
				setMsg(msg, "1", "找到匹配照片记录", null);
				QpTICPictureGroup qpTICPictureGroupDraw = (QpTICPictureGroup) resultPage.getResult().get(0);
				msg.put("groupId", qpTICPictureGroupDraw.getGroupId());
			} else {
				setMsg(msg, "2", "找到匹配照片记录", null);
				QpTICPictureGroup qpTICPictureGroupDraw = (QpTICPictureGroup) resultPage.getResult().get(0);
				msg.put("groupId", qpTICPictureGroupDraw.getGroupId());
			}
		}
		this.writeJson(msg);

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	    setMsg(msg, "N", "提取照片异常,原因:" + e.getMessage(), null);
	    this.writeJson(msg);
	}
	return NONE;
    }

    /**
     * QpTICPictureGroup查询，根据qpTICPictureGroup带过来的值为查询条件进行查询。
     * 
     * @return
     */
    public String queryForDrawSelect() throws Exception {
	if (page == 0) {
	    page = 1;
	}
	if (rows == 0) {
	    rows = 20;
	}

	// Map<String, Object> msg = new HashMap<String, Object>();

	try {
	    if (qpTICPictureGroup == null) {
		qpTICPictureGroup = new QpTICPictureGroup();
	    }
	    qpTICPictureGroup.setMobile(mobile);
	    qpTICPictureGroup.setLicenseNo(licenseNo);

	    Page resultPage = qpTICPictureGroupService.findByQpTICPictureGroupDraw(qpTICPictureGroup, page, rows);
	    this.writeEasyUiData(resultPage);

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	    this.writeAjaxErrorByMap(null);
	}
	return NONE;
    }

    /**
     * 准备更新QpTICPictureGroup信息
     * 
     * @return
     */
    public String prepareUpdate() throws Exception {
	operateType = "update";
	qpTICPictureGroup = qpTICPictureGroupService.findQpTICPictureGroupByPK(id);
	return SUCCESS;
    }

    /**
     * 更新QpTICPictureGroup信息
     * 
     * @return
     */
    public String update() throws Exception {
	qpTICPictureGroupService.updateQpTICPictureGroup(qpTICPictureGroup);
	return SUCCESS;
    }

    /**
     * 准备增加QpTICPictureGroup信息
     * 
     * @return
     */
    public String prepareAdd() throws Exception {
	operateType = "add";
	return SUCCESS;
    }

    /**
     * 新增QpTICPictureGroup信息
     * 
     * @return
     */
    public String add() throws Exception {
	qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroup);
	return SUCCESS;
    }

    /**
     * 删除QpTICPictureGroup信息
     * 
     * @return
     */
    public String delete() throws Exception {
	try {
	    if (id != null) {
		qpTICPictureGroupService.deleteByPK(id);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("errorTitle", "错误信息(ERROR)：");
	    resultMap.put("errorMsg", "删除数据时发生异常！");
	    this.writeAjaxErrorByMap(resultMap);
	}
	return NONE;
    }

    /**
     * 查看QpTICPictureGroup信息方法
     * 
     * @return
     */
    public String view() throws Exception {
	operateType = "view";
	qpTICPictureGroup = qpTICPictureGroupService.findQpTICPictureGroupByPK(id);
	return SUCCESS;
    }

    /**
     * 设置返回的信息
     * 
     * @param msg
     * @param flag
     *            Y/N
     * @param content
     *            提示内容
     */
    private void setMsg(Map<String, Object> msg, String flag, String content, Map<String, String> result) {
	msg.put("flag", flag);
	msg.put("content", content);
	msg.put("result", result);
    }

    /**
     * 准备上传照片组	微信当事人上传图片viwe
     * 
     * @author chen
     * 
     * @return
     * @throws Exception
     *             createtime：2015-12-11 下午12:04:46
     */
    public String prepareUploadPictureGroup() throws Exception {
	HttpServletRequest httpServletRequest = this.getRequest();

	httpServletRequest.setAttribute("acciId", httpServletRequest.getParameter("acciId"));
	return SUCCESS;
    }

    /**
     * 上传图片组
     * 
     * @author chen
     * 
     * @return createtime：2015-12-10 下午7:45:54
     * @throws Exception
     */
    public String uploadPictureGroup() throws Exception {
	ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
	HttpServletRequest httpServletRequest = this.getRequest();

	if (images != null && images.size() > 0) {
	    String userCode = (String) this.getRequest().getSession().getAttribute("userCode_weixin");

	    String pictureType = null;
	    if (ToolsUtils.isEmpty(userCode)) {
		SessionUser sessionUser = (SessionUser) getRequest().getSession().getAttribute("SessionUser");
		userCode = sessionUser.getUserCode();
		pictureType = "02"; // PC端
	    } else {
		pictureType = "01"; // 微信端
	    }

	    // String realpath =
	    // ServletActionContext.getServletContext().getRealPath("/images");
//	    String realpath = Constants.getIMAGEPATH();
//	    if (StringUtils.isEmptyStr(realpath)) {
//		throw new Exception("上传图片组失败，路劲未配置");
//	    }
//	    // 获得当前的日期创建文件夹
//	    String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
//	    realpath = realpath + "/" + currentTime;
//	    File savedir = new File(realpath);
//	    if (!savedir.exists())
//		savedir.mkdirs();
	    QpTICPictureGroupId qpTICPictureGroupId = null;
	    QpTICPictureGroup qpTICPictureGroupSave = null;
	    String returnMsg = ERROR;
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
		qpTICPictureGroupSave.setValidStatus("1");

		qpTICPictureGroupSave.setPictureType(pictureType);

		qpTICPictureGroupService.saveQpTICPictureGroup(qpTICPictureGroupSave);
	    }

	    String acciId = httpServletRequest.getParameter("acciId");
	    if (!ToolsUtils.isEmpty(acciId) && pictureType.equals("02")) {
		QpTICAccident qpTICAccident = new QpTICAccident();

		QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
		qpTICAccidentId.setAcciId(acciId);
		qpTICAccident = qpTICAccidentService.findQpTICAccidentByPK(qpTICAccidentId);

		qpTICAccident.setSurveyGroupId(groupId);
		qpTICAccident.setOperateTimeForHis(new Date());
		qpTICAccidentService.updateQpTICAccident(qpTICAccident);

	    }

	    // 获得当前的ID创建文件夹
//	    realpath = realpath + "/" + groupId;
//	    File savedir1 = new File(realpath);
//	    if (!savedir1.exists())
//		savedir1.mkdirs();
//
//	    for (int i = 0; i < images.size(); i++) {
//		if (images.get(i) != null) {
//		    returnMsg = saveFile(images.get(i), imagesName.get(i), realpath, currentTime, savedir1, userCode, groupId, acciId);
//		}
//	    }
//
//	    if ("error".equals(returnMsg)) {
//		this.writeJSONMsg(ERROR);
//		return NONE;
//	    }
	    qpTICPictureGroupId = new QpTICPictureGroupId();
	    qpTICPictureGroupId.setGroupId(groupId);
	    QpTICPictureGroup qpTICPictureGroupUpdate = qpTICPictureGroupService.findQpTICPictureGroupByPK(qpTICPictureGroupId);
	    qpTICPictureGroupUpdate.setOperateTimeForHis(new Date());
	    qpTICPictureGroupUpdate.setUpdaterCode(userCode);
	    qpTICPictureGroupService.updateQpTICPictureGroup(qpTICPictureGroupUpdate);

	    this.writeJSONMsg(SUCCESS);
	    return NONE;
	}

	this.writeJSONMsg(ERROR);
	return NONE;
    }

    /**
     * 保存附件
     * 
     * @author chen
     * 
     * @param files
     * @param fileNames
     * @param realpath
     * @param currentTime
     * @param savedir
     * @param userCode
     * @param groupId
     * @throws Exception
     *             createtime：2015-12-11 下午2:43:24
     */
    private String saveFile(File[] files, String fileNames, String realpath, String currentTime, File savedir, String userCode, String groupId, String acciId) throws Exception {
	if (files != null) {
	    QpTICPicture qpTICPictureSave = null;
	    String[] fileName = fileNames.split(";");
	    for (int i = 0; i < files.length; i++) {

		// if (!savedir.exists()) {
		// savedir.mkdir();
		// }
		File savefile = new File(savedir, new Date().getTime() + "_" + fileName[i]);
		// File savefile = new File(savedir);
		String type = fileName[i].substring(fileName[i].lastIndexOf('.') + 1);
		if (type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpeg")) {
		    try {
			if (files[i].length() > 1024 * 1024) {
			    zipImageFile(files[i], savefile, 388 * 2, 165 * 2, 1);
			} else {
			    FileUtils.copyFile(files[i], savefile);
			}
			// zipWidthHeightImageFile(files[i], savefile, 80, 80,
			// 3);

			qpTICPictureSave = new QpTICPicture();
			qpTICPictureSave.setFileName(realpath + "/" + fileName[i]);
			qpTICPictureSave.setGroupId(groupId);

			qpTICPictureSave.setAcciId(acciId);
			qpTICPictureService.saveQpTICPicture(qpTICPictureSave, userCode);
			return SUCCESS;
		    } catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			return ERROR;
		    }
		    // FileUtils.copyFile(files[i], savefile);

		}
	    }

	}
	return ERROR;
    }

    /**
     * 等比例压缩图片文件<br>
     * 压缩、上传
     * 
     * @param oldFile
     *            要进行压缩的文件
     * @param newFile
     *            新文件
     * @param width
     *            宽度 //设置宽度时（高度传入0，等比例缩放）
     * @param height
     *            高度 //设置高度时（宽度传入0，等比例缩放）
     * @param quality
     *            质量
     * @return 返回压缩后的文件的全路径
     */
    private static String zipImageFile(File oldFile, File newFile, int width, int height, float quality) {
	if (oldFile == null) {
	    return null;
	}
	try {
	    /** 对服务器上的临时文件进行处理 */
	    Image srcFile = ImageIO.read(oldFile);
	    int w = srcFile.getWidth(null);
	    // System.out.println(w);
	    int h = srcFile.getHeight(null);
	    // System.out.println(h);
	    double bili;
	    if (width > 0) {
		bili = width / (double) w;
		height = (int) (h * bili);
	    } else {
		if (height > 0) {
		    bili = height / (double) h;
		    width = (int) (w * bili);
		}
	    }
	    /** 宽,高设定 */
	    BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
	    // String filePrex = oldFile.getName().substring(0,
	    // oldFile.getName().indexOf('.'));
	    /** 压缩后的文件名 */
	    // newImage = filePrex + smallIcon+
	    // oldFile.getName().substring(filePrex.length());

	    /** 压缩之后临时存放位置 */
	    FileOutputStream out = new FileOutputStream(newFile);

	    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	    JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
	    /** 压缩质量 */
	    jep.setQuality(quality, true);
	    encoder.encode(tag, jep);
	    out.close();

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    //logger.error("", e);
	} catch (IOException e) {
	    e.printStackTrace();
	    //logger.error("", e);
	}
	return newFile.getAbsolutePath();
    }

    public String viewPictureGroup() {
	if (page == 0) {
	    page = 1;
	}
	if (rows == 0) {
	    rows = 20;
	}
	try {
	    // 要下载过去的路径
//	    String root = ServletActionContext.getServletContext().getRealPath("/pages/qp/qpticpicturegroup/images/temp");
	    QpTICPicture qpTICPicture = new QpTICPicture();

	    HttpServletRequest httpServletRequest = this.getRequest();
	    String acciId = httpServletRequest.getParameter("acciId");
	    qpTICPicture.setAcciId(acciId);

	    qpTICPicture.setGroupId(groupId);
	    logger.info("组号ID------------------------" + groupId);

//	    Page pageResult = qpTICPictureService.findByQpTICPicture(qpTICPicture, page, rows);
//	    List<QpTICPicture> ticPicturesList = pageResult.getResult();
	    List<QpTICPicture> ticPicturesList = qpTICPictureService.findQpTICPictureByGroupId(groupId);
//	    if (ticPicturesList != null && ticPicturesList.size() > 0) {
//		List<String> filePathList = new ArrayList<String>();
//		String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		root = root + "/" + currentTime;
//		File savedir = new File(root);
//		if (!savedir.exists())
//		    savedir.mkdirs();
//		File f1 = new File(root);
//		if (!f1.exists()) {
//		    f1.mkdir();
//		}
//		root = root + "/" + groupId;
//		System.out.println("拷贝目录：" + root);
//		File f = new File(root);
//		if (!f.exists()) {
//		    f.mkdir();
//		}
//		String dateStr = "";
//		int index = 0;
//		for (int i = 0; i < ticPicturesList.size(); i++) {
//		    QpTICPicture ticPicture = ticPicturesList.get(i);
//		    String fileDir = ticPicture.getFileName().substring(0, ticPicture.getFileName().lastIndexOf('/'));
//		    String[] fileDirStr = fileDir.split("/");
//
//		    if (!dateStr.equals(fileDirStr[2])) {
//			index = 0;
//		    }
//		    dateStr = fileDirStr[2];
//		    // String fileDir = ticPicture.getFileName();
//		    File file = new File(fileDir);
//		    if (!file.isDirectory()) {
//			logger.info("文件名：" + file.getName());
////			System.out.println("文件名：" + file.getName());
//		    } else if (file.isDirectory()) {
//			String[] list = file.list();
//			File files = new File(fileDir + "/" + list[index]);
//			// int width = 1142;
//			// int height = 800;
//			if (!files.isDirectory()) {
//			    // BufferedImage bufferedImage =
//			    // ImageIO.read(files);
//			    // 不是目录，打印源文件夹里的所有文件名
//			    // System.out.println("文件名：" + files.getName());
//			    // 把所有文件存进输入流
//			    FileInputStream fis = new FileInputStream(fileDir + "/" + list[index]);
//			    // System.out.println(fileDir);
//			    // 构建输出流并指定路径
//			    FileOutputStream fos = new FileOutputStream(f + "/" + list[index]);
//			    byte[] buffer = new byte[1024];
//
//			    @SuppressWarnings("unused")
//			    int length = 0;
//
//			    while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
//				fos.write(buffer);
//			    }
//			    // width = bufferedImage.getWidth();
//			    // height = bufferedImage.getHeight();
//			    fos.close();
//			    fis.close();
//			}
//
//			// QpTICPicture qpTICPicturet = new QpTICPicture();
//			// qpTICPicturet.setFileName("/pages/qp/qpticpicturegroup/images"+
//			// "/"+ currentTime+ "/"+ groupId+ "/" +
//			// files.getName());
//			// qpTICPicturet.setPicDesc(list[index]); // 图片预览用到的文件名
//			// qpTICPicturet.setWidth(width);
//			// qpTICPicturet.setHeight(height);
//			filePathList.add("/pages/qp/qpticpicturegroup/images/temp/" + currentTime + "/" + groupId + "/" + files.getName());
//			index++;
//		    }
//		}
//		if (id == null) {
//		    id = new QpTICPictureGroupId();
//		    id.setGroupId(groupId);
//		}
//		QpTICPictureGroup qpTICPictureGroup = qpTICPictureGroupService.findQpTICPictureGroupByPK(id);
//		// this.getRequest().setAttribute("filePathList", filePathList);
//		ActionContext.getContext().put("accidentGroupId", groupId);
//		if (qpTICPictureGroup != null) {
//		    ActionContext.getContext().put("pictureType", qpTICPictureGroup.getPictureType());
//		}
		// ActionContext.getContext().put("filePathList", filePathList);
		this.writeJsonObjectArray(ticPicturesList);
		return NONE;
//	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("", e);
	}
	this.writeJSONMsg(ERROR);
	return NONE;
    }

    public String viewPictureGroupPC() {
	try {
	    // 要下载过去的路径
//	    String root = ServletActionContext.getServletContext().getRealPath("/pages/qp/qpticpicturegroup/images/temp");

	    SessionUser sessionUser = this.getUserFromSession();
	    String acciId = this.getRequest().getParameter("acciId");
	    // qpTICPicture.setAcciId(acciId);
	    //
	    // qpTICPicture.setGroupId(groupId);
	    //
	    // Page pageResult = qpTICPictureService.findByQpTICPicture(
	    // qpTICPicture, page, rows);
	    // List<QpTICPicture> ticPicturesList = pageResult.getResult();
//	    if(StringUtils.isEmptyStr(groupId) || StringUtils.isEmptyStr(acciId)){
//		return SUCCESS;
//	    }
	    logger.info(sessionUser.getUserName() + "在:" + new Date() + "查看估损录入groupId：" + groupId+ ", 当事人id：" + acciId);
	    List<QpTICPicture> ticPicturesList = qpTICPictureService.findQpTICPictureByGroupId(groupId);
//	    if (ticPicturesList != null && ticPicturesList.size() > 0) {
//		List<QpTICPicture> filePathList = new ArrayList<QpTICPicture>();
//		String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		root = root + "/" + currentTime;
//		File savedir = new File(root);
//		if (!savedir.exists())
//		    savedir.mkdirs();
//		root = root + "/" + groupId;
//		File f = new File(root);
//		if (!f.exists()) {
//		    f.mkdir();
//		}
//		for (int i = 0; i < ticPicturesList.size(); i++) {
//		    QpTICPicture ticPicture = ticPicturesList.get(i);
//		    String fileNamePath = ticPicture.getFileName(); // 获取图片存在数据库的路劲    D://image/xxx/xxx.jpg
//		    String fileName = fileNamePath.substring(fileNamePath.lastIndexOf("/"), fileNamePath.length());// 获取图片名称   xxx.jpg
//		    File file = new File(fileNamePath);
//		    if (!file.exists()) {
//			logger.info("file:" + file + "不存在");
//			System.out.println("file:" + file + "不存在");
//		    } else {
//			try {
//			    File file2 = new File(f + fileName);
//			    if (!file2.exists()) {// 如果发现临时目录不存在图片就去把D盘的图片写进临时目录
//				// 把所有文件存进输入流
//				FileInputStream fis = new FileInputStream(file);
//				// 构建输出流并指定路径
//				FileOutputStream fos = new FileOutputStream(f + fileName);
//				byte[] buffer = new byte[1024];
//
//				@SuppressWarnings("unused")
//				int length = 0;
//
//				while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
//				    fos.write(buffer);
//				}
//				fos.close();
//				fis.close();
//			    }
//			    QpTICPicture qpTICPicturet = new QpTICPicture();
//			    qpTICPicturet.setFileName("/pages/qp/qpticpicturegroup/images/temp" + "/" + currentTime + "/" + groupId + fileName);
//			    qpTICPicturet.setPicDesc(fileNamePath); // 图片预览用到的文件名
//			    qpTICPicturet.setPicOrder(ticPicture.getId().getPicId());
//			    filePathList.add(qpTICPicturet);
//			} catch (Exception e) {
//			    e.printStackTrace();
//			    logger.error("估损录入查看图片出现异常" + e);
//			}
//
//		    }
//		}
//		if (id == null) {
//		    id = new QpTICPictureGroupId();
//		    id.setGroupId(groupId);
//		}
//		QpTICPictureGroup qpTICPictureGroup = qpTICPictureGroupService.findQpTICPictureGroupByPK(id);
//		// this.getRequest().setAttribute("filePathList", filePathList);
//		ActionContext.getContext().put("accidentGroupId", groupId);
//		if (qpTICPictureGroup != null) {
//		    ActionContext.getContext().put("pictureType", qpTICPictureGroup.getPictureType());
//		}
		ActionContext.getContext().put("filePathList", ticPicturesList);
		ActionContext.getContext().put("imgHeader", Constants.getIMAGEHTTPQUERY());
//	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("估损录入查看图片出现异常" + e);
	}

	return SUCCESS;
    }

    public String weixin() throws Exception {
	HttpServletRequest request = getRequest();
	String appId = request.getParameter("appId");
	String appSecret = request.getParameter("appSecret");
	String url = request.getParameter("url");
	Map<String, Object> ret = new HashMap<String, Object>();
	if(StringUtils.isEmptyStr(getSession().getAttribute("accessTokenJsonObj"))){
	    System.out.println("生成session");
	     ret = Sign.sign(appId, appSecret, url);
	     getSession().setAttribute("accessTokenRet", ret);
	     getSession().setAttribute("accessTokenJsonObj", ret.get("accessTokenJsonObj"));
	}else{
	    System.out.println("存在session");
	    ret = (HashMap<String, Object>)getSession().getAttribute("accessTokenRet");
	}
	this.writeAjaxByMap(ret);
	return NONE;
    }

    public String getPicture() throws Exception {
	HttpServletRequest request = getRequest();
	String serverids = request.getParameter("serverids");
	if (serverids != null) {
	    String[] mediaIds = serverids.split(",");
	    if (mediaIds != null && mediaIds.length != 0) {
		try {
		    String userCode = (String) request.getSession().getAttribute("userCode_weixin");

		    String pictureType = null;
		    if (ToolsUtils.isEmpty(userCode)) {
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("SessionUser");
			userCode = sessionUser.getUserCode();
			pictureType = "02"; // PC端
		    } else {
			pictureType = "01"; // 微信端
		    }

		    String ext = ".png";
		    String imgPath = Constants.getIMAGEPATH();
		    if (StringUtils.isEmptyStr(imgPath)) {
			throw new Exception("查看图片出错路劲未配置");
		    }
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
		    if (!savedir1.exists())
			savedir1.mkdirs();

		    JSONObject jSONObject = (JSONObject) getSession().getAttribute("accessTokenJsonObj");
		    // InputStream inputStream = null;
		    QpTICPicture qpTICPictureSave = null;
		    for (int i = 0; i < mediaIds.length; i++) {
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(Sign.downloadPicture(jSONObject.getString("access_token"), mediaIds[i]));
			// new一个文件对象用来保存图片
			File imageFile = new File(imgPath + "/", mediaIds[i] + ext);
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();

			qpTICPictureSave = new QpTICPicture();
			qpTICPictureSave.setFileName(imgPath + "/" + mediaIds[i] + ext);
			qpTICPictureSave.setGroupId(groupId);

			// qpTICPictureSave.setAcciId(acciId);
			qpTICPictureService.saveQpTICPicture(qpTICPictureSave, userCode);
		    }

		    // qpTICPictureGroupId = new QpTICPictureGroupId();
		    // qpTICPictureGroupId.setGroupId(groupId);
		    // QpTICPictureGroup qpTICPictureGroupUpdate =
		    // qpTICPictureGroupService
		    // .findQpTICPictureGroupByPK(qpTICPictureGroupId);
		    // qpTICPictureGroupUpdate.setOperateTimeForHis(new Date());
		    // qpTICPictureGroupUpdate.setUpdaterCode(userCode);
		    // qpTICPictureGroupService
		    // .updateQpTICPictureGroup(qpTICPictureGroupUpdate);

		    this.writeJSONMsg(groupId);
		    return NONE;
		} catch (Exception e) {
		    this.writeJSONMsg(ERROR);
		    e.printStackTrace();
		    logger.error("", e);
		    return NONE;
		}
	    }
	}
	this.writeJSONMsg(ERROR);
	return NONE;
    }

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

    public String getPictureWeixin() {
	HttpServletRequest request = getRequest();
	String serverid = request.getParameter("serverid");
	if (ToolsUtils.notEmpty(serverid)) {

	    try {
		String userCode = (String) request.getSession().getAttribute("userCode_weixin");

		String pictureType = null;
		if (ToolsUtils.isEmpty(userCode)) {
		    SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("SessionUser");
		    userCode = sessionUser.getUserCode();
		    pictureType = "02"; // PC端
		} else {
		    pictureType = "01"; // 微信端
		}

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
		if (!savedir1.exists())
		    savedir1.mkdirs();

		org.activiti.engine.impl.util.json.JSONObject jSONObject = (org.activiti.engine.impl.util.json.JSONObject) getSession().getAttribute("accessTokenJsonObj");
		// InputStream inputStream = null;
		QpTICPicture qpTICPictureSave = null;
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(Sign.downloadPicture(jSONObject.getString("access_token"), serverid));
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
		qpTICPictureService.saveQpTICPicture(qpTICPictureSave, userCode);

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
     * 原当事人上传图片 绑定照片组号流程
     * @return
     * @throws Exception
     */
    public String updatePictureGroup()  {
	JSONObject jsonObject = new JSONObject();
	try {
		String groupId = this.getRequest().getParameter("groupId");
		QpTICPictureGroupId ticPictureGroupId = new QpTICPictureGroupId();
		ticPictureGroupId.setGroupId(groupId);
		QpTICPictureGroup ticPictureGroupUpdate = qpTICPictureGroupService.findQpTICPictureGroupByPK(ticPictureGroupId);
		if (ticPictureGroupUpdate != null) {
		    ticPictureGroupUpdate.setValidStatus("1");
		    ticPictureGroupUpdate.setUploadTimeForHis(new Date());
		    qpTICPictureGroupService.updateQpTICPictureGroup(ticPictureGroupUpdate);
		    jsonObject.put("msg", "上传成功");
		    this.writeJson(jsonObject.toString());
		    return NONE;
		} else {
//		    logger.error("原当事人上传图片  绑定图片组号异常", e);
		    jsonObject.put("code", -1);
		    jsonObject.put("msg", "上传失败");
		    this.writeJson(jsonObject.toString());
		    return NONE;
		}
	} catch (Exception e) {
	    logger.error("原当事人上传图片  绑定图片组号异常", e);
	    jsonObject.put("code", -1);
	    jsonObject.put("msg", "上传失败");
	    this.writeJson(jsonObject.toString());
	    return NONE;
	}
    }
    

}
