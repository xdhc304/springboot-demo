package com.imooc.demo.dao;

import com.imooc.demo.entity.Blog;

import java.util.List;

public interface BlogDao {
	/**
	 * 列出区域列表
	 * 
	 * @return blogList
	 */
	List<Blog> queryBlog();

	/**
	 * 根据Id列出具体区域
	 * 
	 * @return blog
	 */
	Blog queryBlogById(int blogId);

	/**
	 * 插入区域信息
	 * 
	 * @param blog
	 * @return
	 */
	int insertBlog(Blog blog);

	/**
	 * 更新区域信息
	 * 
	 * @param blog
	 * @return
	 */
	int updateBlog(Blog blog);

	/**
	 * 删除区域信息
	 * 
	 * @param blogId
	 * @return
	 */
	int deleteBlog(int blogId);
}
