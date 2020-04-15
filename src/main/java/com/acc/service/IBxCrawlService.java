package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxCrawl;
import com.acc.model.BxHonor;

import java.util.List;

public interface IBxCrawlService extends IBaseService<BxCrawl>{

    List<BxCrawl> getCrawlList(String source) throws SelectException;

}
