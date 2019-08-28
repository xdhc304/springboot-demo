package com.xdhc.demo.service.impl;

import com.xdhc.demo.dao.UserDao;
import com.xdhc.demo.entity.User;
import com.xdhc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 注册
	 * @param user 参数封装
	 * @return Result
	 */
	public boolean regist(User user) {
		// 空值判断，主要是判断userName不为空
		if (user.getUserName() != null && !"".equals(user.getUserName()) && user.getPassword() != null && !"".equals(user.getPassword())) {
			// 设置默认值
			user.setCreateTime(new Date());
			user.setLastEditTime(new Date());
			try {
				User existUser = userDao.getUserByName(user.getUserName());
				if(existUser != null){
					//如果用户名已存在
					throw new RuntimeException("用户名已存在");
				} else {
					int effectedNum = userDao.regist(user);
					if (effectedNum > 0) {
						return true;
					} else {
						throw new RuntimeException("注册失败!");
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("注册失败:" + e.toString());
			}
		} else {
			throw new RuntimeException("注册信息不完整！");
		}
	}
	/**
	 * 登录
	 * @param user 用户名和密码
	 * @return Result
	 */
	public User login(User user) {
		// 空值判断，主要是判断userName不为空
		if (user.getUserName() != null && !"".equals(user.getUserName()) && user.getPassword() != null && !"".equals(user.getPassword())) {
			return userDao.login(user);
		} else {
			throw new RuntimeException("登录信息不完整！");
		}
	}
	/**
	 * 登录
	 * @param userName 用户名
	 * @return Result
	 */
	public User getUserByName(String userName) {
		// 空值判断，主要是判断userName不为空
		if (userName != null && !"".equals(userName)) {
			return userDao.getUserByName(userName);
		} else {
			throw new RuntimeException("userName为空！");
		}
	}

}
