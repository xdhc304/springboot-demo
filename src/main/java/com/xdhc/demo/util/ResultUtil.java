package com.xdhc.demo.util;

import com.xdhc.demo.entity.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setMessage("success");
        result.setData(object);
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(-1);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
