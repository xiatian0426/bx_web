package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxRecruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBxRecruitService {
	/**
	 * 招聘信息
	 * @return
	 * @throws SelectException
	 */
    List<BxRecruit> getRecruitList(String memberId) throws SelectException;

    BxRecruit getRecruitById(String id) throws SelectException;

    Integer getRecruitCount(String memberId) throws SelectException;

    void deleteById(String id) throws Exception;

    void insert(BxRecruit bxRecruit) throws Exception;
}
