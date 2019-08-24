package com.xdhc.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisUtil {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public <T> boolean set(String key, T value){
        try {
            //任意类型转换成String
            String val = JSONUtil.beanToString(value);
            System.out.println("set: " + key + val);
            if(val == null || val.length() <= 0){
                return false;
            }
            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public <T> T get(String key, Class<T> clazz){
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            System.out.println("get: " + key + value );
            return JSONUtil.stringToBean(value, clazz);
        } catch (Exception e){
            System.out.println("get: " + e);
            return null ;
        }
    }

}