package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxMomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxMommentMapper {

    List<BxMomment> getMommentListByWechat(@Param("wechat") String wechat) throws SelectException;
    List<BxMomment> getMommentListByMemberId(@Param("memberId") String memberId) throws SelectException;

    void insert(BxMomment bxMomment)  throws Exception;

    Integer getCountByWechat(@Param("wechat") String wechat) throws SelectException;
    Integer getCountByMemberId(@Param("memberId") String memberId) throws SelectException;
}
