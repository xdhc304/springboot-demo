package com.xdhc.demo.service;

import com.xdhc.demo.service.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public interface UserService {

    UserModel getUserById(Integer id);

    void register(UserModel userModel);

    /*
    telphone:用户注册手机
    encrptPassowrd:用户加密后的密码
     */
    UserModel validateLogin(String telphone, String encrptPassword);
}
