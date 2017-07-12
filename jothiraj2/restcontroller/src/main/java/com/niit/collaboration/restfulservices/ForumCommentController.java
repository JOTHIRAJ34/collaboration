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

import com.niit.collbackend.dao.ForumCommentDAO;
import com.niit.collbackend.model.ForumComment;

@RestController
public class ForumCommentController 
{
@Autowired ForumCommentDAO forumCommentDAO;
	
	@GetMapping("/forumComments")
	public List<ForumComment> list(){
		List<ForumComment> forumCommentList=forumCommentDAO.list();
		return forumCommentList;
		
	}
	
	@GetMapping("/forumComment/{forumComment_id}")
	public ForumComment getByForumCommentId(@PathVariable("forumComment_id") int id) {
		ForumComment forumCommentList = forumCommentDAO.getComment(id);

		return forumCommentList;
	}
	
	@PostMapping("/forumComment")
	public ResponseEntity<ForumComment> save(@RequestBody ForumComment forumComment) {
		forumCommentDAO.save(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	@PutMapping("/forumComment")
	public ResponseEntity<ForumComment> saveOrUpdate(@RequestBody ForumComment forumComment) {
		forumCommentDAO.saveOrUpdate(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/forumComment/{forumId}")
	public ResponseEntity<ForumComment> deleteforumComment(@PathVariable("forumId") int id) {
		ForumComment forumComment = forumCommentDAO.getComment(id);
		if (forumComment == null) {
			return new ResponseEntity("No ForumComment found for ForumId " + id, HttpStatus.NOT_FOUND);
		}
		forumCommentDAO.delete(forumComment);;
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}


