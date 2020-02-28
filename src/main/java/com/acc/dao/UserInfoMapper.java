package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo>{

    UserInfo getById(@Param("id") int id) throws SelectException;

    UserInfo getByUserName(@Param("userName") String userName) throws SelectException;

    UserInfo getByOpenIdWeb(@Param("openIdWeb") String openIdWeb) throws SelectException;
	
	List<UserInfo> getAll() throws SelectException;

	void update(UserInfo userInfo) throws UpdateException;

    void updateImg(UserInfo userInfo) throws UpdateException;

    void updateOpenIdWeb(UserInfo userInfo) throws UpdateException;

	void updateUserStatus(@Param("id") int id, @Param("status") String status) throws UpdateException;

	List<UserInfo> getAllByMap(Map<String, Object> map) throws SelectException;
}
