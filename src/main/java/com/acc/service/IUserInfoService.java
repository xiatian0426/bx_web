package com.acc.service;

import com.acc.exception.InsertException;
import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface IUserInfoService extends IBaseService<UserInfo> {

	/**
	 * 保存 用户
	 * @param userInfo
	 * @throws InsertException
	 * @author TANGCY
	 */
	void  insert(UserInfo userInfo) throws InsertException;

	/**
	 * 获取全部用户信息
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
	List<UserInfo> getAll() throws SelectException;
	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
    UserInfo getById(String userId) throws SelectException;

	/**
	 * 修改用户-根据ID
	 * @param userInfo
	 * @throws UpdateException
	 * @author TANGCY
	 * @since 2016年10月25日
	 */
	void update(UserInfo userInfo) throws UpdateException;

    void updateImg(UserInfo userInfo) throws UpdateException;

    void updateOpenIdWeb(UserInfo userInfo) throws UpdateException;

	/**
	 * 根据用户名称查询用户
	 * @param userName
	 * @return
	 * @throws SelectException
	 */
    UserInfo getByUserName(String userName) throws SelectException;

    /**
     * 根据小程序后台openid查询用户
     * @param openIdWeb
     * @return
     * @throws SelectException
     */
    UserInfo getByOpenIdWeb(String openIdWeb) throws SelectException;

	public Map<Integer, UserInfo> getAllMap() throws SelectException;

	/**
	 * 修改用户状态
	 * @throws UpdateException
	 */
	void  updateUserStatus(String userId, String status)  throws UpdateException;
	/**
	 * 根据条件获取用户信息
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
	List<UserInfo> getAllByMap(Map<String, Object> map) throws SelectException;
}
