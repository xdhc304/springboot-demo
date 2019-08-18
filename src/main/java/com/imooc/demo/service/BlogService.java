package com.imooc.demo.service;

import com.imooc.demo.entity.Blog;

import java.util.List;

public interface BlogService {

	/**
	 * 获取区域列表
	 * 
	 * @return
	 */
	List<Blog> getBlogList();

	/**
	 * 通过区域Id获取区域信息
	 * 
	 * @param blogId
	 * @return
	 */
	Blog getBlogById(int blogId);

	/**
	 * 增加区域信息
	 * 
	 * @param blog
	 * @return
	 */
	boolean addBlog(Blog blog);

	/**
	 * 修改区域信息
	 * 
	 * @param blog
	 * @return
	 */
	boolean modifyBlog(Blog blog);

	/**
	 * 删除区域信息
	 * 
	 * @param blogId
	 * @return
	 */
	boolean deleteBlog(int blogId);

}
