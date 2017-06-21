/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICPictureDaoHibernate;
import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureId;
import com.picc.qp.service.facade.IQpTICPictureService;


@Service("qpTICPictureService")
public class QpTICPictureServiceSpringImpl implements IQpTICPictureService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QpTICPictureDaoHibernate qpTICPictureDao;

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象QpTICPictureId获取QpTICPicture信息
	 * @param QpTICPictureId
	 * @return QpTICPicture
	 */
	public QpTICPicture findQpTICPictureByPK(QpTICPictureId id) throws Exception{
		return qpTICPictureDao.get(QpTICPicture.class,id);
	}

	public List<QpTICPicture> findQpTICPictureAll() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM Qp_T_IC_Picture g  order by g.groupId ");
		List<QpTICPicture> qpTICPictures = (List<QpTICPicture>) commonDao.findListBySql(sql.toString(), QpTICPicture.class);
		return qpTICPictures;
	}

	public QpTICPicture findQpTICPictureByFileName(String fileName) throws Exception{	
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("fileName", fileName);
		List<QpTICPicture> qpTICPictures = commonDao.find(QpTICPicture.class, queryRule);
		if(qpTICPictures != null && qpTICPictures.size() > 0){
			return  qpTICPictures.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 获取所有QpTICPicture信息
	 * @return List<QpTICPicture>
	 * 用于更新图片地址
	 */
	public void updateFileName(int pageNo , int pageSize) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM Qp_T_IC_Picture g  order by g.groupId limit ?,?");
		List<QpTICPicture> qpTICPictures = (List<QpTICPicture>) commonDao.findListBySql(sql.toString(), QpTICPicture.class, pageNo, pageSize);
		if(qpTICPictures != null && qpTICPictures.size() > 0){
			for (int i = 0; i < qpTICPictures.size(); i++) {
				QpTICPicture qpTICPicture = qpTICPictures.get(i);
				logger.info("第"+(i+1)+"张图片：" + qpTICPicture.getId().getPicId());
				File file = new File(qpTICPicture.getFileName());
				//获取路径
				String fileGroupPath = qpTICPicture.getFileName().substring(0, qpTICPicture.getFileName().lastIndexOf("/"));
				File groupFile =  null;
				String [] PicPaths = null;//D/image 目录下的 组号中 所有图片

				groupFile = new File(fileGroupPath);
				PicPaths = groupFile.list();

				//获取数据库保存的图片名称
				String [] fileNames = qpTICPicture.getFileName().split("/");
				String  fileName = fileNames[fileNames.length-1];
				//处理状态  3 ： 已修改   0：路径一致   1：图片不存在
				//文件路径和数据库一致
				if(file.exists()){
					qpTICPicture.setValidStatus("0");
				}else{
					//组号在图片路径下存在文件
					if(PicPaths != null&&PicPaths.length>0){
						int c = 0;
						for (int j = 0; j < PicPaths.length; j++) {
							if(PicPaths[j].length()>14){
								String picNameTemp = PicPaths[j].substring(14);
								if(picNameTemp.equals(fileName)){
									c++;
								}
							}
						}
						boolean temp = false;
						for (int j = 0; j < PicPaths.length; j++) {
							if(PicPaths[j].length()>14){
								String picNameTemp = PicPaths[j].substring(14);
								if(picNameTemp.equals(fileName)){
									if(c > 1){
										QpTICPicture qpTICPicture2 = this.findQpTICPictureByFileName(fileGroupPath + "/" + PicPaths[j]);
										if(qpTICPicture2 != null && qpTICPicture2.getFileName().equals(fileGroupPath + "/" + PicPaths[j]) ){
											continue;
										}
									}
									temp = true;
									qpTICPicture.setFileName(fileGroupPath + "/" + PicPaths[j]);
									qpTICPicture.setValidStatus("3");//处理过的数据
									break;
								}
							}
						}
						if(!temp){
							qpTICPicture.setValidStatus("1");
						}
						
					}else{
						qpTICPicture.setValidStatus("1");
					}	
				}
				this.updateQpTICPicture(qpTICPicture);
			}
		}
	}

	/**
	 * 根据qpTICPicture和页码信息，获取Page对象
	 * @param qpTICPicture
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICPicture的Page对象
	 */
	public Page findByQpTICPicture(QpTICPicture qpTICPicture, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		//根据qpTICPicture生成queryRule
		if(ToolsUtils.notEmpty(qpTICPicture.getAcciId())) {
			queryRule.addEqual("acciId", qpTICPicture.getAcciId());
		}
		if(ToolsUtils.notEmpty(qpTICPicture.getGroupId())) {
			queryRule.addEqual("groupId", qpTICPicture.getGroupId());
		}
		if(ToolsUtils.isEmpty(qpTICPicture.getAcciId()) && ToolsUtils.isEmpty(qpTICPicture.getGroupId())) {
			queryRule.addEqual("groupId", "1");
		}
		return qpTICPictureDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTICPicture信息
	 * @param QpTICPicture
	 */
	public void updateQpTICPicture(QpTICPicture qpTICPicture) throws Exception{
		qpTICPictureDao.update(qpTICPicture);
	}

	/**
	 * 保存QpTICPicture信息
	 * @param QpTICPicture
	 */
	public void saveQpTICPicture(QpTICPicture qpTICPicture) throws Exception{
		qpTICPictureDao.save(qpTICPicture);
	}

	/**
	 * 根据主键对象，删除QpTICPicture信息
	 * @param QpTICPictureId
	 */
	public void deleteByPK(QpTICPictureId id) throws Exception{
		qpTICPictureDao.deleteByPK(QpTICPicture.class,id);
	}

	@Override
	public void saveQpTICPicture(QpTICPicture qpTICPicture, String userCode) {
		QpTICPictureId qpTICPictureId = new QpTICPictureId() ;
		qpTICPictureId.setPicId(sysCommonDao.generateID("PIC", "QP_SEQ_IC_PICTURE", 17));
		qpTICPicture.setId(qpTICPictureId);
//		qpTICPicture.setPicDesc("哎哟！不错哦");
		qpTICPicture.setUploadTime(new Date());
		qpTICPicture.setCreatorCode(userCode);
		qpTICPicture.setInsertTimeForHis(new Date());
		qpTICPictureDao.save(qpTICPicture);
	}
	public String getGroupID(){
		return  sysCommonDao.generateID("G", "QP_SEQ_IC_PICTURE_GROUP", 10);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 通过组号找到对应的图片
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public List<QpTICPicture> findQpTICPictureByGroupId(String groupId)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM Qp_T_IC_Picture g WHERE g.groupId = ?  order by g.PICID ");
		List<QpTICPicture> qpTICAccidentList = (List<QpTICPicture>) commonDao.
				findListBySql(sql.toString(), QpTICPicture.class,groupId);
		return qpTICAccidentList;
	}

	@Override
	public void saveQpTICPictures(List<QpTICPicture> qpTICPictures, String userCode) {
		for (QpTICPicture qpTICPicture : qpTICPictures) {
			saveQpTICPicture(qpTICPicture, userCode);
		}
	}

}
