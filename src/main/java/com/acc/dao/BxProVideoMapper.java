package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxProVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxProVideoMapper {

    List<BxProVideo> getProVideoList(@Param("memberId") String memberId) throws SelectException;

    Integer getProVideoCount(@Param("memberId") String memberId) throws SelectException;

    void deleteById(@Param("id") String id) throws Exception;

    void insert(BxProVideo bxProVideo) throws Exception;

    BxProVideo getProVideoById(String id) throws SelectException;
}
