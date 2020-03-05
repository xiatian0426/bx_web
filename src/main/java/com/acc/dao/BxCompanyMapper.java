package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxCompanyMapper {

    List<BxCompany> getCompanyList(@Param("memberId") String memberId) throws SelectException;

    Integer getCompanyCount(@Param("memberId") String memberId) throws SelectException;

    void deleteById(@Param("id") String id) throws Exception;

    void insert(BxCompany bxCompany) throws Exception;

    BxCompany getCompanyById(String id) throws SelectException;

    void updateById(BxCompany bxCompany) throws Exception;
}
