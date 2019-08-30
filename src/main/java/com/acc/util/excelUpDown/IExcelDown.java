package com.acc.util.excelUpDown;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel表格的下载
 * 
 * @author 唐朝阳
 * 
 */

public interface IExcelDown {
	/**
	 * 生成工作对象
	 */
	public HSSFWorkbook createWorkbook();

	/**
	 * 生成指定名称的Sheet 对象
	 * 
	 * @param workbook
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName);

	/**
	 * 填充表头
	 * 
	 * @param sheetHead
	 */
	public void setExcelHead(HSSFSheet sheet, String[] sheetHead);

	/**
	 * 设置response的响应头
	 * 
	 * @param response
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public OutputStream setResponse(HttpServletResponse response,
			String filename) throws IOException;

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public String encodeFilename(String filename, HttpServletRequest request);

}
