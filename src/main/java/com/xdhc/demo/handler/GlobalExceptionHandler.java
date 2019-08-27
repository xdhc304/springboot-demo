package com.xdhc.demo.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理类
 *
 * @author xchc304
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//	public static final String xdhc_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
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

	public Map<String, Object> exceptionHandler(HttpServletRequest req, Exception e) throws Exception {

		e.printStackTrace();

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		modelMap.put("code", -1);
		modelMap.put("message", e.getMessage());
		modelMap.put("url", req.getRequestURL());
		return modelMap;
	}

}