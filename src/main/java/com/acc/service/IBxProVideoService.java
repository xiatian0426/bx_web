package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxProVideo;

import java.util.List;

public interface IBxProVideoService {
	/**
	 * 招聘信息
	 * @return
	 * @throws SelectException
	 */
    List<BxProVideo> getProVideoList(String memberId) throws SelectException;

    BxProVideo getProVideoById(String id) throws SelectException;

    Integer getProVideoCount(String memberId) throws SelectException;

    void deleteById(String id) throws Exception;

    void insert(BxProVideo BxProVideo) throws Exception;

    BxProVideo getProVideoBymemberId(int memberId) throws SelectException;

    void updateById(BxProVideo BxProVideo) throws Exception;
}
