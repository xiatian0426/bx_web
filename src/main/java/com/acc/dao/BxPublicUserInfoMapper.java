package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxPublicUserInfo;
import com.acc.model.BxToken;

public interface BxPublicUserInfoMapper {

    BxPublicUserInfo getUserInfoByOpenId(String openid) throws SelectException;

    void savePublicUserInfo(BxPublicUserInfo bxPublicUserInfo) throws Exception;

    void updatePublicUserInfo(BxPublicUserInfo bxPublicUserInfo) throws Exception;
}
