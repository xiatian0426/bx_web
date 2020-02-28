package com.acc.service.impl;

import com.acc.dao.UserInfoMapper;
import com.acc.exception.InsertException;
import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.model.UserInfo;
import com.acc.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService {

	private static Logger _logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public void insert(UserInfo userInfo) throws InsertException {
		userInfoMapper.insert(userInfo);
	}
	
	@Override
	public UserInfo getById(String userId) throws SelectException {
		try {
			int id = Integer.parseInt(userId);
			return userInfoMapper.getById(id);
		} catch (Exception ex) {
			_logger.error("[获取用户失败,无效的用户,ID="+userId+"]");
		}
		return null;
	}

	@Override
	public UserInfo getByUserName(String userName) throws SelectException {
		try {
			return userInfoMapper.getByUserName(userName);
		} catch (Exception ex) {
		    ex.printStackTrace();
			_logger.error("[获取用户失败,无效的用户,userName="+userName+"]");
		}
		return null;
	}

    @Override
    public UserInfo getByOpenIdWeb(String openIdWeb) throws SelectException{
        try {
            return userInfoMapper.getByOpenIdWeb(openIdWeb);
        } catch (Exception ex) {
            ex.printStackTrace();
            _logger.error("[获取用户失败,无效的用户,openid="+openIdWeb+"]");
        }
        return null;
    }

	@Override
	public List<UserInfo> getAll() throws SelectException {
		List<UserInfo> list = userInfoMapper.getAll();
		return list;
	}
	
	@Override
	public Map<Integer, UserInfo> getAllMap() throws SelectException {
		List<UserInfo> list = getAll();
		Map<Integer, UserInfo> userInfoDictMap = new HashMap<Integer, UserInfo>();
		for (UserInfo userInfo : list) {
			userInfoDictMap.put(userInfo.getId(), userInfo);
		}
		return userInfoDictMap;
	}
	
	@Override
	public void update(UserInfo userInfo) throws UpdateException {
		userInfoMapper.update(userInfo);
	}

    @Override
    public void updateImg(UserInfo userInfo) throws UpdateException {
        userInfoMapper.updateImg(userInfo);
    }

    @Override
    public void updateOpenIdWeb(UserInfo userInfo) throws UpdateException {
        userInfoMapper.updateOpenIdWeb(userInfo);
    }

	@Override
	public void updateUserStatus(String userId, String status) throws UpdateException {
		int id = 0;
		try {
			id = Integer.parseInt(userId);
		} catch (Exception ex) {
			_logger.error("[无效的用户,ID="+userId+"]");
			throw new UpdateException();
		}
		userInfoMapper.updateUserStatus(id, status);
	}
	@Override
	public List<UserInfo> getAllByMap (Map<String, Object> map) throws SelectException {
		List<UserInfo> list = userInfoMapper.getAllByMap(map);
		return list;
	}
}
