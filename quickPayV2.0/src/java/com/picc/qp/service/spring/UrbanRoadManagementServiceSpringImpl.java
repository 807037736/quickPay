package com.picc.qp.service.spring;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.qp.dao.QpTCOMCityDaoHibernate;
import com.picc.qp.dao.QpTCOMDistrictDaoHibernate;
import com.picc.qp.dao.QpTCOMProvinceDaoHibernate;
import com.picc.qp.dao.QpTCOMRoadDaoHibernate;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.schema.model.QpTCOMRoadId;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCOMRoadService;
import com.picc.qp.service.facade.UrbanRoadManagementService;


@Service("urbanRoadManagementService")
public class UrbanRoadManagementServiceSpringImpl implements UrbanRoadManagementService{
	private IQpTCOMProvinceService iQpTCOMProvinceService = null;	
	private IQpTCOMCityService iQpTCOMCityService = null;
	private IQpTCOMDistrictService iQpTCOMDistrictService = null;
	private IQpTCOMRoadService iQpTCOMRoadService =null;
	private QpTCOMRoadDaoHibernate qpTCOMRoadDao = null;
	private QpTCOMDistrictDaoHibernate  qpTCOMDistrictDao = null;
	private QpTCOMProvinceDaoHibernate qpTCOMProvinceDao = null;
	private QpTCOMCityDaoHibernate qpTCOMCityDao = null;
	/**
	 * 查询省份信息并拼接成json
	 *
	 */
	public String queryProvinceJson(List provinceList){
		StringBuffer strJson = new StringBuffer();
		
		strJson.append("{\"total\":"+(provinceList.size()+1)+",\"rows\":[{\"id\":0,\"name\":\"中华人民共和国\",\"iconCls\":\"icon-ok\"},");
		for(int i=0;i<provinceList.size();i++){
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			qpTCOMProvince = (QpTCOMProvince) provinceList.get(i);
			QpTCOMProvinceId provinceId = qpTCOMProvince.getId();
			String strProvinceId =  provinceId.getProvId();
			strJson.append("{");
			strJson.append("\"id\":"+Integer.valueOf(strProvinceId)+",\"name\":\""+qpTCOMProvince.getProvName()+"\",\"order\":"+qpTCOMProvince.getProvOrder()+",\"carpai\":\""+qpTCOMProvince.getProvVehicleNumPre()+"\",\"state\":\"closed\",\"_parentId\":0");
			if(i==provinceList.size()-1){
				strJson.append("}]");
			}else{
				strJson.append("},");
			}
		}
		strJson.append("}");
		return strJson.toString();
	}
	/**
	 * 异步查询加载市、区、主干道并拼接成json
	 *
	 */
	@SuppressWarnings("unchecked")
	public String queryChildrenJson(List provinceList, List cityList, List districtList,String id){
		StringBuffer strJson = new StringBuffer();
		String strJsonProvince = "{\"total\":"+provinceList.size()+",\"rows\":[";
		String strJsonCity = "{\"total\":"+cityList.size()+",\"rows\":[";
		String strJsonDistrictList = "{\"total\":"+districtList.size()+",\"rows\":[";
		for(int i=0;i<provinceList.size();i++){
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			qpTCOMProvince = (QpTCOMProvince) provinceList.get(i);
			if(id.equals(qpTCOMProvince.getId().getProvId())){
				
				QpTCOMCity qpTCOMCityTemp = new QpTCOMCity();
				qpTCOMCityTemp.setProvId(id);//为qpTCOMCityTemp设置身份id的值
				qpTCOMCityTemp.setValidStatus("1");
				QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMCityTemp);
				
				List<QpTCOMCity> cityListTemp = qpTCOMCityDao.find(rule);//查出复核省份ID的城市信息
				//拼Json
				strJson.append("{\"total\":"+cityListTemp.size()+",\"rows\":[");
				for(int j = 0;j<cityListTemp.size();j++){
					strJson.append("{");
					qpTCOMCityTemp =  cityListTemp.get(j);
					int cityIdTemp =  Integer.valueOf(qpTCOMCityTemp.getCityId());
					strJson.append("\"id\":"+cityIdTemp+",\"name\":\""+qpTCOMCityTemp.getCityName()+"\",\"_parentId\":"+id+",\"order\":"+qpTCOMCityTemp.getCityOrder()+",\"carpai\":\""+qpTCOMCityTemp.getCityVehicleNumPre()+"\",\"state\":\"closed\"");
					if(j==cityListTemp.size()-1){
						strJson.append("}]}");
					}else{
						strJson.append("},");
					}
				}
				return strJson.toString();
			}
		}
		for(int k=0;k<cityList.size();k++){
			QpTCOMCity qpTCOMCity = new QpTCOMCity();
			qpTCOMCity = (QpTCOMCity) cityList.get(k);
			if(id.equals(qpTCOMCity.getId().getCityId())){
				
				QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
				qpTCOMDistrict.setCityId(id);
				qpTCOMDistrict.setValidStatus("1");
				qpTCOMDistrict.setType("0");
				QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
		
				List districtListTemp = qpTCOMDistrictDao.find(rule);
				strJson.append("{\"total\":"+districtListTemp.size()+",\"rows\":[");
				for(int l = 0;l<districtListTemp.size();l++){
					strJson.append("{");
					qpTCOMDistrict = (QpTCOMDistrict) districtListTemp.get(l);
//					int districtIdTemp =  Integer.valueOf(qpTCOMDistrict.getDistrictId());
					strJson.append("\"id\":\""+qpTCOMDistrict.getDistrictId()+"\",\"name\":\""+qpTCOMDistrict.getDistrictName()+"\",\"_parentId\":"+id+",\"order\":"+qpTCOMDistrict.getDistrictOrder()+",\"carpai\":\"\",\"state\":\"closed\"");
					if(l==districtListTemp.size()-1){
						strJson.append("}]}");
					}else{
						strJson.append("},");
					}
				}
				return strJson.toString();
			}
		}
		for(int m=0;m<districtList.size();m++){
			QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
			qpTCOMDistrict = (QpTCOMDistrict) districtList.get(m);
			if(id.equals(qpTCOMDistrict.getId().getDistrictId())){
				QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
				qpTCOMRoad.setDistrictId(id);
				qpTCOMRoad.setValidStatus("1");
				QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
				List qpTCOMRoadList = qpTCOMRoadDao.find(rule);
				strJson.append("{\"total\":"+qpTCOMRoadList.size()+",\"rows\":[");
				for(int n=0;n<qpTCOMRoadList.size();n++){
					strJson.append("{");
					qpTCOMRoad = (QpTCOMRoad) qpTCOMRoadList.get(n);
					int roadIdTemp = Integer.valueOf(qpTCOMRoad.getRoadId());
					strJson.append("\"id\":"+roadIdTemp+",\"name\":\""+qpTCOMRoad.getRoadName()+"\",\"_parentId\":"+id+",\"order\":"+qpTCOMRoad.getRoadOrder()+",\"carpai\":\"\",\"state\":\"closed\",\"iconCls\":\"icon-tip\"");
					if(n==qpTCOMRoadList.size()-1){
						strJson.append("}]}");
					}else{
						strJson.append("},");
					}
				}
				return strJson.toString();
			}
			
		}
		
		return strJson.toString();
	}
	
	@Override
	public void addNode(String id, String nodeName,
			String carpai, String validStatus,String order,String nodelev,String fatherid) throws Exception {
		
		if(nodelev.equals("0")){
			//查询已存在id的最大值在此基础上加1作为插入记录的id
			
			QueryRule queryRule  =  QueryRule.getInstance();
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMProvince);
			List<QpTCOMProvince> configList = qpTCOMProvinceDao.find(rule);
			int tempId = Integer.valueOf(configList.get(0).getId().getProvId());
			int orderMax = Integer.valueOf(configList.get(0).getProvOrder());
			for(int i=1;i<configList.size();i++){
				if(Integer.valueOf(configList.get(i).getId().getProvId())>tempId){
					tempId = Integer.valueOf(configList.get(i).getId().getProvId());
				}
				if(Integer.valueOf(configList.get(i).getProvOrder())>orderMax){
					orderMax = Integer.valueOf(configList.get(i).getProvOrder());
				}
			}
			tempId = tempId+1;
			QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
			qpTCOMProvinceId.setProvId(String.valueOf(id));
			//准备所需插入数据
			qpTCOMProvince.setId(qpTCOMProvinceId);
			qpTCOMProvince.setProvVehicleNumPre(carpai);
			qpTCOMProvince.setProvName(nodeName);
			qpTCOMProvince.setProvOrder(Integer.valueOf(order));
			qpTCOMProvince.setValidStatus("1");
			qpTCOMProvinceDao.save(qpTCOMProvince);
		}else if(nodelev.equals("1")){
			QueryRule queryRule  =  QueryRule.getInstance();
			QpTCOMCity qpTCOMCity = new QpTCOMCity();
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMCity);
			List<QpTCOMCity> configList = qpTCOMCityDao.find(rule);
			int tempId = Integer.valueOf(configList.get(0).getId().getCityId());
			int orderMax = Integer.valueOf(configList.get(0).getCityOrder());
			for(int i=1;i<configList.size();i++){
				if(Integer.valueOf(configList.get(i).getId().getCityId())>tempId){
					tempId = Integer.valueOf(configList.get(i).getId().getCityId());
				}
				if(Integer.valueOf(configList.get(i).getCityOrder())>orderMax){
					orderMax = Integer.valueOf(configList.get(i).getCityOrder());
				}
			}
			tempId = tempId+1;
			QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
			qpTCOMCityId.setCityId(String.valueOf(id));
			qpTCOMCity.setId(qpTCOMCityId);
			qpTCOMCity.setProvId(fatherid);
			qpTCOMCity.setCityVehicleNumPre(carpai);
			qpTCOMCity.setCityName(nodeName);
			qpTCOMCity.setCityOrder(Integer.valueOf(order));//orderMax+1
			qpTCOMCity.setValidStatus("1");
			qpTCOMCityDao.save(qpTCOMCity);
		}else if(nodelev.equals("2")){
			QueryRule queryRule  =  QueryRule.getInstance();
			QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
			List<QpTCOMDistrict> configList = qpTCOMDistrictDao.find(rule);
			int tempId = Integer.valueOf(configList.get(0).getId().getDistrictId());
			int orderMax = Integer.valueOf(configList.get(0).getDistrictOrder());
			for(int i=1;i<configList.size();i++){
				if(Integer.valueOf(configList.get(i).getId().getDistrictId())>tempId){
					tempId = Integer.valueOf(configList.get(i).getId().getDistrictId());
				}
				if(Integer.valueOf(configList.get(i).getDistrictOrder())>orderMax){
					orderMax = Integer.valueOf(configList.get(i).getDistrictOrder());
				}
			}
			tempId = tempId+1;
			QpTCOMDistrictId qpTCOMDistrictId = new QpTCOMDistrictId();
			qpTCOMDistrictId.setDistrictId(String.valueOf(id));
			//准备所需插入数据
			qpTCOMDistrict.setCityId(fatherid);
			qpTCOMDistrict.setId(qpTCOMDistrictId);
			qpTCOMDistrict.setDistrictName(nodeName);
			qpTCOMDistrict.setDistrictOrder(Integer.valueOf(order));
			qpTCOMDistrict.setValidStatus("1");
			qpTCOMProvinceDao.save(qpTCOMDistrict);
		}else if(nodelev.equals("3")){
			QueryRule queryRule  =  QueryRule.getInstance();
			QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
			List<QpTCOMRoad> configList = qpTCOMRoadDao.find(rule);
			int tempId = Integer.valueOf(configList.get(0).getId().getRoadId());
			int orderMax = Integer.valueOf(configList.get(0).getRoadOrder());
			for(int i=1;i<configList.size();i++){
				if(Integer.valueOf(configList.get(i).getId().getRoadId())>tempId){
					tempId = Integer.valueOf(configList.get(i).getId().getRoadId());
				}
				if(Integer.valueOf(configList.get(i).getRoadOrder())>orderMax){
					orderMax = Integer.valueOf(configList.get(i).getRoadOrder());
				}
			}
			tempId = tempId+1;
			QpTCOMRoadId qpTCOMRoadId= new QpTCOMRoadId();
			qpTCOMRoadId.setRoadId(String.valueOf(id));
			//准备所需插入数据
			qpTCOMRoad.setDistrictId(fatherid);
			qpTCOMRoad.setId(qpTCOMRoadId);
			qpTCOMRoad.setRoadName(nodeName);
			qpTCOMRoad.setRoadOrder(Integer.valueOf(order));
			qpTCOMRoad.setValidStatus("1");
			qpTCOMProvinceDao.save(qpTCOMRoad);
		}
	
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String deleteNode(String id,String nodelev) throws Exception {
		if(nodelev.equals("1")){
			//准备省份自身数据
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			QpTCOMProvinceId  qpTCOMProvinceId = new QpTCOMProvinceId();
			qpTCOMProvinceId.setProvId(id);
			
			//准备市级数据
			QpTCOMCity qpTCOMCity = new QpTCOMCity();
			qpTCOMCity.setProvId(id);
			//----------------------
			QpTCOMCity qpTCOMCityTemp = new QpTCOMCity();
			QueryRule queryRule  =  QueryRule.getInstance();
			
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMCity);
			List<QpTCOMCity> configList = qpTCOMCityDao.find(rule);
			//准备区/县数据
			List<ArrayList> fatherList = new ArrayList();
			List<QpTCOMDistrict> qpTCOMDistrictTemp = new ArrayList();
			for(int i= 0;i<configList.size();i++){
				qpTCOMCityTemp = configList.get(i);
				QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
				qpTCOMDistrict.setCityId(qpTCOMCityTemp.getCityId());
				QueryRule districtRule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
				qpTCOMDistrictTemp = qpTCOMDistrictDao.find(districtRule);
				fatherList.add((ArrayList) qpTCOMDistrictTemp);
			}
			
			//准备主干道数据

			for(int j=0;j<fatherList.size();j++){
				
				List<QpTCOMDistrict> checkRoadDistictList = fatherList.get(j);
				for(int l=0;l<checkRoadDistictList.size();l++){
						
						QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
						qpTCOMDistrict = checkRoadDistictList.get(l);
						String districtId = qpTCOMDistrict.getDistrictId();
						QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
						qpTCOMRoad.setDistrictId(districtId);
						QueryRule roadRule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
						List list = qpTCOMRoadDao.find(roadRule);
						if(list.size()!=0&&list!=null){
							qpTCOMRoad = (QpTCOMRoad) list.get(0);
							qpTCOMRoad.setValidStatus("0");
							qpTCOMRoadDao.update(qpTCOMRoad);//直接将有效标志改为无效
						}
						
				}
				
			}
			
			//开始将数据设置为无效
			List qpTCOMDistrictUseList = new ArrayList();
			for(int k = 0;k<fatherList.size();k++){
				qpTCOMDistrictUseList = fatherList.get(k);
				for(int m = 0;m<qpTCOMDistrictUseList.size();m++){
					
					QpTCOMDistrict qpTCOMDistrict= new QpTCOMDistrict();
					if(qpTCOMDistrictUseList.size()!=0&&qpTCOMDistrictUseList!=null){

						qpTCOMDistrict = (QpTCOMDistrict) qpTCOMDistrictUseList.get(m);
						qpTCOMDistrict.setValidStatus("0");
						qpTCOMDistrictDao.update(qpTCOMDistrict);
					}
				}
			}
			
			for(int n=0;n<configList.size();n++){
				QpTCOMCity qpTCOMCityUse = new QpTCOMCity();
				if(configList.size()!=0&&configList!=null){
					qpTCOMCityUse = configList.get(n);
					qpTCOMCityUse.setValidStatus("0");
					qpTCOMCityDao.update(qpTCOMCityUse);
				}
			}
			QueryRule provinceRule = QueryRuleHelper.generateQueryRule(qpTCOMProvince);
		
			qpTCOMProvince = (QpTCOMProvince) qpTCOMProvinceDao.get(QpTCOMProvince.class,qpTCOMProvinceId);
			if(qpTCOMProvince!=null){
				
				qpTCOMProvince.setValidStatus("0");
				qpTCOMProvinceDao.update(qpTCOMProvince);
			}
			
			return "success";
		}else if(nodelev.equals("2")){
		
			QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
			qpTCOMCityId.setCityId(id);
		
			
			QpTCOMDistrict qpTCOMDistrict =new QpTCOMDistrict();
			qpTCOMDistrict.setCityId(id);
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
			List<QpTCOMDistrict> configList = qpTCOMDistrictDao.find(rule);
			
			for(int l=0;l<configList.size();l++){
				
				QpTCOMDistrict qpTCOMDistrictTemp = new QpTCOMDistrict();
				qpTCOMDistrictTemp = configList.get(l);
				String districtId = qpTCOMDistrictTemp.getDistrictId();
				QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
				qpTCOMRoad.setDistrictId(districtId);
				QueryRule roadRule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
				List list = qpTCOMRoadDao.find(roadRule);
				if(list.size()!=0&&list!=null){
					qpTCOMRoad = (QpTCOMRoad) list.get(0);
					qpTCOMRoad.setValidStatus("0");
					qpTCOMRoadDao.update(qpTCOMRoad);//直接将有效标志改为无效

				}
			}
			
			for(int i = 0;i<configList.size();i++){
				QpTCOMDistrict qpTCOMDistrictTemp =new QpTCOMDistrict();
				qpTCOMDistrictTemp = configList.get(i);
				qpTCOMDistrictTemp.setValidStatus("0");
				qpTCOMRoadDao.update(qpTCOMDistrictTemp);
			}
			
			QpTCOMCity qpTCOMCity = (QpTCOMCity) qpTCOMCityDao.get(QpTCOMCity.class, qpTCOMCityId);
			
			if(qpTCOMCity!=null){
				qpTCOMCity.setValidStatus("0");
				qpTCOMCityDao.update(qpTCOMCity);
			}
			
		
			
			return "success";
		}else if(nodelev.equals("3")){
			QpTCOMDistrict qpTCOMDistrict =new QpTCOMDistrict();
			QpTCOMDistrictId qpTCOMDistrictId = new QpTCOMDistrictId();
			qpTCOMDistrictId.setDistrictId(id);
			qpTCOMDistrict.setId(qpTCOMDistrictId);
			
			QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
			qpTCOMRoad.setDistrictId(id);
			QueryRule roadRule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
			List<QpTCOMRoad> roadListUse = qpTCOMRoadDao.find(roadRule);
			for(int i = 0;i<roadListUse.size();i++){
				qpTCOMRoad = roadListUse.get(i);
				qpTCOMRoad.setValidStatus("0");
				qpTCOMRoadDao.update(qpTCOMRoad);
			}
			QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
			qpTCOMDistrict = (QpTCOMDistrict) qpTCOMDistrictDao.get(QpTCOMDistrict.class, qpTCOMDistrictId);
			if(qpTCOMDistrict!=null){
				qpTCOMDistrict.setValidStatus("0");
				qpTCOMDistrictDao.update(qpTCOMDistrict);
			}
			
			return "success";
		}else if(nodelev.equals("4")){
			QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
			QpTCOMRoadId qpTCOMRoadId = new QpTCOMRoadId();
			qpTCOMRoadId.setRoadId(id);
			qpTCOMRoad=qpTCOMRoadDao.get(QpTCOMRoad.class, qpTCOMRoadId);
			qpTCOMRoad.setValidStatus("0");
			qpTCOMRoadDao.update(qpTCOMRoad);
			return "success";
		}
		return "none";
	}
	
	@Override
	public Object prepareEdit(String id,String nodelev) throws Exception {
		if(nodelev.equals("1")){
			QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
			QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
			qpTCOMProvinceId.setProvId(id);
			qpTCOMProvince = qpTCOMProvinceDao.get(QpTCOMProvince.class, qpTCOMProvinceId);
			return qpTCOMProvince;
		}else if(nodelev.equals("2")){
			QpTCOMCity qpTCOMCity = new QpTCOMCity();
			QpTCOMCityId  qpTCOMCityId = new QpTCOMCityId();
			qpTCOMCityId.setCityId(id);
			qpTCOMCity = qpTCOMCityDao.get(QpTCOMCity.class, qpTCOMCityId);
			String  provId = qpTCOMCity.getProvId();
			QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
			qpTCOMProvinceId.setProvId(provId);
			QpTCOMProvince qpTCOMProvince = qpTCOMProvinceDao.get(QpTCOMProvince.class, qpTCOMProvinceId);
			qpTCOMCity.setCreatorCode(qpTCOMProvince.getProvName());
			return qpTCOMCity;
		}else if(nodelev.equals("3")){
			QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
			QpTCOMDistrictId qpTCOMDistrictId = new QpTCOMDistrictId();
			qpTCOMDistrictId.setDistrictId(id);
			qpTCOMDistrict = qpTCOMDistrictDao.get(QpTCOMDistrict.class, qpTCOMDistrictId);
			String cityId = qpTCOMDistrict.getCityId();
			QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
			qpTCOMCityId.setCityId(cityId);
			QpTCOMCity qpTCOMCity = qpTCOMCityDao.get(QpTCOMCity.class, qpTCOMCityId);
			qpTCOMDistrict.setCreatorCode(qpTCOMCity.getCityName());
			return qpTCOMDistrict;
		}else if(nodelev.equals("4")){
			QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
			QpTCOMRoadId qpTCOMRoadId = new QpTCOMRoadId();
			qpTCOMRoadId.setRoadId(id);
			qpTCOMRoad = qpTCOMRoadDao.get(QpTCOMRoad.class, qpTCOMRoadId);
			String districtId = qpTCOMRoad.getDistrictId();
			QpTCOMDistrictId qpTCOMDistrictId = new QpTCOMDistrictId();
			qpTCOMDistrictId.setDistrictId(districtId);
			QpTCOMDistrict qpTCOMDistrict = qpTCOMDistrictDao.get(QpTCOMDistrict.class, qpTCOMDistrictId);
			qpTCOMRoad.setCreatorCode(qpTCOMDistrict.getDistrictName());
			return qpTCOMRoad;
		}
		return "none";
	}
	
	@Override
	public String updateNode(String nodeId, String name, String carpai,
			String validStatus,int order,String nodelev) throws Exception {
		if(nodelev.equals("1")){
			QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
			qpTCOMProvinceId.setProvId(nodeId);
			QpTCOMProvince qpTCOMProvince = qpTCOMProvinceDao.get(QpTCOMProvince.class, qpTCOMProvinceId);
			qpTCOMProvince.setProvName(name);
			qpTCOMProvince.setValidStatus(validStatus);
			qpTCOMProvince.setProvVehicleNumPre(carpai);
			qpTCOMProvince.setProvOrder(order);
			qpTCOMProvinceDao.update(qpTCOMProvince);
			return "success";
		}else if(nodelev.equals("2")){
			QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
			qpTCOMCityId.setCityId(nodeId);
			QpTCOMCity qpTCOMCity = qpTCOMCityDao.get(QpTCOMCity.class, qpTCOMCityId);
			qpTCOMCity.setCityName(name);
			qpTCOMCity.setCityVehicleNumPre(carpai);
			qpTCOMCity.setValidStatus(validStatus);
			qpTCOMCity.setCityOrder(order);
			qpTCOMCityDao.update(qpTCOMCity);
			return "success";
		}else if(nodelev.equals("3")){
			QpTCOMDistrictId qpTCOMDistrictId = new QpTCOMDistrictId();
			qpTCOMDistrictId.setDistrictId(nodeId);
			QpTCOMDistrict QpTCOMDistrict = qpTCOMDistrictDao.get(QpTCOMDistrict.class, qpTCOMDistrictId);
			QpTCOMDistrict.setDistrictName(name);
			QpTCOMDistrict.setValidStatus(validStatus);
			QpTCOMDistrict.setDistrictOrder(order);
			qpTCOMDistrictDao.update(QpTCOMDistrict);
			return "success";
		}else if(nodelev.equals("4")){
			QpTCOMRoadId qpTCOMRoadId = new QpTCOMRoadId();
			qpTCOMRoadId.setRoadId(nodeId);
			QpTCOMRoad qpTCOMRoad = qpTCOMRoadDao.get(QpTCOMRoad.class, qpTCOMRoadId);
			qpTCOMRoad.setRoadName(name);
			qpTCOMRoad.setValidStatus(validStatus);
			qpTCOMRoad.setRoadOrder(order);
			qpTCOMRoadDao.update(qpTCOMRoad);
			return "success";
		}
		return "none";
	}

	public QpTCOMRoadDaoHibernate getQpTCOMRoadDao(){
		return qpTCOMRoadDao;
	}


	public void setQpTCOMRoadDao(QpTCOMRoadDaoHibernate qpTCOMRoadDao) {
		this.qpTCOMRoadDao = qpTCOMRoadDao;
	}


	public QpTCOMDistrictDaoHibernate getQpTCOMDistrictDao() {
		return qpTCOMDistrictDao;
	}


	public void setQpTCOMDistrictDao(QpTCOMDistrictDaoHibernate qpTCOMDistrictDao) {
		this.qpTCOMDistrictDao = qpTCOMDistrictDao;
	}

	public QpTCOMProvinceDaoHibernate getQpTCOMProvinceDao() {
		return qpTCOMProvinceDao;
	}

	public void setQpTCOMProvinceDao(QpTCOMProvinceDaoHibernate qpTCOMProvinceDao) {
		this.qpTCOMProvinceDao = qpTCOMProvinceDao;
	}

	public QpTCOMCityDaoHibernate getQpTCOMCityDao() {
		return qpTCOMCityDao;
	}

	public void setQpTCOMCityDao(QpTCOMCityDaoHibernate qpTCOMCityDao) {
		this.qpTCOMCityDao = qpTCOMCityDao;
	}


}
