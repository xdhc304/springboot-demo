package com.xdhc.demo.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.pagehelper.Page;
import com.xdhc.demo.entity.Blog;
import com.xdhc.demo.entity.PageInfo;
import com.xdhc.demo.service.BlogService;
import com.xdhc.demo.entity.Result;
import com.xdhc.demo.util.RedisUtil;
import com.xdhc.demo.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "BlogController")
@RestController
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private BlogService blogService;

	private static final Logger logger  =  LoggerFactory.getLogger(BlogController.class);

	/**
	 * 获取所有的博客信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "listBlog")
	@RequestMapping(value = "/listblog", method = RequestMethod.GET)
	private Result<Map<String, Object>> listBlog() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Blog> list = new ArrayList<Blog>();
		try {
			logger.info("===== blogList: " + redisUtil.get("blogList", List.class) + " =====");
		} catch(Exception e) {
			logger.error("error", e);
		}

		List blogList = redisUtil.get("blogList", List.class);
		if (null != blogList) {
			logger.info("===== blogList get from redis =====");
			modelMap.put("blogList", blogList);
		} else {
			// 获取博客列表
			list = blogService.getBlogList();
			modelMap.put("blogList", list);
			redisUtil.set("blogList", list);
			logger.info("===== blogList get from MySQL =====");
		}
		return ResultUtil.success(modelMap);
	}

	@ApiOperation(value = "listBlogByPage")
	@RequestMapping(value = "/listblogbypage", method = RequestMethod.GET)
	public Result<PageInfo<Blog>> listBlogByPage(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
										 @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){

		Page<Blog> articles = blogService.getBlogListByPage(pageNum, pageSize);
		// 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
		PageInfo<Blog> pageInfo = new PageInfo<>(articles);
		return ResultUtil.success(pageInfo);
	}

	/**
	 * 通过博客Id获取博客信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getblogbyid", method = RequestMethod.GET)
	private Result<Map<String, Object>> getBlogById(Integer blogId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取博客信息
		Blog blog = blogService.getBlogById(blogId);
		modelMap.put("blog", blog);
		return ResultUtil.success(modelMap);
	}

	/**
	 * 通过博客Id获取博客信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/getblogbyid/{blogId}", method = RequestMethod.GET)
	private Result<Map<String, Object>> getBlogById2(@PathVariable Integer blogId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取博客信息
		Blog blog = blogService.getBlogById(blogId);
		modelMap.put("blog", blog);
		return ResultUtil.success(modelMap);
	}

	/**
	 * 添加博客信息
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
		// 添加博客信息
		modelMap.put("success", blogService.addBlog(blog));
		return modelMap;
	}

	/**
	 * 修改博客信息，主要修改名字
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
		// 修改博客信息
		modelMap.put("success", blogService.modifyBlog(blog));
		return modelMap;
	}

	@RequestMapping(value = "/removeblog", method = RequestMethod.GET)
	private Map<String, Object> removeBlog(Integer blogId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 修改博客信息
		modelMap.put("success", blogService.deleteBlog(blogId));
		return modelMap;
	}

}
