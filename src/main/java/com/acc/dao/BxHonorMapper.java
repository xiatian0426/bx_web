package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxHonor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxHonorMapper {

    List<BxHonor> getHonorList(@Param("memberId") String memberId) throws SelectException;

    Integer getHonorCount(@Param("memberId") String memberId) throws SelectException;

    void deleteByMemId(@Param("memberId") int memberId) throws Exception;

    void insert(BxHonor bxHonor) throws Exception;

    void updateById(BxHonor bxHonor) throws Exception;

    BxHonor getHonorById(int id) throws SelectException;
}
