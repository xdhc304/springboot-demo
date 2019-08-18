package com.imooc.demo.service.impl;

import com.imooc.demo.dao.BlogDao;
import com.imooc.demo.entity.Blog;
import com.imooc.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao blogDao;

	@Override
	public List<Blog> getBlogList() {
		// 返回所有的区域信息
		return blogDao.queryBlog();
	}

	@Override
	public Blog getBlogById(int blogId) {
		return blogDao.queryBlogById(blogId);
	}

	@Transactional
	@Override
	public boolean addBlog(Blog blog) {
		// 空值判断，主要是判断blogName不为空
		if (blog.getBlogTitle() != null && !"".equals(blog.getBlogTitle())) {
			// 设置默认值
			blog.setCreateTime(new Date());
			blog.setLastEditTime(new Date());
			try {
				int effectedNum = blogDao.insertBlog(blog);
				if (effectedNum > 0) {
					return true;
				} else {
					throw new RuntimeException("添加博客信息失败!");
				}
			} catch (Exception e) {
				throw new RuntimeException("添加博客信息失败:" + e.toString());
			}
		} else {
			throw new RuntimeException("博客信息不能为空！");
		}
	}

	@Transactional
	@Override
	public boolean modifyBlog(Blog blog) {
		// 空值判断，主要是blogId不为空
		if (blog.getBlogId() != null && blog.getBlogId() > 0) {
			// 设置默认值
			blog.setLastEditTime(new Date());
			try {
				// 更新区域信息
				int effectedNum = blogDao.updateBlog(blog);
				if (effectedNum > 0) {
					return true;
				} else {
					throw new RuntimeException("更新博客信息失败!");
				}
			} catch (Exception e) {
				throw new RuntimeException("更新博客信息失败:" + e.toString());
			}
		} else {
			throw new RuntimeException("博客信息不能为空！");
		}
	}

	@Transactional
	@Override
	public boolean deleteBlog(int blogId) {
		if (blogId > 0) {
			try {
				// 删除区域信息
				int effectedNum = blogDao.deleteBlog(blogId);
				if (effectedNum > 0) {
					return true;
				} else {
					throw new RuntimeException("删除博客信息失败!");
				}
			} catch (Exception e) {
				throw new RuntimeException("删除博客域信息失败:" + e.toString());
			}
		} else {
			throw new RuntimeException("博客Id不能为空！");
		}
	}
}
