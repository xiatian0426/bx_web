package com.acc.service.impl;

import com.acc.dao.*;
import com.acc.exception.SelectException;
import com.acc.model.*;
import com.acc.service.IBxHomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service("bxHomePageService")
@Transactional
public class BxHomePageServiceImpl extends BaseServiceImpl<BxMomment> implements IBxHomePageService {

	private static Logger _logger = LoggerFactory.getLogger(BxHomePageServiceImpl.class);
	@Autowired
	private BxMemberMapper bxMemberMapper;
    @Autowired
    private BxMommentMapper bxMommentMapper;

    @Override
	public BxMember getMemberByWechat(String wechat) throws SelectException {
		return bxMemberMapper.getMemberByWechat(wechat);
	}
    @Override
    public BxMember getMemberById(int id) throws SelectException {
        return bxMemberMapper.getMemberById(id);
    }
    @Override
    public List<BxMomment> getMommentListByWechat(String wechat) throws SelectException{
        return bxMommentMapper.getMommentListByWechat(wechat);
    }
    @Override
    public List<BxMomment> getMommentListByMemberId(String memberId) throws SelectException{
        return bxMommentMapper.getMommentListByMemberId(memberId);
    }
    @Override
    public void insert(BxMomment bxMomment)  throws Exception{
        bxMommentMapper.insert(bxMomment);
    }
    @Override
    public Integer getCountByWechat(String wechat) throws SelectException{
        return bxMommentMapper.getCountByWechat(wechat);
    }
    @Override
    public Integer getCountByMemberId(String memberId) throws SelectException{
        return bxMommentMapper.getCountByMemberId(memberId);
    }
    @Override
    public void updateMemberById(BxMember bxMember) throws Exception {
        bxMemberMapper.updateMemberById(bxMember);
    }

    @Override
    public void updateMommentStatus(BxMomment bxMomment) throws Exception{
        bxMommentMapper.updateMommentStatus(bxMomment);
    }

    @Override
    public void addMember(BxMember bxMember) throws Exception {
        bxMemberMapper.insert(bxMember);
    }
    @Override
    public void deleteMemberById(int id) throws Exception {
        bxMemberMapper.deleteMemberById(id);
    }
}
