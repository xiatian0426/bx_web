package com.acc.service.impl;

import com.acc.dao.*;
import com.acc.exception.SelectException;
import com.acc.model.BxQA;
import com.acc.service.IBxQAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxQAService")
@Transactional
public class BxQAServiceImpl extends BaseServiceImpl<BxQA> implements IBxQAService {

	private static Logger _logger = LoggerFactory.getLogger(BxQAServiceImpl.class);
	@Autowired
	private BxQAMapper bxQAMapper;

    @Override
    public List<BxQA> getQAList(String memberId) throws SelectException {
        return bxQAMapper.getQAList(memberId);
    }

    @Override
    public Integer getQACount(String memberId) throws SelectException {
        return bxQAMapper.getQACount(memberId);
    }

    @Override
    public void deleteById(String id) throws Exception{
        bxQAMapper.deleteById(id);
    }

    @Override
    public BxQA getQAById(String id) throws SelectException {
        return bxQAMapper.getQAById(id);
    }

    @Override
    public void updateById(BxQA bxQA) throws Exception{
        bxQAMapper.updateById(bxQA);
    }

    @Override
    public void insert(BxQA bxQA) throws Exception {
        bxQAMapper.insert(bxQA);
    }
}
