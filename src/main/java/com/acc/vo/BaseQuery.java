package com.acc.vo;

import org.apache.commons.lang3.StringUtils;

public class BaseQuery {

	/**
	 * 默认每条显示的条数
	 */
	private int pageSize;
	private String pageIndex;
	public int getPageSize() {
		if (pageSize <=0) pageSize = 20;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageIndex() {
		try{
			if (Integer.parseInt(pageIndex) <=0) pageIndex = "1";
		}catch(Exception ex) {
			pageIndex = "1";
		}
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		try{
			if (StringUtils.isEmpty(pageIndex) && Integer.parseInt(pageIndex) <=0) {
				this.pageIndex = "1";
			} else {
				this.pageIndex = pageIndex;
			}
		}catch(Exception ex) {
			pageIndex = "1";
		}
	}
	
	public int getSkip() {
		return (Integer.parseInt(getPageIndex())-1)*pageSize;
	}
}
