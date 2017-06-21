/*
 * Excel工具类
 */
package com.picc.common.utils;

import ins.framework.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;*/

public class ExcelUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	
	private static final String DEFAULT_FONTNAME = "宋体";
	private static final int DEFAULT_FONTPOINTS = 10;
	private static final int DEFAULT_SHEETINDEX =0 ;

	/**
     * 从Excel文件得到二维数组，每个sheet的第一行为标题
     * 
     * @param file
     *            Excel文件
     * @return
     * @throws Exception 
     */
    public static String[][] getData(File file) throws Exception {
        return getData(file, 1);
    }

    /**
     * 从Excel文件得到二维数组 只取标题Title，即第一行。
     * 
     * @param file
     *            Excel文件
     * @param ignoreRows
     *            忽略的行数，通常为每个sheet的标题行数
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getDataTitle(File file) throws FileNotFoundException, IOException {
        ArrayList result = new ArrayList();
        int rowSize = 0;
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        BufferedInputStream in = null;
        Workbook wb = null;
        try {
        	in = new BufferedInputStream(new FileInputStream(file));
        	POIFSFileSystem fs = new POIFSFileSystem(in);
        	wb = new HSSFWorkbook(fs);
        } catch (Exception ex) {
        	in = new BufferedInputStream(new FileInputStream(file));
        	wb = new XSSFWorkbook(in); 
        }
        
        Cell cell = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder value = new StringBuilder();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            Sheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，取
            Row row = st.getRow(0);//只取第一行
            if (row == null) {
                continue;
            }
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                value.setLength(0);
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value.append(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value.append(dateFormat.format(date));
                            }
                        } else {
                            value.append(decimalFormat.format(cell.getNumericCellValue()));
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        // 导入时如果为公式生成的数据则无值
                        if (cell.getStringCellValue().equals("")) {
                            value.append(cell.getNumericCellValue());
                        } else {
                            value.append(cell.getStringCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value.append((cell.getBooleanCellValue() == true ? "Y" : "N"));
                        break;
                    default:
                        value.setLength(0);
                    }
                }
                values[columnIndex] = StringUtils.rightTrim(value.toString());
                hasValue = true;
            }
            if (hasValue) {
                result.add(values);
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 从Excel文件得到二维数组
     * 
     * @param file
     *            Excel文件
     * @param ignoreRows
     *            忽略的行数，通常为每个sheet的标题行数
     * @return
     * @throws Exception 
     */
    public static String[][] getData(File file, int ignoreRows) throws Exception {
        try {
            ArrayList result = new ArrayList();
            int rowSize = 0;
            BufferedInputStream in =null;
            // 打开HSSFWorkbook
            Workbook wb = null;
            try {
            	in =  new BufferedInputStream(new FileInputStream(file));
            	POIFSFileSystem fs = new POIFSFileSystem(in);
            	wb = new HSSFWorkbook(fs);
            } catch (Exception ex) {
            	in =  new BufferedInputStream(new FileInputStream(file));
            	wb = new XSSFWorkbook(in); 
            }
            Cell cell = null;
            DecimalFormat df = new DecimalFormat("#.00000000");
            StringBuilder value = new StringBuilder();
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                Sheet st = wb.getSheetAt(sheetIndex);
                // 第一行为标题，不取
                for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                    Row row = st.getRow(rowIndex);
                     if (row == null) {
                         continue;
                     }
                    if (isBlankRow(row)) {
                        // 判断当前行是否为空，若为空则直接remove此行
                        st.removeRow(row);
                        continue;
                    }

                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }

                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                        value.setLength(0);
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            // 注意：一定要设成这个，否则可能会出现乱码
                            // cell.setEncoding(Cell.ENCODING_UTF_16);
                            switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                value.append(cell.getStringCellValue());
                                break;
                            // Cell的Type为CELL_TYPE_NUMERIC时,还需进一步判断该Cell的数据格式，因为它有可能是Date类型，在Excel中的Date类型也是以Double类型的数字存储的
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value.append(new SimpleDateFormat("yyyy-MM-dd")
                                                .format(date));
                                    }
                                }else {
                                    value.append(df.format(cell.getNumericCellValue()));// 将科学计数法转换为单元格中实际值
                                    /*
                                     * NumberFormat nf =
                                     * NumberFormat.getInstance();
                                     * nf.setGroupingUsed
                                     * (false);//若为true时，金额格式：1,234,567
                                     * value=nf.
                                     * format(cell.getNumericCellValue());
                                     */
                                }
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                value.append(cell.getCellFormula());
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                value.append(cell.getBooleanCellValue() == true ? "Y" : "N");
                                break;
                            default:
                                value.setLength(0);
                            }
                        }
                        values[columnIndex] = StringUtils.rightTrim(value.toString());
                        hasValue = true;
                    }

                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
            in.close();
            String[][] returnArray = new String[result.size()][rowSize];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = (String[]) result.get(i);
            }
            return returnArray;
        } catch (Exception e) {
            e.printStackTrace();
			logger.error("", e);
            Exception ex = new Exception("prompt.excel.excelTemplateInvalid");
            throw ex;
        }
        
    }

    /**
     * 判断是否空行
     * 
     * @param row
     * @return author congfei. 2012-12-14下午06:05:21
     */
    public static boolean isBlankRow(Row row) {
        boolean result = true;
        Cell cell;
        String value;
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
            value = "";
            if (cell != null) {
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                default:
                    break;
                }
                if (!value.trim().equals("")) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 解析一个Excel文件的某个特定的sheet sheet号码从1开始
     * 
     * @param file
     *            excel文件
     * @param ignoreRows
     *            忽略的行数
     * @param sheetIndex
     *            sheet的序列，从0开始
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows, Integer sheetIndex) throws FileNotFoundException, IOException {
        ArrayList result = new ArrayList();
        int rowSize = 0;
        BufferedInputStream in = null;
        // 打开HSSFWorkbook
        Workbook wb = null;
        try {
        	in = new BufferedInputStream(new FileInputStream(file));
        	POIFSFileSystem fs = new POIFSFileSystem(in);
        	wb = new HSSFWorkbook(fs);
        } catch (Exception ex) {
        	in = new BufferedInputStream(new FileInputStream(file));
        	wb = new XSSFWorkbook(in); 
        }
        Cell cell = null;
        if(sheetIndex==null) {
        	sheetIndex=DEFAULT_SHEETINDEX;
        }
        Sheet st = wb.getSheetAt(sheetIndex);
        // 第一行为标题，不取
        for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            Row row = st.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = "";
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        // 导入时如果为公式生成的数据则无值
                        if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + "";
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        value = "";
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                        break;
                    default:
                        value = "";
                    }
                }
                if (columnIndex == 0 && value.trim().equals("")) {
                    break;
                }
                values[columnIndex] = StringUtils.rightTrim(value);
                hasValue = true;
            }

            if (hasValue) {
                result.add(values);
            }
        }

        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
    
    /**
    *	获取excel表
    *	@author xiehui
    */
    public static Workbook getWorkbook(File file) throws Exception {
    	Workbook wb = null;
    	BufferedInputStream in = null;
        try {
        	in = new BufferedInputStream(new FileInputStream(file));
        	POIFSFileSystem fs = new POIFSFileSystem(in);
        	wb = new HSSFWorkbook(fs);
        } catch (Exception ex) {
        	in = new BufferedInputStream(new FileInputStream(file));
        	wb = new XSSFWorkbook(in); 
        }
        
        if(in!=null) {
        	in.close();
        }
        return wb;
    }
    
    
    /**
     * 从Excel文件得到二维数组 只取标题Title，即第一行。
     * 
     * @param workbook
     *            Excel文件
     * @param sheetIndex
     *            sheet序列,从0开始
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getDataTitle(Workbook workbook, Integer sheetIndex) throws FileNotFoundException, IOException {
        ArrayList result = new ArrayList();
        int rowSize = 0;       
        
        Cell cell = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder value = new StringBuilder();
        if(sheetIndex==null) {
        	sheetIndex=DEFAULT_SHEETINDEX;
        }
        
        Sheet st = workbook.getSheetAt(sheetIndex);
        // 第一行为标题，取
        Row row = st.getRow(0);//只取第一行
        if (row != null) {
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                value.setLength(0);
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value.append(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value.append(dateFormat.format(date));
                            }
                        } else {
                            value.append(decimalFormat.format(cell.getNumericCellValue()));
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        // 导入时如果为公式生成的数据则无值
                        if (cell.getStringCellValue().equals("")) {
                            value.append(cell.getNumericCellValue());
                        } else {
                            value.append(cell.getStringCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value.append((cell.getBooleanCellValue() == true ? "Y" : "N"));
                        break;
                    default:
                        value.setLength(0);
                    }
                }
                values[columnIndex] = StringUtils.rightTrim(value.toString());
                hasValue = true;
            }
            if (hasValue) {
                result.add(values);
            }
        }
        
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
    
    /**
     * 解析一个Excel文件的某个特定的sheet sheet号码从1开始
     * 
     * @param file
     *            excel文件
     * @param ignoreRows
     *            忽略的行数
     * @param sheetIndex
     *            sheet的序列，从0开始
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(Workbook workbook, int ignoreRows, Integer sheetIndex) throws FileNotFoundException, IOException {
        ArrayList result = new ArrayList();
        int rowSize = 0;
       
        Cell cell = null;
        if(sheetIndex==null) {
        	sheetIndex=DEFAULT_SHEETINDEX;
        }
        Sheet st = workbook.getSheetAt(sheetIndex);
        // 第一行为标题，不取
        for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            Row row = st.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = "";
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        // 导入时如果为公式生成的数据则无值
                        if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + "";
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        value = "";
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                        break;
                    default:
                        value = "";
                    }
                }
                if (columnIndex == 0 && value.trim().equals("")) {
                    break;
                }
                values[columnIndex] = StringUtils.rightTrim(value);
                hasValue = true;
            }

            if (hasValue) {
                result.add(values);
            }
        }

        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
    
    
    /**
     * 将list中的数据写入的新的excel文件中，页面下载信息
     * @param dataList			//要处理的数据
     * @param fileName			//表头
     * @param response
     * @throws Exception
     * @author wangting
     */
    public static void writeDataToExcel(List<String[]> dataList,List<String> fileds,HttpServletResponse response) throws Exception {
		HSSFWorkbook workbook=new HSSFWorkbook(); 
		HSSFSheet sheet=workbook.createSheet();
		
		//创建的第一行是表头
		HSSFRow hssfRowHead = sheet.createRow(0);
		for (int i = 0; i < fileds.size(); i ++){
			HSSFCell cellsHead = hssfRowHead.createCell((short) i);
			cellsHead.setCellValue(fileds.get(i));
		}
		//对表头一下的数据进行写入
		for (int i = 0; i < dataList.size(); i++) {
            HSSFRow hssfRow = sheet.createRow(i + 1);//数据行，从第1行开始
            for (int j = 0; j < dataList.get(i).length; j ++){
            	HSSFCell cells = hssfRow.createCell((short) j);//数据列，从0开始
            	cells.setCellValue(dataList.get(i)[j]);
            }
        }
		
		//让每一个列进行自动调整大小
        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 4);
        sheet.autoSizeColumn((short) 5);
        sheet.autoSizeColumn((short) 6);
        sheet.autoSizeColumn((short) 7);
        sheet.autoSizeColumn((short) 8);
        sheet.autoSizeColumn((short) 9);
        sheet.autoSizeColumn((short) 10);
        sheet.autoSizeColumn((short) 11);
        sheet.autoSizeColumn((short) 12);
        sheet.autoSizeColumn((short) 13);
        sheet.autoSizeColumn((short) 14);
        sheet.autoSizeColumn((short) 15);
        sheet.autoSizeColumn((short) 16);
        sheet.autoSizeColumn((short) 17);
        sheet.autoSizeColumn((short) 18);
        sheet.autoSizeColumn((short) 19);
        sheet.autoSizeColumn((short) 20);
        sheet.autoSizeColumn((short) 21);
        sheet.autoSizeColumn((short) 22);

        //页面直接提示下载信息
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Content-disposition", "attachment;filename=" + "");  
        OutputStream os = response.getOutputStream();   
        workbook.write(os);   
        os.close();
        
       /* FileOutputStream out=new FileOutputStream("c:\\test.xls");  
        workbook.write(out);  //将Excel文档保存到C盘根目录下
        out.close();*/
    }
    
    /**
     * 将list中的数据写入的新的excel文件中，页面下载信息
     * @param dataList			//要处理的数据
     * @param fileds			//表头
     * @param response
     * @param sheetName		//sheet表单名
     * @throws Exception
     * @author xiehui
     */
    public static void writeDataToExcel(List dataList,List<String> fileds,HttpServletResponse response, String sheetName) throws Exception {
		
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	HSSFWorkbook workbook=new HSSFWorkbook(); 
		HSSFSheet sheet=workbook.createSheet();
		
		//设置sheet名
		if(ToolsUtils.notEmpty(sheetName)) {
			workbook.setSheetName(0, sheetName);
		}
		
		//创建的第一行是表头
		HSSFRow hssfRowHead = sheet.createRow(0);
		HSSFCellStyle headStyle = getTitleStyle(workbook, DEFAULT_FONTNAME, (short)DEFAULT_FONTPOINTS);
		HSSFCell cellsHead = null;
		for (int i = 0; i < fileds.size(); i ++){
			cellsHead = hssfRowHead.createCell((short) i);		
			cellsHead.setCellStyle(headStyle);
			cellsHead.setCellValue(fileds.get(i));
		}
		//对表头一下的数据进行写入
		HSSFCellStyle bodyStyle = getBodyStyle(workbook, DEFAULT_FONTNAME, (short)DEFAULT_FONTPOINTS);
		HSSFRow hssfRow = null;
		HSSFCell cells = null;
		for (int i = 0; i < dataList.size(); i++) {
			hssfRow = sheet.createRow(i + 1);//数据行，从第1行开始
			Object[] data = (Object[]) dataList.get(i);
			 
            for (int j = 0; j < data.length; j++){
            	cells = hssfRow.createCell((short) j);//数据列，从0开始
            	cells.setCellStyle(bodyStyle);
            	if(data[j]!=null) {
            		if(data[j] instanceof Date) {
            			cells.setCellValue(dateFormat.format((Date)data[j]) );
            		}else{
            			cells.setCellValue(data[j].toString());
            		}
            	}
            }
        }
		
		//让每一个列进行自动调整大小
		for(int i = 0; i <  fileds.size(); i++) {
			sheet.autoSizeColumn((short) i);
		}

        //页面直接提示下载信息
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Content-disposition", "attachment;filename=" + changeFileName("*.xls"));  
        OutputStream os = response.getOutputStream();   
        workbook.write(os);   
        os.close();
        
       /* FileOutputStream out=new FileOutputStream("c:\\test.xls");  
        workbook.write(out);  //将Excel文档保存到C盘根目录下
        out.close();*/
    }
    
    /**
     * 将不合格数据封装List写入Excel中
     * @param dataList
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
    public static void exportDataToExcel(List dataList, String fileName,HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        // 设置sheetName
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 设置sheet页名称
        wb.setSheetName(0, "不合格的客户清单");

        // 设置表头行、表字段
        String[] titles = (String[]) session.getAttribute("titleNames");
		String[] fields = (String[]) session.getAttribute("titleIds");
		
        HSSFCellStyle titleStyle = getTitleStyle(wb, DEFAULT_FONTNAME, (short) DEFAULT_FONTPOINTS);//标题样式
        // 创建标题行
        createTitle(sheet, (short) 0, titles, titleStyle);

        HSSFCellStyle bodyStyle = getBodyStyle(wb, DEFAULT_FONTNAME, (short) DEFAULT_FONTPOINTS);//数据样式
                 
        //数据有误的单元格样式设置
        HSSFCellStyle errorStyle = getErrorStyle(wb, DEFAULT_FONTNAME, (short) DEFAULT_FONTPOINTS);
        
        setBodyValue(sheet, (short) 1, dataList, fields, bodyStyle, errorStyle);

        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 4);
        sheet.autoSizeColumn((short) 5);
        sheet.autoSizeColumn((short) 6);
        sheet.autoSizeColumn((short) 7);
        sheet.autoSizeColumn((short) 8);
        sheet.autoSizeColumn((short) 9);
        sheet.autoSizeColumn((short) 10);
        sheet.autoSizeColumn((short) 11);
        sheet.autoSizeColumn((short) 12);
        sheet.autoSizeColumn((short) 13);
        sheet.autoSizeColumn((short) 14);
        sheet.autoSizeColumn((short) 15);
        sheet.autoSizeColumn((short) 16);
        sheet.autoSizeColumn((short) 17);
        sheet.autoSizeColumn((short) 18);
        sheet.autoSizeColumn((short) 19);
        sheet.autoSizeColumn((short) 20);
        sheet.autoSizeColumn((short) 21);
        sheet.autoSizeColumn((short) 22);
        sheet.autoSizeColumn((short) 23);
        sheet.autoSizeColumn((short) 24);
        sheet.autoSizeColumn((short) 25);
        sheet.autoSizeColumn((short) 26);
        sheet.autoSizeColumn((short) 27);
        sheet.autoSizeColumn((short) 28);
        sheet.autoSizeColumn((short) 29);
        sheet.autoSizeColumn((short) 30);
        sheet.autoSizeColumn((short) 31);

        //页面直接提示下载信息
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);  
        OutputStream os = response.getOutputStream();   
        wb.write(os);   
        os.flush();   
        os.close();
    }
    
    
    
    /**
     * 获得表头的样式
     * 
     * @param fontName
     *            字体名称 如：宋体、黑体等
     * @param fontPoins
     *            字体大小 如：10、12、15
     * @return
     */
    public static HSSFCellStyle getTitleStyle(HSSFWorkbook wb, String fontName, short fontPoins) {
        // 设置样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontPoins);
        font.setFontName(fontName);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        return style;

    }
    
    /**
     * 
     * 创建excel的表头 （动态的）
     * 
     * @param sheet
     *            excel中的sheet页
     * @param row
     *            第几行插入表头
     * @param titles
     *            表头的内容
     * @param style
     *            表头的样式
     */
    public static void createTitle(HSSFSheet sheet, short row, String[] titles, HSSFCellStyle style) {
        HSSFRow hsshRow = sheet.createRow(row);
        String textValue = "";
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = hsshRow.createCell((short) i);
            cell.setAsActiveCell();
            cell.setCellStyle(style);
            // cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
            if (titles[i] != null) {
                textValue = titles[i].toString();
            }
            HSSFRichTextString richString = new HSSFRichTextString(textValue);
            cell.setCellValue(richString);
        }
    }
    
    /**
     * 获得除标题外数据的样式
     * 
     * @param fontName
     *            字体名称 如：宋体、黑体等
     * @param fontPoins
     *            字体大小 如：10、12、15
     * @return
     */
    public static HSSFCellStyle getBodyStyle(HSSFWorkbook wb, String fontName, short fontPoins) {
        // 设置样式格式为文本格式 
        HSSFCellStyle style = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();  //设置导出Excel单元格为文本格式
        style.setDataFormat(format.getFormat("@"));        
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontPoins);
        font.setFontName(fontName);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
    
    /**
     * 有误的单元格样式
     * @param wb
     * @param fontName
     * @param fontPoins
     * @return
     */
    public static HSSFCellStyle getErrorStyle(HSSFWorkbook wb, String fontName, short fontPoins) {
        // 设置样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置单元格的边框为粗体   
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        style.setTopBorderColor(HSSFColor.BLUE.index);  // 设置单元格的边框颜色．
        style.setBottomBorderColor(HSSFColor.BLUE.index); 
        style.setLeftBorderColor(HSSFColor.BLUE.index); 
        style.setRightBorderColor(HSSFColor.BLUE.index);   
        
        style.setFillPattern(HSSFCellStyle.BORDER_THIN);
        style.setFillForegroundColor(HSSFColor.RED.index);// 设置单元格的背景颜色．  
        style.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontPoins);
        font.setColor(HSSFColor.YELLOW.index);//设置字体颜色
        font.setFontName(fontName);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
    
    /**
     * 有误的单元格样式
     * @param wb 
     * @param fontName
     * @param fontPoins
     * @return
     */
    public static CellStyle getErrorStyle(Workbook wb, String fontName, short fontPoins) {
        // 设置样式
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置单元格的边框为粗体   
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        style.setTopBorderColor(HSSFColor.BLUE.index);  // 设置单元格的边框颜色．
        style.setBottomBorderColor(HSSFColor.BLUE.index); 
        style.setLeftBorderColor(HSSFColor.BLUE.index); 
        style.setRightBorderColor(HSSFColor.BLUE.index);   
        
        style.setFillPattern(HSSFCellStyle.BORDER_THIN);
        style.setFillForegroundColor(HSSFColor.RED.index);// 设置单元格的背景颜色．  
        style.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
        // 设置字体
        Font font = wb.createFont();
        font.setFontHeightInPoints(fontPoins);
        font.setColor(HSSFColor.YELLOW.index);//设置字体颜色
        font.setFontName(fontName);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
    
    /**
     * 处理数据，校验规则不符，则背景色置为红色,字体颜色为黄色
     * @param sheet 当前sheet页
     * @param row 从1行开始，默认为0
     * @param dataList 无效数据集
     * @param fields 无效字段
     * @param style 样式
     * @param errorStyle 错误的单元格样式
     * @throws Exception
     * 
     */
    public static void setBodyValue(HSSFSheet sheet, short row, List dataList, String[] fields,
            HSSFCellStyle style,HSSFCellStyle errorStyle) throws Exception {
        StringBuilder getMethodName = new StringBuilder(20);
        Object dataDto = null;
        Method getMethod = null;
        String fieldName = null;
        Object value = null;
        String textValue = "";
        for (int i = 0; i < dataList.size(); i++) {
            dataDto = (Object) dataList.get(i);
            Class dtoClass = dataDto.getClass();
            HSSFRow hssfRow = sheet.createRow(row + i);//数据行，下表从0开始，这里取第2行，即row为1
            HSSFCell cells = hssfRow.createCell((short) 0);//数据列，从0开始
            HSSFRichTextString rowNumber = new HSSFRichTextString((i + 1) + "");
            cells.setAsActiveCell();
//            cells.setCellStyle(style);
            cells.setCellValue(rowNumber);
            for (short j = 0; j < fields.length; j++) {
                cells = hssfRow.createCell((short) (j));
                fieldName = fields[j];
                value = null;
                textValue = "";
                if (null != fieldName && !"".equals(fieldName)) {
                    // 获得方法名称
                    getMethodName.setLength(0);
                    getMethodName.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
                    getMethod = dtoClass.getMethod(getMethodName.toString(), new Class[] {});
                    // 获得方法对应的值
                    value = getMethod.invoke(dataDto, new Object[] {});
                    if (null != value) {
                        textValue = value.toString();
                    }
                }
                cells.setCellStyle(style);
                cells.setAsActiveCell();
                setColumnColor(cells,errorStyle,fieldName,value,dataDto);
                setCellValue(cells, textValue);
            }
        }
    }
    
    /**
     * 无效单元格背景色
     * @param cells
     * @param errorStyle
     * @param fieldName
     * @param value
     * @throws Exception
     * 
     */
    public static void setColumnColor(HSSFCell cells, HSSFCellStyle errorStyle, String fieldName, Object value,Object dto) throws Exception {
    	//Object dto 为需要处理的Dto对象
    	//String checkInfo = dto.getClass();
    	String checkInfo = "";
    	String[] checkInfoArray = checkInfo.split(",");
    	for(int i=0; i< checkInfoArray.length;i++) {
    		String fieldCName = checkInfoArray[i];
    		if(fieldCName.equals(fieldName)) {
    			cells.setCellStyle(errorStyle);
    		}
    	}
    }
    
    public static void setCellValue(HSSFCell cells, String textValue) {
        // 判断是否是double类型
        if (isDouble(textValue)) {
            int index = textValue.indexOf(".");
            // 判断是否有小数点，如果有，而且小数点前小于12位则double
            if (index != -1 && index < 12) {
                cells.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cells.setCellValue(Double.parseDouble(textValue));
            } else if (textValue.length() < 12) {// 如果没有小数点并且位数小于12则double
                cells.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cells.setCellValue(Double.parseDouble(textValue));
            } else {// 其他为文本
                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                cells.setCellValue(richString);
            }
        } else if (isNumeric(textValue)) {// 判断是否是数字类型
            cells.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cells.setCellValue(Integer.parseInt(textValue));
        } else {// 字符类型
            // 处理中文问题
            // cells.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
            HSSFRichTextString richString = new HSSFRichTextString(textValue);
            cells.setCellValue(richString);
        }
    }
    
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException ne) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ne) {
            return false;
        }
        return true;
    }
    
    /**
	 * 统一修改文件名
	 * 
	 * @param outputFileName
	 * @return String Author Skify Jun 13, 2011
	 */
	public static String changeFileName(String outputFileName) {
		int index = outputFileName.lastIndexOf(".");
		String fileType = "";
		if (index != -1) {
			fileType = outputFileName.substring(index);
		}
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");

		return simpleDateFormat.format(date) + fileType;
	}
	
	
	/**
     * 设置校验规则不通过的行颜色，排除首行头
     * @param fileName	文件名
     * @param successRows	成功的行
     * @param sheetIndex	sheet的序列，从0开始
     * @param sheetName sheet名称
	 * @throws Exception 
     * 
     */
	public static void setErrorRowsColor(Workbook workbook,
			List<Integer> successRows, Integer sheetIndex, String sheetName, String fontName, Integer fontPoins) throws Exception {
		// TODO Auto-generated method stub
		
		if(sheetIndex==null) {
			sheetIndex=DEFAULT_SHEETINDEX;
		}
		if(sheetName!=null && !sheetName.equals("")) {
			workbook.setSheetName(sheetIndex, sheetName);
		}
		if(fontName==null || "".equals(fontName)) {
			fontName=DEFAULT_FONTNAME;
		}
		if(fontPoins==null) {
			fontPoins=DEFAULT_FONTPOINTS;
		}
		Sheet sheet =  workbook.getSheetAt(sheetIndex);
		Row row = null;
		CellStyle cellStyle = getErrorStyle(workbook, fontName, fontPoins.shortValue() );
		for(int i = 1; i <= sheet.getLastRowNum(); i++) {
			if(successRows.contains(i)) {
				continue;
			}
			
			row = sheet.getRow(i);
			for(int j=0; j<row.getLastCellNum(); j++) {
				row.getCell(j).setCellStyle(cellStyle);		
			}
		}
	}
	/**
     * 设置校验规则不通过的行颜色，排除首行头
     * @param fileName	文件名
     * @param falseRows	失败的行
     * @param falseCells	失败的行对应的所有失败的单元列
     * @param sheetIndex	sheet的序列，从0开始
     * @param sheetName sheet名称
	 * @throws Exception 
     * 
     */
	public static void setErrorCellsColor(Workbook workbook,int size,String message,
			List<Integer> falseRows,Map<String,List<Integer>> falseCells, Integer sheetIndex, String sheetName, String fontName, Integer fontPoins) throws Exception {
		// TODO Auto-generated method stub
		
		if(sheetIndex==null) {
			sheetIndex=DEFAULT_SHEETINDEX;
		}
		if(sheetName!=null && !sheetName.equals("")) {
			workbook.setSheetName(sheetIndex, sheetName);
		}
		if(fontName==null || "".equals(fontName)) {
			fontName=DEFAULT_FONTNAME;
		}
		if(fontPoins==null) {
			fontPoins=DEFAULT_FONTPOINTS;
		}
		Sheet sheet =  workbook.getSheetAt(sheetIndex);
		Row row = null;
		CellStyle cellStyle = getErrorStyle(workbook, fontName, fontPoins.shortValue() );
		
		for(int i = 0; i < falseRows.size(); i++) {
			
			row = sheet.getRow(falseRows.get(i)+1);//注意第一行 标题行
			List<Integer> cellList = falseCells.get(falseRows.get(i).toString());
			for(int j=0; j<cellList.size(); j++) {
				if(row.getCell(cellList.get(j))==null){
					Cell cell = row.createCell(cellList.get(j) );//
					cell.setCellValue("");
				}
				row.getCell(cellList.get(j)).setCellStyle(cellStyle);		
			}
		}
		
		//添加一行空行
		Row newrow =  sheet.createRow(size+1);
		Cell cells = newrow.createCell((short) 0);
		cells.setCellValue("");
		
		//添加导出结果信息
		Row newrowMessage = sheet.createRow(size+2);
		Cell cellsMessage = newrowMessage.createCell((short) 0);
		cells.setCellValue(message);
		
	}

	public static void writeToFile(String downFileName, Workbook wb) throws Exception {
		// TODO Auto-generated method stub
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(downFileName)) ;
		wb.write(os);
		os.flush();
		os.close();
	}

	public static void download(String downFileName, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File downFile = new File(downFileName);
		Workbook wb = getWorkbook(downFile);
		
		 //页面直接提示下载信息
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Content-disposition", "attachment;filename=" + downFileName.substring(downFileName.lastIndexOf(File.separator)+1) );  
        OutputStream os = response.getOutputStream();   
        wb.write(os);   
        os.close();
	}
	
	public static void main(String[] args) {
		
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");  
		   Date date = new Date();  
		   String datestr = simpleDateFormat.format(date);  
		   
		   System.out.println("============"+datestr);
		
	}

}
