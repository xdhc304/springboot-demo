package com.xdhc.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class convertUtil {

    private static final Logger logger  =  LoggerFactory.getLogger(convertUtil.class);

    public static <T> T convertTFromPojo(Class<T> clazz, Object pojoBean) {

        if (pojoBean == null) {
            return null;
        }

        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(pojoBean, target);
            return target;
        } catch (Exception e) {
            logger.info("convertTFromPojo is error-->", e);
        }
        return null;
    }
}
