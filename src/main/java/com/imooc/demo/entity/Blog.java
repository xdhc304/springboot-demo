package com.imooc.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 区域信息
 * @author xdhc304
 *
 */
public class Blog {
	// 主键ID
	@ApiModelProperty(value = "博客id")
	private Integer blogId;
	// 作者
	private String blogAuthor;
	// 名称
	private String blogTitle;
	// Url
	private String blogUrl;
	// 平台
	private String blogPlatform;
	// 权重，越大越排前显示
	private Integer priority;
	// 创建时间
	private Date createTime;
	// 更新时
	private Date lastEditTime;

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogAuthor() {
		return blogAuthor;
	}

	public void setBlogAuthor(String blogAuthor) {
		this.blogAuthor = blogAuthor;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getBlogPlatform() {
		return blogPlatform;
	}

	public void setBlogPlatform(String blogPlatform) {
		this.blogPlatform = blogPlatform;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}
