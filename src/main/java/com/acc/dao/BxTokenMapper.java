package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxToken;

public interface BxTokenMapper {

    BxToken getToken() throws SelectException;

    void delete() throws Exception;

    void insert(BxToken bxToken) throws Exception;
}
