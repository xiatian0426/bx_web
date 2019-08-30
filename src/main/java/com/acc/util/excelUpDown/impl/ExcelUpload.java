package com.acc.util.excelUpDown.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.acc.util.excelUpDown.IExcelUpload;

public  class ExcelUpload implements IExcelUpload{

	/**
	 * 解析为一个对象集合
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<T> clazz) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<T> list = new ArrayList<T>();
		Field[] fields = clazz.getDeclaredFields();	//获取这个类所有的成员变量
		T instance = null;
		for(Map map:datas){
			//遍历key 
			instance = clazz.newInstance();
			for(Field field : fields){
				Iterator<String> iter = map.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					if(key.equals(field.getName())){
						//构建要执行的方法 
						StringBuffer sb = new StringBuffer();
						sb.append("set");
						String subType = key.substring(0, 1).toUpperCase()
										+ key.substring(1);
						sb.append(subType);
						Method method = clazz.getMethod(sb.toString(),
								new Class[] { field.getType() });
						//方法的执行  需要比较判断 参数的类型
						if(field.getType().getName().endsWith("String")){
							if(map.get(key) instanceof String){
								method.invoke(instance, map.get(key));
							}else if(map.get(key) instanceof Date){
								method.invoke(instance, sdf.format(map.get(key)));
							}
						}else if(field.getType().getName().endsWith("int")){
							method.invoke(instance, (Integer)map.get(key));
						}else if(field.getType().getName().endsWith("Date")){
							method.invoke(instance, map.get(key));
						}else if(field.getType().getName().endsWith("Double")){
							method.invoke(instance,map.get(key));
						}
					}
				}
			}
			list.add(instance);
		}
		return list;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public <T> List<T> fetchDataToObject(List<Map<String, Object>> datas,
			Class<T> clazz, String[] fieldList) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<T> list = new ArrayList<T>();
		Field[] fields = clazz.getDeclaredFields();
		T instance = null;
		for(Map map:datas){
			//遍历key 
			instance = clazz.newInstance();
			for(Field field : fields){
				for(String str: fieldList){
					if(str.equals(field.getName())){
						//构建要执行的方法 
						StringBuffer sb = new StringBuffer();
						sb.append("set");
						String subType = str.substring(0, 1).toUpperCase()
										+ str.substring(1);
						sb.append(subType);
						Method method = clazz.getMethod(sb.toString(),
								new Class[] { field.getType() });
						//方法的执行  需要比较判断 参数的类型
						if(field.getType().getName().endsWith("String")){
							if(map.get(str) instanceof String){
								method.invoke(instance, map.get(str));
							}else if(map.get(str) instanceof Date){
								method.invoke(instance, sdf.format(map.get(str)));
							}
						}else if(field.getType().getName().endsWith("int")){
							method.invoke(instance, (Integer)map.get(str));
						}else if(field.getType().getName().endsWith("Date")){
							method.invoke(instance, map.get(str));
						}else if(field.getType().getName().endsWith("double")){
							method.invoke(instance,map.get(str));
						}
					}
				}
			}
			list.add(instance);
		}
		return list;
	}
	/**
	 * 得到一个 WorkBook对象
	 */
	@Override
	public  Workbook readExcel(InputStream fileStream) {
		Workbook wb=null;
        try {
        	wb = WorkbookFactory.create(fileStream);
        } catch (Exception e) {
	           try {
	               if(null!= fileStream){
	            	   fileStream.close();
	               }
	           } catch (IOException e1) {
	               e1.printStackTrace();
	           }
	           e.printStackTrace();
       }
        return wb;
	}
	@Override
	public List<Map<String, Object>> transIntoMaps(Sheet sheet, String[] keys) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		  for(int i=1; i<=sheet.getLastRowNum(); i++){
			  Map<String, Object> map = new HashMap<String, Object>();
			  Row row = sheet.getRow(i);
			  //遍历每一列
			  for(int j=0; j<row.getLastCellNum(); j++){
				  //因为每个表格的数据类型不一样, 所以需要在这里判断单元格的数据类型, 然后自己动态的获取单元格的数据
				  //创建对应的对象, 然后在进行对象的封装
				  Cell cell = row.getCell(j);
				  if (null != cell) {  
                      switch (cell.getCellType()) {  
                      case Cell.CELL_TYPE_NUMERIC: 	// 数字  
                    	  // 如果是Date类型则，取得该Cell的Date值
                    	  if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    		  map.put(keys[j], cell.getDateCellValue()); 
                    	  }else {// 如果是纯数字
                    			map.put(keys[j], cell.getNumericCellValue()); 
                    	  }
                          break;  
                      case Cell.CELL_TYPE_STRING: 	// 字符串  
                    	  map.put(keys[j], cell.getStringCellValue());
                          break;  
                      case Cell.CELL_TYPE_BOOLEAN: 	// Boolean  
                    	  map.put(keys[j], cell.getBooleanCellValue());
                          break;  
                      case Cell.CELL_TYPE_FORMULA: 	// 公式  
                    	  map.put(keys[j], cell.getCellFormula());
                          break;  
                      case Cell.CELL_TYPE_BLANK: 	// 空值  
                    	  map.put(keys[j], "");
                          break;  
                      case Cell.CELL_TYPE_ERROR: 	// 故障  
                    	  map.put(keys[j],"");
                          break;
                      default:  					//未知类型
                    	  map.put(keys[j], cell.getCellComment());
                          break;  
                      }
                  } else {  
                	  map.put(keys[j],null);  
                  }  
			  }
			  datas.add(map);
		  }
		return datas;
	}
	@Override
	public List<Map<String, Object>> transIntoMaps(Sheet sheet, List<String> keyList) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		  for(int i=1; i<=sheet.getLastRowNum(); i++){
			  Map<String, Object> map = new HashMap<String, Object>();
			  Row row = sheet.getRow(i);
			  //遍历每一列
			  for(int j=0; j<row.getLastCellNum(); j++){
				  //因为每个表格的数据类型不一样, 所以需要在这里判断单元格的数据类型, 然后自己动态的获取单元格的数据
				  //创建对应的对象, 然后在进行对象的封装
				  Cell cell = row.getCell(j);
				  if (null != cell) {  
                      switch (cell.getCellType()) {  
                      case Cell.CELL_TYPE_NUMERIC: 	// 数字  
                    	  // 如果是Date类型则，取得该Cell的Date值
                    	  if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    		  map.put(keyList.get(j), cell.getDateCellValue()); 
                    	  }else {// 如果是纯数字
                    			map.put(keyList.get(j), cell.getNumericCellValue()); 
                    	  }
                          break;  
                      case Cell.CELL_TYPE_STRING: 	// 字符串  
                    	  map.put(keyList.get(j), cell.getStringCellValue());
                          break;  
                      case Cell.CELL_TYPE_BOOLEAN: 	// Boolean  
                    	  map.put(keyList.get(j), cell.getBooleanCellValue());
                          break;  
                      case Cell.CELL_TYPE_FORMULA: 	// 公式  
                    	  map.put(keyList.get(j), cell.getCellFormula());
                          break;  
                      case Cell.CELL_TYPE_BLANK: 	// 空值  
                    	  map.put(keyList.get(j), "");
                          break;  
                      case Cell.CELL_TYPE_ERROR: 	// 故障  
                    	  map.put(keyList.get(j),"");
                          break;
                      default:  					//未知类型
                    	  map.put(keyList.get(j), cell.getCellComment());
                          break;  
                      }
                  } else {  
                	  map.put(keyList.get(j),null);  
                  }  
			  }
			  datas.add(map);
		  }
		return datas;
	}
	
	/**
	 * 将3-十二月-2014格式的时间转换为2014-12-3
	 * @param date
	 * @return
	 */
	public static String DateTransfer(String dateStr) {
		// TODO Auto-generated method stub
		String[] date=dateStr.split("-");
		if(date[1].endsWith("月")){
			date[1]=monthChange(date[1]);
		}
		return date[2]+"-"+date[1]+"-"+date[0];
	}
	/*
	 * 月的转换
	 */
	private static String monthChange(String month){
		String result=null;
		if(month.equals("十二月"))
			result="12";
		if(month.equals("十一月"))
			result="11";
		if(month.equals("十月"))
			result="10";
		if(month.equals("九月"))
			result="9";
		if(month.equals("八月"))
			result="8";
		if(month.equals("七月"))
			result="7";
		if(month.equals("六月"))
			result="6";
		if(month.equals("五月"))
			result="5";
		if(month.equals("四月"))
			result="4";
		if(month.equals("三月"))
			result="3";
		if(month.equals("二月"))
			result="2";
		if(month.equals("一月"))
			result="1";
		return result;
	}
	@Override
	public File tranferFile(MultipartFile file) {
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation(); 
		return f;
	}

}
