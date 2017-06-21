/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICAccidentDaoHibernate;
import com.picc.qp.dao.QpTTPCaseDaoHibernate;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.util.SmsUtil;
import com.picc.um.schema.model.UmTUser;


@Service("qpTICAccidentService")
public class QpTICAccidentServiceSpringImpl implements IQpTICAccidentService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTICAccidentDaoHibernate qpTICAccidentDao;
	
	@Autowired
    private QpTTPCaseDaoHibernate qpTTPCaseDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	@Autowired
    private CommonDaoHibernate commonDao;
	
	@Autowired
	private IQpTCommonService qpTCommonService;
	
	/**
	 * 根据主键对象QpTICAccidentId获取QpTICAccident信息
	 * @param QpTICAccidentId
	 * @return QpTICAccident
	 */
	@Override
    public QpTICAccident findQpTICAccidentByPK(QpTICAccidentId id) throws Exception{
			return qpTICAccidentDao.get(QpTICAccident.class,id);
	}

	/**
	 * 根据qpTICAccident和页码信息，获取Page对象
	 * @param qpTICAccident
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICAccident的Page对象
	 */
	@Override
    public Page findByQpTICAccident(QpTICAccident qpTICAccident, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTICAccident生成queryRule
        if(ToolsUtils.notEmpty(qpTICAccident.getCaseId())) {
            queryRule.addEqual("caseId", qpTICAccident.getCaseId());
        }
		
		return qpTICAccidentDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTICAccident信息
	 * @param QpTICAccident
	 */
	@Override
    public void updateQpTICAccident(QpTICAccident qpTICAccident) throws Exception{
			qpTICAccidentDao.update(qpTICAccident);
	}

	/**
	 * 保存QpTICAccident信息
	 * @param QpTICAccident
	 */
	@Override
    public Map<String,String> saveQpTICAccident(QpTTPCase qpTTPCase, QpTICAccident qpTICAccident) throws Exception{
	    Map<String,String> resultMap = new HashMap<String, String>();
	    
	    String caseId = qpTTPCase.getCaseId();                          // 案件ID
	    String acciId = qpTICAccident.getAcciId();
	   // String caseSerialNo = qpTTPCase.getCaseSerialNo();
	    
	    // 当'案件流水号'为空时，直接新增
	    // 否则......
//	    if(ToolsUtils.isEmpty(caseId)) {
//	        // 1、保存案件信息
//	        caseId = sysCommonDao.generateID("C", "QP_SEQ_TP_CASE", 10);  // 生成案件流水号
//	       // caseSerialNo = sysCommonDao.generateID(ToolsUtils.getCurrentDate("yyyy"), "QP_SEQ_TP_CASE_NO", 8); // 生成认字编号
//	       // caseSerialNo = caseSerialNo + "A";
//            QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
//            qpTTPCaseId.setCaseId(caseId);                              // 案件流水号
//            qpTTPCase.setId(qpTTPCaseId);                               
//           // qpTTPCase.setCaseSerialNo(caseSerialNo);                    // 认字编号
//            qpTTPCase.setTpHandleStatus("0");                           // 暂定
//            qpTTPCase.setCreatorCode(qpTTPCase.getTpLoginId());         // 创建人
//            qpTTPCase.setInsertTimeForHis(new Date());                  // 创建时间
//            qpTTPCase.setOperateTimeForHis(new Date());                 // 更新时间
//            qpTTPCase.setValidStatus("1");                              // 有效状态
//            qpTTPCaseDao.save(qpTTPCase);
//            
//	        // 2、保存当事人
//            acciId = sysCommonDao.generateID("A", "QP_SEQ_TP_ACCIDENT", 16); // 生成当事人流水号
//            QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
//            qpTICAccidentId.setAcciId(acciId);
//            qpTICAccident.setId(qpTICAccidentId);                       // 当事人流水号
//            qpTICAccident.setCaseId(caseId);                            // 案件ID
//            qpTICAccident.setCreatorCode(qpTTPCase.getTpLoginId());     // 创建人
//            qpTICAccident.setInsertTimeForHis(new Date());              // 创建时间
//            qpTICAccident.setOperateTimeForHis(new Date());             // 更新时间
//            qpTICAccident.setValidStatus("1");                          // 有效状态
//            qpTICAccidentDao.save(qpTICAccident);
//	        
//	    }else {
	        if(ToolsUtils.isEmpty(acciId)) {
	            acciId = sysCommonDao.generateID("A", "QP_SEQ_TP_ACCIDENT", 16); // 当事人流水号
	            QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
	            qpTICAccidentId.setAcciId(acciId);                      // 当事人流水号
	            qpTICAccident.setId(qpTICAccidentId);
	            qpTICAccident.setCaseId(caseId);                        // 案件流水号
	            qpTICAccident.setCreatorCode(qpTTPCase.getTpLoginId()); // 创建人
	            qpTICAccident.setInsertTimeForHis(new Date());          // 创建时间
	            qpTICAccident.setOperateTimeForHis(new Date());         // 更新时间
	            qpTICAccident.setValidStatus("1");                      // 有效状态
	            qpTICAccidentDao.save(qpTICAccident);
	        }else {
	            QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
                qpTICAccidentId.setAcciId(acciId);                      // 当事人流水号
	            qpTICAccident.setId(qpTICAccidentId);
	            qpTICAccident.setCaseId(caseId);                        // 案件流水号
	            qpTICAccident.setOperateTimeForHis(new Date());         // 更新时间
                qpTICAccident.setValidStatus("1");                      // 有效状态
	            qpTICAccidentDao.update(qpTICAccident);
	        }
//	    }
	 //   resultMap.put("caseId", caseId);             // 案件流水号
	   // resultMap.put("caseSerialNo", caseSerialNo); // 认字编号
	    resultMap.put("acciId", acciId);             // 当事人流水号
		return resultMap;
	}

	/**
	 * 根据主键对象，删除QpTICAccident信息
	 * @param QpTICAccidentId
	 */
	@Override
    public void deleteByPK(QpTICAccidentId id) throws Exception{
	        // 删除关联图片信息
	        String sql = "delete from qp_t_ic_picture where ACCIID = '" + id.getAcciId() + "'";
            commonDao.execute(sql, null);
	        
	        // 删除当事人信息
			qpTICAccidentDao.deleteByPK(QpTICAccident.class,id);
	}
	
	/**
     * 打印 当事人列表
     * 
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public List<QpTICAccident> findQpTICAccidentInfo(QpTICAccident qpTICAccident) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("        SELECT g.acciid acciId,                                         ");
        sql.append("        g.drivername driverName,                                        ");
        sql.append("        g.driversex driverSex,                                          ");
        sql.append("        IF(g. driversex = '0', '男', '女') driverSexDesc,               ");
        sql.append("        g.driveridnumber driverIDNumber,                                ");
        sql.append("        g.driverphone driverPhone,                                      ");
        sql.append("        g.drivermobile driverMobile,                                    ");
        sql.append("        g.coid coId,                                                    ");
        sql.append("        (SELECT a6.coname                                               ");
        sql.append("           FROM qp_t_ic_company a6                                      ");
        sql.append("          WHERE a6.coid = g. coid) coName,                              ");
        sql.append("        g.drivervehiclenumber driverVehicleNumber,                      ");
        sql.append("        g.driverdirection driverDirection,                              ");
        sql.append("        (SELECT a5.codecname                                            ");
        sql.append("           FROM mc_d_newcode a5                                         ");
        sql.append("          WHERE a5.codetype = 'DirectionType'                           ");
        sql.append("            AND a5.codecode = g.driverdirection) driverDirectionDesc,   ");
        sql.append("        (SELECT a4.codecname                                            ");
        sql.append("           FROM mc_d_newcode a4                                         ");
        sql.append("          WHERE a4.codetype = 'VehicleType'                             ");
        sql.append("            AND a4.codecode = g.driverVehicleType) driverVehicleTypeDesc,   ");
        sql.append("        g.driverlawid driverLawId,                                      ");
        sql.append("        (SELECT a7.lawwords                                             ");
        sql.append("           FROM qp_t_tp_law a7                                          ");
        sql.append("          WHERE a7.lawid = g. driverlawid) driverLawDesc,               ");
        sql.append("        g.driverliability driverLiability,                              ");
        sql.append("        (SELECT a8.codecname                                            ");
        sql.append("           FROM mc_d_newcode a8                                         ");
        sql.append("          WHERE a8.codetype = 'ResponsibilityType'                      ");
        sql.append("            AND a8.codecode = g.driverliability) driverLiabilityDesc,   ");
        sql.append("        CONCAT(                               ");
//        sql.append("        	   IFNULL((SELECT a.provname                                ");
//        sql.append("                        FROM qp_t_com_province a                        ");
//        sql.append("                       WHERE a.provid = g.acciprovince),                ");
//        sql.append("                      ''),                                              ");
//        sql.append("               IFNULL((SELECT a1.cityname                               ");
//        sql.append("                        FROM qp_t_com_city a1                           ");
//        sql.append("                       WHERE a1.cityid = g.accicity),                   ");
//        sql.append("                      ''),                                              ");
//        sql.append("               IFNULL((SELECT a2.districtname                           ");
//        sql.append("                        FROM qp_t_com_district a2                       ");
//        sql.append("                       WHERE a2.districtid = g.accidistrict),           ");
//        sql.append("                      ''),                                              ");
//        sql.append("               IFNULL((SELECT a3.roadname                               ");
//        sql.append("                        FROM qp_t_com_road a3                           ");
//        sql.append("                       WHERE a3.roadid = g.acciroad),                   ");
//        sql.append("                      ''),                                               ");
        sql.append("               IFNULL(g.accistreet, '')                                ");
        sql.append("               ) driverAddress,                 ");
        
//        sql.append("        CONCAT(IFNULL((SELECT croadname                               ");
//        sql.append("                        FROM qp_t_com_road cr                           ");
//        sql.append("                       WHERE cr.roadid = g.driverRoad),                 ");
//        sql.append("                      ''),                                              ");
//        sql.append("               IFNULL(g.driverStreet, '')) driverStreet,                ");
//        sql.append("        IFNULL((SELECT cr.roadname                               ");
//        sql.append("                        FROM qp_t_com_road cr                           ");
//        sql.append("                       WHERE cr.roadid = g.driverRoad),                 ");
//        sql.append("                      '') driverStreet,                                  ");
        
        sql.append("        g.caseid caseId,                                                ");
        sql.append("        g.labelType labelType,                                                ");
        sql.append("        g.insured insured,                                                ");
        sql.append("        g.companyNameOther companyNameOther,                                                ");
        sql.append("        g.chassisNumber chassisNumber                                                ");
        sql.append("   FROM qp_t_ic_accident g                                              ");
        sql.append("  WHERE g.caseid = ?                                                    ");
        sql.append("  order by g.acciid                                                     ");
        
        List<QpTICAccident> qpTICAccidentList = (List<QpTICAccident>) commonDao.findListBySql(sql.toString(), QpTICAccident.class, qpTICAccident.getCaseId());
        return qpTICAccidentList;
    }
    
    /**
     * 查询当事人列表(精简)
     * 
     * @param caseId
     * @return
     * @throws Exception
     */
    public List<QpTICAccident> findQpTICAccidentInfoOnly(String caseId) throws Exception {
//        StringBuffer sql = new StringBuffer();
//        sql.append("SELECT * FROM qp_t_ic_accident g WHERE g.caseid = ? order by g.acciid ");
//        List<QpTICAccident> qpTICAccidentList = (List<QpTICAccident>) commonDao.findListBySql(sql.toString(), QpTICAccident.class, caseId);
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        queryRule.addEqual("caseId", caseId);
        queryRule.addAscOrder("id.acciId");
        List<QpTICAccident> qpTICAccidentList =  qpTICAccidentDao.find(queryRule);
        return qpTICAccidentList;
    }
    
    /**
     * 当事人列表查询
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findQpTICAccidentPageBySql(QpTICAccident qpTICAccident, int pageNo, int pageSize) throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append("        SELECT g.acciid acciid,                                                ");
        sql.append("        g.drivername drivername,                                               ");
        sql.append("        g.driversex driversex,                                                 ");    
        sql.append("        g.estimateLossSum estimateLossSum,                                     ");
        sql.append("        g.fixedLossPrice fixedLossPrice,                                       ");
        sql.append("        g.surveyGroupId surveyGroupId,                                       ");
        sql.append("        g.surveyNotes surveyNotes,                                             ");
        sql.append("        IF(g. driversex = '0', '男', '女') driversexdesc,                      ");
        sql.append("        g.driveridnumber driveridnumber,                                       ");
        sql.append("        g.driverphone driverphone,                                             ");
        sql.append("        g.drivermobile drivermobile,                                           ");
        sql.append("        g.coid coid,                                                           ");
      
        sql.append("        (SELECT count(pic.groupid)                                                      ");
        sql.append("           FROM qp_t_ic_picture pic                                             ");
        sql.append("          WHERE pic.groupid = g.surveyGroupId and pic.groupid<>'') picCount,                                     ");
        
        sql.append("        (SELECT a6.coname                                                      ");
        sql.append("           FROM qp_t_ic_company a6                                             ");
        sql.append("          WHERE a6.coid = g. coid) coname,                                     ");
        sql.append("        g.drivervehiclenumber drivervehiclenumber,                             ");
        sql.append("        g.driverdirection driverdirection,                                     ");
        sql.append("        (SELECT a5.codecname                                                   ");
        sql.append("           FROM mc_d_newcode a5                                                ");
        sql.append("          WHERE a5.codetype = 'DirectionType'                                  ");
        sql.append("            AND a5.codecode = g.driverdirection) driverdirectiondesc,          ");
        sql.append("        g.driverlawid driverlawid,                                             ");
        sql.append("        (SELECT a7.lawwords                                                    ");
        sql.append("           FROM qp_t_tp_law a7                                                 ");
        sql.append("          WHERE a7.lawid = g. driverlawid) driverlawdesc,                      ");
        sql.append("        g.driverliability driverliability,                                     ");
        sql.append("        (SELECT a8.codecname                                                   ");
        sql.append("           FROM mc_d_newcode a8                                                ");
        sql.append("          WHERE a8.codetype = 'ResponsibilityType'                             ");
        sql.append("            AND a8.codecode = g.driverliability) driverliabilitydesc,          ");
        sql.append("        g. drivervehicletype,                                                  ");
        sql.append("        (SELECT a9.codecname                                                   ");
        sql.append("           FROM mc_d_newcode a9                                                ");
        sql.append("          WHERE a9.codetype = 'VehicleType'                                    ");
        sql.append("            AND a9.codecode = g. drivervehicletype) driverVehicleTypeDesc,     ");
        sql.append("        concat(							                                       ");
//        sql.append("          	   ifnull((SELECT a.provname                                       ");
//        sql.append("                        FROM qp_t_com_province a                               ");
//        sql.append("                       WHERE a.provid = g.acciprovince),                       ");
//        sql.append("                      ''),                                                     ");
//        sql.append("               ifnull((SELECT a1.cityname                                      ");
//        sql.append("                        FROM qp_t_com_city a1                                  ");
//        sql.append("                       WHERE a1.cityid = g.accicity),                          ");
//        sql.append("                      ''),                                                     ");
//        sql.append("               ifnull((SELECT a2.districtname                                  ");
//        sql.append("                        FROM qp_t_com_district a2                              ");
//        sql.append("                       WHERE a2.districtid = g.accidistrict),                  ");
//        sql.append("                      ''),                                                     ");
//        sql.append("               ifnull((SELECT a3.roadname                                      ");
//        sql.append("                        FROM qp_t_com_road a3                                  ");
//        sql.append("                       WHERE a3.roadid = g.acciroad),                          ");
//        sql.append("                      ''),                                                     ");
        sql.append("               ifnull(g.accistreet, '')) driveraddress,                        ");
        sql.append("(select r.reportCaseStatus from report_case_data r where r.caseId=g.caseid and r.accId=g.acciid)  reportCaseStatus,");
        sql.append("(select r.reportNo from report_case_data r where r.caseId=g.caseid and r.accId=g.acciid order by r.reportNo desc limit 0,1)  reportNo,");
        sql.append("        g.caseid caseid,                                                        ");
        sql.append("        g.groupId groupId,                                                        ");
        sql.append("        g.permissionDrive permissionDrive,                                                        ");
        sql.append("        g.labelType labelType,                                                        ");
        sql.append("        g.insured insured,                                                        ");
        sql.append("        g.chassisNumber chassisNumber                                                ");
        sql.append("   FROM qp_t_ic_accident g                                                     ");
        sql.append("  WHERE g.caseId = '").append(qpTICAccident.getCaseId()).append("'");

        return  sysCommonDao.findBySql(QpTICAccident.class,sql.toString(),  pageNo, pageSize, null);
    }
    
    /**
     * 个人台账查询
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findQpTICAccidentPageBySql(QpTTPCaseStatVO qpTTPCaseStatVO, int pageNo, int pageSize) throws Exception {
        StringBuffer sql = new StringBuffer();
        
        sql.append("        SELECT g.acciid acciid,                                                ");
        sql.append("        tpc.caseTime casetime,                                                 ");
        sql.append("        tpc.caseSerialNo caseserialno,                                         ");
        sql.append("        concat(							                                       ");
        
        sql.append("        	   ifnull((SELECT a.provname                                       ");
        sql.append("                        FROM qp_t_com_province a                               ");
        sql.append("                       WHERE a.provid = tpc.caseprovince),                     ");
        sql.append("                      ''),                                                     ");
        sql.append("               ifnull((SELECT a1.cityname                                      ");
        sql.append("                        FROM qp_t_com_city a1                                  ");
        sql.append("                       WHERE a1.cityid = tpc.casecity),                        ");
        sql.append("                      ''),                                                     ");
        sql.append("               ifnull((SELECT a2.districtname                                  ");
        sql.append("                        FROM qp_t_com_district a2                              ");
        sql.append("                       WHERE a2.districtid = tpc.casedistrict),                ");
        sql.append("                      ''),                                                     ");
        sql.append("               ifnull((SELECT a3.roadname                                      ");
        sql.append("                        FROM qp_t_com_road a3                                  ");
        sql.append("                       WHERE a3.roadid = tpc.caseroad),                        ");
        sql.append("                      ''),                                                     ");
        sql.append("               ifnull(tpc.casestreet, '')) caseAddress,                        ");
        
        sql.append("        g.drivervehiclenumber drivervehiclenumber,                             ");
        sql.append("        (SELECT a1.codecname                                                   ");
        sql.append("           FROM mc_d_newcode a1                                                ");
        sql.append("          WHERE a1.codetype = 'ResponsibilityType'                             ");
        sql.append("            AND a1.codecode = g.driverliability) driverliabilitydesc,          ");
        sql.append("        g.drivername drivername,                                               ");
        sql.append("        ifnull(g.drivermobile, g.driverphone) drivermobile,                    ");
        sql.append("        (SELECT co.coname                                                      ");
        sql.append("           FROM qp_t_ic_company co                                             ");
        sql.append("          WHERE co.coid = g.coid) coName,                                      ");
        sql.append("        CAST(g.estimateLossSum AS CHAR(20)) estimateLossSumDesc,               ");
        sql.append("        CAST(g.fixedLossPrice AS CHAR(20)) fixedLossPriceDesc,                 ");
        sql.append("        tpc.tpHandleTime tpHandleTime,                                         ");
        sql.append("        (SELECT usr.username                                                   ");
        sql.append("           FROM um_t_user usr                                                  ");
        sql.append("          WHERE usr.usercode = g.lossAssessorCode) lossAssessorCode,            ");
        sql.append("        g.labelType labelType,                                                ");
        sql.append("        g.insured insured,                                                ");
        sql.append("        g.chassisNumber chassisNumber                                                ");
        sql.append("   FROM qp_t_ic_accident g, qp_t_tp_case tpc                                   ");
        sql.append("  WHERE g.caseId = tpc.caseId AND g.validstatus = '1'                          ");
        // 自动台账只能查看自己的数据
        if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getLossAssessorCode())) {
        	sql.append("  AND  g.lossAssessorCode = '").append(qpTTPCaseStatVO.getLossAssessorCode()).append("'");
        }
        // 拼接条件参数
        if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getEstimateLossTimeStart())) {
        	sql.append("  AND  g.estimateLossTime >= '").append(qpTTPCaseStatVO.getEstimateLossTimeStart()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getEstimateLossTimeEnd())) {
        	sql.append("  AND  g.estimateLossTime <= '").append(qpTTPCaseStatVO.getEstimateLossTimeEnd()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getCenterId()) && !"0".equals(qpTTPCaseStatVO.getCenterId())) {
        	sql.append("  AND tpc.centerid = '").append(qpTTPCaseStatVO.getCenterId()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPCaseStatVO.getCoId()) && !"0".equals(qpTTPCaseStatVO.getCoId())) {
        	sql.append("  AND g.coid = '").append(qpTTPCaseStatVO.getCoId()).append("'");
        }
        sql.append(" ORDER BY tpc.caseTime, g.driverLiability ASC    ");
        
        return  sysCommonDao.findBySql(QpTICAccident.class, sql.toString(),  pageNo, pageSize, null);
    }
    
    /**
     * 查询历史当事人信息
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public List<QpTICAccident> queryAcciDriverInfo(QpTICAccident qpTICAccident) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        if(!(qpTICAccident.getDriverIDType()==null)&&!(qpTICAccident.getDriverIDNumber()==null)){
        	queryRule.addEqual("driverIDType", qpTICAccident.getDriverIDType());
        	queryRule.addEqual("driverIDNumber", qpTICAccident.getDriverIDNumber());
        }else if(!(qpTICAccident.getDriverVehicleNumber()==null)){
        	queryRule.addEqual("driverVehicleNumber", qpTICAccident.getDriverVehicleNumber());
        }
        queryRule.addEqual("validStatus", "1");
        queryRule.addDescOrder("insertTimeForHis");
        return qpTICAccidentDao.find(queryRule);
    }
    
    public Integer queryAcciDriverInfoCount(String number) throws Exception {
    	StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT count(1) FROM qp_t_ic_accident ");
    	sql.append(" WHERE DRIVERIDTYPE = ? ");
    	sql.append(" AND DRIVERIDNUMBER = ? ");
    	sql.append(" AND INSERTTIMEFORHIS > DATE_SUB(SYSDATE(), INTERVAL 2 DAY) ");
    	List<Object> params = new ArrayList<Object>();
    	params.add("6");
    	params.add(number);
    	return sysCommonDao.getTotalNumber(sql.toString(), params.toArray());
    }
    
    /**
     * 查询当事人列表
     */
    public List<QpTICAccident> findAccidentByUserCode(UmTUser user) throws Exception {
    	// 逻辑待修改,从微信用户-案件当事人表关联查询出当事人列表
		QueryRule queryRule = QueryRule.getInstance();
		if (StringUtils.isNotEmpty(user.getIdentityNumber())) {
			queryRule.addEqual("driverIDNumber", user.getIdentityNumber());
		}
//		if (StringUtils.isNotEmpty(user.getLicenseNo())) {
//			queryRule.addEqual("driverVehicleNumber", user.getLicenseNo());
//		}
//		if (StringUtils.isNotEmpty(user.getMobile())) {
//			queryRule.addEqual("driverMobile", user.getMobile());
//		}
		queryRule.addDescOrder("insertTimeForHis");
		return qpTICAccidentDao.find(queryRule);
    }

	@Override
	public boolean sendSmsToClient(List<QpTICAccident> qpTICAccidents) {
		// TODO Auto-generated method stub  110902
		boolean flag = false;
		try{
			if(qpTICAccidents!=null&&qpTICAccidents.size()>0){
				int count = 0;
				String smsModelNo = "101109";
				String serverCode = "DXW";
				String privateKey = "YMT-DXW-SMS";
				for(QpTICAccident qpTICAccident:qpTICAccidents){
					String phone = qpTICAccident.getDriverMobile();
					if(StringUtils.isNotEmpty(phone)){
						String caseId = qpTICAccident.getCaseId();
						String driverLiability = qpTICAccident.getDriverLiability();
						String driverDirectionDesc = "";
						if(StringUtils.isNotEmpty(driverLiability)){
							driverDirectionDesc =  qpTCommonService.getSelectCodeName("ResponsibilityType",driverLiability);
						}
						String data = caseId+",您的责任为："+driverDirectionDesc;
						String DXWSMSResult = SmsUtil.sendSMS(phone, data, smsModelNo, serverCode, privateKey);
						logger.info(caseId + "大湘网短信定责短信发送：" + DXWSMSResult);
						//解析发送结果再统计，这里暂时按发送了就统计的方式
						count++;	
					}
				}
				if(count==qpTICAccidents.size()){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
//	@Override
	public boolean sendSmsToClient2(List<QpTICAccident> qpTICAccidents, JSONObject jsonObject, String serverCode) {
		// TODO Auto-generated method stub  110902
	    	//【长沙公安交警】您好，您的事故已完成定责，事故编号为{1},您的责任为:{2},对方责任为:{3};为您推荐的定损点为:{4},地址:{5},电话:{6}。感谢您使用长沙公安交警微信快处快赔。
		boolean flag = false;
		try{
		    	String smsModelNo = "110902";
			String privateKey = "YMT-DXW-SMS";
			if(qpTICAccidents!=null&&qpTICAccidents.size()>0){
				int count = 0;
				for(QpTICAccident qpTICAccident:qpTICAccidents){
					String phone = qpTICAccident.getDriverMobile();
					if(StringUtils.isNotEmpty(phone)){
						String caseId = qpTICAccident.getCaseId();
						String driverLiability = qpTICAccident.getDriverLiability();
						String driverDirectionDesc = "";
						String youdriverDirectionDesc = "";
						if(StringUtils.isNotEmpty(driverLiability)){
							driverDirectionDesc =  qpTCommonService.getSelectCodeName("ResponsibilityType",driverLiability);
						}
						//对方责任
						for (QpTICAccident qpTICAccident2 : qpTICAccidents) {
						    if(!qpTICAccident.getAcciId().equals(qpTICAccident2.getAcciId())){
							if(StringUtils.isNotEmpty(qpTICAccident2.getDriverLiability())){
							    youdriverDirectionDesc =  qpTCommonService.getSelectCodeName("ResponsibilityType",qpTICAccident2.getDriverLiability());
							}
						    }
						}
						String data = caseId+"||"+driverDirectionDesc+"||"+youdriverDirectionDesc;
						data += "||" + jsonObject.getString("companyGarageName");
						data += "||" + jsonObject.getString("address");
						if(StringUtils.isNotEmpty(jsonObject.getString("servicePhone"))){
						    data += "||" + jsonObject.getString("servicePhone");
						}
						if(StringUtils.isEmptyStr(jsonObject.getString("servicePhone"))){
						    data += "||" + jsonObject.getString("landlinePhone");
						}
						logger.info("手机号:" + phone + ",发送的内容:" + data);
						String DXWSMSResult = SmsUtil.sendSMS(phone, data, smsModelNo, serverCode, privateKey);
						logger.info(caseId + "大湘网短信定责短信发送：" + DXWSMSResult);
						//解析发送结果再统计，这里暂时按发送了就统计的方式
						if("200".equals(JSONObject.fromObject(DXWSMSResult).getString("code"))){
							count++;
						}
					}
				}
				if(count==qpTICAccidents.size()){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QpTICAccident> findAccidentListByOpenID(String openID) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from qp_t_ic_accident where 1 = 1 ");
		List<Object> params = new ArrayList<Object>(); 
		if (StringUtils.isNotEmpty(openID)) {
			sql.append(" and openID = ?");
			params.add(openID);
		}
		return sysCommonDao.findBySql(QpTICAccident.class, sql.toString(),  1, Integer.MAX_VALUE, params.toArray()).getResult();
	}
    
}
