package com.acc.util.excelUpDown;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * Exel文档的上传(不会保存)
 * @author 唐朝阳
 *
 */
public interface IExcelUpload {

	/**
	 ** 传入一个文件输入流(必须是Excel的输入流)
	 */
	Workbook readExcel(InputStream fileStream);

	/**
	 * 解析指定的属性
	 * @param <T>
	 * @param workbook
	 * @param sheetName
	 * @param clazz
	 * @return
	 */
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<T> clazz, String[] fiels) throws Exception;

	/**
	 * 解析为指定的对象的列表
	 * @param sheet
	 * @param keys
	 * @return
	 */
	public List<Map<String, Object>> transIntoMaps(Sheet sheet, String[] keys);
	/**
	 * 解析为指定的对象的列表
	 * @param sheet
	 * @param keys--Excel文件表头
	 * @return
	 */
	public List<Map<String, Object>> transIntoMaps(Sheet sheet, List<String> keyList);
	
	/**
	 * MultipartFile类型的文件流转换为File 文件流
	 * @param file MultipartFile 类型的文件
	 * @return File类型的文件
	 */
	public File tranferFile(MultipartFile file);
	/**
	 * 映射全部
	 * @param <T>
	 * @param datas
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<T> clazz) throws Exception ;
}
