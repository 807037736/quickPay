/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTTPFastCenterDaoHibernate;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.QpTTPFastCentercompare;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;


@Service("qpTTPFastCenterService")
public class QpTTPFastCenterServiceSpringImpl implements IQpTTPFastCenterService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPFastCenterDaoHibernate qpTTPFastCenterDao;

	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	/**
	 * 根据主键对象QpTTPFastCenterId获取QpTTPFastCenter信息
	 * @param QpTTPFastCenterId
	 * @return QpTTPFastCenter
	 */
	public QpTTPFastCenter findQpTTPFastCenterByPK(QpTTPFastCenterId id) throws Exception{
			return qpTTPFastCenterDao.get(QpTTPFastCenter.class,id);
	}

	/**
	 * 根据qpTTPFastCenter和页码信息，获取Page对象
	 * @param qpTTPFastCenter
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPFastCenter的Page对象
	 */
	public Page findByQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter, int pageNo, int pageSize) throws Exception{
		StringBuffer sql = new StringBuffer();
       // sql.append("       SELECT  t1.centerId centerId,                                             ");
        sql.append("       SELECT  t1.*,                                             ");
        sql.append("      (SELECT  cityName FROM  qp_t_com_city t2 WHERE t1.cityId = t2.cityId) cityName  ");
        sql.append("      FROM qp_t_tp_fast_center t1 where 1=1                                           ");
	    
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterName())){
			//sql.append(" and t1.centerName like '%" + qpTTPFastCenter.getCenterName() + "%'");
			sql.append(" AND t1.centerName like '").append("%"+qpTTPFastCenter.getCenterName()+"%").append("' ");
		}
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterPhone())){
			sql.append(" AND t1.centerPhone like '").append("%"+qpTTPFastCenter.getCenterPhone()+"%").append("' ");
		}
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterNotes())){
			sql.append(" AND t1.centerNotes like '").append("%"+qpTTPFastCenter.getCenterNotes()+"%").append("' ");
		}
	
		if(qpTTPFastCenter.getCenterNumber()!=null && !"".equals(qpTTPFastCenter.getCenterNumber())){
			sql.append(" AND t1.centerNumber = '").append(qpTTPFastCenter.getCenterNumber()).append("' ");
		}
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getValidStatus())){
			sql.append(" AND t1.validStatus = '").append(qpTTPFastCenter.getCenterNotes()).append("' ");
		}
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCityId())){
			sql.append(" AND t1.cityId = '").append(qpTTPFastCenter.getCityId()).append("' ");
		}
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCities())){
			sql.append(" AND t1.cities = '").append(qpTTPFastCenter.getCities()).append("' ");
		}
		sql.append(" order by t1.centerNumber         ");

		return  sysCommonDao.findBySql(QpTTPFastCenter.class,sql.toString(),  pageNo, pageSize, null);
        
		/*QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTTPFastCenter生成queryRule
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterName())){
			queryRule.addLike("centerName", "%"+qpTTPFastCenter.getCenterName()+"%");
		}
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterPhone())){
			queryRule.addLike("centerPhone", "%"+qpTTPFastCenter.getCenterPhone()+"%");
		}
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterNotes())){
			queryRule.addLike("centerNotes", "%"+qpTTPFastCenter.getCenterNotes()+"%");
		}
		
		
//		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCenterNumber().doubleValue())){
//			queryRule.addEqual("centerNumber", qpTTPFastCenter.getCenterNumber());
//		}
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getValidStatus())){
			queryRule.addEqual("validStatus", qpTTPFastCenter.getValidStatus());
		}
		
		if(ToolsUtils.notEmpty(qpTTPFastCenter.getCityId())){
			queryRule.addEqual("cityId", qpTTPFastCenter.getCityId());
		}
		queryRule.addAscOrder("centerNumber");
		return qpTTPFastCenterDao.find(queryRule, pageNo, pageSize);*/
	}
	
	/**
     * 根据qpTTPFastCenter获取列表
     * @param qpTTPFastCenter
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPFastCenter的Page对象
     */
    public List<QpTTPFastCenter> findByQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        SessionUser sessionUser = (SessionUser)ServletActionContext.getRequest().getSession().getAttribute("SessionUser");
        String cityId = sessionUser.getCity();
        if(ToolsUtils.notEmpty(cityId)){
        	queryRule.addEqual("cityId", cityId);
        }
        if(ToolsUtils.notEmpty(qpTTPFastCenter.getValidStatus())){
    	queryRule.addEqual("validStatus", qpTTPFastCenter.getValidStatus());
    }
        queryRule.addAscOrder("centerNumber");
        return qpTTPFastCenterDao.find(queryRule);
    }

	/**
	 * 更新QpTTPFastCenter信息
	 * @param QpTTPFastCenter
	 */
	public void updateQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception{
		qpTTPFastCenterDao.update(qpTTPFastCenter);
	}

	/**
	 * 保存QpTTPFastCenter信息
	 * @param QpTTPFastCenter
	 */
	
//	public void saveQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception{
//			qpTTPFastCenterDao.save(qpTTPFastCenter);
//	}
	
	public void saveQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception{
		String CenterId = sysCommonDao.generateID("TPFC", "QP_SEQ_TP_FASE_CENTER", 16);
		QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
		qpTTPFastCenterId.setCenterId(CenterId);
		qpTTPFastCenter.setId(qpTTPFastCenterId);
		qpTTPFastCenterDao.save(qpTTPFastCenter);
	}

	/**
	 * 根据主键对象，删除QpTTPFastCenter信息
	 * @param QpTTPFastCenterId
	 */
	public void deleteByPK(QpTTPFastCenterId id) throws Exception{
			qpTTPFastCenterDao.deleteByPK(QpTTPFastCenter.class,id);
	}

	@Override
	public void saveQpTTPFastCenterCompare(
			UmTUser umTUser) throws Exception {
		
		String sql = "insert into qp_t_tp_fast_centercompare values (?,?,?,?) ";
		
		if(umTUser.getSurveyCenterId().contains(",")){
			 String[] centerIdArr  = umTUser.getSurveyCenterId().split(",");
			 for (int i = 0 ; i <centerIdArr.length ; i++ ) {
			      String id = sysCommonDao.generateID("cen", "QP_SEQ_TP_FASE_CENTERCOMPARE", 8);  
			      commonDao.execute(sql, new Object[]{id,umTUser.getId().getUserCode(),centerIdArr[i].trim(),umTUser.getCenterName()});
			 } 
		}else{
			 String id = sysCommonDao.generateID("cen", "QP_SEQ_TP_FASE_CENTERCOMPARE", 8);  
			 commonDao.execute(sql, new Object[]{id,umTUser.getId().getUserCode(),umTUser.getSurveyCenterId(),umTUser.getCenterName()});
		}
		
		
		
	}

	@Override
	public void updateQpTTPFastCenterCompare(UmTUser umTUser) throws Exception {
        String delSql = "delete from qp_t_tp_fast_centercompare where usercode = ? ";
		commonDao.execute(delSql, new Object[]{umTUser.getId().getUserCode()});

        String insertSql = "insert into qp_t_tp_fast_centercompare values (?,?,?,?) ";
		if(umTUser.getSurveyCenterId().contains(",")){
			 String[] centerIdArr  = umTUser.getSurveyCenterId().split(",");
			 for (int i = 0 ; i <centerIdArr.length ; i++ ) {
			      String id = sysCommonDao.generateID("cen", "QP_SEQ_TP_FASE_CENTERCOMPARE", 8);  
			      commonDao.execute(insertSql, new Object[]{id,umTUser.getId().getUserCode(),centerIdArr[i].trim(),umTUser.getCenterName()});
			 } 
		}else{
			 String id = sysCommonDao.generateID("cen", "QP_SEQ_TP_FASE_CENTERCOMPARE", 8);  
			 commonDao.execute(insertSql, new Object[]{id,umTUser.getId().getUserCode(),umTUser.getSurveyCenterId(),umTUser.getCenterName()});
		}
	}

	@Override
	public List<QpTTPFastCentercompare> findQpTTPFastCenterCompare(UmTUser umTUser) throws Exception {
		String selectSql = "SELECT t2.CENTERNAME centerName,t2.CENTERID centerId FROM qp_t_tp_fast_centercompare t1,qp_t_tp_fast_center t2 WHERE t1.centerId=t2.CENTERID  and t1.usercode = ?";
	    String userCode = "";
		if(ToolsUtils.notEmpty(umTUser.getUsercode())){
	    	userCode = umTUser.getUsercode();
	    }else{
	    	userCode = umTUser.getId().getUserCode();
	    }
		List<QpTTPFastCentercompare> list = (List<QpTTPFastCentercompare>) commonDao.findListBySql(selectSql.toString(), QpTTPFastCentercompare.class,userCode );
        return list;
	}
}
