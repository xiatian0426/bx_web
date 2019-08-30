package com.acc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 */
public class CalendarUtil {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 增加指定天数
	 * @param date
	 * @param count
	 * @return
	 */
	public static String addDay(String date,int count) {
		try {
			Calendar calendar = Calendar.getInstance();
			Date stringToDate = stringToDate(date, DATE_FORMAT);
			calendar.setTime(stringToDate);
			calendar.add(Calendar.DATE, count);// 把日期往后增加一天.整数往后推,负数往前移动
			Date dated = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			date = dateToString(dated, DATE_FORMAT);
			return date;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 将字符串转化为日期。 字符串格式("YYYY-MM-DD")。
	 * 例如："2012-07-01"或者"2012-7-1"或者"2012-7-01"或者"2012-07-1"是等价的。
	 */
	public static Date stringToDate(String str, String pattern) {
		Date dateTime = null;
		try {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			dateTime = formater.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	/**
	 * 将日期转化为字符串
	 */
	public static String dateToString(Date date, String pattern) {
		String str = "";
		try {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			str = formater.format(date);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 获取当前时间
	 * @param format 日期格式化
	 * @return
	 * @throws Exception
	 * @author TANGCY
	 */
	public static String getCurrentDate(String format){
		Date current = new Date();
		String currentStr = dateToString(current, format);
		return currentStr;
	}
	public static String getCurrentDate() {
		return getCurrentDate(DATE_FORMAT);
	}
	
	/**
	 * 判断是否时间变量在时间定量之后
	 * @param dateDest 时间变量
	 * @param dateObj 时间定量
	 * @return
	 * @author TANGCY
	 */
	public static boolean isAfter(String dateDest, String dateObj) {
		Date dest = CalendarUtil.stringToDate(dateDest, DATE_FORMAT);
		Date obj = CalendarUtil.stringToDate(dateObj, DATE_FORMAT);
		return dest.after(obj);
	}
	public static boolean isBefore(String dateDest, String dateObj) {
		return !isAfter(dateDest, dateObj);
	}
	
	/**
	 * 求指点日期是周几
	 */
	public static int dayForWeek(String pTime) throws Exception {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar c = Calendar.getInstance();
	  c.setTime(format.parse(pTime));
	  int dayForWeek = 0;
	  if(c.get(Calendar.DAY_OF_WEEK) == 1){
		  dayForWeek = 7;
	  }else{
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
	  }
	  return dayForWeek;
	}
	public static void main(String[] args) {
		String date = getCurrentDate();
		String startDate = CalendarUtil.addDay(date, -(90+1));
		String endDate = CalendarUtil.addDay(date, -1);
		System.out.println("===>startDate=" + startDate);
		System.out.println("===>endDate=" + endDate);
	}
}
