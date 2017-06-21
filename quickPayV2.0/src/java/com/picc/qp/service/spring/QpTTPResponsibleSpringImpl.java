package com.picc.qp.service.spring;

import ins.framework.common.Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ExcelUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.vo.QpTTPResponsibleVO;
import com.picc.qp.service.facade.IQpTTPResponsibleService;

@Service("qpTTPResponsibleService")
public class QpTTPResponsibleSpringImpl implements IQpTTPResponsibleService {
    
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
		
	/**
	 * 定责信息查询
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public Page findByQpTTPResponsible(QpTTPResponsibleVO qpTTPResponsible, int pageNo, int pageSize) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append("  SELECT                                                                        ");
		sql.append("	coname as coname,count(coname)  as total ,                                  ");
		sql.append("	sum(mainResponsibility) as mainResponsibility ,                             ");
		sql.append("	sum(mainResponsibility)/count(coname)  as mainResponsibilityPro,            ");
		sql.append("	sum(secondaryResponsibility) as secondaryResponsibility ,                   ");
		sql.append("	sum(secondaryResponsibility)/count(coname) as secondaryResponsibilityPro ,  ");
		sql.append("	sum(sameResponsibility) as sameResponsibility ,                             ");
		sql.append("    sum(sameResponsibility)/count(coname) as sameResponsibilityPro ,            ");
		sql.append("	sum(allResponsibility) as allResponsibility ,                               ");
		sql.append("    sum(allResponsibility)/count(coname) as allResponsibilityPro,               ");
		sql.append("    sum(noResponsibility) as noResponsibility ,                                 ");
		sql.append("	sum(noResponsibility)/count(coname)  as noResponsibilityPro,                ");
		sql.append("    sum(afterEthig) as afterEthig,                                              ");
		sql.append("    sum(afterEthig)/count(coname) as afterEthigPro                              ");
		sql.append("  FROM v_qp_t_ic_accident_forcob                                                ");
		sql.append("  WHERE 1 = 1                                                                   ");
		// 拼接条件参数
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciStartTime())) {
        	sql.append(" AND acciTime >= '").append(qpTTPResponsible.getAcciStartTime()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciEndTime())) {
        	sql.append(" AND  acciTime <= '").append(qpTTPResponsible.getAcciEndTime()).append("'");
        }
        sql.append("  group by coname                                                               ");
		//String sql = "select coname as coname,count(coname)  as total ,sum(mainResponsibility) as mainResponsibility , sum(mainResponsibility)/count(coname)  as mainResponsibilityPro,sum(secondaryResponsibility) as secondaryResponsibility , sum(secondaryResponsibility)/count(coname) as secondaryResponsibilityPro ,sum(sameResponsibility) as sameResponsibility , sum(sameResponsibility)/count(coname) as sameResponsibilityPro ,sum(allResponsibility) as allResponsibility , sum(allResponsibility)/count(coname) as allResponsibilityPro,sum(noResponsibility) as noResponsibility , sum(noResponsibility)/count(coname)  as noResponsibilityPro,sum(afterEthig) as afterEthig, sum(afterEthig)/count(coname) as afterEthigPro from v_qp_t_ic_accident_forcob group by coname";
		return sysCommonDao.findBySql(QpTTPResponsibleVO.class, sql.toString(), pageNo, pageSize,null);
	}
	
	/**
	 * 定责合计信息查询
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public List findByTotalResponsible() throws Exception{
		String sql = "select '合计',count(coname) as totalAmount, sum(mainResponsibility) as totalMainResponsibility , concat( round( sum(mainResponsibility)/count(coname)*100, 2 ) , '%')  as totalMainResponsibilityPro, sum(secondaryResponsibility) as totalSecondaryResponsibility , concat( round( sum(secondaryResponsibility)/count(coname)*100, 2 ) , '%') as totalSecondaryResponsibilityPro , sum(sameResponsibility) as totalSameResponsibility , concat( round( sum(sameResponsibility)/count(coname)*100, 2 ) , '%') as totalSameResponsibilityPro , sum(allResponsibility) as totalAllResponsibility , concat( round( sum(allResponsibility)/count(coname)*100, 2 ) , '%') as totalSllResponsibilityPro, sum(noResponsibility) as totalNoResponsibility , concat( round( sum(noResponsibility)/count(coname)*100, 2 ) , '%')  as  totalNoResponsibilityPro, sum(afterEthig) as totalfterEthig, concat( round( sum(afterEthig)/count(coname)*100, 2 ) , '%') as totalSfterEthigPro from v_qp_t_ic_accident_forcob";
		return sysCommonDao.findBySql(sql,new Object[]{});
	}
	
	/**
	 * 定责信息导出
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public HSSFWorkbook exportResponsibleExcel(QpTTPResponsibleVO qpTTPResponsible, String filePath)  throws Exception  {
		                                                
		StringBuffer exportSql = new StringBuffer();
		
		exportSql.append(" select coname as coname, count(coname)  as total , ");
		exportSql.append(" IFNULL( sum(mainResponsibility), 0)  as mainResponsibility ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(mainResponsibility)/count(coname)*100, 2 ) , '%') , 0)   as mainResponsibilityPro,");
		exportSql.append(" IFNULL( sum(secondaryResponsibility), 0)  as secondaryResponsibility ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(secondaryResponsibility)/count(coname)*100, 2 ) , '%') , 0)   as secondaryResponsibilityPro,");
		exportSql.append(" IFNULL( sum(sameResponsibility), 0)  as sameResponsibility ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(sameResponsibility)/count(coname)*100, 2 ) , '%') , 0)   as sameResponsibilityPro,");
		exportSql.append(" IFNULL( sum(allResponsibility), 0)  as allResponsibility ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(allResponsibility)/count(coname)*100, 2 ) , '%') , 0)   as allResponsibilityPro,");
		exportSql.append(" IFNULL( sum(noResponsibility), 0)  as noResponsibility ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(noResponsibility)/count(coname)*100, 2 ) , '%') , 0)   as noResponsibilityPro,");
		exportSql.append(" IFNULL( sum(afterEthig), 0)  as afterEthig ,  ");
		exportSql.append(" IFNULL(  concat( round( sum(afterEthig)/count(coname)*100, 2 ) , '%') , 0)   as afterEthigPro");
		exportSql.append(" from v_qp_t_ic_accident_forcob");
		exportSql.append("  WHERE 1 = 1   ");
		 // 拼接条件参数
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciStartTime())) {
        	exportSql.append(" AND acciTime >= '").append(qpTTPResponsible.getAcciStartTime()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciEndTime())) {
        	exportSql.append(" AND  acciTime <= '").append(qpTTPResponsible.getAcciEndTime()).append("'");
        }
		exportSql.append(" group by coname");
		
		//String exportSql = "select coname as coname, count(coname)  as total , sum(mainResponsibility) as mainResponsibility ,  concat( round( sum(mainResponsibility)/count(coname)*100, 2 ) , '%')  as mainResponsibilityPro, sum(secondaryResponsibility) as secondaryResponsibility ,  concat( round( sum(secondaryResponsibility)/count(coname)*100, 2 ) , '%') as secondaryResponsibilityPro , sum(sameResponsibility) as sameResponsibility , concat( round( sum(sameResponsibility)/count(coname)*100, 2 ) , '%')  as sameResponsibilityPro , sum(allResponsibility) as allResponsibility ,  concat( round( sum(allResponsibility)/count(coname)*100, 2 ) , '%') as allResponsibilityPro, sum(noResponsibility) as noResponsibility ,  concat( round( sum(noResponsibility)/count(coname)*100, 2 ) , '%')  as noResponsibilityPro, sum(afterEthig) as afterEthig, concat( round( sum(afterEthig)/count(coname)*100, 2 ) , '%')  as afterEthigPro  from v_qp_t_ic_accident_forcob group by coname";
		//获取结果集
		List resultList = sysCommonDao.findBySql(exportSql.toString(), new Object[]{});
		
		//获取合计
		//List list= findByTotalResponsible();
		StringBuffer sql = new StringBuffer();
        sql.append(" select '合计',count(coname) as totalAmount,  ");
        sql.append(" IFNULL( sum(mainResponsibility),0) as totalMainResponsibility , ");
        sql.append(" IFNULL( concat( round( sum(mainResponsibility)/count(coname)*100, 2 ) , '%'),0)  as totalMainResponsibilityPro, ");
        sql.append(" IFNULL( sum(secondaryResponsibility),0) as totalSecondaryResponsibility , ");
        sql.append(" IFNULL( concat( round( sum(secondaryResponsibility)/count(coname)*100, 2 ) , '%'),0) as totalSecondaryResponsibilityPro , ");
        sql.append(" IFNULL( sum(sameResponsibility),0) as totalSameResponsibility , ");
        sql.append(" IFNULL( concat( round( sum(sameResponsibility)/count(coname)*100, 2 ) , '%'),0) as totalSameResponsibilityPro , ");
        sql.append(" IFNULL( sum(allResponsibility),0) as totalAllResponsibility , ");
        sql.append(" IFNULL( concat( round( sum(allResponsibility)/count(coname)*100, 2 ) , '%'),0) as totalSllResponsibilityPro,  ");
        sql.append(" IFNULL( sum(noResponsibility),0) as totalNoResponsibility , ");
        sql.append(" IFNULL( concat( round( sum(noResponsibility)/count(coname)*100, 2 ) , '%'),0)  as  totalNoResponsibilityPro, ");
        sql.append(" IFNULL( sum(afterEthig),0) as totalfterEthig, ");
        sql.append("  IFNULL( concat( round( sum(afterEthig)/count(coname)*100, 2 ) , '%'),0) as totalSfterEthigPro  "); 
        sql.append("  FROM v_qp_t_ic_accident_forcob   ");
        sql.append("  WHERE 1 = 1      ");
       // 拼接条件参数
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciStartTime())) {
        	sql.append(" AND acciTime >= '").append(qpTTPResponsible.getAcciStartTime()).append("'");
        }
        if(ToolsUtils.notEmpty(qpTTPResponsible.getAcciEndTime())) {
        	sql.append(" AND acciTime <= '").append(qpTTPResponsible.getAcciEndTime()).append("'");
        }
        List list=  sysCommonDao.findBySql(sql.toString(),new Object[]{});
		
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		InputStream is = new FileInputStream(new File(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		HSSFSheet sheet = workbook.getSheetAt(0); 
   
		// 对表头一下的数据进行写入
		HSSFCellStyle bodyStyle = ExcelUtils.getBodyStyle(workbook, "宋体", (short) 10);
		HSSFRow hssfRow = null;
		HSSFCell cells = null;
		if(list != null && list.size() > 0) {
			resultList.add(resultList.size(), list.get(0));
		}

		int size = resultList.size();
		for(int i = 0; i < size; i++) {
			if(i == 0) {
				hssfRow = sheet.getRow(1);
				cells = hssfRow.createCell(0);
				cells.setCellValue("统计时间" + qpTTPResponsible.getAcciStartTime() + "至" + qpTTPResponsible.getAcciEndTime());
			}
			
			hssfRow = sheet.createRow(i + 3);// 数据行，从第3行开始
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
					}
				}
			}
		}
		return workbook;
	}
}
