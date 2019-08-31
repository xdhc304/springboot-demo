package com.xdhc.demo.service;

import com.xdhc.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public interface UserService {

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    boolean regist(User user);
    /**
     * 登录
     * @param user
     * @return Result
     */
    User login(User user);
    /**
     * 通过用户名查询
     *
     * @param userName
     * @return
     */
    User getUserByName(String userName);
}
