package com.niit.collaboration.restfulservices;

import java.util.List;

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

@RestController
public class BlogCommentController {
	@Autowired
	BlogCommentDAO blogCommentDAO;

	@GetMapping("/blogcomments")
	public List<BlogComment> getBlogComments() {
		List<BlogComment> blogCommentList = blogCommentDAO.list();

		return blogCommentList;
	}

	@GetMapping("/blogcomment/{blogId}")
	public List<BlogComment> getByBlogCommentId(@PathVariable("blogId") int id) {
		List<BlogComment> blogComment = blogCommentDAO.getById(id);

		return blogComment;
	}
	
	@PostMapping("/blogcomment")
	public ResponseEntity<BlogComment> save(@RequestBody BlogComment blogComment) {
		blogCommentDAO.save(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	@PutMapping("/blogcomment")
	public ResponseEntity<BlogComment> update(@RequestBody BlogComment blogComment) {
		blogCommentDAO.saveOrUpdate(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/blogcomment/{blogId}")
	public ResponseEntity<BlogComment> deleteBlogComment(@PathVariable("blogId") int id) {
		BlogComment blogComment = blogCommentDAO.getByBId(id);
		if (blogComment == null) {
			return new ResponseEntity("No Blog found for ID " + id, HttpStatus.NOT_FOUND);
		}
		blogCommentDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}