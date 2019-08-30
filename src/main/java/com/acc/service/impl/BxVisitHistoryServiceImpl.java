package com.acc.service.impl;

import com.acc.dao.BxVisitHistoryMapper;
import com.acc.model.BxVisitHistory;
import com.acc.service.IBxVisitHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxVisitHistoryService")
@Transactional
public class BxVisitHistoryServiceImpl implements IBxVisitHistoryService {

	private static Logger _logger = LoggerFactory.getLogger(BxVisitHistoryServiceImpl.class);
	@Autowired
	private BxVisitHistoryMapper bxVisitHistoryMapper;

    @Override
	public void insert(BxVisitHistory bxVisitHistory) throws Exception {
		bxVisitHistoryMapper.insert(bxVisitHistory);
	}
}
