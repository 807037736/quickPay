package com.picc.um.service.facade;


/***
 * 炎黄服务自定义接口
 * @author 姜卫洋
 *
 */
public interface IAwsService {
	
	/**
	 * 根据顶级用户代码获取该用户所在省市的顶级comcode
	 * @param comCode		省市的顶级comcode
	 * @param queryType		查询方式
	 * @return
	 */
	public String getCompanyJsonByUpperCode(String comCode,String queryType);
	
	
	
	
	/**
	 * 根据comcode查询 下级机构及人员
	 * @param comCode
	 * @param queryType
	 * @param start
	 * @param limit
	 * @return
	 */
	public String getUserListByComCode(String comCode,String queryType,int start,int limit);


	/**
	 * 根据gradeCode查询 下级岗位
	 * @param gradeCode   父级的岗位代码
	 * @param queryType   查询方式
	 * @return
	 */
	public String findGradeJsonListByUpperCode(String gradeCode, String queryType);

}
