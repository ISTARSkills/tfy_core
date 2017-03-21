package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CollegeRecruiterMapping entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.CollegeRecruiterMapping
 * @author MyEclipse Persistence Tools
 */
public class CollegeRecruiterMappingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CollegeRecruiterMappingDAO.class);
	// property constants
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public void save(CollegeRecruiterMapping transientInstance) {
		log.debug("saving CollegeRecruiterMapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CollegeRecruiterMapping persistentInstance) {
		log.debug("deleting CollegeRecruiterMapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CollegeRecruiterMapping findById(java.lang.Integer id) {
		log.debug("getting CollegeRecruiterMapping instance with id: " + id);
		try {
			CollegeRecruiterMapping instance = (CollegeRecruiterMapping) getSession()
					.get("com.viksitpro.core.dao.entities.CollegeRecruiterMapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CollegeRecruiterMapping> findByExample(CollegeRecruiterMapping instance) {
		log.debug("finding CollegeRecruiterMapping instance by example");
		try {
			List<CollegeRecruiterMapping> results = (List<CollegeRecruiterMapping>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.CollegeRecruiterMapping").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CollegeRecruiterMapping instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from CollegeRecruiterMapping as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CollegeRecruiterMapping> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<CollegeRecruiterMapping> findByMessage(Object message) {
		return findByProperty(MESSAGE, message);
	}

	public List findAll() {
		log.debug("finding all CollegeRecruiterMapping instances");
		try {
			String queryString = "from CollegeRecruiterMapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CollegeRecruiterMapping merge(CollegeRecruiterMapping detachedInstance) {
		log.debug("merging CollegeRecruiterMapping instance");
		try {
			CollegeRecruiterMapping result = (CollegeRecruiterMapping) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CollegeRecruiterMapping instance) {
		log.debug("attaching dirty CollegeRecruiterMapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CollegeRecruiterMapping instance) {
		log.debug("attaching clean CollegeRecruiterMapping instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}