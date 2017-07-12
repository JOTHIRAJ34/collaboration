package com.niit.collbackend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collbackend.dao.BlogCommentDAO;
import com.niit.collbackend.dao.BlogDAO;
import com.niit.collbackend.dao.ForumCommentDAO;
import com.niit.collbackend.dao.ForumDAO;
import com.niit.collbackend.dao.FriendDAO;
import com.niit.collbackend.dao.JobDAO;
import com.niit.collbackend.dao.UserDAO;
import com.niit.collbackend.model.Blog;
import com.niit.collbackend.model.BlogComment;
import com.niit.collbackend.model.Forum;
import com.niit.collbackend.model.ForumComment;
import com.niit.collbackend.model.Friend;
import com.niit.collbackend.model.Job;
import com.niit.collbackend.model.User;

public class UserTestCase 
   
{
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collbackend");
		context.refresh();
		
		BlogDAO blogDAO = (BlogDAO) context.getBean("BlogDAO");
		BlogCommentDAO blogCommentDAO = (BlogCommentDAO) context.getBean("BlogCommentDAO");
		ForumDAO forumDAO = (ForumDAO) context.getBean("ForumDAO");
		ForumCommentDAO forumCommentDAO = (ForumCommentDAO) context.getBean("forumCommentDAO");
		FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
		JobDAO jobDAO = (JobDAO) context.getBean("JobDAO");
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		
		Blog blog = (Blog) context.getBean("blog");
		BlogComment blogComment = (BlogComment) context.getBean("blogComment");
		Forum forum = (Forum) context.getBean("forum");
		ForumComment forumComment = (ForumComment) context.getBean("forumComment");
		Friend friend = (Friend) context.getBean("friend");
		Job job = (Job) context.getBean("job");
		User user = (User) context.getBean("user");
		
		
		/* BlogDATA */
		blog.setBlog_name("");
		blog.setUser_id(1);
		blog.setEmail_Id("");
		blog.setUser_name("");
		blog.setStatus("N");
		blog.setLikes(2);

		blogDAO.save(blog);
		
		
		/* BlogComment */
		blogComment.setBlog_name("AngularJS");
		blogComment.setMessage("message");
		blogComment.setUser_id(1);
		blogComment.setUser_name("Kishore");
		blogComment.setBlogId(2);

		blogCommentDAO.save(blogComment);
		
		
		/* Forum DATA */
		forum.setDescription("bye");
		forum.setTitle("hi");

		forumDAO.save(forum);
		
		
		/* ForumComment */
		forumComment.setEmail_id("Krishna@gmail.com");
		forumComment.setForumid(10);
		forumComment.setUser_name("Krishna");
		
		forumCommentDAO.save(forumComment);
		
		
		/*Friend*/
		friend.setFriendName("Rap");
		friend.setStatus("Y");
		
		friendDAO.save(friend);
		
		
		/*Job*/
		job.setJobProfile("BE");
		job.setJobDescription("developer");
		
		jobDAO.save(job);
		
		
		/* User Table */
		user.setFirst_name("Jothi");
		user.setLast_name("Raj");
		user.setPassword("123");
		user.setEmail_id("jothiraj34@gmail.com");
		user.setDob("19-10-1993");
		user.setRole("User");
		user.setStatus("V");
		user.setIs_online("is_online");

		userDAO.create(user);
	
	}
	
    
}
