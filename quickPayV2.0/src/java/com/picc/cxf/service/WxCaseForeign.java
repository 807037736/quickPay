package com.picc.cxf.service;

import javax.jws.WebService;

/**
 * 微信公众号接口
 * 2016年8月1日
 */
@WebService
public interface WxCaseForeign {

    /**
     * 获取wxCase信息
     * @param json
     * @return
     */
    String findWxCaseByWxCaseId(String json);
    
    /**
     * 保存wxCase
     * @param json （groupid / userCode）
     * @return
     */
    String SaveWxCase(String json);
    
    /**
     * 修改wxCase json为wxcase对象
     * @param json
     * @return
     */
    String updateWxCase(String json);
    
    /**
     * 查询是否是查勘员
     * @param json
     * @return false 不是   true 是
     */
    String isCKUser(String json);
    
    
    /**
     * 查勘员上传图片  绑定当事人的surveyGroupId
     * @param json
     * @return
     */
    String updateAccidentSurveyGroupIdByAcciId(String json);
    
    /**
     * 根据案件id 查询案件
     * @param json
     * @return
     */
    String findCaseByCaseId(String json);
    
    /**
     * 根据认字编号caseSerialNo 查询案件
     * @param json
     * @return
     */
    String findCaseByCaseSerialNo(String json);
    
    /**
     * 根据案件id 查询当事人 -- 查勘员上传图片
     * @param json
     * @return
     */
    String findAccidentByCaseId(String json);
    
    /**
     * 根据当事人id 查询当事人 
     * @param json
     * @return
     */
    String findAccidentByAccId(String json);
    
    /**
     * 根据图片组号查询 图片组(PictrueGroup)对象
     * @param json
     * @return
     */
    String findPictrueGroupByGroupId(String json);
    
    /**
     * 根据图片组号查询 图片组(List<Pictrue>)对象
     * @param json
     * @return
     */
    String findPictrueByGroupId(String json);
    
    /**
     * 保存图片组
     * @param json
     * @return
     */
    String addPictrueGroup(String json);
    
    /**
     * 保存图片	支持单张多张  有groupId直接保存  没有gruopId则创建图片组
     * @param json  需要usercode
     * @return
     */
    String savePictrue(String json);
    
    /**
	 * 查询事故历史记录列表
	 * @param openID
	 * @return
	 */
	String findCaseListByOpenID(String json);
	
	/**
	 * 查询事故历史记录详情
	 * @param id
	 * @return
	 */
	String findCaseByID(String json);
	
	/**
	 * 生成图片记录
	 * @param json
	 * @return
	 */
	String uploadImg(String json);
	
	/**
	 * 获取合伙快赔点
	 * @param json  根绝案件id
	 * @return
	 */
	String findCompanyGarageByCaseId(String json);
	
	/**
	 * 获取合伙快赔点  
	 * @param json  根据经纬度
	 * @return
	 */
	String findCompanyGarageByLatAndLng(String json);
	
	/**
	 * 获取图片记录
	 * @param json
	 * @return
	 */
	String getImg(String json);
	
	/**
	 * 获取事故通用信息
	 * @param json
	 * @return
	 */
	String getCaseCommonInfo(String json);
	
	/**
	 * 保存案件及当事人
	 * @param json
	 * @return
	 */
	String saveCaseAndAccident(String json);
	
	/**
	 * 查询临时事故详情
	 * @param json
	 * @return
	 */
	String findWxCaseByID(String json);
	
	/**
	 * 当事人信息校验验证码
	 * @param json
	 * @return
	 */
	String verifAccidentCode(String json);
	
	/**
	 * 查询事故详情
	 * @param json
	 * @return
	 */
	String findCaseByWxCaseID(String json);
	
	/**
	 * 自助定责完成
	 * @param json
	 * @return
	 */
	String finish(String json);
	
	/**
	 * 交警是否完成定责
	 * @param json
	 * @return
	 */
	String isDone(String json);
	
	/**
	 * 获取微信用户进行中的案件
	 * @param json
	 * @return
	 */
	String getUserCurrentCase(String json);
	
	/**
	 * 通过证件号获取当事人用户信息
	 * @param json
	 * @return
	 */
	String getUmTAccidentUserInfo(String json);
	
}
