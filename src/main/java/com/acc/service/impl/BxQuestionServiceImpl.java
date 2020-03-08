package com.acc.service.impl;

import com.acc.dao.BxQuestionMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxQuestion;
import com.acc.service.IBxQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxQuestionService")
@Transactional
public class BxQuestionServiceImpl extends BaseServiceImpl<BxQuestion> implements IBxQuestionService {

	private static Logger _logger = LoggerFactory.getLogger(BxQuestionServiceImpl.class);
	@Autowired
	private BxQuestionMapper bxQuestionMapper;

    @Override
    public void insert(BxQuestion bxQuestion) throws Exception {
        bxQuestionMapper.insert(bxQuestion);
    }

    @Override
    public void deleteById(String id) throws Exception{
        bxQuestionMapper.deleteById(id);
    }

    @Override
    public BxQuestion getQuestionById(String id) throws SelectException {
        return bxQuestionMapper.getQuestionById(id);
    }

}
