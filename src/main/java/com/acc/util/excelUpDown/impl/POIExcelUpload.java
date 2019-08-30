package com.acc.util.excelUpDown.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.acc.util.excelUpDown.IExcelUpload;

public class POIExcelUpload {

	private IExcelUpload excelUpload;
	
	private List<FileItem> list;
	
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public List<FileItem> getList() {
		return list;
	}
	public void setList(List<FileItem> list) {
		this.list = list;
	}
	public POIExcelUpload(HttpServletRequest request) {
		list = getAllFromRequest(request);
		excelUpload = new ExcelUpload();
	}
	public POIExcelUpload() {
		excelUpload = new ExcelUpload();
	}
	/**
	 * 解析 字节流为一个List集合
	 * 
	 * @param fileStream
	 * @param keys
	 * @return
	 */
	public List<Map<String, Object>> resolveFile(InputStream fileStream,
			String[] keys) {
		Workbook workbook = excelUpload.readExcel(fileStream);
		Sheet sheet = workbook.getSheetAt(0);
		return excelUpload.transIntoMaps(sheet, keys);
	}
	/**
	 * 解析一个List<Map<String, Objec>> 为一个List 的对象
	 * @param <T>
	 * @param datas
	 * @param clazz
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<?> clazz, String[] keys) throws Exception {
		return (List<T>) excelUpload.fetchDataToObject(datas, clazz, keys);
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<?> clazz) throws Exception {
		return (List<T>) excelUpload.fetchDataToObject(datas, clazz);
	}
	/**
	 * 根据表单中的名称来取对应的 文件流
	 * @param request
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public InputStream getStreamFormRequest(String parameter) throws Exception {
		InputStream stream = null;
		for (FileItem item : list) {
		    String fieldName = item.getFieldName();
		    if(parameter.equals(fieldName)){
		    	stream = item.getInputStream();
		    }
		}
		return stream;
	}
	/**
	 * 从 文件表单中获取参数值
	 * @param request
	 * @return
	 * @throws FileUploadException 
	 */
	public String getParameterFromRequest(String parameterName) throws FileUploadException{
		String parameterValue = null;
		for (FileItem item : list) {
		    String fieldName = item.getFieldName();
		    if(parameterName.equals(fieldName)){
		    	parameterValue = item.getString();
		    }
		}
		return parameterValue;
	}
	/**
	 * 解析request
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileItem> getAllFromRequest(HttpServletRequest request){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			return list = null;
		}
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 验证指定键的值 是否 满足
	 * @param key
	 * @param value
	 * @param map
	 * @return
	 */
	public boolean validateVlue(String key, Object value, List<Map<String, Object>> datas){
		boolean flag = true;
		for(Map<String, Object> map:datas){
			if(value.equals(map.get(key))){
				flag = false;
				return flag;
			}
		}
		return flag;
	}
}
