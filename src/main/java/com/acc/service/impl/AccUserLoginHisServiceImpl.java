package com.acc.service.impl;

import com.acc.dao.AccUserLoginHisMapper;
import com.acc.model.AccUserLoginHis;
import com.acc.service.IAccUserLoginHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accUserLoginHisService")
@Transactional
public class AccUserLoginHisServiceImpl extends BaseServiceImpl<AccUserLoginHis> implements IAccUserLoginHisService {

	@Autowired
	private AccUserLoginHisMapper accUserLoginHisMapper;
	
	@Override
	public void insert(AccUserLoginHis accUserLoginHis) throws Exception {
		accUserLoginHisMapper.insert(accUserLoginHis);
		
	}

}
