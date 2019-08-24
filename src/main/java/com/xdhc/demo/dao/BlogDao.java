package com.xdhc.demo.dao;

import com.github.pagehelper.Page;
import com.xdhc.demo.entity.Blog;

import java.util.List;

public interface BlogDao {
	/**
	 * 列出博客列表
	 * 
	 * @return blogList
	 */
	List<Blog> queryBlog();

	/**
	 * 分页列出博客列表
	 *
	 * @return blogList
	 */
	Page<Blog> queryBlogByPage();

	/**
	 * 根据Id列出具体博客
	 * 
	 * @return blog
	 */
	Blog queryBlogById(int blogId);

	/**
	 * 插入博客信息
	 * 
	 * @param blog
	 * @return
	 */
	int insertBlog(Blog blog);

	/**
	 * 更新博客信息
	 * 
	 * @param blog
	 * @return
	 */
	int updateBlog(Blog blog);

	/**
	 * 删除博客信息
	 * 
	 * @param blogId
	 * @return
	 */
	int deleteBlog(int blogId);
}
