package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxQA;
import com.acc.model.BxQuestion;

import java.util.List;

public interface IBxQuestionService extends IBaseService<BxQuestion>{

    void insert(BxQuestion bxQuestion) throws Exception;


    void deleteById(String id) throws Exception;

    BxQuestion getQuestionById(String id) throws SelectException;

}
