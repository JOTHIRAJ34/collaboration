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
public class JobController {
	@Autowired
	JobDAO jobDAO;

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	@GetMapping("/Jobs")
	public List<Job> getJob() {
		List<Job> jobList = jobDAO.list();
		return jobList;
	}

	@GetMapping("/Job/{jobid}")
	public ResponseEntity<Job> getJobByID(@PathVariable("jobid") int jobid) {

		Job job = jobDAO.getByJobId(jobid);
		if (job == null) {
			return new ResponseEntity("No Job found for jobid " + jobid, HttpStatus.NOT_FOUND);
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

	@DeleteMapping("/job/{jobid}")
	public ResponseEntity deleteJob(@PathVariable("jobid") int jobid) {
		Job job = jobDAO.getByJobId(jobid);
		if (job == null) {
			return new ResponseEntity("No Job found for jobid " + jobid, HttpStatus.NOT_FOUND);
		}
		jobDAO.delete(jobid);
		return new ResponseEntity("deleted for jobid " + jobid, HttpStatus.OK);

	}

}
