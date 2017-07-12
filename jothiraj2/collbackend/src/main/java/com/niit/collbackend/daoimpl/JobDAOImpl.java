package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.JobDAO;
import com.niit.collbackend.model.Job;

@Repository("JobDAO")
@Transactional
public class JobDAOImpl implements JobDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Job> list() {
		List<Job> jobList = (List<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return jobList;
	}

	public void save(Job job) {
		sessionFactory.getCurrentSession().save(job);

	}

	public void saveOrUpdate(Job job) {
		sessionFactory.getCurrentSession().saveOrUpdate(job);

	}

	public Job getByJobId(int jobid) {
		Job listById = (Job) sessionFactory.getCurrentSession().get(Job.class, jobid);

		return listById;
	}

	public Job delete(int jobid) {
		Job jobtoDelete = new Job();
		jobtoDelete.setJobid(jobid);
		sessionFactory.getCurrentSession().delete(jobtoDelete);
		return jobtoDelete;

	}
}
