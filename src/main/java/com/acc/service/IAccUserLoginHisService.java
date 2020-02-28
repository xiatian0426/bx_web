package com.acc.service;

import com.acc.model.AccUserLoginHis;

public interface IAccUserLoginHisService extends IBaseService<AccUserLoginHis> {
	
	void insert (AccUserLoginHis accUserLoginHis) throws Exception;
	
}
