package com.acc.service.impl;

import com.acc.dao.*;
import com.acc.exception.SelectException;
import com.acc.model.BxRecruit;
import com.acc.service.IBxRecruitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxRecruitService")
@Transactional
public class BxRecruitServiceImpl implements IBxRecruitService {

	private static Logger _logger = LoggerFactory.getLogger(BxRecruitServiceImpl.class);
    @Autowired
    private BxRecruitMapper bxRecruitMapper;

    @Override
    public List<BxRecruit> getRecruitList(String memberId) throws SelectException {
        return bxRecruitMapper.getRecruitList(memberId);
    }

    @Override
    public BxRecruit getRecruitById(String id) throws SelectException {
        return bxRecruitMapper.getRecruitById(id);
    }

    @Override
    public Integer getRecruitCount(String memberId) throws SelectException {
        return bxRecruitMapper.getRecruitCount(memberId);
    }

    @Override
    public void deleteById(String id) throws Exception {
        bxRecruitMapper.deleteById(id);
    }

    @Override
    public void insert(BxRecruit bxRecruit) throws Exception {
        bxRecruitMapper.insert(bxRecruit);
    }

}
