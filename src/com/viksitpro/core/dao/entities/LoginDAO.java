package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Login
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Login
 * @author MyEclipse Persistence Tools
 */
public class LoginDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LoginDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String JSESSION_ID = "jsessionId";
	public static final String ACTION = "action";

	public void save(Login transientInstance) {
		log.debug("saving Login instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Login persistentInstance) {
		log.debug("deleting Login instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Login findById(java.lang.Integer id) {
		log.debug("getting Login instance with id: " + id);
		try {
			Login instance = (Login) getSession().get("com.viksitpro.core.dao.entities.Login", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Login> findByExample(Login instance) {
		log.debug("finding Login instance by example");
		try {
			List<Login> results = (List<Login>) getSession().createCriteria("com.viksitpro.core.dao.entities.Login")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Login instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Login as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Login> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Login> findByJsessionId(Object jsessionId) {
		return findByProperty(JSESSION_ID, jsessionId);
	}

	public List<Login> findByAction(Object action) {
		return findByProperty(ACTION, action);
	}

	public List findAll() {
		log.debug("finding all Login instances");
		try {
			String queryString = "from Login";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Login merge(Login detachedInstance) {
		log.debug("merging Login instance");
		try {
			Login result = (Login) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Login instance) {
		log.debug("attaching dirty Login instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Login instance) {
		log.debug("attaching clean Login instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}