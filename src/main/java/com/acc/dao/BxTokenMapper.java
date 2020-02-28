package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxToken;
import org.apache.ibatis.annotations.Param;

public interface BxTokenMapper {

    BxToken getToken(@Param("type") Integer type) throws SelectException;

    void delete(@Param("type") Integer type) throws Exception;

    void insert(BxToken bxToken) throws Exception;
}
