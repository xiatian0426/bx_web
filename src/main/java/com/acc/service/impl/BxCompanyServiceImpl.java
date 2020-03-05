package com.acc.service.impl;

import com.acc.dao.BxCompanyMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxCompany;
import com.acc.service.IBxCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxCompanyService")
@Transactional
public class BxCompanyServiceImpl extends BaseServiceImpl<BxCompany> implements IBxCompanyService {

	private static Logger _logger = LoggerFactory.getLogger(BxCompanyServiceImpl.class);
    @Autowired
    private BxCompanyMapper bxCompanyMapper;

    @Override
    public List<BxCompany> getCompanyList(String memberId) throws SelectException {
        return bxCompanyMapper.getCompanyList(memberId);
    }

    @Override
    public BxCompany getCompanyById(String id) throws SelectException {
        return bxCompanyMapper.getCompanyById(id);
    }

    @Override
    public Integer getCompanyCount(String memberId) throws SelectException {
        return bxCompanyMapper.getCompanyCount(memberId);
    }

    @Override
    public void deleteById(String id) throws Exception {
        bxCompanyMapper.deleteById(id);
    }

    @Override
    public void insert(BxCompany bxCompany) throws Exception {
        bxCompanyMapper.insert(bxCompany);
    }

    @Override
    public void updateById(BxCompany bxCompany) throws Exception{
        bxCompanyMapper.updateById(bxCompany);
    }

}
