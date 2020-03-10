package com.acc.service;

import com.acc.model.BxThumbUp;
import com.acc.model.BxVisitHistory;

public interface IBxVisitHistoryService extends IBaseService<BxThumbUp>{

    void insert(BxVisitHistory bxVisitHistory) throws Exception;

    void insertThumbUp(BxThumbUp bxThumbUp) throws Exception;
}
