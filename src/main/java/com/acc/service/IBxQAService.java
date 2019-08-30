package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxQA;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBxQAService {
	/**
	 * QA信息
	 * @return
	 * @throws SelectException
	 */
    List<BxQA> getQAList(String memberId) throws SelectException;

    Integer getQACount(String memberId) throws SelectException;

    void deleteById(String id) throws Exception;

    BxQA getQAById(String id) throws SelectException;

    void updateById(BxQA bxQA) throws Exception;

    void insert(BxQA bxQA) throws Exception;
}
