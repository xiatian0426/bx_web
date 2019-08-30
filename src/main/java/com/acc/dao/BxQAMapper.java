package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxQA;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxQAMapper {

    List<BxQA> getQAList(@Param("memberId") String memberId) throws SelectException;

    Integer getQACount(@Param("memberId") String memberId) throws SelectException;

    void deleteById(@Param("id") String id) throws Exception;

    BxQA getQAById(@Param("id") String id) throws SelectException;

    void updateById(BxQA bxQA) throws Exception;

    void insert(BxQA bxQA) throws Exception;
}
