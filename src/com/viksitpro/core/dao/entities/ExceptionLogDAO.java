package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * ExceptionLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.ExceptionLog
 * @author MyEclipse Persistence Tools
 */
public class ExceptionLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ExceptionLogDAO.class);
	// property constants
	public static final String EXCEPTION_TYPE = "exceptionType";
	public static final String DESCRIPTION = "description";
	public static final String EXCEPTION_COMPONENT = "exceptionComponent";
	public static final String EVENT_ID = "eventId";
	public static final String LEVEL = "level";

	public void save(ExceptionLog transientInstance) {
		log.debug("saving ExceptionLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExceptionLog persistentInstance) {
		log.debug("deleting ExceptionLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExceptionLog findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting ExceptionLog instance with id: " + id);
		try {
			ExceptionLog instance = (ExceptionLog) ss.get("com.viksitpro.core.dao.entities.ExceptionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<ExceptionLog> findByExample(ExceptionLog instance) {
		log.debug("finding ExceptionLog instance by example");
		try {
			List<ExceptionLog> results = (List<ExceptionLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.ExceptionLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding ExceptionLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ExceptionLog as model where model." + propertyName + "= ?";
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

	public List<ExceptionLog> findByExceptionType(Object exceptionType) {
		return findByProperty(EXCEPTION_TYPE, exceptionType);
	}

	public List<ExceptionLog> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<ExceptionLog> findByExceptionComponent(Object exceptionComponent) {
		return findByProperty(EXCEPTION_COMPONENT, exceptionComponent);
	}

	public List<ExceptionLog> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List<ExceptionLog> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findAll() {
		log.debug("finding all ExceptionLog instances");
		try {
			String queryString = "from ExceptionLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExceptionLog merge(ExceptionLog detachedInstance) {
		log.debug("merging ExceptionLog instance");
		try {
			ExceptionLog result = (ExceptionLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExceptionLog instance) {
		log.debug("attaching dirty ExceptionLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExceptionLog instance) {
		log.debug("attaching clean ExceptionLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}