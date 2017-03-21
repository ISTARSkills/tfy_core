package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RecruiterPanelistMapping entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.viksitpro.core.dao.entities.RecruiterPanelistMapping
 * @author MyEclipse Persistence Tools
 */
public class RecruiterPanelistMappingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RecruiterPanelistMappingDAO.class);
	// property constants

	public void save(RecruiterPanelistMapping transientInstance) {
		log.debug("saving RecruiterPanelistMapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RecruiterPanelistMapping persistentInstance) {
		log.debug("deleting RecruiterPanelistMapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecruiterPanelistMapping findById(java.lang.Integer id) {
		log.debug("getting RecruiterPanelistMapping instance with id: " + id);
		try {
			RecruiterPanelistMapping instance = (RecruiterPanelistMapping) getSession()
					.get("com.viksitpro.core.dao.entities.RecruiterPanelistMapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RecruiterPanelistMapping> findByExample(RecruiterPanelistMapping instance) {
		log.debug("finding RecruiterPanelistMapping instance by example");
		try {
			List<RecruiterPanelistMapping> results = (List<RecruiterPanelistMapping>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.RecruiterPanelistMapping").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RecruiterPanelistMapping instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from RecruiterPanelistMapping as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RecruiterPanelistMapping instances");
		try {
			String queryString = "from RecruiterPanelistMapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RecruiterPanelistMapping merge(RecruiterPanelistMapping detachedInstance) {
		log.debug("merging RecruiterPanelistMapping instance");
		try {
			RecruiterPanelistMapping result = (RecruiterPanelistMapping) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RecruiterPanelistMapping instance) {
		log.debug("attaching dirty RecruiterPanelistMapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecruiterPanelistMapping instance) {
		log.debug("attaching clean RecruiterPanelistMapping instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}