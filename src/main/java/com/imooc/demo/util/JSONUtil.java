package com.imooc.demo.util;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String value, Class<T> clazz) {
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
            return JSON.parseObject(value, clazz);
        }
    }

    /**
     *
     * @param 'Every' T values
     * @param 'T'任意类型
     * @return String
     */
    public static <T> String beanToString(T value) {
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
