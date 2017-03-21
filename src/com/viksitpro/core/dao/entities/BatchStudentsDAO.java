package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BatchStudents entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.BatchStudents
 * @author MyEclipse Persistence Tools
 */
public class BatchStudentsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BatchStudentsDAO.class);
	// property constants
	public static final String USER_TYPE = "userType";

	public void save(BatchStudents transientInstance) {
		log.debug("saving BatchStudents instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BatchStudents persistentInstance) {
		log.debug("deleting BatchStudents instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BatchStudents findById(java.lang.Integer id) {
		log.debug("getting BatchStudents instance with id: " + id);
		try {
			BatchStudents instance = (BatchStudents) getSession().get("com.viksitpro.core.dao.entities.BatchStudents",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BatchStudents> findByExample(BatchStudents instance) {
		log.debug("finding BatchStudents instance by example");
		try {
			List<BatchStudents> results = (List<BatchStudents>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.BatchStudents").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BatchStudents instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from BatchStudents as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BatchStudents> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List findAll() {
		log.debug("finding all BatchStudents instances");
		try {
			String queryString = "from BatchStudents";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BatchStudents merge(BatchStudents detachedInstance) {
		log.debug("merging BatchStudents instance");
		try {
			BatchStudents result = (BatchStudents) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BatchStudents instance) {
		log.debug("attaching dirty BatchStudents instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BatchStudents instance) {
		log.debug("attaching clean BatchStudents instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}