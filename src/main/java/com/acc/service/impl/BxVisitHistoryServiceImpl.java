package com.acc.service.impl;

import com.acc.dao.BxThumbUpMapper;
import com.acc.dao.BxVisitHistoryMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxThumbUp;
import com.acc.model.BxVisitHistory;
import com.acc.service.IBxVisitHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxVisitHistoryService")
@Transactional
public class BxVisitHistoryServiceImpl extends BaseServiceImpl<BxThumbUp> implements IBxVisitHistoryService {

	private static Logger _logger = LoggerFactory.getLogger(BxVisitHistoryServiceImpl.class);
	@Autowired
	private BxVisitHistoryMapper bxVisitHistoryMapper;

    @Autowired
    private BxThumbUpMapper bxThumbUpMapper;

    @Override
	public void insert(BxVisitHistory bxVisitHistory) throws Exception {
		bxVisitHistoryMapper.insert(bxVisitHistory);
	}

    @Override
    public void insertThumbUp(BxThumbUp bxThumbUp) throws Exception{
        bxThumbUpMapper.insertThumbUp(bxThumbUp);
    }

    @Override
    public void cancelThumbUp(BxThumbUp bxThumbUp) throws Exception{
        bxThumbUpMapper.cancelThumbUp(bxThumbUp);
    }

    @Override
    public List<BxThumbUp> getThumbUpList(String openId,Integer memberId,Integer status) throws SelectException{
        return bxThumbUpMapper.getThumbUpList(openId,memberId,status);
    }
}
