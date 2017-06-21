/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ExcelUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTTPCaseStatDaoHibernate;
import com.picc.qp.schema.vo.QpTTPCaseStatTempVO;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.qp.service.facade.IQpTTPCaseStatService;
import com.picc.qp.util.Constants;


@Service("qpTTPCaseStatService")
public class QpTTPCaseStatServiceSpringImpl implements IQpTTPCaseStatService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPCaseStatDaoHibernate qpTTPCaseStatDao;
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * 根据qpTTPCase和页码信息，获取Page对象
	 * @param qpTTPCase
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPCase的Page对象
	 */
	public Page findByQpTTPCase(QpTTPCaseStatVO qpTTPCaseStat, int pageNo, int pageSize) throws Exception{
		StringBuffer strBuf = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		

		List<Object> params = new ArrayList<Object>();
		
		
		
		// 添加参数条件
		this.appendParam(whereSql, params, qpTTPCaseStat);
		return sysCommonDao.findBySql(QpTTPCaseStatVO.class, strBuf.toString() + whereSql.toString(), pageNo, pageSize,
				params.toArray());
	}

	@Override
	public HashMap<String,Object> getFieldList(QpTTPCaseStatVO qpTTPCaseStat) {
		//返回用Map
		HashMap<String,Object> rMap = new HashMap<String,Object>();
		//显示结果列
		String[] fieldList = null;
		//最终查询SQL
		StringBuffer mainSqlBuf = new StringBuffer();
		StringBuffer fieldListSqlBuf_1 = new StringBuffer();
		StringBuffer fieldListSqlBuf_2 = new StringBuffer();
		//where 条件
		StringBuffer whereSql = new StringBuffer();
		//查询参数
		List<Object> params = new ArrayList<Object>();
		List<QpTTPCaseStatTempVO> tempList = new ArrayList<QpTTPCaseStatTempVO>();
		List<QpTTPCaseStatTempVO> tempList_1 = new ArrayList<QpTTPCaseStatTempVO>();
		List<QpTTPCaseStatTempVO> tempList_2 = new ArrayList<QpTTPCaseStatTempVO>();
		//第一个统计条件为空的情况，跳过处理
		if(qpTTPCaseStat.getStatType_1()==null||"".equals(qpTTPCaseStat.getStatType_1())){
			return rMap;
		}
		//处理where条件
		this.appendParam(whereSql, params, qpTTPCaseStat);
		//一、区分不同的统计类型，获得统计维度，并区分出哪一个数据应该作为列显示。
		 //1、如果第二个统计条件为空,则列直接显示为出险次数
	     if(qpTTPCaseStat.getStatType_2()==null||"".equals(qpTTPCaseStat.getStatType_2())){
	    	 //设置显示列
	    	 fieldList = new String[]{Constants.STAT_TYPE_MAP.get(qpTTPCaseStat.getStatType_1()),"出险次数"};
	    	 
	    	 this.getfieldListSql(mainSqlBuf,whereSql,qpTTPCaseStat.getStatType_1());
	     //选两个统计条件的情况 	 
	     }else{
	      //二、统计汇总信息（获得表头）
	    	 //1、分别统计两个维度的指标量，然后选取一个较小的作为行
	    	 this.getfieldListSql(fieldListSqlBuf_1,whereSql,qpTTPCaseStat.getStatType_1());
	    	 this.getfieldListSql(fieldListSqlBuf_2,whereSql,qpTTPCaseStat.getStatType_2());
	    	 tempList_1 =  sysCommonDao.findBySql(QpTTPCaseStatTempVO.class,"select * from (" + fieldListSqlBuf_1.toString() + " ) model", 1, 1000,params.toArray()).getResult();
	    	 tempList_2 =  sysCommonDao.findBySql(QpTTPCaseStatTempVO.class,"select * from (" +fieldListSqlBuf_2.toString() + " ) model", 1, 1000,params.toArray()).getResult();
	    	 //选择较少的作为行显示
	    	 //tempList 为行，分组为列。******************
	    	 //统计条件1作为行显示
	    	 if(tempList_1.size()<tempList_2.size()){
	    		 fieldList = new String[tempList_1.size()+1];
	    		 fieldList[0] = Constants.STAT_TYPE_MAP.get(qpTTPCaseStat.getStatType_2());
	    		 for(int i=1;i<fieldList.length;i++){
	    			 fieldList[i] = tempList_1.get(i-1).getField_0();
	    		 }
	    		 this.getDataListSql(mainSqlBuf,whereSql,tempList_2,tempList_1,qpTTPCaseStat.getStatType_1(),qpTTPCaseStat.getStatType_2());
	    	 //统计条件2作为行显示
	    	 }else{
	    		 fieldList = new String[tempList_2.size()+1];
	    		 fieldList[0] = Constants.STAT_TYPE_MAP.get(qpTTPCaseStat.getStatType_1());
	    		 for(int i=1;i<fieldList.length;i++){
	    			 fieldList[i] = tempList_2.get(i-1).getField_0();
	    		 }
	    		 this.getDataListSql(mainSqlBuf,whereSql,tempList_1,tempList_2,qpTTPCaseStat.getStatType_2(),qpTTPCaseStat.getStatType_1());
	    	 }
	     }
	
	     logger.info(mainSqlBuf.toString());
	     
	     tempList =  sysCommonDao.findBySql(QpTTPCaseStatTempVO.class, "select * from (" + mainSqlBuf.toString()+ " ) model" , 1, 1000,params.toArray()).getResult();
	     
		
		rMap.put("fieldList", fieldList);
		rMap.put("tempList", tempList);
		return rMap;
	}
	//拼装查询条件
	private void appendParam(StringBuffer fromSql, List<Object> params, QpTTPCaseStatVO qpTTPCaseStatVO) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		//事发时间
		if(qpTTPCaseStatVO.getCaseTimeStart()!=null&&!"".equals(qpTTPCaseStatVO.getCaseTimeStart())){
			
			fromSql.append(" and tt.casetime >  '"+qpTTPCaseStatVO.getCaseTimeStart()+"' ");
		}
		//事发时间
		if(qpTTPCaseStatVO.getCaseTimeEnd()!=null&&!"".equals(qpTTPCaseStatVO.getCaseTimeEnd())){
			String dateStr = "";
			try {
				dateStr =date.format(ToolsUtils.getDateObjectAfter(qpTTPCaseStatVO.getCaseTimeEnd(),1));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
			fromSql.append(" and tt.casetime <  '"+dateStr+"' ");
		}
		//受理时间
		if(qpTTPCaseStatVO.getTPHandleTimeStart()!=null&&!"".equals(qpTTPCaseStatVO.getTPHandleTimeStart())){
			
			fromSql.append(" and tt.TPHandleTime >  '"+qpTTPCaseStatVO.getTPHandleTimeStart()+"' ");
		}
		//受理时间
		if(qpTTPCaseStatVO.getTPHandleTimeEnd()!=null&&!"".equals(qpTTPCaseStatVO.getTPHandleTimeEnd())){
			String dateStr = "";
			try {
				dateStr =date.format(ToolsUtils.getDateObjectAfter(qpTTPCaseStatVO.getTPHandleTimeEnd(),1));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
			fromSql.append(" and tt.TPHandleTime <  '"+dateStr+"' ");
		}
		//证件类型
		if(qpTTPCaseStatVO.getDriverIDType()!=null&&!"".equals(qpTTPCaseStatVO.getDriverIDType())){
			
			fromSql.append(" and tt.DriverIDType =  '"+qpTTPCaseStatVO.getDriverIDType()+"' ");
		}
		//证件号码
		if(qpTTPCaseStatVO.getDriverIDNumber()!=null&&!"".equals(qpTTPCaseStatVO.getDriverIDNumber())){
			fromSql.append(" and tt.DriverIDNumber =  '"+qpTTPCaseStatVO.getDriverIDNumber()+"' ");
		}
		//当事人姓名
		if(qpTTPCaseStatVO.getDriverName()!=null&&!"".equals(qpTTPCaseStatVO.getDriverName())){
			fromSql.append(" and tt.DriverName =  '"+qpTTPCaseStatVO.getDriverName()+"' ");
		}
		//车牌号
		if(qpTTPCaseStatVO.getDriverVehicleNumber()!=null&&!"".equals(qpTTPCaseStatVO.getDriverVehicleNumber())){
			fromSql.append(" and tt.DriverVehicleNumber =  '"+qpTTPCaseStatVO.getDriverVehicleNumber()+"' ");
		}
		//警员姓名
		if(qpTTPCaseStatVO.getTPUserName()!=null&&!"".equals(qpTTPCaseStatVO.getTPUserName())){
			fromSql.append(" and tt.TPUserName =  '"+qpTTPCaseStatVO.getTPUserName()+"' ");
		}
		//认字编号
		if(qpTTPCaseStatVO.getCaseSerialNo()!=null&&!"".equals(qpTTPCaseStatVO.getCaseSerialNo())){
			fromSql.append(" and tt.CaseSerialNo =  '"+qpTTPCaseStatVO.getCaseSerialNo()+"' ");
		}
		//天气 
		if(qpTTPCaseStatVO.getCaseWeather()!=null&&!"".equals(qpTTPCaseStatVO.getCaseWeather())){
			fromSql.append(" and tt.CaseWeather =  '"+qpTTPCaseStatVO.getCaseWeather()+"' ");
		}
		//受理点
		if(qpTTPCaseStatVO.getCenterId()!=null&&!"".equals(qpTTPCaseStatVO.getCenterId())){
			fromSql.append(" and tt.CenterId =  '"+qpTTPCaseStatVO.getCenterId()+"' ");
		}
		 //保险公司
		if(qpTTPCaseStatVO.getCoId()!=null&&!"".equals(qpTTPCaseStatVO.getCoId())){
			fromSql.append(" and tt.CoId =  '"+qpTTPCaseStatVO.getCoId()+"' ");
		}
		//法律法规
		if(qpTTPCaseStatVO.getLawId()!=null&&!"".equals(qpTTPCaseStatVO.getLawId())){
			fromSql.append(" and tt.LawId =  '"+qpTTPCaseStatVO.getLawId()+"' ");
		}
		
		 //归属市
		if(qpTTPCaseStatVO.getCaseCity()!=null&&!"".equals(qpTTPCaseStatVO.getCaseCity())){
			fromSql.append(" and tt.casecity =  '"+qpTTPCaseStatVO.getCaseCity()+"' ");
		}
		
		
//		qpTTPCaseStatVO.getCaseTimeStart   //受理时间
//		qpTTPCaseStatVO.getCaseTimeEnd
//		qpTTPCaseStatVO.getTPHandleTimeStart //事发时间
//		qpTTPCaseStatVO.getTPHandleTimeEnd
//		qpTTPCaseStatVO.getDriverIDType  //证件类型
//		qpTTPCaseStatVO.getDriverIDNumber //证件号码
//		qpTTPCaseStatVO.getDdriverName   //当事人姓名
//		qpTTPCaseStatVO.getDriverVehicleNumber  //车牌号
//		qpTTPCaseStatVO.getTPUserName //警员姓名
//		qpTTPCaseStatVO.getCaseSerialNo//认字编号
//		qpTTPCaseStatVO.getCaseWeather //天气 
//		qpTTPCaseStatVO.getCenterId   //受理点
//		qpTTPCaseStatVO.getCoId    //保险公司
	}
	//
	//拼装查询条件
	private void getfieldListSql(StringBuffer fromSql,StringBuffer whereSql, String statType){
		 String fieldColumn = "";
	   	 fieldColumn = getFieldColumn(statType);
	   	 
	   	 //性别需要特殊处理，因为它同时关系到两张表
	   	if("ByDriverSex".equals(statType)){
	   	     fromSql.append(" select tt."+fieldColumn+" field_0,count(tt.CASEID) field_1  from v_case_stat tt where 1=1 ");

	   	 }else{
	   		 fromSql.append(" select tt."+fieldColumn+" field_0,count(DISTINCT tt.CASEID) field_1  from v_case_stat tt where 1=1 ");
	   	 }
   		 fromSql.append(whereSql);
   		 fromSql.append(" group by tt."+ fieldColumn);
	}
	/**
	 * 根据拼接查询语句
	 */
		private void getDataListSql(StringBuffer fromSql,StringBuffer whereSql,List<QpTTPCaseStatTempVO>  tempList_row,List<QpTTPCaseStatTempVO>  tempList_col , String statType_row, String statType_col){
			/*
			 * 拼接行相关SQL，
			 */
			String fieldColumn_col  = "";
			String fieldColumn_row  = "";
			//1、获取需要显示的字段
			fieldColumn_col = this.getFieldColumn(statType_col);
			
			fieldColumn_row = this.getFieldColumn(statType_row);
			
			fromSql.append(" SELECT tt."+fieldColumn_col+" AS field_0");
	   		 
	   		for(int i=0;i<tempList_col.size();i++){
	   			int j=i+1;
	   			//性别特殊处理
	   			if("ByDriverSex".equals(statType_row)){
	   				fromSql.append("  ,COUNT(CASE tt."+fieldColumn_row+" WHEN '"+tempList_col.get(i).getField_0()+"' THEN '"+tempList_col.get(i).getField_0()+"' END ) AS field_"+j);
	   			}else{
	   				fromSql.append("  ,COUNT( DISTINCT (CASE tt."+fieldColumn_row+" WHEN '"+tempList_col.get(i).getField_0()+"' THEN  tt.CASEID END) ) AS field_"+j);
	   			}
	   		  }
	   		fromSql.append(" from v_case_stat tt  where 1=1");
	   		fromSql.append(whereSql);
	   		fromSql.append("  GROUP BY tt."+fieldColumn_col);
	}
		
	private String getFieldColumn(String statType){
		String fieldColumn = "";
		 	//日期
		 	if("ByDate".equals(statType)){
		   		fieldColumn = "casedDateT";
		   	 //城市	 
		   	 }else if("ByCity".equals(statType)){
		   		fieldColumn = "CityName";
		   	 //区
		   	 }else if("ByDistrict".equals(statType)){
		   		fieldColumn = "DistrictName";
		   	 //主干道
		   	 }else if("ByRoad".equals(statType)){
		   		fieldColumn = "RoadName";
		   	 //街道   --同主干道处理
		   	 }else if("ByStreet".equals(statType)){
		   		fieldColumn = "RoadName";
		   	 //时间段
		   	 }else if("ByTimeSpan".equals(statType)){
		   		fieldColumn = "caseTimeT";
		   	 //性别	 
		   	 }else if("ByDriverSex".equals(statType)){
		   		fieldColumn = "DriverSexName";
		   	 //天气
		   	 }else if("ByWeather".equals(statType)){
		   		fieldColumn = "WeatherTypeName";
		   	 }else if("ByfastCenter".equals(statType)){
			   		fieldColumn = "centerName";
			 }
		 	 //保险公司
		     else if("ByCompany".equals(statType)){
		   		fieldColumn = "coName";
		     }
		 	//法律法规
		     else if("ByLaw".equals(statType)){
		   		fieldColumn = "lawWords";
		     }
		return fieldColumn;
	}
	
	public HSSFWorkbook getDownLoadWB(List<String> fieldList, List<QpTTPCaseStatTempVO> tempList) throws Exception {
		String title = "案件统计表";
		
		HSSFWorkbook wb = writeDataToExcel(tempList, fieldList, title);
		return wb;
	}
	private HSSFWorkbook writeDataToExcel(List<QpTTPCaseStatTempVO> dataList, List<String> fileds, String title) throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		// 创建的第一行是 标题
		HSSFRow hssfRowTitle = sheet.createRow(0);
		CellRangeAddress cellRangeAddr = new CellRangeAddress(0, 0, 0, fileds.size() - 1);
		sheet.addMergedRegion(cellRangeAddr);
		HSSFCell titleCell = hssfRowTitle.createCell(0);
		HSSFCellStyle titleStyle = ExcelUtils.getTitleStyle(workbook, "黑体", (short) 20);
		titleCell.setCellValue(title);
		titleCell.setCellStyle(titleStyle);

		// 创建的第二行是表头
		HSSFRow hssfRowHead = sheet.createRow(1);
		HSSFCellStyle headStyle = ExcelUtils.getTitleStyle(workbook, "宋体", (short) 10);
		HSSFCell cellsHead = null;
		for(int i = 0; i < fileds.size(); i++) {
			cellsHead = hssfRowHead.createCell(i);
			cellsHead.setCellStyle(headStyle);
			cellsHead.setCellValue(fileds.get(i));
		}
		// 对表头一下的数据进行写入
		HSSFCellStyle bodyStyle = ExcelUtils.getBodyStyle(workbook, "宋体", (short) 10);
		HSSFRow hssfRow = null;
		HSSFCell cells = null;
		int size = dataList.size();
		for(int i = 0; i < size; i++) {
			hssfRow = sheet.createRow(i + 2);// 数据行，从第2行开始
			QpTTPCaseStatTempVO data = (QpTTPCaseStatTempVO) dataList.get(i);
			for(int j = 0; j < fileds.size(); j++) {
				cells = hssfRow.createCell(j);// 数据列，从0开始
				cells.setCellStyle(bodyStyle);
				String value = (data.getData(j)==null?"":data.getData(j).toString());
				cells.setCellValue(value);
				}
			}
		// 让每一个列进行自动调整大小
		for(int i = 0; i < fileds.size(); i++) {
			sheet.autoSizeColumn((short) i);
		}
		return workbook;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getDoingTaskStat(
			QpTTPCaseStatVO qpTTPCaseStat) {
		// 返回用Map
		HashMap<String, Object> rMap = new HashMap<String, Object>();
		// 显示结果列
		String[] fieldList = null;
		// 最终查询SQL
		StringBuffer mainSqlBuf = new StringBuffer();
		// 查询参数
		List<Object> params = new ArrayList<Object>();
		mainSqlBuf.append(" SELECT (SELECT c.CENTERNAME from qp_t_tp_fast_center c where c.CENTERID = g.CENTERID) AS field_0, ");
		mainSqlBuf.append(" COUNT(IF(g.TPHANDLESTATUS = 2,1,NULL)) AS field_1, ");
		mainSqlBuf.append(" COUNT(IF(g.TPHANDLESTATUS = 4,1,NULL)) AS field_2, ");
		mainSqlBuf.append(" COUNT(IF(g.TPHANDLESTATUS = 5,1,NULL)) AS field_3 ");
		mainSqlBuf.append(" FROM qp_t_tp_case g INNER JOIN qp_t_ic_accident t ON g.caseid = t.caseid ");
		mainSqlBuf.append(" WHERE 1 = 1 AND g.TPHANDLESTATUS in (2,4,5) AND g.CENTERID is not null ");
		if (qpTTPCaseStat.getCoId() != null && !"".equals(qpTTPCaseStat.getCoId())) {
			mainSqlBuf.append("  AND EXISTS(SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid AND t2.coId = ?) ");
			params.add(qpTTPCaseStat.getCoId());
		}
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		if (qpTTPCaseStat.getTPHandleTimeStart() != null && !"".equals(qpTTPCaseStat.getTPHandleTimeStart())) {
			mainSqlBuf.append("  AND g.TPHandleTime > ? ");
			params.add(qpTTPCaseStat.getTPHandleTimeStart());
		}
		
		if (qpTTPCaseStat.getTPHandleTimeEnd() != null && !"".equals(qpTTPCaseStat.getTPHandleTimeEnd())) {
			String dateStr = "";
			try {
				dateStr =date.format(ToolsUtils.getDateObjectAfter(qpTTPCaseStat.getTPHandleTimeEnd(),1));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
			mainSqlBuf.append("  AND g.TPHandleTime < ? ");
			params.add(dateStr);
		}
		
		mainSqlBuf.append(" GROUP BY g.CENTERID");
		List<QpTTPCaseStatTempVO> tempList = new ArrayList<QpTTPCaseStatTempVO>();
		fieldList = new String[] {
				"案件状态",
				"数量" };
		tempList = sysCommonDao.findBySql(QpTTPCaseStatTempVO.class,
				"select * from (" + mainSqlBuf.toString() + " ) model", 1,
				100, params.toArray()).getResult();
		rMap.put("fieldList", fieldList);
		rMap.put("tempList", tempList);
		return rMap;
	}
}
