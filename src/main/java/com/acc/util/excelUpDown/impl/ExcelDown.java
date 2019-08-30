package com.acc.util.excelUpDown.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.acc.util.excelUpDown.IExcelDown;

public class ExcelDown implements IExcelDown{
	
	@Override
	public HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName) {
		HSSFSheet sheet = null;
		String replace = sheetName.replace("/", "");
		sheet = workbook.createSheet(replace);
		return sheet;
	}
	@Override
	public HSSFWorkbook createWorkbook() {
		return new HSSFWorkbook();
	}
	@Override
	public String encodeFilename(String filename, HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
		        String newFileName = URLEncoder.encode(filename, "UTF-8");
		        newFileName = StringUtils.replace(newFileName, "+", "%20");
		        if (newFileName.length() > 150) {
		          newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
		          newFileName = StringUtils.replace(newFileName, " ", "%20");
		        }
	        	return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return new String(filename.getBytes(), "UTF-8");
			return filename;
	    } catch (Exception e ) {
	      return filename;
	    }
	}
	@Override
	public void setExcelHead(HSSFSheet sheet, String[] sheetHead) {
		HSSFRow row = sheet.createRow(0);
		for(int i = 0; i <sheetHead.length;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(sheetHead[i]);
		}
		
	}
	@Override
	public OutputStream setResponse(HttpServletResponse response,
			String filename) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		//response.setHeader("Content-disposition", "attachment;filename=" + StringUtils.replace(new String(filename.getBytes("gb2312"),"ISO8859-1"), " ", "") + ".xls");
		response.setHeader("Content-disposition", "attachment;filename=" + encodingFileName(filename) + ".xls");
		return response.getOutputStream();
	}

	//文件名中有空格的时候,
	public static String encodingFileName(String fileName) throws UnsupportedEncodingException {
		fileName = new String(fileName.getBytes("GB2312"),"ISO8859-1");
		fileName = StringUtils.replace(fileName, " ", "");
		return fileName;
	}
}
