package com.acc.util.excelUpDown.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.acc.util.excelUpDown.IExcelDown;

public class POIExcelDown {

	private IExcelDown excelDown;
	private HSSFWorkbook workbook;

	public POIExcelDown() {
		excelDown = new ExcelDown();
	}

	public Workbook createWorkBook(String sheetName) {
		workbook = excelDown.createWorkbook();
		excelDown.createSheet(workbook, sheetName);
		return workbook;
	}

	@SuppressWarnings("deprecation")
	public HSSFWorkbook createBySheetWorkBook(String sheetName) {
		if(ObjectUtils.equals(null, workbook)){
			workbook = excelDown.createWorkbook();
		}
		excelDown.createSheet(workbook, sheetName);
		return workbook;
	}
	
	public Workbook createWorkBookContentManySheet(List<String> list,List<String> head ,List<String> tableNameList) {
		workbook = excelDown.createWorkbook();
		//设置字体格式1
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font=workbook.createFont(); 
		font.setColor(HSSFColor.RED.index);//字体颜色
		font.setFontHeightInPoints((short)15); //字体大小
		font.setFontName("宋体");//字体样式
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); //粗体
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		//设置字体格式2
		HSSFCellStyle cellStyle1 = workbook.createCellStyle();
		HSSFFont font1=workbook.createFont(); 
		font1.setColor(HSSFColor.BLUE.index);//字体颜色
		font1.setFontHeightInPoints((short)10); //字体大小
		font1.setFontName("宋体");//字体样式
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD); //粗体
		cellStyle1.setFont(font1);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		//一级区域表头没有父级
		List<String> head0 = new ArrayList<String>();
		for(String headNa : head){
			head0.add(headNa);
		}
		if(list != null && list.size()>0){
			for(int j=0; j<list.size() ; j++){
				int count = 0 ;
				int count1 = 1 ;
				if(j == 0){
					head.remove(0);
					count = 5;
					count1 = 0 ;
				}else{
					count =6;
					head = head0;
				}
				//设置工作空间名
				String sheetName = list.get(j);
				HSSFSheet sheet = excelDown.createSheet(workbook, sheetName);
				//设置表格表头名
				String tableName = tableNameList.get(j);
				HSSFRow row0 = sheet.createRow(0);
				//合并表头第一行并赋值
				for(int i=0;i<count;i++){
					row0.createCell(i);
				}
				//合并单元格
				CellRangeAddress range = new CellRangeAddress(0, 0, 0, count);
				HSSFCell cell0 = row0.getCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(tableName);
				sheet.addMergedRegion(range);
				//设置表头
				HSSFRow row1 = sheet.createRow(1);
				for (int i = 0; i < head.size(); i++) {
					Cell cell = row1.createCell(i);
					cell.setCellValue(head.get(i));
					cell.setCellStyle(cellStyle1);
				}
				//状态列表
				List<String> stateList = new ArrayList<String>();
				stateList.add("启用");
				stateList.add("禁用");
				sheet = setHSSFValidation(sheet, stateList , 1, 5001, count1, count1);
			}
		}
		return workbook;
	}
	
	public Workbook setHead(String[] head) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < head.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(head[i]);
		}
		return workbook;
	}
	public Workbook setHead(List<String> head) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < head.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(head.get(i));
		}
		return workbook;
	}

	// rowNum celNum 
	public Workbook setRowCellVall(String cellVal, int rowNum, int celNum) {
		if (rowNum < 1 || celNum < 1) {
			workbook = excelDown.createWorkbook();
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.createRow(rowNum - 1);
		HSSFCell cell = row.createCell(celNum - 1);
		cell.setCellValue(cellVal);
		return workbook;
	}
	/**
	 * 生成样式
	 * @param workbook
	 * @return
	 */
	public CellStyle createStyle( Workbook workbook){
		// 生成一个样式  
		CellStyle style = workbook.createCellStyle();  
		// 设置这些样式  
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体  
        Font font = workbook.createFont();  
        font.setFontName("黑体");
        font.setColor(HSSFColor.VIOLET.index); //字体颜色
        font.setFontHeightInPoints((short) 12);  //字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //加粗
        // 把字体应用到当前的样式  
        style.setFont(font);
		return style;
		
	} 
	/**
	 * 填充集合
	 * @param workbook
	 * @param startRow
	 * @param list 数据集合
	 * @return
	 */
	public  Workbook exportExcel(int startRow, List<Map<String, Object>> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		//生成样式
	     CellStyle style = createStyle(workbook);
		//填充行
		for(int index=startRow; index<list.size()+startRow; index++){
			Map<String, Object> map = list.get(index-startRow);
			HSSFRow row = sheet.createRow(index);
			//填充列
			int col = 0;
			for(String key: map.keySet()){
				HSSFCell cell = row.createCell(col);
				cell.setCellStyle(style);
				cell.setCellValue(map.get(key).toString());
				col++;
			}
		}
		return workbook;
	}
	/**
	 * 填充数据,同时指定错误的信息
	 */
	public void setValueAndError(int rowNum,int colNum, String value, boolean errStatu){
		HSSFSheet sheet = workbook.getSheetAt(0);
		//生成样式
	     CellStyle style = createStyle(workbook);
	     HSSFRow row = sheet.getRow(rowNum);
	     if(row == null){
	    	 row = sheet.createRow(rowNum);
	     }
	     HSSFCell cell = row.createCell(colNum);
	     cell.setCellValue(value);
	     if(errStatu) cell.setCellStyle(style);
	}
	
	/**
	 * 获取表对象
	 * @return
	 */
	public Workbook getWorkbook(){
		return workbook;
	}
	/**
	 * Excel表格的填充
	 * @param startRow
	 * @param list
	 * @return
	 */
	
	public  Workbook exportExcelWithList(int startRow, List<List<String>> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		//填充行
	    for(int rowNum=0; rowNum<list.size(); rowNum++){
	    	List<String> col = list.get(rowNum);
	    	HSSFRow row = sheet.createRow(rowNum + startRow);
	    	for(int colNum=0; colNum<col.size(); colNum++){
	    		HSSFCell cell = row.createCell(colNum);
	    		//判断数字,如果是数字强转成double类型放入excel,这样导出的数字不会为文本 2016/1/8
    			try{
    				cell.setCellValue(Double.parseDouble(col.get(colNum)));
    			}catch(Exception e){
    				cell.setCellValue(col.get(colNum));
    			}
	    	}
	    }
		return workbook;
	}
	
	
	/**
	 * Excel表格的填充
	 * @param startRow
	 * @param list
	 * @return
	 */
	public Workbook exportExcelWithList(int startRow,int sheetIndex,List<List<String>> list){
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		//填充行
	    for(int rowNum=0; rowNum<list.size(); rowNum++){
	    	List<String> col = list.get(rowNum);
	    	HSSFRow row = sheet.createRow(rowNum + startRow);
	    	for(int colNum=0; colNum<col.size(); colNum++){
	    		HSSFCell cell = row.createCell(colNum);
	    		//判断数字,如果是数字强转成double类型放入excel,这样导出的数字不会为文本 2016/1/8
    			try{
    				cell.setCellValue(Double.parseDouble(col.get(colNum)));
    			}catch(Exception e){
    				cell.setCellValue(col.get(colNum));
    			}
	    	}
	    }
		return workbook;
	}
	
	/**
	 * 为指定列设置下拉框
	 * @param colNum
	 * @param list
	 * @return
	 */
	public Workbook setSelectOption(int colNum, List<String> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet = setHSSFValidation(sheet, list, 1, 5001, colNum, colNum);
		return workbook;
	}
	private HSSFSheet setHSSFValidation(HSSFSheet sheet, List<String> list, int firstRow,
			int endRow, int firstCol, int endCol) {
		String[] sb = new String[list.size()];
		for(int count=0;count<list.size();count++){
			sb[count] = list.get(count);
		}
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(sb);  
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
		HSSFDataValidation validationList = new HSSFDataValidation(regions, constraint);  
		sheet.addValidationData(validationList);
        return sheet;
	}
	public Workbook setCellString(int colNum){
		HSSFSheet sheet = workbook.getSheetAt(0);
//		for(int i=1;i<5001;i++){
//			HSSFRow row = sheet.createRow(i);
//			HSSFCell cell = row.createCell(colNum);
//			cell
//			
//		}

		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		sheet.setDefaultColumnStyle(colNum, style);
		return workbook;
	}
	
	/**
	 * 填充个表头, 合并表头
	 * @param mergeSize
	 * @param list
	 * @return
	 */
	public Workbook setHead(int mergeSize, List<String> list) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.createRow(0);
		for(int i=0;i<list.size();i++){
			if("".equals(list.get(i))){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue("");
				continue;
			}
			int startCol =  1;
			int endCol = mergeSize;
			if (i > 1) {
				startCol = mergeSize * (i - 1) + 1;
				endCol =  mergeSize * (i - 1) + mergeSize;
			}
			mergeCellFullValue(row, startCol, endCol, list.get(i), sheet);
		}
		return workbook;
	}
	
	
	/**
	 * 填充个表头, 合并表头
	 * @param mergeSize
	 * @param list
	 * @return
	 */
	public Workbook setHead(int mergeSize, List<String> list,int sheetIndex) {
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		HSSFRow row = sheet.createRow(0);
		for(int i=0;i<list.size();i++){
			if("".equals(list.get(i))){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue("");
				continue;
			}
			int startCol =  1;
			int endCol = mergeSize;
			if (i > 1) {
				startCol = mergeSize * (i - 1) + 1;
				endCol =  mergeSize * (i - 1) + mergeSize;
			}
			mergeCellFullValue(row, startCol, endCol, list.get(i), sheet);
		}
		return workbook;
	}
	/**
	 * 合并指定的列,并赋值
	 * @param rowNum
	 * @param startCol
	 * @param endCol
	 * @param cellValue
	 * @return
	 */
	public Workbook mergeCellFullValue(HSSFRow row, int startCol, int endCol, String cellValue, HSSFSheet sheet){
		for(int i=startCol;i<=endCol;i++){
			row.createCell(i);
		}
		CellRangeAddress range = new CellRangeAddress(0, 0, startCol, endCol);
		sheet.addMergedRegion(range);
		HSSFCell cell = row.getCell(startCol);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); 
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
		HSSFFont font = workbook.createFont();    
		//font.setFontName("仿宋_GB2312");    
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);    
		cellStyle.setFont(font);//选择需要用到的字体格式    
		
		cell.setCellStyle(cellStyle);
		cell.setCellValue(cellValue);
		return workbook;
	}
	/**
	 * 填充表格 ,按列插入
	 * @param startCol
	 * @param list
	 * @return
	 */
	public Workbook fullExcelWithCol(int startCol, List<List<String>> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		int colSize = list.size();
		for(int colNum=0; colNum<colSize; colNum++){
			List<String> colData = list.get(colNum);
			int rowSize = colData.size();
			for(int rowNum=0; rowNum<rowSize; rowNum++){
				setCellValue(rowNum, colNum+startCol, colData.get(rowNum), null, sheet);
			}
		}
		return workbook;
	}
	/**
	 * 按列填充, 合并第一行 两个值相同的列
	 * @param startCol
	 * @param list
	 * @return
	 */
	public Workbook fullExcelColMergeHead(int startCol, List<List<String>> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		int colSize = list.size();
		for(int colNum=0; colNum<colSize; colNum++){
			List<String> colData = list.get(colNum);
			int rowSize = colData.size();
			for(int rowNum=0; rowNum<rowSize; rowNum++){
				setCellValue(rowNum, colNum+startCol, colData.get(rowNum), null, sheet);
				if(rowNum == 0&& colNum > 0){
					if(getCellValue(rowNum, colNum+startCol-1, sheet).equals(getCellValue(rowNum, colNum+startCol, sheet))){
						/**  
					     * 合并单元格  
					     *    第一个参数：第一个单元格的行数（从0开始）  
					     *    第二个参数：第二个单元格的行数（从0开始）  
					     *    第三个参数：第一个单元格的列数（从0开始）  
					     *    第四个参数：第二个单元格的列数（从0开始）  
					     * CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);   
					     */
						setCellValue(rowNum, colNum+startCol, null, null, sheet);
						setCellValue(rowNum, colNum+startCol, colData.get(rowNum), null, sheet);
						CellRangeAddress range = new CellRangeAddress(rowNum, rowNum, colNum+startCol-1, colNum+startCol);
						sheet.addMergedRegion(range);
					}
				}
			}
		}
		return workbook;
	}
	public Workbook setErrorCellStyle(List<String> errorRowCol) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		CellStyle createStyle = createStyle(workbook);
		for(String str:errorRowCol){
			String[] split = str.split("_");
			int rowNum = Integer.parseInt(split[0]);
			int colNum = Integer.parseInt(split[1]);
			HSSFRow hssfRow = sheet.getRow(rowNum);
			HSSFCell hssfCell = hssfRow.getCell(colNum);
			hssfCell.setCellStyle(createStyle);
		}
		return workbook;
	}
	/**
	 * 根据行列号填充数据,并制定单元格的样式
	 * @param rowNum
	 * @param colNum
	 * @param value
	 * @param cellStyle
	 * @param HSSFSheet
	 */
	public void setCellValue(int rowNum, int colNum, String value, CellStyle cellStyle, HSSFSheet sheet){
		HSSFRow row = null;
		if(sheet.getRow(rowNum) != null){
			row = sheet.getRow(rowNum);
		}else{
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.createCell(colNum);
		cell.setCellValue(value);
		if(cellStyle != null){
			cell.setCellStyle(cellStyle);
		}
	}
	/**
	 * 根据指定的行号和列号 取单元格的值
	 * @param rowNum
	 * @param colNum
	 * @param HSSFSheet
	 * @return
	 */
	public String getCellValue(int rowNum, int colNum, HSSFSheet sheet){
		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = row.getCell(colNum);
		return cell.getStringCellValue();
	}
	/**
	 * 得到浏览器的信息 加密文件名,使不同浏览器能正确解析 文件名称,不出现乱码
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @return 返回一个文件输出流
	 * @throws IOException
	 */
	public OutputStream getDownStream(HttpServletResponse response, String fileName) throws IOException {
		return excelDown.setResponse(response, fileName);
	}
	
	/**
	 * 将 表格文件加载到输出流中
	 * 
	 * @param workbook
	 * @param outputStream
	 * @throws IOException 
	 */
	public void sendSream(Workbook workbook,
			HttpServletResponse response, String filename) throws IOException {
		setCellAutoSize();
		OutputStream outputStream = getDownStream(response, filename);
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	//导出前 指定列宽
	public void setCellAutoSize(){
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		row = sheet.getRow(0);
		if (row == null) {
		     row = sheet.createRow(0);
		}
		int numberOfCells = row.getPhysicalNumberOfCells();
		for(int i=0; i<numberOfCells; i++){
			sheet.setColumnWidth(i, 3766);
			sheet.setDefaultColumnStyle(i, style);
		}
	}
	
	
	
	/**
	 * 将 表格文件加载到输出流中
	 * 
	 * @param workbook
	 * @param outputStream
	 * @throws IOException 
	 */
	public void sendSreamResponse(Workbook workbook,
			HttpServletResponse response, String filename) throws IOException {
		OutputStream outputStream = getDownStream(response, filename);
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	//导出前 指定列宽
	public void setCellAutoSize(int sheetIndex){
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(0);
		row = sheet.getRow(0);
		if (row == null) {
		     row = sheet.createRow(0);
		}
		int numberOfCells = row.getPhysicalNumberOfCells();
		for(int i=0; i<numberOfCells; i++){
			sheet.setColumnWidth(i, 3766);
			sheet.setDefaultColumnStyle(i, style);
		}
	}
	
	/**
	 * 设置单元格的错误信息 
	 * @param list
	 * @return
	 */
	public Workbook setErrorCellStyleAll(List<String> list){
		HSSFSheet sheet = workbook.getSheetAt(0);
		CellStyle createStyle = createStyle(workbook);
		if(list != null && list.size() > 0){
			for(int j=0;j<list.size();j++){
				String errorStr = (String)list.get(j);
				String[] errorArray = errorStr.split("_");
				int rowNum = Integer.parseInt(errorArray[0]);
				int colNum = Integer.parseInt(errorArray[1]);
				HSSFRow hssfRow = sheet.getRow(rowNum);
				HSSFCell hssfCell = hssfRow.getCell(colNum);
				if(hssfCell == null){
					hssfCell = hssfRow.createCell(colNum);
				}
				hssfCell.setCellStyle(createStyle);
			}
		}			
		return workbook;
	}
	/**
	 * 将集合转换为数组
	 * @param list
	 * @return
	 */
	public static String[] listToString(List<String> list){
		String[] head = new String[list.size()];
		for(int i=0;i<list.size();i++){
			head[i] = list.get(i);
		}
		return head;
	}
}
