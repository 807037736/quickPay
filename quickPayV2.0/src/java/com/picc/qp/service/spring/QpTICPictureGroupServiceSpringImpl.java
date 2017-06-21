/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICPictureGroupDaoHibernate;
import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;
import com.picc.qp.service.facade.IQpTICPictureGroupService;


@Service("qpTICPictureGroupService")
public class QpTICPictureGroupServiceSpringImpl implements IQpTICPictureGroupService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTICPictureGroupDaoHibernate qpTICPictureGroupDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	/**
	 * 根据主键对象QpTICPictureGroupId获取QpTICPictureGroup信息
	 * @param QpTICPictureGroupId
	 * @return QpTICPictureGroup
	 */
	public QpTICPictureGroup findQpTICPictureGroupByPK(QpTICPictureGroupId id) throws Exception{
			return qpTICPictureGroupDao.get(QpTICPictureGroup.class,id);
	}

	/**
	 * 根据qpTICPictureGroup和页码信息，获取Page对象
	 * @param qpTICPictureGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICPictureGroup的Page对象
	 */
	public Page findByQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTICPictureGroup生成queryRule
		
		return qpTICPictureGroupDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTICPictureGroup信息
	 * @param QpTICPictureGroup
	 */
	public void updateQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup) throws Exception{
			qpTICPictureGroupDao.update(qpTICPictureGroup);
	}

	/**
	 * 保存QpTICPictureGroup信息
	 * @param QpTICPictureGroup
	 */
	public void saveQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup) throws Exception{
			qpTICPictureGroupDao.save(qpTICPictureGroup);
	}

	/**
	 * 根据主键对象，删除QpTICPictureGroup信息
	 * @param QpTICPictureGroupId
	 */
	public void deleteByPK(QpTICPictureGroupId id) throws Exception{
			qpTICPictureGroupDao.deleteByPK(QpTICPictureGroup.class,id);
	}
	/**
     * 根据手机号/车牌号+48小时内提取上传照片
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findByQpTICPictureGroupDraw(QpTICPictureGroup qpTICPictureGroup, int pageNo, int pageSize) throws Exception {
        StringBuffer sql = new StringBuffer();
        
        sql.append("  SELECT g.UPLOADTIMEFORHIS UPLOADTIMEFORHIS,     ");
        sql.append("         g.GROUPID GROUPID                        ");
        sql.append("   FROM qp_t_ic_picture_group g, um_t_user u      ");
        sql.append("  WHERE g.UPLOADUSERCODE = u.usercode             ");
        // 拼接条件参数
        // 限制在48小时区间
        sql.append("  AND g.UPLOADTIMEFORHIS > DATE_SUB(SYSDATE(),INTERVAL 2 DAY) ");
        if(ToolsUtils.notEmpty(qpTICPictureGroup.getMobile()) && ToolsUtils.notEmpty(qpTICPictureGroup.getLicenseNo())) {
        	sql.append("  AND  (u.mobile = '").append(qpTICPictureGroup.getMobile()).append("' OR u.licenseno = '").append(qpTICPictureGroup.getLicenseNo()).append("') ");
        }
        else if(ToolsUtils.notEmpty(qpTICPictureGroup.getMobile())) {
        	sql.append("  AND  u.mobile = '").append(qpTICPictureGroup.getMobile()).append("'");
        }
        else if(ToolsUtils.notEmpty(qpTICPictureGroup.getLicenseNo())) {
        	sql.append("  AND u.licenseno = '").append(qpTICPictureGroup.getLicenseNo()).append("'");
        }
        sql.append(" ORDER BY g.UPLOADTIMEFORHIS DESC    ");

        return  sysCommonDao.findBySql(QpTICPictureGroup.class, sql.toString(),  pageNo, pageSize, null);
    }
	@Override
	public List<QpTICPictureGroup> findQpTICPictureGroupByUserCode(
			String userCode) {
		StringBuffer sql = new StringBuffer("select * from qp_t_ic_picture_group t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(ToolsUtils.notEmpty(userCode)) {
			sql.append(" and t.uploadusercode = ?");
			params.add(userCode);
		}
		sql.append(" and validStatus = '1'");
		sql.append(" and DATE_SUB(CURDATE(), INTERVAL 3 MONTH) <= t.uploadtimeforhis order by t.uploadtimeforhis DESC");
		return (List<QpTICPictureGroup>) sysCommonDao.findBySql(sql.toString(), QpTICPictureGroup.class, params.toArray());
	}
}
