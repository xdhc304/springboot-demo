package com.imooc.demo.util;

import com.imooc.demo.entity.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(object);
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(message);
        return result;
    }
}
