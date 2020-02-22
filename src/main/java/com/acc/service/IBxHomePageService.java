package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.*;

import java.util.List;

public interface IBxHomePageService extends IBaseService<BxMomment>{
	/**
	 * 根据id获取会员信息
	 * @return
	 * @throws SelectException
	 */
	BxMember getMemberByWechat(String wechat) throws SelectException;

    BxMember getMemberById(int id) throws SelectException;

    List<BxMemberTag> getMemberTagById(String memberId) throws SelectException;

    void updateMemberTag(BxMemberTag bxMemberTag) throws Exception;

    void saveMemberTag(BxMemberTag bxMemberTag) throws Exception;

    /**
     * 根据id获取所有评论
     * @return
     * @throws SelectException
     */
    List<BxMomment> getMommentListByWechat(String wechat) throws SelectException;
    List<BxMomment> getMommentListByMemberId(String memberId) throws SelectException;

    void insert(BxMomment bxMomment)  throws Exception;
    /**
     * 根据id获取评论总数
     * @return
     * @throws SelectException
     */
    Integer getCountByWechat(String wechat) throws SelectException;
    Integer getCountByMemberId(String memberId) throws SelectException;
    /**
     * 后台管理--更新个人信息
     * @param bxMember
     * @throws Exception
     */
    void updateMemberById(BxMember bxMember) throws Exception;

    void updateMommentStatus(BxMomment bxMomment) throws Exception;

    /**
     * 后台管理--更新个人信息
     * @param bxMember
     * @throws Exception
     */
    void addMember(BxMember bxMember) throws Exception;

    void deleteMemberById(int id) throws Exception;
}
