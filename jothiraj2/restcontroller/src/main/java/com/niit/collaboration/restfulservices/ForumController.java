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

import com.niit.collbackend.dao.ForumDAO;
import com.niit.collbackend.model.Forum;

@RestController
public class ForumController {
	
		
		@Autowired ForumDAO forumDAO;
		
		@GetMapping("/forums")
		public List<Forum> list(){
			List<Forum> forumList=forumDAO.list();
			return forumList;
			
		}
		
		@GetMapping("/forum/{forum_id}")
		public ResponseEntity<Forum> getByForumId(@PathVariable("forum_id")int id ){
			Forum forum = forumDAO.get(id);
			if (forum == null) {
				return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		
		@GetMapping("/acceptedforum")
		public ResponseEntity<List<Forum>> acceptedForumsList() {
			List<Forum> listforum = forumDAO.getAcceptedList();
			return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
		}
		
		@GetMapping("/notAcceptedforum")
		public ResponseEntity<List<Forum>> notAcceptedForumList() {
			List<Forum> listforum = forumDAO.getNotAcceptedList();
			return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
		}
		
		@PostMapping("/forum")
		public ResponseEntity<Forum> save(@RequestBody Forum forum) {
			forumDAO.save(forum);
			return new ResponseEntity(forum, HttpStatus.OK);
		}

		@PutMapping("/forum")
		public ResponseEntity<Forum> update(@RequestBody Forum forum) {
			forumDAO.saveOrUpdate(forum);
			return new ResponseEntity(forum, HttpStatus.OK);
		}
		
		@PutMapping("/acceptForum")
		public ResponseEntity acceptForum(@RequestBody Forum forum){
			forum.setStatus("A");
			forumDAO.saveOrUpdate(forum);
			return new ResponseEntity(forum, HttpStatus.OK);
		}

		@DeleteMapping("/forum/{forum_id}")
		public ResponseEntity<Forum> deleteforum(@PathVariable("forum_id") int id) {
			Forum forum = forumDAO.get(id);
			if (forum == null) {
				return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
			}
			forumDAO.delete(id);
			return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

		}

	}


