package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxMember;
import org.apache.ibatis.annotations.Param;

public interface BxMemberMapper {

    BxMember getMemberByWechat(@Param("wechat") String wechat) throws SelectException;

    BxMember getMemberById(@Param("id") int id) throws SelectException;

    void updateMemberById(BxMember bxMember) throws Exception;

    void insert(BxMember bxMember) throws Exception;

    void deleteMemberById(int id) throws Exception;
}
