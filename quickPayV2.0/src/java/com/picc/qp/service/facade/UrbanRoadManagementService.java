package com.picc.qp.service.facade;

import java.util.List;

public interface UrbanRoadManagementService {
	
	public String queryProvinceJson(List provinceList)throws Exception;
	public String queryChildrenJson(List provinceList, List cityList, List districtList,String id)throws Exception;
	public void addNode(String fatherNodeId,String nodeName,String carpai,String validStatus,String order,String nodelev,String fatherid)throws Exception;
	public String deleteNode(String id,String nodelev)throws Exception;
	public Object prepareEdit(String id,String nodelev)throws Exception;
	public String updateNode(String nodeId,String name,String carpai,String validStatus,int order,String nodelev)throws Exception;
}
