package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.vo.BaseQuery;
import com.acc.vo.Page;

public interface IBaseService<T> {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws SelectException
	 */
	Page<T> selectPage (BaseQuery query) throws SelectException;
	
}
