package com.imooc.demo.service;

import com.github.pagehelper.Page;
import com.imooc.demo.entity.Blog;

import java.util.List;

public interface BlogService {

	/**
	 * 获取博客列表
	 * 
	 * @return
	 */
	List<Blog> getBlogList();

    /**
     * 分页获取博客列表
     *
     * @return
     */
    Page<Blog> getBlogListByPage(int pageNum, int pageSize);

	/**
	 * 通过博客Id获取博客信息
	 * 
	 * @param blogId
	 * @return
	 */
	Blog getBlogById(int blogId);

	/**
	 * 增加博客信息
	 * 
	 * @param blog
	 * @return
	 */
	boolean addBlog(Blog blog);

	/**
	 * 修改博客信息
	 * 
	 * @param blog
	 * @return
	 */
	boolean modifyBlog(Blog blog);

	/**
	 * 删除博客信息
	 * 
	 * @param blogId
	 * @return
	 */
	boolean deleteBlog(int blogId);

}
