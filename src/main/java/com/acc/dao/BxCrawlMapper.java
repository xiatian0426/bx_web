package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxCrawl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxCrawlMapper {

    List<BxCrawl> getCrawlList(@Param("source") String source) throws SelectException;
}
