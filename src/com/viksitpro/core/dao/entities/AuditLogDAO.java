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
 * AuditLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.AuditLog
 * @author MyEclipse Persistence Tools
 */
public class AuditLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AuditLogDAO.class);
	// property constants
	public static final String ACTOR_ID = "actorId";
	public static final String MESSAGE = "message";

	public void save(AuditLog transientInstance) {
		log.debug("saving AuditLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AuditLog persistentInstance) {
		log.debug("deleting AuditLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AuditLog findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting AuditLog instance with id: " + id);
		try {
			AuditLog instance = (AuditLog) ss.get("com.viksitpro.core.dao.entities.AuditLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<AuditLog> findByExample(AuditLog instance) {
		log.debug("finding AuditLog instance by example");
		try {
			List<AuditLog> results = (List<AuditLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.AuditLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding AuditLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AuditLog as model where model." + propertyName + "= ?";
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

	public List<AuditLog> findByActorId(Object actorId) {
		return findByProperty(ACTOR_ID, actorId);
	}

	public List<AuditLog> findByMessage(Object message) {
		return findByProperty(MESSAGE, message);
	}

	public List findAll() {
		log.debug("finding all AuditLog instances");
		try {
			String queryString = "from AuditLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AuditLog merge(AuditLog detachedInstance) {
		log.debug("merging AuditLog instance");
		try {
			AuditLog result = (AuditLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AuditLog instance) {
		log.debug("attaching dirty AuditLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AuditLog instance) {
		log.debug("attaching clean AuditLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}