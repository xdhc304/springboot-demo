package com.xdhc.demo.dao;

import com.xdhc.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

	/**
	 * 用户注册信息
	 * 
	 * @param user
	 * @return
	 */
	int regist(User user);

	/**
	 * 用户登录信息
	 * 
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 查询用户信息
	 *
	 * @param username
	 * @return
	 */
	User getUserByName(String username);

}
