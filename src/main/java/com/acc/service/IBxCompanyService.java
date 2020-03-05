package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxCompany;

import java.util.List;

public interface IBxCompanyService extends IBaseService<BxCompany>{
	/**
	 * 招聘信息
	 * @return
	 * @throws SelectException
	 */
    List<BxCompany> getCompanyList(String memberId) throws SelectException;

    BxCompany getCompanyById(String id) throws SelectException;

    Integer getCompanyCount(String memberId) throws SelectException;

    void deleteById(String id) throws Exception;

    void insert(BxCompany bxCompany) throws Exception;

    void updateById(BxCompany bxCompany) throws Exception;
}
