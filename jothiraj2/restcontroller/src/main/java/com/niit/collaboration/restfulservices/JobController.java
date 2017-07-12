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

import com.niit.collbackend.dao.JobDAO;
import com.niit.collbackend.model.Job;

@RestController
public class JobController 
{
@Autowired JobDAO jobDAO;
	
	@GetMapping("/Jobs")
	public List<Job> getJob() {
		List<Job> jobList = jobDAO.list();
		return jobList;
	}
	
	@GetMapping("/Job/{jobId}")
	public ResponseEntity<Job> getJobByID(@PathVariable("jobId") int id) {

		Job job = jobDAO.getByJobId(id);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PostMapping("/Job")
	public ResponseEntity<Job> save(@RequestBody Job job) {
		jobDAO.save(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PutMapping("/job")
	public ResponseEntity<Job> update(@RequestBody Job job) {
		jobDAO.saveOrUpdate(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@DeleteMapping("/job/{jobId}")
	public ResponseEntity deleteJob(@PathVariable("jobId") int id) {
		Job job = jobDAO.getByJobId(id);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
		}
		jobDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}
