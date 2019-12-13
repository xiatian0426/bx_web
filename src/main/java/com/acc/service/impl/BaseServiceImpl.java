package com.acc.service.impl;

import com.acc.exception.SelectException;
import com.acc.service.IBaseService;
import com.acc.vo.BaseQuery;
import com.acc.vo.Page;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseServiceImpl<T> implements IBaseService<T> {
	
	@Resource
	private SqlSession session;
	private final String path = "com.acc.dao.";
	
	private String getMethodPath(String methodType){
		Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return path + clazz.getSimpleName() + "Mapper." + methodType;
	}
	@Override
	public Page<T> selectPage(BaseQuery query) throws SelectException {
		//获取当前条件下的总条数
		int pageCount = ((Long)session.selectOne(getMethodPath("pageCount"), query)).intValue();
		Page<T> page = new Page<T>(query.getPageSize(), pageCount, Integer.parseInt(query.getPageIndex()));
		//获取当前条件下的结果
		@SuppressWarnings("unchecked")
		List<T> selectList = (List<T>)session.selectList(getMethodPath("findPage"), query);
		page.setResult(selectList);
		return page;
	}
}
