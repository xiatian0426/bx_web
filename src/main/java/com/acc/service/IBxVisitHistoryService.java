package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxThumbUp;
import com.acc.model.BxVisitHistory;

import java.util.List;

public interface IBxVisitHistoryService extends IBaseService<BxThumbUp>{

    void insert(BxVisitHistory bxVisitHistory) throws Exception;

    void insertThumbUp(BxThumbUp bxThumbUp) throws Exception;

    void cancelThumbUp(BxThumbUp bxThumbUp) throws Exception;

    List<BxThumbUp> getThumbUpList(String openId,Integer memberId,Integer status) throws SelectException;
}
