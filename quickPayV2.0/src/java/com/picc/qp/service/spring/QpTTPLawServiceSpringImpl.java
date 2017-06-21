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
import com.picc.qp.dao.QpTTPLawDaoHibernate;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.service.facade.IQpTTPLawService;


@Service("qpTTPLawService")
public class QpTTPLawServiceSpringImpl implements IQpTTPLawService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPLawDaoHibernate qpTTPLawDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;

	/**
	 * 根据主键对象QpTTPLawId获取QpTTPLaw信息
	 * @param QpTTPLawId
	 * @return QpTTPLaw
	 */
	public QpTTPLaw findQpTTPLawByPK(QpTTPLawId id) throws Exception{
			return qpTTPLawDao.get(QpTTPLaw.class,id);
	}

	/**
	 * 根据qpTTPLaw和页码信息，获取Page对象
	 * @param qpTTPLaw
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPLaw的Page对象
	 */
	public Page findByQpTTPLaw(QpTTPLaw qpTTPLaw, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTTPLaw生成queryRule
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawName())){
			queryRule.addLike("lawName", "%"+qpTTPLaw.getLawName()+"%");
		}
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawWords())){
			queryRule.addEqual("lawWords", "%"+qpTTPLaw.getLawWords()+"%");
		}
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawItem())){
			queryRule.addEqual("lawItem", qpTTPLaw.getLawItem());
		}
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawSection())){
			queryRule.addEqual("lawSection", qpTTPLaw.getLawSection());
		}
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawSegment())){
			queryRule.addEqual("lawSegment", qpTTPLaw.getLawSegment());
		}
		
		if(ToolsUtils.notEmpty(qpTTPLaw.getValidStatus())){
			queryRule.addEqual("validStatus", qpTTPLaw.getValidStatus());
		}
		
//		if(ToolsUtils.notEmpty(qpTTPLaw.getLawOrder())){
//			queryRule.addEqual("lawOrder", qpTTPLaw.getLawOrder());
//		}
		queryRule.addAscOrder("lawOrder");
		return qpTTPLawDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTTPLaw和页码信息列表
     * @param qpTTPLaw
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPLaw
     */
    public List<QpTTPLaw> findByQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTTPLaw生成queryRule
        if(ToolsUtils.notEmpty(qpTTPLaw.getLawName())){
            queryRule.addLike("lawName", "%"+qpTTPLaw.getLawName()+"%");
        }
        if(ToolsUtils.notEmpty(qpTTPLaw.getLawWords())){
            queryRule.addEqual("lawWords", "%"+qpTTPLaw.getLawWords()+"%");
        }
        if(ToolsUtils.notEmpty(qpTTPLaw.getLawItem())){
            queryRule.addEqual("lawItem", qpTTPLaw.getLawItem());
        }
        if(ToolsUtils.notEmpty(qpTTPLaw.getLawSection())){
            queryRule.addEqual("lawSection", qpTTPLaw.getLawSection());
        }
        if(ToolsUtils.notEmpty(qpTTPLaw.getLawSegment())){
            queryRule.addEqual("lawSegment", qpTTPLaw.getLawSegment());
        }
        
        if(ToolsUtils.notEmpty(qpTTPLaw.getValidStatus())){
            queryRule.addEqual("validStatus", qpTTPLaw.getValidStatus());
        }
        
        queryRule.addAscOrder("lawOrder");
        return qpTTPLawDao.find(queryRule);
    }

	/**
	 * 更新QpTTPLaw信息
	 * @param QpTTPLaw
	 */
	public void updateQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception{
			qpTTPLawDao.update(qpTTPLaw);
	}

	/**
	 * 保存QpTTPLaw信息
	 * @param QpTTPLaw
	 */
	public void saveQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception{
		String LawId = sysCommonDao.generateID("TPLA", "QP_SEQ_TP_LAW", 16);
		QpTTPLawId qpTTPLawId = new QpTTPLawId();
		qpTTPLawId.setLawId(LawId);
		qpTTPLaw.setId(qpTTPLawId);
		qpTTPLawDao.save(qpTTPLaw);
	}

	/**
	 * 根据主键对象，删除QpTTPLaw信息
	 * @param QpTTPLawId
	 */
	public void deleteByPK(QpTTPLawId id) throws Exception{
			qpTTPLawDao.deleteByPK(QpTTPLaw.class,id);
	}
}
