package com.picc.qp.web;

import ins.framework.web.Struts2Action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.qp.schema.model.QpTIcCompanyGarageDetail;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetailShow;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageDetailService;
import com.picc.qp.service.facade.IQpTIcCompanyGarageService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.util.HttpClientUtils;

@SuppressWarnings("serial")
public class QpTIcCompanyGarageAction extends Struts2Action {
    
    private IQpTIcCompanyGarageDetailService qpTIcCompanyGarageDetailService;
    private IQpTIcCompanyGarageService qpTIcCompanyGarageService;
    private IQpTTPCaseService qpTTPCaseService;
    private IQpTICAccidentService qpTICAccidentService;
    private IQpTICCompanyService qpTICCompanyService;
    
    public IQpTIcCompanyGarageDetailService getQpTIcCompanyGarageDetailService() {
        return qpTIcCompanyGarageDetailService;
    }
    public void setQpTIcCompanyGarageDetailService(IQpTIcCompanyGarageDetailService qpTIcCompanyGarageDetailService) {
        this.qpTIcCompanyGarageDetailService = qpTIcCompanyGarageDetailService;
    }
    public IQpTIcCompanyGarageService getQpTIcCompanyGarageService() {
        return qpTIcCompanyGarageService;
    }
    public void setQpTIcCompanyGarageService(IQpTIcCompanyGarageService qpTIcCompanyGarageService) {
        this.qpTIcCompanyGarageService = qpTIcCompanyGarageService;
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
    public IQpTICCompanyService getQpTICCompanyService() {
        return qpTICCompanyService;
    }
    public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
        this.qpTICCompanyService = qpTICCompanyService;
    }
    
//    
//    public String queryCompanyGarage(){
//	JSONObject result = new JSONObject();
//
//	try {
//	    HttpServletRequest request = this.getRequest();
//	    String caseId =  request.getParameter("caseId");
//	    if(StringUtils.isEmptyStr(caseId)){
//		result.put("code", -1);
//		result.put("code", "caseId不存在");
//		this.writeJson(result);
//		return NONE;
//	    }
//	    
//	    QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
//	    qpTTPCaseId.setCaseId(caseId);
//	    QpTTPCase qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
//	    if(StringUtils.isEmptyStr(qpTTPCase)){
//		result.put("code", -1);
//		result.put("code", "案件不存在");
//		this.writeJson(result);
//		return NONE;
//	    }
//	    
//	    List<QpTICAccident> accidents =  qpTICAccidentService.findQpTICAccidentInfoOnly(caseId);
//	    if(StringUtils.isEmptyStr(accidents) || accidents.size() <= 0){
//		result.put("code", -1);
//		result.put("code", "该案件无当事人");
//		this.writeJson(result);
//		return NONE;
//	    }
//	    if(accidents.size() != 2){
//		result.put("code", -1);
//		result.put("code", "暂时不支持2个当事人以外的案件查询");
//		this.writeJson(result);
//		return NONE;
//	    }
//	    //10 全部责任  11 主要责任  12次要责任  13同等责任 14无责任
//	    QpTICAccident qpTICAccidentA = accidents.get(0);
//	    QpTICAccident qpTICAccidentB = accidents.get(1);
//	    String coId = "";
//	    String accidentMain = "";
//	    String driverLiabilityDesc = "";
//	    for (QpTICAccident qpTICAccident : accidents) {
//		if("10".equals(qpTICAccident.getDriverLiability())){
//		    driverLiabilityDesc = qpTICAccident.getDriverLiabilityDesc();
//		    coId = qpTICAccident.getCoId();
//		    accidentMain = qpTICAccident.getAcciId();
//		    break;
//		}
//	    }
//	    
//	    if(StringUtils.isEmptyStr(coId)){
//		for (QpTICAccident qpTICAccident : accidents) {
//		    if("11".equals(qpTICAccident.getDriverLiability())){
//			driverLiabilityDesc = qpTICAccident.getDriverLiabilityDesc();
//			coId = qpTICAccident.getCoId();
//			accidentMain = qpTICAccident.getAcciId();
//			break;
//		    }
//		}
//	    }
//	    if(!StringUtils.isEmptyStr(coId)){
//		//主要责任  或 全部责任      快赔点 
//		QpTICCompanyId id = new QpTICCompanyId();
//		id.setCoId(coId);
//		System.out.println("银行信息:" + JSONObject.fromObject(qpTICCompanyService.findQpTICCompanyByPK(id)));
//		System.out.println("coId:" + coId);
//		QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(coId);
//		if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
//		    result.put("code", -1);
//		    result.put("msg", "该保险无定损点");
//		    result.put("data", null);
//		    this.writeJson(result);
//		    return NONE;
//		}else{
//		    List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
//		    JSONArray jsonArray = new JSONArray();
//		    JSONObject jsonObject = new JSONObject();
//		    for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
//			if(StringUtils.isEmptyStr(qpTTPCase.getLatitude()) || StringUtils.isEmptyStr(qpTTPCase.getLongitude())){
//			    String address = qpTTPCase.getCaseStreet();
//			    if(StringUtils.isEmptyStr(address)){
//				result.put("code", -1);
//				result.put("msg", "案件地址不存在");
//				result.put("data", null);
//				this.writeJson(result);
//				return NONE;
//			    }else{
//				Map<String, Object> map = this.getLatAndLngByAddress(address);
//				if("200".equals(map.get("code"))){
//				    JSONObject resultData = JSONObject.fromObject(map.get("info"));
//				    if("0".equals(resultData.getString("status"))){
//					double lng = resultData.getJSONObject("result").getJSONObject("location").getDouble("lng");
//			        	double lat = resultData.getJSONObject("result").getJSONObject("location").getDouble("lat");
//			        	qpTIcCompanyGarageDetail.setDistance(this.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
//				    }else{
//					//查询失败
//					logger.error("返回修理厂时，根据地址查询经纬度失败:" + resultData);
//					result.put("code", -1);
//					result.put("msg", "查询失败,请稍后在试");
//					result.put("data", null);
//					this.writeJson(result);
//					return NONE;
//				    }
//				}else{
//				    //失败
//				  //查询失败
//					logger.error("返回修理厂时，根据地址查询经纬度失败:" + map);
//					result.put("code", -1);
//					result.put("msg", "查询失败,请稍后在试");
//					result.put("data", null);
//					this.writeJson(result);
//					return NONE;
//				}
//			    }
//			}else{
//			    qpTIcCompanyGarageDetail.setDistance(this.Distance(Double.valueOf(qpTTPCase.getLatitude()), Double.valueOf(qpTTPCase.getLongitude()), qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
//			}
//		    }
////		    Collections.sort(studentList,mc);     //按照age升序 22，23
////		    Collections.reverse(studentList,mc);    //按照age降序 23,22  
//		    MyCompartor mc = new MyCompartor();
//		    Collections.sort(qpTIcCompanyGarageDetails, mc);
//		    
//		    List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(qpTIcCompanyGarageDetails);
//		    
//		    for (QpTICAccident qpTICAccident : accidents) {
//			jsonObject = new JSONObject();
//			jsonObject.put("acciId", qpTICAccident.getAcciId());
//			jsonObject.put("accident", qpTIcCompanyGarageDetailShows);
//			jsonArray.add(jsonObject);
//		    }
//		    result.put("code", 0);
//		    result.put("msg", "查询成功");
//		    result.put("data", jsonArray);
//		    this.writeJson(result);
//		}
//		
//	    }else{
//		//同等责任
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObject = new JSONObject();
//		for (QpTICAccident qpTICAccident : accidents) {
//		    QpTICCompanyId id = new QpTICCompanyId();
//		    id.setCoId(qpTICAccident.getCoId());
//		    System.out.println("当事人:"+qpTICAccident.getAcciId()+"银行信息:" + JSONObject.fromObject(qpTICCompanyService.findQpTICCompanyByPK(id)));
//		    System.out.println("coId:" + coId);
//		    QpTIcCompanyGarage qpTIcCompanyGarage =  qpTIcCompanyGarageService.findQpTIcCompanyGarageByCoId(qpTICAccident.getCoId());
//		    if(StringUtils.isEmptyStr(qpTIcCompanyGarage)){
//			result.put("code", 0);
//			result.put("msg", "查询成功(修理厂不存在)");
//			result.put("data", null);
//			this.writeJson(result);
//			return NONE;
//		    }else{
//			List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails =qpTIcCompanyGarageDetailService.findQpTIcCompanyGarageDetailByCompanyGarageId(qpTIcCompanyGarage.getId().getId());
//			logger.info("主要责任人快赔点:" + JSONArray.fromObject(qpTIcCompanyGarageDetails));
//			for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
//			    if(StringUtils.isEmptyStr(qpTTPCase.getLatitude()) || StringUtils.isEmptyStr(qpTTPCase.getLongitude())){
//				    String address = qpTTPCase.getCaseAddress();
//				    if(StringUtils.isEmptyStr(address)){
//					result.put("code", -1);
//					result.put("msg", "案件地址不存在");
//					result.put("data", null);
//					this.writeJson(result);
//					return NONE;
//				    }else{
//					Map<String, Object> map = this.getLatAndLngByAddress(address);
//					if("200".equals(map.get("code"))){
//					    JSONObject resultData = JSONObject.fromObject(map.get("info"));
//					    if("0".equals(resultData.getString("status"))){
//						double lng = resultData.getJSONObject("result").getJSONObject("location").getDouble("lng");
//				        	double lat = resultData.getJSONObject("result").getJSONObject("location").getDouble("lat");
//				        	qpTIcCompanyGarageDetail.setDistance(this.Distance(lat, lng, qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
//					    }else{
//						//查询失败
//						logger.error("返回休息厂时，根据地址查询经纬度失败:" + resultData);
//						result.put("code", -1);
//						result.put("msg", "查询失败,请稍后在试");
//						result.put("data", null);
//						this.writeJson(result);
//						return NONE;
//					    }
//					}else{
//					    //失败
//					  //查询失败
//						logger.error("返回休息厂时，根据地址查询经纬度失败:" + map);
//						result.put("code", -1);
//						result.put("msg", "查询失败,请稍后在试");
//						result.put("data", null);
//						this.writeJson(result);
//						return NONE;
//					}
//				    }
//				}else{
//				    qpTIcCompanyGarageDetail.setDistance(this.Distance(Double.valueOf(qpTTPCase.getLatitude()), Double.valueOf(qpTTPCase.getLongitude()), qpTIcCompanyGarageDetail.getLat(), qpTIcCompanyGarageDetail.getLng()));
//				}
//			}
//			//			    Collections.sort(studentList,mc);     //按照age升序 22，23
//			//			    Collections.reverse(studentList,mc);    //按照age降序 23,22  
//			MyCompartor mc = new MyCompartor();
//			Collections.sort(qpTIcCompanyGarageDetails, mc);
//			
//			List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = QpTIcCompanyGarageAction.getQpticCompanyGarageDetailShow(qpTIcCompanyGarageDetails);
//			
//			jsonObject = new JSONObject();
//			jsonObject.put("acciId", qpTICAccident.getAcciId());
//			jsonObject.put("accident", qpTIcCompanyGarageDetailShows);
//			jsonArray.add(jsonObject);
//		    }
//		}
//		result.put("code", 0);
//		result.put("msg", "查询成功");
//		result.put("data", jsonArray);
//		this.writeJson(result);
//	    }
//	    //分两种情况:
//	    //1: 只传caseId
//	    //2： 传caseId  和    经纬度
//	} catch (Exception e) {
//	    result.put("code", -1);
//	    result.put("code", "查询失败,请稍后在试");
//	    this.writeJson(result);
//	    logger.error("获取修理厂地址失败", e);
//	}
//	return NONE;
//    }
    
    
    public static class MyCompartor implements Comparator
    {
	 @Override
         public int compare(Object o1, Object o2)
        {
               QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail1= (QpTIcCompanyGarageDetail )o1;
               QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail2= (QpTIcCompanyGarageDetail )o2;
               //小 -1  大 1  相等 == 0
               if(Integer.valueOf(qpTIcCompanyGarageDetail1.getIsStation()).compareTo(Integer.valueOf(qpTIcCompanyGarageDetail2.getIsStation())) == 0){
        	   return qpTIcCompanyGarageDetail1.getDistance().compareTo(qpTIcCompanyGarageDetail2.getDistance());
               }else{
        	   return Integer.valueOf(qpTIcCompanyGarageDetail2.getIsStation()).compareTo(Integer.valueOf(qpTIcCompanyGarageDetail1.getIsStation()));
               }
        }
    }
    
    public static List<QpTIcCompanyGarageDetailShow> getQpticCompanyGarageDetailShow(List<QpTIcCompanyGarageDetail> qpTIcCompanyGarageDetails){
	List<QpTIcCompanyGarageDetailShow> qpTIcCompanyGarageDetailShows = new ArrayList<QpTIcCompanyGarageDetailShow>();
	for (QpTIcCompanyGarageDetail qpTIcCompanyGarageDetail : qpTIcCompanyGarageDetails) {
	    QpTIcCompanyGarageDetailShow qpTIcCompanyGarageDetailShow = new QpTIcCompanyGarageDetailShow();
	    qpTIcCompanyGarageDetailShow.setAddress(qpTIcCompanyGarageDetail.getAddress());
	    qpTIcCompanyGarageDetailShow.setCompanyGarageName(qpTIcCompanyGarageDetail.getCompanyGarageName());
	    qpTIcCompanyGarageDetailShow.setLandlinePhone(qpTIcCompanyGarageDetail.getLandlinePhone());
	    qpTIcCompanyGarageDetailShow.setLat(qpTIcCompanyGarageDetail.getLat());
	    qpTIcCompanyGarageDetailShow.setLng(qpTIcCompanyGarageDetail.getLng());
	    qpTIcCompanyGarageDetailShow.setName(qpTIcCompanyGarageDetail.getName());
	    qpTIcCompanyGarageDetailShow.setQq(qpTIcCompanyGarageDetail.getQq());
	    qpTIcCompanyGarageDetailShow.setServicePhone(qpTIcCompanyGarageDetail.getServicePhone());
	    qpTIcCompanyGarageDetailShow.setServicePhoneName(qpTIcCompanyGarageDetail.getServicePhoneName());
	    qpTIcCompanyGarageDetailShows.add(qpTIcCompanyGarageDetailShow);
	}
	return qpTIcCompanyGarageDetailShows;
    }
    
    /** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double Distance(double long1, double lat1, double long2, double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2  
                * R  
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
                        * Math.cos(lat2) * sb2 * sb2));  
        return d;  
    } 
    
    public static  Map<String, Object> getLatAndLngByAddress(String address){
	String ak = "H52kDj4pb4iHr1h7UpLo0g2mE2DeGAIf";
	String url = "http://api.map.baidu.com/geocoder/v2/?ak="+ak+"&output=json&address="+URLEncoder.encode(address);
		//"?address=%E6%B7%B1%E5%9C%B3%E5%B8%82&output=json&";
	JSONObject param = new JSONObject();
	Map<String, Object> map = HttpClientUtils.HttpClientJsonPost(url, param.toString(), "UTF-8");
	return map;
    }
    
}
