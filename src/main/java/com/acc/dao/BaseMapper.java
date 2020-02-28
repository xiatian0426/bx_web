package com.acc.dao;

import com.acc.exception.InsertException;
import com.acc.exception.SelectException;
import com.acc.vo.BaseQuery;

import java.util.List;

public interface BaseMapper<T> {

	/**
	 * 保存对象
	 * @param t
	 * @author TANGCY
	 * @since 2016年10月17日
	 */
	void insert(T t) throws InsertException;
	
	/**
	 * 查询当前查询条件下的总页数
	 * @param query
	 * @return
	 * @throws SelectException
	 */
	Long pageCount(BaseQuery query) throws SelectException;
	
	/**
	 * 查询当前条件下的结果
	 * @param query
	 * @return
	 * @throws SelectException
	 */
	List<T> findPage(BaseQuery query) throws SelectException;
}
