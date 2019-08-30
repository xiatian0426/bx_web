package com.acc.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 获取异常信息
 * @author wenhl
 *
 */
public class ExceptionUtil {
	public static String getMsg(Exception e){
		   StringWriter sw = new StringWriter();
		   e.printStackTrace(new PrintWriter(sw, true));
		   return sw.toString();
	}
}
