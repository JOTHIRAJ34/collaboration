package com.niit.collaboration.restfulservices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collbackend.dao.BlogCommentDAO;
import com.niit.collbackend.model.BlogComment;
import com.niit.collbackend.model.User;

@RestController
public class BlogCommentController {
	@Autowired
	BlogCommentDAO blogCommentDAO;

	public BlogCommentDAO getBlogCommentDAO() {
		return blogCommentDAO;
	}
	
	public void setBlogCommentDAO(BlogCommentDAO blogCommentDAO) {
		this.blogCommentDAO = blogCommentDAO;
	}
	
	
	@GetMapping("/blogcomments")
	public List<BlogComment> getBlogComments() {
		List<BlogComment> blogCommentList = blogCommentDAO.list();

		return blogCommentList;
	}
	

	@GetMapping("/blogss/{blogId}")
	public ResponseEntity getBlogId(@PathVariable("blogId") int blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("blogId", blogId);
		System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		System.out.println(blogId);
		List listcomment = blogCommentDAO.getById(blogId);
		if (listcomment == null) {
			return new ResponseEntity("No Comment found for blogId " + blogId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listcomment, HttpStatus.OK);
	}
	
	@GetMapping("/blogcomment/{bcid}")
	public ResponseEntity getBlogCommentId(@PathVariable("bcid") int bcid) {

		BlogComment blogComment = blogCommentDAO.getByBId(bcid);
		if (blogComment == null) {
			return new ResponseEntity("No BlogComment found for bcid " + bcid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blogComment, HttpStatus.OK);
	}
	
	@PostMapping(value = "/blogcomments")
	public ResponseEntity createBlogComment(@RequestBody BlogComment blogComment, HttpSession session) {


		User user = (User) session.getAttribute("user");
		
		System.out.println(user.getEmail_id());
		
		blogComment.setUser_name(user.getUser_name());
		blogComment.setEmail_Id(user.getEmail_id());
		blogComment.setUser_id(user.getUserId());
		
		int blogId = (Integer) session.getAttribute("blogId");
		System.out.println(blogId);
	 blogComment.setBlogId(blogId);
		blogCommentDAO.save(blogComment);
		return new ResponseEntity(blogComment, HttpStatus.OK);
	}
	
	@PutMapping("/blogcomment")
	public ResponseEntity<BlogComment> update(@RequestBody BlogComment blogComment) {
		blogCommentDAO.saveOrUpdate(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/blogcomment/{bcid}")
	public ResponseEntity<BlogComment> deleteBlogComment(@PathVariable("bcid") int bcid) {
		BlogComment blogComment = blogCommentDAO.getByBId(bcid);
		if (blogComment == null) {
			return new ResponseEntity("No Blog found for bcid " + bcid, HttpStatus.NOT_FOUND);
		}
		blogCommentDAO.delete(bcid);
		return new ResponseEntity("deleted for bcid " + bcid, HttpStatus.OK);

	}

}