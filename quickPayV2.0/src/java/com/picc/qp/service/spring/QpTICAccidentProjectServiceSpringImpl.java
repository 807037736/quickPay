/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICAccidentDaoHibernate;
import com.picc.qp.dao.QpTICAccidentProjectDaoHibernate;
import com.picc.qp.dao.QpTTPCaseDaoHibernate;
import com.picc.qp.schema.model.QpTICAccidentProject;
import com.picc.qp.schema.model.QpTICAccidentProjectId;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentProjectService;


@Service("qpTICAccidentProjectService")
public class QpTICAccidentProjectServiceSpringImpl implements IQpTICAccidentProjectService{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QpTICAccidentProjectDaoHibernate qpTICAccidentProjectDao;

    @Autowired
    private QpTTPCaseDaoHibernate qpTTPCaseDao;

    @Autowired
    private QpTICAccidentDaoHibernate qpTICAccidentDao;
    
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
    public QpTICAccidentProject findQpTICAccidentProjectByPK(QpTICAccidentProjectId id) throws Exception{
	
	return qpTICAccidentProjectDao.get(QpTICAccidentProject.class,id);
    }

    @Override
    public List<QpTICAccidentProject> findQpTICAccidentProjectByPK(QpTICAccidentProject qpTICAccidentProject) throws Exception {
	QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	if(ToolsUtils.notEmpty(qpTICAccidentProject.getAcciId())){
	    queryRule.addEqual("acciId", qpTICAccidentProject.getAcciId());
	}
	if(ToolsUtils.notEmpty(qpTICAccidentProject.getCaseId())){
	    queryRule.addEqual("caseId", qpTICAccidentProject.getCaseId());
	}
	if(ToolsUtils.notEmpty(qpTICAccidentProject.getType())){
	    queryRule.addEqual("type", qpTICAccidentProject.getType());
	}
	return qpTICAccidentProjectDao.find(queryRule);
    }

    @Override
    public void updateQpTICAccidentProject(QpTICAccidentProject qpTICAccidentProject) throws Exception {
	qpTICAccidentProjectDao.update(qpTICAccidentProject);
    }
    
    @Override
    public void saveQpTICAccidentProject(QpTICAccidentProject qpTICAccidentProject) throws Exception {
	qpTICAccidentProjectDao.save(qpTICAccidentProject);
    }

    @Override
    public void deleteByCaseId(String caseId, String acciId) throws Exception {
	String sql = "delete from qp_t_ic_accident_project where caseId='" + caseId+"' and acciId = '" + acciId +"'";
	commonDao.execute(sql, null); 
    }

}
