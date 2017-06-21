/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.StringUtils;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCommonService;


@Service("qpTCommonService")
public class QpTCommonServiceSpringImpl implements IQpTCommonService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	/**
	 * 获取下拉框数据
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QpSelectDataVo> getSelectData(String codeType) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.codeCode codeCode,a.codeCName codeCName from MC_D_NewCode a,MC_D_Type b where a.codeType=b.newCodeType and a.CODETYPE = '");
		sql.append(codeType).append("' and a.VALIDSTATUS='1' and b.VALIDSTATUS='1' order by a.sort ");
		return (List<QpSelectDataVo>) commonDao.findBySql(sql.toString(), QpSelectDataVo.class, null);
	}
	/**
	 * 通过codeType，codeCode取codeCName
	 * @param codeType
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String getSelectCodeName(String codeType,String codeCode) throws Exception {
		String codeName = "";
		StringBuffer sql = new StringBuffer();
		sql.append("select a.codeCode codeCode,a.codeCName codeCName from MC_D_NewCode a,MC_D_Type b where a.codeType=b.newCodeType and a.CODETYPE = ? ");
		sql.append(" and a.CODECODE=? and a.VALIDSTATUS='1' and b.VALIDSTATUS='1' ");
		List<QpSelectDataVo> list =(List<QpSelectDataVo>) commonDao.findBySql(sql.toString(), QpSelectDataVo.class, new String[]{codeType,codeCode} );
		if(list!=null&&list.size()>0){
			codeName = list.get(0).getCodeCName();
		}
		return codeName;
	}
	
	public String getCodeCName(String codeType,List<QpSelectDataVo> list){
		String codeName = "";
		if(StringUtils.isEmptyStr(codeType)||list==null||list.size()==0){
			return codeName;
		}
		for(QpSelectDataVo ggcode :list){
			if(codeType.equals(ggcode.getCodeCode())){
				codeName = ggcode.getCodeCName();
				break;
			}
		}
		return codeName;
	}
	
}
