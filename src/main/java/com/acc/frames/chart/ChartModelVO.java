package com.acc.frames.chart;

import java.io.Serializable;

/**
 * 前台图表数据模板
 * @author TANGCY
 *
 */
public class ChartModelVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 值
	 */
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
