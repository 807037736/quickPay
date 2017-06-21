/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTTPTeamDaoHibernate;
import com.picc.qp.schema.model.QpTTPTeam;
import com.picc.qp.schema.model.QpTTPTeamId;
import com.picc.qp.service.facade.IQpTTPTeamService;


@Service("qpTTPTeamService")
public class QpTTPTeamServiceSpringImpl implements IQpTTPTeamService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPTeamDaoHibernate qpTTPTeamDao;
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;

	/**
	 * 根据主键对象QpTTPTeamId获取QpTTPTeam信息
	 * @param QpTTPTeamId
	 * @return QpTTPTeam
	 */
	public QpTTPTeam findQpTTPTeamByPK(QpTTPTeamId id) throws Exception{
			return qpTTPTeamDao.get(QpTTPTeam.class,id);
	}

	/**
	 * 根据qpTTPTeam和页码信息，获取Page对象
	 * @param qpTTPTeam
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPTeam的Page对象
	 */
	public Page findByQpTTPTeam(QpTTPTeam qpTTPTeam, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(ToolsUtils.notEmpty(qpTTPTeam.getTeamName())){
			queryRule.addEqual("teamName", qpTTPTeam.getTeamName());
		}
			
		if(ToolsUtils.notEmpty(qpTTPTeam.getTeamPhone())){
			queryRule.addEqual("teamPhone", qpTTPTeam.getTeamPhone());
		}
			
		if(ToolsUtils.notEmpty(qpTTPTeam.getValidStatus())){
			queryRule.addEqual("validStatus", qpTTPTeam.getValidStatus());
		}
		
		if(ToolsUtils.notEmpty(qpTTPTeam.getCityId())){
			queryRule.addEqual("cityId", qpTTPTeam.getCityId());
		}
		if(ToolsUtils.notEmpty(qpTTPTeam.getIsHighway())){
			queryRule.addEqual("isHighway", qpTTPTeam.getIsHighway());
		}	
		return qpTTPTeamDao.find(queryRule, pageNo, pageSize);
	}

	/**
     * 根据qpTTPTeam查询列表
     * @param qpTTPTeam
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPTeam
     */
    public List<QpTTPTeam> findByQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        if(ToolsUtils.notEmpty(qpTTPTeam.getIsHighway())){
			queryRule.addEqual("isHighway", qpTTPTeam.getIsHighway());
		}	
        queryRule.addAscOrder("teamOrder");
        return qpTTPTeamDao.find(queryRule);
    }
	
	/**
	 * 更新QpTTPTeam信息
	 * @param QpTTPTeam
	 */
	public void updateQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception{
			qpTTPTeamDao.update(qpTTPTeam);
	}

	/**
	 * 保存QpTTPTeam信息
	 * @param QpTTPTeam
	 */
	public void saveQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception{
			String TeamId = sysCommonDao.generateID("TPTE", "QP_SEQ_TP_TEAM", 16);
			QpTTPTeamId qpTTPTeamId = new QpTTPTeamId();
			qpTTPTeamId.setTeamId(TeamId);
			qpTTPTeam.setId(qpTTPTeamId);
			qpTTPTeamDao.save(qpTTPTeam);
	}

	/**
	 * 根据主键对象，删除QpTTPTeam信息
	 * @param QpTTPTeamId
	 */
	public void deleteByPK(QpTTPTeamId id) throws Exception{
			qpTTPTeamDao.deleteByPK(QpTTPTeam.class,id);
	}
}
