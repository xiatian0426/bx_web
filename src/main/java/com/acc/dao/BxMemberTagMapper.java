package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxMemberTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxMemberTagMapper {

    List<BxMemberTag> getMemberTagById(@Param("memberId") String memberId) throws SelectException;
}
