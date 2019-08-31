package com.xdhc.demo.handler;

import javax.servlet.http.HttpServletRequest;

import com.xdhc.demo.entity.Result;
import com.xdhc.demo.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理类
 *
 * @author xchc304
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

//	public static final String xdhc_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody

//	public Object errorHandler(HttpServletRequest reqest,
//							   HttpServletResponse response, Exception e) throws Exception {
//
//		e.printStackTrace();
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", e);
//		mav.addObject("url", reqest.getRequestURL());
//		mav.setViewName(xdhc_ERROR_VIEW);
//		return mav;
//	}

	public Result exceptionHandler(HttpServletRequest req, Exception e) throws Exception {

		e.printStackTrace();

//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		modelMap.put("success", false);
//		modelMap.put("code", -1);
//		modelMap.put("message", e.getMessage());
//		return modelMap;

		return ResultUtil.error(e.getMessage());
	}

}