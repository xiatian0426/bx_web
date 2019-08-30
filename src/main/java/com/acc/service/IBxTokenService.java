package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxToken;

public interface IBxTokenService {

    BxToken getToken() throws SelectException;

    void updateToken(BxToken bxToken) throws Exception;
}
