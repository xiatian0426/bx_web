package com.acc.service.impl;

import com.acc.dao.BxHonorMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxHonor;
import com.acc.service.IBxHonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxHonorService")
@Transactional
public class BxHonorServiceImpl implements IBxHonorService {

	private static Logger _logger = LoggerFactory.getLogger(BxHonorServiceImpl.class);
    @Autowired
    private BxHonorMapper bxHonorMapper;

    @Override
    public List<BxHonor> getHonorList(String memberId) throws SelectException {
        return bxHonorMapper.getHonorList(memberId);
    }

    @Override
    public BxHonor getHonorById(int id) throws SelectException {
        return bxHonorMapper.getHonorById(id);
    }

    @Override
    public Integer getHonorCount(String memberId) throws SelectException {
        return bxHonorMapper.getHonorCount(memberId);
    }

    @Override
    public void deleteByMemId(int memberId) throws Exception {
        bxHonorMapper.deleteByMemId(memberId);
    }

    @Override
    public void insert(BxHonor bxHonor) throws Exception {
        bxHonorMapper.insert(bxHonor);
    }

    @Override
    public void updateById(BxHonor bxHonor) throws Exception {
        bxHonorMapper.updateById(bxHonor);
    }

}
