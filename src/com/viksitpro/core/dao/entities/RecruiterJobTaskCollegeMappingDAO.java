package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RecruiterJobTaskCollegeMapping entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.viksitpro.core.dao.entities.RecruiterJobTaskCollegeMapping
 * @author MyEclipse Persistence Tools
 */
public class RecruiterJobTaskCollegeMappingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RecruiterJobTaskCollegeMappingDAO.class);
	// property constants

	public void save(RecruiterJobTaskCollegeMapping transientInstance) {
		log.debug("saving RecruiterJobTaskCollegeMapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RecruiterJobTaskCollegeMapping persistentInstance) {
		log.debug("deleting RecruiterJobTaskCollegeMapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecruiterJobTaskCollegeMapping findById(java.lang.Integer id) {
		log.debug("getting RecruiterJobTaskCollegeMapping instance with id: " + id);
		try {
			RecruiterJobTaskCollegeMapping instance = (RecruiterJobTaskCollegeMapping) getSession()
					.get("com.viksitpro.core.dao.entities.RecruiterJobTaskCollegeMapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RecruiterJobTaskCollegeMapping> findByExample(RecruiterJobTaskCollegeMapping instance) {
		log.debug("finding RecruiterJobTaskCollegeMapping instance by example");
		try {
			List<RecruiterJobTaskCollegeMapping> results = (List<RecruiterJobTaskCollegeMapping>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.RecruiterJobTaskCollegeMapping")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug(
				"finding RecruiterJobTaskCollegeMapping instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from RecruiterJobTaskCollegeMapping as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RecruiterJobTaskCollegeMapping instances");
		try {
			String queryString = "from RecruiterJobTaskCollegeMapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RecruiterJobTaskCollegeMapping merge(RecruiterJobTaskCollegeMapping detachedInstance) {
		log.debug("merging RecruiterJobTaskCollegeMapping instance");
		try {
			RecruiterJobTaskCollegeMapping result = (RecruiterJobTaskCollegeMapping) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RecruiterJobTaskCollegeMapping instance) {
		log.debug("attaching dirty RecruiterJobTaskCollegeMapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecruiterJobTaskCollegeMapping instance) {
		log.debug("attaching clean RecruiterJobTaskCollegeMapping instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}