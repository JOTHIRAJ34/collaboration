package com.niit.collbackend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Blog")
public class Blog {
	@Id
	@GeneratedValue
	private int blogId;
	
	
	private String blog_name;
	private String title;
	private String description;
	private int user_id;
	private String email_Id;
	private String user_name;
	private Date createdate;
	private String Status;
	private int likes;

	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlog_name() {
		return blog_name;
	}
	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
