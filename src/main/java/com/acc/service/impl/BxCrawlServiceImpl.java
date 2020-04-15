package com.acc.service.impl;

import com.acc.dao.BxCrawlMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxCrawl;
import com.acc.service.IBxCrawlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxCrawlService")
@Transactional
public class BxCrawlServiceImpl extends BaseServiceImpl<BxCrawl> implements IBxCrawlService {

	private static Logger _logger = LoggerFactory.getLogger(BxCrawlServiceImpl.class);
    @Autowired
    private BxCrawlMapper bxCrawlMapper;

    @Override
    public List<BxCrawl> getCrawlList(String source) throws SelectException {
        return bxCrawlMapper.getCrawlList(source);
    }
}
