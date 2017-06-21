package com.picc.qp.util;

import ins.framework.common.ServiceFactory;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import srvSeal.PdfAutoSeal;

import com.picc.common.utils.DateUtil;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.UuidUtil;
import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.service.facade.IQpTCOMDictionaryService;

public class SealUtil {
	private static final Logger logger = LoggerFactory.getLogger(SealUtil.class);
	public static String stampedSeal(String srcPdfPath,String policyCode) throws Exception {
		// TODO Auto-generated method stub
		String returnPath = "";
		String certPath = Constants.getSEAL_CERTPATH();	//证书路径
		String certPwd = Constants.getSEAL_CERTPWD();	//证书密码
		String signImage = Constants.getSEAL_SIGNIMAGE();	//签章图片
		String serverType = Constants.getSEAL_SERVERTYPE();//服务器类型
		String ruleInfo = Constants.getSEAL_RULEINFO();//坐标定位，使用方法详见文档
		PdfAutoSeal autoSeal = new PdfAutoSeal();

		//String srcPdfPath = destFileName; // 需要签章的PDF,DOC文档
		String outPdfPath =srcPdfPath.substring(0, srcPdfPath.lastIndexOf(".pdf"))+"_out.pdf";// 签章后保存的文档名称
		//		String certPath = "D:\\hnca\\serviceCertified.pfx";	//证书路径
		//		String certPwd = "123456";	//证书密码
		//		String signImage = "D:\\hnca\\sealPic.bmp";	//签章图片
		//String ruleInfo = "0,40000,0,39000,";	//坐标定位，使用方法详见文档
		// String ruleInfo = "AUTO_ADD:0,-1,0,0,255,控制中心功能)|(0";	
		//0代表windows服务器，1代表linux服务器，linux服务器需要授权，windows系统不需要
		if("1".equals(serverType)){
			PdfAutoSeal.verifyLic("/usr/seal/license.txt");		// 授权方法
		}
		//加盖印章
		String reposeSeal = autoSeal.addSealImage(ruleInfo, certPath,
				certPwd, signImage, srcPdfPath, outPdfPath);// 图片接口
		logger.info("加盖印章状态：" + reposeSeal);
		returnPath = outPdfPath;
		//加盖签名
		if(StringUtils.isNotEmpty(policyCode)){
			IQpTCOMDictionaryService iQpTCOMDictionaryService = (IQpTCOMDictionaryService) ServiceFactory
					.getService("iQpTCOMDictionaryService");
		   List<QpTCOMDictionary> sealPersons = iQpTCOMDictionaryService.getImagePath("SEAL"+"_"+policyCode.toUpperCase());
		    if(StringUtils.isEmptyStr(sealPersons)){
		    	logger.info("没有找到警员号:"+policyCode+"对应的签名配置");
		    }else{
				String certPathPerson = Constants.getSEAL_CERTPATH();	//证书路径
				String certPwdPerson = Constants.getSEAL_CERTPWD();	//证书密码
				String signImagePerson = Constants.getSEAL_SIGNIMAGE();	//签名图片
				String ruleInfoPerson = Constants.getSEAL_RULEINFO();//坐标定位，使用方法详见文档
		    	for (QpTCOMDictionary qpTCOMDictionary : sealPersons) {
		    	
		    		if("certPath".equals(qpTCOMDictionary.getId().getCode())){
		    			certPathPerson = qpTCOMDictionary.getValue();
		    		}
		    		if("signImage".equals(qpTCOMDictionary.getId().getCode())){
		    			signImagePerson = qpTCOMDictionary.getValue();
		    		}
		    		if("certPwd".equals(qpTCOMDictionary.getId().getCode())){
		    			certPwdPerson = qpTCOMDictionary.getValue();
		    		}

		    		if("ruleInfo".equals(qpTCOMDictionary.getId().getCode())){
		    			ruleInfoPerson = qpTCOMDictionary.getValue();
		    		}
		    	}
		    	String outPdfPersonPath = outPdfPath.substring(0, outPdfPath.lastIndexOf(".pdf"))+"_p.pdf";
		    	String reposeSealPerson = autoSeal.addSealImage(ruleInfoPerson, certPathPerson,
						certPwdPerson, signImagePerson, outPdfPath, outPdfPersonPath);// 图片接口
				logger.info("加盖签名状态：" + reposeSealPerson);
				returnPath = outPdfPersonPath;
		    }
		}
		return returnPath;
	}

	
	public static String csWechatStampedSeal(String reportFilePath,Map<String,Object> parameters,JRDataSource dataSource,String caseID,String policyCode) throws Exception {
	    // TODO Auto-generated method stub
	    String rootPath = Constants.getSEAL_ROOTPATH();
	    String time = DateUtil.parseToFormatString(new Date(),DateUtil.DATE_FORMAT_YYYYMMDD);
	    File rootdir = new File(rootPath);
	    if (!rootdir.exists()) {
		rootdir.mkdirs();
	    }
	    File savedir = new File(rootPath+"/"+time);
	    if (!savedir.exists()) {
		savedir.mkdirs();
	    }
	    String uuid = UuidUtil.get32UUID();
	    String destFileName = rootPath+"/"+time+"/"+uuid+".pdf";
	    JasperRunManager.runReportToPdfFile(reportFilePath, destFileName, parameters,dataSource);
	    logger.info("案件号：" + caseID+"开始加盖印章");
	    String outPdfPath = stampedSeal(destFileName,policyCode);
	    return outPdfPath;
	}
	/**
	 * 无需盖章
	 * @param reportFilePath
	 * @param parameters
	 * @param dataSource
	 * @param caseID
	 * @param policyCode
	 * @return
	 * @throws Exception
	 */
	public static String lossInfoStampedSeal(String reportFilePath,Map<String,Object> parameters,JRDataSource dataSource,String caseID,String policyCode) throws Exception {
		// TODO Auto-generated method stub
		String rootPath = Constants.getSEAL_ROOTPATH();
		String time = DateUtil.parseToFormatString(new Date(),DateUtil.DATE_FORMAT_YYYYMMDD);
		File rootdir = new File(rootPath);
		if (!rootdir.exists()) {
			rootdir.mkdirs();
		}
		File savedir = new File(rootPath+"/"+time);
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		String uuid = UuidUtil.get32UUID();
		String destFileName = rootPath+"/"+time+"/"+uuid+".pdf";
		JasperRunManager.runReportToPdfFile(reportFilePath, destFileName, parameters,dataSource);
		return destFileName;
	}
}
