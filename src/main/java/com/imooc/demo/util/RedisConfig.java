package com.imooc.demo.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisConfig {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public <T> boolean set(String key, T value){
        try {
            //任意类型转换成String
            String val = beanToString(value);
            if(val == null || val.length() <= 0){
                return false;
            }
            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public <T> T get(String key, Class<T> clazz){
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return stringToBean(value, clazz);
        }catch (Exception e){
            return null ;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if(value == null || value.length() <= 0 || clazz == null) {
            return null;
        }

        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(value);
        }
        else if(clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(value);
        }
        else if(clazz == String.class) {
            return (T)value;
        }else {
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }

    /**
     *
     * @param Every T values
     * @param T 任意类型
     * @return String
     */
    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }

        Class <?> clazz = value.getClass();

        if(clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String)value;
        } else {
            return JSON.toJSONString(value);
        }
    }

}