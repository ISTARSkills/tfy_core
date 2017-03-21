package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * JobRoleSkillBenchmark entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.JobRoleSkillBenchmark
 * @author MyEclipse Persistence Tools
 */
public class JobRoleSkillBenchmarkDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobRoleSkillBenchmarkDAO.class);
	// property constants

	public void save(JobRoleSkillBenchmark transientInstance) {
		log.debug("saving JobRoleSkillBenchmark instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(JobRoleSkillBenchmark persistentInstance) {
		log.debug("deleting JobRoleSkillBenchmark instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public JobRoleSkillBenchmark findById(com.viksitpro.core.dao.entities.JobRoleSkillBenchmarkId id) {
		log.debug("getting JobRoleSkillBenchmark instance with id: " + id);
		try {
			JobRoleSkillBenchmark instance = (JobRoleSkillBenchmark) getSession()
					.get("com.viksitpro.core.dao.entities.JobRoleSkillBenchmark", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<JobRoleSkillBenchmark> findByExample(JobRoleSkillBenchmark instance) {
		log.debug("finding JobRoleSkillBenchmark instance by example");
		try {
			List<JobRoleSkillBenchmark> results = (List<JobRoleSkillBenchmark>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.JobRoleSkillBenchmark").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding JobRoleSkillBenchmark instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from JobRoleSkillBenchmark as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all JobRoleSkillBenchmark instances");
		try {
			String queryString = "from JobRoleSkillBenchmark";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public JobRoleSkillBenchmark merge(JobRoleSkillBenchmark detachedInstance) {
		log.debug("merging JobRoleSkillBenchmark instance");
		try {
			JobRoleSkillBenchmark result = (JobRoleSkillBenchmark) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(JobRoleSkillBenchmark instance) {
		log.debug("attaching dirty JobRoleSkillBenchmark instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(JobRoleSkillBenchmark instance) {
		log.debug("attaching clean JobRoleSkillBenchmark instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}