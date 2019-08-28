package com.xdhc.demo.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xdhc.demo.entity.Result;
import com.xdhc.demo.entity.User;
import com.xdhc.demo.service.UserService;
import com.xdhc.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger  =  LoggerFactory.getLogger(UserController.class);

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    public Result<Map<String, Object>> regist(User user) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", userService.regist(user));
        return ResultUtil.success(modelMap);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result<Map<String, Object>> login(User user){
        User userObj = userService.login(user);
        logger.info("user", userObj);
        return ResultUtil.success("登录成功");
    }

}
