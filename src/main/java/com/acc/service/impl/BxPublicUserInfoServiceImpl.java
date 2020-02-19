package com.acc.service.impl;

import com.acc.dao.BxPublicUserInfoMapper;
import com.acc.dao.BxTokenMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxPublicUserInfo;
import com.acc.model.BxToken;
import com.acc.service.IBxPublicUserInfoService;
import com.acc.service.IBxTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxPublicUserInfoService")
@Transactional
public class BxPublicUserInfoServiceImpl implements IBxPublicUserInfoService {

	private static Logger _logger = LoggerFactory.getLogger(BxPublicUserInfoServiceImpl.class);
	@Autowired
	private BxPublicUserInfoMapper bxPublicUserInfoMapper;

    @Override
    public BxPublicUserInfo getUserInfoByOpenId(String openid) throws SelectException{
        return bxPublicUserInfoMapper.getUserInfoByOpenId(openid);
    }

    @Override
    public void savePublicUserInfo(BxPublicUserInfo bxPublicUserInfo) throws Exception{
        bxPublicUserInfoMapper.savePublicUserInfo(bxPublicUserInfo);
    }

    @Override
    public void updatePublicUserInfo(BxPublicUserInfo bxPublicUserInfo) throws Exception{
        bxPublicUserInfoMapper.updatePublicUserInfo(bxPublicUserInfo);
    }
}
