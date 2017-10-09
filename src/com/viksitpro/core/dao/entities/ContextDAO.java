package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextDAO extends BaseHibernateDAO  {
	
	private static final Logger log = LoggerFactory.getLogger(ContextDAO.class);
	// property constants
	public static final String TITLE = "title";
	
	public void save(Context transientInstance) {
		log.debug("saving Context instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Context persistentInstance) {
		log.debug("deleting Context instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Context findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Context instance with id: " + id);
		try {
			Context instance = (Context) ss.get("com.viksitpro.core.dao.entities.Context", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}
	
	public List<Context> findByExample(Context instance) {
		log.debug("finding Context instance by example");
		try {
			List<Context> results = (List<Context>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Context").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Context instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Context as model where model." + propertyName + "= ?";
			Query queryObject = ss.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Context> findByTitle (Object title) {
		return findByProperty(TITLE, title);
	}
	
	public List findAll() {
		log.debug("finding all Context instances");
		try {
			String queryString = "from Context";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Context merge(Context detachedInstance) {
		log.debug("merging Context instance");
		try {
			Context result = (Context) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Context instance) {
		log.debug("attaching dirty Context instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Context instance) {
		log.debug("attaching clean Context instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
