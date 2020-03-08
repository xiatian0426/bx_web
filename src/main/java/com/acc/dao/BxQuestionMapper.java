package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxQA;
import com.acc.model.BxQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxQuestionMapper {

    void deleteById(@Param("id") String id) throws Exception;

    BxQuestion getQuestionById(@Param("id") String id) throws SelectException;

    void insert(BxQuestion bxQuestion) throws Exception;
}
