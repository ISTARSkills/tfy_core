package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * JobPanelistMapping entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.JobPanelistMapping
 * @author MyEclipse Persistence Tools
 */
public class JobPanelistMappingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobPanelistMappingDAO.class);
	// property constants

	public void save(JobPanelistMapping transientInstance) {
		log.debug("saving JobPanelistMapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(JobPanelistMapping persistentInstance) {
		log.debug("deleting JobPanelistMapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public JobPanelistMapping findById(java.lang.Integer id) {
		log.debug("getting JobPanelistMapping instance with id: " + id);
		try {
			JobPanelistMapping instance = (JobPanelistMapping) getSession()
					.get("com.viksitpro.core.dao.entities.JobPanelistMapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<JobPanelistMapping> findByExample(JobPanelistMapping instance) {
		log.debug("finding JobPanelistMapping instance by example");
		try {
			List<JobPanelistMapping> results = (List<JobPanelistMapping>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.JobPanelistMapping").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding JobPanelistMapping instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from JobPanelistMapping as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all JobPanelistMapping instances");
		try {
			String queryString = "from JobPanelistMapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public JobPanelistMapping merge(JobPanelistMapping detachedInstance) {
		log.debug("merging JobPanelistMapping instance");
		try {
			JobPanelistMapping result = (JobPanelistMapping) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(JobPanelistMapping instance) {
		log.debug("attaching dirty JobPanelistMapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(JobPanelistMapping instance) {
		log.debug("attaching clean JobPanelistMapping instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}