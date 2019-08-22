package com.imooc.demo.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.imooc.demo.entity.Blog;
import com.imooc.demo.service.BlogService;
import com.imooc.demo.entity.Result;
import com.imooc.demo.util.RedisConfig;
import com.imooc.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	private RedisConfig redisConfig;
	private static final Logger logger  =  LoggerFactory.getLogger(BlogController.class);

	/**
	 * 获取所有的区域信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listblog", method = RequestMethod.GET)
	private Result<Map<String, Object>> listBlog() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Blog> list = new ArrayList<Blog>();
		try {
			logger.info("redisConfig" + redisConfig.get("blogList", List.class));
		} catch(Exception e) {
			logger.error("error", e);
		}

//		List<Blog> blogList = redisConfig.get("blogList", List.class);
//		System.out.println("blogList" + blogList);
//		if (null != blogList) {
//			list = blogList;
//			modelMap.put("blogList", list);
//			System.out.println("blogList get from redis");
//		} else {
			// 获取区域列表
			list = blogService.getBlogList();
			modelMap.put("blogList", list);
//			redisConfig.set("blogList", list);
			logger.info("blogList get from MySQL");
//		}
		return ResultUtil.success(modelMap);
	}

	/**
	 * 通过区域Id获取区域信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getblogbyid", method = RequestMethod.GET)
	private Map<String, Object> getBlogById(Integer blogId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取区域信息
		Blog blog = blogService.getBlogById(blogId);
		modelMap.put("blog", blog);
		return modelMap;
	}

	/**
	 * 添加区域信息
	 * 
	 * @param blog
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/addblog", method = RequestMethod.POST)
	private Map<String, Object> addBlog(@RequestBody Blog blog)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 添加区域信息
		modelMap.put("success", blogService.addBlog(blog));
		return modelMap;
	}

	/**
	 * 修改区域信息，主要修改名字
	 * 
	 * @param blog
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/modifyblog", method = RequestMethod.POST)
	private Map<String, Object> modifyBlog(@RequestBody Blog blog)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 修改区域信息
		modelMap.put("success", blogService.modifyBlog(blog));
		return modelMap;
	}

	@RequestMapping(value = "/removeblog", method = RequestMethod.GET)
	private Map<String, Object> removeBlog(Integer blogId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 修改区域信息
		modelMap.put("success", blogService.deleteBlog(blogId));
		return modelMap;
	}

}
