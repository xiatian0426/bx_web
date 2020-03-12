package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxThumbUp;
import com.acc.model.BxVisitHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxThumbUpMapper {

    void insertThumbUp(BxThumbUp bxThumbUp) throws Exception;

    void cancelThumbUp(BxThumbUp bxThumbUp) throws Exception;

    List<BxThumbUp> getThumbUpList(@Param("openId") String openId, @Param("memberId") Integer memberId,@Param("status") Integer status) throws SelectException;
}
