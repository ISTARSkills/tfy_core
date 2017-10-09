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
 * UrlTable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.UrlTable
 * @author MyEclipse Persistence Tools
 */
public class UrlTableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UrlTableDAO.class);
	// property constants

	public void save(UrlTable transientInstance) {
		log.debug("saving UrlTable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UrlTable persistentInstance) {
		log.debug("deleting UrlTable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UrlTable findById(com.viksitpro.core.dao.entities.UrlTableId id) {
		Session ss = getSession();
		log.debug("getting UrlTable instance with id: " + id);
		try {
			UrlTable instance = (UrlTable) ss.get("com.viksitpro.core.dao.entities.UrlTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<UrlTable> findByExample(UrlTable instance) {
		log.debug("finding UrlTable instance by example");
		try {
			List<UrlTable> results = (List<UrlTable>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.UrlTable").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding UrlTable instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UrlTable as model where model." + propertyName + "= ?";
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

	public List findAll() {
		log.debug("finding all UrlTable instances");
		try {
			String queryString = "from UrlTable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UrlTable merge(UrlTable detachedInstance) {
		log.debug("merging UrlTable instance");
		try {
			UrlTable result = (UrlTable) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UrlTable instance) {
		log.debug("attaching dirty UrlTable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UrlTable instance) {
		log.debug("attaching clean UrlTable instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}