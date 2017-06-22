/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.ExcelUtils;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTTPCaseDaoHibernate;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.schema.model.UserKey;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.qp.service.facade.UserKeyService;
import com.picc.qp.util.Constants;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.service.facade.IUmTUserService;
import com.report.bean.ClientBean;
import com.report.bean.ClientReport;
import com.sinosoft.sysframework.common.datatype.DateTime;

import edu.emory.mathcs.backport.java.util.Collections;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service("qpTTPCaseService")
public class QpTTPCaseServiceSpringImpl implements IQpTTPCaseService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPCaseDaoHibernate qpTTPCaseDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private IQpTICAccidentService qpTICAccidentService;
	
	@Autowired
	private IQpTCommonService qpTCommonService;
	
	@Autowired
	private IQpTTPLawService qpTTPLawService;
	
	@Autowired
	private IQpTICCompanyService qpTICCompanyService;
	
	@Autowired
	private IQpTCOMProvinceService qpTCOMProvinceService;
	
	@Autowired
	private IQpTCOMCityService qpTCOMCityService;
	
	@Autowired
	private IQpTCOMDistrictService qpTCOMDistrictService;
	
	@Autowired
	private IUmTUserService iUmTUserService;
	
	@Autowired
	private UserKeyService userKeyService;

	/**
	 * 根据主键对象QpTTPCaseId获取QpTTPCase信息
	 * @param QpTTPCaseId
	 * @return QpTTPCase
	 */
	public QpTTPCase findQpTTPCaseByPK(QpTTPCaseId id) throws Exception{
			return qpTTPCaseDao.get(QpTTPCase.class,id);
	}

	/**
	 * 根据qpTTPCase和页码信息，获取Page对象
	 * @param qpTTPCase
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPCase的Page对象
	 */
	public Page findByQpTTPCase(QpTTPCase qpTTPCase, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTTPCase生成queryRule
		if(qpTTPCase.getCaseTimeStart() != null) {
			queryRule.addGreaterEqual("caseTime", qpTTPCase.getCaseTimeStart());
        }
		return qpTTPCaseDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTTPCase信息
	 * @param QpTTPCase
	 */
	public void updateQpTTPCase(QpTTPCase qpTTPCase) throws Exception{
	        QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
	        qpTTPCaseId.setCaseId(qpTTPCase.getCaseId());
	        qpTTPCase.setId(qpTTPCaseId);
	        // 更新涉案人数
	        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	        queryRule.addEqual("caseId", qpTTPCase.getCaseId());
	        List<QpTICAccident> qpTICAccidentList = commonDao.find(QpTICAccident.class, queryRule);
	        if(!ToolsUtils.isEmpty(qpTICAccidentList)) {
	        	qpTTPCase.setRelatedPersons(qpTICAccidentList.size());
	        }			
	        //qpTTPCase.setTpHandleStatus("1");
            qpTTPCase.setOperateTimeForHis(new Date());
            qpTTPCase.setValidStatus("1");			
            qpTTPCaseDao.update(qpTTPCase);
	}
	
	

	/**
	 * 更新QpTTPCase信息
	 * @param QpTTPCase
	 */
	public void updateQpTTPCasePicGroupId(QpTTPCase qpTTPCase) throws Exception{
	        QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
	        qpTTPCaseId.setCaseId(qpTTPCase.getCaseId());
	        qpTTPCase.setId(qpTTPCaseId);
            qpTTPCase.setOperateTimeForHis(new Date());
            qpTTPCaseDao.update(qpTTPCase);
	}

	/**
	 * 保存QpTTPCase信息
	 * @param QpTTPCase
	 */
	public void saveQpTTPCase(QpTTPCase qpTTPCase) throws Exception{
			String caseId = sysCommonDao.generateID("C", "QP_SEQ_TP_CASE", 10);
			QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
			qpTTPCaseId.setCaseId(caseId);
			qpTTPCase.setId(qpTTPCaseId);
			qpTTPCaseDao.save(qpTTPCase);
	}
	
	/**
	 * 保存QpTTPCase信息
	 * @param QpTTPCase
	 */
	public QpTTPCase  saveOrupdateQpTTPCase(QpTTPCase qpTTPCase) throws Exception{
			  String caseId = qpTTPCase.getCaseId();                          // 案件ID
			    //新增案件
			    if(ToolsUtils.isEmpty(caseId)) {
			        caseId = sysCommonDao.generateID("C", "QP_SEQ_TP_CASE", 10);  // 生成案件流水号
			       // caseSerialNo = sysCommonDao.generateID(ToolsUtils.getCurrentDate("yyyy"), "QP_SEQ_TP_CASE_NO", 8); // 生成认字编号
			       // caseSerialNo = caseSerialNo + "A";
		            QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
		            qpTTPCaseId.setCaseId(caseId);                              // 案件流水号
		            qpTTPCase.setId(qpTTPCaseId);                               
		           // qpTTPCase.setCaseSerialNo(caseSerialNo);                    // 认字编号
//		            qpTTPCase.setTpHandleStatus("0");                           // 暂定
		            qpTTPCase.setCreatorCode(qpTTPCase.getTpLoginId());         // 创建人
		            qpTTPCase.setInsertTimeForHis(new Date());                  // 创建时间
		            qpTTPCase.setOperateTimeForHis(new Date());                 // 更新时间
		            qpTTPCase.setValidStatus("1");                              // 有效状态
		            qpTTPCaseDao.save(qpTTPCase);
			        //更新
			    }else {
			    	QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
			        qpTTPCaseId.setCaseId(qpTTPCase.getCaseId());
			        qpTTPCase.setId(qpTTPCaseId);
		            qpTTPCase.setOperateTimeForHis(new Date());
			    	qpTTPCaseDao.save(qpTTPCase);
			    }
				return qpTTPCase;
	}

	/**
	 * 根据主键对象，删除QpTTPCase信息
	 * @param QpTTPCaseId
	 */
	public void deleteByPK(QpTTPCaseId id) throws Exception{
			qpTTPCaseDao.deleteByPK(QpTTPCase.class,id);
	}
	
	
	/**
     * 根据主键对象QpTTPCaseId获取QpTTPCase信息
     * @param QpTTPCaseId
     * @return QpTTPCase
     */
    public QpTTPCase findQpTTPCaseById(QpTTPCase qpTTPCaseT) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.caseid caseid                                                                                                                                                       ");
        sql.append("    , c.caseSerialNo caseSerialNo                                                                                                                                               ");
        sql.append("    , c.caseTime caseTime                                                                                                                                                       ");
        sql.append("    , c.caseWeather caseWeather                                                                                                                                                 ");
        sql.append("   , (select a.codeCName from MC_D_NewCode a,MC_D_Type b where a.codeType=b.newCodeType and a.CODETYPE = 'WeatherType' and a.codecode = c.caseWeather ) caseWeatherDesc       ");
        sql.append("    , c.caseProvince caseProvince                                                                                                                                               ");
        sql.append("    , c.caseCity caseCity                                                                                                                                                       ");
        sql.append("    , c.caseDistrict caseDistrict                                                                                                                                               ");
        sql.append("    , c.caseRoad caseRoad                                                                                                                                                       ");
        sql.append("    , c.caseStreet caseStreet                                                                                                                                                   ");
        sql.append("   , CONCAT(                                                                                                                                         ");
        
        sql.append("   				 IFNULL((SELECT a.provname                                                                                                                                         ");
        sql.append("                          FROM qp_t_com_province a                                                                                                                            ");
        sql.append("                         WHERE a.provid = c.caseProvince),                                                                                                                    ");
        sql.append("                        ''),                                                                                                                                                  ");
        sql.append("                 IFNULL((SELECT a1.cityname                                                                                                                                   ");
        sql.append("                          FROM qp_t_com_city a1                                                                                                                               ");
        sql.append("                         WHERE a1.cityid = c.caseCity),                                                                                                                       ");
        sql.append("                        ''),                                                                                                                                                  ");
        sql.append("                 IFNULL((SELECT a2.districtname                                                                                                                               ");
        sql.append("                          FROM qp_t_com_district a2                                                                                                                           ");
        sql.append("                         WHERE a2.districtid = c.caseDistrict),                                                                                                               ");
        sql.append("                        ''),                                                                                                                                                  ");
//        sql.append("                 IFNULL((SELECT a3.roadname                                                                                                                                   ");
//        sql.append("                          FROM qp_t_com_road a3                                                                                                                               ");
//        sql.append("                         WHERE a3.roadid = c.caseRoad),                                                                                                                       ");
//        sql.append("                        ''),                                                                                                                                                  ");
        sql.append("                 IFNULL(c.caseStreet, '')) caseAddress                                                                                                                        ");
       
        sql.append("    , c.tpLoginId tpLoginId                                                                                                                                                     ");
        sql.append("    , c.tpUserName tpUserName                                                                                                                                                   ");
        sql.append("    , c.tpEmployeeId tpEmployeeId                                                                                                                                               ");
        sql.append("    , c.tpHandleStatus tpHandleStatus                                                                                                                                           ");
        sql.append("    , c.tpHandleTime tpHandleTime                                                                                                                                               ");
        sql.append("    , c.tpHandleNotes tpHandleNotes                                                                                                                                             ");
        sql.append("    , c.caseResult caseResult                                                                                                                                                   ");
        sql.append("    , c.caseNotes caseNotes                                                                                                                                                     ");
        sql.append("    , c.relatedPersons relatedPersons                                                                                                                                           ");
        sql.append("    , c.policeName policeName                                                                                                                                                   ");
        
        sql.append("    , c.assistorId assistorId                                                                                                                                                   ");
        sql.append("    , c.assistorName assistorName                                                                                                                                                   ");
        sql.append("    , c.policeEmployeeId policeEmployeeId                                                                                                                                       ");
        sql.append("    , c.centerId centerId                                                                                                                                                       ");
        sql.append("    , c.spanId spanId                                                                                                                                                           ");
        sql.append("    , c.creatorCode creatorCode                                                                                                                                                 ");
        sql.append("    , c.insertTimeForHis insertTimeForHis                                                                                                                                       ");
        sql.append("    , c.updaterCode updaterCode                                                                                                                                                 ");
        sql.append("    , c.operateTimeForHis operateTimeForHis                                                                                                                                     ");
        sql.append("    , c.validStatus validStatus                                                                                                                                                 ");
        sql.append("    , c.casetype casetype                                                                                                                                                 ");
        sql.append("    , c.longitude longitude                                                                                                                                                 ");
        sql.append("    , c.latitude latitude                                                                                                                                                 ");
        sql.append(" from qp_t_tp_case c                                                                                                                                                          ");
        sql.append("   where c.caseid = ? ");   
        
        List<QpTTPCase> list = (List<QpTTPCase>) commonDao.findListBySql(sql.toString(), QpTTPCase.class, qpTTPCaseT.getCaseId());
        QpTTPCase qpTTPCase = null;
        if(ToolsUtils.notEmpty(list)){
            qpTTPCase = list.get(0);
        }
        return qpTTPCase ;
    }
    /**
     * 根据主键对象QpTTPCaseId获取QpTTPCase信息
     * @param QpTTPCaseId
     * @return QpTTPCase
     */
    public QpTTPCase findEasyCaseById(QpTTPCase qpTTPCaseT) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select c.caseid caseid                                                                                                                                                       ");
    	sql.append("    , c.caseSerialNo caseSerialNo                                                                                                                                               ");
    	sql.append("    , c.caseTime caseTime                                                                                                                                                       ");
    	sql.append("   , CONCAT(                                                                                                                                         ");
    	sql.append("   				 IFNULL((SELECT a.provname                                                                                                                                         ");
    	sql.append("                          FROM qp_t_com_province a                                                                                                                            ");
    	sql.append("                         WHERE a.provid = c.caseProvince),                                                                                                                    ");
    	sql.append("                        ''),                                                                                                                                                  ");
    	sql.append("                 IFNULL((SELECT a1.cityname                                                                                                                                   ");
    	sql.append("                          FROM qp_t_com_city a1                                                                                                                               ");
    	sql.append("                         WHERE a1.cityid = c.caseCity),                                                                                                                       ");
    	sql.append("                        ''),                                                                                                                                                  ");
    	sql.append("                 IFNULL((SELECT a2.districtname                                                                                                                               ");
    	sql.append("                          FROM qp_t_com_district a2                                                                                                                           ");
    	sql.append("                         WHERE a2.districtid = c.caseDistrict),                                                                                                               ");
    	sql.append("                        ''),                                                                                                                                                  ");
    	sql.append("                 IFNULL(c.caseStreet, '')) caseAddress                                                                                                                        ");
    	sql.append(" from qp_t_tp_case c                                                                                                                                                          ");
    	sql.append("   where c.caseid = ? ");   
    	
    	List<QpTTPCase> list = (List<QpTTPCase>) commonDao.findListBySql(sql.toString(), QpTTPCase.class, qpTTPCaseT.getCaseId());
    	QpTTPCase qpTTPCase = null;
    	if(ToolsUtils.notEmpty(list)){
    		qpTTPCase = list.get(0);
    	}
    	return qpTTPCase ;
    }
    
    /**
     * 案件列表查询
     * @param qpTTPCase
     * @return
     * @throws Exception
     */
    public Page findQpTTPCasePageBySql(QpTTPCase qpTTPCase, int pageNo, int pageSize) throws Exception  {
        StringBuffer sql = new StringBuffer();
        sql.append("      SELECT distinct g.caseid caseId,                                             ");
        sql.append("      g.caseserialno caseSerialNo,                                        ");
        sql.append("      g.policename policeName,                                            ");
        sql.append("      g.policeemployeeid policeEmployeeId,                                ");
        sql.append("      g.tpusername tpUserName,                                            ");
        
        sql.append("      g.assistorId assistorId,                                            ");
        sql.append("      g.assistorName assistorName,                                        ");
        
        sql.append("      g.tploginid tpLoginId,                                              ");
        sql.append("      g.casetime caseTime,                                                ");
        sql.append("      concat(                                                             ");
        
        sql.append("      		 ifnull((SELECT a.provname                                    ");
        sql.append("                      FROM qp_t_com_province a                            ");
        sql.append("                     WHERE a.provid = g. caseprovince),                   ");
        sql.append("                    ''),                                                  ");
        sql.append("             ifnull((SELECT a1.cityname                                   ");
        sql.append("                      FROM qp_t_com_city a1                               ");
        sql.append("                     WHERE a1.cityid = g. casecity),                      ");
        sql.append("                    ''),                                                  ");
        sql.append("             ifnull((SELECT a2.districtname                               ");
        sql.append("                      FROM qp_t_com_district a2                           ");
        sql.append("                     WHERE a2.districtid = g. casedistrict),              ");
        sql.append("                    ''),                                                  ");
        sql.append("             ifnull((SELECT a3.casestreet                                 ");
	    sql.append("                      FROM qp_t_tp_case a3                                ");
	    sql.append("                     WHERE a3.caseId = g.caseId),                         ");
        
        sql.append("                    '') ) caseAddress,                                     ");
        sql.append("      g.caseweather caseWeather,                                          ");
        sql.append("      (SELECT a5.codecname                                                ");
        sql.append("         FROM mc_d_newcode a5                                             ");
        sql.append("        WHERE a5.codetype = 'WeatherType'                                 ");
        sql.append("          AND a5.codecode = g. caseweather) caseWeatherDesc,              ");
        sql.append("      g.tphandletime tpHandleTime,                                        ");
        sql.append("      g.caseResult caseResult,                                            ");
        sql.append("      g.casenotes caseNotes,                                              ");
        sql.append("      g.tpHandleStatus tpHandleStatus ,                                    ");
        sql.append("      g.groupId groupId                            				         ");
        sql.append(" FROM qp_t_tp_case g  inner  join qp_t_ic_accident t on g.caseid = t.caseid  where 1=1");
        
        this.appendParam(qpTTPCase, sql);
        if(ToolsUtils.notEmpty(qpTTPCase.getBusinessType())){
        	if(qpTTPCase.getBusinessType().equals("1")) {
            	 sql.append(" order by find_in_set(g.tpHandleStatus,'4,2,5,0,1,3'), ");
            }
            else if(qpTTPCase.getBusinessType().equals("2")) {
           	     sql.append(" order by find_in_set(g.tpHandleStatus,'1,0,2,3,4,5'), ");
            }
            else if(qpTTPCase.getBusinessType().equals("3")) {
                 sql.append(" order by find_in_set(g.tpHandleStatus,'3,0,1,2,4,5'), ");
            }else{
            	 sql.append(" order by g.tpHandleStatus,         ");
            }
        }else{
        	 sql.append(" order by g.tpHandleStatus,         ");
        }
        
        sql.append("     g.tpHandleTime desc                  ");
       
        return  sysCommonDao.findBySql(QpTTPCase.class,sql.toString(),  pageNo, pageSize, null);
    }

	@SuppressWarnings("deprecation")
	public HSSFWorkbook getDownLoadWB(QpTTPCase qpTTPCase, String filePath)  throws Exception  {
		StringBuffer transSql = new StringBuffer();
		String caseIdSql = "";
		transSql.append("SELECT g.caseId FROM `qp_t_ic_accident` t , qp_t_tp_case g WHERE t.caseid = g.caseid");
		//拼接参数
		
		this.appendParam(qpTTPCase, transSql);
		List caseIdList = sysCommonDao.findBySql(transSql.toString(), new Object[]{});
		for(int i = 0; i < caseIdList.size(); i++)  
        {  
			caseIdSql+=(" '"+caseIdList.get(i)+"', ");  
        }
		if(!"".equals(caseIdSql)){
			caseIdSql=caseIdSql.substring(0, caseIdSql.lastIndexOf(","));
		}
		StringBuffer exportSql = new StringBuffer();
        exportSql.append("        SELECT  g.caseserialno AS caseSerialNo,                                      ");
        exportSql.append("        t.drivername driverName, t.driveridnumber AS driverIDNumber,                          ");
        exportSql.append("        IF(t. driversex = '0', '男', '女') AS driverSexDesc,               ");
//        exportSql.append("        t.driverphone AS driverPhone,                                      ");
//        exportSql.append("        t.drivermobile AS driverMobile,                                    ");
        exportSql.append("       (SELECT ");
		exportSql.append("      	 a8.codecname ");
		exportSql.append("       		  FROM");
		exportSql.append("       			    mc_d_newcode a8 ");
		exportSql.append("      	  WHERE a8.codetype = 'VehicleType' ");
		exportSql.append("       	    AND a8.codecode = t.drivervehicletype) AS driverVehicleType, ");
        exportSql.append(" 	t.driverVehicleNumber AS driverVehicleNumber,    ");
	    exportSql.append("        (SELECT a6.coname                                               ");
	    exportSql.append("           FROM qp_t_ic_company a6                                      ");
	    exportSql.append("          WHERE a6.coid = t. coid) AS coName,                              ");
	    exportSql.append("        g.casetime AS caseTime,                                           ");
	    exportSql.append("      concat(                                                             ");
	    
	    exportSql.append("             ifnull((SELECT a0.provname  									"); 
	    exportSql.append("                      FROM qp_t_com_province a0                               ");
	    exportSql.append("                     WHERE a0.provid = g.caseprovince),                      ");
	    exportSql.append("                    ''),													");
	    exportSql.append("             ifnull((SELECT a1.cityname                                   ");
	    exportSql.append("                      FROM qp_t_com_city a1                               ");
	    exportSql.append("                     WHERE a1.cityid = g.casecity),                      ");
	    exportSql.append("                    ''),                                                  ");
	    exportSql.append("             ifnull((SELECT a2.districtname                               ");
	    exportSql.append("                      FROM qp_t_com_district a2                           ");
	    exportSql.append("                     WHERE a2.districtid = g.casedistrict),              ");
	    exportSql.append("                    ''),                                                  "); 
	    
//	    exportSql.append("             ifnull((SELECT b1.roadname                                   ");
//	    exportSql.append("                      FROM qp_t_com_road b1                               ");
//	    exportSql.append("                     WHERE b1.roadid = g.caseroad),                      ");
//	    exportSql.append("                    ''))                                                 ");
	    
	    exportSql.append("             ifnull((SELECT a3.casestreet                                   ");
	    exportSql.append("                      FROM qp_t_tp_case a3                               ");
	    exportSql.append("                     WHERE a3.caseId = g.caseId),                      ");
	    exportSql.append("                    '')                                                  ");
	    
	    
	    exportSql.append("                       )  AS caseAddress,                                     ");
	    exportSql.append("                    c.centername AS centerName,                                    ");
	    exportSql.append("        (SELECT a8.codecname                                            ");
	    exportSql.append("           FROM mc_d_newcode a8                                         ");
	    exportSql.append("          WHERE a8.codetype = 'ResponsibilityType'                      ");
	    exportSql.append("            AND a8.codecode =t.driverliability) AS driverLiabilityDesc,   ");
	    exportSql.append("        (SELECT a7.lawwords                                             ");
	    exportSql.append("           FROM qp_t_tp_law a7                                          ");
	    exportSql.append("          WHERE a7.lawid = t. driverlawid) AS driverLawDesc,               ");
	    exportSql.append("          case when g.tpHandleStatus='2' then '待查勘' when g.tpHandleStatus='3' then '已退回' when g.tpHandleStatus='4' then '查勘处理中' when g.tpHandleStatus='5' then '已受理' when g.tpHandleStatus='6' then '已注销' end ,               ");//案件状态
	    exportSql.append("          t.estimateLossSum estimateLossSum,               ");//估损金额
	    exportSql.append("          t.fixedLossPrice fixedLossPrice,               ");//定损价格
	    exportSql.append("          IF((SELECT COUNT(1) FROM `qp_t_ic_picture` p WHERE p.`GROUPID` =t.`GROUPID` OR  p.`GROUPID` = t.`SURVEYGROUPID`)>0,'是','否') as 'isGroup',               ");//是否上传过图片
	    exportSql.append("          IF((SELECT COUNT(1) FROM qp_t_ic_accident_project p WHERE p.acciId=t.acciId)>0,'是','否') as 'isProject'               ");//是否录入定损单
	    exportSql.append("   FROM qp_t_ic_accident t, qp_t_tp_case g, qp_t_tp_fast_center c     ");
	    exportSql.append("  WHERE   c.centerid = g.centerid  AND t.caseid = g.caseid         ");
	    
	    if(!"".equals(caseIdSql)){
	    	exportSql.append("AND g.caseId IN("+caseIdSql+")");
	    }else{
	    	exportSql.append("     AND 1=2                                                      ");
	    }
	    
		exportSql.append("  order by t.acciid                                                     ");
		
		System.err.println(exportSql);
		// 3、获取结果集
		List resultList = sysCommonDao.findBySql(exportSql.toString(), new Object[]{});

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		InputStream is = new FileInputStream(new File(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		HSSFSheet sheet = workbook.getSheetAt(0); 

		// 对表头一下的数据进行写入
		HSSFCellStyle bodyStyle = ExcelUtils.getBodyStyle(workbook, "宋体", (short) 10);
		HSSFRow hssfRow = null;
		HSSFCell cells = null;
		int size = resultList.size();
		for(int i = 0; i < size; i++) {
			hssfRow = sheet.createRow(i + 2);// 数据行，从第2行开始
			Object[] data = (Object[]) resultList.get(i);

			for(int j = 0; j < data.length; j++) {
				cells = hssfRow.createCell(j);// 数据列，从0开始
				cells.setCellStyle(bodyStyle);
				if(data[j] != null) {
					if(data[j] instanceof Date) {
						cells.setCellValue(dateTimeFormatter.format((Date) data[j]));
					}
					else {
						cells.setCellValue(data[j].toString());
						//int length = data[j].toString().getBytes().length;
						//sheet.setColumnWidth((short)i,(short)(length*256));
					}
				}
			}
		}
		
		// 让每一个列进行自动调整大小
		for(int i = 0; i < size; i++) {
			sheet.autoSizeColumn((short) i);
		}

		return workbook;
	}
	
	private void appendParam(QpTTPCase qpTTPCase, StringBuffer sql) {
	    //案件ID
	    if(ToolsUtils.notEmpty(qpTTPCase.getCaseId())) {
		sql.append(" AND g.CASEID = '").append(qpTTPCase.getCaseId()).append("' ");
	    }
		 //案件状态
        if(ToolsUtils.notEmpty(qpTTPCase.getTpHandleStatus())) {
        	if("-6".equals(qpTTPCase.getTpHandleStatus())){
        		sql.append(" AND g.tpHandleStatus != '6' ");//不查询已删除的
        	}else {
        		sql.append(" AND g.tpHandleStatus = '").append(qpTTPCase.getTpHandleStatus()).append("' ");
			}
        }
		
//		// 业务类型:1-查勘员（案件保存后才可以估损录入）
//		if(ToolsUtils.notEmpty(qpTTPCase.getBusinessType()) && "1".equals(qpTTPCase.getBusinessType())) {
//			sql.append(" AND g.tpHandleStatus ='1' ");
//		}
		// 事发时间起
        if(qpTTPCase.getCaseTimeStart() != null) {
            sql.append(" AND g.CASETIME >= '").append(new DateTime(qpTTPCase.getCaseTimeStart(),DateTime.YEAR_TO_MILLISECOND)).append("' ");
        }
        // 事发时间起
        if(qpTTPCase.getCaseTimeEnd() != null) {
           // sql.append(" AND g.CASETIME < '").append(new DateTime(qpTTPCase.getCaseTimeEnd(),DateTime.YEAR_TO_DAY).addDay(1)).append("' ");
            sql.append(" AND g.CASETIME < '").append(new DateTime(qpTTPCase.getCaseTimeEnd(),DateTime.YEAR_TO_MILLISECOND)).append("' ");
        }
        
        // 处理时间起
        if(qpTTPCase.getTpHandleTimeStart() != null) {
            sql.append(" AND g.tphandletime >= '").append(new DateTime(qpTTPCase.getTpHandleTimeStart(),DateTime.YEAR_TO_MILLISECOND)).append("' ");
        }
        // 处理时间起
        if(qpTTPCase.getTpHandleTimeEnd() != null) {
           // sql.append(" AND g.tphandletime < '").append(new DateTime(qpTTPCase.getTpHandleTimeEnd(),DateTime.YEAR_TO_DAY).addDay(1)).append("' ");
            sql.append(" AND g.tphandletime < '").append(new DateTime(qpTTPCase.getTpHandleTimeEnd(),DateTime.YEAR_TO_MILLISECOND)).append("' ");
        }
        // 证件类型
//        if(!"0".equals(qpTTPCase.getDriverIDType())) {
//            sql.append(" AND EXISTS (SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid  AND t2.driverIDType = '").append(qpTTPCase.getDriverIDType()).append("') ");
//        }
        if(!"0".equals(qpTTPCase.getDriverIDType()) && ToolsUtils.notEmpty(qpTTPCase.getDriverIDType())) {
          sql.append(" AND t.driverIDType = '").append(qpTTPCase.getDriverIDType()).append("' ");
      }
        // 证件号码
        if(ToolsUtils.notEmpty(qpTTPCase.getDriverIDNumber())) {
           // sql.append(" AND EXISTS (SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid  AND t2.driverIDNumber = '").append(qpTTPCase.getDriverIDNumber()).append("') ");
        	 sql.append(" AND t.driverIDNumber = '").append(qpTTPCase.getDriverIDNumber()).append("' ");
        }
        // 当事人姓名
        if(ToolsUtils.notEmpty(qpTTPCase.getDriverName())) {
            //sql.append(" AND EXISTS (SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid  AND t2.driverName = '").append(qpTTPCase.getDriverName()).append("') ");
        	sql.append(" AND  t.driverName = '").append(qpTTPCase.getDriverName()).append("' ");
        }
        //车牌号
        if(ToolsUtils.notEmpty(qpTTPCase.getDriverVehicleNumber())) {
            //sql.append(" AND EXISTS (SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid  AND t2.driverVehicleNumber = '").append(qpTTPCase.getDriverVehicleNumber()).append("') ");
            sql.append(" AND t.driverVehicleNumber = '").append(qpTTPCase.getDriverVehicleNumber()).append("' ");
        }
        // 警员姓名
        if(ToolsUtils.notEmpty(qpTTPCase.getPoliceName())) {
            sql.append(" AND g.policeName = '").append(qpTTPCase.getPoliceName()).append("' ");
        }
        // 认字编号
        if(ToolsUtils.notEmpty(qpTTPCase.getCaseSerialNo())) {
            sql.append(" AND g.caseSerialNo = '").append(qpTTPCase.getCaseSerialNo()).append("' ");
        }
        // 天气
        if(!"0".equals(qpTTPCase.getCaseWeather()) && ToolsUtils.notEmpty(qpTTPCase.getCaseWeather())) {
            sql.append(" AND g.caseWeather = '").append(qpTTPCase.getCaseWeather()).append("' ");
        }
        // 受理点
        if(!"0".equals(qpTTPCase.getCenterId()) && ToolsUtils.notEmpty(qpTTPCase.getCenterId())) {
            sql.append(" AND g.centerId = '").append(qpTTPCase.getCenterId()).append("' ");
        }
        // 保险公司
        if(!"0".equals(qpTTPCase.getCoId()) && ToolsUtils.notEmpty(qpTTPCase.getCoId())) {
            sql.append(" AND EXISTS (SELECT 1 FROM qp_t_ic_accident t2 WHERE t2.caseid = g.caseid  AND t2.coId = '").append(qpTTPCase.getCoId()).append("') ");
        }
        // 当前登录人的城市
        if(ToolsUtils.notEmpty(qpTTPCase.getCaseCity())) {
            sql.append(" AND g.casecity = '").append(qpTTPCase.getCaseCity()).append("' ");
        }
	}

	public HSSFWorkbook getAccountDownLoadWB(QpTTPCaseStatVO qpTTPCaseStatVO,
			String filePath) throws Exception {
		 StringBuffer exportSql = new StringBuffer();
	        exportSql.append("        SELECT   g.caseserialno AS caseSerialNo,  DATE_FORMAT(g.casetime,'%Y-%m-%d %k:%i') AS caseTime,          ");
	        exportSql.append("      concat(	                             									"); 
	        
	        exportSql.append("             ifnull((SELECT a0.provname  									"); 
		    exportSql.append("                      FROM qp_t_com_province a0                               ");
		    exportSql.append("                     WHERE a0.provid = g.caseprovince),                      ");
		    exportSql.append("                    ''),													");
		    exportSql.append("             ifnull((SELECT a1.cityname  									"); 
		    exportSql.append("                      FROM qp_t_com_city a1                               ");
		    exportSql.append("                     WHERE a1.cityid = g.casecity),                      ");
		    exportSql.append("                    ''),													");
			exportSql.append("			   ifnull((SELECT a2.districtname                               ");
		    exportSql.append("                      FROM qp_t_com_district a2                           ");
		    exportSql.append("                     WHERE a2.districtid = g.casedistrict),              ");
		    exportSql.append("                    ''),                                                  ");
		    exportSql.append("             ifnull((SELECT a3.casestreet                                   ");
		    exportSql.append("                      FROM qp_t_tp_case a3                               ");
		    exportSql.append("                     WHERE a3.caseId = g.caseId),                      ");
		    
		    exportSql.append("                    ''), (SELECT a.codecname FROM mc_d_newcode a WHERE a.codetype = 'DirectionType' AND a.codecode =  t.driverDirection)) AS caseAddress,                                     ");
	        exportSql.append("        t.driverVehicleNumber AS driverVehicleNumber, (SELECT a.codecname FROM mc_d_newcode a WHERE a.codetype = 'ResponsibilityType' AND a.codecode = t.driverLiability) AS driverLiability,");
	        exportSql.append("        t.driverName driverName,");
	       // exportSql.append("        IF(t. driverPhone = '',  t. driverMobile, t. driverPhone) AS telphone,               ");
	        exportSql.append("        (SELECT a6.coname                                               ");
		    exportSql.append("           FROM qp_t_ic_company a6                                      ");
		    exportSql.append("          WHERE a6.coid = t. coid) AS coName,                           ");
	        exportSql.append("        CAST(t.estimateLossSum AS CHAR(20)) AS estimateLossSum, CAST(t.fixedLossPrice AS CHAR(20)) AS estimateLossSum1,                                     ");
	        exportSql.append("       DATE_FORMAT(g.tPHandleTime,'%Y-%m-%d') AS tPHandleTime,                            ");
	        
	        exportSql.append("        (SELECT usr.username                                                   ");
	        exportSql.append("           FROM um_t_user usr                                                  ");
	        exportSql.append("          WHERE usr.usercode = t.lossAssessorCode) lossAssessorCode ,g.caseid           ") ;
	        
		    exportSql.append("   FROM qp_t_ic_accident t, qp_t_tp_case g, qp_t_tp_fast_center c      ");
		    exportSql.append("  WHERE    c.centerid = g.centerid  AND t.caseid = g.caseid  AND t.validstatus = '1'  ");
		    
		    //判断是不是管理员用户
		    String userCode = qpTTPCaseStatVO.getLossAssessorCode();
		    boolean manager = isManager(userCode);
		    if(!manager&&ToolsUtils.notEmpty(userCode)){
		    	// 自动台账只能查看自己的数据
		    	exportSql.append("  AND  t.lossAssessorCode = '").append(userCode).append("'");
		    }
		    
			// 拼接参数
			if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getEstimateLossTimeStart())) {
				exportSql.append("  AND  t.estimateLossTime >= '").append(qpTTPCaseStatVO.getEstimateLossTimeStart()).append("'");
			}
			if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getEstimateLossTimeEnd())) {
				exportSql.append("  AND  t.estimateLossTime < '").append(qpTTPCaseStatVO.getEstimateLossTimeEnd()).append("'");
			}
			if(!"0".equals(qpTTPCaseStatVO.getCenterId())) {
				exportSql.append("  AND g.centerid = '").append(qpTTPCaseStatVO.getCenterId()).append("'");
			}
			if(!"0".equals(qpTTPCaseStatVO.getCoId())) {
				exportSql.append("  AND t.coid = '").append(qpTTPCaseStatVO.getCoId()).append("'");
			}
			exportSql.append(" ORDER BY g.CASEID, t.driverLiability ASC    ");
			// 3、获取结果集
			List resultList = sysCommonDao.findBySql(exportSql.toString(), new Object[]{});
			
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			InputStream is = new FileInputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0); 
			// 对表头一下的数据进行写入
			HSSFCellStyle bodyStyle = ExcelUtils.getBodyStyle(workbook, "宋体", (short) 10);
			bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFRow hssfRow = null;
			HSSFCell cells = null;
			int size = resultList.size();
			String upCaseID = "";
			int uppserCaseCount = 0;
			int index = 0;
			Object[] data = null;
			for(int i = 0; i < size+1; i++) {
				
				if(i < size) {
					hssfRow = sheet.createRow(i + 2);// 数据行，从第2行开始
					data = (Object[]) resultList.get(i);
					
					String curCaseID = data[data.length-1].toString();
					if(upCaseID.equals(curCaseID)) {
						uppserCaseCount++;
					}else{
						if(uppserCaseCount!=0) {
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 0, 0));
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 1, 1));
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 2, 2));
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 10, 10));
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 11, 11));
							sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 12, 12));
						}
					    upCaseID = curCaseID ;
						uppserCaseCount=1;
						index++;
					}
					
					for(int j = 0; j < data.length; j++) {
						cells = hssfRow.createCell(j);// 数据列，从0开始
						cells.setCellStyle(bodyStyle);
						if(j == 0) {
							cells.setCellValue(index);
						}else {
							if(j < data.length - 1) {
								if(data[j] != null) {
									if(data[j] instanceof Date) {
										cells.setCellValue(dateTimeFormatter.format((Date) data[j]));
									}
									else {
										cells.setCellValue(data[j].toString());
										//int length = data[j].toString().getBytes().length;
										//sheet.setColumnWidth((short)i,(short)(length*256));
									}
								}
							}
						}
					}
					
				}else {
					// 为处理最后一行不会自动合并的问题
					hssfRow = sheet.createRow(i + 2);
					if(uppserCaseCount!=0) {
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 10, 10));
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 11, 11));
						sheet.addMergedRegion(new CellRangeAddress(i-uppserCaseCount+2,i+1, 12, 12));
					}
				}
				
			}
			
			// 让每一个列进行自动调整大小
			for(int i = 0; i < size; i++) {
				sheet.autoSizeColumn((short) i);
			}

			return workbook;
	}
	
	public String generateSerinalNo(String readNum ){

		if(readNum!=null){
			String serial="CASESERIALNO"+readNum;
			String prefix=readNum;
			List list = commonDao.findBySql("SELECT * FROM  `sequence`  WHERE NAME = ?", serial);
			if(list==null ||list.size()==0){
					commonDao.execute("insert into sequence values(?,?,?)", new Object[]{serial,0,1});
			}
			List list2 = commonDao.findBySql("SELECT * FROM  `sequence`  WHERE NAME = ?", serial);
			return commonDao.generateID(prefix, serial, 6);
			
		}
		return null;
	}
	
	/**
	 * 级联删除案件(事务)
	 * @param id
	 * @throws Exception
	 * @author obba
	 */
	public void deleteCaseAndAccidentByPK(QpTTPCaseId id) throws Exception{
		List<QpTICAccident> list = qpTICAccidentService.findQpTICAccidentInfoOnly(id.getCaseId());
		for (QpTICAccident acc : list) {
			qpTICAccidentService.deleteByPK(acc.getId());
		}
		qpTTPCaseDao.deleteByPK(QpTTPCase.class,id);
	}

	/**
	 * 封装报表datasource数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JRDataSource getDataSourcePrint(QpTTPCase qpTTPCase) throws Exception {
		// TODO Auto-generated method stub
		String caseID = qpTTPCase.getCaseId();
		List<QpTICAccident> qpTICAccidentList = qpTICAccidentService.findQpTICAccidentInfoOnly(caseID);
		Date caseTime = qpTTPCase.getCaseTime();
		
		String province = qpTTPCase.getCaseProvince();
		String city = qpTTPCase.getCaseCity();
		String district = qpTTPCase.getCaseDistrict();
		
		String provName = "";
		if(StringUtils.isNotEmpty(province)){
			QpTCOMProvinceId provinceId = new QpTCOMProvinceId();
			provinceId.setProvId(province);
			QpTCOMProvince qpTCOMProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(provinceId);
			if(qpTCOMProvince!=null){
				provName =  qpTCOMProvince.getProvName();
			}
		}
		String cityName = "";
		if(StringUtils.isNotEmpty(city)){
			QpTCOMCityId cityId = new QpTCOMCityId();
			cityId.setCityId(city);
			QpTCOMCity qpTCOMCity = qpTCOMCityService.findQpTCOMCityByPK(cityId);
			if(qpTCOMCity!=null){
				cityName = qpTCOMCity.getCityName();
			}
		}
		String districtName = "";
		if(StringUtils.isNotEmpty(district)){
			QpTCOMDistrictId districtId = new QpTCOMDistrictId();
			districtId.setDistrictId(district);
			QpTCOMDistrict qpTCOMDistrict =  qpTCOMDistrictService.findQpTCOMDistrictByPK(districtId);
			
			if(qpTCOMDistrict!=null){
				districtName = qpTCOMDistrict.getDistrictName();
			}
		}
		String accidentTime = (caseTime==null?"":(DateUtil.dateFormat(caseTime, DateUtil.FULL_DATE_STR)));
		String caseStreet =  (qpTTPCase.getCaseStreet()==null?"":qpTTPCase.getCaseStreet());
		caseStreet = caseStreet.replaceAll(provName+ cityName + districtName, "");
		String accidentPlace = (provName+ cityName + districtName + caseStreet);
		
		String weatherId = (qpTTPCase.getCaseWeather()==null?"":qpTTPCase.getCaseWeather());
		String weatherName = "";
		if(StringUtils.isEmptyStr(weatherId)){
			//没有存天气默认为晴
			weatherName = "晴";
		}else{
			weatherName =  qpTCommonService.getSelectCodeName("WeatherType",weatherId);
		}
		String quickPayNo = "";
		if("26".equals(qpTTPCase.getCenterId())){
			quickPayNo = "["+DateUtil.getYear()+"]长公交第"+(qpTTPCase.getCaseSerialNo()==null?"":qpTTPCase.getCaseSerialNo())+"号";
		}else{
		 quickPayNo = "["+DateUtil.getYear()+"]高长第"+(qpTTPCase.getCaseSerialNo()==null?"":qpTTPCase.getCaseSerialNo())+"号";
		}
		String accResult = (qpTTPCase.getCaseResult()==null?"":qpTTPCase.getCaseResult());
		String caseNotes = (qpTTPCase.getCaseNotes()== null?"":qpTTPCase.getCaseNotes());
		//责任
		StringBuffer dutyBuffer = new StringBuffer();
		dutyBuffer.append("  ");
		//事实
		StringBuffer factBuffer = new StringBuffer();
		factBuffer.append("  ");
		int size = qpTICAccidentList.size();
		//主要责任人
		String dutyName = "";
		String accidentPlaceTemp = accidentPlace;
		String clientNames = "";
		
		String policyName = (qpTTPCase.getPoliceName()==null?"":qpTTPCase.getPoliceName());
		String policyCode = (qpTTPCase.getPoliceEmployeeId()==null?"":qpTTPCase.getPoliceEmployeeId());
		if(StringUtils.isNotEmpty(policyCode)){
			UserKey userKey = userKeyService.findByUserCode(policyCode);
			//有证书的显示签名
			if(userKey!=null){
				policyName = "";
			}
		}
		List<ClientBean> clientBeans = new ArrayList<ClientBean>();  
		if(size>0){
		for(int i=1;i<=size;i++){
			QpTICAccident accident = qpTICAccidentList.get(i-1);
			 //驾驶员姓名
			String driverName = (accident.getDriverName()==null?"":accident.getDriverName());
		
			
			//车牌号
			String vehicleNumber = (accident.getDriverVehicleNumber() ==null?"":accident.getDriverVehicleNumber());
			//违反法规ID
			String driverLawId = accident.getDriverLawId();
			//行驶方向
			String driverDirection = accident.getDriverDirection();
			//准驾车型
			String permissionDrive = accident.getPermissionDrive();
			
			String driverDirectionDesc = "";
			if(StringUtils.isNotEmpty(driverDirection)){
				driverDirectionDesc =  qpTCommonService.getSelectCodeName("DirectionType",driverDirection);
				//加行驶方向，只加一次
				if(accidentPlace.equals((accidentPlaceTemp))){
					accidentPlaceTemp = accidentPlaceTemp+driverDirectionDesc;
				}
			}
			
			String driverLiability  = accident.getDriverLiability();
			String driverLiabilityText = "";
			// 责任信息处理
			if("10".equals(driverLiability)) {
				driverLiabilityText = "全部";
				if(StringUtils.isEmptyStr(dutyName)){
					dutyName = driverName;
				}
			}else if("11".equals(driverLiability))  {
				driverLiabilityText = "主要";
				if(StringUtils.isEmptyStr(dutyName)){
					dutyName = driverName;
				}
			}else if("12".equals(driverLiability))  {
				driverLiabilityText = "次要";
			}else if("13".equals(driverLiability))  {
				driverLiabilityText = "同等";
				if(StringUtils.isEmptyStr(dutyName)){
					dutyName = driverName;
				}
			}else if("14".equals(driverLiability))  {
				driverLiabilityText = "无";
			}

			//===========当事人 start=============
			ClientBean cb = new ClientBean();
			String jiancheng = "";
			switch (i) {
			case 1:jiancheng="甲:";break;
			case 2:jiancheng="乙:";break;
			case 3:jiancheng="丙:";break;
			case 4:jiancheng="丁:";break;
			case 5:jiancheng="戊:";break;
			default:
				break;
			}
			//性别和年龄通过身份证确定
			String curAge = "";
			String sex = "";
			String idNo = accident.getDriverIDNumber();
			String idType = accident.getDriverIDType();
			//证件类型中文名称
			String idTypeName = qpTCommonService.getSelectCodeName("IdentifyType",idType);
			try{
				//身份证类型才解析
				if("6".equals(idType)&&StringUtils.isNotEmpty(idNo)){
					int age = StringUtils.getAgeFromIdCard(idNo);
					curAge = age+"";
					String sexResult = StringUtils.getGenderFromIdCard(idNo);
					if("M".equals(sexResult)){
						sex = "男";
					}
					if("F".equals(sexResult)){
						sex = "女";
					}
				}
				if(StringUtils.isEmptyStr(sex)){
					String dSex = accident.getDriverSex();
					if(StringUtils.isNotEmpty(dSex)){
						sex = (dSex.equals("0")?"男":"女");
					}
				}
				if(StringUtils.isEmptyStr(curAge)){
					Date birthDay = accident.getBirthDay();
					Date now = new Date();
					if(birthDay!=null&&birthDay.before(now)){
						int age = DateUtil.getAge(birthDay);
						curAge = age+"";
					}

				}
			}catch(Exception e){
                e.printStackTrace();
                logger.error("", e);
			}
			//电话
			String mobile = "";
			if(StringUtils.isNotEmpty(accident.getDriverMobile())){
				mobile = accident.getDriverMobile();
			}else{
				mobile = (accident.getDriverPhone()==null?"":accident.getDriverPhone());
			}
			//现住址
			//String clientAddress = (accident.getDriverAddress()==null?"":accident.getDriverAddress());
			String clientAddress = (accident.getAcciStreet()==null?"":accident.getAcciStreet());
			//车辆类型
            String vehicleTypeName = "";
            if(StringUtils.isNotEmpty(accident.getDriverVehicleType())){
              vehicleTypeName = qpTCommonService.getSelectCodeName("VehicleType",accident.getDriverVehicleType());
            }
			//保险公司
            String insureName = "";
            String insureOther = "";
            if(StringUtils.isNotEmpty(accident.getCoId())){
            	QpTICCompanyId  companyId = new QpTICCompanyId();
            	companyId.setCoId(accident.getCoId());
            	QpTICCompany company = qpTICCompanyService.findQpTICCompanyByPK(companyId);
            	if(StringUtils.isNotEmpty(accident.getCompanyNameOther())){
            		insureOther =  "-"+accident.getCompanyNameOther();
            	}
            	insureName = company.getCoName()+insureOther;
            }
			//当事人
			StringBuffer clientBuffer = new StringBuffer();
			clientBuffer.append("  ");
			clientBuffer.append(jiancheng+driverName+",");
			clientBuffer.append(sex+","+"年龄"+curAge+"岁,");
			clientBuffer.append(idTypeName+"号："+idNo+",");
			clientBuffer.append("准驾车型："+permissionDrive+",");
			clientBuffer.append("电话："+mobile+",");
			clientBuffer.append("现住址："+clientAddress+",");
			clientBuffer.append("交通方式：驾驶"+vehicleNumber+"的"+vehicleTypeName+",");
			clientBuffer.append("保险情况："+insureName);
			if(i==size){
				clientBuffer.append("。");
				clientNames = clientNames+jiancheng+driverName;
			}else{
				clientBuffer.append(";");
				clientNames = clientNames+jiancheng+driverName+"        ";
			}
			cb.setName(clientBuffer.toString());
			clientBeans.add(cb);
			//===========当事人 end=============

			//===========责任 start=============
			dutyBuffer.append(driverName);
			if(StringUtils.isNotEmpty(driverLawId)){
				QpTTPLawId qpTTPLawId = new QpTTPLawId();
				qpTTPLawId.setLawId(driverLawId);
				QpTTPLaw qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(qpTTPLawId);
				if(qpTTPLaw!=null){
					dutyBuffer.append("违反了"+qpTTPLaw.getLawName()+"("+qpTTPLaw.getLawWords()+"),");
				}
			}
			dutyBuffer.append("在本次事故中承担"+driverLiabilityText+"责任");
			if(i==size){
				dutyBuffer.append("。");
			}else{
				dutyBuffer.append(";\n  ");
			}
			//===========责任 end=============
			//===========事实 start=============
			factBuffer.append(driverName+"驾驶牌照为"+vehicleNumber+"的");
			factBuffer.append(vehicleTypeName+"沿"+accidentPlace+"路段行驶,");
			if(i==size){
				if(StringUtils.isEmptyStr(dutyName)){
					dutyName = driverName;
				}
				factBuffer.append("因"+dutyName+"驾驶车辆"+caseNotes);
				//factBuffer.append"追尾"+"致使"+cSize+"车在上述地点发生了"+追尾+",造成");
				//factBuffer.append(clientAllNo.toString()+cSize+"车受损的交通事故。");
			}
			//===========事实 end=============
		}
		}
		String accDuty = dutyBuffer.toString();
		String accFact = factBuffer.toString();
		String date = DateUtil.dateFormat(new Date(), DateUtil.SIMPLE_DATE_HAO_STR);

		Collection<ClientReport> clientReports = new Vector<ClientReport>();  
		ClientReport cr = new ClientReport();
		cr.setAccDuty(accDuty);
		cr.setAccFact(accFact);
		cr.setAccidentPlace(accidentPlaceTemp);
		cr.setAccidentTime(accidentTime);
		cr.setAccResult(accResult);
		cr.setDate(date);
		cr.setQuickPayNo(quickPayNo);
		cr.setWeather(weatherName);
		cr.setClientNames(clientNames);
		cr.setPolicyName(policyName);
		//远程定责中心加盖公章  注释，加盖公章由电子公章执行
//		if("26".equals(qpTTPCase.getCenterId())){
//			cr.setImages(this.getClass().getClassLoader().getResourceAsStream("images/wxjjgz.jpg"));
//		}
		JRBeanCollectionDataSource accClientList = new JRBeanCollectionDataSource(clientBeans);       
		
		cr.setAccClientList(accClientList);  
		clientReports.add(cr); 
		JRDataSource dataSource = new JRBeanCollectionDataSource(clientReports);
		return dataSource;
	}
	
	/**
	 * 查询微信当前用户的历史案件<br>
	 * @return
	 */
	public List<QpTTPCase> findCaseByWxUser(String userCode) throws Exception {
		List<QpTTPCase> result = new ArrayList<QpTTPCase>();
		// 根据openId 查询user
		UmTUser user = iUmTUserService.findUmTUserByUserCode(userCode);
		if (user != null) {
			List<QpTICAccident> accidents = qpTICAccidentService.findAccidentByUserCode(user);
			QpTTPCase qpTTPCase = new QpTTPCase();
			for (QpTICAccident accident : accidents) {
				qpTTPCase.setCaseId(accident.getCaseId());
				QpTTPCase resultQpTTPCase = this.findQpTTPCaseById(qpTTPCase);
				if (qpTTPCase != null) {
					result.add(resultQpTTPCase);
				}
			}
		}
		return result;
	}

	@Override
	public List<QpTTPCase> findCaseByOpenID(String openId) throws Exception {
		List<QpTTPCase> result = new ArrayList<QpTTPCase>();
		List<QpTICAccident> accidents = qpTICAccidentService.findAccidentListByOpenID(openId);
		QpTTPCase qpTTPCase = new QpTTPCase();
		for (QpTICAccident accident : accidents) {
			qpTTPCase.setCaseId(accident.getCaseId());
			QpTTPCase resultQpTTPCase = this.findEasyCaseById(qpTTPCase);
			if (qpTTPCase != null) {
				result.add(resultQpTTPCase);
			}
		}
		// 事故时间倒序排序
		Collections.sort(result, new Comparator<QpTTPCase>() {
			@Override
			public int compare(QpTTPCase case1, QpTTPCase case2) {
				return case2.getCaseTime().compareTo(case1.getCaseTime());
			}
		});
		return result;
	}
	

	/**
	 * 从session中获取用户信息，查询用户角色
	 * @return 
	 * @throws Exception 
	 */
	public boolean isManager(String userCode) throws Exception {
		
		String role = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select r.rolecname from um_t_user u join um_t_userrole u_r on u.usercode=u_r.usercode")
			.append(" join um_t_role r on u_r.roleid=r.roleid where u.usercode= ?");
		List<String> resultList = sysCommonDao.findBySql(sql.toString(), new Object[]{userCode});
		
		for (String str : resultList) {
			if(Constants.SYS_MANAGER.equals(str)) role =str;
		}
		if(role!=null){
			return true;
		}else{
			return false;
		}
		 
	}
}
