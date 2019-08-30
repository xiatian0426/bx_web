package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxHonor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBxHonorService {
	/**
	 * 荣誉信息
	 * @return
	 * @throws SelectException
	 */
    List<BxHonor> getHonorList(String memberId) throws SelectException;

    BxHonor getHonorById(int id) throws SelectException;

    Integer getHonorCount(String memberId) throws SelectException;

    void deleteByMemId(int memberId) throws Exception;

    void insert(BxHonor bxHonor) throws Exception;

    void updateById(BxHonor bxHonor) throws Exception;
}
