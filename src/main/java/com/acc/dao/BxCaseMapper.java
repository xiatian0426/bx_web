package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxCase;
import com.acc.model.BxProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxCaseMapper {

    BxCase getCaseDetail(@Param("productId") String productId) throws SelectException;

    void deleteCaseByProId(@Param("productId") String productId) throws Exception;

    void updateCaseByProId(BxCase bxCase) throws Exception;

    void insertCaseByProId(BxCase bxCase) throws Exception;
}
